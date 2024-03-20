package com.baltinfo.radius.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;

/**
 * @author Suvorina Aleksandra
 * @since 22.11.2018
 */
public class AsvNotificationJob {

    private static final Logger logger = LoggerFactory.getLogger(AsvNotificationJob.class);

    private final NotificationService notificationService;

    public AsvNotificationJob(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Scheduled(cron = "${job.cron.notification.asv}")
    public void run() {
        logger.info("start run job method at {}", Instant.now());
        try {
            notificationService.sendNotifications();
        } catch (Exception ex) {
            logger.error("Error when run AsvNotificationJob", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }
}
