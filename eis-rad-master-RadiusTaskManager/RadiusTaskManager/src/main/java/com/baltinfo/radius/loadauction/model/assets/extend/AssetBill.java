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
 *     Type_asset=16 (Векселя)
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 25.12.2019
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@JsonRootName(value = "Asset")
public class AssetBill extends Asset {
    @JsonProperty("view")
    private final String view;
    @JsonProperty("promiser")
    private final String promiser;
    @JsonProperty("promiserInn")
    private final String promiserInn;
    @JsonProperty("interestRate")
    private final BigDecimal interestRate;
    @JsonProperty("billAmount")
    private final BigDecimal billAmount;
    @JsonProperty("currency")
    private final String currency;
    @JsonProperty("dateOfPreparation")
    private final String dateOfPreparation;
    @JsonProperty("placeOfCompilation")
    private final String placeOfCompilation;
    @JsonProperty("obligatedPersonName")
    private final String obligatedPersonName;
    @JsonProperty("avalist")
    private final String avalist;
    @JsonProperty("paymentTerm")
    private final String paymentTerm;
    @JsonProperty("placeOfPayment")
    private final String placeOfPayment;
    @JsonProperty("promiserLocation")
    private final String promiserLocation;
    @JsonProperty("storage")
    private final String storage;
    @JsonProperty("promiserBankrot")
    private final String promiserBankrot;
    @JsonProperty("url")
    private final String url;
    @JsonProperty("assessmentValue")
    private final BigDecimal assessmentValue;
    @JsonProperty("assessmentDate")
    private final String assessmentDate;
    @JsonProperty("turnoverLimit")
    private final String turnoverLimit;

    private AssetBill(Integer typeAsset, String idSya, String nameAsset, String locationAsset, String nameFoAsset, String aboutAsset, String soonAsset, String deposits,
                      String other, String restrictionsAsset, DocumentAsset documentsAsset, ImageAsset imagesAsset, String view, String promiser, String promiserInn, BigDecimal interestRate,
                      BigDecimal billAmount, String currency, String dateOfPreparation, String placeOfCompilation, String obligatedPersonName, String avalist, String paymentTerm,
                      String placeOfPayment, String promiserLocation, String storage, String promiserBankrot, String url, BigDecimal assessmentValue, String assessmentDate, String about, String turnoverLimit) {
        super(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other, restrictionsAsset, documentsAsset, imagesAsset, about);
        this.view = view;
        this.promiser = promiser;
        this.promiserInn = promiserInn;
        this.interestRate = interestRate;
        this.billAmount = billAmount;
        this.currency = currency;
        this.dateOfPreparation = dateOfPreparation;
        this.placeOfCompilation = placeOfCompilation;
        this.obligatedPersonName = obligatedPersonName;
        this.avalist = avalist;
        this.paymentTerm = paymentTerm;
        this.placeOfPayment = placeOfPayment;
        this.promiserLocation = promiserLocation;
        this.storage = storage;
        this.promiserBankrot = promiserBankrot;
        this.url = url;
        this.assessmentValue = assessmentValue;
        this.assessmentDate = assessmentDate;
        this.turnoverLimit = turnoverLimit;
    }

    public static AssetBillBuilder builder() {
        return new AssetBillBuilder();
    }

    public String getView() {
        return view;
    }

    public String getPromiser() {
        return promiser;
    }

