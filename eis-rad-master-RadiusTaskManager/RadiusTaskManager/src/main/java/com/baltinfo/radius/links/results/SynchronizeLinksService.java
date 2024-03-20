package com.baltinfo.radius.links.results;

import com.baltinfo.radius.application.configuration.AuctionHouseLinksProperties;
import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.controller.LotsController;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.Lots;
import com.baltinfo.radius.db.model.ObjectJPA;
import com.baltinfo.radius.links.constant.Entities;
import com.baltinfo.radius.links.constant.RecipientSite;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SynchronizeLinksService {

    private static final Logger logger = LoggerFactory.getLogger(SynchronizeLinksService.class);

    private final ExchangeProcController exchangeProcController;
    private final LotController lotController;
    private final LotsController lotsController;

    private final AuctionHouseLinksProperties auctionHouseLinksProperties;

    public SynchronizeLinksService(ExchangeProcController exchangeProcController, LotController lotController, LotsController lotsController, AuctionHouseLinksProperties auctionHouseLinksProperties) {
        this.exchangeProcController = exchangeProcController;
        this.lotController = lotController;
        this.lotsController = lotsController;
        this.auctionHouseLinksProperties = auctionHouseLinksProperties;
    }

    public void runProcedure(Long eprUnid) {
        ExchangeProcRun epr = exchangeProcController.getExchangeProcRun(eprUnid);

        List<Lot> lots = lotController.findLotsByAuctionUnid(epr.getEprSourceId());
        RecipientSite site = epr.getEpUnid().equals(ExchangeProcs.SYNCHRONIZE_LINKS_RAD_HOLDING.getUnid())
                ? RecipientSite.HOLDING : RecipientSite.AUCTION_HOUSE;
        List<String> errors = new ArrayList<>();

        Result<Void, String> updateResult = updateLink(lots, site, epr);
        if (updateResult.isError()) {
            errors.add(updateResult.getError());
        }
        Result<Void, String> result = updateLots(lots);
        if (result.isError()) {
            errors.add("При обновлении ссылки произошла ошибка: " + result.getError());
            epr.setEprLoadStatus(ExchangeProcStatus.ERROR_RUNNING.getCode());
        } else {
            epr.setEprLoadStatus(ExchangeProcStatus.FINISHED.getCode());
        }
        saveProcedureInfo(epr, errors);
    }

    private Result<Void, String> updateLink(List<Lot> lots, RecipientSite site, ExchangeProcRun epr) {
        String ahLotCard;
        String siteLink;
        boolean isArtLot;
        List<String> errors = new ArrayList<>();
        List<Lots> ahLot = getAhLotsList(epr, site);
        lots.removeIf(lot -> (site.equals(RecipientSite.HOLDING) && !(lot.getLotRadHoldingSite() == null || lot.getLotRadHoldingSite().isEmpty()))
                || site.equals(RecipientSite.AUCTION_HOUSE) && !(lot.getLotRadSite() == null || lot.getLotRadSite().isEmpty()));
        for (Lot l : lots) {

            Optional<Lots> ahLotOpt = ahLot.stream()
                    .filter(lot -> (lot.getLotNumber()).equals(l.getLotNumber() + ""))
                    .findFirst();

            if (!ahLotOpt.isPresent()) {
                errors.add(String.format("Лот № %s не найден", l.getLotNumber()));
                continue;
            }

            if (ahLotOpt.get().getIsActive()) {
                isArtLot = isArtLot(lotController.getObjectByLotUnid(l.getLotUnid()));
                String slug = "l-" + l.getLotUnid();

                if (site.equals(RecipientSite.HOLDING)) {
                    ahLotCard = auctionHouseLinksProperties.getHoldingLotCardPattern()
                            .replaceAll("###SLUG###", slug);
                    siteLink = auctionHouseLinksProperties.getHoldingBaseUrl() + ahLotCard;
                    l.setLotRadHoldingSite(siteLink);
                } else {
                    if (isArtLot) {
                        ahLotCard = auctionHouseLinksProperties.getAhArtLotCardPattern()
                                .replaceAll("###SLUG###", slug);
                        siteLink = auctionHouseLinksProperties.getAhArtBaseUrl() + ahLotCard;
                    } else {
                        ahLotCard = auctionHouseLinksProperties.getAhLotCardPattern()
                                .replaceAll("###SLUG###", slug);
                        siteLink = auctionHouseLinksProperties.getAhBaseUrl() + ahLotCard;
                    }
                    l.setLotRadSite(siteLink);
                }
            }
        }
        return errors.size() > 0 ? Result.error(String.join(", ", errors)) : Result.ok();
    }

    private List<Lots> getAhLotsList(ExchangeProcRun epr, RecipientSite site) {
        return lotsController.getLotsListByAuctionId(epr.getEprReceiverId(), site);
    }

    private boolean isArtLot(ObjectJPA obj) {
        return obj.getEntityUnid().equals(Entities.ART_OBJECT);
    }

    private Result<Void, String> updateLots(List<Lot> lots) {
        for (Lot lot : lots) {
            Result<Lot, String> result = lotController.updateLot(lot);
            if (result.isError()) {
                return Result.error(result.getError());
            }
        }
        return Result.ok();
    }

    private void saveProcedureInfo(ExchangeProcRun exchangeProcRun, List<String> details) {
        exchangeProcRun.setEprErrorText((String.join(", ", details)));
        exchangeProcController.saveExchangeProcRun(exchangeProcRun);
    }
}
