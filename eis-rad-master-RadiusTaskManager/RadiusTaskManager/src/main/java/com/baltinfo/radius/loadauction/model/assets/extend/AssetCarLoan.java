package com.baltinfo.radius.loadauction.model.assets.extend;

import com.baltinfo.radius.loadauction.model.AssetDescriptionPledge;
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
 *     Java class для десериализация xml-файла
 *     Type_asset=21 (Автокредиты) или 22 (Ипотечные кредиты) или 23 (Прочие кредиты), 24 (Прочие права требования)
 *     или 35 (Овердрафты)
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 26.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public class AssetCarLoan extends Asset {
    @JsonProperty("creditType")
    private final String creditType;
    @JsonProperty("averageInterestRate")
    private final String averageInterestRate;
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
    @JsonProperty("kd")
    private final String kd;
    @JsonProperty("balanceDate")
    private final String balanceDate;
    @JsonProperty("dateKd")
    private final String dateKd;
    @JsonProperty("amountOfBalance")
    private final BigDecimal amountOfBalance;
    @JsonProperty("documentStorage")
    private final String documentStorage;
    @JsonProperty("overdue")
    private final Long overdue;
    @JsonProperty("trial")
    private final String trial;
    @JsonProperty("endDate")
    private final String endDate;

    private AssetCarLoan(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset, String soonAsset, String deposits, String other,
                         String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset, String creditType, String averageInterestRate, String averageRepaymentDate, String debtRepaymentMethod,
                         String availability, AssetDescriptionPledge descriptionPledges, String currency, String kd, String balanceDate, String dateKd, BigDecimal amountOfBalance,
                         String documentStorage, Long overdue, String trial, String endDate, String about) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.creditType = creditType;
        this.averageInterestRate = averageInterestRate;
        this.averageRepaymentDate = averageRepaymentDate;
        this.debtRepaymentMethod = debtRepaymentMethod;
        this.availability = availability;
        this.descriptionPledges = descriptionPledges;
        this.currency = currency;
        this.kd = kd;
        this.balanceDate = balanceDate;
        this.dateKd = dateKd;
        this.amountOfBalance = amountOfBalance;
        this.documentStorage = documentStorage;
        this.overdue = overdue;
        this.trial = trial;
        this.endDate = endDate;
    }

    public static AssetCarLoanBuilder builder() {
        return new AssetCarLoanBuilder();
    }

    public String getCreditType() {
        return creditType;
    }

    public String getAverageInterestRate() {
        return averageInterestRate;
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

    public String getKd() {
        return kd;
    }

    public String getBalanceDate() {
        return balanceDate;
    }

    public String getDateKd() {
        return dateKd;
    }

    public BigDecimal getAmountOfBalance() {
        return amountOfBalance;
    }

    public String getDocumentStorage() {
        return documentStorage;
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


    public static final class AssetCarLoanBuilder {
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
        @JsonProperty("restrictionsAsset")
        private String restrictionsAsset;
        @JsonProperty("Documents_asset")
        private DocumentAsset documentsAsset;
        @JsonProperty("Images_asset")
        private ImageAsset imagesAsset;
        @JsonProperty("creditType")
        private String creditType;
        @JsonProperty("averageInterestRate")
        private String averageInterestRate;
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
        @JsonProperty("kd")
        private String kd;
        @JsonProperty("about")
        private String about;
        @JsonProperty("balanceDate")
        private String balanceDate;
        @JsonProperty("dateKd")
        private String dateKd;
        @JsonProperty("amountOfBalance")
        private BigDecimal amountOfBalance;
        @JsonProperty("documentStorage")
        private String documentStorage;
        @JsonProperty("overdue")
        private Long overdue;
        @JsonProperty("trial")
        private String trial;
        @JsonProperty("endDate")
        private String endDate;

        private AssetCarLoanBuilder() {
        }

        public AssetCarLoanBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetCarLoanBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetCarLoanBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetCarLoanBuilder withCreditType(String creditType) {
            this.creditType = creditType;
            return this;
        }

        public AssetCarLoanBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetCarLoanBuilder withAverageInterestRate(String averageInterestRate) {
            this.averageInterestRate = averageInterestRate;
            return this;
        }

        public AssetCarLoanBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetCarLoanBuilder withAverageRepaymentDate(String averageRepaymentDate) {
            this.averageRepaymentDate = averageRepaymentDate;
            return this;
        }

        public AssetCarLoanBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetCarLoanBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetCarLoanBuilder withDebtRepaymentMethod(String debtRepaymentMethod) {
            this.debtRepaymentMethod = debtRepaymentMethod;
            return this;
        }

        public AssetCarLoanBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetCarLoanBuilder withAvailability(String availability) {
            this.availability = availability;
            return this;
        }

        public AssetCarLoanBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetCarLoanBuilder withDescriptionPledges(AssetDescriptionPledge descriptionPledges) {
            this.descriptionPledges = descriptionPledges;
            return this;
        }

        public AssetCarLoanBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetCarLoanBuilder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public AssetCarLoanBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetCarLoanBuilder withKd(String kd) {
            this.kd = kd;
            return this;
        }

        public AssetCarLoanBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetCarLoanBuilder withBalanceDate(String balanceDate) {
            this.balanceDate = balanceDate;
            return this;
        }

        public AssetCarLoanBuilder withDateKd(String dateKd) {
            this.dateKd = dateKd;
            return this;
        }

        public AssetCarLoanBuilder withAmountOfBalance(BigDecimal amountOfBalance) {
            this.amountOfBalance = amountOfBalance;
            return this;
        }

        public AssetCarLoanBuilder withDocumentStorage(String documentStorage) {
            this.documentStorage = documentStorage;
            return this;
        }

        public AssetCarLoanBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetCarLoanBuilder withOverdue(Long overdue) {
            this.overdue = overdue;
            return this;
        }

        public AssetCarLoanBuilder withTrial(String trial) {
            this.trial = trial;
            return this;
        }

        public AssetCarLoanBuilder withEndDate(String endDate) {
            this.endDate = endDate;
            return this;
        }

        public AssetCarLoan build() {
            return new AssetCarLoan(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset,
                    creditType, averageInterestRate, averageRepaymentDate, debtRepaymentMethod, availability, descriptionPledges, currency, kd,
                    balanceDate, dateKd, amountOfBalance, documentStorage, overdue, trial, endDate, about);
        }
    }
}
