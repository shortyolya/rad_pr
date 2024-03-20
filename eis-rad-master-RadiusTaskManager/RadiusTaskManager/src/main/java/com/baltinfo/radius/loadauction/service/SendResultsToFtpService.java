package com.baltinfo.radius.loadauction.service;

import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.constants.TypeAuctionCode;
import com.baltinfo.radius.db.constants.TypeStateConstant;
import com.baltinfo.radius.db.controller.BlockAuctionController;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.model.DocFile;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.db.model.VAsvApplication;
import com.baltinfo.radius.db.model.VAsvAuction;
import com.baltinfo.radius.db.model.VAsvLot;
import com.baltinfo.radius.db.model.VBaResults;
import com.baltinfo.radius.loadauction.ftp.FileStorage;
import com.baltinfo.radius.loadauction.model.result.Applicant;
import com.baltinfo.radius.loadauction.model.result.AsvApplicantDto;
import com.baltinfo.radius.loadauction.model.result.Lot;
import com.baltinfo.radius.loadauction.model.result.Tender;
import com.baltinfo.radius.loadauction.model.result.TenderResults;
import com.baltinfo.radius.utils.Result;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class SendResultsToFtpService {
    private static final Logger logger = LoggerFactory.getLogger(SendResultsToFtpService.class);
    private static final String BLOCK_TRADE_PREFIX = "B";

    private final BlockAuctionController blockAuctionController;
    private final LotController lotController;
    private final ExchangeProcController exchangeProcController;
    private final AsvResultService asvResultService;
    private final FileStorage fileStorage;

    public SendResultsToFtpService(BlockAuctionController blockAuctionController,
                                   LotController lotController,
                                   ExchangeProcController exchangeProcController,
                                   AsvResultService asvResultService, FileStorage fileStorage) {
        this.blockAuctionController = blockAuctionController;
        this.lotController = lotController;
        this.exchangeProcController = exchangeProcController;
        this.asvResultService = asvResultService;
        this.fileStorage = fileStorage;
    }

    public void sendResult() {
        List<VBaResults> blockAuctions = blockAuctionController.getAsvBlockAuctionsWithResults();
        for (VBaResults blockAuction : blockAuctions) {
            try {
                Result<List<VAsvAuction>, String> auctionsResult = asvResultService.getAuctionStageWithResults(blockAuction);
                if (auctionsResult.isError()) {
                    saveBlockAuctionExchangeProcRun(blockAuction.getBaUnid(), ExchangeProcStatus.ERROR_START.getCode(),
                            auctionsResult.getError());
                    continue;
                }
                List<VAsvAuction> auctions = auctionsResult.getResult();
                if (!auctions.get(0).getTypeAuctionCode().equals(TypeAuctionCode.PUBLIC_SALE.getCode())) {
                    boolean allAuctionsFinished = auctions.stream()
                            .allMatch(a -> a.getTsUnid().equals(TypeStateConstant.TRADE_DONE.getId()));
                    if (!allAuctionsFinished) {
                        continue;
                    }
                }
                String auctionsWithoutAsvId = auctions.stream()
                        .filter(a -> a.getAuctionAsvId() == null || a.getAuctionAsvId().isEmpty())
                        .map(VAsvAuction::getAuctionName)
                        .collect(Collectors.joining(", "));
                if (!auctionsWithoutAsvId.isEmpty()) {
                    String message = "В торгах " + auctionsWithoutAsvId + " отсутствует идентификатор торгов в АСВ";
                    saveBlockAuctionExchangeProcRun(blockAuction.getBaUnid(), ExchangeProcStatus.ERROR_START.getCode(),
                            message);
                    continue;
                }
                Map<String, List<DocFile>> applDocZip = new HashMap<>();
                List<Tender> tenders = new ArrayList<>();
                String formTenderError = null;
                for (VAsvAuction auction : auctions) {
                    List<VAsvLot> eisLots = lotController.getAsvLotsByAuctionUnid(auction.getAuctionUnid());

                    eisLots = eisLots.stream()
                            .filter(l -> StringUtils.isNotEmpty(l.getLotEtpCode()))
                            .collect(Collectors.toList());

                    String lotsWithoutAsvId = eisLots.stream()
                            .filter(l -> l.getLotAsvId() == null || l.getLotAsvId().isEmpty())
                            .map(VAsvLot::getLotNumber)
                            .collect(Collectors.joining(", "));

                    if (!lotsWithoutAsvId.isEmpty()) {
                        formTenderError = "В торгах " + auction.getAuctionName() + " в лотах №" + lotsWithoutAsvId + " отсутствует идентификатор лота в АСВ";
                        break;
                    }

                    List<Lot> asvLots = new ArrayList<>();
                    for (VAsvLot eisLot : eisLots) {
                        List<VAsvApplication> eisApplications = lotController.getAsvLotApplications(eisLot.getLotUnid());
                        List<AsvApplicantDto> asvApplicantDtos = asvResultService.formApplicants(eisApplications, eisLot);
                        for (AsvApplicantDto appl : asvApplicantDtos) {
                            if (!appl.getDocFiles().isEmpty()) {
                                String tradeId = blockAuction.getBaAsvId() != null && blockAuction.getBaAsvId().startsWith(BLOCK_TRADE_PREFIX)
                                        ? blockAuction.getBaAsvId().substring(1)
                                        : "0000";
                                String lotId = eisLot.getLotAsvStageId() != null && !eisLot.getLotAsvStageId().isEmpty()
                                        ? eisLot.getLotAsvStageId()
                                        : eisLot.getLotAsvId();
                                String zipName = tradeId + "_" + auction.getAuctionAsvIdWithoutStage() + "_" + lotId + "_" + appl.getApplicant().getRadId() + ".zip";
                                applDocZip.put(zipName, appl.getDocFiles());
                            }
                        }
                        List<Applicant> asvApplicants = asvApplicantDtos.stream()
                                .map(AsvApplicantDto::getApplicant)
                                .collect(Collectors.toList());
                        Lot asvLot = asvResultService.formAsvLot(eisLot, asvApplicants);
                        asvLots.add(asvLot);
                    }
                    Tender tender = asvResultService.formTender(auction, asvLots);
                    tenders.add(tender);
                }
                if (formTenderError != null) {
                    saveBlockAuctionExchangeProcRun(blockAuction.getBaUnid(), ExchangeProcStatus.ERROR_START.getCode(),
                            formTenderError);
                    continue;
                }

                Result<TenderResults, String> tenderResult = asvResultService.formTenderResults(blockAuction, tenders);
                if (tenderResult.isError()) {
                    saveBlockAuctionExchangeProcRun(blockAuction.getBaUnid(), ExchangeProcStatus.ERROR_START.getCode(),
                            tenderResult.getError());
                    continue;
                }
                String json = serializeJson(tenderResult.getResult());
                Result<Void, String> result = fileStorage.writeResultFile(blockAuction.getBaAsvId(), json);
                if (result.isError()) {
                    saveBlockAuctionExchangeProcRun(blockAuction.getBaUnid(), ExchangeProcStatus.ERROR_START.getCode(),
                            result.getError());
                    continue;
                }
                for (String zipName : applDocZip.keySet()) {
                    fileStorage.writeDocsToZip(zipName, applDocZip.get(zipName));
                }

                List<ExchangeProcRun> auctionExchangeProcs = auctions.stream()
                        .map(a -> formAuctionExchangeProcRun(a.getAuctionUnid()))
                        .collect(Collectors.toList());
                auctionExchangeProcs.forEach(exchangeProcController::saveExchangeProcRun);
                List<VAsvAuction> publicAuctions = auctions.stream()
                        .filter(a -> a.getTypeAuctionCode().equals(TypeAuctionCode.PUBLIC_SALE.getCode()))
                        .collect(Collectors.toList());
                for (VAsvAuction auction : publicAuctions) {
                    List<VAsvLot> lots = lotController.getAsvLotsByAuctionUnid(auction.getAuctionUnid());
                    List<VAsvLot> finishedLots = lots.stream()
                            .filter(l -> TypeStateConstant.isLotFinishedState(l.getTsUnid()))
                            .collect(Collectors.toList());
                    List<ExchangeProcRun> lotExchangeProcs = finishedLots.stream()
                            .map(l -> formLotExchangeProcRun(l.getLotUnid()))
                            .collect(Collectors.toList());
                    lotExchangeProcs.forEach(exchangeProcController::saveExchangeProcRun);
                }
                saveBlockAuctionExchangeProcRun(blockAuction.getBaUnid(), ExchangeProcStatus.FINISHED.getCode(),
                        null);
            } catch (Exception ex) {
                logger.error("Error send results to asv by baAsvId = {}", blockAuction.getBaAsvId(), ex);
                saveBlockAuctionExchangeProcRun(blockAuction.getBaUnid(), ExchangeProcStatus.ERROR_START.getCode(),
                        "Error send results to asv: " + getStackTrace(ex));
            }
        }
    }

    private ExchangeProcRun formAuctionExchangeProcRun(Long auctionUnid) {
        ExchangeProcRun epr = new ExchangeProcRun();
        epr.setEpUnid(ExchangeProcs.SEND_AUCTION_RESULTS_TO_ASV.getUnid());
        epr.setEprRunDate(new Date());
        epr.setEprLoadStatus(ExchangeProcStatus.FINISHED.getCode());
        epr.setEprSourceId(auctionUnid);
        return epr;
    }

    private ExchangeProcRun formLotExchangeProcRun(Long lotUnid) {
        ExchangeProcRun epr = new ExchangeProcRun();
        epr.setEpUnid(ExchangeProcs.SEND_LOT_RESULTS_TO_ASV.getUnid());
        epr.setEprRunDate(new Date());
        epr.setEprLoadStatus(ExchangeProcStatus.FINISHED.getCode());
        epr.setEprSourceId(lotUnid);
        return epr;
    }

    private void saveBlockAuctionExchangeProcRun(Long baUnid, Integer status, String errorText) {
        ExchangeProcRun epr = new ExchangeProcRun();
        epr.setEpUnid(ExchangeProcs.SEND_BLOCK_AUCTION_RESULTS_TO_ASV.getUnid());
        epr.setEprRunDate(new Date());
        epr.setEprLoadStatus(status);
        epr.setEprErrorText(errorText);
        epr.setEprSourceId(baUnid);
        exchangeProcController.saveExchangeProcRun(epr);
    }

    private String serializeJson(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper()
                    .findAndRegisterModules()
                    .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                    .enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception ex) {
            logger.error("Can't serialize json", ex);
            return null;
        }
    }

    private String getStackTrace(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }
}
