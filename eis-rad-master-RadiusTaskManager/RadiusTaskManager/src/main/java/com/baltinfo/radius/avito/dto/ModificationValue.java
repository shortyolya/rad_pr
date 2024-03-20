package com.baltinfo.radius.avito.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Igor Lapenok
 * @since 14.09.2020
 */
public class ModificationValue {
    private final String propertyCode;
    private final String code;
    private final String value;

    @JsonCreator
    public ModificationValue(@JsonProperty("propertyCode") String propertyCode,
                             @JsonProperty("code") String code,
                             @JsonProperty("value") String value) {
        this.propertyCode = propertyCode;
        this.code = code;
        this.value = value;
    }

    @JsonProperty("propertyCode")
    public String getPropertyCode() {
        return propertyCode;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ModificationValue{" +
                "propertyCode='" + propertyCode + '\'' +
                ", code='" + code + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
