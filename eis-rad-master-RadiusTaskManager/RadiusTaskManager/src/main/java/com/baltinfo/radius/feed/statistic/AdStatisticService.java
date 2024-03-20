package com.baltinfo.radius.feed.statistic;

import com.baltinfo.radius.db.controller.AdStatisticsController;
import com.baltinfo.radius.db.controller.ObjectController;
import com.baltinfo.radius.db.model.AdStatistics;
import com.baltinfo.radius.db.model.ObjectJPA;
import com.baltinfo.radius.feed.utils.StatisticResult;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Suvorina Aleksandra
 * @since 12.03.2021
 */
public class AdStatisticService {

    private final Logger logger = LoggerFactory.getLogger(AdStatisticService.class);

    private final FeedStatisticsService feedStatisticsService;
    private final ObjectController objectController;
    private final AdStatisticsController adStatisticsController;
    private final Long mevUnid;

    public AdStatisticService(FeedStatisticsService feedStatisticsService, ObjectController objectController,
                              AdStatisticsController adStatisticsController, Long mevUnid) {
        this.feedStatisticsService = feedStatisticsService;
        this.objectController = objectController;
        this.adStatisticsController = adStatisticsController;
        this.mevUnid = mevUnid;
    }

    public Result<String, String> updateAdStatistic(boolean withAdditionalProps) {
        try {

            StatisticResult<List<FeedStatistic>, String> feedStatistics = feedStatisticsService.getFeedStatistics(withAdditionalProps);
            if (feedStatistics.isError()) {
                return Result.error("Ошибка при получении статистики по объявлениям: " + feedStatistics.getError());
            }

            List<AdStatistics> adStatistics = adStatisticsController.getAdStatisticsByMevUnid(mevUnid);

//            adStatistics.forEach(as -> as.setIndActual(0));

            List<String> notFoundObjects = new ArrayList<>();
            for (FeedStatistic feedStatistic : feedStatistics.getResult()) {
                Optional<ObjectJPA> objectResult = objectController.getObjectByCode(feedStatistic.getObjCode());
                if (!objectResult.isPresent()) {
                    notFoundObjects.add("Не найден объект по коду " + feedStatistic.getObjCode());
                    continue;
                }

                AdStatistics adStatistic = adStatistics.stream()
                        .filter(as -> as.getObjUnid().equals(objectResult.get().getObjUnid()))
                        .findFirst()
                        .orElse(null);
                if (adStatistic == null) {
                    adStatistic = new AdStatistics(mevUnid, objectResult.get().getObjUnid());
                    adStatistics.add(adStatistic);
                }

                adStatistic.setAdsLink(feedStatistic.getAdUrl());
                adStatistic.setAdsErrors(feedStatistic.getAdError());
                adStatistic.setIndActual(1);
                if (feedStatistic.getViewsCount() != null) {
                    adStatistic.setAdsViewsCount(feedStatistic.getViewsCount());
                }
                if (feedStatistic.getCallsCount() != null) {
                    adStatistic.setAdsCallsCount(feedStatistic.getCallsCount());
                }
                if (feedStatistic.getViewsDetails() != null) {
                    adStatistic.setAdsViewDetails(feedStatistic.getViewsDetails());
                }
            }
            Result<Void, String> updateResult = adStatisticsController.updateAdStatistics(adStatistics);
            if (updateResult.isError()) {
                return Result.error("Ошибка БД при обновлении статистики: " + updateResult.getError());
            }

            String notFoundObjectsStr = String.join(";\n", notFoundObjects);
            if (feedStatistics.getError() != null) {
                return Result.ok(feedStatistics.getError() + "/n" + notFoundObjectsStr);
            } else {
                return Result.ok(notFoundObjectsStr);
            }
        } catch (RuntimeException ex) {
            logger.error("Error updateAdStatistic", ex);
            return Result.error("Ошибка при обновлении статистики: " + ex.getMessage());
        }

    }

}
