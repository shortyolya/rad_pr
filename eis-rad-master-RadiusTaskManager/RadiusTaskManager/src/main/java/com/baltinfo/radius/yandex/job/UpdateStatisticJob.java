package com.baltinfo.radius.yandex.job;

import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.constants.MarketingEvent;
import com.baltinfo.radius.db.constants.TypeStateConstant;
import com.baltinfo.radius.db.controller.AdStatisticsController;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.controller.ObjectController;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.utils.Result;
import com.baltinfo.radius.yandex.client.YandexMetrikaApiClient;
import com.baltinfo.radius.yandex.dto.ObjAndDate;
import com.baltinfo.radius.yandex.model.YandexMetricsErrors;
import com.baltinfo.radius.yandex.model.YandexMetricsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import org.joda.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Class for updating the statistic
 *
 * @author AAA
 * @since 14.10.2021
 */
public class UpdateStatisticJob {

    private static final Logger logger = LoggerFactory.getLogger(UpdateStatisticJob.class);
    private static final int HTTP_OK_STATUS = 200;

    private final String counterIdCatalogLO;
    private final String counterIdAuctionHouse;
    private final String counterIdPledgeLO;

    private final YandexMetrikaApiClient yandexMetrikaApiClient;
    private final ObjectController objectController;
    private final AdStatisticsController adStatisticsController;
    private final ExchangeProcController exchangeProcController;

    public UpdateStatisticJob(String counterIdCatalogLO, String counterIdAuctionHouse, String counterIdPledgeLO,
                              YandexMetrikaApiClient yandexMetrikaApiClient, ObjectController objectController,
                              AdStatisticsController adStatisticsController, ExchangeProcController exchangeProcController) {
        this.counterIdCatalogLO = counterIdCatalogLO;
        this.counterIdAuctionHouse = counterIdAuctionHouse;
        this.counterIdPledgeLO = counterIdPledgeLO;
        this.yandexMetrikaApiClient = yandexMetrikaApiClient;
        this.objectController = objectController;
        this.adStatisticsController = adStatisticsController;
        this.exchangeProcController = exchangeProcController;

    }

