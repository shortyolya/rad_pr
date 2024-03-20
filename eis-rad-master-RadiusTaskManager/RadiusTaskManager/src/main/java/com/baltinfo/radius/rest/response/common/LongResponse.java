package com.baltinfo.radius.rest.response.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * <p>
 * </p>
 *
 * @author Lapenok Igor
 * @since 08.04.2019
 */
public class LongResponse implements Serializable {
    @JsonProperty("value")
    private final Long value;

    @JsonCreator
    public LongResponse(@JsonProperty("value") Long value) {
        this.value = value;
    }

    @JsonProperty("value")
    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "LongResponse{" +
                "value=" + value +
                '}';
    }
}
