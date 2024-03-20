package com.baltinfo.radius.db.constants;

/**
 * <p>
 * </p>
 *
 * @author Lapenok Igor
 * @since 24.08.2018
 */
public enum CurrencyConstant {
    RUB(1L);

    private final Long id;

    CurrencyConstant(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
