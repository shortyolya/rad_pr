package com.baltinfo.radius.loadauction.model.assets.extend;

import com.baltinfo.radius.loadauction.model.DocumentAsset;
import com.baltinfo.radius.loadauction.model.ImageAsset;
import com.baltinfo.radius.loadauction.model.assets.Asset;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * <p>
 *     Java class для десериализация xml-файла
 *     Type_asset= 13 (Произведения искусства)
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 25.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public class AssetArt extends Asset {
    @JsonProperty("typeOfObject")
    private final String typeOfObject;
    @JsonProperty("productName")
    private final String productName;
    @JsonProperty("author")
    private final String author;
    @JsonProperty("date")
    private final String date;
    @JsonProperty("size")
    private final String size;
    @JsonProperty("material")
    private final String material;
    @JsonProperty("expertiseInfo")
    private final String expertiseInfo;
    @JsonProperty("address")
    private final String address;

    private AssetArt(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset, String soonAsset,
                     String deposits, String other, String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset, String typeOfAsset,
                     String productName, String author, String date, String size, String material, String expertiseInfo, String address, String about) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.typeOfObject = typeOfAsset;
        this.productName = productName;
        this.author = author;
        this.date = date;
        this.size = size;
        this.material = material;
        this.expertiseInfo = expertiseInfo;
        this.address = address;
    }

    public static AssetArtBuilder builder() {
        return new AssetArtBuilder();
    }

    public String getTypeOfObject() {
        return typeOfObject;
    }

    public String getProductName() {
        return productName;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getSize() {
        return size;
    }

    public String getMaterial() {
        return material;
    }

    public String getExpertiseInfo() {
        return expertiseInfo;
    }

    public String getAddress() {
        return address;
    }


    public static final class AssetArtBuilder {
        @JsonProperty("typeOfObject")
        private String typeOfObject;
        @JsonProperty("productName")
        private String productName;
        @JsonProperty("author")
        private String author;
        @JsonProperty("date")
        private String date;
        @JsonProperty("size")
        private String size;
        @JsonProperty("material")
        private String material;
        @JsonProperty("expertiseInfo")
        private String expertiseInfo;
        @JsonProperty("address")
        private String address;
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

        private AssetArtBuilder() {
        }

        public AssetArtBuilder withTypeOfObject(String typeOfObject) {
            this.typeOfObject = typeOfObject;
            return this;
        }

        public AssetArtBuilder withProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public AssetArtBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetArtBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetArtBuilder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public AssetArtBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetArtBuilder withDate(String date) {
            this.date = date;
            return this;
        }

        public AssetArtBuilder withSize(String size) {
            this.size = size;
            return this;
        }

        public AssetArtBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetArtBuilder withMaterial(String material) {
            this.material = material;
            return this;
        }

        public AssetArtBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetArtBuilder withExpertiseInfo(String expertiseInfo) {
            this.expertiseInfo = expertiseInfo;
            return this;
        }

        public AssetArtBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetArtBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public AssetArtBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetArtBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetArtBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetArtBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetArtBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetArtBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetArtBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetArt build() {
            return new AssetArt(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset,
                    typeOfObject, productName, author, date, size, material, expertiseInfo, address, about);
        }
    }
}
