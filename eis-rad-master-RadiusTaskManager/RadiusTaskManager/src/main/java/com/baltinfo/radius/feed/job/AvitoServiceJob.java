package com.baltinfo.radius.feed.job;

import com.baltinfo.radius.db.constants.MarketingEvent;
import com.baltinfo.radius.db.constants.SubjectConstant;
import com.baltinfo.radius.feed.services.FeedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;

/**
 * @author Suvorina Aleksandra
 * @since 08.09.2020
 */
public class AvitoServiceJob {

    private static final Logger logger = LoggerFactory.getLogger(AvitoServiceJob.class);

    private final FeedService feedService;

    public AvitoServiceJob(FeedService feedService) {
        this.feedService = feedService;
    }

    @Scheduled(cron = "${job.cron.feed.to.avito}")
    public void run() {
        logger.info("start run job method at {}", Instant.now());
        try {
            feedService.formFeed(MarketingEvent.AVITO.getUnid(), SubjectConstant.RAD.getId());
            feedService.formFeed(MarketingEvent.AVITO.getUnid(), SubjectConstant.RAD_HOLDING.getId());
        } catch (Exception ex) {
            logger.error("Error when run AvitoServiceJob", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }
}
