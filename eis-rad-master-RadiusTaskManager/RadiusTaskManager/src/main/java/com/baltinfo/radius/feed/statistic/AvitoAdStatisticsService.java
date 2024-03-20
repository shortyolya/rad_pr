package com.baltinfo.radius.feed.statistic;

import com.baltinfo.radius.feed.avito.api.client.AvitoApiClient;
import com.baltinfo.radius.feed.avito.api.model.Ad;
import com.baltinfo.radius.feed.avito.api.model.AvitoReportItem;
import com.baltinfo.radius.feed.avito.api.model.AvitoReportMessage;
import com.baltinfo.radius.feed.avito.api.model.AvitoReportResponse;
import com.baltinfo.radius.feed.avito.api.model.AvitoReportsList;
import com.baltinfo.radius.feed.avito.api.model.AvitoUser;
import com.baltinfo.radius.feed.avito.api.model.CallsStatisticRequestBody;
import com.baltinfo.radius.feed.avito.api.model.ItemCallStat;
import com.baltinfo.radius.feed.avito.api.model.ItemStat;
import com.baltinfo.radius.feed.avito.api.model.Messages;
import com.baltinfo.radius.feed.avito.api.model.PostStatisticResponse;
import com.baltinfo.radius.feed.avito.api.model.Report;
import com.baltinfo.radius.feed.avito.api.model.ReportFromList;
import com.baltinfo.radius.feed.avito.api.model.ViewStatisticRequestBody;
import com.baltinfo.radius.feed.utils.StatisticResult;
import com.baltinfo.radius.utils.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Suvorina Aleksandra
 * @since 14.03.2021
 */
public class AvitoAdStatisticsService implements FeedStatisticsService {

    private final Logger logger = LoggerFactory.getLogger(AvitoAdStatisticsService.class);
    private static final String ERROR_AD_TYPE = "error";
    private static final int MAX_ID_COUNT = 200;
    private static final long INTERVAL = 540;
    private final ObjectMapper mapper = new ObjectMapper();
    private final List<AvitoApiClient> avitoApiClients;

    public AvitoAdStatisticsService(List<AvitoApiClient> avitoApiClients) {
        this.avitoApiClients = avitoApiClients;
    }

    @Override
    public StatisticResult<List<FeedStatistic>, String> getFeedStatistics(boolean withAdditionalProps) {
        List<FeedStatistic> resultList = new ArrayList<>();
        StringBuilder errors = new StringBuilder();
        for (AvitoApiClient avitoApiClient : avitoApiClients) {
            try {
                Result<String, String> authResult = avitoApiClient.authorization();
                if (authResult.isError()) {
                    logger.error("Error avito api authorization with clientId={} : {}",
                            avitoApiClient.getClientId(),
                            authResult.getError());
                    errors.append(avitoApiClient.getClientId())
                            .append(": Ошибка при авторизации клиента авито: ")
                            .append(authResult.getError())
                            .append("\n");
                    continue;
                }
                Result<AvitoUser, String> userResult = avitoApiClient.getUser(authResult.getResult());
                if (userResult.isError()) {
                    logger.error("Error avito api get user with clientId={} : {}",
                            avitoApiClient.getClientId(),
                            userResult.getError());
                    errors.append(avitoApiClient.getClientId())
                            .append(": Ошибка при получении пользователя api авито: ")
                            .append(userResult.getError())
                            .append("\n");
                    continue;
                }
                Result<List<ReportFromList>, String> reportsListResult =
                        avitoApiClient.getReportsList(authResult.getResult());
                if (reportsListResult.isError() || reportsListResult.getResult() == null) {
                    logger.error("Error avito api get reports list with clientId={} : {}",
                            avitoApiClient.getClientId(),
                            reportsListResult.getError());
                    errors.append(avitoApiClient.getClientId())
                            .append(": Ошибка при получении списка отчетов авито: ")
                            .append(reportsListResult.getError())
                            .append("\n");
                    continue;
                }
                Optional<ReportFromList> actualReport = reportsListResult.getResult().stream()
                        .filter(report -> report.getFinishedAt() != null)
                        .findFirst();
                if (!actualReport.isPresent()) {
                    logger.error("Error avito api get report from list with clientId={}",
                            avitoApiClient.getClientId());
                    errors.append(avitoApiClient.getClientId())
                            .append(": Ошибка при получении последнего успешного отчета авито")
                            .append("\n");
                    continue;
                }
                Result<List<AvitoReportItem>, String> reportByIdItemsResult = avitoApiClient
                        .getReportById(authResult.getResult(), actualReport.get().getId());
                if (reportByIdItemsResult.isError()) {
                    logger.error("Error avito api get report with clientId={} : {}",
                            avitoApiClient.getClientId(),
                            reportByIdItemsResult.getError());
                    errors.append(avitoApiClient.getClientId())
                            .append(": Ошибка при получении отчета авито: ")
                            .append(reportByIdItemsResult.getError())
                            .append("\n");
                    continue;
                }

                List<AvitoReportItem> items = reportByIdItemsResult.getResult();
                HashMap<Long, Long> viewsMap = new HashMap<>();
                HashMap<Long, String> viewsDetailsMap = new HashMap<>();
                HashMap<Long, Long> callsMap = new HashMap<>();
                if (withAdditionalProps) {
                    List<String> viewsErrors = getViewsStatistic(authResult.getResult(), userResult.getResult().getId(), items, avitoApiClient, viewsMap, viewsDetailsMap);
                    viewsErrors.forEach(error -> errors.append(avitoApiClient.getClientId())
                            .append(": ")
                            .append(error));
                    List<String> callsErrors = getCallsStatistic(authResult.getResult(), userResult.getResult().getId(), avitoApiClient, callsMap);
                    callsErrors.forEach(error -> errors.append(avitoApiClient.getClientId())
                            .append(": ")
                            .append(error));
                }
                List<FeedStatistic> feedStatistics = items.stream()
                        .map(ad -> new FeedStatistic(ad.getAdId(),
                                ad.getUrl(),
                                ad.getMessages() != null
                                        ? ad.getMessages().stream()
                                        .filter(m -> m.getType().equals(ERROR_AD_TYPE)).map(AvitoReportMessage::getDescription)
                                        .collect(Collectors.toList())
                                        : null,
                                ad.getAvitoId() != null
                                        ? viewsMap.get(ad.getAvitoId())
                                        : null,
                                ad.getAvitoId() != null
                                        ? viewsDetailsMap.get(ad.getAvitoId())
                                        : null,
                                ad.getAvitoId() != null
                                        ? callsMap.get(ad.getAvitoId())
                                        : null))
                        .collect(Collectors.toList());

                resultList.addAll(feedStatistics);
            } catch (RuntimeException ex) {
                logger.error("Error avito getFeedStatistics with clientId={} : {}",
                        avitoApiClient.getClientId(),
                        ex);
                errors.append(avitoApiClient.getClientId())
                        .append(": Ошибка в методе получения статистики по фидам: ")
                        .append(ex.getMessage())
                        .append("\n");
            }
        }
        return StatisticResult.ok(resultList, errors.toString());
    }

