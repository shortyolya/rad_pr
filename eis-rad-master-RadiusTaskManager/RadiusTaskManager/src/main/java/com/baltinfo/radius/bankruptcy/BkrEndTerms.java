package com.baltinfo.radius.bankruptcy;

/**
 * @author Suvorina Aleksandra
 * @since 24.08.2018
 */
public enum BkrEndTerms {
    LAST_APPLICATION(1),
    AUCTION_TIME(0);

    private final int code;

    BkrEndTerms(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
