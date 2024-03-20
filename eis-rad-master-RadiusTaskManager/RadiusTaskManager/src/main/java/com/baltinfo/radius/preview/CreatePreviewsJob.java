package com.baltinfo.radius.preview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;

public class CreatePreviewsJob {
    private static final Logger logger = LoggerFactory.getLogger(CreatePreviewsJob.class);

    private CreatePreviewsService createPreviewsService;

    public CreatePreviewsJob(CreatePreviewsService createPreviewsService) {
        this.createPreviewsService = createPreviewsService;
    }

    @Scheduled(fixedDelay = Long.MAX_VALUE)
    public void run() {
        logger.info("start run job method at {}", Instant.now());
        try {
            createPreviewsService.createPreviews();
        } catch (Exception ex) {
            logger.error("Error when run ExplanationResponseJob", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }
}
