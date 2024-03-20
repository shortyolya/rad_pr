package com.baltinfo.radius.feed.avito.constant;

import java.util.Arrays;

/**
 * @author Kulikov Semyon
 * @since 09.11.2020
 */

public enum AvitoMaxPhotoCount {
    FC2(2L, 40),
    FC3(3L, 40),
    FC4(4L, 40),
    FC5(5L, 40),
    FC6(6L, 40),
    FC7(7L, 40),
    FC8(8L, 20),
    FC9(9L, 20),
    FC10(10L, 10),
    FC11(11L, 10),
    FC12(12L, 10),
    FC13(13L, 10),
    FC14(14L, 10),
    FC15(15L, 10),
    FC24(24L, 10),
    FC25(25L, 10),
    FC26(26L, 40),
    FC27(27L, 40),
    FC28(28L, 40),
    FC29(29L, 40),
    FC30(30L, 40),
    FC31(31L, 40),
    FC32(32L, 20),
    FC33(33L, 20),
    FC34(34L, 10),
    FC35(35L, 10),
    FC36(36L, 10),
    FC37(37L, 10),
    FC38(38L, 10),
    FC39(39L, 10),
    FC40(40L, 10),
    FC41(41L, 10);



    private final Long fcUnid;
    private final int photoCount;

    AvitoMaxPhotoCount(Long fcUnid, int photoCount) {
        this.fcUnid = fcUnid;
        this.photoCount = photoCount;
    }

    public static AvitoMaxPhotoCount getByValue(Long fcUnid) {
        return Arrays.stream(values())
                .filter(t -> t.fcUnid.equals(fcUnid))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("AvitoMaxPhotoCount not found by fcUnid = " + fcUnid));
    }

    public int getPhotoCount() {
        return photoCount;
    }
}
