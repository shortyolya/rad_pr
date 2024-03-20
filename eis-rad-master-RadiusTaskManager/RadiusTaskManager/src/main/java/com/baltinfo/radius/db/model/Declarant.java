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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author sas
 */
@Entity
@Table(name = "declarant", catalog = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"decl_unid"})})
@SequenceGenerator(name = "seq_declarant", sequenceName = "seq_declarant", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Declarant.findAll", query = "SELECT d FROM Declarant d")})
public class Declarant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_declarant")
    @Basic(optional = false)
    @Column(name = "decl_unid")
    private Long declUnid;
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
    @Column(name = "sub_unid")
    private Long subUnid;
    @Column(name = "sub_sub_unid")
    private Long subSubUnid;
    @JoinColumn(name = "applicat_unid", referencedColumnName = "applicat_unid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Application applicatUnid;
    @JoinColumn(name = "sh_unid", referencedColumnName = "sh_unid")
    @ManyToOne(fetch= FetchType.LAZY)
    private SubjectHistory shUnid;
    @Transient
    private String applicatNumber;
    
    public Declarant() {
    }

    public Declarant(Long declUnid) {
        this.declUnid = declUnid;
    }

    public Long getDeclUnid() {
        return declUnid;
    }

    public void setDeclUnid(Long declUnid) {
        this.declUnid = declUnid;
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

    public Long getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(Long subUnid) {
        this.subUnid = subUnid;
    }

    public Long getSubSubUnid() {
        return subSubUnid;
    }

    public void setSubSubUnid(Long subSubUnid) {
        this.subSubUnid = subSubUnid;
    }

    public Application getApplicatUnid() {
        return applicatUnid;
    }

    public void setApplicatUnid(Application applicatUnid) {
        this.applicatUnid = applicatUnid;
    }

    public SubjectHistory getShUnid() {
        return shUnid;
    }

    public void setShUnid(SubjectHistory shUnid) {
        this.shUnid = shUnid;
    }

    public String getApplicatNumber() {
        return applicatNumber;
    }

    public void setApplicatNumber(String applicatNumber) {
        this.applicatNumber = applicatNumber;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (declUnid != null ? declUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Declarant)) {
            return false;
        }
        Declarant other = (Declarant) object;
        if ((this.declUnid == null && other.declUnid != null) || (this.declUnid != null && !this.declUnid.equals(other.declUnid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.appl.model.Declarant[ declUnid=" + declUnid + " ]";
    }

}
