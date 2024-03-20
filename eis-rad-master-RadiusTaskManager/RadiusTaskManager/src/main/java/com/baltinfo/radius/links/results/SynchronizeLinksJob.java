package com.baltinfo.radius.links.results;

import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.util.Optional;

public class SynchronizeLinksJob {

    private static final Logger logger = LoggerFactory.getLogger(SynchronizeLinksJob.class);
    private final SynchronizeLinksService synchronizeLinksService;
    private final ExchangeProcController exchangeProcController;

    public SynchronizeLinksJob(SynchronizeLinksService synchronizeLinksService, ExchangeProcController exchangeProcController) {
        this.synchronizeLinksService = synchronizeLinksService;
        this.exchangeProcController = exchangeProcController;
    }

    @Scheduled(cron = "${job.cron.synchronize.links}")
    public void runJob() {
        logger.info("start run job method at {}", Instant.now());
        try {
            Optional<ExchangeProcRun> exchangeProcRunOptAh = exchangeProcController
                    .getFirstRunningExchangeProcRun(ExchangeProcs.SYNCHRONIZE_LINKS_AUCTION_HOUSE);
            while (exchangeProcRunOptAh.isPresent()) {
                ExchangeProcRun exchangeProcRunAh = exchangeProcRunOptAh.get();
                synchronizeLinksService.runProcedure(exchangeProcRunAh.getEprUnid());
                exchangeProcRunOptAh = exchangeProcController
                        .getFirstRunningExchangeProcRun(ExchangeProcs.SYNCHRONIZE_LINKS_AUCTION_HOUSE);
            }

            Optional<ExchangeProcRun> exchangeProcRunOptRad = exchangeProcController
                    .getFirstRunningExchangeProcRun(ExchangeProcs.SYNCHRONIZE_LINKS_RAD_HOLDING);
            while (exchangeProcRunOptRad.isPresent()) {
                ExchangeProcRun exchangeProcRunRad = exchangeProcRunOptRad.get();
                synchronizeLinksService.runProcedure(exchangeProcRunRad.getEprUnid());
                exchangeProcRunOptRad = exchangeProcController
                        .getFirstRunningExchangeProcRun(ExchangeProcs.SYNCHRONIZE_LINKS_RAD_HOLDING);
            }
        } catch (Exception ex) {
            logger.error("Error when run SynchronizeLinksJob", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }
}
