package com.baltinfo.radius.loadauction.service;

import com.baltinfo.radius.db.constants.AuctionParticipantLimitation;
import com.baltinfo.radius.db.constants.CalcPriceConstant;
import com.baltinfo.radius.db.constants.ChangePriceAlg;
import com.baltinfo.radius.db.constants.DepositTypeConstant;
import com.baltinfo.radius.db.constants.LoadSourceConstant;
import com.baltinfo.radius.db.constants.TypeAuctionCode;
import com.baltinfo.radius.db.constants.TypeAuctionConstant;
import com.baltinfo.radius.db.constants.TypeSubjectConstant;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.dto.AuctionDto;
import com.baltinfo.radius.db.dto.BlockAuctionDto;
import com.baltinfo.radius.db.dto.DateDto;
import com.baltinfo.radius.db.dto.LoadAuctionDto;
import com.baltinfo.radius.db.dto.LoadFileDto;
import com.baltinfo.radius.db.dto.LoadLotDto;
import com.baltinfo.radius.db.dto.LoadRsDto;
import com.baltinfo.radius.db.model.ClAsv;
import com.baltinfo.radius.db.model.LoadJournal;
import com.baltinfo.radius.db.model.LoadStatus;
import com.baltinfo.radius.loadauction.constants.AsvAuctionForm;
import com.baltinfo.radius.loadauction.constants.AsvTenderType;
import com.baltinfo.radius.loadauction.constants.AsvTypeAuction;
import com.baltinfo.radius.loadauction.constants.CompetitionManagerConstant;
import com.baltinfo.radius.loadauction.constants.DpPayeeConstant;
import com.baltinfo.radius.loadauction.ftp.FileStorage;
import com.baltinfo.radius.loadauction.model.AssetFull;
import com.baltinfo.radius.loadauction.model.Bank;
import com.baltinfo.radius.loadauction.model.Lot;
import com.baltinfo.radius.loadauction.model.LotInfo;
import com.baltinfo.radius.loadauction.model.Period;
import com.baltinfo.radius.loadauction.model.Publication;
import com.baltinfo.radius.loadauction.model.Tender;
import com.baltinfo.radius.loadauction.model.assets.Asset;
import com.baltinfo.radius.utils.Result;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang.StringUtils;
import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Suvorina Aleksandra
 * @since 12.10.2021
 */
public class PublicationConverterService {

    private static final Logger logger = LoggerFactory.getLogger(PublicationConverterService.class);

    public static final Integer PUBLIC_STAGE = 3;
    private static final Integer DEFAULT_PERIOD_NUMBER = 1;
    private static final int S_NAME_LIMIT = 2000;
    private static final String ASV_DATE_PATTERN = "dd.MM.yyyy";
    private static final String SPLIT_DELIMITER = ":";
    private static final BigDecimal DEFAULT_STEP_PERCENT = new BigDecimal("0.05");
    private static final Long DP_UNID_TEMPLATE = 1380L;
    private final DateFormat dateFormat = new SimpleDateFormat(ASV_DATE_PATTERN);
    private final Long ftpTpaUnid;


    private final FileStorage fileStorage;
    private final LotController lotController;

    public PublicationConverterService(Long ftpTpaUnid, FileStorage fileStorage, LotController lotController) {
        this.ftpTpaUnid = ftpTpaUnid;
        this.fileStorage = fileStorage;
        this.lotController = lotController;
    }

