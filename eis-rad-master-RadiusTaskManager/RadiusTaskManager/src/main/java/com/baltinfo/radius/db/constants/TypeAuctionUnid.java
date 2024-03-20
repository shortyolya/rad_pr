package com.baltinfo.radius.db.constants;

import java.io.Serializable;

/**
 * @author sas
 */
public class TypeAuctionUnid implements Serializable {

    public static final long PUBLIC_SALE_PRIVATIZATION = 5; // Продажа посредством публичного предложения (приватизация)
    public static final long AUCTION = 1; // Аукцион
    public static final long CONTEST = 2; // Конкурс
    public static final long HOLLAND = 3; // Голландский аукцион
    public static final long PUBLIC = 4; // Продажа посредством публичного предложения
    public static final long RENTA = 6; // Право аренды
    public static final long PUBLIC_OFFER = 8; // Публичная оферта
    public static final long PRICELESS = 9; // Без объявления цены
    public static final long PROPOSAL_REQUEST = 10; // Запрос предложений
    public static final long PUBLIC_OFFER_SERIES = 11; // Серия публичных оферт
    public static final long TRADE_SESSION = 12; // Торговая сессия
    public static final long COMPETITIVE_SALE = 13; // Конкурентная продажа
    public static final long COLLECTION_OF_OFFER = 14; // Сбор предложений
    public static final long CONTEST_ENGLISH = 16; // Конкурс (английский аукцион)
}
