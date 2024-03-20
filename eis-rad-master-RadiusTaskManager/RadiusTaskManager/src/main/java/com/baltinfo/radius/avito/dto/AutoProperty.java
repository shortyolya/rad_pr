package com.baltinfo.radius.avito.dto;

/**
 * @author Igor Lapenok
 * @since 11.09.2020
 */
public enum AutoProperty {
    MAKE("Make"),
    MODEL("Model"),
    GENERATION("Generation"),
    MODIFICATION("Modification"),
    YEAR_TO("YearTo"),
    POWER("Power"),
    TRANSMISSION("Transmission"),
    YEAR_FROM("YearFrom"),
    DOORS("Doors"),
    DRIVE_TYPE("DriveType"),
    ENGINE_SIZE("EngineSize"),
    BODY_TYPE("BodyType"),
    FUEL_TYPE("FuelType");

    private final String type;

    AutoProperty(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
