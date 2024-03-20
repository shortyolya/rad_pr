package com.baltinfo.radius.dadata.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * <p>
 *    POJO class для обёртки ответа в формате JSON на запрос о стандартизации адреса
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 09.12.2019
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressStandardResponse {
    @JsonProperty("source")
    private final String source;
    @JsonProperty("result")
    private final String result;
    @JsonProperty("postal_code")
    private final String postalCode;
    @JsonProperty("country")
    private final String country;
    @JsonProperty("country_iso_code")
    private final String countryIsoCode;
    @JsonProperty("federal_district")
    private final String federalDistrict;
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
    private final Object cityFiasId;
    @JsonProperty("city_kladr_id")
    private final Object cityKladrId;
    @JsonProperty("city_with_type")
    private final Object cityWithType;
    @JsonProperty("city_type")
    private final Object cityType;
    @JsonProperty("city_type_full")
    private final Object cityTypeFull;
    @JsonProperty("city")
    private final Object city;
    @JsonProperty("city_area")
    private final String cityArea;
    @JsonProperty("city_district_fias_id")
    private final Object cityDistrictFiasId;
    @JsonProperty("city_district_kladr_id")
    private final Object cityDistrictKladrId;
    @JsonProperty("city_district_with_type")
    private final String cityDistrictWithType;
    @JsonProperty("city_district_type")
    private final String cityDistrictType;
    @JsonProperty("city_district_type_full")
    private final String cityDistrictTypeFull;
    @JsonProperty("city_district")
    private final String cityDistrict;
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
    private final String houseFiasId;
    @JsonProperty("house_kladr_id")
    private final String houseKladrId;
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
    private final String flatType;
    @JsonProperty("flat_type_full")
    private final String flatTypeFull;
    @JsonProperty("flat")
    private final String flat;
    @JsonProperty("flat_area")
    private final String flatArea;
    @JsonProperty("square_meter_price")
    private final String squareMeterPrice;
    @JsonProperty("flat_price")
    private final String flatPrice;
    @JsonProperty("postal_box")
    private final Object postalBox;
    @JsonProperty("fias_id")
    private final String fiasId;
    @JsonProperty("fias_code")
    private final String fiasCode;
    @JsonProperty("fias_level")
    private final String fiasLevel;
    @JsonProperty("fias_actuality_state")
    private final String fiasActualityState;
    @JsonProperty("kladr_id")
    private final String kladrId;
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
    private final String timezone;
    @JsonProperty("geo_lat")
    private final String geoLat;
    @JsonProperty("geo_lon")
    private final String geoLon;
    @JsonProperty("beltway_hit")
    private final String beltwayHit;
    @JsonProperty("beltway_distance")
    private final Object beltwayDistance;
    @JsonProperty("qc_geo")
    private final Integer qcGeo;
    @JsonProperty("qc_complete")
    private final Integer qcComplete;
    @JsonProperty("qc_house")
    private final Integer qcHouse;
    @JsonProperty("qc")
    private final Integer qc;
    @JsonProperty("unparsed_parts")
    private final String unparsedParts;
    @JsonProperty("metro")
    private final List<Metro> metro;

    @JsonCreator
    private AddressStandardResponse(@JsonProperty("source") String source,
                @JsonProperty("result") String result, @JsonProperty("postal_code") String postalCode,
                @JsonProperty("country") String country, @JsonProperty("country_iso_code") String countryIsoCode,
                @JsonProperty("federal_district") String federalDistrict, @JsonProperty("region_fias_id") String regionFiasId,
                @JsonProperty("region_kladr_id") String regionKladrId, @JsonProperty("region_iso_code") String regionIsoCode,
                @JsonProperty("region_with_type") String regionWithType, @JsonProperty("region_type") String regionType,
                @JsonProperty("region_type_full") String regionTypeFull, @JsonProperty("region") String region,
                @JsonProperty("area_fias_id") Object areaFiasId, @JsonProperty("area_kladr_id") Object areaKladrId,
                @JsonProperty("area_with_type") Object areaWithType, @JsonProperty("area_type") Object areaType,
                @JsonProperty("area_type_full") Object areaTypeFull, @JsonProperty("area") Object area,
                @JsonProperty("city_fias_id") Object cityFiasId, @JsonProperty("city_kladr_id") Object cityKladrId,
                @JsonProperty("city_with_type") Object cityWithType, @JsonProperty("city_type") Object cityType,
                @JsonProperty("city_type_full") Object cityTypeFull, @JsonProperty("city") Object city,
                @JsonProperty("city_area") String cityArea, @JsonProperty("city_district_fias_id") Object cityDistrictFiasId,
                @JsonProperty("city_district_kladr_id") Object cityDistrictKladrId, @JsonProperty("city_district_with_type") String cityDistrictWithType,
                @JsonProperty("city_district_type") String cityDistrictType, @JsonProperty("city_district_type_full") String cityDistrictTypeFull,
                @JsonProperty("city_district") String cityDistrict, @JsonProperty("settlement_fias_id") Object settlementFiasId,
                @JsonProperty("settlement_kladr_id") Object settlementKladrId, @JsonProperty("settlement_with_type") Object settlementWithType,
                @JsonProperty("settlement_type") Object settlementType, @JsonProperty("settlement_type_full") Object settlementTypeFull,
                @JsonProperty("settlement") Object settlement, @JsonProperty("street_fias_id") String streetFiasId,
                @JsonProperty("street_kladr_id") String streetKladrId, @JsonProperty("street_with_type") String streetWithType,
                @JsonProperty("street_type") String streetType, @JsonProperty("street_type_full") String streetTypeFull,
                @JsonProperty("street") String street, @JsonProperty("house_fias_id") String houseFiasId,
                @JsonProperty("house_kladr_id") String houseKladrId, @JsonProperty("house_type") String houseType,
                @JsonProperty("house_type_full") String houseTypeFull, @JsonProperty("house") String house,
                @JsonProperty("block_type") Object blockType, @JsonProperty("block_type_full") Object blockTypeFull,
                @JsonProperty("block") Object block, @JsonProperty("flat_type") String flatType,
                @JsonProperty("flat_type_full") String flatTypeFull, @JsonProperty("flat") String flat,
                @JsonProperty("flat_area") String flatArea, @JsonProperty("square_meter_price") String squareMeterPrice,
                @JsonProperty("flat_price") String flatPrice, @JsonProperty("postal_box") Object postalBox,
                @JsonProperty("fias_id") String fiasId, @JsonProperty("fias_code") String fiasCode,
                @JsonProperty("fias_level") String fiasLevel, @JsonProperty("fias_actuality_state") String fiasActualityState,
                @JsonProperty("kladr_id") String kladrId, @JsonProperty("capital_marker") String capitalMarker,
                @JsonProperty("okato") String okato, @JsonProperty("oktmo") String oktmo,
                @JsonProperty("tax_office") String taxOffice, @JsonProperty("tax_office_legal") String taxOfficeLegal,
                @JsonProperty("timezone") String timezone, @JsonProperty("geo_lat") String geoLat,
                @JsonProperty("geo_lon") String geoLon, @JsonProperty("beltway_hit") String beltwayHit,
                @JsonProperty("beltway_distance") Object beltwayDistance, @JsonProperty("qc_geo") Integer qcGeo,
                @JsonProperty("qc_complete") Integer qcComplete, @JsonProperty("qc_house") Integer qcHouse,
                @JsonProperty("qc") Integer qc, @JsonProperty("unparsed_parts") String unparsedParts,
                @JsonProperty("metro") List<Metro> metro) {
        this.source = source;
        this.result = result;
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
        this.qcGeo = qcGeo;
        this.qcComplete = qcComplete;
        this.qcHouse = qcHouse;
        this.qc = qc;
        this.unparsedParts = unparsedParts;
        this.metro = metro;
    }

    public static AddressStandardResponseBuilder builder() {
        return new AddressStandardResponseBuilder();
    }

    public String getSource() {
        return source;
    }

    @JsonProperty("result")
    public String getResult() {
        return result;
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
    public String getFederalDistrict() {
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
    public Object getCityFiasId() {
        return cityFiasId;
    }

    @JsonProperty("city_kladr_id")
    public Object getCityKladrId() {
        return cityKladrId;
    }

    @JsonProperty("city_with_type")
    public Object getCityWithType() {
        return cityWithType;
    }

    @JsonProperty("city_type")
    public Object getCityType() {
        return cityType;
    }

    @JsonProperty("city_type_full")
    public Object getCityTypeFull() {
        return cityTypeFull;
    }

    @JsonProperty("city")
    public Object getCity() {
        return city;
    }

    @JsonProperty("city_area")
    public String getCityArea() {
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
    public String getCityDistrictWithType() {
        return cityDistrictWithType;
    }

    @JsonProperty("city_district_type")
    public String getCityDistrictType() {
        return cityDistrictType;
    }

    @JsonProperty("city_district_type_full")
    public String getCityDistrictTypeFull() {
        return cityDistrictTypeFull;
    }

    @JsonProperty("city_district")
    public String getCityDistrict() {
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
    public String getHouseFiasId() {
        return houseFiasId;
    }

    @JsonProperty("house_kladr_id")
    public String getHouseKladrId() {
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
    public String getFlatType() {
        return flatType;
    }

    @JsonProperty("flat_type_full")
    public String getFlatTypeFull() {
        return flatTypeFull;
    }

    @JsonProperty("flat")
    public String getFlat() {
        return flat;
    }

    @JsonProperty("flat_area")
    public String getFlatArea() {
        return flatArea;
    }

    @JsonProperty("squared_meter_price")
    public String getSquareMeterPrice() {
        return squareMeterPrice;
    }

    @JsonProperty("flat_price")
    public String getFlatPrice() {
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
    public String getFiasCode() {
        return fiasCode;
    }

    @JsonProperty("fias_level")
    public String getFiasLevel() {
        return fiasLevel;
    }

    @JsonProperty("fias_actuality_state")
    public String getFiasActualityState() {
        return fiasActualityState;
    }

    @JsonProperty("kladr_id")
    public String getKladrId() {
        return kladrId;
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
    public String getTimezone() {
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
    public String getBeltwayHit() {
        return beltwayHit;
    }

    @JsonProperty("beltway_distance")
    public Object getBeltwayDistance() {
        return beltwayDistance;
    }

    @JsonProperty("qc_geo")
    public Integer getQcGeo() {
        return qcGeo;
    }

    @JsonProperty("qc_complete")
    public Integer getQcComplete() {
        return qcComplete;
    }

    @JsonProperty("qc_house")
    public Integer getQcHouse() {
        return qcHouse;
    }

    @JsonProperty("qc")
    public Integer getQc() {
        return qc;
    }

    @JsonProperty("unparsed_parts")
    public String getUnparsedParts() {
        return unparsedParts;
    }

    @JsonProperty("metro")
    public List<Metro> getMetro() {
        return metro;
    }

    @Override
    public String toString() {
        return "Data{" +
                "source='" + source + '\'' +
                ", result='" + result + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", countryIsoCode='" + countryIsoCode + '\'' +
                ", federalDistrict='" + federalDistrict + '\'' +
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
                ", cityFiasId=" + cityFiasId +
                ", cityKladrId=" + cityKladrId +
                ", cityWithType=" + cityWithType +
                ", cityType=" + cityType +
                ", cityTypeFull=" + cityTypeFull +
                ", city=" + city +
                ", cityArea='" + cityArea + '\'' +
                ", cityDistrictFiasId=" + cityDistrictFiasId +
                ", cityDistrictKladrId=" + cityDistrictKladrId +
                ", cityDistrictWithType='" + cityDistrictWithType + '\'' +
                ", cityDistrictType='" + cityDistrictType + '\'' +
                ", cityDistrictTypeFull='" + cityDistrictTypeFull + '\'' +
                ", cityDistrict='" + cityDistrict + '\'' +
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
                ", houseFiasId='" + houseFiasId + '\'' +
                ", houseKladrId='" + houseKladrId + '\'' +
                ", houseType='" + houseType + '\'' +
                ", houseTypeFull='" + houseTypeFull + '\'' +
                ", house='" + house + '\'' +
                ", blockType=" + blockType +
                ", blockTypeFull=" + blockTypeFull +
                ", block=" + block +
                ", flatType='" + flatType + '\'' +
                ", flatTypeFull='" + flatTypeFull + '\'' +
                ", flat='" + flat + '\'' +
                ", flatArea='" + flatArea + '\'' +
                ", squareMeterPrice='" + squareMeterPrice + '\'' +
                ", flatPrice='" + flatPrice + '\'' +
                ", postalBox=" + postalBox +
                ", fiasId='" + fiasId + '\'' +
                ", fiasCode='" + fiasCode + '\'' +
                ", fiasLevel='" + fiasLevel + '\'' +
                ", fiasActualityState='" + fiasActualityState + '\'' +
                ", kladrId='" + kladrId + '\'' +
                ", capitalMarker='" + capitalMarker + '\'' +
                ", okato='" + okato + '\'' +
                ", oktmo='" + oktmo + '\'' +
                ", taxOffice='" + taxOffice + '\'' +
                ", taxOfficeLegal='" + taxOfficeLegal + '\'' +
                ", timezone='" + timezone + '\'' +
                ", geoLat='" + geoLat + '\'' +
                ", geoLon='" + geoLon + '\'' +
                ", beltwayHit='" + beltwayHit + '\'' +
                ", beltwayDistance=" + beltwayDistance +
                ", qcGeo=" + qcGeo +
                ", qcComplete=" + qcComplete +
                ", qcHouse=" + qcHouse +
                ", qc=" + qc +
                ", unparsedParts=" + unparsedParts +
                ", metro=" + metro +
                '}';
    }


    public static final class AddressStandardResponseBuilder {
        private String source;
        private String result;
        private String postalCode;
        private String country;
        private String countryIsoCode;
        private String federalDistrict;
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
        private Object cityFiasId;
        private Object cityKladrId;
        private Object cityWithType;
        private Object cityType;
        private Object cityTypeFull;
        private Object city;
        private String cityArea;
        private Object cityDistrictFiasId;
        private Object cityDistrictKladrId;
        private String cityDistrictWithType;
        private String cityDistrictType;
        private String cityDistrictTypeFull;
        private String cityDistrict;
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
        private String houseFiasId;
        private String houseKladrId;
        private String houseType;
        private String houseTypeFull;
        private String house;
        private Object blockType;
        private Object blockTypeFull;
        private Object block;
        private String flatType;
        private String flatTypeFull;
        private String flat;
        private String flatArea;
        private String squareMeterPrice;
        private String flatPrice;
        private Object postalBox;
        private String fiasId;
        private String fiasCode;
        private String fiasLevel;
        private String fiasActualityState;
        private String kladrId;
        private String capitalMarker;
        private String okato;
        private String oktmo;
        private String taxOffice;
        private String taxOfficeLegal;
        private String timezone;
        private String geoLat;
        private String geoLon;
        private String beltwayHit;
        private Object beltwayDistance;
        private Integer qcGeo;
        private Integer qcComplete;
        private Integer qcHouse;
        private Integer qc;
        private String unparsedParts;
        private List<Metro> metro;

        private AddressStandardResponseBuilder() {
        }

        public AddressStandardResponseBuilder withSource(String source) {
            this.source = source;
            return this;
        }

        public AddressStandardResponseBuilder withResult(String result) {
            this.result = result;
            return this;
        }

        public AddressStandardResponseBuilder withPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public AddressStandardResponseBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public AddressStandardResponseBuilder withCountryIsoCode(String countryIsoCode) {
            this.countryIsoCode = countryIsoCode;
            return this;
        }

        public AddressStandardResponseBuilder withFederalDistrict(String federalDistrict) {
            this.federalDistrict = federalDistrict;
            return this;
        }

        public AddressStandardResponseBuilder withRegionFiasId(String regionFiasId) {
            this.regionFiasId = regionFiasId;
            return this;
        }

        public AddressStandardResponseBuilder withRegionKladrId(String regionKladrId) {
            this.regionKladrId = regionKladrId;
            return this;
        }

        public AddressStandardResponseBuilder withRegionIsoCode(String regionIsoCode) {
            this.regionIsoCode = regionIsoCode;
            return this;
        }

        public AddressStandardResponseBuilder withRegionWithType(String regionWithType) {
            this.regionWithType = regionWithType;
            return this;
        }

        public AddressStandardResponseBuilder withRegionType(String regionType) {
            this.regionType = regionType;
            return this;
        }

        public AddressStandardResponseBuilder withRegionTypeFull(String regionTypeFull) {
            this.regionTypeFull = regionTypeFull;
            return this;
        }

        public AddressStandardResponseBuilder withRegion(String region) {
            this.region = region;
            return this;
        }

        public AddressStandardResponseBuilder withAreaFiasId(Object areaFiasId) {
            this.areaFiasId = areaFiasId;
            return this;
        }

        public AddressStandardResponseBuilder withAreaKladrId(Object areaKladrId) {
            this.areaKladrId = areaKladrId;
            return this;
        }

        public AddressStandardResponseBuilder withAreaWithType(Object areaWithType) {
            this.areaWithType = areaWithType;
            return this;
        }

        public AddressStandardResponseBuilder withAreaType(Object areaType) {
            this.areaType = areaType;
            return this;
        }

        public AddressStandardResponseBuilder withAreaTypeFull(Object areaTypeFull) {
            this.areaTypeFull = areaTypeFull;
            return this;
        }

        public AddressStandardResponseBuilder withArea(Object area) {
            this.area = area;
            return this;
        }

        public AddressStandardResponseBuilder withCityFiasId(Object cityFiasId) {
            this.cityFiasId = cityFiasId;
            return this;
        }

        public AddressStandardResponseBuilder withCityKladrId(Object cityKladrId) {
            this.cityKladrId = cityKladrId;
            return this;
        }

        public AddressStandardResponseBuilder withCityWithType(Object cityWithType) {
            this.cityWithType = cityWithType;
            return this;
        }

        public AddressStandardResponseBuilder withCityType(Object cityType) {
            this.cityType = cityType;
            return this;
        }

        public AddressStandardResponseBuilder withCityTypeFull(Object cityTypeFull) {
            this.cityTypeFull = cityTypeFull;
            return this;
        }

        public AddressStandardResponseBuilder withCity(Object city) {
            this.city = city;
            return this;
        }

        public AddressStandardResponseBuilder withCityArea(String cityArea) {
            this.cityArea = cityArea;
            return this;
        }

        public AddressStandardResponseBuilder withCityDistrictFiasId(Object cityDistrictFiasId) {
            this.cityDistrictFiasId = cityDistrictFiasId;
            return this;
        }

        public AddressStandardResponseBuilder withCityDistrictKladrId(Object cityDistrictKladrId) {
            this.cityDistrictKladrId = cityDistrictKladrId;
            return this;
        }

        public AddressStandardResponseBuilder withCityDistrictWithType(String cityDistrictWithType) {
            this.cityDistrictWithType = cityDistrictWithType;
            return this;
        }

        public AddressStandardResponseBuilder withCityDistrictType(String cityDistrictType) {
            this.cityDistrictType = cityDistrictType;
            return this;
        }

        public AddressStandardResponseBuilder withCityDistrictTypeFull(String cityDistrictTypeFull) {
            this.cityDistrictTypeFull = cityDistrictTypeFull;
            return this;
        }

        public AddressStandardResponseBuilder withCityDistrict(String cityDistrict) {
            this.cityDistrict = cityDistrict;
            return this;
        }

        public AddressStandardResponseBuilder withSettlementFiasId(Object settlementFiasId) {
            this.settlementFiasId = settlementFiasId;
            return this;
        }

        public AddressStandardResponseBuilder withSettlementKladrId(Object settlementKladrId) {
            this.settlementKladrId = settlementKladrId;
            return this;
        }

        public AddressStandardResponseBuilder withSettlementWithType(Object settlementWithType) {
            this.settlementWithType = settlementWithType;
            return this;
        }

        public AddressStandardResponseBuilder withSettlementType(Object settlementType) {
            this.settlementType = settlementType;
            return this;
        }

        public AddressStandardResponseBuilder withSettlementTypeFull(Object settlementTypeFull) {
            this.settlementTypeFull = settlementTypeFull;
            return this;
        }

        public AddressStandardResponseBuilder withSettlement(Object settlement) {
            this.settlement = settlement;
            return this;
        }

        public AddressStandardResponseBuilder withStreetFiasId(String streetFiasId) {
            this.streetFiasId = streetFiasId;
            return this;
        }

        public AddressStandardResponseBuilder withStreetKladrId(String streetKladrId) {
            this.streetKladrId = streetKladrId;
            return this;
        }

        public AddressStandardResponseBuilder withStreetWithType(String streetWithType) {
            this.streetWithType = streetWithType;
            return this;
        }

        public AddressStandardResponseBuilder withStreetType(String streetType) {
            this.streetType = streetType;
            return this;
        }

        public AddressStandardResponseBuilder withStreetTypeFull(String streetTypeFull) {
            this.streetTypeFull = streetTypeFull;
            return this;
        }

        public AddressStandardResponseBuilder withStreet(String street) {
            this.street = street;
            return this;
        }

        public AddressStandardResponseBuilder withHouseFiasId(String houseFiasId) {
            this.houseFiasId = houseFiasId;
            return this;
        }

        public AddressStandardResponseBuilder withHouseKladrId(String houseKladrId) {
            this.houseKladrId = houseKladrId;
            return this;
        }

        public AddressStandardResponseBuilder withHouseType(String houseType) {
            this.houseType = houseType;
            return this;
        }

        public AddressStandardResponseBuilder withHouseTypeFull(String houseTypeFull) {
            this.houseTypeFull = houseTypeFull;
            return this;
        }

        public AddressStandardResponseBuilder withHouse(String house) {
            this.house = house;
            return this;
        }

        public AddressStandardResponseBuilder withBlockType(Object blockType) {
            this.blockType = blockType;
            return this;
        }

        public AddressStandardResponseBuilder withBlockTypeFull(Object blockTypeFull) {
            this.blockTypeFull = blockTypeFull;
            return this;
        }

        public AddressStandardResponseBuilder withBlock(Object block) {
            this.block = block;
            return this;
        }

        public AddressStandardResponseBuilder withFlatType(String flatType) {
            this.flatType = flatType;
            return this;
        }

        public AddressStandardResponseBuilder withFlatTypeFull(String flatTypeFull) {
            this.flatTypeFull = flatTypeFull;
            return this;
        }

        public AddressStandardResponseBuilder withFlat(String flat) {
            this.flat = flat;
            return this;
        }

        public AddressStandardResponseBuilder withFlatArea(String flatArea) {
            this.flatArea = flatArea;
            return this;
        }

        public AddressStandardResponseBuilder withSquareMeterPrice(String squareMeterPrice) {
            this.squareMeterPrice = squareMeterPrice;
            return this;
        }

        public AddressStandardResponseBuilder withFlatPrice(String flatPrice) {
            this.flatPrice = flatPrice;
            return this;
        }

        public AddressStandardResponseBuilder withPostalBox(Object postalBox) {
            this.postalBox = postalBox;
            return this;
        }

        public AddressStandardResponseBuilder withFiasId(String fiasId) {
            this.fiasId = fiasId;
            return this;
        }

        public AddressStandardResponseBuilder withFiasCode(String fiasCode) {
            this.fiasCode = fiasCode;
            return this;
        }

        public AddressStandardResponseBuilder withFiasLevel(String fiasLevel) {
            this.fiasLevel = fiasLevel;
            return this;
        }

        public AddressStandardResponseBuilder withFiasActualityState(String fiasActualityState) {
            this.fiasActualityState = fiasActualityState;
            return this;
        }

        public AddressStandardResponseBuilder withKladrId(String kladrId) {
            this.kladrId = kladrId;
            return this;
        }

        public AddressStandardResponseBuilder withCapitalMarker(String capitalMarker) {
            this.capitalMarker = capitalMarker;
            return this;
        }

        public AddressStandardResponseBuilder withOkato(String okato) {
            this.okato = okato;
            return this;
        }

        public AddressStandardResponseBuilder withOktmo(String oktmo) {
            this.oktmo = oktmo;
            return this;
        }

        public AddressStandardResponseBuilder withTaxOffice(String taxOffice) {
            this.taxOffice = taxOffice;
            return this;
        }

        public AddressStandardResponseBuilder withTaxOfficeLegal(String taxOfficeLegal) {
            this.taxOfficeLegal = taxOfficeLegal;
            return this;
        }

        public AddressStandardResponseBuilder withTimezone(String timezone) {
            this.timezone = timezone;
            return this;
        }

        public AddressStandardResponseBuilder withGeoLat(String geoLat) {
            this.geoLat = geoLat;
            return this;
        }

        public AddressStandardResponseBuilder withGeoLon(String geoLon) {
            this.geoLon = geoLon;
            return this;
        }

        public AddressStandardResponseBuilder withBeltwayHit(String beltwayHit) {
            this.beltwayHit = beltwayHit;
            return this;
        }

        public AddressStandardResponseBuilder withBeltwayDistance(Object beltwayDistance) {
            this.beltwayDistance = beltwayDistance;
            return this;
        }

        public AddressStandardResponseBuilder withQcGeo(Integer qcGeo) {
            this.qcGeo = qcGeo;
            return this;
        }

        public AddressStandardResponseBuilder withQcComplete(Integer qcComplete) {
            this.qcComplete = qcComplete;
            return this;
        }

        public AddressStandardResponseBuilder withQcHouse(Integer qcHouse) {
            this.qcHouse = qcHouse;
            return this;
        }

        public AddressStandardResponseBuilder withQc(Integer qc) {
            this.qc = qc;
            return this;
        }

        public AddressStandardResponseBuilder withUnparsedParts(String unparsedParts) {
            this.unparsedParts = unparsedParts;
            return this;
        }

        public AddressStandardResponseBuilder withMetro(List<Metro> metro) {
            this.metro = metro;
            return this;
        }

        public AddressStandardResponse build() {
            return new AddressStandardResponse(source, result, postalCode, country, countryIsoCode, federalDistrict,
                    regionFiasId, regionKladrId, regionIsoCode, regionWithType, regionType, regionTypeFull, region,
                    areaFiasId, areaKladrId, areaWithType, areaType, areaTypeFull, area, cityFiasId, cityKladrId,
                    cityWithType, cityType, cityTypeFull, city, cityArea, cityDistrictFiasId, cityDistrictKladrId,
                    cityDistrictWithType, cityDistrictType, cityDistrictTypeFull, cityDistrict, settlementFiasId,
                    settlementKladrId, settlementWithType, settlementType, settlementTypeFull, settlement, streetFiasId,
                    streetKladrId, streetWithType, streetType, streetTypeFull, street, houseFiasId, houseKladrId,
                    houseType, houseTypeFull, house, blockType, blockTypeFull, block, flatType, flatTypeFull, flat,
                    flatArea, squareMeterPrice, flatPrice, postalBox, fiasId, fiasCode, fiasLevel, fiasActualityState,
                    kladrId, capitalMarker, okato, oktmo, taxOffice, taxOfficeLegal, timezone, geoLat, geoLon, beltwayHit,
                    beltwayDistance, qcGeo, qcComplete, qcHouse, qc, unparsedParts, metro);
        }
    }
}