    private List<String> getCallsStatistic(String token, Long userId, AvitoApiClient avitoApiClient, HashMap<Long, Long> callsMap) {
        List<String> errors = new ArrayList<>();
        addCallsStatistic(token, userId, avitoApiClient, avitoApiClient.getFirstDay(), errors, callsMap);
        return errors;
    }

    private void addCallsStatistic(String token, Long userId, AvitoApiClient avitoApiClient, LocalDate dateFrom, List<String> errors, HashMap<Long, Long> callsMap) {
        LocalDate dateTo = dateFrom.plusDays(INTERVAL);
        CallsStatisticRequestBody bodyObject = new CallsStatisticRequestBody(dateFrom, dateTo);
        try {
            String body = mapper.writeValueAsString(bodyObject);
            Result<PostStatisticResponse, String> statResult = avitoApiClient.getCallsStatistics(token, userId, body);
            if (statResult.isSuccess()) {
                statResult.getResult().getResult().getItems().forEach(t -> {
                    Long count = t.getDays().stream().map(ItemCallStat::getCalls).reduce(0L, Long::sum);
                    if (callsMap.containsKey(t.getItemId())) {
                        callsMap.replace(t.getItemId(), callsMap.get(t.getItemId()) + (count == null ? 0L : count));
                    } else {
                        callsMap.put(t.getItemId(), count == null ? 0L : count);
                    }
                });
            } else {
                logger.error("Error avito api getCallsStatistics: {}", statResult.getError());
                errors.add("Ошибка при получении ответа по статистике звонков: " + statResult.getError());
            }
        } catch (Exception e) {
            logger.error("Error in avito addCallsStatistic method: ", e);
            errors.add("Ошибка при получении статистики по звонкам");
        }
        if (dateTo.plusDays(1)
                .isBefore(LocalDate.now())) {
            addCallsStatistic(token, userId, avitoApiClient, dateTo.plusDays(1), errors, callsMap);
        }
    }

    private List<String> getViewsStatistic(String token, Long userId, List<AvitoReportItem> offerList, AvitoApiClient avitoApiClient, HashMap<Long, Long> viewsMap, HashMap<Long, String> viewsDetailsMap) {
        List<String> errors = new ArrayList<>();
        List<Long> offerIdList = offerList.stream()
                .filter(ad -> ad.getAvitoId() != null)
                .map(ad -> ad.getAvitoId())
                .collect(Collectors.toList());
        List<List<Long>> offerIdLists = ListUtils.partition(offerIdList, MAX_ID_COUNT);
        for (List<Long> list : offerIdLists) {
            Result<Void, String> addViewsStatistic = addViewsStatistic(avitoApiClient, token, userId, list, viewsMap, viewsDetailsMap);
            if (addViewsStatistic.isError()) {
                errors.add(addViewsStatistic.getError());
            }
        }
        return errors;
    }

    private Result<Void, String> addViewsStatistic(AvitoApiClient avitoApiClient, String token, Long userId,
                                                   List<Long> idList, HashMap<Long, Long> viewsMap, HashMap<Long, String> viewsDetailsMap) {
        ViewStatisticRequestBody bodyObject = new ViewStatisticRequestBody(idList);
        try {
            String body = mapper.writeValueAsString(bodyObject);
            Result<PostStatisticResponse, String> statResult = avitoApiClient.getViewsStatistics(token, userId, body);
            if (statResult.isSuccess()) {
                statResult.getResult().getResult().getItems().forEach(t -> {
                    Long count = t.getStats().stream().map(ItemStat::getUniqViews).reduce(0L, Long::sum);
                    viewsMap.put(t.getItemId(), count);
                    List<String> viewsDetails = new ArrayList<>();
                    t.getStats().forEach(v -> viewsDetails.add( "{\"month\": " + v.getDate().getMonth() + ", \"value\": " + v.getUniqViews() + "}"));
                    viewsDetailsMap.put(t.getItemId(), "[ " + String.join(", ", viewsDetails) + " ]");
                });
            } else {
                logger.error("Error avito api getViewsStatistics: {}", statResult.getError());
                return Result.error("Ошибка при получении ответа по статистике просмотров");
            }
        } catch (IOException e) {
            logger.error("Can't create RequestBody ", e);
            return Result.error("Ошибка при получении статистики просмотров: " + e.getMessage());
        }
        return Result.ok();
    }
}
