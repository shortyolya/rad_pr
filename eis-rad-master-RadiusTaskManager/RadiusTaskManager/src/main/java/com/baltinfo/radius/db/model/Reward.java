package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 10.09.2020
 */
@Entity
@Table(name = "reward", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"reward_unid"})})
@SequenceGenerator(name = "seq_reward", sequenceName = "seq_reward", allocationSize = 1)
@XmlRootElement
public class Reward implements IHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reward")
    @Column(name = "reward_unid", nullable = false, precision = 2147483647, scale = 0)
    private Long rewardUnid;
    @Column(name = "ind_actual")
    private Integer indActual;
    @Column(name = "date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateB;
    @Column(name = "found_b")
    private String foundB;
    @Column(name = "found_unid")
    private Long foundUnid;
    @Column(name = "pers_code_b")
    private Long persCodeB;
    @Column(name = "date_b_rec")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBRec;
    @Column(name = "pers_code_b_rec")
    private Long persCodeBRec;
    @Column(name = "reward_sum")
    private BigDecimal rewardSum;
    @Column(name = "reward_saler_proc")
    private BigDecimal rewardSalerProc;
    @Column(name = "reward_saler_sum")
    private BigDecimal rewardSalerSum;
    @Column(name = "reward_prof_proc")
    private BigDecimal rewardProfProc;
    @Column(name = "reward_prof_sum")
    private BigDecimal rewardProfSum;
    @Column(name = "reward_man_proc")
    private BigDecimal rewardManProc;
    @Column(name = "reward_man_sum")
    private BigDecimal rewardManSum;
    @Column(name = "obj_unid")
    private Long objUnid;
    @Column(name = "act_unid")
    private Long actUnid;
    @Column(name = "rf_unid")
    private Long rfUnid;
    @Column(name = "reward_sum_fact")
    private BigDecimal rewardSumFact;
    @Column(name = "reward_date")
    @Temporal(TemporalType.DATE)
    private Date rewardDate;
    @Column(name = "reward_type")
    private Integer rewardType;
    @Column(name = "reward_note")
    private String rewardNote;
    @Column(name = "lot_unid")
    private Long lotUnid;

    public Reward() {
    }

    public Long getRewardUnid() {
        return rewardUnid;
    }

    public void setRewardUnid(Long rewardUnid) {
        this.rewardUnid = rewardUnid;
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

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
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

    public BigDecimal getRewardSum() {
        return rewardSum;
    }

    public void setRewardSum(BigDecimal rewardSum) {
        this.rewardSum = rewardSum;
    }

    public BigDecimal getRewardSalerProc() {
        return rewardSalerProc;
    }

    public void setRewardSalerProc(BigDecimal rewardSalerProc) {
        this.rewardSalerProc = rewardSalerProc;
    }

    public BigDecimal getRewardSalerSum() {
        return rewardSalerSum;
    }

    public void setRewardSalerSum(BigDecimal rewardSalerSum) {
        this.rewardSalerSum = rewardSalerSum;
    }

    public BigDecimal getRewardProfProc() {
        return rewardProfProc;
    }

    public void setRewardProfProc(BigDecimal rewardProfProc) {
        this.rewardProfProc = rewardProfProc;
    }

    public BigDecimal getRewardProfSum() {
        return rewardProfSum;
    }

    public void setRewardProfSum(BigDecimal rewardProfSum) {
        this.rewardProfSum = rewardProfSum;
    }

    public BigDecimal getRewardManProc() {
        return rewardManProc;
    }

    public void setRewardManProc(BigDecimal rewardManProc) {
        this.rewardManProc = rewardManProc;
    }

    public BigDecimal getRewardManSum() {
        return rewardManSum;
    }

    public void setRewardManSum(BigDecimal rewardManSum) {
        this.rewardManSum = rewardManSum;
    }

    public Long getObjUnid() {
        return objUnid;
    }

    public void setObjUnid(Long objUnid) {
        this.objUnid = objUnid;
    }

    public Long getActUnid() {
        return actUnid;
    }

    public void setActUnid(Long actUnid) {
        this.actUnid = actUnid;
    }

    public Long getRfUnid() {
        return rfUnid;
    }

    public void setRfUnid(Long rfUnid) {
        this.rfUnid = rfUnid;
    }

    public BigDecimal getRewardSumFact() {
        return rewardSumFact;
    }

    public void setRewardSumFact(BigDecimal rewardSumFact) {
        this.rewardSumFact = rewardSumFact;
    }

    public Date getRewardDate() {
        return rewardDate;
    }

    public void setRewardDate(Date rewardDate) {
        this.rewardDate = rewardDate;
    }

    public Integer getRewardType() {
        return rewardType;
    }

    public void setRewardType(Integer rewardType) {
        this.rewardType = rewardType;
    }

    public String getRewardNote() {
        return rewardNote;
    }

    public void setRewardNote(String rewardNote) {
        this.rewardNote = rewardNote;
    }

    public Long getLotUnid() {
        return lotUnid;
    }

    public void setLotUnid(Long lotUnid) {
        this.lotUnid = lotUnid;
    }
}
