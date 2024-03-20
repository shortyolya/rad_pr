package com.baltinfo.radius.db.model;


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
import java.util.Date;

/**
 * @author ssv
 * @since 20.02.2020
 */
@Entity
@Table(name = "v_load_journal", catalog = "", schema = "web")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "VLoadJournal.findAll", query = "SELECT v FROM VLoadJournal v"),
        @NamedQuery(name = "VLoadJournal.findByLaUnid", query = "SELECT v FROM VLoadJournal v " +
                "WHERE v.laUnid = :laUnid"),
        @NamedQuery(name = "VLoadJournal.findByBaUnid", query = "SELECT v FROM VLoadJournal v " +
                "WHERE v.baUnid = :baUnid")

})
public class VLoadJournal {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "lj_unid")
    private long ljUnid;
    @Column(name = "la_unid")
    private Long laUnid;
    @Column(name = "auction_unid")
    private Long auctionUnid;
    @Column(name = "ba_unid")
    private Long baUnid;
    @Column(name = "lj_debtor_name")
    private String ljDebtorName;
    @Column(name = "lj_type_trade")
    private String ljTypeTrade;
    @Column(name = "lj_type_trade_name")
    private String ljTypeTradeName;
    @Column(name = "pa_unid")
    private Long paUnid;
    @Column(name = "pa_name")
    private String paName;
    @Column(name = "lj_order_num")
    private String ljOrderNum;
    @Column(name = "lst_unid")
    private Long lstUnid;
    @Column(name = "lst_name")
    private String lstName;
    @Column(name = "lj_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ljDate;
    @Column(name = "lj_result")
    private String ljResult;

    public VLoadJournal() {
    }

    public long getLjUnid() {
        return ljUnid;
    }

    public void setLjUnid(long ljUnid) {
        this.ljUnid = ljUnid;
    }

    public Long getLaUnid() {
        return laUnid;
    }

    public void setLaUnid(Long laUnid) {
        this.laUnid = laUnid;
    }

    public Long getAuctionUnid() {
        return auctionUnid;
    }

    public void setAuctionUnid(Long auctionUnid) {
        this.auctionUnid = auctionUnid;
    }

    public Long getBaUnid() {
        return baUnid;
    }

    public void setBaUnid(Long baUnid) {
        this.baUnid = baUnid;
    }

    public String getLjDebtorName() {
        return ljDebtorName;
    }

    public void setLjDebtorName(String ljDebtorName) {
        this.ljDebtorName = ljDebtorName;
    }

    public String getLjTypeTrade() {
        return ljTypeTrade;
    }

    public void setLjTypeTrade(String ljTypeTrade) {
        this.ljTypeTrade = ljTypeTrade;
    }

    public String getLjTypeTradeName() {
        return ljTypeTradeName;
    }

    public void setLjTypeTradeName(String ljTypeTradeName) {
        this.ljTypeTradeName = ljTypeTradeName;
    }

    public Long getPaUnid() {
        return paUnid;
    }

    public void setPaUnid(Long paUnid) {
        this.paUnid = paUnid;
    }

    public String getPaName() {
        return paName;
    }

    public void setPaName(String paName) {
        this.paName = paName;
    }

    public String getLjOrderNum() {
        return ljOrderNum;
    }

    public void setLjOrderNum(String ljOrderNum) {
        this.ljOrderNum = ljOrderNum;
    }

    public Long getLstUnid() {
        return lstUnid;
    }

    public void setLstUnid(Long lstUnid) {
        this.lstUnid = lstUnid;
    }

    public String getLstName() {
        return lstName;
    }

    public void setLstName(String lstName) {
        this.lstName = lstName;
    }

    public Date getLjDate() {
        return ljDate;
    }

    public void setLjDate(Date ljDate) {
        this.ljDate = ljDate;
    }

    public String getLjResult() {
        return ljResult;
    }

    public void setLjResult(String ljResult) {
        this.ljResult = ljResult;
    }
}
