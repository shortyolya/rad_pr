package com.baltinfo.radius.loadauction.constants;

/**
 * @author Suvorina Aleksandra
 * @since 10.08.2021
 */
public enum AsvAuctionForm {
    OPENED("opened"),
    CLOSED("closed");

    private final String code;

    AsvAuctionForm(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
