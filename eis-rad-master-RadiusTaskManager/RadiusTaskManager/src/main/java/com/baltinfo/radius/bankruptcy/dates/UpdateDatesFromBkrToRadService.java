package com.baltinfo.radius.bankruptcy.dates;

import com.baltinfo.radius.bankruptcy.BkrTypeStates;
import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.RunningDetailsStatus;
import com.baltinfo.radius.db.constants.TypeAuctionCode;
import com.baltinfo.radius.db.controller.AuctionController;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.controller.UpdateDatesFromBkrController;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.ReductionSchedule;
import com.baltinfo.radius.db.model.RunningDetails;
import com.baltinfo.radius.db.model.bankruptcy.VAuctionBkr;
import com.baltinfo.radius.db.model.bankruptcy.VAuctionLotAll;
import com.baltinfo.radius.db.model.bankruptcy.VReductionScheduleBkr;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class for update dates auctions, lots and reduction schedules from Bankruptcy to RAD
 *
 * @author Andrei Shymko
 * @since 16.11.2020
 */

public class UpdateDatesFromBkrToRadService {

    private static final Logger logger = LoggerFactory.getLogger(UpdateDatesFromBkrToRadService.class);

    private final AuctionController auctionController;
    private final UpdateDatesFromBkrController updateDatesFromBkrController;
    private final ExchangeProcController exchangeProcController;
    private final LotController lotController;

    private final String CANT_FIND_LOT_ON_BKR = "Не удалось найти лот на ЭТП Банкротство";
    private final String CANT_FIND_AUCTION_ON_BKR = "Не удалось найти торги на ЭТП Банкротство";
    private final String AUCTION_IN_WRONG_STATE = "Обновление сроков в текущем статусе не производится";

    public UpdateDatesFromBkrToRadService(AuctionController auctionController, UpdateDatesFromBkrController updateDatesFromBkrController,
                                          ExchangeProcController exchangeProcController, LotController lotController) {
        this.auctionController = Objects.requireNonNull(auctionController, "Can't load auction controller");
        this.updateDatesFromBkrController = Objects.requireNonNull(updateDatesFromBkrController, "Can't load service dates controller");
        this.exchangeProcController = Objects.requireNonNull(exchangeProcController, "Can't load exchangeProc controller");
        this.lotController = Objects.requireNonNull(lotController, "Can't load lot controller");
    }

