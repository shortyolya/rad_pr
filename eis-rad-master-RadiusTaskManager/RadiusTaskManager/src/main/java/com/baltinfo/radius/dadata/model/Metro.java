package com.baltinfo.radius.dadata.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 11.12.2019
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Metro {
    @JsonProperty("distance")
    private final Double distance;
    @JsonProperty("line")
    private final String line;
    @JsonProperty("name")
    private final String name;

    @JsonCreator
    public Metro(@JsonProperty("distance") Double distance,
                 @JsonProperty("line") String line,
                 @JsonProperty("name") String name) {
        this.distance = distance;
        this.line = line;
        this.name = name;
    }

    @JsonProperty("distance")
    public Double getDistance() {
        return distance;
    }

    @JsonProperty("line")
    public String getLine() {
        return line;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Metro{" +
                "distance=" + distance +
                ", line='" + line + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}