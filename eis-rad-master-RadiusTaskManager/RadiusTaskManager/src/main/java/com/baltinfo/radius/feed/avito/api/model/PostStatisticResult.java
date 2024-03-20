package com.baltinfo.radius.feed.avito.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PostStatisticResult {

    @JsonProperty("items")
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
