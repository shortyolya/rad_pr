package com.baltinfo.radius.db.dto;

import com.baltinfo.radius.db.model.FeedObject;

import java.math.BigDecimal;

/**
 * @author Suvorina Aleksandra
 * @since 09.09.2020
 */
public class FeedObjectDto{

    private final Long objUnid;
    private final Long lsUnid;
    private final Boolean toAvito;
    private final Boolean toCian;
    private final Boolean toJCat;

    public FeedObjectDto(Long objUnid, Long lsUnid, Boolean toAvito, Boolean toCian, Boolean toJCat) {
        this.objUnid = objUnid;
        this.lsUnid = lsUnid;
        this.toAvito = toAvito;
        this.toCian = toCian;
        this.toJCat = toJCat;
    }

    public FeedObjectDto(Object[] sqlResult) {
        this.objUnid = ((BigDecimal) sqlResult[0]).longValue();
        this.lsUnid = sqlResult[1] != null ? ((BigDecimal) sqlResult[1]).longValue() : null;
        this.toAvito = sqlResult[2] != null ? (Boolean) sqlResult[2] : false;
        this.toCian = sqlResult[3] != null ? (Boolean) sqlResult[3] : false;
        this.toJCat = sqlResult[4] != null ? (Boolean) sqlResult[4] : false;
    }

    public Long getObjUnid() {
        return objUnid;
    }

    public Long getLsUnid() {
        return lsUnid;
    }

    public Boolean getToAvito() {
        return toAvito;
    }

    public Boolean getToCian() {
        return toCian;
    }

    public Boolean getToJCat() {
        return toJCat;
    }
}
