package com.baltinfo.radius.loadauction.constants;

/**
 * @author Suvorina Aleksandra
 * @since 10.08.2021
 */
public enum AsvTenderType {
    BLOCK_AUCTION("Блочный торг", "Б"),
    NON_BLOCK_AUCTION("Обычный торг", "О");

    private final String code;
    private final String letter;

    AsvTenderType(String code, String letter) {
        this.code = code;
        this.letter = letter;
    }

    public String getCode() {
        return code;
    }

    public String getLetter() {
        return letter;
    }
}
