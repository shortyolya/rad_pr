package com.baltinfo.radius.feed.avito.api.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Igor Lapenok
 * @since 15.11.2022
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ad_id",
        "applied_vas",
        "avito_date_end",
        "avito_id",
        "avito_status",
        "messages",
        "section",
        "url"
})
public class AvitoReportItem {

    @JsonProperty("ad_id")
    private String adId;
    @JsonProperty("applied_vas")
    private List<AvitoReportAppliedVa> appliedVas = null;
    @JsonProperty("avito_date_end")
    private String avitoDateEnd;
    @JsonProperty("avito_id")
    private Long avitoId;
    @JsonProperty("avito_status")
    private String avitoStatus;
    @JsonProperty("messages")
    private List<AvitoReportMessage> messages = null;
    @JsonProperty("section")
    private AvitoReportSection section;
    @JsonProperty("url")
    private String url;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("ad_id")
    public String getAdId() {
        return adId;
    }

    @JsonProperty("ad_id")
    public void setAdId(String adId) {
        this.adId = adId;
    }

    @JsonProperty("applied_vas")
    public List<AvitoReportAppliedVa> getAppliedVas() {
        return appliedVas;
    }

    @JsonProperty("applied_vas")
    public void setAppliedVas(List<AvitoReportAppliedVa> appliedVas) {
        this.appliedVas = appliedVas;
    }

    @JsonProperty("avito_date_end")
    public String getAvitoDateEnd() {
        return avitoDateEnd;
    }

    @JsonProperty("avito_date_end")
    public void setAvitoDateEnd(String avitoDateEnd) {
        this.avitoDateEnd = avitoDateEnd;
    }

    @JsonProperty("avito_id")
    public Long getAvitoId() {
        return avitoId;
    }

    @JsonProperty("avito_id")
    public void setAvitoId(Long avitoId) {
        this.avitoId = avitoId;
    }

    @JsonProperty("avito_status")
    public String getAvitoStatus() {
        return avitoStatus;
    }

    @JsonProperty("avito_status")
    public void setAvitoStatus(String avitoStatus) {
        this.avitoStatus = avitoStatus;
    }

    @JsonProperty("messages")
    public List<AvitoReportMessage> getMessages() {
        return messages;
    }

    @JsonProperty("messages")
    public void setMessages(List<AvitoReportMessage> messages) {
        this.messages = messages;
    }

    @JsonProperty("section")
    public AvitoReportSection getSection() {
        return section;
    }

    @JsonProperty("section")
    public void setSection(AvitoReportSection section) {
        this.section = section;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
