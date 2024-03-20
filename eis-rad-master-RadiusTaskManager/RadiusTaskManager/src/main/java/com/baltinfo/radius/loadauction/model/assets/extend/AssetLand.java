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
 * Type_asset= 1 (Земельные участки)
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 25.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public class AssetLand extends Asset {

    @JsonProperty("typeOfObject")
    private final String typeOfObject;
    @JsonProperty("square")
    private final BigDecimal square;
    @JsonProperty("address")
    private final String address;
    @JsonProperty("landCategory")
    private final String landCategory;
    @JsonProperty("permittedUse")
    private final String permittedUse;
    @JsonProperty("distanceToTheRegionalCenter")
    private final BigDecimal distanceToCenter;
    @JsonProperty("cadastralNumber")
    private final String cadastralNumber;
    @JsonProperty("communications")
    private final String communications;
    @JsonProperty("assessmentValue")
    private final BigDecimal assessmentValue;
    @JsonProperty("assessmentDate")
    private final String assessmentDate;
    @JsonProperty("shareFo")
    private final BigDecimal shareFO;

    private AssetLand(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset, String soonAsset, String deposits, String other,
                      String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset, String typeOfObject, BigDecimal square, String address, String landCategory,
                      String permittedUse, BigDecimal distanceToCenter, String cadastralNumber, String communications, BigDecimal assessmentValue, String assessmentDate, String about, BigDecimal shareFO) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.typeOfObject = typeOfObject;
        this.square = square;
        this.address = address;
        this.landCategory = landCategory;
        this.permittedUse = permittedUse;
        this.distanceToCenter = distanceToCenter;
        this.cadastralNumber = cadastralNumber;
        this.communications = communications;
        this.assessmentValue = assessmentValue;
        this.assessmentDate = assessmentDate;
        this.shareFO = shareFO;
    }

    public static AssetLandBuilder builder() {
        return new AssetLandBuilder();
    }

    public String getTypeOfObject() {
        return typeOfObject;
    }

    public BigDecimal getSquare() {
        return square;
    }

    public String getLandCategory() {
        return landCategory;
    }

    public String getPermittedUse() {
        return permittedUse;
    }

    public BigDecimal getDistanceToCenter() {
        return distanceToCenter;
    }

    public String getCadastralNumber() {
        return cadastralNumber;
    }

    public String getCommunications() {
        return communications;
    }

    public BigDecimal getAssessmentValue() {
        return assessmentValue;
    }

    public String getAssessmentDate() {
        return assessmentDate;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getShareFO() {
        return shareFO;
    }

    public static final class AssetLandBuilder {

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
        @JsonProperty("typeOfObject")
        private String typeOfObject;
        @JsonProperty("square")
        private BigDecimal square;
        @JsonProperty("address")
        private String address;
        @JsonProperty("landCategory")
        private String landCategory;
        @JsonProperty("permittedUse")
        private String permittedUse;
        @JsonProperty("distanceToTheRegionalCenter")
        private BigDecimal distanceToCenter;
        @JsonProperty("cadastralNumber")
        private String cadastralNumber;
        @JsonProperty("communications")
        private String communications;
        @JsonProperty("assessmentValue")
        private BigDecimal assessmentValue;
        @JsonProperty("assessmentDate")
        private String assessmentDate;
        @JsonProperty("shareFo")
        private BigDecimal shareFO;

        private AssetLandBuilder() {
        }

        public AssetLandBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetLandBuilder withTypeOfObject(String typeOfObject) {
            this.typeOfObject = typeOfObject;
            return this;
        }

        public AssetLandBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetLandBuilder withSquare(BigDecimal square) {
            this.square = square;
            return this;
        }

        public AssetLandBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetLandBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public AssetLandBuilder withLandCategory(String landCategory) {
            this.landCategory = landCategory;
            return this;
        }

        public AssetLandBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetLandBuilder withPermittedUse(String permittedUse) {
            this.permittedUse = permittedUse;
            return this;
        }

        public AssetLandBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetLandBuilder withDistanceToCenter(BigDecimal distanceToCenter) {
            this.distanceToCenter = distanceToCenter;
            return this;
        }

        public AssetLandBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetLandBuilder withCadastralNumber(String cadastralNumber) {
            this.cadastralNumber = cadastralNumber;
            return this;
        }

        public AssetLandBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetLandBuilder withCommunications(String communications) {
            this.communications = communications;
            return this;
        }

        public AssetLandBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetLandBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetLandBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetLandBuilder withAssessmentValue(BigDecimal assessmentValue) {
            this.assessmentValue = assessmentValue;
            return this;
        }

        public AssetLandBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetLandBuilder withAssessmentDate(String assessmentDate) {
            this.assessmentDate = assessmentDate;
            return this;
        }

        public AssetLandBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetLandBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetLandBuilder withShareFO(BigDecimal shareFO) {
            this.shareFO = shareFO;
            return this;
        }

        public AssetLand build() {
            return new AssetLand(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset,
                    imagesAsset, typeOfObject, square, landCategory, address, permittedUse, distanceToCenter, cadastralNumber, communications, assessmentValue, assessmentDate, about, shareFO);
        }
    }
}
