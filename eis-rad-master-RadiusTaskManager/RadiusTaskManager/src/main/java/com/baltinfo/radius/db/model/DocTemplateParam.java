/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baltinfo.radius.db.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import java.math.BigInteger;
import java.util.Date;

/**
 * @author kya
 */
@Entity
@Table(name = "doc_template_param", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"dt_param_unid"})})
@SequenceGenerator(name = "seq_doc_template_param", sequenceName = "seq_doc_template_param", allocationSize = 1)
@XmlRootElement
public class DocTemplateParam implements IHistory {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_doc_template_param")
    @Column(name = "dt_param_unid", nullable = false, precision = 2147483647, scale = 0)
    private long dtParamUnid;
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
    @Column(name = "dt_param_name")
    private String dtParamName;
    @Column(name = "dt_param_text")
    private String dtParamText;
    @Column(name = "dt_param_def_value")
    private String dtParamDefValue;
    @Column(name = "dt_param_ind_mandatory")
    private BigInteger dtParamIndMandatory;
    @JoinColumn(name = "tdtp_unid", referencedColumnName = "tdtp_unid")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TypeDocTemplateParam tdtpUnid;
    @JoinColumn(name = "tdic_unid", referencedColumnName = "tdic_unid")
    @ManyToOne(fetch = FetchType.LAZY)
    private TypeDictionary tdicUnid;
    @Column(name = "dt_unid", nullable = false)
    private Long dtUnid;
    @Column(name = "dt_param_sql")
    private String dtParamSql;
    @Column(name = "dt_param_ind_entity")
    private BigInteger dtParamIndEntity;

    public DocTemplateParam() {
    }

    public DocTemplateParam(long dtParamUnid) {
        this.dtParamUnid = dtParamUnid;
    }

    public long getDtParamUnid() {
        return dtParamUnid;
    }

    public void setDtParamUnid(long dtParamUnid) {
        this.dtParamUnid = dtParamUnid;
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

    public String getDtParamName() {
        return dtParamName;
    }

    public void setDtParamName(String dtParamName) {
        this.dtParamName = dtParamName;
    }

    public String getDtParamText() {
        return dtParamText;
    }

    public void setDtParamText(String dtParamText) {
        this.dtParamText = dtParamText;
    }

    public String getDtParamDefValue() {
        return dtParamDefValue;
    }

    public void setDtParamDefValue(String dtParamDefValue) {
        this.dtParamDefValue = dtParamDefValue;
    }

    public BigInteger getDtParamIndMandatory() {
        return dtParamIndMandatory;
    }

    public void setDtParamIndMandatory(BigInteger dtParamIndMandatory) {
        this.dtParamIndMandatory = dtParamIndMandatory;
    }

    public TypeDocTemplateParam getTdtpUnid() {
        return tdtpUnid;
    }

    public void setTdtpUnid(TypeDocTemplateParam tdtpUnid) {
        this.tdtpUnid = tdtpUnid;
    }

    public TypeDictionary getTdicUnid() {
        return tdicUnid;
    }

    public void setTdicUnid(TypeDictionary tdicUnid) {
        this.tdicUnid = tdicUnid;
    }

    public Long getDtUnid() {
        return dtUnid;
    }

    public void setDtUnid(Long dtUnid) {
        this.dtUnid = dtUnid;
    }

    public String getDtParamSql() {
        return dtParamSql;
    }

    public void setDtParamSql(String dtParamSql) {
        this.dtParamSql = dtParamSql;
    }

    public BigInteger getDtParamIndEntity() {
        return dtParamIndEntity;
    }

    public void setDtParamIndEntity(BigInteger dtParamIndEntity) {
        this.dtParamIndEntity = dtParamIndEntity;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.DocTemplateParam[ dtParamUnid=" + dtParamUnid + " ]";
    }

}
