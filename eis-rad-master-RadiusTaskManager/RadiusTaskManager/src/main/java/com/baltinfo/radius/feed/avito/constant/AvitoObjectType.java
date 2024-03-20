package com.baltinfo.radius.feed.avito.constant;

import java.util.Arrays;

public enum AvitoObjectType {

    SC3(3L, "Промназначения"),
    SC4(4L, "Поселений (ИЖС)"),
    SC5(5L, "Поселений (ИЖС)"),
    SC6(6L, "Промназначения"),
    SC7(7L, "Промназначения"),
    SC8(8L, "Промназначения"),
    SC9(9L, "Поселений (ИЖС)"),
    SC10(10L, "Промназначения"),
    SC11(11L, "Промназначения"),
    SC12(12L, "Сельхозназначения (СНТ, ДНП)"),
    SC13(13L, "Промназначения"),
    SC14(14L, "Промназначения"),
    SC15(15L, "Промназначения"),
    SC16(16L, "Промназначения"),
    SC18(18L, "Офисное помещение"),
    SC19(19L, "Торговое помещение"),
    SC20(20L, "Гостиница"),
    SC21(21L, "Помещение общественного питания"),
    SC22(22L, "Помещение свободного назначения"),
    SC23(23L, "Офисное помещение"),
    SC24(24L, "Торговое помещение"),
    SC25(25L, "Производственное помещение"),
    SC26(26L, "Гараж"),
    SC27(27L, "Помещение свободного назначения"),
    SC28(28L, "Производственное помещение"),
    SC29(29L, "Промназначения"),
    SC30(30L, "Промназначения"),
    SC31(31L, "Помещение свободного назначения"),
    SC32(32L, "Помещение свободного назначения"),
    SC37(37L, "Таунхаус"),
    SC38(38L, "Коттедж"),
    SC39(39L, "Дача"),
    SC40(40L, "Здание"),
    SC88(88L, "Поселений (ИЖС)"),
    SC89(89L, "Поселений (ИЖС)"),
    SC90(90L, "Складское помещение");


    private final Long scUnid;
    private final String objectType;

    AvitoObjectType(Long scUnid, String objectType) {
        this.scUnid = scUnid;
        this.objectType = objectType;
    }

    public Long getScUnid() {
        return scUnid;
    }

    public String getObjectType() {
        return objectType;
    }

    public static AvitoObjectType getByValue(Long scUnid) {
        return Arrays.stream(values())
                .filter(t -> t.getScUnid().equals(scUnid))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("AvitoObjectType not found by scUnid = " + scUnid));
    }
}
