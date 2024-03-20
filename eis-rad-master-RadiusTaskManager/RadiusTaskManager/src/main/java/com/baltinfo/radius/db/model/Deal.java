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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sas
 */
@Entity
@Table(name = "deal", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"deal_unid"})})
@SequenceGenerator(name = "seq_deal", sequenceName = "seq_deal", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Deal.findAll", query = "SELECT d FROM Deal d where d.indActual = 1"),
        @NamedQuery(name = "Deal.findByDealUnid", query = "SELECT d FROM Deal d where d.dealUnid = :dealUnid and d.indActual = 1")
})
public class Deal {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_deal")
    @Basic(optional = false)
    @Column(name = "deal_unid")
    private Long dealUnid;
    @Column(name = "tdeal_unid")
    private Long tdealUnid;
    @Column(name = "ts_unid")
    private Long tsUnid;
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
    @Column(name = "deal_num")
    private String dealNum;
    @Column(name = "deal_date")
    @Temporal(TemporalType.DATE)
    private Date dealDate;
    @Column(name = "deal_date_b")
    @Temporal(TemporalType.DATE)
    private Date dealDateB;
    @Column(name = "deal_date_e")
    @Temporal(TemporalType.DATE)
    private Date dealDateE;
    @Column(name = "deal_num_rad")
    private String dealNumRad;
    @Column(name = "deal_date_rad")
    @Temporal(TemporalType.DATE)
    private Date dealDateRad;
    @Column(name = "deal_terminate_date")
    @Temporal(TemporalType.DATE)
    private Date dealTerminateDate;
    @Column(name = "deal_reward_sum")
    private BigDecimal dealRewardSum;
    @Column(name = "deal_reward_proc", precision = 5, scale = 2)
    private BigDecimal dealRewardProc;
    @Column(name = "deal_excess_proc", precision = 5, scale = 2)
    private BigDecimal dealExcessProc;
    @Column(name = "deal_ind_advance_payment")
    private Integer dealIndAdvancePayment;
    @Column(name = "deal_advance_sum")
    private BigDecimal dealAdvanceSum;
    @Column(name = "deal_advance_proc", precision = 5, scale = 2)
    private BigDecimal dealAdvanceProc;
    @Column(name = "deal_advance_tax")
    private BigDecimal dealAdvanceTax;
    @Column(name = "deal_advance_tax_proc")
    private Integer dealAdvanceTaxProc;
    @Column(name = "deal_advance_ind_tax")
    private Integer dealAdvanceIndTax;
    @Column(name = "deal_penalty_sum")
    private BigDecimal dealPenaltySum;
    @Column(name = "deal_penalty_proc", precision = 5, scale = 2)
    private BigDecimal dealPenaltyProc;
    @Column(name = "deal_ind_frame")
    private Integer dealIndFrame;
    @Column(name = "deal_note")
    private String dealNote;
    @Column(name = "deal_act_date")
    @Temporal(TemporalType.DATE)
    private Date dealActDate;
    @Column(name = "deal_act_num")
    private String dealActNum;
    @JoinColumn(name = "sub_unid", referencedColumnName = "sub_unid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subUnid;
    @JoinColumn(name = "dea_deal_unid", referencedColumnName = "deal_unid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Deal deaDealUnid;
    @Column(name = "tpa_unid")
    private Long tpaUnid;
    @Column(name = "deal_reward_sum_act")
    private BigDecimal dealRewardSumAct;
    @Column(name = "deal_reward_tax_act")
    private BigDecimal dealRewardTaxAct;
    @Column(name = "deal_reward_ind_tax_act")
    private Integer dealRewardIndTaxAct;
    @Column(name = "deal_reward_tax_proc")
    private Integer dealRewardTaxProc;
    @Column(name = "rf_winner_unid")
    private Long rfWinnerUnid;
    @Column(name = "rf_owner_unid")
    private Long rfOwnerUnid;
    @Column(name = "pa_unid")
    private Long paUnid;
    @JoinColumn(name = "af_unid", referencedColumnName = "af_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private ActFoundation afUnid;

    @Column(name = "deal_not_sold_single")
    private Boolean dealNotSoldSingle;

    public Deal() {
    }

    public Deal(Long dealUnid) {
        this.dealUnid = dealUnid;
    }

    public Long getDealUnid() {
        return dealUnid;
    }

    public void setDealUnid(Long dealUnid) {
        this.dealUnid = dealUnid;
    }

    public Long getTdealUnid() {
        return tdealUnid;
    }

    public void setTdealUnid(Long tdealUnid) {
        this.tdealUnid = tdealUnid;
    }

    public Long getTsUnid() {
        return tsUnid;
    }

    public void setTsUnid(Long tsUnid) {
        this.tsUnid = tsUnid;
    }

    public void setTpaUnid(Long tpaUnid) {
        this.tpaUnid = tpaUnid;
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

    public String getDealNum() {
        return dealNum;
    }

    public void setDealNum(String dealNum) {
        this.dealNum = dealNum;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public Date getDealDateB() {
        return dealDateB;
    }

    public void setDealDateB(Date dealDateB) {
        this.dealDateB = dealDateB;
    }

    public Date getDealDateE() {
        return dealDateE;
    }

    public void setDealDateE(Date dealDateE) {
        this.dealDateE = dealDateE;
    }

    public String getDealNumRad() {
        return dealNumRad;
    }

    public void setDealNumRad(String dealNumRad) {
        this.dealNumRad = dealNumRad;
    }

    public Date getDealDateRad() {
        return dealDateRad;
    }

    public void setDealDateRad(Date dealDateRad) {
        this.dealDateRad = dealDateRad;
    }

    public Date getDealTerminateDate() {
        return dealTerminateDate;
    }

    public void setDealTerminateDate(Date dealTerminateDate) {
        this.dealTerminateDate = dealTerminateDate;
    }

    public BigDecimal getDealRewardSum() {
        return dealRewardSum;
    }

    public void setDealRewardSum(BigDecimal dealRewardSum) {
        this.dealRewardSum = dealRewardSum;
    }

    public BigDecimal getDealRewardProc() {
        return dealRewardProc;
    }

    public void setDealRewardProc(BigDecimal dealRewardProc) {
        this.dealRewardProc = dealRewardProc;
    }

    public BigDecimal getDealExcessProc() {
        return dealExcessProc;
    }

    public void setDealExcessProc(BigDecimal dealExcessProc) {
        this.dealExcessProc = dealExcessProc;
    }

    public BigDecimal getDealAdvanceSum() {
        return dealAdvanceSum;
    }

    public void setDealAdvanceSum(BigDecimal dealAdvanceSum) {
        this.dealAdvanceSum = dealAdvanceSum;
    }

    public BigDecimal getDealAdvanceProc() {
        return dealAdvanceProc;
    }

    public void setDealAdvanceProc(BigDecimal dealAdvanceProc) {
        this.dealAdvanceProc = dealAdvanceProc;
    }

    public BigDecimal getDealAdvanceTax() {
        return dealAdvanceTax;
    }

    public void setDealAdvanceTax(BigDecimal dealAdvanceTax) {
        this.dealAdvanceTax = dealAdvanceTax;
    }

    public Integer getDealAdvanceIndTax() {
        return dealAdvanceIndTax;
    }

    public void setDealAdvanceIndTax(Integer dealAdvanceIndTax) {
        this.dealAdvanceIndTax = dealAdvanceIndTax;
    }

    public BigDecimal getDealPenaltySum() {
        return dealPenaltySum;
    }

    public void setDealPenaltySum(BigDecimal dealPenaltySum) {
        this.dealPenaltySum = dealPenaltySum;
    }

    public BigDecimal getDealPenaltyProc() {
        return dealPenaltyProc;
    }

    public void setDealPenaltyProc(BigDecimal dealPenaltyProc) {
        this.dealPenaltyProc = dealPenaltyProc;
    }

    public Boolean getDealIndAdvancePayment() {
        return dealIndAdvancePayment != null && !dealIndAdvancePayment.equals(0);
    }

    public void setDealIndAdvancePayment(Boolean dealIndAdvancePayment) {
        this.dealIndAdvancePayment = (dealIndAdvancePayment == null || dealIndAdvancePayment.equals(Boolean.FALSE)) ? 0 : 1;
    }

    public Integer getDealIndFrame() {
        return dealIndFrame;
    }

    public void setDealIndFrame(Integer dealIndFrame) {
        this.dealIndFrame = dealIndFrame;
    }

    public String getDealNote() {
        return dealNote;
    }

    public void setDealNote(String dealNote) {
        this.dealNote = dealNote;
    }

    public Date getDealActDate() {
        return dealActDate;
    }

    public void setDealActDate(Date dealActDate) {
        this.dealActDate = dealActDate;
    }

    public String getDealActNum() {
        return dealActNum;
    }

    public void setDealActNum(String dealActNum) {
        this.dealActNum = dealActNum;
    }

    public Subject getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(Subject subUnid) {
        this.subUnid = subUnid;
    }

    public Deal getDeaDealUnid() {
        return deaDealUnid;
    }

    public void setDeaDealUnid(Deal deaDealUnid) {
        this.deaDealUnid = deaDealUnid;
    }

    public BigDecimal getDealRewardSumAct() {
        return dealRewardSumAct;
    }

    public void setDealRewardSumAct(BigDecimal dealRewardSumAct) {
        this.dealRewardSumAct = dealRewardSumAct;
    }

    public BigDecimal getDealRewardTaxAct() {
        return dealRewardTaxAct;
    }

    public void setDealRewardTaxAct(BigDecimal dealRewardTaxAct) {
        this.dealRewardTaxAct = dealRewardTaxAct;
    }

    public Integer getDealRewardIndTaxAct() {
        return dealRewardIndTaxAct;
    }

    public void setDealRewardIndTaxAct(Integer dealRewardIndTaxAct) {
        this.dealRewardIndTaxAct = dealRewardIndTaxAct;
    }

    public Integer getDealAdvanceTaxProc() {
        return dealAdvanceTaxProc;
    }

    public void setDealAdvanceTaxProc(Integer dealAdvanceTaxProc) {
        this.dealAdvanceTaxProc = dealAdvanceTaxProc;
    }

    public Integer getDealRewardTaxProc() {
        return dealRewardTaxProc;
    }

    public void setDealRewardTaxProc(Integer dealRewardTaxProc) {
        this.dealRewardTaxProc = dealRewardTaxProc;
    }

    public Long getTpaUnid() {
        return tpaUnid;
    }

    public Long getRfWinnerUnid() {
        return rfWinnerUnid;
    }

    public void setRfWinnerUnid(Long rfWinnerUnid) {
        this.rfWinnerUnid = rfWinnerUnid;
    }

    public Long getRfOwnerUnid() {
        return rfOwnerUnid;
    }

    public void setRfOwnerUnid(Long rfOwnerUnid) {
        this.rfOwnerUnid = rfOwnerUnid;
    }

    public Long getPaUnid() {
        return paUnid;
    }

    public void setPaUnid(Long paUnid) {
        this.paUnid = paUnid;
    }

    public ActFoundation getAfUnid() {
        return afUnid;
    }

    public void setAfUnid(ActFoundation afUnid) {
        this.afUnid = afUnid;
    }

    public Boolean getDealNotSoldSingle() {
        return dealNotSoldSingle;
    }

    public void setDealNotSoldSingle(Boolean dealNotSoldSingle) {
        this.dealNotSoldSingle = dealNotSoldSingle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dealUnid != null ? dealUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Deal)) {
            return false;
        }
        Deal other = (Deal) object;
        if ((this.dealUnid == null && other.dealUnid != null) || (this.dealUnid != null && !this.dealUnid.equals(other.dealUnid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.Deal[ dealUnid=" + dealUnid + " ]";
    }

    public String getDealDescr() {
        String descr = "";
        if (tdealUnid != null) {
            descr += tdealUnid;
        }
        if (dealNum != null && !dealNum.isEmpty()) {
            descr += " № " + dealNum;
        }
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy");
        if (dealDate != null) {
            descr += " от " + sdfDate.format(dealDate);
        }
        return descr.trim();
    }

}
