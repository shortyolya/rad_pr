package com.baltinfo.radius.db.constants;

import java.io.Serializable;

/**
 * @author sas
 */
public enum OperType implements Serializable {
    /**
     * Переход
     */
    JUMP(0),
    /**
     * Создание
     */
    CREATE(1),
    /**
     * Изменение
     */
    EDIT(2),
    /**
     * Удаление
     */
    DELETE(3),
    /**
     * Иное
     */
    OTHER(4);

    private final int unid;

    OperType(int unid) {
        this.unid = unid;
    }

    public int getUnid() {
        return unid;
    }

}
