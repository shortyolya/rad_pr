package com.baltinfo.radius.db.constants;

/**
 * @author Lapenok Igor
 * @since 24.08.2018
 */
public enum TypeCostConstant {
    /**
     * Начальная стоимость
     */
    START(1L),
    /**
     * Конечная стоимость
     */
    RESULT(2L),
    /**
     * Стоимость продажи
     */
    SALE(5L),
    /**
     * Цена отсечения
     */
    MIN(9L);

    private final Long id;

    TypeCostConstant(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
