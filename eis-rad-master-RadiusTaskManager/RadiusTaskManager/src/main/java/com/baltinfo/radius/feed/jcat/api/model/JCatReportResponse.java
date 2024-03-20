package com.baltinfo.radius.feed.jcat.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Suvorina Aleksandra
 * @since 30.03.2021
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data"
})
public class JCatReportResponse {

    @JsonProperty("data")
    private final ReportData data;

    @JsonCreator
    public JCatReportResponse(@JsonProperty("data") ReportData data) {
        this.data = data;
    }

    @JsonProperty("data")
    public ReportData getData() {
        return data;
    }

    @Override
    public String toString() {
        return "JcatReportResponse{" +
                "data=" + data +
                '}';
    }
}
