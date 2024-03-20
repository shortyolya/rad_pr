package com.baltinfo.radius.avito.job;

import com.baltinfo.radius.avito.service.AvitoDevelopmentClassificatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class AvitoDevelopmentClassifierJob {

    private static final Logger logger = LoggerFactory.getLogger(AvitoDevelopmentClassifierJob.class);

    private final AvitoDevelopmentClassificatorService avitoDevelopmentClassificatorService;

    public AvitoDevelopmentClassifierJob(AvitoDevelopmentClassificatorService avitoDevelopmentClassificatorService) {
        this.avitoDevelopmentClassificatorService = avitoDevelopmentClassificatorService;
    }

    @Scheduled(cron = "${job.cron.load.avito.development.classifier}")
    public void runJob() {
        Long startDate = new Date().getTime();
        logger.info("Start run Load Avito Development Classifier Job at {}", startDate);
        try {
            avitoDevelopmentClassificatorService.parseDevelopmentCatalog();
        } catch (Exception ex) {
            logger.error("Error when run Load Avito Development Classifier Job", ex);
        } finally {
            logger.info("End run Load Avito Development Classifier Job at {}; duration = {}", new Date().getTime(), (new Date().getTime() - startDate));
        }

    }
}
