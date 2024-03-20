package com.baltinfo.radius.bankruptcy.export;

import com.baltinfo.radius.bankruptcy.BkrLotParams;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.model.ExchangeProc;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.db.model.bankruptcy.VAuctionBkr;
import com.baltinfo.radius.exchange.impl.ExportToEtpService;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.util.Optional;

/**
 * @author Suvorina Aleksandra
 * @since 22.08.2018
 */
public class ExportToBankruptcyJob {

    private static final Logger logger = LoggerFactory.getLogger(ExportToBankruptcyJob.class);

    private final ExchangeProcController exchangeProcController;
    private final ExportToEtpService<VAuctionBkr, BkrLotParams> exportToEtpService;

    public ExportToBankruptcyJob(ExchangeProcController exchangeProcController,
                                 ExportToEtpService<VAuctionBkr, BkrLotParams> exportToEtpService) {
        this.exchangeProcController = exchangeProcController;
        this.exportToEtpService = exportToEtpService;
    }

    @Scheduled(cron = "${job.cron.export.bankruptcy}")
    public void run() {
        logger.info("start run job method at {}", Instant.now());
        try {
            ExchangeProc exchangeProc = exchangeProcController.getExchangeProc(ExchangeProcs.SEND_AUCTION_TO_BANKRUPTCY.getUnid());
            Optional<ExchangeProcRun> exchangeProcRunOpt = exchangeProcController
                    .getFirstRunningExchangeProcRun(ExchangeProcs.SEND_AUCTION_TO_BANKRUPTCY);
            while (exchangeProcRunOpt.isPresent()) {
                ExchangeProcRun exchangeProcRun = exchangeProcRunOpt.get();
                Result<Void, String> result = exportToEtpService.runProcedure(exchangeProcRun.getEprUnid(),
                        (exchangeProc.getEpIndStopOnError() != null && exchangeProc.getEpIndStopOnError() == 1));
                exchangeProcRunOpt = exchangeProcController
                        .getFirstRunningExchangeProcRun(ExchangeProcs.SEND_AUCTION_TO_BANKRUPTCY);
            }

        } catch (Exception ex) {
            logger.error("Error when run ExportToBankruptcyJob", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }
}
