
package com.baltinfo.radius.yandex.model;

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
@JsonPropertyOrder({
    "ids",
    "preset",
    "dimensions",
    "metrics",
    "sort",
    "date1",
    "date2",
    "limit",
    "offset",
    "group",
    "auto_group_size",
    "attr_name",
    "quantile",
    "offline_window",
    "attribution",
    "currency",
    "adfox_event_id"
})
public class Query {

    @JsonProperty("ids")
    private List<Integer> ids = null;
    @JsonProperty("preset")
    private String preset;
    @JsonProperty("dimensions")
    private List<String> dimensions = null;
    @JsonProperty("metrics")
    private List<String> metrics = null;
    @JsonProperty("sort")
    private List<String> sort = null;
    @JsonProperty("date1")
    private String date1;
    @JsonProperty("date2")
    private String date2;
    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("group")
    private String group;
    @JsonProperty("auto_group_size")
    private String autoGroupSize;
    @JsonProperty("attr_name")
    private String attrName;
    @JsonProperty("quantile")
    private String quantile;
    @JsonProperty("offline_window")
    private String offlineWindow;
    @JsonProperty("attribution")
    private String attribution;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("adfox_event_id")
    private String adfoxEventId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ids")
    public List<Integer> getIds() {
        return ids;
    }

    @JsonProperty("ids")
    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    @JsonProperty("preset")
    public String getPreset() {
        return preset;
    }

    @JsonProperty("preset")
    public void setPreset(String preset) {
        this.preset = preset;
    }

    @JsonProperty("dimensions")
    public List<String> getDimensions() {
        return dimensions;
    }

    @JsonProperty("dimensions")
    public void setDimensions(List<String> dimensions) {
        this.dimensions = dimensions;
    }

    @JsonProperty("metrics")
    public List<String> getMetrics() {
        return metrics;
    }

    @JsonProperty("metrics")
    public void setMetrics(List<String> metrics) {
        this.metrics = metrics;
    }

    @JsonProperty("sort")
    public List<String> getSort() {
        return sort;
    }

    @JsonProperty("sort")
    public void setSort(List<String> sort) {
        this.sort = sort;
    }

    @JsonProperty("date1")
    public String getDate1() {
        return date1;
    }

    @JsonProperty("date1")
    public void setDate1(String date1) {
        this.date1 = date1;
    }

    @JsonProperty("date2")
    public String getDate2() {
        return date2;
    }

    @JsonProperty("date2")
    public void setDate2(String date2) {
        this.date2 = date2;
    }

    @JsonProperty("limit")
    public Integer getLimit() {
        return limit;
    }

    @JsonProperty("limit")
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @JsonProperty("offset")
    public Integer getOffset() {
        return offset;
    }

    @JsonProperty("offset")
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @JsonProperty("group")
    public String getGroup() {
        return group;
    }

    @JsonProperty("group")
    public void setGroup(String group) {
        this.group = group;
    }

    @JsonProperty("auto_group_size")
    public String getAutoGroupSize() {
        return autoGroupSize;
    }

    @JsonProperty("auto_group_size")
    public void setAutoGroupSize(String autoGroupSize) {
        this.autoGroupSize = autoGroupSize;
    }

    @JsonProperty("attr_name")
    public String getAttrName() {
        return attrName;
    }

    @JsonProperty("attr_name")
    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    @JsonProperty("quantile")
    public String getQuantile() {
        return quantile;
    }

    @JsonProperty("quantile")
    public void setQuantile(String quantile) {
        this.quantile = quantile;
    }

    @JsonProperty("offline_window")
    public String getOfflineWindow() {
        return offlineWindow;
    }

    @JsonProperty("offline_window")
    public void setOfflineWindow(String offlineWindow) {
        this.offlineWindow = offlineWindow;
    }

    @JsonProperty("attribution")
    public String getAttribution() {
        return attribution;
    }

    @JsonProperty("attribution")
    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("adfox_event_id")
    public String getAdfoxEventId() {
        return adfoxEventId;
    }

    @JsonProperty("adfox_event_id")
    public void setAdfoxEventId(String adfoxEventId) {
        this.adfoxEventId = adfoxEventId;
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
