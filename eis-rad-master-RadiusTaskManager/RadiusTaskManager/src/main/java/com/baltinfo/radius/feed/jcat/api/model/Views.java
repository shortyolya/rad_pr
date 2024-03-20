package com.baltinfo.radius.feed.jcat.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Views {

    @JsonProperty("summary")
    private Long summary;

    public Long getSummary() {
        return summary;
    }

    public void setSummary(Long summary) {
        this.summary = summary;
    }
}
