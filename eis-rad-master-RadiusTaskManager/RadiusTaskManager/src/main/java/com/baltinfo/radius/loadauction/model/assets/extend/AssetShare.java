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
 * Type_asset=17 (Доли ООО)
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 25.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public class AssetShare extends Asset {

    @JsonProperty("associationName")
    private final String associationName;
    @JsonProperty("associationInn")
    private final String associationInn;
    @JsonProperty("share")
    private final BigDecimal share;
    @JsonProperty("realActivity")
    private final String realActivity;
    @JsonProperty("nominalCost")
    private final BigDecimal nominalCost;
    @JsonProperty("associationLocation")
    private final String associationLocation;
    @JsonProperty("url")
    private final String url;
    @JsonProperty("assessmentValue")
    private final BigDecimal assessmentValue;
    @JsonProperty("assessmentDate")
    private final String assessmentDate;
    @JsonProperty("turnoverLimit")
    private final String turnoverLimit;

    private AssetShare(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset, String soonAsset, String deposits, String other,
                       String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset, String associationName, String associationInn, BigDecimal share, String realActivity,
                       BigDecimal nominalCost, String associationLocation, String url, BigDecimal assessmentValue, String assessmentDate, String about, String turnoverLimit) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.associationName = associationName;
        this.associationInn = associationInn;
        this.share = share;
        this.realActivity = realActivity;
        this.nominalCost = nominalCost;
        this.associationLocation = associationLocation;
        this.url = url;
        this.assessmentValue = assessmentValue;
        this.assessmentDate = assessmentDate;
        this.turnoverLimit = turnoverLimit;
    }

    public static AssetShareBuilder builder() {
        return new AssetShareBuilder();
    }

    public String getAssociationName() {
        return associationName;
    }

    public String getAssociationInn() {
        return associationInn;
    }

    public BigDecimal getShare() {
        return share;
    }

    public String getRealActivity() {
        return realActivity;
    }

    public BigDecimal getNominalCost() {
        return nominalCost;
    }

    public String getAssociationLocation() {
        return associationLocation;
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

    public static final class AssetShareBuilder {

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
        @JsonProperty("associationName")
        private String associationName;
        @JsonProperty("associationInn")
        private String associationInn;
        @JsonProperty("share")
        private BigDecimal share;
        @JsonProperty("realActivity")
        private String realActivity;
        @JsonProperty("nominalCost")
        private BigDecimal nominalCost;
        @JsonProperty("associationLocation")
        private String associationLocation;
        @JsonProperty("url")
        private String url;
        @JsonProperty("assessmentValue")
        private BigDecimal assessmentValue;
        @JsonProperty("assessmentDate")
        private String assessmentDate;
        @JsonProperty("turnoverLimit")
        private String turnoverLimit;

        private AssetShareBuilder() {
        }

        public AssetShareBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetShareBuilder withAssociationName(String associationName) {
            this.associationName = associationName;
            return this;
        }

        public AssetShareBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetShareBuilder withAssociationInn(String associationInn) {
            this.associationInn = associationInn;
            return this;
        }

        public AssetShareBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetShareBuilder withShare(BigDecimal share) {
            this.share = share;
            return this;
        }

        public AssetShareBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetShareBuilder withRealActivity(String realActivity) {
            this.realActivity = realActivity;
            return this;
        }

        public AssetShareBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetShareBuilder withNominalCost(BigDecimal nominalCost) {
            this.nominalCost = nominalCost;
            return this;
        }

        public AssetShareBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetShareBuilder withAssociationLocation(String associationLocation) {
            this.associationLocation = associationLocation;
            return this;
        }

        public AssetShareBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetShareBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public AssetShareBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetShareBuilder withAssessmentValue(BigDecimal assessmentValue) {
            this.assessmentValue = assessmentValue;
            return this;
        }

        public AssetShareBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetShareBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetShareBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetShareBuilder withAssessmentDate(String assessmentDate) {
            this.assessmentDate = assessmentDate;
            return this;
        }

        public AssetShareBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetShareBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetShareBuilder withTurnoverLimit(String turnoverLimit) {
            this.turnoverLimit = turnoverLimit;
            return this;
        }

        public AssetShare build() {
            return new AssetShare(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset,
                    documentsAsset, imagesAsset, associationName, associationInn, share, realActivity, nominalCost, associationLocation,
                    url, assessmentValue, assessmentDate, about, turnoverLimit);
        }
    }
}
