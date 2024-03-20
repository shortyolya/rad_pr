package com.baltinfo.radius.db.model;

import com.baltinfo.radius.db.model.IHistory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Kulikov Semyon
 * @since 06.02.2020
 */

@Entity
@Table(name = "feed_ad", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"fad_unid"})})
@SequenceGenerator(name = "seq_feed_ad", sequenceName = "seq_feed_ad", allocationSize = 1)
@NamedQuery(name = "FeedAd.getTheLatestAd", query = "SELECT f FROM FeedAd f " +
        "where f.indActual = 1 " +
        "and f.fadStatus = 1 " +
        "and (f.fcUnid = :fcUnid or (:fcUnid is null and f.fcUnid is null)) " +
        "and f.mevUnid = :mevUnid " +
        "and (f.fcgUnid = :fcgUnid or (:fcgUnid is null and f.fcgUnid is null)) " +
        "and f.subUnid = :subUnid ")
@XmlRootElement
public class FeedAd implements IHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_feed_ad")
    @Column(name = "fad_unid", nullable = false)
    private Long fadUnid;
    @Column(name = "fc_unid")
    private Long fcUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
    @Column(name = "mev_unid")
    private Long mevUnid;
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
    @Column(name = "fad_date")
    @Temporal(TemporalType.DATE)
    private Date fadDate;
    @Column(name = "fad_xml")
    private String fadXml;
    @Column(name = "fad_status")
    private Integer fadStatus;
    @Column(name = "fad_note", length = 2000)
    private String fadNote;
    @Column(name = "fad_error_text", length = 2000)
    private String fadErrorText;
    @Column(name = "fcg_unid")
    private Long fcgUnid;
    @Column(name = "sub_unid")
    private Long subUnid;

    public Long getFadUnid() {
        return fadUnid;
    }

    public void setFadUnid(Long fadUnid) {
        this.fadUnid = fadUnid;
    }

    public Long getFcUnid() {
        return fcUnid;
    }

    public void setFcUnid(Long fcUnid) {
        this.fcUnid = fcUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    public Long getMevUnid() {
        return mevUnid;
    }

    public void setMevUnid(Long mevUnid) {
        this.mevUnid = mevUnid;
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

    @Override
    public void setPersCodeBRec(Long persCodeBRec) {
        this.persCodeBRec = persCodeBRec;
    }

    public Date getFadDate() {
        return fadDate;
    }

    public void setFadDate(Date fadDate) {
        this.fadDate = fadDate;
    }

    public String getFadXml() {
        return fadXml;
    }

    public void setFadXml(String fadXml) {
        this.fadXml = fadXml;
    }

    public Integer getFadStatus() {
        return fadStatus;
    }

    public void setFadStatus(Integer fadStatus) {
        this.fadStatus = fadStatus;
    }

    public String getFadNote() {
        return fadNote;
    }

    public void setFadNote(String fadNote) {
        this.fadNote = fadNote;
    }

    public String getFadErrorText() {
        return fadErrorText;
    }

    public void setFadErrorText(String fadErrorText) {
        this.fadErrorText = fadErrorText;
    }

    public Long getFcgUnid() {
        return fcgUnid;
    }

    public void setFcgUnid(Long fcgUnid) {
        this.fcgUnid = fcgUnid;
    }

    public Long getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(Long subUnid) {
        this.subUnid = subUnid;
    }
}

