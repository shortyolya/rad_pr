/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lia
 */
@Entity
@Table(name = "load_source_settings", catalog = "", schema = "web")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "LoadSourceSettings.findAll", query = "SELECT l FROM LoadSourceSettings l"),
        @NamedQuery(name = "LoadSourceSettings.findByLssUnid", query = "SELECT l FROM LoadSourceSettings l WHERE l.lssUnid = :lssUnid"),
        @NamedQuery(name = "LoadSourceSettings.findByLsUnid", query = "SELECT l FROM LoadSourceSettings l WHERE l.indActual = 1 and l.lsUnid.lsUnid = :lsUnid")})
public class LoadSourceSettings implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "lss_unid")
    private Long lssUnid;
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
    @JoinColumn(name = "ls_unid", referencedColumnName = "ls_unid")
    @ManyToOne(optional = false)
    private LoadSource lsUnid;
    @Column(name = "pa_unid")
    private Long paUnid;
    @Column(name = "sg_unid")
    private Long sgUnid;
    @Column(name = "sm_unid")
    private Long smUnid;
    @Column(name = "sub_unid")
    private Long subUnid;
    @Column(name = "to_unid")
    private Long toUnid;
    @Column(name = "tpa_unid")
    private Long tpaUnid;

    public LoadSourceSettings() {
    }

    public Long getLssUnid() {
        return lssUnid;
    }

    public void setLssUnid(Long lssUnid) {
        this.lssUnid = lssUnid;
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

    public LoadSource getLsUnid() {
        return lsUnid;
    }

    public void setLsUnid(LoadSource lsUnid) {
        this.lsUnid = lsUnid;
    }

    public Long getPaUnid() {
        return paUnid;
    }

    public void setPaUnid(Long paUnid) {
        this.paUnid = paUnid;
    }

    public Long getSgUnid() {
        return sgUnid;
    }

    public void setSgUnid(Long sgUnid) {
        this.sgUnid = sgUnid;
    }

    public Long getSmUnid() {
        return smUnid;
    }

    public void setSmUnid(Long smUnid) {
        this.smUnid = smUnid;
    }

    public Long getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(Long subUnid) {
        this.subUnid = subUnid;
    }

    public Long getToUnid() {
        return toUnid;
    }

    public void setToUnid(Long toUnid) {
        this.toUnid = toUnid;
    }

    public Long getTpaUnid() {
        return tpaUnid;
    }

    public void setTpaUnid(Long tpaUnid) {
        this.tpaUnid = tpaUnid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lssUnid != null ? lssUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoadSourceSettings)) {
            return false;
        }
        LoadSourceSettings other = (LoadSourceSettings) object;
        return (this.lssUnid != null || other.lssUnid == null) && (this.lssUnid == null || this.lssUnid.equals(other.lssUnid));
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.LoadSourceSettings[ lssUnid=" + lssUnid + " ]";
    }

}
