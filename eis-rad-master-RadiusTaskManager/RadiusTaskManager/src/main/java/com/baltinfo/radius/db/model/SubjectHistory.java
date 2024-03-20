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
import java.io.Serializable;
import java.util.Date;

/**
 * @author sas
 */
@Entity
@Table(catalog = "", name = "subject_history", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"sh_unid"})})
@SequenceGenerator(name = "seq_subject_history", sequenceName = "seq_subject_history", allocationSize = 1)
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "SubjectHistory.findAll", query = "SELECT s FROM SubjectHistory s where s.indActual = 1")})
public class SubjectHistory implements IHistory {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_subject_history")
    @Basic(optional = false)
    @Column(name = "sh_unid")
    private Long shUnid;
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
    @Column(name = "sh_sub_name")
    private String shSubName;
    @Column(name = "sh_sub_sname")
    private String shSubSname;
    @Column(name = "sh_sub_name_f")
    private String shSubNameF;
    @Column(name = "sh_sub_name_o")
    private String shSubNameO;
    @Column(name = "sh_sub_name_i")
    private String shSubNameI;
    @Column(name = "sh_sub_sex")
    private Integer shSubSex;
    @Column(name = "sh_sub_birthday")
    @Temporal(TemporalType.DATE)
    private Date shSubBirthday;
    @Column(name = "sh_sub_birthplace")
    private String shSubBirthplace;
    @Column(name = "sh_sub_reg_organ")
    private String shSubRegOrgan;
    @Column(name = "sh_sub_reg_number")
    private String shSubRegNumber;
    @Column(name = "sh_sub_reg_date")
    @Temporal(TemporalType.DATE)
    private Date shSubRegDate;
    @Column(name = "sh_sub_docum_name")
    private String shSubDocumName;
    @Column(name = "sh_sub_docum_series")
    private String shSubDocumSeries;
    @Column(name = "sh_sub_docum_number")
    private String shSubDocumNumber;
    @Column(name = "sh_sub_docum_issue_agency")
    private String shSubDocumIssueAgency;
    @Column(name = "sh_sub_docum_issue_date")
    @Temporal(TemporalType.DATE)
    private Date shSubDocumIssueDate;
    @Column(name = "sh_sub_okpo")
    private String shSubOkpo;
    @Column(name = "sh_sub_inn")
    private String shSubInn;
    @Column(name = "sh_sub_addr_legal")
    private String shSubAddrLegal;
    @Column(name = "sh_sub_addr_fact")
    private String shSubAddrFact;
    @Column(name = "sh_sub_phone")
    private String shSubPhone;
    @Column(name = "sh_sub_fax")
    private String shSubFax;
    @Column(name = "sh_sub_e_mail", length = 256)
    private String shSubEMail;
    @Column(name = "sh_sub_web")
    private String shSubWeb;
    @Column(name = "sh_sub_code_kpp")
    private String shSubCodeKpp;
    @Column(name = "sh_sub_ogrn")
    private String shSubOgrn;
    @Column(name = "sh_sub_register_num")
    private String shSubRegisterNum;
    @Column(name = "sh_sub_registration_time")
    @Temporal(TemporalType.DATE)
    private Date shSubRegistrationTime;
    @Column(name = "sh_sub_ind_registr")
    private Integer shSubIndRegistr;
    @Column(name = "sh_sub_account_name")
    private String shSubAccountName;
    @Column(name = "sh_sub_account_code")
    private String shSubAccountCode;
    @Column(name = "sh_sub_foregn_staff")
    private String shSubForegnStaff;
    @Column(name = "sh_sub_man_f")
    private String shSubManF;
    @Column(name = "sh_sub_man_i")
    private String shSubManI;
    @Column(name = "sh_sub_man_o")
    private String shSubManO;
    @Column(name = "sh_sub_man_base")
    private String shSubManBase;
    @Column(name = "sh_sub_man_post")
    private String shSubManPost;
    @Column(name = "sh_sub_bank_name")
    private String shSubBankName;
    @Column(name = "sh_sub_bank_addr")
    private String shSubBankAddr;
    @Column(name = "sh_sub_bank_bic")
    private String shSubBankBic;
    @Column(name = "sh_sub_bank_account")
    private String shSubBankAccount;
    @Column(name = "sh_sub_bank_inn")
    private String shSubBankInn;
    @Column(name = "sh_sub_bank_kpp")
    private String shSubBankKpp;
    @Column(name = "sh_sub_rfsubjectcode")
    private String shSubRfsubjectcode;
    @Column(name = "sh_sub_timezone_offset")
    private Integer shSubTimezoneOffset;
    @Column(name = "sh_sub_timezone_name")
    private String shSubTimezoneName;
    @Column(name = "sh_sub_snils")
    private String shSubSnils;
    @Column(name = "sh_sub_okato")
    private String shSubOkato;
    @Column(name = "sh_pa_sub_name_f")
    private String shPaSubNameF;
    @Column(name = "sh_pa_sub_name_i")
    private String shPaSubNameI;
    @Column(name = "sh_pa_sub_name_o")
    private String shPaSubNameO;
    @Column(name = "sh_pa_sub_sex")
    private Integer shPaSubSex;
    @Column(name = "sh_pa_sub_birthday")
    @Temporal(TemporalType.DATE)
    private Date shPaSubBirthday;
    @Column(name = "sh_pa_sub_birthplace")
    private String shPaSubBirthplace;
    @Column(name = "sh_pa_sub_docum_name")
    private String shPaSubDocumName;
    @Column(name = "sh_pa_sub_docum_number")
    private String shPaSubDocumNumber;
    @Column(name = "sh_pa_sub_docum_series")
    private String shPaSubDocumSeries;
    @Column(name = "sh_pa_sub_docum_issue_agency")
    private String shPaSubDocumIssueAgency;
    @Column(name = "sh_pa_sub_docum_issue_date")
    @Temporal(TemporalType.DATE)
    private Date shPaSubDocumIssueDate;
    @Column(name = "sh_pa_sub_phone")
    private String shPaSubPhone;
    @Column(name = "sh_pa_sub_fax")
    private String shPaSubFax;
    @Column(name = "sh_pa_sub_e_mail", length = 256)
    private String shPaSubEMail;
    @Column(name = "sh_pa_doc_name")
    private String shPaDocName;
    @Column(name = "sh_pa_doc_number")
    private String shPaDocNumber;
    @Column(name = "sh_pa_doc_date")
    @Temporal(TemporalType.DATE)
    private Date shPaDocDate;
    @Column(name = "sh_pa_doc_date_b")
    @Temporal(TemporalType.DATE)
    private Date shPaDocDateB;
    @Column(name = "sh_pa_doc_date_e")
    @Temporal(TemporalType.DATE)
    private Date shPaDocDateE;
    @Column(name = "sh_pa_ind_send_notification")
    private Integer shPaIndSendNotification;
    @Column(name = "sh_pa_ind_man")
    private Integer shPaIndMan;
    @Column(name = "sh_pa_snils")
    private String shPaSnils;
    @Column(name = "bank_unid")
    private Long bankUnid;
    @Column(name = "dla_unid")
    private Long dlaUnid;
    @Column(name = "okcm_unid")
    private Long okcmUnid;
    @Column(name = "okfs_unid")
    private Long okfsUnid;
    @Column(name = "oktmo_unid")
    private Long oktmoUnid;
    @Column(name = "opf_unid")
    private Long opfUnid;
    @Column(name = "pa_unid")
    private Long paUnid;
    @Column(name = "sub_unid")
    private Long subUnid;
    @Column(name = "types_unid", nullable = false)
    private Long typesUnid;
    @Column(name = "sub_sub_unid")
    private Long subSubUnid;
    @Column(name = "sh_sub_man_sex")
    private Integer shSubManSex;
    @Column(name = "sh_pa_base")
    private String shPaBase;
    @Column(name = "sh_pa_post")
    private String shPaPost;
    @Column(name = "dla_pa_unid")
    private Long dlaPaUnid;
    @Column(name = "sh_sub_reg_edit_doc", length = 500)
    private String shSubRegEditDoc;
    @Column(name = "sh_sub_reg_edit_organ", length = 500)
    private String shSubRegEditOrgan;
    @Column(name = "sh_sub_reg_edit_num", length = 100)
    private String shSubRegEditNum;
    @Column(name = "sh_sub_reg_edit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shSubRegEditDate;
    @Column(name = "sub_sh_unid")
    private Long subShUnid;
    @Column(name = "sh_sub_mobile_phone", length = 50)
    private String shSubMobilePhone;
    @Column(name = "sh_sub_addr_legal_fias_id", length = 50)
    private String shSubAddrLegalFiasId;
    @Column(name = "sh_sub_addr_fact_fias_id", length = 50)
    private String shSubAddrFactFiasId;

    public SubjectHistory() {
    }

    public SubjectHistory(SubjectHistory sh) {
        shSubName = sh.getShSubName();
        shSubSname = sh.getShSubSname();
        shSubNameF = sh.getShSubNameF();
        shSubNameO = sh.getShSubNameO();
        shSubNameI = sh.getShSubNameI();
        shSubSex = sh.getShSubSex();
        shSubBirthday = sh.getShSubBirthday();
        shSubBirthplace = sh.getShSubBirthplace();
        shSubRegOrgan = sh.getShSubRegOrgan();
        shSubRegNumber = sh.getShSubRegNumber();
        shSubRegDate = sh.getShSubRegDate();
        shSubDocumName = sh.getShSubDocumName();
        shSubDocumSeries = sh.getShSubDocumSeries();
        shSubDocumNumber = sh.getShSubDocumNumber();
        shSubDocumIssueAgency = sh.getShSubDocumIssueAgency();
        shSubDocumIssueDate = sh.getShSubDocumIssueDate();
        shSubOkpo = sh.getShSubOkpo();
        shSubInn = sh.getShSubInn();
        shSubAddrLegal = sh.getShSubAddrLegal();
        shSubAddrFact = sh.getShSubAddrFact();
        shSubPhone = sh.getShSubPhone();
        shSubFax = sh.getShSubFax();
        shSubEMail = sh.getShSubEMail();
        shSubWeb = sh.getShSubWeb();
        shSubCodeKpp = sh.getShSubCodeKpp();
        shSubOgrn = sh.getShSubOgrn();
        shSubAccountName = sh.getShSubAccountName();
        shSubAccountCode = sh.getShSubAccountCode();
        shSubForegnStaff = sh.getShSubForegnStaff();
        shSubManF = sh.getShSubManF();
        shSubManI = sh.getShSubManI();
        shSubManO = sh.getShSubManO();
        shSubManBase = sh.getShSubManBase();
        shSubManPost = sh.getShSubManPost();
        shSubManSex = sh.getShSubManSex();
        shSubBankName = sh.getShSubBankName();
        shSubBankAddr = sh.getShSubBankAddr();
        shSubBankBic = sh.getShSubBankBic();
        shSubBankAccount = sh.getShSubBankAccount();
        shSubBankInn = sh.getShSubBankInn();
        shSubBankKpp = sh.getShSubBankKpp();
        shSubRfsubjectcode = sh.getShSubRfsubjectcode();
        shSubTimezoneOffset = sh.getShSubTimezoneOffset();
        shSubTimezoneName = sh.getShSubTimezoneName();
        shSubSnils = sh.getShSubSnils();
        shSubOkato = sh.getShSubOkato();
        shPaSubNameF = sh.getShPaSubNameF();
        shPaSubNameI = sh.getShPaSubNameI();
        shPaSubNameO = sh.getShPaSubNameO();
        shPaSubSex = sh.getShPaSubSex();
        shPaSubBirthday = sh.getShPaSubBirthday();
        shPaSubBirthplace = sh.getShPaSubBirthplace();
        shPaSubDocumName = sh.getShPaSubDocumName();
        shPaSubDocumNumber = sh.getShPaSubDocumNumber();
        shPaSubDocumSeries = sh.getShPaSubDocumSeries();
        shPaSubDocumIssueAgency = sh.getShPaSubDocumIssueAgency();
        shPaSubDocumIssueDate = sh.getShPaSubDocumIssueDate();
        shPaSubPhone = sh.getShPaSubPhone();
        shPaSubFax = sh.getShPaSubFax();
        shPaSubEMail = sh.getShPaSubEMail();
        shPaDocName = sh.getShPaDocName();
        shPaDocNumber = sh.getShPaDocNumber();
        shPaDocDate = sh.getShPaDocDate();
        shPaDocDateB = sh.getShPaDocDateB();
        shPaDocDateE = sh.getShPaDocDateE();
        shPaBase = sh.getShPaBase();
        shPaPost = sh.getShPaPost();
        shPaIndMan = sh.getShPaIndMan() ? 1 : 0;
        shPaSnils = sh.getShPaSnils();
        bankUnid = sh.getBankUnid();
        dlaUnid = sh.getDlaUnid();
        okcmUnid = sh.getOkcmUnid();
        okfsUnid = sh.getOkfsUnid();
        oktmoUnid = sh.getOktmoUnid();
        opfUnid = sh.getOpfUnid();
        paUnid = sh.getPaUnid();
        subUnid = sh.getSubUnid();
        typesUnid = sh.getTypesUnid();
        subSubUnid = sh.getSubSubUnid();
        dlaPaUnid = sh.getDlaPaUnid();
        shSubRegEditDate = sh.getShSubRegEditDate();
        shSubRegEditDoc = sh.getShSubRegEditDoc();
        shSubRegEditNum = sh.getShSubRegEditNum();
        shSubRegEditOrgan = sh.getShSubRegEditOrgan();
        shSubMobilePhone = sh.getShSubMobilePhone();
        shSubAddrLegalFiasId = sh.getShSubAddrLegalFiasId();
        shSubAddrFactFiasId = sh.getShSubAddrFactFiasId();
    }

    public SubjectHistory(Long shUnid) {
        this.shUnid = shUnid;
    }

    public SubjectHistory(Long shUnid, Long subUnid) {
        this.shUnid = shUnid;
        this.subUnid = subUnid;
    }

    public Long getShUnid() {
        return shUnid;
    }

    public void setShUnid(Long shUnid) {
        this.shUnid = shUnid;
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

    public String getShSubName() {
        return shSubName;
    }

    public void setShSubName(String shSubName) {
        this.shSubName = shSubName;
    }

    public String getShSubSname() {
        return shSubSname;
    }

    public void setShSubSname(String shSubSname) {
        this.shSubSname = shSubSname;
    }

    public String getShSubNameF() {
        return shSubNameF;
    }

    public void setShSubNameF(String shSubNameF) {
        this.shSubNameF = shSubNameF;
    }

    public String getShSubNameO() {
        return shSubNameO;
    }

    public void setShSubNameO(String shSubNameO) {
        this.shSubNameO = shSubNameO;
    }

    public String getShSubNameI() {
        return shSubNameI;
    }

    public void setShSubNameI(String shSubNameI) {
        this.shSubNameI = shSubNameI;
    }

    public Integer getShSubSex() {
        return shSubSex;
    }

    public void setShSubSex(Integer shSubSex) {
        this.shSubSex = shSubSex;
    }

    public Date getShSubBirthday() {
        return shSubBirthday;
    }

    public void setShSubBirthday(Date shSubBirthday) {
        this.shSubBirthday = shSubBirthday;
    }

    public String getShSubBirthplace() {
        return shSubBirthplace;
    }

    public void setShSubBirthplace(String shSubBirthplace) {
        this.shSubBirthplace = shSubBirthplace;
    }

    public String getShSubRegOrgan() {
        return shSubRegOrgan;
    }

    public void setShSubRegOrgan(String shSubRegOrgan) {
        this.shSubRegOrgan = shSubRegOrgan;
    }

    public String getShSubRegNumber() {
        return shSubRegNumber;
    }

    public void setShSubRegNumber(String shSubRegNumber) {
        this.shSubRegNumber = shSubRegNumber;
    }

    public Date getShSubRegDate() {
        return shSubRegDate;
    }

    public void setShSubRegDate(Date shSubRegDate) {
        this.shSubRegDate = shSubRegDate;
    }

    public String getShSubDocumName() {
        return shSubDocumName;
    }

    public void setShSubDocumName(String shSubDocumName) {
        this.shSubDocumName = shSubDocumName;
    }

    public String getShSubDocumSeries() {
        return shSubDocumSeries;
    }

    public void setShSubDocumSeries(String shSubDocumSeries) {
        this.shSubDocumSeries = shSubDocumSeries;
    }

    public String getShSubDocumNumber() {
        return shSubDocumNumber;
    }

    public void setShSubDocumNumber(String shSubDocumNumber) {
        this.shSubDocumNumber = shSubDocumNumber;
    }

    public String getShSubDocumIssueAgency() {
        return shSubDocumIssueAgency;
    }

    public void setShSubDocumIssueAgency(String shSubDocumIssueAgency) {
        this.shSubDocumIssueAgency = shSubDocumIssueAgency;
    }

    public Date getShSubDocumIssueDate() {
        return shSubDocumIssueDate;
    }

    public void setShSubDocumIssueDate(Date shSubDocumIssueDate) {
        this.shSubDocumIssueDate = shSubDocumIssueDate;
    }

    public String getShSubOkpo() {
        return shSubOkpo;
    }

    public void setShSubOkpo(String shSubOkpo) {
        this.shSubOkpo = shSubOkpo;
    }

    public String getShSubInn() {
        return shSubInn;
    }

    public void setShSubInn(String shSubInn) {
        this.shSubInn = shSubInn;
    }

    public String getShSubAddrLegal() {
        return shSubAddrLegal;
    }

    public void setShSubAddrLegal(String shSubAddrLegal) {
        this.shSubAddrLegal = shSubAddrLegal;
    }

    public String getShSubAddrFact() {
        return shSubAddrFact;
    }

    public void setShSubAddrFact(String shSubAddrFact) {
        this.shSubAddrFact = shSubAddrFact;
    }

    public String getShSubPhone() {
        return shSubPhone;
    }

    public void setShSubPhone(String shSubPhone) {
        this.shSubPhone = shSubPhone;
    }

    public String getShSubFax() {
        return shSubFax;
    }

    public void setShSubFax(String shSubFax) {
        this.shSubFax = shSubFax;
    }

    public String getShSubEMail() {
        return shSubEMail;
    }

    public void setShSubEMail(String shSubEMail) {
        this.shSubEMail = shSubEMail;
    }

    public String getShSubWeb() {
        return shSubWeb;
    }

    public void setShSubWeb(String shSubWeb) {
        this.shSubWeb = shSubWeb;
    }

    public String getShSubCodeKpp() {
        return shSubCodeKpp;
    }

    public void setShSubCodeKpp(String shSubCodeKpp) {
        this.shSubCodeKpp = shSubCodeKpp;
    }

    public String getShSubOgrn() {
        return shSubOgrn;
    }

    public void setShSubOgrn(String shSubOgrn) {
        this.shSubOgrn = shSubOgrn;
    }

    public String getShSubRegisterNum() {
        return shSubRegisterNum;
    }

    public void setShSubRegisterNum(String shSubRegisterNum) {
        this.shSubRegisterNum = shSubRegisterNum;
    }

    public Date getShSubRegistrationTime() {
        return shSubRegistrationTime;
    }

    public void setShSubRegistrationTime(Date shSubRegistrationTime) {
        this.shSubRegistrationTime = shSubRegistrationTime;
    }

    public Integer getShSubIndRegistr() {
        return shSubIndRegistr;
    }

    public void setShSubIndRegistr(Integer shSubIndRegistr) {
        this.shSubIndRegistr = shSubIndRegistr;
    }

    public String getShSubAccountName() {
        return shSubAccountName;
    }

    public void setShSubAccountName(String shSubAccountName) {
        this.shSubAccountName = shSubAccountName;
    }

    public String getShSubAccountCode() {
        return shSubAccountCode;
    }

    public void setShSubAccountCode(String shSubAccountCode) {
        this.shSubAccountCode = shSubAccountCode;
    }

    public String getShSubForegnStaff() {
        return shSubForegnStaff;
    }

    public void setShSubForegnStaff(String shSubForegnStaff) {
        this.shSubForegnStaff = shSubForegnStaff;
    }

    public String getShSubManF() {
        return shSubManF;
    }

    public void setShSubManF(String shSubManF) {
        this.shSubManF = shSubManF;
    }

    public String getShSubManI() {
        return shSubManI;
    }

    public void setShSubManI(String shSubManI) {
        this.shSubManI = shSubManI;
    }

    public String getShSubManO() {
        return shSubManO;
    }

    public void setShSubManO(String shSubManO) {
        this.shSubManO = shSubManO;
    }

    public String getShSubManBase() {
        return shSubManBase;
    }

    public void setShSubManBase(String shSubManBase) {
        this.shSubManBase = shSubManBase;
    }

    public String getShSubManPost() {
        return shSubManPost;
    }

    public void setShSubManPost(String shSubManPost) {
        this.shSubManPost = shSubManPost;
    }

    public String getShSubBankName() {
        return shSubBankName;
    }

    public void setShSubBankName(String shSubBankName) {
        this.shSubBankName = shSubBankName;
    }

    public String getShSubBankAddr() {
        return shSubBankAddr;
    }

    public void setShSubBankAddr(String shSubBankAddr) {
        this.shSubBankAddr = shSubBankAddr;
    }

    public String getShSubBankBic() {
        return shSubBankBic;
    }

    public void setShSubBankBic(String shSubBankBic) {
        this.shSubBankBic = shSubBankBic;
    }

    public String getShSubBankAccount() {
        return shSubBankAccount;
    }

    public void setShSubBankAccount(String shSubBankAccount) {
        this.shSubBankAccount = shSubBankAccount;
    }

    public String getShSubBankInn() {
        return shSubBankInn;
    }

    public void setShSubBankInn(String shSubBankInn) {
        this.shSubBankInn = shSubBankInn;
    }

    public String getShSubBankKpp() {
        return shSubBankKpp;
    }

    public void setShSubBankKpp(String shSubBankKpp) {
        this.shSubBankKpp = shSubBankKpp;
    }

    public String getShSubRfsubjectcode() {
        return shSubRfsubjectcode;
    }

    public void setShSubRfsubjectcode(String shSubRfsubjectcode) {
        this.shSubRfsubjectcode = shSubRfsubjectcode;
    }

    public Integer getShSubTimezoneOffset() {
        return shSubTimezoneOffset;
    }

    public void setShSubTimezoneOffset(Integer shSubTimezoneOffset) {
        this.shSubTimezoneOffset = shSubTimezoneOffset;
    }

    public String getShSubTimezoneName() {
        return shSubTimezoneName;
    }

    public void setShSubTimezoneName(String shSubTimezoneName) {
        this.shSubTimezoneName = shSubTimezoneName;
    }

    public String getShSubSnils() {
        return shSubSnils;
    }

    public void setShSubSnils(String shSubSnils) {
        this.shSubSnils = shSubSnils;
    }

    public String getShSubOkato() {
        return shSubOkato;
    }

    public void setShSubOkato(String shSubOkato) {
        this.shSubOkato = shSubOkato;
    }

    public String getShPaSubNameF() {
        return shPaSubNameF;
    }

    public void setShPaSubNameF(String shPaSubNameF) {
        this.shPaSubNameF = shPaSubNameF;
    }

    public String getShPaSubNameI() {
        return shPaSubNameI;
    }

    public void setShPaSubNameI(String shPaSubNameI) {
        this.shPaSubNameI = shPaSubNameI;
    }

    public String getShPaSubNameO() {
        return shPaSubNameO;
    }

    public void setShPaSubNameO(String shPaSubNameO) {
        this.shPaSubNameO = shPaSubNameO;
    }

    public Integer getShPaSubSex() {
        return shPaSubSex;
    }

    public void setShPaSubSex(Integer shPaSubSex) {
        this.shPaSubSex = shPaSubSex;
    }

    public Date getShPaSubBirthday() {
        return shPaSubBirthday;
    }

    public void setShPaSubBirthday(Date shPaSubBirthday) {
        this.shPaSubBirthday = shPaSubBirthday;
    }

    public String getShPaSubBirthplace() {
        return shPaSubBirthplace;
    }

    public void setShPaSubBirthplace(String shPaSubBirthplace) {
        this.shPaSubBirthplace = shPaSubBirthplace;
    }

    public String getShPaSubDocumName() {
        return shPaSubDocumName;
    }

    public void setShPaSubDocumName(String shPaSubDocumName) {
        this.shPaSubDocumName = shPaSubDocumName;
    }

    public String getShPaSubDocumNumber() {
        return shPaSubDocumNumber;
    }

    public void setShPaSubDocumNumber(String shPaSubDocumNumber) {
        this.shPaSubDocumNumber = shPaSubDocumNumber;
    }

    public String getShPaSubDocumSeries() {
        return shPaSubDocumSeries;
    }

    public void setShPaSubDocumSeries(String shPaSubDocumSeries) {
        this.shPaSubDocumSeries = shPaSubDocumSeries;
    }

    public String getShPaSubDocumIssueAgency() {
        return shPaSubDocumIssueAgency;
    }

    public void setShPaSubDocumIssueAgency(String shPaSubDocumIssueAgency) {
        this.shPaSubDocumIssueAgency = shPaSubDocumIssueAgency;
    }

    public Date getShPaSubDocumIssueDate() {
        return shPaSubDocumIssueDate;
    }

    public void setShPaSubDocumIssueDate(Date shPaSubDocumIssueDate) {
        this.shPaSubDocumIssueDate = shPaSubDocumIssueDate;
    }

    public String getShPaSubPhone() {
        return shPaSubPhone;
    }

    public void setShPaSubPhone(String shPaSubPhone) {
        this.shPaSubPhone = shPaSubPhone;
    }

    public String getShPaSubFax() {
        return shPaSubFax;
    }

    public void setShPaSubFax(String shPaSubFax) {
        this.shPaSubFax = shPaSubFax;
    }

    public String getShPaSubEMail() {
        return shPaSubEMail;
    }

    public void setShPaSubEMail(String shPaSubEMail) {
        this.shPaSubEMail = shPaSubEMail;
    }

    public String getShPaDocName() {
        return shPaDocName;
    }

    public void setShPaDocName(String shPaDocName) {
        this.shPaDocName = shPaDocName;
    }

    public String getShPaDocNumber() {
        return shPaDocNumber;
    }

    public void setShPaDocNumber(String shPaDocNumber) {
        this.shPaDocNumber = shPaDocNumber;
    }

    public Date getShPaDocDate() {
        return shPaDocDate;
    }

    public void setShPaDocDate(Date shPaDocDate) {
        this.shPaDocDate = shPaDocDate;
    }

    public Date getShPaDocDateB() {
        return shPaDocDateB;
    }

    public void setShPaDocDateB(Date shPaDocDateB) {
        this.shPaDocDateB = shPaDocDateB;
    }

    public Date getShPaDocDateE() {
        return shPaDocDateE;
    }

    public void setShPaDocDateE(Date shPaDocDateE) {
        this.shPaDocDateE = shPaDocDateE;
    }

    public Integer getShPaIndSendNotification() {
        return shPaIndSendNotification;
    }

    public void setShPaIndSendNotification(Integer shPaIndSendNotification) {
        this.shPaIndSendNotification = shPaIndSendNotification;
    }

    public boolean getShPaIndMan() {
        return shPaIndMan != null && shPaIndMan == 1;
    }

    public void setShPaIndMan(boolean checked) {
        this.shPaIndMan = checked ? 1 : 0;
    }

    public String getShPaSnils() {
        return shPaSnils;
    }

    public void setShPaSnils(String shPaSnils) {
        this.shPaSnils = shPaSnils;
    }

    public Long getBankUnid() {
        return bankUnid;
    }

    public void setBankUnid(Long bankUnid) {
        this.bankUnid = bankUnid;
    }

    public Long getDlaUnid() {
        return dlaUnid;
    }

    public void setDlaUnid(Long dlaUnid) {
        this.dlaUnid = dlaUnid;
    }

    public Long getOkcmUnid() {
        return okcmUnid;
    }

    public void setOkcmUnid(Long okcmUnid) {
        this.okcmUnid = okcmUnid;
    }

    public Long getOkfsUnid() {
        return okfsUnid;
    }

    public void setOkfsUnid(Long okfsUnid) {
        this.okfsUnid = okfsUnid;
    }

    public Long getOktmoUnid() {
        return oktmoUnid;
    }

    public void setOktmoUnid(Long oktmoUnid) {
        this.oktmoUnid = oktmoUnid;
    }

    public Long getOpfUnid() {
        return opfUnid;
    }

    public void setOpfUnid(Long opfUnid) {
        this.opfUnid = opfUnid;
    }

    public Long getPaUnid() {
        return paUnid;
    }

    public void setPaUnid(Long paUnid) {
        this.paUnid = paUnid;
    }

    public Long getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(Long subUnid) {
        this.subUnid = subUnid;
    }

    public Long getTypesUnid() {
        return typesUnid;
    }

    public void setTypesUnid(Long typesUnid) {
        this.typesUnid = typesUnid;
    }

    public Long getSubSubUnid() {
        return subSubUnid;
    }

    public void setSubSubUnid(Long subSubUnid) {
        this.subSubUnid = subSubUnid;
    }

    public Integer getShSubManSex() {
        return shSubManSex;
    }

    public void setShSubManSex(Integer shSubManSex) {
        this.shSubManSex = shSubManSex;
    }

    public String getShPaBase() {
        return shPaBase;
    }

    public void setShPaBase(String shPaBase) {
        this.shPaBase = shPaBase;
    }

    public String getShPaPost() {
        return shPaPost;
    }

    public void setShPaPost(String shPaPost) {
        this.shPaPost = shPaPost;
    }

    public Long getDlaPaUnid() {
        return dlaPaUnid;
    }

    public void setDlaPaUnid(Long dlaPaUnid) {
        this.dlaPaUnid = dlaPaUnid;
    }

    public String getShSubRegEditDoc() {
        return shSubRegEditDoc;
    }

    public void setShSubRegEditDoc(String shSubRegEditDoc) {
        this.shSubRegEditDoc = shSubRegEditDoc;
    }

    public String getShSubRegEditOrgan() {
        return shSubRegEditOrgan;
    }

    public void setShSubRegEditOrgan(String shSubRegEditOrgan) {
        this.shSubRegEditOrgan = shSubRegEditOrgan;
    }

    public String getShSubRegEditNum() {
        return shSubRegEditNum;
    }

    public void setShSubRegEditNum(String shSubRegEditNum) {
        this.shSubRegEditNum = shSubRegEditNum;
    }

    public Date getShSubRegEditDate() {
        return shSubRegEditDate;
    }

    public void setShSubRegEditDate(Date shSubRegEditDate) {
        this.shSubRegEditDate = shSubRegEditDate;
    }

    public Long getSubShUnid() {
        return subShUnid;
    }

    public void setSubShUnid(Long subShUnid) {
        this.subShUnid = subShUnid;
    }

    public String getShSubMobilePhone() {
        return shSubMobilePhone;
    }

    public void setShSubMobilePhone(String shSubMobilePhone) {
        this.shSubMobilePhone = shSubMobilePhone;
    }

    public String getShSubAddrLegalFiasId() {
        return shSubAddrLegalFiasId;
    }

    public void setShSubAddrLegalFiasId(String shSubAddrLegalFiasId) {
        this.shSubAddrLegalFiasId = shSubAddrLegalFiasId;
    }

    public String getShSubAddrFactFiasId() {
        return shSubAddrFactFiasId;
    }

    public void setShSubAddrFactFiasId(String shSubAddrFactFiasId) {
        this.shSubAddrFactFiasId = shSubAddrFactFiasId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shUnid != null ? shUnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubjectHistory)) {
            return false;
        }
        SubjectHistory other = (SubjectHistory) object;
        if ((this.shUnid == null && other.shUnid != null) || (this.shUnid != null && !this.shUnid.equals(other.shUnid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.model.SubjectHistory[ shUnid=" + shUnid + " ]";
    }


}