    public TypeAuctionConstant extractTypeAuction(Tender tender) {
        String tenderType = tender.getTenderType().toLowerCase().trim();
        String tenderForm = tender.getTenderForm() == null ? "" : tender.getTenderForm().toLowerCase().trim();
        String appForm = tender.getAppForm() == null ? "" : tender.getAppForm().toLowerCase().trim();
        try {
            if (tenderForm.equals("") && tenderType.equals(AsvTypeAuction.PUBLIC.getCode())) {
                return TypeAuctionConstant.PUBLIC;
            }
            if (appForm.equals(AsvAuctionForm.CLOSED.getCode())) {
                if (tenderType.equals(AsvTypeAuction.AUCTION.getCode())) {
                    return TypeAuctionConstant.CLOSE_AUCTION;
                } else {
                    return TypeAuctionConstant.CLOSE_CONCURS;
                }
            } else {
                if (tenderType.equals(AsvTypeAuction.AUCTION.getCode())) {
                    return TypeAuctionConstant.OPEN_AUCTION;
                } else {
                    return TypeAuctionConstant.OPEN_CONCURS_AUCTION;
                }
            }
        } catch (Exception e) {
            logger.error("Can't fill tender type! Wrong xml format. tender_form = {} и tender_type = {}", tenderForm, tenderType, e);
            return null;
        }
    }

    public boolean isBlockAuction(Publication publication) {
        Tender tender = publication.getTenderFulls().getTenders().get(0);
        TypeAuctionConstant typeAuction = extractTypeAuction(tender);
        return publication.getTenderFulls().getTenderType().equalsIgnoreCase(AsvTenderType.BLOCK_AUCTION.getCode())
                || ((typeAuction.getRadiusTypeAuctionCode().equals(TypeAuctionCode.AUCTION.getCode())
                || typeAuction.getRadiusTypeAuctionCode().equals(TypeAuctionCode.COMPETITION.getCode()))
                && tender.getAmountPeriod() >= 2);
    }

    private String formAuctionName(Publication publication, Integer stage, Integer amountLot, String numberLots) {
        if (isBlockAuction(publication)) {
            return "Этап - " + stage +
                    (amountLot > 1 ? " лоты " : " лот ") + numberLots + ", " +
                    publication.getBank().getBankName();
        } else {
            return publication.getBank().getBankName();
        }
    }

    public List<LoadFileDto> getDocsForTenders(Publication publication) {
        boolean isBlockAuction = publication.getTenderFulls().getTenderType().equalsIgnoreCase(AsvTenderType.BLOCK_AUCTION.getCode());
        if (isBlockAuction) {
            return fileStorage.getDocumentsForAuction(publication.getTenderFulls().getTradeId());
        } else {
            return fileStorage.getDocumentsForAuction(publication.getTenderFulls().getTenders().get(0).getTradeId());
        }
    }

    public BlockAuctionDto formBlockAuction(Publication publication) {
        boolean isBlockAuction = isBlockAuction(publication);
        String asvTradeId = publication.getTenderFulls().getTradeId() != null && !publication.getTenderFulls().getTradeId().isEmpty()
                ? publication.getTenderFulls().getTradeId()
                : publication.getTenderFulls().getTenders().get(0).getTradeId();
        return new BlockAuctionDto(publication.getBank().getBankName(),
                asvTradeId,
                !isBlockAuction,
                DP_UNID_TEMPLATE);
    }

    public Result<List<LoadAuctionDto>, String> formLoadAuctions(Publication publication) {
        try {
            List<LoadAuctionDto> auctionDtoList = new ArrayList<>();
            Set<String> alreadyLoadedFiles = new HashSet<>();
            for (Tender tender : publication.getTenderFulls().getTenders()) {
                TypeAuctionConstant typeAuction = extractTypeAuction(tender);
                if (!typeAuction.equals(TypeAuctionConstant.PUBLIC)) {
                    Result<List<LoadAuctionDto>, String> auctionResult = formLoadAuctions(publication, tender, alreadyLoadedFiles);
                    if (auctionResult.isError()) {
                        return Result.error(auctionResult.getError());
                    } else {
                        auctionDtoList.addAll(auctionResult.getResult());
                    }
                } else {
                    Result<LoadAuctionDto, String> publicResult = formPublicLoadAuction(publication, tender, alreadyLoadedFiles);
                    if (publicResult.isError()) {
                        return Result.error(publicResult.getError());
                    } else {
                        auctionDtoList.add(publicResult.getResult());
                    }
                }
            }
            return Result.ok(auctionDtoList);
        } catch (Exception e) {
            logger.error("Can't create LoadAuctionDto", e);
            return Result.error("Can't create LoadAuctionDto. Error: " + e);
        }
    }

