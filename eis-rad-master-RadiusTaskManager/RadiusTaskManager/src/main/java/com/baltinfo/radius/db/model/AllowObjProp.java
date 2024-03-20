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
import java.math.BigDecimal;
import java.util.Date;

/**
 * Допустимое свойство объекта
 */
@Entity
@Table(name = "allow_obj_prop", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"aop_unid"})})
@SequenceGenerator(name = "seq_allow_obj_prop", sequenceName = "seq_allow_obj_prop", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "AllowObjProp.findAll", query = "select a from AllowObjProp a where a.indActual = 1"),
        @NamedQuery(name = "AllowObjProp.findByAopUnid",
                query = "select a from AllowObjProp a where a.indActual = 1 and aopUnid = :aopUnid")
})
public class AllowObjProp implements IHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_allow_obj_prop")
    @Basic(optional = false)
    @Column(name = "aop_unid", nullable = false, precision = 2147483647)
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
    @Column(name = "aop_name")
    private String aopName;
    @Column(name = "aop_type")
    private String aopType;
    @Column(name = "aop_mask")
    private String aopMask;
    @Column(name = "aop_def_val")
    private String aopDefVal;
    @Column(name = "aop_code")
    private String aopCode;
    @Column(name = "aop_mandatory")
    private Boolean aopMandatory;
    @Column(name = "aop_min_val")
    private BigDecimal aopMinVal;
    @Column(name = "aop_max_val")
    private BigDecimal aopMaxVal;
    @Column(name = "aop_parent_unid")
    private Long aopParentUnid;

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

    public String getAopName() {
        return aopName;
    }

    public void setAopName(String aopName) {
        this.aopName = aopName;
    }

    public String getAopType() {
        return aopType;
    }

    public void setAopType(String aopType) {
        this.aopType = aopType;
    }

    public String getAopMask() {
        return aopMask;
    }

    public void setAopMask(String aopMask) {
        this.aopMask = aopMask;
    }

    public String getAopDefVal() {
        return aopDefVal;
    }

    public void setAopDefVal(String aopDefVal) {
        this.aopDefVal = aopDefVal;
    }

    public String getAopCode() {
        return aopCode;
    }

    public void setAopCode(String aopCode) {
        this.aopCode = aopCode;
    }

    public Boolean getAopMandatory() {
        return aopMandatory;
    }

    public void setAopMandatory(Boolean aopMandatory) {
        this.aopMandatory = aopMandatory;
    }


    public BigDecimal getAopMinVal() {
        return aopMinVal;
    }

    public void setAopMinVal(BigDecimal aopMinVal) {
        this.aopMinVal = aopMinVal;
    }

    public BigDecimal getAopMaxVal() {
        return aopMaxVal;
    }

    public void setAopMaxVal(BigDecimal aopMaxVal) {
        this.aopMaxVal = aopMaxVal;
    }

    public Long getAopParentUnid() {
        return aopParentUnid;
    }

    public void setAopParentUnid(Long aopParentUnid) {
        this.aopParentUnid = aopParentUnid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllowObjProp that = (AllowObjProp) o;

        return aopUnid.equals(that.aopUnid);
    }

    @Override
    public int hashCode() {
        return aopUnid.hashCode();
    }
}
