package com.baltinfo.radius.avito.dto.truck;

/**
 * @author Andrei Shymko
 * @since 09.11.2020
 */
public enum TruckProperty {
    TRUCK_MAKE("TruckMake", "Марка грузовика"),
    TRUCK_MODEL("TruckModel", "Модель грузовика"),
    TRUCK_BODY_TYPE("TruckBodyType", "Тип кузова грузовика");

    private final String type;
    private final String name;

    TruckProperty(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}