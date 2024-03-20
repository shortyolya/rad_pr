package com.baltinfo.radius.feed.job;

import com.baltinfo.radius.db.constants.MarketingEvent;
import com.baltinfo.radius.db.constants.SubjectConstant;
import com.baltinfo.radius.feed.services.FeedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;

public class JCatServiceJob {

    private static final Logger logger = LoggerFactory.getLogger(JCatServiceJob.class);

    private final FeedService feedService;

    public JCatServiceJob(FeedService feedService) {
        this.feedService = feedService;
    }

    @Scheduled(cron = "${job.cron.feed.to.jcat}")
    public void run() {
        logger.info("start run job method at {}", Instant.now());
        try {
            feedService.formFeed(MarketingEvent.JCAT.getUnid(), SubjectConstant.RAD.getId());
            feedService.formFeed(MarketingEvent.JCAT.getUnid(), SubjectConstant.RAD_HOLDING.getId());
        } catch (Exception ex) {
            logger.error("Error when run JCatServiceJob", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }
}
