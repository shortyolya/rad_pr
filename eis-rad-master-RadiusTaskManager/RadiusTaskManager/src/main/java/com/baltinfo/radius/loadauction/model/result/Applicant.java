package com.baltinfo.radius.loadauction.model.result;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Applicant {
    @JsonProperty("radId")
    private final Long radId;
    @JsonProperty("legalStatus")
    private final Integer legalStatus;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("inn")
    private final String inn;
    @JsonProperty("kpp")
    private final String kpp;
    @JsonProperty("ogrn")
    private final String ogrn;
    @JsonProperty("ogrnDateIssued")
    private final String ogrnDateIssued;
    @JsonProperty("ogrnByIssued")
    private final String ogrnByIssued;
    @JsonProperty("dulSeries")
    private final String dulSeries;
    @JsonProperty("dulNumber")
    private final String dulNumber;
    @JsonProperty("dulByIssued")
    private final String dulByIssued;
    @JsonProperty("dulDateIssued")
    private final String dulDateIssued;
    @JsonProperty("dateBirth")
    private final String dateBirth;
    @JsonProperty("leaderFIO")
    private final String leaderFIO;
    @JsonProperty("email")
    private final String email;
    @JsonProperty("phone")
    private final String phone;
    @JsonProperty("location")
    private final String location;
    @JsonProperty("payerAccNumber")
    private final String payerAccNumber;
    @JsonProperty("payerBankName")
    private final String payerBankName;
    @JsonProperty("payerCorrAccNumber")
    private final String payerCorrAccNumber;
    @JsonProperty("payerBIK")
    private final String payerBIK;
    @JsonProperty("secondary")
    private final String secondary;
    @JsonProperty("secondaryDocType")
    private final String secondaryDocType;
    @JsonProperty("secondaryDocNumber")
    private final String secondaryDocNumber;
    @JsonProperty("secondaryDocDate")
    private final String secondaryDocDate;
    @JsonProperty("secondaryDulSeries")
    private final String secondaryDulSeries;
    @JsonProperty("secondaryDulNumber")
    private final String secondaryDulNumber;
    @JsonProperty("secondaryDulByIssued")
    private final String secondaryDulByIssued;
    @JsonProperty("secondaryDulDateIssued")
    private final String secondaryDulDateIssued;
    @JsonProperty("applicationNumber")
    private final String applicationNumber;
    @JsonProperty("applicationDate")
    private final String applicationDate;
    @JsonProperty("period")
    private final Integer period;
    @JsonProperty("offeredPrice")
    private final BigDecimal offeredPrice;
    @JsonProperty("accountedDeposit")
    private final BigDecimal accountedDeposit;
    @JsonProperty("participantType")
    private final Integer participantType;

    @JsonCreator
    public Applicant(@JsonProperty("radId") Long radId,
                     @JsonProperty("legalStatus") Integer legalStatus,
                     @JsonProperty("name") String name,
                     @JsonProperty("inn") String inn,
                     @JsonProperty("kpp") String kpp,
                     @JsonProperty("ogrn") String ogrn,
                     @JsonProperty("ogrnDateIssued") String ogrnDateIssued,
                     @JsonProperty("ogrnByIssued") String ogrnByIssued,
                     @JsonProperty("dulSeries") String dulSeries,
                     @JsonProperty("dulNumber") String dulNumber,
                     @JsonProperty("dulByIssued") String dulByIssued,
                     @JsonProperty("dulDateIssued") String dulDateIssued,
                     @JsonProperty("dateBirth") String dateBirth,
                     @JsonProperty("leaderFIO") String leaderFIO,
                     @JsonProperty("email") String email,
                     @JsonProperty("phone") String phone,
                     @JsonProperty("location") String location,
                     @JsonProperty("payerAccNumber") String payerAccNumber,
                     @JsonProperty("payerBankName") String payerBankName,
                     @JsonProperty("payerCorrAccNumber") String payerCorrAccNumber,
                     @JsonProperty("payerBIK") String payerBIK,
                     @JsonProperty("secondary") String secondary,
                     @JsonProperty("secondaryDocType") String secondaryDocType,
                     @JsonProperty("secondaryDocNumber") String secondaryDocNumber,
                     @JsonProperty("secondaryDocDate") String secondaryDocDate,
                     @JsonProperty("secondaryDulSeries") String secondaryDulSeries,
                     @JsonProperty("secondaryDulNumber") String secondaryDulNumber,
                     @JsonProperty("secondaryDulByIssued") String secondaryDulByIssued,
                     @JsonProperty("secondaryDulDateIssued") String secondaryDulDateIssued,
                     @JsonProperty("applicationNumber") String applicationNumber,
                     @JsonProperty("applicationDate") String applicationDate,
                     @JsonProperty("period") Integer period,
                     @JsonProperty("offeredPrice") BigDecimal offeredPrice,
                     @JsonProperty("accountedDeposit") BigDecimal accountedDeposit,
                     @JsonProperty("participantType") Integer participantType) {
        this.radId = radId;
        this.legalStatus = legalStatus;
        this.name = name;
        this.inn = inn;
        this.kpp = kpp;
        this.ogrn = ogrn;
        this.ogrnDateIssued = ogrnDateIssued;
        this.ogrnByIssued = ogrnByIssued;
        this.dulSeries = dulSeries;
        this.dulNumber = dulNumber;
        this.dulByIssued = dulByIssued;
        this.dulDateIssued = dulDateIssued;
        this.dateBirth = dateBirth;
        this.leaderFIO = leaderFIO;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.payerAccNumber = payerAccNumber;
        this.payerBankName = payerBankName;
        this.payerCorrAccNumber = payerCorrAccNumber;
        this.payerBIK = payerBIK;
        this.secondary = secondary;
        this.secondaryDocType = secondaryDocType;
        this.secondaryDocNumber = secondaryDocNumber;
        this.secondaryDocDate = secondaryDocDate;
        this.secondaryDulSeries = secondaryDulSeries;
        this.secondaryDulNumber = secondaryDulNumber;
        this.secondaryDulByIssued = secondaryDulByIssued;
        this.secondaryDulDateIssued = secondaryDulDateIssued;
        this.applicationNumber = applicationNumber;
        this.applicationDate = applicationDate;
        this.period = period;
        this.offeredPrice = offeredPrice;
        this.accountedDeposit = accountedDeposit;
        this.participantType = participantType;
    }


    public static ApplicantBuilder builder() {
        return new ApplicantBuilder();
    }

    public static final class ApplicantBuilder {
        private Long radId;
        private Integer legalStatus;
        private String name;
        private String inn;
        private String kpp;
        private String ogrn;
        private String ogrnDateIssued;
        private String ogrnByIssued;
        private String dulSeries;
        private String dulNumber;
        private String dulByIssued;
        private String dulDateIssued;
        private String dateBirth;
        private String leaderFIO;
        private String email;
        private String phone;
        private String location;
        private String payerAccNumber;
        private String payerBankName;
        private String payerCorrAccNumber;
        private String payerBIK;
        private String secondary;
        private String secondaryDocType;
        private String secondaryDocNumber;
        private String secondaryDocDate;
        private String secondaryDulSeries;
        private String secondaryDulNumber;
        private String secondaryDulByIssued;
        private String secondaryDulDateIssued;
        private String applicationNumber;
        private String applicationDate;
        private Integer period;
        private BigDecimal offeredPrice;
        private BigDecimal accountedDeposit;
        private Integer participantType;

        private ApplicantBuilder() {
        }

        public ApplicantBuilder withRadId(Long radId) {
            this.radId = radId;
            return this;
        }

        public ApplicantBuilder withLegalStatus(Integer legalStatus) {
            this.legalStatus = legalStatus;
            return this;
        }

        public ApplicantBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ApplicantBuilder withInn(String inn) {
            this.inn = inn;
            return this;
        }

        public ApplicantBuilder withKpp(String kpp) {
            this.kpp = kpp;
            return this;
        }

        public ApplicantBuilder withOgrn(String ogrn) {
            this.ogrn = ogrn;
            return this;
        }

        public ApplicantBuilder withOgrnDateIssued(String ogrnDateIssued) {
            this.ogrnDateIssued = ogrnDateIssued;
            return this;
        }

        public ApplicantBuilder withOgrnByIssued(String ogrnByIssued) {
            this.ogrnByIssued = ogrnByIssued;
            return this;
        }

        public ApplicantBuilder withDulSeries(String dulSeries) {
            this.dulSeries = dulSeries;
            return this;
        }

        public ApplicantBuilder withDulNumber(String dulNumber) {
            this.dulNumber = dulNumber;
            return this;
        }

        public ApplicantBuilder withDulByIssued(String dulByIssued) {
            this.dulByIssued = dulByIssued;
            return this;
        }

        public ApplicantBuilder withDulDateIssued(String dulDateIssued) {
            this.dulDateIssued = dulDateIssued;
            return this;
        }

        public ApplicantBuilder withDateBirth(String dateBirth) {
            this.dateBirth = dateBirth;
            return this;
        }

        public ApplicantBuilder withLeaderFIO(String leaderFIO) {
            this.leaderFIO = leaderFIO;
            return this;
        }

        public ApplicantBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public ApplicantBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public ApplicantBuilder withLocation(String location) {
            this.location = location;
            return this;
        }

        public ApplicantBuilder withPayerAccNumber(String payerAccNumber) {
            this.payerAccNumber = payerAccNumber;
            return this;
        }

        public ApplicantBuilder withPayerBankName(String payerBankName) {
            this.payerBankName = payerBankName;
            return this;
        }

        public ApplicantBuilder withPayerCorrAccNumber(String payerCorrAccNumber) {
            this.payerCorrAccNumber = payerCorrAccNumber;
            return this;
        }

        public ApplicantBuilder withPayerBIK(String payerBIK) {
            this.payerBIK = payerBIK;
            return this;
        }

        public ApplicantBuilder withSecondary(String secondary) {
            this.secondary = secondary;
            return this;
        }

        public ApplicantBuilder withSecondaryDocType(String secondaryDocType) {
            this.secondaryDocType = secondaryDocType;
            return this;
        }

        public ApplicantBuilder withSecondaryDocNumber(String secondaryDocNumber) {
            this.secondaryDocNumber = secondaryDocNumber;
            return this;
        }

        public ApplicantBuilder withSecondaryDocDate(String secondaryDocDate) {
            this.secondaryDocDate = secondaryDocDate;
            return this;
        }

        public ApplicantBuilder withSecondaryDulSeries(String secondaryDulSeries) {
            this.secondaryDulSeries = secondaryDulSeries;
            return this;
        }

        public ApplicantBuilder withSecondaryDulNumber(String secondaryDulNumber) {
            this.secondaryDulNumber = secondaryDulNumber;
            return this;
        }

        public ApplicantBuilder withSecondaryDulByIssued(String secondaryDulByIssued) {
            this.secondaryDulByIssued = secondaryDulByIssued;
            return this;
        }

        public ApplicantBuilder withSecondaryDulDateIssued(String secondaryDulDateIssued) {
            this.secondaryDulDateIssued = secondaryDulDateIssued;
            return this;
        }

        public ApplicantBuilder withApplicationNumber(String applicationNumber) {
            this.applicationNumber = applicationNumber;
            return this;
        }

        public ApplicantBuilder withApplicationDate(String applicationDate) {
            this.applicationDate = applicationDate;
            return this;
        }

        public ApplicantBuilder withPeriod(Integer period) {
            this.period = period;
            return this;
        }

        public ApplicantBuilder withOfferedPrice(BigDecimal offeredPrice) {
            this.offeredPrice = offeredPrice;
            return this;
        }

        public ApplicantBuilder withAccountedDeposit(BigDecimal accountedDeposit) {
            this.accountedDeposit = accountedDeposit;
            return this;
        }

        public ApplicantBuilder withParticipantType(Integer participantType) {
            this.participantType = participantType;
            return this;
        }

        public Applicant build() {
            return new Applicant(radId, legalStatus, name, inn, kpp, ogrn, ogrnDateIssued, ogrnByIssued, dulSeries, dulNumber, dulByIssued, dulDateIssued, dateBirth, leaderFIO, email, phone, location, payerAccNumber, payerBankName, payerCorrAccNumber, payerBIK, secondary, secondaryDocType, secondaryDocNumber, secondaryDocDate, secondaryDulSeries, secondaryDulNumber, secondaryDulByIssued, secondaryDulDateIssued, applicationNumber, applicationDate, period, offeredPrice, accountedDeposit, participantType);
        }
    }

    public Long getRadId() {
        return radId;
    }

    public Integer getLegalStatus() {
        return legalStatus;
    }

    public String getName() {
        return name;
    }

    public String getInn() {
        return inn;
    }

    public String getKpp() {
        return kpp;
    }

    public String getOgrn() {
        return ogrn;
    }

    public String getOgrnDateIssued() {
        return ogrnDateIssued;
    }

    public String getOgrnByIssued() {
        return ogrnByIssued;
    }

    public String getDulSeries() {
        return dulSeries;
    }

    public String getDulNumber() {
        return dulNumber;
    }

    public String getDulByIssued() {
        return dulByIssued;
    }

    public String getDulDateIssued() {
        return dulDateIssued;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public String getLeaderFIO() {
        return leaderFIO;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public String getPayerAccNumber() {
        return payerAccNumber;
    }

    public String getPayerBankName() {
        return payerBankName;
    }

    public String getPayerCorrAccNumber() {
        return payerCorrAccNumber;
    }

    public String getPayerBIK() {
        return payerBIK;
    }

    public String getSecondary() {
        return secondary;
    }

    public String getSecondaryDocType() {
        return secondaryDocType;
    }

    public String getSecondaryDocNumber() {
        return secondaryDocNumber;
    }

    public String getSecondaryDocDate() {
        return secondaryDocDate;
    }

    public String getSecondaryDulSeries() {
        return secondaryDulSeries;
    }

    public String getSecondaryDulNumber() {
        return secondaryDulNumber;
    }

    public String getSecondaryDulByIssued() {
        return secondaryDulByIssued;
    }

    public String getSecondaryDulDateIssued() {
        return secondaryDulDateIssued;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public Integer getPeriod() {
        return period;
    }

    public BigDecimal getOfferedPrice() {
        return offeredPrice;
    }

    public BigDecimal getAccountedDeposit() {
        return accountedDeposit;
    }

    public Integer getParticipantType() {
        return participantType;
    }
}
