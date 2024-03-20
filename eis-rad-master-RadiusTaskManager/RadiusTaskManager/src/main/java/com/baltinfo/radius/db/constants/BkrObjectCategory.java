package com.baltinfo.radius.db.constants;

/**
 * @author Suvorina Aleksandra
 * @since 27.01.2021
 */
public enum BkrObjectCategory {

    REAL_ESTATE(1L, "Недвижимое имущество"),
    MOVABLE_PROPERTY(2L, "Движимое имущество"),
    INTANGIBLE_ASSETS(41L, "Нематериальные и финансовые активы");

    private final Long unid;
    private final String name;

    BkrObjectCategory(Long unid, String name) {
        this.unid = unid;
        this.name = name;
    }

    public Long getUnid() {
        return unid;
    }

    public String getName() {
        return name;
    }
}
