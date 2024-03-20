package com.baltinfo.radius.notification.paydoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;

public class PayDocNotificationJob {

    private static final Logger logger = LoggerFactory.getLogger(PayDocNotificationJob.class);
    private final PayDocNotificationService payDocNotificationService;

    public PayDocNotificationJob(PayDocNotificationService payDocNotificationService) {
        this.payDocNotificationService = payDocNotificationService;
    }

    @Scheduled(cron = "${job.cron.send.pay.doc.notification}")
    public void run() {
        logger.info("start run job method at {}", Instant.now());
        try {
            payDocNotificationService.runProcedure();
        } catch (Exception ex) {
            logger.error("Error when run PayDocNotificationJob", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }
}
