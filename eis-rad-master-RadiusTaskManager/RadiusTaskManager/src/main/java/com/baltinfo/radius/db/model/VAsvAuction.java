package com.baltinfo.radius.db.model;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name = "VAsvAuction.findAll", query = "SELECT a FROM VAsvAuction a"),
        @NamedQuery(name = "VAsvAuction.findByBaUnid", query = "SELECT a FROM VAsvAuction a where a.baUnid = :baUnid order by a.auctionStageNum, a.auctionUnid")
})
@Entity
@Table(name = "V_ASV_AUCTION", catalog = "", schema = "WEB")
public class VAsvAuction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "auction_unid")
    private Long auctionUnid;
    @Column(name = "ba_unid")
    private Long baUnid;
    @Column(name = "auction_stage_num")
    private Integer auctionStageNum;
    @Column(name = "auction_asv_id")
    private String auctionAsvId;
    @Column(name = "auction_name")
    private String auctionName;
    @Column(name = "ts_unid")
    private Long tsUnid;
    @Column(name = "type_auction_unid")
    private Long typeAuctionUnid;
    @Column(name = "type_auction_code")
    private Long typeAuctionCode;
    @Column(name = "epr_results_unid")
    private Long eprResultsUnid;

    public Long getAuctionUnid() {
        return auctionUnid;
    }

    public void setAuctionUnid(Long auctionUnid) {
        this.auctionUnid = auctionUnid;
    }

    public Long getBaUnid() {
        return baUnid;
    }

    public void setBaUnid(Long baUnid) {
        this.baUnid = baUnid;
    }

    public Integer getAuctionStageNum() {
        return auctionStageNum;
    }

    public void setAuctionStageNum(Integer auctionStageNum) {
        this.auctionStageNum = auctionStageNum;
    }

    public String getAuctionAsvId() {
        return auctionAsvId;
    }

    public void setAuctionAsvId(String auctionAsvId) {
        this.auctionAsvId = auctionAsvId;
    }

    public Long getTsUnid() {
        return tsUnid;
    }

    public void setTsUnid(Long tsUnid) {
        this.tsUnid = tsUnid;
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

    public Long getEprResultsUnid() {
        return eprResultsUnid;
    }

    public void setEprResultsUnid(Long eprResultsUnid) {
        this.eprResultsUnid = eprResultsUnid;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }

    public String getAuctionAsvIdWithoutStage() {
        if (auctionAsvId == null || auctionAsvId.isEmpty()) {
            return null;
        }
        return StringUtils.substringBefore(auctionAsvId.trim(), "/");
    }
}
