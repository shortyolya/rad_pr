package com.baltinfo.radius.db.constants;

/**
 * <p>
 * </p>
 *
 * @author Lapenok Igor
 * @since 24.08.2018
 */
public enum OksmConstant {
    RUSSIA(643L);
    private final Long id;

    OksmConstant(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
