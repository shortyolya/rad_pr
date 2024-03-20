package com.baltinfo.radius.feed.statistic;

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
 * @author Suvorina Aleksandra
 * @since 17.03.2021
 */
public class CianAdStatisticsJob {

    private static final Logger logger = LoggerFactory.getLogger(CianAdStatisticsJob.class);

    private final AdStatisticService cianAdStatisticService;
    private final ExchangeProcController exchangeProcController;

    public CianAdStatisticsJob(AdStatisticService cianAdStatisticService, ExchangeProcController exchangeProcController) {
        this.cianAdStatisticService = cianAdStatisticService;
        this.exchangeProcController = exchangeProcController;
    }

    @Scheduled(cron = "${job.cron.feed.statistic.cian}")
    public void runJob() {
        Long startDate = new Date().getTime();
        logger.info("start CianAdStatisticsJob run job method at " + startDate);
        try {
            Result<String, String> result = cianAdStatisticService.updateAdStatistic(false);
            if (result.isError()) {
                createErrorExchangeProc(result.getError());
            } else {
                createExchangeProc(result.getResult());
            }
        } catch (Exception ex) {
            logger.error("Error when run CianAdStatisticsJob", ex);
        }
        logger.info("end CianAdStatisticsJob run job method at " + new Date().getTime() + " duration = " + (new Date().getTime() - startDate));
    }

    @Scheduled(cron = "${job.cron.feed.additional.statistic.cian}")
    public void runAdditionalStatisticJob() {
        Long startDate = new Date().getTime();
        logger.info("start cian additional statistic job at " + startDate);
        try {
            Result<String, String> result = cianAdStatisticService.updateAdStatistic(true);
            if (result.isError()) {
                createErrorExchangeProc(result.getError());
            } else {
                createExchangeProc(result.getResult());
            }
        } catch (Exception ex) {
            logger.error("Error when run cian additional statistic job", ex);
        }
        logger.info("end cian additional statistic job at " + new Date().getTime() + " duration = " + (new Date().getTime() - startDate));
    }

    private void createErrorExchangeProc(String error) {
        ExchangeProcRun exchangeProcRun = new ExchangeProcRun();
        exchangeProcRun.setEpUnid(ExchangeProcs.CIAN_AD_STATISTIC.getUnid());
        exchangeProcRun.setEprLoadStatus(ExchangeProcStatus.ERROR_START.getCode());
        exchangeProcRun.setEprErrorText(error);
        exchangeProcRun.setEprPaUnid(1L);
        exchangeProcRun.setEprRunDate(new Date());
        exchangeProcController.saveExchangeProcRun(exchangeProcRun);
    }

    private void createExchangeProc(String error) {
        ExchangeProcRun exchangeProcRun = new ExchangeProcRun();
        exchangeProcRun.setEprPaUnid(1L);
        exchangeProcRun.setEprRunDate(new Date());
        exchangeProcRun.setEpUnid(ExchangeProcs.CIAN_AD_STATISTIC.getUnid());
        if (error != null && !error.isEmpty()) {
            exchangeProcRun.setEprLoadStatus(ExchangeProcStatus.ERROR_RUNNING.getCode());
            exchangeProcRun.setEprErrorText(error);
        } else {
            exchangeProcRun.setEprLoadStatus(ExchangeProcStatus.FINISHED.getCode());
        }
        exchangeProcController.saveExchangeProcRun(exchangeProcRun);
    }
}
