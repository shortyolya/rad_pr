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
 * Java class для десериализация xml-файла
 * Type_asset=34 (Нематериальные активы)
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 26.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public class AssetIntangible extends Asset {

    @JsonProperty("nameRightholder")
    private final String nameRightHolder;
    @JsonProperty("address")
    private final String address;
    @JsonProperty("numberOfSvidet")
    private final Long numberOfSvidet;

    private AssetIntangible(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset, String soonAsset, String deposits, String other,
                            String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset, String nameRightHolder, String address, Long numberOfSvidet, String about) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.nameRightHolder = nameRightHolder;
        this.address = address;
        this.numberOfSvidet = numberOfSvidet;
    }

    public static AssetIntangibleBuilder builder() {
        return new AssetIntangibleBuilder();
    }

    public String getNameRightHolder() {
        return nameRightHolder;
    }

    public String getAddress() {
        return address;
    }

    public Long getNumberOfSvidet() {
        return numberOfSvidet;
    }


    public static final class AssetIntangibleBuilder {

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
        @JsonProperty("nameRightholder")
        private String nameRightHolder;
        @JsonProperty("address")
        private String address;
        @JsonProperty("about")
        private String about;
        @JsonProperty("numberOfSvidet")
        private Long numberOfSvidet;

        private AssetIntangibleBuilder() {
        }

        public AssetIntangibleBuilder withNameRightHolder(String nameRightHolder) {
            this.nameRightHolder = nameRightHolder;
            return this;
        }

        public AssetIntangibleBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetIntangibleBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetIntangibleBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public AssetIntangibleBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetIntangibleBuilder withNumberOfSvidet(Long numberOfSvidet) {
            this.numberOfSvidet = numberOfSvidet;
            return this;
        }

        public AssetIntangibleBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetIntangibleBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetIntangibleBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetIntangibleBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetIntangibleBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetIntangibleBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetIntangibleBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetIntangibleBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetIntangibleBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetIntangibleBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetIntangible build() {
            return new AssetIntangible(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset,
                    documentsAsset, imagesAsset, nameRightHolder, address, numberOfSvidet, about);
        }
    }
}
