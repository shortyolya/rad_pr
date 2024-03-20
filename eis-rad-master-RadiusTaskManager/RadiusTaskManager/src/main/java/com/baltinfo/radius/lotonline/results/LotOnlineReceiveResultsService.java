package com.baltinfo.radius.lotonline.results;

import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.RunningDetailsStatus;
import com.baltinfo.radius.db.constants.TypeStateConstant;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.controller.LotOnlineExchangeResultsController;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.RunningDetails;
import com.baltinfo.radius.exchange.ExchangeResult;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 10.10.2018
 */
public class LotOnlineReceiveResultsService {

    private static final Logger logger = LoggerFactory.getLogger(LotOnlineReceiveResultsService.class);

    private final ExchangeProcController exchangeProcController;
    private final LotOnlineExchangeResultsController lotOnlineExchangeResultsController;
    private final LotController lotController;

    private final String LOT_ERROR_TEMPLATE = "Ошибка при загрузке результатов торгов по лоту №%s: %s";
    private final String COMMON_ERROR_TEMPLATE = "Ошибка при загрузке результатов торгов: %s";
    private final String AUCTION_ERROR_TEMPLATE = "Ошибка при обновлении статуса торгов: %s";
    private final String LOT_LOADED_WITH_ERRORS_TEMPLATE = "Лот №%s загружен с ошибкой: %s";

    public LotOnlineReceiveResultsService(ExchangeProcController exchangeProcController,
                                          LotOnlineExchangeResultsController lotOnlineExchangeResultsController,
                                          LotController lotController) {
        this.exchangeProcController = exchangeProcController;
        this.lotOnlineExchangeResultsController = lotOnlineExchangeResultsController;
        this.lotController = lotController;
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
                        updateEprError(epr, error);
                        return Result.error(error);
                    } else {
                        return Result.ok();
                    }
                }

                Auction eisAuction = lotController.getAuction(epr.getEprSourceId());
                Lot eisLot = lotController.getLot(runningDetails.getRdSourceId());
                Long tenderId = epr.getEprReceiverId();

                if (eisLot.getTsUnid().equals(TypeStateConstant.LOT_CANCEL.getId())) {
                    runningDetails.setRdResult(RunningDetailsStatus.DO_NOT_SEND.getCode());
                    exchangeProcController.updateRunningDetails(runningDetails);
                    continue;
                }

                Result<Boolean, String> checkLotIsFinishedResult = lotOnlineExchangeResultsController
                        .checkLotIsFinished(tenderId,
                        eisLot.getLotNumber());
                if (checkLotIsFinishedResult.isError()) {
                    String error = String.format(LOT_ERROR_TEMPLATE, eisLot.getLotNumber(), checkLotIsFinishedResult.getError());
                    updateRunningDetailError(runningDetails, error);
                    if (stopOnError) {
                        updateEprError(epr, error);
                        return Result.error(error);
                    } else {
                        errors.add(error);
                        continue;
                    }
                }

                Boolean lotFinished = checkLotIsFinishedResult.getResult();
                if (!lotFinished) {
                    continue;
                }
                ExchangeResult receiveResult = lotOnlineExchangeResultsController.receiveResultsByLot(eisAuction,
                        eisLot,
                        tenderId, epr.getEprPaUnid());
                if (!receiveResult.isLoaded()) {
                    String error = String.format(LOT_ERROR_TEMPLATE, eisLot.getLotNumber(), receiveResult.getError());
                    updateRunningDetailError(runningDetails, error);
                    if (stopOnError) {
                        updateEprError(epr, error);
                        return Result.error(error);
                    } else {
                        errors.add(error);
                    }
                } else {
                    runningDetails.setRdResult(RunningDetailsStatus.SENT.getCode());
                    if (receiveResult.hasError()) {
                        String error = String.format(LOT_LOADED_WITH_ERRORS_TEMPLATE, eisLot.getLotNumber(), receiveResult.getError());
                        runningDetails.setRdErrorInfo(error);
                    }
                    runningDetails.setRdReceiveId(receiveResult.getLotUnid());
                    exchangeProcController.updateRunningDetails(runningDetails);
                }
            } catch (Exception ex) {
                logger.error("Error when receive results for lot. lotUnid = {}", runningDetails.getRdSourceId(), ex);
                Lot eisLot = lotController.getLot(runningDetails.getRdSourceId());
                String error = String.format(LOT_ERROR_TEMPLATE, eisLot.getLotNumber(), ex.getMessage());
                updateRunningDetailError(runningDetails, error);
                if (stopOnError) {
                    ExchangeProcRun epr = exchangeProcController.getExchangeProcRun(eprUnid);
                    updateEprError(epr, error);
                    return Result.error(error);
                } else {
                    errors.add(error);
                    continue;
                }
            }
        }
        ExchangeProcRun epr = exchangeProcController.getExchangeProcRun(eprUnid);
        if (!errors.isEmpty()) {
            String error = String.format(COMMON_ERROR_TEMPLATE, String.join(";\n", errors));
            updateEprError(epr, error);
            return Result.error(error);
        } else {
            List<RunningDetails> allDetails = exchangeProcController.getRunningDetails(eprUnid);
            boolean allSent = allDetails.stream()
                    .allMatch(rd -> (rd.getRdResult().equals(RunningDetailsStatus.SENT.getCode()) && rd.getRdReceiveId() != null)
                            || rd.getRdResult().equals(RunningDetailsStatus.DO_NOT_SEND.getCode()));
            if (allSent) {
                Result<Void, String> auctionStateResult = lotOnlineExchangeResultsController
                        .setAuctionStateAfterExchange(epr.getEprSourceId(), epr.getEprPaUnid());
                if (auctionStateResult.isError()) {
                    String error = String.format(AUCTION_ERROR_TEMPLATE, auctionStateResult.getError());
                    updateEprError(epr, error);
                    return Result.error(error);
                } else {
                    epr.setEprLoadStatus(ExchangeProcStatus.FINISHED.getCode());
                    exchangeProcController.updateExchangeProcRun(epr);
                }
            } else {
                epr.setEprLoadStatus(ExchangeProcStatus.PAUSED.getCode());
                exchangeProcController.updateExchangeProcRun(epr);
            }
            return Result.ok();
        }
    }

    private void updateEprError(ExchangeProcRun epr, String error) {
        epr.setEprLoadStatus(ExchangeProcStatus.ERROR_RUNNING.getCode());
        epr.setEprErrorText(error);
        exchangeProcController.updateExchangeProcRun(epr);
    }

    private void updateRunningDetailError(RunningDetails runningDetails, String error) {
        runningDetails.setRdResult(RunningDetailsStatus.ERROR.getCode());
        runningDetails.setRdErrorInfo(error);
        exchangeProcController.updateRunningDetails(runningDetails);
    }

}
