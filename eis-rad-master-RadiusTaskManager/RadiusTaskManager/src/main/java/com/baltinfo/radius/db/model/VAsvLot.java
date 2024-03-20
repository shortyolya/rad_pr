package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = "VAsvLot.findAll", query = "SELECT a FROM VAsvLot a"),
        @NamedQuery(name = "VAsvLot.findByAuctionUnid", query = "SELECT a FROM VAsvLot a where a.auctionUnid = :auctionUnid order by a.lotNumber"),
        @NamedQuery(name = "VAsvLot.countForResults", query = "SELECT count(a) FROM VAsvLot a where a.lotStatus <> 0 and a.eprResultsUnid is null and a.auctionUnid = :auctionUnid")
})
@Entity
@Table(name = "V_ASV_LOT", catalog = "", schema = "WEB")
public class VAsvLot implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "LOT_UNID")
    private Long lotUnid;
    @Column(name = "OBJ_UNID")
    private Long objUnid;
    @Column(name = "AUCTION_UNID")
    private Long auctionUnid;
    @Column(name = "TYPE_AUCTION_UNID")
    private Long typeAuctionUnid;
    @Column(name = "TYPE_AUCTION_CODE")
    private Long typeAuctionCode;
    @Column(name = "AUCTION_ASV_ID")
    private String auctionAsvId;
    @Column(name = "BA_UNID")
    private Long baUnid;
    @Column(name = "LA_UNID")
    private Long laUnid;
    @Column(name = "AUCTION_STAGE_NUM")
    private Integer auctionStageNum;
    @Column(name = "ts_unid")
    private Long tsUnid;

    @Column(name = "lot_status")
    private Integer lotStatus;
    @Column(name = "LOT_ASV_ID")
    private String lotAsvId;
    @Column(name = "LOT_NUMBER")
    private String lotNumber;
    @Column(name = "lot_etp_code")
    private String lotEtpCode;
    @Column(name = "PROTOCOL_APPL_DOCUM_UNID")
    private Long protocolApplDocumUnid;
    @Column(name = "PROTOCOL_APPL_DATE")
    private Date protocolApplDate;
    @Column(name = "PROTOCOL_RESULT_DOCUM_UNID")
    private Long protocolResultDocumUnid;
    @Column(name = "PROTOCOL_RESULT_DATE")
    private Date protocolResultDate;
    @Column(name = "NON_EXEC_REASON_UNID")
    private Long nonExecReasonUnid;
    @Column(name = "LOT_AUCTION_CANCEL_CAUSE")
    private String lotAuctionCancelCause;
    @Column(name = "LOT_SUM_DEPOSIT")
    private BigDecimal lotSumDeposit;
    @Column(name = "LOT_ASV_STAGE_ID")
    private String lotAsvStageId;

    @Column(name = "END_COST_UNID")
    private Long endCostUnid;
    @Column(name = "END_COST_VALUE_RUB")
    private BigDecimal endCostValueRub;

    @Column(name = "START_COST_UNID")
    private Long startCostUnid;
    @Column(name = "START_COST_VALUE_RUB")
    private BigDecimal startCostValueRub;

    @Column(name = "BA_ASV_ID")
    private String baAsvId;

    @Column(name = "epr_results_unid")
    private Long eprResultsUnid;


    public Long getLotUnid() {
        return lotUnid;
    }

    public void setLotUnid(Long lotUnid) {
        this.lotUnid = lotUnid;
    }

    public Long getAuctionUnid() {
        return auctionUnid;
    }

    public void setAuctionUnid(Long auctionUnid) {
        this.auctionUnid = auctionUnid;
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

    public String getAuctionAsvId() {
        return auctionAsvId;
    }

    public void setAuctionAsvId(String auctionAsvId) {
        this.auctionAsvId = auctionAsvId;
    }

    public Long getBaUnid() {
        return baUnid;
    }

    public void setBaUnid(Long baUnid) {
        this.baUnid = baUnid;
    }

    public Long getLaUnid() {
        return laUnid;
    }

    public void setLaUnid(Long laUnid) {
        this.laUnid = laUnid;
    }

    public String getLotAsvId() {
        return lotAsvId;
    }

    public void setLotAsvId(String lotAsvId) {
        this.lotAsvId = lotAsvId;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Long getObjUnid() {
        return objUnid;
    }

    public void setObjUnid(Long objUnid) {
        this.objUnid = objUnid;
    }

    public Integer getAuctionStageNum() {
        return auctionStageNum;
    }

    public void setAuctionStageNum(Integer auctionStageNum) {
        this.auctionStageNum = auctionStageNum;
    }

    public Long getTsUnid() {
        return tsUnid;
    }

    public void setTsUnid(Long tsUnid) {
        this.tsUnid = tsUnid;
    }

    public Integer getLotStatus() {
        return lotStatus;
    }

    public void setLotStatus(Integer lotStatus) {
        this.lotStatus = lotStatus;
    }

    public Long getProtocolApplDocumUnid() {
        return protocolApplDocumUnid;
    }

    public void setProtocolApplDocumUnid(Long protocolApplDocumUnid) {
        this.protocolApplDocumUnid = protocolApplDocumUnid;
    }

    public Date getProtocolApplDate() {
        return protocolApplDate;
    }

    public void setProtocolApplDate(Date protocolApplDate) {
        this.protocolApplDate = protocolApplDate;
    }

    public Long getProtocolResultDocumUnid() {
        return protocolResultDocumUnid;
    }

    public void setProtocolResultDocumUnid(Long protocolResultDocumUnid) {
        this.protocolResultDocumUnid = protocolResultDocumUnid;
    }

    public Date getProtocolResultDate() {
        return protocolResultDate;
    }

    public void setProtocolResultDate(Date protocolResultDate) {
        this.protocolResultDate = protocolResultDate;
    }

    public Long getNonExecReasonUnid() {
        return nonExecReasonUnid;
    }

    public void setNonExecReasonUnid(Long nonExecReasonUnid) {
        this.nonExecReasonUnid = nonExecReasonUnid;
    }

    public String getLotAuctionCancelCause() {
        return lotAuctionCancelCause;
    }

    public void setLotAuctionCancelCause(String lotAuctionCancelCause) {
        this.lotAuctionCancelCause = lotAuctionCancelCause;
    }

    public BigDecimal getLotSumDeposit() {
        return lotSumDeposit;
    }

    public void setLotSumDeposit(BigDecimal lotSumDeposit) {
        this.lotSumDeposit = lotSumDeposit;
    }

    public String getLotEtpCode() {
        return lotEtpCode;
    }

    public void setLotEtpCode(String lotEtpCode) {
        this.lotEtpCode = lotEtpCode;
    }

    public BigDecimal getEndCostValueRub() {
        return endCostValueRub;
    }

    public void setEndCostValueRub(BigDecimal endCostValueRub) {
        this.endCostValueRub = endCostValueRub;
    }

    public Long getEndCostUnid() {
        return endCostUnid;
    }

    public void setEndCostUnid(Long endCostUnid) {
        this.endCostUnid = endCostUnid;
    }

    public Long getStartCostUnid() {
        return startCostUnid;
    }

    public void setStartCostUnid(Long startCostUnid) {
        this.startCostUnid = startCostUnid;
    }

    public BigDecimal getStartCostValueRub() {
        return startCostValueRub;
    }

    public void setStartCostValueRub(BigDecimal startCostValueRub) {
        this.startCostValueRub = startCostValueRub;
    }

    public String getLotAsvStageId() {
        return lotAsvStageId;
    }

    public void setLotAsvStageId(String lotAsvStageId) {
        this.lotAsvStageId = lotAsvStageId;
    }

    public String getBaAsvId() {
        return baAsvId;
    }

    public void setBaAsvId(String baAsvId) {
        this.baAsvId = baAsvId;
    }

    public Long getEprResultsUnid() {
        return eprResultsUnid;
    }

    public void setEprResultsUnid(Long eprResultsUnid) {
        this.eprResultsUnid = eprResultsUnid;
    }
}
