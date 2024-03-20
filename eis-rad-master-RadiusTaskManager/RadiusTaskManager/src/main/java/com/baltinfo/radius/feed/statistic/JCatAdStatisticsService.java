package com.baltinfo.radius.feed.statistic;

import com.baltinfo.radius.db.constants.JCatStatisticsUrl;
import com.baltinfo.radius.feed.jcat.api.client.JCatApiClient;
import com.baltinfo.radius.feed.jcat.api.model.Report;
import com.baltinfo.radius.feed.jcat.api.model.ReportUrl;
import com.baltinfo.radius.feed.jcat.api.model.Views;
import com.baltinfo.radius.feed.utils.StatisticResult;
import com.baltinfo.radius.utils.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Suvorina Aleksandra
 * @since 30.03.2021
 */
public class JCatAdStatisticsService implements FeedStatisticsService {

    private final Logger logger = LoggerFactory.getLogger(JCatAdStatisticsService.class);

    private static final String REPORT_URL_ERROR = "Не распознан тип парсера.";
    public static Pattern UIZ_REGEX = Pattern.compile("uiz - (\\d*)");

    private final JCatApiClient jCatApiClient;
    private final XmlMapper xmlMapper;

    public JCatAdStatisticsService(JCatApiClient jCatApiClient) {
        this.jCatApiClient = jCatApiClient;
        xmlMapper = new XmlMapper();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        xmlMapper.setDateFormat(df);
        xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Override
    public StatisticResult<List<FeedStatistic>, String> getFeedStatistics(boolean withAdditionalProps) {
        try {
            Result<List<ReportUrl>, String> reportUrlResult = jCatApiClient.getActiveReports();
            if (reportUrlResult.isError()) {
                logger.error("Error JCat api getActiveReports: {}", reportUrlResult.getError());
                return StatisticResult.error("Ошибка при получении списка активных XML-парсеров JCat: " + reportUrlResult.getError());
            }

            List<ReportUrl> reportUrls = reportUrlResult.getResult().stream()
                    .filter(url -> !url.getReportUrl().equals(REPORT_URL_ERROR))
                    .collect(Collectors.toList());

            Map<JCatStatisticsUrl, List<ReportUrl>> reportUrlMap = new HashMap<JCatStatisticsUrl, List<ReportUrl>>() {{
                Arrays.stream(JCatStatisticsUrl.values()).forEach(item -> put(item, new ArrayList<>()));
            }};

            for (ReportUrl reportUrl : reportUrls) {
                String url = reportUrl.getReportUrl().substring(0, reportUrl.getReportUrl().indexOf("report_p") + 8);
                JCatStatisticsUrl jCatStatisticsUrl = JCatStatisticsUrl.getByReportUrl(url);
                if (reportUrlMap.containsKey(jCatStatisticsUrl))
                    reportUrlMap.get(jCatStatisticsUrl).add(reportUrl);
            }

            List<FeedStatistic> feedStatistics = new ArrayList<>();
            for (Map.Entry<JCatStatisticsUrl, List<ReportUrl>> entry : reportUrlMap.entrySet()) {
                feedStatistics.addAll(createFeedStatisticListFromReportUrlList(
                        getSortedReportUrlList(entry.getValue(), entry.getKey().getReportUrl()),
                        entry.getKey().getBaseUrl(),
                        withAdditionalProps));
            }
            return StatisticResult.ok(feedStatistics);
        } catch (RuntimeException ex) {
            logger.error("Error JCat getFeedStatistics", ex);
            return StatisticResult.error("Ошибка при получении статистики по объявлениям JCat: " + ex.getMessage());
        }
    }

    private List<FeedStatistic> createFeedStatisticListFromReportUrlList(List<ReportUrl> reportUrlList, String baseUrl, boolean withAdditionalProps) {
        List<FeedStatistic> feedStatisticList = new ArrayList<>();
        for (ReportUrl reportUrl : reportUrlList) {
            Result<String, String> reportXml = jCatApiClient.getReportXml(reportUrl.getReportUrl());
            if (reportXml.isError() || reportXml.getResult() == null || reportXml.getResult().isEmpty()) {
                logger.error("Error get report by url {}", reportUrl.getReportUrl());
                continue;
            }
            Result<Report, Void> reportResult = parseReportXml(reportXml.getResult());
            if (reportResult.isError()) {
                continue;
            }
            Report report = reportResult.getResult();
            if (report.getAdded().getCount() != 0) {
                report.getAdded().getItems().forEach(item -> {
                    FeedStatistic existed = feedStatisticList.stream()
                            .filter(fs -> fs.getObjCode().equals(item.getId()))
                            .findFirst()
                            .orElse(null);
                    Long viewsCount = null;
                    if (withAdditionalProps) {
                        Result<Views, String> viewsResult = jCatApiClient.getViews(item.getJcatId());
                        if (viewsResult.isSuccess()) {
                            viewsCount = viewsResult.getResult().getSummary();
                        }
                    }
                    if (existed != null) {
                        feedStatisticList.remove(existed);
                        feedStatisticList.add(new FeedStatistic(item.getId(), getUrl(baseUrl, item.getJcatId()), existed.getAdError(), viewsCount));
                    } else {
                        feedStatisticList.add(new FeedStatistic(item.getId(), getUrl(baseUrl, item.getJcatId()), viewsCount));
                    }
                });
            }
            if (report.getMissing().getCount() != 0) {
                report.getMissing().getItems().forEach(item -> {

                    FeedStatistic existed = feedStatisticList.stream()
                            .filter(fs -> fs.getObjCode().equals(item.getId()))
                            .findFirst()
                            .orElse(null);
                    if (existed != null) {
                        feedStatisticList.remove(existed);
                        feedStatisticList.add(new FeedStatistic(item.getId(), existed.getAdUrl(), item.getErrors()));
                    } else {
                        List<String> newErrorList = new ArrayList<>();
                        String formedUrl = getUrlFromError(item.getErrors(), newErrorList, baseUrl);
                        if (formedUrl != null) {
                            feedStatisticList.add(new FeedStatistic(item.getId(), formedUrl, newErrorList));
                        } else {
                            feedStatisticList.add(new FeedStatistic(item.getId(), item.getErrors()));
                        }
                    }
                });
            }
        }
        return feedStatisticList;
    }

    private String getUrl(String baseUrl, String jcatId) {
        return baseUrl + jcatId;
    }

    private Result<Report, Void> parseReportXml(String xml) {
        try {
            return Result.ok(xmlMapper.readValue(xml, Report.class));
        } catch (JsonProcessingException e) {
            logger.error("Error parse xml report = {}", xml, e);
            return Result.error();
        }
    }

    private String getUrlFromError(List<String> list, List<String> newErrorList, String baseUrl) {
        newErrorList.clear();
        for (String str : list) {
            Matcher match = UIZ_REGEX.matcher(str);
            if (match.find()) {
                newErrorList.addAll(list);
                newErrorList.removeIf(error -> error.equals(str));
                return getUrl(baseUrl, match.group(1));
            }
        }
        return null;
    }

    private List<ReportUrl> getSortedReportUrlList(List<ReportUrl> reportUrlList, String baseUrl) {
        List<ReportUrl> sortedReportUrlList = reportUrlList.stream()
                .filter(url -> url.getReportUrl().contains(baseUrl))
                .sorted(Comparator.comparingLong(url ->
                        Long.parseLong(url.getReportUrl()
                                .replace(baseUrl, "").replace("/", ""))))
                .collect(Collectors.toList());
        Collections.reverse(sortedReportUrlList);
        return sortedReportUrlList;
    }
}
