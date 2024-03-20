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
import java.util.Date;

/**
 * <p>
 *     Entity для таблицы журнала загрузки торгов
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 20.02.2020
 */


@Entity
@Table(name = "load_journal", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"lj_unid"})})
@SequenceGenerator(name = "seq_load_journal", sequenceName = "seq_load_journal", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "LoadJournal.findByLjUnid", query = "SELECT lj FROM LoadJournal lj WHERE lj.ljUnid = :ljUnid")
})
public class LoadJournal implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_load_journal")
    @Column(name = "lj_unid")
    private Long ljUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
    @Column(name = "ba_unid")
    private Long baUnid;
    @Column(name = "lst_unid")
    private Long lstUnid;
    @Column(name = "la_unid")
    private Long laUnid;
    @Column(name = "pa_unid")
    private Long paUnid;
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
    @Column(name = "lj_debtor_name")
    private String ljDebtorName;
    @Column(name = "lj_debtor_inn")
    private String ljDebtorInn;
    @Column(name = "lj_debtor_bic")
    private String ljDebtorBic;
    @Column(name = "lj_debtor_ogrn")
    private String ljDebtorOgrn;
    @Column(name = "lj_type_trade")
    private String ljTypeTrade;
    @Column(name = "lj_order_num")
    private String ljOrderNum;
    @Column(name = "lj_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ljDate;
    @Column(name = "lj_result")
    private String ljResult;

    public LoadJournal() {
    }

    public Long getLjUnid() {
        return ljUnid;
    }

    public void setLjUnid(Long ljUnid) {
        this.ljUnid = ljUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    public Long getBaUnid() {
        return baUnid;
    }

    public void setBaUnid(Long baUnid) {
        this.baUnid = baUnid;
    }

    public Long getLstUnid() {
        return lstUnid;
    }

    public void setLstUnid(Long lstUnid) {
        this.lstUnid = lstUnid;
    }

    public Long getLaUnid() {
        return laUnid;
    }

    public void setLaUnid(Long laUnid) {
        this.laUnid = laUnid;
    }

    public Long getPaUnid() {
        return paUnid;
    }

    public void setPaUnid(Long paUnid) {
        this.paUnid = paUnid;
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

    public String getLjDebtorName() {
        return ljDebtorName;
    }

    public void setLjDebtorName(String ljDebtorName) {
        this.ljDebtorName = ljDebtorName;
    }

    public String getLjDebtorInn() {
        return ljDebtorInn;
    }

    public void setLjDebtorInn(String ljDebtorInn) {
        this.ljDebtorInn = ljDebtorInn;
    }

    public String getLjDebtorBic() {
        return ljDebtorBic;
    }

    public void setLjDebtorBic(String ljDebtorBic) {
        this.ljDebtorBic = ljDebtorBic;
    }

    public String getLjDebtorOgrn() {
        return ljDebtorOgrn;
    }

    public void setLjDebtorOgrn(String ljDebtorOgrn) {
        this.ljDebtorOgrn = ljDebtorOgrn;
    }

    public String getLjTypeTrade() {
        return ljTypeTrade;
    }

    public void setLjTypeTrade(String ljTypeTrade) {
        this.ljTypeTrade = ljTypeTrade;
    }

    public String getLjOrderNum() {
        return ljOrderNum;
    }

    public void setLjOrderNum(String ljOrderNum) {
        this.ljOrderNum = ljOrderNum;
    }

    public Date getLjDate() {
        return ljDate;
    }

    public void setLjDate(Date ljDate) {
        this.ljDate = ljDate;
    }

    public String getLjResult() {
        return ljResult;
    }

    public void setLjResult(String ljResult) {
        this.ljResult = ljResult;
    }
}
