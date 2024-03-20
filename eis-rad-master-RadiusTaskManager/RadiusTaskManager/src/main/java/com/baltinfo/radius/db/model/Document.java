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
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

@Entity
@Table(name = "document", catalog = "", schema = "", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"docum_unid"})})
@SequenceGenerator(name = "seq_document", sequenceName = "seq_document", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "Document.getByDocumStatusAndDgtUnid", query = "SELECT d FROM Document d WHERE d.indActual = 1" +
                " and d.documStatus= :documStatus and d.dgtUnid.dgtUnid = :dgtUnid ORDER BY d.documUnid"),
        @NamedQuery(name = "Document.findByApplicatUnid", query = "SELECT d FROM Document d WHERE d.indActual = 1" +
                " and d.applicatUnid.applicatUnid = :applicatUnid ORDER BY d.documUnid")
})
@XmlRootElement
public class Document implements IHistory {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_document")
    @Basic(optional = false)
    @Column(name = "docum_unid", nullable = false, precision = 2147483647, scale = 0)
    private long documUnid;
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
    @Column(name = "docum_name", length = 2147483647)
    private String documName;
    @Column(name = "docum_number", length = 30)
    private String documNumber;
    @Column(name = "docum_series", length = 30)
    private String documSeries;
    @Column(name = "docum_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date documDate;
    @Column(name = "docum_ind_check")
    private Short documIndCheck;
    @Column(name = "doc_docum_unid")
    private Long docDocumUnid;
    @JoinColumn(name = "df_unid", referencedColumnName = "df_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private DocFile dfUnid;
    @JoinColumn(name = "df_scan_unid", referencedColumnName = "df_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private DocFile dfScanUnid;
    @Column(name = "obj_unid")
    private Long objUnid;
    @JoinColumn(name = "td_unid", referencedColumnName = "td_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private TypeDoc tdUnid;
    @Column(name = "deal_unid")
    private Long dealUnid;
    @Column(name = "com_unid")
    private Long comUnid;
    @Column(name = "ds_unid")
    private Long dsUnid;
    @JoinColumn(name = "applicat_unid", referencedColumnName = "applicat_unid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Application applicatUnid;
    @Column(name = "lot_unid")
    private Long lotUnid;
    @Column(name = "auction_unid")
    private Long auctionUnid;
    @Column(name = "val_unid")
    private Long valUnid;
    @Column(name = "acc_oper_unid")
    private Long accOperUnid;
    @Column(name = "docum_ind_export")
    private Integer documIndExport;
    @Column(name = "ba_unid")
    private Long baUnid;
    @Column(name = "docum_ind_export_etp")
    private Integer documIndExportEtp;
    @Column(name = "docum_ind_export_feed")
    private Integer documIndExportFeed;
    @Column(name = "docum_order_num")
    private Integer documOrderNum;
    @Column(name = "docum_status")
    private Long documStatus;
    @JoinColumn(name = "dgt_unid", referencedColumnName = "dgt_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private DocGenerationType dgtUnid;
    @JoinColumn(name = "dt_unid", referencedColumnName = "dt_unid")
    @ManyToOne(fetch = FetchType.EAGER)
    private DocTemplate dtUnid;
    @Column(name = "docum_form_result", length = 2147483647)
    private String documFormResult;
    @Column(name = "event_unid")
    private Long eventUnid;
    @Column(name = "pa_unid")
    private Long paUnid;
    @Column(name = "docum_pay_doc_id")
    private Long documPayDocId;

    public Document() {
    }

    public Document(long documUnid) {
        this.documUnid = documUnid;
    }

    public long getDocumUnid() {
        return documUnid;
    }

    public void setDocumUnid(long documUnid) {
        this.documUnid = documUnid;
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

    public DocFile getDfScanUnid() {
        return dfScanUnid;
    }

    public void setDfScanUnid(DocFile dfScanUnid) {
        this.dfScanUnid = dfScanUnid;
    }

    public Long getPersCodeBRec() {
        return persCodeBRec;
    }

    public void setPersCodeBRec(Long persCodeBRec) {
        this.persCodeBRec = persCodeBRec;
    }

    public String getDocumName() {
        return documName;
    }

    public void setDocumName(String documName) {
        this.documName = documName;
    }

    public String getDocumNumber() {
        return documNumber;
    }

    public void setDocumNumber(String documNumber) {
        this.documNumber = documNumber;
    }

    public String getDocumSeries() {
        return documSeries;
    }

    public void setDocumSeries(String documSeries) {
        this.documSeries = documSeries;
    }

    public Date getDocumDate() {
        return documDate;
    }

    public void setDocumDate(Date documDate) {
        this.documDate = documDate;
    }

    public Boolean getDocumIndCheck() {
        return documIndCheck == null ? false : documIndCheck == 1;
    }

    public void setDocumIndCheck(Boolean documIndCheck) {
        this.documIndCheck = documIndCheck == null ? (short) 0 : (documIndCheck ? (short) 1 : (short) 0);
    }

    public Long getDocDocumUnid() {
        return docDocumUnid;
    }

    public void setDocDocumUnid(Long docDocumUnid) {
        this.docDocumUnid = docDocumUnid;
    }

    @XmlTransient
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

    public TypeDoc getTdUnid() {
        return tdUnid;
    }

    public void setTdUnid(TypeDoc tdUnid) {
        this.tdUnid = tdUnid;
    }

    public Long getDealUnid() {
        return dealUnid;
    }

    public void setDealUnid(Long dealUnid) {
        this.dealUnid = dealUnid;
    }

    public Long getComUnid() {
        return comUnid;
    }

    public void setComUnid(Long comUnid) {
        this.comUnid = comUnid;
    }

    public Long getDsUnid() {
        return dsUnid;
    }

    public void setDsUnid(Long dsUnid) {
        this.dsUnid = dsUnid;
    }

    public Application getApplicatUnid() {
        return applicatUnid;
    }

    public void setApplicatUnid(Application applicatUnid) {
        this.applicatUnid = applicatUnid;
    }

    public Long getAuctionUnid() {
        return auctionUnid;
    }

    public void setAuctionUnid(Long auctionUnid) {
        this.auctionUnid = auctionUnid;
    }

    public Long getLotUnid() {
        return lotUnid;
    }

    public void setLotUnid(Long lotUnid) {
        this.lotUnid = lotUnid;
    }

    public Long getValUnid() {
        return valUnid;
    }

    public void setValUnid(Long valUnid) {
        this.valUnid = valUnid;
    }

    public Boolean getDocumIndExport() {
        return documIndExport == null ? false : documIndExport == 1;
    }

    public void setDocumIndExport(Boolean documIndExport) {
        this.documIndExport = documIndExport == null ? 0 : (documIndExport ? 1 : 0);
    }

    public Long getAccOperUnid() {
        return accOperUnid;
    }

    public void setAccOperUnid(Long accOperUnid) {
        this.accOperUnid = accOperUnid;
    }

    public Long getBaUnid() {
        return baUnid;
    }

    public void setBaUnid(Long baUnid) {
        this.baUnid = baUnid;
    }

    public Boolean getDocumIndExportEtp() {
        return documIndExportEtp != null && documIndExportEtp == 1;
    }

    public void setDocumIndExportEtp(Boolean documIndExportEtp) {
        this.documIndExportEtp = documIndExportEtp == null ? 0 : (documIndExportEtp ? 1 : 0);
    }

    public Boolean getDocumIndExportFeed() {
        return documIndExportFeed != null && documIndExportFeed == 1;
    }

    public void setDocumIndExportFeed(Boolean documIndExportFeed) {
        this.documIndExportFeed = documIndExportFeed == null ? 0 : (documIndExportFeed ? 1 : 0);
    }

    public Integer getDocumOrderNum() {
        return documOrderNum;
    }

    public void setDocumOrderNum(Integer documOrderNum) {
        this.documOrderNum = documOrderNum;
    }

    public Long getDocumStatus() {
        return documStatus;
    }

    public void setDocumStatus(Long documStatus) {
        this.documStatus = documStatus;
    }

    public DocGenerationType getDgtUnid() {
        return dgtUnid;
    }

    public void setDgtUnid(DocGenerationType dgtUnid) {
        this.dgtUnid = dgtUnid;
    }

    public DocTemplate getDtUnid() {
        return dtUnid;
    }

    public void setDtUnid(DocTemplate dtUnid) {
        this.dtUnid = dtUnid;
    }

    public String getDocumFormResult() {
        return documFormResult;
    }

    public void setDocumFormResult(String documFormResult) {
        this.documFormResult = documFormResult;
    }

    public Long getEventUnid() {
        return eventUnid;
    }

    public void setEventUnid(Long eventUnid) {
        this.eventUnid = eventUnid;
    }

    public Long getPaUnid() {
        return paUnid;
    }

    public void setPaUnid(Long paUnid) {
        this.paUnid = paUnid;
    }

    public Long getDocumPayDocId() {
        return documPayDocId;
    }

    public void setDocumPayDocId(Long documPayDocId) {
        this.documPayDocId = documPayDocId;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.Document[ documUnid=" + documUnid + ", addr=" + super.hashCode() + " ]";
    }

}
