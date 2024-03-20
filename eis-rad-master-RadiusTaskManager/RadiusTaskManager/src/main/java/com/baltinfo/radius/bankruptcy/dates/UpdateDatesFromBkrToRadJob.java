package com.baltinfo.radius.bankruptcy.dates;

import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The job for update dates auctions, lots and reduction schedules from Bankruptcy to RAD
 *
 * @author Andrei Shymko
 * @since 16.11.2020
 */

public class UpdateDatesFromBkrToRadJob {

    private static final Logger logger = LoggerFactory.getLogger(UpdateDatesFromBkrToRadJob.class);

    private final UpdateDatesFromBkrToRadService updateDatesFromBkrToRadService;
    private final ExchangeProcController exchangeProcController;

    public UpdateDatesFromBkrToRadJob(UpdateDatesFromBkrToRadService exportCodesFromBkrToRadService, ExchangeProcController exchangeProcController) {
        this.updateDatesFromBkrToRadService = Objects.requireNonNull(exportCodesFromBkrToRadService, "Can't load updateDatesFromBkrToRad!");
        this.exchangeProcController = Objects.requireNonNull(exchangeProcController, "Can't load exchangeProc controller");
    }

    @Scheduled(cron = "${job.cron.update.dates.from.bankruptcy.to.rad}")
    public void runJob() {
        long startDate = new Date().getTime();
        logger.info("start UpdateDatesFromBkrToRadJob run job method at " + startDate);
        try {
            List<ExchangeProcRun> list = exchangeProcController.getRunningExchangeProcRun(ExchangeProcs.UPDATE_DATES_FROM_BANKRUPTCY)
                    .orElse(new ArrayList<>());
            updateDatesFromBkrToRadService.runProcedure(list);
        } catch (Exception ex) {
            logger.error("Error when run UpdateDatesFromBkrToRadJob", ex);
        }
        logger.info("end UpdateDatesFromBkrToRadJob run job method at " + new Date().getTime() + " duration = " + (new Date().getTime() - startDate));
    }
}