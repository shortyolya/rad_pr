package com.baltinfo.radius.db.model.dep;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
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
 * @author sas
 */
@Entity
@Table(name = "account_operation", catalog = "", schema = "dep", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"acc_oper_unid"})})
@SequenceGenerator(name = "seq_account_operation", sequenceName = "seq_account_operation", allocationSize = 1)
@XmlRootElement
@NamedEntityGraphs({
    @NamedEntityGraph(name = "graph.AccountOperation.none"),
    @NamedEntityGraph(name = "graph.AccountOperation.all", attributeNodes = {
        @NamedAttributeNode("bbUnid"),
        @NamedAttributeNode("taoUnid")
    })
})
@NamedQueries({
    @NamedQuery(name = "AccountOperation.findAll", query = "SELECT a FROM AccountOperation a")})
public class AccountOperation implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_account_operation")
    @Column(name = "acc_oper_unid")
    private Long accOperUnid;
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
    @Column(name = "acc_oper_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accOperTime;
    @Column(name = "acc_oper_sum")
    private BigDecimal accOperSum;
    @Column(name = "acc_oper_descr")
    private String accOperDescr;
    @Column(name = "acc_oper_ind_return")
    private Integer accOperIndReturn;
    @Basic(optional = false)
    @JoinColumn(name = "bb_unid", referencedColumnName = "bb_unid")
    @ManyToOne(fetch = FetchType.LAZY)
    private BankBook bbUnid;
    @Column(name = "trade_list_unid")
    private Long tradeListUnid;
    @Column(name = "tao_unid")
    private Long taoUnid;
    @Column(name = "acc_oper_oper_site_id")
    private Long accOperOperSiteId;
    @Column(name = "acc_oper_lot_notice_num")
    private String accOperLotNoticeNum;
    @Column(name = "acc_oper_org_name")
    private String accOperOrgName;
    @Column(name = "acc_oper_prot_num")
    private String accOperProtNum;
    @Column(name = "acc_oper_prot_date")
    @Temporal(TemporalType.DATE)
    private Date accOperProtDate;
    @Column(name = "acc_oper_status")
    private Integer accOperStatus;
    @Column(name = "adep_unid")
    private Long adepUnid;
    @Column(name = "acc_oper_type_auction")
    private String accOperTypeAuction;

    public AccountOperation() {
    }

    public AccountOperation(Long accOperUnid) {
        this.accOperUnid = accOperUnid;
    }

    public Long getAccOperUnid() {
        return accOperUnid;
    }

    public void setAccOperUnid(Long accOperUnid) {
        this.accOperUnid = accOperUnid;
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

    public Date getAccOperTime() {
        return accOperTime;
    }

    public void setAccOperTime(Date accOperTime) {
        this.accOperTime = accOperTime;
    }

    public BigDecimal getAccOperSum() {
        return accOperSum;
    }

    public void setAccOperSum(BigDecimal accOperSum) {
        this.accOperSum = accOperSum;
    }

    public String getAccOperDescr() {
        return accOperDescr;
    }

    public void setAccOperDescr(String accOperDescr) {
        this.accOperDescr = accOperDescr;
    }

    public Integer getAccOperIndReturn() {
        return accOperIndReturn;
    }

    public void setAccOperIndReturn(Integer accOperIndReturn) {
        this.accOperIndReturn = accOperIndReturn;
    }

    public BankBook getBbUnid() {
        return bbUnid;
    }

    public void setBbUnid(BankBook bbUnid) {
        this.bbUnid = bbUnid;
    }

    public Long getTradeListUnid() {
        return tradeListUnid;
    }

    public void setTradeListUnid(Long tradeListUnid) {
        this.tradeListUnid = tradeListUnid;
    }

    public Long getTaoUnid() {
        return taoUnid;
    }

    public void setTaoUnid(Long taoUnid) {
        this.taoUnid = taoUnid;
    }

    public Long getAccOperOperSiteId() {
        return accOperOperSiteId;
    }

    public void setAccOperOperSiteId(Long accOperOperSiteId) {
        this.accOperOperSiteId = accOperOperSiteId;
    }

    
    public String getAccOperLotNoticeNum() {
        return accOperLotNoticeNum;
    }

    public void setAccOperLotNoticeNum(String accOperLotNoticeNum) {
        this.accOperLotNoticeNum = accOperLotNoticeNum;
    }

    public String getAccOperOrgName() {
        return accOperOrgName;
    }

    public void setAccOperOrgName(String accOperOrgName) {
        this.accOperOrgName = accOperOrgName;
    }

    public String getAccOperProtNum() {
        return accOperProtNum;
    }

    public void setAccOperProtNum(String accOperProtNum) {
        this.accOperProtNum = accOperProtNum;
    }

    public Date getAccOperProtDate() {
        return accOperProtDate;
    }

    public void setAccOperProtDate(Date accOperProtDate) {
        this.accOperProtDate = accOperProtDate;
    }

    public Integer getAccOperStatus() {
        return accOperStatus;
    }

    public void setAccOperStatus(Integer accOperStatus) {
        this.accOperStatus = accOperStatus;
    }
    
    public Long getAdepUnid() {
        return adepUnid;
    }

    public void setAdepUnid(Long adepUnid) {
        this.adepUnid = adepUnid;
    }
    
    public String getAccOperTypeAuction() {
        return accOperTypeAuction;
    }

    public void setAccOperTypeAuction(String accOperTypeAuction) {
        this.accOperTypeAuction = accOperTypeAuction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accOperUnid != null ? accOperUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountOperation)) {
            return false;
        }
        AccountOperation other = (AccountOperation) object;
        if ((this.accOperUnid == null && other.accOperUnid != null) || (this.accOperUnid != null && !this.accOperUnid.equals(other.accOperUnid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.dep.model.AccountOperation[ accOperUnid=" + accOperUnid + " ]";
    }
}
