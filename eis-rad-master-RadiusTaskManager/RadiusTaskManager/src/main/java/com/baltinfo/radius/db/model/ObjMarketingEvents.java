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
import java.util.Date;

/**
 * @author ssv
 * @since 09.07.2019
 */
@Entity
@Table(name = "obj_marketing_events", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ome_unid"})})
@SequenceGenerator(name = "seq_obj_marketing_events", sequenceName = "seq_obj_marketing_events", allocationSize = 1)
@XmlRootElement
public class ObjMarketingEvents implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_obj_marketing_events")
    @Column(name = "ome_unid", nullable = false, precision = 2147483647, scale = 0)
    private long omeUnid;
    @Column(name = "obj_unid", nullable = false)
    private Long objUnid;
    @Column(name = "mev_unid")
    private Long mevUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
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
    @Column(name = "ome_ind_exists", nullable = false)
    private Boolean omeIndExists;

    public ObjMarketingEvents() {
    }

    public ObjMarketingEvents(long omeUnid) {
        this.omeUnid = omeUnid;
    }

    public long getOmeUnid() {
        return omeUnid;
    }

    public void setOmeUnid(long omeUnid) {
        this.omeUnid = omeUnid;
    }

    public Long getObjUnid() {
        return objUnid;
    }

    public void setObjUnid(Long objUnid) {
        this.objUnid = objUnid;
    }

    public Long getMevUnid() {
        return mevUnid;
    }

    public void setMevUnid(Long mevUnid) {
        this.mevUnid = mevUnid;
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

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
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

    public Boolean getOmeIndExists() {
        return omeIndExists;
    }

    public void setOmeIndExists(Boolean omeIndExists) {
        this.omeIndExists = omeIndExists;
    }

    @Override
    public String toString() {
        return "com.baltinfo.radius.db.model.ObjMarketingEvents[ omeUnid=" + omeUnid + " ]";
    }

}
