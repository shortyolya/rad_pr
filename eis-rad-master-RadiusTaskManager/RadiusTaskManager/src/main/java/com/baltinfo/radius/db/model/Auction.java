/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baltinfo.radius.db.model;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
import java.util.Collection;
import java.util.Date;

/**
 * @author lia
 */
@Entity
@Table(name = "auction", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"auction_unid"})})
@SequenceGenerator(name = "seq_auction", sequenceName = "seq_auction", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Auction.findAll", query = "SELECT a FROM Auction a where a.indActual = 1"),
        @NamedQuery(name = "Auction.findByAuctionUnid", query = "SELECT a FROM Auction a WHERE a.auctionUnid = :auctionUnid"),
        @NamedQuery(name = "Auction.findMinStageForBlock", query = "select min(a.auctionStageNum) from Auction a, Lot l " +
                "where a.auctionUnid = l.auctionUnid and a.baUnid = :baUnid and l.lotNumber = :lotNumber " +
                "and a.indActual = 1 and l.indActual = 1"),
        @NamedQuery(name = "Auction.findAuctionUnidsWithoutEtpCode", query = "SELECT a.auctionUnid FROM Auction a " +
                "WHERE a.auctionEtpCode is null and a.indActual = 1"),
        @NamedQuery(name = "Auction.findByBaUnid", query = "SELECT a FROM Auction a " +
                "WHERE a.indActual = 1 and a.baUnid = :baUnid order by a.auctionStageNum, a.auctionUnid")
})
public class Auction implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_auction")
    @Column(name = "auction_unid")
    private Long auctionUnid;
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
    @Column(name = "auction_name")
    private String auctionName;
    @Column(name = "auction_reg_num")
    private String auctionRegNum;
    @Column(name = "auction_trade_form")
    private Integer auctionTradeForm;
    @Column(name = "auction_participant_limitation")
    private Integer auctionParticipantLimitation;
    @Column(name = "auction_step_form")
    private Integer auctionStepForm;
    @Column(name = "auction_reg_date")
    @Temporal(TemporalType.DATE)
    private Date auctionRegDate;
    @Column(name = "auction_date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionDateB;
    @Column(name = "auction_date_e")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionDateE;
    @Column(name = "auction_recep_date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionRecepDateB;
    @Column(name = "auction_recep_date_e")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionRecepDateE;
    @Column(name = "auction_dep_date_e")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionDepDateE;
    @Column(name = "auction_ind_down")
    private Long auctionIndDown;
    @Column(name = "auction_auctioneer")
    private String auctionAuctioneer;
    @Column(name = "auction_end_term")
    private Short auctionEndTerm;
    @Column(name = "auction_end_time")
    private Integer auctionEndTime;
    @Column(name = "auction_change_price_period")
    private Long auctionChangePricePeriod;
    @Column(name = "auction_ind_filing")
    private Long auctionIndFiling;
    @Column(name = "auction_ind_e_step")
    private Long auctionIndEStep;
    @Column(name = "auction_ind_join")
    private Long auctionIndJoin;
    @Column(name = "auction_publish_date_e")
    @Temporal(TemporalType.DATE)
    private Date auctionPublishDateE;
    @Column(name = "auction_applicat_date_e")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionApplicatDateE;
    @Column(name = "auction_refusal_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionRefusalDate;
    @Column(name = "auction_ind_step_down")
    private Long auctionIndStepDown;
    @Column(name = "auction_place")
    private String auctionPlace;
    @Column(name = "auction_place_appl")
    private String auctionPlaceAppl;
    @Column(name = "auction_bid_form")
    private String auctionBidForm;
    @Column(name = "auction_env_open_place")
    private String auctionEnvOpenPlace;
    @Column(name = "auction_env_open_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionEnvOpenDate;
    @Column(name = "auction_result_place")
    private String auctionResultPlace;
    @Column(name = "auction_result_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionResultDate;
    @Column(name = "auction_order_num")
    private String auctionOrderNum;
    @Column(name = "auction_order_date")
    @Temporal(TemporalType.DATE)
    private Date auctionOrderDate;
    @Column(name = "auction_foundation")
    private String auctionFoundation;
    @Column(name = "auction_dep_account_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionDepAccountDate;
    @Column(name = "auction_first_price_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionFirstPriceDate;
    @Column(name = "auction_recep_date_b_f")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionRecepDateBF;
    @Column(name = "auction_recep_date_e_f")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionRecepDateEF;
    @Column(name = "auction_appl_date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionApplDateB;
    @Column(name = "auction_appl_date_e")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionApplDateE;
    @Column(name = "auction_recep_place")
    private String auctionRecepPlace;
    @Column(name = "auction_ind_confirm_step")
    private Short auctionIndConfirmStep;//Признак подтверждения мин. предложения
    @Column(name = "auction_ind_multiplicity_step")
    private Short auctionIndMultiplicityStep;//Признак обязательности кратности шага
    @Column(name = "auction_ind_steps_together")
    private Short auctionIndStepTogether;//Признак возможности подачи предложений подряд одним участником
    @Column(name = "auction_ind_show_outside")
    private Short auctionIndShowOutside;
    @Column(name = "auction_transfer_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auctionTransferDate;
    @Column(name = "entity_unid")
    private Long entityUnid;
    @Column(name = "sg_unid")
    private Long sgUnid;
    @JoinColumn(name = "type_auction_unid", referencedColumnName = "type_auction_unid")
    @ManyToOne
    private TypeAuction typeAuctionUnid;
    @Column(name = "tpa_unid")
    private Long tpaUnid;
    @Column(name = "ts_unid")
    private Long tsUnid;
    @Column(name = "apl_unid")
    private Long aplUnid;
    @Column(name = "apl_env_open_unid")
    private Long aplEnvOpenUnid;
    @Column(name = "apl_recep_unid")
    private Long aplRecepUnid;
    @Column(name = "apl_result_unid")
    private Long aplResultUnid;
    @Column(name = "apl_appl_unid")
    private Long aplApplUnid;
    @JoinColumn(name = "sub_unid", referencedColumnName = "sub_unid")
    @ManyToOne
    private Subject subUnid;
    @Column(name = "sub_cust_unid")
    private Long subCustUnid;
    @JoinColumn(name = "dp_unid", referencedColumnName = "dp_unid")
    @ManyToOne
    private DebitorProperty dpUnid;
    @Column(name = "ba_unid")
    private Long baUnid;
    @Column(name = "auction_stage_num")
    private Integer auctionStageNum;
    @Column(name = "auction_asv_order_num")
    private String auctionAsvOrderNum;
    @Column(name = "auction_asv_id")
    private String auctionAsvId;
    @Column(name = "auction_etp_code")
    private String auctionEtpCode;
    @Column(name = "auction_efrsb_code")
    private String auctionEfrsbCode;
    @Column(name = "auction_note")
    private String auctionNote;
    @Column(name = "auction_etp_id")
    private Long auctionEtpId;
    @Column(name = "mp_unid")
    private Long mpUnid;
    @Column(name = "auction_ind_sign")
    private Boolean auctionIndSign;
    @Column(name = "auction_publicate_date")
    private Date auctionPublicateDate;
    @Column(name = "auction_ind_buy_at_start_price")
    private Boolean auctionIndBuyAtStartPrice;
    @Column(name = "auction_ind_req_confirm_by_two")
    private Boolean auctionIndReqConfirmByTwo;

    @Column(name = "auction_ind_special_terms")
    private Boolean auctionIndSpecialTerms;
    @Column(name = "auction_ind_revers")
    private Boolean auctionIndRevers;
    @Column(name = "auction_gis_id")
    private String auctionGisId;
    @Column(name = "auction_gis_link")
    private String auctionGisLink;
    @Column(name = "auction_ind_close_publication")
    private Integer auctionIndClosePublication;
    @Column(name = "auction_ind_no_deposit")
    private Integer auctionIndNoDeposit;
    @Column(name = "auction_deal_type_cost")
    private Integer auctionDealTypeCost;

    public Auction() {
    }

    public Auction(long auctionUnid) {
        this.auctionUnid = auctionUnid;
    }

    public Long getAuctionUnid() {
        return auctionUnid;
    }

    public void setAuctionUnid(Long auctionUnid) {
        this.auctionUnid = auctionUnid;
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

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }

    public String getAuctionRegNum() {
        return auctionRegNum;
    }

    public void setAuctionRegNum(String auctionRegNum) {
        this.auctionRegNum = auctionRegNum;
    }

    public Integer getAuctionTradeForm() {
        return auctionTradeForm;
    }

    public void setAuctionTradeForm(Integer auctionTradeForm) {
        this.auctionTradeForm = auctionTradeForm;
    }

    public Integer getAuctionParticipantLimitation() {
        return auctionParticipantLimitation;
    }

    public void setAuctionParticipantLimitation(Integer auctionParticipantLimitation) {
        this.auctionParticipantLimitation = auctionParticipantLimitation;
    }

    public Integer getAuctionStepForm() {
        return auctionStepForm;
    }

    public void setAuctionStepForm(Integer auctionStepForm) {
        this.auctionStepForm = auctionStepForm;
    }

    public Date getAuctionRegDate() {
        return auctionRegDate;
    }

    public void setAuctionRegDate(Date auctionRegDate) {
        this.auctionRegDate = auctionRegDate;
    }

    public Date getAuctionDateB() {
        return auctionDateB;
    }

    public void setAuctionDateB(Date auctionDateB) {
        this.auctionDateB = auctionDateB;
    }

    public Date getAuctionDateE() {
        return auctionDateE;
    }

    public void setAuctionDateE(Date auctionDateE) {
        this.auctionDateE = auctionDateE;
    }

    public Date getAuctionRecepDateB() {
        return auctionRecepDateB;
    }

    public void setAuctionRecepDateB(Date auctionRecepDateB) {
        this.auctionRecepDateB = auctionRecepDateB;
    }

    public Date getAuctionRecepDateE() {
        return auctionRecepDateE;
    }

    public void setAuctionRecepDateE(Date auctionRecepDateE) {
        this.auctionRecepDateE = auctionRecepDateE;
    }

    public Date getAuctionDepDateE() {
        return auctionDepDateE;
    }

    public void setAuctionDepDateE(Date auctionDepDateE) {
        this.auctionDepDateE = auctionDepDateE;
    }

    public Long getAuctionIndDown() {
        return auctionIndDown;
    }

    public void setAuctionIndDown(Long auctionIndDown) {
        this.auctionIndDown = auctionIndDown;
    }

    public String getAuctionAuctioneer() {
        return auctionAuctioneer;
    }

    public void setAuctionAuctioneer(String auctionAuctioneer) {
        this.auctionAuctioneer = auctionAuctioneer;
    }

    public Short getAuctionEndTerm() {
        return auctionEndTerm;
    }

    public void setAuctionEndTerm(Short auctionEndTerm) {
        this.auctionEndTerm = auctionEndTerm;
    }

    public Integer getAuctionEndTime() {
        return auctionEndTime;
    }

    public void setAuctionEndTime(Integer auctionEndTime) {
        this.auctionEndTime = auctionEndTime;
    }

    public Long getAuctionChangePricePeriod() {
        return auctionChangePricePeriod;
    }

    public void setAuctionChangePricePeriod(Long auctionChangePricePeriod) {
        this.auctionChangePricePeriod = auctionChangePricePeriod;
    }

    public Long getAuctionIndFiling() {
        return auctionIndFiling;
    }

    public void setAuctionIndFiling(Long auctionIndFiling) {
        this.auctionIndFiling = auctionIndFiling;
    }

    public Long getAuctionIndEStep() {
        return auctionIndEStep;
    }

    public void setAuctionIndEStep(Long auctionIndEStep) {
        this.auctionIndEStep = auctionIndEStep;
    }

    public Long getAuctionIndJoin() {
        return auctionIndJoin;
    }

    public void setAuctionIndJoin(Long auctionIndJoin) {
        this.auctionIndJoin = auctionIndJoin;
    }

    public Date getAuctionPublishDateE() {
        return auctionPublishDateE;
    }

    public void setAuctionPublishDateE(Date auctionPublishDateE) {
        this.auctionPublishDateE = auctionPublishDateE;
    }

    public Date getAuctionApplicatDateE() {
        return auctionApplicatDateE;
    }

    public void setAuctionApplicatDateE(Date auctionApplicatDateE) {
        this.auctionApplicatDateE = auctionApplicatDateE;
    }

    public Date getAuctionRefusalDate() {
        return auctionRefusalDate;
    }

    public void setAuctionRefusalDate(Date auctionRefusalDate) {
        this.auctionRefusalDate = auctionRefusalDate;
    }

    public Long getAuctionIndStepDown() {
        return auctionIndStepDown;
    }

    public void setAuctionIndStepDown(Long auctionIndStepDown) {
        this.auctionIndStepDown = auctionIndStepDown;
    }

    public String getAuctionPlace() {
        return auctionPlace;
    }

    public void setAuctionPlace(String auctionPlace) {
        this.auctionPlace = auctionPlace;
    }

    public String getAuctionPlaceAppl() {
        return auctionPlaceAppl;
    }

    public void setAuctionPlaceAppl(String auctionPlaceAppl) {
        this.auctionPlaceAppl = auctionPlaceAppl;
    }

    public String getAuctionBidForm() {
        return auctionBidForm;
    }

    public void setAuctionBidForm(String auctionBidForm) {
        this.auctionBidForm = auctionBidForm;
    }

    public String getAuctionEnvOpenPlace() {
        return auctionEnvOpenPlace;
    }

    public void setAuctionEnvOpenPlace(String auctionEnvOpenPlace) {
        this.auctionEnvOpenPlace = auctionEnvOpenPlace;
    }

    public Date getAuctionEnvOpenDate() {
        return auctionEnvOpenDate;
    }

    public void setAuctionEnvOpenDate(Date auctionEnvOpenDate) {
        this.auctionEnvOpenDate = auctionEnvOpenDate;
    }

    public String getAuctionResultPlace() {
        return auctionResultPlace;
    }

    public void setAuctionResultPlace(String auctionResultPlace) {
        this.auctionResultPlace = auctionResultPlace;
    }

    public Date getAuctionResultDate() {
        return auctionResultDate;
    }

    public void setAuctionResultDate(Date auctionResultDate) {
        this.auctionResultDate = auctionResultDate;
    }

    public String getAuctionOrderNum() {
        return auctionOrderNum;
    }

    public void setAuctionOrderNum(String auctionOrderNum) {
        this.auctionOrderNum = auctionOrderNum;
    }

    public Date getAuctionOrderDate() {
        return auctionOrderDate;
    }

    public void setAuctionOrderDate(Date auctionOrderDate) {
        this.auctionOrderDate = auctionOrderDate;
    }

    public String getAuctionFoundation() {
        return auctionFoundation;
    }

    public void setAuctionFoundation(String auctionFoundation) {
        this.auctionFoundation = auctionFoundation;
    }

    public Date getAuctionDepAccountDate() {
        return auctionDepAccountDate;
    }

    public void setAuctionDepAccountDate(Date auctionDepAccountDate) {
        this.auctionDepAccountDate = auctionDepAccountDate;
    }

    public Date getAuctionFirstPriceDate() {
        return auctionFirstPriceDate;
    }

    public void setAuctionFirstPriceDate(Date auctionFirstPriceDate) {
        this.auctionFirstPriceDate = auctionFirstPriceDate;
    }

    public Date getAuctionRecepDateBF() {
        return auctionRecepDateBF;
    }

    public void setAuctionRecepDateBF(Date auctionRecepDateBF) {
        this.auctionRecepDateBF = auctionRecepDateBF;
    }

    public Date getAuctionRecepDateEF() {
        return auctionRecepDateEF;
    }

    public void setAuctionRecepDateEF(Date auctionRecepDateEF) {
        this.auctionRecepDateEF = auctionRecepDateEF;
    }

    public Date getAuctionApplDateB() {
        return auctionApplDateB;
    }

    public void setAuctionApplDateB(Date auctionApplDateB) {
        this.auctionApplDateB = auctionApplDateB;
    }

    public Date getAuctionApplDateE() {
        return auctionApplDateE;
    }

    public void setAuctionApplDateE(Date auctionApplDateE) {
        this.auctionApplDateE = auctionApplDateE;
    }

    public String getAuctionRecepPlace() {
        return auctionRecepPlace;
    }

    public void setAuctionRecepPlace(String auctionRecepPlace) {
        this.auctionRecepPlace = auctionRecepPlace;
    }

    public Short getAuctionIndConfirmStep() {
        return auctionIndConfirmStep;
    }

    public void setAuctionIndConfirmStep(Short auctionIndConfirmStep) {
        this.auctionIndConfirmStep = auctionIndConfirmStep;
    }

    public Short getAuctionIndMultiplicityStep() {
        return auctionIndMultiplicityStep;
    }

    public void setAuctionIndMultiplicityStep(Short auctionIndMultiplicityStep) {
        this.auctionIndMultiplicityStep = auctionIndMultiplicityStep;
    }

    public Short getAuctionIndStepTogether() {
        return auctionIndStepTogether;
    }

    public void setAuctionIndStepTogether(Short auctionIndStepTogether) {
        this.auctionIndStepTogether = auctionIndStepTogether;
    }

    public Short getAuctionIndShowOutside() {
        return auctionIndShowOutside;
    }

    public void setAuctionIndShowOutside(Short auctionIndShowOutside) {
        this.auctionIndShowOutside = auctionIndShowOutside;
    }

    public Date getAuctionTransferDate() {
        return auctionTransferDate;
    }

    public void setAuctionTransferDate(Date auctionTransferDate) {
        this.auctionTransferDate = auctionTransferDate;
    }

    public Long getEntityUnid() {
        return entityUnid;
    }

    public void setEntityUnid(Long entityUnid) {
        this.entityUnid = entityUnid;
    }

    public Long getSgUnid() {
        return sgUnid;
    }

    public void setSgUnid(Long sgUnid) {
        this.sgUnid = sgUnid;
    }

    public TypeAuction getTypeAuctionUnid() {
        return typeAuctionUnid;
    }

    public void setTypeAuctionUnid(TypeAuction typeAuctionUnid) {
        this.typeAuctionUnid = typeAuctionUnid;
    }

    public Long getTpaUnid() {
        return tpaUnid;
    }

    public void setTpaUnid(Long tpaUnid) {
        this.tpaUnid = tpaUnid;
    }

    public Long getTsUnid() {
        return tsUnid;
    }

    public void setTsUnid(Long tsUnid) {
        this.tsUnid = tsUnid;
    }

    public Long getAplUnid() {
        return aplUnid;
    }

    public void setAplUnid(Long aplUnid) {
        this.aplUnid = aplUnid;
    }

    public Long getAplEnvOpenUnid() {
        return aplEnvOpenUnid;
    }

    public void setAplEnvOpenUnid(Long aplEnvOpenUnid) {
        this.aplEnvOpenUnid = aplEnvOpenUnid;
    }

    public Long getAplRecepUnid() {
        return aplRecepUnid;
    }

    public void setAplRecepUnid(Long aplRecepUnid) {
        this.aplRecepUnid = aplRecepUnid;
    }

    public Long getAplResultUnid() {
        return aplResultUnid;
    }

    public void setAplResultUnid(Long aplResultUnid) {
        this.aplResultUnid = aplResultUnid;
    }

    public Long getAplApplUnid() {
        return aplApplUnid;
    }

    public void setAplApplUnid(Long aplApplUnid) {
        this.aplApplUnid = aplApplUnid;
    }

    public Subject getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(Subject subUnid) {
        this.subUnid = subUnid;
    }

    public Long getSubCustUnid() {
        return subCustUnid;
    }

    public void setSubCustUnid(Long subCustUnid) {
        this.subCustUnid = subCustUnid;
    }

    public DebitorProperty getDpUnid() {
        return dpUnid;
    }

    public void setDpUnid(DebitorProperty dpUnid) {
        this.dpUnid = dpUnid;
    }

    public Long getBaUnid() {
        return baUnid;
    }

    public void setBaUnid(Long baUnid) {
        this.baUnid = baUnid;
    }

    public Integer getAuctionStageNum() {
        return auctionStageNum;
    }

    public void setAuctionStageNum(Integer auctionStageNum) {
        this.auctionStageNum = auctionStageNum;
    }

    public String getAuctionAsvOrderNum() {
        return auctionAsvOrderNum;
    }

    public void setAuctionAsvOrderNum(String auctionAsvOrderNum) {
        this.auctionAsvOrderNum = auctionAsvOrderNum;
    }

    public String getAuctionAsvId() {
        return auctionAsvId;
    }

    public void setAuctionAsvId(String auctionAsvId) {
        this.auctionAsvId = auctionAsvId;
    }

    public String getAuctionEtpCode() {
        return auctionEtpCode;
    }

    public void setAuctionEtpCode(String auctionEtpCode) {
        this.auctionEtpCode = auctionEtpCode;
    }

    public String getAuctionEfrsbCode() {
        return auctionEfrsbCode;
    }

    public void setAuctionEfrsbCode(String auctionEfrsbCode) {
        this.auctionEfrsbCode = auctionEfrsbCode;
    }

    public String getAuctionNote() {
        return auctionNote;
    }

    public void setAuctionNote(String auctionNote) {
        this.auctionNote = auctionNote;
    }

    public Long getAuctionEtpId() {
        return auctionEtpId;
    }

    public void setAuctionEtpId(Long auctionEtpId) {
        this.auctionEtpId = auctionEtpId;
    }

    public Long getMpUnid() {
        return mpUnid;
    }

    public void setMpUnid(Long mpUnid) {
        this.mpUnid = mpUnid;
    }

    public Boolean getAuctionIndSign() {
        return auctionIndSign;
    }

    public void setAuctionIndSign(Boolean auctionIndSign) {
        this.auctionIndSign = auctionIndSign;
    }

    public Date getAuctionPublicateDate() {
        return auctionPublicateDate;
    }

    public void setAuctionPublicateDate(Date auctionPublicateDate) {
        this.auctionPublicateDate = auctionPublicateDate;
    }

    public Boolean getAuctionIndBuyAtStartPrice() {
        return auctionIndBuyAtStartPrice;
    }

    public void setAuctionIndBuyAtStartPrice(Boolean auctionIndBuyAtStartPrice) {
        this.auctionIndBuyAtStartPrice = auctionIndBuyAtStartPrice;
    }

    public Boolean getAuctionIndReqConfirmByTwo() {
        return auctionIndReqConfirmByTwo;
    }

    public void setAuctionIndReqConfirmByTwo(Boolean auctionIndReqConfirmByTwo) {
        this.auctionIndReqConfirmByTwo = auctionIndReqConfirmByTwo;
    }

    public Integer getAuctionDealTypeCost() {
        return auctionDealTypeCost;
    }

    public void setAuctionDealTypeCost(Integer auctionDealTypeCost) {
        this.auctionDealTypeCost = auctionDealTypeCost;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.Auction[ auctionUnid=" + auctionUnid + " ]";
    }

    public Auction clone() {
        Auction auction = new Auction();
        auction.auctionName = this.auctionName;
        auction.auctionRegNum = this.auctionRegNum;
        auction.auctionTradeForm = this.auctionTradeForm;
        auction.auctionParticipantLimitation = this.auctionParticipantLimitation;
        auction.auctionStepForm = this.auctionStepForm;
        auction.auctionRegDate = this.auctionRegDate;
        auction.auctionDateB = this.auctionDateB;
        auction.auctionDateE = this.auctionDateE;
        auction.auctionRecepDateB = this.auctionRecepDateB;
        auction.auctionRecepDateE = this.auctionRecepDateE;
        auction.auctionDepDateE = this.auctionDepDateE;
        auction.auctionIndDown = this.auctionIndDown;
        auction.auctionAuctioneer = this.auctionAuctioneer;
        auction.auctionEndTerm = this.auctionEndTerm;
        auction.auctionEndTime = this.auctionEndTime;
        auction.auctionChangePricePeriod = this.auctionChangePricePeriod;
        auction.auctionIndFiling = this.auctionIndFiling;
        auction.auctionIndEStep = this.auctionIndEStep;
        auction.auctionIndJoin = this.auctionIndJoin;
        auction.auctionPublishDateE = this.auctionPublishDateE;
        auction.auctionApplicatDateE = this.auctionApplicatDateE;
        auction.auctionRefusalDate = this.auctionRefusalDate;
        auction.auctionIndStepDown = this.auctionIndStepDown;
        auction.auctionPlace = this.auctionPlace;
        auction.auctionPlaceAppl = this.auctionPlaceAppl;
        auction.auctionBidForm = this.auctionBidForm;
        auction.auctionEnvOpenPlace = this.auctionEnvOpenPlace;
        auction.auctionEnvOpenDate = this.auctionEnvOpenDate;
        auction.auctionResultPlace = this.auctionResultPlace;
        auction.auctionResultDate = this.auctionResultDate;
        auction.auctionOrderNum = this.auctionOrderNum;
        auction.auctionOrderDate = this.auctionOrderDate;
        auction.auctionFoundation = this.auctionFoundation;
        auction.auctionDepAccountDate = this.auctionDepAccountDate;
        auction.auctionFirstPriceDate = this.auctionFirstPriceDate;
        auction.auctionRecepDateBF = this.auctionRecepDateBF;
        auction.auctionRecepDateEF = this.auctionRecepDateEF;
        auction.auctionApplDateB = this.auctionApplDateB;
        auction.auctionApplDateE = this.auctionApplDateE;
        auction.auctionRecepPlace = this.auctionRecepPlace;
        auction.auctionIndConfirmStep = this.auctionIndConfirmStep;
        auction.auctionIndMultiplicityStep = this.auctionIndMultiplicityStep;
        auction.auctionIndStepTogether = this.auctionIndStepTogether;
        auction.auctionIndShowOutside = this.auctionIndShowOutside;
        auction.auctionTransferDate = this.auctionTransferDate;
        auction.entityUnid = this.entityUnid;
        auction.sgUnid = this.sgUnid;
        auction.typeAuctionUnid = this.typeAuctionUnid;
        auction.tpaUnid = this.tpaUnid;
        auction.tsUnid = this.tsUnid;
        auction.aplUnid = this.aplUnid;
        auction.aplEnvOpenUnid = this.aplEnvOpenUnid;
        auction.aplRecepUnid = this.aplRecepUnid;
        auction.aplResultUnid = this.aplResultUnid;
        auction.aplApplUnid = this.aplApplUnid;
        auction.subUnid = this.subUnid;
        auction.subCustUnid = this.subCustUnid;
        auction.baUnid = this.baUnid;
        auction.auctionStageNum = this.auctionStageNum;
        auction.auctionAsvOrderNum = this.auctionAsvOrderNum;
        auction.auctionAsvId = this.auctionAsvId;
        auction.auctionNote = this.auctionNote;
        auction.auctionIndSpecialTerms = this.auctionIndSpecialTerms;
        auction.auctionIndReqConfirmByTwo = this.auctionIndReqConfirmByTwo;
        auction.auctionIndSign = this.auctionIndSign;
        auction.auctionIndBuyAtStartPrice = this.auctionIndBuyAtStartPrice;
        auction.auctionIndRevers = this.auctionIndRevers;
        auction.auctionIndNoDeposit = this.auctionIndNoDeposit;
        auction.auctionIndClosePublication = this.auctionIndClosePublication;
        auction.auctionDealTypeCost = this.auctionDealTypeCost;
        return auction;
    }



    public String getAuctionAsvIdWithoutStage() {
        if (auctionAsvId == null || auctionAsvId.isEmpty()) {
            return null;
        }
        return StringUtils.substringBefore(auctionAsvId.trim(), "/");
    }
}
