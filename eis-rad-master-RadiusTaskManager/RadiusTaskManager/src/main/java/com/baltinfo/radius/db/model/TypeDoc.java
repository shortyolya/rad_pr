package com.baltinfo.radius.db.model;

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
import java.io.Serializable;
import java.util.Date;

/**
 * @author css
 */
@Entity
@Table(name = "type_doc", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"td_unid"})})
@SequenceGenerator(name = "seq_type_doc", sequenceName = "seq_type_doc", allocationSize = 1)
@XmlRootElement
public class TypeDoc implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_type_doc")
    @Column(name = "td_unid", nullable = false, precision = 2147483647, scale = 0)
    private Long tdUnid;
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
    @Column(name = "td_name", length = 2147483647)
    private String tdName;
    @Column(name = "td_sname", length = 2147483647)
    private String tdSname;
    @Column(name = "td_ind_form_some_docs")
    private Integer tdIndFormSomeDocs;
    @Column(name = "td_ind_load_site")
    private Integer tdIndLoadSite;
    @Column(name = "td_ind_load_etp")
    private Integer tdIndLoadEtp;
    @Column(name = "td_ind_load_feed")
    private Integer tdIndLoadFeed;

    public TypeDoc() {
    }

    public TypeDoc(Long tdUnid) {
        this.tdUnid = tdUnid;
    }

    public Long getTdUnid() {
        return tdUnid;
    }

    public void setTdUnid(long tdUnid) {
        this.tdUnid = tdUnid;
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

    public String getTdName() {
        return tdName;
    }

    public void setTdName(String tdName) {
        this.tdName = tdName;
    }

    public String getTdSname() {
        return tdSname;
    }

    public void setTdSname(String tdSname) {
        this.tdSname = tdSname;
    }

    public Boolean getTdIndLoadSite() {
        return tdIndLoadSite != null && tdIndLoadSite == 1;
    }

    public Boolean getTdIndLoadEtp() {
        return tdIndLoadEtp != null && tdIndLoadEtp == 1;
    }

    public Boolean getTdIndLoadFeed() {
        return tdIndLoadFeed != null && tdIndLoadFeed == 1;
    }

    public void setTdIndLoadSite(Boolean tdIndLoadSite) {
        this.tdIndLoadSite = tdIndLoadSite != null && tdIndLoadSite ? 1 : 0;
    }

    public void setTdIndLoadEtp(Boolean tdIndLoadEtp) {
        this.tdIndLoadEtp = tdIndLoadEtp != null && tdIndLoadEtp ? 1 : 0;
    }

    public void setTdIndLoadFeed(Boolean tdIndLoadFeed) {
        this.tdIndLoadFeed = tdIndLoadFeed != null && tdIndLoadFeed ? 1 : 0;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.TypeDoc[ tdUnid=" + tdUnid + " ]";
    }

    public Boolean getTdIndFormSomeDocs() {
        return tdIndFormSomeDocs != null && tdIndFormSomeDocs == 1;
    }

    public void setTdIndFormSomeDocs(Boolean tdIndFormSomeDocs) {
        this.tdIndFormSomeDocs = tdIndFormSomeDocs != null && tdIndFormSomeDocs ? 1 : 0;
    }
}
