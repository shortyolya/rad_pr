package com.baltinfo.radius.feed.avito.constant;


public enum VAvitoMovableParams {

    OME_UNID("omeUnid", "Идентификатор маркетингового мероприятия"),
    OBJ_UNID("objUnid", "Идентификатор объекта"),
    MEV_UNID("mevUnid", "Идентификатор маркетингового мероприятия"),
    OBJ_CODE("objCode", "Код объекта"),
    LS_UNID("lsUnid", "Источник загрузки"),
    SC_UNID("scUnid", "Идентификатор категории"),
    FC_UNID("fcUnid", "Идентификатор категории фида"),
    CATEGORY_NAME("categoryName", "Категория"),
    CATEGORY_CODE("categoryCode", "Код категории"),
    CATEGORY_FEED_CODE("categoryFeedCode", "Код категории фида"),
    TITLE("title", "Заголовок объявления"),
    DESCRIPTION("description", "Текст объявления"),
    MAKE("make", "Марка"),
    MODEL("model", "Модель"),
    BODY_TYPE("bodyType", "Тип кузова"),
    TECHNICAL_PASSPORT("technicalPassport", "Наличие ПТС транспортного средства"),
    YEAR("year", "Год выпуска"),
    PRICE("price", "Стоимость (аукцион), цена отсечения (голландский аукцион), стоимость на текущем периоде (ППП)"),
    ADDRESS("address", "Адрес"),
    SUBAGENT_MOBILE_PHONE("subagentMobilePhone", "Менеджер продаж: телефон"),
    SUBAGENT_NAME_F("subagentNameF", "Менеджер продаж: фамилия"),
    SUBAGENT_NAME_I("subagentNameI", "Менеджер продаж: имя"),
    LISTING_FEE("listingFee", "Вариант платного размещения"),
    OBJ_INT_RENT("objIndRent", "Признак аренды"),
    TS_UNID("tsUnid", "Статус объекта"),
    GOODS_TYPE("goodsType","Вид оборудования"),
    GOODS_SUB_TYPE("goodsSubTypeNew","Подвид оборудования"),
    FURNITURE_TYPE("furnitureType", ""),
    СASHIER_TYPE("cashierType", ""),
    ADVERTISING_TYPE("advertisingType", ""),
    PAVILIONS_TYPE("pavilionsType", ""),
    ENTERTAINING_TYPE("entertainingType", ""),
    INVENTORY_TYPE("inventoryType", "Вид промышленного оборудования"),
    PRIVOD_TYPE("privodType", "Тип приводного оборудования"),
    ELECTRIC_TYPE("electricType", "Тип электрического оборудования"),
    STANOK_TYPE("stanokType", "Вид станка"),
    IZMERENIE_TYPE("izmerenieType", "Тип контрольно-измерительного оборудования"),
    KONVEIER_TYPE("konveierType", "Тип конвейерного оборудования"),
    SVARKA_TYPE("svarkaType", "Тип паяльного и сварочного оборудования"),
    NASOS_TYPE("nasosType", "Тип насосного и компрессорного оборудования"),
    KLIMAT_TYPE("klimatType", "Тип климатического оборудования"),
    PACK_TYPE("packType", "Тип фасовочного и упаковочного оборудования"),
    SPEC_TYPE("specType", "Вид специализированного оборудования"),
    MATERIAL_TYPE("materialType", "Материал обработки"),
    CHPU("chpu", "Станок с ЧПУ"),
    PICTURE_COUNT("", "");

    private final String fieldName;
    private final String fieldDescr;

    VAvitoMovableParams(String fieldName, String fieldDescr) {
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
