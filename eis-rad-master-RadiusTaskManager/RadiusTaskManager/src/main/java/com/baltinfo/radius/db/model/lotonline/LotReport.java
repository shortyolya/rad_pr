/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baltinfo.radius.db.model.lotonline;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author sas
 */
@Entity
@Table(name = "lot_report", catalog = "", schema = "")
@XmlRootElement
public class LotReport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "report_name")
    private String reportName;
    @Basic(optional = false)
    @Column(name = "report_filename")
    private String reportFilename;
    @Basic(optional = false)
    @Column(name = "report_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportTime;
    @Basic(optional = false)
    @Column(name = "is_protocol")
    private boolean isProtocol;
    @Column(name = "code")
    private String code;
    @Column(name = "report_code")
    private String reportCode;
    @Column(name = "hash_sum")
    private String hashSum;
    @Column(name = "lot_fk")
    private Long lotFk;
    @Column(name = "tender_fk")
    private Long tenderFk;

    public LotReport() {
    }

    public LotReport(Long id) {
        this.id = id;
    }

    public LotReport(Long id, String reportName, String reportFilename, Date reportTime, boolean isProtocol) {
        this.id = id;
        this.reportName = reportName;
        this.reportFilename = reportFilename;
        this.reportTime = reportTime;
        this.isProtocol = isProtocol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportFilename() {
        return reportFilename;
    }

    public void setReportFilename(String reportFilename) {
        this.reportFilename = reportFilename;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public boolean getIsProtocol() {
        return isProtocol;
    }

    public void setIsProtocol(boolean isProtocol) {
        this.isProtocol = isProtocol;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getHashSum() {
        return hashSum;
    }

    public void setHashSum(String hashSum) {
        this.hashSum = hashSum;
    }

    public Long getLotFk() {
        return lotFk;
    }

    public void setLotFk(Long lotFk) {
        this.lotFk = lotFk;
    }

    public Long getTenderFk() {
        return tenderFk;
    }

    public void setTenderFk(Long tenderFk) {
        this.tenderFk = tenderFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LotReport)) {
            return false;
        }
        LotReport other = (LotReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.lotonline.model.LotReport[ id=" + id + " ]";
    }

}
