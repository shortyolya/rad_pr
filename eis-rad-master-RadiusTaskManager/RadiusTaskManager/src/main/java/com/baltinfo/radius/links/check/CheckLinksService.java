package com.baltinfo.radius.links.check;

import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.controller.AuctionController;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.links.results.SynchronizeLinksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class CheckLinksService {

    private static final Logger logger = LoggerFactory.getLogger(SynchronizeLinksService.class);
    private final Long DEFAULT_PA_UNID = 1L;
    private final ExchangeProcController exchangeProcController;
    private final AuctionController auctionController;

    public CheckLinksService(ExchangeProcController exchangeProcController, AuctionController auctionController) {
        this.exchangeProcController = exchangeProcController;
        this.auctionController = auctionController;
    }

    public void runProcedure() {
        List<Auction> auctionsAh = auctionController.findAuctionsForSyncLinks(ExchangeProcs.SEND_AUCTION_TO_AUCTION_HOUSE);
        createExchangeProcRun(auctionsAh, ExchangeProcs.SEND_AUCTION_TO_AUCTION_HOUSE.getUnid(), ExchangeProcs.SYNCHRONIZE_LINKS_AUCTION_HOUSE.getUnid());

        List<Auction> auctionsRad = auctionController.findAuctionsForSyncLinks(ExchangeProcs.SEND_AUCTION_TO_RAD_HOLDING);
        createExchangeProcRun(auctionsRad, ExchangeProcs.SEND_AUCTION_TO_RAD_HOLDING.getUnid(), ExchangeProcs.SYNCHRONIZE_LINKS_RAD_HOLDING.getUnid());
    }

    private ExchangeProcRun buildExchangeProcRunWorking(Long exchangeProcUnid, Long sourceId, Long receiverId, Long paUnid) {
        ExchangeProcRun epr = new ExchangeProcRun();
        epr.setEpUnid(exchangeProcUnid);
        epr.setEprSourceId(sourceId);
        epr.setEprLoadStatus(ExchangeProcStatus.RUNNING.getCode());
        epr.setEprReceiverId(receiverId);
        epr.setEprPaUnid(paUnid);
        epr.setEprRunDate(new Date());
        return epr;
    }

    private ExchangeProcRun buildExchangeProcRunError(Long exchangeProcUnid, Long sourceId) {
        ExchangeProcRun epr = new ExchangeProcRun();
        epr.setEpUnid(exchangeProcUnid);
        epr.setEprSourceId(sourceId);
        epr.setEprLoadStatus(ExchangeProcStatus.ERROR_START.getCode());
        epr.setEprPaUnid(DEFAULT_PA_UNID);
        epr.setEprRunDate(new Date());
        return epr;
    }

    private void createExchangeProcRun(List<Auction> auctions, Long epUnid, Long ep4Create) {
        for (Auction auction : auctions) {
            Long receiverId = exchangeProcController.getFinishedExchangeProcRunByAuction(epUnid, auction.getAuctionUnid()).getEprReceiverId();
            ExchangeProcRun epr = buildExchangeProcRunWorking(ep4Create, auction.getAuctionUnid(), receiverId, DEFAULT_PA_UNID);
            boolean createEpr = exchangeProcController.saveExchangeProcRun(epr);
            if (!createEpr) {
                ExchangeProcRun errorEpr = buildExchangeProcRunError(ep4Create, auction.getAuctionUnid());
                exchangeProcController.saveExchangeProcRun(errorEpr);
            }
        }
    }
}