    private Result<List<LoadAuctionDto>, String> formLoadAuctions(Publication publication,
                                                                  Tender tender, Set<String> alreadyLoadedFiles) {
        List<LoadAuctionDto> auctions = new ArrayList<>();

        String blockTradeId = publication.getTenderFulls().getTradeId();

        TypeAuctionConstant typeAuction = extractTypeAuction(tender);

        for (Period period : tender.getPeriods()) {
            if (period.getStartDatePeriod() == null || period.getStartDatePeriod().isEmpty()) {
                continue;
            }
            AuctionDto.AuctionDtoBuilder builder = AuctionDto.builder();
            builder.withTypeAuction(typeAuction);
            String tenderForm = tender.getTenderForm() == null ? "" : tender.getTenderForm().toLowerCase().trim();
            builder.withAuctionParticipantLimitation(tenderForm.equals(AsvAuctionForm.CLOSED.getCode())
                    ? AuctionParticipantLimitation.CLOSE.getCode()
                    : AuctionParticipantLimitation.OPEN.getCode());
            Integer periodNum = period.getPeriodNumber();

            String auctionAsvId = tender.getTradeId() + "/" + periodNum;

            builder.withAuctionStageNum(periodNum)
                    .withAuctionAsvId(auctionAsvId)
                    .withAuctionNumberLots(tender.getNumberLots());

            Bank bank = publication.getBank();

            builder.withTypeSubject(TypeSubjectConstant.YL)
                    .withDebitorSubName(bank.getBankName())
                    .withDebitorInn(bank.getBankInn())
                    .withDebitorOgrn(bank.getBankOgrn());

            fillAuctionInfo(builder, period);
            if (StringUtils.isEmpty(period.getEndDatePeriod()) && StringUtils.isNotEmpty(period.getStartDatePeriod())) {
                    DateDto dateB = new DateDto(dateFromString(period.getStartDatePeriod()),
                            getHoursByString(period.getTimeAuction()),
                            getMinutesByString(period.getTimeAuction()));
                    Instant dateTimeE = dateB.getDate().toInstant().plus(1, ChronoUnit.HOURS);
                    builder.withAuctionDateE(new DateDto(dateTimeE));
            } else {
                builder.withAuctionDateE(new DateDto(dateFromString(period.getEndDatePeriod()),
                        getHoursByString(period.getEndTimePeriod()),
                        getMinutesByString(period.getEndTimePeriod())));
            }

            builder.withAuctionDepDateE(new DateDto(dateFromString(period.getEndRequestPeriod()),
                            getHoursByString(period.getEndTimeRequestPeriod()),
                            getMinutesByString(period.getEndTimeRequestPeriod())))
                    .withAuctionRecepDateE(new DateDto(dateFromString(period.getEndRequestPeriod()),
                            getHoursByString(period.getEndTimeRequestPeriod()),
                            getMinutesByString(period.getEndTimeRequestPeriod())))
                    .withDpContacts(tender.getPhoneTender());
            Result<List<LoadLotDto>, String> resultLoadLots = formLots(tender, blockTradeId, periodNum, false, alreadyLoadedFiles);
            if (resultLoadLots.isError()) {
                return Result.error(resultLoadLots.getError());
            }
            builder.withLotList(resultLoadLots.getResult());

            AuctionDto auctionDto = builder.build();

            String auctionName = formAuctionName(publication, period.getPeriodNumber(), tender.getAmountLot(), tender.getNumberLots());
            LoadAuctionDto loadAuctionDto = new LoadAuctionDto(LoadStatus.PREPARE_XML_RUN_STATUS,
                    LoadSourceConstant.ASV.getId(),
                    ftpTpaUnid,
                    periodNum,
                    auctionName, auctionDto);
            auctions.add(loadAuctionDto);
        }
        return Result.ok(auctions);
    }

