package com.baltinfo.radius.db.constants;

import java.util.Arrays;

/**
 * Статус работы процедуры информационного обмена
 *
 * @author Suvorina Aleksandra
 * @since 21.08.2018
 */
public enum ExchangeProcStatus {

    NOT_STARTED(0, "Не запускалась"),
    RUNNING(1, "Идет"),
    ERROR_START(2, "Ошибка при старте процедуры"),
    PAUSED(3, "Приостановлена"),
    FINISHED(4, "Завершена"),
    ERROR_RUNNING(5, "Ошибка в ходе процедуры"),
    CANCELED(6, "Отменена");

    private final int code;
    private final String name;

    ExchangeProcStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getNameByCode(int code) {
        return Arrays.stream(values())
                .filter(status -> status.getCode() == code)
                .map(ExchangeProcStatus::getName)
                .findFirst()
                .orElse(null);
    }
}
