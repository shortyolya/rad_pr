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
import java.util.Date;

/**
 *
 * @author sas
 */
@Entity
@Table(name = "type_deal", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"tdeal_unid"})})
@SequenceGenerator(name = "seq_type_deal", sequenceName = "seq_type_deal", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TypeDeal.findAll", query = "SELECT t FROM TypeDeal t where t.indActual = 1")
})
public class TypeDeal {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_type_deal")
    @Basic(optional = false)
    @Column(name = "tdeal_unid")
    private Long tdealUnid;
    @Column(name = "found_unid")
    private Integer foundUnid;
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
    @Column(name = "tdeal_name")
    private String tdealName;
    @Column(name = "tdeal_sname")
    private String tdealSname;
    @Column(name = "tdeal_class")
    private Long tdealClass;

    public TypeDeal() {
    }

    public TypeDeal(Long tdealUnid) {
        this.tdealUnid = tdealUnid;
    }

    public Long getTdealUnid() {
        return tdealUnid;
    }

    public void setTdealUnid(Long tdealUnid) {
        this.tdealUnid = tdealUnid;
    }

    public Integer getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Integer foundUnid) {
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

    public String getTdealName() {
        return tdealName;
    }

    public void setTdealName(String tdealName) {
        this.tdealName = tdealName;
    }

    public String getTdealSname() {
        return tdealSname;
    }

    public void setTdealSname(String tdealSname) {
        this.tdealSname = tdealSname;
    }

    public Long getTdealClass() {
        return tdealClass;
    }

    public void setTdealClass(Long tdealClass) {
        this.tdealClass = tdealClass;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tdealUnid != null ? tdealUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeDeal)) {
            return false;
        }
        TypeDeal other = (TypeDeal) object;
        if ((this.tdealUnid == null && other.tdealUnid != null) || (this.tdealUnid != null && !this.tdealUnid.equals(other.tdealUnid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.TypeDeal[ tdealUnid=" + tdealUnid + " ]";
    }

}

