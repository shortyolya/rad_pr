package com.baltinfo.radius.feed.avito.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Item {

    @JsonProperty("itemId")
    private Long itemId;
    @JsonProperty("stats")
    private List<ItemStat> stats;
    @JsonProperty("days")
    private List<ItemCallStat> days;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public List<ItemStat> getStats() {
        return stats;
    }

    public void setStats(List<ItemStat> stats) {
        this.stats = stats;
    }

    public List<ItemCallStat> getDays() {
        return days;
    }
}
