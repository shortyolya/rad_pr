package com.baltinfo.radius.bankruptcy.counter;

import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.constants.Marketplaces;
import com.baltinfo.radius.db.controller.AuctionController;
import com.baltinfo.radius.db.controller.BkrCounterController;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.RunningDetails;
import com.baltinfo.radius.db.model.bankruptcy.VAuctionLotAll;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Suvorina Aleksandra
 * @since 26.10.2020
 */
public class BkrReceiveLotCounterService {

    private static final Logger logger = LoggerFactory.getLogger(BkrReceiveLotCounterService.class);

    private final ExchangeProcController exchangeProcController;
    private final BkrCounterController bkrCounterController;
    private final LotController lotController;
    private final AuctionController auctionController;

    public BkrReceiveLotCounterService(ExchangeProcController exchangeProcController,
                                       BkrCounterController bkrCounterController,
                                       LotController lotController,
                                       AuctionController auctionController) {
        this.exchangeProcController = exchangeProcController;
        this.bkrCounterController = bkrCounterController;
        this.lotController = lotController;
        this.auctionController = auctionController;
    }

    public void runProcedure() {
        List<Auction> notFinishedAuctions = auctionController.getAuctionsOnEtpWithoutResults(
                Marketplaces.BANKRUPTCY.getMpUnid(),
                ExchangeProcs.RECEIVE_AUCTION_FROM_BANKRUPTCY.getUnid());

        List<Long> tenderIds = notFinishedAuctions.stream()
                .map(Auction::getAuctionEtpId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(tenderIds)) {
            return;
        }


        for (Auction auction : notFinishedAuctions) {
            List<String> errors = new ArrayList<>();

            List<Lot> lots = lotController.getCurrentLotsByAuction(auction.getAuctionUnid());

            List<VAuctionLotAll> bkrLots = bkrCounterController.getBkrLotsByAuctionUnid(auction.getAuctionEtpId());

            for (Lot eisLot : lots) {
                try {
                    VAuctionLotAll bkrLot = bkrLots.stream()
                            .filter(l -> l.getLotNumber().equals(eisLot.getLotNumber() + ""))
                            .findFirst()
                            .orElse(null);
                    if (bkrLot == null) {
                        errors.add("Лот №" + eisLot.getLotNumber() + " не найден на площадке Банкротство.");
                        continue;
                    }
                    Long count = bkrCounterController.getViewCountByLotUnid(bkrLot.getLotUnid());
                    if (count == null) {
                        errors.add("Не удалось получить количество просмотров по лоту №" + eisLot.getLotNumber());
                    } else {
                        eisLot.setLotViewCount(count);
                    }
                    Long applCount = bkrCounterController.getApplCountByLotUnid(bkrLot.getLotUnid());
                    if (applCount == null) {
                        errors.add("Не удалось получить количество поданных заявок по лоту №" + eisLot.getLotNumber());
                    } else {
                        eisLot.setLotApplCount(applCount);
                    }
                    if ((count != null && count > 0) || (applCount != null && applCount > 0)) {
                        lotController.updateLot(eisLot);
                    }
                } catch (Exception ex) {
                    logger.error("Error update lot_view_count eis lotUnid = {}",
                            eisLot.getLotUnid(), ex);
                    errors.add("Не удалось получить количество просмотров и количество заявок");
                }
            }
            if (errors.isEmpty()) {
                ExchangeProcRun newEpr = createSuccessEpr(auction.getAuctionUnid(), auction.getAuctionEtpId());
                exchangeProcController.saveExchangeProcRun(newEpr);
            } else {
                ExchangeProcRun newEpr = createErrorEpr(auction.getAuctionUnid(), auction.getAuctionEtpId(),
                        String.join("\n", errors));
                exchangeProcController.saveExchangeProcRun(newEpr);
            }
        }

    }

    private ExchangeProcRun createSuccessEpr(Long eprSourceId, Long eprReceiverId) {
        ExchangeProcRun epr = new ExchangeProcRun();
        epr.setEprLoadStatus(ExchangeProcStatus.FINISHED.getCode());
        epr.setEprSourceId(eprSourceId);
        epr.setEprReceiverId(eprReceiverId);
        epr.setEpUnid(ExchangeProcs.UPDATE_LOT_VIEW_COUNT.getUnid());
        epr.setEprRunDate(new Date());
        epr.setEprPaUnid(1L);
        return epr;
    }

    private ExchangeProcRun createErrorEpr(Long eprSourceId, Long eprReceiverId, String errors) {
        ExchangeProcRun epr = new ExchangeProcRun();
        epr.setEprLoadStatus(ExchangeProcStatus.ERROR_RUNNING.getCode());
        epr.setEprSourceId(eprSourceId);
        epr.setEprReceiverId(eprReceiverId);
        epr.setEpUnid(ExchangeProcs.UPDATE_LOT_VIEW_COUNT.getUnid());
        epr.setEprErrorText(errors);
        epr.setEprRunDate(new Date());
        epr.setEprPaUnid(1L);
        return epr;
    }
}
