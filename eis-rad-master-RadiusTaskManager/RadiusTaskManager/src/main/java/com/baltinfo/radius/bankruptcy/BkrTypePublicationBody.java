package com.baltinfo.radius.bankruptcy;

/**
 * @author Suvorina Aleksandra
 * @since 06.09.2018
 */
public enum BkrTypePublicationBody {

    PICTURE(3),
    HTML(5),
    GALLERY(6),
    ANY(7),
    ADDITIONAL_PROPERTIES(8);

    private final long unid;

    BkrTypePublicationBody(long unid) {
        this.unid = unid;
    }

    public long getUnid() {
        return unid;
    }
}
