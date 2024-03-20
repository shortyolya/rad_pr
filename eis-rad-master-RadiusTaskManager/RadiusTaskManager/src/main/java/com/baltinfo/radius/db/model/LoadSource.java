package com.baltinfo.radius.db.model;

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
import java.util.Date;

/**
 * @author lia
 */
@Entity
@Table(name = "load_source", catalog = "", schema = "web")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "LoadSource.findByLsUnid", query = "SELECT l FROM LoadSource l WHERE l.lsUnid = :lsUnid")})
public class LoadSource implements Serializable, IHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ls_unid")
    private Long lsUnid;
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
    @Column(name = "ls_name")
    private String lsName;
    @Column(name = "ls_prefix")
    private String lsPrefix;
    @Column(name = "ls_num_seq")
    private String lsNumSeq;

    public LoadSource() {
    }

    public Long getLsUnid() {
        return lsUnid;
    }

    public void setLsUnid(Long lsUnid) {
        this.lsUnid = lsUnid;
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

    public String getLsName() {
        return lsName;
    }

    public void setLsName(String lsName) {
        this.lsName = lsName;
    }

    public String getLsPrefix() {
        return lsPrefix;
    }

    public void setLsPrefix(String lsPrefix) {
        this.lsPrefix = lsPrefix;
    }

    public String getLsNumSeq() {
        return lsNumSeq;
    }

    public void setLsNumSeq(String lsNumSeq) {
        this.lsNumSeq = lsNumSeq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lsUnid != null ? lsUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoadSource)) {
            return false;
        }
        LoadSource other = (LoadSource) object;
        return (this.lsUnid != null || other.lsUnid == null) && (this.lsUnid == null || this.lsUnid.equals(other.lsUnid));
    }

    @Override
    public String toString() {
        return "LoadSource{" +
                "lsUnid=" + lsUnid +
                ", lsName='" + lsName + '\'' +
                ", indActual=" + indActual +
                '}';
    }
}
