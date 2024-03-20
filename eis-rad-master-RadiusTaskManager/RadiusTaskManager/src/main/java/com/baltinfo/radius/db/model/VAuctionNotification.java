package com.baltinfo.radius.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Suvorina Aleksandra
 * @since 23.11.2018
 */
@Entity
@Table(name = "v_auction_notification", catalog = "", schema = "web")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "VAuctionNotification.findAll", query = "SELECT v FROM VAuctionNotification v")})
public class VAuctionNotification implements Serializable, EntityNotification {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "auction_unid")
    private Long auctionUnid;
    @Column(name = "auction_name")
    private String auctionName;
    @Column(name = "auction_recep_date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionRecepDateB;
    @Column(name = "auction_recep_date_e")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionRecepDateE;
    @Column(name = "auction_date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionDateB;
    @Column(name = "auction_date_e")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionDateE;
    @Column(name = "type_auction_unid")
    private Long typeAuctionUnid;
    @Column(name = "ts_unid")
    private Long tsUnid;
    @Column(name = "dp_unid")
    private Long dpUnid;
    @Column(name = "sub_owner_name")
    private String subOwnerName;
    @Column(name = "ind_asv")
    private Integer indAsv;
    @Column(name = "lots_count")
    private Integer lotsCount;
    @Column(name = "auction_recep_date_e_str")
    private String auctionRecepDateEStr;
    @Column(name = "auction_date_b_str")
    private String auctionDateBStr;
    @Column(name = "auction_etp_id")
    private Long auctionEtpId;
    @Column(name = "type_auction_code")
    private Long typeAuctionCode;
    @Column(name = "ls_unid")
    private Long lsUnid;
    @Column(name = "ls_name")
    private String lsName;
    @Column(name = "pa_curator_unid")
    private Long paCuratorUnid;
    @Column(name = "curator_name")
    private String curatorName;
    @Column(name = "auction_publication_date_str")
    private String auctionPublicationDateStr;
    @Column(name = "auction_ind_special_terms")
    private Boolean auctionIndSpecialTerms;

    public VAuctionNotification() {
    }

    public Long getAuctionUnid() {
        return auctionUnid;
    }

    @Override
    public Long getRedSchedUnid() {
        return null;
    }

    public void setAuctionUnid(Long auctionUnid) {
        this.auctionUnid = auctionUnid;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }

    public Date getAuctionRecepDateB() {
        return auctionRecepDateB;
    }

    public void setAuctionRecepDateB(Date auctionRecepDateB) {
        this.auctionRecepDateB = auctionRecepDateB;
    }

    public Date getAuctionRecepDateE() {
        return auctionRecepDateE;
    }

    public void setAuctionRecepDateE(Date auctionRecepDateE) {
        this.auctionRecepDateE = auctionRecepDateE;
    }

    public Date getAuctionDateB() {
        return auctionDateB;
    }

    public void setAuctionDateB(Date auctionDateB) {
        this.auctionDateB = auctionDateB;
    }

    public Date getAuctionDateE() {
        return auctionDateE;
    }

    public void setAuctionDateE(Date auctionDateE) {
        this.auctionDateE = auctionDateE;
    }

    public Long getDpUnid() {
        return dpUnid;
    }

    public void setDpUnid(Long dpUnid) {
        this.dpUnid = dpUnid;
    }

    public String getSubOwnerName() {
        return subOwnerName;
    }

    public void setSubOwnerName(String subOwnerName) {
        this.subOwnerName = subOwnerName;
    }

    public Integer getIndAsv() {
        return indAsv;
    }

    public void setIndAsv(Integer indAsv) {
        this.indAsv = indAsv;
    }

    public Integer getLotsCount() {
        return lotsCount;
    }

    public void setLotsCount(Integer lotsCount) {
        this.lotsCount = lotsCount;
    }

    public String getAuctionRecepDateEStr() {
        return auctionRecepDateEStr;
    }

    public void setAuctionRecepDateEStr(String auctionRecepDateEStr) {
        this.auctionRecepDateEStr = auctionRecepDateEStr;
    }

    public Long getAuctionEtpId() {
        return auctionEtpId;
    }

    public void setAuctionEtpId(Long auctionEtpId) {
        this.auctionEtpId = auctionEtpId;
    }

    public String getAuctionDateBStr() {
        return auctionDateBStr;
    }

    public void setAuctionDateBStr(String auctionDateBStr) {
        this.auctionDateBStr = auctionDateBStr;
    }

    public Long getTypeAuctionUnid() {
        return typeAuctionUnid;
    }

    public void setTypeAuctionUnid(Long typeAuctionUnid) {
        this.typeAuctionUnid = typeAuctionUnid;
    }

    public Long getTypeAuctionCode() {
        return typeAuctionCode;
    }

    public void setTypeAuctionCode(Long typeAuctionCode) {
        this.typeAuctionCode = typeAuctionCode;
    }

    public Long getTsUnid() {
        return tsUnid;
    }

    public void setTsUnid(Long tsUnid) {
        this.tsUnid = tsUnid;
    }

    public Long getLsUnid() {
        return lsUnid;
    }

    public void setLsUnid(Long lsUnid) {
        this.lsUnid = lsUnid;
    }

    public String getLsName() {
        return lsName;
    }

    public void setLsName(String lsName) {
        this.lsName = lsName;
    }

    public Long getPaCuratorUnid() {
        return paCuratorUnid;
    }

    public void setPaCuratorUnid(Long paCuratorUnid) {
        this.paCuratorUnid = paCuratorUnid;
    }

    public String getCuratorName() {
        return curatorName;
    }

    public void setCuratorName(String curatorName) {
        this.curatorName = curatorName;
    }

    public String getAuctionPublicationDateStr() {
        return auctionPublicationDateStr;
    }

    public void setAuctionPublicationDateStr(String auctionPublicationDateStr) {
        this.auctionPublicationDateStr = auctionPublicationDateStr;
    }

    public Boolean getAuctionIndSpecialTerms() { return auctionIndSpecialTerms != null && auctionIndSpecialTerms == true;  }

    public void setAuctionIndSpecialTerms(Boolean auctionIndSpecialTerms) { this.auctionIndSpecialTerms = auctionIndSpecialTerms;   }
}
