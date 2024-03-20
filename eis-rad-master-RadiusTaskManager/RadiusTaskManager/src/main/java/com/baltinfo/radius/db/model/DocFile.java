package com.baltinfo.radius.db.model;

import com.baltinfo.radius.loadauction.ftp.FtpClient;

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
import java.io.File;
import java.util.Date;

@Entity
@Table(name = "doc_file", catalog = "", schema = "", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"df_unid"})})
@SequenceGenerator(name = "seq_doc_file", sequenceName = "seq_doc_file", allocationSize = 1)
@XmlRootElement
public class DocFile implements IHistory{

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_doc_file")
    @Column(name = "df_unid", nullable = false, precision = 2147483647, scale = 0)
    private long dfUnid;
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
    @Column(name = "df_name")
    private String dfName;
    @Column(name = "df_name_db")
    private String dfNameDb;
    @Column(name = "df_ext", length = 50)
    private String dfExt;
    @Column(name = "df_size")
    private Integer dfSize;
    @Column(name = "df_load_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dfLoadDate;
    @Column(name = "df_file_path", length = 1024)
    private String dfFilePath;
    @Column(name = "df_ftp_path", length = 1024)
    private String dfFtpPath;

    public DocFile() {
    }

    public DocFile(long dfUnid) {
        this.dfUnid = dfUnid;
    }

    public long getDfUnid() {
        return dfUnid;
    }

    public void setDfUnid(long dfUnid) {
        this.dfUnid = dfUnid;
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

    public String getDfName() {
        return dfName;
    }

    public void setDfName(String dfName) {
        this.dfName = dfName;
    }

    public String getDfNameDb() {
        return dfNameDb;
    }

    public void setDfNameDb(String dfNameDb) {
        this.dfNameDb = dfNameDb;
    }

    public String getDfExt() {
        return dfExt;
    }

    public void setDfExt(String dfExt) {
        this.dfExt = dfExt;
    }

    public Integer getDfSize() {
        return dfSize;
    }

    public void setDfSize(Integer dfSize) {
        this.dfSize = dfSize;
    }

    public Date getDfLoadDate() {
        return dfLoadDate;
    }

    public void setDfLoadDate(Date dfLoadDate) {
        this.dfLoadDate = dfLoadDate;
    }

    public String getDfFilePath() {
        if (dfFilePath == null) {
            return null;
        }
        return dfFilePath.replace("\\", File.separator).replace("/", File.separator);
    }

    public void setDfFilePath(String dfFilePath) {
        if (dfFilePath == null) {
            this.dfFilePath = null;
        } else {
            this.dfFilePath = dfFilePath.replace("\\", File.separator).replace("/", File.separator);
        }
    }

    public String getDfFtpPath() {
        if (dfFtpPath == null) {
            return null;
        }
        return dfFtpPath.replace("\\", FtpClient.separator);
    }

    public void setDfFtpPath(String dfFtpPath) {
        if (dfFtpPath == null) {
            this.dfFtpPath = null;
            return;
        }
        this.dfFtpPath = dfFtpPath.replace("\\", FtpClient.separator);
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.DocFile[ dfUnid=" + dfUnid + " ]";
    }

}
