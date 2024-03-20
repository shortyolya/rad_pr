package com.baltinfo.radius.db.model.bankruptcy;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author sas
 */
@Entity
@Table(name = "WEB_BIDDER_ALL", catalog = "", schema = "WEB")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "VWebBidderAll.findAll", query = "SELECT w FROM VWebBidderAll w")})
public class VWebBidderAll implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @Column(name = "PERSON_PA_UNID")
    private long personPaUnid;
    @Column(name = "PERSON_UNID")
    private Long personUnid;
    @Column(name = "PARTY_UNID")
    private Long partyUnid;
    @Column(name = "SUB_UNID")
    private Long subUnid;
    @Column(name = "WB_UNID")
    private Long wbUnid;
    @Column(name = "WB_IND_OPERATOR")
    private Short wbIndOperator;
    @Column(name = "TWB_NAME")
    private String twbName;
    @Column(name = "TWB_UNID")
    private Long twbUnid;
    @Column(name = "WB_KIND")
    private BigInteger wbKind;
    @Column(name = "PERSON_NAME_F")
    private String personNameF;
    @Column(name = "PERSON_NAME_I")
    private String personNameI;
    @Column(name = "PERSON_NAME_O")
    private String personNameO;
    @Column(name = "PERSON_BIRTHDAY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date personBirthday;
    @Column(name = "PERSON_E_MAIL")
    private String personEMail;
    @Column(name = "PERSON_TEL")
    private String personTel;
    @Column(name = "PERSON_BIRTHPLACE")
    private String personBirthplace;
    @Column(name = "PERSON_ADDRESS")
    private String personAddress;
    @Column(name = "PERSON_WORK_ADRES")
    private String personWorkAdres;
    @Column(name = "PERSON_FOREGN_STAFF")
    private String personForegnStaff;
    @Column(name = "PERSON_PA_F")
    private String personPaF;
    @Column(name = "PERSON_PA_I")
    private String personPaI;
    @Column(name = "PERSON_PA_O")
    private String personPaO;
    @Column(name = "PERSON_PA_BIRTHDAY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date personPaBirthday;
    @Column(name = "PERSON_PA_E_MAIL")
    private String personPaEMail;
    @Column(name = "PERSON_PA_TEL")
    private String personPaTel;
    @Column(name = "PERSON_PA_BIRTHPLACE")
    private String personPaBirthplace;
    @Column(name = "PERSON_PA_ADDRESS")
    private String personPaAddress;
    @Column(name = "PERSON_PA_WORK_ADRES")
    private String personPaWorkAdres;
    @Column(name = "PERSON_PA_FOREGN_STAFF")
    private String personPaForegnStaff;
    @Column(name = "PERSON_PA_SNILS")
    private String personPaSnils;
    @Column(name = "IND_PERSON_IS_PA")
    private String indPersonIsPa;
    @Column(name = "SUB_NAME")
    private String subName;
    @Column(name = "SUB_INN")
    private String subInn;
    @Column(name = "SUB_CODE_KPP")
    private String subCodeKpp;
    @Column(name = "SUB_SNAME")
    private String subSname;
    @Column(name = "SUB_OGRN")
    private String subOgrn;
    @Column(name = "SUB_PHONE")
    private String subPhone;
    @Column(name = "SUB_FAX")
    private String subFax;
    @Column(name = "SUB_ADDR_LEGAL")
    private String subAddrLegal;
    @Column(name = "SUB_ADDR_FACT")
    private String subAddrFact;
    @Column(name = "SUB_REG_NUMBER")
    private String subRegNumber;
    @Column(name = "SUB_REG_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date subRegDate;
    @Column(name = "SUB_REG_ORGAN")
    private String subRegOrgan;
    @Column(name = "SUB_ADDR_UNID")
    private Long subAddrUnid;
    @Column(name = "SUB_ADD_ADDR_UNID")
    private Long subAddAddrUnid;
    @Column(name = "SUB_REG_DOC")
    private String subRegDoc;
    @Column(name = "ACCOUNT_UNID")
    private BigInteger accountUnid;
    @Column(name = "BANK_UNID")
    private BigInteger bankUnid;
    @Column(name = "BANK_NAME")
    private String bankName;
    @Column(name = "BANK_BIC")
    private String bankBic;
    @Column(name = "BANK_C_ACCOUNT")
    private String bankCAccount;
    @Column(name = "BANK_ADDRESS")
    private String bankAddress;
    @Column(name = "ACCOUNT_CODE")
    private String accountCode;
    @Column(name = "ACCOUNT_NAME")
    private String accountName;
    @Column(name = "BANK_INN")
    private String bankInn;
    @Column(name = "BANK_CODE_KPP")
    private String bankCodeKpp;
    @Column(name = "WB_REGISTER_NUM")
    private String wbRegisterNum;
    @Column(name = "PA_UNID")
    private Long paUnid;
    @Column(name = "FOUND_B")
    private String foundB;
    @Column(name = "PA_SYS_NAME")
    private String paSysName;
    @Column(name = "PA_SYS_PSW")
    private String paSysPsw;
    @Column(name = "PA_IND_TRUSTEE")
    private Short paIndTrustee;
    @Column(name = "PA_KEY_WORD")
    private String paKeyWord;
    @Column(name = "PA_REGISTRATION_IP")
    private String paRegistrationIp;
    @Column(name = "PA_REGISTRATION_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paRegistrationTime;
    @Column(name = "PA_MAIL_COPY")
    private Short paMailCopy;
    @Column(name = "PA_IND_SHOW_WELLCOME")
    private BigInteger paIndShowWellcome;
    @Column(name = "PA_IND_BLOCK")
    private BigInteger paIndBlock;
    @Column(name = "PA_DS_THUMBPRINT")
    private String paDsThumbprint;
    @Column(name = "PA_DS_CODE")
    private String paDsCode;
    @Column(name = "PA_DS_DATE_B")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paDsDateB;
    @Column(name = "PA_DS_DATE_E")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paDsDateE;
    @Column(name = "WUB_OPERATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date wubOperationDate;
    @Column(name = "WUB_ITS_USER_ID")
    private Long wubItsUserId;
    @Column(name = "WUB_REASON")
    private String wubReason;
    @Basic(optional = false)
    @Column(name = "TYPE_STATE_UNID")
    private long typeStateUnid;
    @Column(name = "DLA_UNID")
    private Long dlaUnid;
    @Column(name = "DLA_NAME")
    private String dlaName;
    @Column(name = "STAFF_SUB_PERSON_UNID")
    private Long staffSubPersonUnid;
    @Column(name = "STAFF_SUB_PERSON_NAME_F")
    private String staffSubPersonNameF;
    @Column(name = "STAFF_SUB_PERSON_NAME_I")
    private String staffSubPersonNameI;
    @Column(name = "STAFF_SUB_PERSON_NAME_O")
    private String staffSubPersonNameO;
    @Column(name = "STAFF_SUB_PERSON_E_MAIL")
    private String staffSubPersonEMail;

    @Column(name = "STAFF_SUB_PERSON_TEL")
    private String staffSubPersonTel;

    @Column(name = "SSTAFF_FOUNDATION")
    private String sstaffFoundation;

    @Column(name = "PARTY_NAME")
    private String partyName;

    @Column(name = "PARTY_ADDR_FACT")
    private String partyAddrFact;

    @Column(name = "PARTY_ADDR_LEGAL")
    private String partyAddrLegal;

    @Column(name = "PARTY_EMAIL")
    private String partyEmail;

    @Column(name = "PARTY_PHONE")
    private String partyPhone;

    @Column(name = "PARTY_INN")
    private String partyInn;
    @Column(name = "OPF_UNID")
    private Long opfUnid;
    @Column(name = "OPF_CODE")
    private Integer opfCode;

    @Column(name = "OPF_NAME")
    private String opfName;
    @Column(name = "OPF_SNAME")
    private String opfSname;
    @Column(name = "SRA_UNID")
    private Long sraUnid;
    @Column(name = "SRA_NAME")
    private String sraName;
    @Column(name = "WB_IND_COMMISSIONER")
    private Short wbIndCommissioner;
    @Column(name = "SUB_IND_ASV")
    private BigInteger subIndAsv;
    @Column(name = "ORGANIZER_UNID")
    private BigInteger organizerUnid;

    public VWebBidderAll() {
    }

    public long getPersonPaUnid() {
        return personPaUnid;
    }

    public void setPersonPaUnid(long personPaUnid) {
        this.personPaUnid = personPaUnid;
    }

    public Long getPersonUnid() {
        return personUnid;
    }

    public void setPersonUnid(Long personUnid) {
        this.personUnid = personUnid;
    }

    public Long getPartyUnid() {
        return partyUnid;
    }

    public void setPartyUnid(Long partyUnid) {
        this.partyUnid = partyUnid;
    }

    public Long getSubUnid() {
        return subUnid;
    }

    public void setSubUnid(Long subUnid) {
        this.subUnid = subUnid;
    }

    public Long getWbUnid() {
        return wbUnid;
    }

    public void setWbUnid(Long wbUnid) {
        this.wbUnid = wbUnid;
    }

    public Short getWbIndOperator() {
        return wbIndOperator;
    }

    public void setWbIndOperator(Short wbIndOperator) {
        this.wbIndOperator = wbIndOperator;
    }

    public String getTwbName() {
        return twbName;
    }

    public void setTwbName(String twbName) {
        this.twbName = twbName;
    }

    public Long getTwbUnid() {
        return twbUnid;
    }

    public void setTwbUnid(Long twbUnid) {
        this.twbUnid = twbUnid;
    }

    public BigInteger getWbKind() {
        return wbKind;
    }

    public void setWbKind(BigInteger wbKind) {
        this.wbKind = wbKind;
    }

    public String getPersonNameF() {
        return personNameF;
    }

    public void setPersonNameF(String personNameF) {
        this.personNameF = personNameF;
    }

    public String getPersonNameI() {
        return personNameI;
    }

    public void setPersonNameI(String personNameI) {
        this.personNameI = personNameI;
    }

    public String getPersonNameO() {
        return personNameO;
    }

    public void setPersonNameO(String personNameO) {
        this.personNameO = personNameO;
    }

    public Date getPersonBirthday() {
        return personBirthday;
    }

    public void setPersonBirthday(Date personBirthday) {
        this.personBirthday = personBirthday;
    }

    public String getPersonEMail() {
        return personEMail;
    }

    public void setPersonEMail(String personEMail) {
        this.personEMail = personEMail;
    }

    public String getPersonTel() {
        return personTel;
    }

    public void setPersonTel(String personTel) {
        this.personTel = personTel;
    }

    public String getPersonBirthplace() {
        return personBirthplace;
    }

    public void setPersonBirthplace(String personBirthplace) {
        this.personBirthplace = personBirthplace;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }

    public String getPersonWorkAdres() {
        return personWorkAdres;
    }

    public void setPersonWorkAdres(String personWorkAdres) {
        this.personWorkAdres = personWorkAdres;
    }

    public String getPersonForegnStaff() {
        return personForegnStaff;
    }

    public void setPersonForegnStaff(String personForegnStaff) {
        this.personForegnStaff = personForegnStaff;
    }

    public String getPersonPaF() {
        return personPaF;
    }

    public void setPersonPaF(String personPaF) {
        this.personPaF = personPaF;
    }

    public String getPersonPaI() {
        return personPaI;
    }

    public void setPersonPaI(String personPaI) {
        this.personPaI = personPaI;
    }

    public String getPersonPaO() {
        return personPaO;
    }

    public void setPersonPaO(String personPaO) {
        this.personPaO = personPaO;
    }

    public Date getPersonPaBirthday() {
        return personPaBirthday;
    }

    public void setPersonPaBirthday(Date personPaBirthday) {
        this.personPaBirthday = personPaBirthday;
    }

    public String getPersonPaEMail() {
        return personPaEMail;
    }

    public void setPersonPaEMail(String personPaEMail) {
        this.personPaEMail = personPaEMail;
    }

    public String getPersonPaTel() {
        return personPaTel;
    }

    public void setPersonPaTel(String personPaTel) {
        this.personPaTel = personPaTel;
    }

    public String getPersonPaBirthplace() {
        return personPaBirthplace;
    }

    public void setPersonPaBirthplace(String personPaBirthplace) {
        this.personPaBirthplace = personPaBirthplace;
    }

    public String getPersonPaAddress() {
        return personPaAddress;
    }

    public void setPersonPaAddress(String personPaAddress) {
        this.personPaAddress = personPaAddress;
    }

    public String getPersonPaWorkAdres() {
        return personPaWorkAdres;
    }

    public void setPersonPaWorkAdres(String personPaWorkAdres) {
        this.personPaWorkAdres = personPaWorkAdres;
    }

    public String getPersonPaForegnStaff() {
        return personPaForegnStaff;
    }

    public void setPersonPaForegnStaff(String personPaForegnStaff) {
        this.personPaForegnStaff = personPaForegnStaff;
    }

    public String getPersonPaSnils() {
        return personPaSnils;
    }

    public void setPersonPaSnils(String personPaSnils) {
        this.personPaSnils = personPaSnils;
    }

    public String getIndPersonIsPa() {
        return indPersonIsPa;
    }

    public void setIndPersonIsPa(String indPersonIsPa) {
        this.indPersonIsPa = indPersonIsPa;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubInn() {
        return subInn;
    }

    public void setSubInn(String subInn) {
        this.subInn = subInn;
    }

    public String getSubCodeKpp() {
        return subCodeKpp;
    }

    public void setSubCodeKpp(String subCodeKpp) {
        this.subCodeKpp = subCodeKpp;
    }

    public String getSubSname() {
        return subSname;
    }

    public void setSubSname(String subSname) {
        this.subSname = subSname;
    }

    public String getSubOgrn() {
        return subOgrn;
    }

    public void setSubOgrn(String subOgrn) {
        this.subOgrn = subOgrn;
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

    public String getSubRegOrgan() {
        return subRegOrgan;
    }

    public void setSubRegOrgan(String subRegOrgan) {
        this.subRegOrgan = subRegOrgan;
    }

    public Long getSubAddrUnid() {
        return subAddrUnid;
    }

    public void setSubAddrUnid(Long subAddrUnid) {
        this.subAddrUnid = subAddrUnid;
    }

    public Long getSubAddAddrUnid() {
        return subAddAddrUnid;
    }

    public void setSubAddAddrUnid(Long subAddAddrUnid) {
        this.subAddAddrUnid = subAddAddrUnid;
    }

    public String getSubRegDoc() {
        return subRegDoc;
    }

    public void setSubRegDoc(String subRegDoc) {
        this.subRegDoc = subRegDoc;
    }

    public BigInteger getAccountUnid() {
        return accountUnid;
    }

    public void setAccountUnid(BigInteger accountUnid) {
        this.accountUnid = accountUnid;
    }

    public BigInteger getBankUnid() {
        return bankUnid;
    }

    public void setBankUnid(BigInteger bankUnid) {
        this.bankUnid = bankUnid;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBic() {
        return bankBic;
    }

    public void setBankBic(String bankBic) {
        this.bankBic = bankBic;
    }

    public String getBankCAccount() {
        return bankCAccount;
    }

    public void setBankCAccount(String bankCAccount) {
        this.bankCAccount = bankCAccount;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankInn() {
        return bankInn;
    }

    public void setBankInn(String bankInn) {
        this.bankInn = bankInn;
    }

    public String getBankCodeKpp() {
        return bankCodeKpp;
    }

    public void setBankCodeKpp(String bankCodeKpp) {
        this.bankCodeKpp = bankCodeKpp;
    }

    public String getWbRegisterNum() {
        return wbRegisterNum;
    }

    public void setWbRegisterNum(String wbRegisterNum) {
        this.wbRegisterNum = wbRegisterNum;
    }

    public Long getPaUnid() {
        return paUnid;
    }

    public void setPaUnid(Long paUnid) {
        this.paUnid = paUnid;
    }

    public String getFoundB() {
        return foundB;
    }

    public void setFoundB(String foundB) {
        this.foundB = foundB;
    }

    public String getPaSysName() {
        return paSysName;
    }

    public void setPaSysName(String paSysName) {
        this.paSysName = paSysName;
    }

    public String getPaSysPsw() {
        return paSysPsw;
    }

    public void setPaSysPsw(String paSysPsw) {
        this.paSysPsw = paSysPsw;
    }

    public Short getPaIndTrustee() {
        return paIndTrustee;
    }

    public void setPaIndTrustee(Short paIndTrustee) {
        this.paIndTrustee = paIndTrustee;
    }

    public String getPaKeyWord() {
        return paKeyWord;
    }

    public void setPaKeyWord(String paKeyWord) {
        this.paKeyWord = paKeyWord;
    }

    public String getPaRegistrationIp() {
        return paRegistrationIp;
    }

    public void setPaRegistrationIp(String paRegistrationIp) {
        this.paRegistrationIp = paRegistrationIp;
    }

    public Date getPaRegistrationTime() {
        return paRegistrationTime;
    }

    public void setPaRegistrationTime(Date paRegistrationTime) {
        this.paRegistrationTime = paRegistrationTime;
    }

    public Short getPaMailCopy() {
        return paMailCopy;
    }

    public void setPaMailCopy(Short paMailCopy) {
        this.paMailCopy = paMailCopy;
    }

    public BigInteger getPaIndShowWellcome() {
        return paIndShowWellcome;
    }

    public void setPaIndShowWellcome(BigInteger paIndShowWellcome) {
        this.paIndShowWellcome = paIndShowWellcome;
    }

    public BigInteger getPaIndBlock() {
        return paIndBlock;
    }

    public void setPaIndBlock(BigInteger paIndBlock) {
        this.paIndBlock = paIndBlock;
    }

    public String getPaDsThumbprint() {
        return paDsThumbprint;
    }

    public void setPaDsThumbprint(String paDsThumbprint) {
        this.paDsThumbprint = paDsThumbprint;
    }

    public String getPaDsCode() {
        return paDsCode;
    }

    public void setPaDsCode(String paDsCode) {
        this.paDsCode = paDsCode;
    }

    public Date getPaDsDateB() {
        return paDsDateB;
    }

    public void setPaDsDateB(Date paDsDateB) {
        this.paDsDateB = paDsDateB;
    }

    public Date getPaDsDateE() {
        return paDsDateE;
    }

    public void setPaDsDateE(Date paDsDateE) {
        this.paDsDateE = paDsDateE;
    }

    public Date getWubOperationDate() {
        return wubOperationDate;
    }

    public void setWubOperationDate(Date wubOperationDate) {
        this.wubOperationDate = wubOperationDate;
    }

    public Long getWubItsUserId() {
        return wubItsUserId;
    }

    public void setWubItsUserId(Long wubItsUserId) {
        this.wubItsUserId = wubItsUserId;
    }

    public String getWubReason() {
        return wubReason;
    }

    public void setWubReason(String wubReason) {
        this.wubReason = wubReason;
    }

    public long getTypeStateUnid() {
        return typeStateUnid;
    }

    public void setTypeStateUnid(long typeStateUnid) {
        this.typeStateUnid = typeStateUnid;
    }

    public Long getDlaUnid() {
        return dlaUnid;
    }

    public void setDlaUnid(Long dlaUnid) {
        this.dlaUnid = dlaUnid;
    }

    public String getDlaName() {
        return dlaName;
    }

    public void setDlaName(String dlaName) {
        this.dlaName = dlaName;
    }

    public Long getStaffSubPersonUnid() {
        return staffSubPersonUnid;
    }

    public void setStaffSubPersonUnid(Long staffSubPersonUnid) {
        this.staffSubPersonUnid = staffSubPersonUnid;
    }

    public String getStaffSubPersonNameF() {
        return staffSubPersonNameF;
    }

    public void setStaffSubPersonNameF(String staffSubPersonNameF) {
        this.staffSubPersonNameF = staffSubPersonNameF;
    }

    public String getStaffSubPersonNameI() {
        return staffSubPersonNameI;
    }

    public void setStaffSubPersonNameI(String staffSubPersonNameI) {
        this.staffSubPersonNameI = staffSubPersonNameI;
    }

    public String getStaffSubPersonNameO() {
        return staffSubPersonNameO;
    }

    public void setStaffSubPersonNameO(String staffSubPersonNameO) {
        this.staffSubPersonNameO = staffSubPersonNameO;
    }

    public String getStaffSubPersonEMail() {
        return staffSubPersonEMail;
    }

    public void setStaffSubPersonEMail(String staffSubPersonEMail) {
        this.staffSubPersonEMail = staffSubPersonEMail;
    }

    public String getStaffSubPersonTel() {
        return staffSubPersonTel;
    }

    public void setStaffSubPersonTel(String staffSubPersonTel) {
        this.staffSubPersonTel = staffSubPersonTel;
    }

    public String getSstaffFoundation() {
        return sstaffFoundation;
    }

    public void setSstaffFoundation(String sstaffFoundation) {
        this.sstaffFoundation = sstaffFoundation;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPartyAddrFact() {
        return partyAddrFact;
    }

    public void setPartyAddrFact(String partyAddrFact) {
        this.partyAddrFact = partyAddrFact;
    }

    public String getPartyAddrLegal() {
        return partyAddrLegal;
    }

    public void setPartyAddrLegal(String partyAddrLegal) {
        this.partyAddrLegal = partyAddrLegal;
    }

    public String getPartyEmail() {
        return partyEmail;
    }

    public void setPartyEmail(String partyEmail) {
        this.partyEmail = partyEmail;
    }

    public String getPartyPhone() {
        return partyPhone;
    }

    public void setPartyPhone(String partyPhone) {
        this.partyPhone = partyPhone;
    }

    public String getPartyInn() {
        return partyInn;
    }

    public void setPartyInn(String partyInn) {
        this.partyInn = partyInn;
    }

    public Long getOpfUnid() {
        return opfUnid;
    }

    public void setOpfUnid(Long opfUnid) {
        this.opfUnid = opfUnid;
    }

    public Integer getOpfCode() {
        return opfCode;
    }

    public void setOpfCode(Integer opfCode) {
        this.opfCode = opfCode;
    }

    public String getOpfName() {
        return opfName;
    }

    public void setOpfName(String opfName) {
        this.opfName = opfName;
    }

    public String getOpfSname() {
        return opfSname;
    }

    public void setOpfSname(String opfSname) {
        this.opfSname = opfSname;
    }

    public Long getSraUnid() {
        return sraUnid;
    }

    public void setSraUnid(Long sraUnid) {
        this.sraUnid = sraUnid;
    }

    public String getSraName() {
        return sraName;
    }

    public void setSraName(String sraName) {
        this.sraName = sraName;
    }

    public Short getWbIndCommissioner() {
        return wbIndCommissioner;
    }

    public void setWbIndCommissioner(Short wbIndCommissioner) {
        this.wbIndCommissioner = wbIndCommissioner;
    }

    public BigInteger getSubIndAsv() {
        return subIndAsv;
    }

    public void setSubIndAsv(BigInteger subIndAsv) {
        this.subIndAsv = subIndAsv;
    }

    public BigInteger getOrganizerUnid() {
        return organizerUnid;
    }

    public void setOrganizerUnid(BigInteger organizerUnid) {
        this.organizerUnid = organizerUnid;
    }

}
