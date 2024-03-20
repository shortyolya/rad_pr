package com.baltinfo.radius.db.constants;

import java.util.Arrays;
import java.util.Optional;

/**
 * <p>
 * Тип задатка
 * </p>
 *
 * @author Lapenok Igor
 * @since 17.08.2018
 */
public enum DepositTypeConstant {
    START_PRICE_PROC(1L, "процент от начальной цены"),
    PERIOD_PRICE_PROC(2L, "процент от цены на периоде"),
    FIXED_VALUE(3L, "фиксированное значение в рублях");

    private final Long id;
    private final String name;

    DepositTypeConstant(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Optional<DepositTypeConstant> getByName(String name) {
        if (name == null || name.isEmpty()) {
            return Optional.empty();
        }
        return Arrays.stream(values()).filter(x -> x.getName().equals(name.trim().toLowerCase())).findFirst();
    }
}
