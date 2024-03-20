package com.baltinfo.radius.db.constants;

/**
 * @author Suvorina Aleksandra
 * @since 28.10.2020
 */
public enum TypeDealClassConstants {

    /**
     * Класс договора купли-продажи
     */
    CLASS_TRADE(3L),
    CLASS_EXTRA_AGREEMENT(4L),
    CLASS_COMMISSION(2L);

    private final Long id;

    TypeDealClassConstants(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
