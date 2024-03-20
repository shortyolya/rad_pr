package com.baltinfo.radius.feed.cian.model;

/**
 * @author Igor Lapenok
 * @since 22.03.2023
 */
public enum CianLandUnitType {
    SOTKA("sotka"),
    HECTARE("hectare");

    private final String code;

    CianLandUnitType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
