package com.baltinfo.radius.feed.cian.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Igor Lapenok
 * @since 10.02.2021
 */
public class GetOrderResponse {
    @JsonProperty("operationId")
    private final String operationId;
    @JsonProperty("result")
    private final GetOrderResult result;

    @JsonCreator
    public GetOrderResponse(@JsonProperty("operationId") String operationId,
                            @JsonProperty("result") GetOrderResult result) {
        this.operationId = operationId;
        this.result = result;
    }

    @JsonProperty("operationId")
    public String getOperationId() {
        return operationId;
    }

    @JsonProperty("result")
    public GetOrderResult getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "GetOrderResponse{" +
                "operationId='" + operationId + '\'' +
                ", result=" + result +
                '}';
    }
}
