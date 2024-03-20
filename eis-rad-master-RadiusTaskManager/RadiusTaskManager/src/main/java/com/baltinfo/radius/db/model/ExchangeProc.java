package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 *
 * @author css
 */
@Entity
@Table(name = "exchange_proc", catalog = "", schema = "web", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ep_unid"})})
@SequenceGenerator(name = "seq_exchange_proc", sequenceName = "seq_exchange_proc", allocationSize = 1)
@XmlRootElement
public class ExchangeProc {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_exchange_proc")
    @Column(name = "ep_unid", nullable = false, precision = 2147483647, scale = 0)
    private long epUnid;
    @Column(name = "found_unid")
    private Integer foundUnid;
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
    @Column(name = "ep_name", length = 2147483647)
    private String epName;
    @Column(name = "ep_sname", length = 2147483647)
    private String epSname;
    @Column(name = "ep_ind_manage")
    private Integer epIndManage;
    @Column(name = "ep_ind_stop_on_error")
    private Integer epIndStopOnError;

    public ExchangeProc() {
    }

    public ExchangeProc(long epUnid) {
        this.epUnid = epUnid;
    }

    public long getEpUnid() {
        return epUnid;
    }

    public void setEpUnid(long epUnid) {
        this.epUnid = epUnid;
    }

    public Integer getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Integer foundUnid) {
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

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public String getEpSname() {
        return epSname;
    }

    public void setEpSname(String epSname) {
        this.epSname = epSname;
    }

    public Integer getEpIndManage() {
        return epIndManage;
    }

    public void setEpIndManage(Integer epIndManage) {
        this.epIndManage = epIndManage;
    }

    public Integer getEpIndStopOnError() {
        return epIndStopOnError;
    }

    public void setEpIndStopOnError(Integer epIndStopOnError) {
        this.epIndStopOnError = epIndStopOnError;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.ExchangeProc[ epUnid=" + epUnid + " ]";
    }
    
}
