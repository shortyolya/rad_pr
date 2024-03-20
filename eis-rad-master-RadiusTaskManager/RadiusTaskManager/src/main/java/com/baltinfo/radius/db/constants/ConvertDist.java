package com.baltinfo.radius.db.constants;

public enum ConvertDist {

    /**
     * Регион
     */
    REGION(8),
    /**
     * Направление продаж
     */
    SALE_GUIDE(1),
    /**
     * Тип объекта
     */
    TYPE_OBJECT(2),
    /**
     * Тип собственности
     */
    TYPE_PROPERTY(3),
    /**
     * Назначение объекта
     */
    OBJECT_DEST(4),
    /**
     * Филиал
     */
    FILLIAL(5),
    /**
     * Вид торгов
     */
    TYPE_AUCTION(6),
    /**
     * Дополнительное свойство
     */
    ADD_PROP(7),
    /**
     * Собственник объекта
     */
    OBJ_OWNER(9),
    /**
     * Категория объекта
     */
    OBJ_CATEGORY(10),
    /**
     * Причина непроведения торгов
     */
    NON_EXEC_REASON(11),
    /**
     * Тип документа
     */
    TYPE_DOC(12);

    private final long unid;

    ConvertDist(long unid) {
        this.unid = unid;
    }

    public long getUnid() {
        return unid;
    }
}
