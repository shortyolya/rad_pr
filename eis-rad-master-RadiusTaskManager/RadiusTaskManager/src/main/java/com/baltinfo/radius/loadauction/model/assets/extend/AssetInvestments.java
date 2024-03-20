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
 * Type_asset=18 (Инвестиционные паи)
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 25.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public class AssetInvestments extends Asset {

    @JsonProperty("fundName")
    private final String fundName;
    @JsonProperty("fundCategory")
    private final String fundCategory;
    @JsonProperty("fundType")
    private final String fundType;
    @JsonProperty("registrationNumber")
    private final String registrationNumber;
    @JsonProperty("termTrustManagement")
    private final String termTrustManagement;
    @JsonProperty("numberOfShares")
    private final Long numberOfShares;
    @JsonProperty("sharesPercentage")
    private final BigDecimal partOfAllShares;
    @JsonProperty("storageOfShares")
    private final String storageOfShare;
    @JsonProperty("managementCompanyName")
    private final String managementCompanyName;
    @JsonProperty("intervals")
    private final String intervals;
    @JsonProperty("mainAssetsOfTheFund")
    private final String mainAssets;
    @JsonProperty("assessmentValue")
    private final BigDecimal assessmentValue;
    @JsonProperty("assessmentDate")
    private final String assessmentDate;
    @JsonProperty("url")
    private final String url;
    @JsonProperty("turnoverLimit")
    private final String turnoverLimit;

    private AssetInvestments(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset, String soonAsset, String deposits, String other,
                             String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset, String fundName, String fundCategory, String fundType, String registrationNumber,
                             String termTrustManagement, Long numberOfShares, BigDecimal partOfAllShares, String storageOfShare, String managementCompanyName, String intervals, String mainAssets,
                             BigDecimal assessmentValue, String assessmentDate, String url, String about, String turnoverLimit) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.fundName = fundName;
        this.fundCategory = fundCategory;
        this.fundType = fundType;
        this.registrationNumber = registrationNumber;
        this.termTrustManagement = termTrustManagement;
        this.numberOfShares = numberOfShares;
        this.partOfAllShares = partOfAllShares;
        this.storageOfShare = storageOfShare;
        this.managementCompanyName = managementCompanyName;
        this.intervals = intervals;
        this.mainAssets = mainAssets;
        this.assessmentValue = assessmentValue;
        this.assessmentDate = assessmentDate;
        this.url = url;
        this.turnoverLimit = turnoverLimit;
    }

    public static AssetInvestmentsBuilder builder() {
        return new AssetInvestmentsBuilder();
    }

    public String getFundName() {
        return fundName;
    }

    public String getFundCategory() {
        return fundCategory;
    }

    public String getFundType() {
        return fundType;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getTermTrustManagement() {
        return termTrustManagement;
    }

    public Long getNumberOfShares() {
        return numberOfShares;
    }

    public BigDecimal getPartOfAllShares() {
        return partOfAllShares;
    }

    public String getStorageOfShare() {
        return storageOfShare;
    }

    public String getManagementCompanyName() {
        return managementCompanyName;
    }

    public String getIntervals() {
        return intervals;
    }

    public String getMainAssets() {
        return mainAssets;
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

    public static final class AssetInvestmentsBuilder {

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
        @JsonProperty("fundName")
        private String fundName;
        @JsonProperty("fundCategory")
        private String fundCategory;
        @JsonProperty("fundType")
        private String fundType;
        @JsonProperty("registrationNumber")
        private String registrationNumber;
        @JsonProperty("termTrustManagement")
        private String termTrustManagement;
        @JsonProperty("numberOfShares")
        private Long numberOfShares;
        @JsonProperty("sharesPercentage")
        private BigDecimal partOfAllShares;
        @JsonProperty("storageOfShares")
        private String storageOfShare;
        @JsonProperty("managementCompanyName")
        private String managementCompanyName;
        @JsonProperty("intervals")
        private String intervals;
        @JsonProperty("mainAssetsOfTheFund")
        private String mainAssets;
        @JsonProperty("assessmentValue")
        private BigDecimal assessmentValue;
        @JsonProperty("assessmentDate")
        private String assessmentDate;
        @JsonProperty("url")
        private String url;
        @JsonProperty("turnoverLimit")
        private String turnoverLimit;

        private AssetInvestmentsBuilder() {
        }

        public AssetInvestmentsBuilder withFundName(String fundName) {
            this.fundName = fundName;
            return this;
        }

        public AssetInvestmentsBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetInvestmentsBuilder withFundCategory(String fundCategory) {
            this.fundCategory = fundCategory;
            return this;
        }

        public AssetInvestmentsBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetInvestmentsBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetInvestmentsBuilder withFundType(String fundType) {
            this.fundType = fundType;
            return this;
        }

        public AssetInvestmentsBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetInvestmentsBuilder withRegistrationNumber(String registrationNumber) {
            this.registrationNumber = registrationNumber;
            return this;
        }

        public AssetInvestmentsBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetInvestmentsBuilder withTermTrustManagement(String termTrustManagement) {
            this.termTrustManagement = termTrustManagement;
            return this;
        }

        public AssetInvestmentsBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetInvestmentsBuilder withNumberOfShares(Long numberOfShares) {
            this.numberOfShares = numberOfShares;
            return this;
        }

        public AssetInvestmentsBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetInvestmentsBuilder withPartOfAllShares(BigDecimal partOfAllShares) {
            this.partOfAllShares = partOfAllShares;
            return this;
        }

        public AssetInvestmentsBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetInvestmentsBuilder withStorageOfShare(String storageOfShare) {
            this.storageOfShare = storageOfShare;
            return this;
        }

        public AssetInvestmentsBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetInvestmentsBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetInvestmentsBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetInvestmentsBuilder withManagementCompanyName(String managementCompanyName) {
            this.managementCompanyName = managementCompanyName;
            return this;
        }

        public AssetInvestmentsBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetInvestmentsBuilder withIntervals(String intervals) {
            this.intervals = intervals;
            return this;
        }

        public AssetInvestmentsBuilder withMainAssets(String mainAssets) {
            this.mainAssets = mainAssets;
            return this;
        }

        public AssetInvestmentsBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetInvestmentsBuilder withAssessmentValue(BigDecimal assessmentValue) {
            this.assessmentValue = assessmentValue;
            return this;
        }

        public AssetInvestmentsBuilder withAssessmentDate(String assessmentDate) {
            this.assessmentDate = assessmentDate;
            return this;
        }

        public AssetInvestmentsBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public AssetInvestmentsBuilder withTurnoverLimit(String turnoverLimit) {
            this.turnoverLimit = turnoverLimit;
            return this;
        }

        public AssetInvestments build() {
            return new AssetInvestments(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset,
                    documentsAsset, imagesAsset, fundName, fundCategory, fundType, registrationNumber, termTrustManagement, numberOfShares, partOfAllShares,
                    storageOfShare, managementCompanyName, intervals, mainAssets, assessmentValue, assessmentDate, url, about, turnoverLimit);
        }
    }
}
