package com.baltinfo.radius.feed.avito.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ItemStat {

    @JsonProperty("date")
    private Date date;
    @JsonProperty("uniqViews")
    private Long uniqViews;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getUniqViews() {
        return uniqViews;
    }

    public void setUniqViews(Long uniqViews) {
        this.uniqViews = uniqViews;
    }
}
