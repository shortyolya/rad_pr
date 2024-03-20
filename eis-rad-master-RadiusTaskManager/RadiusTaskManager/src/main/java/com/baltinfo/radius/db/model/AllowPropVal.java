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
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Допустимое значение свойства
 */
@Entity
@Table(name = "allow_prop_val", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"apv_unid"})})
@SequenceGenerator(name = "seq_allow_prop_val", sequenceName = "seq_allow_prop_val", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "AllowPropVal.findByAopUnid",
                query = "select a from AllowPropVal a where a.indActual = 1 and a.aopUnid = :aopUnid order by a.apvUnid asc"),
        @NamedQuery(name = "AllowPropVal.findByAopUnidAndApvValue",
                query = "select a from AllowPropVal a where a.indActual = 1 and a.aopUnid = :aopUnid and trim(a.apvValue) = :apvValue order by a.apvUnid asc")
})
public class AllowPropVal implements IHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_allow_prop_val")
    @Basic(optional = false)
    @Column(name = "apv_unid", nullable = false, precision = 2147483647)
    private Long apvUnid;
    @Column(name = "aop_unid")
    private Long aopUnid;
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
    @Column(name = "apv_code")
    private String apvCode;
    @Column(name = "apv_value")
    private String apvValue;
    @Column(name = "apv_json_properties")
    private String apvJsonProperties;
    @Column(name = "apv_parent_unid")
    private Long apvParentUnid;

    public Long getApvUnid() {
        return apvUnid;
    }

    public void setApvUnid(Long apvUnid) {
        this.apvUnid = apvUnid;
    }

    public Long getAopUnid() {
        return aopUnid;
    }

    public void setAopUnid(Long aopUnid) {
        this.aopUnid = aopUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    @Override
    public Integer getIndActual() {
        return indActual;
    }

    @Override
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

    public String getApvCode() {
        return apvCode;
    }

    public void setApvCode(String apvCode) {
        this.apvCode = apvCode;
    }

    public String getApvValue() {
        return apvValue;
    }

    public void setApvValue(String apvValue) {
        this.apvValue = apvValue;
    }

    public String getApvJsonProperties() {
        return apvJsonProperties;
    }

    public void setApvJsonProperties(String apvJsonProperties) {
        this.apvJsonProperties = apvJsonProperties;
    }

    public Long getApvParentUnid() {
        return apvParentUnid;
    }

    public void setApvParentUnid(Long apvParentUnid) {
        this.apvParentUnid = apvParentUnid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllowPropVal propVal = (AllowPropVal) o;

        if (apvUnid != null ? !apvUnid.equals(propVal.apvUnid) : propVal.apvUnid != null) return false;
        return apvValue != null ? apvValue.equals(propVal.apvValue) : propVal.apvValue == null;
    }

    @Override
    public int hashCode() {
        int result = apvUnid != null ? apvUnid.hashCode() : 0;
        result = 31 * result + (apvValue != null ? apvValue.hashCode() : 0);
        return result;
    }
}
