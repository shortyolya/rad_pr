package com.baltinfo.radius.db.model;

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
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author sas
 */
@Entity
@Table(name = "account", schema = "web", catalog = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"acc_unid"})})
@SequenceGenerator(name = "seq_account", sequenceName = "seq_account", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a where a.indActual = 1"),
    @NamedQuery(name = "Account.findBySubUnid", query = "SELECT a FROM Account a where a.indActual = 1 and a.subUnid = :subUnid")})
public class Account implements IHistory {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_account")
    @Column(name = "acc_unid")
    private Long accUnid;
    @Column(name = "found_unid")
    private Integer foundUnid;
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
    @Column(name = "acc_name")
    private String accName;
    @Column(name = "acc_code")
    private String accCode;
    @Column(name = "acc_bank_name")
    private String accBankName;
    @Column(name = "acc_bank_addr")
    private String accBankAddr;
    @Column(name = "acc_bank_bik")
    private String accBankBik;
    @Column(name = "acc_bank_account")
    private String accBankAccount;
    @Column(name = "acc_bank_inn")
    private String accBankInn;
    @Column(name = "acc_bank_kpp")
    private String accBankKpp;
    @Column(name = "pd_unid")
    private Long pdUnid;
    @Column(name = "sub_unid")
    private Long subUnid;

    public Account() {
    }

    public Account(Long accUnid) {
        this.accUnid = accUnid;
    }

    public Long getAccUnid() {
        return accUnid;
    }

    public void setAccUnid(Long accUnid) {
        this.accUnid = accUnid;
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

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }

    public String getAccBankName() {
        return accBankName;
    }

    public void setAccBankName(String accBankName) {
        this.accBankName = accBankName;
    }

    public String getAccBankAddr() {
        return accBankAddr;
    }

    public void setAccBankAddr(String accBankAddr) {
        this.accBankAddr = accBankAddr;
    }

    public String getAccBankBik() {
        return accBankBik;
    }

    public void setAccBankBik(String accBankBik) {
        this.accBankBik = accBankBik;
    }

    public String getAccBankAccount() {
        return accBankAccount;
    }

    public void setAccBankAccount(String accBankAccount) {
        this.accBankAccount = accBankAccount;
    }

    public String getAccBankInn() {
        return accBankInn;
    }

    public void setAccBankInn(String accBankInn) {
        this.accBankInn = accBankInn;
    }

    public String getAccBankKpp() {
        return accBankKpp;
    }

    public void setAccBankKpp(String accBankKpp) {
        this.accBankKpp = accBankKpp;
    }

    public Long getPdUnid() {
        return pdUnid;
    }

    public void setPdUnid(Long pdUnid) {
        this.pdUnid = pdUnid;
    }

    public Long getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(Long subUnid) {
        this.subUnid = subUnid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accUnid != null ? accUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accUnid == null && other.accUnid != null) || (this.accUnid != null && !this.accUnid.equals(other.accUnid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.Account[ accUnid=" + accUnid + " ]";
    }

    public String getDescr() {
        return accName + " №" + accCode + " в банке " + accBankName;
    }

}
