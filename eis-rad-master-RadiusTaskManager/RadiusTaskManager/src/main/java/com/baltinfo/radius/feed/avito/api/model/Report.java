
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
public class Report {

    @JsonProperty("ads")
    private List<Ad> ads = null;
    @JsonProperty("finished_at")
    private String finishedAt;
    @JsonProperty("started_at")
    private String startedAt;
    @JsonProperty("customer_name")
    private String customerName;
    @JsonProperty("fee_packages")
    private List<FeePackage> feePackages = null;
    @JsonProperty("generated_at")
    private String generatedAt;
    @JsonProperty("status")
    private String status;
    @JsonProperty("vas")
    private List<Va> vas = null;
    @JsonProperty("version")
    private String version;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ads")
    public List<Ad> getAds() {
        return ads;
    }

    @JsonProperty("ads")
    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    @JsonProperty("finished_at")
    public String getFinishedAt() {
        return finishedAt;
    }

    @JsonProperty("finished_at")
    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }

    @JsonProperty("started_at")
    public String getStartedAt() {
        return startedAt;
    }

    @JsonProperty("started_at")
    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    @JsonProperty("customer_name")
    public String getCustomerName() {
        return customerName;
    }

    @JsonProperty("customer_name")
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @JsonProperty("fee_packages")
    public List<FeePackage> getFeePackages() {
        return feePackages;
    }

    @JsonProperty("fee_packages")
    public void setFeePackages(List<FeePackage> feePackages) {
        this.feePackages = feePackages;
    }

    @JsonProperty("generated_at")
    public String getGeneratedAt() {
        return generatedAt;
    }

    @JsonProperty("generated_at")
    public void setGeneratedAt(String generatedAt) {
        this.generatedAt = generatedAt;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("vas")
    public List<Va> getVas() {
        return vas;
    }

    @JsonProperty("vas")
    public void setVas(List<Va> vas) {
        this.vas = vas;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
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
