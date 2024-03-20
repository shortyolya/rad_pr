package com.baltinfo.radius.bankruptcy;

/**
 * @author Suvorina Aleksandra
 * @since 13.09.2021
 */
public enum BkrCurrency {

    RUB(1L),
    USD(2L),
    EURO(6L);

    private final Long unid;

    BkrCurrency(Long unid) {
        this.unid = unid;
    }

    public Long getUnid() {
        return unid;
    }
}
