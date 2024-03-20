package com.baltinfo.radius.db.model.dep;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * @author css
 */
@Entity
@Table(name = "trade_account", catalog = "", schema = "DEP", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"tac_unid"})})
@XmlRootElement
public class TradeAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "tac_unid", nullable = false, precision = 2147483647, scale = 0)
    private long tacUnid;
    @Column(name = "found_unid")
    private Integer foundUnid;
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
    @Column(name = "tac_ind")
    private Integer tacInd;
    @Basic(optional = false)
    @Column(name = "trade_list_unid", nullable = false)
    private Long tradeListUnid;
    @Basic(optional = false)
    @Column(name = "adep_unid", nullable = false)
    private Long adepUnid;
    @Basic(optional = false)
    @Column(name = "tac_ep_unid")
    private Long tacEpUnid;

    public TradeAccount() {
    }

    public TradeAccount(long tacUnid) {
        this.tacUnid = tacUnid;
    }

    public long getTacUnid() {
        return tacUnid;
    }

    public void setTacUnid(long tacUnid) {
        this.tacUnid = tacUnid;
    }

    public Integer getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Integer foundUnid) {
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

    public Integer getTacInd() {
        return tacInd;
    }

    public void setTacInd(Integer tacInd) {
        this.tacInd = tacInd;
    }

    public Long getTradeListUnid() {
        return tradeListUnid;
    }

    public void setTradeListUnid(Long tradeListUnid) {
        this.tradeListUnid = tradeListUnid;
    }

    public Long getAdepUnid() {
        return adepUnid;
    }

    public void setAdepUnid(Long adepUnid) {
        this.adepUnid = adepUnid;
    }

    public Long getTacEpUnid() {
        return tacEpUnid;
    }

    public void setTacEpUnid(Long tacEpUnid) {
        this.tacEpUnid = tacEpUnid;
    }


}
