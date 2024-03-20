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
 * @author css
 */
@Entity
@Table(name = "object", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"obj_unid"})})
@SequenceGenerator(name = "seq_object", sequenceName = "seq_object", allocationSize = 1)
@NamedQuery(name = "ObjectJPA.findByObjCode", query = "SELECT o FROM ObjectJPA o where o.indActual = 1 and o.objCode = :objCode")
@XmlRootElement
public class ObjectJPA implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_object")
    @Column(name = "obj_unid", nullable = false, precision = 2147483647, scale = 0)
    private Long objUnid;
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
    @Column(name = "obj_code", length = 50)
    private String objCode;
    @Column(name = "obj_name", length = 2000)
    private String objName;
    @Column(name = "obj_ind_main")
    private Integer objIndMain;
    @Column(name = "obj_ind_common_eng")
    private Integer objIndCommonEng;
    @Column(name = "obj_count")
    private Long objCount;
    @Column(name = "obj_note", length = 2147483647)
    private String objNote;
    @Column(name = "ts_unid")
    private Long tsUnid;
    @Column(name = "to_unid")
    private Long toUnid;
    @Column(name = "sub_unid")
    private Long subUnid;
    @Column(name = "sub_cont_unid")
    private Long subContUnid;
    @Column(name = "sm_unid")
    private Long smUnid;
    @Column(name = "sg_unid")
    private Long sgUnid;
    @Column(name = "par_pa_unid")
    private Long parPaUnid;
    @Column(name = "pa_unid")
    private Long paUnid;
    @Column(name = "entity_unid")
    private Long entityUnid;
    @Column(name = "obj_reward_amount")
    private BigDecimal objRewardAmount;
    @Column(name = "obj_reward_proc", precision = 5, scale = 2)
    private BigDecimal objRewardProc;
    @Column(name = "obj_advance_proc", precision = 5, scale = 2)
    private BigDecimal objAdvanceProc;
    @Column(name = "obj_advance_amount")
    private BigDecimal objAdvanceAmount;
    @Column(name = "obj_offer_period")
    private Integer objOfferPeriod;
    @Column(name = "obj_offer_date_e")
    @Temporal(TemporalType.DATE)
    private Date objOfferDateE;
    @Column(name = "tper_unid")
    private Long tperUnid;
    @Column(name = "own_unid")
    private Long ownUnid;
//    @Column(name = "obj_link_site", length = 2000)
//    private String objLinkSite;
    @Column(name = "obj_impr_load", precision = 18, scale = 2)
    private BigDecimal objImprLoad;
    @Column(name = "obj_ind_summ")
    private Integer objIndSumm;
    @Column(name = "obj_ind_saled")
    private Integer objIndSaled;
    @Column(name = "obj_public_status")
    private Integer objPublicStatus;
    @Column(name = "obj_add_note")
    private String objAddNote;
    @Column(name = "ls_unid")
    private Long lsUnid;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "obj_sale_date")
    private Date objSaleDate;
    @Column(name = "obj_land_comman_sqr", precision = 18, scale = 2)
    private BigDecimal objLandCommanSqr; // Общая площадь ЗУ
    @Column(name = "obj_flat_comman_sqr", precision = 18, scale = 2)
    private BigDecimal objFlatCommanSqr; // Общая площадь помещений
    @Column(name = "obj_links")
    private String objLinks;