    public String getPromiserInn() {
        return promiserInn;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public BigDecimal getBillAmount() {
        return billAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDateOfPreparation() {
        return dateOfPreparation;
    }

    public String getPlaceOfCompilation() {
        return placeOfCompilation;
    }

    public String getObligatedPersonName() {
        return obligatedPersonName;
    }

    public String getAvalist() {
        return avalist;
    }

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public String getPlaceOfPayment() {
        return placeOfPayment;
    }

    public String getPromiserLocation() {
        return promiserLocation;
    }

    public String getStorage() {
        return storage;
    }

    public String getPromiserBankrot() {
        return promiserBankrot;
    }

    public String getUrl() {
        return url;
    }

    public BigDecimal getAssessmentValue() {
        return assessmentValue;
    }

    public String getAssessmentDate() {
        return assessmentDate;
    }

    public String getTurnoverLimit() {
        return turnoverLimit;
    }

    public static final class AssetBillBuilder {
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
        @JsonProperty("view")
        private String view;
        @JsonProperty("promiser")
        private String promiser;
        @JsonProperty("promiserInn")
        private String promiserInn;
        @JsonProperty("interestRate")
        private BigDecimal interestRate;
        @JsonProperty("billAmount")
        private BigDecimal billAmount;
        @JsonProperty("currency")
        private String currency;
        @JsonProperty("dateOfPreparation")
        private String dateOfPreparation;
        @JsonProperty("placeOfCompilation")
        private String placeOfCompilation;
        @JsonProperty("obligatedPersonName")
        private String obligatedPersonName;
        @JsonProperty("avalist")
        private String avalist;
        @JsonProperty("paymentTerm")
        private String paymentTerm;
        @JsonProperty("placeOfPayment")
        private String placeOfPayment;
        @JsonProperty("promiserLocation")
        private String promiserLocation;
        @JsonProperty("storage")
        private String storage;
        @JsonProperty("promiserBankrot")
        private String promiserBankrot;
        @JsonProperty("url")
        private String url;
        @JsonProperty("about")
        private String about;
        @JsonProperty("assessmentValue")
        private BigDecimal assessmentValue;
        @JsonProperty("assessmentDate")
        private String assessmentDate;
        @JsonProperty("turnoverLimit")
        private String turnoverLimit;

        private AssetBillBuilder() {
        }

        public AssetBillBuilder withView(String view) {
            this.view = view;
            return this;
        }

        public AssetBillBuilder withTypeAsset(Integer typeAsset) {
            this.typeAsset = typeAsset;
            return this;
        }

        public AssetBillBuilder withPromiser(String promiser) {
            this.promiser = promiser;
            return this;
        }

        public AssetBillBuilder withIdSya(String idSya) {
            this.idSya = idSya;
            return this;
        }

        public AssetBillBuilder withPromiserInn(String promiserInn) {
            this.promiserInn = promiserInn;
            return this;
        }

        public AssetBillBuilder withNameAsset(String nameAsset) {
            this.nameAsset = nameAsset;
            return this;
        }

        public AssetBillBuilder withLocationAsset(String locationAsset) {
            this.locationAsset = locationAsset;
            return this;
        }

        public AssetBillBuilder withInterestRate(BigDecimal interestRate) {
            this.interestRate = interestRate;
            return this;
        }

        public AssetBillBuilder withNameFoAsset(String nameFoAsset) {
            this.nameFoAsset = nameFoAsset;
            return this;
        }

        public AssetBillBuilder withBillAmount(BigDecimal billAmount) {
            this.billAmount = billAmount;
            return this;
        }

        public AssetBillBuilder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public AssetBillBuilder withAboutAsset(String aboutAsset) {
            this.aboutAsset = aboutAsset;
            return this;
        }

        public AssetBillBuilder withDateOfPreparation(String dateOfPreparation) {
            this.dateOfPreparation = dateOfPreparation;
            return this;
        }

        public AssetBillBuilder withSoonAsset(String soonAsset) {
            this.soonAsset = soonAsset;
            return this;
        }

        public AssetBillBuilder withDeposits(String deposits) {
            this.deposits = deposits;
            return this;
        }

        public AssetBillBuilder withPlaceOfCompilation(String placeOfCompilation) {
            this.placeOfCompilation = placeOfCompilation;
            return this;
        }

        public AssetBillBuilder withOther(String other) {
            this.other = other;
            return this;
        }

        public AssetBillBuilder withObligatedPersonName(String obligatedPersonName) {
            this.obligatedPersonName = obligatedPersonName;
            return this;
        }

        public AssetBillBuilder withRestrictionsAsset(String restrictionsAsset) {
            this.restrictionsAsset = restrictionsAsset;
            return this;
        }

        public AssetBillBuilder withAvalist(String avalist) {
            this.avalist = avalist;
            return this;
        }

        public AssetBillBuilder withDocumentsAsset(DocumentAsset documentsAsset) {
            this.documentsAsset = documentsAsset;
            return this;
        }

        public AssetBillBuilder withPaymentTerm(String paymentTerm) {
            this.paymentTerm = paymentTerm;
            return this;
        }

        public AssetBillBuilder withImagesAsset(ImageAsset imagesAsset) {
            this.imagesAsset = imagesAsset;
            return this;
        }

        public AssetBillBuilder withPlaceOfPayment(String placeOfPayment) {
            this.placeOfPayment = placeOfPayment;
            return this;
        }

        public AssetBillBuilder withPromiserLocation(String promiserLocation) {
            this.promiserLocation = promiserLocation;
            return this;
        }

        public AssetBillBuilder withStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public AssetBillBuilder withPromiserBankrot(String promiserBankrot) {
            this.promiserBankrot = promiserBankrot;
            return this;
        }

        public AssetBillBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public AssetBillBuilder withAssessmentValue(BigDecimal assessmentValue) {
            this.assessmentValue = assessmentValue;
            return this;
        }

        public AssetBillBuilder withAssessmentDate(String assessmentDate) {
            this.assessmentDate = assessmentDate;
            return this;
        }

        public AssetBillBuilder withAbout(String about) {
            this.about = about;
            return this;
        }

        public AssetBillBuilder withTurnoverLimit(String turnoverLimit) {
            this.turnoverLimit = turnoverLimit;
            return this;
        }

        public AssetBill build() {
            return new AssetBill(typeAsset, idSya, nameAsset, locationAsset, nameFoAsset, aboutAsset, soonAsset, deposits, other,
                    restrictionsAsset, documentsAsset, imagesAsset, view, promiser, promiserInn, interestRate, billAmount, currency,
                    dateOfPreparation, placeOfCompilation, obligatedPersonName, avalist, paymentTerm, placeOfPayment, promiserLocation,
                    storage, promiserBankrot, url, assessmentValue, assessmentDate, about, turnoverLimit);
        }
    }
}
