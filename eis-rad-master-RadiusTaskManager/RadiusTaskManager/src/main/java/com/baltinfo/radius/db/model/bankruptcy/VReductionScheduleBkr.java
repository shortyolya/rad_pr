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
@Table(name = "REDUCTION_SCHEDULE", catalog = "", schema = "WEB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VReductionScheduleBkr.findAll", query = "SELECT v FROM VReductionScheduleBkr v")})
public class VReductionScheduleBkr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @Column(name = "RED_SCHED_UNID")
    private long redSchedUnid;
    @Basic(optional = false)
    @Column(name = "LOT_UNID")
    private long lotUnid;
    @Column(name = "RED_SCHED_DATE_B")
    @Temporal(TemporalType.TIMESTAMP)
    private Date redSchedDateB;
    @Column(name = "RED_SCHED_DATE_E")
    @Temporal(TemporalType.TIMESTAMP)
    private Date redSchedDateE;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "RED_SCHED_REDUCTION_VALUE")
    private BigDecimal redSchedReductionValue;
    @Column(name = "RED_SCHED_ASK_PRICE")
    private BigDecimal redSchedAskPrice;
    @Column(name = "RED_SCHED_DEP_DATE_E")
    @Temporal(TemporalType.TIMESTAMP)
    private Date redSchedDepDateE;
    @Column(name = "RED_SCHED_DEP_SUM")
    private BigDecimal redSchedDepSum;
    @Column(name = "ADD_UNID")
    private Long addUnid;
    @Column(name = "APP_NOT_REJECT_COUNT")
    private BigInteger appNotRejectCount;
    @Column(name = "APP_NOT_REVIEW_COUNT")
    private BigInteger appNotReviewCount;
    @Column(name = "ORGANIZER_UNID")
    private BigInteger organizerUnid;

    public VReductionScheduleBkr() {
    }

    public long getRedSchedUnid() {
        return redSchedUnid;
    }

    public void setRedSchedUnid(long redSchedUnid) {
        this.redSchedUnid = redSchedUnid;
    }

    public long getLotUnid() {
        return lotUnid;
    }

    public void setLotUnid(long lotUnid) {
        this.lotUnid = lotUnid;
    }

    public Date getRedSchedDateB() {
        return redSchedDateB;
    }

    public void setRedSchedDateB(Date redSchedDateB) {
        this.redSchedDateB = redSchedDateB;
    }

    public Date getRedSchedDateE() {
        return redSchedDateE;
    }

    public void setRedSchedDateE(Date redSchedDateE) {
        this.redSchedDateE = redSchedDateE;
    }

    public BigDecimal getRedSchedReductionValue() {
        return redSchedReductionValue;
    }

    public void setRedSchedReductionValue(BigDecimal redSchedReductionValue) {
        this.redSchedReductionValue = redSchedReductionValue;
    }

    public BigDecimal getRedSchedAskPrice() {
        return redSchedAskPrice;
    }

    public void setRedSchedAskPrice(BigDecimal redSchedAskPrice) {
        this.redSchedAskPrice = redSchedAskPrice;
    }

    public Date getRedSchedDepDateE() {
        return redSchedDepDateE;
    }

    public void setRedSchedDepDateE(Date redSchedDepDateE) {
        this.redSchedDepDateE = redSchedDepDateE;
    }

    public BigDecimal getRedSchedDepSum() {
        return redSchedDepSum;
    }

    public void setRedSchedDepSum(BigDecimal redSchedDepSum) {
        this.redSchedDepSum = redSchedDepSum;
    }

    public Long getAddUnid() {
        return addUnid;
    }

    public void setAddUnid(Long addUnid) {
        this.addUnid = addUnid;
    }

    public BigInteger getAppNotRejectCount() {
        return appNotRejectCount;
    }

    public void setAppNotRejectCount(BigInteger appNotRejectCount) {
        this.appNotRejectCount = appNotRejectCount;
    }

    public BigInteger getAppNotReviewCount() {
        return appNotReviewCount;
    }

    public void setAppNotReviewCount(BigInteger appNotReviewCount) {
        this.appNotReviewCount = appNotReviewCount;
    }

    public BigInteger getOrganizerUnid() {
        return organizerUnid;
    }

    public void setOrganizerUnid(BigInteger organizerUnid) {
        this.organizerUnid = organizerUnid;
    }

}
