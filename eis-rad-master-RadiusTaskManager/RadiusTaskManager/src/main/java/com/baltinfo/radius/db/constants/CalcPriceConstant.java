package com.baltinfo.radius.db.constants;

/**
 * <p>
 * Алгоритм расчета величины предложения в графике снижения цена по 5 типу
 * </p>
 *
 * @author Lapenok Igor
 * @since 23.10.2018
 */
public enum CalcPriceConstant {
    START_PRICE_PERCENT("процент от начальной цены"),
    PREV_PRICE_PERCENT("процент от предыдущей цены"),
    FIXED_PRICE("фиксированное значение в рублях");

    private final String value;

    CalcPriceConstant(String value) {
        this.value = value;
    }

    public static CalcPriceConstant getByValue(String value) {
        for (CalcPriceConstant t : values()) {
            if (t.value.equals(value)) {
                return t;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getValue() {
        return value;
    }
}
