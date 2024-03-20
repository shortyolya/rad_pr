package com.baltinfo.radius.notification;

import com.baltinfo.radius.notification.impl.LotPublishedNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;

public class LotPublishedNotificationJob {
    private static final Logger logger = LoggerFactory.getLogger(LotPublishedNotificationJob.class);

    private final LotPublishedNotificationService notificationService;

    public LotPublishedNotificationJob(LotPublishedNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Scheduled(cron = "${job.cron.notification.lot}")
    public void run() {
        logger.info("start run job method at {}", Instant.now());
        try {
            notificationService.sendNotifications();
        } catch (Exception ex) {
            logger.error("Error when run LotPublishedNotificationJob", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }
}
