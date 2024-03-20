package com.baltinfo.radius.bankruptcy;

/**
 * Статус допуска заявки на ЭТП "Банкротство"
 *
 * @author Suvorina Aleksandra
 * @since 10.10.2018
 */
public enum BkrAppIndPermit {
    /**
     * На рассмотрении
     */
    REVIEW(0),
    /**
     * Отклонена
     */
    REJECTED(2),
    /**
     * Допущена
     */
    ALLOWED(1),
    /**
     * Отозвана
     */
    CANCELED(3);

    private final Integer code;

    BkrAppIndPermit(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
