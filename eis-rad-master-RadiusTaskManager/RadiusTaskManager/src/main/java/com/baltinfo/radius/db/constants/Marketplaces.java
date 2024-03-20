package com.baltinfo.radius.db.constants;

public enum Marketplaces {

    BANKRUPTCY(1L),
    LOT_ONLINE(2L);

    private final Long mpUnid;

    Marketplaces(Long mpUnid) {
        this.mpUnid = mpUnid;
    }

    public Long getMpUnid() {
        return mpUnid;
    }
}
