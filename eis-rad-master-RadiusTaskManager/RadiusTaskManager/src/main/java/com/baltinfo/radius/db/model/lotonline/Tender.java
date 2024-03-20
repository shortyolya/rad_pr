package com.baltinfo.radius.db.model.lotonline;

import com.baltinfo.radius.db.controller.exchange.EtpEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

/**
 * @author css
 */
@Entity
@TypeDef(name = "TwoDimensionalStringArrayType", typeClass = TwoDimensionalStringArrayType.class)
@Table(catalog = "", schema = "")
@XmlRootElement
public class Tender implements Serializable, EtpEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "seq_tender_id")
    @GenericGenerator(name = "seq_tender_id",
            strategy = "com.baltinfo.radius.db.model.lotonline.LotOnlineShard3SequnceGenerator",
            parameters = @org.hibernate.annotations.Parameter(name = "sequence", value = "seq_tender_id"))
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Column(name = "flow_type", length = 50)
    private String flowType;
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private long userId;
    @Basic(optional = false)
    @Column(name = "profile_id", nullable = false)
    private long profileId;
    @Basic(optional = false)
    @Column(name = "sale_type_fk", nullable = false)
    private long saleTypeFk;
    @Column(name = "tender_type", length = 50)
    private String tenderType;
    @Column(name = "is_absentee_offer")
    private Boolean isAbsenteeOffer;
    @Column(name = "is_soft_finish")
    private Boolean isSoftFinish;
    @Column(name = "is_absentee_bid")
    private Boolean isAbsenteeBid;
    @Column(name = "is_open_offers")
    private Boolean isOpenOffers;
    @Column(length = 2147483647)
    private String name;
    @Column(name = "inter_name", length = 2147483647)
    private String interName;
    @Column(length = 2147483647)
    private String description;
    @Column(name = "inter_description", length = 2147483647)
    private String interDescription;
    @Column(name = "required_documents", length = 2147483647)
    private String requiredDocuments;
    @Column(name = "inter_required_documents", length = 2147483647)
    private String interRequiredDocuments;
    @Column(name = "is_higgling")
    private Boolean isHiggling;
    @Column(name = "app_submit_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appSubmitStart;
    @Column(name = "app_submit_stop")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appSubmitStop;
    @Column(name = "pre_qualification", length = 50)
    private String preQualification;
    @Basic(optional = false)
    @Column(name = "is_consecutive_bidding", nullable = false)
    private boolean isConsecutiveBidding;
    @Column(name = "is_digital_signature_required")
    private Boolean isDigitalSignatureRequired;
    @Basic(optional = false)
    @Column(name = "email_sent", nullable = false)
    private boolean emailSent;
    @Column(name = "tender_code", length = 50)
    private String tenderCode;
    @Column(name = "tender_status", length = 50)
    private String tenderStatus;
    @Column(name = "finish_interval")
    private Integer finishInterval;
    @Column(name = "publish_from")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishFrom;
    @Basic(optional = false)
    @Column(name = "is_published", nullable = false)
    private boolean isPublished;
    @Basic(optional = false)
    @Column(name = "is_defered_publication", nullable = false)
    private boolean isDeferedPublication;
    @Column(name = "process_app_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date processAppDate;
    @Basic(optional = false)
    @Column(name = "is_process_app_email_sent", nullable = false)
    private boolean isProcessAppEmailSent;
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @Column(name = "is_apps_processed")
    private Boolean isAppsProcessed;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "application_documents", columnDefinition = "TwoDimensionalStringArrayType")
    @Type(type = "TwoDimensionalStringArrayType")
    private String[][] applicationDocuments;
    @Basic(optional = false)
    @Column(name = "is_appl_docs_for_tender", nullable = false)
    private boolean isApplDocsForTender;
    @Basic(optional = false)
    @Column(name = "is_open_consecutive_bidding", nullable = false)
    private boolean isOpenConsecutiveBidding;
    @Column(name = "publication_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publicationDate;
    @Column(name = "active_consecutive_lot_id")
    private BigInteger activeConsecutiveLotId;
    @Column(name = "history_profile_id")
    private BigInteger historyProfileId;
    @Column(name = "end_process_app_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endProcessAppDate;
    @Column(name = "is_only_manual")
    private Boolean isOnlyManual;
    @Column(length = 2147483647)
    private String fio;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Недопустимый формат номера телефона/факса (должен иметь формат xxx-xxx-xxxx)")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Column(length = 2147483647)
    private String phone;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Недопустимый формат номера телефона/факса (должен иметь формат xxx-xxx-xxxx)")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Column(length = 2147483647)
    private String fax;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Недопустимый адрес электронной почты")//if the field contains email address consider using this annotation to enforce field validation
    @Column(length = 2147483647)
    private String email;
    @Column(name = "doc_provision_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date docProvisionStart;
    @Column(name = "doc_provision_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date docProvisionEnd;
    @Column(name = "doc_provision_place", length = 2147483647)
    private String docProvisionPlace;
    @Column(name = "doc_provision_rules", length = 2147483647)
    private String docProvisionRules;
    @Basic(optional = false)
    @Column(name = "doc_provision_payment", nullable = false)
    private boolean docProvisionPayment;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "doc_provision_price", precision = 19, scale = 2)
    private BigDecimal docProvisionPrice;
    @Column(name = "doc_provision_currency_key_fk")
    private Integer docProvisionCurrencyKeyFk;
    @Column(name = "doc_provision_payment_rules", length = 2147483647)
    private String docProvisionPaymentRules;
    @Basic(optional = false)
    @Column(name = "is_additional_doc_requirements", nullable = false)
    private boolean isAdditionalDocRequirements;
    @Column(length = 63)
    private String guid;
    @Column(name = "registration_number", length = 31)
    private String registrationNumber;
    @Column(name = "notice_oos_url", length = 255)
    private String noticeOosUrl;
    @Column(name = "accept_protocol_oos_url", length = 255)
    private String acceptProtocolOosUrl;
    @Column(name = "winner_protocol_oos_url", length = 255)
    private String winnerProtocolOosUrl;
    @Column(name = "new_wizard")
    private Boolean newWizard;
    @Column(name = "root_id")
    private BigInteger rootId;
    @Column(name = "version_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date versionDate;
    private BigInteger version;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "send_to_oos")
    private Boolean sendToOos;
    @Column(name = "change_reason", length = 2000)
    private String changeReason;
    @Column(name = "change_user_id")
    private BigInteger changeUserId;
    @Column(name = "is_nonelectronic")
    private Boolean isNonelectronic;
    @Column(name = "examination_place", length = 2147483647)
    private String examinationPlace;
    @Column(name = "summingup_place", length = 2147483647)
    private String summingupPlace;
    @Column(name = "snapshot_id")
    private BigInteger snapshotId;
    @Column(name = "show_on_lotonline")
    private Boolean showOnLotonline;
    @Column(name = "envelopes_open_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date envelopesOpenDate;
    @Column(name = "envelopes_open_place", length = 2000)
    private String envelopesOpenPlace;
    @Column(name = "envelopes_sign_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date envelopesSignDate;
    @Column(name = "envelopes_document_list_fk")
    private BigInteger envelopesDocumentListFk;
    @Column(name = "is_multiple_offers")
    private Boolean isMultipleOffers;
    @Column(name = "lot_process_offers_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotProcessOffersDate;
    @Column(name = "lot_bidding_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotBiddingStart;
    @Column(name = "lot_bidding_stop")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotBiddingStop;
    @Column(name = "lot_result_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotResultDate;
    @Column(name = "cancel_reason_fk")
    private BigInteger cancelReasonFk;
    @Column(name = "auction_foundation")
    private String auctionFoundation;
    @Column(name = "catalog_num")
    private String catalogNum;
    @Column(name = "obj_id")
    private BigInteger objId;
    //    @Column(name = "is_privatization")
//    private Boolean privatization178;
    @Column(name = "is_end_no_appl_tender")
    private Boolean endNoApplTender;
    @JoinColumn(name = "document_list_fk", referencedColumnName = "id")
    @ManyToOne
    private Documentlist documentListFk;
    @OneToMany(mappedBy = "tenderFk")
    private Collection<LotInfo> lotInfoCollection;

    @Column(name = "is_buy_at_start_price", columnDefinition = "boolean default false")
    private Boolean isBuyAtStartPrice = false;
    @Column(name = "is_reverse_after_first_bid", columnDefinition = "boolean default false")
    private Boolean reverseAfterFirstBid = false;

    @Column(name = "is_object_culture", columnDefinition = "boolean default false")
    private boolean isObjectCulture = false;

    public Tender() {
    }

    public Tender(Long id) {
        this.id = id;
    }

    public Tender(Long id, long userId, long profileId, long saleTypeFk, boolean isConsecutiveBidding, boolean emailSent, boolean isPublished, boolean isDeferedPublication, boolean isProcessAppEmailSent, boolean isApplDocsForTender, boolean isOpenConsecutiveBidding, boolean docProvisionPayment, boolean isAdditionalDocRequirements) {
        this.id = id;
        this.userId = userId;
        this.profileId = profileId;
        this.saleTypeFk = saleTypeFk;
        this.isConsecutiveBidding = isConsecutiveBidding;
        this.emailSent = emailSent;
        this.isPublished = isPublished;
        this.isDeferedPublication = isDeferedPublication;
        this.isProcessAppEmailSent = isProcessAppEmailSent;
        this.isApplDocsForTender = isApplDocsForTender;
        this.isOpenConsecutiveBidding = isOpenConsecutiveBidding;
        this.docProvisionPayment = docProvisionPayment;
        this.isAdditionalDocRequirements = isAdditionalDocRequirements;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public long getSaleTypeFk() {
        return saleTypeFk;
    }

    public void setSaleTypeFk(long saleTypeFk) {
        this.saleTypeFk = saleTypeFk;
    }

    public String getTenderType() {
        return tenderType;
    }

    public void setTenderType(String tenderType) {
        this.tenderType = tenderType;
    }

    public Boolean getIsAbsenteeOffer() {
        return isAbsenteeOffer;
    }

    public void setIsAbsenteeOffer(Boolean isAbsenteeOffer) {
        this.isAbsenteeOffer = isAbsenteeOffer;
    }

    public Boolean getIsSoftFinish() {
        return isSoftFinish;
    }

    public void setIsSoftFinish(Boolean isSoftFinish) {
        this.isSoftFinish = isSoftFinish;
    }

    public Boolean getIsAbsenteeBid() {
        return isAbsenteeBid;
    }

    public void setIsAbsenteeBid(Boolean isAbsenteeBid) {
        this.isAbsenteeBid = isAbsenteeBid;
    }

    public Boolean getIsOpenOffers() {
        return isOpenOffers;
    }

    public void setIsOpenOffers(Boolean isOpenOffers) {
        this.isOpenOffers = isOpenOffers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInterName() {
        return interName;
    }

    public void setInterName(String interName) {
        this.interName = interName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInterDescription() {
        return interDescription;
    }

    public void setInterDescription(String interDescription) {
        this.interDescription = interDescription;
    }

    public String getRequiredDocuments() {
        return requiredDocuments;
    }

    public void setRequiredDocuments(String requiredDocuments) {
        this.requiredDocuments = requiredDocuments;
    }

    public String getInterRequiredDocuments() {
        return interRequiredDocuments;
    }

    public void setInterRequiredDocuments(String interRequiredDocuments) {
        this.interRequiredDocuments = interRequiredDocuments;
    }

    public Boolean getIsHiggling() {
        return isHiggling;
    }

    public void setIsHiggling(Boolean isHiggling) {
        this.isHiggling = isHiggling;
    }

    public Date getAppSubmitStart() {
        return appSubmitStart;
    }

    public void setAppSubmitStart(Date appSubmitStart) {
        this.appSubmitStart = appSubmitStart;
    }

    public Date getAppSubmitStop() {
        return appSubmitStop;
    }

    public void setAppSubmitStop(Date appSubmitStop) {
        this.appSubmitStop = appSubmitStop;
    }

    public String getPreQualification() {
        return preQualification;
    }

    public void setPreQualification(String preQualification) {
        this.preQualification = preQualification;
    }

    public boolean getIsConsecutiveBidding() {
        return isConsecutiveBidding;
    }

    public void setIsConsecutiveBidding(boolean isConsecutiveBidding) {
        this.isConsecutiveBidding = isConsecutiveBidding;
    }

    public Boolean getIsDigitalSignatureRequired() {
        return isDigitalSignatureRequired;
    }

    public void setIsDigitalSignatureRequired(Boolean isDigitalSignatureRequired) {
        this.isDigitalSignatureRequired = isDigitalSignatureRequired;
    }

    public boolean getEmailSent() {
        return emailSent;
    }

    public void setEmailSent(boolean emailSent) {
        this.emailSent = emailSent;
    }

    public String getTenderCode() {
        return tenderCode;
    }

    public void setTenderCode(String tenderCode) {
        this.tenderCode = tenderCode;
    }

    public String getTenderStatus() {
        return tenderStatus;
    }

    public void setTenderStatus(String tenderStatus) {
        this.tenderStatus = tenderStatus;
    }

    public Integer getFinishInterval() {
        return finishInterval;
    }

    public void setFinishInterval(Integer finishInterval) {
        this.finishInterval = finishInterval;
    }

    public Date getPublishFrom() {
        return publishFrom;
    }

    public void setPublishFrom(Date publishFrom) {
        this.publishFrom = publishFrom;
    }

    public boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    public boolean getIsDeferedPublication() {
        return isDeferedPublication;
    }

    public void setIsDeferedPublication(boolean isDeferedPublication) {
        this.isDeferedPublication = isDeferedPublication;
    }

    public Date getProcessAppDate() {
        return processAppDate;
    }

    public void setProcessAppDate(Date processAppDate) {
        this.processAppDate = processAppDate;
    }

    public boolean getIsProcessAppEmailSent() {
        return isProcessAppEmailSent;
    }

    public void setIsProcessAppEmailSent(boolean isProcessAppEmailSent) {
        this.isProcessAppEmailSent = isProcessAppEmailSent;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Boolean getIsAppsProcessed() {
        return isAppsProcessed;
    }

    public void setIsAppsProcessed(Boolean isAppsProcessed) {
        this.isAppsProcessed = isAppsProcessed;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String[][] getApplicationDocuments() {
        return applicationDocuments;
    }

    public void setApplicationDocuments(String[][] applicationDocuments) {
        this.applicationDocuments = applicationDocuments;
    }

    public boolean getIsApplDocsForTender() {
        return isApplDocsForTender;
    }

    public void setIsApplDocsForTender(boolean isApplDocsForTender) {
        this.isApplDocsForTender = isApplDocsForTender;
    }

    public boolean getIsOpenConsecutiveBidding() {
        return isOpenConsecutiveBidding;
    }

    public void setIsOpenConsecutiveBidding(boolean isOpenConsecutiveBidding) {
        this.isOpenConsecutiveBidding = isOpenConsecutiveBidding;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public BigInteger getActiveConsecutiveLotId() {
        return activeConsecutiveLotId;
    }

    public void setActiveConsecutiveLotId(BigInteger activeConsecutiveLotId) {
        this.activeConsecutiveLotId = activeConsecutiveLotId;
    }

    public BigInteger getHistoryProfileId() {
        return historyProfileId;
    }

    public void setHistoryProfileId(BigInteger historyProfileId) {
        this.historyProfileId = historyProfileId;
    }

    public Date getEndProcessAppDate() {
        return endProcessAppDate;
    }

    public void setEndProcessAppDate(Date endProcessAppDate) {
        this.endProcessAppDate = endProcessAppDate;
    }

    public Boolean getIsOnlyManual() {
        return isOnlyManual;
    }

    public void setIsOnlyManual(Boolean isOnlyManual) {
        this.isOnlyManual = isOnlyManual;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDocProvisionStart() {
        return docProvisionStart;
    }

    public void setDocProvisionStart(Date docProvisionStart) {
        this.docProvisionStart = docProvisionStart;
    }

    public Date getDocProvisionEnd() {
        return docProvisionEnd;
    }

    public void setDocProvisionEnd(Date docProvisionEnd) {
        this.docProvisionEnd = docProvisionEnd;
    }

    public String getDocProvisionPlace() {
        return docProvisionPlace;
    }

    public void setDocProvisionPlace(String docProvisionPlace) {
        this.docProvisionPlace = docProvisionPlace;
    }

    public String getDocProvisionRules() {
        return docProvisionRules;
    }

    public void setDocProvisionRules(String docProvisionRules) {
        this.docProvisionRules = docProvisionRules;
    }

    public boolean getDocProvisionPayment() {
        return docProvisionPayment;
    }

    public void setDocProvisionPayment(boolean docProvisionPayment) {
        this.docProvisionPayment = docProvisionPayment;
    }

    public BigDecimal getDocProvisionPrice() {
        return docProvisionPrice;
    }

    public void setDocProvisionPrice(BigDecimal docProvisionPrice) {
        this.docProvisionPrice = docProvisionPrice;
    }

    public Integer getDocProvisionCurrencyKeyFk() {
        return docProvisionCurrencyKeyFk;
    }

    public void setDocProvisionCurrencyKeyFk(Integer docProvisionCurrencyKeyFk) {
        this.docProvisionCurrencyKeyFk = docProvisionCurrencyKeyFk;
    }

    public String getDocProvisionPaymentRules() {
        return docProvisionPaymentRules;
    }

    public void setDocProvisionPaymentRules(String docProvisionPaymentRules) {
        this.docProvisionPaymentRules = docProvisionPaymentRules;
    }

    public boolean getIsAdditionalDocRequirements() {
        return isAdditionalDocRequirements;
    }

    public void setIsAdditionalDocRequirements(boolean isAdditionalDocRequirements) {
        this.isAdditionalDocRequirements = isAdditionalDocRequirements;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getNoticeOosUrl() {
        return noticeOosUrl;
    }

    public void setNoticeOosUrl(String noticeOosUrl) {
        this.noticeOosUrl = noticeOosUrl;
    }

    public String getAcceptProtocolOosUrl() {
        return acceptProtocolOosUrl;
    }

    public void setAcceptProtocolOosUrl(String acceptProtocolOosUrl) {
        this.acceptProtocolOosUrl = acceptProtocolOosUrl;
    }

    public String getWinnerProtocolOosUrl() {
        return winnerProtocolOosUrl;
    }

    public void setWinnerProtocolOosUrl(String winnerProtocolOosUrl) {
        this.winnerProtocolOosUrl = winnerProtocolOosUrl;
    }

    public Boolean getNewWizard() {
        return newWizard;
    }

    public void setNewWizard(Boolean newWizard) {
        this.newWizard = newWizard;
    }

    public BigInteger getRootId() {
        return rootId;
    }

    public void setRootId(BigInteger rootId) {
        this.rootId = rootId;
    }

    public Date getVersionDate() {
        return versionDate;
    }

    public void setVersionDate(Date versionDate) {
        this.versionDate = versionDate;
    }

    public BigInteger getVersion() {
        return version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getSendToOos() {
        return sendToOos;
    }

    public void setSendToOos(Boolean sendToOos) {
        this.sendToOos = sendToOos;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public BigInteger getChangeUserId() {
        return changeUserId;
    }

    public void setChangeUserId(BigInteger changeUserId) {
        this.changeUserId = changeUserId;
    }

    public Boolean getIsNonelectronic() {
        return isNonelectronic;
    }

    public void setIsNonelectronic(Boolean isNonelectronic) {
        this.isNonelectronic = isNonelectronic;
    }

    public String getExaminationPlace() {
        return examinationPlace;
    }

    public void setExaminationPlace(String examinationPlace) {
        this.examinationPlace = examinationPlace;
    }

    public String getSummingupPlace() {
        return summingupPlace;
    }

    public void setSummingupPlace(String summingupPlace) {
        this.summingupPlace = summingupPlace;
    }

    public BigInteger getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(BigInteger snapshotId) {
        this.snapshotId = snapshotId;
    }

    public Boolean getShowOnLotonline() {
        return showOnLotonline;
    }

    public void setShowOnLotonline(Boolean showOnLotonline) {
        this.showOnLotonline = showOnLotonline;
    }

    public Date getEnvelopesOpenDate() {
        return envelopesOpenDate;
    }

    public void setEnvelopesOpenDate(Date envelopesOpenDate) {
        this.envelopesOpenDate = envelopesOpenDate;
    }

    public String getEnvelopesOpenPlace() {
        return envelopesOpenPlace;
    }

    public void setEnvelopesOpenPlace(String envelopesOpenPlace) {
        this.envelopesOpenPlace = envelopesOpenPlace;
    }

    public Date getEnvelopesSignDate() {
        return envelopesSignDate;
    }

    public void setEnvelopesSignDate(Date envelopesSignDate) {
        this.envelopesSignDate = envelopesSignDate;
    }

    public BigInteger getEnvelopesDocumentListFk() {
        return envelopesDocumentListFk;
    }

    public void setEnvelopesDocumentListFk(BigInteger envelopesDocumentListFk) {
        this.envelopesDocumentListFk = envelopesDocumentListFk;
    }

    public Boolean getIsMultipleOffers() {
        return isMultipleOffers;
    }

    public void setIsMultipleOffers(Boolean isMultipleOffers) {
        this.isMultipleOffers = isMultipleOffers;
    }

    public Date getLotProcessOffersDate() {
        return lotProcessOffersDate;
    }

    public void setLotProcessOffersDate(Date lotProcessOffersDate) {
        this.lotProcessOffersDate = lotProcessOffersDate;
    }

    public Date getLotBiddingStart() {
        return lotBiddingStart;
    }

    public void setLotBiddingStart(Date lotBiddingStart) {
        this.lotBiddingStart = lotBiddingStart;
    }

    public Date getLotBiddingStop() {
        return lotBiddingStop;
    }

    public void setLotBiddingStop(Date lotBiddingStop) {
        this.lotBiddingStop = lotBiddingStop;
    }

    public Date getLotResultDate() {
        return lotResultDate;
    }

    public void setLotResultDate(Date lotResultDate) {
        this.lotResultDate = lotResultDate;
    }

    public BigInteger getCancelReasonFk() {
        return cancelReasonFk;
    }

    public void setCancelReasonFk(BigInteger cancelReasonFk) {
        this.cancelReasonFk = cancelReasonFk;
    }

    public Documentlist getDocumentListFk() {
        return documentListFk;
    }

    public void setDocumentListFk(Documentlist documentListFk) {
        this.documentListFk = documentListFk;
    }

    public String getAuctionFoundation() {
        return auctionFoundation;
    }

    public void setAuctionFoundation(String auctionFoundation) {
        this.auctionFoundation = auctionFoundation;
    }

    public String getCatalogNum() {
        return catalogNum;
    }

    public void setCatalogNum(String catalogNum) {
        this.catalogNum = catalogNum;
    }

    public BigInteger getObjId() {
        return objId;
    }

    public void setObjId(BigInteger objId) {
        this.objId = objId;
    }

//    public Boolean getPrivatization178() {
//        return privatization178;
//    }
//
//    public void setPrivatization178(Boolean privatization178) {
//        this.privatization178 = privatization178;
//    }

    public boolean isEndNoApplTender() {
        return endNoApplTender;
    }

    public void setEndNoApplTender(Boolean endNoApplTender) {
        this.endNoApplTender = endNoApplTender;
    }

    public boolean isBuyAtStartPrice() {
        return isBuyAtStartPrice;
    }

    public void setBuyAtStartPrice(Boolean buyAtStartPrice) {
        isBuyAtStartPrice = buyAtStartPrice == null ? false : buyAtStartPrice;
    }

    public boolean getReverseAfterFirstBid() {
        return reverseAfterFirstBid;
    }

    public void setReverseAfterFirstBid(Boolean reverseAfterFirstBid) {
        this.reverseAfterFirstBid = reverseAfterFirstBid == null ? false : reverseAfterFirstBid;
    }

    @XmlTransient
    public Collection<LotInfo> getLotInfoCollection() {
        return lotInfoCollection;
    }

    public void setLotInfoCollection(Collection<LotInfo> lotInfoCollection) {
        this.lotInfoCollection = lotInfoCollection;
    }

    public boolean isObjectCulture() {
        return isObjectCulture;
    }

    public void setObjectCulture(boolean objectCulture) {
        isObjectCulture = objectCulture;
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
        if (!(object instanceof Tender)) {
            return false;
        }
        Tender other = (Tender) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.lotonline.model.Tender[ id=" + id + " ]";
    }

}
