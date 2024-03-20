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
 * Type_asset=20 (Прочие)
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 25.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public class AssetOther extends Asset {

    @JsonProperty("nameCb")
    private final String nameCb;
    @JsonProperty("typeCb")
    private final String typeCb;
    @JsonProperty("issuer")
    private final String issuer;
    @JsonProperty("number")
    private final String number;
    @JsonProperty("term")
    private final String term;
    @JsonProperty("storage")
    private final String storage;
    @JsonProperty("supportingDocuments")
    private final String supportingDocuments;
    @JsonProperty("counterparty")
    private final String counterparty;
    @JsonProperty("assessmentValue")
    private final BigDecimal assessmentValue;
    @JsonProperty("assessmentDate")
    private final String assessmentDate;
    @JsonProperty("url")
    private final String url;
    @JsonProperty("amount")
    private final BigDecimal amount;
    @JsonProperty("turnoverLimit")
    private final String turnoverLimit;

    private AssetOther(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset, String soonAsset, String deposits,
                       String other, String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset, String nameCb, String typeCb, String issuer, String number,
                       String term, String storage, String supportingDocuments, String counterparty, BigDecimal assessmentValue, String assessmentDate, String url, String about, BigDecimal amount, String turnoverLimit) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.nameCb = nameCb;
        this.typeCb = typeCb;
        this.issuer = issuer;
        this.number = number;
        this.term = term;
        this.storage = storage;
        this.supportingDocuments = supportingDocuments;
        this.counterparty = counterparty;
        this.assessmentValue = assessmentValue;
        this.assessmentDate = assessmentDate;
        this.url = url;
        this.amount = amount;
        this.turnoverLimit = turnoverLimit;
    }

    public static AssetOtherBuilder builder() {
        return new AssetOtherBuilder();
    }

    public String getNameCb() {
        return nameCb;
    }

    public String getTypeCb() {
        return typeCb;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getNumber() {
        return number;
    }

    public String getTerm() {
        return term;
    }

    public String getStorage() {
        return storage;
    }

    public String getSupportingDocuments() {
        return supportingDocuments;
    }

    public String getCounterparty() {
        return counterparty;
    }

    public BigDecimal getAssessmentValue() {
        return assessmentValue;
    }

    public String getAssessmentDate() {
        return assessmentDate;
    }

    public String getUrl() {
        return url;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getTurnoverLimit() {
        return turnoverLimit;
    }

    public static final class AssetOtherBuilder {

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
        @JsonProperty("nameCb")
        private String nameCb;
        @JsonProperty("typeCb")
        private String typeCb;
        @JsonProperty("issuer")
        private String issuer;
        @JsonProperty("number")
        private String number;
        @JsonProperty("term")
        private String term;
        @JsonProperty("storage")
        private String storage;
        @JsonProperty("supportingDocuments")
        private String supportingDocuments;
        @JsonProperty("counterparty")
        private String counterparty;
        @JsonProperty("assessmentValue")
        private BigDecimal assessmentValue;
        @JsonProperty("assessmentDate")
        private String assessmentDate;
        @JsonProperty("url")
        private String url;
        @JsonProperty("amount")
        private BigDecimal amount;
        @JsonProperty("turnoverLimit")
        private String turnoverLimit;

        private AssetOtherBuilder() {
        }

        public AssetOtherBuilder withNameCb(String nameCb) {
            this.nameCb = nameCb;
            return this;
        }

        public AssetOtherBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetOtherBuilder withTypeCb(String typeCb) {
            this.typeCb = typeCb;
            return this;
        }

        public AssetOtherBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetOtherBuilder withIssuer(String issuer) {
            this.issuer = issuer;
            return this;
        }

        public AssetOtherBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetOtherBuilder withNumber(String number) {
            this.number = number;
            return this;
        }

        public AssetOtherBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetOtherBuilder withTerm(String term) {
            this.term = term;
            return this;
        }

        public AssetOtherBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetOtherBuilder withStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public AssetOtherBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetOtherBuilder withSupportingDocuments(String supportingDocuments) {
            this.supportingDocuments = supportingDocuments;
            return this;
        }

        public AssetOtherBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetOtherBuilder withCounterparty(String counterparty) {
            this.counterparty = counterparty;
            return this;
        }

        public AssetOtherBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetOtherBuilder withAssessmentValue(BigDecimal assessmentValue) {
            this.assessmentValue = assessmentValue;
            return this;
        }

        public AssetOtherBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetOtherBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetOtherBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetOtherBuilder withAssessmentDate(String assessmentDate) {
            this.assessmentDate = assessmentDate;
            return this;
        }

        public AssetOtherBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetOtherBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public AssetOtherBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetOtherBuilder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public AssetOtherBuilder withTurnoverLimit(String turnoverLimit) {
            this.turnoverLimit = turnoverLimit;
            return this;
        }

        public AssetOther build() {
            return new AssetOther(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset,
                    documentsAsset, imagesAsset, nameCb, typeCb, issuer, number, term, storage, supportingDocuments, counterparty, assessmentValue,
                    assessmentDate, url, about, amount, turnoverLimit);
        }
    }
}
