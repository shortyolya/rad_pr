package com.baltinfo.radius.db.model.bankruptcy;

import com.baltinfo.radius.db.controller.exchange.EtpEntity;

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
import java.math.BigInteger;
import java.util.Date;

/**
 * @author sas
 */
@Entity
@Table(name = "AUCTION", catalog = "", schema = "WEB")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "VAuctionBkr.findAll", query = "SELECT v FROM VAuctionBkr v")})
public class VAuctionBkr implements Serializable, EtpEntity {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @Column(name = "AUCTION_UNID")
    private long auctionUnid;
    @Column(name = "WB_UNID")
    private Long wbUnid;
    @Column(name = "AUCTION_RECEP_DATE_B")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionRecepDateB;
    @Column(name = "AUCTION_RECEP_DATE_E")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionRecepDateE;
    @Column(name = "AUCTION_DEP_DATE_E")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionDepDateE;
    @Column(name = "AUCTION_DATE_PLAN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionDatePlan;
    @Column(name = "AUCTION_DATE_E")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionDateE;
    @Column(name = "AUCTION_REG_NUM")
    private String auctionRegNum;
    @Column(name = "AUCTION_REG_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionRegDate;
    @Basic(optional = false)
    @Column(name = "TYPE_AUCTION_UNID")
    private long typeAuctionUnid;
    @Column(name = "TYPE_AUCTION_NAME")
    private String typeAuctionName;
    @Basic(optional = false)
    @Column(name = "TYPE_AUCTION_CODE")
    private short typeAuctionCode;
    @Basic(optional = false)
    @Column(name = "TYPE_STATE_UNID")
    private long typeStateUnid;
    @Column(name = "TYPE_STATE_NAME")
    private String typeStateName;
    @Column(name = "STATE")
    private BigInteger state;
    @Column(name = "LOT_COUNT")
    private BigInteger lotCount;
    @Column(name = "LOT_PUBLISHED_COUNT")
    private BigInteger lotPublishedCount;
    @Column(name = "AUCTION_END_TERM")
    private Short auctionEndTerm;
    @Column(name = "AUCTION_END_TIME")
    private Integer auctionEndTime;
    @Column(name = "LOT_CANCELED_COUNT")
    private BigInteger lotCanceledCount;
    @Column(name = "AUCTION_TRADE_FORM")
    private Short auctionTradeForm;
    @Column(name = "AUCTION_IND_SIGN")
    private Short auctionIndSign;
    @Column(name = "AUCTION_IND_BID")
    private Short auctionIndBid;
    @Column(name = "AUCTION_IND_DOWN")
    private Short auctionIndDown;
    @Column(name = "AUCTION_CHANGE_PRICE_PERIOD")
    private Long auctionChangePricePeriod;
    @Column(name = "TCP_UNID")
    private Long tcpUnid;
    @Column(name = "AUCTION_IND_CLOSE")
    private BigInteger auctionIndClose;
    @Column(name = "SG_UNID")
    private Long sgUnid;
    @Column(name = "ORGANIZER_UNID")
    private BigInteger organizerUnid;
    @Column(name = "LANG")
    private String lang;
    @Column(name = "IND_NO_LOADED_FAIL_PROTOCOL")
    private BigInteger indNoLoadedFailProtocol;
    @Column(name = "AUCTION_CANCELLATION_CAUSE")
    private String auctionCancellationCause;
    @Column(name = "SAVE_AS_COUNT")
    private BigInteger saveAsCount;
    @Column(name = "DP_INTERFAX_MESS_NUM")
    private String dpInterfaxMessNum;

    public VAuctionBkr() {
    }

    public long getAuctionUnid() {
        return auctionUnid;
    }

    public void setAuctionUnid(long auctionUnid) {
        this.auctionUnid = auctionUnid;
    }

    public Long getWbUnid() {
        return wbUnid;
    }

    public void setWbUnid(Long wbUnid) {
        this.wbUnid = wbUnid;
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

    public Date getAuctionDepDateE() {
        return auctionDepDateE;
    }

    public void setAuctionDepDateE(Date auctionDepDateE) {
        this.auctionDepDateE = auctionDepDateE;
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

    public long getTypeStateUnid() {
        return typeStateUnid;
    }

    public void setTypeStateUnid(long typeStateUnid) {
        this.typeStateUnid = typeStateUnid;
    }

    public String getTypeStateName() {
        return typeStateName;
    }

    public void setTypeStateName(String typeStateName) {
        this.typeStateName = typeStateName;
    }

    public BigInteger getState() {
        return state;
    }

    public void setState(BigInteger state) {
        this.state = state;
    }

    public BigInteger getLotCount() {
        return lotCount;
    }

    public void setLotCount(BigInteger lotCount) {
        this.lotCount = lotCount;
    }

    public BigInteger getLotPublishedCount() {
        return lotPublishedCount;
    }

    public void setLotPublishedCount(BigInteger lotPublishedCount) {
        this.lotPublishedCount = lotPublishedCount;
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

    public BigInteger getLotCanceledCount() {
        return lotCanceledCount;
    }

    public void setLotCanceledCount(BigInteger lotCanceledCount) {
        this.lotCanceledCount = lotCanceledCount;
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

    public Short getAuctionIndDown() {
        return auctionIndDown;
    }

    public void setAuctionIndDown(Short auctionIndDown) {
        this.auctionIndDown = auctionIndDown;
    }

    public Long getAuctionChangePricePeriod() {
        return auctionChangePricePeriod;
    }

    public void setAuctionChangePricePeriod(Long auctionChangePricePeriod) {
        this.auctionChangePricePeriod = auctionChangePricePeriod;
    }

    public Long getTcpUnid() {
        return tcpUnid;
    }

    public void setTcpUnid(Long tcpUnid) {
        this.tcpUnid = tcpUnid;
    }

    public BigInteger getAuctionIndClose() {
        return auctionIndClose;
    }

    public void setAuctionIndClose(BigInteger auctionIndClose) {
        this.auctionIndClose = auctionIndClose;
    }

    public Long getSgUnid() {
        return sgUnid;
    }

    public void setSgUnid(Long sgUnid) {
        this.sgUnid = sgUnid;
    }

    public BigInteger getOrganizerUnid() {
        return organizerUnid;
    }

    public void setOrganizerUnid(BigInteger organizerUnid) {
        this.organizerUnid = organizerUnid;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public BigInteger getIndNoLoadedFailProtocol() {
        return indNoLoadedFailProtocol;
    }

    public void setIndNoLoadedFailProtocol(BigInteger indNoLoadedFailProtocol) {
        this.indNoLoadedFailProtocol = indNoLoadedFailProtocol;
    }

    public String getAuctionCancellationCause() {
        return auctionCancellationCause;
    }

    public void setAuctionCancellationCause(String auctionCancellationCause) {
        this.auctionCancellationCause = auctionCancellationCause;
    }

    public BigInteger getSaveAsCount() {
        return saveAsCount;
    }

    public void setSaveAsCount(BigInteger saveAsCount) {
        this.saveAsCount = saveAsCount;
    }

    public String getDpInterfaxMessNum() {
        return dpInterfaxMessNum;
    }

    public void setDpInterfaxMessNum(String dpInterfaxMessNum) {
        this.dpInterfaxMessNum = dpInterfaxMessNum;
    }
}
