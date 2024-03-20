package com.baltinfo.radius.feed.cian.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferStatistic {


    private final Long totalViews;

    private final Long phoneShows;

    private final Long offerId;

    @JsonCreator
    public OfferStatistic(@JsonProperty("totalViews") Long totalViews,
                          @JsonProperty("offerId") Long offerId,
                          @JsonProperty("phoneShows") Long phoneShows) {
        this.totalViews = totalViews;
        this.offerId = offerId;
        this.phoneShows = phoneShows;
    }

    public Long getTotalViews() {
        return totalViews;
    }

    public Long getOfferId() {
        return offerId;
    }

    public Long getPhoneShows() {
        return phoneShows;
    }
}
