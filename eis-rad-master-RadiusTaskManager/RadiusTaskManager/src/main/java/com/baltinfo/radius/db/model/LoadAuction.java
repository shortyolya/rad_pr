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
import java.util.Date;

/**
 * @author lia
 */
@Entity
@Table(name = "load_auction", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"la_unid"})})
@SequenceGenerator(name = "seq_load_auction", sequenceName = "seq_load_auction", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "LoadAuction.findAll", query = "SELECT l FROM LoadAuction l"),
        @NamedQuery(name = "LoadAuction.findByLaUnid", query = "SELECT l FROM LoadAuction l WHERE l.laUnid = :laUnid"),
        @NamedQuery(name = "LoadAuction.findByStatus", query = "SELECT l FROM LoadAuction l WHERE " +
                "l.lstUnid.lstUnid = :lstUnid and l.indActual = 1")
})
public class LoadAuction implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_load_auction")
    @Column(name = "la_unid")
    private Long laUnid;
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
    @JoinColumn(name = "auction_unid", referencedColumnName = "auction_unid")
    @ManyToOne
    private Auction auctionUnid;
    @JoinColumn(name = "las_unid", referencedColumnName = "las_unid")
    @ManyToOne
    private LoadAuctionSettings lasUnid;
    @JoinColumn(name = "ls_unid", referencedColumnName = "ls_unid")
    @ManyToOne(optional = false)
    private LoadSource lsUnid;
    @JoinColumn(name = "lst_unid", referencedColumnName = "lst_unid")
    @ManyToOne(optional = false)
    private LoadStatus lstUnid;
    @Column(name = "pa_unid")
    private Long paUnid;
    @Column(name = "sg_unid")
    private Long sgUnid;
    @Column(name = "sm_unid")
    private Long smUnid;
    @Column(name = "sub_unid")
    private Long subUnid;
    @Column(name = "to_unid")
    private Long toUnid;
    @Column(name = "tpa_unid")
    private Long tpaUnid;
    @Column(name = "ba_unid")
    private Long baUnid;
    @Column(name = "la_file_path")
    private String laFilePath;
    @Column(name = "la_error_info")
    private String laErrorInfo;
    @Column(name = "la_stage_num")
    private Integer laStageNum;
    @Column(name = "la_auction_name")
    private String laAuctionName;
    @JoinColumn(name = "deal_unid", referencedColumnName = "deal_unid")
    @ManyToOne
    private Deal dealUnid;

    public LoadAuction() {
    }

    public Long getLaUnid() {
        return laUnid;
    }

    public void setLaUnid(Long laUnid) {
        this.laUnid = laUnid;
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

    public Auction getAuctionUnid() {
        return auctionUnid;
    }

    public void setAuctionUnid(Auction auctionUnid) {
        this.auctionUnid = auctionUnid;
    }

    public LoadAuctionSettings getLasUnid() {
        return lasUnid;
    }

    public void setLasUnid(LoadAuctionSettings lasUnid) {
        this.lasUnid = lasUnid;
    }

    public LoadSource getLsUnid() {
        return lsUnid;
    }

    public void setLsUnid(LoadSource lsUnid) {
        this.lsUnid = lsUnid;
    }

    public LoadStatus getLstUnid() {
        return lstUnid;
    }

    public void setLstUnid(LoadStatus lstUnid) {
        this.lstUnid = lstUnid;
    }

    public Long getPaUnid() {
        return paUnid;
    }

    public void setPaUnid(Long paUnid) {
        this.paUnid = paUnid;
    }

    public Long getSgUnid() {
        return sgUnid;
    }

    public void setSgUnid(Long sgUnid) {
        this.sgUnid = sgUnid;
    }

    public Long getSmUnid() {
        return smUnid;
    }

    public void setSmUnid(Long smUnid) {
        this.smUnid = smUnid;
    }

    public Long getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(Long subUnid) {
        this.subUnid = subUnid;
    }

    public Long getToUnid() {
        return toUnid;
    }

    public void setToUnid(Long toUnid) {
        this.toUnid = toUnid;
    }

    public Long getTpaUnid() {
        return tpaUnid;
    }

    public void setTpaUnid(Long tpaUnid) {
        this.tpaUnid = tpaUnid;
    }

    public String getLaFilePath() {
        return laFilePath;
    }

    public void setLaFilePath(String laFilePath) {
        this.laFilePath = laFilePath;
    }

    public String getLaErrorInfo() {
        return laErrorInfo;
    }

    public void setLaErrorInfo(String laErrorInfo) {
        this.laErrorInfo = laErrorInfo;
    }

    public Long getBaUnid() {
        return baUnid;
    }

    public void setBaUnid(Long baUnid) {
        this.baUnid = baUnid;
    }

    public Integer getLaStageNum() {
        return laStageNum;
    }

    public void setLaStageNum(Integer laStageNum) {
        this.laStageNum = laStageNum;
    }

    public String getLaAuctionName() {
        return laAuctionName;
    }

    public void setLaAuctionName(String laAuctionName) {
        this.laAuctionName = laAuctionName;
    }

    public Deal getDealUnid() {
        return dealUnid;
    }

    public void setDealUnid(Deal dealUnid) {
        this.dealUnid = dealUnid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (laUnid != null ? laUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoadAuction)) {
            return false;
        }
        LoadAuction other = (LoadAuction) object;
        return (this.laUnid != null || other.laUnid == null) && (this.laUnid == null || this.laUnid.equals(other.laUnid));
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.LoadAuction[ laUnid=" + laUnid + " ]";
    }

}
