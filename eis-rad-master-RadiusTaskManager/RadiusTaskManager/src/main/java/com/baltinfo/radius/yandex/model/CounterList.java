
package com.baltinfo.radius.yandex.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "rows",
    "counters"
})
public class CounterList {

    @JsonProperty("rows")
    private Integer rows;
    @JsonProperty("counters")
    private List<Counter> counters = null;

    @JsonProperty("rows")
    public Integer getRows() {
        return rows;
    }

    @JsonProperty("rows")
    public void setRows(Integer rows) {
        this.rows = rows;
    }

    @JsonProperty("counters")
    public List<Counter> getCounters() {
        return counters;
    }

    @JsonProperty("counters")
    public void setCounters(List<Counter> counters) {
        this.counters = counters;
    }
}
