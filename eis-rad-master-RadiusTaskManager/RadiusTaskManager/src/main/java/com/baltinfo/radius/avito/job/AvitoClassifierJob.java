package com.baltinfo.radius.avito.job;

import com.baltinfo.radius.avito.service.AvitoClassificatorService;
import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @author Igor Lapenok
 * @since 16.09.2020
 */
public class AvitoClassifierJob {
    private static final Logger logger = LoggerFactory.getLogger(AvitoClassifierJob.class);

    private final AvitoClassificatorService avitoClassificatorService;
    private final ExchangeProcController exchangeProcController;

    public AvitoClassifierJob(AvitoClassificatorService avitoClassificatorService,
                              ExchangeProcController exchangeProcController) {
        this.avitoClassificatorService = avitoClassificatorService;
        this.exchangeProcController = exchangeProcController;
    }

    @Scheduled(cron = "${job.cron.load.avito.auto.classifier}")
    public void runJob() {
        Long startDate = new Date().getTime();
        logger.info("Start run Load Avito Auto Classifier Job at {}", startDate);
        Long eprUnid = null;
        try {
            Result<ExchangeProcRun, String> exchangeProcRun =
                    exchangeProcController.createExchangeProcRun(ExchangeProcs.LOAD_AVITO_AUTO_CLASSIFIER,
                            ExchangeProcStatus.RUNNING, null);
            if (exchangeProcRun.isSuccess()) {
                eprUnid = exchangeProcRun.getResult().getEprUnid();
            }
            Result<Void, String> result = avitoClassificatorService.parseAutoCatalog();
            if (result.isError()) {
                exchangeProcController.updateExchangeProcRun(eprUnid, ExchangeProcStatus.ERROR_RUNNING, result.getError());
            } else {
                exchangeProcController.updateExchangeProcRun(eprUnid, ExchangeProcStatus.FINISHED, null);
            }
        } catch (Exception ex) {
            logger.error("Error when run Load Avito Auto Classifier Job", ex);
            try {
                exchangeProcController.createExchangeProcRun(ExchangeProcs.LOAD_AVITO_AUTO_CLASSIFIER,
                                ExchangeProcStatus.ERROR_RUNNING,
                                ex.getMessage());
            } catch (Exception e) {
                logger.error("Error when call createExchangeProcRun method", e);
            }
        } finally {
            logger.info("End run Load Avito Auto Classifier Job at {}; duration = {}", new Date().getTime(), (new Date().getTime() - startDate));
        }

    }


}
