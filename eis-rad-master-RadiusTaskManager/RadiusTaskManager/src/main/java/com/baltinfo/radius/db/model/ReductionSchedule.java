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
 * @author kya
 */
@Entity
@Table(name = "reduction_schedule", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"red_sched_unid"})})
@SequenceGenerator(name = "seq_reduction_schedule", sequenceName = "seq_reduction_schedule", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "ReductionSchedule.findAll", query = "SELECT r FROM ReductionSchedule r WHERE r.indActual = 1"),
        @NamedQuery(name = "ReductionSchedule.findByRedSchedUnid", query = "SELECT r FROM ReductionSchedule r WHERE r.indActual = 1 AND r.redSchedUnid = :redSchedUnid"),
        @NamedQuery(name = "ReductionSchedule.findByLotUnid", query = "SELECT r FROM ReductionSchedule r, Lot l WHERE r.lotUnid = l AND r.indActual = 1 AND l.lotUnid = :lotUnid ORDER BY r.redSchedDateB"),
        @NamedQuery(name = "ReductionSchedule.findByLotUnidAndDateB", query = "SELECT r FROM ReductionSchedule r WHERE r.lotUnid.lotUnid = :lotUnid AND r.indActual = 1 and r.redSchedDateB = :redSchedDateB")
})
public class ReductionSchedule implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reduction_schedule")
    @Column(name = "red_sched_unid", nullable = false, precision = 2147483647, scale = 0)
    private long redSchedUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
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
    @Column(name = "red_sched_date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date redSchedDateB;
    @Column(name = "red_sched_reduction_value")
    private BigDecimal redSchedReductionValue;
    @Column(name = "red_sched_ask_price")
    private BigDecimal redSchedAskPrice;
    @Column(name = "red_sched_dep_date_e")
    @Temporal(TemporalType.TIMESTAMP)
    private Date redSchedDepDateE;
    @Column(name = "red_sched_dep_sum")
    private BigDecimal redSchedDepSum;
    @Column(name = "red_sched_appl_date_e")
    @Temporal(TemporalType.TIMESTAMP)
    private Date redSchedApplDateE;
    @JoinColumn(name = "lot_unid", referencedColumnName = "lot_unid")
    @ManyToOne
    private Lot lotUnid;

    public ReductionSchedule() {
    }

    public ReductionSchedule(long redSchedUnid) {
        this.redSchedUnid = redSchedUnid;
    }

    public long getRedSchedUnid() {
        return redSchedUnid;
    }

    public void setRedSchedUnid(long redSchedUnid) {
        this.redSchedUnid = redSchedUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
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

    public Date getRedSchedDateB() {
        return redSchedDateB;
    }

    public void setRedSchedDateB(Date redSchedDateB) {
        this.redSchedDateB = redSchedDateB;
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

    public Date getRedSchedApplDateE() {
        return redSchedApplDateE;
    }

    public void setRedSchedApplDateE(Date redSchedApplDateE) {
        this.redSchedApplDateE = redSchedApplDateE;
    }

    public Lot getLotUnid() {
        return lotUnid;
    }

    public void setLotUnid(Lot lotUnid) {
        this.lotUnid = lotUnid;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.ReductionSchedule[ redSchedUnid=" + redSchedUnid + " ]";
    }

}
