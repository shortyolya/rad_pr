package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.util.Date;

@Entity
@Table(name = "reward_formula", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"rf_unid"})})
@SequenceGenerator(name = "seq_reward_formula", sequenceName = "seq_reward_formula", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "RewardFormula.getAll", query = "SELECT rf FROM RewardFormula rf where rf.indActual = 1")
})

public class RewardFormula {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reward_formula")
    @Column(name = "rf_unid", nullable = false)
    private long rfUnid;
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
    @Column(name = "rf_code")
    private String rfCode;
    @Column(name = "rf_name")
    private String rfName;
    @Column(name = "rf_formula")
    private String rfFormula;
    @Column(name = "rf_note")
    private String rfNote;

    public long getRfUnid() {
        return rfUnid;
    }

    public void setRfUnid(long rfUnid) {
        this.rfUnid = rfUnid;
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

    public String getRfCode() {
        return rfCode;
    }

    public void setRfCode(String rfCode) {
        this.rfCode = rfCode;
    }

    public String getRfName() {
        return rfName;
    }

    public void setRfName(String rfName) {
        this.rfName = rfName;
    }

    public String getRfFormula() {
        return rfFormula;
    }

    public void setRfFormula(String rfFormula) {
        this.rfFormula = rfFormula;
    }

    public String getRfNote() {
        return rfNote;
    }

    public void setRfNote(String rfNote) {
        this.rfNote = rfNote;
    }

}
