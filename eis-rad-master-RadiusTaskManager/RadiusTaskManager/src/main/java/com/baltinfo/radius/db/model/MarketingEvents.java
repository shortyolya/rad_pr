package com.baltinfo.radius.db.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ssv
 * @since 09.07.2019
 */
@Entity
@Table(name = "marketing_events", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"mev_unid"})})
@NamedQueries({
        @NamedQuery(name = "MarketingEvents.findAll",
                query = "SELECT v FROM MarketingEvents v where v.indActual = 1 order by v.mevUnid")})
@XmlRootElement
public class MarketingEvents implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "mev_unid", nullable = false, precision = 2147483647, scale = 0)
    private long mevUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
    @Column(name = "ind_actual")
    private Integer indActual;
    @Column(name = "date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateB;
    @Column(name = "found_b", length = 500)
    private String foundB;
    @Column(name = "pers_code_b")
    private Long persCodeB;
    @Column(name = "date_b_rec")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBRec;
    @Column(name = "pers_code_b_rec")
    private Long persCodeBRec;
    @Column(name = "mev_name", length = 500)
    private String mevName;

    public MarketingEvents() {
    }

    public MarketingEvents(long mevUnid) {
        this.mevUnid = mevUnid;
    }

    public long getMevUnid() {
        return mevUnid;
    }

    public void setMevUnid(long mevUnid) {
        this.mevUnid = mevUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    public Integer getIndActual() {
        return indActual;
    }

    public void setIndActual(Integer indActual) {
        this.indActual = indActual;
    }

    public Date getDateB() {
        return dateB;
    }

    public void setDateB(Date dateB) {
        this.dateB = dateB;
    }

    public String getFoundB() {
        return foundB;
    }

    public void setFoundB(String foundB) {
        this.foundB = foundB;
    }

    public Long getPersCodeB() {
        return persCodeB;
    }

    public void setPersCodeB(Long persCodeB) {
        this.persCodeB = persCodeB;
    }

    public Date getDateBRec() {
        return dateBRec;
    }

    public void setDateBRec(Date dateBRec) {
        this.dateBRec = dateBRec;
    }

    public Long getPersCodeBRec() {
        return persCodeBRec;
    }

    public void setPersCodeBRec(Long persCodeBRec) {
        this.persCodeBRec = persCodeBRec;
    }

    public String getMevName() {
        return mevName;
    }

    public void setMevName(String mevName) {
        this.mevName = mevName;
    }

    @Override
    public String toString() {
        return "com.baltinfo.radius.db.model.MarketingEvents[ mevUnid=" + mevUnid + " ]";
    }

}
