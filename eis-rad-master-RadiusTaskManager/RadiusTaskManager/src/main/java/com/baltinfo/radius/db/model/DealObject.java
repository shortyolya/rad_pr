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
import java.util.Date;

/**
 * @author kya
 */
@Entity
@Table(name = "deal_object", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"do_unid"})})
@SequenceGenerator(name = "seq_deal_object", sequenceName = "seq_deal_object", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DealObject.findByObjDealUnid", query = "SELECT d FROM DealObject d where d.indActual = 1 AND d.objUnid = :objUnid AND d.dealUnid.dealUnid = :dealUnid")
})
public class DealObject {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_deal_object")
    @Basic(optional = false)
    @Column(name = "do_unid", nullable = false, precision = 2147483647, scale = 0)
    private long doUnid;
    @Column(name = "ind_actual")
    private Long indActual;
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
    @Column(name = "do_num")
    private Integer doNum;
    @JoinColumn(name = "deal_unid", referencedColumnName = "deal_unid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Deal dealUnid;
    @Column(name = "obj_unid")
    private Long objUnid;

    public DealObject() {
    }

    public DealObject(long doUnid) {
        this.doUnid = doUnid;
    }

    public long getDoUnid() {
        return doUnid;
    }

    public void setDoUnid(long doUnid) {
        this.doUnid = doUnid;
    }

    public Long getIndActual() {
        return indActual;
    }

    public void setIndActual(Long indActual) {
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

    public Integer getDoNum() {
        return doNum;
    }

    public void setDoNum(Integer doNum) {
        this.doNum = doNum;
    }

    public Deal getDealUnid() {
        return dealUnid;
    }

    public void setDealUnid(Deal dealUnid) {
        this.dealUnid = dealUnid;
    }

    public Long getObjUnid() {
        return objUnid;
    }

    public void setObjUnid(Long objUnid) {
        this.objUnid = objUnid;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.DealObject[ doUnid=" + doUnid + " ]";
    }

}
