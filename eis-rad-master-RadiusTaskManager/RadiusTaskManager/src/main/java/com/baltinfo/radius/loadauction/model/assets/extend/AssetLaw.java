package com.baltinfo.radius.loadauction.model.assets.extend;

import com.baltinfo.radius.loadauction.model.AssetDescriptionPledge;
import com.baltinfo.radius.loadauction.model.DescriptionPledge;
import com.baltinfo.radius.loadauction.model.DocumentAsset;
import com.baltinfo.radius.loadauction.model.ImageAsset;
import com.baltinfo.radius.loadauction.model.assets.Asset;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * Java class для десериализация xml-файла
 * Type_asset=25 (Права требования к юридическим лицам) или 26 ( Права требования к ИП
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 26.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName(value = "Asset")
public class AssetLaw extends Asset {

    @JsonProperty("borrowerName")
    private final String borrowerName;
    @JsonProperty("borrowerInn")
    private final String borrowerInn;
    @JsonProperty("kd")
    private final String kd;
    @JsonProperty("weightAverageInterestRate")
    private final BigDecimal weightAverageInterestRate;
    @JsonProperty("dateKd")
    private final String dateKd;
    @JsonProperty("averageRepaymentDate")
    private final String averageRepaymentDate;
    @JsonProperty("debtRepaymentMethod")
    private final String debtRepaymentMethod;
    @JsonProperty("availability")
    private final String availability;
    @JsonProperty("descriptionPledges")
    private final AssetDescriptionPledge descriptionPledges;
    @JsonProperty("currency")
    private final String currency;
    @JsonProperty("amountOfBalance")
    private final BigDecimal amountOfBalance;
    @JsonProperty("overdue")
    private final Long overdue;
    @JsonProperty("trial")
    private final String trial;
    @JsonProperty("endDate")
    private final String endDate;
    @JsonProperty("documentStorage")
    private final String documentStorage;
    @JsonProperty("creditType")
    private final String creditType;
    @JsonProperty("balanceDate")
    private final String balanceDate;

    private AssetLaw(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset, String soonAsset, String deposits, String other,
                     String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset, String borrowerName, String borrowerInn, String kd, BigDecimal weightAverageInterestRate, String dateKd,
                     String averageRepaymentDate, String debtRepaymentMethod, String availability, AssetDescriptionPledge descriptionPledges, String currency, BigDecimal amountOfBalance, Long overdue,
                     String trial, String endDate, String documentStorage, String about, String creditType, String balanceDate) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.borrowerName = borrowerName;
        this.borrowerInn = borrowerInn;
        this.kd = kd;
        this.weightAverageInterestRate = weightAverageInterestRate;
        this.dateKd = dateKd;
        this.averageRepaymentDate = averageRepaymentDate;
        this.debtRepaymentMethod = debtRepaymentMethod;
        this.availability = availability;
        this.descriptionPledges = descriptionPledges;
        this.currency = currency;
        this.amountOfBalance = amountOfBalance;
        this.overdue = overdue;
        this.trial = trial;
        this.endDate = endDate;
        this.documentStorage = documentStorage;
        this.creditType = creditType;
        this.balanceDate = balanceDate;
    }

    public static AssetLawBuilder builder() {
        return new AssetLawBuilder();
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public String getBorrowerInn() {
        return borrowerInn;
    }

    public String getKd() {
        return kd;
    }

    public BigDecimal getWeightAverageInterestRate() {
        return weightAverageInterestRate;
    }

    public String getDateKd() {
        return dateKd;
    }

    public String getAverageRepaymentDate() {
        return averageRepaymentDate;
    }

    public String getDebtRepaymentMethod() {
        return debtRepaymentMethod;
    }

    public String getAvailability() {
        return availability;
    }

    public AssetDescriptionPledge getDescriptionPledges() {
        return descriptionPledges;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmountOfBalance() {
        return amountOfBalance;
    }

    public Long getOverdue() {
        return overdue;
    }

    public String getTrial() {
        return trial;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDocumentStorage() {
        return documentStorage;
    }

    public String getCreditType() {
        return creditType;
    }

    public String getBalanceDate() {
        return balanceDate;
    }

    public static final class AssetLawBuilder {

        @JsonProperty("typeAsset")
        private Integer typeAsset;
        @JsonProperty("idSya")
        private String idSya;
        @JsonProperty("nameAsset")
        private String nameAsset;
        @JsonProperty("locationAsset")
        private String locationAsset;
        @JsonProperty("nameFoAsset")
        private String nameFoAsset;
        @JsonProperty("aboutAsset")
        private String aboutAsset;
        @JsonProperty("soonAsset")
        private String soonAsset;
        @JsonProperty("deposits")
        private String deposits;
        @JsonProperty("other")
        private String other;
        @JsonProperty("about")
        private String about;
        @JsonProperty("restrictionsAsset")
        private String restrictionsAsset;
        @JsonProperty("Documents_asset")
        private DocumentAsset documentsAsset;
        @JsonProperty("Images_asset")
        private ImageAsset imagesAsset;
        @JsonProperty("borrowerName")
        private String borrowerName;
        @JsonProperty("borrowerInn")
        private String borrowerInn;
        @JsonProperty("kd")
        private String kd;
        @JsonProperty("weightAverageInterestRate")
        private BigDecimal weightAverageInterestRate;
        @JsonProperty("dateKd")
        private String dateKd;
        @JsonProperty("averageRepaymentDate")
        private String averageRepaymentDate;
        @JsonProperty("debtRepaymentMethod")
        private String debtRepaymentMethod;
        @JsonProperty("availability")
        private String availability;
        @JsonProperty("descriptionPledges")
        private AssetDescriptionPledge descriptionPledges;
        @JsonProperty("currency")
        private String currency;
        @JsonProperty("amountOfBalance")
        private BigDecimal amountOfBalance;
        @JsonProperty("overdue")
        private Long overdue;
        @JsonProperty("trial")
        private String trial;
        @JsonProperty("endDate")
        private String endDate;
        @JsonProperty("documentStorage")
        private String documentStorage;
        @JsonProperty("creditType")
        private String creditType;
        @JsonProperty("balanceDate")
        private String balanceDate;

        private AssetLawBuilder() {
        }

        public AssetLawBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetLawBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetLawBuilder withBorrowerName(String borrowerName) {
            this.borrowerName = borrowerName;
            return this;
        }

        public AssetLawBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetLawBuilder withBorrowerInn(String borrowerInn) {
            this.borrowerInn = borrowerInn;
            return this;
        }

        public AssetLawBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetLawBuilder withKd(String kd) {
            this.kd = kd;
            return this;
        }

        public AssetLawBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetLawBuilder withWeightAverageInterestRate(BigDecimal weightAverageInterestRate) {
            this.weightAverageInterestRate = weightAverageInterestRate;
            return this;
        }

        public AssetLawBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetLawBuilder withDateKd(String dateKd) {
            this.dateKd = dateKd;
            return this;
        }

        public AssetLawBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetLawBuilder withAverageRepaymentDate(String averageRepaymentDate) {
            this.averageRepaymentDate = averageRepaymentDate;
            return this;
        }

        public AssetLawBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetLawBuilder withDebtRepaymentMethod(String debtRepaymentMethod) {
            this.debtRepaymentMethod = debtRepaymentMethod;
            return this;
        }

        public AssetLawBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetLawBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetLawBuilder withAvailability(String availability) {
            this.availability = availability;
            return this;
        }

        public AssetLawBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetLawBuilder withDescriptionPledges(AssetDescriptionPledge descriptionPledges) {
            this.descriptionPledges = descriptionPledges;
            return this;
        }

        public AssetLawBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetLawBuilder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public AssetLawBuilder withAmountOfBalance(BigDecimal amountOfBalance) {
            this.amountOfBalance = amountOfBalance;
            return this;
        }

        public AssetLawBuilder withOverdue(Long overdue) {
            this.overdue = overdue;
            return this;
        }

        public AssetLawBuilder withTrial(String trial) {
            this.trial = trial;
            return this;
        }

        public AssetLawBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetLawBuilder withEndDate(String endDate) {
            this.endDate = endDate;
            return this;
        }

        public AssetLawBuilder withDocumentStorage(String documentStorage) {
            this.documentStorage = documentStorage;
            return this;
        }

        public AssetLawBuilder withCreditType(String creditType) {
            this.creditType = creditType;
            return this;
        }

        public AssetLawBuilder withBalanceDate(String balanceDate) {
            this.balanceDate = balanceDate;
            return this;
        }

        public AssetLaw build() {
            return new AssetLaw(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset,
                    imagesAsset, borrowerName, borrowerInn, kd, weightAverageInterestRate, dateKd, averageRepaymentDate, debtRepaymentMethod, availability, descriptionPledges,
                    currency, amountOfBalance, overdue, trial, endDate, documentStorage, about, creditType, balanceDate);
        }
    }
}
