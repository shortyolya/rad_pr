package com.baltinfo.radius.db.constants;

/**
 * @author Suvorina Aleksandra
 * @since 08.04.2019
 */
public enum LOParticipationRequestStatus {

    PENDING("PENDING"),
    REJECTED("REJECTED"),
    LOT_OWNER_APPROVED("LOT_OWNER_APPROVED"),
    CANCELED("CANCELED");

    private final String code;

    LOParticipationRequestStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
