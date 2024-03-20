package com.baltinfo.radius.db.model;

import java.util.Date;

/**
 * <p>
 * </p>
 *
 * @author Lapenok Igor
 * @since 16.08.2018
 */
public interface IHistory {

    Integer getIndActual();

    void setIndActual(Integer indActual);

    Date getDateB();

    void setDateB(Date dateB);

    String getFoundB();

    void setFoundB(String foundB);

    Long getPersCodeB();

    void setPersCodeB(Long persCodeB);

    Date getDateBRec();

    void setDateBRec(Date dateBRec);

    Long getPersCodeBRec();

    void setPersCodeBRec(Long persCodeBRec);
}

