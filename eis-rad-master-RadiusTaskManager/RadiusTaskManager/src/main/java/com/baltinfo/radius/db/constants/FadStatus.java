package com.baltinfo.radius.db.constants;

/**
 * @author Suvorina Aleksandra
 * @since 07.09.2020
 */
public enum FadStatus {
    ACTIVE(1),
    ARCHIVE(2),
    ERROR(3),
    CATEGORY_ERROR(4);

    private final Integer code;

    FadStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
