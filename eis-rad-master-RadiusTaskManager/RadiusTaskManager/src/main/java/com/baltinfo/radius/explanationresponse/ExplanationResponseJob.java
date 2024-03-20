package com.baltinfo.radius.explanationresponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;

/**
 * @author Suvorina Aleksandra
 * @since 23.01.2022
 */
public class ExplanationResponseJob {
    private static final Logger logger = LoggerFactory.getLogger(ExplanationResponseJob.class);

    private final ExplanationResponseService explanationResponseService;

    public ExplanationResponseJob(ExplanationResponseService explanationResponseService) {
        this.explanationResponseService = explanationResponseService;
    }

    // temporarily commented
    //@Scheduled(cron = "${job.cron.explanation.requests}")
    public void run() {
        logger.info("start run job method at {}", Instant.now());
        try {
            explanationResponseService.processNewExplanationResponses();
        } catch (Exception ex) {
            logger.error("Error when run ExplanationResponseJob", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }
}
