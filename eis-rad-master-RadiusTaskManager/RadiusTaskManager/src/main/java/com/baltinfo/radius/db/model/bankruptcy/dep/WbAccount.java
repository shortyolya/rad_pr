/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baltinfo.radius.db.model.bankruptcy.dep;

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
 * @author css
 */
@Entity
@Table(name = "WB_ACCOUNT_EIS", catalog = "", schema = "WEB")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "WbAccount.findNew",
                query = "SELECT a FROM WbAccount a WHERE a.wbAccountNum IS NOT NULL AND a.wbEisAccountUnid IS NULL"),
        @NamedQuery(name = "WbAccount.findUpdateByDateFrom",
                query = "SELECT a FROM WbAccount a WHERE a.wbEisAccountUnid IS NOT NULL and a.wbEisAccountUnid > 0 AND a.lastUpdateDate > :dateFrom")
})
public class WbAccount implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WB_UNID", nullable = false)
    private long wbUnid;
    @Column(name = "PARTY_NAME", length = 350)
    private String partyName;
    @Column(name = "WB_REGISTER_NUM", length = 50)
    private String wbRegisterNum;
    @Column(name = "WB_ACCOUNT_NUM")
    private Long wbAccountNum;
    @Column(name = "WB_ACCOUNT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date wbAccountDate;
    @Column(name = "WB_EIS_ACCOUNT_UNID")
    private Long wbEisAccountUnid;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "PARTY_INN", length = 12)
    private String partyInn;
    @Column(name = "PARTY_CODE_KPP", length = 20)
    private String partyCodeKpp;
    @Column(name = "PARTY_SNILS", length = 11)
    private String partySnils;
    @Column(name = "PARTY_ADDR_LEGAL", length = 250)
    private String partyAddrLegal;
    @Column(name = "PARTY_PHONE", length = 250)
    private String partyPhone;
    @Column(name = "PARTY_EMAIL", length = 250)
    private String partyEmail;
    @Column(name = "TYPES_UNID")
    private Short typesUnid;
    @Column(name = "ACCOUNT_CODE", length = 25)
    private String accountCode;
    @Column(name = "BANK_INN", length = 12)
    private String bankInn;
    @Column(name = "BANK_CODE_KPP", length = 20)
    private String bankCodeKpp;
    @Column(name = "BANK_NAME", length = 200)
    private String bankName;
    @Column(name = "BANK_BIC", length = 10)
    private String bankBic;
    @Column(name = "BANK_C_ACCOUNT", length = 20)
    private String bankCAccount;
    @Column(name = "ind_has_accreditation")
    private Integer indHasAccreditation;

    public WbAccount() {
    }

    public long getWbUnid() {
        return wbUnid;
    }

    public void setWbUnid(long wbUnid) {
        this.wbUnid = wbUnid;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getWbRegisterNum() {
        return wbRegisterNum;
    }

    public void setWbRegisterNum(String wbRegisterNum) {
        this.wbRegisterNum = wbRegisterNum;
    }

    public Long getWbAccountNum() {
        return wbAccountNum;
    }

    public void setWbAccountNum(Long wbAccountNum) {
        this.wbAccountNum = wbAccountNum;
    }

    public Date getWbAccountDate() {
        return wbAccountDate;
    }

    public void setWbAccountDate(Date wbAccountDate) {
        this.wbAccountDate = wbAccountDate;
    }

    public Long getWbEisAccountUnid() {
        return wbEisAccountUnid;
    }

    public void setWbEisAccountUnid(Long wbEisAccountUnid) {
        this.wbEisAccountUnid = wbEisAccountUnid;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getPartyInn() {
        return partyInn;
    }

    public void setPartyInn(String partyInn) {
        this.partyInn = partyInn;
    }

    public String getPartyCodeKpp() {
        return partyCodeKpp;
    }

    public void setPartyCodeKpp(String partyCodeKpp) {
        this.partyCodeKpp = partyCodeKpp;
    }

    public String getPartySnils() {
        return partySnils;
    }

    public void setPartySnils(String partySnils) {
        this.partySnils = partySnils;
    }

    public String getPartyAddrLegal() {
        return partyAddrLegal;
    }

    public void setPartyAddrLegal(String partyAddrLegal) {
        this.partyAddrLegal = partyAddrLegal;
    }

    public String getPartyPhone() {
        return partyPhone;
    }

    public void setPartyPhone(String partyPhone) {
        this.partyPhone = partyPhone;
    }

    public String getPartyEmail() {
        return partyEmail;
    }

    public void setPartyEmail(String partyEmail) {
        this.partyEmail = partyEmail;
    }

    public Short getTypesUnid() {
        return typesUnid;
    }

    public void setTypesUnid(Short typesUnid) {
        this.typesUnid = typesUnid;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getBankInn() {
        return bankInn;
    }

    public void setBankInn(String bankInn) {
        this.bankInn = bankInn;
    }

    public String getBankCodeKpp() {
        return bankCodeKpp;
    }

    public void setBankCodeKpp(String bankCodeKpp) {
        this.bankCodeKpp = bankCodeKpp;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBic() {
        return bankBic;
    }

    public void setBankBic(String bankBic) {
        this.bankBic = bankBic;
    }

    public String getBankCAccount() {
        return bankCAccount;
    }

    public void setBankCAccount(String bankCAccount) {
        this.bankCAccount = bankCAccount;
    }

    public Integer getIndHasAccreditation() {
        return indHasAccreditation;
    }

    public void setIndHasAccreditation(Integer indHasAccreditation) {
        this.indHasAccreditation = indHasAccreditation;
    }
}
