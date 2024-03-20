package com.baltinfo.radius.db.constants;

public enum TypePartAgentConstant {
    SALE_DEPARTMENT_SPB(12L),
    SALE_DEPARTMENT_MSC(57L);

    private final Long tpaUnid;

    TypePartAgentConstant(Long tpaUnid) {
        this.tpaUnid = tpaUnid;
    }

    public Long getTpaUnid() {
        return tpaUnid;
    }
}
