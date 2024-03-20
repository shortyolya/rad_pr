/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baltinfo.radius.db.model.lotonline;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author css
 */
@Entity
@Table(name = "user_profile", catalog = "", schema = "")
@XmlRootElement
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "profile_type", nullable = false, length = 31)
    private String profileType;
    @Column(name = "person_email", length = 255)
    private String personEmail;
    @Column(name = "person_phone", length = 50)
    private String personPhone;
    @Column(length = 12)
    private String inn;
    @Column(length = 15)
    private String ogrn;
    @Column(name = "company_full_name", length = 512)
    private String companyFullName;
    @Column(name = "company_short_name", length = 128)
    private String companyShortName;
    @Column(length = 9)
    private String kpp;
    @Column(name = "user_id")
    private BigInteger userId;
    @Column(length = 20)
    private String state;
    @Column(name = "linkedprofile_id")
    private BigInteger linkedprofileId;
    @Column(name = "documents_id")
    private BigInteger documentsId;
    @Column(name = "person_fio", length = 250)
    private String personFio;
    @Column(name = "agent_code", length = 10)
    private String agentCode;
    @Column(name = "company_speciality", length = 512)
    private String companySpeciality;
    @Column(name = "company_description", length = 2048)
    private String companyDescription;
    @Column(name = "company_address", length = 1024)
    private String companyAddress;
    @Column(name = "company_legal_address", length = 1024)
    private String companyLegalAddress;
    @Column(name = "company_phones", length = 128)
    private String companyPhones;
    @Column(name = "company_fax", length = 128)
    private String companyFax;
    @Column(name = "company_email", length = 255)
    private String companyEmail;
    @Column(name = "company_website", length = 512)
    private String companyWebsite;
    @Column(name = "transactional_account", length = 20)
    private String transactionalAccount;
    @Column(name = "correspondent_account", length = 20)
    private String correspondentAccount;
    @Column(name = "bank_identification_code", length = 9)
    private String bankIdentificationCode;
    @Column(length = 10)
    private String okpo;
    @Column(name = "responsible_employees_fio", length = 250)
    private String responsibleEmployeesFio;
    @Column(name = "responsible_employees_phones", length = 250)
    private String responsibleEmployeesPhones;
    @Column(name = "responsible_employees_emails", length = 250)
    private String responsibleEmployeesEmails;
    @Column(name = "attached_documents_id")
    private BigInteger attachedDocumentsId;
    @Column(name = "bank_full_name", length = 250)
    private String bankFullName;
    @Column(length = 11)
    private String okato;
    @Column(length = 15)
    private String ogrnip;
    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @Column(length = 250)
    private String logotype;
    @Column(name = "responsible_employees_position", length = 100)
    private String responsibleEmployeesPosition;
    @Column(name = "activation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activationDate;
    @Column(name = "region_id")
    private BigInteger regionId;
    @Column(name = "passport_number", length = 100)
    private String passportNumber;
    @Column(name = "passport_serial", length = 100)
    private String passportSerial;
    @Column(name = "passport_issued", length = 255)
    private String passportIssued;
    @Basic(optional = false)
    @Column(name = "remove_from_search_index", nullable = false)
    private boolean removeFromSearchIndex;
    @Column(name = "submission_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date submissionTimestamp;
    @Basic(optional = false)
    @Column(name = "profile_title", nullable = false, length = 250)
    private String profileTitle;
    @Column(name = "author_id")
    private BigInteger authorId;
    @Column(length = 250)
    private String photo;
    @Basic(optional = false)
    @Column(name = "remove_from_collateral_search_index", nullable = false)
    private boolean removeFromCollateralSearchIndex;
    @Column(length = 512)
    private String address;
    @Column(name = "fias_address_guid", length = 36)
    private String fiasAddressGuid;
    @Basic(optional = false)
    @Column(name = "is_house_address", nullable = false)
    private boolean isHouseAddress;
    @Column(name = "country_fk")
    private BigInteger countryFk;
    @Basic(optional = false)
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;
    @Basic(optional = false)
    @Column(name = "is_blocked", nullable = false)
    private boolean isBlocked;
    @Column(name = "manual_address_text", length = 1024)
    private String manualAddressText;
    @Column(name = "oos_login", length = 255)
    private String oosLogin;
    @Column(name = "oos_password", length = 255)
    private String oosPassword;
    @Column(name = "oos_placer", length = 15)
    private String oosPlacer;
    @Column(name = "is_publications_limited")
    private Boolean isPublicationsLimited;
    @Column(name = "publications_limit")
    private BigInteger publicationsLimit;
    @Column(name = "root_id")
    private BigInteger rootId;
    private BigInteger version;
    @Column(length = 20)
    private String iko;
    @Column(name = "snapshot_id")
    private BigInteger snapshotId;

    public UserProfile() {
    }

    public UserProfile(Long id) {
        this.id = id;
    }

    public UserProfile(Long id, String profileType, boolean removeFromSearchIndex, String profileTitle, boolean removeFromCollateralSearchIndex, boolean isHouseAddress, boolean isDeleted, boolean isBlocked) {
        this.id = id;
        this.profileType = profileType;
        this.removeFromSearchIndex = removeFromSearchIndex;
        this.profileTitle = profileTitle;
        this.removeFromCollateralSearchIndex = removeFromCollateralSearchIndex;
        this.isHouseAddress = isHouseAddress;
        this.isDeleted = isDeleted;
        this.isBlocked = isBlocked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfileType() {
        return profileType;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getCompanyFullName() {
        return companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
    }

    public String getCompanyShortName() {
        return companyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigInteger getLinkedprofileId() {
        return linkedprofileId;
    }

    public void setLinkedprofileId(BigInteger linkedprofileId) {
        this.linkedprofileId = linkedprofileId;
    }

    public BigInteger getDocumentsId() {
        return documentsId;
    }

    public void setDocumentsId(BigInteger documentsId) {
        this.documentsId = documentsId;
    }

    public String getPersonFio() {
        return personFio;
    }

    public void setPersonFio(String personFio) {
        this.personFio = personFio;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getCompanySpeciality() {
        return companySpeciality;
    }

    public void setCompanySpeciality(String companySpeciality) {
        this.companySpeciality = companySpeciality;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyLegalAddress() {
        return companyLegalAddress;
    }

    public void setCompanyLegalAddress(String companyLegalAddress) {
        this.companyLegalAddress = companyLegalAddress;
    }

    public String getCompanyPhones() {
        return companyPhones;
    }

    public void setCompanyPhones(String companyPhones) {
        this.companyPhones = companyPhones;
    }

    public String getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getTransactionalAccount() {
        return transactionalAccount;
    }

    public void setTransactionalAccount(String transactionalAccount) {
        this.transactionalAccount = transactionalAccount;
    }

    public String getCorrespondentAccount() {
        return correspondentAccount;
    }

    public void setCorrespondentAccount(String correspondentAccount) {
        this.correspondentAccount = correspondentAccount;
    }

    public String getBankIdentificationCode() {
        return bankIdentificationCode;
    }

    public void setBankIdentificationCode(String bankIdentificationCode) {
        this.bankIdentificationCode = bankIdentificationCode;
    }

    public String getOkpo() {
        return okpo;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

    public String getResponsibleEmployeesFio() {
        return responsibleEmployeesFio;
    }

    public void setResponsibleEmployeesFio(String responsibleEmployeesFio) {
        this.responsibleEmployeesFio = responsibleEmployeesFio;
    }

    public String getResponsibleEmployeesPhones() {
        return responsibleEmployeesPhones;
    }

    public void setResponsibleEmployeesPhones(String responsibleEmployeesPhones) {
        this.responsibleEmployeesPhones = responsibleEmployeesPhones;
    }

    public String getResponsibleEmployeesEmails() {
        return responsibleEmployeesEmails;
    }

    public void setResponsibleEmployeesEmails(String responsibleEmployeesEmails) {
        this.responsibleEmployeesEmails = responsibleEmployeesEmails;
    }

    public BigInteger getAttachedDocumentsId() {
        return attachedDocumentsId;
    }

    public void setAttachedDocumentsId(BigInteger attachedDocumentsId) {
        this.attachedDocumentsId = attachedDocumentsId;
    }

    public String getBankFullName() {
        return bankFullName;
    }

    public void setBankFullName(String bankFullName) {
        this.bankFullName = bankFullName;
    }

    public String getOkato() {
        return okato;
    }

    public void setOkato(String okato) {
        this.okato = okato;
    }

    public String getOgrnip() {
        return ogrnip;
    }

    public void setOgrnip(String ogrnip) {
        this.ogrnip = ogrnip;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLogotype() {
        return logotype;
    }

    public void setLogotype(String logotype) {
        this.logotype = logotype;
    }

    public String getResponsibleEmployeesPosition() {
        return responsibleEmployeesPosition;
    }

    public void setResponsibleEmployeesPosition(String responsibleEmployeesPosition) {
        this.responsibleEmployeesPosition = responsibleEmployeesPosition;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public BigInteger getRegionId() {
        return regionId;
    }

    public void setRegionId(BigInteger regionId) {
        this.regionId = regionId;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportSerial() {
        return passportSerial;
    }

    public void setPassportSerial(String passportSerial) {
        this.passportSerial = passportSerial;
    }

    public String getPassportIssued() {
        return passportIssued;
    }

    public void setPassportIssued(String passportIssued) {
        this.passportIssued = passportIssued;
    }

    public boolean getRemoveFromSearchIndex() {
        return removeFromSearchIndex;
    }

    public void setRemoveFromSearchIndex(boolean removeFromSearchIndex) {
        this.removeFromSearchIndex = removeFromSearchIndex;
    }

    public Date getSubmissionTimestamp() {
        return submissionTimestamp;
    }

    public void setSubmissionTimestamp(Date submissionTimestamp) {
        this.submissionTimestamp = submissionTimestamp;
    }

    public String getProfileTitle() {
        return profileTitle;
    }

    public void setProfileTitle(String profileTitle) {
        this.profileTitle = profileTitle;
    }

    public BigInteger getAuthorId() {
        return authorId;
    }

    public void setAuthorId(BigInteger authorId) {
        this.authorId = authorId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean getRemoveFromCollateralSearchIndex() {
        return removeFromCollateralSearchIndex;
    }

    public void setRemoveFromCollateralSearchIndex(boolean removeFromCollateralSearchIndex) {
        this.removeFromCollateralSearchIndex = removeFromCollateralSearchIndex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFiasAddressGuid() {
        return fiasAddressGuid;
    }

    public void setFiasAddressGuid(String fiasAddressGuid) {
        this.fiasAddressGuid = fiasAddressGuid;
    }

    public boolean getIsHouseAddress() {
        return isHouseAddress;
    }

    public void setIsHouseAddress(boolean isHouseAddress) {
        this.isHouseAddress = isHouseAddress;
    }

    public BigInteger getCountryFk() {
        return countryFk;
    }

    public void setCountryFk(BigInteger countryFk) {
        this.countryFk = countryFk;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public String getManualAddressText() {
        return manualAddressText;
    }

    public void setManualAddressText(String manualAddressText) {
        this.manualAddressText = manualAddressText;
    }

    public String getOosLogin() {
        return oosLogin;
    }

    public void setOosLogin(String oosLogin) {
        this.oosLogin = oosLogin;
    }

    public String getOosPassword() {
        return oosPassword;
    }

    public void setOosPassword(String oosPassword) {
        this.oosPassword = oosPassword;
    }

    public String getOosPlacer() {
        return oosPlacer;
    }

    public void setOosPlacer(String oosPlacer) {
        this.oosPlacer = oosPlacer;
    }

    public Boolean getIsPublicationsLimited() {
        return isPublicationsLimited;
    }

    public void setIsPublicationsLimited(Boolean isPublicationsLimited) {
        this.isPublicationsLimited = isPublicationsLimited;
    }

    public BigInteger getPublicationsLimit() {
        return publicationsLimit;
    }

    public void setPublicationsLimit(BigInteger publicationsLimit) {
        this.publicationsLimit = publicationsLimit;
    }

    public BigInteger getRootId() {
        return rootId;
    }

    public void setRootId(BigInteger rootId) {
        this.rootId = rootId;
    }

    public BigInteger getVersion() {
        return version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }

    public String getIko() {
        return iko;
    }

    public void setIko(String iko) {
        this.iko = iko;
    }

    public BigInteger getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(BigInteger snapshotId) {
        this.snapshotId = snapshotId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserProfile)) {
            return false;
        }
        UserProfile other = (UserProfile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.lotonline.model.UserProfile[ id=" + id + " ]";
    }
    
}
