package com.baltinfo.radius.loadauction.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * Java class для десериализация xml-файла
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 23.12.2019
 */
public class Lot {

    @JacksonXmlProperty(localName = "ID")
    @JsonProperty("id")
    private final Long id;
    @JacksonXmlProperty(localName = "Number")
    @JsonProperty("number")
    private final Long number;
    @JacksonXmlProperty(localName = "URL_lot_1")
    @JsonProperty("urlLot1")
    private final String urlLot1;
    @JacksonXmlProperty(localName = "URL_lot_2")
    @JsonProperty("urlLot2")
    private final String urlLot2;
    @JacksonXmlProperty(localName = "URL_lot_Torgiasv")
    @JsonProperty("urllotTorgiasv")
    private final String urlLotTorgiAsv;
    @JacksonXmlProperty(localName = "Name")
    @JsonProperty("name")
    private final String lotName;
    @JacksonXmlProperty(localName = "Coordinate_longitude")
    @JsonProperty("coordinateLongitude")
    private final String coordinateLongitude;
    @JacksonXmlProperty(localName = "Coordinate_latitude")
    @JsonProperty("coordinateLatitude")
    private final String coordinateLatitude;
    @JacksonXmlProperty(localName = "Price_first")
    @JsonProperty("priceFirst")
    private final BigDecimal priceFirst;
    @JacksonXmlProperty(localName = "Amount")
    @JsonProperty("amount")
    private final Integer amount;
    @JacksonXmlProperty(localName = "Description")
    @JsonProperty("description")
    private final String description;
    @JacksonXmlProperty(localName = "Classifier")
    @JsonProperty("classifier")
    private final Integer classifier;
    @JacksonXmlProperty(localName = "Location")
    @JsonProperty("location")
    private final String location;
    @JacksonXmlProperty(localName = "Address")
    @JsonProperty("address")
    private final String address;
    @JacksonXmlProperty(localName = "Restrictions")
    @JsonProperty("restrictions")
    private final String restrictions;
    @JacksonXmlProperty(localName = "Additional_information")
    @JsonProperty("additionalInformation")
    private final String addInfo;
    @JacksonXmlProperty(localName = "Info_lot")
    @JsonProperty("infoLot")
    @JacksonXmlElementWrapper(useWrapping = false)
    private final List<LotInfo> lotsInfo;
    @JacksonXmlProperty(localName = "Documents")
    @JsonProperty("documents")
    private final Documents documents;
    @JacksonXmlProperty(localName = "Images")
    @JsonProperty("images")
    private final Images images;
    @JacksonXmlProperty(localName = "Assets")
    @JacksonXmlElementWrapper(localName = "Assets")
    @JsonProperty("assets")
    private final List<AssetFull> assetFullList;

    public Lot(@JacksonXmlProperty(localName = "ID")
               @JsonProperty("id") Long id,
               @JacksonXmlProperty(localName = "Number")
               @JsonProperty("number") Long number,
               @JacksonXmlProperty(localName = "URL_lot_1")
               @JsonProperty("urllot1") String urlLot1,
               @JacksonXmlProperty(localName = "URL_lot_2")
               @JsonProperty("urllot2") String urlLot2,
               @JacksonXmlProperty(localName = "URL_lot_Torgiasv")
               @JsonProperty("urllotTorgiasv") String urlLotTorgiAsv,
               @JacksonXmlProperty(localName = "Name")
               @JsonProperty("name") String lotName,
               @JacksonXmlProperty(localName = "Coordinate_longitude")
               @JsonProperty("coordinateLongitude") String coordinateLongitude,
               @JacksonXmlProperty(localName = "Coordinate_latitude")
               @JsonProperty("coordinateLatitude") String coordinateLatitude,
               @JacksonXmlProperty(localName = "Price_first")
               @JsonProperty("priceFirst") BigDecimal priceFirst,
               @JacksonXmlProperty(localName = "Amount")
               @JsonProperty("amount") Integer amount,
               @JacksonXmlProperty(localName = "Description")
               @JsonProperty("description") String description,
               @JacksonXmlProperty(localName = "Classifier")
               @JsonProperty("classifier") Integer classifier,
               @JacksonXmlProperty(localName = "Location")
               @JsonProperty("location") String location,
               @JacksonXmlProperty(localName = "Address")
               @JsonProperty("address") String address,
               @JacksonXmlProperty(localName = "Restrictions")
               @JsonProperty("restrictions") String restrictions,
               @JacksonXmlProperty(localName = "Additional_information")
               @JsonProperty("additionalInformation") String addInfo,
               @JacksonXmlProperty(localName = "Info_lot")
               @JsonProperty("infoLot") List<LotInfo> lotsInfo,
               @JacksonXmlProperty(localName = "Documents")
               @JsonProperty("documents") Documents documents,
               @JacksonXmlProperty(localName = "Images")
               @JsonProperty("images") Images images,
               @JacksonXmlProperty(localName = "Assets")
               @JsonProperty("assets") List<AssetFull> assetFullList) {
        this.id = id;
        this.number = number;
        this.urlLot1 = urlLot1;
        this.urlLot2 = urlLot2;
        this.urlLotTorgiAsv = urlLotTorgiAsv;
        this.lotName = lotName;
        this.coordinateLongitude = coordinateLongitude;
        this.coordinateLatitude = coordinateLatitude;
        this.priceFirst = priceFirst;
        this.amount = amount;
        this.description = description;
        this.classifier = classifier;
        this.location = location;
        this.address = address;
        this.restrictions = restrictions;
        this.addInfo = addInfo;
        this.lotsInfo = lotsInfo;
        this.documents = documents;
        this.images = images;
        this.assetFullList = assetFullList;
    }

    public Long getId() {
        return id;
    }

    public Long getNumber() {
        return number;
    }

    public String getUrlLot1() {
        return urlLot1;
    }

    public String getUrlLot2() {
        return urlLot2;
    }

    public String getUrlLotTorgiAsv() {
        return urlLotTorgiAsv;
    }

    public String getLotName() {
        return lotName;
    }

    public BigDecimal getPriceFirst() {
        return priceFirst;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Integer getClassifier() {
        return classifier;
    }

    public String getLocation() {
        return location;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public List<LotInfo> getLotsInfo() {
        return lotsInfo;
    }

    public Documents getDocuments() {
        return documents;
    }

    public Images getImages() {
        return images;
    }

    public List<AssetFull> getAssetFullList() {
        return assetFullList;
    }

    public String getAddress() {
        return address;
    }

    public String getCoordinateLongitude() {
        return coordinateLongitude;
    }

    public String getCoordinateLatitude() {
        return coordinateLatitude;
    }
}
