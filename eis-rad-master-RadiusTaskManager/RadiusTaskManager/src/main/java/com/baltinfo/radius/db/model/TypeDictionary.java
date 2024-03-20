/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baltinfo.radius.db.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

/**
 * @author kya
 */
@Entity
@Table(name = "type_dictionary", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"tdic_unid"})})
@SequenceGenerator(name = "seq_type_dictionary", sequenceName = "seq_type_dictionary", allocationSize = 1)
@XmlRootElement
public class TypeDictionary implements IHistory {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_type_dictionary")
    @Column(name = "tdic_unid", nullable = false, precision = 2147483647, scale = 0)
    private long tdicUnid;
    @Column(name = "found_unid")
    private BigInteger foundUnid;
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
    @Column(name = "tdic_name")
    private String tdicName;
    @Column(name = "tdic_table")
    private String tdicTable;
    @Column(name = "tdic_table_unid")
    private String tdicTableUnid;
    @Column(name = "tdic_table_name")
    private String tdicTableName;
    @Column(name = "tdic_note")
    private String tdicNote;
    @OneToMany(mappedBy = "tdicUnid")
    private Collection<DocTemplateParam> docTemplateParamCollection;

    public TypeDictionary() {
    }

    public TypeDictionary(long tdicUnid) {
        this.tdicUnid = tdicUnid;
    }

    public long getTdicUnid() {
        return tdicUnid;
    }

    public void setTdicUnid(long tdicUnid) {
        this.tdicUnid = tdicUnid;
    }

    public BigInteger getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(BigInteger foundUnid) {
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

    public String getTdicName() {
        return tdicName;
    }

    public void setTdicName(String tdicName) {
        this.tdicName = tdicName;
    }

    public String getTdicTable() {
        return tdicTable;
    }

    public void setTdicTable(String tdicTable) {
        this.tdicTable = tdicTable;
    }

    public String getTdicTableUnid() {
        return tdicTableUnid;
    }

    public void setTdicTableUnid(String tdicTableUnid) {
        this.tdicTableUnid = tdicTableUnid;
    }

    public String getTdicTableName() {
        return tdicTableName;
    }

    public void setTdicTableName(String tdicTableName) {
        this.tdicTableName = tdicTableName;
    }

    public String getTdicNote() {
        return tdicNote;
    }

    public void setTdicNote(String tdicNote) {
        this.tdicNote = tdicNote;
    }

    @XmlTransient
    public Collection<DocTemplateParam> getDocTemplateParamCollection() {
        return docTemplateParamCollection;
    }

    public void setDocTemplateParamCollection(Collection<DocTemplateParam> docTemplateParamCollection) {
        this.docTemplateParamCollection = docTemplateParamCollection;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.TypeDictionary[ tdicUnid=" + tdicUnid + " ]";
    }

}
