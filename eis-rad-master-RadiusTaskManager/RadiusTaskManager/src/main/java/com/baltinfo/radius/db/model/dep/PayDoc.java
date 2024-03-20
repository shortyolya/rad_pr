package com.baltinfo.radius.db.model.dep;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author kya
 */
@Entity
@Table(name = "pay_doc", catalog = "", schema = "dep", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"pay_doc_unid"})})
@SequenceGenerator(name = "seq_pay_doc", sequenceName = "seq_pay_doc", allocationSize = 1)
@XmlRootElement
public class PayDoc {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pay_doc")
    @Column(name = "pay_doc_unid")
    private Long payDocUnid;
    @JoinColumn(name = "bb_unid", referencedColumnName = "bb_unid")
    @ManyToOne(fetch = FetchType.LAZY)
    private BankBook bbUnid;
    @Column(name = "adep_unid")
    private Long adepUnid;
    @Column(name = "copying_bank_unid")
    private Long copyingBankUnid;
    @JoinColumn(name = "acc_oper_unid", referencedColumnName = "acc_oper_unid")
    @ManyToOne(fetch = FetchType.LAZY)
    private AccountOperation accOperUnid;
    @JoinColumn(name = "acc_acc_oper_unid", referencedColumnName = "acc_oper_unid")
    @ManyToOne(fetch = FetchType.LAZY)
    private AccountOperation accAccOperUnid;
    @Column(name = "found_unid")
    private Long foundUnid;
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
    @Column(name = "pay_doc_name")
    private String payDocName;
    @Column(name = "pay_doc_number")
    private String payDocNumber;
    @Column(name = "pay_doc_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date payDocDate;
    @Column(name = "pay_doc_bank_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date payDocBankDate;
    @Column(name = "pay_doc_sum")
    private BigDecimal payDocSum;
    @Column(name = "pay_doc_payer")
    private String payDocPayer;
    @Column(name = "pay_doc_payer_inn")
    private String payDocPayerInn;
    @Column(name = "pay_doc_payer_account")
    private String payDocPayerAccount;
    @Column(name = "pay_doc_payer_bank")
    private String payDocPayerBank;
    @Column(name = "pay_doc_payer_bank_bik")
    private String payDocPayerBankBik;
    @Column(name = "pay_doc_payer_corr_account")
    private String payDocPayerCorrAccount;
    @Column(name = "pay_doc_recipient")
    private String payDocRecipient;
    @Column(name = "pay_doc_rec_inn")
    private String payDocRecInn;
    @Column(name = "pay_doc_rec_account")
    private String payDocRecAccount;
    @Column(name = "pay_doc_rec_bank")
    private String payDocRecBank;
    @Column(name = "pay_doc_rec_bank_bik")
    private String payDocRecBankBik;
    @Column(name = "pay_doc_rec_corr_account")
    private String payDocRecCorrAccount;
    @Column(name = "pay_doc_purpose")
    private String payDocPurpose;
    @Column(name = "pay_doc_ind_out")
    private Short payDocIndOut;
    @Column(name = "pay_doc_linking")
    private Integer payDocLinking;
    @Column(name = "pay_doc_linking_descr")
    private String payDocLinkingDescr;
    @Column(name = "pay_doc_ind_download")
    private Integer payDocIndDownload;
    @Column(name = "pay_doc_payer_kpp")
    private String payDocPayerKpp;
    @Column(name = "pay_doc_rec_kpp")
    private String payDocRecKpp;
    @Column(name = "pay_doc_rec_kbk")
    private String payDocRecKbk;
    @Column(name = "pay_doc_rec_oktmo")
    private String payDocRecOktmo;
    @Column(name = "pay_doc_payer_status")
    private String payDocPayerStatus;
    @Column(name = "pay_doc_code")
    private String payDocCode;
    @Column(name = "pay_doc_base_indicator")
    private String payDocBaseIndicator;
    @Column(name = "pay_doc_taxable_period")
    private String payDocTaxablePeriod;
    @Column(name = "pay_doc_doc_num_indicator")
    private String payDocDocNumIndicator;
    @Column(name = "pay_doc_doc_date_indicator")
    private String payDocDocDateIndicator;
    @Column(name = "pay_doc_version_num")
    private String payDocVersionNum;
    @Column(name = "pay_doc_version_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date payDocVersionDate;
    @Column(name = "pay_doc_version_ind_current")
    private Boolean payDocVersionIndCurrent;
    @JsonIgnore
    @JoinColumn(name = "pay_doc_version_unid", referencedColumnName = "pay_doc_unid")
    @ManyToOne(fetch = FetchType.LAZY)
    private PayDoc payDocVersionUnid;
    @Column(name = "pay_doc_rec_bank_dadata")
    private String payDocRecBankDadata;
    @Column(name = "pay_doc_rec_corr_account_dadata")
    private String payDocRecCorrAccountDadata;
    @Column(name = "pay_doc_lot_number")
    private String payDocLotNumber;
    @Column(name = "pay_doc_profile_id")
    private Long payDocProfileId;
    @Column(name = "pay_doc_wb_unid")
    private Long payDocWbUnid;

    public PayDoc() {
    }

    public PayDoc(Long payDocUnid) {
        this.payDocUnid = payDocUnid;
    }

    public Long getPayDocUnid() {
        return payDocUnid;
    }

    public void setPayDocUnid(Long payDocUnid) {
        this.payDocUnid = payDocUnid;
    }

    public Long getFoundUnid() {
        return foundUnid;
    }

    public void setFoundUnid(Long foundUnid) {
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

    public String getPayDocName() {
        return payDocName;
    }

    public void setPayDocName(String payDocName) {
        this.payDocName = payDocName;
    }

    public String getPayDocNumber() {
        return payDocNumber;
    }

    public void setPayDocNumber(String payDocNumber) {
        this.payDocNumber = payDocNumber;
    }

    public Date getPayDocDate() {
        return payDocDate;
    }

    public void setPayDocDate(Date payDocDate) {
        this.payDocDate = payDocDate;
    }

    public Date getPayDocBankDate() {
        return payDocBankDate;
    }

    public void setPayDocBankDate(Date payDocBankDate) {
        this.payDocBankDate = payDocBankDate;
    }

    public BigDecimal getPayDocSum() {
        return payDocSum;
    }

    public void setPayDocSum(BigDecimal payDocSum) {
        this.payDocSum = payDocSum;
    }

    public String getPayDocPayer() {
        return payDocPayer;
    }

    public void setPayDocPayer(String payDocPayer) {
        this.payDocPayer = payDocPayer;
    }

    public String getPayDocPayerInn() {
        return payDocPayerInn;
    }

    public void setPayDocPayerInn(String payDocPayerInn) {
        this.payDocPayerInn = payDocPayerInn;
    }

    public String getPayDocPayerAccount() {
        return payDocPayerAccount;
    }

    public void setPayDocPayerAccount(String payDocPayerAccount) {
        this.payDocPayerAccount = payDocPayerAccount;
    }

    public String getPayDocPayerBank() {
        return payDocPayerBank;
    }

    public void setPayDocPayerBank(String payDocPayerBank) {
        this.payDocPayerBank = payDocPayerBank;
    }

    public String getPayDocPayerBankBik() {
        return payDocPayerBankBik;
    }

    public void setPayDocPayerBankBik(String payDocPayerBankBik) {
        this.payDocPayerBankBik = payDocPayerBankBik;
    }

    public String getPayDocPayerCorrAccount() {
        return payDocPayerCorrAccount;
    }

    public void setPayDocPayerCorrAccount(String payDocPayerCorrAccount) {
        this.payDocPayerCorrAccount = payDocPayerCorrAccount;
    }

    public String getPayDocRecipient() {
        return payDocRecipient;
    }

    public void setPayDocRecipient(String payDocRecipient) {
        this.payDocRecipient = payDocRecipient;
    }

    public String getPayDocRecInn() {
        return payDocRecInn;
    }

    public void setPayDocRecInn(String payDocRecInn) {
        this.payDocRecInn = payDocRecInn;
    }

    public String getPayDocRecAccount() {
        return payDocRecAccount;
    }

    public void setPayDocRecAccount(String payDocRecAccount) {
        this.payDocRecAccount = payDocRecAccount;
    }

    public String getPayDocRecBank() {
        return payDocRecBank;
    }

    public void setPayDocRecBank(String payDocRecBank) {
        this.payDocRecBank = payDocRecBank;
    }

    public String getPayDocRecBankBik() {
        return payDocRecBankBik;
    }

    public void setPayDocRecBankBik(String payDocRecBankBik) {
        this.payDocRecBankBik = payDocRecBankBik;
    }

    public String getPayDocRecCorrAccount() {
        return payDocRecCorrAccount;
    }

    public void setPayDocRecCorrAccount(String payDocRecCorrAccount) {
        this.payDocRecCorrAccount = payDocRecCorrAccount;
    }

    public String getPayDocPurpose() {
        return payDocPurpose;
    }

    public void setPayDocPurpose(String payDocPurpose) {
        this.payDocPurpose = payDocPurpose;
    }

    public boolean isPayDocIndOut() {
        return payDocIndOut != null && payDocIndOut == 1;
    }

    public void setPayDocIndOut(boolean payDocIndOut) {
        this.payDocIndOut = payDocIndOut ? (short) 1 : (short) 0;
    }

    public Integer getPayDocLinking() {
        return payDocLinking;
    }

    public void setPayDocLinking(Integer payDocLinking) {
        this.payDocLinking = payDocLinking;
    }

    public String getPayDocLinkingDescr() {
        return payDocLinkingDescr;
    }

    public void setPayDocLinkingDescr(String payDocLinkingDescr) {
        this.payDocLinkingDescr = payDocLinkingDescr;
    }

    public Integer getPayDocIndDownload() {
        return payDocIndDownload;
    }

    public void setPayDocIndDownload(Integer payDocIndDownload) {
        this.payDocIndDownload = payDocIndDownload;
    }

    public String getPayDocPayerKpp() {
        return payDocPayerKpp;
    }

    public void setPayDocPayerKpp(String payDocPayerKpp) {
        this.payDocPayerKpp = payDocPayerKpp;
    }

    public String getPayDocRecKpp() {
        return payDocRecKpp;
    }

    public void setPayDocRecKpp(String payDocRecKpp) {
        this.payDocRecKpp = payDocRecKpp;
    }

    public BankBook getBbUnid() {
        return bbUnid;
    }

    public void setBbUnid(BankBook bbUnid) {
        this.bbUnid = bbUnid;
    }

    public Long getAdepUnid() {
        return adepUnid;
    }

    public void setAdepUnid(Long adepUnid) {
        this.adepUnid = adepUnid;
    }

    public Long getCopyingBankUnid() {
        return copyingBankUnid;
    }

    public void setCopyingBankUnid(Long copyingBankUnid) {
        this.copyingBankUnid = copyingBankUnid;
    }

    public AccountOperation getAccOperUnid() {
        return accOperUnid;
    }

    public void setAccOperUnid(AccountOperation accOperUnid) {
        this.accOperUnid = accOperUnid;
    }

    public AccountOperation getAccAccOperUnid() {
        return accAccOperUnid;
    }

    public void setAccAccOperUnid(AccountOperation accAccOperUnid) {
        this.accAccOperUnid = accAccOperUnid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (payDocUnid != null ? payDocUnid.hashCode() : 0);
        return hash;
    }

    public String getPayDocRecKbk() {
        return payDocRecKbk;
    }

    public void setPayDocRecKbk(String payDocRecKbk) {
        this.payDocRecKbk = payDocRecKbk;
    }

    public String getPayDocRecOktmo() {
        return payDocRecOktmo;
    }

    public void setPayDocRecOktmo(String payDocRecOktmo) {
        this.payDocRecOktmo = payDocRecOktmo;
    }

    public String getPayDocPayerStatus() {
        return payDocPayerStatus;
    }

    public void setPayDocPayerStatus(String payDocPayerStatus) {
        this.payDocPayerStatus = payDocPayerStatus;
    }

    public String getPayDocCode() {
        return payDocCode;
    }

    public void setPayDocCode(String payDocCode) {
        this.payDocCode = payDocCode;
    }

    public String getPayDocBaseIndicator() {
        return payDocBaseIndicator;
    }

    public void setPayDocBaseIndicator(String payDocBaseIndicator) {
        this.payDocBaseIndicator = payDocBaseIndicator;
    }

    public String getPayDocTaxablePeriod() {
        return payDocTaxablePeriod;
    }

    public void setPayDocTaxablePeriod(String payDocTaxablePeriod) {
        this.payDocTaxablePeriod = payDocTaxablePeriod;
    }

    public String getPayDocDocNumIndicator() {
        return payDocDocNumIndicator;
    }

    public void setPayDocDocNumIndicator(String payDocDocNumIndicator) {
        this.payDocDocNumIndicator = payDocDocNumIndicator;
    }

    public String getPayDocDocDateIndicator() {
        return payDocDocDateIndicator;
    }

    public void setPayDocDocDateIndicator(String payDocDocDateIndicator) {
        this.payDocDocDateIndicator = payDocDocDateIndicator;
    }

    public String getPayDocVersionNum() {
        return payDocVersionNum;
    }

    public void setPayDocVersionNum(String payDocVersionNum) {
        this.payDocVersionNum = payDocVersionNum;
    }

    public Date getPayDocVersionDate() {
        return payDocVersionDate;
    }

    public void setPayDocVersionDate(Date payDocVersionDate) {
        this.payDocVersionDate = payDocVersionDate;
    }

    public Boolean getPayDocVersionIndCurrent() {
        return payDocVersionIndCurrent;
    }

    public void setPayDocVersionIndCurrent(Boolean payDocVersionIndCurrent) {
        this.payDocVersionIndCurrent = payDocVersionIndCurrent;
    }

    public PayDoc getPayDocVersionUnid() {
        return payDocVersionUnid;
    }

    public void setPayDocVersionUnid(PayDoc payDocVersionUnid) {
        this.payDocVersionUnid = payDocVersionUnid;
    }

    public String getPayDocRecBankDadata() {
        return payDocRecBankDadata;
    }

    public void setPayDocRecBankDadata(String payDocRecBankDadata) {
        this.payDocRecBankDadata = payDocRecBankDadata;
    }

    public String getPayDocRecCorrAccountDadata() {
        return payDocRecCorrAccountDadata;
    }

    public void setPayDocRecCorrAccountDadata(String payDocRecCorrAccountDadata) {
        this.payDocRecCorrAccountDadata = payDocRecCorrAccountDadata;
    }

    public String getPayDocLotNumber() {
        return payDocLotNumber;
    }

    public void setPayDocLotNumber(String payDocLotNumber) {
        this.payDocLotNumber = payDocLotNumber;
    }

    public Long getPayDocProfileId() {
        return payDocProfileId;
    }

    public void setPayDocProfileId(Long payDocProfileId) {
        this.payDocProfileId = payDocProfileId;
    }

    public Long getPayDocWbUnid() {
        return payDocWbUnid;
    }

    public void setPayDocWbUnid(Long payDocWbUnid) {
        this.payDocWbUnid = payDocWbUnid;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PayDoc)) {
            return false;
        }
        PayDoc other = (PayDoc) object;
        if ((this.payDocUnid == null && other.payDocUnid != null) || (this.payDocUnid != null && !this.payDocUnid.equals(other.payDocUnid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.dep.model.PayDoc[ payDocUnid=" + payDocUnid + " ]";
    }

}
