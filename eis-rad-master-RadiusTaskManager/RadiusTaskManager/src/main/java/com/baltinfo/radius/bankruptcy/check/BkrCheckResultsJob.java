package com.baltinfo.radius.bankruptcy.check;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;

/**
 * @author Suvorina Aleksandra
 * @since 23.07.2019
 */
public class BkrCheckResultsJob {

    private static final Logger logger = LoggerFactory.getLogger(BkrCheckResultsJob.class);

    private final BkrCheckResultsService bkrCheckResultsService;

    public BkrCheckResultsJob(BkrCheckResultsService bkrCheckResultsService) {
        this.bkrCheckResultsService = bkrCheckResultsService;
    }

    @Scheduled(cron = "${job.cron.export.bankruptcy.results.check}")
    public void run() {
        logger.info("start run job method at {}", Instant.now());
        try {
            bkrCheckResultsService.checkAndRunProcedure();
        } catch (Exception ex) {
            logger.error("Error when run BkrCheckResultsJob", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }

}
