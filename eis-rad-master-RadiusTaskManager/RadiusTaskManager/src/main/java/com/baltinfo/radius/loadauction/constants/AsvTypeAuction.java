package com.baltinfo.radius.loadauction.constants;

import com.baltinfo.radius.db.constants.TypeAuctionCode;

import java.util.Arrays;

/**
 * @author Suvorina Aleksandra
 * @since 10.08.2021
 */
public enum AsvTypeAuction {
    PUBLIC("ppp", TypeAuctionCode.PUBLIC_SALE.getCode()),
    AUCTION("auction", TypeAuctionCode.AUCTION.getCode()),
    COMPETITION("competition", TypeAuctionCode.COMPETITION.getCode());

    private final String code;
    private final Long eisCode;

    AsvTypeAuction(String code, Long eisCode) {
        this.code = code;
        this.eisCode = eisCode;
    }

    public String getCode() {
        return code;
    }

    public Long getEisCode() {
        return eisCode;
    }

    public static String getAsvCodeByEisCode(Long eisCode) {
        return Arrays.stream(values())
                .filter(type -> type.eisCode.equals(eisCode))
                .map(AsvTypeAuction::getCode)
                .findFirst()
                .orElse(null);
    }
}
