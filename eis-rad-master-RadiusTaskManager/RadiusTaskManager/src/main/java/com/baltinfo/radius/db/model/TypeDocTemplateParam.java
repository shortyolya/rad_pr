/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baltinfo.radius.db.model;


import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

/**
 * @author kya
 */
@Entity
@Table(name = "type_doc_template_param", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"tdtp_unid"})})
@XmlRootElement
public class TypeDocTemplateParam implements IHistory {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "tdtp_unid")
    private long tdtpUnid;
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
    @Column(name = "tdtp_name")
    private String tdtpName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tdtpUnid")
    private Collection<DocTemplateParam> docTemplateParamCollection;

    public TypeDocTemplateParam() {
    }

    public TypeDocTemplateParam(long tdtpUnid) {
        this.tdtpUnid = tdtpUnid;
    }

    public long getTdtpUnid() {
        return tdtpUnid;
    }

    public void setTdtpUnid(long tdtpUnid) {
        this.tdtpUnid = tdtpUnid;
    }

    public BigInteger getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(BigInteger foundUnid) {
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

    public String getTdtpName() {
        return tdtpName;
    }

    public void setTdtpName(String tdtpName) {
        this.tdtpName = tdtpName;
    }

    @XmlTransient
    public Collection<DocTemplateParam> getDocTemplateParamCollection() {
        return docTemplateParamCollection;
    }

    public void setDocTemplateParamCollection(Collection<DocTemplateParam> docTemplateParamCollection) {
        this.docTemplateParamCollection = docTemplateParamCollection;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.TypeDocTemplateParam[ tdtpUnid=" + tdtpUnid + " ]";
    }

}
