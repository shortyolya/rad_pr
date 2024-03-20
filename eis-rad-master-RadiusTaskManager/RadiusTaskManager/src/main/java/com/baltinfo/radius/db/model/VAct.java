package com.baltinfo.radius.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "V_ACT", catalog = "", schema = "web")
@XmlRootElement
public class VAct {
    @Id
    @Column(name = "act_unid")
    private Long actUnid;
    @Column(name = "ta_unid")
    private Long taUnid;
    @Column(name = "ta_name")
    private String taName;
    @Column(name = "act_num", length = 50)
    private String actNum;
    @Column(name = "act_date")
    @Temporal(TemporalType.DATE)
    private Date actDate;
    @Column(name = "lots_count")
    private Long lotsCount;
    @Column(name = "lots")
    private String lots;
    @Column(name = "docum_unid")
    private Long documUnid;
    @Column(name = "ts_unid")
    private Long tsUnid;
    @Column(name = "ts_name")
    private String tsName;
    @Column(name = "pa_unid")
    private Long paUnid;
    @Column(name = "pa_sub_name")
    private String paSubName;
    @Column(name = "act_sign_both")
    private Boolean actSignBoth;
    @Column(name = "reward_sum")
    private BigDecimal rewardSum;
    @Column(name = "docum_name")
    private String documName;
    @Column(name = "df_unid")
    private Long dfUnid;
    @Column(name = "df_name")
    private String dfName;
    @Column(name = "deal_unid")
    private Long dealUnid;
    @Column(name = "deal_num")
    private String dealNum;
    @Column(name = "sgt_unid")
    private Long sgtUnid;
    @Column(name = "sgt_name")
    private String sgtName;
    @Column(name = "act_reward_sum")
    private BigDecimal actRewardSum;
    @Column(name = "pa_bk_unid")
    private Long paBkUnid;
    @Column(name = "sub_bk_unid")
    private Long subBkUnid;
    @Column(name = "sub_bk_name")
    private String subBkName;

    @Column(name = "report_docum_unid")
    private Long reportDocumUnid;
    @Column(name = "report_docum_name")
    private String reportDocumName;
    @Column(name = "report_df_unid")
    private Long reportDfUnid;
    @Column(name = "report_df_name")
    private String reportDfName;
    @Column(name = "obj_tpa_unid")
    private String objTpaUnid;
    @Column(name = "obj_sale_date")
    @Temporal(TemporalType.DATE)
    private Date objSaleDate;
    @Column(name = "auction_unid")
    private Long auctionUnid;
    @Column(name = "type_auction_code")
    private Long typeAuctionCode;
    @Column(name = "sub_unid")
    private Long subUnid;
    @Column(name = "sub_name")
    private String subName;
    @Column(name = "sub_inn")
    private String subInn;


    public VAct() {
    }

    public Long getActUnid() {
        return actUnid;
    }

    public void setActUnid(Long actUnid) {
        this.actUnid = actUnid;
    }

    public Long getTaUnid() {
        return taUnid;
    }

    public void setTaUnid(Long taUnid) {
        this.taUnid = taUnid;
    }

    public String getActNum() {
        return actNum;
    }

    public void setActNum(String actNum) {
        this.actNum = actNum;
    }

    public Date getActDate() {
        return actDate;
    }

    public void setActDate(Date actDate) {
        this.actDate = actDate;
    }

    public Long getLotsCount() {
        return lotsCount;
    }

    public void setLotsCount(Long lotsCount) {
        this.lotsCount = lotsCount;
    }

    public String getLots() {
        return lots;
    }

    public void setLots(String lots) {
        this.lots = lots;
    }

    public Long getDocumUnid() {
        return documUnid;
    }

    public void setDocumUnid(Long documUnid) {
        this.documUnid = documUnid;
    }

    public Long getTsUnid() {
        return tsUnid;
    }

    public void setTsUnid(Long tsUnid) {
        this.tsUnid = tsUnid;
    }

    public Long getPaUnid() {
        return paUnid;
    }

