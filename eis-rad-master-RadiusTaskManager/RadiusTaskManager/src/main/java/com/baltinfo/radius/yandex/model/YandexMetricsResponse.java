
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
    "query",
    "data",
    "total_rows",
    "total_rows_rounded",
    "sampled",
    "contains_sensitive_data",
    "sample_share",
    "sample_size",
    "sample_space",
    "data_lag",
    "totals",
    "min",
    "max"
})
public class YandexMetricsResponse {

    @JsonProperty("query")
    private Query query;
    @JsonProperty("data")
    private List<Datum> data = null;
    @JsonProperty("total_rows")
    private Integer totalRows;
    @JsonProperty("total_rows_rounded")
    private Boolean totalRowsRounded;
    @JsonProperty("sampled")
    private Boolean sampled;
    @JsonProperty("contains_sensitive_data")
    private Boolean containsSensitiveData;
    @JsonProperty("sample_share")
    private Double sampleShare;
    @JsonProperty("sample_size")
    private Integer sampleSize;
    @JsonProperty("sample_space")
    private Integer sampleSpace;
    @JsonProperty("data_lag")
    private Integer dataLag;
    @JsonProperty("totals")
    private List<Double> totals = null;
    @JsonProperty("min")
    private List<Double> min = null;
    @JsonProperty("max")
    private List<Double> max = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("query")
    public Query getQuery() {
        return query;
    }

    @JsonProperty("query")
    public void setQuery(Query query) {
        this.query = query;
    }

    @JsonProperty("data")
    public List<Datum> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Datum> data) {
        this.data = data;
    }

    @JsonProperty("total_rows")
    public Integer getTotalRows() {
        return totalRows;
    }

    @JsonProperty("total_rows")
    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    @JsonProperty("total_rows_rounded")
    public Boolean getTotalRowsRounded() {
        return totalRowsRounded;
    }

    @JsonProperty("total_rows_rounded")
    public void setTotalRowsRounded(Boolean totalRowsRounded) {
        this.totalRowsRounded = totalRowsRounded;
    }

    @JsonProperty("sampled")
    public Boolean getSampled() {
        return sampled;
    }

    @JsonProperty("sampled")
    public void setSampled(Boolean sampled) {
        this.sampled = sampled;
    }

    @JsonProperty("contains_sensitive_data")
    public Boolean getContainsSensitiveData() {
        return containsSensitiveData;
    }

    @JsonProperty("contains_sensitive_data")
    public void setContainsSensitiveData(Boolean containsSensitiveData) {
        this.containsSensitiveData = containsSensitiveData;
    }

    @JsonProperty("sample_share")
    public Double getSampleShare() {
        return sampleShare;
    }

    @JsonProperty("sample_share")
    public void setSampleShare(Double sampleShare) {
        this.sampleShare = sampleShare;
    }

    @JsonProperty("sample_size")
    public Integer getSampleSize() {
        return sampleSize;
    }

    @JsonProperty("sample_size")
    public void setSampleSize(Integer sampleSize) {
        this.sampleSize = sampleSize;
    }

    @JsonProperty("sample_space")
    public Integer getSampleSpace() {
        return sampleSpace;
    }

    @JsonProperty("sample_space")
    public void setSampleSpace(Integer sampleSpace) {
        this.sampleSpace = sampleSpace;
    }

    @JsonProperty("data_lag")
    public Integer getDataLag() {
        return dataLag;
    }

    @JsonProperty("data_lag")
    public void setDataLag(Integer dataLag) {
        this.dataLag = dataLag;
    }

    @JsonProperty("totals")
    public List<Double> getTotals() {
        return totals;
    }

    @JsonProperty("totals")
    public void setTotals(List<Double> totals) {
        this.totals = totals;
    }

    @JsonProperty("min")
    public List<Double> getMin() {
        return min;
    }

    @JsonProperty("min")
    public void setMin(List<Double> min) {
        this.min = min;
    }

    @JsonProperty("max")
    public List<Double> getMax() {
        return max;
    }

    @JsonProperty("max")
    public void setMax(List<Double> max) {
        this.max = max;
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
