/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baltinfo.radius.db.model.lotonline;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author sas
 */
@Entity
@Table(name = "tender_flow", catalog = "", schema = "")
@XmlRootElement
public class TenderFlow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "tender_status")
    private String tenderStatus;
    @Basic(optional = false)
    @Column(name = "lot_info_fk")
    private long lotInfoFk;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "start_amt")
    private BigDecimal startAmt;
    @Column(name = "bid_price")
    private BigDecimal bidPrice;
    @Column(name = "bid_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bidTimestamp;
    @Column(name = "stop_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stopTime;
    @Column(name = "write_bids_after_finish")
    private BigInteger writeBidsAfterFinish;
    @Column(name = "extension_time")
    private BigInteger extensionTime;
    @Column(name = "time_left_for_extension")
    private BigInteger timeLeftForExtension;
    @Basic(optional = false)
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @Column(name = "user_fk")
    private BigInteger userFk;
    @Column(name = "tender_type")
    private String tenderType;
    @Column(name = "fixed_step")
    private Boolean fixedStep;
    @Column(name = "step_up")
    private BigDecimal stepUp;
    @Column(name = "step_down")
    private BigDecimal stepDown;
    @Column(name = "step_direction")
    private String stepDirection;
    @Column(name = "auto_bid_period")
    private BigInteger autoBidPeriod;
    @Column(name = "min_price")
    private BigDecimal minPrice;
    @Basic(optional = false)
    @Column(name = "is_consecutive_bidding")
    private boolean isConsecutiveBidding;
    @Basic(optional = false)
    @Column(name = "signature_required")
    private boolean signatureRequired;
    @Column(name = "current_price")
    private BigDecimal currentPrice;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Basic(optional = false)
    @Column(name = "absentee_participation_processed")
    private boolean absenteeParticipationProcessed;
    @Column(name = "base_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date baseStart;
    @Column(name = "step_down_bid_price")
    private BigDecimal stepDownBidPrice;
    @Column(name = "step_down_bid_user_fk")
    private BigInteger stepDownBidUserFk;
    @Column(name = "start_price_bid_period")
    private BigInteger startPriceBidPeriod;
    @Column(name = "direction_turn_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date directionTurnTimestamp;

    public TenderFlow() {
    }

    public TenderFlow(Long id) {
        this.id = id;
    }

    public TenderFlow(Long id, long lotInfoFk, Date lastUpdate, boolean isConsecutiveBidding, boolean signatureRequired, boolean absenteeParticipationProcessed) {
        this.id = id;
        this.lotInfoFk = lotInfoFk;
        this.lastUpdate = lastUpdate;
        this.isConsecutiveBidding = isConsecutiveBidding;
        this.signatureRequired = signatureRequired;
        this.absenteeParticipationProcessed = absenteeParticipationProcessed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenderStatus() {
        return tenderStatus;
    }

    public void setTenderStatus(String tenderStatus) {
        this.tenderStatus = tenderStatus;
    }

    public long getLotInfoFk() {
        return lotInfoFk;
    }

    public void setLotInfoFk(long lotInfoFk) {
        this.lotInfoFk = lotInfoFk;
    }

    public BigDecimal getStartAmt() {
        return startAmt;
    }

    public void setStartAmt(BigDecimal startAmt) {
        this.startAmt = startAmt;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }

    public Date getBidTimestamp() {
        return bidTimestamp;
    }

    public void setBidTimestamp(Date bidTimestamp) {
        this.bidTimestamp = bidTimestamp;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public BigInteger getWriteBidsAfterFinish() {
        return writeBidsAfterFinish;
    }

    public void setWriteBidsAfterFinish(BigInteger writeBidsAfterFinish) {
        this.writeBidsAfterFinish = writeBidsAfterFinish;
    }

    public BigInteger getExtensionTime() {
        return extensionTime;
    }

    public void setExtensionTime(BigInteger extensionTime) {
        this.extensionTime = extensionTime;
    }

    public BigInteger getTimeLeftForExtension() {
        return timeLeftForExtension;
    }

    public void setTimeLeftForExtension(BigInteger timeLeftForExtension) {
        this.timeLeftForExtension = timeLeftForExtension;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public BigInteger getUserFk() {
        return userFk;
    }

    public void setUserFk(BigInteger userFk) {
        this.userFk = userFk;
    }

    public String getTenderType() {
        return tenderType;
    }

    public void setTenderType(String tenderType) {
        this.tenderType = tenderType;
    }

    public Boolean getFixedStep() {
        return fixedStep;
    }

    public void setFixedStep(Boolean fixedStep) {
        this.fixedStep = fixedStep;
    }

    public BigDecimal getStepUp() {
        return stepUp;
    }

    public void setStepUp(BigDecimal stepUp) {
        this.stepUp = stepUp;
    }

    public BigDecimal getStepDown() {
        return stepDown;
    }

    public void setStepDown(BigDecimal stepDown) {
        this.stepDown = stepDown;
    }

    public String getStepDirection() {
        return stepDirection;
    }

    public void setStepDirection(String stepDirection) {
        this.stepDirection = stepDirection;
    }

    public BigInteger getAutoBidPeriod() {
        return autoBidPeriod;
    }

    public void setAutoBidPeriod(BigInteger autoBidPeriod) {
        this.autoBidPeriod = autoBidPeriod;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public boolean getIsConsecutiveBidding() {
        return isConsecutiveBidding;
    }

    public void setIsConsecutiveBidding(boolean isConsecutiveBidding) {
        this.isConsecutiveBidding = isConsecutiveBidding;
    }

    public boolean getSignatureRequired() {
        return signatureRequired;
    }

    public void setSignatureRequired(boolean signatureRequired) {
        this.signatureRequired = signatureRequired;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public boolean getAbsenteeParticipationProcessed() {
        return absenteeParticipationProcessed;
    }

    public void setAbsenteeParticipationProcessed(boolean absenteeParticipationProcessed) {
        this.absenteeParticipationProcessed = absenteeParticipationProcessed;
    }

    public Date getBaseStart() {
        return baseStart;
    }

    public void setBaseStart(Date baseStart) {
        this.baseStart = baseStart;
    }

    public BigDecimal getStepDownBidPrice() {
        return stepDownBidPrice;
    }

    public void setStepDownBidPrice(BigDecimal stepDownBidPrice) {
        this.stepDownBidPrice = stepDownBidPrice;
    }

    public BigInteger getStepDownBidUserFk() {
        return stepDownBidUserFk;
    }

    public void setStepDownBidUserFk(BigInteger stepDownBidUserFk) {
        this.stepDownBidUserFk = stepDownBidUserFk;
    }

    public BigInteger getStartPriceBidPeriod() {
        return startPriceBidPeriod;
    }

    public void setStartPriceBidPeriod(BigInteger startPriceBidPeriod) {
        this.startPriceBidPeriod = startPriceBidPeriod;
    }

    public Date getDirectionTurnTimestamp() {
        return directionTurnTimestamp;
    }

    public void setDirectionTurnTimestamp(Date directionTurnTimestamp) {
        this.directionTurnTimestamp = directionTurnTimestamp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TenderFlow)) {
            return false;
        }
        TenderFlow other = (TenderFlow) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.lotonline.model.TenderFlow[ id=" + id + " ]";
    }

}
