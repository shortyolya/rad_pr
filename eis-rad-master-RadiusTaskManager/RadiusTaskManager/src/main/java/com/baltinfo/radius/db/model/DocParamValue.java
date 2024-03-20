package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@Table(name = "doc_param_value", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"dpv_unid"})})
@NamedQueries({
        @NamedQuery(name = "DocParamValue.getByDocumUnid", query = "select d from DocParamValue d where d.indActual = 1 and d.documUnid.documUnid = :documUnid ")
})
@XmlRootElement
public class DocParamValue implements IHistory {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "dpv_unid", nullable = false, precision = 2147483647, scale = 0)
    private long dpvUnid;
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
    @JoinColumn(name = "docum_unid", referencedColumnName = "docum_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private Document documUnid;
    @JoinColumn(name = "dt_param_unid", referencedColumnName = "dt_param_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private DocTemplateParam dtParamUnid;
    @Column(name = "dpv_value")
    private String dpvValue;

    public DocParamValue() {
    }

    public Document getDocumUnid() {
        return documUnid;
    }

    public void setDocumUnid(Document documUnid) {
        this.documUnid = documUnid;
    }

    public DocTemplateParam getDtParamUnid() {
        return dtParamUnid;
    }

    public void setDtParamUnid(DocTemplateParam dtParamUnid) {
        this.dtParamUnid = dtParamUnid;
    }

    public String getDpvValue() {
        return dpvValue;
    }

    public void setDpvValue(String dpvValue) {
        this.dpvValue = dpvValue;
    }

    public long getDpvUnid() {
        return dpvUnid;
    }

    public void setDpvUnid(long dpvUnid) {
        this.dpvUnid = dpvUnid;
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
}
