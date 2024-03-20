package com.baltinfo.radius.db.model.dep;

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
 *
 * @author sas
 */
@Entity
@Table(name = "type_account_operation", catalog = "", schema = "dep", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"tao_unid"})})
@SequenceGenerator(name = "seq_type_account_operation", sequenceName = "seq_type_account_operation", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeAccountOperation.findAll", query = "SELECT t FROM TypeAccountOperation t")})
public class TypeAccountOperation implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_type_account_operation")
    @Column(name = "tao_unid")
    private Long taoUnid;
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
    @Column(name = "tao_sname")
    private String taoSname;
    @Column(name = "tao_name")
    private String taoName;    
    @Column(name = "tao_bias")
    private Short taoBias;    
    @Column(name = "tao_ind_work_day")
    private Short taoIndWorkDay;
    

    public TypeAccountOperation() {
    }

    public TypeAccountOperation(Long taoUnid) {
        this.taoUnid = taoUnid;
    }

    public Long getTaoUnid() {
        return taoUnid;
    }

    public void setTaoUnid(Long taoUnid) {
        this.taoUnid = taoUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
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

    public String getTaoSname() {
        return taoSname;
    }

    public void setTaoSname(String taoSname) {
        this.taoSname = taoSname;
    }

    public String getTaoName() {
        return taoName;
    }

    public void setTaoName(String taoName) {
        this.taoName = taoName;
    }

    public Short getTaoBias() {
        return taoBias;
    }

    public void setTaoBias(Short taoBias) {
        this.taoBias = taoBias;
    }

    public Short getTaoIndWorkDay() {
        return taoIndWorkDay;
    }

    public void setTaoIndWorkDay(Short taoIndWorkDay) {
        this.taoIndWorkDay = taoIndWorkDay;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taoUnid != null ? taoUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeAccountOperation)) {
            return false;
        }
        TypeAccountOperation other = (TypeAccountOperation) object;
        if ((this.taoUnid == null && other.taoUnid != null) || (this.taoUnid != null && !this.taoUnid.equals(other.taoUnid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.dep.model.TypeAccountOperation[ taoUnid=" + taoUnid + " ]";
    }

}
