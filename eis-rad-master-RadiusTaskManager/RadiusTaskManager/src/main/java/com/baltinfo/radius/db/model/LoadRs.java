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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
 * @author lia
 */
@Entity
@Table(name = "load_rs", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"lrs_unid"})})
@SequenceGenerator(name = "seq_load_rs", sequenceName = "seq_load_rs", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "LoadRs.findAll", query = "SELECT l FROM LoadRs l"),
        @NamedQuery(name = "LoadRs.findByLrsUnid", query = "SELECT l FROM LoadRs l WHERE l.lrsUnid = :lrsUnid"),
        @NamedQuery(name = "LoadRs.findForTransferByLaUnid", query = "SELECT l FROM LoadRs l WHERE l.indActual = 1 AND l.laUnid.laUnid = :laUnid Order by lrsUnid")})
public class LoadRs implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_load_rs")
    @Column(name = "lrs_unid")
    private Long lrsUnid;
    @Column(name = "ind_actual")
    private Integer indActual;
    @Column(name = "date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateB;
    @Column(name = "found_b")
    private String foundB;
    @Column(name = "pers_code_b")
    private Long persCodeB;
    @Column(name = "date_b_rec")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBRec;
    @Column(name = "pers_code_b_rec")
    private Long persCodeBRec;
    @Column(name = "lrs_change_price_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lrsChangePriceDate;
    @Column(name = "lrs_appl_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lrsApplDate;
    @Column(name = "lrs_summ")
    private BigDecimal lrsSumm;
    @Column(name = "lrs_deposit_summ")
    private BigDecimal lrsDepositSumm;
    @Column(name = "lrs_calc_price_alg")
    private String lrsCalcPriceAlg;
    @Column(name = "lrs_lots")
    private String lrsLots;
    @JoinColumn(name = "la_unid", referencedColumnName = "la_unid")
    @ManyToOne(optional = false)
    private LoadAuction laUnid;

    public LoadRs() {
    }

    public Long getLrsUnid() {
        return lrsUnid;
    }

    public void setLrsUnid(Long lrsUnid) {
        this.lrsUnid = lrsUnid;
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

    public Date getLrsChangePriceDate() {
        return lrsChangePriceDate;
    }

    public void setLrsChangePriceDate(Date lrsChangePriceDate) {
        this.lrsChangePriceDate = lrsChangePriceDate;
    }

    public Date getLrsApplDate() {
        return lrsApplDate;
    }

    public void setLrsApplDate(Date lrsApplDate) {
        this.lrsApplDate = lrsApplDate;
    }

    public BigDecimal getLrsSumm() {
        return lrsSumm;
    }

    public void setLrsSumm(BigDecimal lrsSumm) {
        this.lrsSumm = lrsSumm;
    }

    public BigDecimal getLrsDepositSumm() {
        return lrsDepositSumm;
    }

    public void setLrsDepositSumm(BigDecimal lrsDepositSumm) {
        this.lrsDepositSumm = lrsDepositSumm;
    }

    public String getLrsCalcPriceAlg() {
        return lrsCalcPriceAlg;
    }

    public void setLrsCalcPriceAlg(String lrsCalcPriceAlg) {
        this.lrsCalcPriceAlg = lrsCalcPriceAlg;
    }

    public LoadAuction getLaUnid() {
        return laUnid;
    }

    public void setLaUnid(LoadAuction laUnid) {
        this.laUnid = laUnid;
    }

    public String getLrsLots() {
        return lrsLots;
    }

    public void setLrsLots(String lrsLots) {
        this.lrsLots = lrsLots;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lrsUnid != null ? lrsUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoadRs)) {
            return false;
        }
        LoadRs other = (LoadRs) object;
        return (this.lrsUnid != null || other.lrsUnid == null) && (this.lrsUnid == null || this.lrsUnid.equals(other.lrsUnid));
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.LoadRs[ lrsUnid=" + lrsUnid + " ]";
    }



}
