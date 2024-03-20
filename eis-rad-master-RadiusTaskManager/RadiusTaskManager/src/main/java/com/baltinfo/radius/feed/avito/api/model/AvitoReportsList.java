package com.baltinfo.radius.feed.avito.api.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AvitoReportsList {

    @JsonProperty("reports")
    private List<ReportFromList> reports;

    @JsonProperty("meta")
    private Meta meta;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public List<ReportFromList> getReports() {
        return reports;
    }

    public void setReports(List<ReportFromList> reports) {
        this.reports = reports;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
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
