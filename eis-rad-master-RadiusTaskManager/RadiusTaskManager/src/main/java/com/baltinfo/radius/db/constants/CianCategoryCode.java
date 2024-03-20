package com.baltinfo.radius.db.constants;

import java.util.Arrays;

/**
 * @author Kulikov Semyon
 * @since 06.02.2020
 */

public enum CianCategoryCode {
    GARAGE("garageSale"),
    BUSINESS("businessSale"),
    BUILDING("buildingSale"),
    COMMERCIAL_LAND("commercialLandSale"),
    OFFICE("officeSale"),
    FREE_APPOINTMENT_OBJECT("freeAppointmentObjectSale"),
    INDUSTRY("industrySale"),
    WAREHOUSE("warehouseSale"),
    SHOPPING_AREA("shoppingAreaSale"),
    GARAGE_RENT("garageRent"),
    BUSINESS_RENT("businessRent"),
    BUILDING_RENT("buildingRent"),
    COMMERCIAL_LAND_RENT("commercialLandRent"),
    OFFICE_RENT("officeRent"),
    FREE_APPOINTMENT_OBJECT_RENT("freeAppointmentObjectRent"),
    INDUSTRY_RENT("industryRent"),
    WAREHOUSE_RENT("warehouseRent"),
    SHOPPING_AREA_RENT("shoppingAreaRent"),
    FLAT_SALE("flatSale"),
    FLAT_SHARE_SALE("flatShareSale"),
    NEW_BUILDING_FLAT_SALE("newBuildingFlatSale"),
    ROOM_SALE("roomSale"),
    HOUSE_SALE("houseSale"),
    COTTAGE_SALE("cottageSale"),
    TOWNHOUSE_SALE("townhouseSale"),
    LAND_SALE("landSale"),
    HOUSE_SHARE_SALE("houseShareSale");

    private final String code;

    CianCategoryCode(String code) {
        this.code = code;
    }

    public static CianCategoryCode getByValue(String value) {
        return Arrays.stream(values())
                .filter(item -> item.code.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("CianCategoryCode not found by code = " + value));
    }

    public String getCode() {
        return code;
    }
}
