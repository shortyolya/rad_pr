package com.baltinfo.radius.lotonline.codes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.Objects;

/**
 * @author Kulikov Semyon
 * @since 07.08.2020
 */

public class ExportCodesFromLOToRadJob {

    private static final Logger logger = LoggerFactory.getLogger(ExportCodesFromLOToRadJob.class);

    private final ExportCodesFromLOToRadService exportCodesFromLOToRadService;

    public ExportCodesFromLOToRadJob(ExportCodesFromLOToRadService exportCodesFromLOToRadService) {
        this.exportCodesFromLOToRadService = Objects.requireNonNull(exportCodesFromLOToRadService, "Can't load exportCodesFromLOToRad ftp!");
    }

    @Scheduled(cron = "${job.cron.export.codes.from.lo.to.rad}")
    public void runJob() {
        Long startDate = new Date().getTime();
        logger.info("start ExportCodesFromLOToRadJob run job method at " + startDate);
        try {
            exportCodesFromLOToRadService.runProcedure();
        } catch (Exception ex) {
            logger.error("Error when run ExportCodesFromLOToRadJob", ex);
        }
        logger.info("end ExportCodesFromLOToRadJob run job method at " + new Date().getTime() + " duration = " + (new Date().getTime() - startDate));
    }
}
