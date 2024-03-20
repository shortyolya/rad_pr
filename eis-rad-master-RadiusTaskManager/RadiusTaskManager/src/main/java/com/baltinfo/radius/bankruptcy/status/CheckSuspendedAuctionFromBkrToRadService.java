package com.baltinfo.radius.bankruptcy.status;

import com.baltinfo.radius.bankruptcy.BkrTypeStates;
import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.constants.LotStatus;
import com.baltinfo.radius.db.constants.Marketplaces;
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

public class CheckSuspendedAuctionFromBkrToRadService {

    private static final Logger logger = LoggerFactory.getLogger(CheckSuspendedAuctionFromBkrToRadService.class);

    private final AuctionController auctionController;
    private final UpdateDatesFromBkrController updateDatesFromBkrController;
    private final ExchangeProcController exchangeProcController;
    private final LotController lotController;

    public CheckSuspendedAuctionFromBkrToRadService(AuctionController auctionController, UpdateDatesFromBkrController updateDatesFromBkrController,
                                                    ExchangeProcController exchangeProcController, LotController lotController) {
        this.auctionController = Objects.requireNonNull(auctionController, "Can't load auction controller");
        this.updateDatesFromBkrController = Objects.requireNonNull(updateDatesFromBkrController, "Can't load updateDatesFromBkrController");
        this.exchangeProcController = Objects.requireNonNull(exchangeProcController, "Can't load exchangeProc controller");
        this.lotController = Objects.requireNonNull(lotController, "Can't load lot controller");
    }

    public void runProcedure() {
        List<Auction> notFinishedAuctions = auctionController.getAuctionsOnEtpWithoutResults(
                Marketplaces.BANKRUPTCY.getMpUnid(),
                ExchangeProcs.RECEIVE_AUCTION_FROM_BANKRUPTCY.getUnid());

        for (Auction auction : notFinishedAuctions) {

            try {
                List<Lot> eisLotList = lotController.findLotsByAuctionUnid(auction.getAuctionUnid());
                List<VAuctionLotAll> bkrLotList = updateDatesFromBkrController.getBkrLotsByAuctionUnid(auction.getAuctionEtpId());

                List<VAuctionLotAll> suspendedBkrLots = bkrLotList.stream()
                        .filter(lot -> lot.getDoTypeStateUnid() == BkrTypeStates.DO_TYPE_STATE_SUSPENDED.getUnid())
                        .collect(Collectors.toList());
                if (suspendedBkrLots.isEmpty()) {
                    continue;
                }

                List<Lot> notSuspendedEisLots = eisLotList.stream()
                        .filter(eisLot -> !eisLot.getLotStatus().equals(LotStatus.TRADE_SUSPENDED.getCode()))
                        .collect(Collectors.toList());

                List<String> bkrSuspendedLotNumbers = suspendedBkrLots.stream()
                        .map(VAuctionLotAll::getLotNumber)
                        .collect(Collectors.toList());

                boolean hasNotSuspendedInEis = notSuspendedEisLots.stream()
                        .anyMatch(lot -> bkrSuspendedLotNumbers.contains(lot.getLotNumber().toString()));
                if (!hasNotSuspendedInEis) {
                    continue;
                }

                ExchangeProcRun newEpr = new ExchangeProcRun();
                newEpr.setEpUnid(ExchangeProcs.CHECK_SUSPENDED_AUCTION.getUnid());
                newEpr.setEprLoadStatus(ExchangeProcStatus.RUNNING.getCode());
                newEpr.setEprSourceId(auction.getAuctionUnid());
                newEpr.setEprReceiverId(auction.getAuctionEtpId());
                exchangeProcController.saveExchangeProcRun(newEpr);
                StringBuilder errors = new StringBuilder();

                for (VAuctionLotAll bkrLot : suspendedBkrLots) {
                    RunningDetails runningDetails = new RunningDetails();
                    runningDetails.setRdReceiveId(bkrLot.getLotUnid());
                    runningDetails.setRdResult(RunningDetailsStatus.NOT_SENT.getCode());
                    runningDetails.setEprUnid(newEpr.getEprUnid());
                    Lot lot = eisLotList.stream()
                            .filter(l -> Long.parseLong(bkrLot.getLotNumber()) == l.getLotNumber())
                            .findFirst()
                            .orElse(null);
                    if (lot == null) {
                        String error = "Не удалось найти лот в ЕИС с номером " + bkrLot.getLotNumber() + " ";
                        errors.append(error);
                        updateRunningDetailError(runningDetails, error);
                        continue;
                    }
                    runningDetails.setRdSourceId(lot.getLotUnid());

                    if (bkrLot.getDoTypeStateUnid() == BkrTypeStates.DO_TYPE_STATE_SUSPENDED.getUnid()
                            && !lot.getLotStatus().equals(LotStatus.TRADE_SUSPENDED.getCode())) {

                        exchangeProcController.updateRunningDetails(runningDetails);

                        lot.setLotStatus(LotStatus.TRADE_SUSPENDED.getCode());
                        lot.setTsUnid(TypeStateConstant.LOT_SUSPENDED.getId());
                        Result<Lot, String> result = lotController.updateLot(lot);
                        if (result.isSuccess()) {
                            runningDetails.setRdResult(RunningDetailsStatus.SENT.getCode());
                            exchangeProcController.updateRunningDetails(runningDetails);
                        } else {
                            errors.append("Ошибка при обновлении лота. lotUnid = " + runningDetails.getRdSourceId().toString() + " " + result.getError() + " ");
                            updateRunningDetailError(runningDetails, result.getError());
                        }
                    }
                }

                if (suspendedBkrLots.size() == eisLotList.size()) {
                    auction.setTsUnid(TypeStateConstant.SUSPENDED.getId());
                    Result<Auction, String> result = auctionController.updateAuction(auction);
                    if (!result.isSuccess()) {
                        errors.append(result.getError());
                    }
                }

                if (errors.length() == 0) {
                    newEpr.setEprLoadStatus(ExchangeProcStatus.FINISHED.getCode());
                    exchangeProcController.updateExchangeProcRun(newEpr);
                } else {
                    updateEprErrorRunning(newEpr, errors.toString());
                }
            } catch (Exception ex) {
                logger.error("Error check suspended auctions. auctionUnid = {}", auction.getAuctionUnid(), ex);
                ExchangeProcRun eprError = new ExchangeProcRun();
                eprError.setEpUnid(ExchangeProcs.CHECK_SUSPENDED_AUCTION.getUnid());
                eprError.setEprSourceId(auction.getAuctionUnid());
                eprError.setEprReceiverId(auction.getAuctionEtpId());
                updateEprErrorRunning(eprError, getStackTrace(ex));
            }
        }
    }

    private String getStackTrace(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }

    private void updateRunningDetailError(RunningDetails runningDetails, String error) {
        runningDetails.setRdResult(RunningDetailsStatus.ERROR.getCode());
        runningDetails.setRdErrorInfo(error);
        exchangeProcController.updateRunningDetails(runningDetails);
    }

    private void updateEprErrorRunning(ExchangeProcRun epr, String error) {
        epr.setEprErrorText(error);
        epr.setEprLoadStatus(ExchangeProcStatus.ERROR_RUNNING.getCode());
        exchangeProcController.updateExchangeProcRun(epr);
    }

}
