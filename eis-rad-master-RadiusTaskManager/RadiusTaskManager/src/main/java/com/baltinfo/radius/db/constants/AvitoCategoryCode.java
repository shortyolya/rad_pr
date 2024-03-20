package com.baltinfo.radius.db.constants;

import java.util.Arrays;

public enum AvitoCategoryCode {

    FLAT("Квартиры"),
    ROOM("Комнаты"),
    HOUSE("Дома, дачи, коттеджи"),
    LAND_PLOT("Земельные участки"),
    GARAGE("Гаражи и машиноместа"),
    COMMERCIAL_REAL_ESTATE("Коммерческая недвижимость"),
    TRUCK("Грузовики и спецтехника"),
    WATER_TRANSPORT("Водный транспорт"),
    BUSINESS_EQUIPMENT("Оборудование для бизнеса"), //
    BUSINESS("Готовый бизнес"), //
    COMPUTER_PRODUCTS("Товары для компьютера"), //
    AUTO_PARTS("Запчасти и аксессуары"),
    COLLECTING("Коллекционирование"), //
    JEWELRY("Часы и украшения"), //
    AUTO("Автомобили"),
    FURNITURE("Мебель и интерьер"); //

    private final String code;

    AvitoCategoryCode(String code) {
        this.code = code;
    }

    public static AvitoCategoryCode getByValue(String value) {
        return Arrays.stream(values())
                .filter(item -> item.code.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("AvitoCategoryCode not found by code = " + value));
    }

    public String getCode() {
        return code;
    }
}
