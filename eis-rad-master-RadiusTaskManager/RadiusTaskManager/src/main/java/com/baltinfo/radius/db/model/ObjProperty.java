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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
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
 * Свойство объекта
 * </p>
 *
 * @author Sergeev Yuriy
 * @since 24.11.2019
 */
@Entity
@Table(name = "obj_property", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"op_unid"})})
@SequenceGenerator(name = "seq_obj_property", sequenceName = "seq_obj_property", allocationSize = 1)
@XmlRootElement
@NamedEntityGraphs({
        @NamedEntityGraph(name = "graph.ObjProperty.none"),
        @NamedEntityGraph(name = "graph.ObjProperty.aopUnid", attributeNodes = {
                @NamedAttributeNode("aopUnid")
        })
})
@NamedQueries({
        @NamedQuery(name = "ObjProperty.findByPgUnid",
                query = "select a from ObjProperty a where a.indActual = 1 and a.pgUnid.pgUnid = :pgUnid order by a.opNum asc")
})
public class ObjProperty implements IHistory, Serializable {
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_obj_property")
    @Column(name = "op_unid", nullable = false, precision = 2147483647)
    private Long opUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
    @JoinColumn(name = "pg_unid", referencedColumnName = "pg_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private PropertyGroup pgUnid;
    @JoinColumn(name = "aop_unid", referencedColumnName = "aop_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private AllowObjProp aopUnid;
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
    @Column(name = "op_num")
    private Integer opNum;
    @Column(name = "op_def_val")
    private String opDefVal;
    @Column(name = "op_ind_mandatory")
    private Boolean opIndMandatory;
    @Column(name = "op_ind_export_etp")
    private Boolean opIndExportEtp;

    public Long getOpUnid() {
        return opUnid;
    }

    public void setOpUnid(Long opUnid) {
        this.opUnid = opUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
        this.foundUnid = foundUnid;
    }

    public PropertyGroup getPgUnid() {
        return pgUnid;
    }

    public void setPgUnid(PropertyGroup pgUnid) {
        this.pgUnid = pgUnid;
    }

    public AllowObjProp getAopUnid() {
        return aopUnid;
    }

    public void setAopUnid(AllowObjProp aopUnid) {
        this.aopUnid = aopUnid;
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

    public Integer getOpNum() {
        return opNum;
    }

    public void setOpNum(Integer opNum) {
        this.opNum = opNum;
    }

    public String getOpDefVal() {
        return opDefVal;
    }

    public void setOpDefVal(String opDefVal) {
        this.opDefVal = opDefVal;
    }

    public Boolean getOpIndMandatory() {
        return opIndMandatory;
    }

    public void setOpIndMandatory(Boolean opIndMandatory) {
        this.opIndMandatory = opIndMandatory;
    }

    public Boolean getOpIndExportEtp() {
        return opIndExportEtp;
    }

    public void setOpIndExportEtp(Boolean opIndExportEtp) {
        this.opIndExportEtp = opIndExportEtp;
    }
}
