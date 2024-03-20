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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@Table(name = "picture", catalog = "", schema = "web", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"pict_unid"})})
@SequenceGenerator(name = "seq_picture", sequenceName = "seq_picture", allocationSize = 1)
@NamedEntityGraphs({
        @NamedEntityGraph(name = "graph.Picture.none", attributeNodes = {
        }),
        @NamedEntityGraph(name = "graph.Picture.all", attributeNodes = {
                @NamedAttributeNode("dfUnid"),
                @NamedAttributeNode("tpUnid"),
                @NamedAttributeNode("dfPreviewUnid")
        })
})

@XmlRootElement
public class Picture implements IHistory{

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_picture")
    @Column(name = "pict_unid", nullable = false, precision = 2147483647, scale = 0)
    private long pictUnid;
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
    @Column(name = "pict_name", length = 2147483647)
    private String pictName;
    @Column(name = "pict_num")
    private Integer pictNum;
    @Column(name = "pict_note", length = 2147483647)
    private String pictNote;
    @JoinColumn(name = "df_unid", referencedColumnName = "df_unid", nullable = false)
    @ManyToOne(optional = false)
    private DocFile dfUnid;
    @Column(name = "obj_unid")
    private Long objUnid;
    @Column(name = "tp_unid")
    private Long tpUnid;
    @Column(name = "pict_ind_export")
    private Integer pictIndExport;
    @Column(name = "pict_ind_export_etp")
    private Integer pictIndExportEtp;
    @Column(name = "pict_ind_export_feed")
    private Integer pictIndExportFeed;
    @JoinColumn(name = "df_preview_unid", referencedColumnName = "df_unid")
    @ManyToOne(fetch = FetchType.LAZY)
    private DocFile dfPreviewUnid;

    public Picture() {
        pictIndExport = 1;
    }

    public Picture(long pictUnid) {
        this.pictUnid = pictUnid;
    }

    public long getPictUnid() {
        return pictUnid;
    }

    public void setPictUnid(long pictUnid) {
        this.pictUnid = pictUnid;
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

    public String getPictName() {
        return pictName;
    }

    public void setPictName(String pictName) {
        this.pictName = pictName;
    }

    public Integer getPictNum() {
        return pictNum;
    }

    public void setPictNum(Integer pictNum) {
        this.pictNum = pictNum;
    }

    public String getPictNote() {
        return pictNote;
    }

    public void setPictNote(String pictNote) {
        this.pictNote = pictNote;
    }

    public DocFile getDfUnid() {
        return dfUnid;
    }

    public void setDfUnid(DocFile dfUnid) {
        this.dfUnid = dfUnid;
    }

    public Long getObjUnid() {
        return objUnid;
    }

    public void setObjUnid(Long objUnid) {
        this.objUnid = objUnid;
    }

    public Long getTpUnid() {
        return tpUnid;
    }

    public void setTpUnid(Long tpUnid) {
        this.tpUnid = tpUnid;
    }

    public Boolean getPictIndExport() {
        return pictIndExport != null && pictIndExport == 1;
    }

    public void setPictIndExport(Boolean pictIndExport) {
        this.pictIndExport = pictIndExport == null ? 0 : (pictIndExport ? 1 : 0);
    }

    public void setPictIndExportEtp(Boolean pictIndExportEtp) {
        this.pictIndExportEtp = pictIndExportEtp == null ? 0 : (pictIndExportEtp ? 1 : 0);
    }

    public void setPictIndExportFeed(Boolean pictIndExportFeed) {
        this.pictIndExportFeed = pictIndExportFeed == null ? 0 : (pictIndExportFeed ? 1 : 0);
    }

    public Boolean getPictIndExportEtp() {
        return pictIndExportEtp != null && pictIndExportEtp == 1;
    }

    public Boolean getPictIndExportFeed() {
        return pictIndExportFeed != null && pictIndExportFeed == 1;
    }

    public DocFile getDfPreviewUnid() {
        return dfPreviewUnid;
    }

    public void setDfPreviewUnid(DocFile dfPreviewUnid) {
        this.dfPreviewUnid = dfPreviewUnid;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.Picture[ pictUnid=" + pictUnid + " ]";
    }


}
