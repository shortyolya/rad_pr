package com.baltinfo.radius.feed.cian.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetStatisticResponse {

    private final String operationId;
    private final GetStatisticResult result;

    @JsonCreator
    public GetStatisticResponse(@JsonProperty("operationId") String operationId,
                                @JsonProperty("result") GetStatisticResult result) {
        this.operationId = operationId;
        this.result = result;
    }

    public String getOperationId() {
        return operationId;
    }

    public GetStatisticResult getResult() {
        return result;
    }
}
