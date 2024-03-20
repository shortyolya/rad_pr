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
import java.util.Date;

/**
 * СРО
 *
 * @author AAA
 * @since 13.04.2023
 */
@Entity
@Table(name = "sro", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"sro_id"})})
@SequenceGenerator(name = "seq_sro", sequenceName = "seq_sro", allocationSize = 1)
@XmlRootElement
public class SRO {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sro")
    @Column(name = "sro_id", nullable = false, precision = 2147483647, scale = 0)
    private long sroId;
    @Column(name = "found_unid")
    private Long foundUnid;
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
    @Column(name = "sro_name", length = 2000)
    private String sroName;


    public String getName() {
        return sroName;
    }

    public void setName(String name) {
        sroName = name;
    }

    public String getSname() {
        return null;
    }

    public void setSname(String sname) {

    }


    public Integer getIndActual() {
        return Math.toIntExact(indActual);
    }


    public void setIndActual(Integer indActual) {
        this.indActual = Long.valueOf(indActual);
    }

    public long getSroId() {
        return sroId;
    }

    public void setSroId(long sroId) {
        this.sroId = sroId;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
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

    public String getSroName() {
        return sroName;
    }

    public void setSroName(String sroName) {
        this.sroName = sroName;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SRO that = (SRO) o;

        return sroId == that.sroId;
    }


    public int hashCode() {
        return (int) (sroId ^ (sroId >>> 32));
    }
}
