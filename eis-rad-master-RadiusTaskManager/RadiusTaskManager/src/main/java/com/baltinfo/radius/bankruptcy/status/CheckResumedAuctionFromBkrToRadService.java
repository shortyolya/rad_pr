package com.baltinfo.radius.bankruptcy.status;

import com.baltinfo.radius.bankruptcy.BkrTypeStates;
import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.constants.LotStatus;
import com.baltinfo.radius.db.constants.RunningDetailsStatus;
import com.baltinfo.radius.db.constants.TypeStateConstant;
import com.baltinfo.radius.db.controller.AuctionController;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.controller.UpdateDatesFromBkrController;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.RunningDetails;
import com.baltinfo.radius.db.model.bankruptcy.VAuctionLotAll;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author AAA
 * @since 25.11.2021
 */
public class CheckResumedAuctionFromBkrToRadService {

    private static final Logger logger = LoggerFactory.getLogger(CheckResumedAuctionFromBkrToRadService.class);

    private final AuctionController auctionController;
    private final UpdateDatesFromBkrController updateDatesFromBkrController;
    private final ExchangeProcController exchangeProcController;
    private final LotController lotController;

    public CheckResumedAuctionFromBkrToRadService(AuctionController auctionController, UpdateDatesFromBkrController updateDatesFromBkrController,
                                                  ExchangeProcController exchangeProcController, LotController lotController) {
        this.auctionController = Objects.requireNonNull(auctionController, "Can't load auction controller");
        this.updateDatesFromBkrController = Objects.requireNonNull(updateDatesFromBkrController, "Can't load updateDatesFromBkrController");
        this.exchangeProcController = Objects.requireNonNull(exchangeProcController, "Can't load exchangeProc controller");
        this.lotController = Objects.requireNonNull(lotController, "Can't load lot controller");
    }

