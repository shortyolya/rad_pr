package com.baltinfo.radius.db.constants;


import java.util.Arrays;

/**
 * @author Kulikov Semyon
 * @since 11.06.2020
 */

public enum VatType {
    NOT_INCLUDED(0, "notIncluded"),
    INCLUDED(1, "included"),
    NONE(2, "usn");

    private final Integer dbCode;
    private final String feedCode;

    VatType(Integer dbCode, String feedCode) {
        this.dbCode = dbCode;
        this.feedCode = feedCode;
    }

    public static VatType getByDbCode(Integer value) {
        return Arrays.stream(values())
                .filter(item -> item.dbCode.equals(value))
                .findFirst()
                .orElse(NONE);
    }

    public Integer getDbCode() {
        return dbCode;
    }

    public String getFeedCode() {
        return feedCode;
    }
}
