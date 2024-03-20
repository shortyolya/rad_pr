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
 * Type_asset= 11 (Драгоценные металлы, драгоценные камни, ювелирные изделия)
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 25.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public class AssetGem extends Asset {

    @JsonProperty("typeOfObject")
    private final String typeOfObject;
    @JsonProperty("metal")
    private final String metal;
    @JsonProperty("sample")
    private final String sample;
    @JsonProperty("totalWeight")
    private final BigDecimal totalWeight;
    @JsonProperty("nameStone")
    private final String nameStone;
    @JsonProperty("address")
    private final String address;
    @JsonProperty("typeThings")
    private final String typeThings;

    private AssetGem(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset, String soonAsset,
                     String deposits, String other, String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset, String typeOfObject, String metal,
                     String sample, BigDecimal totalWeight, String nameStone, String address, String about, String typeThings) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.typeOfObject = typeOfObject;
        this.metal = metal;
        this.sample = sample;
        this.totalWeight = totalWeight;
        this.nameStone = nameStone;
        this.address = address;
        this.typeThings = typeThings;
    }

    public static AssetGemBuilder builder() {
        return new AssetGemBuilder();
    }

    public String getTypeOfObject() {
        return typeOfObject;
    }

    public String getMetal() {
        return metal;
    }

    public String getSample() {
        return sample;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public String getNameStone() {
        return nameStone;
    }

    public String getAddress() {
        return address;
    }

    public String getTypeThings() {
        return typeThings;
    }

    public static final class AssetGemBuilder {

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
        @JsonProperty("metal")
        private String metal;
        @JsonProperty("sample")
        private String sample;
        @JsonProperty("totalWeight")
        private BigDecimal totalWeight;
        @JsonProperty("nameStone")
        private String nameStone;
        @JsonProperty("address")
        private String address;
        @JsonProperty("typeThings")
        private String typeThings;

        private AssetGemBuilder() {
        }

        public AssetGemBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetGemBuilder withTypeOfObject(String typeOfObject) {
            this.typeOfObject = typeOfObject;
            return this;
        }

        public AssetGemBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetGemBuilder withMetal(String metal) {
            this.metal = metal;
            return this;
        }

        public AssetGemBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetGemBuilder withSample(String sample) {
            this.sample = sample;
            return this;
        }

        public AssetGemBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetGemBuilder withTotalWeight(BigDecimal totalWeight) {
            this.totalWeight = totalWeight;
            return this;
        }

        public AssetGemBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetGemBuilder withNameStone(String nameStone) {
            this.nameStone = nameStone;
            return this;
        }

        public AssetGemBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetGemBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public AssetGemBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetGemBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetGemBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetGemBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetGemBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetGemBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetGemBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetGemBuilder withTypeThings(String typeThings) {
            this.typeThings = typeThings;
            return this;
        }

        public AssetGem build() {
            return new AssetGem(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset,
                    imagesAsset, typeOfObject, metal, sample, totalWeight, nameStone, address, about, typeThings);
        }
    }
}
