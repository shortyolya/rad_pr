package com.baltinfo.radius.db.constants;

/**
 * <p>
 * Источник загрузки
 * </p>
 *
 * @author Lapenok Igor
 * @since 20.08.2018
 */
public enum LoadSourceConstant {
    ASV(1L),
    ASVZ(2L);

    private final Long id;

    LoadSourceConstant(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
