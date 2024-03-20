package com.baltinfo.radius.feed.cian.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Igor Lapenok
 * @since 10.02.2021
 */
public class Offer {
    @JsonProperty("errors")
    private final List<String> errors;
    @JsonProperty("externalId")
    private final String externalId;
    @JsonProperty("offerId")
    private final Long offerId;
    @JsonProperty("status")
    private final String status; // TODO: convert to enum?
    @JsonProperty("url")
    private final String url;
    @JsonProperty("warnings")
    private final List<String> warnings;

    @JsonCreator
    public Offer(@JsonProperty("errors") List<String> errors,
                 @JsonProperty("externalId") String externalId,
                 @JsonProperty("offerId") Long offerId,
                 @JsonProperty("status") String status,
                 @JsonProperty("url") String url,
                 @JsonProperty("warnings") List<String> warnings) {
        this.errors = errors;
        this.externalId = externalId;
        this.offerId = offerId;
        this.status = status;
        this.url = url;
        this.warnings = warnings;
    }

    @JsonProperty("errors")
    public List<String> getErrors() {
        return errors;
    }

    @JsonProperty("externalId")
    public String getExternalId() {
        return externalId;
    }

    @JsonProperty("offerId")
    public Long getOfferId() {
        return offerId;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("warnings")
    public List<String> getWarnings() {
        return warnings;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "errors=" + errors +
                ", externalId='" + externalId + '\'' +
                ", offerId=" + offerId +
                ", status='" + status + '\'' +
                ", url='" + url + '\'' +
                ", warnings=" + warnings +
                '}';
    }
}
