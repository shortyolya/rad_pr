package com.baltinfo.radius.feed.archive;

import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class DeleteArchiveFeedJob {
    private static final Logger logger = LoggerFactory.getLogger(DeleteArchiveFeedJob.class);

    private final DeleteArchiveFeedService deleteArchiveFeedService;
    private final ExchangeProcController exchangeProcController;

    public DeleteArchiveFeedJob(DeleteArchiveFeedService deleteArchiveFeedService, ExchangeProcController exchangeProcController) {
        this.deleteArchiveFeedService = deleteArchiveFeedService;
        this.exchangeProcController = exchangeProcController;
    }


    @Scheduled(cron = "${job.cron.feed.delete.archive}")
    public void runJob() {
        Long startDate = new Date().getTime();
        logger.info("start DeleteArchiveFeedJob run job method at " + startDate);
        try {
            Result<Void, String> result = deleteArchiveFeedService.deleteArchiveFeed();
            if (result.isError()) {
                createErrorExchangeProc(result.getError());
            } else {
                createExchangeProc();
            }
        } catch (Exception ex) {
            logger.error("Error when run DeleteArchiveFeedJob", ex);
        }
        logger.info("end DeleteArchiveFeedJob run job method at " + new Date().getTime() + " duration = " + (new Date().getTime() - startDate));
    }


    private void createErrorExchangeProc(String error) {
        ExchangeProcRun exchangeProcRun = new ExchangeProcRun();
        exchangeProcRun.setEpUnid(ExchangeProcs.DELETE_ARCHIVE_FEED.getUnid());
        exchangeProcRun.setEprLoadStatus(ExchangeProcStatus.ERROR_RUNNING.getCode());
        exchangeProcRun.setEprErrorText(error);
        exchangeProcRun.setEprPaUnid(1L);
        exchangeProcRun.setEprRunDate(new Date());
        exchangeProcController.saveExchangeProcRun(exchangeProcRun);
    }

    private void createExchangeProc() {
        ExchangeProcRun exchangeProcRun = new ExchangeProcRun();
        exchangeProcRun.setEpUnid(ExchangeProcs.DELETE_ARCHIVE_FEED.getUnid());
        exchangeProcRun.setEprPaUnid(1L);
        exchangeProcRun.setEprRunDate(new Date());
        exchangeProcRun.setEprLoadStatus(ExchangeProcStatus.FINISHED.getCode());
        exchangeProcController.saveExchangeProcRun(exchangeProcRun);
    }

}
