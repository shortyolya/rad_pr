/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author css
 */
@Entity
@Table(name = "rate", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"rate_unid"})})
@SequenceGenerator(name = "seq_rate", sequenceName = "seq_rate", allocationSize = 1)
@XmlRootElement
public class Rate implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_rate")
    @Column(name = "rate_unid", nullable = false, precision = 2147483647, scale = 0)
    private long rateUnid;
    @Column(name = "found_unid")
    private Integer foundUnid;
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
    @Column(name = "rate_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rateDate;
    @Column(name = "rate_value", precision = 12, scale = 6)
    private BigDecimal rateValue;
    @Column(name = "rate_for")
    private Integer rateFor;
    @Basic(optional = false)
    @Column(name = "cur_unid")
    private Long curUnid;

    public Rate() {
    }

    public long getRateUnid() {
        return rateUnid;
    }

    public void setRateUnid(long rateUnid) {
        this.rateUnid = rateUnid;
    }

    public Integer getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Integer foundUnid) {
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

    public Date getRateDate() {
        return rateDate;
    }

    public void setRateDate(Date rateDate) {
        this.rateDate = rateDate;
    }

    public BigDecimal getRateValue() {
        return rateValue;
    }

    public void setRateValue(BigDecimal rateValue) {
        this.rateValue = rateValue;
    }

    public Integer getRateFor() {
        return rateFor;
    }

    public void setRateFor(Integer rateFor) {
        this.rateFor = rateFor;
    }

    public Long getCurUnid() {
        return curUnid;
    }

    public void setCurUnid(Long curUnid) {
        this.curUnid = curUnid;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.Rate[ rateUnid=" + rateUnid + " ]";
    }

}
