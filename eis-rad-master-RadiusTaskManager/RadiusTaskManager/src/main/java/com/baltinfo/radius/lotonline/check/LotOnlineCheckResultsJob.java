package com.baltinfo.radius.lotonline.check;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;

/**
 * @author Suvorina Aleksandra
 * @since 23.07.2019
 */
public class LotOnlineCheckResultsJob {

    private static final Logger logger = LoggerFactory.getLogger(LotOnlineCheckResultsJob.class);

    private final LotOnlineCheckResultsService lotOnlineCheckResultsService;

    public LotOnlineCheckResultsJob(LotOnlineCheckResultsService lotOnlineCheckResultsService) {
        this.lotOnlineCheckResultsService = lotOnlineCheckResultsService;
    }

    @Scheduled(cron = "${job.cron.export.lotonline.results.check}")
    public void run() {
        logger.info("start run job method at {}", Instant.now());
        try {
            lotOnlineCheckResultsService.checkAndRunProcedure();
        } catch (Exception ex) {
            logger.error("Error when run LotOnlineCheckResultsJob", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }

}
