package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

@javax.persistence.Entity
@Table(name = "act", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"act_unid"})})
@SequenceGenerator(name = "seq_act", sequenceName = "seq_act", allocationSize = 1)
@XmlRootElement

public class Act implements IHistory {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_act")
    @Column(name = "act_unid", nullable = false, precision = 2147483647, scale = 0)
    private Long actUnid;
    @Column(name = "ind_actual")
    private Integer indActual;
    @Column(name = "date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateB;
    @Column(name = "act_date")
    @Temporal(TemporalType.DATE)
    private Date actDate;
    @Column(name = "act_num")
    private String actNum;
    @Column(name = "act_sign_both")
    private Boolean actSignBoth;
    @Column(name = "found_b")
    private String foundB;
    @Column(name = "pers_code_b")
    private Long persCodeB;
    @Column(name = "date_b_rec")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBRec;
    @Column(name = "pers_code_b_rec")
    private Long persCodeBRec;
    @Column(name = "ts_unid")
    private Long tsUnid;
    @Column(name = "entity_unid")
    private Long entityUnid;
    @Column(name = "pa_unid")
    private Long paUnid;
    @Column(name = "ta_unid")
    private Long taUnid;
    @Column(name = "act_reward_sum")
    private BigDecimal actRewardSum;
    @Column(name = "pa_bk_unid")
    private Long paBkUnid;
    @Column(name = "act_reason")
    private String actReason;
    @Column(name = "act_protocol_date")
    @Temporal(TemporalType.DATE)
    private Date actProtocolDate;
    @Column(name = "sub_unid")
    private Long subUnid;

    public Act() {
    }

    public Long getActUnid() {
        return actUnid;
    }

    public void setActUnid(Long actUnid) {
        this.actUnid = actUnid;
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

    public Date getActDate() {
        return actDate;
    }

    public void setActDate(Date actDate) {
        this.actDate = actDate;
    }

    public String getActNum() {
        return actNum;
    }

    public void setActNum(String actNum) {
        this.actNum = actNum;
    }

    public Boolean getActSignBoth() {
        return actSignBoth;
    }

    public void setActSignBoth(Boolean actSignBoth) {
        this.actSignBoth = actSignBoth;
    }

    public BigDecimal getActRewardSum() {
        return actRewardSum;
    }

    public void setActRewardSum(BigDecimal actRewardSum) {
        this.actRewardSum = actRewardSum;
    }

    public Long getTsUnid() {
        return tsUnid;
    }

    public void setTsUnid(Long tsUnid) {
        this.tsUnid = tsUnid;
    }

    public Long getEntityUnid() {
        return entityUnid;
    }

    public void setEntityUnid(Long entityUnid) {
        this.entityUnid = entityUnid;
    }

    public Long getPaUnid() {
        return paUnid;
    }

    public void setPaUnid(Long paUnid) {
        this.paUnid = paUnid;
    }

    public Long getTaUnid() {
        return taUnid;
    }

    public void setTaUnid(Long taUnid) {
        this.taUnid = taUnid;
    }

    public Long getPaBkUnid() {
        return paBkUnid;
    }

    public void setPaBkUnid(Long paBkUnid) {
        this.paBkUnid = paBkUnid;
    }

    public String getActReason() {
        return actReason;
    }

    public void setActReason(String actReason) {
        this.actReason = actReason;
    }

    public Date getActProtocolDate() {
        return actProtocolDate;
    }

    public void setActProtocolDate(Date actProtocolDate) {
        this.actProtocolDate = actProtocolDate;
    }

    public Long getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(Long subUnid) {
        this.subUnid = subUnid;
    }
}
