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
import java.util.Date;

/**
 *
 * @author sas
 */
@Entity
@Table(name = "type_event", catalog = "", schema = "web", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"tev_unid"})})
@SequenceGenerator(name = "seq_type_event", sequenceName = "seq_type_event", allocationSize = 1)
@XmlRootElement
public class TypeEvent implements IHistory {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "tev_unid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_type_event")
    private Long tevUnid;
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
    @Column(name = "tev_name")
    private String tevName;
    @Column(name = "tev_sname")
    private String tevSname;
    @Column(name = "tev_sql_create")
    private String tevSqlCreate;
    @Column(name = "tev_sql_remove")
    private String tevSqlRemove;
    @Column(name = "tev_code")
    private String tevCode;
    @Column(name = "tev_formula")
    private String tevFormula;
    @Column(name = "found_unid")
    private Long foundUnid;
    @Column(name = "tper_unid")
    private Long tperUnid;
    @Column(name = "tev_period_e")
    private Integer tevPeriodE;
    @Column(name = "tev_style")
    private String tevStyle;

    public TypeEvent() {
    }

    public TypeEvent(Long tevUnid) {
        this.tevUnid = tevUnid;
    }

    public Long getTevUnid() {
        return tevUnid;
    }

    public void setTevUnid(Long tevUnid) {
        this.tevUnid = tevUnid;
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

    public String getTevName() {
        return tevName;
    }

    public void setTevName(String tevName) {
        this.tevName = tevName;
    }

    public String getTevSname() {
        return tevSname;
    }

    public void setTevSname(String tevSname) {
        this.tevSname = tevSname;
    }

    public String getTevSqlCreate() {
        return tevSqlCreate;
    }

    public void setTevSqlCreate(String tevSqlCreate) {
        this.tevSqlCreate = tevSqlCreate;
    }

    public String getTevSqlRemove() {
        return tevSqlRemove;
    }

    public void setTevSqlRemove(String tevSqlRemove) {
        this.tevSqlRemove = tevSqlRemove;
    }

    public String getTevCode() {
        return tevCode;
    }

    public void setTevCode(String tevCode) {
        this.tevCode = tevCode;
    }

    public String getTevFormula() {
        return tevFormula;
    }

    public void setTevFormula(String tevFormula) {
        this.tevFormula = tevFormula;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    public Long getTperUnid() {
        return tperUnid;
    }

    public void setTperUnid(Long tperUnid) {
        this.tperUnid = tperUnid;
    }

    public Integer getTevPeriodE() {
        return tevPeriodE;
    }

    public void setTevPeriodE(Integer tevPeriodE) {
        this.tevPeriodE = tevPeriodE;
    }

    public String getTevStyle() {
        return tevStyle;
    }

    public void setTevStyle(String tevStyle) {
        this.tevStyle = tevStyle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tevUnid != null ? tevUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeEvent)) {
            return false;
        }
        TypeEvent other = (TypeEvent) object;
        if ((this.tevUnid == null && other.tevUnid != null) || (this.tevUnid != null && !this.tevUnid.equals(other.tevUnid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.TypeEvent[ tevUnid=" + tevUnid + " ]";
    }

}
