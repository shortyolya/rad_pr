package com.baltinfo.radius.job;

import com.baltinfo.radius.calls.client.CallsApiClient;
import com.baltinfo.radius.calls.model.CallServiceResponse;
import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;

/**
 * @author Igor Lapenok
 * @since 06.09.2023
 */
public class FillCallsCountToTrustLotsJob {

    private static final Logger logger = LoggerFactory.getLogger(FillCallsCountToTrustLotsJob.class);

    private final LotController lotController;
    private final CallsApiClient callsApiClient;
    private final ExchangeProcController exchangeProcController;

    public FillCallsCountToTrustLotsJob(LotController lotController,
                                        CallsApiClient callsApiClient,
                                        ExchangeProcController exchangeProcController) {
        this.lotController = lotController;
        this.callsApiClient = callsApiClient;
        this.exchangeProcController = exchangeProcController;
    }

    @Scheduled(cron = "${job.cron.fill.calls.count.to.lots}")
    public void runJob() {
        Long startDate = new Date().getTime();
        logger.info("start run job method at {}", startDate);
        try {
            Result<ExchangeProcRun, String> exchangeProcRun =
                    exchangeProcController.createExchangeProcRun(ExchangeProcs.UPDATE_CALLS_COUNT_FOR_TRUST_LOTS,
                            ExchangeProcStatus.RUNNING, null);
            if (exchangeProcRun.isError()) {
                logger.error("Can't create exchange proc run: " + exchangeProcRun.getError());
                return;
            }
            ExchangeProcRun epr = exchangeProcRun.getResult();
            StringBuilder errors = new StringBuilder();
            Long count = 0L;
            Long errorCount = 0L;
            try {
                List<Lot> list = lotController.getTrustLots();
                for (Lot lot : list) {
                    Result<CallServiceResponse, String> result = callsApiClient.getCallCount(lot.getLotEtpCode());
                    if (result.isSuccess()) {
                        CallServiceResponse callServiceResponse = result.getResult();
                        lot.setLotCallsCount(callServiceResponse.getCount() == null
                                ? 0L
                                : Long.parseLong(callServiceResponse.getCount()));
                        lot.setLotCallsUniqCount(callServiceResponse.getUniqueCount() == null
                                ? 0L
                                : Long.parseLong(callServiceResponse.getUniqueCount()));
                        Result<Lot, String> updateResult = lotController.updateLot(lot);
                        if (updateResult.isSuccess()) {
                            count++;
                        } else {
                            errorCount++;
                            errors.append("Не удалось сохранить в БД данные по лоту с идентификатором ЕИС ").append(lot.getLotUnid()).append(", текст ошибки: ").append(updateResult.getError()).append(".\n");
                        }
                    } else {
                        errorCount++;
                        errors.append("Не удалось получить данные по лоту").append(lot.getLotEtpCode()).append(", текст ошибки: ").append(result.getError()).append(".\n");
                    }
                }
                if (errors.length() == 0) {
                    epr.setEprLoadStatus(ExchangeProcStatus.FINISHED.getCode());
                    epr.setEprErrorText("Обновление успешно произведено. Обновлено - " + count + "лотов");
                    exchangeProcController.updateExchangeProcRun(epr);
                } else {
                    updateEprError(epr, "Обновление произведено с ошибками. Обновлено - " + count +
                            "лотов, ошибки обновления - " + errorCount + ". Детально:\n" + errors);
                }
            } catch (Exception ex) {
                logger.error("Error when runJob", ex);
                updateEprError(epr, getStackTrace(ex));
            }

        } catch (Exception ex) {
            logger.error("Error when run FillCallsCountToTrustLotsJob", ex);
        } finally {
            logger.info("end run job method at {}; duration = {}", new Date().getTime(), (new Date().getTime() - startDate));
        }
    }

    private void updateEprError(ExchangeProcRun epr, String error) {
        epr.setEprLoadStatus(ExchangeProcStatus.ERROR_RUNNING.getCode());
        epr.setEprErrorText(error);
        exchangeProcController.updateExchangeProcRun(epr);
    }

    private String getStackTrace(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }
}
