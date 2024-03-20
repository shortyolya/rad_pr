package com.baltinfo.radius.db.constants;

/**
 * @author Suvorina Aleksandra
 * @since 22.08.2019
 */
public enum LotOnlineCurrency {

    RUB(810);

    private final int code;

    LotOnlineCurrency(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
