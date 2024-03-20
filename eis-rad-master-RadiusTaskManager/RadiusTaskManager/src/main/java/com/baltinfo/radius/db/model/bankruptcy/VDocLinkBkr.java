package com.baltinfo.radius.db.model.bankruptcy;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author sas
 */
@Entity
@Table(name = "DOC_LINK", catalog = "", schema = "WEB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VDocLinkBkr.findAll", query = "SELECT d FROM VDocLinkBkr d")})
public class VDocLinkBkr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @Column(name = "DOC_LINK_UNID")
    private long docLinkUnid;
    @Column(name = "DOC_LINK_CODE_ENTITY")
    private String docLinkCodeEntity;
    @Column(name = "DOC_LINK_ENTITY_UNID")
    private Long docLinkEntityUnid;
    @Basic(optional = false)
    @Column(name = "DOCUM_UNID")
    private long documUnid;
    @Column(name = "PERSON_UNID")
    private Long personUnid;
    @Column(name = "WB_UNID")
    private Long wbUnid;
    @Column(name = "STATE_UNID")
    private Long stateUnid;
    @Column(name = "STATUS_DOC_UNID")
    private Long statusDocUnid;
    @Column(name = "ENF_UNID")
    private Long enfUnid;
    @Column(name = "DOC_TEMPLATE_UNID")
    private Long docTemplateUnid;
    @Column(name = "LAW_PROC_UNID")
    private Long lawProcUnid;
    @Column(name = "LC_UNID")
    private Long lcUnid;
    @Column(name = "MSG_UNID")
    private Long msgUnid;
    @Column(name = "DOC_DOCUM_UNID")
    private Long docDocumUnid;
    @Basic(optional = false)
    @Column(name = "TYPE_DOC_UNID")
    private long typeDocUnid;
    @Column(name = "MAIL_UNID")
    private Long mailUnid;
    @Column(name = "DOCUM_NAME")
    private String documName;
    @Column(name = "DOCUM_NUMBER")
    private String documNumber;
    @Column(name = "DOCUM_SERIES")
    private String documSeries;
    @Column(name = "DOCUM_ISSUE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date documIssueDate;
    @Column(name = "DOCUM_ISSUE_AGENCY")
    private String documIssueAgency;
    @Column(name = "DOCUM_REG_NUMBER")
    private String documRegNumber;
    @Column(name = "DOCUM_REG_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date documRegDate;
    @Column(name = "DOCUM_REG_AGENCY")
    private String documRegAgency;
    @Column(name = "DOCUM_NOT_NUMBER")
    private String documNotNumber;
    @Column(name = "DOCUM_NOT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date documNotDate;
    @Column(name = "DOCUM_NOTE")
    private String documNote;
    @Column(name = "DOCUM_IND_DIGITAL_SIGN")
    private Short documIndDigitalSign;
    @Column(name = "DOCUM_DATE_B")
    @Temporal(TemporalType.TIMESTAMP)
    private Date documDateB;
    @Column(name = "DOCUM_DATE_E")
    @Temporal(TemporalType.TIMESTAMP)
    private Date documDateE;
    @Column(name = "DOCUM_NOTICE_NUM")
    private String documNoticeNum;
    @Column(name = "STATUS_DOC_NAME")
    private String statusDocName;
    @Column(name = "DOC_BODY_FILE")
    private String docBodyFile;
    @Column(name = "DOC_BODY_UNID")
    private Long docBodyUnid;
    @Lob
    @Column(name = "DOC_BODY_BODY")
    private byte[] docBodyBody;
    @Column(name = "PA_UNID")
    private BigInteger paUnid;
    @Column(name = "IND_EXTRACT_SIGN")
    private BigInteger indExtractSign;
    @Column(name = "TYPE_DOC_NAME")
    private String typeDocName;
    @Column(name = "ORGANIZER_UNID")
    private BigInteger organizerUnid;
    @Column(name = "DF_UNID")
    private Long dfUnid;

    @Column(name = "DF_FILE_PATH")
    private String dfFilePath;

    @Column(name = "DF_FILE_HASH")
    private String dfFileHash;

    public VDocLinkBkr() {
    }

    public long getDocLinkUnid() {
        return docLinkUnid;
    }

    public void setDocLinkUnid(long docLinkUnid) {
        this.docLinkUnid = docLinkUnid;
    }

    public String getDocLinkCodeEntity() {
        return docLinkCodeEntity;
    }

    public void setDocLinkCodeEntity(String docLinkCodeEntity) {
        this.docLinkCodeEntity = docLinkCodeEntity;
    }

    public Long getDocLinkEntityUnid() {
        return docLinkEntityUnid;
    }

    public void setDocLinkEntityUnid(Long docLinkEntityUnid) {
        this.docLinkEntityUnid = docLinkEntityUnid;
    }

    public long getDocumUnid() {
        return documUnid;
    }

    public void setDocumUnid(long documUnid) {
        this.documUnid = documUnid;
    }

    public Long getPersonUnid() {
        return personUnid;
    }

    public void setPersonUnid(Long personUnid) {
        this.personUnid = personUnid;
    }

    public Long getWbUnid() {
        return wbUnid;
    }

    public void setWbUnid(Long wbUnid) {
        this.wbUnid = wbUnid;
    }

    public Long getStateUnid() {
        return stateUnid;
    }

    public void setStateUnid(Long stateUnid) {
        this.stateUnid = stateUnid;
    }

    public Long getStatusDocUnid() {
        return statusDocUnid;
    }

    public void setStatusDocUnid(Long statusDocUnid) {
        this.statusDocUnid = statusDocUnid;
    }

    public Long getEnfUnid() {
        return enfUnid;
    }

    public void setEnfUnid(Long enfUnid) {
        this.enfUnid = enfUnid;
    }

    public Long getDocTemplateUnid() {
        return docTemplateUnid;
    }

    public void setDocTemplateUnid(Long docTemplateUnid) {
        this.docTemplateUnid = docTemplateUnid;
    }

    public Long getLawProcUnid() {
        return lawProcUnid;
    }

    public void setLawProcUnid(Long lawProcUnid) {
        this.lawProcUnid = lawProcUnid;
    }

    public Long getLcUnid() {
        return lcUnid;
    }

    public void setLcUnid(Long lcUnid) {
        this.lcUnid = lcUnid;
    }

    public Long getMsgUnid() {
        return msgUnid;
    }

    public void setMsgUnid(Long msgUnid) {
        this.msgUnid = msgUnid;
    }

    public Long getDocDocumUnid() {
        return docDocumUnid;
    }

    public void setDocDocumUnid(Long docDocumUnid) {
        this.docDocumUnid = docDocumUnid;
    }

    public long getTypeDocUnid() {
        return typeDocUnid;
    }

    public void setTypeDocUnid(long typeDocUnid) {
        this.typeDocUnid = typeDocUnid;
    }

    public Long getMailUnid() {
        return mailUnid;
    }

    public void setMailUnid(Long mailUnid) {
        this.mailUnid = mailUnid;
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

    public Date getDocumIssueDate() {
        return documIssueDate;
    }

    public void setDocumIssueDate(Date documIssueDate) {
        this.documIssueDate = documIssueDate;
    }

    public String getDocumIssueAgency() {
        return documIssueAgency;
    }

    public void setDocumIssueAgency(String documIssueAgency) {
        this.documIssueAgency = documIssueAgency;
    }

    public String getDocumRegNumber() {
        return documRegNumber;
    }

    public void setDocumRegNumber(String documRegNumber) {
        this.documRegNumber = documRegNumber;
    }

    public Date getDocumRegDate() {
        return documRegDate;
    }

    public void setDocumRegDate(Date documRegDate) {
        this.documRegDate = documRegDate;
    }

    public String getDocumRegAgency() {
        return documRegAgency;
    }

    public void setDocumRegAgency(String documRegAgency) {
        this.documRegAgency = documRegAgency;
    }

    public String getDocumNotNumber() {
        return documNotNumber;
    }

    public void setDocumNotNumber(String documNotNumber) {
        this.documNotNumber = documNotNumber;
    }

    public Date getDocumNotDate() {
        return documNotDate;
    }

    public void setDocumNotDate(Date documNotDate) {
        this.documNotDate = documNotDate;
    }

    public String getDocumNote() {
        return documNote;
    }

    public void setDocumNote(String documNote) {
        this.documNote = documNote;
    }

    public Short getDocumIndDigitalSign() {
        return documIndDigitalSign;
    }

    public void setDocumIndDigitalSign(Short documIndDigitalSign) {
        this.documIndDigitalSign = documIndDigitalSign;
    }

    public Date getDocumDateB() {
        return documDateB;
    }

    public void setDocumDateB(Date documDateB) {
        this.documDateB = documDateB;
    }

    public Date getDocumDateE() {
        return documDateE;
    }

    public void setDocumDateE(Date documDateE) {
        this.documDateE = documDateE;
    }

    public String getDocumNoticeNum() {
        return documNoticeNum;
    }

    public void setDocumNoticeNum(String documNoticeNum) {
        this.documNoticeNum = documNoticeNum;
    }

    public String getStatusDocName() {
        return statusDocName;
    }

    public void setStatusDocName(String statusDocName) {
        this.statusDocName = statusDocName;
    }

    public String getDocBodyFile() {
        return docBodyFile;
    }

    public void setDocBodyFile(String docBodyFile) {
        this.docBodyFile = docBodyFile;
    }

    public Long getDocBodyUnid() {
        return docBodyUnid;
    }

    public void setDocBodyUnid(Long docBodyUnid) {
        this.docBodyUnid = docBodyUnid;
    }

    public byte[] getDocBodyBody() {
        return docBodyBody;
    }

    public void setDocBodyBody(byte[] docBodyBody) {
        this.docBodyBody = docBodyBody;
    }

    public BigInteger getPaUnid() {
        return paUnid;
    }

    public void setPaUnid(BigInteger paUnid) {
        this.paUnid = paUnid;
    }

    public BigInteger getIndExtractSign() {
        return indExtractSign;
    }

    public void setIndExtractSign(BigInteger indExtractSign) {
        this.indExtractSign = indExtractSign;
    }

    public String getTypeDocName() {
        return typeDocName;
    }

    public void setTypeDocName(String typeDocName) {
        this.typeDocName = typeDocName;
    }

    public BigInteger getOrganizerUnid() {
        return organizerUnid;
    }

    public void setOrganizerUnid(BigInteger organizerUnid) {
        this.organizerUnid = organizerUnid;
    }

    public Long getDfUnid() {
        return dfUnid;
    }

    public void setDfUnid(Long dfUnid) {
        this.dfUnid = dfUnid;
    }

    public String getDfFilePath() {
        return dfFilePath;
    }

    public void setDfFilePath(String dfFilePath) {
        this.dfFilePath = dfFilePath;
    }

    public String getDfFileHash() {
        return dfFileHash;
    }

    public void setDfFileHash(String dfFileHash) {
        this.dfFileHash = dfFileHash;
    }
}
