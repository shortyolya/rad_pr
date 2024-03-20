package com.baltinfo.radius.loadauction.model.assets.extend;

import com.baltinfo.radius.loadauction.model.DocumentAsset;
import com.baltinfo.radius.loadauction.model.ImageAsset;
import com.baltinfo.radius.loadauction.model.assets.Asset;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;

/**
 * <p>
 * Java class для десериализация xml-файла
 * Type_asset = 14 (Акции)
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 25.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public class AssetStock extends Asset {

    @JsonProperty("view")
    private final String view;
    @JsonProperty("issuerName")
    private final String issuerName;
    @JsonProperty("issuerInn")
    private final String issuerInn;
    @JsonProperty("amount")
    private final BigDecimal amount;
    @JsonProperty("share")
    private final BigDecimal share;
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
    @JsonProperty("url")
    private final String url;
    @JsonProperty("assessmentValue")
    private final BigDecimal assessmentValue;
    @JsonProperty("assessmentDate")
    private final String assessmentDate;
    @JsonProperty("turnoverLimit")
    private final String turnOverLimit;

    private AssetStock(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset, String soonAsset, String deposits, String other,
                       String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset, String view, String issuerName, String issuerInn, BigDecimal amount, BigDecimal share,
                       String registrationNumber, String isin, BigDecimal nominalCost, String currency, String issuerLocation, String storage, String url, BigDecimal assessmentValue,
                       String assessmentDate, String turnOverLimit, String about) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.view = view;
        this.issuerName = issuerName;
        this.issuerInn = issuerInn;
        this.amount = amount;
        this.share = share;
        this.registrationNumber = registrationNumber;
        this.isin = isin;
        this.nominalCost = nominalCost;
        this.currency = currency;
        this.issuerLocation = issuerLocation;
        this.storage = storage;
        this.url = url;
        this.assessmentValue = assessmentValue;
        this.assessmentDate = assessmentDate;
        this.turnOverLimit = turnOverLimit;
    }

    public static AssetStockBuilder builder() {
        return new AssetStockBuilder();
    }

    public String getView() {
        return view;
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

    public BigDecimal getShare() {
        return share;
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

    public String getUrl() {
        return url;
    }

    public BigDecimal getAssessmentValue() {
        return assessmentValue;
    }

    public String getAssessmentDate() {
        return assessmentDate;
    }

    public String getTurnOverLimit() {
        return turnOverLimit;
    }


    public static final class AssetStockBuilder {

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
        @JsonProperty("view")
        private String view;
        @JsonProperty("issuerName")
        private String issuerName;
        @JsonProperty("issuerInn")
        private String issuerInn;
        @JsonProperty("amount")
        private BigDecimal amount;
        @JsonProperty("share")
        private BigDecimal share;
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
        @JsonProperty("url")
        private String url;
        @JsonProperty("assessmentValue")
        private BigDecimal assessmentValue;
        @JsonProperty("assessmentDate")
        private String assessmentDate;
        @JsonProperty("turnoverLimit")
        private String turnOverLimit;

        private AssetStockBuilder() {
        }

        public AssetStockBuilder withView(String view) {
            this.view = view;
            return this;
        }

        public AssetStockBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetStockBuilder withIssuerName(String issuerName) {
            this.issuerName = issuerName;
            return this;
        }

        public AssetStockBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetStockBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetStockBuilder withIssuerInn(String issuerInn) {
            this.issuerInn = issuerInn;
            return this;
        }

        public AssetStockBuilder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public AssetStockBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetStockBuilder withShare(BigDecimal share) {
            this.share = share;
            return this;
        }

        public AssetStockBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetStockBuilder withRegistrationNumber(String registrationNumber) {
            this.registrationNumber = registrationNumber;
            return this;
        }

        public AssetStockBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetStockBuilder withIsin(String isin) {
            this.isin = isin;
            return this;
        }

        public AssetStockBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetStockBuilder withNominalCost(BigDecimal nominalCost) {
            this.nominalCost = nominalCost;
            return this;
        }

        public AssetStockBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetStockBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetStockBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetStockBuilder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public AssetStockBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetStockBuilder withIssuerLocation(String issuerLocation) {
            this.issuerLocation = issuerLocation;
            return this;
        }

        public AssetStockBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetStockBuilder withStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public AssetStockBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public AssetStockBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetStockBuilder withAssessmentValue(BigDecimal assessmentValue) {
            this.assessmentValue = assessmentValue;
            return this;
        }

        public AssetStockBuilder withAssessmentDate(String assessmentDate) {
            this.assessmentDate = assessmentDate;
            return this;
        }

        public AssetStockBuilder withTurnOverLimit(String turnOverLimit) {
            this.turnOverLimit = turnOverLimit;
            return this;
        }

        public AssetStock build() {
            return new AssetStock(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset,
                    documentsAsset, imagesAsset, view, issuerName, issuerInn, amount, share, registrationNumber, isin, nominalCost, currency,
                    issuerLocation, storage, url, assessmentValue, assessmentDate, turnOverLimit, about);
        }
    }
}
