package com.baltinfo.radius.feed.cian.constant;

public enum VCianRealEstateParams {
    OBJ_CODE("objCode", "Код объекта"),
    TITLE("title", "Заголовок объявления"),
    DESCRIPTION("description", "Текст объявления"),
    CATEGORY("category", "Категория"),
    TOTAL_AREA("totalArea", "Общая площадь помещений"),
    FLOOR_NUMBER("floorNumber", "Этаж"),
    FLOORS_COUNT("floorsCount", "Количество этажей в здании"),
    GARAGE_TYPE("garageType", "Тип гаража"),
    LAND_STATUS("landStatus", "Категория земли"),
    PRICE("price", "Стоимость (аукцион), цена отсечения (голландский аукцион), стоимость на текущем периоде (ППП)"),
    COST_IND_TAX("costIndTax", "НДС"),
    ADDRESS("address", "Адрес"),
    SUBAGENT_MOBILE_PHONE("subagentMobilePhone", "Менеджер продаж: телефон"),
    SUBAGENT_EMAIL("subagentEmail", "Менеджер продаж: email"),
    SUBAGENT_NAME_F("subAgentNameF", "Менеджер продаж: фамилия"),
    SUBAGENT_NAME_I("subAgentNameI", "Менеджер продаж: имя"),
    CATEGORY_FEED_NAME("categoryFeedName", "Код категории фида"),
    LAND_SQR("landSqr", "Общая площадь земельных участков"),
    LEASE_TERM_TYPE("leaseTermType", "Срок аренды"),
    TS_UNID("tsUnid", "Статус"),
    PICTURE_COUNT("", ""),
    FLAT_ROOMS_COUNT("flatRoomsCount", "Количество комнат"),
    SHARE_AMOUNT("shareAmount", "Размер доли в доме"),
    ROOM_AREA("roomArea", "Площадь комнаты"),
    LAND_AREA("landArea", "Площадь участка");

    private final String fieldName;
    private final String fieldDescr;

    VCianRealEstateParams(String fieldName, String fieldDescr) {
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
