package com.baltinfo.radius.db.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author ssv
 * @since 10.07.2019
 */
@Entity
@Table(name = "type_obj_descr", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"tod_unid"})})
@SequenceGenerator(name = "seq_type_obj_descr", sequenceName = "seq_type_obj_descr", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "TypeObjDescr.findByDsubUnid",
                query = "SELECT v FROM TypeObjDescr v WHERE v.indActual = 1 AND v.dsubUnid = :dsubUnid ORDER BY v.todUnid")})
@XmlRootElement
public class TypeObjDescr implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_type_obj_descr")
    @Column(name = "tod_unid")
    private long todUnid;
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
    @Column(name = "tod_name", length = 2147483647)
    private String todName;
    @Column(name = "tod_sname", length = 2147483647)
    private String todSname;
    @Column(name = "dsub_unid")
    private Integer dsubUnid;

    public TypeObjDescr() {
    }

    public TypeObjDescr(long todUnid) {
        this.todUnid = todUnid;
    }

    public long getTodUnid() {
        return todUnid;
    }

    public void setTodUnid(long todUnid) {
        this.todUnid = todUnid;
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

    public String getTodName() {
        return todName;
    }

    public void setTodName(String todName) {
        this.todName = todName;
    }

    public String getTodSname() {
        return todSname;
    }

    public void setTodSname(String todSname) {
        this.todSname = todSname;
    }

    public Integer getDsubUnid() {
        return dsubUnid;
    }

    public void setDsubUnid(Integer dsubUnid) {
        this.dsubUnid = dsubUnid;
    }

    @Override
    public String toString() {
        return "com.baltinfo.radius.db.model.TypeObjDescr[ todUnid=" + todUnid + " ]";
    }
}
