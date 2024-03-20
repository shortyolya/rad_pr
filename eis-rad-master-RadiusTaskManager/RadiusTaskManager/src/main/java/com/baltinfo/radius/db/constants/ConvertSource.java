package com.baltinfo.radius.db.constants;

/**
 * @author sas
 */
public enum ConvertSource {

    /**
     * Сайт auction-house
     */
    AUCTION_HOUSE(1),
    /**
     * ЭТП lot-online
     */
    LOT_ONLINE(2),
    /**
     * ЭТП банкротство
     */
    BANKRUPTCY(3);

    private final long unid;

    ConvertSource(long unid) {
        this.unid = unid;
    }

    public long getUnid() {
        return unid;
    }

}
