package com.baltinfo.radius.exchange.impl;

import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.RunningDetailsStatus;
import com.baltinfo.radius.db.constants.TypeStateConstant;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.controller.OperJournalController;
import com.baltinfo.radius.db.controller.exchange.EtpEntity;
import com.baltinfo.radius.db.controller.exchange.ExchangeUnloadController;
import com.baltinfo.radius.db.model.ExchangeProc;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.RunningDetails;
import com.baltinfo.radius.exchange.ExchangeEntityParams;
import com.baltinfo.radius.exchange.ExchangeParamsBuilder;
import com.baltinfo.radius.exchange.ExchangeResult;
import com.baltinfo.radius.exchange.OuterLinkService;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 22.08.2019
 */
public class ExportToEtpService<T extends EtpEntity, K extends ExchangeEntityParams> {

    private static final Logger logger = LoggerFactory.getLogger(ExportToEtpService.class);

    private final ExchangeProcController exchangeProcController;
    private final ExchangeUnloadController<T, K> exchangeUnloadController;
    private final LotController lotController;
    private final OperJournalController operJournalController;
    private final OuterLinkService outerLinkService;
    private final ExchangeParamsBuilder<K> exchangeParamsBuilder;
    private final Long marketplaceUnid;

    private final String LOT_ERROR_TEMPLATE = "Ошибка при выгрузке лота №%s: %s";
    private final String LOT_LOADED_WITH_ERRORS_TEMPLATE = "Лот №%s выгружен с ошибкой: %s";
    private final String COMMON_ERROR_TEMPLATE = "Ошибки при выгрузке: %s";

    public ExportToEtpService(ExchangeProcController exchangeProcController,
                              ExchangeUnloadController exchangeUnloadController,
                              LotController lotController,
                              OperJournalController operJournalController,
                              OuterLinkService outerLinkService,
                              ExchangeParamsBuilder<K> exchangeParamsBuilder, Long marketplaceUnid) {
        this.exchangeProcController = exchangeProcController;
        this.exchangeUnloadController = exchangeUnloadController;
        this.lotController = lotController;
        this.operJournalController = operJournalController;
        this.outerLinkService = outerLinkService;
        this.exchangeParamsBuilder = exchangeParamsBuilder;
        this.marketplaceUnid = marketplaceUnid;
    }

