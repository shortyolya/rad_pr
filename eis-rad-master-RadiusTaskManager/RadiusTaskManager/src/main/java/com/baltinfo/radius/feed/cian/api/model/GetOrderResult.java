package com.baltinfo.radius.feed.cian.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Igor Lapenok
 * @since 10.02.2021
 */
public class GetOrderResult {
    @JsonProperty("offers")
    private final List<Offer> offers;
    @JsonProperty("message")
    private final String message;
    @JsonProperty("errors")
    private final List<Error> errors;

    @JsonCreator
    public GetOrderResult(@JsonProperty("offers") List<Offer> offers,
                          @JsonProperty("message") String message,
                          @JsonProperty("errors") List<Error> errors) {
        this.offers = offers;
        this.errors = errors;
        this.message = message;
    }

    @JsonProperty("offers")
    public List<Offer> getOffers() {
        return offers;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("errors")
    public List<Error> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "GetOrderResult{" +
                "offers=" + offers +
                ", message='" + message + '\'' +
                ", errors=" + errors +
                '}';
    }
}
