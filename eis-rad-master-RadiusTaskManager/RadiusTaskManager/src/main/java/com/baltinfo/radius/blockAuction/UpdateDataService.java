package com.baltinfo.radius.blockAuction;

import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.controller.*;
import com.baltinfo.radius.db.model.*;

import com.baltinfo.radius.loadauction.constants.AsvTypeAuction;
import com.baltinfo.radius.loadauction.model.Period;
import com.baltinfo.radius.loadauction.model.Publication;
import com.baltinfo.radius.loadauction.model.Tender;
import com.baltinfo.radius.loadauction.service.JsonExportService;
import com.baltinfo.radius.loadauction.service.JsonExportToAssetsService;
import com.baltinfo.radius.loadauction.service.PublicationConverterService;
import com.baltinfo.radius.loadauction.service.XmlExportService;
import com.baltinfo.radius.utils.Result;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class UpdateDataService {

    private static final Logger logger = LoggerFactory.getLogger(UpdateDataService.class);

    private final ExchangeProcController exchangeProcController;
    private final BlockAuctionController blockAuctionController;
    private final AuctionController auctionController;
    private final JsonExportService jsonExportService;
    private final JsonExportToAssetsService jsonExportToAssetsService;
    private final XmlExportService xmlExportService;
    private final LotController lotController;
    private final DocFileController docFileController;
    private final String pathToFiles;

    public UpdateDataService(ExchangeProcController exchangeProcController,
                             BlockAuctionController blockAuctionController,
                             AuctionController auctionController, JsonExportService jsonExportService,
                             JsonExportToAssetsService jsonExportToAssetsService,
                             XmlExportService xmlExportService,
                             LotController lotController, DocFileController docFileController,
                             String pathToFiles) {
        this.exchangeProcController = exchangeProcController;
        this.blockAuctionController = blockAuctionController;
        this.auctionController = auctionController;
        this.jsonExportService = jsonExportService;
        this.jsonExportToAssetsService = jsonExportToAssetsService;
        this.xmlExportService = xmlExportService;
        this.lotController = lotController;
        this.docFileController = docFileController;
        this.pathToFiles = pathToFiles;
    }

    public void runProcedure() {
        updateAssetsExchangeProcRun();
        updateAuctionAndLotNumberExchangeProcRun();
    }

    private void updateAssetsExchangeProcRun() {
        Optional<List<ExchangeProcRun>> exchangeProcRunListOpt = exchangeProcController.getRunningExchangeProcRun(ExchangeProcs.UPDATE_ASSETS);
        if (!exchangeProcRunListOpt.isPresent()) return;
        List<ExchangeProcRun> exchangeProcRunList = exchangeProcRunListOpt.get();
        for (ExchangeProcRun epr : exchangeProcRunList) {
            Long dfUnid = epr.getEprReceiverId();
            Long baUnid = epr.getEprSourceId();
            DocFile docFile = docFileController.getDocFileByUnid(dfUnid);
            if (docFile == null) {
                saveProcedureInfo(epr, "Не удалось найти файл с dfUnid = " + dfUnid, ExchangeProcStatus.ERROR_RUNNING.getCode());
                return;
            }
            List<Lot> currentLots = blockAuctionController.getBlockAuctionLotsByBaUnid(baUnid);
            if (currentLots == null || currentLots.isEmpty()) return;
            Result<Publication, String> readResult = readPublicationFromFile(docFile);
            if (readResult.isError()) {
                saveProcedureInfo(epr, readResult.getError(), ExchangeProcStatus.ERROR_RUNNING.getCode());
                return;
            }
            Publication publication = readResult.getResult();
            Result<Map<Long, String>, String> result = getObjUnidsWithAssets(publication, currentLots);
            if (result.isError()) {
                saveProcedureInfo(epr, result.getError(), ExchangeProcStatus.ERROR_RUNNING.getCode());
                return;
            }
            Result<Void, String> res = lotController.updateAssets(result.getResult());
            if (res.isError()) {
                saveProcedureInfo(epr, res.getError(), ExchangeProcStatus.ERROR_RUNNING.getCode());
            } else {
                saveProcedureInfo(epr, "Активы успешно обновлены", ExchangeProcStatus.FINISHED.getCode());
            }
        }
    }

    private void updateAuctionAndLotNumberExchangeProcRun() {
        Optional<List<ExchangeProcRun>> exchangeProcRunListOpt = exchangeProcController.getRunningExchangeProcRun(ExchangeProcs.UPDATE_AUCTION_AND_LOT_NUMBER);
        if (!exchangeProcRunListOpt.isPresent()) {
            return;
        }
        List<ExchangeProcRun> exchangeProcRunList = exchangeProcRunListOpt.get();

        for (ExchangeProcRun epr : exchangeProcRunList) {
            Long dfUnid = epr.getEprReceiverId();
            Long baUnid = epr.getEprSourceId();
            DocFile docFile = docFileController.getDocFileByUnid(dfUnid);
            if (docFile == null) {
                saveProcedureInfo(epr, "Не удалось найти файл с dfUnid = " + dfUnid, ExchangeProcStatus.ERROR_RUNNING.getCode());
                return;
            }
            Result<Publication, String> readResult = readPublicationFromFile(docFile);
            if (readResult.isError()) {
                saveProcedureInfo(epr, readResult.getError(), ExchangeProcStatus.ERROR_RUNNING.getCode());
                return;
            }
            Publication publication = readResult.getResult();
            String asvTradeId = publication.getTenderFulls().getTradeId() != null && !publication.getTenderFulls().getTradeId().isEmpty()
                    ? publication.getTenderFulls().getTradeId()
                    : publication.getTenderFulls().getTenders().get(0).getTradeId();
            BlockAuction blockauction = blockAuctionController.getBlockAuctionByUnid(baUnid);
            if (blockauction == null) {
                saveProcedureInfo(epr, "Не найдены блочные торги с идентификатором baUnid = " + baUnid, ExchangeProcStatus.ERROR_RUNNING.getCode());
                return;
            }
            if (!blockauction.getBaAsvId().equals(asvTradeId)) {
                saveProcedureInfo(epr,
                        "Идентификатор АСВ в файле (" + asvTradeId + ") отличается от идентификатора АСВ в обновляемых торгах (" + blockauction.getBaAsvId() + ")",
                        ExchangeProcStatus.ERROR_RUNNING.getCode());
                return;
            }
            List<String> errors = new ArrayList<>();
            List<Tender> tenders = publication.getTenderFulls().getTenders();
            for (Tender tender : tenders) {
                Long lotNumber = tender.getLots().get(0).getNumber();
                boolean typeAuctionPublic = isTypeAuctionPublic(tender);
                if (typeAuctionPublic) {
                    Integer auctionStageNum = PublicationConverterService.PUBLIC_STAGE;
                    String auctionAsvId = tender.getTradeId();
                    String error = updateAuctionAndLots(baUnid, auctionStageNum, lotNumber, tender, auctionAsvId);
                    if (error != null && !error.isEmpty()) {
                        errors.add(error);
                    }
                } else {
                    for (Period period : tender.getPeriods()) {
                        if (period.getStartDatePeriod() == null || period.getStartDatePeriod().isEmpty()) {
                            continue;
                        }
                        Integer auctionStageNum = period.getPeriodNumber();
                        String auctionAsvId = tender.getTradeId() + "/" + auctionStageNum;
                        String error = updateAuctionAndLots(baUnid, auctionStageNum, lotNumber, tender, auctionAsvId);
                        if (error != null && !error.isEmpty()) {
                            errors.add(error);
                        }
                    }
                }
            }
            if (errors.size() > 0) {
                saveProcedureInfo(epr, "Ошибки при выполнении процедуры обновления номеров торгов/лотов: " + StringUtils.join(errors, "; \n"), ExchangeProcStatus.ERROR_RUNNING.getCode());
            } else {
                saveProcedureInfo(epr, "Номера торгов/лотов успешно обновлены", ExchangeProcStatus.FINISHED.getCode());
            }
        }
    }

    public boolean isTypeAuctionPublic(Tender tender) {
        String tenderType = tender.getTenderType().toLowerCase().trim();
        String tenderForm = tender.getTenderForm() == null ? "" : tender.getTenderForm().toLowerCase().trim();
        return tenderForm.equals("") && tenderType.equals(AsvTypeAuction.PUBLIC.getCode());
    }

    private String updateAuctionAndLots(Long baUnid, Integer auctionStageNum, Long lotNumber, Tender tender, String auctionAsvId) {
        Result<Auction, String> auctionOpt = auctionController.findAuctionByBaUnidAndStage(baUnid, auctionStageNum, lotNumber);
        if (auctionOpt.isError()) {
            return auctionOpt.getError();
        }
        Auction auction = auctionOpt.getResult();
        if (auction == null) {
            return "Не найдены торги по следующим условиям: baUnid = " + baUnid + ", auctionStageNum = " + auctionStageNum + ", lotNumber = " + lotNumber;
        }
        auction.setAuctionAsvId(auctionAsvId);
        List<Lot> lots = lotController.findLotsByAuctionUnidOrderByLotNumber(auction.getAuctionUnid());
        List<com.baltinfo.radius.loadauction.model.Lot> tenderLots = tender.getLots();
        List<String> lotErrors = new ArrayList<>();
        for (com.baltinfo.radius.loadauction.model.Lot asvLot : tenderLots) {
            Lot eisLot = lots.stream()
                    .filter(l -> l.getLotNumber().equals(asvLot.getNumber()))
                    .findFirst()
                    .orElse(null);
            if (eisLot == null) {
                lotErrors.add("Не найден лот с номером " + asvLot.getNumber());
                continue;
            }
            eisLot.setLotAsvId(asvLot.getId().toString());
            eisLot.setLotAsvStageId(asvLot.getLotsInfo().get(0).getLotId());
        }

        Result<Void, String> res = auctionController.updateAuctionAndLots(auction, lots);
        if (res.isError()) {
            return "Ошибка при обновлении торгов и лотов. auctionUnid = " + auction.getAuctionUnid() + ". Ошибка: " + res.getError();
        } else {
            return StringUtils.join(lotErrors, ";");
        }
    }

    private Result<Map<Long, String>, String> getObjUnidsWithAssets(Publication publication, List<Lot> currentLots) {
        List<Tender> tenders = publication.getTenderFulls().getTenders();
        Map<Long, String> objAssets = new HashMap<>();
        for (Tender tender : tenders) {
            List<com.baltinfo.radius.loadauction.model.Lot> lots = tender.getLots();

            for (com.baltinfo.radius.loadauction.model.Lot lot : lots) {
                Optional<Lot> currentLotOpt = currentLots.stream()
                        .filter(l -> l.getLotNumber().equals(lot.getNumber()))
                        .findFirst();
                if (!currentLotOpt.isPresent()) {
                    continue;
                }
                Lot currentLot = currentLotOpt.get();
                Result<String, String> result = jsonExportToAssetsService.buildHtmlFromAssets(lot.getAssetFullList());
                if (result.isError()) {
                    return Result.error(result.getError());
                }
                String html = result.getResult();
                objAssets.put(currentLot.getObjUnid().getObjUnid(), html);
            }
        }
        return Result.ok(objAssets);
    }

    private Result<Publication, String> readPublicationFromFile(DocFile docFile) {
        String ext = docFile.getDfExt();
        Publication publication;
        switch (ext) {
            case "json":
                String jsonSource = readFile(pathToFiles + File.separator + docFile.getDfFilePath());
                if (jsonSource == null) {
                    return Result.error("Не удалось считать файл с именем: " + docFile.getDfName());
                }
                Result<Publication, String> loadJsonResult = jsonExportService.loadJsonFromString(jsonSource, docFile.getDfName());
                if (loadJsonResult.isError()) {
                    return Result.error(loadJsonResult.getError());
                }
                publication = loadJsonResult.getResult();
                break;
            case "xml":
                String xmlSource = readFile(pathToFiles + File.separator + docFile.getDfFilePath());
                if (xmlSource == null) {
                    return Result.error("Не удалось считать файл с именем: " + docFile.getDfName());
                }
                Result<Publication, String> loadXmlResult = xmlExportService.loadXmlFromString(xmlSource, docFile.getDfName());
                if (loadXmlResult.isError()) {
                    return Result.error(loadXmlResult.getError());
                }
                publication = loadXmlResult.getResult();
                break;
            default:
                return Result.error("Недопустимый формат файла: " + ext);
        }
        return Result.ok(publication);
    }

    private boolean saveProcedureInfo(ExchangeProcRun exchangeProcRun, String details, Integer status) {
        String text = exchangeProcRun.getEprErrorText() == null
                ? ""
                : exchangeProcRun.getEprErrorText();
        text += details;
        exchangeProcRun.setEprErrorText(text);
        exchangeProcRun.setEprLoadStatus(status);
        return exchangeProcController.saveExchangeProcRun(exchangeProcRun);
    }

    private static String readFile(String filePath) {
        try {
            return FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error("Can't get file source", e);
            return null;
        }
    }
}
