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
 * Type_asset= 5 (Объекты незавершенного строительства)
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 25.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public class AssetConstruction extends Asset {

    @JsonProperty("typeOfObject")
    private final String typeOfObject;
    @JsonProperty("square")
    private final BigDecimal square;
    @JsonProperty("address")
    private final String address;
    @JsonProperty("readiness")
    private final String readiness;
    @JsonProperty("cadastralNumber")
    private final String cadastralNumber;
    @JsonProperty("assessmentValue")
    private final BigDecimal assessmentValue;
    @JsonProperty("assessmentDate")
    private final String assessmentDate;
    @JsonProperty("shareFo")
    private final BigDecimal shareFO;
    @JsonProperty("floors")
    private final Integer floors;

    private AssetConstruction(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset, String soonAsset,
                              String deposits, String other, String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset, String typeOfObject,
                              BigDecimal square, String address, String readiness, String cadastralNumber, BigDecimal assessmentValue, String assessmentDate, String about, BigDecimal shareFO, Integer floors) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.typeOfObject = typeOfObject;
        this.square = square;
        this.address = address;
        this.readiness = readiness;
        this.cadastralNumber = cadastralNumber;
        this.assessmentValue = assessmentValue;
        this.assessmentDate = assessmentDate;
        this.shareFO = shareFO;
        this.floors = floors;
    }

    public static AssetConstructionBuilder builder() {
        return new AssetConstructionBuilder();
    }

    public String getTypeOfObject() {
        return typeOfObject;
    }

    public BigDecimal getSquare() {
        return square;
    }

    public String getAddress() {
        return address;
    }

    public String getReadiness() {
        return readiness;
    }

    public String getCadastralNumber() {
        return cadastralNumber;
    }

    public BigDecimal getAssessmentValue() {
        return assessmentValue;
    }

    public String getAssessmentDate() {
        return assessmentDate;
    }

    public BigDecimal getShareFO() {
        return shareFO;
    }

    public Integer getFloors() {
        return floors;
    }

    public static final class AssetConstructionBuilder {

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
        @JsonProperty("readiness")
        private String readiness;
        @JsonProperty("cadastralNumber")
        private String cadastralNumber;
        @JsonProperty("assessmentValue")
        private BigDecimal assessmentValue;
        @JsonProperty("assessmentDate")
        private String assessmentDate;
        @JsonProperty("shareFo")
        private BigDecimal shareFO;
        @JsonProperty("floors")
        private Integer floors;

        private AssetConstructionBuilder() {
        }

        public AssetConstructionBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetConstructionBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetConstructionBuilder withTypeOfObject(String typeOfObject) {
            this.typeOfObject = typeOfObject;
            return this;
        }

        public AssetConstructionBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetConstructionBuilder withSquare(BigDecimal square) {
            this.square = square;
            return this;
        }

        public AssetConstructionBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetConstructionBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public AssetConstructionBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetConstructionBuilder withReadiness(String readiness) {
            this.readiness = readiness;
            return this;
        }

        public AssetConstructionBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetConstructionBuilder withCadastralNumber(String cadastralNumber) {
            this.cadastralNumber = cadastralNumber;
            return this;
        }

        public AssetConstructionBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetConstructionBuilder withAssessmentValue(BigDecimal assessmentValue) {
            this.assessmentValue = assessmentValue;
            return this;
        }

        public AssetConstructionBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetConstructionBuilder withAssessmentDate(String assessmentDate) {
            this.assessmentDate = assessmentDate;
            return this;
        }

        public AssetConstructionBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetConstructionBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetConstructionBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetConstructionBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetConstructionBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetConstructionBuilder withShareFO(BigDecimal shareFO) {
            this.shareFO = shareFO;
            return this;
        }

        public AssetConstructionBuilder withFloors(Integer floors) {
            this.floors = floors;
            return this;
        }

        public AssetConstruction build() {
            return new AssetConstruction(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset,
                    documentsAsset, imagesAsset, typeOfObject, square, address, readiness, cadastralNumber, assessmentValue, assessmentDate, about, shareFO, floors);
        }
    }
}
