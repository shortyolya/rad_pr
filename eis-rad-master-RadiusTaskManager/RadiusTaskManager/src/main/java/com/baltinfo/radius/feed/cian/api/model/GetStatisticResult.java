package com.baltinfo.radius.feed.cian.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetStatisticResult {

    private final List<OfferStatistic> statistics;

    @JsonCreator
    public GetStatisticResult(@JsonProperty("statistics") List<OfferStatistic> statistics) {
        this.statistics = statistics;
    }

    public List<OfferStatistic> getStatistics() {
        return statistics;
    }
}
