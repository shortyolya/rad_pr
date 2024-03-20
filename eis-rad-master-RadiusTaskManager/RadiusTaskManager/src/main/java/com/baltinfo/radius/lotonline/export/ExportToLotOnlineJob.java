package com.baltinfo.radius.lotonline.export;

import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.model.ExchangeProc;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.db.model.lotonline.Tender;
import com.baltinfo.radius.exchange.impl.ExportToEtpService;
import com.baltinfo.radius.utils.Result;
import org.joda.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Optional;

/**
 * @author Suvorina Aleksandra
 * @since 22.08.2019
 */
public class ExportToLotOnlineJob {

    private static final Logger logger = LoggerFactory.getLogger(ExportToLotOnlineJob.class);

    private final ExchangeProcController exchangeProcController;
    private final ExportToEtpService<Tender, LotOnlineLotParams> exportToEtpService;

    public ExportToLotOnlineJob(ExchangeProcController exchangeProcController,
                                ExportToEtpService<Tender, LotOnlineLotParams> exportToEtpService) {
        this.exchangeProcController = exchangeProcController;
        this.exportToEtpService = exportToEtpService;
    }

    @Scheduled(cron = "${job.cron.export.lotonline}")
    public void run() {
        logger.info("start run job method at {}", Instant.now());
        try {
            ExchangeProc exchangeProc = exchangeProcController.getExchangeProc(ExchangeProcs.SEND_AUCTION_TO_LOTONLINE.getUnid());
            Optional<ExchangeProcRun> exchangeProcRunOpt = exchangeProcController
                    .getFirstRunningExchangeProcRun(ExchangeProcs.SEND_AUCTION_TO_LOTONLINE);
            while (exchangeProcRunOpt.isPresent()) {
                ExchangeProcRun exchangeProcRun = exchangeProcRunOpt.get();
                Result<Void, String> result = exportToEtpService.runProcedure(exchangeProcRun.getEprUnid(),
                        (exchangeProc.getEpIndStopOnError() != null && exchangeProc.getEpIndStopOnError() == 1));
                exchangeProcRunOpt = exchangeProcController
                        .getFirstRunningExchangeProcRun(ExchangeProcs.SEND_AUCTION_TO_LOTONLINE);
            }

        } catch (Exception ex) {
            logger.error("Error when run ExportToLotOnlineJob", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }
}
