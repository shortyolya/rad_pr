package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author kya
 */
@Entity
@Table(name = "obj_cost", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"cost_unid"})})
@SequenceGenerator(name = "seq_obj_cost", sequenceName = "seq_obj_cost", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "ObjCost.findStartCostByLotUnid", query = "SELECT c FROM ObjCost c, Lot l WHERE c.lotUnid = l AND c.typeCosUnid = 1 AND c.indActual = 1 AND l.lotUnid = :lotUnid"),
        @NamedQuery(name = "ObjCost.findMinCostByLotUnid", query = "SELECT c FROM ObjCost c, Lot l WHERE c.lotUnid = l AND c.typeCosUnid = 9 AND c.indActual = 1 AND l.lotUnid = :lotUnid"),
        @NamedQuery(name = "ObjCost.findEndCostByLotUnid", query = "SELECT c FROM ObjCost c, Lot l WHERE c.lotUnid = l AND c.typeCosUnid = 2 AND c.indActual = 1 AND l.lotUnid = :lotUnid"),
        @NamedQuery(name = "ObjCost.findCostIndCurrentByObjUnid", query = "SELECT c FROM ObjCost c, ObjectJPA o WHERE c.objUnid = o AND c.costIndCurrent = 1 AND c.indActual = 1 AND o.objUnid = :objUnid")
})
public class ObjCost implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_obj_cost")
    @Column(name = "cost_unid", nullable = false, precision = 2147483647, scale = 0)
    private Long costUnid;
    @Column(name = "ind_actual")
    private Integer indActual;
    @Column(name = "type_cos_unid", nullable = false, precision = 2147483647, scale = 0)
    private long typeCosUnid;
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
    @Column(name = "cost_value")
    private BigDecimal costValue;
    @Column(name = "cost_value_rub")
    private BigDecimal costValueRub;
    @Column(name = "cost_rate")
    private BigDecimal costRate;
    @Column(name = "cost_tax")
    private BigDecimal costTax;
    @Column(name = "cost_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date costDate;
    @Column(name = "cost_note")
    private String costNote;
    @Column(name = "cost_ind_current")
    private Integer costIndCurrent;
    @JoinColumn(name = "obj_unid", referencedColumnName = "obj_unid")
    @ManyToOne
    private ObjectJPA objUnid;
    @JoinColumn(name = "lot_unid", referencedColumnName = "lot_unid")
    @ManyToOne
    private Lot lotUnid;
    @Column(name = "val_unid")
    private Long valUnid;
    @Column(name = "deal_unid")
    private Long dealUnid;
    @Column(name = "cur_unid")
    private Long curUnid;
    @Column(name = "cost_ind_tax")
    private Integer costIndTax;
    @Column(name = "cost_value_min")
    private BigDecimal costValueMin;
    @Column(name = "cost_tax_min")
    private BigDecimal costTaxMin;
    @Column(name = "cost_ind_tax_min")
    private Integer costIndTaxMin;
    @Column(name = "ss_Unid")
    private Long ssUnid;
    @Column(name = "cost_main_unid")
    private Long costMainUnid;
    @Column(name = "cost_ind_tax_calc")
    private Integer costIndTaxCalc;
    @Column(name = "cost_status")
    private Integer costStatus;
    @Column(name = "cost_tax_proc")
    private Integer costTaxProc;
    @Column(name = "cost_tax_min_proc")
    private Integer costTaxMinProc;

    public ObjCost() {
    }

    public Long getCostUnid() {
        return costUnid;
    }

    public void setCostUnid(Long costUnid) {
        this.costUnid = costUnid;
    }

    @Override
    public Integer getIndActual() {
        return indActual;
    }

    @Override
    public void setIndActual(Integer indActual) {
        this.indActual = indActual;
    }

    public long getTypeCosUnid() {
        return typeCosUnid;
    }

    public void setTypeCosUnid(long typeCosUnid) {
        this.typeCosUnid = typeCosUnid;
    }

    @Override
    public Date getDateB() {
        return dateB;
    }

    @Override
    public void setDateB(Date dateB) {
        this.dateB = dateB;
    }

    @Override
    public String getFoundB() {
        return foundB;
    }

    @Override
    public void setFoundB(String foundB) {
        this.foundB = foundB;
    }

    @Override
    public Long getPersCodeB() {
        return persCodeB;
    }

    @Override
    public void setPersCodeB(Long persCodeB) {
        this.persCodeB = persCodeB;
    }

    @Override
    public Date getDateBRec() {
        return dateBRec;
    }

    @Override
    public void setDateBRec(Date dateBRec) {
        this.dateBRec = dateBRec;
    }

    @Override
    public Long getPersCodeBRec() {
        return persCodeBRec;
    }

    @Override
    public void setPersCodeBRec(Long persCodeBRec) {
        this.persCodeBRec = persCodeBRec;
    }

    public BigDecimal getCostValue() {
        return costValue;
    }

    public void setCostValue(BigDecimal costValue) {
        this.costValue = costValue;
    }

    public BigDecimal getCostValueRub() {
        return costValueRub;
    }

    public void setCostValueRub(BigDecimal costValueRub) {
        this.costValueRub = costValueRub;
    }

    public BigDecimal getCostRate() {
        return costRate;
    }

    public void setCostRate(BigDecimal costRate) {
        this.costRate = costRate;
    }

    public BigDecimal getCostTax() {
        return costTax;
    }

    public void setCostTax(BigDecimal costTax) {
        this.costTax = costTax;
    }

    public Date getCostDate() {
        return costDate;
    }

    public void setCostDate(Date costDate) {
        this.costDate = costDate;
    }

    public String getCostNote() {
        return costNote;
    }

    public void setCostNote(String costNote) {
        this.costNote = costNote;
    }

    public Integer getCostIndCurrent() {
        return costIndCurrent;
    }

    public void setCostIndCurrent(Integer costIndCurrent) {
        this.costIndCurrent = costIndCurrent;
    }

    public ObjectJPA getObjUnid() {
        return objUnid;
    }

    public void setObjUnid(ObjectJPA objUnid) {
        this.objUnid = objUnid;
    }

    public Lot getLotUnid() {
        return lotUnid;
    }

    public void setLotUnid(Lot lotUnid) {
        this.lotUnid = lotUnid;
    }

    public Long getValUnid() {
        return valUnid;
    }

    public void setValUnid(Long valUnid) {
        this.valUnid = valUnid;
    }

    public Long getDealUnid() {
        return dealUnid;
    }

    public void setDealUnid(Long dealUnid) {
        this.dealUnid = dealUnid;
    }

    public Long getCurUnid() {
        return curUnid;
    }

    public void setCurUnid(Long curUnid) {
        this.curUnid = curUnid;
    }

    public Integer getCostIndTax() {
        return costIndTax;
    }

    public void setCostIndTax(Integer costIndTax) {
        this.costIndTax = costIndTax;
    }

    public BigDecimal getCostValueMin() {
        return costValueMin;
    }

    public void setCostValueMin(BigDecimal costValueMin) {
        this.costValueMin = costValueMin;
    }

    public BigDecimal getCostTaxMin() {
        return costTaxMin;
    }

    public void setCostTaxMin(BigDecimal costTaxMin) {
        this.costTaxMin = costTaxMin;
    }

    public Integer getCostIndTaxMin() {
        return costIndTaxMin;
    }

    public void setCostIndTaxMin(Integer costIndTaxMin) {
        this.costIndTaxMin = costIndTaxMin;
    }

    public Long getSsUnid() {
        return ssUnid;
    }

    public void setSsUnid(Long ssUnid) {
        this.ssUnid = ssUnid;
    }

    public Long getCostMainUnid() {
        return costMainUnid;
    }

    public void setCostMainUnid(Long costMainUnid) {
        this.costMainUnid = costMainUnid;
    }

    public Integer getCostIndTaxCalc() {
        return costIndTaxCalc;
    }

    public void setCostIndTaxCalc(Integer costIndTaxCalc) {
        this.costIndTaxCalc = costIndTaxCalc;
    }

    public Integer getCostStatus() {
        return costStatus;
    }

    public void setCostStatus(Integer costStatus) {
        this.costStatus = costStatus;
    }

    public Integer getCostTaxProc() {
        return costTaxProc;
    }

    public void setCostTaxProc(Integer costTaxProc) {
        this.costTaxProc = costTaxProc;
    }

    public Integer getCostTaxMinProc() {
        return costTaxMinProc;
    }

    public void setCostTaxMinProc(Integer costTaxMinProc) {
        this.costTaxMinProc = costTaxMinProc;
    }

    @Override
    public String toString() {
        return super.toString() + "[ costUnid=" + costUnid + " ]";
    }
}
