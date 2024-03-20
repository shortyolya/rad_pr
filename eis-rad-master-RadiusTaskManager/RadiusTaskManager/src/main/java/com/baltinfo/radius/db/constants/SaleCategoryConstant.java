package com.baltinfo.radius.db.constants;

/**
 * @author AAA
 * @since 01.06.2022
 */
public enum SaleCategoryConstant {

    OFFICE_BUILDINGS(18L),
    TRADE_BUILDINGS(19L),
    HOTELS(20L),
    CATERING_FACILITIES(21L),
    MANSIONS(22L), //Особняки, ОСЗ, здания свободного назначения
    STREET_RETAIL_OBJECTS(24L), //Объекты стрит-ритейла
    NON_LIVING_ROOM(23L), //Нежилые встроенные помещения
    SPORT_OBJECTS(27L), //Объекты спорта и ФОК
    CAREERS(30L),
    CONSTRUCTIONS_IN_PROGRESS(31L), //Объекты незавершенного строительства
    OTHER_BUILDING(32L), //Иное
    PROPERTY_COMPLEXES(40L), //Имущественные комплексы (земельные участки со зданиями)
    STORAGE_FACILITIES(90L),
    TEMP_NFA(0L); // Временная категория для загрузки свойств НФА

    private final Long scUnid;

    SaleCategoryConstant(Long scUnid) {
        this.scUnid = scUnid;
    }

    public Long getCode() {
        return scUnid;
    }
}
