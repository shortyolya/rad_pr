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
 * Type_asset =3 (Жилые дома (коттеджи, дачи, таунхаусы))
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 23.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public class AssetHouse extends Asset {

    @JsonProperty("typeOfObject")
    private final String typeOfObject;
    @JsonProperty("square")
    private final BigDecimal square;
    @JsonProperty("numberOfFloors")
    private final Integer numberOfFloors;
    @JsonProperty("moreAboutNumberOfFloors")
    private final String moreAboutNumberOfFloors;
    @JsonProperty("cadastralNumber")
    private final String cadastralNumber;
    @JsonProperty("yearBuilt")
    private final Integer yearBuilt;
    @JsonProperty("wallMaterial")
    private final String wallMaterial;
    @JsonProperty("roofingMaterial")
    private final String roofingMaterial;
    @JsonProperty("transportAccessibility")
    private final String transportAccessibility;
    @JsonProperty("foundation")
    private final String foundation;
    @JsonProperty("communications")
    private final String communications;
    @JsonProperty("assessmentValue")
    private final BigDecimal assessmentValue;
    @JsonProperty("assessmentDate")
    private final String assessmentDate;
    @JsonProperty("address")
    private final String address;
    @JsonProperty("shareFo")
    private final BigDecimal shareFO;

    private AssetHouse(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset, String soonAsset, String deposits, String other,
                       String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset, String typeOfObject, BigDecimal square, Integer numberOfFloors, String moreAboutNumberOfFloors,
                       String cadastralNumber, Integer yearBuilt, String wallMaterial, String roofingMaterial, String transportAccessibility, String foundation,
                       String communications, BigDecimal assessmentValue, String assessmentDate, String address, String about, BigDecimal shareFO) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.typeOfObject = typeOfObject;
        this.square = square;
        this.numberOfFloors = numberOfFloors;
        this.moreAboutNumberOfFloors = moreAboutNumberOfFloors;
        this.cadastralNumber = cadastralNumber;
        this.yearBuilt = yearBuilt;
        this.wallMaterial = wallMaterial;
        this.roofingMaterial = roofingMaterial;
        this.transportAccessibility = transportAccessibility;
        this.foundation = foundation;
        this.communications = communications;
        this.assessmentValue = assessmentValue;
        this.assessmentDate = assessmentDate;
        this.address = address;
        this.shareFO = shareFO;
    }

    public static AssetHouseBuilder builder() {
        return new AssetHouseBuilder();
    }

    public String getTypeOfObject() {
        return typeOfObject;
    }

    public BigDecimal getSquare() {
        return square;
    }

    public Integer getNumberOfFloors() {
        return numberOfFloors;
    }

    public String getMoreAboutNumberOfFloors() {
        return moreAboutNumberOfFloors;
    }

    public String getCadastralNumber() {
        return cadastralNumber;
    }

    public Integer getYearBuilt() {
        return yearBuilt;
    }

    public String getWallMaterial() {
        return wallMaterial;
    }

    public String getRoofingMaterial() {
        return roofingMaterial;
    }

    public String getTransportAccessibility() {
        return transportAccessibility;
    }

    public String getFoundation() {
        return foundation;
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

    public static final class AssetHouseBuilder {

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
        @JsonProperty("numberOfFloors")
        private Integer numberOfFloors;
        @JsonProperty("moreAboutNumberOfFloors")
        private String moreAboutNumberOfFloors;
        @JsonProperty("cadastralNumber")
        private String cadastralNumber;
        @JsonProperty("yearBuilt")
        private Integer yearBuilt;
        @JsonProperty("wallMaterial")
        private String wallMaterial;
        @JsonProperty("roofingMaterial")
        private String roofingMaterial;
        @JsonProperty("transportAccessibility")
        private String transportAccessibility;
        @JsonProperty("foundation")
        private String foundation;
        @JsonProperty("communications")
        private String communications;
        @JsonProperty("assessmentValue")
        private BigDecimal assessmentValue;
        @JsonProperty("assessmentDate")
        private String assessmentDate;
        @JsonProperty("address")
        private String address;
        @JsonProperty("shareFo")
        private BigDecimal shareFO;

        private AssetHouseBuilder() {
        }

        public AssetHouseBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetHouseBuilder withTypeOfObject(String typeOfObject) {
            this.typeOfObject = typeOfObject;
            return this;
        }

        public AssetHouseBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetHouseBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetHouseBuilder withSquare(BigDecimal square) {
            this.square = square;
            return this;
        }

        public AssetHouseBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetHouseBuilder withNumberOfFloors(Integer numberOfFloors) {
            this.numberOfFloors = numberOfFloors;
            return this;
        }

        public AssetHouseBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetHouseBuilder withMoreAboutNumberOfFloors(String moreAboutNumberOfFloors) {
            this.moreAboutNumberOfFloors = moreAboutNumberOfFloors;
            return this;
        }

        public AssetHouseBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetHouseBuilder withCadastralNumber(String cadastralNumber) {
            this.cadastralNumber = cadastralNumber;
            return this;
        }

        public AssetHouseBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetHouseBuilder withYearBuilt(Integer yearBuilt) {
            this.yearBuilt = yearBuilt;
            return this;
        }

        public AssetHouseBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetHouseBuilder withWallMaterial(String wallMaterial) {
            this.wallMaterial = wallMaterial;
            return this;
        }

        public AssetHouseBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetHouseBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetHouseBuilder withRoofingMaterial(String roofingMaterial) {
            this.roofingMaterial = roofingMaterial;
            return this;
        }

        public AssetHouseBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetHouseBuilder withTransportAccessibility(String transportAccessibility) {
            this.transportAccessibility = transportAccessibility;
            return this;
        }

        public AssetHouseBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetHouseBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetHouseBuilder withFoundation(String foundation) {
            this.foundation = foundation;
            return this;
        }

        public AssetHouseBuilder withCommunications(String communications) {
            this.communications = communications;
            return this;
        }

        public AssetHouseBuilder withAssessmentValue(BigDecimal assessmentValue) {
            this.assessmentValue = assessmentValue;
            return this;
        }

        public AssetHouseBuilder withAssessmentDate(String assessmentDate) {
            this.assessmentDate = assessmentDate;
            return this;
        }

        public AssetHouseBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public AssetHouseBuilder withShareFO(BigDecimal shareFO) {
            this.shareFO = shareFO;
            return this;
        }

        public AssetHouse build() {
            return new AssetHouse(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset,
                    imagesAsset, typeOfObject, square, numberOfFloors, moreAboutNumberOfFloors, cadastralNumber, yearBuilt, wallMaterial, roofingMaterial,
                    transportAccessibility, foundation, communications, assessmentValue, assessmentDate, address, about, shareFO);
        }
    }
}
