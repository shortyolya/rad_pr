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
 *     Type_asset= 12 (Монеты)
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 25.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public class AssetCoin extends Asset {
    @JsonProperty("typeOfObject")
    private final String typeOfObject;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("country")
    private final String country;
    @JsonProperty("yearOfIssue")
    private final Integer yearOfIssue;
    @JsonProperty("series")
    private final String series;
    @JsonProperty("metal")
    private final String metal;
    @JsonProperty("sample")
    private final String sample;
    @JsonProperty("nominalCost")
    private final BigDecimal nominalCost;
    @JsonProperty("weight")
    private final BigDecimal weight;
    @JsonProperty("address")
    private final String address;
    @JsonProperty("amount")
    private final BigDecimal amount;

    private AssetCoin(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset, String soonAsset, String deposits,
                      String other, String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset, String typeOfObject, String name, String country,
                      Integer yearOfIssue, String series, String metal, String sample, BigDecimal nominalCost, BigDecimal weight, String address, String about, BigDecimal amount) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.typeOfObject = typeOfObject;
        this.name = name;
        this.country = country;
        this.yearOfIssue = yearOfIssue;
        this.series = series;
        this.metal = metal;
        this.sample = sample;
        this.nominalCost = nominalCost;
        this.weight = weight;
        this.address = address;
        this.amount = amount;
    }

    public static AssetCoinBuilder builder() {
        return new AssetCoinBuilder();
    }

    public String getTypeOfObject() {
        return typeOfObject;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public Integer getYearOfIssue() {
        return yearOfIssue;
    }

    public String getSeries() {
        return series;
    }

    public String getMetal() {
        return metal;
    }

    public String getSample() {
        return sample;
    }

    public BigDecimal getNominalCost() {
        return nominalCost;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public static final class AssetCoinBuilder {
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
        @JsonProperty("Soon_asset")
        private String soonAsset;
        @JsonProperty("Deposits")
        private String deposits;
        @JsonProperty("Other")
        private String other;
        @JsonProperty("Restrictions_asset")
        private String restrictionsAsset;
        @JsonProperty("Documents_asset")
        private DocumentAsset documentsAsset;
        @JsonProperty("Images_asset")
        private ImageAsset imagesAsset;
        @JsonProperty("Type_of_object")
        private String typeOfObject;
        @JsonProperty("Name")
        private String name;
        @JsonProperty("Country")
        private String country;
        @JsonProperty("Year_of_issue")
        private Integer yearOfIssue;
        @JsonProperty("Series")
        private String series;
        @JsonProperty("Metal")
        private String metal;
        @JsonProperty("Sample")
        private String sample;
        @JsonProperty("Nominal_cost")
        private BigDecimal nominalCost;
        @JsonProperty("Weight")
        private BigDecimal weight;
        @JsonProperty("Address")
        private String address;
        @JsonProperty("About")
        private String about;
        @JsonProperty("Amount")
        private BigDecimal amount;

        private AssetCoinBuilder() {
        }

        public AssetCoinBuilder withTypeOfObject(String typeOfObject) {
            this.typeOfObject = typeOfObject;
            return this;
        }

        public AssetCoinBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetCoinBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public AssetCoinBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetCoinBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public AssetCoinBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetCoinBuilder withYearOfIssue(Integer yearOfIssue) {
            this.yearOfIssue = yearOfIssue;
            return this;
        }

        public AssetCoinBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetCoinBuilder withSeries(String series) {
            this.series = series;
            return this;
        }

        public AssetCoinBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetCoinBuilder withMetal(String metal) {
            this.metal = metal;
            return this;
        }

        public AssetCoinBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetCoinBuilder withSample(String sample) {
            this.sample = sample;
            return this;
        }

        public AssetCoinBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetCoinBuilder withNominalCost(BigDecimal nominalCost) {
            this.nominalCost = nominalCost;
            return this;
        }

        public AssetCoinBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetCoinBuilder withWeight(BigDecimal weight) {
            this.weight = weight;
            return this;
        }

        public AssetCoinBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetCoinBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public AssetCoinBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetCoinBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetCoinBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetCoinBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetCoinBuilder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public AssetCoin build() {
            return new AssetCoin(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset,
                    typeOfObject, name, country, yearOfIssue, series, metal, sample, nominalCost, weight, address, about, amount);
        }
    }
}
