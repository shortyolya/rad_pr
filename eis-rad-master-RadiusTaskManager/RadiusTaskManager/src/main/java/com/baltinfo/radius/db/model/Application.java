package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
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
 *
 * @author lia
 */
@Entity
@Table(name = "application", catalog = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"applicat_unid"})})
@SequenceGenerator(name = "seq_application", sequenceName = "seq_application", allocationSize = 1)
@XmlRootElement
@NamedEntityGraph(name = "graph.Application.tsUnid", attributeNodes = @NamedAttributeNode("tsUnid"))
@NamedQueries({
    @NamedQuery(name = "Application.findAll", query = "SELECT a FROM Application a where a.indActual = 1"),
    @NamedQuery(name = "Application.countAll", query = "SELECT count(a) FROM Application a"),
    @NamedQuery(name = "Application.findByLotUnid", query = "SELECT a FROM Application a WHERE a.lotUnid = :lotUnid")
})
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_application")
    @Basic(optional = false)
    @Column(name = "applicat_unid")
    private Long applicatUnid;
    @Column(name = "entity_unid")
    private Long entityUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
    @Basic(optional = false)
    @Column(name = "lot_unid")
    private Long lotUnid;
    @Basic(optional = false)
    @Column(name = "ts_unid")
    private Long tsUnid;
    @Column(name = "ind_actual")
    private Long indActual;
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
    @Column(name = "applicat_app_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicatAppTime;
    @Column(name = "applicat_number")
    private String applicatNumber;
    @Column(name = "applicat_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicatDate;
    @Column(name = "applicat_amount")
    private BigDecimal applicatAmount;
    @Column(name = "applicat_max_pers_ext")
    private Integer applicatMaxPersExt;
    @Column(name = "applicat_max_buying_price")
    private Long applicatMaxBuyingPrice;
    @Column(name = "applicat_review_results")
    private Integer applicatReviewResults;
    @Column(name = "applicat_refusal_found")
    private String applicatRefusalFound;
    @Column(name = "applicat_card_number")
    private String applicatCardNumber;
    @Column(name = "applicat_ind_winner")
    private Integer applicatIndWinner;
    @Column(name = "applicat_ind_pay_dep")
    private Integer applicatIndPayDep;
    @Column(name = "applicat_date_refuse")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicatDateRefuse;
    @Column(name = "applicat_deposit_number")
    private String applicatDepositNumber;
    @Column(name = "applicat_deposit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicatDepositDate;
    @Column(name = "applicat_appl_form")
    private Integer applicatApplForm;
    @Column(name = "applicat_ind_arendator")
    private Long applicatIndArendator;
    @Column(name = "applicat_ind_bid")
    private Long applicatIndBid;
    @Column(name = "applicat_ind_bid_user")
    private Long applicatIndBidUser;
    @Column(name = "applicat_pay_doc_num")
    private String applicatPayDocNum;
    @Column(name = "applicat_pay_doc_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicatPayDocDate;
    @Column(name = "applicat_order_num")
    private Integer applicatOrderNum;
    @Column(name = "tpa_unid")
    private Long tpaUnid;
    @Column(name = "applicat_withdraw_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicatWithdrawTime;
    @Column(name = "applicat_withdraw_reason")
    private String applicatWithdrawReason;
    @Column(name = "applicat_ind_transfer")
    private Short applicatIndTransfer;
    @Column(name = "applicat_transfer_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicatTransferDate;
    @Column(name = "applicat_ind_doc_compliance")
    private Integer applicatIndDocCompliance;
    @Column(name = "red_sched_unid")
    private Long redSchedUnid;

    public Application() {
    }

    public Application(Long applicatUnid) {
        this.applicatUnid = applicatUnid;
    }

    public Application(Long applicatUnid, Long lotUnid) {
        this.applicatUnid = applicatUnid;
        this.lotUnid = lotUnid;
    }

    public Long getApplicatUnid() {
        return applicatUnid;
    }

    public void setApplicatUnid(Long applicatUnid) {
        this.applicatUnid = applicatUnid;
    }

    public Long getEntityUnid() {
        return entityUnid;
    }

    public void setEntityUnid(Long entityUnid) {
        this.entityUnid = entityUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    public Long getLotUnid() {
        return lotUnid;
    }

    public void setLotUnid(Long lotUnid) {
        this.lotUnid = lotUnid;
    }

    public Long getTsUnid() {
        return tsUnid;
    }

    public void setTsUnid(Long tsUnid) {
        this.tsUnid = tsUnid;
    }

    public Long getIndActual() {
        return indActual;
    }

    public void setIndActual(Long indActual) {
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

    public Date getApplicatAppTime() {
        return applicatAppTime;
    }

    public void setApplicatAppTime(Date applicatAppTime) {
        this.applicatAppTime = applicatAppTime;
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

    public Integer getApplicatMaxPersExt() {
        return applicatMaxPersExt;
    }

    public void setApplicatMaxPersExt(Integer applicatMaxPersExt) {
        this.applicatMaxPersExt = applicatMaxPersExt;
    }

    public Long getApplicatMaxBuyingPrice() {
        return applicatMaxBuyingPrice;
    }

    public void setApplicatMaxBuyingPrice(Long applicatMaxBuyingPrice) {
        this.applicatMaxBuyingPrice = applicatMaxBuyingPrice;
    }

    public Integer getApplicatReviewResults() {
        return applicatReviewResults;
    }

    public void setApplicatReviewResults(Integer applicatReviewResults) {
        this.applicatReviewResults = applicatReviewResults;
    }

    public String getApplicatRefusalFound() {
        return applicatRefusalFound;
    }

    public void setApplicatRefusalFound(String applicatRefusalFound) {
        this.applicatRefusalFound = applicatRefusalFound;
    }

    public String getApplicatCardNumber() {
        return applicatCardNumber;
    }

    public void setApplicatCardNumber(String applicatCardNumber) {
        this.applicatCardNumber = applicatCardNumber;
    }

    public boolean getApplicatIndWinner() {
        return applicatIndWinner != null && applicatIndWinner == 1;
    }

    public void setApplicatIndWinner(boolean applicatIndWinner) {
        this.applicatIndWinner = applicatIndWinner == true ? 1 : 0;
    }

    public boolean getApplicatIndPayDep() {
        return applicatIndPayDep != null && applicatIndPayDep == 1;
    }

    public void setApplicatIndPayDep(boolean applicatIndPayDep) {
        this.applicatIndPayDep = applicatIndPayDep == true ? 1 : 0;
    }

    public Date getApplicatDateRefuse() {
        return applicatDateRefuse;
    }

    public void setApplicatDateRefuse(Date applicatDateRefuse) {
        this.applicatDateRefuse = applicatDateRefuse;
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

    public Integer getApplicatApplForm() {
        return applicatApplForm;
    }

    public void setApplicatApplForm(Integer applicatApplForm) {
        this.applicatApplForm = applicatApplForm;
    }

    public Long getApplicatIndArendator() {
        return applicatIndArendator;
    }

    public void setApplicatIndArendator(Long applicatIndArendator) {
        this.applicatIndArendator = applicatIndArendator;
    }

    public Long getApplicatIndBid() {
        return applicatIndBid;
    }

    public void setApplicatIndBid(Long applicatIndBid) {
        this.applicatIndBid = applicatIndBid;
    }

    public Long getApplicatIndBidUser() {
        return applicatIndBidUser;
    }

    public void setApplicatIndBidUser(Long applicatIndBidUser) {
        this.applicatIndBidUser = applicatIndBidUser;
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

    public Integer getApplicatOrderNum() {
        return applicatOrderNum;
    }

    public void setApplicatOrderNum(Integer applicatOrderNum) {
        this.applicatOrderNum = applicatOrderNum;
    }

    public Long getTpaUnid() {
        return tpaUnid;
    }

    public void setTpaUnid(Long tpaUnid) {
        this.tpaUnid = tpaUnid;
    }

    public Date getApplicatWithdrawTime() {
        return applicatWithdrawTime;
    }

    public void setApplicatWithdrawTime(Date applicatWithdrawTime) {
        this.applicatWithdrawTime = applicatWithdrawTime;
    }

    public String getApplicatWithdrawReason() {
        return applicatWithdrawReason;
    }

    public void setApplicatWithdrawReason(String applicatWithdrawReason) {
        this.applicatWithdrawReason = applicatWithdrawReason;
    }

    public Date getApplicatTransferDate() {
        return applicatTransferDate;
    }

    public void setApplicatTransferDate(Date applicatTransferDate) {
        this.applicatTransferDate = applicatTransferDate;
    }
    
    public boolean getApplicatIndTransfer() {
        return applicatIndTransfer != null && applicatIndTransfer == 1;
    }

    public void setApplicatIndTransfer(boolean applicatIndTransfer) {
        this.applicatIndTransfer = applicatIndTransfer == true ? (short)1 : (short)0;
    }

    public boolean getApplicatIndDocCompliance() {
        return applicatIndDocCompliance != null && applicatIndDocCompliance == 1;
    }

    public void setApplicatIndDocCompliance(boolean applicatIndDocCompliance) {
        this.applicatIndDocCompliance = applicatIndDocCompliance == true ? 1 : 0;
    }

    public Long getRedSchedUnid() {
        return redSchedUnid;
    }

    public void setRedSchedUnid(Long redSchedUnid) {
        this.redSchedUnid = redSchedUnid;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.Application[ applicatUnid=" + applicatUnid + " ]";
    }

}
