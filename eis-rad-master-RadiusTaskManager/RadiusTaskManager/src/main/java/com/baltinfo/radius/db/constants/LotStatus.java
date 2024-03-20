
package com.baltinfo.radius.db.constants;

public enum LotStatus {

    /**
     * Торги не проводились
     */
    TRADE_NO(0),
    /**
     * Торги проведены
     */
    TRADE_DONE(1),
    /**
     * Торги не состоялись
     */
    TRADE_NOT_DONE(2),
    /**
     * Торги отменены
     */
    TRADE_CANCEL(3),
    /**
     * Торги по лоту перенесены
     */
    TRADE_RESHEDULED(4),
    /**
     * Торги по лоту приостановлены
     */
    TRADE_SUSPENDED (5);

    private final int code;

    LotStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }


}
