
package com.baltinfo.radius.db.model.bankruptcy;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "AUCTION_LOT_ALL", catalog = "", schema = "WEB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VAuctionLotAll.findAll", query = "SELECT a FROM VAuctionLotAll a"),
    @NamedQuery(name = "VAuctionLotAll.findByAuctionUnid", query = "SELECT a FROM VAuctionLotAll a where a.auctionUnid = :auctionUnid")})
public class VAuctionLotAll implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @Column(name = "LOT_UNID")
    private long lotUnid;
    @Column(name = "DO_NAME")
    private String doName;
    @Column(name = "NON_EXEC_REASON_UNID")
    private Long nonExecReasonUnid;
    @Column(name = "LOT_STEP_VALUE")
    private BigDecimal lotStepValue;
    @Column(name = "LOT_DEPOSIT_PER")
    private BigDecimal lotDepositPer;
    @Column(name = "LOT_SUM_DEPOSIT")
    private BigDecimal lotSumDeposit;
    @Column(name = "LOT_NUMBER")
    private String lotNumber;
    @Column(name = "LOT_IND_COND")
    private Short lotIndCond;
    @Column(name = "LOT_AUCTION_CANCEL_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotAuctionCancelTime;
    @Column(name = "LOT_AUCTION_STOP_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotAuctionStopTime;
    @Column(name = "LOT_AUCTION_RESTART_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotAuctionRestartTime;
    @Column(name = "LOT_AUCTION_CANCEL_CAUSE")
    private String lotAuctionCancelCause;
    @Column(name = "LOT_AUCTION_RESTART_CAUSE")
    private String lotAuctionRestartCause;
    @Column(name = "LOT_LOT_UNID")
    private Long lotLotUnid;
    @Column(name = "DO_ADDRESS")
    private String doAddress;
    @Basic(optional = false)
    @Column(name = "DO_UNID")
    private long doUnid;
    @Column(name = "DO_AUCTION_THEME")
    private String doAuctionTheme;
    @Column(name = "DO_PUBL_HEADER")
    private String doPublHeader;
    @Column(name = "DO0_OBJ_COMMON_SQR")
    private BigDecimal do0ObjCommonSqr;
    @Column(name = "DO1_OBJ_COMMON_SQR")
    private BigDecimal do1ObjCommonSqr;
    @Column(name = "DO2_OBJ_COMMON_SQR")
    private BigDecimal do2ObjCommonSqr;
    @Column(name = "DO_FILE_NAME")
    private String doFileName;
    @Column(name = "RUBR_UNID")
    private Long rubrUnid;
    @Basic(optional = false)
    @Column(name = "TDO_NAME")
    private String tdoName;
    @Basic(optional = false)
    @Column(name = "TDO_UNID")
    private long tdoUnid;
    @Column(name = "LOT_DEPOSIT_DAV")
    private BigDecimal lotDepositDav;
    @Column(name = "LOT_IND_DOWN")
    private Short lotIndDown;
    @Column(name = "LOT_DOWN_PERIOD")
    private Short lotDownPeriod;
    @Column(name = "LOT_DAY_TYPE")
    private Short lotDayType;
    @Column(name = "LOT_MIN_PRICE")
    private BigDecimal lotMinPrice;
    @Column(name = "LOT_RATE")
    private BigDecimal lotRate;
    @Column(name = "LOT_SUM_DEPOSIT_RUB")
    private BigDecimal lotSumDepositRub;
    @Column(name = "LOT_NMB_PRT")
    private String lotNmbPrt;
    @Column(name = "LOT_DATE_PRT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotDatePrt;
    @Column(name = "LOT_DEPOSIT_RATE")
    private BigDecimal lotDepositRate;
    @Column(name = "LOT_RECEP_DATE_B")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotRecepDateB;
    @Column(name = "LOT_RECEP_DATE_E")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotRecepDateE;
    @Column(name = "LOT_DEP_DATE_E")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotDepDateE;
    @Column(name = "LOT_STEP_DECREASE_VALUE")
    private BigDecimal lotStepDecreaseValue;
    @Column(name = "LOT_NOTICE_NUM")
    private String lotNoticeNum;
    @Column(name = "LOT_AUCTION_CALC_END_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotAuctionCalcEndTime;
    @Column(name = "LOT_REVIEW_DEBITOR_ORDER")
    private String lotReviewDebitorOrder;
    @Column(name = "LOT_CHANGE_PRICE_HOUR")
    private Short lotChangePriceHour;
    @Column(name = "LOT_CHANGE_PRICE_MIN")
    private Short lotChangePriceMin;
    @Column(name = "LOT_CHANGE_PRICE_PERIOD")
    private Long lotChangePricePeriod;
    @Column(name = "TCP_UNID")
    private Long tcpUnid;
    @Column(name = "LOT_PARTICIPANT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotParticipantDate;
    @Basic(optional = false)
    @Column(name = "WP_UNID")
    private long wpUnid;
    @Column(name = "OC_UNID")
    private Long ocUnid;
    @Column(name = "OC_NAME")
    private String ocName;
    @Column(name = "WP_PUBLISH_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date wpPublishTime;
    @Column(name = "WP_PUBLISH_USER")
    private Long wpPublishUser;
    @Column(name = "WP_IND_PUBLISH")
    private Integer wpIndPublish;
    @Column(name = "WP_UNPUBLISH_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date wpUnpublishTime;
    @Column(name = "WP_UNPUBLISH_MOTIVE")
    private String wpUnpublishMotive;
    @Column(name = "WP_IND_FILING")
    private Integer wpIndFiling;
    @Column(name = "WP_IND_SHOW_AUCTION_STEPS")
    private Integer wpIndShowAuctionSteps;
    @Column(name = "WP_IND_AUCTION_PARTICIPATION")
    private Integer wpIndAuctionParticipation;
    @Column(name = "WP_IND_NONE_APPLICATION")
    private Integer wpIndNoneApplication;
    @Column(name = "WP_IND_CANCEL_ACCREDITATION")
    private Integer wpIndCancelAccreditation;
    @Column(name = "WP_PLACE")
    private Short wpPlace;
    @Column(name = "WP_IND_COMB_AUCTION")
    private Integer wpIndCombAuction;
    @Column(name = "WP_IND_SHOW_IN_NEWS")
    private Integer wpIndShowInNews;
    @Column(name = "COST_B_VALUE")
    private BigDecimal costBValue;
    @Column(name = "CUR_NAME_B")
    private String curNameB;
    @Column(name = "COST_E_VALUE")
    private BigDecimal costEValue;
    @Column(name = "CUR_NAME_E")
    private String curNameE;
    @Column(name = "VAL_COST_RUB_MIN")
    private BigDecimal valCostRubMin;
    @Column(name = "VAL_COST_RUB_MAX")
    private BigDecimal valCostRubMax;
    @Column(name = "VAL_COST_RUB_CUR_NAME_GEN")
    private String valCostRubCurNameGen;
    @Column(name = "VAL_COST_USD_MIN")
    private BigDecimal valCostUsdMin;
    @Column(name = "VAL_COST_USD_MAX")
    private BigDecimal valCostUsdMax;
    @Column(name = "VAL_COST_USD_CUR_NAME_GEN")
    private String valCostUsdCurNameGen;
    @Column(name = "VAL_COST_EURO_MIN")
    private BigDecimal valCostEuroMin;
    @Column(name = "VAL_COST_EURO_MAX")
    private BigDecimal valCostEuroMax;
    @Column(name = "VAL_COST_EURO_CUR_NAME_GEN")
    private String valCostEuroCurNameGen;
    @Column(name = "AUCTIONER_NAME")
    private String auctionerName;
    @Column(name = "AUCTIONER_UNID")
    private Long auctionerUnid;
    @Basic(optional = false)
    @Column(name = "AUCTION_UNID")
    private long auctionUnid;
    @Column(name = "AUCTION_RECEP_DATE_B")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionRecepDateB;
    @Column(name = "AUCTION_RECEP_DATE_E")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionRecepDateE;
    @Column(name = "AUCTION_DATE_PLAN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionDatePlan;
    @Column(name = "AUCTION_DATE_FACT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionDateFact;
    @Column(name = "AUCTION_DEP_DATE_E")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionDepDateE;
    @Column(name = "AUCTION_PUBLIC_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionPublicDate;
    @Column(name = "AUCTION_PRESENTATION")
    private String auctionPresentation;
    @Column(name = "AUCTION_IND_DOWN")
    private Short auctionIndDown;
    @Column(name = "AUCTION_END_TERM")
    private Short auctionEndTerm;
    @Column(name = "AUCTION_END_TIME")
    private Integer auctionEndTime;
    @Column(name = "AUCTION_DATE_E")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionDateE;
    @Column(name = "LOT_AUCTION_DATE_E_FACT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotAuctionDateEFact;
    @Column(name = "WB_UNID")
    private Long wbUnid;
    @Column(name = "AUCTION_REG_NUM")
    private String auctionRegNum;
    @Column(name = "AUCTION_REG_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionRegDate;
    @Column(name = "DP_UNID")
    private Long dpUnid;
    @Column(name = "AUCTION_TRADE_FORM")
    private Short auctionTradeForm;
    @Column(name = "AUCTION_IND_SIGN")
    private Short auctionIndSign;
    @Column(name = "AUCTION_IND_BID")
    private Short auctionIndBid;
    @Column(name = "CUR_UNID")
    private Long curUnid;
    @Column(name = "CUR_NAME")
    private String curName;
    @Basic(optional = false)
    @Column(name = "TYPE_AUCTION_UNID")
    private long typeAuctionUnid;
    @Column(name = "TYPE_AUCTION_NAME")
    private String typeAuctionName;
    @Basic(optional = false)
    @Column(name = "TYPE_AUCTION_CODE")
    private short typeAuctionCode;
    @Basic(optional = false)
    @Column(name = "DO_INSTANCE_UNID")
    private long doInstanceUnid;
    @Basic(optional = false)
    @Column(name = "DO_ENTITY_UNID")
    private long doEntityUnid;
    @Column(name = "DO_ENTITY_NAME")
    private String doEntityName;
    @Basic(optional = false)
    @Column(name = "DO_STATE_UNID")
    private long doStateUnid;
    @Basic(optional = false)
    @Column(name = "DO_TYPE_STATE_UNID")
    private long doTypeStateUnid;
    @Column(name = "DO_TYPE_STATE_NAME")
    private String doTypeStateName;
    @Column(name = "SG_CATEGORY")
    private String sgCategory;
    @Column(name = "SG_UNID")
    private Long sgUnid;
    @Column(name = "SG_IND_DIGITAL_SIGNATURE")
    private Integer sgIndDigitalSignature;
    @Column(name = "SG_IND_NONE_APPLICATION")
    private Integer sgIndNoneApplication;
    @Column(name = "SG_IND_BID")
    private Integer sgIndBid;
    @Column(name = "SG_IND_ACCREDITATION")
    private Integer sgIndAccreditation;
    @Column(name = "WB_IND_OPERATOR")
    private Integer wbIndOperator;
    @Column(name = "WEB_BIDDER_PARTY_NAME")
    private String webBidderPartyName;
    @Column(name = "WEB_BIDDER_PARTY_ADDR_FACT")
    private String webBidderPartyAddrFact;
    @Column(name = "WEB_BIDDER_PARTY_ADDR_LEGAL")
    private String webBidderPartyAddrLegal;
    @Column(name = "WEB_BIDDER_PARTY_EMAIL")
    private String webBidderPartyEmail;
    @Column(name = "WEB_BIDDER_PARTY_PHONE")
    private String webBidderPartyPhone;
    @Column(name = "RUBR_NAME")
    private String rubrName;
    @Column(name = "APP_COUNT")
    private Integer appCount;
    @Column(name = "APP_RS_TOTAL_COUNT")
    private Integer appRsTotalCount;
    @Column(name = "APP_TOTAL_COUNT")
    private Integer appTotalCount;
    @Column(name = "LAST_STEP")
    private BigDecimal lastStep;
    @Column(name = "LOT_CHANGE_PRICE_NEXT_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotChangePriceNextTime;
    @Column(name = "STATE")
    private Integer state;
    @Column(name = "AUCTION_IND_CLOSE")
    private Integer auctionIndClose;
    @Column(name = "LOT_STEP_PROCENT")
    private BigDecimal lotStepProcent;
    @Column(name = "LOT_DEAL_STATE")
    private Short lotDealState;
    @Column(name = "LOT_DEAL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lotDealDate;
    @Column(name = "LOT_DEAL_SUM")
    private BigDecimal lotDealSum;
    @Column(name = "LOT_DEAL_CANCEL_CAUSE")
    private String lotDealCancelCause;
    @Column(name = "LOT_DEAL_PARTY_INFO")
    private String lotDealPartyInfo;
    @Column(name = "PARTY_UNID")
    private Long partyUnid;
    @Column(name = "SRF_UNID")
    private Long srfUnid;
    @Column(name = "SRF_NAME")
    private String srfName;
    @Column(name = "SRF_CODE")
    private String srfCode;
    @Column(name = "LANG")
    private String lang;
    @Column(name = "LOT_IND_PLEDGE")
    private Short lotIndPledge;
    @Column(name = "LOT_IND_RIGHT_ENSURE")
    private Short lotIndRightEnsure;

    public VAuctionLotAll() {
    }

    public long getLotUnid() {
        return lotUnid;
    }

    public void setLotUnid(long lotUnid) {
        this.lotUnid = lotUnid;
    }

    public String getDoName() {
        return doName;
    }

    public void setDoName(String doName) {
        this.doName = doName;
    }

    public Long getNonExecReasonUnid() {
        return nonExecReasonUnid;
    }

    public void setNonExecReasonUnid(Long nonExecReasonUnid) {
        this.nonExecReasonUnid = nonExecReasonUnid;
    }

    public BigDecimal getLotStepValue() {
        return lotStepValue;
    }

    public void setLotStepValue(BigDecimal lotStepValue) {
        this.lotStepValue = lotStepValue;
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

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Short getLotIndCond() {
        return lotIndCond;
    }

    public void setLotIndCond(Short lotIndCond) {
        this.lotIndCond = lotIndCond;
    }

    public Date getLotAuctionCancelTime() {
        return lotAuctionCancelTime;
    }

    public void setLotAuctionCancelTime(Date lotAuctionCancelTime) {
        this.lotAuctionCancelTime = lotAuctionCancelTime;
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

    public String getLotAuctionCancelCause() {
        return lotAuctionCancelCause;
    }

    public void setLotAuctionCancelCause(String lotAuctionCancelCause) {
        this.lotAuctionCancelCause = lotAuctionCancelCause;
    }

    public String getLotAuctionRestartCause() {
        return lotAuctionRestartCause;
    }

    public void setLotAuctionRestartCause(String lotAuctionRestartCause) {
        this.lotAuctionRestartCause = lotAuctionRestartCause;
    }

    public Long getLotLotUnid() {
        return lotLotUnid;
    }

    public void setLotLotUnid(Long lotLotUnid) {
        this.lotLotUnid = lotLotUnid;
    }

    public String getDoAddress() {
        return doAddress;
    }

    public void setDoAddress(String doAddress) {
        this.doAddress = doAddress;
    }

    public long getDoUnid() {
        return doUnid;
    }

    public void setDoUnid(long doUnid) {
        this.doUnid = doUnid;
    }

    public String getDoAuctionTheme() {
        return doAuctionTheme;
    }

    public void setDoAuctionTheme(String doAuctionTheme) {
        this.doAuctionTheme = doAuctionTheme;
    }

    public String getDoPublHeader() {
        return doPublHeader;
    }

    public void setDoPublHeader(String doPublHeader) {
        this.doPublHeader = doPublHeader;
    }

    public BigDecimal getDo0ObjCommonSqr() {
        return do0ObjCommonSqr;
    }

    public void setDo0ObjCommonSqr(BigDecimal do0ObjCommonSqr) {
        this.do0ObjCommonSqr = do0ObjCommonSqr;
    }

    public BigDecimal getDo1ObjCommonSqr() {
        return do1ObjCommonSqr;
    }

    public void setDo1ObjCommonSqr(BigDecimal do1ObjCommonSqr) {
        this.do1ObjCommonSqr = do1ObjCommonSqr;
    }

    public BigDecimal getDo2ObjCommonSqr() {
        return do2ObjCommonSqr;
    }

    public void setDo2ObjCommonSqr(BigDecimal do2ObjCommonSqr) {
        this.do2ObjCommonSqr = do2ObjCommonSqr;
    }

    public String getDoFileName() {
        return doFileName;
    }

    public void setDoFileName(String doFileName) {
        this.doFileName = doFileName;
    }

    public Long getRubrUnid() {
        return rubrUnid;
    }

    public void setRubrUnid(Long rubrUnid) {
        this.rubrUnid = rubrUnid;
    }

    public String getTdoName() {
        return tdoName;
    }

    public void setTdoName(String tdoName) {
        this.tdoName = tdoName;
    }

    public long getTdoUnid() {
        return tdoUnid;
    }

    public void setTdoUnid(long tdoUnid) {
        this.tdoUnid = tdoUnid;
    }

    public BigDecimal getLotDepositDav() {
        return lotDepositDav;
    }

    public void setLotDepositDav(BigDecimal lotDepositDav) {
        this.lotDepositDav = lotDepositDav;
    }

    public Short getLotIndDown() {
        return lotIndDown;
    }

    public void setLotIndDown(Short lotIndDown) {
        this.lotIndDown = lotIndDown;
    }

    public Short getLotDownPeriod() {
        return lotDownPeriod;
    }

    public void setLotDownPeriod(Short lotDownPeriod) {
        this.lotDownPeriod = lotDownPeriod;
    }

    public Short getLotDayType() {
        return lotDayType;
    }

    public void setLotDayType(Short lotDayType) {
        this.lotDayType = lotDayType;
    }

    public BigDecimal getLotMinPrice() {
        return lotMinPrice;
    }

    public void setLotMinPrice(BigDecimal lotMinPrice) {
        this.lotMinPrice = lotMinPrice;
    }

    public BigDecimal getLotRate() {
        return lotRate;
    }

    public void setLotRate(BigDecimal lotRate) {
        this.lotRate = lotRate;
    }

    public BigDecimal getLotSumDepositRub() {
        return lotSumDepositRub;
    }

    public void setLotSumDepositRub(BigDecimal lotSumDepositRub) {
        this.lotSumDepositRub = lotSumDepositRub;
    }

    public String getLotNmbPrt() {
        return lotNmbPrt;
    }

    public void setLotNmbPrt(String lotNmbPrt) {
        this.lotNmbPrt = lotNmbPrt;
    }

    public Date getLotDatePrt() {
        return lotDatePrt;
    }

    public void setLotDatePrt(Date lotDatePrt) {
        this.lotDatePrt = lotDatePrt;
    }

    public BigDecimal getLotDepositRate() {
        return lotDepositRate;
    }

    public void setLotDepositRate(BigDecimal lotDepositRate) {
        this.lotDepositRate = lotDepositRate;
    }

    public Date getLotRecepDateB() {
        return lotRecepDateB;
    }

    public void setLotRecepDateB(Date lotRecepDateB) {
        this.lotRecepDateB = lotRecepDateB;
    }

    public Date getLotRecepDateE() {
        return lotRecepDateE;
    }

    public void setLotRecepDateE(Date lotRecepDateE) {
        this.lotRecepDateE = lotRecepDateE;
    }

    public Date getLotDepDateE() {
        return lotDepDateE;
    }

    public void setLotDepDateE(Date lotDepDateE) {
        this.lotDepDateE = lotDepDateE;
    }

    public BigDecimal getLotStepDecreaseValue() {
        return lotStepDecreaseValue;
    }

    public void setLotStepDecreaseValue(BigDecimal lotStepDecreaseValue) {
        this.lotStepDecreaseValue = lotStepDecreaseValue;
    }

    public String getLotNoticeNum() {
        return lotNoticeNum;
    }

    public void setLotNoticeNum(String lotNoticeNum) {
        this.lotNoticeNum = lotNoticeNum;
    }

    public Date getLotAuctionCalcEndTime() {
        return lotAuctionCalcEndTime;
    }

    public void setLotAuctionCalcEndTime(Date lotAuctionCalcEndTime) {
        this.lotAuctionCalcEndTime = lotAuctionCalcEndTime;
    }

    public String getLotReviewDebitorOrder() {
        return lotReviewDebitorOrder;
    }

    public void setLotReviewDebitorOrder(String lotReviewDebitorOrder) {
        this.lotReviewDebitorOrder = lotReviewDebitorOrder;
    }

    public Short getLotChangePriceHour() {
        return lotChangePriceHour;
    }

    public void setLotChangePriceHour(Short lotChangePriceHour) {
        this.lotChangePriceHour = lotChangePriceHour;
    }

    public Short getLotChangePriceMin() {
        return lotChangePriceMin;
    }

    public void setLotChangePriceMin(Short lotChangePriceMin) {
        this.lotChangePriceMin = lotChangePriceMin;
    }

    public Long getLotChangePricePeriod() {
        return lotChangePricePeriod;
    }

    public void setLotChangePricePeriod(Long lotChangePricePeriod) {
        this.lotChangePricePeriod = lotChangePricePeriod;
    }

    public Long getTcpUnid() {
        return tcpUnid;
    }

    public void setTcpUnid(Long tcpUnid) {
        this.tcpUnid = tcpUnid;
    }

    public Date getLotParticipantDate() {
        return lotParticipantDate;
    }

    public void setLotParticipantDate(Date lotParticipantDate) {
        this.lotParticipantDate = lotParticipantDate;
    }

    public long getWpUnid() {
        return wpUnid;
    }

    public void setWpUnid(long wpUnid) {
        this.wpUnid = wpUnid;
    }

    public Long getOcUnid() {
        return ocUnid;
    }

    public void setOcUnid(Long ocUnid) {
        this.ocUnid = ocUnid;
    }

    public String getOcName() {
        return ocName;
    }

    public void setOcName(String ocName) {
        this.ocName = ocName;
    }

    public Date getWpPublishTime() {
        return wpPublishTime;
    }

    public void setWpPublishTime(Date wpPublishTime) {
        this.wpPublishTime = wpPublishTime;
    }

    public Long getWpPublishUser() {
        return wpPublishUser;
    }

    public void setWpPublishUser(Long wpPublishUser) {
        this.wpPublishUser = wpPublishUser;
    }

    public Integer getWpIndPublish() {
        return wpIndPublish;
    }

    public void setWpIndPublish(Integer wpIndPublish) {
        this.wpIndPublish = wpIndPublish;
    }

    public Date getWpUnpublishTime() {
        return wpUnpublishTime;
    }

    public void setWpUnpublishTime(Date wpUnpublishTime) {
        this.wpUnpublishTime = wpUnpublishTime;
    }

    public String getWpUnpublishMotive() {
        return wpUnpublishMotive;
    }

    public void setWpUnpublishMotive(String wpUnpublishMotive) {
        this.wpUnpublishMotive = wpUnpublishMotive;
    }

    public Integer getWpIndFiling() {
        return wpIndFiling;
    }

    public void setWpIndFiling(Integer wpIndFiling) {
        this.wpIndFiling = wpIndFiling;
    }

    public Integer getWpIndShowAuctionSteps() {
        return wpIndShowAuctionSteps;
    }

    public void setWpIndShowAuctionSteps(Integer wpIndShowAuctionSteps) {
        this.wpIndShowAuctionSteps = wpIndShowAuctionSteps;
    }

    public Integer getWpIndAuctionParticipation() {
        return wpIndAuctionParticipation;
    }

    public void setWpIndAuctionParticipation(Integer wpIndAuctionParticipation) {
        this.wpIndAuctionParticipation = wpIndAuctionParticipation;
    }

    public Integer getWpIndNoneApplication() {
        return wpIndNoneApplication;
    }

    public void setWpIndNoneApplication(Integer wpIndNoneApplication) {
        this.wpIndNoneApplication = wpIndNoneApplication;
    }

    public Integer getWpIndCancelAccreditation() {
        return wpIndCancelAccreditation;
    }

    public void setWpIndCancelAccreditation(Integer wpIndCancelAccreditation) {
        this.wpIndCancelAccreditation = wpIndCancelAccreditation;
    }

    public Short getWpPlace() {
        return wpPlace;
    }

    public void setWpPlace(Short wpPlace) {
        this.wpPlace = wpPlace;
    }

    public Integer getWpIndCombAuction() {
        return wpIndCombAuction;
    }

    public void setWpIndCombAuction(Integer wpIndCombAuction) {
        this.wpIndCombAuction = wpIndCombAuction;
    }

    public Integer getWpIndShowInNews() {
        return wpIndShowInNews;
    }

    public void setWpIndShowInNews(Integer wpIndShowInNews) {
        this.wpIndShowInNews = wpIndShowInNews;
    }

    public BigDecimal getCostBValue() {
        return costBValue;
    }

    public void setCostBValue(BigDecimal costBValue) {
        this.costBValue = costBValue;
    }

    public String getCurNameB() {
        return curNameB;
    }

    public void setCurNameB(String curNameB) {
        this.curNameB = curNameB;
    }

    public BigDecimal getCostEValue() {
        return costEValue;
    }

    public void setCostEValue(BigDecimal costEValue) {
        this.costEValue = costEValue;
    }

    public String getCurNameE() {
        return curNameE;
    }

    public void setCurNameE(String curNameE) {
        this.curNameE = curNameE;
    }

    public BigDecimal getValCostRubMin() {
        return valCostRubMin;
    }

    public void setValCostRubMin(BigDecimal valCostRubMin) {
        this.valCostRubMin = valCostRubMin;
    }

    public BigDecimal getValCostRubMax() {
        return valCostRubMax;
    }

    public void setValCostRubMax(BigDecimal valCostRubMax) {
        this.valCostRubMax = valCostRubMax;
    }

    public String getValCostRubCurNameGen() {
        return valCostRubCurNameGen;
    }

    public void setValCostRubCurNameGen(String valCostRubCurNameGen) {
        this.valCostRubCurNameGen = valCostRubCurNameGen;
    }

    public BigDecimal getValCostUsdMin() {
        return valCostUsdMin;
    }

    public void setValCostUsdMin(BigDecimal valCostUsdMin) {
        this.valCostUsdMin = valCostUsdMin;
    }

    public BigDecimal getValCostUsdMax() {
        return valCostUsdMax;
    }

    public void setValCostUsdMax(BigDecimal valCostUsdMax) {
        this.valCostUsdMax = valCostUsdMax;
    }

    public String getValCostUsdCurNameGen() {
        return valCostUsdCurNameGen;
    }

    public void setValCostUsdCurNameGen(String valCostUsdCurNameGen) {
        this.valCostUsdCurNameGen = valCostUsdCurNameGen;
    }

    public BigDecimal getValCostEuroMin() {
        return valCostEuroMin;
    }

    public void setValCostEuroMin(BigDecimal valCostEuroMin) {
        this.valCostEuroMin = valCostEuroMin;
    }

    public BigDecimal getValCostEuroMax() {
        return valCostEuroMax;
    }

    public void setValCostEuroMax(BigDecimal valCostEuroMax) {
        this.valCostEuroMax = valCostEuroMax;
    }

    public String getValCostEuroCurNameGen() {
        return valCostEuroCurNameGen;
    }

    public void setValCostEuroCurNameGen(String valCostEuroCurNameGen) {
        this.valCostEuroCurNameGen = valCostEuroCurNameGen;
    }

    public String getAuctionerName() {
        return auctionerName;
    }

    public void setAuctionerName(String auctionerName) {
        this.auctionerName = auctionerName;
    }

    public Long getAuctionerUnid() {
        return auctionerUnid;
    }

    public void setAuctionerUnid(Long auctionerUnid) {
        this.auctionerUnid = auctionerUnid;
    }

    public long getAuctionUnid() {
        return auctionUnid;
    }

    public void setAuctionUnid(long auctionUnid) {
        this.auctionUnid = auctionUnid;
    }

    public Date getAuctionRecepDateB() {
        return auctionRecepDateB;
    }

    public void setAuctionRecepDateB(Date auctionRecepDateB) {
        this.auctionRecepDateB = auctionRecepDateB;
    }

    public Date getAuctionRecepDateE() {
        return auctionRecepDateE;
    }

    public void setAuctionRecepDateE(Date auctionRecepDateE) {
        this.auctionRecepDateE = auctionRecepDateE;
    }

    public Date getAuctionDatePlan() {
        return auctionDatePlan;
    }

    public void setAuctionDatePlan(Date auctionDatePlan) {
        this.auctionDatePlan = auctionDatePlan;
    }

    public Date getAuctionDateFact() {
        return auctionDateFact;
    }

    public void setAuctionDateFact(Date auctionDateFact) {
        this.auctionDateFact = auctionDateFact;
    }

    public Date getAuctionDepDateE() {
        return auctionDepDateE;
    }

    public void setAuctionDepDateE(Date auctionDepDateE) {
        this.auctionDepDateE = auctionDepDateE;
    }

    public Date getAuctionPublicDate() {
        return auctionPublicDate;
    }

    public void setAuctionPublicDate(Date auctionPublicDate) {
        this.auctionPublicDate = auctionPublicDate;
    }

    public String getAuctionPresentation() {
        return auctionPresentation;
    }

    public void setAuctionPresentation(String auctionPresentation) {
        this.auctionPresentation = auctionPresentation;
    }

    public Short getAuctionIndDown() {
        return auctionIndDown;
    }

    public void setAuctionIndDown(Short auctionIndDown) {
        this.auctionIndDown = auctionIndDown;
    }

    public Short getAuctionEndTerm() {
        return auctionEndTerm;
    }

    public void setAuctionEndTerm(Short auctionEndTerm) {
        this.auctionEndTerm = auctionEndTerm;
    }

    public Integer getAuctionEndTime() {
        return auctionEndTime;
    }

    public void setAuctionEndTime(Integer auctionEndTime) {
        this.auctionEndTime = auctionEndTime;
    }

    public Date getAuctionDateE() {
        return auctionDateE;
    }

    public void setAuctionDateE(Date auctionDateE) {
        this.auctionDateE = auctionDateE;
    }

    public Date getLotAuctionDateEFact() {
        return lotAuctionDateEFact;
    }

    public void setLotAuctionDateEFact(Date lotAuctionDateEFact) {
        this.lotAuctionDateEFact = lotAuctionDateEFact;
    }

    public Long getWbUnid() {
        return wbUnid;
    }

    public void setWbUnid(Long wbUnid) {
        this.wbUnid = wbUnid;
    }

    public String getAuctionRegNum() {
        return auctionRegNum;
    }

    public void setAuctionRegNum(String auctionRegNum) {
        this.auctionRegNum = auctionRegNum;
    }

    public Date getAuctionRegDate() {
        return auctionRegDate;
    }

    public void setAuctionRegDate(Date auctionRegDate) {
        this.auctionRegDate = auctionRegDate;
    }

    public Long getDpUnid() {
        return dpUnid;
    }

    public void setDpUnid(Long dpUnid) {
        this.dpUnid = dpUnid;
    }

    public Short getAuctionTradeForm() {
        return auctionTradeForm;
    }

    public void setAuctionTradeForm(Short auctionTradeForm) {
        this.auctionTradeForm = auctionTradeForm;
    }

    public Short getAuctionIndSign() {
        return auctionIndSign;
    }

    public void setAuctionIndSign(Short auctionIndSign) {
        this.auctionIndSign = auctionIndSign;
    }

    public Short getAuctionIndBid() {
        return auctionIndBid;
    }

    public void setAuctionIndBid(Short auctionIndBid) {
        this.auctionIndBid = auctionIndBid;
    }

    public Long getCurUnid() {
        return curUnid;
    }

    public void setCurUnid(Long curUnid) {
        this.curUnid = curUnid;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public long getTypeAuctionUnid() {
        return typeAuctionUnid;
    }

    public void setTypeAuctionUnid(long typeAuctionUnid) {
        this.typeAuctionUnid = typeAuctionUnid;
    }

    public String getTypeAuctionName() {
        return typeAuctionName;
    }

    public void setTypeAuctionName(String typeAuctionName) {
        this.typeAuctionName = typeAuctionName;
    }

    public short getTypeAuctionCode() {
        return typeAuctionCode;
    }

    public void setTypeAuctionCode(short typeAuctionCode) {
        this.typeAuctionCode = typeAuctionCode;
    }

    public long getDoInstanceUnid() {
        return doInstanceUnid;
    }

    public void setDoInstanceUnid(long doInstanceUnid) {
        this.doInstanceUnid = doInstanceUnid;
    }

    public long getDoEntityUnid() {
        return doEntityUnid;
    }

    public void setDoEntityUnid(long doEntityUnid) {
        this.doEntityUnid = doEntityUnid;
    }

    public String getDoEntityName() {
        return doEntityName;
    }

    public void setDoEntityName(String doEntityName) {
        this.doEntityName = doEntityName;
    }

    public long getDoStateUnid() {
        return doStateUnid;
    }

    public void setDoStateUnid(long doStateUnid) {
        this.doStateUnid = doStateUnid;
    }

    public long getDoTypeStateUnid() {
        return doTypeStateUnid;
    }

    public void setDoTypeStateUnid(long doTypeStateUnid) {
        this.doTypeStateUnid = doTypeStateUnid;
    }

    public String getDoTypeStateName() {
        return doTypeStateName;
    }

    public void setDoTypeStateName(String doTypeStateName) {
        this.doTypeStateName = doTypeStateName;
    }

    public String getSgCategory() {
        return sgCategory;
    }

    public void setSgCategory(String sgCategory) {
        this.sgCategory = sgCategory;
    }

    public Long getSgUnid() {
        return sgUnid;
    }

    public void setSgUnid(Long sgUnid) {
        this.sgUnid = sgUnid;
    }

    public Integer getSgIndDigitalSignature() {
        return sgIndDigitalSignature;
    }

    public void setSgIndDigitalSignature(Integer sgIndDigitalSignature) {
        this.sgIndDigitalSignature = sgIndDigitalSignature;
    }

    public Integer getSgIndNoneApplication() {
        return sgIndNoneApplication;
    }

    public void setSgIndNoneApplication(Integer sgIndNoneApplication) {
        this.sgIndNoneApplication = sgIndNoneApplication;
    }

    public Integer getSgIndBid() {
        return sgIndBid;
    }

    public void setSgIndBid(Integer sgIndBid) {
        this.sgIndBid = sgIndBid;
    }

    public Integer getSgIndAccreditation() {
        return sgIndAccreditation;
    }

    public void setSgIndAccreditation(Integer sgIndAccreditation) {
        this.sgIndAccreditation = sgIndAccreditation;
    }

    public Integer getWbIndOperator() {
        return wbIndOperator;
    }

    public void setWbIndOperator(Integer wbIndOperator) {
        this.wbIndOperator = wbIndOperator;
    }

    public String getWebBidderPartyName() {
        return webBidderPartyName;
    }

    public void setWebBidderPartyName(String webBidderPartyName) {
        this.webBidderPartyName = webBidderPartyName;
    }

    public String getWebBidderPartyAddrFact() {
        return webBidderPartyAddrFact;
    }

    public void setWebBidderPartyAddrFact(String webBidderPartyAddrFact) {
        this.webBidderPartyAddrFact = webBidderPartyAddrFact;
    }

    public String getWebBidderPartyAddrLegal() {
        return webBidderPartyAddrLegal;
    }

    public void setWebBidderPartyAddrLegal(String webBidderPartyAddrLegal) {
        this.webBidderPartyAddrLegal = webBidderPartyAddrLegal;
    }

    public String getWebBidderPartyEmail() {
        return webBidderPartyEmail;
    }

    public void setWebBidderPartyEmail(String webBidderPartyEmail) {
        this.webBidderPartyEmail = webBidderPartyEmail;
    }

    public String getWebBidderPartyPhone() {
        return webBidderPartyPhone;
    }

    public void setWebBidderPartyPhone(String webBidderPartyPhone) {
        this.webBidderPartyPhone = webBidderPartyPhone;
    }

    public String getRubrName() {
        return rubrName;
    }

    public void setRubrName(String rubrName) {
        this.rubrName = rubrName;
    }

    public Integer getAppCount() {
        return appCount;
    }

    public void setAppCount(Integer appCount) {
        this.appCount = appCount;
    }

    public Integer getAppRsTotalCount() {
        return appRsTotalCount;
    }

    public void setAppRsTotalCount(Integer appRsTotalCount) {
        this.appRsTotalCount = appRsTotalCount;
    }

    public Integer getAppTotalCount() {
        return appTotalCount;
    }

    public void setAppTotalCount(Integer appTotalCount) {
        this.appTotalCount = appTotalCount;
    }

    public BigDecimal getLastStep() {
        return lastStep;
    }

    public void setLastStep(BigDecimal lastStep) {
        this.lastStep = lastStep;
    }

    public Date getLotChangePriceNextTime() {
        return lotChangePriceNextTime;
    }

    public void setLotChangePriceNextTime(Date lotChangePriceNextTime) {
        this.lotChangePriceNextTime = lotChangePriceNextTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getAuctionIndClose() {
        return auctionIndClose;
    }

    public void setAuctionIndClose(Integer auctionIndClose) {
        this.auctionIndClose = auctionIndClose;
    }

    public BigDecimal getLotStepProcent() {
        return lotStepProcent;
    }

    public void setLotStepProcent(BigDecimal lotStepProcent) {
        this.lotStepProcent = lotStepProcent;
    }

    public Short getLotDealState() {
        return lotDealState;
    }

    public void setLotDealState(Short lotDealState) {
        this.lotDealState = lotDealState;
    }

    public Date getLotDealDate() {
        return lotDealDate;
    }

    public void setLotDealDate(Date lotDealDate) {
        this.lotDealDate = lotDealDate;
    }

    public BigDecimal getLotDealSum() {
        return lotDealSum;
    }

    public void setLotDealSum(BigDecimal lotDealSum) {
        this.lotDealSum = lotDealSum;
    }

    public String getLotDealCancelCause() {
        return lotDealCancelCause;
    }

    public void setLotDealCancelCause(String lotDealCancelCause) {
        this.lotDealCancelCause = lotDealCancelCause;
    }

    public String getLotDealPartyInfo() {
        return lotDealPartyInfo;
    }

    public void setLotDealPartyInfo(String lotDealPartyInfo) {
        this.lotDealPartyInfo = lotDealPartyInfo;
    }

    public Long getPartyUnid() {
        return partyUnid;
    }

    public void setPartyUnid(Long partyUnid) {
        this.partyUnid = partyUnid;
    }

    public Long getSrfUnid() {
        return srfUnid;
    }

    public void setSrfUnid(Long srfUnid) {
        this.srfUnid = srfUnid;
    }

    public String getSrfName() {
        return srfName;
    }

    public void setSrfName(String srfName) {
        this.srfName = srfName;
    }

    public String getSrfCode() {
        return srfCode;
    }

    public void setSrfCode(String srfCode) {
        this.srfCode = srfCode;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Short getLotIndPledge() {
        return lotIndPledge;
    }

    public void setLotIndPledge(Short lotIndPledge) {
        this.lotIndPledge = lotIndPledge;
    }

    public Short getLotIndRightEnsure() {
        return lotIndRightEnsure;
    }

    public void setLotIndRightEnsure(Short lotIndRightEnsure) {
        this.lotIndRightEnsure = lotIndRightEnsure;
    }
}
