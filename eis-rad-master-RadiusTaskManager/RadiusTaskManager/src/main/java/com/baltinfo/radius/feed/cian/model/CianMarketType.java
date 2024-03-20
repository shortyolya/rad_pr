package com.baltinfo.radius.feed.cian.model;

/**
 * @author Igor Lapenok
 * @since 24.03.2023
 */
public enum CianMarketType {
    RESALE("Вторичка"),
    NEW_BUILD("Новостройка");

    private final String code;

    CianMarketType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
