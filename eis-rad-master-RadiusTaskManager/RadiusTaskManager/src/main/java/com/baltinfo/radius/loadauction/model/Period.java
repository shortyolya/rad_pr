package com.baltinfo.radius.loadauction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


/**
 * <p>
 *     Java class для десериализация xml-файла
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 23.12.2019
 */
public class Period {
    @JacksonXmlProperty(localName = "Number_period")
    @JsonProperty("numberPeriod")
    private final Integer periodNumber;
    @JacksonXmlProperty(localName = "Start_date_period")
    @JsonProperty("startDatePeriod")
    private final String startDatePeriod;
    @JacksonXmlProperty(localName = "Time_auction")
    @JsonProperty("timeAuction")
    private final String timeAuction;
    @JacksonXmlProperty(localName = "End_date_period")
    @JsonProperty("endDatePeriod")
    private final String endDatePeriod;
    @JacksonXmlProperty(localName = "End_time_period")
    @JsonProperty("endTimePeriod")
    private final String endTimePeriod;
    @JacksonXmlProperty(localName = "Start_request_period")
    @JsonProperty("startRequestPeriod")
    private final String startRequestPeriod;
    @JacksonXmlProperty(localName = "End_request_period")
    @JsonProperty("endRequestPeriod")
    private final String endRequestPeriod;
    @JacksonXmlProperty(localName = "End_time_request_period")
    @JsonProperty("endTimeRequestPeriod")
    private final String endTimeRequestPeriod;

    public Period(@JacksonXmlProperty(localName = "Number_period")
                  @JsonProperty("numberPeriod") Integer periodNumber,
                  @JacksonXmlProperty(localName = "Start_date_period")
                  @JsonProperty("startDatePeriod") String startDatePeriod,
                  @JacksonXmlProperty(localName = "Time_auction")
                  @JsonProperty("timeAuction") String timeAuction,
                  @JacksonXmlProperty(localName = "End_date_period")
                  @JsonProperty("endDatePeriod") String endDatePeriod,
                  @JacksonXmlProperty(localName = "End_time_period")
                  @JsonProperty("endTimePeriod") String endTimePeriod,
                  @JacksonXmlProperty(localName = "Start_request_period")
                  @JsonProperty("startRequestPeriod") String startRequestPeriod,
                  @JacksonXmlProperty(localName = "End_request_period")
                  @JsonProperty("endRequestPeriod") String endRequestPeriod,
                  @JacksonXmlProperty(localName = "End_time_request_period")
                  @JsonProperty("endTimeRequestPeriod") String endTimeRequestPeriod) {
        this.periodNumber = periodNumber;
        this.startDatePeriod = startDatePeriod;
        this.timeAuction = timeAuction;
        this.endDatePeriod = endDatePeriod;
        this.endTimePeriod = endTimePeriod;
        this.startRequestPeriod = startRequestPeriod;
        this.endRequestPeriod = endRequestPeriod;
        this.endTimeRequestPeriod = endTimeRequestPeriod;
    }

    public Integer getPeriodNumber() {
        return periodNumber;
    }

    public String getStartDatePeriod() {
        return startDatePeriod;
    }

    public String getTimeAuction() {
        return timeAuction;
    }

    public String getEndDatePeriod() {
        return endDatePeriod;
    }

    public String getEndTimePeriod() {
        return endTimePeriod;
    }

    public String getStartRequestPeriod() {
        return startRequestPeriod;
    }

    public String getEndRequestPeriod() {
        return endRequestPeriod;
    }

    public String getEndTimeRequestPeriod() {
        return endTimeRequestPeriod;
    }
}
