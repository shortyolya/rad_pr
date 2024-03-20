package com.baltinfo.radius.db.model;

import java.io.Serializable;
import java.util.Date;
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

/**
 * <p>
 * Группа свойств
 * </p>
 *
 * @author Sergeev Yuriy
 * @since 24.11.2019
 */
@Entity
@Table(name = "property_group", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"pg_unid"})})
@SequenceGenerator(name = "seq_property_group", sequenceName = "seq_property_group", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "PropertyGroup.findByPgUnid",
                query = "select a from PropertyGroup a where a.indActual = 1 and a.scUnid.scUnid = :scUnid order by a.pgNum asc")
})
@XmlRootElement
public class PropertyGroup implements IHistory, Serializable {
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_property_group")
    @Column(name = "pg_unid", nullable = false, precision = 2147483647)
    private Long pgUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
    @JoinColumn(name = "sc_unid", referencedColumnName = "sc_unid")
    @ManyToOne(fetch = FetchType.LAZY)
    private SaleCategory scUnid;
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
    @Column(name = "pg_name", length = 500)
    private String pgName;
    @Column(name = "pg_num")
    private Integer pgNum;

    public PropertyGroup() {
    }

    public Long getPgUnid() {
        return pgUnid;
    }

    public void setPgUnid(Long pgUnid) {
        this.pgUnid = pgUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    public SaleCategory getScUnid() {
        return scUnid;
    }

    public void setScUnid(SaleCategory scUnid) {
        this.scUnid = scUnid;
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

    public String getPgName() {
        return pgName;
    }

    public void setPgName(String pgName) {
        this.pgName = pgName;
    }

    public Integer getPgNum() {
        return pgNum;
    }

    public void setPgNum(Integer pgNum) {
        this.pgNum = pgNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PropertyGroup that = (PropertyGroup) o;

        if (pgUnid != that.pgUnid) return false;
        return pgName != null ? pgName.equals(that.pgName) : that.pgName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (pgUnid ^ (pgUnid >>> 32));
        result = 31 * result + (pgName != null ? pgName.hashCode() : 0);
        return result;
    }
}
