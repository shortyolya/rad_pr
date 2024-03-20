package com.baltinfo.radius.loadauction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.math.BigDecimal;

/**
 * <p>
 * Java class для десериализация xml-файла
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 26.12.2019
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetFull {
    @JacksonXmlProperty(localName = "About")
    @JsonProperty("about")
    private final String about;
    @JacksonXmlProperty(localName = "Type_asset")
    @JsonProperty("typeAsset")
    private final Integer typeAsset;
    @JacksonXmlProperty(localName = "ID_SYA")
    @JsonProperty("idSya")
    private final String idSya;
    @JacksonXmlProperty(localName = "Name_asset")
    @JsonProperty("nameAsset")
    private final String nameAsset;
    @JacksonXmlProperty(localName = "Location_asset")
    @JsonProperty("locationAsset")
    private final String locationAsset;
    @JacksonXmlProperty(localName = "Name_FO_asset")
    @JsonProperty("nameFoAsset")
    private final String nameFoAsset;
    @JacksonXmlProperty(localName = "About_asset")
    @JsonProperty("aboutAsset")
    private final String aboutAsset;
    @JacksonXmlProperty(localName = "Soon_asset")
    @JsonProperty("soonAsset")
    private final String soonAsset;
    @JacksonXmlProperty(localName = "Deposits")
    @JsonProperty("deposits")
    private final String deposits;
    @JacksonXmlProperty(localName = "Other")
    @JsonProperty("other")
    private final String other;
    @JacksonXmlProperty(localName = "Restrictions_asset")
    @JsonProperty("restrictionsAsset")
    private final String restrictionsAsset;
    @JacksonXmlProperty(localName = "Type_of_object")
    @JsonProperty("typeOfObject")
    private final String typeOfObject;
    @JacksonXmlProperty(localName = "Square")
    @JsonProperty("square")
    private final BigDecimal square;
    @JacksonXmlProperty(localName = "Address")
    @JsonProperty("address")
    private final String address;
    @JacksonXmlProperty(localName = "Cadastral_number")
    @JsonProperty("cadastralNumber")
    private final String cadastralNumber;
    @JacksonXmlProperty(localName = "Year_built")
    @JsonProperty("yearBuilt")
    private final Integer yearBuilt;
    @JacksonXmlProperty(localName = "Number_of_rooms")
    @JsonProperty("numberOfRooms")
    private final Integer numberOfRooms;
    @JacksonXmlProperty(localName = "Floor_number")
    @JsonProperty("floorNumber")
    private final String floorNumber;
    @JacksonXmlProperty(localName = "Floors")
    @JsonProperty("floors")
    private final Integer floors;
    @JacksonXmlProperty(localName = "Ceiling_height")
    @JsonProperty("ceilingHeight")
    private final BigDecimal ceilingHeight;
    @JacksonXmlProperty(localName = "Living_space")
    @JsonProperty("livingSpace")
    private final BigDecimal livingSpace;
    @JacksonXmlProperty(localName = "Kitchen_area")
    @JsonProperty("kitchenArea")
    private final BigDecimal kitchenArea;
    @JacksonXmlProperty(localName = "Elevator")
    @JsonProperty("elevator")
    private final String elevator;
    @JacksonXmlProperty(localName = "House_type")
    @JsonProperty("houseType")
    private final String houseType;
    @JacksonXmlProperty(localName = "Balcony")
    @JsonProperty("balcony")
    private final String balcony;
    @JacksonXmlProperty(localName = "View_from_the_window")
    @JsonProperty("viewFromTheWindow")
    private final String viewFromTheWindow;
    @JacksonXmlProperty(localName = "Communications")
    @JsonProperty("communications")
    private final String communications;
    @JacksonXmlProperty(localName = "Assessment_value")
    @JsonProperty("assessmentValue")
    private final BigDecimal assessmentValue;
    @JacksonXmlProperty(localName = "Assessment_date")
    @JsonProperty("assessmentDate")
    private final String assessmentDate;
    @JacksonXmlProperty(localName = "Type_of_asset")
    @JsonProperty("typeOfAsset")
    private final String typeOfAsset;
    @JacksonXmlProperty(localName = "Name_product")
    @JsonProperty("nameProduct")
    private final String productName;
    @JacksonXmlProperty(localName = "Author")
    @JsonProperty("author")
    private final String author;
    @JacksonXmlProperty(localName = "Date")
    @JsonProperty("date")
    private final String date;
    @JacksonXmlProperty(localName = "Size")
    @JsonProperty("size")
    private final String size;
    @JacksonXmlProperty(localName = "Material")
    @JsonProperty("material")
    private final String material;
    @JacksonXmlProperty(localName = "Expertise_information")
    @JsonProperty("expertiseInformation")
    private final String expertiseInfo;
    @JacksonXmlProperty(localName = "View")
    @JsonProperty("view")
    private final String view;
    @JacksonXmlProperty(localName = "Promiser")
    @JsonProperty("promiser")
    private final String promiser;
    @JacksonXmlProperty(localName = "Promiser_INN")
    @JsonProperty("promiserInn")
    private final String promiserInn;
    @JacksonXmlProperty(localName = "Interest_rate")
    @JsonProperty("interestRate")
    private final BigDecimal interestRate;
    @JacksonXmlProperty(localName = "Bill_amount")
    @JsonProperty("billAmount")
    private final BigDecimal billAmount;
    @JacksonXmlProperty(localName = "Currency")
    @JsonProperty("currency")
    private final String currency;
    @JacksonXmlProperty(localName = "Date_of_preparation")
    @JsonProperty("dateOfPreparation")
    private final String dateOfPreparation;
    @JacksonXmlProperty(localName = "Place_of_compilation")
    @JsonProperty("placeOfCompilation")
    private final String placeOfCompilation;
    @JacksonXmlProperty(localName = "Obligated_person_name")
    @JsonProperty("obligatedPersonName")
    private final String obligatedPersonName;
    @JacksonXmlProperty(localName = "Avalist")
    @JsonProperty("avalist")
    private final String avalist;
    @JacksonXmlProperty(localName = "Payment_term")
    @JsonProperty("paymentTerm")
    private final String paymentTerm;
    @JacksonXmlProperty(localName = "Place_of_payment")
    @JsonProperty("placeOfPayment")
    private final String placeOfPayment;
    @JacksonXmlProperty(localName = "Promiser_location")
    @JsonProperty("promiserLocation")
    private final String promiserLocation;
    @JacksonXmlProperty(localName = "Storage")
    @JsonProperty("storage")
    private final String storage;
    @JacksonXmlProperty(localName = "Promiser_bankrot")
    @JsonProperty("promiserBankrot")
    private final String promiserBankrot;
    @JacksonXmlProperty(localName = "URL")
    @JsonProperty("url")
    private final String url;
    @JacksonXmlProperty(localName = "Type_bond")
    @JsonProperty("typeBond")
    private final String typeBond;
    @JacksonXmlProperty(localName = "Issuer_name")
    @JsonProperty("issuerName")
    private final String issuerName;
    @JacksonXmlProperty(localName = "Issuer_INN")
    @JsonProperty("issuerInn")
    private final String issuerInn;
    @JacksonXmlProperty(localName = "Amount")
    @JsonProperty("amount")
    private final BigDecimal amount;
    @JacksonXmlProperty(localName = "Coupon_rate")
    @JsonProperty("couponRate")
    private final String couponRate;
    @JacksonXmlProperty(localName = "Payment_frequency")
    @JsonProperty("paymentFrequency")
    private final String paymentFrequency;
    @JacksonXmlProperty(localName = "Maturity_date")
    @JsonProperty("maturityDate")
    private final String maturityDate;
    @JacksonXmlProperty(localName = "Registration_number")
    @JsonProperty("registrationNumber")
    private final String registrationNumber;
    @JacksonXmlProperty(localName = "ISIN")
    @JsonProperty("isin")
    private final String isin;
    @JacksonXmlProperty(localName = "Nominal_cost")
    @JsonProperty("nominalCost")
    private final BigDecimal nominalCost;
    @JacksonXmlProperty(localName = "Issuer_location")
    @JsonProperty("issuerLocation")
    private final String issuerLocation;
    @JacksonXmlProperty(localName = "Default")
    @JsonProperty("defaults")
    private final String defaults;
    @JacksonXmlProperty(localName = "Bankrupycy_of_the_issuer")
    @JsonProperty("bankrupycyOfTheIssuer")
    private final String bankruptcyOfTheIssuer;
    @JacksonXmlProperty(localName = "Credit_type")
    @JsonProperty("creditType")
    private final String creditType;
    @JacksonXmlProperty(localName = "Average_interest_rate")
    @JsonProperty("averageInterestRate")
    private final String averageInterestRate;
    @JacksonXmlProperty(localName = "Avarage_repayment_date")
    @JsonProperty("avarageRepaymentDate")
    private final String avarageRepaymentDate;
    @JacksonXmlProperty(localName = "Average_repayment_date")
    @JsonProperty("averageRepaymentDate")   //Добавлены два одинаковых поля из разных типов (опечатка Average / Avarage)
    private final String averageRepaymentDate;
    @JacksonXmlProperty(localName = "Debt_repayment_method")
    @JsonProperty("debtRepaymentMethod")
    private final String debtRepaymentMethod;
    @JacksonXmlProperty(localName = "Availability")
    @JsonProperty("availability")
    private final String availability;
    @JacksonXmlProperty(localName = "Description_pledges")
    @JsonProperty("descriptionPledges")
    private final DescriptionPledge descriptionPledges;
    @JacksonXmlProperty(localName = "KD")
    @JsonProperty("kd")
    private final String kd;
    @JacksonXmlProperty(localName = "Name_CB")
    @JsonProperty("nameCb")
    private final String nameCb;
    @JacksonXmlProperty(localName = "Balance_String")
    @JsonProperty("balanceString")
    private final String balanceString;
    @JacksonXmlProperty(localName = "Date_KD")
    @JsonProperty("dateKD")
    private final String dateKD;
    @JacksonXmlProperty(localName = "Amount_of_balance")
    @JsonProperty("amountOfBalance")
    private final BigDecimal amountOfBalance;
    @JacksonXmlProperty(localName = "Document_storage")
    @JsonProperty("documentStorage")
    private final String documentStorage;
    @JacksonXmlProperty(localName = "Overdue")
    @JsonProperty("overdue")
    private final Long overdue;
    @JacksonXmlProperty(localName = "Trial")
    @JsonProperty("trial")
    private final String trial;
    @JacksonXmlProperty(localName = "End_date")
    @JsonProperty("endDate")
    private final String endDate;
    @JacksonXmlProperty(localName = "Mark")
    @JsonProperty("mark")
    private final String mark;
    @JacksonXmlProperty(localName = "Model")
    @JsonProperty("model")
    private final String model;
    @JacksonXmlProperty(localName = "Color")
    @JsonProperty("color")
    private final String color;
    @JacksonXmlProperty(localName = "Year_of_issue")
    @JsonProperty("yearOfIssue")
    private final Integer yearOfIssue;
    @JacksonXmlProperty(localName = "Mileage")
    @JsonProperty("mileage")
    private final BigDecimal mileage;
    @JacksonXmlProperty(localName = "Engine_capacity")
    @JsonProperty("engineCapacity")
    private final BigDecimal engineCapacity;
    @JacksonXmlProperty(localName = "Car_body")
    @JsonProperty("carBody")
    private final String carBody;
    @JacksonXmlProperty(localName = "Steering_wheel")
    @JsonProperty("steeringWheel")
    private final String steeringWheel;
    @JacksonXmlProperty(localName = "Transmission")
    @JsonProperty("transmission")
    private final String transmission;
    @JacksonXmlProperty(localName = "Engine_power")
    @JsonProperty("enginePower")
    private final BigDecimal enginePower;
    @JacksonXmlProperty(localName = "Engine_type")
    @JsonProperty("engineType")
    private final String engineType;
    @JacksonXmlProperty(localName = "Type_of_drive")
    @JsonProperty("typeOfDrive")
    private final String typeOfDrive;
    @JacksonXmlProperty(localName = "VIN")
    @JsonProperty("vin")
    private final String vin;
    @JacksonXmlProperty(localName = "Name")
    @JsonProperty("name")
    private final String name;
    @JacksonXmlProperty(localName = "Country")
    @JsonProperty("country")
    private final String country;
    @JacksonXmlProperty(localName = "Series")
    @JsonProperty("series")
    private final String series;
    @JacksonXmlProperty(localName = "Metal")
    @JsonProperty("metal")
    private final String metal;
    @JacksonXmlProperty(localName = "Sample")
    @JsonProperty("sample")
    private final String sample;
    @JacksonXmlProperty(localName = "Weight")
    @JsonProperty("weight")
    private final BigDecimal weight;
    @JacksonXmlProperty(localName = "Categor")
    @JsonProperty("categor")
    private final String category;
    @JacksonXmlProperty(localName = "Readiness")
    @JsonProperty("readiness")
    private final String readiness;
    @JacksonXmlProperty(localName = "Garage_type")
    @JsonProperty("garageType")
    private final String garageType;
    @JacksonXmlProperty(localName = "Type_of_parking")
    @JsonProperty("typeOfParking")
    private final String typeOfParking;
    @JacksonXmlProperty(localName = "Presence_of_security")
    @JsonProperty("presenceOfSecurity")
    private final String presenceOfSecurity;
    @JacksonXmlProperty(localName = "Total_weight")
    @JsonProperty("totalWeight")
    private final BigDecimal totalWeight;
    @JacksonXmlProperty(localName = "Name_stone")
    @JsonProperty("nameStone")
    private final String nameStone;
    @JacksonXmlProperty(localName = "Number_of_floors")
    @JsonProperty("numberOfFloors")
    private final Integer numberOfFloors;
    @JacksonXmlProperty(localName = "More_about_number_of_floors")
    @JsonProperty("moreAboutNumberOfFloors")
    private final String moreAboutNumberOfFloors;
    @JacksonXmlProperty(localName = "Wall_material")
    @JsonProperty("wallMaterial")
    private final String wallMaterial;
    @JacksonXmlProperty(localName = "Roofing_material")
    @JsonProperty("roofingMaterial")
    private final String roofingMaterial;
    @JacksonXmlProperty(localName = "Transport_accessibility")
    @JsonProperty("transportAccessibility")
    private final String transportAccessibility;
    @JacksonXmlProperty(localName = "Foundation")
    @JsonProperty("foundation")
    private final String foundation;
    @JacksonXmlProperty(localName = "Name_rightholder")
    @JsonProperty("nameRightholder")
    private final String nameRightHolder;
    @JacksonXmlProperty(localName = "Number_of_svidet")
    @JsonProperty("numberOfSvidet")
    private final Long numberOfSvidet;
    @JacksonXmlProperty(localName = "Fund_name")
    @JsonProperty("fundName")
    private final String fundName;
    @JacksonXmlProperty(localName = "Fund_category")
    @JsonProperty("fundCategory")
    private final String fundCategory;
    @JacksonXmlProperty(localName = "Fund_type")
    @JsonProperty("fundType")
    private final String fundType;
    @JacksonXmlProperty(localName = "Term_trust_management")
    @JsonProperty("termTrustManagement")
    private final String termTrustManagement;
    @JacksonXmlProperty(localName = "Number_of_shares")
    @JsonProperty("numberOfShares")
    private final Long numberOfShares;
    @JacksonXmlProperty(localName = "Shares_percentage")
    @JsonProperty("sharesPercentage")
    private final BigDecimal partOfAllShares;
    @JacksonXmlProperty(localName = "Storage_of_shares")
    @JsonProperty("storageOfShares")
    private final String storageOfShare;
    @JacksonXmlProperty(localName = "Management_company_name")
    @JsonProperty("managementCompanyName")
    private final String managementCompanyName;
    @JacksonXmlProperty(localName = "Intervals")
    @JsonProperty("intervals")
    private final String intervals;
    @JacksonXmlProperty(localName = "Main_assets_of_the_fund")
    @JsonProperty("mainAssetsOfTheFund")
    private final String mainAssets;
    @JacksonXmlProperty(localName = "Land_category")
    @JsonProperty("landCategory")
    private final String landCategory;
    @JacksonXmlProperty(localName = "Permitted_use")
    @JsonProperty("permittedUse")
    private final String permittedUse;
    @JacksonXmlProperty(localName = "Distance_to_the_regional_center")
    @JsonProperty("distanceToTheRegionalCenter")
    private final BigDecimal distanceToCenter;
    @JacksonXmlProperty(localName = "Borrower_name")
    @JsonProperty("borrowerName")
    private final String borrowerName;
    @JacksonXmlProperty(localName = "Borrower_INN")
    @JsonProperty("borrowerInn")
    private final String borrowerInn;
    @JacksonXmlProperty(localName = "ISU_name")
    @JsonProperty("isuName")
    private final String isuName;
    @JacksonXmlProperty(localName = "Management_company")
    @JsonProperty("managementCompany")
    private final String managementCompany;
    @JacksonXmlProperty(localName = "Percentage")
    @JsonProperty("percentage")
    private final BigDecimal partOfAllAmount;
    @JacksonXmlProperty(localName = "Mortgage_coverage")
    @JsonProperty("mortgageCoverage")
    private final String mortgageCoverage;
    @JacksonXmlProperty(localName = "Type_CB")
    @JsonProperty("typeCb")
    private final String typeCb;
    @JacksonXmlProperty(localName = "Issuer")
    @JsonProperty("issuer")
    private final String issuer;
    @JacksonXmlProperty(localName = "Number")
    @JsonProperty("number")
    private final String number;
    @JacksonXmlProperty(localName = "Term")
    @JsonProperty("term")
    private final String term;
    @JacksonXmlProperty(localName = "Supporting_documents")
    @JsonProperty("supportingDocuments")
    private final String supportingDocuments;
    @JacksonXmlProperty(localName = "Counterparty")
    @JsonProperty("counterparty")
    private final String counterparty;
    @JacksonXmlProperty(localName = "Association_name")
    @JsonProperty("associationName")
    private final String associationName;
    @JacksonXmlProperty(localName = "Association_INN")
    @JsonProperty("associationInn")
    private final String associationInn;
    @JacksonXmlProperty(localName = "Share")
    @JsonProperty("share")
    private final BigDecimal share;
    @JacksonXmlProperty(localName = "Share_FO")
    @JsonProperty("shareFo")
    private final BigDecimal shareFO;
    @JacksonXmlProperty(localName = "Real_activity")
    @JsonProperty("realActivity")
    private final String realActivity;
    @JacksonXmlProperty(localName = "Association_location")
    @JsonProperty("associationLocation")
    private final String associationLocation;
    @JacksonXmlProperty(localName = "Turnover_limit")
    @JsonProperty("turnoverLimit")
    private final String turnOverLimit;
    @JacksonXmlProperty(localName = "Type_of_transport")
    @JsonProperty("typeOfTransport")
    private final String typeOfTransport;
    @JacksonXmlProperty(localName = "Type_things")
    @JsonProperty("typeThings")
    private final String typeThings;
    @JacksonXmlProperty(localName = "Balance_date")
    @JsonProperty("balanceDate")
    private final String balanceDate;
    @JacksonXmlProperty(localName = "Weight_average_interest_rate")
    @JsonProperty("weightAverageInterestRate")
    private final BigDecimal weightAverageInterestRate;

    public AssetFull(
            @JacksonXmlProperty(localName = "About")
            @JsonProperty("about") String about,
            @JacksonXmlProperty(localName = "Type_asset")
            @JsonProperty("typeAsset") Integer typeAsset,
            @JacksonXmlProperty(localName = "ID_SYA")
            @JsonProperty("idSya") String idSya,
            @JacksonXmlProperty(localName = "Name_asset")
            @JsonProperty("nameAsset") String nameAsset,
            @JacksonXmlProperty(localName = "Location_asset")
            @JsonProperty("locationAsset") String locationAsset,
            @JacksonXmlProperty(localName = "Name_FO_asset")
            @JsonProperty("nameFoAsset") String nameFoAsset,
            @JacksonXmlProperty(localName = "About_asset")
            @JsonProperty("aboutAsset") String aboutAsset,
            @JacksonXmlProperty(localName = "Soon_asset")
            @JsonProperty("soonAsset") String soonAsset,
            @JacksonXmlProperty(localName = "Deposits")
            @JsonProperty("deposits") String deposits,
            @JacksonXmlProperty(localName = "Other")
            @JsonProperty("other") String other,
            @JacksonXmlProperty(localName = "Restrictions_asset")
            @JsonProperty("restrictionsAsset") String restrictionsAsset,
            @JacksonXmlProperty(localName = "Type_of_object")
            @JsonProperty("typeOfObject") String typeOfObject,
            @JacksonXmlProperty(localName = "Square")
            @JsonProperty("square") BigDecimal square,
            @JacksonXmlProperty(localName = "Address")
            @JsonProperty("address") String address,
            @JacksonXmlProperty(localName = "Cadastral_number")
            @JsonProperty("cadastralNumber") String cadastralNumber,
            @JacksonXmlProperty(localName = "Year_built")
            @JsonProperty("yearBuilt") Integer yearBuilt,
            @JacksonXmlProperty(localName = "Number_of_rooms")
            @JsonProperty("numberOfRooms") Integer numberOfRooms,
            @JacksonXmlProperty(localName = "Floor_number")
            @JsonProperty("floorNumber") String floorNumber,
            @JacksonXmlProperty(localName = "Floors")
            @JsonProperty("floors") Integer floors,
            @JacksonXmlProperty(localName = "Ceiling_height")
            @JsonProperty("ceilingHeight") BigDecimal ceilingHeight,
            @JacksonXmlProperty(localName = "Living_space")
            @JsonProperty("livingSpace") BigDecimal livingSpace,
            @JacksonXmlProperty(localName = "Kitchen_area")
            @JsonProperty("kitchenArea") BigDecimal kitchenArea,
            @JacksonXmlProperty(localName = "Elevator")
            @JsonProperty("elevator") String elevator,
            @JacksonXmlProperty(localName = "House_type")
            @JsonProperty("houseType") String houseType,
            @JacksonXmlProperty(localName = "Balcony")
            @JsonProperty("balcony") String balcony,
            @JacksonXmlProperty(localName = "View_from_the_window")
            @JsonProperty("viewFromTheWindow") String viewFromTheWindow,
            @JacksonXmlProperty(localName = "Communications")
            @JsonProperty("communications") String communications,
            @JacksonXmlProperty(localName = "Assessment_value")
            @JsonProperty("assessmentValue") BigDecimal assessmentValue,
            @JacksonXmlProperty(localName = "Assessment_date")
            @JsonProperty("assessmentDate") String assessmentDate,
            @JacksonXmlProperty(localName = "Type_of_asset")
            @JsonProperty("typeOfAsset") String typeOfAsset,
            @JacksonXmlProperty(localName = "Name_product")
            @JsonProperty("nameProduct") String productName,
            @JacksonXmlProperty(localName = "Author")
            @JsonProperty("author") String author,
            @JacksonXmlProperty(localName = "Date")
            @JsonProperty("date") String date,
            @JacksonXmlProperty(localName = "Size")
            @JsonProperty("size") String size,
            @JacksonXmlProperty(localName = "Material")
            @JsonProperty("material") String material,
            @JacksonXmlProperty(localName = "Expertise_information")
            @JsonProperty("expertiseInformation") String expertiseInfo,
            @JacksonXmlProperty(localName = "View")
            @JsonProperty("view") String view,
            @JacksonXmlProperty(localName = "Promiser")
            @JsonProperty("promiser") String promiser,
            @JacksonXmlProperty(localName = "Promiser_INN")
            @JsonProperty("promiserInn") String promiserInn,
            @JacksonXmlProperty(localName = "Interest_rate")
            @JsonProperty("interestRate") BigDecimal interestRate,
            @JacksonXmlProperty(localName = "Bill_amount")
            @JsonProperty("billAmount") BigDecimal billAmount,
            @JacksonXmlProperty(localName = "Currency")
            @JsonProperty("currency") String currency,
            @JacksonXmlProperty(localName = "Date_of_preparation")
            @JsonProperty("dateOfPreparation") String dateOfPreparation,
            @JacksonXmlProperty(localName = "Place_of_compilation")
            @JsonProperty("placeOfCompilation") String placeOfCompilation,
            @JacksonXmlProperty(localName = "Obligated_person_name")
            @JsonProperty("obligatedPersonName") String obligatedPersonName,
            @JacksonXmlProperty(localName = "Avalist")
            @JsonProperty("avalist") String avalist,
            @JacksonXmlProperty(localName = "Payment_term")
            @JsonProperty("paymentTerm") String paymentTerm,
            @JacksonXmlProperty(localName = "Place_of_payment")
            @JsonProperty("placeOfPayment") String placeOfPayment,
            @JacksonXmlProperty(localName = "Promiser_location")
            @JsonProperty("promiserLocation") String promiserLocation,
            @JacksonXmlProperty(localName = "Storage")
            @JsonProperty("storage") String storage,
            @JacksonXmlProperty(localName = "Promiser_bankrot")
            @JsonProperty("promiserBankrot") String promiserBankrot,
            @JacksonXmlProperty(localName = "URL")
            @JsonProperty("url") String url,
            @JacksonXmlProperty(localName = "Type_bond")
            @JsonProperty("typeBond") String typeBond,
            @JacksonXmlProperty(localName = "Issuer_name")
            @JsonProperty("issuerName") String issuerName,
            @JacksonXmlProperty(localName = "Issuer_INN")
            @JsonProperty("issuerInn") String issuerInn,
            @JacksonXmlProperty(localName = "Amount")
            @JsonProperty("amount") BigDecimal amount,
            @JacksonXmlProperty(localName = "Coupon_rate")
            @JsonProperty("couponRate") String couponRate,
            @JacksonXmlProperty(localName = "Payment_frequency")
            @JsonProperty("paymentFrequency") String paymentFrequency,
            @JacksonXmlProperty(localName = "Maturity_date")
            @JsonProperty("maturityDate") String maturityDate,
            @JacksonXmlProperty(localName = "Registration_number")
            @JsonProperty("registrationNumber") String registrationNumber,
            @JacksonXmlProperty(localName = "ISIN")
            @JsonProperty("isin") String isin,
            @JacksonXmlProperty(localName = "Nominal_cost")
            @JsonProperty("nominalCost") BigDecimal nominalCost,
            @JacksonXmlProperty(localName = "Issuer_location")
            @JsonProperty("issuerLocation") String issuerLocation,
            @JacksonXmlProperty(localName = "Default")
            @JsonProperty("defaults") String defaults,
            @JacksonXmlProperty(localName = "Bankrupycy_of_the_issuer")
            @JsonProperty("bankrupycyOfTheIssuer") String bankruptcyOfTheIssuer,
            @JacksonXmlProperty(localName = "Credit_type")
            @JsonProperty("creditType") String creditType,
            @JacksonXmlProperty(localName = "Average_interest_rate")
            @JsonProperty("averageInterestRate") String averageInterestRate,
            @JacksonXmlProperty(localName = "Avarage_repayment_date")
            @JsonProperty("avarageRepaymentDate") String avarageRepaymentDate,
            @JacksonXmlProperty(localName = "Average_repayment_date")
            @JsonProperty("averageRepaymentDate") String averageRepaymentDate,
            @JacksonXmlProperty(localName = "Debt_repayment_method")
            @JsonProperty("debtRepaymentMethod") String debtRepaymentMethod,
            @JacksonXmlProperty(localName = "Availability")
            @JsonProperty("availability") String availability,
            @JacksonXmlProperty(localName = "Description_pledges")
            @JsonProperty("descriptionPledges") DescriptionPledge descriptionPledges,
            @JacksonXmlProperty(localName = "KD")
            @JsonProperty("kd") String kd,
            @JacksonXmlProperty(localName = "Name_CB")
            @JsonProperty("nameCb") String nameCb,
            @JacksonXmlProperty(localName = "Balance_String")
            @JsonProperty("balanceString") String balanceString,
            @JacksonXmlProperty(localName = "Date_KD")
            @JsonProperty("dateKD") String dateKD,
            @JacksonXmlProperty(localName = "Amount_of_balance")
            @JsonProperty("amountOfBalance") BigDecimal amountOfBalance,
            @JacksonXmlProperty(localName = "Document_storage")
            @JsonProperty("documentStorage") String documentStorage,
            @JacksonXmlProperty(localName = "Overdue")
            @JsonProperty("overdue") Long overdue,
            @JacksonXmlProperty(localName = "Trial")
            @JsonProperty("trial") String trial,
            @JacksonXmlProperty(localName = "End_date")
            @JsonProperty("endDate") String endDate,
            @JacksonXmlProperty(localName = "Mark")
            @JsonProperty("mark") String mark,
            @JacksonXmlProperty(localName = "Model")
            @JsonProperty("model") String model,
            @JacksonXmlProperty(localName = "Color")
            @JsonProperty("color") String color,
            @JacksonXmlProperty(localName = "Year_of_issue")
            @JsonProperty("yearOfIssue") Integer yearOfIssue,
            @JacksonXmlProperty(localName = "Mileage")
            @JsonProperty("mileage") BigDecimal mileage,
            @JacksonXmlProperty(localName = "Engine_capacity")
            @JsonProperty("engineCapacity") BigDecimal engineCapacity,
            @JacksonXmlProperty(localName = "Car_body")
            @JsonProperty("carBody") String carBody,
            @JacksonXmlProperty(localName = "Steering_wheel")
            @JsonProperty("steeringWheel") String steeringWheel,
            @JacksonXmlProperty(localName = "Transmission")
            @JsonProperty("transmission") String transmission,
            @JacksonXmlProperty(localName = "Engine_power")
            @JsonProperty("enginePower") BigDecimal enginePower,
            @JacksonXmlProperty(localName = "Engine_type")
            @JsonProperty("engineType") String engineType,
            @JacksonXmlProperty(localName = "Type_of_drive")
            @JsonProperty("typeOfDrive") String typeOfDrive,
            @JacksonXmlProperty(localName = "VIN")
            @JsonProperty("vin") String vin,
            @JacksonXmlProperty(localName = "Name")
            @JsonProperty("name") String name,
            @JacksonXmlProperty(localName = "Country")
            @JsonProperty("country") String country,
            @JacksonXmlProperty(localName = "Series")
            @JsonProperty("series") String series,
            @JacksonXmlProperty(localName = "Metal")
            @JsonProperty("metal") String metal,
            @JacksonXmlProperty(localName = "Sample")
            @JsonProperty("sample") String sample,
            @JacksonXmlProperty(localName = "Weight")
            @JsonProperty("weight") BigDecimal weight,
            @JacksonXmlProperty(localName = "Categor")
            @JsonProperty("categor") String category,
            @JacksonXmlProperty(localName = "Readiness")
            @JsonProperty("readiness") String readiness,
            @JacksonXmlProperty(localName = "Garage_type")
            @JsonProperty("garageType") String garageType,
            @JacksonXmlProperty(localName = "Type_of_parking")
            @JsonProperty("typeOfParking") String typeOfParking,
            @JacksonXmlProperty(localName = "Presence_of_security")
            @JsonProperty("presenceOfSecurity") String presenceOfSecurity,
            @JacksonXmlProperty(localName = "Total_weight")
            @JsonProperty("totalWeight") BigDecimal totalWeight,
            @JacksonXmlProperty(localName = "Name_stone")
            @JsonProperty("nameStone") String nameStone,
            @JacksonXmlProperty(localName = "Number_of_floors")
            @JsonProperty("numberOfFloors") Integer numberOfFloors,
            @JacksonXmlProperty(localName = "More_about_number_of_floors")
            @JsonProperty("moreAboutNumberOfFloors") String moreAboutNumberOfFloors,
            @JacksonXmlProperty(localName = "Wall_material")
            @JsonProperty("wallMaterial") String wallMaterial,
            @JacksonXmlProperty(localName = "Roofing_material")
            @JsonProperty("roofingMaterial") String roofingMaterial,
            @JacksonXmlProperty(localName = "Transport_accessibility")
            @JsonProperty("transportAccessibility") String transportAccessibility,
            @JacksonXmlProperty(localName = "Foundation")
            @JsonProperty("foundation") String foundation,
            @JacksonXmlProperty(localName = "Name_rightholder")
            @JsonProperty("nameRightholder") String nameRightHolder,
            @JacksonXmlProperty(localName = "Number_of_svidet")
            @JsonProperty("numberOfSvidet") Long numberOfSvidet,
            @JacksonXmlProperty(localName = "Fund_name")
            @JsonProperty("fundName") String fundName,
            @JacksonXmlProperty(localName = "Fund_category")
            @JsonProperty("fundCategory") String fundCategory,
            @JacksonXmlProperty(localName = "Fund_type")
            @JsonProperty("fundType") String fundType,
            @JacksonXmlProperty(localName = "Term_trust_management")
            @JsonProperty("termTrustManagement") String termTrustManagement,
            @JacksonXmlProperty(localName = "Number_of_shares")
            @JsonProperty("numberOfShares") Long numberOfShares,
            @JacksonXmlProperty(localName = "Shares_percentage")
            @JsonProperty("sharesPercentage") BigDecimal partOfAllShares,
            @JacksonXmlProperty(localName = "Storage_of_shares")
            @JsonProperty("storageOfShares") String storageOfShare,
            @JacksonXmlProperty(localName = "Management_company_name")
            @JsonProperty("managementCompanyName") String managementCompanyName,
            @JacksonXmlProperty(localName = "Intervals")
            @JsonProperty("intervals") String intervals,
            @JacksonXmlProperty(localName = "Main_assets_of_the_fund")
            @JsonProperty("mainAssetsOfTheFund") String mainAssets,
            @JacksonXmlProperty(localName = "Land_category")
            @JsonProperty("landCategory") String landCategory,
            @JacksonXmlProperty(localName = "Permitted_use")
            @JsonProperty("permittedUse") String permittedUse,
            @JacksonXmlProperty(localName = "Distance_to_the_regional_center")
            @JsonProperty("distanceToTheRegionalCenter") BigDecimal distanceToCenter,
            @JacksonXmlProperty(localName = "Borrower_name")
            @JsonProperty("borrowerName") String borrowerName,
            @JacksonXmlProperty(localName = "Borrower_INN")
            @JsonProperty("borrowerInn") String borrowerInn,
            @JacksonXmlProperty(localName = "ISU_name")
            @JsonProperty("isuName") String isuName,
            @JacksonXmlProperty(localName = "Management_company")
            @JsonProperty("managementCompany") String managementCompany,
            @JacksonXmlProperty(localName = "Percentage")
            @JsonProperty("percentage") BigDecimal partOfAllAmount,
            @JacksonXmlProperty(localName = "Mortgage_coverage")
            @JsonProperty("mortgageCoverage") String mortgageCoverage,
            @JacksonXmlProperty(localName = "Type_CB")
            @JsonProperty("typeCb") String typeCb,
            @JacksonXmlProperty(localName = "Issuer")
            @JsonProperty("issuer") String issuer,
            @JacksonXmlProperty(localName = "Number")
            @JsonProperty("number") String number,
            @JacksonXmlProperty(localName = "Term")
            @JsonProperty("term") String term,
            @JacksonXmlProperty(localName = "Supporting_documents")
            @JsonProperty("supportingDocuments") String supportingDocuments,
            @JacksonXmlProperty(localName = "Counterparty")
            @JsonProperty("counterparty") String counterparty,
            @JacksonXmlProperty(localName = "Association_name")
            @JsonProperty("associationName") String associationName,
            @JacksonXmlProperty(localName = "Association_INN")
            @JsonProperty("associationInn") String associationInn,
            @JacksonXmlProperty(localName = "Share")
            @JsonProperty("share") BigDecimal share,
            @JacksonXmlProperty(localName = "Share_FO")
            @JsonProperty("shareFo") BigDecimal shareFO,
            @JacksonXmlProperty(localName = "Real_activity")
            @JsonProperty("realActivity") String realActivity,
            @JacksonXmlProperty(localName = "Association_location")
            @JsonProperty("associationLocation") String associationLocation,
            @JacksonXmlProperty(localName = "Turnover_limit")
            @JsonProperty("turnoverLimit") String turnOverLimit,
            @JacksonXmlProperty(localName = "Type_of_transport")
            @JsonProperty("typeOfTransport") String typeOfTransport,
            @JacksonXmlProperty(localName = "Type_things")
            @JsonProperty("typeThings") String typeThings,
            @JacksonXmlProperty(localName = "Balance_date")
            @JsonProperty("balanceDate") String balanceDate,
            @JacksonXmlProperty(localName = "Weight_average_interest_rate")
            @JsonProperty("weightAverageInterestRate") BigDecimal weightAverageInterestRate) {
        this.about = about;
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
        this.typeOfObject = typeOfObject;
        this.square = square;
        this.address = address;
        this.cadastralNumber = cadastralNumber;
        this.yearBuilt = yearBuilt;
        this.numberOfRooms = numberOfRooms;
        this.floorNumber = floorNumber;
        this.floors = floors;
        this.ceilingHeight = ceilingHeight;
        this.livingSpace = livingSpace;
        this.kitchenArea = kitchenArea;
        this.elevator = elevator;
        this.houseType = houseType;
        this.balcony = balcony;
        this.viewFromTheWindow = viewFromTheWindow;
        this.communications = communications;
        this.assessmentValue = assessmentValue;
        this.assessmentDate = assessmentDate;
        this.typeOfAsset = typeOfAsset;
        this.productName = productName;
        this.author = author;
        this.date = date;
        this.size = size;
        this.material = material;
        this.expertiseInfo = expertiseInfo;
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
        this.typeBond = typeBond;
        this.issuerName = issuerName;
        this.issuerInn = issuerInn;
        this.amount = amount;
        this.couponRate = couponRate;
        this.paymentFrequency = paymentFrequency;
        this.maturityDate = maturityDate;
        this.registrationNumber = registrationNumber;
        this.isin = isin;
        this.nominalCost = nominalCost;
        this.issuerLocation = issuerLocation;
        this.defaults = defaults;
        this.bankruptcyOfTheIssuer = bankruptcyOfTheIssuer;
        this.creditType = creditType;
        this.averageInterestRate = averageInterestRate;
        this.avarageRepaymentDate = avarageRepaymentDate;
        this.averageRepaymentDate = averageRepaymentDate;
        this.debtRepaymentMethod = debtRepaymentMethod;
        this.availability = availability;
        this.descriptionPledges = descriptionPledges;
        this.kd = kd;
        this.nameCb = nameCb;
        this.balanceString = balanceString;
        this.dateKD = dateKD;
        this.amountOfBalance = amountOfBalance;
        this.documentStorage = documentStorage;
        this.overdue = overdue;
        this.trial = trial;
        this.endDate = endDate;
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
        this.name = name;
        this.country = country;
        this.series = series;
        this.metal = metal;
        this.sample = sample;
        this.weight = weight;
        this.category = category;
        this.readiness = readiness;
        this.garageType = garageType;
        this.typeOfParking = typeOfParking;
        this.presenceOfSecurity = presenceOfSecurity;
        this.totalWeight = totalWeight;
        this.nameStone = nameStone;
        this.numberOfFloors = numberOfFloors;
        this.moreAboutNumberOfFloors = moreAboutNumberOfFloors;
        this.wallMaterial = wallMaterial;
        this.roofingMaterial = roofingMaterial;
        this.transportAccessibility = transportAccessibility;
        this.foundation = foundation;
        this.nameRightHolder = nameRightHolder;
        this.numberOfSvidet = numberOfSvidet;
        this.fundName = fundName;
        this.fundCategory = fundCategory;
        this.fundType = fundType;
        this.termTrustManagement = termTrustManagement;
        this.numberOfShares = numberOfShares;
        this.partOfAllShares = partOfAllShares;
        this.storageOfShare = storageOfShare;
        this.managementCompanyName = managementCompanyName;
        this.intervals = intervals;
        this.mainAssets = mainAssets;
        this.landCategory = landCategory;
        this.permittedUse = permittedUse;
        this.distanceToCenter = distanceToCenter;
        this.borrowerName = borrowerName;
        this.borrowerInn = borrowerInn;
        this.isuName = isuName;
        this.managementCompany = managementCompany;
        this.partOfAllAmount = partOfAllAmount;
        this.mortgageCoverage = mortgageCoverage;
        this.typeCb = typeCb;
        this.issuer = issuer;
        this.number = number;
        this.term = term;
        this.supportingDocuments = supportingDocuments;
        this.counterparty = counterparty;
        this.associationName = associationName;
        this.associationInn = associationInn;
        this.share = share;
        this.shareFO = shareFO;
        this.realActivity = realActivity;
        this.associationLocation = associationLocation;
        this.turnOverLimit = turnOverLimit;
        this.typeOfTransport = typeOfTransport;
        this.typeThings = typeThings;
        this.balanceDate = balanceDate;
        this.weightAverageInterestRate = weightAverageInterestRate;
    }

    public String getAbout() {
        return about;
    }

    public String getAssessmentDate() {
        return assessmentDate;
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

    public String getTypeOfObject() {
        return typeOfObject;
    }

    public BigDecimal getSquare() {
        return square;
    }

    public String getAddress() {
        return address;
    }

    public String getCadastralNumber() {
        return cadastralNumber;
    }

    public Integer getYearBuilt() {
        return yearBuilt;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public Integer getFloors() {
        return floors;
    }

    public BigDecimal getCeilingHeight() {
        return ceilingHeight;
    }

    public BigDecimal getLivingSpace() {
        return livingSpace;
    }

    public BigDecimal getKitchenArea() {
        return kitchenArea;
    }

    public String getElevator() {
        return elevator;
    }

    public String getHouseType() {
        return houseType;
    }

    public String getBalcony() {
        return balcony;
    }

    public String getViewFromTheWindow() {
        return viewFromTheWindow;
    }

    public String getCommunications() {
        return communications;
    }

    public BigDecimal getAssessmentValue() {
        return assessmentValue;
    }

    public String getTypeOfAsset() {
        return typeOfAsset;
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

    public String getTypeBond() {
        return typeBond;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public String getIssuerInn() {
        return issuerInn;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCouponRate() {
        return couponRate;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getIsin() {
        return isin;
    }

    public BigDecimal getNominalCost() {
        return nominalCost;
    }

    public String getIssuerLocation() {
        return issuerLocation;
    }

    public String getDefaults() {
        return defaults;
    }

    public String getBankruptcyOfTheIssuer() {
        return bankruptcyOfTheIssuer;
    }

    public String getCreditType() {
        return creditType;
    }

    public String getAverageInterestRate() {
        return averageInterestRate;
    }

    public String getAvarageRepaymentDate() {
        return avarageRepaymentDate;
    }

    public String getAverageRepaymentDate() {
        return averageRepaymentDate;
    }

    public String getDebtRepaymentMethod() {
        return debtRepaymentMethod;
    }

    public String getAvailability() {
        return availability;
    }

    public DescriptionPledge getDescriptionPledges() {
        return descriptionPledges;
    }

    public String getKd() {
        return kd;
    }

    public String getNameCb() {
        return nameCb;
    }

    public String getBalanceString() {
        return balanceString;
    }

    public String getDateKD() {
        return dateKD;
    }

    public BigDecimal getAmountOfBalance() {
        return amountOfBalance;
    }

    public String getDocumentStorage() {
        return documentStorage;
    }

    public Long getOverdue() {
        return overdue;
    }

    public String getTrial() {
        return trial;
    }

    public String getEndDate() {
        return endDate;
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

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
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

    public BigDecimal getWeight() {
        return weight;
    }

    public String getCategory() {
        return category;
    }

    public String getReadiness() {
        return readiness;
    }

    public String getGarageType() {
        return garageType;
    }

    public String getTypeOfParking() {
        return typeOfParking;
    }

    public String getPresenceOfSecurity() {
        return presenceOfSecurity;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public String getNameStone() {
        return nameStone;
    }

    public Integer getNumberOfFloors() {
        return numberOfFloors;
    }

    public String getMoreAboutNumberOfFloors() {
        return moreAboutNumberOfFloors;
    }

    public String getWallMaterial() {
        return wallMaterial;
    }

    public String getRoofingMaterial() {
        return roofingMaterial;
    }

    public String getTransportAccessibility() {
        return transportAccessibility;
    }

    public String getFoundation() {
        return foundation;
    }

    public String getNameRightHolder() {
        return nameRightHolder;
    }

    public Long getNumberOfSvidet() {
        return numberOfSvidet;
    }

    public String getFundName() {
        return fundName;
    }

    public String getFundCategory() {
        return fundCategory;
    }

    public String getFundType() {
        return fundType;
    }

    public String getTermTrustManagement() {
        return termTrustManagement;
    }

    public Long getNumberOfShares() {
        return numberOfShares;
    }

    public BigDecimal getPartOfAllShares() {
        return partOfAllShares;
    }

    public String getStorageOfShare() {
        return storageOfShare;
    }

    public String getManagementCompanyName() {
        return managementCompanyName;
    }

    public String getIntervals() {
        return intervals;
    }

    public String getMainAssets() {
        return mainAssets;
    }

    public String getLandCategory() {
        return landCategory;
    }

    public String getPermittedUse() {
        return permittedUse;
    }

    public BigDecimal getDistanceToCenter() {
        return distanceToCenter;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public String getBorrowerInn() {
        return borrowerInn;
    }

    public String getIsuName() {
        return isuName;
    }

    public String getManagementCompany() {
        return managementCompany;
    }

    public BigDecimal getPartOfAllAmount() {
        return partOfAllAmount;
    }

    public String getMortgageCoverage() {
        return mortgageCoverage;
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

    public String getSupportingDocuments() {
        return supportingDocuments;
    }

    public String getCounterparty() {
        return counterparty;
    }

    public String getAssociationName() {
        return associationName;
    }

    public String getAssociationInn() {
        return associationInn;
    }

    public BigDecimal getShare() {
        return share;
    }

    public BigDecimal getShareFO() {
        return shareFO;
    }

    public String getRealActivity() {
        return realActivity;
    }

    public String getAssociationLocation() {
        return associationLocation;
    }

    public String getTurnOverLimit() {
        return turnOverLimit;
    }

    public String getTypeOfTransport() {
        return typeOfTransport;
    }

    public String getTypeThings() {
        return typeThings;
    }

    public String getBalanceDate() {
        return balanceDate;
    }

    public BigDecimal getWeightAverageInterestRate() {
        return weightAverageInterestRate;
    }
}
