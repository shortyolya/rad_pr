package com.baltinfo.radius.db.constants;

/**
 * @author Suvorina Aleksandra
 * @since 28.06.2021
 */
public enum AuctionParticipantLimitation {
    /**
     * Открытые по составу участников
     */
    OPEN(0),
    /**
     * Закрытые по составу участников
     */
    CLOSE(1);

    private final int code;

    AuctionParticipantLimitation(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
