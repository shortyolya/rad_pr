package com.baltinfo.radius.feed.cian.model;

/**
 * @author Igor Lapenok
 * @since 21.03.2023
 */
public enum CianSaleType {
    FREE("free"),
    ALTERNATIVE("alternative");

    private final String code;

    public String getCode() {
        return code;
    }

    CianSaleType(String code) {
        this.code = code;
    }
}