    /**
     * The procedure receives data from exchange_proc_run about the processes running
     * to update the dates from Bankruptcy, then by the tender id it receives data about
     * the auctions, lots and reduction schedules in the Bankruptcy, and by the auction id it receives data about
     * the auctions, lots and reduction schedules in the RAD. If there are discrepancies, the data is updated
     * from the lot online in the RAD
     */
    public void runProcedure(List<ExchangeProcRun> list) {
        for (ExchangeProcRun epr : list) {
            VAuctionBkr vAuctionBkr = updateDatesFromBkrController.getAuctionFromBkr(epr.getEprReceiverId());
            if (vAuctionBkr == null) {
                updateEprErrorRunning(epr, CANT_FIND_AUCTION_ON_BKR);
                continue;
            }
            if (vAuctionBkr.getAuctionRegNum() == null
                    || !(vAuctionBkr.getTypeStateUnid() == BkrTypeStates.AUCTION_TYPE_STATE_AUCTION_CREATED.getUnid()
                    || vAuctionBkr.getTypeStateUnid() == BkrTypeStates.AUCTION_TYPE_STATE_DONE.getUnid()
                    || vAuctionBkr.getTypeStateUnid() == BkrTypeStates.AUCTION_RECEIVING_APPLICATIONS.getUnid())) {
                updateEprErrorRunning(epr, AUCTION_IN_WRONG_STATE);
                continue;
            }
            Auction auction = auctionController.findByAuctionUnid(epr.getEprSourceId());
            List<Lot> radLotList = lotController.findLotsByAuctionUnid(epr.getEprSourceId());
            List<VAuctionLotAll> bkrLotList = updateDatesFromBkrController.getBkrLotsByAuctionUnid(epr.getEprReceiverId());
            List<RunningDetails> runningDetailsList = makeListRunningDetails(radLotList, bkrLotList);

            for (RunningDetails rd : runningDetailsList) {
                rd.setEprUnid(epr.getEprUnid());
                exchangeProcController.updateRunningDetails(rd);
                if (rd.getRdReceiveId() != null) {
                    Lot radLot = findLotInRadByLotUnid(radLotList, rd.getRdSourceId());
                    VAuctionLotAll bkrLot = findLotInBkrByLotUnid(bkrLotList, rd.getRdReceiveId());
                    if ((radLot != null) && (bkrLot != null)) {

                        List<VReductionScheduleBkr> schedulesBkr = updateDatesFromBkrController.getBkrScheduleByLotUnid(bkrLot.getLotUnid());
                        List<ReductionSchedule> schedulesRad = lotController.getRedSchedsByLotUnid(radLot.getLotUnid());
                        updateReductionSchedulesList(schedulesBkr, schedulesRad, radLot);

                        radLot.setLotDateBPlan(bkrLot.getAuctionDatePlan());
                        radLot.setLotDateEPlan(bkrLot.getAuctionDateE());
                        Result<Lot, String> result = lotController.updateLot(radLot);
                        if (result.isSuccess()) {
                            rd.setRdResult(RunningDetailsStatus.SENT.getCode());
                            exchangeProcController.updateRunningDetails(rd);
                        } else {
                            updateRunningDetailError(rd, result.getError());
                        }
                    } else {
                        updateRunningDetailError(rd, CANT_FIND_LOT_ON_BKR);
                    }
                } else {
                    updateRunningDetailError(rd, CANT_FIND_LOT_ON_BKR);
                }
            }

            if (auction.getTypeAuctionUnid().getTypeAuctionCode().equals(TypeAuctionCode.PUBLIC_SALE.getCode())) {
                Date recepDateB = getMinRecepDateB(bkrLotList);
                Date recepDateE = getMaxRecepDateE(bkrLotList);
                auction.setAuctionRecepDateB(recepDateB);
                auction.setAuctionRecepDateE(recepDateE);
                auction.setAuctionDateB(recepDateB);
                auction.setAuctionDateE(recepDateE);
                auction.setAuctionDepDateE(vAuctionBkr.getAuctionDepDateE());
            } else {
                auction.setAuctionRecepDateB(vAuctionBkr.getAuctionRecepDateB());
                auction.setAuctionRecepDateE(vAuctionBkr.getAuctionRecepDateE());
                auction.setAuctionDateB(vAuctionBkr.getAuctionDatePlan());
                auction.setAuctionDepDateE(vAuctionBkr.getAuctionDepDateE());
            }


            Result<Auction, String> result = auctionController.updateAuction(auction);
            if (result.isSuccess()) {
                epr.setEprLoadStatus(ExchangeProcStatus.FINISHED.getCode());
                exchangeProcController.updateExchangeProcRun(epr);
            } else {
                updateEprErrorRunning(epr, result.getError());
            }

        }
    }

    /**
     * Method for update  reduction schedules
     *
     * @param schedulesBkr {@link VReductionScheduleBkr} instance of reduction schedule is stored on Bankruptcy
     * @param schedulesRad {@link ReductionSchedule} instance of reduction schedule is stored on RAD
     * @param radLot       {@link Lot} instance for save new reduction schedules
     */
    private void updateReductionSchedulesList(List<VReductionScheduleBkr> schedulesBkr, List<ReductionSchedule> schedulesRad, Lot radLot) {

        // compare Schedules by dates
        if (compareSchedules(schedulesBkr, schedulesRad)) {
            for (VReductionScheduleBkr scheduleBkr : schedulesBkr) {
                for (ReductionSchedule scheduleRad : schedulesRad) {
                    if (isNonEqualsReductionSchedule(scheduleBkr, scheduleRad)) {
                        // update Reduction Schedule
                        updateScheduleFromBkr(scheduleBkr, scheduleRad);
                        lotController.saveReductionSchedule(scheduleRad);
                    }
                }
            }
        } else {
            // set non actual exist Reduction Schedule
            schedulesRad.forEach(scheduleRad -> {
                scheduleRad.setIndActual(0);
                lotController.saveReductionSchedule(scheduleRad);
            });

            // create new Reduction Schedule
            schedulesRad.forEach(lotController::saveReductionSchedule);
            schedulesBkr.stream()
                    .map(scheduleBkr -> buildReductionSchedule(scheduleBkr, radLot))
                    .forEach(lotController::saveReductionSchedule);
        }
    }

