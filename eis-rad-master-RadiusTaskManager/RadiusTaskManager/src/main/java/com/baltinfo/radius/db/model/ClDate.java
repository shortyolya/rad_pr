package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author sas
 */
@Entity
@Table(name = "cl_date", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"date_date"})})
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "ClDate.findAll", query = "SELECT c FROM ClDate c"),
        @NamedQuery(name = "ClDate.findByDateDate", query = "SELECT c FROM ClDate c WHERE c.dateDate = :dateDate"),
        @NamedQuery(name = "ClDate.findByDateInterval", query = "SELECT c FROM ClDate c WHERE c.dateDate >= :beginDate and c.dateDate <= :endDate"),
        @NamedQuery(name = "ClDate.findByDateIndHoliday", query = "SELECT c FROM ClDate c WHERE c.dateIndHoliday = :dateIndHoliday")})
public class ClDate {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "date_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDate;
    @Column(name = "date_ind_holiday")
    private Short dateIndHoliday;

    public ClDate() {
    }

    public ClDate(Date dateDate) {
        this.dateDate = dateDate;
    }

    public Date getDateDate() {
        return dateDate;
    }

    public void setDateDate(Date dateDate) {
        this.dateDate = dateDate;
    }

    public Short getDateIndHoliday() {
        return dateIndHoliday;
    }

    public void setDateIndHoliday(Short dateIndHoliday) {
        this.dateIndHoliday = dateIndHoliday;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dateDate != null ? dateDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClDate)) {
            return false;
        }
        ClDate other = (ClDate) object;
        if ((this.dateDate == null && other.dateDate != null) || (this.dateDate != null && !this.dateDate.equals(other.dateDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.ClDate[ dateDate=" + dateDate + " ]";
    }

}
