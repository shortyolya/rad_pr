package com.baltinfo.radius.bankruptcy;

import java.math.BigDecimal;

/**
 * @author Suvorina Aleksandra
 * @since 14.10.2019
 */
public interface IBkrPublicationBodyParams {
    Long getTypePublicationBody();

    String getFileName();

    byte[] getFileBody();

    BigDecimal getPbNum();
}
