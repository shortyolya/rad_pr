package com.baltinfo.radius.db.constants;

/**
 * Статус хода обмена
 *
 * @author Suvorina Aleksandra
 * @since 22.08.2018
 */
public enum RunningDetailsStatus {

    NOT_SENT(0, "Не передавалось"),
    SENT(1, "Передано"),
    ERROR(2, "Ошибка"),
    DO_NOT_SEND(3, "Не передавать");

    private final int code;
    private final String name;

    RunningDetailsStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
