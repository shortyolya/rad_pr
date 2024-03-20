package com.baltinfo.radius.feed.avito.constant;

public enum VAvitoAutoParams {

    OME_UNID("omeUnid", "Идентификатор маркетингового мероприятия"),
    OBJ_UNID("objUnid", "Идентификатор объекта"),
    MEV_UNID("mevUnid", "Идентификатор маркетингового мероприятия"),
    OBJ_CODE("objCode", "Код объекта"),
    LS_UNID("lsUnid", "Источник загрузки"),
    SC_UNID("scUnid", "Категория"),
    FC_UNID("fcUnid", "Категория фида"),
    CATEGORY("category", "Категория"),
    CATEGORY_FEED_CODE("categoryFeedCode", "Код категории фида"),
    DESCRIPTION("description", "Текст объявления"),
    MAKE("make", "Марка"),
    MODEL("model", "Модель"),
    YEAR("year", "Год выпуска"),
    KILOMETRAGE("kilometrage", "Пробег"),
    ACCIDENT("accident", "Состояние"),
    VIN("vin", "VIN-номер"),
    BODY_TYPE("bodyType", "Тип кузова"),
    DOORS("doors", "Количество дверей"),
    GENERATION_ID("generationId", "Поколение"),
    MODIFICATION_ID("modificationId", "Модификация"),
    COLOR("color", "Цвет"),
    FUEL_TYPE("fuelType", "Тип двигателя"),
    ENGINE_SIZE("engineSize", "Объем двигателя"),
    POWER("power", "Мощность двигателя"),
    TRANSMISSION("transmission", "Коробка передач"),
    DRIVE_TYPE("driveType", "Привод"),
    WHEEL_TYPE("wheelType", "Руль"),
    OWNERS("owners", "Количество владельцев по ПТС"),
    PRICE("price", "Стоимость (аукцион), цена отсечения (голландский аукцион), стоимость на текущем периоде (ППП)"),
    ADDRESS("address", "Адрес"),
    SUBAGENT_MOBILE_PHONE("subagentMobilePhone", "Менеджер продаж: телефон"),
    SUBAGENT_NAME_F("subagentNameF", "Менеджер продаж: фамилия"),
    SUBAGENT_NAME_I("subagentNameI", "Менеджер продаж: имя"),
    LISTING_FEE("listingFee", "Вариант платного размещения"),
    OBJ_INT_RENT("objIndRent", "Признак аренды"),
    TS_UNID("tsUnid", "Статус объекта"),
    PICTURE_COUNT("", "");

    private final String fieldName;
    private final String fieldDescr;

    VAvitoAutoParams(String fieldName, String fieldDescr) {
        this.fieldName = fieldName;
        this.fieldDescr = fieldDescr;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldDescr() {
        return fieldDescr;
    }
}
