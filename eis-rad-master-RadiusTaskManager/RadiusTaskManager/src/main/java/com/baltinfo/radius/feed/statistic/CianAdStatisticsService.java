package com.baltinfo.radius.feed.statistic;

import com.baltinfo.radius.feed.cian.api.client.CianApiClient;
import com.baltinfo.radius.feed.cian.api.model.GetOrderResponse;
import com.baltinfo.radius.feed.cian.api.model.GetOrderResult;
import com.baltinfo.radius.feed.cian.api.model.GetStatisticResponse;
import com.baltinfo.radius.feed.cian.api.model.Offer;
import com.baltinfo.radius.feed.utils.StatisticResult;
import com.baltinfo.radius.utils.Result;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Suvorina Aleksandra
 * @since 14.03.2021
 */
public class CianAdStatisticsService implements FeedStatisticsService {

    private final Logger logger = LoggerFactory.getLogger(CianAdStatisticsService.class);

    private final List<CianApiClient> cianApiClients;

    private static final int MAX_ID_COUNT = 50;
    private static final String RENT_SUFFIX = "_rent";

    public CianAdStatisticsService(List<CianApiClient> cianApiClients) {
        this.cianApiClients = cianApiClients;
    }

    @Override
    public StatisticResult<List<FeedStatistic>, String> getFeedStatistics(boolean withAdditionalProps) {
        try {
            List<FeedStatistic> resultList = new ArrayList<>();
            for(CianApiClient cianApiClient : cianApiClients) {
                Result<GetOrderResponse, String> orderResult = cianApiClient.getOrder();
                if (orderResult.isError()) {
                    logger.error("Error cian api getOrder: {}", orderResult.getError());
                    return StatisticResult.error("Ошибка при получении статистики циан: " + orderResult.getError());
                }
                GetOrderResult order = orderResult.getResult().getResult();
                Map<Long, Long> viewsMap = new HashMap<>();
                Map<Long, Long> callsMap = new HashMap<>();
                if (withAdditionalProps) {
                    getStatistic(cianApiClient, order.getOffers(), viewsMap, callsMap);
                }

                List<FeedStatistic> feedStatistics = order.getOffers().stream()
                        .map(o -> new FeedStatistic(o.getExternalId().contains(RENT_SUFFIX) ? o.getExternalId().substring(0, o.getExternalId().lastIndexOf(RENT_SUFFIX)) : o.getExternalId(),
                                o.getUrl(),
                                o.getErrors(),
                                viewsMap.get(o.getOfferId()),
                                null,
                                callsMap.get(o.getOfferId())))
                        .collect(Collectors.toList());
                resultList.addAll(feedStatistics);
            }
            return StatisticResult.ok(resultList);
        } catch (RuntimeException ex) {
            logger.error("Error cian getFeedStatistics", ex);
            return StatisticResult.error("Ошибка при получении статистики по объявлениям циан: " + ex.getMessage());
        }
    }

    private void getStatistic(CianApiClient cianApiClient, List<Offer> offerList, Map<Long, Long> viewsMap, Map<Long, Long> callsMap) {
        List<String> offerIdList = offerList.stream()
                .filter(t -> t.getOfferId() != null)
                .map(t -> t.getOfferId().toString())
                .collect(Collectors.toList());
        List<List<String>> offerIdLists = ListUtils.partition(offerIdList, MAX_ID_COUNT);
        for (List<String> list : offerIdLists) {
            Result<GetStatisticResponse, String> statResult = cianApiClient.getViewsStatistics(list);
            if (statResult.isSuccess()) {
                statResult.getResult().getResult().getStatistics().forEach(t -> {
                    viewsMap.put(t.getOfferId(), t.getTotalViews());
                    callsMap.put(t.getOfferId(), t.getPhoneShows());
                });
            } else {
                logger.error("Error cian api getViewStatistics: {}", statResult.getError());
            }
        }
    }
}
