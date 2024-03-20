package com.baltinfo.radius.db.model.lotonline;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Suvorina Aleksandra
 * @since 16.12.2019
 */
@Entity
@Table(name = "user_account_details")
public class UserAccountDetails implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "status")
    private String status;

    @Column(name = "recipient_name")
    private String recipientName;

    @Column(name = "recipient_inn")
    private String recipientInn;

    @Column(name = "recipient_kpp")
    private String recipientKpp;

    @Column(name = "recipient_kbk")
    private String recipientKbk;

    @Column(name = "recipient_oktmo")
    private String recipientOktmo;

    @Column(name = "recipient_account")
    private String recipientAccount;

    @Column(name = "recipient_bank_name")
    private String recipientBankName;

    @Column(name = "recipient_bank_bic")
    private String recipientBankBic;

    @Column(name = "recipient_bank_corr_account")
    private String recipientBankCorrAccount;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "npa_code")
    private String npaCode;

    @Column(name = "status_document_settlement")
    private String statusDocumentSettlement;

    @Column(name = "value_reason_payment")
    private String valueReasonPayment;

    @Column(name = "tax_code")
    private String taxCode;

    @Column(name = "indicator_document_number")
    private String indicatorDocumentNumber;

    @Column(name = "indicator_document_date")
    private String indicatorDocumentDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientInn() {
        return recipientInn;
    }

    public void setRecipientInn(String recipientInn) {
        this.recipientInn = recipientInn;
    }

    public String getRecipientKpp() {
        return recipientKpp;
    }

    public void setRecipientKpp(String recipientKpp) {
        this.recipientKpp = recipientKpp;
    }

    public String getRecipientKbk() {
        return recipientKbk;
    }

    public void setRecipientKbk(String recipientKbk) {
        this.recipientKbk = recipientKbk;
    }

    public String getRecipientOktmo() {
        return recipientOktmo;
    }

    public void setRecipientOktmo(String recipientOktmo) {
        this.recipientOktmo = recipientOktmo;
    }

    public String getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(String recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    public String getRecipientBankName() {
        return recipientBankName;
    }

    public void setRecipientBankName(String recipientBankName) {
        this.recipientBankName = recipientBankName;
    }

    public String getRecipientBankBic() {
        return recipientBankBic;
    }

    public void setRecipientBankBic(String recipientBankBic) {
        this.recipientBankBic = recipientBankBic;
    }

    public String getRecipientBankCorrAccount() {
        return recipientBankCorrAccount;
    }

    public void setRecipientBankCorrAccount(String recipientBankCorrAccount) {
        this.recipientBankCorrAccount = recipientBankCorrAccount;
    }

    public String getAccountName() { return accountName; }

    public void setAccountName(String accountName) { this.accountName = accountName; }

    public String getNpaCode() {
        return npaCode;
    }

    public void setNpaCode(String npaCode) {
        this.npaCode = npaCode;
    }

    public String getStatusDocumentSettlement() {
        return statusDocumentSettlement;
    }

    public void setStatusDocumentSettlement(String statusDocumentSettlement) {
        this.statusDocumentSettlement = statusDocumentSettlement;
    }

    public String getValueReasonPayment() {
        return valueReasonPayment;
    }

    public void setValueReasonPayment(String valueReasonPayment) {
        this.valueReasonPayment = valueReasonPayment;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getIndicatorDocumentNumber() {
        return indicatorDocumentNumber;
    }

    public void setIndicatorDocumentNumber(String indicatorDocumentNumber) {
        this.indicatorDocumentNumber = indicatorDocumentNumber;
    }

    public String getIndicatorDocumentDate() {
        return indicatorDocumentDate;
    }

    public void setIndicatorDocumentDate(String indicatorDocumentDate) {
        this.indicatorDocumentDate = indicatorDocumentDate;
    }

}
