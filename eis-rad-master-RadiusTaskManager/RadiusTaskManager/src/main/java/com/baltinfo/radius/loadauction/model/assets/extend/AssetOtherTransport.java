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
 *     Type_asset = 9 (Воздушный, водный, железнодорожный транспорт)
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 23.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public class AssetOtherTransport extends Asset {
@JsonProperty("address")
    private final String address;

    private AssetOtherTransport(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset,
                                String soonAsset, String deposits, String other, String restrictionsAsset, DocumentAsset documentsAsset,
                                ImageAsset imagesAsset, String address, String about) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.address = address;
    }

    public static AssetOtherTransportBuilder builder() {
        return new AssetOtherTransportBuilder();
    }

    public String getAddress() {
        return address;
    }


    public static final class AssetOtherTransportBuilder {
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
@JsonProperty("address")
        private String address;

        private AssetOtherTransportBuilder() {
        }

        public AssetOtherTransportBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetOtherTransportBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetOtherTransportBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public AssetOtherTransportBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetOtherTransportBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetOtherTransportBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetOtherTransportBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetOtherTransportBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetOtherTransportBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetOtherTransportBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetOtherTransportBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetOtherTransportBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetOtherTransportBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetOtherTransportBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetOtherTransport build() {
            return new AssetOtherTransport(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset,
                    documentsAsset, imagesAsset, address, about);
        }
    }
}
