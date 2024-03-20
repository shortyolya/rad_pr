package com.baltinfo.radius.bankruptcy;

/**
 * @author Suvorina Aleksandra
 * @since 23.08.2018
 */
public enum BkrAuctionType {
    AUCTION_OPEN(21),
    AUCTION_CLOSED(22),
    CONTEST_OPEN(24),
    CONTEST_CLOSED(28),
    PUBLIC_SALE_OF_PROPERTY(100),
    PUBLIC_OFFER(112),
    /**
     * Запрос предложений
     */
    PROPOSAL_REQUEST(101),
    /**
     * Серия публичных оферт
     */
    PUBLIC_OFFER_SERIES(102),
    /**
     * Конкрунтная продажа(иной способ)
     */
    COMPETITIVE_SALE(10),
    /**
     * Продажа без объявления цены
     */
    SALE_WITHOUT_PRICE(26),
    /**
     * Сбор предложений
     */
    COLLECTION_OF_OFFER(105),
    /**
     * Торговая сессия
     */
    TRADE_SESSION(103);

    private final long code;

    BkrAuctionType(long code) {
        this.code = code;
    }

    public long getCode() {
        return code;
    }
}
