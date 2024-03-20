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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author css
 */
@Entity
@Table(name = "doc_template_part", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"dtp_unid"})})
@XmlRootElement
public class DocTemplatePart implements IHistory {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "dtp_unid", nullable = false, precision = 2147483647, scale = 0)
    private long dtpUnid;
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
    @Column(name = "dtp_name", length = 2147483647)
    private String dtpName;
    @Column(name = "dtp_code", length = 2147483647)
    private String dtpCode;
    @Column(name = "dtp_sql", length = 2147483647)
    private String dtpSql;
    @Column(name = "dt_unid")
    private Long dtUnid;
    @Column(name = "dtp_ind_list")
    private Short dtpIndList;


    public DocTemplatePart() {
    }

    public long getDtpUnid() {
        return dtpUnid;
    }

    public void setDtpUnid(long dtpUnid) {
        this.dtpUnid = dtpUnid;
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

    public String getDtpName() {
        return dtpName;
    }

    public void setDtpName(String dtpName) {
        this.dtpName = dtpName;
    }

    public String getDtpCode() {
        return dtpCode;
    }

    public void setDtpCode(String dtpCode) {
        this.dtpCode = dtpCode;
    }

    public String getDtpSql() {
        return dtpSql;
    }

    public void setDtpSql(String dtpSql) {
        this.dtpSql = dtpSql;
    }

    public Long getDtUnid() {
        return dtUnid;
    }

    public void setDtUnid(Long dtUnid) {
        this.dtUnid = dtUnid;
    }

    public Short getDtpIndList() {
        return dtpIndList;
    }

    public void setDtpIndList(Short dtpIndList) {
        this.dtpIndList = dtpIndList;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.DocTemplatePart[ dtpUnid=" + dtpUnid + " ]";
    }

}
