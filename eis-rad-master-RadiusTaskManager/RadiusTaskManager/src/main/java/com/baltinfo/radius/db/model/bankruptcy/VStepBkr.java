package com.baltinfo.radius.db.model.bankruptcy;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Entity
@Table(name = "STEP", schema = "WEB")
@NamedQueries({
        @NamedQuery(name = "Step.findByLotUnid", query = "SELECT s FROM VStepBkr s WHERE s.lotUnid = :lotUnid and s.indActual = 1"),
})
public class VStepBkr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "STEP_UNID")
    private long stepUnid;
    @Basic(optional = false)
    @Column(name = "LOT_UNID")
    private long lotUnid;
    @Column(name = "STEP_VALUE")
    private BigDecimal stepValue;
    @Column(name = "STEP_CARD_NUMBER")
    private String stepCardNumber;
    @Column(name = "STEP_VALUE_RUB")
    private BigDecimal stepValueRub;
    @Column(name = "STEP_NUMBER")
    private Integer stepNumber;
    @Column(name = "STEP_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stepTime;
    @Column(name = "STEP_IND_BID_ACCEPTANCE")
    private Short stepIndBidAcceptance;
    @Column(name = "STEP_REJECTION_RESON")
    private String stepRejectionReson;
    @Column(name = "STEP_IND_DIGIT_SIGN")
    private Short stepIndDigitSign;
    @Column(name = "STEP_USER_NAME")
    private String stepUserName;
    @Column(name = "STEP_IP")
    private String stepIp;
    @Column(name = "IND_ACTUAL")
    private Short indActual;
    @Column(name = "STEP_TIME_STAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stepTimeStamp;

    public long getStepUnid() {
        return stepUnid;
    }

    public void setStepUnid(long stepUnid) {
        this.stepUnid = stepUnid;
    }

    public long getLotUnid() {
        return lotUnid;
    }

    public void setLotUnid(long lotUnid) {
        this.lotUnid = lotUnid;
    }

    public BigDecimal getStepValue() {
        return stepValue;
    }

    public void setStepValue(BigDecimal stepValue) {
        this.stepValue = stepValue;
    }

    public String getStepCardNumber() {
        return stepCardNumber;
    }

    public void setStepCardNumber(String stepCardNumber) {
        this.stepCardNumber = stepCardNumber;
    }

    public BigDecimal getStepValueRub() {
        return stepValueRub;
    }

    public void setStepValueRub(BigDecimal stepValueRub) {
        this.stepValueRub = stepValueRub;
    }

    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    public Date getStepTime() {
        return stepTime;
    }

    public void setStepTime(Date stepTime) {
        this.stepTime = stepTime;
    }

    public Short getStepIndBidAcceptance() {
        return stepIndBidAcceptance;
    }

    public void setStepIndBidAcceptance(Short stepIndBidAcceptance) {
        this.stepIndBidAcceptance = stepIndBidAcceptance;
    }

    public String getStepRejectionReson() {
        return stepRejectionReson;
    }

    public void setStepRejectionReson(String stepRejectionReson) {
        this.stepRejectionReson = stepRejectionReson;
    }

    public Short getStepIndDigitSign() {
        return stepIndDigitSign;
    }

    public void setStepIndDigitSign(Short stepIndDigitSign) {
        this.stepIndDigitSign = stepIndDigitSign;
    }

    public String getStepUserName() {
        return stepUserName;
    }

    public void setStepUserName(String stepUserName) {
        this.stepUserName = stepUserName;
    }

    public String getStepIp() {
        return stepIp;
    }

    public void setStepIp(String stepIp) {
        this.stepIp = stepIp;
    }

    public Short getIndActual() {
        return indActual;
    }

    public void setIndActual(Short indActual) {
        this.indActual = indActual;
    }

    public Date getStepTimeStamp() {
        return stepTimeStamp;
    }

    public void setStepTimeStamp(Date stepTimeStamp) {
        this.stepTimeStamp = stepTimeStamp;
    }
}
