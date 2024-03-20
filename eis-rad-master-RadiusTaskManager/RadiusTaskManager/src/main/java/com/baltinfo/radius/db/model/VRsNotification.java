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
 * @since 27.11.2018
 */
@Entity
@Table(name = "v_rs_notification")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "VRsNotification.findAll", query = "SELECT v FROM VRsNotification v")})
public class VRsNotification implements Serializable, EntityNotification {

    private static final long serialVersionUID = 1L;
    @Column(name = "red_sched_unid")
    @Id
    private Long redSchedUnid;
    @Column(name = "red_sched_date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date redSchedDateB;
    @Column(name = "red_sched_appl_date_e")
    @Temporal(TemporalType.TIMESTAMP)
    private Date redSchedApplDateE;
    @Column(name = "red_sched_dep_date_e")
    @Temporal(TemporalType.TIMESTAMP)
    private Date redSchedDepDateE;
    @Column(name = "red_sched_date_b_str")
    private String redSchedDateBStr;
    @Column(name = "red_sched_appl_date_e_str")
    private String redSchedApplDateEStr;
    @Column(name = "next_red_sched_date_b_str")
    private String nextRedSchedDateBStr;
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
    @Column(name = "dp_unid")
    private Long dpUnid;
    @Column(name = "type_auction_unid")
    private Long typeAuctionUnid;
    @Column(name = "ts_unid")
    private Long tsUnid;
    @Column(name = "auction_recep_date_e_str")
    private String auctionRecepDateEStr;
    @Column(name = "auction_date_b_str")
    private String auctionDateBStr;
    @Column(name = "sub_owner_name")
    private String subOwnerName;
    @Column(name = "ind_asv")
    private Integer indAsv;
    @Column(name = "lots_count")
    private Integer lotsCount;
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

    public VRsNotification() {
    }

    public Long getRedSchedUnid() {
        return redSchedUnid;
    }

    public void setRedSchedUnid(Long redSchedUnid) {
        this.redSchedUnid = redSchedUnid;
    }

    public Date getRedSchedDateB() {
        return redSchedDateB;
    }

    public void setRedSchedDateB(Date redSchedDateB) {
        this.redSchedDateB = redSchedDateB;
    }

    public Date getRedSchedApplDateE() {
        return redSchedApplDateE;
    }

    public void setRedSchedApplDateE(Date redSchedApplDateE) {
        this.redSchedApplDateE = redSchedApplDateE;
    }

    public Date getRedSchedDepDateE() {
        return redSchedDepDateE;
    }

    public void setRedSchedDepDateE(Date redSchedDepDateE) {
        this.redSchedDepDateE = redSchedDepDateE;
    }

    public String getRedSchedDateBStr() {
        return redSchedDateBStr;
    }

    public void setRedSchedDateBStr(String redSchedDateBStr) {
        this.redSchedDateBStr = redSchedDateBStr;
    }

    public String getRedSchedApplDateEStr() {
        return redSchedApplDateEStr;
    }

    public void setRedSchedApplDateEStr(String redSchedApplDateEStr) {
        this.redSchedApplDateEStr = redSchedApplDateEStr;
    }

    public String getNextRedSchedDateBStr() {
        return nextRedSchedDateBStr;
    }

    public void setNextRedSchedDateBStr(String nextRedSchedDateBStr) {
        this.nextRedSchedDateBStr = nextRedSchedDateBStr;
    }

    public Long getAuctionUnid() {
        return auctionUnid;
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

    public Long getTypeAuctionUnid() {
        return typeAuctionUnid;
    }

    public void setTypeAuctionUnid(Long typeAuctionUnid) {
        this.typeAuctionUnid = typeAuctionUnid;
    }

    public Long getTsUnid() {
        return tsUnid;
    }

    public void setTsUnid(Long tsUnid) {
        this.tsUnid = tsUnid;
    }

    public String getAuctionRecepDateEStr() {
        return auctionRecepDateEStr;
    }

    public void setAuctionRecepDateEStr(String auctionRecepDateEStr) {
        this.auctionRecepDateEStr = auctionRecepDateEStr;
    }

    public String getAuctionDateBStr() {
        return auctionDateBStr;
    }

    public void setAuctionDateBStr(String auctionDateBStr) {
        this.auctionDateBStr = auctionDateBStr;
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

    public Long getAuctionEtpId() {
        return auctionEtpId;
    }

    public void setAuctionEtpId(Long auctionEtpId) {
        this.auctionEtpId = auctionEtpId;
    }

    public Long getTypeAuctionCode() {
        return typeAuctionCode;
    }

    public void setTypeAuctionCode(Long typeAuctionCode) {
        this.typeAuctionCode = typeAuctionCode;
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

    public Boolean getAuctionIndSpecialTerms() {
        return auctionIndSpecialTerms;
    }

    public void setAuctionIndSpecialTerms(Boolean auctionIndSpecialTerms) {
        this.auctionIndSpecialTerms = auctionIndSpecialTerms;
    }
}
