package com.baltinfo.radius.db.constants;

public enum TypeStateTradeConstants {
    OBJ_SALED(1L), //"Продан"
    APPOINTED_FIRST_AUCTION(4L), //"Назначен первый аукцион"
    APPOINTED_REPEATED_AUCTION(5L), //"Назначен повторый аукцион"
    DIRECT_SALE(9L), //"Прямая продажа"
    REJECT_SALE_DEAL(14L), //"Отказ от ДКП"
    APPOINTED_PPP(28L), //"Назначен ППП"
    APPOINTED_TRADE(30L); //"Назначены торги"

    private final Long tstUnid;

    TypeStateTradeConstants(Long tstUnid) {
        this.tstUnid = tstUnid;
    }

    public Long getTstUnid() {
        return tstUnid;
    }
}