    public void setPaUnid(Long paUnid) {
        this.paUnid = paUnid;
    }

    public Boolean getActSignBoth() {
        return actSignBoth;
    }

    public void setActSignBoth(Boolean actSignBoth) {
        this.actSignBoth = actSignBoth;
    }

    public BigDecimal getRewardSum() {
        return rewardSum;
    }

    public void setRewardSum(BigDecimal rewardSum) {
        this.rewardSum = rewardSum;
    }

    public String getTaName() {
        return taName;
    }

    public void setTaName(String taName) {
        this.taName = taName;
    }

    public String getTsName() {
        return tsName;
    }

    public void setTsName(String tsName) {
        this.tsName = tsName;
    }

    public String getPaSubName() {
        return paSubName;
    }

    public void setPaSubName(String paSubName) {
        this.paSubName = paSubName;
    }

    public String getDocumName() {
        return documName;
    }

    public void setDocumName(String documName) {
        this.documName = documName;
    }

    public Long getDfUnid() {
        return dfUnid;
    }

    public void setDfUnid(Long dfUnid) {
        this.dfUnid = dfUnid;
    }

    public String getDfName() {
        return dfName;
    }

    public void setDfName(String dfName) {
        this.dfName = dfName;
    }

    public Long getDealUnid() {
        return dealUnid;
    }

    public void setDealUnid(Long dealUnid) {
        this.dealUnid = dealUnid;
    }

    public String getDealNum() {
        return dealNum;
    }

    public void setDealNum(String dealNum) {
        this.dealNum = dealNum;
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

    public BigDecimal getActRewardSum() {
        return actRewardSum;
    }

    public void setActRewardSum(BigDecimal actRewardSum) {
        this.actRewardSum = actRewardSum;
    }

    public Long getPaBkUnid() {
        return paBkUnid;
    }

    public void setPaBkUnid(Long paBkUnid) {
        this.paBkUnid = paBkUnid;
    }

    public Long getSubBkUnid() {
        return subBkUnid;
    }

    public void setSubBkUnid(Long subBkUnid) {
        this.subBkUnid = subBkUnid;
    }

    public String getSubBkName() {
        return subBkName;
    }

    public void setSubBkName(String subBkName) {
        this.subBkName = subBkName;
    }

    public Long getReportDocumUnid() {
        return reportDocumUnid;
    }

    public void setReportDocumUnid(Long reportDocumUnid) {
        this.reportDocumUnid = reportDocumUnid;
    }

    public String getReportDocumName() {
        return reportDocumName;
    }

    public void setReportDocumName(String reportDocumName) {
        this.reportDocumName = reportDocumName;
    }

    public Long getReportDfUnid() {
        return reportDfUnid;
    }

    public void setReportDfUnid(Long reportDfUnid) {
        this.reportDfUnid = reportDfUnid;
    }

    public String getReportDfName() {
        return reportDfName;
    }

    public void setReportDfName(String reportDfName) {
        this.reportDfName = reportDfName;
    }

    public String getObjTpaUnid() {
        return objTpaUnid;
    }

    public void setObjTpaUnid(String objTpaUnid) {
        this.objTpaUnid = objTpaUnid;
    }

    public Date getObjSaleDate() {
        return objSaleDate;
    }

    public void setObjSaleDate(Date objSaleDate) {
        this.objSaleDate = objSaleDate;
    }

    public Long getAuctionUnid() {
        return auctionUnid;
    }

    public void setAuctionUnid(Long auctionUnid) {
        this.auctionUnid = auctionUnid;
    }

    public Long getTypeAuctionCode() {
        return typeAuctionCode;
    }

    public void setTypeAuctionCode(Long typeAuctionCode) {
        this.typeAuctionCode = typeAuctionCode;
    }

    public Long getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(Long subUnid) {
        this.subUnid = subUnid;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubInn() {
        return subInn;
    }

    public void setSubInn(String subInn) {
        this.subInn = subInn;
    }
}
