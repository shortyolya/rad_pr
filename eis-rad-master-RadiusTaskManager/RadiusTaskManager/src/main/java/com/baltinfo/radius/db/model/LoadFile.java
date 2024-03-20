package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
 * @since 04.02.2020
 */

@Entity
@Table(name = "load_files", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"lf_unid"})})
@SequenceGenerator(name = "seq_load_files", sequenceName = "seq_load_files", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "LoadFile.findAll", query = "SELECT l FROM LoadFile l"),
        @NamedQuery(name = "LoadFile.findLoadFileByLoadLot", query = "SELECT lf FROM LoadLot l, LoadFile lf " +
                "WHERE l.indActual = 1 AND (l.llTransferResult is null or l.llTransferResult = 0) " +
                "AND l.llUnid = lf.llUnid AND lf.llUnid = :llUnid order by CAST(l.llLotNum as integer), lf.lfFileNameAsv ")
})
public class LoadFile implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_load_files")
    @Column(name = "lf_unid")
    private Long lfUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
    @Column(name = "ll_unid")
    private Long llUnid;
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
    @Column(name = "lf_type")
    private Short lfType;
    @Column(name = "lf_eis_path")
    private String lfEisPath;
    @Column(name = "lf_ftp_path")
    private String lfFtpPath;
    @Column(name = "lf_file_name")
    private String lfFileName;
    @Column(name = "lf_asv_id")
    private String lfAsvId;
    @Column(name = "lf_file_name_asv")
    private String lfFileNameAsv;
    @Column(name = "lf_file_ext")
    private String lfFileExt;
    @Column(name = "lf_file_size")
    private Integer lfFileSize;
    @Column(name = "ba_unid")
    private Long baUnid;

    public LoadFile() {
    }

    public Long getLfUnid() {
        return lfUnid;
    }

    public void setLfUnid(Long lfUnid) {
        this.lfUnid = lfUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    public Long getLlUnid() {
        return llUnid;
    }

    public void setLlUnid(Long llUnid) {
        this.llUnid = llUnid;
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

    public Short getLfType() {
        return lfType;
    }

    public void setLfType(Short lfType) {
        this.lfType = lfType;
    }

    public String getLfEisPath() {
        return lfEisPath;
    }

    public void setLfEisPath(String lfEisPath) {
        this.lfEisPath = lfEisPath;
    }

    public String getLfFtpPath() {
        return lfFtpPath;
    }

    public void setLfFtpPath(String lfFtpPath) {
        this.lfFtpPath = lfFtpPath;
    }

    public String getLfFileName() {
        return lfFileName;
    }

    public void setLfFileName(String lfFileName) {
        this.lfFileName = lfFileName;
    }

    public String getLfAsvId() {
        return lfAsvId;
    }

    public void setLfAsvId(String lfAsvId) {
        this.lfAsvId = lfAsvId;
    }

    public String getLfFileNameAsv() {
        return lfFileNameAsv;
    }

    public void setLfFileNameAsv(String lfFileNameAsv) {
        this.lfFileNameAsv = lfFileNameAsv;
    }

    public String getLfFileExt() {
        return lfFileExt;
    }

    public void setLfFileExt(String lfFileExt) {
        this.lfFileExt = lfFileExt;
    }

    public Integer getLfFileSize() {
        return lfFileSize;
    }

    public void setLfFileSize(Integer lfFileSize) {
        this.lfFileSize = lfFileSize;
    }

    public Long getBaUnid() {
        return baUnid;
    }

    public void setBaUnid(Long baUnid) {
        this.baUnid = baUnid;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.LoadFiles[ lfUnid=" + lfUnid + " ]";
    }
}
