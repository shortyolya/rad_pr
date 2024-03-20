/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author sas
 */
@Entity
@Table(name = "bank_book", catalog = "", schema = "dep", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"bb_unid"})})
@SequenceGenerator(name = "seq_bank_book", sequenceName = "seq_bank_book", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BankBook.findAll", query = "SELECT b FROM BankBook b")})
public class BankBook implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bank_book")
    @Column(name = "bb_unid")
    private Long bbUnid;
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
    @Column(name = "bb_account")
    private String bbAccount;
    @Column(name = "bb_owner")
    private String bbOwner;
    @Column(name = "bb_owner_inn")
    private String bbOwnerInn;
    @Column(name = "bb_owner_kpp")
    private String bbOwnerKpp;
    @Column(name = "bb_owner_snils")
    private String bbOwnerSnils;
    @Column(name = "bb_owner_type")
    private Short bbOwnerType;
    @Column(name = "bb_address")
    private String bbAddress;
    @Column(name = "bb_phone")
    private String bbPhone;
    @Column(name = "bb_email")
    private String bbEmail;
    @Column(name = "bb_owner_sname")
    private String bbOwnerSname;
    @Column(name = "bb_owner_site")
    private String bbOwnerSite;
    @Column(name = "bb_status")
    private Integer bbStatus;

    public BankBook() {
    }

    public BankBook(Long bbUnid) {
        this.bbUnid = bbUnid;
    }

    public Long getBbUnid() {
        return bbUnid;
    }

    public void setBbUnid(Long bbUnid) {
        this.bbUnid = bbUnid;
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

    public String getBbAccount() {
        return bbAccount;
    }

    public void setBbAccount(String bbAccount) {
        this.bbAccount = bbAccount;
    }

    public String getBbOwner() {
        return bbOwner;
    }

    public void setBbOwner(String bbOwner) {
        this.bbOwner = bbOwner;
    }

    public String getBbOwnerInn() {
        return bbOwnerInn;
    }

    public void setBbOwnerInn(String bbOwnerInn) {
        this.bbOwnerInn = bbOwnerInn;
    }

    public String getBbOwnerKpp() {
        return bbOwnerKpp;
    }

    public void setBbOwnerKpp(String bbOwnerKpp) {
        this.bbOwnerKpp = bbOwnerKpp;
    }

    public String getBbOwnerSnils() {
        return bbOwnerSnils;
    }

    public void setBbOwnerSnils(String bbOwnerSnils) {
        this.bbOwnerSnils = bbOwnerSnils;
    }

    public Short getBbOwnerType() {
        return bbOwnerType;
    }

    public void setBbOwnerType(Short bbOwnerType) {
        this.bbOwnerType = bbOwnerType;
    }

    public String getBbAddress() {
        return bbAddress;
    }

    public void setBbAddress(String bbAddress) {
        this.bbAddress = bbAddress;
    }

    public String getBbPhone() {
        return bbPhone;
    }

    public void setBbPhone(String bbPhone) {
        this.bbPhone = bbPhone;
    }

    public String getBbEmail() {
        return bbEmail;
    }

    public void setBbEmail(String bbEmail) {
        this.bbEmail = bbEmail;
    }
    
    public String getBbOwnerSname() {
        return bbOwnerSname;
    }

    public void setBbOwnerSname(String bbOwnerSname) {
        this.bbOwnerSname = bbOwnerSname;
    }

    public String getBbOwnerSite() {
        return bbOwnerSite;
    }

    public void setBbOwnerSite(String bbOwnerSite) {
        this.bbOwnerSite = bbOwnerSite;
    }

    public Integer getBbStatus() {
        return bbStatus;
    }

    public void setBbStatus(Integer bbStatus) {
        this.bbStatus = bbStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bbUnid != null ? bbUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BankBook)) {
            return false;
        }
        BankBook other = (BankBook) object;
        if ((this.bbUnid == null && other.bbUnid != null) || (this.bbUnid != null && !this.bbUnid.equals(other.bbUnid))) {
            return false;
        }
        return true;
    }

}
