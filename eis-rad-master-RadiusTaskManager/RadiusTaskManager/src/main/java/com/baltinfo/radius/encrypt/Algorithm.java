package com.baltinfo.radius.encrypt;

import java.io.Serializable;

/**
 * @author lia
 */
public enum Algorithm implements Serializable {
    NONE,
    BASE64,
    CAESAR;

    public static Algorithm converse(String alg) {
        if (alg == null) return null;
        alg = alg.trim().toUpperCase();

        return Algorithm.valueOf(alg);
    }
}

