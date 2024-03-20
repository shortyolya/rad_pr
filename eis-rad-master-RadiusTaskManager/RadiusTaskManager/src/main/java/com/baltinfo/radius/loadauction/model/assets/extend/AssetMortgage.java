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
 *     Java class для десериализация xml-файла
 *     Type_asset=19 (Ипотечные сертификаты участия)
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 25.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public class AssetMortgage extends Asset {
    @JsonProperty("isuName")
    private final String isuName;
    @JsonProperty("managementCompany")
    private final String managementCompany;
    @JsonProperty("termTrustManagement")
    private final String termTrustManagement;
    @JsonProperty("amount")
    private final BigDecimal amount;
    @JsonProperty("percentage")
    private final BigDecimal partOfAllAmount;
    @JsonProperty("storage")
    private final String storage;
    @JsonProperty("mortgageCoverage")
    private final String mortgageCoverage;
    @JsonProperty("registrationNumber")
    private final String registrationNumber;
    @JsonProperty("assessmentValue")
    private final BigDecimal assessmentValue;
    @JsonProperty("assessmentDate")
    private final String assessmentDate;
    @JsonProperty("url")
    private final String url;
    @JsonProperty("turnoverLimit")
    private final String turnoverLimit;

    private AssetMortgage(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset, String soonAsset, String deposits, String other,
                          String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset, String isuName, String managementCompany, String termTrustManagement,
                          BigDecimal amount, BigDecimal partOfAllAmount, String storage, String mortgageCoverage, String registrationNumber,
                          BigDecimal assessmentValue, String assessmentDate, String url, String about, String turnoverLimit) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.isuName = isuName;
        this.managementCompany = managementCompany;
        this.termTrustManagement = termTrustManagement;
        this.amount = amount;
        this.partOfAllAmount = partOfAllAmount;
        this.storage = storage;
        this.mortgageCoverage = mortgageCoverage;
        this.registrationNumber = registrationNumber;
        this.assessmentValue = assessmentValue;
        this.assessmentDate = assessmentDate;
        this.url = url;
        this.turnoverLimit = turnoverLimit;
    }

    public static AssetMortgageBuilder builder() {
        return new AssetMortgageBuilder();
    }

    public String getIsuName() {
        return isuName;
    }

    public String getManagementCompany() {
        return managementCompany;
    }

    public String getTermTrustManagement() {
        return termTrustManagement;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getPartOfAllAmount() {
        return partOfAllAmount;
    }

    public String getStorage() {
        return storage;
    }

    public String getMortgageCoverage() {
        return mortgageCoverage;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public BigDecimal getAssessmentValue() {
        return assessmentValue;
    }

    public String getAssessmentDate() {
        return assessmentDate;
    }

    public String getUrl() {
        return url;
    }

    public String getTurnoverLimit() {
        return turnoverLimit;
    }

    public static final class AssetMortgageBuilder {
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
        @JsonProperty("isuName")
        private String isuName;
        @JsonProperty("managementCompany")
        private String managementCompany;
        @JsonProperty("termTrustManagement")
        private String termTrustManagement;
        @JsonProperty("amount")
        private BigDecimal amount;
        @JsonProperty("percentage")
        private BigDecimal partOfAllAmount;
        @JsonProperty("storage")
        private String storage;
        @JsonProperty("mortgageCoverage")
        private String mortgageCoverage;
        @JsonProperty("registrationNumber")
        private String registrationNumber;
        @JsonProperty("assessmentValue")
        private BigDecimal assessmentValue;
        @JsonProperty("assessmentDate")
        private String assessmentDate;
        @JsonProperty("url")
        private String url;
        @JsonProperty("turnoverLimit")
        private String turnoverLimit;

        private AssetMortgageBuilder() {
        }

        public AssetMortgageBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetMortgageBuilder withIsuName(String isuName) {
            this.isuName = isuName;
            return this;
        }

        public AssetMortgageBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetMortgageBuilder withManagementCompany(String managementCompany) {
            this.managementCompany = managementCompany;
            return this;
        }

        public AssetMortgageBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetMortgageBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetMortgageBuilder withTermTrustManagement(String termTrustManagement) {
            this.termTrustManagement = termTrustManagement;
            return this;
        }

        public AssetMortgageBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetMortgageBuilder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public AssetMortgageBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetMortgageBuilder withPartOfAllAmount(BigDecimal partOfAllAmount) {
            this.partOfAllAmount = partOfAllAmount;
            return this;
        }

        public AssetMortgageBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetMortgageBuilder withStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public AssetMortgageBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetMortgageBuilder withMortgageCoverage(String mortgageCoverage) {
            this.mortgageCoverage = mortgageCoverage;
            return this;
        }

        public AssetMortgageBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetMortgageBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetMortgageBuilder withRegistrationNumber(String registrationNumber) {
            this.registrationNumber = registrationNumber;
            return this;
        }

        public AssetMortgageBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetMortgageBuilder withAssessmentValue(BigDecimal assessmentValue) {
            this.assessmentValue = assessmentValue;
            return this;
        }

        public AssetMortgageBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetMortgageBuilder withAssessmentDate(String assessmentDate) {
            this.assessmentDate = assessmentDate;
            return this;
        }

        public AssetMortgageBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetMortgageBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public AssetMortgageBuilder withTurnoverLimit(String turnoverLimit) {
            this.turnoverLimit = turnoverLimit;
            return this;
        }

        public AssetMortgage build() {
            return new AssetMortgage(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset,
                    documentsAsset, imagesAsset, isuName, managementCompany, termTrustManagement, amount, partOfAllAmount, storage, mortgageCoverage,
                    registrationNumber, assessmentValue, assessmentDate, url, about, turnoverLimit);
        }
    }
}
