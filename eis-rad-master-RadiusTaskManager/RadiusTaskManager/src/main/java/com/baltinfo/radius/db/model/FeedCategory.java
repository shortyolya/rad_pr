package com.baltinfo.radius.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "feed_category", schema = "web")
public class FeedCategory {

    @Id
    @Column(name = "fc_unid")
    private Long fcUnid;
    @Column(name = "fc_code")
    private String fcCode;
    @Column(name = "mev_unid")
    private Long mevUnid;
    @Column(name = "fc_ind_rent")
    private Integer fcIndRent;
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
    @Column(name = "fc_ind_export")
    private Boolean fcIndExport;
    @Column(name = "fcg_unid")
    private Long fcgUnid;

    public Long getFcUnid() {
        return fcUnid;
    }

    public void setFcUnid(Long fcUnid) {
        this.fcUnid = fcUnid;
    }

    public String getFcCode() {
        return fcCode;
    }

    public void setFcCode(String fcCode) {
        this.fcCode = fcCode;
    }

    public Long getMevUnid() {
        return mevUnid;
    }

    public void setMevUnid(Long mevUnid) {
        this.mevUnid = mevUnid;
    }

    public Integer getFcIndRent() {
        return fcIndRent;
    }

    public void setFcIndRent(Integer fcIndRent) {
        this.fcIndRent = fcIndRent;
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

    public Boolean getFcIndExport() {
        return fcIndExport;
    }

    public void setFcIndExport(Boolean fcIndExport) {
        this.fcIndExport = fcIndExport;
    }

    public Long getFcgUnid() {
        return fcgUnid;
    }

    public void setFcgUnid(Long fcgUnid) {
        this.fcgUnid = fcgUnid;
    }
}
