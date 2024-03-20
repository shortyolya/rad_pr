package com.baltinfo.radius.db.constants;

/**
 * @author Suvorina Aleksandra
 * @since 09.01.2020
 */
public enum TypeDealConstants {
    /**
     * Договор купли-продажи
     */
    SALE_DEAL(3L),
    ASSIGNMENT_DEAL(4L);

    private final Long id;

    TypeDealConstants(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
