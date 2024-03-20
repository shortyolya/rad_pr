package com.baltinfo.radius.db.constants;

/**
 * @author Suvorina Aleksandra
 * @since 27.01.2021
 */
public enum BaseSaleCategory {
    /**
     * Недвижимое имущество
     */
    REAL_ESTATE("1"),
    /**
     * Движимое имущество
     */
    MOVABLE_PROPERTY("2"),
    /**
     * Нематериальные и финансовые активы
     */
    INTANGIBLE_ASSETS("3");

    private final String scCode;

    BaseSaleCategory(String scCode) {
        this.scCode = scCode;
    }

    public String getScCode() {
        return scCode;
    }
}
