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
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 10.02.2020
 */

@Entity
@Table(name = "obj_sale_category", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"osc_unid"})})
@SequenceGenerator(name = "seq_obj_sale_category", sequenceName = "seq_obj_sale_category", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "ObjSaleCategory.findByObjUnid",
                query = "SELECT v FROM ObjSaleCategory v join fetch v.scUnid where v.objUnid = :objUnid and v.indActual = 1 order by v.scUnid"),
        @NamedQuery(name = "ObjSaleCategory.findLeavesByObjUnid",
                query = "SELECT v FROM ObjSaleCategory v join fetch v.scUnid " +
                        "where v.objUnid = :objUnid " +
                        "and v.indActual = 1 " +
                        "and not exists " +
                        "(select sc from SaleCategory sc " +
                        "where sc.parentScUnid = v.scUnid " +
                        "and sc.indActual = 1)" +
                        " order by v.scUnid"),
        @NamedQuery(name = "ObjSaleCategory.findByScUnid",
                query = "SELECT v FROM ObjSaleCategory v where v.scUnid = :scUnid and v.indActual = 1")
})
@XmlRootElement
public class ObjSaleCategory implements IHistory, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_obj_sale_category")
    @Column(name = "osc_unid", nullable = false, precision = 2147483647, scale = 0)
    private long oscUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
    @Column(name = "obj_unid", nullable = false)
    private Long objUnid;
    @JoinColumn(name = "sc_unid", referencedColumnName = "sc_unid")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private SaleCategory scUnid;
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
    @Column(name = "osc_ind_main", nullable = false)
    private Boolean oscIndMain;

    public ObjSaleCategory() {
    }

    public long getOscUnid() {
        return oscUnid;
    }

    public void setOscUnid(long oscUnid) {
        this.oscUnid = oscUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    public Long getObjUnid() {
        return objUnid;
    }

    public void setObjUnid(Long objUnid) {
        this.objUnid = objUnid;
    }

    public SaleCategory getScUnid() {
        return scUnid;
    }

    public void setScUnid(SaleCategory scUnid) {
        this.scUnid = scUnid;
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

    public Boolean getOscIndMain() {
        return oscIndMain;
    }

    public void setOscIndMain(Boolean oscIndMain) {
        this.oscIndMain = oscIndMain;
    }
}
