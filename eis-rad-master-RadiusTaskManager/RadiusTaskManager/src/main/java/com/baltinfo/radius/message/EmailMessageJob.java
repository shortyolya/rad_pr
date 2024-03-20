package com.baltinfo.radius.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;

/**
 * <p>
 *     Постоянно повторяющийся джоб, рассылающий определённые сообщения на почту
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 02.04.2020
 */
public class EmailMessageJob {

    private static final Logger logger = LoggerFactory.getLogger(EmailMessageJob.class);

    private final EmailMessageService emailMessageService;

    public EmailMessageJob(EmailMessageService emailMessageService) {
        this.emailMessageService = emailMessageService;
    }

    @Scheduled(cron = "${job.cron.email.message}")
    public void run() {
        logger.info("start run job method at {}", Instant.now());
        try {
            emailMessageService.sendEmailMessage();
        } catch (Exception ex) {
            logger.error("Error when run EmailMessageJob", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }
}
