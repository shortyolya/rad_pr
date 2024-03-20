package com.baltinfo.radius.db.model;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Suvorina Aleksandra
 * @since 12.03.2021
 */
@Entity
@Table(name = "ad_statistics", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ads_unid"})})
@SequenceGenerator(name = "seq_ad_statistics", sequenceName = "seq_ad_statistics", allocationSize = 1)
@NamedQuery(name = "AdStatistics.findByMevUnid", query = "SELECT a FROM AdStatistics a where a.indActual = 1 and a.mevUnid = :mevUnid ")
@XmlRootElement
public class AdStatistics implements IHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ad_statistics")
    @Column(name = "ads_unid", nullable = false)
    private Long adsUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
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
    @Column(name = "mev_unid")
    private Long mevUnid;
    @Column(name = "obj_unid")
    private Long objUnid;
    @Column(name = "ads_link")
    private String adsLink;
    @Column(name = "ads_errors")
    private String adsErrors;
    @Column(name = "ads_views_count")
    private Long adsViewsCount;
    @Column(name = "ads_calls_count")
    private Long adsCallsCount;
    @Column(name = "ads_shows_count")
    private Long adsShowsCount;
    @Column(name = "ads_view_details")
    @ColumnTransformer(write = "?::jsonb")
    private String adsViewDetails;

    public AdStatistics(Long mevUnid, Long objUnid) {
        this.mevUnid = mevUnid;
        this.objUnid = objUnid;
    }

    public AdStatistics() {
    }

    public Long getAdsUnid() {
        return adsUnid;
    }

    public void setAdsUnid(Long adsUnid) {
        this.adsUnid = adsUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
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

    public Long getMevUnid() {
        return mevUnid;
    }

    public void setMevUnid(Long mevUnid) {
        this.mevUnid = mevUnid;
    }

    public Long getObjUnid() {
        return objUnid;
    }

    public void setObjUnid(Long objUnid) {
        this.objUnid = objUnid;
    }

    public String getAdsLink() {
        return adsLink;
    }

    public void setAdsLink(String adsLink) {
        this.adsLink = adsLink;
    }

    public String getAdsErrors() {
        return adsErrors;
    }

    public void setAdsErrors(String adsErrors) {
        this.adsErrors = adsErrors;
    }

    public Long getAdsViewsCount() {
        return adsViewsCount;
    }

    public void setAdsViewsCount(Long adsViewsCount) {
        this.adsViewsCount = adsViewsCount;
    }

    public Long getAdsCallsCount() {
        return adsCallsCount;
    }

    public void setAdsCallsCount(Long adsCallsCount) {
        this.adsCallsCount = adsCallsCount;
    }

    public Long getAdsShowsCount() {
        return adsShowsCount;
    }

    public void setAdsShowsCount(Long adsShowsCount) {
        this.adsShowsCount = adsShowsCount;
    }

    public String getAdsViewDetails() {
        return adsViewDetails;
    }

    public void setAdsViewDetails(String adsViewDetails) {
        this.adsViewDetails = adsViewDetails;
    }
}
