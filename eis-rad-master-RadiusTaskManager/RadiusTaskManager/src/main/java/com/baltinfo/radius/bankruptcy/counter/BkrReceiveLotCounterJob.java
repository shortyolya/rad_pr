package com.baltinfo.radius.bankruptcy.counter;

import com.baltinfo.radius.bankruptcy.results.BkrReceiveResultsJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;

/**
 * @author Suvorina Aleksandra
 * @since 26.10.2020
 */
public class BkrReceiveLotCounterJob {
    private static final Logger logger = LoggerFactory.getLogger(BkrReceiveResultsJob.class);

    private final BkrReceiveLotCounterService bkrReceiveLotCounterService;

    public BkrReceiveLotCounterJob(BkrReceiveLotCounterService bkrReceiveLotCounterService) {
        this.bkrReceiveLotCounterService = bkrReceiveLotCounterService;
    }

    @Scheduled(cron = "${job.cron.bankruptcy.lot.counter}")
    public void run() {
        logger.info("start run job method at {}", Instant.now());
        try {
            bkrReceiveLotCounterService.runProcedure();
        } catch (Exception ex) {
            logger.error("Error when run BkrReceiveLotCounterJob", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }
}
