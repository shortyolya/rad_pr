package com.baltinfo.radius.bankruptcy;

/**
 * @author Suvorina Aleksandra
 * @since 07.09.2018
 */
public enum BkrTypeAdditionalData {
    /**
     * Договор о задатке
     */
    TAD_DEPOSIT(5),
    /**
     * Проект договора купли-продажи имущества (предприятия) должника
     */
    TAD_SALE_DEAL_DRAFT_PROPERTY(6),
    /**
     * Протокол определения участников
     */
    PROTOCOL_APPL(8L),
    /**
     * Протокол подведения итогов
     */
    PROTOCOL_RESULT(7L);

    private final long unid;

    BkrTypeAdditionalData(long unid) {
        this.unid = unid;
    }

    public long getUnid() {
        return unid;
    }
}
