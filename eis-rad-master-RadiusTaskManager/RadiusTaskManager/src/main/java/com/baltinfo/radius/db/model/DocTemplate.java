/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baltinfo.radius.db.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * @author kya
 */
@Entity
@Table(name = "doc_template", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"dt_unid"})})
@XmlRootElement
public class DocTemplate implements IHistory {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "dt_unid", nullable = false, precision = 2147483647, scale = 0)
    private long dtUnid;
    @JoinColumn(name = "td_unid", referencedColumnName = "td_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private TypeDoc tdUnid;
    @JoinColumn(name = "df_unid", referencedColumnName = "df_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private DocFile dfUnid;
    @JoinColumn(name = "to_unid", referencedColumnName = "to_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private TypeObject toUnid;
    @Column(name = "entity_unid")
    private Long entityUnid;
    @Column(name = "tt_unid", nullable = false)
    private long ttUnid;
    @Column(name = "types_unid")
    private Long typesUnid;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "fl_unid", referencedColumnName = "fl_unid")
    private FormatLoad flUnid;
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
    @Column(name = "dt_name")
    private String dtName;
    @Column(name = "dt_sname")
    private String dtSname;
    @Column(name = "dt_sql")
    private String dtSql;
    @Column(name = "dt_call_str")
    private String dtCallStr;
    @Basic(optional = false)
    @Column(name = "dt_type", nullable = false)
    private long dtType;
    @Transient
    private List<DocTemplateParam> docTemplateParams;
    @JoinColumn(name = "sub_unid", referencedColumnName = "sub_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subUnid;
    @JoinColumn(name = "deal_unid", referencedColumnName = "deal_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private Deal dealUnid;
    @JoinColumn(name = "type_auction_unid", referencedColumnName = "type_auction_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private TypeAuction typeAuctionUnid;
    @Column(name = "dt_type_db")
    private int dtTypeDb;

    public DocTemplate() {
    }

    public DocTemplate(long dtUnid) {
        this.dtUnid = dtUnid;
    }

    public DocTemplate(long dtUnid, long dtType) {
        this.dtUnid = dtUnid;
        this.dtType = dtType;
    }

    public long getDtUnid() {
        return dtUnid;
    }

    public void setDtUnid(long dtUnid) {
        this.dtUnid = dtUnid;
    }

    public TypeDoc getTdUnid() {
        return tdUnid;
    }

    public void setTdUnid(TypeDoc tdUnid) {
        this.tdUnid = tdUnid;
    }

    public DocFile getDfUnid() {
        return dfUnid;
    }

    public void setDfUnid(DocFile dfUnid) {
        this.dfUnid = dfUnid;
    }

    public TypeObject getToUnid() {
        return toUnid;
    }

    public void setToUnid(TypeObject toUnid) {
        this.toUnid = toUnid;
    }

    public Long getEntityUnid() {
        return entityUnid;
    }

    public void setEntityUnid(Long entityUnid) {
        this.entityUnid = entityUnid;
    }

    public long getTtUnid() {
        return ttUnid;
    }

    public void setTtUnid(long ttUnid) {
        this.ttUnid = ttUnid;
    }

    public Long getTypesUnid() {
        return typesUnid;
    }

    public void setTypesUnid(Long typesUnid) {
        this.typesUnid = typesUnid;
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

    public String getDtName() {
        return dtName;
    }

    public void setDtName(String dtName) {
        this.dtName = dtName;
    }

    public String getDtSname() {
        return dtSname;
    }

    public void setDtSname(String dtSname) {
        this.dtSname = dtSname;
    }

    public String getDtCallStr() {
        return dtCallStr;
    }

    public void setDtCallStr(String dtCallStr) {
        this.dtCallStr = dtCallStr;
    }

    public long getDtType() {
        return dtType;
    }

    public void setDtType(long dtType) {
        this.dtType = dtType;
    }

    public List<DocTemplateParam> getDocTemplateParams() {
        return docTemplateParams;
    }

    public void setDocTemplateParams(List<DocTemplateParam> docTemplateParams) {
        this.docTemplateParams = docTemplateParams;
    }

    public Subject getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(Subject subUnid) {
        this.subUnid = subUnid;
    }

    public Deal getDealUnid() {
        return dealUnid;
    }

    public void setDealUnid(Deal dealUnid) {
        this.dealUnid = dealUnid;
    }

    public String getDtSql() {
        return dtSql;
    }

    public void setDtSql(String dtSql) {
        this.dtSql = dtSql;
    }

    public TypeAuction getTypeAuctionUnid() {
        return typeAuctionUnid;
    }

    public void setTypeAuctionUnid(TypeAuction typeAuctionUnid) {
        this.typeAuctionUnid = typeAuctionUnid;
    }

    public int getDtTypeDb() {
        return dtTypeDb;
    }

    public void setDtTypeDb(int dtTypeDb) {
        this.dtTypeDb = dtTypeDb;
    }

    public FormatLoad getFlUnid() {
        return flUnid;
    }

    public void setFlUnid(FormatLoad flUnid) {
        this.flUnid = flUnid;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.DocTemplate[ dtUnid=" + dtUnid + " ]";
    }

}
