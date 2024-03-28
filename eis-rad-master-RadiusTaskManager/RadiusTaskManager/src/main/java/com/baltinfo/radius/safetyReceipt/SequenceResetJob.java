package com.baltinfo.radius.safetyReceipt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.util.Objects;

public class SequenceResetJob {

    private static final Logger logger = LoggerFactory.getLogger(SequenceResetJob.class);
    private final SequenceResetService sequenceResetService;

    public SequenceResetJob(SequenceResetService sequenceResetService) {
        this.sequenceResetService = Objects.requireNonNull(sequenceResetService, "Can't load sequenceResetService");
    }

    @Scheduled(cron = "${job.cron.sequence.reset}")
    public void runJob() {
        logger.info("start run job method at {}", Instant.now());
        try {
            sequenceResetService.runProcedure();
        } catch (Exception ex) {
            logger.error("Error when run SequenceResetJob", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }
}
