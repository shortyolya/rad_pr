package com.baltinfo.radius.db.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "property_value", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"pv_unid"})})
@SequenceGenerator(name = "seq_property_value", sequenceName = "seq_property_value", allocationSize = 1)
@XmlRootElement
public class PropertyValue implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_property_value")
    @Column(name = "pv_unid")
    private Long pvUnid;
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
    @Column(name = "pv_value")
    private String pvValue;
    @JoinColumn(name = "apv_unid", referencedColumnName = "apv_unid")
    @ManyToOne
    private AllowPropVal apvUnid;
    @JoinColumn(name = "op_unid", referencedColumnName = "op_unid")
    @ManyToOne(optional = false)
    private ObjProperty opUnid;
    @Column(name = "obj_unid")
    private Long objUnid;

    public PropertyValue() {
    }

    public PropertyValue(Long pvUnid) {
        this.pvUnid = pvUnid;
    }

    public Long getPvUnid() {
        return pvUnid;
    }

    public void setPvUnid(Long pvUnid) {
        this.pvUnid = pvUnid;
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

    public String getPvValue() {
        return pvValue;
    }

    public void setPvValue(String pvValue) {
        this.pvValue = pvValue;
    }

    public AllowPropVal getApvUnid() {
        return apvUnid;
    }

    public void setApvUnid(AllowPropVal apvUnid) {
        this.apvUnid = apvUnid;
    }

    public ObjProperty getOpUnid() {
        return opUnid;
    }

    public void setOpUnid(ObjProperty opUnid) {
        this.opUnid = opUnid;
    }

    public Long getObjUnid() {
        return objUnid;
    }

    public void setObjUnid(Long objUnid) {
        this.objUnid = objUnid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pvUnid != null ? pvUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PropertyValue)) {
            return false;
        }
        PropertyValue other = (PropertyValue) object;
        if ((this.pvUnid == null && other.pvUnid != null) || (this.pvUnid != null && !this.pvUnid.equals(other.pvUnid))) {
            return false;
        }
        return true;
    }

}
