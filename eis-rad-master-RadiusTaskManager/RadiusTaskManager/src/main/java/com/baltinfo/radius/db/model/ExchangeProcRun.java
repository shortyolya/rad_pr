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
import java.io.Serializable;
import java.util.Date;

/**
 * Журнал запуска процедуры информационного обмена
 *
 * @author sas
 */
@Entity
@Table(name = "exchange_proc_run", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"epr_unid"})})
@SequenceGenerator(name = "seq_exchange_proc_run", sequenceName = "seq_exchange_proc_run", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "ExchangeProcRun.findAll", query = "SELECT e FROM ExchangeProcRun e where e.indActual = 1"),
        @NamedQuery(name = "ExchangeProcRun.findByStatus", query = "SELECT e FROM ExchangeProcRun e where e.indActual = 1 and e.eprLoadStatus = :eprLoadStatus and e.epUnid = :epUnid order by e.dateB"),
        @NamedQuery(name = "ExchangeProcRun.findByReceiverId", query = "SELECT e.eprSourceId FROM ExchangeProcRun e WHERE e.indActual = 1 and e.eprReceiverId = :eprReceiverId"),
        @NamedQuery(name = "ExchangeProcRun.findLastSuccessDateByEpUnid",
                query = "SELECT max(e.eprRunDate) FROM ExchangeProcRun e WHERE e.eprLoadStatus = 4 AND e.epUnid = :epUnid AND e.indActual = 1")

})
public class ExchangeProcRun implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_exchange_proc_run")
    @Column(name = "epr_unid")
    private Long eprUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
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
    @Column(name = "epr_run_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eprRunDate;
    @Column(name = "epr_error_text")
    private String eprErrorText;
    @Column(name = "epr_source_id")
    private Long eprSourceId;
    @Column(name = "epr_receiver_id")
    private Long eprReceiverId;
    @Column(name = "epr_pa_unid")
    private Long eprPaUnid;
    @Column(name = "epr_load_status")
    private Integer eprLoadStatus;
    @Basic(optional = false)
    @Column(name = "ep_unid")
    private Long epUnid;

    public ExchangeProcRun() {
    }

    public ExchangeProcRun(Long eprUnid) {
        this.eprUnid = eprUnid;
    }

    public ExchangeProcRun(Long eprUnid, Long epUnid) {
        this.eprUnid = eprUnid;
        this.epUnid = epUnid;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getEprUnid() {
        return eprUnid;
    }

    public void setEprUnid(Long eprUnid) {
        this.eprUnid = eprUnid;
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

    public Date getEprRunDate() {
        return eprRunDate;
    }

    public void setEprRunDate(Date eprRunDate) {
        this.eprRunDate = eprRunDate;
    }

    public String getEprErrorText() {
        return eprErrorText;
    }

    public void setEprErrorText(String eprErrorText) {
        this.eprErrorText = eprErrorText;
    }

    public Long getEprSourceId() {
        return eprSourceId;
    }

    public void setEprSourceId(Long eprSourceId) {
        this.eprSourceId = eprSourceId;
    }

    public Long getEprReceiverId() {
        return eprReceiverId;
    }

    public void setEprReceiverId(Long eprReceiverId) {
        this.eprReceiverId = eprReceiverId;
    }

    public Long getEprPaUnid() {
        return eprPaUnid;
    }

    public void setEprPaUnid(Long eprPaUnid) {
        this.eprPaUnid = eprPaUnid;
    }

    public Integer getEprLoadStatus() {
        return eprLoadStatus;
    }

    public void setEprLoadStatus(Integer eprLoadStatus) {
        this.eprLoadStatus = eprLoadStatus;
    }

    public Long getEpUnid() {
        return epUnid;
    }

    public void setEpUnid(Long epUnid) {
        this.epUnid = epUnid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eprUnid != null ? eprUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExchangeProcRun)) {
            return false;
        }
        ExchangeProcRun other = (ExchangeProcRun) object;
        if ((this.eprUnid == null && other.eprUnid != null) || (this.eprUnid != null && !this.eprUnid.equals(other.eprUnid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.mavenproject1.ExchangeProcRun[ eprUnid=" + eprUnid + " ]";
    }

}
