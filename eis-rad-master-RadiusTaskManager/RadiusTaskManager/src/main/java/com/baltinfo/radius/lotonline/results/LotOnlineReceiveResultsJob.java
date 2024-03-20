package com.baltinfo.radius.lotonline.results;

import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.model.ExchangeProc;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.util.Optional;

/**
 * @author Suvorina Aleksandra
 * @since 10.10.2018
 */
public class LotOnlineReceiveResultsJob {

    private static final Logger logger = LoggerFactory.getLogger(LotOnlineReceiveResultsJob.class);

    private final ExchangeProcController exchangeProcController;
    private final LotOnlineReceiveResultsService lotOnlineReceiveResultsService;

    public LotOnlineReceiveResultsJob(ExchangeProcController exchangeProcController,
                                      LotOnlineReceiveResultsService lotOnlineReceiveResultsService) {
        this.exchangeProcController = exchangeProcController;
        this.lotOnlineReceiveResultsService = lotOnlineReceiveResultsService;
    }

    @Scheduled(cron = "${job.cron.export.lotonline.results}")
    public void run() {
        logger.info("start run job method at {}", Instant.now());
        try {
            ExchangeProc exchangeProc = exchangeProcController
                    .getExchangeProc(ExchangeProcs.RECEIVE_AUCTION_FROM_LOT_ONLINE.getUnid());
            Optional<ExchangeProcRun> exchangeProcRunOpt = exchangeProcController
                    .getFirstRunningExchangeProcRun(ExchangeProcs.RECEIVE_AUCTION_FROM_LOT_ONLINE);
            while (exchangeProcRunOpt.isPresent()) {
                ExchangeProcRun exchangeProcRun = exchangeProcRunOpt.get();
                Result<Void, String> result = lotOnlineReceiveResultsService.runProcedure(exchangeProcRun.getEprUnid(),
                        (exchangeProc.getEpIndStopOnError() != null && exchangeProc.getEpIndStopOnError() == 1));
                exchangeProcRunOpt = exchangeProcController
                        .getFirstRunningExchangeProcRun(ExchangeProcs.RECEIVE_AUCTION_FROM_LOT_ONLINE);
            }

        } catch (Exception ex) {
            logger.error("Error when run LotOnlineReceiveResultsJob", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }
}
