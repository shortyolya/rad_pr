package com.baltinfo.radius.db.constants;

import java.math.BigInteger;

/**
 * @author Suvorina Aleksandra
 * @since 22.08.2019
 */
public enum LotOnlineCategoryId {

    OTHER(new BigInteger("9005")),
    ART(new BigInteger("1001"));

    private final BigInteger id;

    LotOnlineCategoryId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }
}
