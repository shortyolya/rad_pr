package com.baltinfo.radius.db.constants;

import java.util.Arrays;
import java.util.Optional;

/**
 * <p>
 * </p>
 *
 * @author Lapenok Igor
 * @since 23.08.2018
 */
public enum ChangePriceAlg {
    ALG1((short)1),
    ALG2((short)2),
    ALG3((short)3),
    ALG4((short)4),
    ALG5((short)5);

    private final Short code;

    ChangePriceAlg(Short code) {
        this.code = code;
    }

    public static Optional<ChangePriceAlg> getByCode(Short code) {
        if (code == null) {
            return Optional.empty();
        }
        return Arrays.stream(values()).filter(x -> x.code.equals(code)).findFirst();
    }

    public Short getCode() {
        return code;
    }
}
