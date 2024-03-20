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
 *     Type_ asset = 2 (Квартиры, комнаты, апартаменты)
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 23.12.2019
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public class AssetApartment extends Asset {
    @JsonProperty("typeOfObject")
    private final String typeOfObject;
    @JsonProperty("square")
    private final BigDecimal square;
    @JsonProperty("address")
    private final String address;
    @JsonProperty("cadastralNumber")
    private final String cadastralNumber;
    @JsonProperty("yearBuilt")
    private final Integer yearBuilt;
    @JsonProperty("numberOfRooms")
    private final Integer numberOfRooms;
    @JsonProperty("floorNumber")
    private final String floorNumber;
    @JsonProperty("floors")
    private final Integer floors;
    @JsonProperty("ceilingHeight")
    private final BigDecimal ceilingHeight;
    @JsonProperty("livingSpace")
    private final BigDecimal livingSpace;
    @JsonProperty("kitchenArea")
    private final BigDecimal kitchenArea;
    @JsonProperty("elevator")
    private final String elevator;
    @JsonProperty("houseType")
    private final String houseType;
    @JsonProperty("balcony")
    private final String balcony;
    @JsonProperty("viewFromTheWindow")
    private final String viewFromTheWindow;
    @JsonProperty("communications")
    private final String communications;
    @JsonProperty("assessmentValue")
    private final BigDecimal assessmentValue;
    @JsonProperty("assessmentDate")
    private final String assessmentDate;
    @JsonProperty("shareFo")
    private final BigDecimal shareFO;

    private AssetApartment(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset,
                           String soonAsset, String deposits, String other, String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset,
                           String typeOfObject, BigDecimal square, String address, String cadastralNumber, Integer yearBuilt, Integer numberOfRooms, String floorNumber,
                           Integer floors, BigDecimal ceilingHeight, BigDecimal livingSpace, BigDecimal kitchenArea, String elevator, String houseType, String balcony,
                           String viewFromTheWindow, String communications, BigDecimal assessmentValue, String assessmentDate, String about, BigDecimal shareFO) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.typeOfObject = typeOfObject;
        this.square = square;
        this.address = address;
        this.cadastralNumber = cadastralNumber;
        this.yearBuilt = yearBuilt;
        this.numberOfRooms = numberOfRooms;
        this.floorNumber = floorNumber;
        this.floors = floors;
        this.ceilingHeight = ceilingHeight;
        this.livingSpace = livingSpace;
        this.kitchenArea = kitchenArea;
        this.elevator = elevator;
        this.houseType = houseType;
        this.balcony = balcony;
        this.viewFromTheWindow = viewFromTheWindow;
        this.communications = communications;
        this.assessmentValue = assessmentValue;
        this.assessmentDate = assessmentDate;
        this.shareFO = shareFO;
    }

    public static AssetApartmentBuilder builder() {
        return new AssetApartmentBuilder();
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

    public String getCadastralNumber() {
        return cadastralNumber;
    }

    public Integer getYearBuilt() {
        return yearBuilt;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public Integer getFloors() {
        return floors;
    }

    public BigDecimal getCeilingHeight() {
        return ceilingHeight;
    }

    public BigDecimal getLivingSpace() {
        return livingSpace;
    }

    public BigDecimal getKitchenArea() {
        return kitchenArea;
    }

    public String getElevator() {
        return elevator;
    }

    public String getHouseType() {
        return houseType;
    }

    public String getBalcony() {
        return balcony;
    }

    public String getViewFromTheWindow() {
        return viewFromTheWindow;
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

    public BigDecimal getShareFO() {
        return shareFO;
    }

    public static final class AssetApartmentBuilder {
        @JsonProperty("typeAsset")
        private Integer typeAsset;
        @JsonProperty("idSya")
        private String idSya;
        @JsonProperty("about")
        private String about;
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
        @JsonProperty("cadastralNumber")
        private String cadastralNumber;
        @JsonProperty("yearBuilt")
        private Integer yearBuilt;
        @JsonProperty("numberOfRooms")
        private Integer numberOfRooms;
        @JsonProperty("floorNumber")
        private String floorNumber;
        @JsonProperty("floors")
        private Integer floors;
        @JsonProperty("ceilingHeight")
        private BigDecimal ceilingHeight;
        @JsonProperty("livingSpace")
        private BigDecimal livingSpace;
        @JsonProperty("kitchenArea")
        private BigDecimal kitchenArea;
        @JsonProperty("elevator")
        private String elevator;
        @JsonProperty("houseType")
        private String houseType;
        @JsonProperty("balcony")
        private String balcony;
        @JsonProperty("viewFromTheWindow")
        private String viewFromTheWindow;
        @JsonProperty("communications")
        private String communications;
        @JsonProperty("assessmentValue")
        private BigDecimal assessmentValue;
        @JsonProperty("assessmentDate")
        private String assessmentDate;
        @JsonProperty("shareFo")
        private BigDecimal shareFO;

        private AssetApartmentBuilder() {
        }

        public AssetApartmentBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetApartmentBuilder withTypeOfObject(String typeOfObject) {
            this.typeOfObject = typeOfObject;
            return this;
        }

        public AssetApartmentBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetApartmentBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetApartmentBuilder withSquare(BigDecimal square) {
            this.square = square;
            return this;
        }

        public AssetApartmentBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetApartmentBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public AssetApartmentBuilder withCadastralNumber(String cadastralNumber) {
            this.cadastralNumber = cadastralNumber;
            return this;
        }

        public AssetApartmentBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetApartmentBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetApartmentBuilder withYearBuilt(Integer yearBuilt) {
            this.yearBuilt = yearBuilt;
            return this;
        }

        public AssetApartmentBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetApartmentBuilder withNumberOfRooms(Integer numberOfRooms) {
            this.numberOfRooms = numberOfRooms;
            return this;
        }

        public AssetApartmentBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetApartmentBuilder withFloorNumber(String floorNumber) {
            this.floorNumber = floorNumber;
            return this;
        }

        public AssetApartmentBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetApartmentBuilder withFloors(Integer floors) {
            this.floors = floors;
            return this;
        }

        public AssetApartmentBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetApartmentBuilder withCeilingHeight(BigDecimal ceilingHeight) {
            this.ceilingHeight = ceilingHeight;
            return this;
        }

        public AssetApartmentBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetApartmentBuilder withLivingSpace(BigDecimal livingSpace) {
            this.livingSpace = livingSpace;
            return this;
        }

        public AssetApartmentBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetApartmentBuilder withKitchenArea(BigDecimal kitchenArea) {
            this.kitchenArea = kitchenArea;
            return this;
        }

        public AssetApartmentBuilder withElevator(String elevator) {
            this.elevator = elevator;
            return this;
        }

        public AssetApartmentBuilder withHouseType(String houseType) {
            this.houseType = houseType;
            return this;
        }

        public AssetApartmentBuilder withBalcony(String balcony) {
            this.balcony = balcony;
            return this;
        }

        public AssetApartmentBuilder withViewFromTheWindow(String viewFromTheWindow) {
            this.viewFromTheWindow = viewFromTheWindow;
            return this;
        }

        public AssetApartmentBuilder withCommunications(String communications) {
            this.communications = communications;
            return this;
        }

        public AssetApartmentBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetApartmentBuilder withAssessmentValue(BigDecimal assessmentValue) {
            this.assessmentValue = assessmentValue;
            return this;
        }

        public AssetApartmentBuilder withAssessmentDate(String assessmentDate) {
            this.assessmentDate = assessmentDate;
            return this;
        }

        public AssetApartmentBuilder withShareFO(BigDecimal shareFO) {
            this.shareFO = shareFO;
            return this;
        }

        public AssetApartment build() {
            return new AssetApartment(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other,
                    restrictionsAsset, documentsAsset, imagesAsset, typeOfObject, square, address, cadastralNumber, yearBuilt, numberOfRooms,
                    floorNumber, floors, ceilingHeight, livingSpace, kitchenArea, elevator, houseType, balcony, viewFromTheWindow, communications, assessmentValue, assessmentDate, about, shareFO);
        }
    }
}
