package com.baltinfo.radius.db.constants;

public enum AccOperIndReturn {
    // "Не возврат"
    NOT_REFUND(0),
    // "Платёжка подготовлена"
    PAYDOC_PREPARED(1),
    // "Платёжка отправлена"
    PAYDOC_SENT(2),
    //"Результат передан на ЭТП"
    RESULT_TRANSMITTED(3);
    private final Integer code;

    AccOperIndReturn(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
