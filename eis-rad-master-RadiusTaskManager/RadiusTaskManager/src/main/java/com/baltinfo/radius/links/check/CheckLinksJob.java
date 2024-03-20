package com.baltinfo.radius.links.check;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.util.Objects;

public class CheckLinksJob {

    private static final Logger logger = LoggerFactory.getLogger(CheckLinksJob.class);
    private final CheckLinksService checkLinksService;

    public CheckLinksJob(CheckLinksService checkLinksService) {
        this.checkLinksService = Objects.requireNonNull(checkLinksService, "Can't load checkListService");
    }

    @Scheduled(cron = "${job.cron.check.links}")
    public void runJob() {
        logger.info("start run job method at {}", Instant.now());
        try {
            checkLinksService.runProcedure();
        } catch (Exception ex) {
            logger.error("Error when run CheckLinksJob", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }
}
