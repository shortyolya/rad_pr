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
@Table(name = "notification", catalog = "", schema = "web")
@XmlRootElement
@SequenceGenerator(name = "seq_notification", sequenceName = "seq_notification", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n")})
public class Notification implements Serializable, IHistory {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_notification")
    @Column(name = "ntf_unid")
    private Long ntfUnid;
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
    @Column(name = "ntf_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ntfTime;
    @Column(name = "ntf_ind_exec")
    private Integer ntfIndExec;
    @Column(name = "ntf_ind_repeat")
    private Integer ntfIndRepeat;
    @Column(name = "ntf_ind_email")
    private Integer ntfIndEmail;
    @Column(name = "ntf_ind_system")
    private Integer ntfIndSystem;
    @JoinColumn(name = "event_unid", referencedColumnName = "event_unid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Event eventUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
    @Basic(optional = false)
    @Column(name = "sub_unid")
    private Long subUnid;
    @Column(name = "tpa_unid")
    private Long tpaUnid;
    @Column(name = "ntf_text")
    private String ntfText;

    public Notification() {
    }

    public Notification(Long ntfUnid) {
        this.ntfUnid = ntfUnid;
    }

    public Notification(Long ntfUnid, Event eventUnid, Long subUnid) {
        this.ntfUnid = ntfUnid;
        this.eventUnid = eventUnid;
        this.subUnid = subUnid;
    }

    public Long getNtfUnid() {
        return ntfUnid;
    }

    public void setNtfUnid(Long ntfUnid) {
        this.ntfUnid = ntfUnid;
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

    public Date getNtfTime() {
        return ntfTime;
    }

    public void setNtfTime(Date ntfTime) {
        this.ntfTime = ntfTime;
    }

    public Integer getNtfIndExec() {
        return ntfIndExec;
    }

    public void setNtfIndExec(Integer ntfIndExec) {
        this.ntfIndExec = ntfIndExec;
    }

    public Integer getNtfIndRepeat() {
        return ntfIndRepeat;
    }

    public void setNtfIndRepeat(Integer ntfIndRepeat) {
        this.ntfIndRepeat = ntfIndRepeat;
    }

    public Integer getNtfIndEmail() {
        return ntfIndEmail;
    }

    public void setNtfIndEmail(Integer ntfIndEmail) {
        this.ntfIndEmail = ntfIndEmail;
    }

    public void setNtfIndEmail(boolean ntfIndEmail) {
        this.ntfIndEmail = ntfIndEmail ? 1 : 0;
    }

    public Event getEventUnid() {
        return eventUnid;
    }

    public void setEventUnid(Event eventUnid) {
        this.eventUnid = eventUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    public Long getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(Long subUnid) {
        this.subUnid = subUnid;
    }

    public Long getTpaUnid() {
        return tpaUnid;
    }

    public void setTpaUnid(Long tpaUnid) {
        this.tpaUnid = tpaUnid;
    }

    public Integer getNtfIndSystem() {
        return ntfIndSystem;
    }

    public void setNtfIndSystem(Integer ntfIndSystem) {
        this.ntfIndSystem = ntfIndSystem;
    }

    public void setNtfIndSystem(boolean ntfIndSystem) {
        this.ntfIndSystem = ntfIndSystem ? 1 : 0;
    }


    public String getNtfText() {
        return ntfText;
    }

    public void setNtfText(String ntfText) {
        this.ntfText = ntfText;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ntfUnid != null ? ntfUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.Notification[ ntfUnid=" + ntfUnid + " ]";
    }

}
