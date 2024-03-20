package com.baltinfo.radius.db.model.dep;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 *
 * @author sas
 */
@Entity
@Table(name = "account_deposit", catalog = "", schema = "dep", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"adep_unid"})})
@SequenceGenerator(name = "seq_account_deposit", sequenceName = "seq_account_deposit", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountDeposit.findAll", query = "SELECT a FROM AccountDeposit a where a.indActual = 1"),
    @NamedQuery(name = "AccountDeposit.findByAdepUnid", query = "SELECT a FROM AccountDeposit a WHERE a.indActual = 1 AND a.adepUnid = :adepUnid")})
public class AccountDeposit {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_account_deposit")
    @Column(name = "adep_unid")
    private Long adepUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
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
    @Column(name = "adep_name")
    private String adepName;
    @Column(name = "adep_account")
    private String adepAccount;
    @Column(name = "adep_bank")
    private String adepBank;
    @Column(name = "adep_corr_account")
    private String adepCorrAccount;
    @Column(name = "adep_bank_bik")
    private String adepBankBik;
    @Column(name = "adep_periodicity")
    private String adepPeriodicity;
    @Column(name = "adep_periodicity_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date adepPeriodicityTime;
    @Column(name = "adep_copying_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date adepCopyingTime;
    @Column(name = "adep_copying_result")
    private Short adepCopyingResult;
    @Column(name = "adep_org_name")
    private String adepOrgName;
    @Column(name = "adep_org_inn")
    private String adepOrgInn;
    @Column(name = "adep_org_kpp")
    private String adepOrgKpp;
    @Column(name = "adep_prefix")
    private String adepPrefix;
    @Column(name = "adep_sub_name")
    private String adepSubName;
    @Column(name = "adep_sub_inn")
    private String adepSubInn;
    @Column(name = "adep_sub_kpp")
    private String adepSubKpp;
    @Column(name = "adep_sub_bank")
    private String adepSubBank;
    @Column(name = "adep_sub_account")
    private String adepSubAccount;
    @Column(name = "adep_sub_corr_account")
    private String adepSubCorrAccount;
    @Column(name = "adep_sub_bank_bik")
    private String adepSubBankBik;
    @Column(name = "adep_bank_phrase")
    private String adepBankPhrase;    
    

    public AccountDeposit() {
    }

    public AccountDeposit(Long adepUnid) {
        this.adepUnid = adepUnid;
    }

    public Long getAdepUnid() {
        return adepUnid;
    }

    public void setAdepUnid(Long adepUnid) {
        this.adepUnid = adepUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
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

    public String getAdepName() {
        return adepName;
    }

    public void setAdepName(String adepName) {
        this.adepName = adepName;
    }

    public String getAdepAccount() {
        return adepAccount;
    }

    public void setAdepAccount(String adepAccount) {
        this.adepAccount = adepAccount;
    }

    public String getAdepBank() {
        return adepBank;
    }

    public void setAdepBank(String adepBank) {
        this.adepBank = adepBank;
    }

    public String getAdepCorrAccount() {
        return adepCorrAccount;
    }

    public void setAdepCorrAccount(String adepCorrAccount) {
        this.adepCorrAccount = adepCorrAccount;
    }

    public String getAdepBankBik() {
        return adepBankBik;
    }

    public void setAdepBankBik(String adepBankBik) {
        this.adepBankBik = adepBankBik;
    }

    public String getAdepPeriodicity() {
        return adepPeriodicity;
    }

    public void setAdepPeriodicity(String adepPeriodicity) {
        this.adepPeriodicity = adepPeriodicity;
    }

    public Date getAdepPeriodicityTime() {
        return adepPeriodicityTime;
    }

    public void setAdepPeriodicityTime(Date adepPeriodicityTime) {
        this.adepPeriodicityTime = adepPeriodicityTime;
    }

    public Date getAdepCopyingTime() {
        return adepCopyingTime;
    }

    public void setAdepCopyingTime(Date adepCopyingTime) {
        this.adepCopyingTime = adepCopyingTime;
    }

    public Short getAdepCopyingResult() {
        return adepCopyingResult;
    }

    public void setAdepCopyingResult(Short adepCopyingResult) {
        this.adepCopyingResult = adepCopyingResult;
    }

    public String getAdepOrgName() {
        return adepOrgName;
    }

    public void setAdepOrgName(String adepOrgName) {
        this.adepOrgName = adepOrgName;
    }

    public String getAdepOrgInn() {
        return adepOrgInn;
    }

    public void setAdepOrgInn(String adepOrgInn) {
        this.adepOrgInn = adepOrgInn;
    }

    public String getAdepOrgKpp() {
        return adepOrgKpp;
    }

    public void setAdepOrgKpp(String adepOrgKpp) {
        this.adepOrgKpp = adepOrgKpp;
    }
    
    public String getAdepPrefix() {
        return adepPrefix;
    }

    public void setAdepPrefix(String adepPrefix) {
        this.adepPrefix = adepPrefix;
    }

    public String getAdepSubName() {
        return adepSubName;
    }

    public void setAdepSubName(String adepSubName) {
        this.adepSubName = adepSubName;
    }

    public String getAdepSubInn() {
        return adepSubInn;
    }

    public void setAdepSubInn(String adepSubInn) {
        this.adepSubInn = adepSubInn;
    }

    public String getAdepSubKpp() {
        return adepSubKpp;
    }

    public void setAdepSubKpp(String adepSubKpp) {
        this.adepSubKpp = adepSubKpp;
    }

    public String getAdepSubBank() {
        return adepSubBank;
    }

    public void setAdepSubBank(String adepSubBank) {
        this.adepSubBank = adepSubBank;
    }

    public String getAdepSubAccount() {
        return adepSubAccount;
    }

    public void setAdepSubAccount(String adepSubAccount) {
        this.adepSubAccount = adepSubAccount;
    }

    public String getAdepSubCorrAccount() {
        return adepSubCorrAccount;
    }

    public void setAdepSubCorrAccount(String adepSubCorrAccount) {
        this.adepSubCorrAccount = adepSubCorrAccount;
    }

    public String getAdepSubBankBik() {
        return adepSubBankBik;
    }

    public void setAdepSubBankBik(String adepSubBankBik) {
        this.adepSubBankBik = adepSubBankBik;
    }

    public String getAdepBankPhrase() {
        return adepBankPhrase;
    }

    public void setAdepBankPhrase(String adepBankPhrase) {
        this.adepBankPhrase = adepBankPhrase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adepUnid != null ? adepUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountDeposit)) {
            return false;
        }
        AccountDeposit other = (AccountDeposit) object;
        if ((this.adepUnid == null && other.adepUnid != null) || (this.adepUnid != null && !this.adepUnid.equals(other.adepUnid))) {
            return false;
        }
        return true;
    }

}
