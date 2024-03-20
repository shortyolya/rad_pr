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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
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
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 29.01.2020
 */
@Entity
@Table(catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"sr_unid"})})
@SequenceGenerator(name = "seq_share", sequenceName = "seq_share", allocationSize = 1)
@NamedEntityGraphs({
        @NamedEntityGraph(name = "graph.Share.none", attributeNodes = {
        }),
        @NamedEntityGraph(name = "graph.Share.all", attributeNodes = {
                @NamedAttributeNode("subUnid"),
                @NamedAttributeNode("subSubUnid"),
                @NamedAttributeNode("typeShareCode")
        })
})
@XmlRootElement
public class Share implements IHistory, Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_share")
    @Column(name = "sr_unid", nullable = false, precision = 2147483647, scale = 0)
    private long srUnid;
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
    @Column(name = "sr_count")
    private Long srCount;
    @Column(name = "sr_cost_nom")
    private BigDecimal srCostNom;
    @Column(name = "sr_cost_nom_sum")
    private BigDecimal srCostNomSum;
    @Column(name = "sr_part_proc", precision = 9, scale = 4)
    private BigDecimal srPartProc;
    @Column(name = "sr_org", length = 300)
    private String srOrg;
    @Column(name = "sr_date")
    @Temporal(TemporalType.DATE)
    private Date srDate;
    @Column(name = "sr_nmb", length = 50)
    private String srNmb;
    @JoinColumn(name = "sub_unid", referencedColumnName = "sub_unid")
    @ManyToOne(fetch=FetchType.LAZY)
    private Subject subUnid;
    @JoinColumn(name = "sub_sub_unid", referencedColumnName = "sub_unid")
    @ManyToOne(fetch=FetchType.LAZY)
    private Subject subSubUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
    @JoinColumn(name = "type_share_code", referencedColumnName = "type_share_code", nullable = false)
    @ManyToOne(fetch=FetchType.LAZY,optional = false)
    private ClTypeShare typeShareCode;
    @Column(name = "sr_charter_capital")
    private BigDecimal srCharterCapital;
    @Column(name = "sr_charter_capital_count")
    private Long srCharterCapitalCount;
    @Column(name = "sr_reg")
    private String srReg;

    public Share() {
    }

    public Share(Share sourceShare) {
        this.srCount = sourceShare.srCount;
        this.srCostNom = sourceShare.srCostNom;
        this.srCostNomSum = sourceShare.srCostNomSum;
        this.srPartProc = sourceShare.srPartProc;
        this.srOrg = sourceShare.srOrg;
        this.srDate = sourceShare.srDate;
        this.srNmb = sourceShare.srNmb;
        this.subUnid = sourceShare.subUnid;
        this.subSubUnid = sourceShare.subSubUnid;
        this.typeShareCode = sourceShare.typeShareCode;
        this.srCharterCapital = sourceShare.srCharterCapital;
        this.srCharterCapitalCount = sourceShare.srCharterCapitalCount;
        this.srReg = sourceShare.srReg;
    }

    public Share(long srUnid) {
        this.srUnid = srUnid;
    }

    public long getSrUnid() {
        return srUnid;
    }

    public void setSrUnid(long srUnid) {
        this.srUnid = srUnid;
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

    public Long getSrCount() {
        return srCount;
    }

    public void setSrCount(Long srCount) {
        this.srCount = srCount;
    }

    public BigDecimal getSrCostNom() {
        return srCostNom;
    }

    public void setSrCostNom(BigDecimal srCostNom) {
        this.srCostNom = srCostNom;
    }

    public BigDecimal getSrCostNomSum() {
        return srCostNomSum;
    }

    public void setSrCostNomSum(BigDecimal srCostNomSum) {
        this.srCostNomSum = srCostNomSum;
    }

    public BigDecimal getSrPartProc() {
        return srPartProc;
    }

    public void setSrPartProc(BigDecimal srPartProc) {
        this.srPartProc = srPartProc;
    }

    public String getSrOrg() {
        return srOrg;
    }

    public void setSrOrg(String srOrg) {
        this.srOrg = srOrg;
    }

    public Date getSrDate() {
        return srDate;
    }

    public void setSrDate(Date srDate) {
        this.srDate = srDate;
    }

    public String getSrNmb() {
        return srNmb;
    }

    public void setSrNmb(String srNmb) {
        this.srNmb = srNmb;
    }

    public Subject getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(Subject subUnid) {
        this.subUnid = subUnid;
    }

    public Subject getSubSubUnid() {
        return subSubUnid;
    }

    public void setSubSubUnid(Subject subSubUnid) {
        this.subSubUnid = subSubUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    public ClTypeShare getTypeShareCode() {
        return typeShareCode;
    }

    public void setTypeShareCode(ClTypeShare typeShareCode) {
        this.typeShareCode = typeShareCode;
    }

    public BigDecimal getSrCharterCapital() {
        return srCharterCapital;
    }

    public void setSrCharterCapital(BigDecimal srCharterCapital) {
        this.srCharterCapital = srCharterCapital;
    }

    public Long getSrCharterCapitalCount() {
        return srCharterCapitalCount;
    }

    public void setSrCharterCapitalCount(Long srCharterCapitalCount) {
        this.srCharterCapitalCount = srCharterCapitalCount;
    }

    public String getSrReg() {
        return srReg;
    }

    public void setSrReg(String srReg) {
        this.srReg = srReg;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.Share[ srUnid=" + srUnid + " ]";
    }
}
