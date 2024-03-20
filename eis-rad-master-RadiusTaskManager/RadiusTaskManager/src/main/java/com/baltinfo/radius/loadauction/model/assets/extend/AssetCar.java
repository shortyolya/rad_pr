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
 *     Type_asset = 7 (Легковые автомобили) или 8 (Бронеавтомобили)
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 23.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public class AssetCar extends Asset {
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
    @JsonProperty("carBody")
    private final String carBody;
    @JsonProperty("steeringWheel")
    private final String steeringWheel;
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
    @JsonProperty("address")
    private final String address;

    private AssetCar(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset, String soonAsset, String deposits, String other,
                     String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset, String mark, String model, String color, Integer yearOfIssue, BigDecimal mileage,
                     BigDecimal engineCapacity, String carBody, String steeringWheel, String transmission, BigDecimal enginePower, String engineType, String typeOfDrive,
                     String vin, String address, String about) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.yearOfIssue = yearOfIssue;
        this.mileage = mileage;
        this.engineCapacity = engineCapacity;
        this.carBody = carBody;
        this.steeringWheel = steeringWheel;
        this.transmission = transmission;
        this.enginePower = enginePower;
        this.engineType = engineType;
        this.typeOfDrive = typeOfDrive;
        this.vin = vin;
        this.address = address;
    }

    public static AssetCarBuilder builder() {
        return new AssetCarBuilder();
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

    public String getCarBody() {
        return carBody;
    }

    public String getSteeringWheel() {
        return steeringWheel;
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

    public String getAddress() {
        return address;
    }


    public static final class AssetCarBuilder {
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
        @JsonProperty("about")
        private String about;
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
        @JsonProperty("carBody")
        private String carBody;
        @JsonProperty("steeringWheel")
        private String steeringWheel;
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
        @JsonProperty("address")
        private String address;

        private AssetCarBuilder() {
        }

        public AssetCarBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetCarBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetCarBuilder withMark(String mark) {
            this.mark = mark;
            return this;
        }

        public AssetCarBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetCarBuilder withModel(String model) {
            this.model = model;
            return this;
        }

        public AssetCarBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetCarBuilder withColor(String color) {
            this.color = color;
            return this;
        }

        public AssetCarBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetCarBuilder withYearOfIssue(Integer yearOfIssue) {
            this.yearOfIssue = yearOfIssue;
            return this;
        }

        public AssetCarBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetCarBuilder withMileage(BigDecimal mileage) {
            this.mileage = mileage;
            return this;
        }

        public AssetCarBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetCarBuilder withEngineCapacity(BigDecimal engineCapacity) {
            this.engineCapacity = engineCapacity;
            return this;
        }

        public AssetCarBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetCarBuilder withCarBody(String carBody) {
            this.carBody = carBody;
            return this;
        }

        public AssetCarBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetCarBuilder withSteeringWheel(String steeringWheel) {
            this.steeringWheel = steeringWheel;
            return this;
        }

        public AssetCarBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetCarBuilder withTransmission(String transmission) {
            this.transmission = transmission;
            return this;
        }

        public AssetCarBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetCarBuilder withEnginePower(BigDecimal enginePower) {
            this.enginePower = enginePower;
            return this;
        }

        public AssetCarBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetCarBuilder withEngineType(String engineType) {
            this.engineType = engineType;
            return this;
        }

        public AssetCarBuilder withTypeOfDrive(String typeOfDrive) {
            this.typeOfDrive = typeOfDrive;
            return this;
        }

        public AssetCarBuilder withVin(String vin) {
            this.vin = vin;
            return this;
        }

        public AssetCarBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetCarBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public AssetCar build() {
            return new AssetCar(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset,
                    documentsAsset, imagesAsset, mark, model, color, yearOfIssue, mileage, engineCapacity, carBody, steeringWheel, transmission,
                    enginePower, engineType, typeOfDrive, vin, address, about);
        }
    }
}
