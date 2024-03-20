package com.baltinfo.radius.feed.avito.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostStatisticResponse {
    @JsonProperty("result")
    private PostStatisticResult result;

    public PostStatisticResult getResult() {
        return result;
    }

    public void setResult(PostStatisticResult result) {
        this.result = result;
    }
}
