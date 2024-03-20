
package com.baltinfo.radius.feed.avito.api.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ad {

    @JsonProperty("ad_id")
    private String adId;
    @JsonProperty("avito_date_end")
    private String avitoDateEnd;
    @JsonProperty("avito_id")
    private String avitoId;
    @JsonProperty("statuses")
    private Statuses statuses;
    @JsonProperty("url")
    private String url;
    @JsonProperty("messages")
    private List<Messages> messages;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ad_id")
    public String getAdId() {
        return adId;
    }

    @JsonProperty("ad_id")
    public void setAdId(String adId) {
        this.adId = adId;
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
    public String getAvitoId() {
        return avitoId;
    }

    @JsonProperty("avito_id")
    public void setAvitoId(String avitoId) {
        this.avitoId = avitoId;
    }

    @JsonProperty("statuses")
    public Statuses getStatuses() {
        return statuses;
    }

    @JsonProperty("statuses")
    public void setStatuses(Statuses statuses) {
        this.statuses = statuses;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("messages")
    public List<Messages> getMessages() {
        return messages;
    }

    @JsonProperty("messages")
    public void setMessages(List<Messages> messages) {
        this.messages = messages;
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
