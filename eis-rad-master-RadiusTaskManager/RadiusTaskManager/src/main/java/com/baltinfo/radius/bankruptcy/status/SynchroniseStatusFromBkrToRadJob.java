package com.baltinfo.radius.bankruptcy.status;

import com.baltinfo.radius.bankruptcy.dates.UpdateDatesFromBkrToRadService;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import org.joda.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The job for type state auctions, lots and reduction schedules from Bankruptcy to RAD
 *
 * @author AAA
 * @since 25.11.2021
 */

public class SynchroniseStatusFromBkrToRadJob {

    private static final Logger logger = LoggerFactory.getLogger(SynchroniseStatusFromBkrToRadJob.class);

    private final CheckSuspendedAuctionFromBkrToRadService checkSuspendedAuctionFromBkrToRadService;
    private final CheckResumedAuctionFromBkrToRadService checkResumedAuctionFromBkrToRadService;


    public SynchroniseStatusFromBkrToRadJob(CheckSuspendedAuctionFromBkrToRadService checkSuspendedAuctionFromBkrToRadService,
                                            CheckResumedAuctionFromBkrToRadService checkResumedAuctionFromBkrToRadService) {
        this.checkSuspendedAuctionFromBkrToRadService = Objects.requireNonNull(checkSuspendedAuctionFromBkrToRadService, "Can't load checkSuspendedAuctionFromBkrToRadService!");
        this.checkResumedAuctionFromBkrToRadService = Objects.requireNonNull(checkResumedAuctionFromBkrToRadService, "Can't load checkResumedAuctionFromBkrToRadService!");

    }

    @Scheduled(cron = "${job.cron.update.status.from.bankruptcy.to.rad}")
    public void runJob() {
        Integer startDate = new Date().getSeconds();
        logger.info("start SynchroniseStatusFromBkrToRadJob run job method at " + Instant.now());
        try {
            checkSuspendedAuctionFromBkrToRadService.runProcedure();
            checkResumedAuctionFromBkrToRadService.runProcedure();
        } catch (Exception ex) {
            logger.error("Error when run SynchroniseStatusFromBkrToRadJob", ex);
        }
        logger.info("end SynchroniseStatusFromBkrToRadJob run job method at " + Instant.now() + " duration = " + (new Date().getSeconds() - startDate) + "c");
    }
}
