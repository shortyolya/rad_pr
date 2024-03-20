package com.baltinfo.radius.feed.job;

import com.baltinfo.radius.db.constants.MarketingEvent;
import com.baltinfo.radius.db.constants.SubjectConstant;
import com.baltinfo.radius.feed.services.FeedByCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;

/**
 * @author Kulikov Semyon
 * @since 06.02.2020
 */

public class CianCommercialServiceJob {
    private static final Logger logger = LoggerFactory.getLogger(CianCommercialServiceJob.class);

    private final FeedByCategoryService feedService;

    public CianCommercialServiceJob(FeedByCategoryService feedService) {
        this.feedService = feedService;
    }

    @Scheduled(cron = "${job.cron.export.commercial.estate.to.cian}")
    public void run() {
        logger.info("start run job method at {}", Instant.now());
        try {
            feedService.formFeed(MarketingEvent.CIAN.getUnid(), SubjectConstant.RAD.getId());
            feedService.formFeed(MarketingEvent.CIAN.getUnid(), SubjectConstant.RAD_HOLDING.getId());
        } catch (Exception ex) {
            logger.error("Error when run CianCommercialServiceJob", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }
}
