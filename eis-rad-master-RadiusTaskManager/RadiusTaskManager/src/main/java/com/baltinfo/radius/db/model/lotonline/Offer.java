package com.baltinfo.radius.db.model.lotonline;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Suvorina Aleksandra
 * @since 28.12.2020
 */
@Entity
@Table(name = "offer", catalog = "", schema = "")
@XmlRootElement
public class Offer {
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "lot_id")
    private Long lotId;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "narration")
    private String narration;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "place")
    private Integer place;

    @Column(name = "favorite")
    private boolean favorite;

    @Column(name = "rejection_reason_code")
    private String rejectionReasonCode;

    @Column(name = "reject_reason")
    private String rejectReason;

    @Column(name = "offer_status")
    private String offerStatus;

    @Column(name = "linked_participation_request_id")
    private Long linkedParticipationRequest;

    @Column(name = "refuse_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date refuseDate;

    @Column(name = "deposit_id")
    private Long depositId;

    @Column(name = "provision_id")
    private Long provisionId;

    @Column(name = "investment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date investmentDate;

    public Offer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getRejectionReasonCode() {
        return rejectionReasonCode;
    }

    public void setRejectionReasonCode(String rejectionReasonCode) {
        this.rejectionReasonCode = rejectionReasonCode;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(String offerStatus) {
        this.offerStatus = offerStatus;
    }

    public Long getLinkedParticipationRequest() {
        return linkedParticipationRequest;
    }

    public void setLinkedParticipationRequest(Long linkedParticipationRequest) {
        this.linkedParticipationRequest = linkedParticipationRequest;
    }

    public Date getRefuseDate() {
        return refuseDate;
    }

    public void setRefuseDate(Date refuseDate) {
        this.refuseDate = refuseDate;
    }

    public Long getDepositId() {
        return depositId;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    public Long getProvisionId() {
        return provisionId;
    }

    public void setProvisionId(Long provisionId) {
        this.provisionId = provisionId;
    }

    public Date getInvestmentDate() {
        return investmentDate;
    }

    public void setInvestmentDate(Date investmentDate) {
        this.investmentDate = investmentDate;
    }
}
