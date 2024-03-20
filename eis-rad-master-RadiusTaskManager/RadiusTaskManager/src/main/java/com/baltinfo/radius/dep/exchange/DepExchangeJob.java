package com.baltinfo.radius.dep.exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;

public class DepExchangeJob {
    private static final Logger logger = LoggerFactory.getLogger(DepExchangeJob.class);
    private final DepExchangeService depExchangeService;

    public DepExchangeJob(DepExchangeService depExchangeService) {
        this.depExchangeService = depExchangeService;
    }


    @Scheduled(cron = "${job.cron.dep.exchange}")
    public void getBkrAccountInfo() {
        logger.info("start run job method at {}", Instant.now());
        try {
            depExchangeService.getBkrAccountInfo();
        } catch (Exception ex) {
            logger.error("Error when run getBkrAccountInfo", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }

    @Scheduled(cron = "${job.cron.dep.exchange}")
    public void getLoAccountInfo() {
        logger.info("start run job method at {}", Instant.now());
        try {
            depExchangeService.getLoAccountInfo();
        } catch (Exception ex) {
            logger.error("Error when run getLoAccountInfo", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }

    @Scheduled(cron = "${job.cron.dep.exchange}")
    public void getBkrReturnInfo() {
        logger.info("start run job method at {}", Instant.now());
        try {
            depExchangeService.getBkrReturnInfo();
        } catch (Exception ex) {
            logger.error("Error when run getBkrReturnInfo", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }

    @Scheduled(cron = "${job.cron.dep.exchange}")
    public void sendBkrPaymentInfo() {
        logger.info("start run job method at {}", Instant.now());
        try {
            depExchangeService.sendBkrPaymentInfo();
        } catch (Exception ex) {
            logger.error("Error when run sendBkrPaymentInfo", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }

    @Scheduled(cron = "${job.cron.dep.exchange}")
    public void sendBkrReturnInfo() {
        logger.info("start run job method at {}", Instant.now());
        try {
            depExchangeService.sendBkrReturnInfo();
        } catch (Exception ex) {
            logger.error("Error when run sendBkrReturnInfo", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }

    @Scheduled(cron = "${job.cron.dep.exchange}")
    public void getBkrReturnToOrgInfo() {
        logger.info("start run job method at {}", Instant.now());
        try {
            depExchangeService.getBkrReturnToOrgInfo();
        } catch (Exception ex) {
            logger.error("Error when run getBkrReturnToOrgInfo", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }

    @Scheduled(cron = "${job.cron.dep.exchange}")
    public void getBkrTransferToOperatorInfo() {
        logger.info("start run job method at {}", Instant.now());
        try {
            depExchangeService.getBkrTransferToOperatorInfo();
        } catch (Exception ex) {
            logger.error("Error when run getBkrTransferToOperatorInfo", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }

    @Scheduled(cron = "${job.cron.dep.exchange}")
    public void getLoReturnInfo() {
        logger.info("start run job method at {}", Instant.now());
        try {
            depExchangeService.getLoReturnInfo();
        } catch (Exception ex) {
            logger.error("Error when run getLoReturnInfo", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }

    @Scheduled(cron = "${job.cron.dep.exchange}")
    public void sendLoPaymentInfo() {
        logger.info("start run job method at {}", Instant.now());
        try {
            depExchangeService.sendLoPaymentInfo();
        } catch (Exception ex) {
            logger.error("Error when run sendLoPaymentInfo", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }

    @Scheduled(cron = "${job.cron.dep.exchange}")
    public void getLoReturnToOrgInfo() {
        logger.info("start run job method at {}", Instant.now());
        try {
            depExchangeService.getLoReturnToOrgInfo();
        } catch (Exception ex) {
            logger.error("Error when run getLoReturnToOrgInfo", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }

    @Scheduled(cron = "${job.cron.dep.exchange}")
    public void getLoRealizationInfo() {
        logger.info("start run job method at {}", Instant.now());
        try {
            depExchangeService.getLoRealizationInfo();
        } catch (Exception ex) {
            logger.error("Error when run getLoRealizationInfo", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }
}
