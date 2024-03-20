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
 * Журнал операций
 */
@Entity
@Table(name = "oper_journal", catalog = "", schema = "web", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"oj_unid"})})
@SequenceGenerator(name = "seq_oper_journal", sequenceName = "seq_oper_journal", allocationSize = 1)
@XmlRootElement
public class OperJournal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_oper_journal")
    @Column(name = "oj_unid", nullable = false, precision = 2147483647, scale = 0)
    private long ojUnid;
    @Column(name = "ind_actual")
    private Long indActual;
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
    @Column(name = "oj_oper_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ojOperTime;
    @Column(name = "oj_oper_type")
    private Integer ojOperType;
    @Column(name = "oj_oper_note", length = 2147483647)
    private String ojOperNote;
    @Column(name = "oj_oper_name", length = 2147483647)
    private String ojOperName;
    @Column(name = "oj_instance_unid")
    private Long ojInstanceUnid;
    @Column(name = "oj_obj_unid")
    private Long ojObjUnid;
    @Column(name = "ts_start_unid")
    private Long tsStartUnid;
    @Column(name = "ts_end_unid")
    private Long tsEndUnid;
    @Column(name = "entity_unid")
    private Long entityUnid;
    @Column(name = "pa_unid")
    private Long paUnid;

    public OperJournal() {
    }

    public OperJournal(long ojUnid) {
        this.ojUnid = ojUnid;
    }

    public long getOjUnid() {
        return ojUnid;
    }

    public void setOjUnid(long ojUnid) {
        this.ojUnid = ojUnid;
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

    public Date getOjOperTime() {
        return ojOperTime;
    }

    public void setOjOperTime(Date ojOperTime) {
        this.ojOperTime = ojOperTime;
    }

    public Integer getOjOperType() {
        return ojOperType;
    }

    public void setOjOperType(Integer ojOperType) {
        this.ojOperType = ojOperType;
    }

    public String getOjOperNote() {
        return ojOperNote;
    }

    public void setOjOperNote(String ojOperNote) {
        this.ojOperNote = ojOperNote;
    }

    public String getOjOperName() {
        return ojOperName;
    }

    public void setOjOperName(String ojOperName) {
        this.ojOperName = ojOperName;
    }

    public Long getOjInstanceUnid() {
        return ojInstanceUnid;
    }

    public void setOjInstanceUnid(Long ojInstanceUnid) {
        this.ojInstanceUnid = ojInstanceUnid;
    }

    public Long getOjObjUnid() {
        return ojObjUnid;
    }

    public void setOjObjUnid(Long ojObjUnid) {
        this.ojObjUnid = ojObjUnid;
    }

    public Long getTsStartUnid() {
        return tsStartUnid;
    }

    public void setTsStartUnid(Long tsStartUnid) {
        this.tsStartUnid = tsStartUnid;
    }

    public Long getTsEndUnid() {
        return tsEndUnid;
    }

    public void setTsEndUnid(Long tsEndUnid) {
        this.tsEndUnid = tsEndUnid;
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

    @Override
    public String toString() {
        return "com.baltinfo.model.model.OperJournal[ ojUnid=" + ojUnid + " ]";
    }

}
