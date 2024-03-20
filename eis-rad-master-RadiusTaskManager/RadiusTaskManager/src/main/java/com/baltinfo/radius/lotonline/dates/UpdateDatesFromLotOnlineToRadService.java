package com.baltinfo.radius.lotonline.dates;

import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.constants.RunningDetailsStatus;
import com.baltinfo.radius.db.controller.AuctionController;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.controller.UpdateDatesFromLOController;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.RunningDetails;
import com.baltinfo.radius.db.model.lotonline.LotInfo;
import com.baltinfo.radius.db.model.lotonline.Tender;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

/**
 * Class for update dates auctions and lots from Lot-Online to RAD
 *
 * @author Andrei Shymko
 * @since 16.11.2020
 */

public class UpdateDatesFromLotOnlineToRadService {

    private static final Logger logger = LoggerFactory.getLogger(UpdateDatesFromLotOnlineToRadService.class);

    private static final String DATEFORMAT = "dd.MM.yyyy HH:mm:ss.SSS";
    private static final SimpleDateFormat sdfUTC = new SimpleDateFormat(DATEFORMAT);

    static {
        //Форматируем дату со сдвигом в UTC
        sdfUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    private static final SimpleDateFormat sdfCur = new SimpleDateFormat(DATEFORMAT);

    private final AuctionController auctionController;
    private final UpdateDatesFromLOController updateDatesFromLOController;
    private final ExchangeProcController exchangeProcController;
    private final LotController lotController;

    private final String CANT_FIND_LOT_ON_LO = "Не удалось найти лот на ЭТП lot-online";

    public UpdateDatesFromLotOnlineToRadService(AuctionController auctionController, UpdateDatesFromLOController updateDatesFromLOController,
                                                ExchangeProcController exchangeProcController, LotController lotController) {
        this.auctionController = Objects.requireNonNull(auctionController, "Can't load auction controller");
        this.updateDatesFromLOController = Objects.requireNonNull(updateDatesFromLOController, "Can't load service dates controller");
        this.exchangeProcController = Objects.requireNonNull(exchangeProcController, "Can't load exchangeProc controller");
        this.lotController = Objects.requireNonNull(lotController, "Can't load lot controller");
    }

    /**
     * The procedure receives data from exchange_proc_run about the processes running
     * to update the dates from the lot online, then by the tender id it receives data about
     * the tenders and lots in the lot online, and by the auction id it receives data about
     * the auctions and lots in the RAD. If there are discrepancies, the data is updated
     * from the lot online in the RAD
     */
    public void runProcedure() throws ParseException {
        List<ExchangeProcRun> list = exchangeProcController.getRunningExchangeProcRun(ExchangeProcs.UPDATE_DATES_FROM_LOT_ONLINE).orElse(new ArrayList<>());
        for (ExchangeProcRun epr : list) {
            Tender tender = updateDatesFromLOController.getTenderById(epr.getEprReceiverId());
            if (tender != null) {
                if (tender.getTenderCode() != null && tender.getIsPublished()) {
                    Auction auction = auctionController.findByAuctionUnid(epr.getEprSourceId());
                    List<Lot> radLotList = lotController.findLotsByAuctionUnid(epr.getEprSourceId());
                    List<LotInfo> loLotList = updateDatesFromLOController.getLOLotsById(epr.getEprReceiverId());
                    List<RunningDetails> runningDetailsList = makeListRunningDetails(radLotList, loLotList);

                    for (RunningDetails rd : runningDetailsList) {
                        rd.setEprUnid(epr.getEprUnid());
                        exchangeProcController.updateRunningDetails(rd);
                        if (rd.getRdReceiveId() != null) {
                            Lot radLot = findLotInRadByLotUnid(radLotList, rd.getRdSourceId());
                            LotInfo loLot = findLotInLoByLotUnid(loLotList, rd.getRdReceiveId());
                            if ((radLot != null) && (loLot != null)) {

                                radLot.setLotDateBPlan(convertFromUTC(loLot.getBiddingStart()));
                                radLot.setLotDateEPlan(convertFromUTC(loLot.getBiddingStop()));
                                Result<Lot, String> result = lotController.updateLot(radLot);
                                if (result.isSuccess()) {
                                    rd.setRdResult(RunningDetailsStatus.SENT.getCode());
                                    exchangeProcController.updateRunningDetails(rd);
                                } else {
                                    updateRunningDetailError(rd, result.getError());
                                }
                            } else {
                                updateRunningDetailError(rd, CANT_FIND_LOT_ON_LO);
                            }
                        } else {
                            updateRunningDetailError(rd, CANT_FIND_LOT_ON_LO);
                        }
                    }

                    auction.setAuctionRecepDateB(convertFromUTC(tender.getAppSubmitStart()));
                    auction.setAuctionRecepDateE(convertFromUTC(tender.getAppSubmitStop()));
                    auction.setAuctionDepDateE(convertFromUTC(tender.getProcessAppDate()));

                    Result<Auction, String> result = auctionController.updateAuction(auction);
                    if (result.isSuccess()) {
                        epr.setEprLoadStatus(ExchangeProcStatus.FINISHED.getCode());
                        exchangeProcController.updateExchangeProcRun(epr);
                    } else {
                        updateEprErrorRunning(epr, result.getError());
                    }
                }
            }
        }
    }

    private List<RunningDetails> makeListRunningDetails(List<Lot> radLotList, List<LotInfo> loLotList) {
        List<RunningDetails> runningDetailsList = new ArrayList<>();
        for (Lot lot : radLotList) {
            RunningDetails runningDetails = new RunningDetails();
            runningDetails.setRdSourceId(lot.getLotUnid());
            runningDetails.setRdResult(RunningDetailsStatus.NOT_SENT.getCode());
            for (LotInfo loLot : loLotList) {
                if (Long.valueOf(loLot.getLotNumber()).equals(lot.getLotNumber())) {
                    runningDetails.setRdReceiveId(loLot.getId());
                }
            }
            runningDetailsList.add(runningDetails);
        }
        return runningDetailsList;
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

    private Lot findLotInRadByLotUnid(List<Lot> list, Long lotUnid) {
        return list.stream()
                .filter(lot -> lot.getLotUnid().equals(lotUnid))
                .findFirst()
                .orElse(null);
    }

    private LotInfo findLotInLoByLotUnid(List<LotInfo> list, Long lotUnid) {
        return list.stream()
                .filter(lot -> lot.getId().equals(lotUnid))
                .findFirst()
                .orElse(null);
    }

    private Date convertFromUTC(Date dateIn) throws ParseException {
        if (dateIn == null) {
            return null;
        }
        return sdfUTC.parse(sdfCur.format(dateIn));
    }
}
