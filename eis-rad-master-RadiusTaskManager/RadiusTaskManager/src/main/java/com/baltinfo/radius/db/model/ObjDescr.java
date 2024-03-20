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
 */
@Entity
@Table(name = "obj_descr", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"od_unid"})})
@SequenceGenerator(name = "seq_obj_descr", sequenceName = "seq_obj_descr", allocationSize = 1)
@XmlRootElement
public class ObjDescr implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_obj_descr")
    @Column(name = "od_unid", nullable = false, precision = 2147483647, scale = 0)
    private long odUnid;
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
    @Column(name = "od_text", length = 2147483647)
    private String odText;
    @Column(name = "tod_unid", nullable = false)
    private Long todUnid;
    @Column(name = "obj_unid", nullable = false)
    private Long objUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
    @Column(name = "od_num")
    private Long odNum;

    public ObjDescr() {
    }

    public ObjDescr(long odUnid) {
        this.odUnid = odUnid;
    }

    public long getOdUnid() {
        return odUnid;
    }

    public void setOdUnid(long odUnid) {
        this.odUnid = odUnid;
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

    public String getOdText() {
        return odText;
    }

    public void setOdText(String odText) {
        this.odText = odText;
    }

    public Long getTodUnid() {
        return todUnid;
    }

    public void setTodUnid(Long todUnid) {
        this.todUnid = todUnid;
    }

    public Long getObjUnid() {
        return objUnid;
    }

    public void setObjUnid(Long objUnid) {
        this.objUnid = objUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    public Long getOdNum() {
        return odNum;
    }

    public void setOdNum(Long odNum) {
        this.odNum = odNum;
    }

    @Override
    public String toString() {
        return "com.baltinfo.radius.db.model.ObjDescr[ odUnid=" + odUnid + " ]";
    }

}
