package com.baltinfo.radius.db.constants;

/**
 * @author Suvorina Aleksandra
 * @since 13.11.2020
 */
public enum TpaDivision {

    SALES_DEPARTMENT_SPB(12L),
    SALES_DEPARTMENT_MOSCOW(57L);

    private final Long tpaUnid;

    TpaDivision(Long tpaUnid) {
        this.tpaUnid = tpaUnid;
    }

    public Long getTpaUnid() {
        return tpaUnid;
    }
}
