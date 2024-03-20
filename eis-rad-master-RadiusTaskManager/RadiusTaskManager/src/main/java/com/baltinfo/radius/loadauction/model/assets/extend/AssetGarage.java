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
 * Type_ asset = 6 (Гаражи и машиноместа)
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 25.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public class AssetGarage extends Asset {

    @JsonProperty("typeOfObject")
    private final String typeOfObject;
    @JsonProperty("square")
    private final BigDecimal square;
    @JsonProperty("address")
    private final String address;
    @JsonProperty("garageType")
    private final String garageType;
    @JsonProperty("typeOfParking")
    private final String typeOfParking;
    @JsonProperty("cadastralNumber")
    private final String cadastralNumber;
    @JsonProperty("communications")
    private final String communications;
    @JsonProperty("presenceOfSecurity")
    private final String presenceOfSecurity;
    @JsonProperty("assessmentValue")
    private final BigDecimal assessmentValue;
    @JsonProperty("assessmentDate")
    private final String assessmentDate;
    @JsonProperty("shareFo")
    private final BigDecimal shareFO;

    private AssetGarage(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset, String soonAsset, String deposits, String other,
                        String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset, String typeOfObject, BigDecimal square, String address, String garageType,
                        String typeOfParking, String cadastralNumber, String communications, String presenceOfSecurity, BigDecimal assessmentValue, String assessmentDate, String about, BigDecimal shareFO) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.typeOfObject = typeOfObject;
        this.square = square;
        this.address = address;
        this.garageType = garageType;
        this.typeOfParking = typeOfParking;
        this.cadastralNumber = cadastralNumber;
        this.communications = communications;
        this.presenceOfSecurity = presenceOfSecurity;
        this.assessmentValue = assessmentValue;
        this.assessmentDate = assessmentDate;
        this.shareFO = shareFO;
    }

    public static AssetGarageBuilder builder() {
        return new AssetGarageBuilder();
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

    public String getGarageType() {
        return garageType;
    }

    public String getTypeOfParking() {
        return typeOfParking;
    }

    public String getCadastralNumber() {
        return cadastralNumber;
    }

    public String getCommunications() {
        return communications;
    }

    public String getPresenceOfSecurity() {
        return presenceOfSecurity;
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

    public static final class AssetGarageBuilder {

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
        @JsonProperty("typeOfObject")
        private String typeOfObject;
        @JsonProperty("square")
        private BigDecimal square;
        @JsonProperty("address")
        private String address;
        @JsonProperty("about")
        private String about;
        @JsonProperty("garageType")
        private String garageType;
        @JsonProperty("typeOfParking")
        private String typeOfParking;
        @JsonProperty("cadastralNumber")
        private String cadastralNumber;
        @JsonProperty("communications")
        private String communications;
        @JsonProperty("presenceOfSecurity")
        private String presenceOfSecurity;
        @JsonProperty("assessmentValue")
        private BigDecimal assessmentValue;
        @JsonProperty("assessmentDate")
        private String assessmentDate;
        @JsonProperty("shareFo")
        private BigDecimal shareFO;

        private AssetGarageBuilder() {
        }

        public AssetGarageBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetGarageBuilder withTypeOfObject(String typeOfObject) {
            this.typeOfObject = typeOfObject;
            return this;
        }

        public AssetGarageBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetGarageBuilder withSquare(BigDecimal square) {
            this.square = square;
            return this;
        }

        public AssetGarageBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetGarageBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public AssetGarageBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetGarageBuilder withGarageType(String garageType) {
            this.garageType = garageType;
            return this;
        }

        public AssetGarageBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetGarageBuilder withTypeOfParking(String typeOfParking) {
            this.typeOfParking = typeOfParking;
            return this;
        }

        public AssetGarageBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetGarageBuilder withCadastralNumber(String cadastralNumber) {
            this.cadastralNumber = cadastralNumber;
            return this;
        }

        public AssetGarageBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetGarageBuilder withCommunications(String communications) {
            this.communications = communications;
            return this;
        }

        public AssetGarageBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetGarageBuilder withPresenceOfSecurity(String presenceOfSecurity) {
            this.presenceOfSecurity = presenceOfSecurity;
            return this;
        }

        public AssetGarageBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetGarageBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetGarageBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetGarageBuilder withAssessmentValue(BigDecimal assessmentValue) {
            this.assessmentValue = assessmentValue;
            return this;
        }

        public AssetGarageBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetGarageBuilder withAssessmentDate(String assessmentDate) {
            this.assessmentDate = assessmentDate;
            return this;
        }

        public AssetGarageBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetGarageBuilder withShareFO(BigDecimal shareFO) {
            this.shareFO = shareFO;
            return this;
        }

        public AssetGarage build() {
            return new AssetGarage(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset,
                    imagesAsset, typeOfObject, square, address, garageType, typeOfParking, cadastralNumber, communications, presenceOfSecurity,
                    assessmentValue, assessmentDate, about, shareFO);
        }
    }
}
