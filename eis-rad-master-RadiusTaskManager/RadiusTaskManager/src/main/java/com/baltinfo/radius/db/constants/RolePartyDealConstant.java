package com.baltinfo.radius.db.constants;

/**
 * @author Suvorina Aleksandra
 * @since 10.01.2020
 */
public enum RolePartyDealConstant {

    /**
     * Покупатель
     */
    BUYER(7L),
    /**
     * Заказчик
     */
    CUSTOMER(11L);

    private final Long id;

    RolePartyDealConstant(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