    // todo: Переписать на список лотов, сейчас сделано неправильно
    @Scheduled(cron = "${job.cron.update.metrica.statistic}")
    public void runJob() {
        Integer startDate = new Date().getSeconds();
        logger.info("start UpdateStatistic run job method at " + Instant.now());
        try {
            List<String> errorsAH = new ArrayList<>();
            List<ObjAndDate> objectJPAUpdateAH = objectController.getObjectsFromAuctionByEpr(ExchangeProcs.SEND_AUCTION_TO_AUCTION_HOUSE.getUnid(), TypeStateConstant.TRADE_IN_PROCESS.getId());
            objectJPAUpdateAH.addAll(objectController.getObjectForUpdateStatistic(ExchangeProcs.SEND_OBJ_TO_AUCTION_HOUSE.getUnid()));
            for (ObjAndDate objAndDate : objectJPAUpdateAH) {
                Lot lot = objectController.getCurrentLotByObjUnid(objAndDate.getObjectJPA().getObjUnid());
                if (lot != null && lot.getLotLinkSite() != null && !lot.getLotLinkSite().trim().isEmpty()) {
                    Result<Void, String> rez = updateAdStatistics(objAndDate, lot.getLotLinkSite(), MarketingEvent.RAD_SITE.getUnid(), counterIdAuctionHouse);
                    if (rez.isError()) errorsAH.add(rez.getError());
                }
            }
            if (errorsAH.isEmpty()) {
                createExchangeProc(ExchangeProcs.UPDATE_STATISTIC_FROM_AUCTION_HOUSE.getUnid(), ExchangeProcStatus.FINISHED.getCode(), null);
            } else {
                createExchangeProc(ExchangeProcs.UPDATE_STATISTIC_FROM_AUCTION_HOUSE.getUnid(), ExchangeProcStatus.ERROR_RUNNING.getCode(), errorsAH);
            }
        } catch (Exception ex) {
            logger.error("Error when run UpdateStatistic for auction-house counter", ex);
            createExchangeProc(ExchangeProcs.UPDATE_STATISTIC_FROM_AUCTION_HOUSE.getUnid(),
                    ExchangeProcStatus.ERROR_RUNNING.getCode(), Arrays.asList("Ошибка в процедуре обновления статистики: " + ex.getMessage()));
        }

        try {
            List<String> errorsETP = new ArrayList<>();
            List<ObjAndDate> objectJPAUpdateETP = objectController.getObjectsFromAuctionByEpr(ExchangeProcs.SEND_AUCTION_TO_LOTONLINE.getUnid(), TypeStateConstant.TRADE_IN_PROCESS.getId());
            objectJPAUpdateETP.addAll(objectController.getObjectsFromAuctionByEpr(ExchangeProcs.SEND_AUCTION_TO_BANKRUPTCY.getUnid(), TypeStateConstant.TRADE_IN_PROCESS.getId()));
            for (ObjAndDate objAndDate : objectJPAUpdateETP) {
                Lot lot = objectController.getCurrentLotByObjUnid(objAndDate.getObjectJPA().getObjUnid());
                if (lot != null && lot.getLotLinkSite() != null && !lot.getLotLinkSite().trim().isEmpty()) {
                    Result<Void, String> rez = updateAdStatistics(objAndDate, lot.getLotLinkSite(), MarketingEvent.LINK_SITE.getUnid(), counterIdCatalogLO);
                    if (rez.isError()) errorsETP.add(rez.getError());
                }
            }
            if (errorsETP.isEmpty()) {
                createExchangeProc(ExchangeProcs.UPDATE_STATISTIC_FROM_ETP.getUnid(), ExchangeProcStatus.FINISHED.getCode(), null);
            } else {
                createExchangeProc(ExchangeProcs.UPDATE_STATISTIC_FROM_ETP.getUnid(), ExchangeProcStatus.ERROR_RUNNING.getCode(), errorsETP);
            }
        } catch (Exception ex) {
            logger.error("Error when run UpdateStatistic for etp vitrina counter", ex);
            createExchangeProc(ExchangeProcs.UPDATE_STATISTIC_FROM_ETP.getUnid(),
                    ExchangeProcStatus.ERROR_RUNNING.getCode(), Arrays.asList("Ошибка в процедуре обновления статистики: " + ex.getMessage()));
        }

        try {
            List<String> errorsPLEDGE = new ArrayList<>();
            List<ObjAndDate> objectJPAUpdatePLEDGE = objectController.getObjectForUpdateStatistic(ExchangeProcs.SEND_OBJ_TO_PLEDGE.getUnid());
            for (ObjAndDate objAndDate : objectJPAUpdatePLEDGE) {
                String objUrl = objAndDate.getObjectJPA().getObjPledgeUrl();
                if (objUrl != null && !objUrl.trim().isEmpty()) {
                    Result<Void, String> rez = updateAdStatistics(objAndDate, objUrl, MarketingEvent.PLEDGE_URL.getUnid(), counterIdPledgeLO);
                    if (rez.isError()) errorsPLEDGE.add(rez.getError());
                }
            }
            if (errorsPLEDGE.isEmpty()) {
                createExchangeProc(ExchangeProcs.UPDATE_STATISTIC_FROM_PLEDGE.getUnid(), ExchangeProcStatus.FINISHED.getCode(), null);
            } else {
                createExchangeProc(ExchangeProcs.UPDATE_STATISTIC_FROM_PLEDGE.getUnid(), ExchangeProcStatus.ERROR_RUNNING.getCode(), errorsPLEDGE);
            }
        } catch (Exception ex) {
            logger.error("Error when run UpdateStatistic for pledge counter", ex);
            createExchangeProc(ExchangeProcs.UPDATE_STATISTIC_FROM_PLEDGE.getUnid(),
                    ExchangeProcStatus.ERROR_RUNNING.getCode(), Arrays.asList("Ошибка в процедуре обновления статистики: " + ex.getMessage()));
        }

        logger.info("end UpdateStatistic run job method at " + Instant.now() + " duration = " + (new Date().getSeconds() - startDate) + "c");
    }

    private Result<Void, String> updateAdStatistics(ObjAndDate objAndDate, String objUrl, Long mevUnid, String counterId) {
        HttpResponse<Object> responseStatistic = yandexMetrikaApiClient.getStatistics(counterId, objUrl, objAndDate.getDateString());
        String objIdentifier = "objUnid = " + objAndDate.getObjectJPA().getObjUnid().toString() + " и objCode = " + objAndDate.getObjectJPA().getObjCode();
        if (responseStatistic == null)
            return Result.error("Ошибка при запросе по объекту " + objIdentifier);
        if (responseStatistic.getStatus() != HTTP_OK_STATUS) {
            String errorMessage = new ObjectMapper().convertValue(responseStatistic.getBody(), YandexMetricsErrors.class).getMessage();
            return Result.error("Метрика вернула ошибку: " + errorMessage + " по объекту " + objIdentifier);
        }
        YandexMetricsResponse statistic = new ObjectMapper().convertValue(responseStatistic.getBody(), YandexMetricsResponse.class);
        if (statistic.getTotalRows() == 0) return Result.ok();

        return adStatisticsController.updateAdStatisticsViews(objAndDate.getObjectJPA().getObjUnid(), mevUnid, statistic.getTotals().get(0).longValue(), objIdentifier);
    }

    private void createExchangeProc(Long epUnid, int code, List<String> error) {
        ExchangeProcRun exchangeProcRun = new ExchangeProcRun();
        exchangeProcRun.setEpUnid(epUnid);
        exchangeProcRun.setEprLoadStatus(code);
        if (code == ExchangeProcStatus.ERROR_RUNNING.getCode())
            exchangeProcRun.setEprErrorText(String.join(";\n", error));
        exchangeProcRun.setEprPaUnid(1L);
        exchangeProcRun.setEprRunDate(new Date());
        exchangeProcController.saveExchangeProcRun(exchangeProcRun);
    }

}
