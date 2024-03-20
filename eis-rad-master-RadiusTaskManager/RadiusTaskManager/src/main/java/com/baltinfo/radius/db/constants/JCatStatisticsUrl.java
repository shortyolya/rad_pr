package com.baltinfo.radius.db.constants;

import java.util.Arrays;

/**
 * @author AAA
 * @since 29.12.2021
 */
public enum JCatStatisticsUrl {

    REALTY("https://www.jcat.ru/personal/orders/realty/reports/", "http://xml.jcat.ru/export/avitoparser/report_p"),
    AUTO("https://www.jcat.ru/personal/orders/auto/reports/", "http://xml.jcat.ru/export/avitoautoparser/report_p"),
    GOODS("https://www.jcat.ru/personal/orders/goods/reports/", "http://xml.jcat.ru/export/avitogoodsparser/report_p"),
    BUSINESS("https://www.jcat.ru/personal/orders/business/reports/", "http://xml.jcat.ru/export/avitobusinessparser/report_p");

    private final String baseUrl;
    private final String reportUrl;

    JCatStatisticsUrl(String baseUrl, String reportUrl) {
        this.baseUrl = baseUrl;
        this.reportUrl = reportUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public static JCatStatisticsUrl getByReportUrl(String reportUrl) {
        return Arrays.stream(values())
                .filter(item -> item.reportUrl.equals(reportUrl))
                .findFirst()
                .orElse(null);
    }
}
