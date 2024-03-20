package com.baltinfo.radius.radapi.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StringDto {
    @JsonProperty(value = "value")
    private String value;


    @JsonCreator
    public StringDto(@JsonProperty(value = "value") String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}