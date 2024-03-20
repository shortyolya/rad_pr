package com.baltinfo.radius.db.model.lotonline;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author css
 */
@Entity
@TypeDef(name = "TwoDimensionalStringArrayType", typeClass = TwoDimensionalStringArrayType.class)
@Table(name = "lot_info", catalog = "", schema = "")
@XmlRootElement
public class LotInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "seq_lot_info_id")
    @GenericGenerator(name = "seq_lot_info_id",
            strategy = "com.baltinfo.radius.db.model.lotonline.LotOnlineShard3SequnceGenerator",
            parameters = @org.hibernate.annotations.Parameter(name = "sequence", value = "seq_lot_info_id"))
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Column(name = "lot_tender_order")
    private Short lotTenderOrder;
    @Column(name = "tender_type", length = 50)
    private String tenderType;
    @Column(length = 2147483647)
    private String description;
    @Column(name = "inter_description", length = 2147483647)
    private String interDescription;
    @Column(length = 2147483647)
    private String name;
    @Column(name = "inter_name", length = 2147483647)
    private String interName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price_amt", precision = 19, scale = 4)
    private BigDecimal priceAmt;
    @Column(name = "deposit_amt", precision = 19, scale = 4)
    private BigDecimal depositAmt;
    @Column(name = "min_amt", precision = 19, scale = 4)
    private BigDecimal minAmt;
    @Column(name = "bidding_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date biddingStart;
    @Column(name = "bidding_stop")
    @Temporal(TemporalType.TIMESTAMP)
    private Date biddingStop;
    @Column(name = "start_amt", precision = 19, scale = 4)
    private BigDecimal startAmt;
    @Column(name = "step_down_amt", precision = 19, scale = 4)
    private BigDecimal stepDownAmt;
    @Column(name = "step_up_amt", precision = 19, scale = 4)
    private BigDecimal stepUpAmt;
    @Column(name = "tender_status", length = 50)
    private String tenderStatus;
    @Column(name = "extension_time")
    private BigInteger extensionTime;
    @Column(name = "time_left_for_extension")
    private BigInteger timeLeftForExtension;
    @Basic(optional = false)
    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "photo_files", length = 300)
    private String photoFiles;
    @Column(name = "lot_code", length = 50)
    private String lotCode;
    @Column(name = "app_submit_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appSubmitStart;
    @Column(name = "app_submit_stop")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appSubmitStop;
    @Column(name = "deposit_reciept_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date depositRecieptStart;
    @Column(name = "deposit_reciept_stop")
    @Temporal(TemporalType.TIMESTAMP)
    private Date depositRecieptStop;
    @Column(name = "show_on_site")
    private Boolean showOnSite;
    @Column(name = "public_access")
    private Boolean publicAccess;
    @Column(name = "online_app_reciept")
    private Boolean onlineAppReciept;
    @Column(name = "result_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resultDate;
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @Column(name = "evaluation_criterion", length = 2147483647)
    private String evaluationCriterion;
    @Column(name = "payment_conditions", length = 2147483647)
    private String paymentConditions;
    @Column(name = "delivery_place", length = 2147483647)
    private String deliveryPlace;
    @Column(name = "delivery_time_req", length = 2147483647)
    private String deliveryTimeReq;
    @Column(length = 2147483647)
    private String quantity;
    @Column(name = "application_deposit", length = 2147483647)
    private String applicationDeposit;
    @Column(name = "required_documents", length = 2147483647)
    private String requiredDocuments;
    @Column(name = "inter_evaluation_criterion", length = 2147483647)
    private String interEvaluationCriterion;
    @Column(name = "inter_payment_conditions", length = 2147483647)
    private String interPaymentConditions;
    @Column(name = "inter_delivery_place", length = 2147483647)
    private String interDeliveryPlace;
    @Column(name = "inter_delivery_time_req", length = 2147483647)
    private String interDeliveryTimeReq;
    @Column(name = "inter_quantity", length = 2147483647)
    private String interQuantity;
    @Column(name = "inter_application_deposit", length = 2147483647)
    private String interApplicationDeposit;
    @Column(name = "inter_required_documents", length = 2147483647)
    private String interRequiredDocuments;
    @Column(name = "fixed_step")
    private Boolean fixedStep;
    @Column(name = "user_winner_id")
    private Long userWinnerId;
    @Column(name = "short_description", length = 2147483647)
    private String shortDescription;
    @Column(name = "inter_short_description", length = 2147483647)
    private String interShortDescription;
    @Column(name = "auto_bid_period")
    private BigInteger autoBidPeriod;
    @Basic(optional = false)
    @Column(name = "email_sent", nullable = false)
    private boolean emailSent;
    @Column(name = "winner_offer_id")
    private BigInteger winnerOfferId;
    @Column(name = "winner_price", precision = 19, scale = 4)
    private BigDecimal winnerPrice;
    @Basic(optional = false)
    @Column(name = "is_published", nullable = false)
    private boolean isPublished;
    @Column(name = "process_app_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date processAppDate;
    @Column(name = "is_apps_processed")
    private Boolean isAppsProcessed;
    @Column(name = "bidding_result", length = 50)
    private String biddingResult;
    @Column(name = "application_documents", columnDefinition = "TwoDimensionalStringArrayType")
    @Type(type = "TwoDimensionalStringArrayType")
    private String[][] applicationDocuments;
    @Column(name = "lot_number")
    private Integer lotNumber;
    @Basic(optional = false)
    @Column(name = "winner_id_updated", nullable = false)
    private boolean winnerIdUpdated;
    @Column(name = "start_price_bid_period")
    private BigInteger startPriceBidPeriod;
    @Column(name = "after_direction_flip_bid_period")
    private BigInteger afterDirectionFlipBidPeriod;
    @Column(name = "country_fk")
    private BigInteger countryFk;
    @Column(name = "fias_region_guid", length = 36)
    private String fiasRegionGuid;
    @Column(name = "end_process_app_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endProcessAppDate;
    @Column(name = "step_percent", precision = 4, scale = 2)
    private BigDecimal stepPercent;
    @Column(name = "actual_publish_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualPublishDate;
    @Column(name = "start_amt_description", length = 2147483647)
    private String startAmtDescription;
    @Basic(optional = false)
    @Column(name = "hasnt_start_amt", nullable = false)
    private boolean hasntStartAmt;
    @Column(name = "didnt_take_place_reason", length = 2147483647)
    private String didntTakePlaceReason;
    @Column(length = 63)
    private String guid;
    @Column(name = "root_id")
    private BigInteger rootId;
    @Column(name = "version_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date versionDate;
    private BigInteger version;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "deposit_type", length = 20)
    private String depositType;
    @Column(name = "deposit_info", length = 2147483647)
    private String depositInfo;
    @Column(name = "bidding_result_doclist_fk")
    private BigInteger biddingResultDoclistFk;
    private Integer term;
    @Column(length = 2147483647)
    private String okato;
    @Column(name = "start_price_type", length = 20)
    private String startPriceType;
    @Column(name = "price_amt_nds", precision = 19, scale = 4)
    private BigDecimal priceAmtNds;
    @Column(name = "nds_rate", precision = 19, scale = 4)
    private BigDecimal ndsRate;
    @Column(name = "contract_decision", length = 2147483647)
    private String contractDecision;
    @Column(name = "frustrated_reason", length = 2147483647)
    private String frustratedReason;
    @Column(name = "allow_alt_deposit")
    private Boolean allowAltDeposit;
    @Column(name = "alt_deposit_info", length = 2147483647)
    private String altDepositInfo;
    @Basic(optional = false)
    @Column(name = "offer_step_type", nullable = false, length = 50)
    private String offerStepType;
    @Column(name = "purchase_plan_guid", length = 50)
    private String purchasePlanGuid;
    @Column(name = "purchase_plan_position_guid", length = 50)
    private String purchasePlanPositionGuid;
    @Column(name = "subcontractors_requirement")
    private Boolean subcontractorsRequirement;
    @Column(name = "process_offers_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date processOffersDate;
    @Column(name = "region_fk")
    private BigInteger regionFk;
    @Basic(optional = false)
    @Column(name = "currency_key_fk", nullable = false)
    private int currencyKeyFk;
    @Column(name = "category_fk")
    private Long categoryFk;
    @Column(name = "cancel_reason_fk")
    private Long cancelReasonFk;
    @JoinColumn(name = "tender_fk", referencedColumnName = "id")
    @ManyToOne
    private Tender tenderFk;
    @JoinColumn(name = "document_list_fk", referencedColumnName = "id")
    @ManyToOne
    private Documentlist documentListFk;
    @Column(name = "sales_manager_name", length = 255)
    private String salesManagerName;
    @Column(name = "sales_manager_phone", length = 255)
    private String salesManagerPhone;
    @Column(name = "sales_manager_email", length = 50)
    private String salesManagerEmail;

    @Column(name = "add_property_json")
    private String addPropertyJson;

    @Column(name = "account_details_id")
    private Long accountDetailsId;

    public LotInfo() {
    }

    public LotInfo(Long id) {
        this.id = id;
    }

    public LotInfo(Long id, Date creationDate, boolean emailSent, boolean isPublished, boolean winnerIdUpdated, boolean hasntStartAmt, String offerStepType, int currencyKeyFk) {
        this.id = id;
        this.creationDate = creationDate;
        this.emailSent = emailSent;
        this.isPublished = isPublished;
        this.winnerIdUpdated = winnerIdUpdated;
        this.hasntStartAmt = hasntStartAmt;
        this.offerStepType = offerStepType;
        this.currencyKeyFk = currencyKeyFk;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getLotTenderOrder() {
        return lotTenderOrder;
    }

    public void setLotTenderOrder(Short lotTenderOrder) {
        this.lotTenderOrder = lotTenderOrder;
    }

    public String getTenderType() {
        return tenderType;
    }

    public void setTenderType(String tenderType) {
        this.tenderType = tenderType;
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

    public BigDecimal getPriceAmt() {
        return priceAmt;
    }

    public void setPriceAmt(BigDecimal priceAmt) {
        this.priceAmt = priceAmt;
    }

    public BigDecimal getDepositAmt() {
        return depositAmt;
    }

    public void setDepositAmt(BigDecimal depositAmt) {
        this.depositAmt = depositAmt;
    }

    public BigDecimal getMinAmt() {
        return minAmt;
    }

    public void setMinAmt(BigDecimal minAmt) {
        this.minAmt = minAmt;
    }

    public Date getBiddingStart() {
        return biddingStart;
    }

    public void setBiddingStart(Date biddingStart) {
        this.biddingStart = biddingStart;
    }

    public Date getBiddingStop() {
        return biddingStop;
    }

    public void setBiddingStop(Date biddingStop) {
        this.biddingStop = biddingStop;
    }

    public BigDecimal getStartAmt() {
        return startAmt;
    }

    public void setStartAmt(BigDecimal startAmt) {
        this.startAmt = startAmt;
    }

    public BigDecimal getStepDownAmt() {
        return stepDownAmt;
    }

    public void setStepDownAmt(BigDecimal stepDownAmt) {
        this.stepDownAmt = stepDownAmt;
    }

    public BigDecimal getStepUpAmt() {
        return stepUpAmt;
    }

    public void setStepUpAmt(BigDecimal stepUpAmt) {
        this.stepUpAmt = stepUpAmt;
    }

    public String getTenderStatus() {
        return tenderStatus;
    }

    public void setTenderStatus(String tenderStatus) {
        this.tenderStatus = tenderStatus;
    }

    public BigInteger getExtensionTime() {
        return extensionTime;
    }

    public void setExtensionTime(BigInteger extensionTime) {
        this.extensionTime = extensionTime;
    }

    public BigInteger getTimeLeftForExtension() {
        return timeLeftForExtension;
    }

    public void setTimeLeftForExtension(BigInteger timeLeftForExtension) {
        this.timeLeftForExtension = timeLeftForExtension;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getPhotoFiles() {
        return photoFiles;
    }

    public void setPhotoFiles(String photoFiles) {
        this.photoFiles = photoFiles;
    }

    public String getLotCode() {
        return lotCode;
    }

    public void setLotCode(String lotCode) {
        this.lotCode = lotCode;
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

    public Date getDepositRecieptStart() {
        return depositRecieptStart;
    }

    public void setDepositRecieptStart(Date depositRecieptStart) {
        this.depositRecieptStart = depositRecieptStart;
    }

    public Date getDepositRecieptStop() {
        return depositRecieptStop;
    }

    public void setDepositRecieptStop(Date depositRecieptStop) {
        this.depositRecieptStop = depositRecieptStop;
    }

    public Boolean getShowOnSite() {
        return showOnSite;
    }

    public void setShowOnSite(Boolean showOnSite) {
        this.showOnSite = showOnSite;
    }

    public Boolean getPublicAccess() {
        return publicAccess;
    }

    public void setPublicAccess(Boolean publicAccess) {
        this.publicAccess = publicAccess;
    }

    public Boolean getOnlineAppReciept() {
        return onlineAppReciept;
    }

    public void setOnlineAppReciept(Boolean onlineAppReciept) {
        this.onlineAppReciept = onlineAppReciept;
    }

    public Date getResultDate() {
        return resultDate;
    }

    public void setResultDate(Date resultDate) {
        this.resultDate = resultDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getEvaluationCriterion() {
        return evaluationCriterion;
    }

    public void setEvaluationCriterion(String evaluationCriterion) {
        this.evaluationCriterion = evaluationCriterion;
    }

    public String getPaymentConditions() {
        return paymentConditions;
    }

    public void setPaymentConditions(String paymentConditions) {
        this.paymentConditions = paymentConditions;
    }

    public String getDeliveryPlace() {
        return deliveryPlace;
    }

    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    public String getDeliveryTimeReq() {
        return deliveryTimeReq;
    }

    public void setDeliveryTimeReq(String deliveryTimeReq) {
        this.deliveryTimeReq = deliveryTimeReq;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getApplicationDeposit() {
        return applicationDeposit;
    }

    public void setApplicationDeposit(String applicationDeposit) {
        this.applicationDeposit = applicationDeposit;
    }

    public String getRequiredDocuments() {
        return requiredDocuments;
    }

    public void setRequiredDocuments(String requiredDocuments) {
        this.requiredDocuments = requiredDocuments;
    }

    public String getInterEvaluationCriterion() {
        return interEvaluationCriterion;
    }

    public void setInterEvaluationCriterion(String interEvaluationCriterion) {
        this.interEvaluationCriterion = interEvaluationCriterion;
    }

    public String getInterPaymentConditions() {
        return interPaymentConditions;
    }

    public void setInterPaymentConditions(String interPaymentConditions) {
        this.interPaymentConditions = interPaymentConditions;
    }

    public String getInterDeliveryPlace() {
        return interDeliveryPlace;
    }

    public void setInterDeliveryPlace(String interDeliveryPlace) {
        this.interDeliveryPlace = interDeliveryPlace;
    }

    public String getInterDeliveryTimeReq() {
        return interDeliveryTimeReq;
    }

    public void setInterDeliveryTimeReq(String interDeliveryTimeReq) {
        this.interDeliveryTimeReq = interDeliveryTimeReq;
    }

    public String getInterQuantity() {
        return interQuantity;
    }

    public void setInterQuantity(String interQuantity) {
        this.interQuantity = interQuantity;
    }

    public String getInterApplicationDeposit() {
        return interApplicationDeposit;
    }

    public void setInterApplicationDeposit(String interApplicationDeposit) {
        this.interApplicationDeposit = interApplicationDeposit;
    }

    public String getInterRequiredDocuments() {
        return interRequiredDocuments;
    }

    public void setInterRequiredDocuments(String interRequiredDocuments) {
        this.interRequiredDocuments = interRequiredDocuments;
    }

    public Boolean getFixedStep() {
        return fixedStep;
    }

    public void setFixedStep(Boolean fixedStep) {
        this.fixedStep = fixedStep;
    }

    public Long getUserWinnerId() {
        return userWinnerId;
    }

    public void setUserWinnerId(Long userWinnerId) {
        this.userWinnerId = userWinnerId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getInterShortDescription() {
        return interShortDescription;
    }

    public void setInterShortDescription(String interShortDescription) {
        this.interShortDescription = interShortDescription;
    }

    public BigInteger getAutoBidPeriod() {
        return autoBidPeriod;
    }

    public void setAutoBidPeriod(BigInteger autoBidPeriod) {
        this.autoBidPeriod = autoBidPeriod;
    }

    public boolean getEmailSent() {
        return emailSent;
    }

    public void setEmailSent(boolean emailSent) {
        this.emailSent = emailSent;
    }

    public BigInteger getWinnerOfferId() {
        return winnerOfferId;
    }

    public void setWinnerOfferId(BigInteger winnerOfferId) {
        this.winnerOfferId = winnerOfferId;
    }

    public BigDecimal getWinnerPrice() {
        return winnerPrice;
    }

    public void setWinnerPrice(BigDecimal winnerPrice) {
        this.winnerPrice = winnerPrice;
    }

    public boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    public Date getProcessAppDate() {
        return processAppDate;
    }

    public void setProcessAppDate(Date processAppDate) {
        this.processAppDate = processAppDate;
    }

    public Boolean getIsAppsProcessed() {
        return isAppsProcessed;
    }

    public void setIsAppsProcessed(Boolean isAppsProcessed) {
        this.isAppsProcessed = isAppsProcessed;
    }

    public String getBiddingResult() {
        return biddingResult;
    }

    public void setBiddingResult(String biddingResult) {
        this.biddingResult = biddingResult;
    }

    public String[][] getApplicationDocuments() {
        return applicationDocuments;
    }

    public void setApplicationDocuments(String[][] applicationDocuments) {
        this.applicationDocuments = applicationDocuments;
    }

    public Integer getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(Integer lotNumber) {
        this.lotNumber = lotNumber;
    }

    public boolean getWinnerIdUpdated() {
        return winnerIdUpdated;
    }

    public void setWinnerIdUpdated(boolean winnerIdUpdated) {
        this.winnerIdUpdated = winnerIdUpdated;
    }

    public BigInteger getStartPriceBidPeriod() {
        return startPriceBidPeriod;
    }

    public void setStartPriceBidPeriod(BigInteger startPriceBidPeriod) {
        this.startPriceBidPeriod = startPriceBidPeriod;
    }

    public BigInteger getAfterDirectionFlipBidPeriod() {
        return afterDirectionFlipBidPeriod;
    }

    public void setAfterDirectionFlipBidPeriod(BigInteger afterDirectionFlipBidPeriod) {
        this.afterDirectionFlipBidPeriod = afterDirectionFlipBidPeriod;
    }

    public BigInteger getCountryFk() {
        return countryFk;
    }

    public void setCountryFk(BigInteger countryFk) {
        this.countryFk = countryFk;
    }

    public String getFiasRegionGuid() {
        return fiasRegionGuid;
    }

    public void setFiasRegionGuid(String fiasRegionGuid) {
        this.fiasRegionGuid = fiasRegionGuid;
    }

    public Date getEndProcessAppDate() {
        return endProcessAppDate;
    }

    public void setEndProcessAppDate(Date endProcessAppDate) {
        this.endProcessAppDate = endProcessAppDate;
    }

    public BigDecimal getStepPercent() {
        return stepPercent;
    }

    public void setStepPercent(BigDecimal stepPercent) {
        this.stepPercent = stepPercent;
    }

    public Date getActualPublishDate() {
        return actualPublishDate;
    }

    public void setActualPublishDate(Date actualPublishDate) {
        this.actualPublishDate = actualPublishDate;
    }

    public String getStartAmtDescription() {
        return startAmtDescription;
    }

    public void setStartAmtDescription(String startAmtDescription) {
        this.startAmtDescription = startAmtDescription;
    }

    public boolean getHasntStartAmt() {
        return hasntStartAmt;
    }

    public void setHasntStartAmt(boolean hasntStartAmt) {
        this.hasntStartAmt = hasntStartAmt;
    }

    public String getDidntTakePlaceReason() {
        return didntTakePlaceReason;
    }

    public void setDidntTakePlaceReason(String didntTakePlaceReason) {
        this.didntTakePlaceReason = didntTakePlaceReason;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }

    public String getDepositInfo() {
        return depositInfo;
    }

    public void setDepositInfo(String depositInfo) {
        this.depositInfo = depositInfo;
    }

    public BigInteger getBiddingResultDoclistFk() {
        return biddingResultDoclistFk;
    }

    public void setBiddingResultDoclistFk(BigInteger biddingResultDoclistFk) {
        this.biddingResultDoclistFk = biddingResultDoclistFk;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getOkato() {
        return okato;
    }

    public void setOkato(String okato) {
        this.okato = okato;
    }

    public String getStartPriceType() {
        return startPriceType;
    }

    public void setStartPriceType(String startPriceType) {
        this.startPriceType = startPriceType;
    }

    public BigDecimal getPriceAmtNds() {
        return priceAmtNds;
    }

    public void setPriceAmtNds(BigDecimal priceAmtNds) {
        this.priceAmtNds = priceAmtNds;
    }

    public BigDecimal getNdsRate() {
        return ndsRate;
    }

    public void setNdsRate(BigDecimal ndsRate) {
        this.ndsRate = ndsRate;
    }

    public String getContractDecision() {
        return contractDecision;
    }

    public void setContractDecision(String contractDecision) {
        this.contractDecision = contractDecision;
    }

    public String getFrustratedReason() {
        return frustratedReason;
    }

    public void setFrustratedReason(String frustratedReason) {
        this.frustratedReason = frustratedReason;
    }

    public Boolean getAllowAltDeposit() {
        return allowAltDeposit;
    }

    public void setAllowAltDeposit(Boolean allowAltDeposit) {
        this.allowAltDeposit = allowAltDeposit;
    }

    public String getAltDepositInfo() {
        return altDepositInfo;
    }

    public void setAltDepositInfo(String altDepositInfo) {
        this.altDepositInfo = altDepositInfo;
    }

    public String getOfferStepType() {
        return offerStepType;
    }

    public void setOfferStepType(String offerStepType) {
        this.offerStepType = offerStepType;
    }

    public String getPurchasePlanGuid() {
        return purchasePlanGuid;
    }

    public void setPurchasePlanGuid(String purchasePlanGuid) {
        this.purchasePlanGuid = purchasePlanGuid;
    }

    public String getPurchasePlanPositionGuid() {
        return purchasePlanPositionGuid;
    }

    public void setPurchasePlanPositionGuid(String purchasePlanPositionGuid) {
        this.purchasePlanPositionGuid = purchasePlanPositionGuid;
    }

    public Boolean getSubcontractorsRequirement() {
        return subcontractorsRequirement;
    }

    public void setSubcontractorsRequirement(Boolean subcontractorsRequirement) {
        this.subcontractorsRequirement = subcontractorsRequirement;
    }

    public Date getProcessOffersDate() {
        return processOffersDate;
    }

    public void setProcessOffersDate(Date processOffersDate) {
        this.processOffersDate = processOffersDate;
    }

    public BigInteger getRegionFk() {
        return regionFk;
    }

    public void setRegionFk(BigInteger regionFk) {
        this.regionFk = regionFk;
    }

    public int getCurrencyKeyFk() {
        return currencyKeyFk;
    }

    public void setCurrencyKeyFk(int currencyKeyFk) {
        this.currencyKeyFk = currencyKeyFk;
    }

    public Long getCategoryFk() {
        return categoryFk;
    }

    public void setCategoryFk(Long categoryFk) {
        this.categoryFk = categoryFk;
    }

    public Long getCancelReasonFk() {
        return cancelReasonFk;
    }

    public void setCancelReasonFk(Long cancelReasonFk) {
        this.cancelReasonFk = cancelReasonFk;
    }

    public Tender getTenderFk() {
        return tenderFk;
    }

    public void setTenderFk(Tender tenderFk) {
        this.tenderFk = tenderFk;
    }

    public Documentlist getDocumentListFk() {
        return documentListFk;
    }

    public void setDocumentListFk(Documentlist documentListFk) {
        this.documentListFk = documentListFk;
    }

    public String getSalesManagerName() {
        return salesManagerName;
    }

    public void setSalesManagerName(String salesManagerName) {
        this.salesManagerName = salesManagerName;
    }

    public String getSalesManagerPhone() {
        return salesManagerPhone;
    }

    public void setSalesManagerPhone(String salesManagerPhone) {
        this.salesManagerPhone = salesManagerPhone;
    }

    public String getSalesManagerEmail() {
        return salesManagerEmail;
    }

    public void setSalesManagerEmail(String salesManagerEmail) {
        this.salesManagerEmail = salesManagerEmail;
    }

    public String getAddPropertyJson() {
        return addPropertyJson;
    }

    public void setAddPropertyJson(String addPropertyJson) {
        this.addPropertyJson = addPropertyJson;
    }

    public Long getAccountDetailsId() {
        return accountDetailsId;
    }

    public void setAccountDetailsId(Long accountDetailsId) {
        this.accountDetailsId = accountDetailsId;
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
        if (!(object instanceof LotInfo)) {
            return false;
        }
        LotInfo other = (LotInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.lotonline.model.LotInfo[ id=" + id + " ]";
    }

}
