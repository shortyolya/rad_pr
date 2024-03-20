package com.baltinfo.radius.db.constants;

/**
 * @author AAA
 * @since 14.02.2022
 */
public enum EisCurrencyUnid {

    RUB(1L),
    USD(2L),
    EURO(3L);

    private final Long unid;

    EisCurrencyUnid(Long unid) {
        this.unid = unid;
    }

    public Long getUnid() {
        return unid;
    }
}
