package com.baltinfo.radius.db.model;


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
 * @author css
 */
@Entity
@Table(name = "subject", schema = "web", catalog = "", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"sub_unid"})})
@SequenceGenerator(name = "seq_subject", sequenceName = "seq_subject", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Subject.findOrg", query = "SELECT s FROM Subject s WHERE s.indActual = 1 and s.typesUnid = 2")
})

public class Subject implements Serializable, IHistory {
    public static final String KIO_INN = "7832000076";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_subject")
    @Column(name = "sub_unid", nullable = false, precision = 2147483647, scale = 0)
    private Long subUnid;
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
    @Column(name = "sub_name", length = 500)
    private String subName;
    @Column(name = "sub_sname", length = 200)
    private String subSname;
    @Column(name = "sub_name_f", length = 50)
    private String subNameF;
    @Column(name = "sub_name_o", length = 50)
    private String subNameO;
    @Column(name = "sub_name_i", length = 50)
    private String subNameI;
    @Column(name = "sub_sex")
    private Integer subSex;
    @Column(name = "sub_birthday")
    @Temporal(TemporalType.TIMESTAMP)
    private Date subBirthday;
    @Column(name = "sub_birthplace", length = 250)
    private String subBirthplace;
    @Column(name = "sub_reg_organ", length = 150)
    private String subRegOrgan;
    @Column(name = "sub_reg_number", length = 100)
    private String subRegNumber;
    @Column(name = "sub_reg_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date subRegDate;
    @Column(name = "sub_docum_name", length = 200)
    private String subDocumName;
    @Column(name = "sub_docum_series", length = 30)
    private String subDocumSeries;
    @Column(name = "sub_docum_number", length = 30)
    private String subDocumNumber;
    @Column(name = "sub_docum_issue_agency", length = 200)
    private String subDocumIssueAgency;
    @Column(name = "sub_docum_issue_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date subDocumIssueDate;
    @Column(name = "sub_okpo", length = 50)
    private String subOkpo;
    @Column(name = "sub_inn", length = 12)
    private String subInn;
    @Column(name = "sub_addr_legal", length = 250)
    private String subAddrLegal;
    @Column(name = "sub_addr_fact", length = 250)
    private String subAddrFact;
    @Column(name = "sub_phone", length = 50)
    private String subPhone;
    @Column(name = "sub_fax", length = 50)
    private String subFax;
    @Column(name = "sub_e_mail", length = 256)
    private String subEMail;
    @Column(name = "sub_web", length = 500)
    private String subWeb;
    @Column(name = "sub_code_kpp", length = 20)
    private String subCodeKpp;
    @Column(name = "sub_ogrn", length = 20)
    private String subOgrn;
    @Column(name = "sub_region_code", length = 20)
    private String subRegionCode;
    @Column(name = "sub_snils", length = 11)
    private String subSnils;
    @Column(name = "sub_region_name", length = 255)
    private String subRegionName;
    @Column(name = "sub_okato", length = 50)
    private String subOkato;
    @Column(name = "sub_guid", length = 36)
    private String subGuid;
    @Column(name = "sub_ind_block")
    private Long subIndBlock;
    @Column(name = "sub_ind_rad")
    private Long subIndRad;
    @Column(name = "types_unid", nullable = false)
    private Long typesUnid;
    @JoinColumn(name = "sub_sub_unid", referencedColumnName = "sub_unid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subSubUnid;
    @Column(name = "opf_unid")
    private Long opfUnid;
    @Column(name = "oktmo_unid")
    private Long oktmoUnid;
    @Column(name = "okfs_unid")
    private Long okfsUnid;
    @Column(name = "bank_unid")
    private Long bankUnid;
    @Column(name = "okcm_unid")
    private Long okcmUnid;
    @Column(name = "slim_unid")
    private Long slimUnid;
    @Column(name = "sub_reg_edit_doc", length = 500)
    private String subRegEditDoc;
    @Column(name = "sub_reg_edit_organ", length = 500)
    private String subRegEditOrgan;
    @Column(name = "sub_reg_edit_num", length = 100)
    private String subRegEditNum;
    @Column(name = "sub_reg_edit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date subRegEditDate;
    @Column(name = "sub_mobile_phone", length = 50)
    private String subMobilePhone;
    @Column(name = "sub_addr_legal_fias_id", length = 50)
    private String subAddrLegalFiasId;
    @Column(name = "sub_addr_fact_fias_id", length = 50)
    private String subAddrFactFiasId;
    @Column(name = "sub_additional_email")
    private String subAdditionalEmail;

    public Subject() {

    }

    public long getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(long subUnid) {
        this.subUnid = subUnid;
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

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubSname() {
        return subSname;
    }

    public void setSubSname(String subSname) {
        this.subSname = subSname;
    }

    public String getSubNameF() {
        return subNameF;
    }

    public void setSubNameF(String subNameF) {
        this.subNameF = subNameF;
    }

    public String getSubNameO() {
        return subNameO;
    }

    public void setSubNameO(String subNameO) {
        this.subNameO = subNameO;
    }

    public String getSubNameI() {
        return subNameI;
    }

    public void setSubNameI(String subNameI) {
        this.subNameI = subNameI;
    }

    public Integer getSubSex() {
        return subSex;
    }

    public void setSubSex(Integer subSex) {
        this.subSex = subSex;
    }

    public Date getSubBirthday() {
        return subBirthday;
    }

    public void setSubBirthday(Date subBirthday) {
        this.subBirthday = subBirthday;
    }

    public String getSubBirthplace() {
        return subBirthplace;
    }

    public void setSubBirthplace(String subBirthplace) {
        this.subBirthplace = subBirthplace;
    }

    public String getSubRegOrgan() {
        return subRegOrgan;
    }

    public void setSubRegOrgan(String subRegOrgan) {
        this.subRegOrgan = subRegOrgan;
    }

    public String getSubRegNumber() {
        return subRegNumber;
    }

    public void setSubRegNumber(String subRegNumber) {
        this.subRegNumber = subRegNumber;
    }

    public Date getSubRegDate() {
        return subRegDate;
    }

    public void setSubRegDate(Date subRegDate) {
        this.subRegDate = subRegDate;
    }

    public String getSubDocumName() {
        return subDocumName;
    }

    public void setSubDocumName(String subDocumName) {
        this.subDocumName = subDocumName;
    }

    public String getSubDocumSeries() {
        return subDocumSeries;
    }

    public void setSubDocumSeries(String subDocumSeries) {
        this.subDocumSeries = subDocumSeries;
    }

    public String getSubDocumNumber() {
        return subDocumNumber;
    }

    public void setSubDocumNumber(String subDocumNumber) {
        this.subDocumNumber = subDocumNumber;
    }

    public String getSubDocumIssueAgency() {
        return subDocumIssueAgency;
    }

    public void setSubDocumIssueAgency(String subDocumIssueAgency) {
        this.subDocumIssueAgency = subDocumIssueAgency;
    }

    public Date getSubDocumIssueDate() {
        return subDocumIssueDate;
    }

    public void setSubDocumIssueDate(Date subDocumIssueDate) {
        this.subDocumIssueDate = subDocumIssueDate;
    }

    public String getSubOkpo() {
        return subOkpo;
    }

    public void setSubOkpo(String subOkpo) {
        this.subOkpo = subOkpo;
    }

    public String getSubInn() {
        return subInn;
    }

    public void setSubInn(String subInn) {
        this.subInn = subInn;
    }

    public String getSubAddrLegal() {
        return subAddrLegal;
    }

    public void setSubAddrLegal(String subAddrLegal) {
        this.subAddrLegal = subAddrLegal;
    }

    public String getSubAddrFact() {
        return subAddrFact;
    }

    public void setSubAddrFact(String subAddrFact) {
        this.subAddrFact = subAddrFact;
    }

    public String getSubPhone() {
        return subPhone;
    }

    public void setSubPhone(String subPhone) {
        this.subPhone = subPhone;
    }

    public String getSubFax() {
        return subFax;
    }

    public void setSubFax(String subFax) {
        this.subFax = subFax;
    }

    public String getSubEMail() {
        return subEMail;
    }

    public void setSubEMail(String subEMail) {
        this.subEMail = subEMail;
    }

    public String getSubWeb() {
        return subWeb;
    }

    public void setSubWeb(String subWeb) {
        this.subWeb = subWeb;
    }

    public String getSubCodeKpp() {
        return subCodeKpp;
    }

    public void setSubCodeKpp(String subCodeKpp) {
        this.subCodeKpp = subCodeKpp;
    }

    public String getSubOgrn() {
        return subOgrn;
    }

    public void setSubOgrn(String subOgrn) {
        this.subOgrn = subOgrn;
    }

    public String getSubRegionCode() {
        return subRegionCode;
    }

    public void setSubRegionCode(String subRegionCode) {
        this.subRegionCode = subRegionCode;
    }

    public String getSubSnils() {
        return subSnils;
    }

    public void setSubSnils(String subSnils) {
        this.subSnils = subSnils;
    }

    public String getSubRegionName() {
        return subRegionName;
    }

    public void setSubRegionName(String subRegionName) {
        this.subRegionName = subRegionName;
    }

    public String getSubOkato() {
        return subOkato;
    }

    public void setSubOkato(String subOkato) {
        this.subOkato = subOkato;
    }

    public String getSubGuid() {
        return subGuid;
    }

    public void setSubGuid(String subGuid) {
        this.subGuid = subGuid;
    }

    public Long getSubIndBlock() {
        return subIndBlock;
    }

    public void setSubIndBlock(Long subIndBlock) {
        this.subIndBlock = subIndBlock;
    }

    public Long getTypesUnid() {
        return typesUnid;
    }

    public void setTypesUnid(Long typesUnid) {
        this.typesUnid = typesUnid;
    }

    public Long getSubIndRad() {
        return subIndRad;
    }

    public void setSubIndRad(Long subIndRad) {
        this.subIndRad = subIndRad;
    }

    public Subject getSubSubUnid() {
        return subSubUnid;
    }

    public void setSubSubUnid(Subject subSubUnid) {
        this.subSubUnid = subSubUnid;
    }

    public Long getOpfUnid() {
        return opfUnid;
    }

    public void setOpfUnid(Long opfUnid) {
        this.opfUnid = opfUnid;
    }

    public Long getOktmoUnid() {
        return oktmoUnid;
    }

    public void setOktmoUnid(Long oktmoUnid) {
        this.oktmoUnid = oktmoUnid;
    }

    public Long getOkfsUnid() {
        return okfsUnid;
    }

    public void setOkfsUnid(Long okfsUnid) {
        this.okfsUnid = okfsUnid;
    }

    public Long getBankUnid() {
        return bankUnid;
    }

    public void setBankUnid(Long bankUnid) {
        this.bankUnid = bankUnid;
    }

    public Long getOkcmUnid() {
        return okcmUnid;
    }

    public void setOkcmUnid(Long okcmUnid) {
        this.okcmUnid = okcmUnid;
    }

    public Long getSlimUnid() {
        return slimUnid;
    }

    public void setSlimUnid(Long slimUnid) {
        this.slimUnid = slimUnid;
    }

    public String getSubRegEditDoc() {
        return subRegEditDoc;
    }

    public void setSubRegEditDoc(String subRegEditDoc) {
        this.subRegEditDoc = subRegEditDoc;
    }

    public String getSubRegEditOrgan() {
        return subRegEditOrgan;
    }

    public void setSubRegEditOrgan(String subRegEditOrgan) {
        this.subRegEditOrgan = subRegEditOrgan;
    }

    public String getSubRegEditNum() {
        return subRegEditNum;
    }

    public void setSubRegEditNum(String subRegEditNum) {
        this.subRegEditNum = subRegEditNum;
    }

    public Date getSubRegEditDate() {
        return subRegEditDate;
    }

    public void setSubRegEditDate(Date subRegEditDate) {
        this.subRegEditDate = subRegEditDate;
    }

    public String getSubMobilePhone() {
        return subMobilePhone;
    }

    public void setSubMobilePhone(String subMobilePhone) {
        this.subMobilePhone = subMobilePhone;
    }

    public String getSubAddrLegalFiasId() {
        return subAddrLegalFiasId;
    }

    public void setSubAddrLegalFiasId(String subAddrLegalFiasId) {
        this.subAddrLegalFiasId = subAddrLegalFiasId;
    }

    public String getSubAddrFactFiasId() {
        return subAddrFactFiasId;
    }

    public void setSubAddrFactFiasId(String subAddrFactFiasId) {
        this.subAddrFactFiasId = subAddrFactFiasId;
    }

    public String getSubAdditionalEmail() {
        return subAdditionalEmail;
    }

    public void setSubAdditionalEmail(String subAdditionalEmail) {
        this.subAdditionalEmail = subAdditionalEmail;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.Subject[ subUnid=" + subUnid + " ]";
    }

}
