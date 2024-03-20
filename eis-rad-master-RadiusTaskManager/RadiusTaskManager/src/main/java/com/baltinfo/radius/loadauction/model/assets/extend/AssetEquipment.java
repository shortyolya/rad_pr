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
 * Type_asset=27 (Банковское оборудование) или 29 (Вычислительная и оргтехника) или 28 (Мебель и предметы интерьера) или
 * 30 (Оборудование связи и сетевое оборудование) или 31 (Прочие ОС), 32 (Хозтовары и инвентарь) или 33 (Машины и производственное оборудование)
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 25.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public class AssetEquipment extends Asset {

    @JsonProperty("typeOfObject")
    private final String typeOfObject;
    @JsonProperty("model")
    private final String model;
    @JsonProperty("mark")
    private final String mark;
    @JsonProperty("address")
    private final String address;
    @JsonProperty("amount")
    private final BigDecimal amount;

    private AssetEquipment(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset, String soonAsset,
                           String deposits, String other, String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset,
                           String model, String mark, String address, BigDecimal amount, String about, String typeOfObject) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.model = model;
        this.mark = mark;
        this.address = address;
        this.amount = amount;
        this.typeOfObject = typeOfObject;
    }

    public static AssetEquipmentBuilder builder() {
        return new AssetEquipmentBuilder();
    }

    public String getTypeOfObject() {
        return typeOfObject;
    }

    public String getModel() {
        return model;
    }

    public String getMark() {
        return mark;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getAmount() {
        return amount;
    }


    public static final class AssetEquipmentBuilder {

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
        @JsonProperty("model")
        private String model;
        @JsonProperty("mark")
        private String mark;
        @JsonProperty("address")
        private String address;
        @JsonProperty("amount")
        private BigDecimal amount;

        private AssetEquipmentBuilder() {
        }

        public AssetEquipmentBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetEquipmentBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetEquipmentBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetEquipmentBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetEquipmentBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetEquipmentBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetEquipmentBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetEquipmentBuilder withTypeOfObject(String typeOfObject) {
            this.typeOfObject = typeOfObject;
            return this;
        }

        public AssetEquipmentBuilder withModel(String model) {
            this.model = model;
            return this;
        }

        public AssetEquipmentBuilder withMark(String mark) {
            this.mark = mark;
            return this;
        }

        public AssetEquipmentBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetEquipmentBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public AssetEquipmentBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetEquipmentBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetEquipmentBuilder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public AssetEquipmentBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetEquipmentBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetEquipmentBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetEquipment build() {
            return new AssetEquipment(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset,
                    model, mark, address, amount, about, typeOfObject);
        }
    }
}
