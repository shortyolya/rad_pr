package com.baltinfo.radius.lotonline.dates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.Objects;

/**
 * The job for for update dates auctions and lots from Lot-Online to RAD
 *
 * @author Andrei Shymko
 * @since 16.11.2020
 */

public class UpdateDatesFromLotOnlineToRadJob {

    private static final Logger logger = LoggerFactory.getLogger(UpdateDatesFromLotOnlineToRadJob.class);

    private final UpdateDatesFromLotOnlineToRadService updateDatesFromLotOnlineToRadService;

    public UpdateDatesFromLotOnlineToRadJob(UpdateDatesFromLotOnlineToRadService updateDatesFromLotOnlineToRadService) {
        this.updateDatesFromLotOnlineToRadService = Objects.requireNonNull(updateDatesFromLotOnlineToRadService, "Can't load updateDatesFromLotOnlineToRad!");
    }

    @Scheduled(cron = "${job.cron.update.dates.from.lot.online.to.rad}")
    public void runJob() {
        long startDate = new Date().getTime();
        logger.info("start UpdateDatesFromLotOnlineToRadJob run job method at " + startDate);
        try {
            updateDatesFromLotOnlineToRadService.runProcedure();
        } catch (Exception ex) {
            logger.error("Error when run UpdateDatesFromLotOnlineToRadJob", ex);
        }
        logger.info("end UpdateDatesFromLotOnlineToRadJob run job method at " + new Date().getTime() + " duration = " + (new Date().getTime() - startDate));
    }
}