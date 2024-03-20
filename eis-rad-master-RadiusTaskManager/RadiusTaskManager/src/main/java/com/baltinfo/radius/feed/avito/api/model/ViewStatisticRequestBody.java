package com.baltinfo.radius.feed.avito.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

public class ViewStatisticRequestBody {
    private static final String UNIQ_VIEWS_FIELD = "uniqViews";
    private static final String GROUPING_PERIOD_MONTH = "month";
    private static final int STATISTIC_PERIOD_IN_DAYS = 269;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            .withZone(ZoneId.systemDefault());

    @JsonProperty("itemIds")
    private List<Long> itemIds;
    @JsonProperty("fields")
    private List<String> fields = Arrays.asList(UNIQ_VIEWS_FIELD);
    @JsonProperty("dateFrom")
    private String dateFrom;
    @JsonProperty("dateTo")
    private String dateTo;
    @JsonProperty("periodGrouping")
    private String periodGrouping;

    public ViewStatisticRequestBody(List<Long> itemIds) {
        this.itemIds = itemIds;
        Instant instant = Instant.now();
        this.dateFrom = formatter.format(instant.minus(STATISTIC_PERIOD_IN_DAYS, ChronoUnit.DAYS));
        this.dateTo = formatter.format(instant);
        this.periodGrouping = GROUPING_PERIOD_MONTH;
    }

    public List<Long> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<Long> itemIds) {
        this.itemIds = itemIds;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getPeriodGrouping() {
        return periodGrouping;
    }

    public void setPeriodGrouping(String periodGrouping) {
        this.periodGrouping = periodGrouping;
    }
}
