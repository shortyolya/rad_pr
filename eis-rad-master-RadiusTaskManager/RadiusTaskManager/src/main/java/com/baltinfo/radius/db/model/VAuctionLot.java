package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lia
 */
@Entity
@Table(name = "v_auction_lot", catalog = "", schema = "web")
@XmlRootElement
public class VAuctionLot {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "lot_unid")
    private Long lotUnid;
    @Column(name = "obj_unid")
    private Long objUnid;
    @Column(name = "ts_unid")
    private Long tsUnid;
    @Column(name = "ts_name")
    private String tsName;
    @Column(name = "obj_ts_unid")
    private Long objTsUnid;
    @Column(name = "obj_ts_name")
    private String objTsName;
    @Column(name = "to_unid")
    private Long toUnid;
    @Column(name = "to_name")
    private String toName;
    @Column(name = "sg_unid")
    private Long sgUnid;
    @Column(name = "sg_name")
    private String sgName;
    @Column(name = "sm_unid")
    private Long smUnid;
    @Column(name = "sm_name")
    private String smName;
    @Column(name = "obj_code")
    private String objCode;
    @Column(name = "obj_name")
    private String objName;
    @Column(name = "start_cost")
    private BigDecimal startCost;
    @Column(name = "end_cost")
    private BigDecimal endCost;
    @Column(name = "lot_number")
    private Long lotNumber;
    @Column(name = "lot_publ_header")
    private String lotPublHeader;
    @Column(name = "non_exec_reason_unid")
    private Long nonExecReasonUnid;
    @Column(name = "non_exec_reason_name")
    private String nonExecReasonName;
    @Column(name = "auction_unid")
    private Long auctionUnid;
    @Column(name = "lot_auction_theme")
    private String lotAuctionTheme;
    @Column(name = "lot_step_value")
    private BigDecimal lotStepValue;
    @Column(name = "lot_step_decrease_value")
    private BigDecimal lotStepDecreaseValue;
    @Column(name = "min_cost")
    private BigDecimal minCost;
    @Column(name = "lot_ind_current")
    private Short lotIndCurrent;
    @Column(name = "ca_unid")
    private Long caUnid;
    @Column(name = "ca_code")
    private Integer caCode;
    @Column(name = "ca_name")
    private String caName;
    @Column(name = "lot_sum_deposit")
    private BigDecimal lotSumDeposit;
    @Column(name = "srf_asv_name")
    private String srfAsvName;
    @Column(name = "addr_region_id")
    private String addrRegionId;
    @Column(name = "sale_division")
    private String saleDivision;
    @Column(name = "create_division")
    private String createDivision;
    @Column(name = "sale_division_unid")
    private Long saleDivisionUnid;
    @Column(name = "pa_manager_unid")
    private Long paManagerUnid;
    @Column(name = "sale_manager_name")
    private String saleManagerName;
    @Column(name = "first_cost_unid")
    private Long firstCostUnid;
    @Column(name = "first_cost")
    private BigDecimal firstCost;
    @Column(name = "sale_deal_cost_unid")
    private Long saleDealCostUnid;
    @Column(name = "sale_deal_cost")
    private BigDecimal saleDealCost;
    @Column(name = "sale_deal_num")
    private String saleDealNum;
    @Column(name = "sale_deal_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saleDealDate;
    @Column(name = "rf_owner_unid")
    private Long rfOwnerUnid;
    @Column(name = "rf_owner_name")
    private String rfOwnerName;
    @Column(name = "plan_reward_unid")
    private Long planRewardUnid;
    @Column(name = "plan_reward_sum")
    private BigDecimal planRewardSum;
    @Column(name = "act_reward_unid")
    private Long actRewardUnid;
    @Column(name = "act_reward_sum")
    private BigDecimal actRewardSum;
    @Column(name = "act_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actDate;
    @Column(name = "act_ts_name")
    private String actTsName;
    @Column(name = "act_sign_both")
    private Boolean actSignBoth;
    @Column(name = "act_reward_sum_fact")
    private BigDecimal actRewardSumFact;
    @Column(name = "act_reward_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actRewardDate;
    @Column(name = "tst_unid")
    private Long tstUnid;
    @Column(name = "tst_name")
    private String tstName;
    @Column(name = "sgt_unid")
    private Long sgtUnid;
    @Column(name = "sgt_name")
    private String sgtName;
    @Column(name = "obj_ind_saled")
    private Integer objIndSaled;
    @Column(name = "sc_name")
    private String scName;
    @Column(name = "buyer_acts_json")
    private String buyerActsJson;
    @Column(name = "entity_unid")
    private Long entityUnid;
    @Column(name = "cur_iso_sname")
    private String curIsoSname;
    @Column(name = "start_cost_rub")
    private BigDecimal startCostRub;
    @Column(name = "end_cost_rub")
    private BigDecimal endCostRub;
    @Column(name = "min_cost_rub")
    private BigDecimal minCostRub;
    @Column(name = "cur_iso_code")
    private Long curIsoCode;
    @Column(name = "lot_deposit_per")
    private BigDecimal lotDepositPer;
    @Column(name = "lot_step_decrease_proc")
    private BigDecimal lotStepDecreaseProc;
    @Column(name = "lot_step_procent")
    private BigDecimal lotStepProcent;
    @Column(name = "sc_efrsb_code")
    private String scEfrsbCode;
    @Column(name = "lot_appl_count")
    private BigDecimal lotApplCount;
    @Column(name = "lot_link_site")
    private String lotLinkSite;
    @Column(name = "lot_rad_site")
    private String lotRadSite;
    @Column(name = "lot_etp_code")
    private String lotEtpCode;
    @Column(name = "type_auction_unid")
    private Long typeAuctionUnid;
    @Column(name = "type_auction_name")
    private String typeAuctionName;
    @Column(name = "auction_date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionDateB;
    @Column(name = "auction_recep_date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionRecepDateB;

    public VAuctionLot() {
    }

    //<editor-fold defaultstate="collapsed" desc="get-set">
    public Long getLotUnid() {
        return lotUnid;
    }

    public void setLotUnid(Long lotUnid) {
        this.lotUnid = lotUnid;
    }

    public Long getObjUnid() {
        return objUnid;
    }

    public void setObjUnid(Long objUnid) {
        this.objUnid = objUnid;
    }

    public Long getTsUnid() {
        return tsUnid;
    }

    public void setTsUnid(Long tsUnid) {
        this.tsUnid = tsUnid;
    }

    public String getTsName() {
        return tsName;
    }

    public void setTsName(String tsName) {
        this.tsName = tsName;
    }

    public Long getObjTsUnid() {
        return objTsUnid;
    }

    public void setObjTsUnid(Long objTsUnid) {
        this.objTsUnid = objTsUnid;
    }

    public String getObjTsName() {
        return objTsName;
    }

    public void setObjTsName(String objTsName) {
        this.objTsName = objTsName;
    }

    public Long getToUnid() {
        return toUnid;
    }

    public void setToUnid(Long toUnid) {
        this.toUnid = toUnid;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public Long getSgUnid() {
        return sgUnid;
    }

    public void setSgUnid(Long sgUnid) {
        this.sgUnid = sgUnid;
    }

    public String getSgName() {
        return sgName;
    }

    public void setSgName(String sgName) {
        this.sgName = sgName;
    }

    public Long getSmUnid() {
        return smUnid;
    }

    public void setSmUnid(Long smUnid) {
        this.smUnid = smUnid;
    }

    public String getSmName() {
        return smName;
    }

    public void setSmName(String smName) {
        this.smName = smName;
    }

    public String getObjCode() {
        return objCode;
    }

    public void setObjCode(String objCode) {
        this.objCode = objCode;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public BigDecimal getStartCost() {
        return startCost;
    }

    public void setStartCost(BigDecimal startCost) {
        this.startCost = startCost;
    }

    public BigDecimal getEndCost() {
        return endCost;
    }

    public void setEndCost(BigDecimal endCost) {
        this.endCost = endCost;
    }

    public Long getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(Long lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getLotPublHeader() {
        return lotPublHeader;
    }

    public void setLotPublHeader(String lotPublHeader) {
        this.lotPublHeader = lotPublHeader;
    }

    public Long getNonExecReasonUnid() {
        return nonExecReasonUnid;
    }

    public void setNonExecReasonUnid(Long nonExecReasonUnid) {
        this.nonExecReasonUnid = nonExecReasonUnid;
    }

    public String getNonExecReasonName() {
        return nonExecReasonName;
    }

    public void setNonExecReasonName(String nonExecReasonName) {
        this.nonExecReasonName = nonExecReasonName;
    }

    public Long getAuctionUnid() {
        return auctionUnid;
    }

    public void setAuctionUnid(Long auctionUnid) {
        this.auctionUnid = auctionUnid;
    }

    public String getLotAuctionTheme() {
        return lotAuctionTheme;
    }

    public void setLotAuctionTheme(String lotAuctionTheme) {
        this.lotAuctionTheme = lotAuctionTheme;
    }

    public BigDecimal getLotStepValue() {
        return lotStepValue;
    }

    public void setLotStepValue(BigDecimal lotStepValue) {
        this.lotStepValue = lotStepValue;
    }

    public BigDecimal getLotStepDecreaseValue() {
        return lotStepDecreaseValue;
    }

    public void setLotStepDecreaseValue(BigDecimal lotStepDecreaseValue) {
        this.lotStepDecreaseValue = lotStepDecreaseValue;
    }

    public BigDecimal getMinCost() {
        return minCost;
    }

    public void setMinCost(BigDecimal minCost) {
        this.minCost = minCost;
    }

    public Short getLotIndCurrent() {
        return lotIndCurrent;
    }

    public void setLotIndCurrent(Short lotIndCurrent) {
        this.lotIndCurrent = lotIndCurrent;
    }

    public Long getCaUnid() {
        return caUnid;
    }

    public void setCaUnid(Long caUnid) {
        this.caUnid = caUnid;
    }

    public String getCaName() {
        return caName;
    }

    public void setCaName(String caName) {
        this.caName = caName;
    }

    public Integer getCaCode() {
        return caCode;
    }

    public void setCaCode(Integer caCode) {
        this.caCode = caCode;
    }

    public BigDecimal getLotSumDeposit() {
        return lotSumDeposit;
    }

    public void setLotSumDeposit(BigDecimal lotSumDeposit) {
        this.lotSumDeposit = lotSumDeposit;
    }

    public String getSrfAsvName() {
        return srfAsvName;
    }

    public void setSrfAsvName(String srfAsvName) {
        this.srfAsvName = srfAsvName;
    }

    public String getAddrRegionId() {
        return addrRegionId;
    }

    public void setAddrRegionId(String addrRegionId) {
        this.addrRegionId = addrRegionId;
    }

    public String getSaleDivision() {
        return saleDivision;
    }

    public void setSaleDivision(String saleDivision) {
        this.saleDivision = saleDivision;
    }

    public Long getSaleDivisionUnid() {
        return saleDivisionUnid;
    }

    public void setSaleDivisionUnid(Long saleDivisionUnid) {
        this.saleDivisionUnid = saleDivisionUnid;
    }

    public String getCreateDivision() {
        return createDivision;
    }

    public void setCreateDivision(String createDivision) {
        this.createDivision = createDivision;
    }

    public Long getPaManagerUnid() {
        return paManagerUnid;
    }

    public void setPaManagerUnid(Long paManagerUnid) {
        this.paManagerUnid = paManagerUnid;
    }

    public String getSaleManagerName() {
        return saleManagerName;
    }

    public void setSaleManagerName(String saleManagerName) {
        this.saleManagerName = saleManagerName;
    }

    public Long getFirstCostUnid() {
        return firstCostUnid;
    }

    public void setFirstCostUnid(Long firstCostUnid) {
        this.firstCostUnid = firstCostUnid;
    }

    public BigDecimal getFirstCost() {
        return firstCost;
    }

    public void setFirstCost(BigDecimal firstCost) {
        this.firstCost = firstCost;
    }

    public Long getSaleDealCostUnid() {
        return saleDealCostUnid;
    }

    public void setSaleDealCostUnid(Long saleDealCostUnid) {
        this.saleDealCostUnid = saleDealCostUnid;
    }

    public BigDecimal getSaleDealCost() {
        return saleDealCost;
    }

    public void setSaleDealCost(BigDecimal saleDealCost) {
        this.saleDealCost = saleDealCost;
    }

    public String getSaleDealNum() {
        return saleDealNum;
    }

    public void setSaleDealNum(String saleDealNum) {
        this.saleDealNum = saleDealNum;
    }

    public Long getRfOwnerUnid() {
        return rfOwnerUnid;
    }

    public void setRfOwnerUnid(Long rfOwnerUnid) {
        this.rfOwnerUnid = rfOwnerUnid;
    }

    public String getRfOwnerName() {
        return rfOwnerName;
    }

    public void setRfOwnerName(String rfOwnerName) {
        this.rfOwnerName = rfOwnerName;
    }

    public Long getPlanRewardUnid() {
        return planRewardUnid;
    }

    public void setPlanRewardUnid(Long planRewardUnid) {
        this.planRewardUnid = planRewardUnid;
    }

    public BigDecimal getPlanRewardSum() {
        return planRewardSum;
    }

    public void setPlanRewardSum(BigDecimal planRewardSum) {
        this.planRewardSum = planRewardSum;
    }

    public Long getActRewardUnid() {
        return actRewardUnid;
    }

    public void setActRewardUnid(Long actRewardUnid) {
        this.actRewardUnid = actRewardUnid;
    }

    public BigDecimal getActRewardSum() {
        return actRewardSum;
    }

    public void setActRewardSum(BigDecimal actRewardSum) {
        this.actRewardSum = actRewardSum;
    }

    public Date getActDate() {
        return actDate;
    }

    public void setActDate(Date actDate) {
        this.actDate = actDate;
    }

    public String getActTsName() {
        return actTsName;
    }

    public void setActTsName(String actTsName) {
        this.actTsName = actTsName;
    }

    public Boolean getActSignBoth() {
        return actSignBoth;
    }

    public void setActSignBoth(Boolean actSignBoth) {
        this.actSignBoth = actSignBoth;
    }

    public BigDecimal getActRewardSumFact() {
        return actRewardSumFact;
    }

    public void setActRewardSumFact(BigDecimal actRewardSumFact) {
        this.actRewardSumFact = actRewardSumFact;
    }

    public Date getActRewardDate() {
        return actRewardDate;
    }

    public void setActRewardDate(Date actRewardDate) {
        this.actRewardDate = actRewardDate;
    }

    public Date getSaleDealDate() {
        return saleDealDate;
    }

    public void setSaleDealDate(Date saleDealDate) {
        this.saleDealDate = saleDealDate;
    }

    public Long getTstUnid() {
        return tstUnid;
    }

    public void setTstUnid(Long tstUnid) {
        this.tstUnid = tstUnid;
    }

    public String getTstName() {
        return tstName;
    }

    public void setTstName(String tstName) {
        this.tstName = tstName;
    }

    public Long getSgtUnid() {
        return sgtUnid;
    }

    public void setSgtUnid(Long sgtUnid) {
        this.sgtUnid = sgtUnid;
    }

    public String getSgtName() {
        return sgtName;
    }

    public void setSgtName(String sgtName) {
        this.sgtName = sgtName;
    }

    public Integer getObjIndSaled() {
        return objIndSaled;
    }

    public void setObjIndSaled(Integer objIndSaled) {
        this.objIndSaled = objIndSaled;
    }

    public String getScName() {
        return scName;
    }

    public void setScName(String scName) {
        this.scName = scName;
    }

    public String getBuyerActsJson() {
        return buyerActsJson;
    }

    public void setBuyerActsJson(String buyerActsJson) {
        this.buyerActsJson = buyerActsJson;
    }

    public Long getEntityUnid() {
        return entityUnid;
    }

    public void setEntityUnid(Long entityUnid) {
        this.entityUnid = entityUnid;
    }

    public String getCurIsoSname() {
        return curIsoSname;
    }

    public void setCurIsoSname(String curIsoSname) {
        this.curIsoSname = curIsoSname;
    }

    public BigDecimal getStartCostRub() {
        return startCostRub;
    }

    public void setStartCostRub(BigDecimal startCostRub) {
        this.startCostRub = startCostRub;
    }

    public BigDecimal getEndCostRub() {
        return endCostRub;
    }

    public void setEndCostRub(BigDecimal endCostRub) {
        this.endCostRub = endCostRub;
    }

    public BigDecimal getMinCostRub() {
        return minCostRub;
    }

    public void setMinCostRub(BigDecimal minCostRub) {
        this.minCostRub = minCostRub;
    }

    public Long getCurIsoCode() {
        return curIsoCode;
    }

    public void setCurIsoCode(Long curIsoCode) {
        this.curIsoCode = curIsoCode;
    }

    public BigDecimal getLotDepositPer() {
        return lotDepositPer;
    }

    public void setLotDepositPer(BigDecimal lotDepositPer) {
        this.lotDepositPer = lotDepositPer;
    }

    public BigDecimal getLotStepDecreaseProc() {
        return lotStepDecreaseProc;
    }

    public void setLotStepDecreaseProc(BigDecimal lotStepDecreaseProc) {
        this.lotStepDecreaseProc = lotStepDecreaseProc;
    }

    public BigDecimal getLotStepProcent() {
        return lotStepProcent;
    }

    public void setLotStepProcent(BigDecimal lotStepProcent) {
        this.lotStepProcent = lotStepProcent;
    }

    public String getScEfrsbCode() {
        return scEfrsbCode;
    }

    public void setScEfrsbCode(String scEfrsbCode) {
        this.scEfrsbCode = scEfrsbCode;
    }

    public BigDecimal getLotApplCount() {
        return lotApplCount;
    }

    public void setLotApplCount(BigDecimal lotApplCount) {
        this.lotApplCount = lotApplCount;
    }

    public String getLotLinkSite() {
        return lotLinkSite;
    }

    public void setLotLinkSite(String lotLinkSite) {
        this.lotLinkSite = lotLinkSite;
    }

    public String getLotRadSite() {
        return lotRadSite;
    }

    public void setLotRadSite(String lotRadSite) {
        this.lotRadSite = lotRadSite;
    }

    public String getLotEtpCode() {
        return lotEtpCode;
    }

    public void setLotEtpCode(String lotEtpCode) {
        this.lotEtpCode = lotEtpCode;
    }

    public Long getTypeAuctionUnid() {
        return typeAuctionUnid;
    }

    public void setTypeAuctionUnid(Long typeAuctionUnid) {
        this.typeAuctionUnid = typeAuctionUnid;
    }

    public String getTypeAuctionName() {
        return typeAuctionName;
    }

    public void setTypeAuctionName(String typeAuctionName) {
        this.typeAuctionName = typeAuctionName;
    }

    public Date getAuctionDateB() {
        return auctionDateB;
    }

    public void setAuctionDateB(Date auctionDateB) {
        this.auctionDateB = auctionDateB;
    }

    public Date getAuctionRecepDateB() {
        return auctionRecepDateB;
    }

    public void setAuctionRecepDateB(Date auctionRecepDateB) {
        this.auctionRecepDateB = auctionRecepDateB;
    }
//</editor-fold>

    @Override
    public String toString() {
        return "VAuctionLot{" +
                "lotUnid=" + lotUnid +
                ", objUnid=" + objUnid +
                ", tsUnid=" + tsUnid +
                ", tsName='" + tsName + '\'' +
                ", objTsUnid=" + objTsUnid +
                ", objTsName='" + objTsName + '\'' +
                ", toUnid=" + toUnid +
                ", toName='" + toName + '\'' +
                ", sgUnid=" + sgUnid +
                ", sgName='" + sgName + '\'' +
                ", smUnid=" + smUnid +
                ", smName='" + smName + '\'' +
                ", objCode='" + objCode + '\'' +
                ", objName='" + objName + '\'' +
                ", startCost=" + startCost +
                ", endCost=" + endCost +
                ", lotNumber=" + lotNumber +
                ", lotPublHeader='" + lotPublHeader + '\'' +
                ", nonExecReasonUnid=" + nonExecReasonUnid +
                ", nonExecReasonName='" + nonExecReasonName + '\'' +
                ", auctionUnid=" + auctionUnid +
                ", lotAuctionTheme='" + lotAuctionTheme + '\'' +
                ", lotStepValue=" + lotStepValue +
                ", lotStepDecreaseValue=" + lotStepDecreaseValue +
                ", minCost=" + minCost +
                ", lotIndCurrent=" + lotIndCurrent +
                ", caUnid=" + caUnid +
                ", caCode=" + caCode +
                ", caName='" + caName + '\'' +
                ", lotSumDeposit=" + lotSumDeposit +
                ", srfAsvName='" + srfAsvName + '\'' +
                '}';
    }
}
