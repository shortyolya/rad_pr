package com.baltinfo.radius.db.model;

/**
 * @author Suvorina Aleksandra
 * @since 08.09.2020
 */
public interface FeedObject {

    Long getObjUnid();

    Long getLsUnid();

    void setPictureCount(int pictureCount);

    int getPictureCount();

    String getLotEtpCode();

    String getDescription();

    String getEtpLink();

    Long getOrgSubUnid();
}
