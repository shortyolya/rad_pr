package com.baltinfo.radius.db.constants;

/**
 * @author Suvorina Aleksandra
 * @since 22.08.2019
 */
public enum AddrInputMode {

    MAP(1),
    FIAS(2);

    private final long code;

    AddrInputMode(long code) {
        this.code = code;
    }

    public long getCode() {
        return code;
    }
}