    public void runProcedure() {
        List<ExchangeProcRun> suspendedEpr = exchangeProcController.getSuspendedBkrAuctionExchangeProcRuns();

        for (ExchangeProcRun epr : suspendedEpr) {

            ExchangeProcRun newEpr = new ExchangeProcRun();
            newEpr.setEpUnid(ExchangeProcs.CHECK_RESUMED_AUCTION.getUnid());
            newEpr.setEprLoadStatus(ExchangeProcStatus.RUNNING.getCode());
            newEpr.setEprSourceId(epr.getEprSourceId());
            newEpr.setEprReceiverId(epr.getEprReceiverId());

            try {
                List<RunningDetails> suspendedRunningDetails = exchangeProcController.getRunningDetails(epr.getEprUnid());

                suspendedRunningDetails = suspendedRunningDetails.stream()
                        .filter(rd -> rd.getRdResult().equals(RunningDetailsStatus.SENT.getCode()))
                        .collect(Collectors.toList());

                List<VAuctionLotAll> bkrLotList = updateDatesFromBkrController.getBkrLotsByAuctionUnid(epr.getEprReceiverId());

                StringBuilder errors = new StringBuilder();
                List<RunningDetails> resumeRd = new ArrayList<>();

                for (RunningDetails rd : suspendedRunningDetails) {
                    Lot eisLot = lotController.getLot(rd.getRdSourceId());

                    RunningDetails runningDetails = new RunningDetails();
                    runningDetails.setRdSourceId(eisLot.getLotUnid());
                    runningDetails.setRdResult(RunningDetailsStatus.NOT_SENT.getCode());

                    if (!eisLot.getLotStatus().equals(LotStatus.TRADE_SUSPENDED.getCode())) {
                        continue;
                    }
                    VAuctionLotAll bkrLot = bkrLotList.stream()
                            .filter(lot -> lot.getLotNumber().equals(eisLot.getLotNumber().toString()))
                            .findFirst()
                            .orElse(null);
                    if (bkrLot == null) {
                        String error = "Не удалось найти лот на ЭТП с номером " + eisLot.getLotNumber() + " ";
                        errors.append(error);
                        runningDetails = fillRunningDetailError(runningDetails, error);
                        resumeRd.add(runningDetails);
                        continue;
                    }

                    if (bkrLot.getDoTypeStateUnid() == BkrTypeStates.DO_TYPE_STATE_SUSPENDED.getUnid()) {
                        continue;
                    }
                    runningDetails.setRdReceiveId(bkrLot.getLotUnid());

                    eisLot.setLotStatus(LotStatus.TRADE_NO.getCode());
                    eisLot.setTsUnid(TypeStateConstant.NOT_TAKE_PLACE.getId());
                    Result<Lot, String> result = lotController.updateLot(eisLot);
                    if (result.isSuccess()) {
                        runningDetails.setRdResult(RunningDetailsStatus.SENT.getCode());
                    } else {
                        errors.append("Ошибка при обновлении лота. lotUnid = " + runningDetails.getRdSourceId().toString() + " " + result.getError() + " ");
                        runningDetails = fillRunningDetailError(runningDetails, result.getError());
                    }
                    resumeRd.add(runningDetails);
                }

                resumeRd = resumeRd.stream()
                        .filter(rd -> !rd.getRdResult().equals(RunningDetailsStatus.NOT_SENT.getCode()))
                        .collect(Collectors.toList());

                List<Lot> eisLots = lotController.findLotsByAuctionUnid(epr.getEprSourceId());
                boolean hasSuspended = eisLots.stream()
                        .anyMatch(l -> l.getLotStatus().equals(LotStatus.TRADE_SUSPENDED.getCode()));
                if (!hasSuspended) {
                    Auction auction = auctionController.findByAuctionUnid(epr.getEprSourceId());
                    auction.setTsUnid(TypeStateConstant.TRADE_IN_PROCESS.getId());
                    Result<Auction, String> result = auctionController.updateAuction(auction);
                    if (!result.isSuccess()) {
                        errors.append(result.getError());
                    }
                }

                if (errors.length() == 0) {
                    if (!resumeRd.isEmpty()) {
                        newEpr.setEprLoadStatus(ExchangeProcStatus.FINISHED.getCode());
                        exchangeProcController.updateExchangeProcRun(newEpr);

                        for (RunningDetails rd : resumeRd) {
                            rd.setEprUnid(newEpr.getEprUnid());
                            exchangeProcController.updateRunningDetails(rd);
                        }


                        ExchangeProcRun eprUpdateDates = new ExchangeProcRun();
                        eprUpdateDates.setEpUnid(ExchangeProcs.UPDATE_DATES_FROM_BANKRUPTCY.getUnid());
                        eprUpdateDates.setEprLoadStatus(ExchangeProcStatus.RUNNING.getCode());
                        eprUpdateDates.setEprSourceId(epr.getEprSourceId());
                        eprUpdateDates.setEprReceiverId(epr.getEprReceiverId());
                        exchangeProcController.saveExchangeProcRun(eprUpdateDates);
                    }
                } else {
                    updateEprErrorRunning(newEpr, errors.toString());
                    for (RunningDetails rd : resumeRd) {
                        rd.setEprUnid(newEpr.getEprUnid());
                        exchangeProcController.updateRunningDetails(rd);
                    }
                }

            } catch (Exception ex) {
                logger.error("Error check resumed auctions. epr_unid = {}", epr.getEprUnid(), ex);
                updateEprErrorRunning(newEpr, getStackTrace(ex));
            }
        }
    }

    private String getStackTrace(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }

    private RunningDetails fillRunningDetailError(RunningDetails runningDetails, String error) {
        runningDetails.setRdResult(RunningDetailsStatus.ERROR.getCode());
        runningDetails.setRdErrorInfo(error);
        return runningDetails;
    }

    private void updateEprErrorRunning(ExchangeProcRun epr, String error) {
        epr.setEprErrorText(error);
        epr.setEprLoadStatus(ExchangeProcStatus.ERROR_RUNNING.getCode());
        exchangeProcController.updateExchangeProcRun(epr);
    }

}
