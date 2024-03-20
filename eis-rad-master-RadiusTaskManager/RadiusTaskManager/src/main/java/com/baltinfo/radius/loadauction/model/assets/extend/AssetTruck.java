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
 * Type_asset = 10 (Грузовой транспорт, автобусы, спецтехника)
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 23.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public class AssetTruck extends Asset {

    @JsonProperty("typeOfTransport")
    private final String typeOfTransport;
    @JsonProperty("mark")
    private final String mark;
    @JsonProperty("model")
    private final String model;
    @JsonProperty("color")
    private final String color;
    @JsonProperty("yearOfIssue")
    private final Integer yearOfIssue;
    @JsonProperty("mileage")
    private final BigDecimal mileage;
    @JsonProperty("engineCapacity")
    private final BigDecimal engineCapacity;
    @JsonProperty("transmission")
    private final String transmission;
    @JsonProperty("enginePower")
    private final BigDecimal enginePower;
    @JsonProperty("engineType")
    private final String engineType;
    @JsonProperty("typeOfDrive")
    private final String typeOfDrive;
    @JsonProperty("vin")
    private final String vin;
    @JsonProperty("steeringWheel")
    private final String steeringWheel;
    @JsonProperty("address")
    private final String address;


    private AssetTruck(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset, String soonAsset, String deposits,
                       String other, String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset, String typeOfTransport, String mark, String model, String color,
                       Integer yearOfIssue, BigDecimal mileage, BigDecimal engineCapacity, String transmission, BigDecimal enginePower, String engineType, String typeOfDrive,
                       String vin, String steeringWheel, String address, String about) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.typeOfTransport = typeOfTransport;
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.yearOfIssue = yearOfIssue;
        this.mileage = mileage;
        this.engineCapacity = engineCapacity;
        this.transmission = transmission;
        this.enginePower = enginePower;
        this.engineType = engineType;
        this.typeOfDrive = typeOfDrive;
        this.vin = vin;
        this.steeringWheel = steeringWheel;
        this.address = address;
    }

    public static AssetTruckBuilder builder() {
        return new AssetTruckBuilder();
    }

    public String getTypeOfTransport() {
        return typeOfTransport;
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public Integer getYearOfIssue() {
        return yearOfIssue;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public BigDecimal getEngineCapacity() {
        return engineCapacity;
    }

    public String getTransmission() {
        return transmission;
    }

    public BigDecimal getEnginePower() {
        return enginePower;
    }

    public String getEngineType() {
        return engineType;
    }

    public String getTypeOfDrive() {
        return typeOfDrive;
    }

    public String getVin() {
        return vin;
    }

    public String getSteeringWheel() {
        return steeringWheel;
    }

    public String getAddress() {
        return address;
    }


    public static final class AssetTruckBuilder {

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
        @JsonProperty("typeOfTransport")
        private String typeOfTransport;
        @JsonProperty("mark")
        private String mark;
        @JsonProperty("model")
        private String model;
        @JsonProperty("color")
        private String color;
        @JsonProperty("yearOfIssue")
        private Integer yearOfIssue;
        @JsonProperty("mileage")
        private BigDecimal mileage;
        @JsonProperty("engineCapacity")
        private BigDecimal engineCapacity;
        @JsonProperty("transmission")
        private String transmission;
        @JsonProperty("enginePower")
        private BigDecimal enginePower;
        @JsonProperty("engineType")
        private String engineType;
        @JsonProperty("typeOfDrive")
        private String typeOfDrive;
        @JsonProperty("vin")
        private String vin;
        @JsonProperty("steeringWheel")
        private String steeringWheel;
        @JsonProperty("address")
        private String address;

        private AssetTruckBuilder() {
        }

        public AssetTruckBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetTruckBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetTruckBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetTruckBuilder withTypeOfTransport(String typeOfTransport) {
            this.typeOfTransport = typeOfTransport;
            return this;
        }

        public AssetTruckBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetTruckBuilder withMark(String mark) {
            this.mark = mark;
            return this;
        }

        public AssetTruckBuilder withModel(String model) {
            this.model = model;
            return this;
        }

        public AssetTruckBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetTruckBuilder withColor(String color) {
            this.color = color;
            return this;
        }

        public AssetTruckBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetTruckBuilder withYearOfIssue(Integer yearOfIssue) {
            this.yearOfIssue = yearOfIssue;
            return this;
        }

        public AssetTruckBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetTruckBuilder withMileage(BigDecimal mileage) {
            this.mileage = mileage;
            return this;
        }

        public AssetTruckBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetTruckBuilder withEngineCapacity(BigDecimal engineCapacity) {
            this.engineCapacity = engineCapacity;
            return this;
        }

        public AssetTruckBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetTruckBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetTruckBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetTruckBuilder withTransmission(String transmission) {
            this.transmission = transmission;
            return this;
        }

        public AssetTruckBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetTruckBuilder withEnginePower(BigDecimal enginePower) {
            this.enginePower = enginePower;
            return this;
        }

        public AssetTruckBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetTruckBuilder withEngineType(String engineType) {
            this.engineType = engineType;
            return this;
        }

        public AssetTruckBuilder withTypeOfDrive(String typeOfDrive) {
            this.typeOfDrive = typeOfDrive;
            return this;
        }

        public AssetTruckBuilder withVin(String vin) {
            this.vin = vin;
            return this;
        }

        public AssetTruckBuilder withSteeringWheel(String steeringWheel) {
            this.steeringWheel = steeringWheel;
            return this;
        }

        public AssetTruckBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public AssetTruck build() {
            return new AssetTruck(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset,
                    documentsAsset, imagesAsset, typeOfTransport, mark, model, color, yearOfIssue, mileage, engineCapacity, transmission, enginePower,
                    engineType, typeOfDrive, vin, steeringWheel, address, about);
        }
    }
}
