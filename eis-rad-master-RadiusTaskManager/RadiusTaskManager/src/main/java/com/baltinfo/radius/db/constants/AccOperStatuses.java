package com.baltinfo.radius.db.constants;

public enum AccOperStatuses {

    NOT_PROCESSED(0), // "Не обработано"
    SENT_TO_ACCOUNT_DEP(1),// "Передано в бухгалтерию"
    WRITTEN_OFF(2),// "Списано с расчётного счёта"
    ERR_WRITTEN_OFF(3);// "Ошибочно списано"

    private final Integer code;

    AccOperStatuses(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
