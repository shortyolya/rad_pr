/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lia
 */
@Entity
@Table(name = "load_lot", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ll_unid"})})
@SequenceGenerator(name = "seq_load_lot", sequenceName = "seq_load_lot", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "LoadLot.findAll", query = "SELECT l FROM LoadLot l"),
        @NamedQuery(name = "LoadLot.findByLlUnid", query = "SELECT l FROM LoadLot l WHERE l.llUnid = :llUnid"),
        @NamedQuery(name = "LoadLot.findForTransferByLaUnid", query = "SELECT l FROM LoadLot l, LoadAuction la " +
                "WHERE l.indActual = 1 AND (l.llTransferResult is null or l.llTransferResult = 0) " +
                "AND l.laUnid = la AND la.laUnid = :laUnid order by CAST(l.llLotNum as integer)")
})
public class LoadLot implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_load_lot")
    @Column(name = "ll_unid")
    private Long llUnid;
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
    @Column(name = "ll_lot_num")
    private String llLotNum;
    @Column(name = "ll_rubric")
    private String llRubric;
    @Column(name = "ll_region")
    private String llRegion;
    @Column(name = "ll_lot_sname")
    private String llLotSname;
    @Column(name = "ll_lot_name")
    private String llLotName;
    @Column(name = "ll_review_debitor_order")
    private String llReviewDebitorOrder;
    @Column(name = "ll_start_cost")
    private BigDecimal llStartCost;
    @Column(name = "ll_deposit_sum")
    private BigDecimal llDepositSum;
    @Column(name = "ll_step_value")
    private BigDecimal llStepValue;
    @Column(name = "ll_change_price_time_h")
    private Short llChangePriceTimeH;
    @Column(name = "ll_change_price_time_m")
    private Short llChangePriceTimeM;
    @Column(name = "ll_change_price_period")
    private Integer llChangePricePeriod;
    @Column(name = "ll_appl_period")
    private Integer llApplPeriod;
    @Column(name = "ll_change_price_value")
    private BigDecimal llChangePriceValue;
    @Column(name = "ll_change_price_alg")
    private Short llChangePriceAlg;
    @Column(name = "ll_deposit_alg")
    private String llDepositAlg;
    @Column(name = "ll_transfer_result")
    private Integer llTransferResult;
    @Column(name = "ll_transfer_error")
    private String llTransferError;
    @Column(name = "ll_type_object")
    private String llTypeObject;
    @Column(name = "ll_address")
    private String llAddress;
    @Column(name = "ll_cl_asv")
    private String llClAsv;
    @Column(name = "ll_ind_right_ensure")
    private Short llIndRightEnsure;
    @JoinColumn(name = "la_unid", referencedColumnName = "la_unid")
    @ManyToOne(optional = false)
    private LoadAuction laUnid;
    @Column(name = "lot_unid")
    private Long lotUnid;
    @Column(name = "ll_land_common_sqr", precision = 18, scale = 2)
    private BigDecimal llLandCommonSqr; // Общая площадь ЗУ
    @Column(name = "ll_flat_common_sqr", precision = 18, scale = 2)
    private BigDecimal llFlatCommonSqr; // Общая площадь помещений
    @Column(name = "ll_contacts")
    private String llContacts;
    @Column(name = "ll_reward_rad")
    private String llRewardRadius;
    @Column(name = "ll_marketing_requirements")
    private String llMarketingRequirements;
    @Column(name = "ll_obj_links")
    private String llObjectLinks;
    @Column(name = "ll_assets")
    private String llAssets;
    @Column(name = "ll_asv_id")
    private String llAsvId;
    @Column(name = "ll_asv_link")
    private String llAsvLink;
    @Column(name = "sc_unid")
    private Long scUnid;
    @Column(name = "ll_change_price_period_min")
    private Integer llChangePricePeriodMin;
    @Column(name = "ll_start_price_time")
    private Integer llStartPriceTime;
    @Column(name = "ll_step_decrease_value")
    private BigDecimal llStepDecreaseValue;
    @Column(name = "ll_min_price")
    private BigDecimal llMinPrice;
    @Column(name = "ll_tz_num")
    private String llTzNum;
    @Column(name = "ll_tz_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date llTzDate;
    @Column(name = "ll_asv_stage_id")
    private String llAsvStageId;
    @Column(name = "ll_period_beginning_decline")
    private Integer llPeriodBeginningDecline;

    public LoadLot() {
    }

    public Long getLlUnid() {
        return llUnid;
    }

    public void setLlUnid(Long llUnid) {
        this.llUnid = llUnid;
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

    public String getLlLotNum() {
        return llLotNum;
    }

    public void setLlLotNum(String llLotNum) {
        this.llLotNum = llLotNum;
    }

    public String getLlRubric() {
        return llRubric;
    }

    public void setLlRubric(String llRubric) {
        this.llRubric = llRubric;
    }

    public String getLlRegion() {
        return llRegion;
    }

    public void setLlRegion(String llRegion) {
        this.llRegion = llRegion;
    }

    public String getLlLotSname() {
        return llLotSname;
    }

    public void setLlLotSname(String llLotSname) {
        this.llLotSname = llLotSname;
    }

    public String getLlLotName() {
        return llLotName;
    }

    public void setLlLotName(String llLotName) {
        this.llLotName = llLotName;
    }

    public String getLlReviewDebitorOrder() {
        return llReviewDebitorOrder;
    }

    public void setLlReviewDebitorOrder(String llReviewDebitorOrder) {
        this.llReviewDebitorOrder = llReviewDebitorOrder;
    }

    public BigDecimal getLlStartCost() {
        return llStartCost;
    }

    public void setLlStartCost(BigDecimal llStartCost) {
        this.llStartCost = llStartCost;
    }

    public BigDecimal getLlDepositSum() {
        return llDepositSum;
    }

    public void setLlDepositSum(BigDecimal llDepositSum) {
        this.llDepositSum = llDepositSum;
    }

    public BigDecimal getLlStepValue() {
        return llStepValue;
    }

    public void setLlStepValue(BigDecimal llStepValue) {
        this.llStepValue = llStepValue;
    }

    public Short getLlChangePriceTimeH() {
        return llChangePriceTimeH;
    }

    public void setLlChangePriceTimeH(Short llChangePriceTimeH) {
        this.llChangePriceTimeH = llChangePriceTimeH;
    }

    public Short getLlChangePriceTimeM() {
        return llChangePriceTimeM;
    }

    public void setLlChangePriceTimeM(Short llChangePriceTimeM) {
        this.llChangePriceTimeM = llChangePriceTimeM;
    }

    public Integer getLlChangePricePeriod() {
        return llChangePricePeriod;
    }

    public void setLlChangePricePeriod(Integer llChangePricePeriod) {
        this.llChangePricePeriod = llChangePricePeriod;
    }

    public Integer getLlApplPeriod() {
        return llApplPeriod;
    }

    public void setLlApplPeriod(Integer llApplPeriod) {
        this.llApplPeriod = llApplPeriod;
    }

    public BigDecimal getLlChangePriceValue() {
        return llChangePriceValue;
    }

    public void setLlChangePriceValue(BigDecimal llChangePriceValue) {
        this.llChangePriceValue = llChangePriceValue;
    }

    public Short getLlChangePriceAlg() {
        return llChangePriceAlg;
    }

    public void setLlChangePriceAlg(Short llChangePriceAlg) {
        this.llChangePriceAlg = llChangePriceAlg;
    }

    public String getLlDepositAlg() {
        return llDepositAlg;
    }

    public void setLlDepositAlg(String llDepositAlg) {
        this.llDepositAlg = llDepositAlg;
    }

    public LoadAuction getLaUnid() {
        return laUnid;
    }

    public void setLaUnid(LoadAuction laUnid) {
        this.laUnid = laUnid;
    }

    public Long getLotUnid() {
        return lotUnid;
    }

    public void setLotUnid(Long lotUnid) {
        this.lotUnid = lotUnid;
    }

    public Integer getLlTransferResult() {
        return llTransferResult;
    }

    public void setLlTransferResult(Integer llTransferResult) {
        this.llTransferResult = llTransferResult;
    }

    public String getLlTransferError() {
        return llTransferError;
    }

    public void setLlTransferError(String llTransferError) {
        this.llTransferError = llTransferError;
    }

    public String getLlTypeObject() {
        return llTypeObject;
    }

    public void setLlTypeObject(String llTypeObject) {
        this.llTypeObject = llTypeObject;
    }

    public String getLlAddress() {
        return llAddress;
    }

    public void setLlAddress(String llAddress) {
        this.llAddress = llAddress;
    }

    public String getLlClAsv() {
        return llClAsv;
    }

    public void setLlClAsv(String llClAsv) {
        this.llClAsv = llClAsv;
    }

    public Short getLlIndRightEnsure() {
        return llIndRightEnsure;
    }

    public void setLlIndRightEnsure(Short llIndRightEnsure) {
        this.llIndRightEnsure = llIndRightEnsure;
    }

    public BigDecimal getLlLandCommonSqr() {
        return llLandCommonSqr;
    }

    public void setLlLandCommonSqr(BigDecimal llLandCommonSqr) {
        this.llLandCommonSqr = llLandCommonSqr;
    }

    public BigDecimal getLlFlatCommonSqr() {
        return llFlatCommonSqr;
    }

    public void setLlFlatCommonSqr(BigDecimal llFlatCommonSqr) {
        this.llFlatCommonSqr = llFlatCommonSqr;
    }

    public String getLlContacts() {
        return llContacts;
    }

    public void setLlContacts(String llContacts) {
        this.llContacts = llContacts;
    }

    public String getLlRewardRadius() {
        return llRewardRadius;
    }

    public void setLlRewardRadius(String llRewardRadius) {
        this.llRewardRadius = llRewardRadius;
    }

    public String getLlMarketingRequirements() {
        return llMarketingRequirements;
    }

    public void setLlMarketingRequirements(String llMarketingRequirements) {
        this.llMarketingRequirements = llMarketingRequirements;
    }

    public String getLlObjectLinks() {
        return llObjectLinks;
    }

    public void setLlObjectLinks(String llObjectLinks) {
        this.llObjectLinks = llObjectLinks;
    }

    public String getLlAssets() {
        return llAssets;
    }

    public void setLlAssets(String llAssets) {
        this.llAssets = llAssets;
    }

    public String getLlAsvId() {
        return llAsvId;
    }

    public void setLlAsvId(String llAsvId) {
        this.llAsvId = llAsvId;
    }

    public String getLlAsvLink() {
        return llAsvLink;
    }

    public void setLlAsvLink(String llAsvLink) {
        this.llAsvLink = llAsvLink;
    }

    public Long getScUnid() {
        return scUnid;
    }

    public void setScUnid(Long scUnid) {
        this.scUnid = scUnid;
    }

    public Integer getLlChangePricePeriodMin() {
        return llChangePricePeriodMin;
    }

    public void setLlChangePricePeriodMin(Integer llChangePricePeriodMin) {
        this.llChangePricePeriodMin = llChangePricePeriodMin;
    }

    public Integer getLlStartPriceTime() {
        return llStartPriceTime;
    }

    public void setLlStartPriceTime(Integer llStartPriceTime) {
        this.llStartPriceTime = llStartPriceTime;
    }

    public BigDecimal getLlStepDecreaseValue() {
        return llStepDecreaseValue;
    }

    public void setLlStepDecreaseValue(BigDecimal llStepDecreaseValue) {
        this.llStepDecreaseValue = llStepDecreaseValue;
    }

    public BigDecimal getLlMinPrice() {
        return llMinPrice;
    }

    public void setLlMinPrice(BigDecimal llMinPrice) {
        this.llMinPrice = llMinPrice;
    }

    public String getLlTzNum() {
        return llTzNum;
    }

    public void setLlTzNum(String llTzNum) {
        this.llTzNum = llTzNum;
    }

    public Date getLlTzDate() {
        return llTzDate;
    }

    public void setLlTzDate(Date llTzDate) {
        this.llTzDate = llTzDate;
    }

    public String getLlAsvStageId() {
        return llAsvStageId;
    }

    public void setLlAsvStageId(String lotAsvStageId) {
        this.llAsvStageId = lotAsvStageId;
    }

    public Integer getLlPeriodBeginningDecline() {
        return llPeriodBeginningDecline;
    }

    public void setLlPeriodBeginningDecline(Integer llPeriodBeginningDecline) {
        this.llPeriodBeginningDecline = llPeriodBeginningDecline;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (llUnid != null ? llUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoadLot)) {
            return false;
        }
        LoadLot other = (LoadLot) object;
        return (this.llUnid != null || other.llUnid == null) && (this.llUnid == null || this.llUnid.equals(other.llUnid));
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.LoadLot[ llUnid=" + llUnid + " ]";
    }

}