    private Result<LoadAuctionDto, String> formPublicLoadAuction(Publication publication,
                                                                 Tender tender,
                                                                 Set<String> alreadyLoadedFiles) {
        TypeAuctionConstant typeAuction = extractTypeAuction(tender);
        AuctionDto.AuctionDtoBuilder builder = AuctionDto.builder();
        builder.withTypeAuction(typeAuction);
        builder.withAuctionStageNum(PUBLIC_STAGE)
                .withAuctionAsvId(tender.getTradeId())
                .withAuctionNumberLots(tender.getNumberLots());
        Bank bank = publication.getBank();

        builder.withTypeSubject(TypeSubjectConstant.YL)
                .withDebitorSubName(bank.getBankName())
                .withDebitorInn(bank.getBankInn())
                .withDebitorOgrn(bank.getBankOgrn())
                .withDebitorBik(bank.getBankBik());
        Period period = tender.getPeriods().get(0);
        fillAuctionInfo(builder, period);
        period = tender.getPeriods().get(tender.getAmountPeriod() - 1);
        builder.withAuctionDateE(new DateDto(dateFromString(period.getEndDatePeriod()),
                getHoursByString(period.getEndTimePeriod()),
                getMinutesByString(period.getEndTimePeriod())));
        builder.withAuctionRecepDateE(new DateDto(dateFromString(period.getEndDatePeriod()),
                        getHoursByString(period.getEndTimePeriod()),
                        getMinutesByString(period.getEndTimePeriod())))
                .withDpContacts(tender.getPhoneTender());

        String blockTradeId = publication.getTenderFulls().getTradeId();
        Result<List<LoadLotDto>, String> resultLoadLots = formLots(tender, blockTradeId, DEFAULT_PERIOD_NUMBER, true, alreadyLoadedFiles);
        if (resultLoadLots.isError()) {
            return Result.error(resultLoadLots.getError());
        }
        builder.withLotList(resultLoadLots.getResult());

        Result<List<LoadRsDto>, String> resultLoadRs = formReductionSchedules(tender);
        if (resultLoadRs.isError()) {
            return Result.error(resultLoadRs.getError());
        }
        builder.withRsList(resultLoadRs.getResult());
        AuctionDto auctionDto = builder.build();

        String auctionName = formAuctionName(publication, PUBLIC_STAGE, tender.getAmountLot(), tender.getNumberLots());
        LoadAuctionDto loadAuctionDto = new LoadAuctionDto(LoadStatus.PREPARE_XML_RUN_STATUS,
                LoadSourceConstant.ASV.getId(),
                ftpTpaUnid,
                PUBLIC_STAGE,
                auctionName, auctionDto);

        return Result.ok(loadAuctionDto);
    }

    private void fillAuctionInfo(AuctionDto.AuctionDtoBuilder builder, Period period) {
        builder.withAuctionDatePlan(new DateDto(dateFromString(period.getStartDatePeriod()),
                        getHoursByString(period.getTimeAuction()),
                        getMinutesByString(period.getTimeAuction())))
                .withAuctionRecepDateB(new DateDto(dateFromString(period.getStartRequestPeriod()), 0, 0));

        builder.withDpTendManagerName(CompetitionManagerConstant.TENDER_MANAGER_NAME)
                .withDpIndTendManager(CompetitionManagerConstant.COMPETITION_MANAGER)
                .withDpTendManagerSname(CompetitionManagerConstant.TENDER_MANAGER_S_NAME)
                .withDpArbitManagerInn(CompetitionManagerConstant.TENDER_MANAGER_INN)
                .withDpTendManagerOgrn(CompetitionManagerConstant.TENDER_MANAGER_OGRN);

        builder.withDpPayeeName(DpPayeeConstant.PAYEE_NAME)
                .withDpPayeeInn(DpPayeeConstant.PAYEE_INN)
                .withDpPayeeKpp(DpPayeeConstant.PAYEE_KPP)
                .withDpPayeeAccount(DpPayeeConstant.PAYEE_ACCOUNT_NUMBER)
                .withDpPayeeBankName(DpPayeeConstant.PAYEE_BANK_NAME)
                .withDpPayeeBankBik(DpPayeeConstant.PAYEE_BANK_BIK);
    }

