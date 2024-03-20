package com.baltinfo.radius.loadauction.job;

import com.baltinfo.radius.loadauction.service.SendResultsToFtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class SendResultsToFtpJob {
    private static final Logger logger = LoggerFactory.getLogger(SendResultsToFtpJob.class);

    private final SendResultsToFtpService sendResultsToFtpService;

    public SendResultsToFtpJob(SendResultsToFtpService sendResultsToFtpService) {
        this.sendResultsToFtpService = sendResultsToFtpService;
    }

    @Scheduled(cron = "${job.cron.send.results.to.ftp}")
    public void runJob() {
        Long startDate = new Date().getTime();
        logger.info("start SendResultsToFtpJob run job method at " + startDate);
        try {
            sendResultsToFtpService.sendResult();
        } catch (Exception e) {
            logger.error("Error in SendResultsToFtpJob", e);
        }
        logger.info("end SendResultsToFtpJob run job method at " + new Date().getTime() + " duration = " + (new Date().getTime() - startDate));
    }

}
