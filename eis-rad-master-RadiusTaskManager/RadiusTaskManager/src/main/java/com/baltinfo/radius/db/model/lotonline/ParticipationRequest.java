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
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author sas
 */
@Entity
@Table(name = "participation_request", catalog = "", schema = "")
@XmlRootElement
public class ParticipationRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "advance_payment_received")
    private boolean advancePaymentReceived;
    @Column(name = "payment_type")
    private String paymentType;
    @Column(name = "state")
    private String state;
    @Column(name = "user_id")
    private BigInteger userId;
    @Column(name = "documentlist_id")
    private BigInteger documentlistId;
    @Basic(optional = false)
    @Column(name = "profile_id")
    private long profileId;
    @Basic(optional = false)
    @Column(name = "request_type")
    private String requestType;
    @Column(name = "ticket")
    private Integer ticket;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "last_rejection_reason")
    private String lastRejectionReason;
    @Column(name = "cancel_reason")
    private String cancelReason;
    @Column(name = "operational_status")
    private String operationalStatus;
    @Basic(optional = false)
    @Column(name = "submited_from_internet")
    private boolean submitedFromInternet;
    @Column(name = "request_code")
    private String requestCode;
    @Column(name = "linked_offer_id")
    private Long linkedOfferId;
    @Column(name = "cancel_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cancelDate;
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @Column(name = "deposit_id")
    private BigInteger depositId;
    @Column(name = "provision_id")
    private BigInteger provisionId;
    @Column(name = "cancel_reason_fk")
    private BigInteger cancelReasonFk;
    @Column(name = "lot_id")
    private BigInteger lotId;
    @Column(name = "tender_fk")
    private BigInteger tenderFk;

    public ParticipationRequest() {
    }

    public ParticipationRequest(Long id) {
        this.id = id;
    }

    public ParticipationRequest(Long id, boolean advancePaymentReceived, long profileId, String requestType, boolean submitedFromInternet) {
        this.id = id;
        this.advancePaymentReceived = advancePaymentReceived;
        this.profileId = profileId;
        this.requestType = requestType;
        this.submitedFromInternet = submitedFromInternet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getAdvancePaymentReceived() {
        return advancePaymentReceived;
    }

    public void setAdvancePaymentReceived(boolean advancePaymentReceived) {
        this.advancePaymentReceived = advancePaymentReceived;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public BigInteger getDocumentlistId() {
        return documentlistId;
    }

    public void setDocumentlistId(BigInteger documentlistId) {
        this.documentlistId = documentlistId;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastRejectionReason() {
        return lastRejectionReason;
    }

    public void setLastRejectionReason(String lastRejectionReason) {
        this.lastRejectionReason = lastRejectionReason;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getOperationalStatus() {
        return operationalStatus;
    }

    public void setOperationalStatus(String operationalStatus) {
        this.operationalStatus = operationalStatus;
    }

    public boolean getSubmitedFromInternet() {
        return submitedFromInternet;
    }

    public void setSubmitedFromInternet(boolean submitedFromInternet) {
        this.submitedFromInternet = submitedFromInternet;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public Long getLinkedOfferId() {
        return linkedOfferId;
    }

    public void setLinkedOfferId(Long linkedOfferId) {
        this.linkedOfferId = linkedOfferId;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public BigInteger getDepositId() {
        return depositId;
    }

    public void setDepositId(BigInteger depositId) {
        this.depositId = depositId;
    }

    public BigInteger getProvisionId() {
        return provisionId;
    }

    public void setProvisionId(BigInteger provisionId) {
        this.provisionId = provisionId;
    }

    public BigInteger getCancelReasonFk() {
        return cancelReasonFk;
    }

    public void setCancelReasonFk(BigInteger cancelReasonFk) {
        this.cancelReasonFk = cancelReasonFk;
    }

    public BigInteger getLotId() {
        return lotId;
    }

    public void setLotId(BigInteger lotId) {
        this.lotId = lotId;
    }

    public BigInteger getTenderFk() {
        return tenderFk;
    }

    public void setTenderFk(BigInteger tenderFk) {
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
        if (!(object instanceof ParticipationRequest)) {
            return false;
        }
        ParticipationRequest other = (ParticipationRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.lotonline.model.ParticipationRequest[ id=" + id + " ]";
    }

}