    private Result<List<LoadLotDto>, String> formLots(Tender tender, String blockTradeId, int periodNumber, boolean isPublic, Set<String> alreadyLoadedFiles) {
        try {
            List<LoadLotDto> lotList = new ArrayList<>();
            for (Lot lot : tender.getLots()) {
                LoadLotDto.LoadLotDtoBuilder builder = LoadLotDto.builder()
                        .withLlFiles(getDocsForLot(alreadyLoadedFiles, lot, blockTradeId, tender.getTradeId()))
                        .withLlLotNum(lot.getNumber().toString())
                        .withLlAsvId(lot.getId().toString())
                        .withLlAsvLink(lot.getUrlLotTorgiAsv());

                ClAsv clAsv = lotController.getClAsv(Long.valueOf(lot.getClassifier()));
                if (clAsv != null) {
                    builder.withLlClAsv(clAsv.getCaName())
                            .withLlTypeObject(clAsv.getToUnid() == null ? null : clAsv.getToUnid().getToName())
                            .withLlSaleCategory(clAsv.getScUnid() == null ? null : clAsv.getScUnid().getScUnid());
                }

                String sName = lot.getLotName().length() > S_NAME_LIMIT ? lot.getLotName().substring(0, S_NAME_LIMIT) : lot.getLotName();
                builder.withLlLotSname(sName);

                Optional<LotInfo> lotInfoOpt = lot.getLotsInfo().stream()
                        .filter(li -> li.getNumberPeriod().equals(periodNumber))
                        .findFirst();
                if (!lotInfoOpt.isPresent()) {
                    logger.info("Can't create LoadLotDto. Can't find current lotInfo by periodNumber = " + periodNumber);
                    continue;
                }
                LotInfo lotInfo = lotInfoOpt.get();
                BigDecimal startCost = lotInfo.getPricePeriod();
                BigDecimal depositSum = lotInfo.getDeposit();
                BigDecimal stepValue = null;
                BigDecimal stepDecreaseValue = null;
                if (isPublic) {
                    Result<BigDecimal, String> stepValueResult = getPublicStepValue(lot);
                    if (stepValueResult.isSuccess()) {
                        stepDecreaseValue = stepValueResult.getResult();
                    }
                } else {
                    stepValue = startCost.multiply(DEFAULT_STEP_PERCENT);
                }

                builder.withLlLotName(lot.getDescription())
                        .withLlStartCost(startCost)
                        .withLlDepositSum(depositSum)
                        .withLlStepValue(stepValue)
                        .withLlStepDecreaseValue(stepDecreaseValue)
                        .withLlDepositAlg(DepositTypeConstant.FIXED_VALUE)
                        .withLlChangePriceAlg(ChangePriceAlg.ALG5.getCode())
                        .withLlAddress(StringUtils.isBlank(lot.getAddress()) ? lot.getLocation() : lot.getAddress())
                        .withLlRegion(lot.getLocation())
                        .withLlReviewDebitorOrder(tender.getReviewTender())
                        .withLlObjectLinks(lot.getUrlLot1())
                        .withLlAsvStageId(lotInfo.getLotId());
                if (lot.getAssetFullList() != null) {
                    addAssetsJson(builder, lot.getAssetFullList());
                }
                lotList.add(builder.build());
            }
            return Result.ok(lotList);
        } catch (Exception e) {
            logger.error("Can't create LoadLotDto", e);
            return Result.error("Can't create LoadLotDto");
        }
    }

