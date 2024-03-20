package com.baltinfo.radius.loadauction.model.assets;

import com.baltinfo.radius.loadauction.model.DocumentAsset;
import com.baltinfo.radius.loadauction.model.ImageAsset;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * <p>
 *     Java class для десериализация xml-файла
 *     Поля обязательные для всех активов
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 23.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public abstract class Asset {
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
    @JsonProperty("about")
    private String about;
    @JsonProperty("Documents_asset")
    private DocumentAsset documentsAsset;
    @JsonProperty("Images_asset")
    private ImageAsset imagesAsset;

    public Asset(Integer typeAsset, String idSya, String nameAsset, String locationAsset,
                 String nameFoAsset, String aboutAsset, String soonAsset, String deposits,
                 String other, String restrictionsAsset, DocumentAsset documentsAsset,
                 ImageAsset imagesAsset, String about) {
        this.typeAsset = typeAsset;
        this.idSya = idSya;
        this.nameAsset = nameAsset;
        this.locationAsset = locationAsset;
        this.nameFoAsset = nameFoAsset;
        this.aboutAsset = aboutAsset;
        this.soonAsset = soonAsset;
        this.deposits = deposits;
        this.other = other;
        this.restrictionsAsset = restrictionsAsset;
        this.about = about;
        this.documentsAsset = documentsAsset;
        this.imagesAsset = imagesAsset;
    }

    public Integer getTypeAsset() {
        return typeAsset;
    }

    public String getIdSya() {
        return idSya;
    }

    public String getNameAsset() {
        return nameAsset;
    }

    public String getLocationAsset() {
        return locationAsset;
    }

    public String getNameFoAsset() {
        return nameFoAsset;
    }

    public String getAboutAsset() {
        return aboutAsset;
    }

    public String getSoonAsset() {
        return soonAsset;
    }

    public String getDeposits() {
        return deposits;
    }

    public String getOther() {
        return other;
    }

    public String getRestrictionsAsset() {
        return restrictionsAsset;
    }

    public String getAbout() {
        return about;
    }

    public DocumentAsset getDocumentsAsset() {
        return documentsAsset;
    }

    public ImageAsset getImagesAsset() {
        return imagesAsset;
    }
}
