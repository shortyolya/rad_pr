package com.baltinfo.radius.feed.jcat.api.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 30.03.2021
 */
public class ReportData {

    @JsonProperty("count")
    private final int count;
    @JsonProperty("items")
    private final List<ReportUrl> items;

    public ReportData(@JsonProperty("count") int count, @JsonProperty("items") List<ReportUrl> items) {
        this.count = count;
        this.items = items;
    }

    @JsonProperty("count")
    public int getCount() {
        return count;
    }

    @JsonProperty("items")
    public List<ReportUrl> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "ReportData{" +
                "count=" + count +
                ", items=" + items +
                '}';
    }
}
