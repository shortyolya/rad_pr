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
@Table(name = "load_status", catalog = "", schema = "web")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "LoadStatus.findAll", query = "SELECT l FROM LoadStatus l"),
        @NamedQuery(name = "LoadStatus.findByLstUnid", query = "SELECT l FROM LoadStatus l WHERE l.lstUnid = :lstUnid")})
public class LoadStatus implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    public static final Long PREPARE_XLS_RUN_STATUS = 1L;
    public static final Long XLS_RUN_STATUS = 2L;
    public static final Long RUN_LOTS_STATUS = 3L;
    public static final Long END_LOAD_STATUS = 4L;
    public static final Long XLS_LOAD_ERROR_STATUS = 5L;
    public static final Long TEMP_DB_SAVE_ERROR_STATUS = 6L;
    public static final Long LOT_SAVE_ERROR_STATUS = 7L;
    public static final Long PREPARE_XML_RUN_STATUS = 10L;
    public static final Long XML_RUN_STATUS = 11L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "lst_unid")
    private Long lstUnid;
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
    @Column(name = "lst_name")
    private String lstName;

    public LoadStatus() {
    }

    public Long getLstUnid() {
        return lstUnid;
    }

    public void setLstUnid(Long lstUnid) {
        this.lstUnid = lstUnid;
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

    public String getLstName() {
        return lstName;
    }

    public void setLstName(String lstName) {
        this.lstName = lstName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lstUnid != null ? lstUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoadStatus)) {
            return false;
        }
        LoadStatus other = (LoadStatus) object;
        return (this.lstUnid != null || other.lstUnid == null) && (this.lstUnid == null || this.lstUnid.equals(other.lstUnid));
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.LoadStatus[ lstUnid=" + lstUnid + " ]";
    }

}
