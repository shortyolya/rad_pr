package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "doc_generation_type", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"dgt_unid"})})
@XmlRootElement
public class DocGenerationType implements IHistory {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "dgt_unid")
    private long dgtUnid;
    @Column(name = "found_unid")
    private BigInteger foundUnid;
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
    @Column(name = "dgt_name")
    private String dgtName;
    @Column(name = "dgt_sname")
    private String dgtSname;

    public DocGenerationType() {
    }

    public long getDgtUnid() {
        return dgtUnid;
    }

    public void setDgtUnid(long dgtUnid) {
        this.dgtUnid = dgtUnid;
    }

    public BigInteger getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(BigInteger foundUnid) {
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

    @Override
    public Date getDateB() {
        return dateB;
    }

    @Override
    public void setDateB(Date dateB) {
        this.dateB = dateB;
    }

    @Override
    public String getFoundB() {
        return foundB;
    }

    @Override
    public void setFoundB(String foundB) {
        this.foundB = foundB;
    }

    @Override
    public Long getPersCodeB() {
        return persCodeB;
    }

    @Override
    public void setPersCodeB(Long persCodeB) {
        this.persCodeB = persCodeB;
    }

    @Override
    public Date getDateBRec() {
        return dateBRec;
    }

    @Override
    public void setDateBRec(Date dateBRec) {
        this.dateBRec = dateBRec;
    }

    @Override
    public Long getPersCodeBRec() {
        return persCodeBRec;
    }

    @Override
    public void setPersCodeBRec(Long persCodeBRec) {
        this.persCodeBRec = persCodeBRec;
    }

    public String getDgtName() {
        return dgtName;
    }

    public void setDgtName(String dgtName) {
        this.dgtName = dgtName;
    }

    public String getDgtSname() {
        return dgtSname;
    }

    public void setDgtSname(String dgtSname) {
        this.dgtSname = dgtSname;
    }
}
