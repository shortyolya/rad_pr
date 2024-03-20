package com.baltinfo.radius.vitrina;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @author Suvorina Aleksandra
 * @since 18.10.2021
 */
public class UpdateVitrinaUrlJob {
    private static final Logger logger = LoggerFactory.getLogger(UpdateVitrinaUrlJob.class);

    private final UpdateVitrinaUrlService updateVitrinaUrlService;

    public UpdateVitrinaUrlJob(UpdateVitrinaUrlService updateVitrinaUrlService) {
        this.updateVitrinaUrlService = updateVitrinaUrlService;
    }

    @Scheduled(cron = "${job.cron.update.vitrina.url}")
    public void runJob() {
        Long startDate = new Date().getTime();
        logger.info("start UpdateVitrinaUrlJob run job method at " + startDate);
        try {
            updateVitrinaUrlService.runProcedure();
        } catch (Exception ex) {
            logger.error("Error when run UpdateVitrinaUrlJob", ex);
        }
        logger.info("end UpdateVitrinaUrlJob run job method at " + new Date().getTime() + " duration = " + (new Date().getTime() - startDate));
    }

}
