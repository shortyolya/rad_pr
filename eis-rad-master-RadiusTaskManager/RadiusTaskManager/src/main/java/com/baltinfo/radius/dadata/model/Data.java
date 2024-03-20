package com.baltinfo.radius.dadata.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>
 *    POJO class содержащий различные параметры адреса
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 09.12.2019
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {
    @JsonProperty("postal_code")
    private final String postalCode;
    @JsonProperty("country")
    private final String country;
    @JsonProperty("country_iso_code")
    private final String countryIsoCode;
    @JsonProperty("federal_district")
    private final Object federalDistrict;
    @JsonProperty("region_fias_id")
    private final String regionFiasId;
    @JsonProperty("region_kladr_id")
    private final String regionKladrId;
    @JsonProperty("region_iso_code")
    private final String regionIsoCode;
    @JsonProperty("region_with_type")
    private final String regionWithType;
    @JsonProperty("region_type")
    private final String regionType;
    @JsonProperty("region_type_full")
    private final String regionTypeFull;
    @JsonProperty("region")
    private final String region;
    @JsonProperty("area_fias_id")
    private final Object areaFiasId;
    @JsonProperty("area_kladr_id")
    private final Object areaKladrId;
    @JsonProperty("area_with_type")
    private final Object areaWithType;
    @JsonProperty("area_type")
    private final Object areaType;
    @JsonProperty("area_type_full")
    private final Object areaTypeFull;
    @JsonProperty("area")
    private final Object area;
    @JsonProperty("city_fias_id")
    private final String cityFiasId;
    @JsonProperty("city_kladr_id")
    private final String cityKladrId;
    @JsonProperty("city_with_type")
    private final String cityWithType;
    @JsonProperty("city_type")
    private final String cityType;
    @JsonProperty("city_type_full")
    private final String cityTypeFull;
    @JsonProperty("city")
    private final String city;
    @JsonProperty("city_area")
    private final Object cityArea;
    @JsonProperty("city_district_fias_id")
    private final Object cityDistrictFiasId;
    @JsonProperty("city_district_kladr_id")
    private final Object cityDistrictKladrId;
    @JsonProperty("city_district_with_type")
    private final Object cityDistrictWithType;
    @JsonProperty("city_district_type")
    private final Object cityDistrictType;
    @JsonProperty("city_district_type_full")
    private final Object cityDistrictTypeFull;
    @JsonProperty("city_district")
    private final Object cityDistrict;
    @JsonProperty("settlement_fias_id")
    private final Object settlementFiasId;
    @JsonProperty("settlement_kladr_id")
    private final Object settlementKladrId;
    @JsonProperty("settlement_with_type")
    private final Object settlementWithType;
    @JsonProperty("settlement_type")
    private final Object settlementType;
    @JsonProperty("settlement_type_full")
    private final Object settlementTypeFull;
    @JsonProperty("settlement")
    private final Object settlement;
    @JsonProperty("street_fias_id")
    private final String streetFiasId;
    @JsonProperty("street_kladr_id")
    private final String streetKladrId;
    @JsonProperty("street_with_type")
    private final String streetWithType;
    @JsonProperty("street_type")
    private final String streetType;
    @JsonProperty("street_type_full")
    private final String streetTypeFull;
    @JsonProperty("street")
    private final String street;
    @JsonProperty("house_fias_id")
    private final Object houseFiasId;
    @JsonProperty("house_kladr_id")
    private final Object houseKladrId;
    @JsonProperty("house_type")
    private final String houseType;
    @JsonProperty("house_type_full")
    private final String houseTypeFull;
    @JsonProperty("house")
    private final String house;
    @JsonProperty("block_type")
    private final Object blockType;
    @JsonProperty("block_type_full")
    private final Object blockTypeFull;
    @JsonProperty("block")
    private final Object block;
    @JsonProperty("flat_type")
    private final Object flatType;
    @JsonProperty("flat_type_full")
    private final Object flatTypeFull;
    @JsonProperty("flat")
    private final Object flat;
    @JsonProperty("flat_area")
    private final Object flatArea;
    @JsonProperty("square_meter_price")
    private final Object squareMeterPrice;
    @JsonProperty("flat_price")
    private final Object flatPrice;
    @JsonProperty("postal_box")
    private final Object postalBox;
    @JsonProperty("fias_id")
    private final String fiasId;
    @JsonProperty("fias_code")
    private final Object fiasCode;
    @JsonProperty("fias_level")
    private final String fiasLevel;
    @JsonProperty("fias_actuality_state")
    private final Object fiasActualityState;
    @JsonProperty("kladr_id")
    private final String kladrId;
    @JsonProperty("geoname_id")
    private final Object geonameId;
    @JsonProperty("capital_marker")
    private final String capitalMarker;
    @JsonProperty("okato")
    private final String okato;
    @JsonProperty("oktmo")
    private final String oktmo;
    @JsonProperty("tax_office")
    private final String taxOffice;
    @JsonProperty("tax_office_legal")
    private final String taxOfficeLegal;
    @JsonProperty("timezone")
    private final Object timezone;
    @JsonProperty("geo_lat")
    private final String geoLat;
    @JsonProperty("geo_lon")
    private final String geoLon;
    @JsonProperty("beltway_hit")
    private final Object beltwayHit;
    @JsonProperty("beltway_distance")
    private final Object beltwayDistance;
    @JsonProperty("metro")
    private final Object metro;
    @JsonProperty("qc_geo")
    private final String qcGeo;
    @JsonProperty("qc_complete")
    private final Object qcComplete;
    @JsonProperty("qc_house")
    private final Object qcHouse;
    @JsonProperty("history_values")
    private final Object historyValues;
    @JsonProperty("unparsed_parts")
    private final Object unparsedParts;
    @JsonProperty("source")
    private final Object source;
    @JsonProperty("qc")
    private final Object qc;


    @JsonCreator
    private Data(@JsonProperty("postal_code") String postalCode,
                @JsonProperty("country")String country, @JsonProperty("country_iso_code") String countryIsoCode,
                @JsonProperty("federal_district") Object federalDistrict, @JsonProperty("region_fias_id") String regionFiasId,
                @JsonProperty("region_kladr_id") String regionKladrId, @JsonProperty("region_iso_code") String regionIsoCode,
                @JsonProperty("region_with_type") String regionWithType, @JsonProperty("region_type") String regionType,
                @JsonProperty("region_type_full") String regionTypeFull, @JsonProperty("region") String region,
                @JsonProperty("area_fias_id") Object areaFiasId, @JsonProperty("area_kladr_id") Object areaKladrId,
                @JsonProperty("area_with_type") Object areaWithType, @JsonProperty("area_type") Object areaType,
                @JsonProperty("area_type_full") Object areaTypeFull, @JsonProperty("area") Object area,
                @JsonProperty("city_fias_id") String cityFiasId, @JsonProperty("city_kladr_id") String cityKladrId,
                @JsonProperty("city_with_type") String cityWithType, @JsonProperty("city_type") String cityType,
                @JsonProperty("city_type_full") String cityTypeFull, @JsonProperty("city") String city,
                @JsonProperty("city_area") Object cityArea, @JsonProperty("city_district_fias_id") Object cityDistrictFiasId,
                @JsonProperty("city_district_kladr_id") Object cityDistrictKladrId,
                @JsonProperty("city_district_with_type") Object cityDistrictWithType, @JsonProperty("city_district_type") Object cityDistrictType,
                @JsonProperty("city_district_type_full") Object cityDistrictTypeFull, @JsonProperty("city_district") Object cityDistrict,
                @JsonProperty("settlement_fias_id") Object settlementFiasId,
                @JsonProperty("settlement_kladr_id") Object settlementKladrId, @JsonProperty("settlement_with_type") Object settlementWithType,
                @JsonProperty("settlement_type") Object settlementType, @JsonProperty("settlement_type_full") Object settlementTypeFull,
                @JsonProperty("settlement") Object settlement, @JsonProperty("street_fias_id") String streetFiasId,
                @JsonProperty("street_kladr_id") String streetKladrId,
                @JsonProperty("street_with_type") String streetWithType, @JsonProperty("street_type") String streetType,
                @JsonProperty("street_type_full") String streetTypeFull, @JsonProperty("street") String street,
                @JsonProperty("house_fias_id") Object houseFiasId, @JsonProperty("house_kladr_id") Object houseKladrId,
                @JsonProperty("house_type") String houseType, @JsonProperty("house_type_full") String houseTypeFull,
                @JsonProperty("house") String house, @JsonProperty("block_type") Object blockType,
                @JsonProperty("block_type_full") Object blockTypeFull, @JsonProperty("block") Object block,
                @JsonProperty("flat_type") Object flatType, @JsonProperty("flat_type_full") Object flatTypeFull,
                @JsonProperty("flat") Object flat, @JsonProperty("flat_area") Object flatArea,
                @JsonProperty("square_meter_price") Object squareMeterPrice, @JsonProperty("flat_price") Object flatPrice,
                @JsonProperty("postal_box") Object postalBox, @JsonProperty("fias_id") String fiasId,
                @JsonProperty("fias_code") Object fiasCode, @JsonProperty("fias_level") String fiasLevel,
                @JsonProperty("fias_actuality_state") Object fiasActualityState, @JsonProperty("kladr_id") String kladrId,
                @JsonProperty("geoname_id") Object geonameId, @JsonProperty("capital_marker") String capitalMarker,
                @JsonProperty("okato") String okato, @JsonProperty("oktmo") String oktmo,
                @JsonProperty("tax_office") String taxOffice, @JsonProperty("tax_office_legal") String taxOfficeLegal,
                @JsonProperty("timezone") Object timezone, @JsonProperty("geo_lat") String geoLat,
                @JsonProperty("geo_lon") String geoLon, @JsonProperty("beltway_hit") Object beltwayHit,
                @JsonProperty("beltway_distance") Object beltwayDistance, @JsonProperty("metro") Object metro,
                @JsonProperty("qc_geo") String qcGeo, @JsonProperty("qc_complete") Object qcComplete,
                @JsonProperty("qc_house") Object qcHouse, @JsonProperty("history_values") Object historyValues,
                @JsonProperty("unparsed_parts") Object unparsedParts, @JsonProperty("source") Object source,
                @JsonProperty("qc") Object qc) {
        this.postalCode = postalCode;
        this.country = country;
        this.countryIsoCode = countryIsoCode;
        this.federalDistrict = federalDistrict;
        this.regionFiasId = regionFiasId;
        this.regionKladrId = regionKladrId;
        this.regionIsoCode = regionIsoCode;
        this.regionWithType = regionWithType;
        this.regionType = regionType;
        this.regionTypeFull = regionTypeFull;
        this.region = region;
        this.areaFiasId = areaFiasId;
        this.areaKladrId = areaKladrId;
        this.areaWithType = areaWithType;
        this.areaType = areaType;
        this.areaTypeFull = areaTypeFull;
        this.area = area;
        this.cityFiasId = cityFiasId;
        this.cityKladrId = cityKladrId;
        this.cityWithType = cityWithType;
        this.cityType = cityType;
        this.cityTypeFull = cityTypeFull;
        this.city = city;
        this.cityArea = cityArea;
        this.cityDistrictFiasId = cityDistrictFiasId;
        this.cityDistrictKladrId = cityDistrictKladrId;
        this.cityDistrictWithType = cityDistrictWithType;
        this.cityDistrictType = cityDistrictType;
        this.cityDistrictTypeFull = cityDistrictTypeFull;
        this.cityDistrict = cityDistrict;
        this.settlementFiasId = settlementFiasId;
        this.settlementKladrId = settlementKladrId;
        this.settlementWithType = settlementWithType;
        this.settlementType = settlementType;
        this.settlementTypeFull = settlementTypeFull;
        this.settlement = settlement;
        this.streetFiasId = streetFiasId;
        this.streetKladrId = streetKladrId;
        this.streetWithType = streetWithType;
        this.streetType = streetType;
        this.streetTypeFull = streetTypeFull;
        this.street = street;
        this.houseFiasId = houseFiasId;
        this.houseKladrId = houseKladrId;
        this.houseType = houseType;
        this.houseTypeFull = houseTypeFull;
        this.house = house;
        this.blockType = blockType;
        this.blockTypeFull = blockTypeFull;
        this.block = block;
        this.flatType = flatType;
        this.flatTypeFull = flatTypeFull;
        this.flat = flat;
        this.flatArea = flatArea;
        this.squareMeterPrice = squareMeterPrice;
        this.flatPrice = flatPrice;
        this.postalBox = postalBox;
        this.fiasId = fiasId;
        this.fiasCode = fiasCode;
        this.fiasLevel = fiasLevel;
        this.fiasActualityState = fiasActualityState;
        this.kladrId = kladrId;
        this.geonameId = geonameId;
        this.capitalMarker = capitalMarker;
        this.okato = okato;
        this.oktmo = oktmo;
        this.taxOffice = taxOffice;
        this.taxOfficeLegal = taxOfficeLegal;
        this.timezone = timezone;
        this.geoLat = geoLat;
        this.geoLon = geoLon;
        this.beltwayHit = beltwayHit;
        this.beltwayDistance = beltwayDistance;
        this.metro = metro;
        this.qcGeo = qcGeo;
        this.qcComplete = qcComplete;
        this.qcHouse = qcHouse;
        this.historyValues = historyValues;
        this.unparsedParts = unparsedParts;
        this.source = source;
        this.qc = qc;
    }

    public static DataBuilder builder() {
        return new DataBuilder();
    }

    @JsonProperty("postal_code")
    public String getPostalCode() {
        return postalCode;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country_iso_code")
    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    @JsonProperty("federal_district")
    public Object getFederalDistrict() {
        return federalDistrict;
    }

    @JsonProperty("region_fias_id")
    public String getRegionFiasId() {
        return regionFiasId;
    }

    @JsonProperty("region_kladr_id")
    public String getRegionKladrId() {
        return regionKladrId;
    }

    @JsonProperty("region_iso_code")
    public String getRegionIsoCode() {
        return regionIsoCode;
    }

    @JsonProperty("region_with_type")
    public String getRegionWithType() {
        return regionWithType;
    }

    @JsonProperty("region_type")
    public String getRegionType() {
        return regionType;
    }

    @JsonProperty("region_type_full")
    public String getRegionTypeFull() {
        return regionTypeFull;
    }

    @JsonProperty("region")
    public String getRegion() {
        return region;
    }

    @JsonProperty("area_fias_id")
    public Object getAreaFiasId() {
        return areaFiasId;
    }

    @JsonProperty("area_kladr_id")
    public Object getAreaKladrId() {
        return areaKladrId;
    }

    @JsonProperty("area_with_type")
    public Object getAreaWithType() {
        return areaWithType;
    }

    @JsonProperty("area_type")
    public Object getAreaType() {
        return areaType;
    }

    @JsonProperty("area_type_full")
    public Object getAreaTypeFull() {
        return areaTypeFull;
    }

    @JsonProperty("area")
    public Object getArea() {
        return area;
    }

    @JsonProperty("city_fias_id")
    public String getCityFiasId() {
        return cityFiasId;
    }

    @JsonProperty("city_kladr_id")
    public String getCityKladrId() {
        return cityKladrId;
    }

    @JsonProperty("city_with_type")
    public String getCityWithType() {
        return cityWithType;
    }

    @JsonProperty("city_type")
    public String getCityType() {
        return cityType;
    }

    @JsonProperty("city_type_full")
    public String getCityTypeFull() {
        return cityTypeFull;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city_area")
    public Object getCityArea() {
        return cityArea;
    }

    @JsonProperty("city_district_fias_id")
    public Object getCityDistrictFiasId() {
        return cityDistrictFiasId;
    }

    @JsonProperty("city_district_kladr_id")
    public Object getCityDistrictKladrId() {
        return cityDistrictKladrId;
    }

    @JsonProperty("city_district_with_type")
    public Object getCityDistrictWithType() {
        return cityDistrictWithType;
    }

    @JsonProperty("city_district_type")
    public Object getCityDistrictType() {
        return cityDistrictType;
    }

    @JsonProperty("city_district_type_full")
    public Object getCityDistrictTypeFull() {
        return cityDistrictTypeFull;
    }

    @JsonProperty("city_district")
    public Object getCityDistrict() {
        return cityDistrict;
    }

    @JsonProperty("settlement_fias_id")
    public Object getSettlementFiasId() {
        return settlementFiasId;
    }

    @JsonProperty("settlement_kladr_id")
    public Object getSettlementKladrId() {
        return settlementKladrId;
    }

    @JsonProperty("settlement_with_type")
    public Object getSettlementWithType() {
        return settlementWithType;
    }

    @JsonProperty("settlement_type")
    public Object getSettlementType() {
        return settlementType;
    }

    @JsonProperty("settlement_type_full")
    public Object getSettlementTypeFull() {
        return settlementTypeFull;
    }

    @JsonProperty("settlement")
    public Object getSettlement() {
        return settlement;
    }

    @JsonProperty("street_fias_id")
    public String getStreetFiasId() {
        return streetFiasId;
    }

    @JsonProperty("street_kladr_id")
    public String getStreetKladrId() {
        return streetKladrId;
    }

    @JsonProperty("street_with_type")
    public String getStreetWithType() {
        return streetWithType;
    }

    @JsonProperty("street_type")
    public String getStreetType() {
        return streetType;
    }

    @JsonProperty("street_type_full")
    public String getStreetTypeFull() {
        return streetTypeFull;
    }

    @JsonProperty("street")
    public String getStreet() {
        return street;
    }

    @JsonProperty("house_fias_id")
    public Object getHouseFiasId() {
        return houseFiasId;
    }

    @JsonProperty("house_kladr_id")
    public Object getHouseKladrId() {
        return houseKladrId;
    }

    @JsonProperty("house_type")
    public String getHouseType() {
        return houseType;
    }

    @JsonProperty("house_type_full")
    public String getHouseTypeFull() {
        return houseTypeFull;
    }

    @JsonProperty("house")
    public String getHouse() {
        return house;
    }

    @JsonProperty("block_type")
    public Object getBlockType() {
        return blockType;
    }

    @JsonProperty("block_type_full")
    public Object getBlockTypeFull() {
        return blockTypeFull;
    }

    @JsonProperty("block")
    public Object getBlock() {
        return block;
    }

    @JsonProperty("flat_type")
    public Object getFlatType() {
        return flatType;
    }

    @JsonProperty("flat_type_full")
    public Object getFlatTypeFull() {
        return flatTypeFull;
    }


    @JsonProperty("flat")
    public Object getFlat() {
        return flat;
    }

    @JsonProperty("flat_area")
    public Object getFlatArea() {
        return flatArea;
    }

    @JsonProperty("square_meter_price")
    public Object getSquareMeterPrice() {
        return squareMeterPrice;
    }

    @JsonProperty("flat_price")
    public Object getFlatPrice() {
        return flatPrice;
    }

    @JsonProperty("postal_box")
    public Object getPostalBox() {
        return postalBox;
    }

    @JsonProperty("fias_id")
    public String getFiasId() {
        return fiasId;
    }

    @JsonProperty("fias_code")
    public Object getFiasCode() {
        return fiasCode;
    }

    @JsonProperty("fias_level")
    public String getFiasLevel() {
        return fiasLevel;
    }

    @JsonProperty("fias_actuality_state")
    public Object getFiasActualityState() {
        return fiasActualityState;
    }

    @JsonProperty("kladr_id")
    public String getKladrId() {
        return kladrId;
    }

    @JsonProperty("geoname_id")
    public Object getGeonameId() {
        return geonameId;
    }

    @JsonProperty("capital_marker")
    public String getCapitalMarker() {
        return capitalMarker;
    }

    @JsonProperty("okato")
    public String getOkato() {
        return okato;
    }

    @JsonProperty("oktmo")
    public String getOktmo() {
        return oktmo;
    }

    @JsonProperty("tax_office")
    public String getTaxOffice() {
        return taxOffice;
    }

    @JsonProperty("tax_office_legal")
    public String getTaxOfficeLegal() {
        return taxOfficeLegal;
    }

    @JsonProperty("timezone")
    public Object getTimezone() {
        return timezone;
    }

    @JsonProperty("geo_lat")
    public String getGeoLat() {
        return geoLat;
    }

    @JsonProperty("geo_lon")
    public String getGeoLon() {
        return geoLon;
    }

    @JsonProperty("beltway_hit")
    public Object getBeltwayHit() {
        return beltwayHit;
    }

    @JsonProperty("beltway_distance")
    public Object getBeltwayDistance() {
        return beltwayDistance;
    }

    @JsonProperty("metro")
    public Object getMetro() {
        return metro;
    }

    @JsonProperty("qc_geo")
    public String getQcGeo() {
        return qcGeo;
    }


    @JsonProperty("qc_complete")
    public Object getQcComplete() {
        return qcComplete;
    }

    @JsonProperty("qc_house")
    public Object getQcHouse() {
        return qcHouse;
    }

    @JsonProperty("history_values")
    public Object getHistoryValues() {
        return historyValues;
    }

    @JsonProperty("unparsed_parts")
    public Object getUnparsedParts() {
        return unparsedParts;
    }

    @JsonProperty("source")
    public Object getSource() {
        return source;
    }

    @JsonProperty("qc")
    public Object getQc() {
        return qc;
    }



    @Override
    public String toString() {
        return "Data{" +
                "postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", countryIsoCode='" + countryIsoCode + '\'' +
                ", federalDistrict=" + federalDistrict +
                ", regionFiasId='" + regionFiasId + '\'' +
                ", regionKladrId='" + regionKladrId + '\'' +
                ", regionIsoCode='" + regionIsoCode + '\'' +
                ", regionWithType='" + regionWithType + '\'' +
                ", regionType='" + regionType + '\'' +
                ", regionTypeFull='" + regionTypeFull + '\'' +
                ", region='" + region + '\'' +
                ", areaFiasId=" + areaFiasId +
                ", areaKladrId=" + areaKladrId +
                ", areaWithType=" + areaWithType +
                ", areaType=" + areaType +
                ", areaTypeFull=" + areaTypeFull +
                ", area=" + area +
                ", cityFiasId='" + cityFiasId + '\'' +
                ", cityKladrId='" + cityKladrId + '\'' +
                ", cityWithType='" + cityWithType + '\'' +
                ", cityType='" + cityType + '\'' +
                ", cityTypeFull='" + cityTypeFull + '\'' +
                ", city='" + city + '\'' +
                ", cityArea=" + cityArea +
                ", cityDistrictFiasId=" + cityDistrictFiasId +
                ", cityDistrictKladrId=" + cityDistrictKladrId +
                ", cityDistrictWithType=" + cityDistrictWithType +
                ", cityDistrictType=" + cityDistrictType +
                ", cityDistrictTypeFull=" + cityDistrictTypeFull +
                ", cityDistrict=" + cityDistrict +
                ", settlementFiasId=" + settlementFiasId +
                ", settlementKladrId=" + settlementKladrId +
                ", settlementWithType=" + settlementWithType +
                ", settlementType=" + settlementType +
                ", settlementTypeFull=" + settlementTypeFull +
                ", settlement=" + settlement +
                ", streetFiasId='" + streetFiasId + '\'' +
                ", streetKladrId='" + streetKladrId + '\'' +
                ", streetWithType='" + streetWithType + '\'' +
                ", streetType='" + streetType + '\'' +
                ", streetTypeFull='" + streetTypeFull + '\'' +
                ", street='" + street + '\'' +
                ", houseFiasId=" + houseFiasId +
                ", houseKladrId=" + houseKladrId +
                ", houseType='" + houseType + '\'' +
                ", houseTypeFull='" + houseTypeFull + '\'' +
                ", house='" + house + '\'' +
                ", blockType=" + blockType +
                ", blockTypeFull=" + blockTypeFull +
                ", block=" + block +
                ", flatType=" + flatType +
                ", flatTypeFull=" + flatTypeFull +
                ", flat=" + flat +
                ", flatArea=" + flatArea +
                ", squareMeterPrice=" + squareMeterPrice +
                ", flatPrice=" + flatPrice +
                ", postalBox=" + postalBox +
                ", fiasId='" + fiasId + '\'' +
                ", fiasCode=" + fiasCode +
                ", fiasLevel='" + fiasLevel + '\'' +
                ", fiasActualityState=" + fiasActualityState +
                ", kladrId='" + kladrId + '\'' +
                ", geonameId=" + geonameId +
                ", capitalMarker='" + capitalMarker + '\'' +
                ", okato='" + okato + '\'' +
                ", oktmo='" + oktmo + '\'' +
                ", taxOffice='" + taxOffice + '\'' +
                ", taxOfficeLegal='" + taxOfficeLegal + '\'' +
                ", timezone=" + timezone +
                ", geoLat='" + geoLat + '\'' +
                ", geoLon='" + geoLon + '\'' +
                ", beltwayHit=" + beltwayHit +
                ", beltwayDistance=" + beltwayDistance +
                ", metro=" + metro +
                ", qcGeo='" + qcGeo + '\'' +
                ", qcComplete=" + qcComplete +
                ", qcHouse=" + qcHouse +
                ", historyValues=" + historyValues +
                ", unparsedParts=" + unparsedParts +
                ", source=" + source +
                ", qc=" + qc +
                '}';
    }


    public static final class DataBuilder {
        private String postalCode;
        private String country;
        private String countryIsoCode;
        private Object federalDistrict;
        private String regionFiasId;
        private String regionKladrId;
        private String regionIsoCode;
        private String regionWithType;
        private String regionType;
        private String regionTypeFull;
        private String region;
        private Object areaFiasId;
        private Object areaKladrId;
        private Object areaWithType;
        private Object areaType;
        private Object areaTypeFull;
        private Object area;
        private String cityFiasId;
        private String cityKladrId;
        private String cityWithType;
        private String cityType;
        private String cityTypeFull;
        private String city;
        private Object cityArea;
        private Object cityDistrictFiasId;
        private Object cityDistrictKladrId;
        private Object cityDistrictWithType;
        private Object cityDistrictType;
        private Object cityDistrictTypeFull;
        private Object cityDistrict;
        private Object settlementFiasId;
        private Object settlementKladrId;
        private Object settlementWithType;
        private Object settlementType;
        private Object settlementTypeFull;
        private Object settlement;
        private String streetFiasId;
        private String streetKladrId;
        private String streetWithType;
        private String streetType;
        private String streetTypeFull;
        private String street;
        private Object houseFiasId;
        private Object houseKladrId;
        private String houseType;
        private String houseTypeFull;
        private String house;
        private Object blockType;
        private Object blockTypeFull;
        private Object block;
        private Object flatType;
        private Object flatTypeFull;
        private Object flat;
        private Object flatArea;
        private Object squareMeterPrice;
        private Object flatPrice;
        private Object postalBox;
        private String fiasId;
        private Object fiasCode;
        private String fiasLevel;
        private Object fiasActualityState;
        private String kladrId;
        private Object geonameId;
        private String capitalMarker;
        private String okato;
        private String oktmo;
        private String taxOffice;
        private String taxOfficeLegal;
        private Object timezone;
        private String geoLat;
        private String geoLon;
        private Object beltwayHit;
        private Object beltwayDistance;
        private Object metro;
        private String qcGeo;
        private Object qcComplete;
        private Object qcHouse;
        private Object historyValues;
        private Object unparsedParts;
        private Object source;
        private Object qc;

        private DataBuilder() {
        }

        public DataBuilder withPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public DataBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public DataBuilder withCountryIsoCode(String countryIsoCode) {
            this.countryIsoCode = countryIsoCode;
            return this;
        }

        public DataBuilder withFederalDistrict(Object federalDistrict) {
            this.federalDistrict = federalDistrict;
            return this;
        }

        public DataBuilder withRegionFiasId(String regionFiasId) {
            this.regionFiasId = regionFiasId;
            return this;
        }

        public DataBuilder withRegionKladrId(String regionKladrId) {
            this.regionKladrId = regionKladrId;
            return this;
        }

        public DataBuilder withRegionIsoCode(String regionIsoCode) {
            this.regionIsoCode = regionIsoCode;
            return this;
        }

        public DataBuilder withRegionWithType(String regionWithType) {
            this.regionWithType = regionWithType;
            return this;
        }

        public DataBuilder withRegionType(String regionType) {
            this.regionType = regionType;
            return this;
        }

        public DataBuilder withRegionTypeFull(String regionTypeFull) {
            this.regionTypeFull = regionTypeFull;
            return this;
        }

        public DataBuilder withRegion(String region) {
            this.region = region;
            return this;
        }

        public DataBuilder withAreaFiasId(Object areaFiasId) {
            this.areaFiasId = areaFiasId;
            return this;
        }

        public DataBuilder withAreaKladrId(Object areaKladrId) {
            this.areaKladrId = areaKladrId;
            return this;
        }

        public DataBuilder withAreaWithType(Object areaWithType) {
            this.areaWithType = areaWithType;
            return this;
        }

        public DataBuilder withAreaType(Object areaType) {
            this.areaType = areaType;
            return this;
        }

        public DataBuilder withAreaTypeFull(Object areaTypeFull) {
            this.areaTypeFull = areaTypeFull;
            return this;
        }

        public DataBuilder withArea(Object area) {
            this.area = area;
            return this;
        }

        public DataBuilder withCityFiasId(String cityFiasId) {
            this.cityFiasId = cityFiasId;
            return this;
        }

        public DataBuilder withCityKladrId(String cityKladrId) {
            this.cityKladrId = cityKladrId;
            return this;
        }

        public DataBuilder withCityWithType(String cityWithType) {
            this.cityWithType = cityWithType;
            return this;
        }

        public DataBuilder withCityType(String cityType) {
            this.cityType = cityType;
            return this;
        }

        public DataBuilder withCityTypeFull(String cityTypeFull) {
            this.cityTypeFull = cityTypeFull;
            return this;
        }

        public DataBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public DataBuilder withCityArea(Object cityArea) {
            this.cityArea = cityArea;
            return this;
        }

        public DataBuilder withCityDistrictFiasId(Object cityDistrictFiasId) {
            this.cityDistrictFiasId = cityDistrictFiasId;
            return this;
        }

        public DataBuilder withCityDistrictKladrId(Object cityDistrictKladrId) {
            this.cityDistrictKladrId = cityDistrictKladrId;
            return this;
        }

        public DataBuilder withCityDistrictWithType(Object cityDistrictWithType) {
            this.cityDistrictWithType = cityDistrictWithType;
            return this;
        }

        public DataBuilder withCityDistrictType(Object cityDistrictType) {
            this.cityDistrictType = cityDistrictType;
            return this;
        }

        public DataBuilder withCityDistrictTypeFull(Object cityDistrictTypeFull) {
            this.cityDistrictTypeFull = cityDistrictTypeFull;
            return this;
        }

        public DataBuilder withCityDistrict(Object cityDistrict) {
            this.cityDistrict = cityDistrict;
            return this;
        }

        public DataBuilder withSettlementFiasId(Object settlementFiasId) {
            this.settlementFiasId = settlementFiasId;
            return this;
        }

        public DataBuilder withSettlementKladrId(Object settlementKladrId) {
            this.settlementKladrId = settlementKladrId;
            return this;
        }

        public DataBuilder withSettlementWithType(Object settlementWithType) {
            this.settlementWithType = settlementWithType;
            return this;
        }

        public DataBuilder withSettlementType(Object settlementType) {
            this.settlementType = settlementType;
            return this;
        }

        public DataBuilder withSettlementTypeFull(Object settlementTypeFull) {
            this.settlementTypeFull = settlementTypeFull;
            return this;
        }

        public DataBuilder withSettlement(Object settlement) {
            this.settlement = settlement;
            return this;
        }

        public DataBuilder withStreetFiasId(String streetFiasId) {
            this.streetFiasId = streetFiasId;
            return this;
        }

        public DataBuilder withStreetKladrId(String streetKladrId) {
            this.streetKladrId = streetKladrId;
            return this;
        }

        public DataBuilder withStreetWithType(String streetWithType) {
            this.streetWithType = streetWithType;
            return this;
        }

        public DataBuilder withStreetType(String streetType) {
            this.streetType = streetType;
            return this;
        }

        public DataBuilder withStreetTypeFull(String streetTypeFull) {
            this.streetTypeFull = streetTypeFull;
            return this;
        }

        public DataBuilder withStreet(String street) {
            this.street = street;
            return this;
        }

        public DataBuilder withHouseFiasId(Object houseFiasId) {
            this.houseFiasId = houseFiasId;
            return this;
        }

        public DataBuilder withHouseKladrId(Object houseKladrId) {
            this.houseKladrId = houseKladrId;
            return this;
        }

        public DataBuilder withHouseType(String houseType) {
            this.houseType = houseType;
            return this;
        }

        public DataBuilder withHouseTypeFull(String houseTypeFull) {
            this.houseTypeFull = houseTypeFull;
            return this;
        }

        public DataBuilder withHouse(String house) {
            this.house = house;
            return this;
        }

        public DataBuilder withBlockType(Object blockType) {
            this.blockType = blockType;
            return this;
        }

        public DataBuilder withBlockTypeFull(Object blockTypeFull) {
            this.blockTypeFull = blockTypeFull;
            return this;
        }

        public DataBuilder withBlock(Object block) {
            this.block = block;
            return this;
        }

        public DataBuilder withFlatType(Object flatType) {
            this.flatType = flatType;
            return this;
        }

        public DataBuilder withFlatTypeFull(Object flatTypeFull) {
            this.flatTypeFull = flatTypeFull;
            return this;
        }

        public DataBuilder withFlat(Object flat) {
            this.flat = flat;
            return this;
        }

        public DataBuilder withFlatArea(Object flatArea) {
            this.flatArea = flatArea;
            return this;
        }

        public DataBuilder withSquareMeterPrice(Object squareMeterPrice) {
            this.squareMeterPrice = squareMeterPrice;
            return this;
        }

        public DataBuilder withFlatPrice(Object flatPrice) {
            this.flatPrice = flatPrice;
            return this;
        }

        public DataBuilder withPostalBox(Object postalBox) {
            this.postalBox = postalBox;
            return this;
        }

        public DataBuilder withFiasId(String fiasId) {
            this.fiasId = fiasId;
            return this;
        }

        public DataBuilder withFiasCode(Object fiasCode) {
            this.fiasCode = fiasCode;
            return this;
        }

        public DataBuilder withFiasLevel(String fiasLevel) {
            this.fiasLevel = fiasLevel;
            return this;
        }

        public DataBuilder withFiasActualityState(Object fiasActualityState) {
            this.fiasActualityState = fiasActualityState;
            return this;
        }

        public DataBuilder withKladrId(String kladrId) {
            this.kladrId = kladrId;
            return this;
        }

        public DataBuilder withGeonameId(Object geonameId) {
            this.geonameId = geonameId;
            return this;
        }

        public DataBuilder withCapitalMarker(String capitalMarker) {
            this.capitalMarker = capitalMarker;
            return this;
        }

        public DataBuilder withOkato(String okato) {
            this.okato = okato;
            return this;
        }

        public DataBuilder withOktmo(String oktmo) {
            this.oktmo = oktmo;
            return this;
        }

        public DataBuilder withTaxOffice(String taxOffice) {
            this.taxOffice = taxOffice;
            return this;
        }

        public DataBuilder withTaxOfficeLegal(String taxOfficeLegal) {
            this.taxOfficeLegal = taxOfficeLegal;
            return this;
        }

        public DataBuilder withTimezone(Object timezone) {
            this.timezone = timezone;
            return this;
        }

        public DataBuilder withGeoLat(String geoLat) {
            this.geoLat = geoLat;
            return this;
        }

        public DataBuilder withGeoLon(String geoLon) {
            this.geoLon = geoLon;
            return this;
        }

        public DataBuilder withBeltwayHit(Object beltwayHit) {
            this.beltwayHit = beltwayHit;
            return this;
        }

        public DataBuilder withBeltwayDistance(Object beltwayDistance) {
            this.beltwayDistance = beltwayDistance;
            return this;
        }

        public DataBuilder withMetro(Object metro) {
            this.metro = metro;
            return this;
        }

        public DataBuilder withQcGeo(String qcGeo) {
            this.qcGeo = qcGeo;
            return this;
        }

        public DataBuilder withQcComplete(Object qcComplete) {
            this.qcComplete = qcComplete;
            return this;
        }

        public DataBuilder withQcHouse(Object qcHouse) {
            this.qcHouse = qcHouse;
            return this;
        }

        public DataBuilder withHistoryValues(Object historyValues) {
            this.historyValues = historyValues;
            return this;
        }

        public DataBuilder withUnparsedParts(Object unparsedParts) {
            this.unparsedParts = unparsedParts;
            return this;
        }

        public DataBuilder withSource(Object source) {
            this.source = source;
            return this;
        }

        public DataBuilder withQc(Object qc) {
            this.qc = qc;
            return this;
        }

        public Data build() {
            return new Data(postalCode, country, countryIsoCode, federalDistrict, regionFiasId, regionKladrId,
                    regionIsoCode, regionWithType, regionType, regionTypeFull, region, areaFiasId, areaKladrId,
                    areaWithType, areaType, areaTypeFull, area, cityFiasId, cityKladrId, cityWithType, cityType,
                    cityTypeFull, city, cityArea, cityDistrictFiasId, cityDistrictKladrId, cityDistrictWithType,
                    cityDistrictType, cityDistrictTypeFull, cityDistrict, settlementFiasId, settlementKladrId,
                    settlementWithType, settlementType, settlementTypeFull, settlement, streetFiasId, streetKladrId,
                    streetWithType, streetType, streetTypeFull, street, houseFiasId, houseKladrId, houseType,
                    houseTypeFull, house, blockType, blockTypeFull, block, flatType, flatTypeFull, flat, flatArea,
                    squareMeterPrice, flatPrice, postalBox, fiasId, fiasCode, fiasLevel, fiasActualityState, kladrId,
                    geonameId, capitalMarker, okato, oktmo, taxOffice, taxOfficeLegal, timezone, geoLat, geoLon,
                    beltwayHit, beltwayDistance, metro, qcGeo, qcComplete, qcHouse, historyValues, unparsedParts, source,
                    qc);
        }
    }
}
