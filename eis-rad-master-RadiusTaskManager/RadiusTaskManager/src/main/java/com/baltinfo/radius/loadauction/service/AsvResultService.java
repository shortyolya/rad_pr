package com.baltinfo.radius.loadauction.service;

import com.baltinfo.radius.db.constants.NonExecReasons;
import com.baltinfo.radius.db.constants.TypeAuctionCode;
import com.baltinfo.radius.db.constants.TypeStateConstant;
import com.baltinfo.radius.db.controller.AuctionController;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.model.DocFile;
import com.baltinfo.radius.db.model.Document;
import com.baltinfo.radius.db.model.VAsvApplication;
import com.baltinfo.radius.db.model.VAsvAuction;
import com.baltinfo.radius.db.model.VAsvLot;
import com.baltinfo.radius.db.model.VBaResults;
import com.baltinfo.radius.loadauction.constants.AsvTypeAuction;
import com.baltinfo.radius.loadauction.model.result.Applicant;
import com.baltinfo.radius.loadauction.model.result.AsvApplicantDto;
import com.baltinfo.radius.loadauction.model.result.AsvApplicantLegalStatus;
import com.baltinfo.radius.loadauction.model.result.AsvLotResult;
import com.baltinfo.radius.loadauction.model.result.AsvParticipantType;
import com.baltinfo.radius.loadauction.model.result.AsvTorgResult;
import com.baltinfo.radius.loadauction.model.result.Bank;
import com.baltinfo.radius.loadauction.model.result.InfoLot;
import com.baltinfo.radius.loadauction.model.result.Lot;
import com.baltinfo.radius.loadauction.model.result.Tender;
import com.baltinfo.radius.loadauction.model.result.TenderResult;
import com.baltinfo.radius.loadauction.model.result.TenderResults;
import com.baltinfo.radius.loadauction.model.result.Tenders;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AsvResultService {
    private static final Logger logger = LoggerFactory.getLogger(AsvResultService.class);
    private static final String BLOCK_TRADE_PREFIX = "B";
    private final AuctionController auctionController;
    private final LotController lotController;

    public AsvResultService(AuctionController auctionController, LotController lotController) {
        this.auctionController = auctionController;
        this.lotController = lotController;
    }

    public Result<List<VAsvAuction>, String> getAuctionStageWithResults(VBaResults blockAuction) {

        List<VAsvAuction> auctions = auctionController.getAuctionsByBaUnid(blockAuction.getBaUnid());
        Integer stageNum = null;
        for (VAsvAuction auction : auctions) {
            if (!auction.getTypeAuctionCode().equals(TypeAuctionCode.PUBLIC_SALE.getCode())) {
                if (auction.getTsUnid().equals(TypeStateConstant.TRADE_DONE.getId())
                        && auction.getEprResultsUnid() == null) {
                    stageNum = auction.getAuctionStageNum();
                    break;
                }
            } else {
                Boolean isExistsLotsWithResults = auctionController.isExistsLotsWithResults(auction.getAuctionUnid());
                if (isExistsLotsWithResults) {
                    stageNum = auction.getAuctionStageNum();
                    break;
                }
            }
        }

        if (stageNum == null) {
            return Result.error("Не удалось определить этап для выгрузки результатов.");
        }

        Integer finalStageNum = stageNum;
        List<VAsvAuction> auctionsByStage = auctions.stream()
                .filter(a -> a.getAuctionStageNum().equals(finalStageNum))
                .collect(Collectors.toList());

        return Result.ok(auctionsByStage);
    }

    public Result<TenderResults, String> formTenderResults(VBaResults blockAuction, List<Tender> tenderList) {
        try {
            Bank bank = new Bank(blockAuction.getDebtorName(), blockAuction.getDebtorInn(), blockAuction.getDebtorOgrn());
            String tradeId = blockAuction.getBaAsvId() != null && blockAuction.getBaAsvId().startsWith(BLOCK_TRADE_PREFIX)
                    ? blockAuction.getBaAsvId()
                    : "";
            Tenders tenders = new Tenders(tradeId, "АО \"РАД\"", "АО \"РАД\"", tenderList);
            TenderResults tenderResult = new TenderResults(new TenderResult(bank, tenders));
            return Result.ok(tenderResult);
        } catch (Exception ex) {
            logger.error("Error send results to asv by baAsvId = {}", blockAuction.getBaAsvId(), ex);
            return Result.error("Error send results to asv by baAsvId = " + blockAuction.getBaAsvId() + ". Error: " + ex.getMessage());
        }
    }

    public Tender formTender(VAsvAuction eisAuction, List<Lot> asvLots) {

        String asvTypeTender = AsvTypeAuction.getAsvCodeByEisCode(eisAuction.getTypeAuctionCode());

        Integer torgResult = TypeStateConstant.TRADE_DONE.getId().equals(eisAuction.getTsUnid())
                ? AsvTorgResult.DONE.getAsvCode()
                : (TypeStateConstant.TRADE_CANCELED.getId().equals(eisAuction.getTsUnid())
                ? AsvTorgResult.CANCELED.getAsvCode()
                : AsvTorgResult.NOT_DONE.getAsvCode());

        Tender tender = new Tender(eisAuction.getAuctionAsvIdWithoutStage(), asvTypeTender, torgResult, asvLots);
        return tender;
    }

    public Lot formAsvLot(VAsvLot eisLot, List<Applicant> applicants) {
        String successDate = null;
        String faultDate = null;
        String cancelReason = null;
        Integer asvLotResult;
        String reportDate = null;
        if (applicants != null && !applicants.isEmpty()) {
            reportDate = formatDate(eisLot.getProtocolApplDate());
        }
        if (isSingleParticipant(eisLot, applicants == null ? 0 : applicants.size())) {
            asvLotResult = AsvLotResult.SINGLE_PARTICIPANT.getCode();
            faultDate = formatDate(eisLot.getProtocolResultDate());
        } else if (eisLot.getTsUnid().equals(TypeStateConstant.LOT_DONE.getId())) {
            asvLotResult = AsvLotResult.TRADE_DONE.getCode();
            successDate = formatDate(eisLot.getProtocolResultDate());
        } else if (eisLot.getTsUnid().equals(TypeStateConstant.LOT_CANCEL.getId())) {
            asvLotResult = AsvLotResult.CANCELED.getCode();
            cancelReason = eisLot.getLotAuctionCancelCause();
            successDate = formatDate(eisLot.getProtocolResultDate());
        } else if (eisLot.getTsUnid().equals(TypeStateConstant.LOT_NOT_DONE.getId())) {
            asvLotResult = AsvLotResult.DIDNT_TAKE_PLACE.getCode();
            faultDate = formatDate(eisLot.getProtocolResultDate());
        } else {
            asvLotResult = AsvLotResult.RUNNING.getCode();
        }

        Long asvId = Long.parseLong(eisLot.getLotAsvId());
        InfoLot infoLot = new InfoLot(eisLot.getLotAsvStageId() != null && !eisLot.getLotAsvStageId().isEmpty()
                ? Long.parseLong(eisLot.getLotAsvStageId())
                : asvId);
        Lot lot = new Lot(asvId,
                eisLot.getLotEtpCode(),
                reportDate,
                asvLotResult,
                successDate,
                faultDate,
                cancelReason,
                Collections.singletonList(infoLot),
                applicants
        );

        return lot;
    }

    private boolean isSingleParticipant(VAsvLot eisLot, int applicationCount) {
        return (eisLot.getTsUnid().equals(TypeStateConstant.LOT_NOT_DONE.getId())
                && eisLot.getNonExecReasonUnid() != null
                && eisLot.getNonExecReasonUnid().equals(NonExecReasons.ONE_APPLICATION.getUnid()))
                || (!eisLot.getTypeAuctionCode().equals(TypeAuctionCode.PUBLIC_SALE.getCode())
                && eisLot.getTsUnid().equals(TypeStateConstant.LOT_DONE.getId())
                && applicationCount == 1);
    }

    public List<AsvApplicantDto> formApplicants(List<VAsvApplication> eisApplications, VAsvLot eisLot) {
        boolean isOneApplication = isSingleParticipant(eisLot, eisApplications == null ? 0 : eisApplications.size());
        List<AsvApplicantDto> applicants = new ArrayList<>();
        VAsvApplication second = eisApplications.stream()
                .filter(app -> app.getApplicatAmount() != null)
                .sorted(Comparator.comparing(VAsvApplication::getApplicatAmount).reversed())
                .skip(1)
                .findFirst()
                .orElse(null);

        for (VAsvApplication eisAppl : eisApplications) {
            Applicant.ApplicantBuilder builder = Applicant.builder()
                    .withRadId(eisAppl.getSubUnid())
                    .withLegalStatus(AsvApplicantLegalStatus.getByEisCode(eisAppl.getTypesUnid()).getAsvCode())
                    .withName(eisAppl.getShSubName())
                    .withInn(eisAppl.getShSubInn())
                    .withApplicationNumber(eisAppl.getApplicatNumber())
                    .withApplicationDate(formatTimestamp(eisAppl.getApplicatAppTime()))
                    .withPeriod(eisAppl.getRedSchedPeriodNumber());
            if (eisLot.getTypeAuctionCode().equals(TypeAuctionCode.PUBLIC_SALE.getCode())) {
                builder.withAccountedDeposit(eisAppl.getRedSchedDepSum());
            } else {
                builder.withAccountedDeposit(eisLot.getLotSumDeposit());
            }
            AsvParticipantType participantType;
            if (isOneApplication) {
                participantType = AsvParticipantType.SINGLE;
            } else if (eisAppl.getApplicatIndWinner() != null && eisAppl.getApplicatIndWinner() == 1) {
                participantType = AsvParticipantType.WINNER;
            } else if (second != null && second.getApplicatUnid().equals(eisAppl.getApplicatUnid())) {
                participantType = AsvParticipantType.SECOND;
            } else {
                participantType = AsvParticipantType.PARTICIPANT;
            }
            builder.withParticipantType(participantType.getCode());
            if (participantType.equals(AsvParticipantType.SINGLE)) {
                builder.withOfferedPrice(eisLot.getStartCostValueRub());
            } else {
                builder.withOfferedPrice(eisAppl.getApplicatAmount());
            }

            List<DocFile> applicantDocs = new ArrayList<>();
            if ((eisAppl.getApplicatIndWinner() != null && eisAppl.getApplicatIndWinner() == 1) || isOneApplication) {
                builder.withKpp(eisAppl.getShSubCodeKpp())
                        .withOgrn(eisAppl.getShSubOgrn())
                        .withOgrnDateIssued(formatDate(eisAppl.getShSubRegDate()))
                        .withOgrnByIssued(eisAppl.getShSubRegOrgan())

                        .withDulSeries(removeSpaces(eisAppl.getShSubDocumSeries()))
                        .withDulNumber(removeSpaces(eisAppl.getShSubDocumNumber()))
                        .withDulDateIssued(formatDate(eisAppl.getShSubDocumIssueDate()))
                        .withDulByIssued(eisAppl.getShSubDocumIssueAgency())

                        .withDateBirth(formatDate(eisAppl.getShSubBirthday()));

                String chiefName = Stream.of(eisAppl.getShSubManF(),
                                eisAppl.getShSubManI(), eisAppl.getShSubManO())
                        .filter(n -> n != null && !n.isEmpty())
                        .collect(Collectors.joining(" "));

                builder.withLeaderFIO(chiefName.isEmpty() ? null : chiefName)

                        .withPhone(eisAppl.getShSubPhone())
                        .withEmail(eisAppl.getShSubEMail())
                        .withLocation(eisAppl.getShSubAddrLegal() == null || eisAppl.getShSubAddrLegal().isEmpty() ? eisAppl.getShSubAddrFact() : eisAppl.getShSubAddrLegal())

                        .withPayerAccNumber(eisAppl.getShSubAccountCode())
                        .withPayerBankName(eisAppl.getShSubBankName())
                        .withPayerCorrAccNumber(eisAppl.getShSubBankAccount())
                        .withPayerBIK(eisAppl.getShSubBankBic());

                String declarantName = Stream.of(eisAppl.getShPaSubNameF(),
                                eisAppl.getShPaSubNameI(), eisAppl.getShPaSubNameO())
                        .filter(n -> n != null && !n.isEmpty())
                        .collect(Collectors.joining(" "));

                builder.withSecondary(declarantName)
                        .withSecondaryDocDate(formatDate(eisAppl.getShPaDocDate()))
                        .withSecondaryDocNumber(eisAppl.getShPaDocNumber())
                        .withSecondaryDocType(eisAppl.getShPaDocName())

                        .withSecondaryDulSeries(removeSpaces(eisAppl.getShPaSubDocumSeries()))
                        .withSecondaryDulNumber(removeSpaces(eisAppl.getShPaSubDocumNumber()))
                        .withSecondaryDulDateIssued(formatDate(eisAppl.getShPaSubDocumIssueDate()))
                        .withSecondaryDulByIssued(eisAppl.getShPaSubDocumIssueAgency());

                boolean isLotAlreadySent = eisLot.getEprResultsUnid() != null;
                if (!isLotAlreadySent) {
                    List<Document> applDocs = lotController.getApplicationDocuments(eisAppl.getApplicatUnid());
                    applicantDocs = applDocs.stream()
                            .filter(d -> d.getDfUnid() != null || d.getDfScanUnid() != null)
                            .map(d -> d.getDfUnid() != null ? d.getDfUnid() : d.getDfScanUnid())
                            .collect(Collectors.toList());
                }
            }
            applicants.add(new AsvApplicantDto(builder.build(), applicantDocs));
        }

        return applicants;
    }

    private String removeSpaces(String str) {
        return str != null
                ? str.replaceAll("\\s+", "")
                : null;
    }

    private String formatTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss,SSSSSS");
        return sdf.format(date);
    }

    private String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyy");
        return sdf.format(date);
    }

}
