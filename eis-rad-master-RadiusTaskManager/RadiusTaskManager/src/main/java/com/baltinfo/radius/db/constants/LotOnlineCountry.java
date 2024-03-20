package com.baltinfo.radius.db.constants;

import java.math.BigInteger;

/**
 * @author Suvorina Aleksandra
 * @since 22.08.2019
 */
public enum LotOnlineCountry {

    RUSSIA(new BigInteger("1001"));

    private final BigInteger code;

    LotOnlineCountry(BigInteger code) {
        this.code = code;
    }

    public BigInteger getCode() {
        return code;
    }
}
