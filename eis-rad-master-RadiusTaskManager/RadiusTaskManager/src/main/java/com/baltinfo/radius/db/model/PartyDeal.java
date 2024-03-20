package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 *
 * @author sas
 */
@Entity
@Table(name = "party_deal", catalog = "", schema = "web")
@SequenceGenerator(name = "seq_party_deal", sequenceName = "seq_party_deal", allocationSize = 1)
@XmlRootElement
public class PartyDeal {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_party_deal")
    @Column(name = "pd_unid")
    private Long pdUnid;
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
    @Column(name = "pd_num")
    private Integer pdNum;
    @Basic(optional = false)
    @Column(name = "deal_unid")
    private Long dealUnid;
    @Basic(optional = false)
    @Column(name = "sub_unid")
    private Long subUnid;
    @Column(name = "sub_sub_unid")
    private Long subSubUnid;
    @JoinColumn(name = "sh_unid", referencedColumnName = "sh_unid", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private SubjectHistory shUnid;
    @Column(name = "rpd_unid")
    private Long rpdUnid;
    @Column(name = "sub_sro_unid")
    private Long subSroUnid;
    @Column(name = "pd_bankr_type_doc")
    private String pdBankrTypeDoc;
    @Column(name = "pd_bankr_arbitr_name")
    private String pdBankrArbitrName;
    @Column(name = "pd_bankr_num")
    private String pdBankrNum;
    @Column(name = "pd_bankr_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pdBankrDate;

    public PartyDeal() {
    }

    public PartyDeal(Long pdUnid) {
        this.pdUnid = pdUnid;
    }

    public Long getPdUnid() {
        return pdUnid;
    }

    public void setPdUnid(Long pdUnid) {
        this.pdUnid = pdUnid;
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

    public Integer getPdNum() {
        return pdNum;
    }

    public void setPdNum(Integer pdNum) {
        this.pdNum = pdNum;
    }

    public Long getDealUnid() {
        return dealUnid;
    }

    public void setDealUnid(Long dealUnid) {
        this.dealUnid = dealUnid;
    }

    public Long getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(Long subUnid) {
        this.subUnid = subUnid;
    }

    public void setRpdUnid(Long rpdUnid) {
        this.rpdUnid = rpdUnid;
    }

    public void setSubSroUnid(Long subSroUnid) {
        this.subSroUnid = subSroUnid;
    }

    public Long getSubSubUnid() {
        return subSubUnid;
    }

    public void setSubSubUnid(Long subSubUnid) {
        this.subSubUnid = subSubUnid;
    }

    public SubjectHistory getShUnid() {
        return shUnid;
    }

    public String getPdBankrTypeDoc() {
        return pdBankrTypeDoc;
    }

    public void setPdBankrTypeDoc(String pdBankrTypeDoc) {
        this.pdBankrTypeDoc = pdBankrTypeDoc;
    }

    public String getPdBankrArbitrName() {
        return pdBankrArbitrName;
    }

    public void setPdBankrArbitrName(String pdBankrArbitrName) {
        this.pdBankrArbitrName = pdBankrArbitrName;
    }

    public String getPdBankrNum() {
        return pdBankrNum;
    }

    public void setPdBankrNum(String pdBankrNum) {
        this.pdBankrNum = pdBankrNum;
    }

    public Date getPdBankrDate() {
        return pdBankrDate;
    }

    public void setPdBankrDate(Date pdBankrDate) {
        this.pdBankrDate = pdBankrDate;
    }

    public void setShUnid(SubjectHistory shUnid) {
        this.shUnid = shUnid;
    }

    public Long getRpdUnid() {
        return rpdUnid;
    }

    public Long getSubSroUnid() {
        return subSroUnid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pdUnid != null ? pdUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PartyDeal)) {
            return false;
        }
        PartyDeal other = (PartyDeal) object;
        if ((this.pdUnid == null && other.pdUnid != null) || (this.pdUnid != null && !this.pdUnid.equals(other.pdUnid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.PartyDeal[ pdUnid=" + pdUnid + " ]";
    }

}
