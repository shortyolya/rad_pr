package com.baltinfo.radius.feed.jcat.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Suvorina Aleksandra
 * @since 30.03.2021
 */
public class ReportUrl {

    @JsonProperty("reportUrl")
    private final String reportUrl;

    @JsonCreator
    public ReportUrl(@JsonProperty("reportUrl") String reportUrl) {
        this.reportUrl = reportUrl;
    }

    @JsonProperty("reportUrl")
    public String getReportUrl() {
        return reportUrl;
    }

    @Override
    public String toString() {
        return "ReportUrl{" +
                "reportUrl='" + reportUrl + '\'' +
                '}';
    }
}
