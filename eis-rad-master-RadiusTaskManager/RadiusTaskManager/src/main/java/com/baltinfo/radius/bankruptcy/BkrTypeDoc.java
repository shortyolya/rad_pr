package com.baltinfo.radius.bankruptcy;

/**
 * Тип документа на ЭТП "Банкротство"
 *
 * @author Suvorina Aleksandra
 * @since 11.10.2018
 */
public enum BkrTypeDoc {

    PASSPORT(1L),
    TRUSTY(4L);

    private final Long unid;

    BkrTypeDoc(Long unid) {
        this.unid = unid;
    }

    public Long getUnid() {
        return unid;
    }
}
