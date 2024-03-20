package com.baltinfo.radius.feed.avito.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class CallsStatisticRequestBody {

    @JsonProperty("dateFrom")
    private String dateFrom;
    @JsonProperty("dateTo")
    private String dateTo;

    public CallsStatisticRequestBody(LocalDate dateFrom, LocalDate daysTo) {
        this.dateFrom = String.valueOf(dateFrom);
        this.dateTo = String.valueOf(daysTo);
    }

    public String getDateTo() {
        return dateTo;
    }
}
