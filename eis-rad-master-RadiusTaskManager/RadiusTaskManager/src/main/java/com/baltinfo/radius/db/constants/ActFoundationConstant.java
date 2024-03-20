package com.baltinfo.radius.db.constants;



public enum ActFoundationConstant {
    PROTOCOL(1L),
    AGREEMENT(2L);

    private final Long unid;

    ActFoundationConstant(Long unid) {
        this.unid = unid;

    }
    public Long getUnid() {
        return unid;
    }

}
