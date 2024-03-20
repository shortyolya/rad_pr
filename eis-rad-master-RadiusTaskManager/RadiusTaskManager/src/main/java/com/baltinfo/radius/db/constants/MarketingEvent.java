package com.baltinfo.radius.db.constants;

import java.util.Arrays;

/**
 * @author Suvorina Aleksandra
 * @since 07.09.2020
 */
public enum MarketingEvent {

    AVITO(2L),
    CIAN(4L),
    JCAT(6L),
    LINK_SITE(7L),      //Витрина/ЭТП
    PLEDGE_URL(8L),     //Залоги/Все залоги
    RAD_SITE(9L);       //Сайт РАД

    private final Long unid;

    MarketingEvent(Long unid) {
        this.unid = unid;
    }

    public Long getUnid() {
        return unid;
    }

    public static MarketingEvent getByValue(Long value) {
        return Arrays.stream(values())
                .filter(item -> item.unid.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("MarketingEvent not found by unid = " + value));
    }
}
