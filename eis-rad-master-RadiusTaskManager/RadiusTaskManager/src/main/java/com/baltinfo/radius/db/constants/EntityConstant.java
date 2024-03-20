package com.baltinfo.radius.db.constants;

/**
 * @author Lapenok Igor
 * @since 24.08.2018
 */
public enum EntityConstant {

    OBJECT(1L),
    LOT(5L),
    AUCTION(3L),
    DEAL(4L),
    APPLICATION(2L),
    ACT(9L);

    private final Long id;

    EntityConstant(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