    public Result<Void, String> runProcedure(Long eprUnid, boolean stopOnError) {
        List<RunningDetails> details = exchangeProcController.getNotSentRunningDetails(eprUnid);
        List<String> errors = new ArrayList<>();
        for (RunningDetails runningDetails : details) {
            try {
                ExchangeProcRun epr = exchangeProcController.getExchangeProcRun(eprUnid);
                if (!epr.getEprLoadStatus().equals(ExchangeProcStatus.RUNNING.getCode())) {
                    if (!errors.isEmpty()) {
                        String error = String.format(COMMON_ERROR_TEMPLATE, String.join(";\n", errors));
                        updateEprErrorRunning(epr);
                        return Result.error(error);
                    } else {
                        return Result.ok();
                    }
                }
                Lot lot = lotController.getLot(runningDetails.getRdSourceId());

                T eptAuction = exchangeUnloadController.getEtpAuction(epr.getEprReceiverId());
                if (eptAuction == null) {
                    epr = exchangeProcController.getExchangeProcRun(eprUnid);
                    String error = String.format(COMMON_ERROR_TEMPLATE, "аукцион не найден на площадке");
                    updateEprErrorStart(epr, error);
                    return Result.error(error);
                }

                Result<K, String> params = exchangeParamsBuilder.buildParams(runningDetails.getRdSourceId());
                if (params.isError()) {
                    String error = String.format(COMMON_ERROR_TEMPLATE, params.getError());
                    updateRunningDetailError(runningDetails, error);
                    epr = exchangeProcController.getExchangeProcRun(eprUnid);
                    updateEprErrorRunning(epr);
                    return Result.error(error);
                }

                ExchangeResult exportResult = exchangeUnloadController.exportLot(eptAuction, params.getResult());

                if (!exportResult.isLoaded()) {
                    String error = String.format(LOT_ERROR_TEMPLATE, lot.getLotNumber(), exportResult.getError());
                    updateRunningDetailError(runningDetails, error);
                    if (stopOnError) {
                        epr = exchangeProcController.getExchangeProcRun(eprUnid);
                        updateEprErrorRunning(epr);
                        return Result.error(error);
                    } else {
                        errors.add(error);
                    }
                } else {
                    lotController.updateObjectStatus(lot.getObjUnid().getObjUnid(), TypeStateConstant.TRADE_ASSIGNED.getId());
                    ExchangeProc exchangeProc = exchangeProcController.getExchangeProc(epr.getEpUnid());
                    operJournalController.createOperJournalForLotExchange(lot.getLotUnid(),
                            exchangeProc.getEpName(), epr.getEprPaUnid());

                    updateLotLinkSite(lot.getLotUnid(), exportResult.getLotUnid());
                    runningDetails.setRdResult(RunningDetailsStatus.SENT.getCode());
                    if (exportResult.hasError()) {
                        String error = String.format(LOT_LOADED_WITH_ERRORS_TEMPLATE, lot.getLotNumber(), exportResult.getError());
                        runningDetails.setRdErrorInfo(error);
                    }
                    runningDetails.setRdReceiveId(exportResult.getLotUnid());
                    exchangeProcController.updateRunningDetails(runningDetails);
                }
            } catch (Exception ex) {
                logger.error("Error when service lot. lotUnid = {}", runningDetails.getRdSourceId(), ex);
                Lot lot = lotController.getLot(runningDetails.getRdSourceId());

                String error = String.format(LOT_ERROR_TEMPLATE, lot.getLotNumber(), ex.getMessage());
                updateRunningDetailError(runningDetails, error);
                if (stopOnError) {
                    ExchangeProcRun epr = exchangeProcController.getExchangeProcRun(eprUnid);
                    updateEprErrorRunning(epr);
                    return Result.error(error);
                } else {
                    errors.add(error);
                }
            }
        }
        ExchangeProcRun epr = exchangeProcController.getExchangeProcRun(eprUnid);
        if (!errors.isEmpty()) {
            String error = String.format(COMMON_ERROR_TEMPLATE, String.join(";\n", errors));
            updateEprErrorRunning(epr);
            return Result.error(error);
        } else {
            List<RunningDetails> allDetails = exchangeProcController.getRunningDetails(eprUnid);
            boolean allSent = allDetails.stream()
                    .allMatch(rd -> rd.getRdResult().equals(RunningDetailsStatus.SENT.getCode()) && rd.getRdReceiveId() != null);
            if (allSent) {
                lotController.updateAuctionAfterSendToEtp(epr.getEprSourceId(), epr.getEprReceiverId(), marketplaceUnid,
                        TypeStateConstant.TRADE_IN_PROCESS.getId());
                epr.setEprLoadStatus(ExchangeProcStatus.FINISHED.getCode());
                exchangeProcController.updateExchangeProcRun(epr);
            }
            return Result.ok();
        }
    }

    private void updateLotLinkSite(Long eisLotUnid, Long etpLotUnid) {
        String lotLinkSite = outerLinkService.formOuterLink(etpLotUnid);
        lotController.updateLotLinkSite(eisLotUnid, lotLinkSite);
    }

    private void updateEprErrorRunning(ExchangeProcRun epr) {
        epr.setEprLoadStatus(ExchangeProcStatus.ERROR_RUNNING.getCode());
        exchangeProcController.updateExchangeProcRun(epr);
    }

    private void updateEprErrorStart(ExchangeProcRun epr, String error) {
        epr.setEprLoadStatus(ExchangeProcStatus.ERROR_START.getCode());
        String fullError = error;
        if (epr.getEprErrorText() != null) {
            fullError = String.join(";\n", epr.getEprErrorText(), error);
        }
        epr.setEprErrorText(fullError);
        exchangeProcController.updateExchangeProcRun(epr);
    }

    private void updateRunningDetailError(RunningDetails runningDetails, String error) {
        runningDetails.setRdResult(RunningDetailsStatus.ERROR.getCode());
        runningDetails.setRdErrorInfo(error);
        exchangeProcController.updateRunningDetails(runningDetails);
    }
}