//    @Column(name = "obj_rad_site", length = 2000)
//    private String objRadSite;
    @Column(name = "pa_manager_unid")
    private Long paManagerUnid;
    @Column(name = "sgt_unid")
    private Long sgtUnid;
    @Column(name = "obj_pledge_url")
    private String objPledgeUrl;
    @Column(name = "tst_unid")
    private Long tstUnid;
    @Column(name = "obj_addr_feed")
    private String objAddrFeed;

    public ObjectJPA() {
    }

    public Long getObjUnid() {
        return objUnid;
    }

    public void setObjUnid(Long objUnid) {
        this.objUnid = objUnid;
    }

    @Override
    public Integer getIndActual() {
        return indActual;
    }

    @Override
    public void setIndActual(Integer indActual) {
        this.indActual = indActual;
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

    public Integer getObjIndMain() {
        return objIndMain;
    }

    public void setObjIndMain(Integer objIndMain) {
        this.objIndMain = objIndMain;
    }

    public Integer getObjIndCommonEng() {
        return objIndCommonEng;
    }

    public void setObjIndCommonEng(Integer objIndCommonEng) {
        this.objIndCommonEng = objIndCommonEng;
    }

    public Long getObjCount() {
        return objCount;
    }

    public void setObjCount(Long objCount) {
        this.objCount = objCount;
    }

    public String getObjNote() {
        return objNote;
    }

    public void setObjNote(String objNote) {
        this.objNote = objNote;
    }

    public Long getTsUnid() {
        return tsUnid;
    }

    public void setTsUnid(Long tsUnid) {
        this.tsUnid = tsUnid;
    }

    public Long getToUnid() {
        return toUnid;
    }

    public void setToUnid(Long toUnid) {
        this.toUnid = toUnid;
    }

    public Long getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(Long subUnid) {
        this.subUnid = subUnid;
    }

    public Long getSubContUnid() {
        return subContUnid;
    }

    public void setSubContUnid(Long subContUnid) {
        this.subContUnid = subContUnid;
    }

    public Long getSmUnid() {
        return smUnid;
    }

    public void setSmUnid(Long smUnid) {
        this.smUnid = smUnid;
    }

    public Long getSgUnid() {
        return sgUnid;
    }

    public void setSgUnid(Long sgUnid) {
        this.sgUnid = sgUnid;
    }

    public Long getParPaUnid() {
        return parPaUnid;
    }

    public void setParPaUnid(Long parPaUnid) {
        this.parPaUnid = parPaUnid;
    }

    public Long getEntityUnid() {
        return entityUnid;
    }

    public void setEntityUnid(Long entityUnid) {
        this.entityUnid = entityUnid;
    }

    public BigDecimal getObjRewardAmount() {
        return objRewardAmount;
    }

    public void setObjRewardAmount(BigDecimal objRewardAmount) {
        this.objRewardAmount = objRewardAmount;
    }

    public BigDecimal getObjRewardProc() {
        return objRewardProc;
    }

    public void setObjRewardProc(BigDecimal objRewardProc) {
        this.objRewardProc = objRewardProc;
    }

    public BigDecimal getObjAdvanceProc() {
        return objAdvanceProc;
    }

    public void setObjAdvanceProc(BigDecimal objAdvanceProc) {
        this.objAdvanceProc = objAdvanceProc;
    }

    public BigDecimal getObjAdvanceAmount() {
        return objAdvanceAmount;
    }

    public void setObjAdvanceAmount(BigDecimal objAdvanceAmount) {
        this.objAdvanceAmount = objAdvanceAmount;
    }

    public Integer getObjOfferPeriod() {
        return objOfferPeriod;
    }

    public void setObjOfferPeriod(Integer objOfferPeriod) {
        this.objOfferPeriod = objOfferPeriod;
    }

    public Date getObjOfferDateE() {
        return objOfferDateE;
    }

    public void setObjOfferDateE(Date objOfferDateE) {
        this.objOfferDateE = objOfferDateE;
    }

    public Long getTperUnid() {
        return tperUnid;
    }

    public void setTperUnid(Long tperUnid) {
        this.tperUnid = tperUnid;
    }

    public Long getOwnUnid() {
        return ownUnid;
    }

    public void setOwnUnid(Long ownUnid) {
        this.ownUnid = ownUnid;
    }

//    public String getObjLinkSite() {
//        return objLinkSite;
//    }
//
//    public void setObjLinkSite(String objLinkSite) {
//        this.objLinkSite = objLinkSite;
//    }

    public BigDecimal getObjImprLoad() {
        return objImprLoad;
    }

    public void setObjImprLoad(BigDecimal objImprLoad) {
        this.objImprLoad = objImprLoad;
    }

    public Integer getObjIndSumm() {
        return objIndSumm;
    }

    public void setObjIndSumm(Integer objIndSumm) {
        this.objIndSumm = objIndSumm;
    }

    public Integer getObjIndSaled() {
        return objIndSaled;
    }

    public void setObjIndSaled(Integer objIndSaled) {
        this.objIndSaled = objIndSaled;
    }

    public Integer getObjPublicStatus() {
        return objPublicStatus;
    }

    public void setObjPublicStatus(Integer objPublicStatus) {
        this.objPublicStatus = objPublicStatus;
    }

    public String getObjAddNote() {
        return objAddNote;
    }

    public void setObjAddNote(String objAddNote) {
        this.objAddNote = objAddNote;
    }

    public Long getPaUnid() {
        return paUnid;
    }

    public void setPaUnid(Long paUnid) {
        this.paUnid = paUnid;
    }

    public Long getLsUnid() {
        return lsUnid;
    }

    public void setLsUnid(Long lsUnid) {
        this.lsUnid = lsUnid;
    }

    public Date getObjSaleDate() {
        return objSaleDate;
    }

    public void setObjSaleDate(Date objSaleDate) {
        this.objSaleDate = objSaleDate;
    }

    public BigDecimal getObjLandCommanSqr() {
        return objLandCommanSqr;
    }

    public void setObjLandCommanSqr(BigDecimal objLandCommanSqr) {
        this.objLandCommanSqr = objLandCommanSqr;
    }

    public BigDecimal getObjFlatCommanSqr() {
        return objFlatCommanSqr;
    }

    public void setObjFlatCommanSqr(BigDecimal objFlatCommanSqr) {
        this.objFlatCommanSqr = objFlatCommanSqr;
    }

    public String getObjLinks() {
        return objLinks;
    }

    public void setObjLinks(String objLinks) {
        this.objLinks = objLinks;
    }

//    public String getObjRadSite() {
//        return objRadSite;
//    }
//
//    public void setObjRadSite(String objRadSite) {
//        this.objRadSite = objRadSite;
//    }

    public Long getPaManagerUnid() {
        return paManagerUnid;
    }

    public void setPaManagerUnid(Long paManagerUnid) {
        this.paManagerUnid = paManagerUnid;
    }

    public Long getSgtUnid() {
        return sgtUnid;
    }

    public void setSgtUnid(Long sgtUnid) {
        this.sgtUnid = sgtUnid;
    }

    public String getObjPledgeUrl() {
        return objPledgeUrl;
    }

    public void setObjPledgeUrl(String objPledgeUrl) {
        this.objPledgeUrl = objPledgeUrl;
    }

    public Long getTstUnid() {
        return tstUnid;
    }

    public void setTstUnid(Long tstUnid) {
        this.tstUnid = tstUnid;
    }

    public String getObjAddrFeed() {
        return objAddrFeed;
    }

    public void setObjAddrFeed(String objAddrFeed) {
        this.objAddrFeed = objAddrFeed;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.ObjectJPA[ objUnid=" + objUnid + " ]";
    }
}