    /**
     * Method return true if we must update reduction schedule
     *
     * @param scheduleBkr {@link VReductionScheduleBkr} instance of reduction schedule is stored on Bankruptcy
     * @param scheduleRad {@link ReductionSchedule} instance of reduction schedule is stored on RAD
     * @return true, when instances not equal
     */
    private boolean isNonEqualsReductionSchedule(VReductionScheduleBkr scheduleBkr, ReductionSchedule scheduleRad) {

        return scheduleRad.getRedSchedDateB().equals(scheduleBkr.getRedSchedDateB())
                && (!isEqualRedSchedReductiomValue(scheduleBkr, scheduleRad)
                || !isEqualRedSchedAskPrice(scheduleBkr, scheduleRad)
                || !isEqualRedSchedDepDateE(scheduleBkr, scheduleRad)
                || !isEqualRedSchedDepSum(scheduleBkr, scheduleRad)
        );
    }

    /**
     * Method for creating {@link VReductionScheduleBkr} to {@link ReductionSchedule}
     *
     * @param scheduleBkr {@link VReductionScheduleBkr} instance of reduction schedule is stored on Bankruptcy
     * @param radLot      {@link Lot} instance for save new reduction schedules
     * @return new instance {@link ReductionSchedule}
     */
    private ReductionSchedule buildReductionSchedule(VReductionScheduleBkr scheduleBkr, Lot radLot) {
        ReductionSchedule scheduleRad = new ReductionSchedule();

        scheduleRad.setLotUnid(radLot);
        scheduleRad.setIndActual(1);
        scheduleRad.setRedSchedDepSum(scheduleBkr.getRedSchedDepSum());
        scheduleRad.setRedSchedApplDateE(scheduleBkr.getRedSchedDateE());
        scheduleRad.setRedSchedAskPrice(scheduleBkr.getRedSchedAskPrice());
        scheduleRad.setRedSchedDateB(scheduleBkr.getRedSchedDateB());
        scheduleRad.setRedSchedDepDateE(scheduleBkr.getRedSchedDepDateE());
        scheduleRad.setRedSchedDepSum(scheduleBkr.getRedSchedDepSum());
        scheduleRad.setRedSchedReductionValue(scheduleBkr.getRedSchedReductionValue());

        return scheduleRad;
    }

    /**
     * Method for updating {@link VReductionScheduleBkr} to {@link ReductionSchedule}
     *
     * @param scheduleBkr {@link VReductionScheduleBkr} instance of reduction schedule is stored on Bankruptcy
     */
    private void updateScheduleFromBkr(VReductionScheduleBkr scheduleBkr, ReductionSchedule scheduleRad) {
        scheduleRad.setRedSchedApplDateE(scheduleBkr.getRedSchedDateE());
        scheduleRad.setRedSchedAskPrice(scheduleBkr.getRedSchedAskPrice());
        scheduleRad.setRedSchedDateB(scheduleBkr.getRedSchedDateB());
        scheduleRad.setRedSchedDepDateE(scheduleBkr.getRedSchedDepDateE());
        scheduleRad.setRedSchedDepSum(scheduleBkr.getRedSchedDepSum());
        scheduleRad.setRedSchedReductionValue(scheduleBkr.getRedSchedReductionValue());
    }

    private Boolean compareSchedules(List<VReductionScheduleBkr> schedulesBkr, List<ReductionSchedule> schedulesRad) {
        Map<Date, Date> schedulesBkrDates = schedulesBkr.stream()
                .collect(Collectors.toMap(VReductionScheduleBkr::getRedSchedDateB,
                        VReductionScheduleBkr::getRedSchedDateE,
                        (rs1, rs2) -> rs1));

        Map<Date, Date> schedulesRadDates = schedulesRad.stream()
                .filter(schedule -> schedule.getIndActual() == 1)
                .collect(Collectors.toMap(ReductionSchedule::getRedSchedDateB, ReductionSchedule::getRedSchedApplDateE,
                        (rs1, rs2) -> rs1));

        if (schedulesBkr.size() != schedulesBkrDates.size()) {
            return false;
        }
        return schedulesBkrDates.equals(schedulesRadDates);
    }

