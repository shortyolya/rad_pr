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
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * @author sas
 */
@Entity
@Table(name = "message_email", catalog = "", schema = "web")
@XmlRootElement
@SequenceGenerator(name = "seq_message_email", sequenceName = "seq_message_email", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "MessageEmail.findAll", query = "SELECT m FROM MessageEmail m")})
public class MessageEmail implements Serializable, IHistory {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_message_email")
    @Column(name = "me_unid")
    private Long meUnid;
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
    @Column(name = "me_recipient")
    private String meRecipient;
    @Column(name = "me_subject")
    private String meSubject;
    @Column(name = "me_text")
    private String meText;
    @Column(name = "me_send_date_plan")
    @Temporal(TemporalType.TIMESTAMP)
    private Date meSendDatePlan;
    @Column(name = "me_send_date_fact")
    @Temporal(TemporalType.TIMESTAMP)
    private Date meSendDateFact;
    @Column(name = "me_ind_send")
    private Integer meIndSend;
    @Column(name = "me_send_result")
    private String meSendResult;
    @Column(name = "found_unid")
    private Long foundUnid;
    @Column(name = "ntf_unid")
    private Long ntfUnid;

    public MessageEmail() {
    }

    public MessageEmail(Long meUnid) {
        this.meUnid = meUnid;
    }

    public MessageEmail(String meRecipient, String meSubject, String meText, Date meSendDatePlan) {
        this.meRecipient = meRecipient;
        this.meSubject = meSubject;
        this.meText = meText;
        this.meSendDatePlan = meSendDatePlan;
        this.meIndSend = 0;
    }

    public Long getMeUnid() {
        return meUnid;
    }

    public void setMeUnid(Long meUnid) {
        this.meUnid = meUnid;
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

    public String getMeRecipient() {
        return meRecipient;
    }

    public void setMeRecipient(String meRecipient) {
        this.meRecipient = meRecipient;
    }

    public String getMeSubject() {
        return meSubject;
    }

    public void setMeSubject(String meSubject) {
        this.meSubject = meSubject;
    }

    public String getMeText() {
        return meText;
    }

    public void setMeText(String meText) {
        this.meText = meText;
    }

    public Date getMeSendDatePlan() {
        return meSendDatePlan;
    }

    public void setMeSendDatePlan(Date meSendDatePlan) {
        this.meSendDatePlan = meSendDatePlan;
    }

    public Date getMeSendDateFact() {
        return meSendDateFact;
    }

    public void setMeSendDateFact(Date meSendDateFact) {
        this.meSendDateFact = meSendDateFact;
    }

    public Integer getMeIndSend() {
        return meIndSend;
    }

    public void setMeIndSend(Integer meIndSend) {
        this.meIndSend = meIndSend;
    }

    public String getMeSendResult() {
        return meSendResult;
    }

    public void setMeSendResult(String meSendResult) {
        this.meSendResult = meSendResult;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    public Long getNtfUnid() {
        return ntfUnid;
    }

    public void setNtfUnid(Long ntfUnid) {
        this.ntfUnid = ntfUnid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (meUnid != null ? meUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessageEmail)) {
            return false;
        }
        MessageEmail other = (MessageEmail) object;
        if ((this.meUnid == null && other.meUnid != null) || (this.meUnid != null && !this.meUnid.equals(other.meUnid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.MessageEmail[ meUnid=" + meUnid + " ]";
    }

}
