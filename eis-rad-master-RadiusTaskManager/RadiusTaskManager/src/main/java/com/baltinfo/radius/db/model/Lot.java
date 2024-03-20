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
import java.math.BigInteger;
import java.util.Date;

/**
 * @author lia
 */
@Entity
@Table(name = "lot", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"lot_unid"})})
@SequenceGenerator(name = "seq_lot", sequenceName = "seq_lot", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Lot.findAll", query = "SELECT l FROM Lot l"),
        @NamedQuery(name = "Lot.findByLotUnid", query = "SELECT l FROM Lot l WHERE l.lotUnid = :lotUnid"),
        @NamedQuery(name = "Lot.findByAuctionUnid", query = "SELECT l FROM Lot l WHERE l.indActual = 1 and l.auctionUnid = :auctionUnid"),
        @NamedQuery(name = "Lot.findByAuctionUnidOrderByLotNumber", query = "SELECT l FROM Lot l WHERE l.auctionUnid = :auctionUnid and l.indActual = 1 ORDER BY l.lotNumber"),
        @NamedQuery(name = "Lot.findByObjUnid", query = "SELECT l FROM Lot l WHERE l.objUnid.objUnid = :objUnid"),
        @NamedQuery(name = "Lot.findCurrentByObjUnid", query = "SELECT l FROM Lot l WHERE l.objUnid.objUnid = :objUnid and l.lotIndCurrent = 1 and l.indActual = 1"),
        @NamedQuery(name = "Lot.findByAuctionUnidAndTypeState", query = "SELECT l FROM Lot l WHERE l.auctionUnid = :auctionUnid and l.lotIndCurrent = 1 and l.indActual = 1 and l.tsUnid = :typeStateUnid "),
        @NamedQuery(name = "Lot.findNextStageLots", query = "SELECT l FROM Lot l, Auction a " +
                "WHERE l.auctionUnid = a.auctionUnid and a.baUnid = :baUnid and a.auctionStageNum > :auctionStageNum " +
                " and l.lotNumber = :lotNumber and a.indActual = 1 and l.indActual = 1 ")
})
public class Lot implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lot")
    @Column(name = "lot_unid")
    private Long lotUnid;
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
    @Column(name = "ts_unid")
    private Long tsUnid;
    @Column(name = "entity_unid")
    private Long entityUnid;
    @Column(name = "lot_notice_num")
    private String lotNoticeNum;
    @Column(name = "lot_number")
    private Long lotNumber;
    @Column(name = "lot_publ_header")
    private String lotPublHeader;
    @Column(name = "lot_auction_theme")
    private String lotAuctionTheme;
    @Column(name = "lot_step_value")
    private BigDecimal lotStepValue;
    @Column(name = "lot_step_decrease_value")
    private BigDecimal lotStepDecreaseValue;
    @Column(name = "lot_deposit_per")
    private BigDecimal lotDepositPer;
    @Column(name = "lot_sum_deposit")
    private BigDecimal lotSumDeposit;
    @Column(name = "lot_deposit_dav")
    private BigInteger lotDepositDav;
    @Column(name = "lot_ind_cond")
    private Integer lotIndCond;
    @Column(name = "lot_auction_cancel_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotAuctionCancelTime;
    @Column(name = "lot_auction_cancel_cause")
    private String lotAuctionCancelCause;
    @Column(name = "lot_auction_stop_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotAuctionStopTime;
    @Column(name = "lot_auction_restart_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotAuctionRestartTime;
    @Column(name = "lot_auction_restart_cause")
    private String lotAuctionRestartCause;
    @Column(name = "lot_auction_calc_end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotAuctionCalcEndTime;
    @Column(name = "lot_change_price_period")
    private Long lotChangePricePeriod;
    @Column(name = "lot_change_price_next_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotChangePriceNextTime;
    @Column(name = "lot_auction_time_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotAuctionTimeB;
    @Column(name = "lot_auction_time_e")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotAuctionTimeE;
    @Column(name = "lot_step_procent")
    private BigDecimal lotStepProcent;
    @Column(name = "lot_step_decrease_proc")
    private BigDecimal lotStepDecreaseProc;
    @Column(name = "lot_use_step")
    private Short lotUseStep;
    @Column(name = "lot_ind_current")
    private Integer lotIndCurrent;
    @Column(name = "lot_status")
    private Integer lotStatus;
    @Column(name = "auction_unid")
    private Long auctionUnid;
    @Column(name = "non_exec_reason_unid")
    private Long nonExecReasonUnid;
    @JoinColumn(name = "obj_unid", referencedColumnName = "obj_unid")
    @ManyToOne
    private ObjectJPA objUnid;
    @Column(name = "lot_lot_unid")
    private Long lotLotUnid;
    @Column(name = "lot_date_b_plan")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotDateBPlan;
    @Column(name = "lot_date_e_plan")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotDateEPlan;
    @Column(name = "rubr_unid")
    private Long rubrUnid;
    @Column(name = "ca_unid")
    private Long caUnid;
    @Column(name = "lot_review_debitor_order")
    private String lotReviewDebitorOrder;
    @Column(name = "lot_ind_right_ensure")
    private Short lotIndRightEnsure;
    @Column(name = "lot_asv_link")
    private String lotAsvLink;
    @Column(name = "lot_asv_id")
    private String lotAsvId;
    @Column(name = "lot_etp_code")
    private String lotEtpCode;
    @Column(name = "lot_start_price_time")
    private Long lotStartPriceTime;
    @Column(name = "lot_view_count")
    private Long lotViewCount;
    @Column(name = "lot_appl_count")
    private Long lotApplCount;
    @Column(name = "lot_operator_deposit")
    private BigDecimal lotOperatorDeposit;
    @Column(name = "lot_app_provide_amount")
    private String lotAppProvideAmount;
    @Column(name = "lot_pay_terms")
    private String lotPayTerms;
    @Column(name = "lot_min_price")
    private BigDecimal lotMinPrice;
    @Column(name = "cur_unid")
    private Long curUnid;
    @Column(name = "lot_asv_stage_id")
    private String lotAsvStageId;
    @Column(name = "lot_link_site")
    private String lotLinkSite;
    @Column(name = "lot_rad_site")
    private String lotRadSite;
    @Column(name = "LOT_DEAL_SINGLE_ALLOW") // Договор с единственным участником может быть заключен
    private Boolean lotDealSingleAllow;
    @Column(name = "LOT_DEAL_SINGLE_REQUIRED") // Договор с единственным участником должен быть заключен';
    private Boolean lotDealSingleRequired;
    @Column(name = "LOT_DEAL_SECOND_OFFER_ALLOW")
    // Заключение договора с участником, сделавшим предпоследнее предложение';
    private Boolean lotDealSecondOfferAllow;
    @Column(name = "lot_etp_id")
    private Long lotEtpId;
    @Column(name = "lot_calls_count")
    private Long lotCallsCount;
    @Column(name = "lot_calls_uniq_count")
    private Long lotCallsUniqCount;
    @Column(name = "lot_rad_holding_site")
    private String lotRadHoldingSite;

    public Lot() {
    }

    public Lot(Long lotUnid) {
        this.lotUnid = lotUnid;
    }

    public Long getLotUnid() {
        return lotUnid;
    }

    public void setLotUnid(Long lotUnid) {
        this.lotUnid = lotUnid;
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

    public Long getTsUnid() {
        return tsUnid;
    }

    public void setTsUnid(Long tsUnid) {
        this.tsUnid = tsUnid;
    }

    public Long getEntityUnid() {
        return entityUnid;
    }

    public void setEntityUnid(Long entityUnid) {
        this.entityUnid = entityUnid;
    }

    public String getLotNoticeNum() {
        return lotNoticeNum;
    }

    public void setLotNoticeNum(String lotNoticeNum) {
        this.lotNoticeNum = lotNoticeNum;
    }

    public Long getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(Long lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getLotPublHeader() {
        return lotPublHeader;
    }

    public void setLotPublHeader(String lotPublHeader) {
        this.lotPublHeader = lotPublHeader;
    }

    public String getLotAuctionTheme() {
        return lotAuctionTheme;
    }

    public void setLotAuctionTheme(String lotAuctionTheme) {
        this.lotAuctionTheme = lotAuctionTheme;
    }

    public BigDecimal getLotStepValue() {
        return lotStepValue;
    }

    public void setLotStepValue(BigDecimal lotStepValue) {
        this.lotStepValue = lotStepValue;
    }

    public BigDecimal getLotStepDecreaseValue() {
        return lotStepDecreaseValue;
    }

    public void setLotStepDecreaseValue(BigDecimal lotStepDecreaseValue) {
        this.lotStepDecreaseValue = lotStepDecreaseValue;
    }

    public BigDecimal getLotDepositPer() {
        return lotDepositPer;
    }

    public void setLotDepositPer(BigDecimal lotDepositPer) {
        this.lotDepositPer = lotDepositPer;
    }

    public BigDecimal getLotSumDeposit() {
        return lotSumDeposit;
    }

    public void setLotSumDeposit(BigDecimal lotSumDeposit) {
        this.lotSumDeposit = lotSumDeposit;
    }

    public BigInteger getLotDepositDav() {
        return lotDepositDav;
    }

    public void setLotDepositDav(BigInteger lotDepositDav) {
        this.lotDepositDav = lotDepositDav;
    }

    public Integer getLotIndCond() {
        return lotIndCond;
    }

    public void setLotIndCond(Integer lotIndCond) {
        this.lotIndCond = lotIndCond;
    }

    public Date getLotAuctionCancelTime() {
        return lotAuctionCancelTime;
    }

    public void setLotAuctionCancelTime(Date lotAuctionCancelTime) {
        this.lotAuctionCancelTime = lotAuctionCancelTime;
    }

    public String getLotAuctionCancelCause() {
        return lotAuctionCancelCause;
    }

    public void setLotAuctionCancelCause(String lotAuctionCancelCause) {
        this.lotAuctionCancelCause = lotAuctionCancelCause;
    }

    public Date getLotAuctionStopTime() {
        return lotAuctionStopTime;
    }

    public void setLotAuctionStopTime(Date lotAuctionStopTime) {
        this.lotAuctionStopTime = lotAuctionStopTime;
    }

    public Date getLotAuctionRestartTime() {
        return lotAuctionRestartTime;
    }

    public void setLotAuctionRestartTime(Date lotAuctionRestartTime) {
        this.lotAuctionRestartTime = lotAuctionRestartTime;
    }

    public String getLotAuctionRestartCause() {
        return lotAuctionRestartCause;
    }

    public void setLotAuctionRestartCause(String lotAuctionRestartCause) {
        this.lotAuctionRestartCause = lotAuctionRestartCause;
    }

    public Date getLotAuctionCalcEndTime() {
        return lotAuctionCalcEndTime;
    }

    public void setLotAuctionCalcEndTime(Date lotAuctionCalcEndTime) {
        this.lotAuctionCalcEndTime = lotAuctionCalcEndTime;
    }

    public Long getLotChangePricePeriod() {
        return lotChangePricePeriod;
    }

    public void setLotChangePricePeriod(Long lotChangePricePeriod) {
        this.lotChangePricePeriod = lotChangePricePeriod;
    }

    public Date getLotChangePriceNextTime() {
        return lotChangePriceNextTime;
    }

    public void setLotChangePriceNextTime(Date lotChangePriceNextTime) {
        this.lotChangePriceNextTime = lotChangePriceNextTime;
    }

    public Date getLotAuctionTimeB() {
        return lotAuctionTimeB;
    }

    public void setLotAuctionTimeB(Date lotAuctionTimeB) {
        this.lotAuctionTimeB = lotAuctionTimeB;
    }

    public Date getLotAuctionTimeE() {
        return lotAuctionTimeE;
    }

    public void setLotAuctionTimeE(Date lotAuctionTimeE) {
        this.lotAuctionTimeE = lotAuctionTimeE;
    }

    public BigDecimal getLotStepProcent() {
        return lotStepProcent;
    }

    public void setLotStepProcent(BigDecimal lotStepProcent) {
        this.lotStepProcent = lotStepProcent;
    }

    public BigDecimal getLotStepDecreaseProc() {
        return lotStepDecreaseProc;
    }

    public void setLotStepDecreaseProc(BigDecimal lotStepDecreaseProc) {
        this.lotStepDecreaseProc = lotStepDecreaseProc;
    }

    public Short getLotUseStep() {
        return lotUseStep;
    }

    public void setLotUseStep(Short lotUseStep) {
        this.lotUseStep = lotUseStep;
    }

    public Integer getLotIndCurrent() {
        return lotIndCurrent;
    }

    public void setLotIndCurrent(Integer lotIndCurrent) {
        this.lotIndCurrent = lotIndCurrent;
    }

    public Integer getLotStatus() {
        return lotStatus;
    }

    public void setLotStatus(Integer lotStatus) {
        this.lotStatus = lotStatus;
    }

    public Long getAuctionUnid() {
        return auctionUnid;
    }

    public void setAuctionUnid(Long auctionUnid) {
        this.auctionUnid = auctionUnid;
    }

    public Long getNonExecReasonUnid() {
        return nonExecReasonUnid;
    }

    public void setNonExecReasonUnid(Long nonExecReasonUnid) {
        this.nonExecReasonUnid = nonExecReasonUnid;
    }

    public ObjectJPA getObjUnid() {
        return objUnid;
    }

    public void setObjUnid(ObjectJPA objUnid) {
        this.objUnid = objUnid;
    }

    public Long getLotLotUnid() {
        return lotLotUnid;
    }

    public void setLotLotUnid(Long lotLotUnid) {
        this.lotLotUnid = lotLotUnid;
    }

    public Date getLotDateBPlan() {
        return lotDateBPlan;
    }

    public void setLotDateBPlan(Date lotDateBPlan) {
        this.lotDateBPlan = lotDateBPlan;
    }

    public Date getLotDateEPlan() {
        return lotDateEPlan;
    }

    public void setLotDateEPlan(Date lotDateEPlan) {
        this.lotDateEPlan = lotDateEPlan;
    }

    public String getLotReviewDebitorOrder() {
        return lotReviewDebitorOrder;
    }

    public void setLotReviewDebitorOrder(String lotReviewDebitorOrder) {
        this.lotReviewDebitorOrder = lotReviewDebitorOrder;
    }

    public Long getRubrUnid() {
        return rubrUnid;
    }

    public void setRubrUnid(Long rubrUnid) {
        this.rubrUnid = rubrUnid;
    }

    public Long getCaUnid() {
        return caUnid;
    }

    public void setCaUnid(Long caUnid) {
        this.caUnid = caUnid;
    }

    public Short getLotIndRightEnsure() {
        return lotIndRightEnsure;
    }

    public void setLotIndRightEnsure(Short lotIndRightEnsure) {
        this.lotIndRightEnsure = lotIndRightEnsure;
    }

    public String getLotAsvLink() {
        return lotAsvLink;
    }

    public void setLotAsvLink(String lotAsvLink) {
        this.lotAsvLink = lotAsvLink;
    }

    public String getLotAsvId() {
        return lotAsvId;
    }

    public void setLotAsvId(String lotAsvId) {
        this.lotAsvId = lotAsvId;
    }

    public String getLotEtpCode() {
        return lotEtpCode;
    }

    public void setLotEtpCode(String lotEtpCode) {
        this.lotEtpCode = lotEtpCode;
    }

    public Long getLotStartPriceTime() {
        return lotStartPriceTime;
    }

    public void setLotStartPriceTime(Long lotStartPriceTime) {
        this.lotStartPriceTime = lotStartPriceTime;
    }

    public Long getLotViewCount() {
        return lotViewCount;
    }

    public void setLotViewCount(Long lotViewCount) {
        this.lotViewCount = lotViewCount;
    }

    public Long getLotApplCount() {
        return lotApplCount;
    }

    public void setLotApplCount(Long lotApplCount) {
        this.lotApplCount = lotApplCount;
    }

    public BigDecimal getLotOperatorDeposit() {
        return lotOperatorDeposit;
    }

    public void setLotOperatorDeposit(BigDecimal lotOperatorDeposit) {
        this.lotOperatorDeposit = lotOperatorDeposit;
    }

    public String getLotAppProvideAmount() {
        return lotAppProvideAmount;
    }

    public void setLotAppProvideAmount(String lotAppProvideAmount) {
        this.lotAppProvideAmount = lotAppProvideAmount;
    }

    public String getLotPayTerms() {
        return lotPayTerms;
    }

    public void setLotPayTerms(String lotPayTerms) {
        this.lotPayTerms = lotPayTerms;
    }

    public BigDecimal getLotMinPrice() {
        return lotMinPrice;
    }

    public void setLotMinPrice(BigDecimal lotMinPrice) {
        this.lotMinPrice = lotMinPrice;
    }

    public Long getCurUnid() {
        return curUnid;
    }

    public void setCurUnid(Long curUnid) {
        this.curUnid = curUnid;
    }

    public String getLotAsvStageId() {
        return lotAsvStageId;
    }

    public void setLotAsvStageId(String lotAsvStageId) {
        this.lotAsvStageId = lotAsvStageId;
    }

    public String getLotLinkSite() {
        return lotLinkSite;
    }

    public void setLotLinkSite(String lotLinkSite) {
        this.lotLinkSite = lotLinkSite;
    }

    public String getLotRadSite() {
        return lotRadSite;
    }

    public void setLotRadSite(String lotRadSite) {
        this.lotRadSite = lotRadSite;
    }

    public Boolean getLotDealSingleAllow() {
        return lotDealSingleAllow;
    }

    public void setLotDealSingleAllow(Boolean lotDealSingleAllow) {
        this.lotDealSingleAllow = lotDealSingleAllow;
    }

    public Boolean getLotDealSingleRequired() {
        return lotDealSingleRequired;
    }

    public void setLotDealSingleRequired(Boolean lotDealSingleRequired) {
        this.lotDealSingleRequired = lotDealSingleRequired;
    }

    public Boolean getLotDealSecondOfferAllow() {
        return lotDealSecondOfferAllow;
    }

    public void setLotDealSecondOfferAllow(Boolean lotDealSecondOfferAllow) {
        this.lotDealSecondOfferAllow = lotDealSecondOfferAllow;
    }

    public Long getLotEtpId() {
        return lotEtpId;
    }

    public void setLotEtpId(Long lotEtpId) {
        this.lotEtpId = lotEtpId;
    }

    public Long getLotCallsCount() {
        return lotCallsCount;
    }

    public void setLotCallsCount(Long lotCallsCount) {
        this.lotCallsCount = lotCallsCount;
    }

    public Long getLotCallsUniqCount() {
        return lotCallsUniqCount;
    }

    public void setLotCallsUniqCount(Long lotCallsUniqCount) {
        this.lotCallsUniqCount = lotCallsUniqCount;
    }

    public String getLotRadHoldingSite() {
        return lotRadHoldingSite;
    }

    public void setLotRadHoldingSite(String lotRadHoldingSite) {
        this.lotRadHoldingSite = lotRadHoldingSite;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.Lot[ lotUnid=" + lotUnid + " ]";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (int) (this.lotUnid ^ (this.lotUnid >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lot other = (Lot) obj;
        return this.lotUnid == other.lotUnid;
    }

}
