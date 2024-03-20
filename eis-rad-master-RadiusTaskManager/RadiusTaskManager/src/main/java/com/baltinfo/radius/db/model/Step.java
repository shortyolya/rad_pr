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
 *
 * @author css
 */
@Entity
@Table(catalog = "", schema = "web", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"step_unid"})})
@SequenceGenerator(name = "seq_step", sequenceName = "seq_step", allocationSize = 1)
@XmlRootElement
public class Step implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_step")
    @Column(name = "step_unid", nullable = false, precision = 2147483647, scale = 0)
    private long stepUnid;
    @Column(name = "found_unid")
    private Integer foundUnid;
    @Column(name = "ind_actual")
    private Integer indActual;
    @Column(name = "date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateB;
    @Column(name = "found_b", length = 500)
    private String foundB;
    @Column(name = "pers_code_b")
    private Long persCodeB;
    @Column(name = "date_b_rec")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBRec;
    @Column(name = "pers_code_b_rec")
    private Long persCodeBRec;
    @Column(name = "step_value")
    private BigDecimal stepValue;
    @Column(name = "step_card_number", length = 50)
    private String stepCardNumber;
    @Column(name = "step_number")
    private Integer stepNumber;
    @Column(name = "step_time_stamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stepTimeStamp;
    @Column(name = "step_ind_bid_acceptance")
    private Integer stepIndBidAcceptance;
    @Column(name = "step_rejection_reson", length = 2000)
    private String stepRejectionReson;
    @Column(name = "step_ind_bid")
    private Integer stepIndBid;
    @Basic(optional = false)
    @Column(name = "lot_unid", nullable = false)

    private long lotUnid;

    public Step() {
    }

    public long getStepUnid() {
        return stepUnid;
    }

    public void setStepUnid(long stepUnid) {
        this.stepUnid = stepUnid;
    }

    public Integer getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Integer foundUnid) {
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

    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    public Date getStepTimeStamp() {
        return stepTimeStamp;
    }

    public void setStepTimeStamp(Date stepTimeStamp) {
        this.stepTimeStamp = stepTimeStamp;
    }

    public Integer getStepIndBidAcceptance() {
        return stepIndBidAcceptance;
    }

    public void setStepIndBidAcceptance(Integer stepIndBidAcceptance) {
        this.stepIndBidAcceptance = stepIndBidAcceptance;
    }

    public String getStepRejectionReson() {
        return stepRejectionReson;
    }

    public void setStepRejectionReson(String stepRejectionReson) {
        this.stepRejectionReson = stepRejectionReson;
    }

    public Integer getStepIndBid() {
        return stepIndBid;
    }

    public void setStepIndBid(Integer stepIndBid) {
        this.stepIndBid = stepIndBid;
    }

    public long getLotUnid() {
        return lotUnid;
    }

    public void setLotUnid(long lotUnid) {
        this.lotUnid = lotUnid;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.Step[ stepUnid=" + stepUnid + " ]";
    }

}
