package com.baltinfo.radius.bankruptcy.codes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.Objects;

/**
 * @author Kulikov Semyon
 * @since 16.03.2020
 */

public class ExportCodesFromBkrToRadJob {

    private static final Logger logger = LoggerFactory.getLogger(ExportCodesFromBkrToRadJob.class);

    private final ExportCodesFromBkrToRadService exportCodesFromBkrToRadService;

    public ExportCodesFromBkrToRadJob(ExportCodesFromBkrToRadService exportCodesFromBkrToRadService) {
        this.exportCodesFromBkrToRadService = Objects.requireNonNull(exportCodesFromBkrToRadService, "Can't load exportCodesFromBkrToRad ftp!");
    }

    @Scheduled(cron = "${job.cron.export.codes.from.bankruptcy.to.rad}")
    public void runJob() {
        Long startDate = new Date().getTime();
        logger.info("start ExportCodesFromBkrToRadJob run job method at " + startDate);
        try {
            exportCodesFromBkrToRadService.runProcedure();
        } catch (Exception ex) {
            logger.error("Error when run ExportCodesFromBkrToRadJob", ex);
        }
        logger.info("end ExportCodesFromBkrToRadJob run job method at " + new Date().getTime() + " duration = " + (new Date().getTime() - startDate));
    }
}
