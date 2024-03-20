package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author css
 */
@Entity
@Table(catalog = "", schema = "web", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"event_unid"})})
@SequenceGenerator(name = "seq_event", sequenceName = "seq_event", allocationSize = 1)
@XmlRootElement
public class Event implements Serializable, IHistory {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_event")
    @Column(name = "event_unid", nullable = false, precision = 2147483647, scale = 0)
    private long eventUnid;
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
    @Column(name = "event_theme", length = 2147483647)
    private String eventTheme;
    @Column(name = "event_date_b")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDateB;
    @Column(name = "event_date_e")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDateE;
    @Column(name = "event_note", length = 2147483647)
    private String eventNote;
    @Column(name = "tev_unid")
    private Long tevUnid;
    @JoinColumn(name = "sub_unid", referencedColumnName = "sub_unid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subUnid;
    @JoinColumn(name = "sub_sub_unid", referencedColumnName = "sub_unid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subSubUnid;
    @Column(name = "obj_unid")
    private Long objUnid;
    @Column(name = "lot_unid")
    private Long lotUnid;
    @Column(name = "termd_unid")
    private Long termdUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
    @Column(name = "auction_unid")
    private Long auctionUnid;
    @Column(name = "red_sched_unid")
    private Long redSchedUnid;
    @Column(name = "ba_unid")
    private Long baUnid;

    public Event() {
    }

    public long getEventUnid() {
        return eventUnid;
    }

    public void setEventUnid(long eventUnid) {
        this.eventUnid = eventUnid;
    }

    public Integer getIndActual() {
        return indActual;
    }

    @Override
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

    public String getEventTheme() {
        return eventTheme;
    }

    public void setEventTheme(String eventTheme) {
        this.eventTheme = eventTheme;
    }

    public Date getEventDateB() {
        return eventDateB;
    }

    public void setEventDateB(Date eventDateB) {
        this.eventDateB = eventDateB;
    }

    public Date getEventDateE() {
        return eventDateE;
    }

    public void setEventDateE(Date eventDateE) {
        this.eventDateE = eventDateE;
    }

    public String getEventNote() {
        return eventNote;
    }

    public void setEventNote(String eventNote) {
        this.eventNote = eventNote;
    }

    public Long getTevUnid() {
        return tevUnid;
    }

    public void setTevUnid(Long tevUnid) {
        this.tevUnid = tevUnid;
    }

    public Subject getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(Subject subUnid) {
        this.subUnid = subUnid;
    }

    public Subject getSubSubUnid() {
        return subSubUnid;
    }

    public void setSubSubUnid(Subject subSubUnid) {
        this.subSubUnid = subSubUnid;
    }

    public Long getObjUnid() {
        return objUnid;
    }

    public void setObjUnid(Long objUnid) {
        this.objUnid = objUnid;
    }

    public Long getLotUnid() {
        return lotUnid;
    }

    public void setLotUnid(Long lotUnid) {
        this.lotUnid = lotUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    public Long getTermdUnid() {
        return termdUnid;
    }

    public void setTermdUnid(Long termdUnid) {
        this.termdUnid = termdUnid;
    }

    public Long getAuctionUnid() {
        return auctionUnid;
    }

    public void setAuctionUnid(Long auctionUnid) {
        this.auctionUnid = auctionUnid;
    }

    public Long getRedSchedUnid() {
        return redSchedUnid;
    }

    public void setRedSchedUnid(Long redSchedUnid) {
        this.redSchedUnid = redSchedUnid;
    }

    public Long getBaUnid() {
        return baUnid;
    }

    public void setBaUnid(Long baUnid) {
        this.baUnid = baUnid;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.Event[ eventUnid=" + eventUnid + " ]";
    }

}
