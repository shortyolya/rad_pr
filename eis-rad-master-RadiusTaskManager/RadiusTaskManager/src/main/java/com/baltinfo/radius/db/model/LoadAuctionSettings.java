/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lia
 */
@Entity
@Table(name = "load_auction_settings", catalog = "", schema = "web")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "LoadAuctionSettings.findAll", query = "SELECT l FROM LoadAuctionSettings l"),
        @NamedQuery(name = "LoadAuctionSettings.findByLasUnid", query = "SELECT l FROM LoadAuctionSettings l WHERE l.lasUnid = :lasUnid"),
        @NamedQuery(name = "LoadAuctionSettings.findBySourceForTypeAuction", query = "SELECT l FROM LoadAuctionSettings l, LoadSourceSettings lss " +
                "WHERE lss = l.lssUnid AND lss.indActual = 1 AND l.indActual = 1 and lss.lsUnid.lsUnid = :lsUnid and trim(lower(l.lasTypeAuctionName)) = :typeAuctionName"),
        @NamedQuery(name = "LoadAuctionSettings.findByTypeAuctionAndStepForm", query = "SELECT l FROM LoadAuctionSettings l, LoadSourceSettings lss " +
                "WHERE lss = l.lssUnid AND lss.indActual = 1 AND l.indActual = 1 and lss.lsUnid.lsUnid = :lsUnid " +
                " and l.typeAuctionUnid = :typeAuctionUnid and l.lasStepForm = :lasStepForm")})
public class LoadAuctionSettings implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "las_unid")
    private Long lasUnid;
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
    @Column(name = "las_type_auction_name")
    private String lasTypeAuctionName;
    @JoinColumn(name = "auction_unid", referencedColumnName = "auction_unid")
    @ManyToOne(optional = false)
    private Auction auctionUnid;
    @JoinColumn(name = "lss_unid", referencedColumnName = "lss_unid")
    @ManyToOne(optional = false)
    private LoadSourceSettings lssUnid;
    @Column(name = "type_auction_unid")
    private Long typeAuctionUnid;
    @Column(name = "las_step_form")
    private Integer lasStepForm;

    public LoadAuctionSettings() {
    }

    public Long getLasUnid() {
        return lasUnid;
    }

    public void setLasUnid(Long lasUnid) {
        this.lasUnid = lasUnid;
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

    public String getLasTypeAuctionName() {
        return lasTypeAuctionName;
    }

    public void setLasTypeAuctionName(String lasTypeAuctionName) {
        this.lasTypeAuctionName = lasTypeAuctionName;
    }

    public Auction getAuctionUnid() {
        return auctionUnid;
    }

    public void setAuctionUnid(Auction auctionUnid) {
        this.auctionUnid = auctionUnid;
    }

    public LoadSourceSettings getLssUnid() {
        return lssUnid;
    }

    public void setLssUnid(LoadSourceSettings lssUnid) {
        this.lssUnid = lssUnid;
    }

    public Long getTypeAuctionUnid() {
        return typeAuctionUnid;
    }

    public void setTypeAuctionUnid(Long typeAuctionUnid) {
        this.typeAuctionUnid = typeAuctionUnid;
    }

    public Integer getLasStepForm() {
        return lasStepForm;
    }

    public void setLasStepForm(Integer lasStepForm) {
        this.lasStepForm = lasStepForm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lasUnid != null ? lasUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoadAuctionSettings)) {
            return false;
        }
        LoadAuctionSettings other = (LoadAuctionSettings) object;
        return (this.lasUnid != null || other.lasUnid == null) && (this.lasUnid == null || this.lasUnid.equals(other.lasUnid));
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.LoadAuctionSettings[ lasUnid=" + lasUnid + " ]";
    }

}
