package com.baltinfo.radius.utils;

import java.math.BigDecimal;

public enum AreaConverter {

    SQUARE_METER(1),
    SOTKA(100),
    HECTARE(10000);

    private final Double factor;

    AreaConverter(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }

    public Double convertTo(AreaConverter finalSystem, Double value) {
        return value * factor / finalSystem.getFactor();
    }

    public BigDecimal convertTo(AreaConverter finalSystem, BigDecimal value) {
        return value.multiply(BigDecimal.valueOf(factor))
                .divide(BigDecimal.valueOf(finalSystem.factor));
    }
}
