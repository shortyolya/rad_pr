package com.baltinfo.radius.loadauction.model.assets.extend;

import com.baltinfo.radius.loadauction.model.DocumentAsset;
import com.baltinfo.radius.loadauction.model.ImageAsset;
import com.baltinfo.radius.loadauction.model.assets.Asset;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * <p>
 * Java class для десериализация xml-файла
 * Type_asset= 15 (Облигации)
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 25.12.2019
 */

public class AssetBond extends Asset {
    @JsonProperty("typeBond")
    private final String typeBond;
    @JsonProperty("issuerName")
    private final String issuerName;
    @JsonProperty("issuerInn")
    private final String issuerInn;
    @JsonProperty("amount")
    private final BigDecimal amount;
    @JsonProperty("couponRate")
    private final String couponRate;
    @JsonProperty("paymentFrequency")
    private final String paymentFrequency;
    @JsonProperty("maturityDate")
    private final String maturityDate;
    @JsonProperty("registrationNumber")
    private final String registrationNumber;
    @JsonProperty("isin")
    private final String isin;
    @JsonProperty("nominalCost")
    private final BigDecimal nominalCost;
    @JsonProperty("currency")
    private final String currency;
    @JsonProperty("issuerLocation")
    private final String issuerLocation;
    @JsonProperty("storage")
    private final String storage;
    @JsonProperty("defaults")
    private final String defaults;
    @JsonProperty("bankrupycyOfTheIssuer")
    private final String bankruptcyOfTheIssuer;
    @JsonProperty("url")
    private final String url;
    @JsonProperty("assessmentValue")
    private final BigDecimal assessmentValue;
    @JsonProperty("assessmentDate")
    private final String assessmentDate;
    @JsonProperty("turnoverLimit")
    private final String turnoverLimit;