    private boolean isEqualRedSchedDepSum(VReductionScheduleBkr scheduleBkr, ReductionSchedule scheduleRad) {

        BigDecimal redSchedDepSumRad = scheduleRad.getRedSchedDepSum();
        BigDecimal redSchedDepSumBkr = scheduleBkr.getRedSchedDepSum();

        if (redSchedDepSumRad == null && redSchedDepSumBkr == null) {
            return true;
        } else if (redSchedDepSumRad == null || redSchedDepSumBkr == null) {
            return false;
        } else {
            return redSchedDepSumRad.equals(redSchedDepSumBkr);
        }
    }

    private boolean isEqualRedSchedDepDateE(VReductionScheduleBkr scheduleBkr, ReductionSchedule scheduleRad) {

        Date redSchedDepDateERad = scheduleRad.getRedSchedDepDateE();
        Date redSchedDepDateEBkr = scheduleBkr.getRedSchedDepDateE();

        if (redSchedDepDateERad == null && redSchedDepDateEBkr == null) {
            return true;
        } else if (redSchedDepDateERad == null || redSchedDepDateEBkr == null) {
            return false;
        } else {
            return redSchedDepDateERad.equals(redSchedDepDateEBkr);
        }
    }

    private boolean isEqualRedSchedAskPrice(VReductionScheduleBkr scheduleBkr, ReductionSchedule scheduleRad) {

        BigDecimal redSchedAskPriceRad = scheduleRad.getRedSchedAskPrice();
        BigDecimal redSchedAskPriceBkr = scheduleBkr.getRedSchedAskPrice();

        if (redSchedAskPriceRad == null && redSchedAskPriceBkr == null) {
            return true;
        } else if (redSchedAskPriceRad == null || redSchedAskPriceBkr == null) {
            return false;
        } else {
            return redSchedAskPriceRad.compareTo(redSchedAskPriceBkr) == 0;
        }
    }

    private boolean isEqualRedSchedReductiomValue(VReductionScheduleBkr scheduleBkr, ReductionSchedule scheduleRad) {

        BigDecimal redSchedReductionValueRad = scheduleRad.getRedSchedReductionValue();
        BigDecimal redSchedReductionValueBkr = scheduleBkr.getRedSchedReductionValue();

        if (redSchedReductionValueRad == null && redSchedReductionValueBkr == null) {
            return true;
        } else if (redSchedReductionValueRad == null || redSchedReductionValueBkr == null) {
            return false;
        } else {
            return redSchedReductionValueRad.compareTo(redSchedReductionValueBkr) == 0;
        }
    }

    private List<RunningDetails> makeListRunningDetails(List<Lot> radLotList, List<VAuctionLotAll> bkrLotList) {
        List<RunningDetails> runningDetailsList = new ArrayList<>();
        for (Lot lot : radLotList) {
            RunningDetails runningDetails = new RunningDetails();
            runningDetails.setRdSourceId(lot.getLotUnid());
            runningDetails.setRdResult(RunningDetailsStatus.NOT_SENT.getCode());
            for (VAuctionLotAll bkrLot : bkrLotList) {
                if (Long.parseLong(bkrLot.getLotNumber()) == lot.getLotNumber()) {
                    runningDetails.setRdReceiveId(bkrLot.getLotUnid());
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
        for (Lot lot : list) {
            if (lot.getLotUnid().equals(lotUnid)) {
                return lot;
            }
        }
        return null;
    }

    private VAuctionLotAll findLotInBkrByLotUnid(List<VAuctionLotAll> list, Long lotUnid) {
        for (VAuctionLotAll lot : list) {
            if (lot.getLotUnid() == lotUnid) {
                return lot;
            }
        }
        return null;
    }

    private Date getMinRecepDateB(List<VAuctionLotAll> lotList) {
        return lotList.stream()
                .map(VAuctionLotAll::getLotRecepDateB)
                .min(Date::compareTo)
                .orElse(null);
    }

    private Date getMaxRecepDateE(List<VAuctionLotAll> lotList) {
        return lotList.stream()
                .map(VAuctionLotAll::getLotRecepDateE)
                .max(Date::compareTo)
                .orElse(null);
    }
}
