package com.baltinfo.radius.db.constants;

public enum DocGenerationTypes {
    GENERAL(1L),
    ASYNCHRONOUS(2L);

    private final Long dgtUnid;

    DocGenerationTypes(Long dgtUnid) {
        this.dgtUnid = dgtUnid;
    }

    public Long getDgtUnid() {
        return dgtUnid;
    }
}
