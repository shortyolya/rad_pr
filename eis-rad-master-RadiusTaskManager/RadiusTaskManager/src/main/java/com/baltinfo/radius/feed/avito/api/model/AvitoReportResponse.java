
package com.baltinfo.radius.feed.avito.api.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "items",
        "meta",
        "report_id"
})
public class AvitoReportResponse {

    @JsonProperty("items")
    private List<AvitoReportItem> items = null;
    @JsonProperty("meta")
    private Meta meta;
    @JsonProperty("report_id")
    private Integer reportId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("items")
    public List<AvitoReportItem> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<AvitoReportItem> items) {
        this.items = items;
    }

    @JsonProperty("meta")
    public Meta getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @JsonProperty("report_id")
    public Integer getReportId() {
        return reportId;
    }

    @JsonProperty("report_id")
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
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
