package com.baltinfo.radius.dadata.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>
 *     POJO class отражающий возможные адреса
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 09.12.2019
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Suggestion {
    @JsonProperty("value")
    private final String value;
    @JsonProperty("unrestricted_value")
    private final String unrestrictedValue;
    @JsonProperty("data")
    private final Data data;

    @JsonCreator
    public Suggestion(@JsonProperty("value")String value,
                      @JsonProperty("unrestricted_value") String unrestrictedValue,
                      @JsonProperty("data") Data data) {
        this.value = value;
        this.unrestrictedValue = unrestrictedValue;
        this.data = data;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("unrestricted_value")
    public String getUnrestrictedValue() {
        return unrestrictedValue;
    }

    @JsonProperty("data")
    public Data getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Suggestion{" +
                "value='" + value + '\'' +
                ", unrestrictedValue='" + unrestrictedValue + '\'' +
                ", data=" + data +
                '}';
    }
}