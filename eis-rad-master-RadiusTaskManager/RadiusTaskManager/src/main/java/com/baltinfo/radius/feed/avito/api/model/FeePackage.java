
package com.baltinfo.radius.feed.avito.api.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeePackage {

    @JsonProperty("count")
    private Integer count;
    @JsonProperty("count_left")
    private Integer countLeft;
    @JsonProperty("description")
    private String description;
    @JsonProperty("fee_package_id")
    private String feePackageId;
    @JsonProperty("finish_time")
    private String finishTime;
    @JsonProperty("is_active")
    private Boolean isActive;
    @JsonProperty("used")
    private Integer used;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    @JsonProperty("count_left")
    public Integer getCountLeft() {
        return countLeft;
    }

    @JsonProperty("count_left")
    public void setCountLeft(Integer countLeft) {
        this.countLeft = countLeft;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("fee_package_id")
    public String getFeePackageId() {
        return feePackageId;
    }

    @JsonProperty("fee_package_id")
    public void setFeePackageId(String feePackageId) {
        this.feePackageId = feePackageId;
    }

    @JsonProperty("finish_time")
    public String getFinishTime() {
        return finishTime;
    }

    @JsonProperty("finish_time")
    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    @JsonProperty("is_active")
    public Boolean getIsActive() {
        return isActive;
    }

    @JsonProperty("is_active")
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @JsonProperty("used")
    public Integer getUsed() {
        return used;
    }

    @JsonProperty("used")
    public void setUsed(Integer used) {
        this.used = used;
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
