package com.baltinfo.radius.db.constants;

/**
 * @author Suvorina Aleksandra
 * @since 23.03.2021
 */
public enum LotOnlineSaleType {

    PRIVATIZATION(4001L),
    RAD_AUCTION(3001L),
    RENT(7001L);

    private final Long id;

    LotOnlineSaleType(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
