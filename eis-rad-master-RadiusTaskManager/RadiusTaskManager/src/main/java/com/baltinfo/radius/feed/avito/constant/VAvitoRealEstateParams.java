package com.baltinfo.radius.feed.avito.constant;


import com.baltinfo.radius.db.constants.SaleCategoryConstant;

import java.util.Arrays;
import java.util.List;

import static com.baltinfo.radius.db.constants.SaleCategoryConstant.CATERING_FACILITIES;
import static com.baltinfo.radius.db.constants.SaleCategoryConstant.CONSTRUCTIONS_IN_PROGRESS;
import static com.baltinfo.radius.db.constants.SaleCategoryConstant.HOTELS;
import static com.baltinfo.radius.db.constants.SaleCategoryConstant.MANSIONS;
import static com.baltinfo.radius.db.constants.SaleCategoryConstant.NON_LIVING_ROOM;
import static com.baltinfo.radius.db.constants.SaleCategoryConstant.OFFICE_BUILDINGS;
import static com.baltinfo.radius.db.constants.SaleCategoryConstant.OTHER_BUILDING;
import static com.baltinfo.radius.db.constants.SaleCategoryConstant.PROPERTY_COMPLEXES;
import static com.baltinfo.radius.db.constants.SaleCategoryConstant.SPORT_OBJECTS;
import static com.baltinfo.radius.db.constants.SaleCategoryConstant.STORAGE_FACILITIES;
import static com.baltinfo.radius.db.constants.SaleCategoryConstant.STREET_RETAIL_OBJECTS;
import static com.baltinfo.radius.db.constants.SaleCategoryConstant.TRADE_BUILDINGS;

public enum VAvitoRealEstateParams {

    OME_UNID("omeUnid", "Идентификатор маркетингового мероприятия", null),
    OBJ_UNID("objUnid", "Идентификатор объекта", null),
    MEV_UNID("mevUnid", "Идентификатор маркетингового мероприятия", null),
    OBJ_CODE("objCode", "Код объекта", null),
    LS_UNID("lsUnid", "Источник загрузки", null),
    SC_UNID("scUnid", "Категория", null),
    FC_UNID("fcUnid", "Категория фида", null),
    CATEGORY("category", "Категория", null),
    CATEGORY_FEED_CODE("categoryFeedCode", "Код категории фида", null),
    TITLE("title", "Заголовок объявления", null),
    DESCRIPTION("description", "Текст объявления", null),
    SQUARE("square", "Общая площадь помещений", null),
    LAND_AREA("landArea", "Общая площадь земельных участков", null),
    FLOOR("floor", "Этаж", null),
    FLOORS("floors", "Количество этажей в здании", null),
    MARKET_TYPE("marketType", "Тип жилья", null),
    ROOMS("rooms", "Количество комнат", null),
    HOUSE_TYPE("houseType", "Тип дома", null),
    PRICE("price", "Стоимость (аукцион), цена отсечения (голландский аукцион), стоимость на текущем периоде (ППП)", null),
    ADDRESS("address", "Адрес", null),
    WALLS_TYPE("wallsType", "Материал стен", null),
    MATERIAL_OF_GARAGE("materialOfGarage", "Материл гаража", null),
    SECURED("secured", "Охрана парковки", null),
    TYPE_OF_PARKING("typeOfParking", "Тип паркинга", null),
    TYPE_OF_GARAGE("typeOfGarage", "Тип гаража", null),
    STATUS("status", "Статус недвижимости", null),
    KITCHEN_SPACE("kitchenSpace", "Площадь кухни", null),
    BALCONY_OR_LOGGIA_MULTI("balconyOrLoggiaMulti", "Наличие балкона", null),
    DEAL_TYPE("dealType", "Тип продажи", null),
    ROOM_TYPE("roomType", "Тип комнаты (комнат)", null),
    SUBAGENT_MOBILE_PHONE("subagentMobilePhone", "Менеджер продаж: телефон", null),
    SUBAGENT_NAME_F("subagentNameF", "Менеджер продаж: фамилия", null),
    SUBAGENT_NAME_I("subagentNameI", "Менеджер продаж: имя", null),
    OBJ_IND_RENT("objIndRent", "Признак аренды", null),
    LEASE_COMMISSION_SIZE("leaseCommissionSize", "Размер комиссии в %", null),
    DEVELOPMENT_ID("developmentId", "Объект новостройки", null),
    TS_UNID("tsUnid", "Статус объекта", null),
    PICTURE_COUNT("", "", null),
    BATHROOM_MULTI("bathroomMulti", "Санузел", null),
    LAND_STATUS("landStatus", "Статус участка", null),
    OBJECT_TYPE("objectType", "Статус земельного участка", null),
    BUILDING_TYPE("buildingType", "Тип здания", null),
    RENOVATION("renovation", "Ремонт", null),

    DECORATION_COMM("decoration", "Отделка помещения", null),
    PARKING_TYPE("parkingType", "Парковка", Arrays.asList(OFFICE_BUILDINGS, NON_LIVING_ROOM,
            SPORT_OBJECTS, MANSIONS, CONSTRUCTIONS_IN_PROGRESS, OTHER_BUILDING, PROPERTY_COMPLEXES,
            TRADE_BUILDINGS, STREET_RETAIL_OBJECTS,
            CATERING_FACILITIES,
            HOTELS)),
    ENTRANCE("entrance", "Вход", Arrays.asList(SPORT_OBJECTS, MANSIONS, CONSTRUCTIONS_IN_PROGRESS, OTHER_BUILDING, PROPERTY_COMPLEXES,
            TRADE_BUILDINGS, STREET_RETAIL_OBJECTS,
            CATERING_FACILITIES)),
    LAYOUT("layout", "Тип планировки", Arrays.asList(OFFICE_BUILDINGS, NON_LIVING_ROOM, STORAGE_FACILITIES));
    private final String fieldName;
    private final String fieldDescr;
    
    private final List<SaleCategoryConstant> saleCategories;

    VAvitoRealEstateParams(String fieldName, String fieldDescr, List<SaleCategoryConstant> saleCategories) {
        this.fieldName = fieldName;
        this.fieldDescr = fieldDescr;
        this.saleCategories = saleCategories;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldDescr() {
        return fieldDescr;
    }

    public List<SaleCategoryConstant> getSaleCategories() {
        return saleCategories;
    }
}
