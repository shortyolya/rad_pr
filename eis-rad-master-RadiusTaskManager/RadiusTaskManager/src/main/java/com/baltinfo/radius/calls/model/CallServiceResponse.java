package com.baltinfo.radius.calls.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Igor Lapenok
 * @since 01.09.2023
 */
public class CallServiceResponse {
    @JsonProperty("count")
    private final String count;
    @JsonProperty("unique_count")
    private final String uniqueCount;

    @JsonCreator
    public CallServiceResponse(@JsonProperty("count") String count,
                               @JsonProperty("unique_count") String uniqueCount) {
        this.count = count;
        this.uniqueCount = uniqueCount;
    }

    @JsonProperty("count")
    public String getCount() {
        return count;
    }

    @JsonProperty("unique_count")
    public String getUniqueCount() {
        return uniqueCount;
    }

    @Override
    public String toString() {
        return "CallServiceResponse{" +
                "count='" + count + '\'' +
                ", uniqueCount='" + uniqueCount + '\'' +
                '}';
    }
}
