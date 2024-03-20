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
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * @author kya
 */
@Entity
@Table(name = "obj_role", schema = "web")
@SequenceGenerator(name = "seq_obj_role", sequenceName = "seq_obj_role", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "ObjRole.findSaleObjRole", query = "select o from ObjRole o where o.indActual = 1 and o.orIndSale = 1 and o.objUnid.objUnid = :objUnid"),
        @NamedQuery(name = "ObjRole.findCreatorObjRole", query = "select o from ObjRole o where o.indActual = 1 and o.orIndCreate = 1 and o.objUnid.objUnid = :objUnid")
})
@XmlRootElement
public class ObjRole implements Serializable, IHistory {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_obj_role")
    @Column(name = "or_unid", nullable = false, precision = 2147483647, scale = 0)
    private long orUnid;
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
    @Column(name = "or_ind_create")
    private Integer orIndCreate;
    @Column(name = "or_ind_sale")
    private Integer orIndSale;
    @JoinColumn(name = "obj_unid", referencedColumnName = "obj_unid")
    @ManyToOne
    private ObjectJPA objUnid;
    @Column(name = "tpa_unid")
    private Long tpaUnid;

    public ObjRole() {
    }

    public ObjRole(long orUnid) {
        this.orUnid = orUnid;
    }

    public long getOrUnid() {
        return orUnid;
    }

    public void setOrUnid(long orUnid) {
        this.orUnid = orUnid;
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

    public Integer getOrIndCreate() {
        return orIndCreate;
    }

    public void setOrIndCreate(Integer orIndCreate) {
        this.orIndCreate = orIndCreate;
    }

    public ObjectJPA getObjUnid() {
        return objUnid;
    }

    public void setObjUnid(ObjectJPA objUnid) {
        this.objUnid = objUnid;
    }

    public Long getTpaUnid() {
        return tpaUnid;
    }

    public void setTpaUnid(Long tpaUnid) {
        this.tpaUnid = tpaUnid;
    }

    public Integer getOrIndSale() {
        return orIndSale;
    }

    public void setOrIndSale(Integer orIndSale) {
        this.orIndSale = orIndSale;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.ObjRole[ orUnid=" + orUnid + " ]";
    }

}
