package com.baltinfo.radius.db.model.bankruptcy;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author sas
 */
@Entity
@Table(name = "ADDITIONAL_DATA", catalog = "", schema = "WEB")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "VAdditionalDataBkr.findAll", query = "SELECT v FROM VAdditionalDataBkr v"),
        @NamedQuery(name = "VAdditionalDataBkr.findByDoAndTadUnid", query = "SELECT v FROM VAdditionalDataBkr v where v.tadUnid = :tadUnid and v.addDoUnid = :doUnid")})
public class VAdditionalDataBkr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @Column(name = "ADD_UNID")
    private long addUnid;
    @Basic(optional = false)
    @Column(name = "TAD_UNID")
    private long tadUnid;
    @Column(name = "TAD_NAME")
    private String tadName;
    @Column(name = "TAD_SNAME")
    private String tadSname;
    @Column(name = "TAD_NOTE")
    private String tadNote;
    @Column(name = "TAD_IND_APPLICAT")
    private Short tadIndApplicat;
    @Column(name = "ADD_DO_UNID")
    private Long addDoUnid;
    @Column(name = "ADD_NOTE")
    private String addNote;
    @Column(name = "ADD_IND_INCLUDE")
    private Short addIndInclude;
    @Column(name = "ADD_NUM")
    private Short addNum;
    @Column(name = "ADD_FILE_NAME")
    private String addFileName;
    @Column(name = "ADD_DO_DO_UNID")
    private Long addDoDoUnid;
    @Column(name = "ADD_AUCTION_UNID")
    private Long addAuctionUnid;
    @Column(name = "PA_UNID")
    private BigInteger paUnid;
    @Column(name = "DATE_B")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateB;
    @Column(name = "DATE_B_REC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBRec;
    @Column(name = "PERS_CODE_B")
    private Long persCodeB;
    @Column(name = "IND_EXTRACT_SIGN")
    private BigInteger indExtractSign;
    @Column(name = "IND_ACTUAL")
    private Short indActual;
    @Column(name = "ADD_ADD_UNID")
    private Long addAddUnid;
    @Column(name = "ADD_RS_UNID")
    private Long addRsUnid;
    @Column(name = "ORGANIZER_UNID")
    private BigInteger organizerUnid;
    @Column(name = "ADD_DF_UNID")
    private Long addDfUnid;

    public VAdditionalDataBkr() {
    }

    public long getAddUnid() {
        return addUnid;
    }

    public void setAddUnid(long addUnid) {
        this.addUnid = addUnid;
    }

    public long getTadUnid() {
        return tadUnid;
    }

    public void setTadUnid(long tadUnid) {
        this.tadUnid = tadUnid;
    }

    public String getTadName() {
        return tadName;
    }

    public void setTadName(String tadName) {
        this.tadName = tadName;
    }

    public String getTadSname() {
        return tadSname;
    }

    public void setTadSname(String tadSname) {
        this.tadSname = tadSname;
    }

    public String getTadNote() {
        return tadNote;
    }

    public void setTadNote(String tadNote) {
        this.tadNote = tadNote;
    }

    public Short getTadIndApplicat() {
        return tadIndApplicat;
    }

    public void setTadIndApplicat(Short tadIndApplicat) {
        this.tadIndApplicat = tadIndApplicat;
    }

    public Long getAddDoUnid() {
        return addDoUnid;
    }

    public void setAddDoUnid(Long addDoUnid) {
        this.addDoUnid = addDoUnid;
    }

    public String getAddNote() {
        return addNote;
    }

    public void setAddNote(String addNote) {
        this.addNote = addNote;
    }

    public Short getAddIndInclude() {
        return addIndInclude;
    }

    public void setAddIndInclude(Short addIndInclude) {
        this.addIndInclude = addIndInclude;
    }

    public Short getAddNum() {
        return addNum;
    }

    public void setAddNum(Short addNum) {
        this.addNum = addNum;
    }

    public String getAddFileName() {
        return addFileName;
    }

    public void setAddFileName(String addFileName) {
        this.addFileName = addFileName;
    }

    public Long getAddDoDoUnid() {
        return addDoDoUnid;
    }

    public void setAddDoDoUnid(Long addDoDoUnid) {
        this.addDoDoUnid = addDoDoUnid;
    }

    public Long getAddAuctionUnid() {
        return addAuctionUnid;
    }

    public void setAddAuctionUnid(Long addAuctionUnid) {
        this.addAuctionUnid = addAuctionUnid;
    }

    public BigInteger getPaUnid() {
        return paUnid;
    }

    public void setPaUnid(BigInteger paUnid) {
        this.paUnid = paUnid;
    }

    public Date getDateB() {
        return dateB;
    }

    public void setDateB(Date dateB) {
        this.dateB = dateB;
    }

    public Date getDateBRec() {
        return dateBRec;
    }

    public void setDateBRec(Date dateBRec) {
        this.dateBRec = dateBRec;
    }

    public Long getPersCodeB() {
        return persCodeB;
    }

    public void setPersCodeB(Long persCodeB) {
        this.persCodeB = persCodeB;
    }

    public BigInteger getIndExtractSign() {
        return indExtractSign;
    }

    public void setIndExtractSign(BigInteger indExtractSign) {
        this.indExtractSign = indExtractSign;
    }

    public Short getIndActual() {
        return indActual;
    }

    public void setIndActual(Short indActual) {
        this.indActual = indActual;
    }

    public Long getAddAddUnid() {
        return addAddUnid;
    }

    public void setAddAddUnid(Long addAddUnid) {
        this.addAddUnid = addAddUnid;
    }

    public Long getAddRsUnid() {
        return addRsUnid;
    }

    public void setAddRsUnid(Long addRsUnid) {
        this.addRsUnid = addRsUnid;
    }

    public BigInteger getOrganizerUnid() {
        return organizerUnid;
    }

    public void setOrganizerUnid(BigInteger organizerUnid) {
        this.organizerUnid = organizerUnid;
    }

    public Long getAddDfUnid() {
        return addDfUnid;
    }

    public void setAddDfUnid(Long addDfUnid) {
        this.addDfUnid = addDfUnid;
    }
}
