
package com.baltinfo.radius.yandex.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "status",
        "code_status",
        "site"
})
public class Counter {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("status")
    private String status;
    @JsonProperty("code_status")
    private String codeStatus;
    @JsonProperty("site")
    private String site;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("code_status")
    public String getCodeStatus() {
        return codeStatus;
    }

    @JsonProperty("code_status")
    public void setCodeStatus(String codeStatus) {
        this.codeStatus = codeStatus;
    }

    @JsonProperty("site")
    public String getSite() {
        return site;
    }

    @JsonProperty("site")
    public void setSite(String site) {
        this.site = site;
    }
}
