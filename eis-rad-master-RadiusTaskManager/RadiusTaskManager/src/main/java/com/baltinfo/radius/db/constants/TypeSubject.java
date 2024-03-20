package com.baltinfo.radius.db.constants;

/**
 * @author Suvorina Aleksandra
 * @since 11.10.2018
 */
public enum TypeSubject {

    FL(1L),
    IP(3L),
    YL(2L);

    private final Long unid;

    TypeSubject(Long unid) {
        this.unid = unid;
    }

    public Long getUnid() {
        return unid;
    }
}
