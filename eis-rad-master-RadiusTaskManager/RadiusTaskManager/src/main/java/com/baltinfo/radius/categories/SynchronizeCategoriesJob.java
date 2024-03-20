package com.baltinfo.radius.categories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.Objects;

/**
 * @author Suvorina Aleksandra
 * @since 09.02.2021
 */
public class SynchronizeCategoriesJob {

    private static final Logger logger = LoggerFactory.getLogger(SynchronizeCategoriesJob.class);
    private final SynchronizeCategoriesService synchronizeCategoriesService;

    public SynchronizeCategoriesJob(SynchronizeCategoriesService synchronizeCategoriesService) {
        this.synchronizeCategoriesService = Objects.requireNonNull(synchronizeCategoriesService, "Can't load synchronizeCategoriesService");
    }

    @Scheduled(cron = "${job.cron.synchronize.categories}")
    public void runJob() {
        Long startDate = new Date().getTime();
        logger.info("start SynchronizeCategoriesJob run job method at " + startDate);
        try {
            synchronizeCategoriesService.runProcedure();
        } catch (Exception ex) {
            logger.error("Error when run SynchronizeCategoriesJob", ex);
        }
        logger.info("end SynchronizeCategoriesJob run job method at " + new Date().getTime() + " duration = " + (new Date().getTime() - startDate));
    }
}
