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
 * Ход обмена
 *
 * @author sas
 */
@Entity
@Table(name = "running_details", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"rd_unid"})})
@SequenceGenerator(name = "seq_running_details", sequenceName = "seq_running_details", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "RunningDetails.findAll", query = "SELECT r FROM RunningDetails r where r.indActual = 1"),
        @NamedQuery(name = "RunningDetails.findByEprUnid", query = "SELECT r FROM RunningDetails r where r.indActual = 1 and r.eprUnid = :eprUnid"),
        @NamedQuery(name = "RunningDetails.findByResultStatus", query = "SELECT r FROM RunningDetails r where r.indActual = 1 and r.eprUnid = :eprUnid and r.rdResult = :result order by r.rdSourceId")})
public class RunningDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_running_details")
    @Column(name = "rd_unid")
    private Long rdUnid;
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
    @Column(name = "rd_source_id")
    private Long rdSourceId;
    @Column(name = "rd_receive_id")
    private Long rdReceiveId;
    @Column(name = "rd_result")
    private Integer rdResult;
    @Column(name = "rd_error_info")
    private String rdErrorInfo;
    @Basic(optional = false)
    @Column(name = "epr_unid")
    private Long eprUnid;

    public RunningDetails() {
    }

    public RunningDetails(Long rdUnid) {
        this.rdUnid = rdUnid;
    }

    public RunningDetails(Long rdUnid, Long eprUnid) {
        this.rdUnid = rdUnid;
        this.eprUnid = eprUnid;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getRdUnid() {
        return rdUnid;
    }

    public void setRdUnid(Long rdUnid) {
        this.rdUnid = rdUnid;
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

    public Long getRdSourceId() {
        return rdSourceId;
    }

    public void setRdSourceId(Long rdSourceId) {
        this.rdSourceId = rdSourceId;
    }

    public Long getRdReceiveId() {
        return rdReceiveId;
    }

    public void setRdReceiveId(Long rdReceiveId) {
        this.rdReceiveId = rdReceiveId;
    }

    public Integer getRdResult() {
        return rdResult;
    }

    public void setRdResult(Integer rdResult) {
        this.rdResult = rdResult;
    }

    public String getRdErrorInfo() {
        return rdErrorInfo;
    }

    public void setRdErrorInfo(String rdErrorInfo) {
        this.rdErrorInfo = rdErrorInfo;
    }

    public Long getEprUnid() {
        return eprUnid;
    }

    public void setEprUnid(Long eprUnid) {
        this.eprUnid = eprUnid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rdUnid != null ? rdUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RunningDetails)) {
            return false;
        }
        RunningDetails other = (RunningDetails) object;
        if ((this.rdUnid == null && other.rdUnid != null) || (this.rdUnid != null && !this.rdUnid.equals(other.rdUnid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.mavenproject1.RunningDetails[ rdUnid=" + rdUnid + " ]";
    }

}
