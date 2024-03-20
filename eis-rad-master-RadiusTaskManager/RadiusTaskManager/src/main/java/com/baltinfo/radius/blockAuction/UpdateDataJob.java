package com.baltinfo.radius.blockAuction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class UpdateDataJob {

    private static final Logger logger = LoggerFactory.getLogger(UpdateDataJob.class);
    private final UpdateDataService updateDataService;

    public UpdateDataJob(UpdateDataService updateDataService) {
        this.updateDataService = updateDataService;
    }

    @Scheduled(cron = "${job.cron.update.assets}")
    public void runJob() {
        Long startDate = new Date().getTime();
        logger.info("start UpdateDataJob run job method at " + startDate);
        try {
            updateDataService.runProcedure();
        } catch (Exception ex) {
            logger.error("Error when run UpdateDataJob", ex);
        }
        logger.info("end UpdateDataJob run job method at " + new Date().getTime() + " duration = " + (new Date().getTime() - startDate));
    }
}
