package com.baltinfo.radius.db.constants;

/**
 * @author Suvorina Aleksandra
 * @since 22.11.2018
 */
public enum TypeEventConstant {
    /**
     * Окончание приема заявок
     */
    END_RECEIPT_APPLICATIONS(2L),

    /**
     * Дата проведения торгов
     */
    START_AUCTION(5L),

    /**
     * Окончание приема заявок периода снижения цены
     */
    END_APPL_REDUCTION_PERIOD(18L),

    /**
     * Получено информационное сообщение о торгах
     */
    RECEIVE_INFORMATION_MESSAGE(20L),

    DOC_FORM(21L),

    EXPLANATION_REQUEST(22L),
    /**
     * Подвведены итоги процедуры
     */
    LOT_RESULTS(23L),
    /**
     * Процедура опубликована
     */
    AUCTION_PUBLISHED(24L),
    /**
     * Лот опубликован
     */
    LOT_PUBLISHED(25L);

    private final Long unid;

    TypeEventConstant(Long unid) {
        this.unid = unid;
    }

    public Long getUnid() {
        return unid;
    }
}
