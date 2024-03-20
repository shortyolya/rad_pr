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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "explanation_response", catalog="", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"exr_unid"})})
@SequenceGenerator(name = "seq_explanation_response", sequenceName = "seq_explanation_response", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "ExplanationResponse.findByExrUnid", query = "SELECT a FROM ExplanationResponse a WHERE a.exrUnid = :exrUnid"),
        @NamedQuery(name = "ExplanationResponse.findByLotUnid", query = "SELECT a FROM ExplanationResponse a WHERE a.indActual = 1 and a.lotUnid.lotUnid = :lotUnid"),
        @NamedQuery(name = "ExplanationResponse.findCountByExrEtpId", query = "SELECT count(a) FROM ExplanationResponse a WHERE a.indActual = 1 and a.exrEtpId = :exrEtpId")
})
@NamedEntityGraphs({
        @NamedEntityGraph(name = "graph.ExplanationResponse.all", attributeNodes = {
                @NamedAttributeNode(value = "paUnid"),
                @NamedAttributeNode(value = "lotUnid")
        })
})
public class ExplanationResponse implements Serializable, IHistory {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_explanation_response")
    @Column(name = "exr_unid", nullable = false, precision = 2147483647, scale = 0)
    private long exrUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
    @JoinColumn(name = "pa_unid", referencedColumnName = "pa_unid")
    @ManyToOne(fetch= FetchType.LAZY)
    private ParticipantAgent paUnid;
    @Column(name = "lot_unid")
    private Long lotUnid;
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
    @Column(name = "exr_question_thema")
    private String exrQuestionThema;
    @Column(name = "exr_question_text")
    private String exrQuestionText;
    @Column(name = "exr_question_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exrQuestionTime;
    @Column(name = "exr_answer_text")
    private String exrAnswerText;
    @Column(name = "exr_answer_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exrAnswerTime;
    @Column(name = "exr_etp_id")
    private Long exrEtpId;

    public long getExrUnid() {
        return exrUnid;
    }

    public void setExrUnid(long exrUnid) {
        this.exrUnid = exrUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    public ParticipantAgent getPaUnid() {
        return paUnid;
    }

    public void setPaUnid(ParticipantAgent paUnid) {
        this.paUnid = paUnid;
    }

    public Long getLotUnid() {
        return lotUnid;
    }

    public void setLotUnid(Long lotUnid) {
        this.lotUnid = lotUnid;
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

    public String getExrQuestionThema() {
        return exrQuestionThema;
    }

    public void setExrQuestionThema(String exrQuestionThema) {
        this.exrQuestionThema = exrQuestionThema;
    }

    public String getExrQuestionText() {
        return exrQuestionText;
    }

    public void setExrQuestionText(String exrQuestionText) {
        this.exrQuestionText = exrQuestionText;
    }

    public Date getExrQuestionTime() {
        return exrQuestionTime;
    }

    public void setExrQuestionTime(Date exrQuestionTime) {
        this.exrQuestionTime = exrQuestionTime;
    }

    public String getExrAnswerText() {
        return exrAnswerText;
    }

    public void setExrAnswerText(String exrAnswerText) {
        this.exrAnswerText = exrAnswerText;
    }

    public Date getExrAnswerTime() {
        return exrAnswerTime;
    }

    public void setExrAnswerTime(Date exrAnswerTime) {
        this.exrAnswerTime = exrAnswerTime;
    }

    public Long getExrEtpId() {
        return exrEtpId;
    }

    public void setExrEtpId(Long exrEtpId) {
        this.exrEtpId = exrEtpId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExplanationResponse that = (ExplanationResponse) o;

        return exrUnid == that.exrUnid;
    }

    @Override
    public int hashCode() {
        return (int) (exrUnid ^ (exrUnid >>> 32));
    }

}
