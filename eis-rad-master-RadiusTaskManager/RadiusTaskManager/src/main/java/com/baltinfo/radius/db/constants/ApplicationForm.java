package com.baltinfo.radius.db.constants;

/**
 * @author Suvorina Aleksandra
 * @since 11.10.2018
 */
public enum ApplicationForm {

    ELECTRONIC(0);

    private final int code;

    ApplicationForm(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
