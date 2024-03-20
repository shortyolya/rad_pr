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
 * Type_ asset = 4 (Коммерческая недвижимость)
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 23.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public class AssetCommercialProperty extends Asset {

    @JsonProperty("typeOfObject")
    private final String typeOfObject;
    @JsonProperty("square")
    private final BigDecimal square;
    @JsonProperty("address")
    private final String address;
    @JsonProperty("floorNumber")
    private final String floorNumber;
    @JsonProperty("floors")
    private final Integer floors;
    @JsonProperty("cadastralNumber")
    private final String cadastralNumber;
    @JsonProperty("communications")
    private final String communications;
    @JsonProperty("categor")
    private final String category;
    @JsonProperty("assessmentValue")
    private final BigDecimal assessmentValue;
    @JsonProperty("assessmentDate")
    private final String assessmentDate;
    @JsonProperty("shareFo")
    private final BigDecimal shareFO;
    @JsonProperty("yearBuilt")
    private final Integer yearBuilt;

    private AssetCommercialProperty(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset, String soonAsset, String deposits, String other,
                                    String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset, String typeOfObject, BigDecimal square, String address, String floorNumber,
                                    Integer floors, String cadastralNumber, String communications, String category, BigDecimal assessmentValue, String assessmentDate, String about, BigDecimal shareFO, Integer yearBuilt) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.typeOfObject = typeOfObject;
        this.square = square;
        this.address = address;
        this.floorNumber = floorNumber;
        this.floors = floors;
        this.cadastralNumber = cadastralNumber;
        this.communications = communications;
        this.category = category;
        this.assessmentValue = assessmentValue;
        this.assessmentDate = assessmentDate;
        this.shareFO = shareFO;
        this.yearBuilt = yearBuilt;
    }

    public static AssetCommercialPropertyBuilder builder() {
        return new AssetCommercialPropertyBuilder();
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

    public String getFloorNumber() {
        return floorNumber;
    }

    public Integer getFloors() {
        return floors;
    }

    public String getCadastralNumber() {
        return cadastralNumber;
    }

    public String getCommunications() {
        return communications;
    }

    public String getCategory() {
        return category;
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

    public Integer getYearBuilt() {
        return yearBuilt;
    }

    public static final class AssetCommercialPropertyBuilder {

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
        @JsonProperty("floorNumber")
        private String floorNumber;
        @JsonProperty("floors")
        private Integer floors;
        @JsonProperty("cadastralNumber")
        private String cadastralNumber;
        @JsonProperty("communications")
        private String communications;
        @JsonProperty("categor")
        private String category;
        @JsonProperty("about")
        private String about;
        @JsonProperty("assessmentValue")
        private BigDecimal assessmentValue;
        @JsonProperty("assessmentDate")
        private String assessmentDate;
        @JsonProperty("shareFo")
        private BigDecimal shareFO;
        @JsonProperty("yearBuilt")
        private Integer yearBuilt;

        private AssetCommercialPropertyBuilder() {
        }

        public AssetCommercialPropertyBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetCommercialPropertyBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetCommercialPropertyBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetCommercialPropertyBuilder withTypeOfObject(String typeOfObject) {
            this.typeOfObject = typeOfObject;
            return this;
        }

        public AssetCommercialPropertyBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetCommercialPropertyBuilder withSquare(BigDecimal square) {
            this.square = square;
            return this;
        }

        public AssetCommercialPropertyBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetCommercialPropertyBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public AssetCommercialPropertyBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetCommercialPropertyBuilder withFloorNumber(String floorNumber) {
            this.floorNumber = floorNumber;
            return this;
        }

        public AssetCommercialPropertyBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetCommercialPropertyBuilder withFloors(Integer floors) {
            this.floors = floors;
            return this;
        }

        public AssetCommercialPropertyBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetCommercialPropertyBuilder withCadastralNumber(String cadastralNumber) {
            this.cadastralNumber = cadastralNumber;
            return this;
        }

        public AssetCommercialPropertyBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetCommercialPropertyBuilder withCommunications(String communications) {
            this.communications = communications;
            return this;
        }

        public AssetCommercialPropertyBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetCommercialPropertyBuilder withCategory(String category) {
            this.category = category;
            return this;
        }

        public AssetCommercialPropertyBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetCommercialPropertyBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetCommercialPropertyBuilder withAssessmentValue(BigDecimal assessmentValue) {
            this.assessmentValue = assessmentValue;
            return this;
        }

        public AssetCommercialPropertyBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetCommercialPropertyBuilder withAssessmentDate(String assessmentDate) {
            this.assessmentDate = assessmentDate;
            return this;
        }

        public AssetCommercialPropertyBuilder withShareFO(BigDecimal shareFO) {
            this.shareFO = shareFO;
            return this;
        }

        public AssetCommercialPropertyBuilder withYearBuilt(Integer yearBuilt) {
            this.yearBuilt = yearBuilt;
            return this;
        }

        public AssetCommercialProperty build() {
            return new AssetCommercialProperty(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset,
                    typeOfObject, square, address, floorNumber, floors, cadastralNumber, communications, category, assessmentValue, assessmentDate, about, shareFO, yearBuilt);
        }
    }
}
