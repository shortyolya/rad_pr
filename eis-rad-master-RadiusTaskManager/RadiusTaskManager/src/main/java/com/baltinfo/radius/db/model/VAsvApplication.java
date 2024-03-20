package com.baltinfo.radius.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = "VAsvApplication.findAll", query = "SELECT a FROM VAsvApplication a"),
        @NamedQuery(name = "VAsvApplication.findByLotUnid", query = "SELECT a FROM VAsvApplication a where a.lotUnid = :lotUnid")
})
@Entity
@Table(name = "V_ASV_APPLICATION", catalog = "", schema = "WEB")
public class VAsvApplication implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "APPLICAT_UNID")
    private Long applicatUnid;
    @Column(name = "LOT_UNID")
    private Long lotUnid;
    @Column(name = "APPLICAT_NUMBER")
    private String applicatNumber;
    @Column(name = "APPLICAT_CARD_NUMBER")
    private String applicatCardNumber;
    @Column(name = "APPLICAT_IND_WINNER")
    private Integer applicatIndWinner;
    @Column(name = "APPLICAT_APP_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicatAppTime;
    @Column(name = "APPLICAT_AMOUNT")
    private BigDecimal applicatAmount;
    @Column(name = "APPLICAT_IND_PAY_DEP")
    private Integer applicatIndPayDep;
    @Column(name = "RED_SCHED_UNID")
    private Long redSchedUnid;
    @Column(name = "sh_unid")
    private Long shUnid;
    @Column(name = "sub_unid")
    private Long subUnid;
    @Column(name = "sh_sub_name")
    private String shSubName;
    @Column(name = "sh_sub_inn")
    private String shSubInn;
    @Column(name = "sh_sub_ogrn")
    private String shSubOgrn;
    @Column(name = "sh_sub_reg_organ")
    private String shSubRegOrgan;
    @Column(name = "sh_sub_reg_date")
    private Date shSubRegDate;
    @Column(name = "sh_sub_code_kpp")
    private String shSubCodeKpp;
    @Column(name = "types_unid")
    private Long typesUnid;
    @Column(name = "sh_sub_docum_name")
    private String shSubDocumName;
    @Column(name = "sh_sub_docum_series")
    private String shSubDocumSeries;
    @Column(name = "sh_sub_docum_number")
    private String shSubDocumNumber;
    @Column(name = "sh_sub_docum_issue_agency")
    private String shSubDocumIssueAgency;
    @Column(name = "sh_sub_docum_issue_date")
    private Date shSubDocumIssueDate;
    @Column(name = "sh_sub_birthday")
    private Date shSubBirthday;
    @Column(name = "sh_sub_man_f")
    private String shSubManF;
    @Column(name = "sh_sub_man_i")
    private String shSubManI;
    @Column(name = "sh_sub_man_o")
    private String shSubManO;
    @Column(name = "sh_sub_phone")
    private String shSubPhone;
    @Column(name = "sh_sub_e_mail")
    private String shSubEMail;
    @Column(name = "sh_sub_addr_legal")
    private String shSubAddrLegal;
    @Column(name = "sh_sub_addr_fact")
    private String shSubAddrFact;
    @Column(name = "sh_sub_account_name")
    private String shSubAccountName;
    @Column(name = "sh_sub_account_code")
    private String shSubAccountCode;
    @Column(name = "sh_sub_bank_name")
    private String shSubBankName;
    @Column(name = "sh_sub_bank_bic")
    private String shSubBankBic;
    @Column(name = "sh_sub_bank_account")
    private String shSubBankAccount;
    @Column(name = "sh_sub_bank_kpp")
    private String shSubBankKpp;
    @Column(name = "sh_sub_bank_addr")
    private String shSubBankAddr;

    @Column(name = "sh_pa_sub_name_f")
    private String shPaSubNameF;
    @Column(name = "sh_pa_sub_name_i")
    private String shPaSubNameI;
    @Column(name = "sh_pa_sub_name_o")
    private String ShPaSubNameO;
    @Column(name = "sh_pa_sub_docum_name")
    private String shPaSubDocumName;
    @Column(name = "sh_pa_sub_docum_number")
    private String shPaSubDocumNumber;
    @Column(name = "sh_pa_sub_docum_series")
    private String shPaSubDocumSeries;
    @Column(name = "sh_pa_sub_docum_issue_agency")
    private String shPaSubDocumIssueAgency;
    @Column(name = "sh_pa_sub_docum_issue_date")
    private Date shPaSubDocumIssueDate;

    @Column(name = "sh_pa_doc_name")
    private String shPaDocName;
    @Column(name = "sh_pa_doc_number")
    private String shPaDocNumber;
    @Column(name = "sh_pa_doc_date")
    private Date shPaDocDate;

    @Column(name = "red_sched_period_number")
    private Integer redSchedPeriodNumber;

    @Column(name = "red_sched_dep_sum")
    private BigDecimal redSchedDepSum;

    public Long getApplicatUnid() {
        return applicatUnid;
    }

    public void setApplicatUnid(Long applicatUnid) {
        this.applicatUnid = applicatUnid;
    }

    public Long getLotUnid() {
        return lotUnid;
    }

    public void setLotUnid(Long lotUnid) {
        this.lotUnid = lotUnid;
    }

    public String getApplicatNumber() {
        return applicatNumber;
    }

    public void setApplicatNumber(String applicatNumber) {
        this.applicatNumber = applicatNumber;
    }

    public String getApplicatCardNumber() {
        return applicatCardNumber;
    }

    public void setApplicatCardNumber(String applicatCardNumber) {
        this.applicatCardNumber = applicatCardNumber;
    }

    public Integer getApplicatIndWinner() {
        return applicatIndWinner;
    }

    public void setApplicatIndWinner(Integer applicatIndWinner) {
        this.applicatIndWinner = applicatIndWinner;
    }

    public Date getApplicatAppTime() {
        return applicatAppTime;
    }

    public void setApplicatAppTime(Date applicatAppTime) {
        this.applicatAppTime = applicatAppTime;
    }

    public BigDecimal getApplicatAmount() {
        return applicatAmount;
    }

    public void setApplicatAmount(BigDecimal applicatAmount) {
        this.applicatAmount = applicatAmount;
    }

    public Integer getApplicatIndPayDep() {
        return applicatIndPayDep;
    }

    public void setApplicatIndPayDep(Integer applicatIndPayDep) {
        this.applicatIndPayDep = applicatIndPayDep;
    }

    public Long getRedSchedUnid() {
        return redSchedUnid;
    }

    public void setRedSchedUnid(Long redSchedUnid) {
        this.redSchedUnid = redSchedUnid;
    }

    public Long getShUnid() {
        return shUnid;
    }

    public void setShUnid(Long shUnid) {
        this.shUnid = shUnid;
    }

    public Long getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(Long subUnid) {
        this.subUnid = subUnid;
    }

    public String getShSubName() {
        return shSubName;
    }

    public void setShSubName(String shSubName) {
        this.shSubName = shSubName;
    }

    public String getShSubInn() {
        return shSubInn;
    }

    public void setShSubInn(String shSubInn) {
        this.shSubInn = shSubInn;
    }

    public String getShSubOgrn() {
        return shSubOgrn;
    }

    public void setShSubOgrn(String shSubOgrn) {
        this.shSubOgrn = shSubOgrn;
    }

    public String getShSubRegOrgan() {
        return shSubRegOrgan;
    }

    public void setShSubRegOrgan(String shSubRegOrgan) {
        this.shSubRegOrgan = shSubRegOrgan;
    }

    public Date getShSubRegDate() {
        return shSubRegDate;
    }

    public void setShSubRegDate(Date shSubRegDate) {
        this.shSubRegDate = shSubRegDate;
    }

    public String getShSubCodeKpp() {
        return shSubCodeKpp;
    }

    public void setShSubCodeKpp(String shSubCodeKpp) {
        this.shSubCodeKpp = shSubCodeKpp;
    }

    public Long getTypesUnid() {
        return typesUnid;
    }

    public void setTypesUnid(Long typesUnid) {
        this.typesUnid = typesUnid;
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

    public Date getShSubBirthday() {
        return shSubBirthday;
    }

    public void setShSubBirthday(Date shSubBirthday) {
        this.shSubBirthday = shSubBirthday;
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

    public String getShSubPhone() {
        return shSubPhone;
    }

    public void setShSubPhone(String shSubPhone) {
        this.shSubPhone = shSubPhone;
    }

    public String getShSubEMail() {
        return shSubEMail;
    }

    public void setShSubEMail(String shSubEMail) {
        this.shSubEMail = shSubEMail;
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

    public String getShSubBankName() {
        return shSubBankName;
    }

    public void setShSubBankName(String shSubBankName) {
        this.shSubBankName = shSubBankName;
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

    public String getShSubBankKpp() {
        return shSubBankKpp;
    }

    public void setShSubBankKpp(String shSubBankKpp) {
        this.shSubBankKpp = shSubBankKpp;
    }

    public String getShSubBankAddr() {
        return shSubBankAddr;
    }

    public void setShSubBankAddr(String shSubBankAddr) {
        this.shSubBankAddr = shSubBankAddr;
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
        return ShPaSubNameO;
    }

    public void setShPaSubNameO(String shPaSubNameO) {
        ShPaSubNameO = shPaSubNameO;
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

    public Integer getRedSchedPeriodNumber() {
        return redSchedPeriodNumber;
    }

    public void setRedSchedPeriodNumber(Integer redSchedPeriodNumber) {
        this.redSchedPeriodNumber = redSchedPeriodNumber;
    }

    public BigDecimal getRedSchedDepSum() {
        return redSchedDepSum;
    }

    public void setRedSchedDepSum(BigDecimal redSchedDepSum) {
        this.redSchedDepSum = redSchedDepSum;
    }
}