    private AssetBond(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset, String soonAsset, String deposits, String other,
                      String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset, String typeBond, String issuerName, String issuerInn, BigDecimal amount, String couponRate,
                      String paymentFrequency, String maturityDate, String registrationNumber, String isin, BigDecimal nominalCost, String currency, String issuerLocation,
                      String storage, String defaults, String bankruptcyOfTheIssuer, String url, BigDecimal assessmentValue, String assessmentDate, String about, String turnoverLimit) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.typeBond = typeBond;
        this.issuerName = issuerName;
        this.issuerInn = issuerInn;
        this.amount = amount;
        this.couponRate = couponRate;
        this.paymentFrequency = paymentFrequency;
        this.maturityDate = maturityDate;
        this.registrationNumber = registrationNumber;
        this.isin = isin;
        this.nominalCost = nominalCost;
        this.currency = currency;
        this.issuerLocation = issuerLocation;
        this.storage = storage;
        this.defaults = defaults;
        this.bankruptcyOfTheIssuer = bankruptcyOfTheIssuer;
        this.url = url;
        this.assessmentValue = assessmentValue;
        this.assessmentDate = assessmentDate;
        this.turnoverLimit = turnoverLimit;
    }

    public static AssetBondBuilder builder() {
        return new AssetBondBuilder();
    }

    public String getTypeBond() {
        return typeBond;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public String getIssuerInn() {
        return issuerInn;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCouponRate() {
        return couponRate;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getIsin() {
        return isin;
    }

    public BigDecimal getNominalCost() {
        return nominalCost;
    }

    public String getCurrency() {
        return currency;
    }

    public String getIssuerLocation() {
        return issuerLocation;
    }

    public String getStorage() {
        return storage;
    }

    public String getDefaults() {
        return defaults;
    }

    public String getBankruptcyOfTheIssuer() {
        return bankruptcyOfTheIssuer;
    }

    public String getUrl() {
        return url;
    }

    public BigDecimal getAssessmentValue() {
        return assessmentValue;
    }

    public String getAssessmentDate() {
        return assessmentDate;
    }

    public String getTurnoverLimit() {
        return turnoverLimit;
    }

    public static final class AssetBondBuilder {
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
        @JsonProperty("typeBond")
        private String typeBond;
        @JsonProperty("issuerName")
        private String issuerName;
        @JsonProperty("issuerInn")
        private String issuerInn;
        @JsonProperty("amount")
        private BigDecimal amount;
        @JsonProperty("couponRate")
        private String couponRate;
        @JsonProperty("paymentFrequency")
        private String paymentFrequency;
        @JsonProperty("maturityDate")
        private String maturityDate;
        @JsonProperty("registrationNumber")
        private String registrationNumber;
        @JsonProperty("isin")
        private String isin;
        @JsonProperty("nominalCost")
        private BigDecimal nominalCost;
        @JsonProperty("currency")
        private String currency;
        @JsonProperty("issuerLocation")
        private String issuerLocation;
        @JsonProperty("storage")
        private String storage;
        @JsonProperty("defaults")
        private String defaults;
        @JsonProperty("bankrupycyOfTheIssuer")
        private String bankruptcyOfTheIssuer;
        @JsonProperty("url")
        private String url;
        @JsonProperty("about")
        private String about;
        @JsonProperty("assessmentValue")
        private BigDecimal assessmentValue;
        @JsonProperty("assessmentDate")
        private String assessmentDate;
        @JsonProperty("turnoverLimit")
        private String turnoverLimit;

        private AssetBondBuilder() {
        }

        public AssetBondBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetBondBuilder withTypeBond(String typeBond) {
            this.typeBond = typeBond;
            return this;
        }

        public AssetBondBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetBondBuilder withIssuerName(String issuerName) {
            this.issuerName = issuerName;
            return this;
        }

        public AssetBondBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetBondBuilder withIssuerInn(String issuerInn) {
            this.issuerInn = issuerInn;
            return this;
        }

        public AssetBondBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetBondBuilder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public AssetBondBuilder withCouponRate(String couponRate) {
            this.couponRate = couponRate;
            return this;
        }

        public AssetBondBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetBondBuilder withPaymentFrequency(String paymentFrequency) {
            this.paymentFrequency = paymentFrequency;
            return this;
        }

        public AssetBondBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetBondBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetBondBuilder withMaturityDate(String maturityDate) {
            this.maturityDate = maturityDate;
            return this;
        }

        public AssetBondBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetBondBuilder withRegistrationNumber(String registrationNumber) {
            this.registrationNumber = registrationNumber;
            return this;
        }

        public AssetBondBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetBondBuilder withIsin(String isin) {
            this.isin = isin;
            return this;
        }

        public AssetBondBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetBondBuilder withNominalCost(BigDecimal nominalCost) {
            this.nominalCost = nominalCost;
            return this;
        }

        public AssetBondBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetBondBuilder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public AssetBondBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetBondBuilder withIssuerLocation(String issuerLocation) {
            this.issuerLocation = issuerLocation;
            return this;
        }

        public AssetBondBuilder withStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public AssetBondBuilder withDefaults(String defaults) {
            this.defaults = defaults;
            return this;
        }

        public AssetBondBuilder withBankruptcyOfTheIssuer(String bankruptcyOfTheIssuer) {
            this.bankruptcyOfTheIssuer = bankruptcyOfTheIssuer;
            return this;
        }

        public AssetBondBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public AssetBondBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetBondBuilder withAssessmentValue(BigDecimal assessmentValue) {
            this.assessmentValue = assessmentValue;
            return this;
        }

        public AssetBondBuilder withAssessmentDate(String assessmentDate) {
            this.assessmentDate = assessmentDate;
            return this;
        }

        public AssetBondBuilder withTurnoverLimit(String turnoverLimit) {
            this.turnoverLimit = turnoverLimit;
            return this;
        }

        public AssetBond build() {
            return new AssetBond(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset,
                    documentsAsset, imagesAsset, typeBond, issuerName, issuerInn, amount, couponRate, paymentFrequency, maturityDate, registrationNumber,
                    isin, nominalCost, currency, issuerLocation, storage, defaults, bankruptcyOfTheIssuer, url, assessmentValue, assessmentDate, about, turnoverLimit);
        }
    }
}
