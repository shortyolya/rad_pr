package com.baltinfo.radius.loadauction.model.result;

public enum AsvLotResult {

    SINGLE_PARTICIPANT(0),
    TRADE_DONE(1),
    DIDNT_TAKE_PLACE(2),
    RUNNING(3),
    CANCELED(4);

    private final Integer code;

    AsvLotResult(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
