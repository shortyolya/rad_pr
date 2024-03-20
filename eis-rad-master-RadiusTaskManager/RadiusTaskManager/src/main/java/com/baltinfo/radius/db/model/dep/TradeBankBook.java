package com.baltinfo.radius.db.model.dep;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author css
 */
@Entity
@Table(name = "trade_bank_book", catalog = "", schema = "DEP", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"tbb_unid"})})
@XmlRootElement

public class TradeBankBook implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_trade_bank_book")
    @SequenceGenerator(name = "seq_trade_bank_book", sequenceName = "dep.seq_trade_bank_book", allocationSize = 1)
    @Column(name = "tbb_unid", nullable = false, precision = 2147483647, scale = 0)
    private long tbbUnid;
    @Column(name = "found_unid")
    private Integer foundUnid;
    @Column(name = "ind_actual")
    private Integer indActual;
    @Column(name = "date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateB;
    @Column(name = "found_b", length = 500)
    private String foundB;
    @Column(name = "pers_code_b")
    private Long persCodeB;
    @Column(name = "date_b_rec")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBRec;
    @Column(name = "pers_code_b_rec")
    private Long persCodeBRec;
    @Column(name = "tbb_trade_unid", length = 20)
    private String tbbTradeUnid;
    @Column(name = "tbb_bank", length = 150)
    private String tbbBank;
    @Column(name = "tbb_corr_account", length = 20)
    private String tbbCorrAccount;
    @Column(name = "tbb_bank_bik", length = 20)
    private String tbbBankBik;
    @Column(name = "tbb_account", length = 20)
    private String tbbAccount;
    @Basic(optional = false)
    @Column(name = "trade_list_unid", nullable = false)
    private Long tradeListUnid;
    @Basic(optional = false)
    @Column(name = "bb_unid", nullable = false)
    private Long bbUnid;


    public TradeBankBook() {
    }

    public TradeBankBook(long tbbUnid) {
        this.tbbUnid = tbbUnid;
    }

    public long getTbbUnid() {
        return tbbUnid;
    }

    public void setTbbUnid(long tbbUnid) {
        this.tbbUnid = tbbUnid;
    }

    public Integer getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Integer foundUnid) {
        this.foundUnid = foundUnid;
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

    public String getTbbTradeUnid() {
        return tbbTradeUnid;
    }

    public void setTbbTradeUnid(String tbbTradeUnid) {
        this.tbbTradeUnid = tbbTradeUnid;
    }

    public String getTbbBank() {
        return tbbBank;
    }

    public void setTbbBank(String tbbBank) {
        this.tbbBank = tbbBank;
    }

    public String getTbbCorrAccount() {
        return tbbCorrAccount;
    }

    public void setTbbCorrAccount(String tbbCorrAccount) {
        this.tbbCorrAccount = tbbCorrAccount;
    }

    public String getTbbBankBik() {
        return tbbBankBik;
    }

    public void setTbbBankBik(String tbbBankBik) {
        this.tbbBankBik = tbbBankBik;
    }

    public String getTbbAccount() {
        return tbbAccount;
    }

    public void setTbbAccount(String tbbAccount) {
        this.tbbAccount = tbbAccount;
    }

    public Long getTradeListUnid() {
        return tradeListUnid;
    }

    public void setTradeListUnid(Long tradeListUnid) {
        this.tradeListUnid = tradeListUnid;
    }

    public Long getBbUnid() {
        return bbUnid;
    }

    public void setBbUnid(Long bbUnid) {
        this.bbUnid = bbUnid;
    }

    
}
