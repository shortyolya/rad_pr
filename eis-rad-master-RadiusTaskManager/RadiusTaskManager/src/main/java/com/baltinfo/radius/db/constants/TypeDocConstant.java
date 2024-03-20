package com.baltinfo.radius.db.constants;

/**
 * @author Suvorina Aleksandra
 * @since 07.09.2018
 */
public enum TypeDocConstant {
    /**
     * Договор о задатке
     */
    RECEIPT_CONTRACT(25),
    /**
     * Проект договора купли-продажи имущества
     */
    SALE_DEAL_DRAFT_PROPERTY(50),
    /**
     * Протокол определения участников
     */
    PROTOCOL_APPL(4),
    /**
     * Протокол об итогах торгов
     */
    PROTOCOL_RESULT (22),
    /**
     * Заявка на проведение торгов
     */
    APPLICATION_DRAFT (24),
    /**
     * Информационное сообщение о проведении торгов
     */
    INFORMATION_MESSAGE (6),
    /**
     * Не указан
     */
    NO_INFO(1),
    /**
     * Иное
     */
    OTHER(68),
    /**
     * Иное
     */
    PAYMENT_ORDER(100),
    /**
     * Дайджест
     */
    DIGEST(59);

    private final long unid;

    TypeDocConstant(long unid) {
        this.unid = unid;
    }

    public long getUnid() {
        return unid;
    }
}
