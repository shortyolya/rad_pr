package com.baltinfo.radius.loadauction.job;

import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.controller.LoadAuctionController;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.db.model.LoadFile;
import com.baltinfo.radius.loadauction.ftp.FileStorage;
import com.baltinfo.radius.utils.Result;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 04.03.2020
 */

public class SendAsvTenderFileToFtpJob {
    private static final Logger logger = LoggerFactory.getLogger(SendAsvTenderFileToFtpJob.class);

    private final FileStorage fileStorage;
    private final LoadAuctionController loadAuctionController;
    private final ExchangeProcController exchangeProcController;

    public SendAsvTenderFileToFtpJob(FileStorage fileStorage, LoadAuctionController loadAuctionController,
                                     ExchangeProcController exchangeProcController) {
        this.fileStorage = Objects.requireNonNull(fileStorage, "Can't get fileStorage");
        this.loadAuctionController = Objects.requireNonNull(loadAuctionController, "Can't get loadAuctionController");
        this.exchangeProcController = Objects.requireNonNull(exchangeProcController, "Can't get exchangeProcController");
    }

    @Scheduled(cron = "${job.cron.asv.export.to.ftp}")
    public void runJob() {
        Long startDate = new Date().getTime();
        logger.info("start SendAsvTenderFileToFtpJob run job method at " + startDate);
        try {
            List<ExchangeProcRun> exchangeProcRunList = exchangeProcController.getEPRSendWithoutFinishedReceive(
                    ExchangeProcs.RECEIVE_CODES_FROM_BANKRUPTCY.getUnid(),
                    ExchangeProcs.SEND_AUCTION_TO_ASV.getUnid());
            List<Pair<Long, Auction>> auctions = new ArrayList<>();
            for (ExchangeProcRun epr : exchangeProcRunList) {
                Auction auction = loadAuctionController.getAuctionByUnid(epr.getEprSourceId());
                if (auction != null && loadAuctionController.isAsvAuction(auction.getAuctionUnid())) {
                    auctions.add(new Pair<>(epr.getEprReceiverId(), auction));
                }
            }
            for (Pair<Long, Auction> auction : auctions) {
                postAsvTenderAndCreateExchangeProcRun(auction);
            }
        } catch (Exception e) {
            logger.error("Can't post asv tender file to file storage system", e);
        } finally {
            logger.info("end SendAsvTenderFileToFtpJob run job method at " + new Date().getTime() +" duration = " + (new Date().getTime() - startDate));
        }
    }

    private void postAsvTenderAndCreateExchangeProcRun(Pair<Long, Auction> auction) {
        try {
            Result<LoadFile, String> loadFileResult = loadAuctionController.getLoadAsvTenderFileByBaUnid(auction.getValue().getBaUnid());
            if (loadFileResult.isError()) {
                ExchangeProcRun epr = buildExchangeProcRunError(ExchangeProcs.SEND_AUCTION_TO_ASV.getUnid(), auction.getValue().getAuctionUnid(), loadFileResult.getError());
                exchangeProcController.saveExchangeProcRun(epr);
                logger.error(loadFileResult.getError());
                return;
            }
            String interfaxMessNum = auction.getValue().getDpUnid() != null
                    ? auction.getValue().getDpUnid().getDpInterfaxMessNum()
                    : null;
            if (interfaxMessNum == null || interfaxMessNum.isEmpty()) {
                ExchangeProcRun epr = buildExchangeProcRunError(ExchangeProcs.SEND_AUCTION_TO_ASV.getUnid(),
                        auction.getValue().getAuctionUnid(), "Номер сообщения ЕФРСБ не заполнен");
                exchangeProcController.saveExchangeProcRun(epr);
                return;
            }
            Result<Void, String> postAsvTenderFileResult = fileStorage.postAsvTenderFile(interfaxMessNum, auction.getValue().getAuctionEtpCode(), loadFileResult.getResult());
            if (postAsvTenderFileResult.isError()) {
                ExchangeProcRun epr = buildExchangeProcRunError(ExchangeProcs.SEND_AUCTION_TO_ASV.getUnid(), auction.getValue().getAuctionUnid(), postAsvTenderFileResult.getError());
                exchangeProcController.saveExchangeProcRun(epr);
                logger.error(postAsvTenderFileResult.getError());
                return;
            }
            ExchangeProcRun epr = buildExchangeProcRun(ExchangeProcs.SEND_AUCTION_TO_ASV.getUnid(), auction.getValue().getAuctionUnid(), auction.getKey());
            exchangeProcController.saveExchangeProcRun(epr);
        } catch (Exception e) {
            logger.error("Can't post asv tender to server or create exchange_proc_run record", e);
            ExchangeProcRun epr = buildExchangeProcRunError(ExchangeProcs.SEND_AUCTION_TO_ASV.getUnid(), auction.getValue().getAuctionUnid(), e.getMessage());
            exchangeProcController.saveExchangeProcRun(epr);
        }
    }

    private ExchangeProcRun buildExchangeProcRun(Long exchangeProcUnid, Long sourceId, Long receiverId) {
        ExchangeProcRun epr = new ExchangeProcRun();
        Long paUnid = 1L;
        epr.setEpUnid(exchangeProcUnid);
        epr.setEprSourceId(sourceId);
        epr.setEprLoadStatus(ExchangeProcStatus.FINISHED.getCode());
        epr.setEprPaUnid(paUnid);
        epr.setEprRunDate(new Date());
        epr.setEprReceiverId(receiverId);
        return epr;
    }

    private ExchangeProcRun buildExchangeProcRunError(Long exchangeProcUnid, Long sourceId, String error) {
        ExchangeProcRun epr = new ExchangeProcRun();
        long paUnid = 1L;
        epr.setEpUnid(exchangeProcUnid);
        epr.setEprLoadStatus(ExchangeProcStatus.ERROR_START.getCode());
        epr.setEprSourceId(sourceId);
        epr.setEprErrorText(error);
        epr.setEprRunDate(new Date());
        epr.setEprPaUnid(paUnid);
        return epr;
    }
}
