package com.baltinfo.radius.db.constants;

public enum RewardTypeConstant {
    PLAN(0),
    FACT(1);

    private final Integer unid;

    RewardTypeConstant(Integer unid) {
        this.unid = unid;
    }

    public Integer getUnid() {
        return unid;
    }

}
