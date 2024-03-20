package com.baltinfo.radius.db.constants;

/**
 * @author Suvorina Aleksandra
 * @since 10.10.2018
 */
public enum ApplicationReviewResult {

    /**
     * Не рассматривалась
     */
    PENDING(0),
    /**
     * Допущена
     */
    ALLOWED(1),
    /**
     * Отклонена
     */
    REJECTED(2),
    /**
     * Отозвана
     */
    CANCELED(3);

    private final Integer code;

    ApplicationReviewResult(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
