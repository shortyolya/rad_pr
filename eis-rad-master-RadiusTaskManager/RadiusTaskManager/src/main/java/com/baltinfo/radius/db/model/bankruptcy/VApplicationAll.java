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
@Table(name = "APPLICATION_ALL", catalog = "", schema = "WEB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VApplicationAll.findAll", query = "SELECT a FROM VApplicationAll a")})
public class VApplicationAll implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "APPLICAT_UNID")
    private long applicatUnid;
    @Basic(optional = false)
    @Column(name = "TYPE_APPLICAT_UNID")
    private long typeApplicatUnid;
    @Column(name = "APPLICAT_NUMBER")
    private String applicatNumber;
    @Column(name = "APPLICAT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicatDate;
    @Column(name = "APPLICAT_AMOUNT")
    private BigDecimal applicatAmount;
    @Column(name = "APPLICAT_IND_PERMIT")
    private Integer applicatIndPermit;
    @Column(name = "APPLICAT_CARD_NUMBER")
    private String applicatCardNumber;
    @Column(name = "APPLICAT_IND_WINNER")
    private Integer applicatIndWinner;
    @Column(name = "APPLICAT_IND_ADVICE")
    private Integer applicatIndAdvice;
    @Column(name = "APPLICAT_IND_PAY_DEP")
    private Integer applicatIndPayDep;
    @Column(name = "APPLICAT_REFUSAL_FOUND")
    private String applicatRefusalFound;
    @Column(name = "APPLICAT_AMOUNT_RUB")
    private BigDecimal applicatAmountRub;
    @Column(name = "APPLICAT_IND_RECALL")
    private Integer applicatIndRecall;
    @Column(name = "APPLICAT_DATE_CANCEL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicatDateCancel;
    @Column(name = "APPLICAT_DEPOSIT_NUMBER")
    private String applicatDepositNumber;
    @Column(name = "APPLICAT_DEPOSIT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicatDepositDate;
    @Column(name = "APPLICAT_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicatTime;
    @Column(name = "APPLICAT_IND_AUCTION")
    private Integer applicatIndAuction;
    @Column(name = "APPLICAT_TERM_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicatTermDate;
    @Column(name = "APPLICAT_AMOUNT_BID")
    private BigDecimal applicatAmountBid;
    @Column(name = "CUR_UNID")
    private Long curUnid;
    @Column(name = "TYPE_APPLICAT_NAME")
    private String typeApplicatName;
    @Column(name = "TYPE_STATE_NAME")
    private String typeStateName;
    @Column(name = "TYPE_STATE_NAME_ORG")
    private String typeStateNameOrg;
    @Basic(optional = false)
    @Column(name = "TYPE_STATE_UNID")
    private long typeStateUnid;
    @Column(name = "STATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stateDate;
    @Basic(optional = false)
    @Column(name = "INSTANCE_UNID")
    private long instanceUnid;
    @Basic(optional = false)
    @Column(name = "DO_UNID")
    private long doUnid;
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
    @Basic(optional = false)
    @Column(name = "LOT_UNID")
    private long lotUnid;
    @Column(name = "LOT_NOTICE_NUM")
    private String lotNoticeNum;
    @Column(name = "DO_AUCTION_THEME")
    private String doAuctionTheme;
    @Column(name = "DECLARANT_UNID")
    private Long declarantUnid;
    @Column(name = "PERSON_UNID")
    private Long personUnid;
    @Column(name = "PERSON_FIO")
    private String personFio;
    @Column(name = "WB_UNID")
    private Long wbUnid;
    @Basic(optional = false)
    @Column(name = "TYPE_AUCTION_UNID")
    private long typeAuctionUnid;
    @Column(name = "TYPE_AUCTION_CODE")
    private Long typeAuctionCode;
    @Column(name = "AUCTION_DATE_PLAN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionDatePlan;
    @Column(name = "AUCTION_DATE_E")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionDateE;
    @Column(name = "AO_AMOUNT")
    private String aoAmmount;
    @Column(name = "AO_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionOfferAoDate;
    @Column(name = "DEPOSIT_PAY_SUM")
    private BigDecimal depositPaySum;
    @Column(name = "WP_IND_PUBLISH")
    private Integer wpIndPublish;
    @Column(name = "APPLICAT_INTERESTS_INFO")
    private String applicatInterestsInfo;
    @Column(name = "APPLICAT_APP_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicatAppTime;
    @Column(name = "APPLICAT_COMPLIANCE")
    private Integer applicatCompliance;
    @Column(name = "APPLICAT_CONCURS_OFFERS")
    private String applicatConcursOffers;
    @Column(name = "APPLICAT_APP_COMPLIANCE")
    private Integer applicatAppCompliance;
    @Column(name = "APPLICAT_DOC_COMPLIANCE")
    private Integer applicatDocCompliance;
    @Column(name = "APPLICAT_DOC_NOT_COMPLIANCE")
    private String applicatDocNotCompliance;
    @Column(name = "APPLICAT_PAY_DOC_NUM")
    private String applicatPayDocNum;
    @Column(name = "APPLICAT_PAY_DOC_DATE")
    @Temporal(TemporalType.DATE)
    private Date applicatPayDocDate;
    @Column(name = "AUCTION_UNID")
    private Long auctionUnid;
    @Column(name = "APPLICAT_WINNER_FOUND")
    private String applicatWinnerFound;
    @Column(name = "RED_SCHED_UNID")
    private Long redSchedUnid;
    @Column(name = "ORGANIZER_UNID")
    private Integer organizerUnid;

    public VApplicationAll() {
    }

    public long getApplicatUnid() {
        return applicatUnid;
    }

    public void setApplicatUnid(long applicatUnid) {
        this.applicatUnid = applicatUnid;
    }

    public long getTypeApplicatUnid() {
        return typeApplicatUnid;
    }

    public void setTypeApplicatUnid(long typeApplicatUnid) {
        this.typeApplicatUnid = typeApplicatUnid;
    }

    public String getApplicatNumber() {
        return applicatNumber;
    }

    public void setApplicatNumber(String applicatNumber) {
        this.applicatNumber = applicatNumber;
    }

    public Date getApplicatDate() {
        return applicatDate;
    }

    public void setApplicatDate(Date applicatDate) {
        this.applicatDate = applicatDate;
    }

    public BigDecimal getApplicatAmount() {
        return applicatAmount;
    }

    public void setApplicatAmount(BigDecimal applicatAmount) {
        this.applicatAmount = applicatAmount;
    }

    public Integer getApplicatIndPermit() {
        return applicatIndPermit;
    }

    public void setApplicatIndPermit(Integer applicatIndPermit) {
        this.applicatIndPermit = applicatIndPermit;
    }

    public String getApplicatCardNumber() {
        return applicatCardNumber;
    }

    public void setApplicatCardNumber(String applicatCardNumber) {
        this.applicatCardNumber = applicatCardNumber;
    }

    public Integer getApplicatIndWinner() {
        return applicatIndWinner;
    }

    public void setApplicatIndWinner(Integer applicatIndWinner) {
        this.applicatIndWinner = applicatIndWinner;
    }

    public Integer getApplicatIndAdvice() {
        return applicatIndAdvice;
    }

    public void setApplicatIndAdvice(Integer applicatIndAdvice) {
        this.applicatIndAdvice = applicatIndAdvice;
    }

    public Integer getApplicatIndPayDep() {
        return applicatIndPayDep;
    }

    public void setApplicatIndPayDep(Integer applicatIndPayDep) {
        this.applicatIndPayDep = applicatIndPayDep;
    }

    public String getApplicatRefusalFound() {
        return applicatRefusalFound;
    }

    public void setApplicatRefusalFound(String applicatRefusalFound) {
        this.applicatRefusalFound = applicatRefusalFound;
    }

    public BigDecimal getApplicatAmountRub() {
        return applicatAmountRub;
    }

    public void setApplicatAmountRub(BigDecimal applicatAmountRub) {
        this.applicatAmountRub = applicatAmountRub;
    }

    public Integer getApplicatIndRecall() {
        return applicatIndRecall;
    }

    public void setApplicatIndRecall(Integer applicatIndRecall) {
        this.applicatIndRecall = applicatIndRecall;
    }

    public Date getApplicatDateCancel() {
        return applicatDateCancel;
    }

    public void setApplicatDateCancel(Date applicatDateCancel) {
        this.applicatDateCancel = applicatDateCancel;
    }

    public String getApplicatDepositNumber() {
        return applicatDepositNumber;
    }

    public void setApplicatDepositNumber(String applicatDepositNumber) {
        this.applicatDepositNumber = applicatDepositNumber;
    }

    public Date getApplicatDepositDate() {
        return applicatDepositDate;
    }

    public void setApplicatDepositDate(Date applicatDepositDate) {
        this.applicatDepositDate = applicatDepositDate;
    }

    public Date getApplicatTime() {
        return applicatTime;
    }

    public void setApplicatTime(Date applicatTime) {
        this.applicatTime = applicatTime;
    }

    public Integer getApplicatIndAuction() {
        return applicatIndAuction;
    }

    public void setApplicatIndAuction(Integer applicatIndAuction) {
        this.applicatIndAuction = applicatIndAuction;
    }

    public Date getApplicatTermDate() {
        return applicatTermDate;
    }

    public void setApplicatTermDate(Date applicatTermDate) {
        this.applicatTermDate = applicatTermDate;
    }

    public Long getCurUnid() {
        return curUnid;
    }

    public void setCurUnid(Long curUnid) {
        this.curUnid = curUnid;
    }

    public BigDecimal getApplicatAmountBid() {
        return applicatAmountBid;
    }

    public void setApplicatAmountBid(BigDecimal applicatAmountBid) {
        this.applicatAmountBid = applicatAmountBid;
    }

    public String getTypeApplicatName() {
        return typeApplicatName;
    }

    public void setTypeApplicatName(String typeApplicatName) {
        this.typeApplicatName = typeApplicatName;
    }

    public String getTypeStateName() {
        return typeStateName;
    }

    public void setTypeStateName(String typeStateName) {
        this.typeStateName = typeStateName;
    }

    public long getTypeStateUnid() {
        return typeStateUnid;
    }

    public void setTypeStateUnid(long typeStateUnid) {
        this.typeStateUnid = typeStateUnid;
    }

    public String getTypeStateNameOrg() {
        return typeStateNameOrg;
    }

    public void setTypeStateNameOrg(String typeStateNameOrg) {
        this.typeStateNameOrg = typeStateNameOrg;
    }

    public Date getStateDate() {
        return stateDate;
    }

    public void setStateDate(Date stateDate) {
        this.stateDate = stateDate;
    }

    public long getInstanceUnid() {
        return instanceUnid;
    }

    public void setInstanceUnid(long instanceUnid) {
        this.instanceUnid = instanceUnid;
    }

    public long getDoUnid() {
        return doUnid;
    }

    public void setDoUnid(long doUnid) {
        this.doUnid = doUnid;
    }

    public long getLotUnid() {
        return lotUnid;
    }

    public void setLotUnid(long lotUnid) {
        this.lotUnid = lotUnid;
    }

    public String getLotNoticeNum() {
        return lotNoticeNum;
    }

    public void setLotNoticeNum(String lotNoticeNum) {
        this.lotNoticeNum = lotNoticeNum;
    }

    public String getDoAuctionTheme() {
        return doAuctionTheme;
    }

    public void setDoAuctionTheme(String doAuctionTheme) {
        this.doAuctionTheme = doAuctionTheme;
    }

    public Long getDeclarantUnid() {
        return declarantUnid;
    }

    public void setDeclarantUnid(Long declarantUnid) {
        this.declarantUnid = declarantUnid;
    }

    public Long getPersonUnid() {
        return personUnid;
    }

    public void setPersonUnid(Long personUnid) {
        this.personUnid = personUnid;
    }

    public String getPersonFio() {
        return personFio;
    }

    public void setPersonFio(String personFio) {
        this.personFio = personFio;
    }

    public Long getWbUnid() {
        return wbUnid;
    }

    public void setWbUnid(Long wbUnid) {
        this.wbUnid = wbUnid;
    }

    public long getTypeAuctionUnid() {
        return typeAuctionUnid;
    }

    public void setTypeAuctionUnid(long typeAuctionUnid) {
        this.typeAuctionUnid = typeAuctionUnid;
    }

    public Long getTypeAuctionCode() {
        return typeAuctionCode;
    }

    public void setTypeAuctionCode(Long typeAuctionCode) {
        this.typeAuctionCode = typeAuctionCode;
    }

    public Date getAuctionDatePlan() {
        return auctionDatePlan;
    }

    public void setAuctionDatePlan(Date auctionDatePlan) {
        this.auctionDatePlan = auctionDatePlan;
    }

    public Date getAuctionDateE() {
        return auctionDateE;
    }

    public void setAuctionDateE(Date auctionDateE) {
        this.auctionDateE = auctionDateE;
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

    public BigDecimal getDepositPaySum() {
        return depositPaySum;
    }

    public void setDepositPaySum(BigDecimal depositPaySum) {
        this.depositPaySum = depositPaySum;
    }

    public Integer getWpIndPublish() {
        return wpIndPublish;
    }

    public void setWpIndPublish(Integer wpIndPublish) {
        this.wpIndPublish = wpIndPublish;
    }

    public String getApplicatInterestsInfo() {
        return applicatInterestsInfo;
    }

    public void setApplicatInterestsInfo(String applicatInterestsInfo) {
        this.applicatInterestsInfo = applicatInterestsInfo;
    }

    public Date getApplicatAppTime() {
        return applicatAppTime;
    }

    public void setApplicatAppTime(Date applicatAppTime) {
        this.applicatAppTime = applicatAppTime;
    }

    public Integer getApplicatCompliance() {
        return applicatCompliance;
    }

    public void setApplicatCompliance(Integer applicatCompliance) {
        this.applicatCompliance = applicatCompliance;
    }

    public String getApplicatConcursOffers() {
        return applicatConcursOffers;
    }

    public void setApplicatConcursOffers(String applicatConcursOffers) {
        this.applicatConcursOffers = applicatConcursOffers;
    }

    public Integer getApplicatAppCompliance() {
        return applicatAppCompliance;
    }

    public void setApplicatAppCompliance(Integer applicatAppCompliance) {
        this.applicatAppCompliance = applicatAppCompliance;
    }

    public Integer getApplicatDocCompliance() {
        return applicatDocCompliance;
    }

    public void setApplicatDocCompliance(Integer applicatDocCompliance) {
        this.applicatDocCompliance = applicatDocCompliance;
    }

    public String getApplicatDocNotCompliance() {
        return applicatDocNotCompliance;
    }

    public void setApplicatDocNotCompliance(String applicatDocNotCompliance) {
        this.applicatDocNotCompliance = applicatDocNotCompliance;
    }

    public String getApplicatPayDocNum() {
        return applicatPayDocNum;
    }

    public void setApplicatPayDocNum(String applicatPayDocNum) {
        this.applicatPayDocNum = applicatPayDocNum;
    }

    public Date getApplicatPayDocDate() {
        return applicatPayDocDate;
    }

    public void setApplicatPayDocDate(Date applicatPayDocDate) {
        this.applicatPayDocDate = applicatPayDocDate;
    }

    public Long getAuctionUnid() {
        return auctionUnid;
    }

    public void setAuctionUnid(Long auctionUnid) {
        this.auctionUnid = auctionUnid;
    }

    public String getApplicatWinnerFound() {
        return applicatWinnerFound;
    }

    public void setApplicatWinnerFound(String applicatWinnerFound) {
        this.applicatWinnerFound = applicatWinnerFound;
    }

    public Long getRedSchedUnid() {
        return redSchedUnid;
    }

    public void setRedSchedUnid(Long redSchedUnid) {
        this.redSchedUnid = redSchedUnid;
    }

    public Integer getOrganizerUnid() {
        return organizerUnid;
    }

    public void setOrganizerUnid(Integer organizerUnid) {
        this.organizerUnid = organizerUnid;
    }

}
