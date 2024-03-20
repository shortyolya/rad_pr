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
 * @since 30.03.2021
 */
public class JcatAdStatisticJob {
    private static final Logger logger = LoggerFactory.getLogger(JcatAdStatisticJob.class);

    private final AdStatisticService jcatAdStatisticService;
    private final ExchangeProcController exchangeProcController;

    public JcatAdStatisticJob(AdStatisticService jcatAdStatisticService, ExchangeProcController exchangeProcController) {
        this.jcatAdStatisticService = jcatAdStatisticService;
        this.exchangeProcController = exchangeProcController;
    }

    @Scheduled(cron = "${job.cron.feed.statistic.jcat}")
    public void runJob() {
        Long startDate = new Date().getTime();
        logger.info("start JcatAdStatisticJob run job method at " + startDate);
        try {
            Result<String, String> result = jcatAdStatisticService.updateAdStatistic(false);
            if (result.isError()) {
                createErrorExchangeProc(result.getError());
            } else {
                createExchangeProc(result.getResult());
            }
        } catch (Exception ex) {
            logger.error("Error when run JcatAdStatisticJob", ex);
        }
        logger.info("end JcatAdStatisticJob run job method at " + new Date().getTime() + " duration = " + (new Date().getTime() - startDate));
    }

    @Scheduled(cron = "${job.cron.feed.additional.statistic.jcat}")
    public void runAdditionalStatisticJob() {
        Long startDate = new Date().getTime();
        logger.info("start jcat additional statistic job at " + startDate);
        try {
            Result<String, String> result = jcatAdStatisticService.updateAdStatistic(true);
            if (result.isError()) {
                createErrorExchangeProc(result.getError());
            } else {
                createExchangeProc(result.getResult());
            }
        } catch (Exception ex) {
            logger.error("Error when run jcat additional statistic job", ex);
        }
        logger.info("end jcat additional statistic job at " + new Date().getTime() + " duration = " + (new Date().getTime() - startDate));
    }

    private void createErrorExchangeProc(String error) {
        ExchangeProcRun exchangeProcRun = new ExchangeProcRun();
        exchangeProcRun.setEpUnid(ExchangeProcs.JCAT_AD_STATISTIC.getUnid());
        exchangeProcRun.setEprLoadStatus(ExchangeProcStatus.ERROR_START.getCode());
        exchangeProcRun.setEprErrorText(error);
        exchangeProcRun.setEprPaUnid(1L);
        exchangeProcRun.setEprRunDate(new Date());
        exchangeProcController.saveExchangeProcRun(exchangeProcRun);
    }

    private void createExchangeProc(String error) {
        ExchangeProcRun exchangeProcRun = new ExchangeProcRun();
        exchangeProcRun.setEpUnid(ExchangeProcs.JCAT_AD_STATISTIC.getUnid());
        exchangeProcRun.setEprPaUnid(1L);
        exchangeProcRun.setEprRunDate(new Date());
        if (error != null && !error.isEmpty()) {
            exchangeProcRun.setEprLoadStatus(ExchangeProcStatus.ERROR_RUNNING.getCode());
            exchangeProcRun.setEprErrorText(error);
        } else {
            exchangeProcRun.setEprLoadStatus(ExchangeProcStatus.FINISHED.getCode());
        }
        exchangeProcController.saveExchangeProcRun(exchangeProcRun);
    }
}
