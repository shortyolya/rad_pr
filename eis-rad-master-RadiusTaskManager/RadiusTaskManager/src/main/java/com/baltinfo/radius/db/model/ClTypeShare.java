package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @since 29.01.2020
 */
@Entity
@Table(name = "cl_type_share", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"type_share_code"})})
@SequenceGenerator(name = "seq_cl_type_share", sequenceName = "seq_cl_type_share", allocationSize = 1)
@XmlRootElement
public class ClTypeShare implements IHistory, Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cl_type_share")
    @Column(name = "type_share_code", nullable = false, precision = 2147483647, scale = 0)
    private long typeShareCode;
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
    @Column(name = "type_share_name", length = 500)
    private String typeShareName;
    @Column(name = "type_share_sname", length = 100)
    private String typeShareSname;
    @Column(name = "type_share_note", length = 2147483647)
    private String typeShareNote;
    @Column(name = "found_unid")
    private Long foundUnid;
    @Column(name = "type_share_name_gen", length = 500)
    private String typeShareNameGen;


    public ClTypeShare() {
    }

    public ClTypeShare(long typeShareCode) {
        this.typeShareCode = typeShareCode;
    }

    public long getTypeShareCode() {
        return typeShareCode;
    }

    public void setTypeShareCode(long typeShareCode) {
        this.typeShareCode = typeShareCode;
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

    public String getTypeShareName() {
        return typeShareName;
    }

    public void setTypeShareName(String typeShareName) {
        this.typeShareName = typeShareName;
    }

    public String getTypeShareSname() {
        return typeShareSname;
    }

    public void setTypeShareSname(String typeShareSname) {
        this.typeShareSname = typeShareSname;
    }

    public String getTypeShareNote() {
        return typeShareNote;
    }

    public void setTypeShareNote(String typeShareNote) {
        this.typeShareNote = typeShareNote;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    public String getTypeShareNameGen() {
        return typeShareNameGen;
    }

    public void setTypeShareNameGen(String typeShareNameGen) {
        this.typeShareNameGen = typeShareNameGen;
    }

    public long getId() {
        return typeShareCode;
    }

    public void setId(long id) {
        typeShareCode = id;
    }

    public String getName() {
        return typeShareName;
    }

    public void setName(String name) {
        typeShareName = name;
    }

    public String getSname() {
        return typeShareSname;
    }

    public void setSname(String sname) {
        typeShareSname = sname;
    }

    public String toString() {
        return "com.baltinfo.model.model.ClTypeShare[ typeShareCode=" + typeShareCode + " ]";
    }
}