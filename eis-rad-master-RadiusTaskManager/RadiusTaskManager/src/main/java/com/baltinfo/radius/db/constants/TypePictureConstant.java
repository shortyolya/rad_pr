package com.baltinfo.radius.db.constants;

/**
 * <p>
 *     Таблица type_picture
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 07.02.2020
 */
public enum TypePictureConstant {
    /**
     * Не указан
     */
    NOT_SPECIFIED(1),
    /**
     * Состояние объекта
     */
    OBJECT_STATUS(2),
    /**
     * Окружение объекта
     */
    OBJECT_ENVIRONMENT(3);

    private final long unid;

    TypePictureConstant(long unid) {
        this.unid = unid;
    }

    public long getUnid() {
        return unid;
    }
}
