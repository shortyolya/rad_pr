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
@Table(name = "type_vehicle", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"tv_unid"})})
@SequenceGenerator(name = "seq_type_vehicle", sequenceName = "seq_type_vehicle", allocationSize = 1)
@XmlRootElement
public class TypeVehicle implements IHistory, Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_type_vehicle")
    @Column(name = "tv_unid", nullable = false, precision = 2147483647, scale = 0)
    private long tvUnid;
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
    @Column(name = "tv_name", length = 2147483647)
    private String tvName;
    @Column(name = "tv_sname", length = 2147483647)
    private String tvSname;
    @Column(name = "tv_note", length = 2147483647)
    private String tvNote;
    @Column(name = "found_unid")
    private Long foundUnid;
    @Column(name = "tv_ind_car")
    private Integer tvIndCar;

    public TypeVehicle() {
    }

    public TypeVehicle(long tvUnid) {
        this.tvUnid = tvUnid;
    }

    public long getTvUnid() {
        return tvUnid;
    }

    public void setTvUnid(long tvUnid) {
        this.tvUnid = tvUnid;
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

    public void setPersCodeBRec(Long persCodeBRec) {
        this.persCodeBRec = persCodeBRec;
    }

    public String getTvName() {
        return tvName;
    }

    public void setTvName(String tvName) {
        this.tvName = tvName;
    }

    public String getTvSname() {
        return tvSname;
    }

    public void setTvSname(String tvSname) {
        this.tvSname = tvSname;
    }

    public String getTvNote() {
        return tvNote;
    }

    public void setTvNote(String tvNote) {
        this.tvNote = tvNote;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    public long getId() {
        return tvUnid;
    }

    public void setId(long id) {
        tvUnid = id;
    }

    public String getName() {
        return tvName;
    }

    public void setName(String name) {
        tvName = name;
    }

    public String getSname() {
        return tvSname;
    }

    public void setSname(String sname) {
        tvSname = sname;
    }

    public Boolean getTvIndCar() {
        return tvIndCar == 1;
    }

    public void setTvIndCar(Boolean tvIndCar) {
        this.tvIndCar = tvIndCar ? 1 : 0;

    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.TypeVehicle[ tvUnid=" + tvUnid + " ]";
    }
}