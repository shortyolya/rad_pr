package com.baltinfo.radius.lotonline.check;

import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.constants.Marketplaces;
import com.baltinfo.radius.db.constants.RunningDetailsStatus;
import com.baltinfo.radius.db.controller.AuctionController;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.controller.LotOnlineExchangeResultsController;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.RunningDetails;
import com.baltinfo.radius.db.model.lotonline.LotInfo;
import com.baltinfo.radius.utils.Result;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Suvorina Aleksandra
 * @since 23.07.2019
 */
public class LotOnlineCheckResultsService {

    private static final Logger logger = LoggerFactory.getLogger(LotOnlineCheckResultsService.class);
    private static final int MAX_PARTITION_SIZE = 1000;
    private final Long DEFAULT_PA_UNID = 1L;

    private final ExchangeProcController exchangeProcController;
    private final LotOnlineExchangeResultsController lotOnlineExchangeResultsController;
    private final LotController lotController;
    private final AuctionController auctionController;

    public LotOnlineCheckResultsService(ExchangeProcController exchangeProcController,
                                        LotOnlineExchangeResultsController lotOnlineExchangeResultsController,
                                        LotController lotController, AuctionController auctionController) {
        this.exchangeProcController = exchangeProcController;
        this.lotOnlineExchangeResultsController = lotOnlineExchangeResultsController;
        this.lotController = lotController;
        this.auctionController = auctionController;
    }

    public Result<Void, String> checkAndRunProcedure() {
        List<Auction> notFinishedAuctions = auctionController.getAuctionsOnEtpWithoutResults(
                Marketplaces.LOT_ONLINE.getMpUnid(),
                ExchangeProcs.RECEIVE_AUCTION_FROM_LOT_ONLINE.getUnid());

        if (notFinishedAuctions.size() > MAX_PARTITION_SIZE) {
            List<List<Auction>> partitions = ListUtils.partition(notFinishedAuctions, MAX_PARTITION_SIZE);
            for (List<Auction> part : partitions) {
                runProcedureByPartition(part);
            }
        } else {
            runProcedureByPartition(notFinishedAuctions);
        }
        return Result.ok();
    }

    private void runProcedureByPartition(List<Auction> notFinishedAuctions) {

        List<Long> tenderIds = notFinishedAuctions.stream()
                .map(Auction::getAuctionEtpId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(tenderIds)) {
            return;
        }
        List<LotInfo> finishedLots = lotOnlineExchangeResultsController.getFinishedLoLots(tenderIds);
        if (CollectionUtils.isEmpty(finishedLots)) {
            return;
        }

        Set<Long> finishedTenderIds = finishedLots.stream()
                .map(lotInfo -> lotInfo.getTenderFk().getId())
                .collect(Collectors.toSet());

        for (Long tenderId : finishedTenderIds) {
            ExchangeProcRun notFinishedEpr = exchangeProcController
                    .getNotStartErrorExchangeProcRun(ExchangeProcs.RECEIVE_AUCTION_FROM_LOT_ONLINE.getUnid(),
                    tenderId);
            if (isExchangeInProcess(notFinishedEpr)) {
                exchangeProcController.resumeExchange(notFinishedEpr);
            } else {
                Auction sendEpr = notFinishedAuctions.stream()
                        .filter(a -> a.getAuctionEtpId() != null && a.getAuctionEtpId().equals(tenderId))
                        .findFirst()
                        .orElse(null);
                startReceiveAuctionResults(sendEpr);
            }
        }
    }

    private boolean isExchangeInProcess(ExchangeProcRun epr) {
        if (epr == null) {
            return false;
        }
        Integer eprLoadStatus = epr.getEprLoadStatus();
        return eprLoadStatus.equals(ExchangeProcStatus.RUNNING.getCode())
                || eprLoadStatus.equals(ExchangeProcStatus.PAUSED.getCode())
                || eprLoadStatus.equals(ExchangeProcStatus.ERROR_RUNNING.getCode());
    }

    private Result<Void, String> startReceiveAuctionResults(Auction auction) {
        try {
            List<Lot> lots = lotController.getCurrentLotsByAuction(auction.getAuctionUnid());

            List<RunningDetails> runningDetails = buildRunningDetails(lots);

            ExchangeProcRun epr = buildExchangeProcRunWorking(ExchangeProcs.RECEIVE_AUCTION_FROM_LOT_ONLINE.getUnid(),
                    auction.getAuctionUnid(), auction.getAuctionEtpId(), DEFAULT_PA_UNID);

            Result<Void, String> createEprResult = exchangeProcController.saveExchangeProcRunWithDetails(epr, runningDetails);

            if (createEprResult.isError()) {
                ExchangeProcRun errorEpr = buildExchangeProcRunError(ExchangeProcs.RECEIVE_AUCTION_FROM_LOT_ONLINE.getUnid(),
                        auction.getAuctionUnid(),
                        createEprResult.getError());
                exchangeProcController.saveExchangeProcRun(errorEpr);
                return Result.error("Произошла ошибка при записи в журнале выполнения процедур.");
            }

            return Result.ok();
        } catch (Exception ex) {
            ExchangeProcRun errorEpr = buildExchangeProcRunError(ExchangeProcs.RECEIVE_AUCTION_FROM_LOT_ONLINE.getUnid(),
                    auction.getAuctionUnid(),
                    getStackTrace(ex));
            exchangeProcController.saveExchangeProcRun(errorEpr);
            logger.error("Error startReceiveAuctionResults", ex);
            return Result.error("Произошла ошибка при запуске процедуры.");
        }
    }

    private List<RunningDetails> buildRunningDetails(List<Lot> lots) {
        return lots.stream()
                .map(lot -> {
                    RunningDetails rd = new RunningDetails();
                    rd.setRdSourceId(lot.getLotUnid());
                    rd.setRdResult(RunningDetailsStatus.NOT_SENT.getCode());
                    return rd;
                })
                .collect(Collectors.toList());
    }


    private ExchangeProcRun buildExchangeProcRunWorking(Long exchangeProcUnid, Long sourceId, Long receiverId, Long paUnid) {
        ExchangeProcRun epr = new ExchangeProcRun();
        epr.setEpUnid(exchangeProcUnid);
        epr.setEprSourceId(sourceId);
        epr.setEprLoadStatus(ExchangeProcStatus.RUNNING.getCode());
        epr.setEprPaUnid(paUnid);
        epr.setEprRunDate(new Date());
        epr.setEprReceiverId(receiverId);
        return epr;
    }

    private ExchangeProcRun buildExchangeProcRunError(Long exchangeProcUnid, Long sourceId, String error) {
        ExchangeProcRun epr = new ExchangeProcRun();
        long paUnid = 1L;
        epr.setEpUnid(exchangeProcUnid);
        epr.setEprSourceId(sourceId);
        epr.setEprLoadStatus(ExchangeProcStatus.ERROR_START.getCode());
        epr.setEprErrorText(error);
        epr.setEprPaUnid(paUnid);
        epr.setEprRunDate(new Date());
        return epr;
    }

    private String getStackTrace(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }
}
