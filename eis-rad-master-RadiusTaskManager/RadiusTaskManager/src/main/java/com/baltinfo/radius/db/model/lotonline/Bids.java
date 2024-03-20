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
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author sas
 */
@Entity
@Table(name = "bids", catalog = "", schema = "")
@XmlRootElement
public class Bids implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "bid_price")
    private BigDecimal bidPrice;
    @Column(name = "bid_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bidTimestamp;
    @Basic(optional = false)
    @Column(name = "user_fk")
    private long userFk;
    @Column(name = "bid_status")
    private String bidStatus;
    @Column(name = "ticket")
    private Integer ticket;
    @Basic(optional = false)
    @Column(name = "accepted_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acceptedTimestamp;
    @Basic(optional = false)
    @Column(name = "bid_type")
    private String bidType;
    @Basic(optional = false)
    @Column(name = "tender_flow_fk")
    private long tenderFlowFk;

    public Bids() {
    }

    public Bids(Long id) {
        this.id = id;
    }

    public Bids(Long id, long userFk, Date acceptedTimestamp, String bidType, long tenderFlowFk) {
        this.id = id;
        this.userFk = userFk;
        this.acceptedTimestamp = acceptedTimestamp;
        this.bidType = bidType;
        this.tenderFlowFk = tenderFlowFk;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }

    public Date getBidTimestamp() {
        return bidTimestamp;
    }

    public void setBidTimestamp(Date bidTimestamp) {
        this.bidTimestamp = bidTimestamp;
    }

    public long getUserFk() {
        return userFk;
    }

    public void setUserFk(long userFk) {
        this.userFk = userFk;
    }

    public String getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(String bidStatus) {
        this.bidStatus = bidStatus;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public Date getAcceptedTimestamp() {
        return acceptedTimestamp;
    }

    public void setAcceptedTimestamp(Date acceptedTimestamp) {
        this.acceptedTimestamp = acceptedTimestamp;
    }

    public String getBidType() {
        return bidType;
    }

    public void setBidType(String bidType) {
        this.bidType = bidType;
    }

    public long getTenderFlowFk() {
        return tenderFlowFk;
    }

    public void setTenderFlowFk(long tenderFlowFk) {
        this.tenderFlowFk = tenderFlowFk;
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
        if (!(object instanceof Bids)) {
            return false;
        }
        Bids other = (Bids) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.lotonline.model.Bids[ id=" + id + " ]";
    }

}
