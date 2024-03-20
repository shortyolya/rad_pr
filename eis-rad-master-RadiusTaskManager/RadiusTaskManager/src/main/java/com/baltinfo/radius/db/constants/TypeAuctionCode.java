package com.baltinfo.radius.db.constants;

/**
 * @author Suvorina Aleksandra
 * @since 22.08.2019
 */
public enum TypeAuctionCode {
    AUCTION(1),
    COMPETITION(2),
    HOLLAND(3),
    PUBLIC_SALE(4),
    PRICELESS(5);

    private final long code;

    TypeAuctionCode(long code) {
        this.code = code;
    }

    public long getCode() {
        return code;
    }
}
