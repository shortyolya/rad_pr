package com.baltinfo.radius.feed.utils;

import java.util.HashMap;
import java.util.List;

/**
 * @author Kulikov Semyon
 * @since 06.02.2020
 */

public class ResultForFeed<T> {
    private T feed;
    private String error;
    private final HashMap<Long, String> objUnidsWithErrors;
    private final List<Long> badObjUnids;

    public ResultForFeed(T feed, String error, HashMap<Long, String> objUnidsWithErrors, List<Long> bagObjUnids) {
        this.error = error;
        this.feed = feed;
        this.objUnidsWithErrors = objUnidsWithErrors;
        this.badObjUnids = bagObjUnids;
    }

    public T getFeed() {
        return feed;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void addError(Long objUnid, String error) {
        this.error += objUnid + ":" + error + "\n";
    }

    public HashMap<Long, String> getObjUnidsWithErrors() {
        return objUnidsWithErrors;
    }

    public List<Long> getBadObjUnids() {
        return badObjUnids;
    }
}
