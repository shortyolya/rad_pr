package com.baltinfo.radius.feed.statistic;

import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 14.03.2021
 */
public class FeedStatistic {

    private final String objCode;
    private final String adUrl;
    private final String adError;
    private final Long viewsCount;
    private final Long callsCount;
    private String viewsDetails;

    public FeedStatistic(String objCode, String adUrl, Long viewsCount, Long callsCount) {
        this(objCode, adUrl, (String) null, viewsCount, callsCount);
    }

    public FeedStatistic(String objCode, String adUrl, String errors, Long viewsCount) {
        this(objCode, adUrl, errors, viewsCount, null);
    }

    public FeedStatistic(String objCode, String adUrl, Long viewsCount) {
        this(objCode, adUrl, (String) null, viewsCount, null);
    }

    public FeedStatistic(String objCode, List<String> errors) {
        this(objCode, null, errors, null, null, null);
    }

    public FeedStatistic(String objCode, String adUrl, List<String> errors) {
        this(objCode, adUrl, errors, null, null, null);
    }

    public FeedStatistic(String objCode, String adUrl, List<String> errors, Long viewsCount) {
        this(objCode, adUrl, errors, viewsCount, null, null);
    }

    public FeedStatistic(String objCode,
                         String adUrl,
                         List<String> errors,
                         Long viewsCount,
                         String viewsDetails,
                         Long callsCount) {
        this.objCode = objCode;
        this.adUrl = adUrl;
        this.viewsCount = viewsCount;
        this.viewsDetails = viewsDetails;
        this.callsCount = callsCount;
        if (errors != null && !errors.isEmpty()) {
            this.adError = String.join("<br>", errors).replace("\\\"", "\"");
        } else {
            adError = null;
        }
    }

    public FeedStatistic(String objCode,
                         String adUrl,
                         String errors,
                         Long viewsCount,
                         Long callsCount) {
        this.objCode = objCode;
        this.adUrl = adUrl;
        this.adError = errors;
        this.viewsCount = viewsCount;
        this.callsCount = callsCount;
    }

    public String getObjCode() {
        return objCode;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public String getAdError() {
        return adError;
    }

    public Long getViewsCount() {
        return viewsCount;
    }

    public Long getCallsCount() {
        return callsCount;
    }

    public String getViewsDetails() {
        return viewsDetails;
    }
}
