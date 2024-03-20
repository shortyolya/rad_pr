package com.baltinfo.radius.bankruptcy;

/**
 * Направления продаж на ЭТП "Банкростсво"
 *
 * @author Suvorina Aleksandra
 * @since 23.08.2018
 */
public enum BkrSaleGuides {

    /**
     * Продажа имущества должников
     */
    DEBITOR_PROPERTY(22L, 390L),
    /**
     * Продажа имущества частных собственников
     */
    PRIVATE_PROPERTY(42L, 146L);

    private final Long sgUnid;
    private final Long tdoUnid;

    BkrSaleGuides(long sgUnid, Long tdoUnid) {
        this.sgUnid = sgUnid;
        this.tdoUnid = tdoUnid;
    }

    public long getSgUnid() {
        return sgUnid;
    }

    public Long getTdoUnid() {
        return tdoUnid;
    }
}