    private List<LoadFileDto> getDocsForLot(Set<String> alreadyLoadedFiles, Lot lot, String blockTradeId, String tradeId) {
        List<LoadFileDto> files = fileStorage.getDocumentsForLot(alreadyLoadedFiles, lot, blockTradeId, tradeId);
        alreadyLoadedFiles.addAll(files.stream().map(LoadFileDto::getLfFileNameAsv).collect(Collectors.toList()));
        return files;
    }

    private Result<List<LoadRsDto>, String> formReductionSchedules(Tender tender) {
        List<LoadRsDto> rsList = new ArrayList<>();
        for (Lot lot : tender.getLots()) {
            for (LotInfo lotInfo : lot.getLotsInfo()) {
                Period period = tender.getPeriods().get(lotInfo.getNumberPeriod() - 1);
                LoadRsDto loadRsDto = new LoadRsDto(
                        new DateDto(dateFromString(period.getStartRequestPeriod()),
                                getHoursByString(period.getTimeAuction()),
                                getMinutesByString(period.getTimeAuction())),
                        new DateDto(dateFromString(period.getEndRequestPeriod()),
                                getHoursByString(period.getEndTimeRequestPeriod()),
                                getMinutesByString(period.getEndTimeRequestPeriod())),
                        lotInfo.getPricePeriod(),
                        lotInfo.getDeposit(),
                        CalcPriceConstant.FIXED_PRICE,
                        Collections.singletonList(lot.getNumber())
                );
                rsList.add(loadRsDto);
            }
        }
        return Result.ok(rsList);
    }

    private Result<BigDecimal, String> getPublicStepValue(Lot lot) {
        LotInfo firstPricePeriod = lot.getLotsInfo().stream()
                .filter(li -> li.getNumberPeriod().equals(1))
                .findFirst()
                .orElse(null);
        LotInfo secondPricePeriod = lot.getLotsInfo().stream()
                .filter(li -> li.getNumberPeriod().equals(2))
                .findFirst()
                .orElse(null);
        if (firstPricePeriod != null && secondPricePeriod != null) {
            return Result.ok(firstPricePeriod.getPricePeriod().subtract(secondPricePeriod.getPricePeriod()));
        } else {
            return Result.error("Can't get step value for public auction");
        }
    }

    private void addAssetsJson(LoadLotDto.LoadLotDtoBuilder builder, List<AssetFull> assetFullList) {
        AssetFactory assetFactory = new AssetFactory();
        Result<List<Asset>, String> result = assetFactory.getListOfAsset(assetFullList);
        if (result.isError()) {
            return;
        }
        List<Asset> assetList = result.getResult();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            String jsonAssets = mapper.writeValueAsString(assetList);
            builder.withLlAssets(jsonAssets);
        } catch (IOException e) {
            logger.error("Can't create Asset JSON", e);
        }
    }

    private Date dateFromString(String date) throws ParseException {
        try {
            if (date != null) {
                return dateFormat.parse(date);
            }
        } catch (Exception e) {
            logger.warn("Can't create date by pattern. Date = {}", date);
        }
        return null;
    }

    private Integer getHoursByString(String time) {
        return time == null || time.isEmpty() ? null : Integer.valueOf(time.split(SPLIT_DELIMITER)[0]);
    }

    private Integer getMinutesByString(String time) {
        return time == null || time.isEmpty() ? null : Integer.valueOf(time.split(SPLIT_DELIMITER)[1]);
    }

    public LoadJournal formLoadJournal(Publication publication) {
        LoadJournal loadJournal = new LoadJournal();
        loadJournal.setLjDebtorName(publication.getBank().getBankName());
        loadJournal.setLjDebtorBic(publication.getBank().getBankBik());
        loadJournal.setLjDebtorInn(publication.getBank().getBankInn());
        loadJournal.setLjDebtorOgrn(publication.getBank().getBankOgrn());
        boolean isBlockAuction = isBlockAuction(publication);
        loadJournal.setLjTypeTrade(isBlockAuction ? AsvTenderType.BLOCK_AUCTION.getLetter() : AsvTenderType.NON_BLOCK_AUCTION.getLetter());
        return loadJournal;
    }
}
