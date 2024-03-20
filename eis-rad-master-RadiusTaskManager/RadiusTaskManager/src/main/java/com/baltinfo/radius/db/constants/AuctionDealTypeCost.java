package com.baltinfo.radius.db.constants;

public enum AuctionDealTypeCost {

    START_COST(0),
    MIN_COST(1);

    private final int code;

    AuctionDealTypeCost(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
