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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 10.02.2020
 */

@Entity
@Table(name = "sale_category", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"sc_unid"})})
@SequenceGenerator(name = "seq_sale_category", sequenceName = "seq_sale_category", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "SaleCategory.findAll",
                query = "SELECT v FROM SaleCategory v WHERE v.indActual = 1 ORDER BY v.scUnid"),
        @NamedQuery(name = "SaleCategory.findByUnid",
                query = "SELECT v FROM SaleCategory v WHERE v.indActual = 1 and v.scUnid = :scUnid"),
        @NamedQuery(name = "SaleCategory.findByScCode",
                query = "SELECT v FROM SaleCategory v WHERE v.indActual = 1 and v.scCode = :scCode"),
        @NamedQuery(name = "SaleCategory.findByName",
                query = "SELECT v FROM SaleCategory v WHERE v.indActual = 1 and trim(lower(v.scName)) = trim(lower(:scName))"),
        @NamedQuery(name = "SaleCategory.findChild",
                query = "SELECT v FROM SaleCategory v WHERE v.indActual = 1 AND v.parentScUnid.scUnid = :scUnid"),
        @NamedQuery(name = "SaleCategory.findToUnidByScUnid",
                query = "SELECT v.toUnid FROM SaleCategory v WHERE v.indActual = 1 AND v.scUnid = :scUnid"),
        @NamedQuery(name = "SaleCategory.findByScIndDelete",
                query = "SELECT v FROM SaleCategory v WHERE v.indActual = 1 AND v.scIndDelete = true"),
        @NamedQuery(name = "SaleCategory.findFirstLevelCategories",
                query = "SELECT v FROM SaleCategory v WHERE v.indActual = 1 AND v.parentScUnid is null")})
@XmlRootElement
public class SaleCategory implements IHistory, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sale_category")
    @Column(name = "sc_unid", nullable = false, precision = 2147483647, scale = 0)
    private long scUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
    @JoinColumn(name = "parent_sc_unid", referencedColumnName = "sc_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private SaleCategory parentScUnid;
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
    @Column(name = "sc_name", length = 2147483647)
    private String scName;
    @Column(name = "sc_code")
    private String scCode;
    @JoinColumn(name = "to_unid", referencedColumnName = "to_unid")
    @ManyToOne
    private TypeObject toUnid;
    @Column(name = "sc_efrsb_code")
    private String scEfrsbCode;
    @Column(name = "sc_asv_code")
    private String scAsvCode;
    @Column(name = "sc_ind_delete")
    private Boolean scIndDelete;
    @Column(name = "sc_new_code")
    private String scNewCode;


    public SaleCategory() {
    }

    public long getScUnid() {
        return scUnid;
    }

    public void setScUnid(long scUnid) {
        this.scUnid = scUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    public SaleCategory getParentScUnid() {
        return parentScUnid;
    }

    public void setParentScUnid(SaleCategory parentScUnid) {
        this.parentScUnid = parentScUnid;
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

    public String getScName() {
        return scName;
    }

    public void setScName(String scName) {
        this.scName = scName;
    }

    public String getScCode() {
        return scCode;
    }

    public void setScCode(String scCode) {
        this.scCode = scCode;
    }

    public TypeObject getToUnid() {
        return toUnid;
    }

    public void setToUnid(TypeObject toUnid) {
        this.toUnid = toUnid;
    }

    public String getScEfrsbCode() {
        return scEfrsbCode;
    }

    public void setScEfrsbCode(String scEfrsbCode) {
        this.scEfrsbCode = scEfrsbCode;
    }

    public String getScAsvCode() {
        return scAsvCode;
    }

    public void setScAsvCode(String scAsvCode) {
        this.scAsvCode = scAsvCode;
    }

    public Boolean getScIndDelete() {
        return scIndDelete;
    }

    public void setScIndDelete(Boolean scIndDelete) {
        this.scIndDelete = scIndDelete;
    }

    public String getScNewCode() {
        return scNewCode;
    }

    public void setScNewCode(String scNewCode) {
        this.scNewCode = scNewCode;
    }
}
