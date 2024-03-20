package com.baltinfo.radius.slowTest.configuration;

import com.baltinfo.radius.dadata.client.DadataClient;
import com.baltinfo.radius.dadata.model.AddressResponse;
import com.baltinfo.radius.dadata.model.AddressStandardResponse;
import com.baltinfo.radius.dadata.model.Data;
import com.baltinfo.radius.dadata.model.Metro;
import com.baltinfo.radius.dadata.model.Suggestion;
import com.baltinfo.radius.utils.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * <p>
 *     Заглушка для тестирования работы сервиса
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 11.12.2019
 */
@Configuration
public class DadataClientConfiguration {
    @Bean
    DadataClient dadataClient() {
        String lat = "59.715";
        String lon = "30.421";
        String addressFirst = "Архангельск, Логинова 20/175 48";
        String addressSecond = "Санкт-Петербург, Торфяная дорога, 7 лит Ф, 812";
        AddressResponse addressResponse = getAddressResponse();
        AddressStandardResponse[] responseFirst = getAddressStandardResponseFirstTest();
        AddressStandardResponse[] responseSecond = getAddressStandardResponseSecondTest();
        DadataClient dadataClient = mock(DadataClient.class);
        when(dadataClient.getAddressByCoordinate(lat, lon)).thenReturn(Result.ok(addressResponse));
        when(dadataClient.getAddressStandard(addressFirst)).thenReturn(Result.ok(responseFirst));
        when(dadataClient.getAddressStandard(addressSecond)).thenReturn(Result.ok(responseSecond));
        return dadataClient;
    }

    private AddressStandardResponse[] getAddressStandardResponseFirstTest() {
        AddressStandardResponse response = AddressStandardResponse.builder()
        .withSource("Архангельск, Логинова 20/175 48")
        .withResult("г Архангельск, ул Логинова, д 20/175, кв 48")
        .withPostalCode("163046")
        .withCountry("Россия")
        .withCountryIsoCode("RU")
        .withFederalDistrict("Северо-Западный")
        .withRegionFiasId("294277aa-e25d-428c-95ad-46719c4ddb44")
        .withRegionKladrId("2900000000000")
        .withRegionIsoCode("RU-ARK")
        .withRegionWithType("Архангельская обл")
        .withRegionType("обл")
        .withRegionTypeFull("область")
        .withRegion("Архангельская")
        .withAreaFiasId(null)
        .withAreaKladrId(null)
        .withAreaWithType(null)
        .withAreaType(null)
        .withAreaTypeFull(null)
        .withArea(null)
        .withCityFiasId("06814fb6-0dc3-4bec-ba20-11f894a0faf5")
        .withCityKladrId("2900000100000")
        .withCityWithType("г Архангельск")
        .withCityType("г")
        .withCityTypeFull("город")
        .withCity("Архангельск")
        .withCityArea(null)
        .withCityDistrictFiasId(null)
        .withCityDistrictKladrId(null)
        .withCityDistrictWithType(null)
        .withCityDistrictType(null)
        .withCityDistrictTypeFull(null)
        .withCityDistrict(null)
        .withSettlementFiasId(null)
        .withSettlementKladrId(null)
        .withSettlementWithType(null)
        .withSettlementType(null)
        .withSettlementTypeFull(null)
        .withSettlement(null)
        .withStreetFiasId("870ed7ef-a187-4969-8422-49a6aad1202f")
        .withStreetKladrId("29000001000025400")
        .withStreetWithType("ул Логинова")
        .withStreetType("ул")
        .withStreetTypeFull("улица")
        .withStreet("Логинова")
        .withHouseFiasId(null)
        .withHouseKladrId(null)
        .withHouseType("д")
        .withHouseTypeFull("дом")
        .withHouse("20/175")
        .withBlockType(null)
        .withBlockTypeFull(null)
        .withBlock(null)
        .withFlatType("кв")
        .withFlatTypeFull("квартира")
        .withFlat("48")
        .withFlatArea(null)
        .withSquareMeterPrice(null)
        .withFlatPrice(null)
        .withPostalBox(null)
        .withFiasId("870ed7ef-a187-4969-8422-49a6aad1202f")
        .withFiasCode("29000001000000002540000")
        .withFiasLevel("7")
        .withFiasActualityState("0")
        .withKladrId("29000001000025400")
        .withCapitalMarker("2")
        .withOkato("11401000000")
        .withOktmo("11701000")
        .withTaxOffice("2901")
        .withTaxOfficeLegal("2901")
        .withTimezone("UTC+3")
        .withGeoLat("64.5479165")
        .withGeoLon("40.5298203")
        .withBeltwayHit(null)
        .withBeltwayDistance(null)
        .withQcGeo(1)
        .withQcComplete(10)
        .withQcHouse(10)
        .withQc(0)
        .withUnparsedParts(null)
        .withMetro(null)
        .build();
        AddressStandardResponse[] responses = new AddressStandardResponse[1];
        responses[0] = response;
        return responses;
    }

    private AddressStandardResponse[] getAddressStandardResponseSecondTest() {
        List<Metro> metroList = new ArrayList<>();
        Metro metro = new Metro(0.3, "Фрунзенско-Приморская", "Старая деревня");
        metroList.add(metro);
        metro = new Metro(2.0, "Фрунзенско-Приморская", "Комендантский проспект");
        metroList.add(metro);
        metro = new Metro(2.1, "Фрунзенско-Приморская", "Крестовский остров");
        metroList.add(metro);
        AddressStandardResponse response = AddressStandardResponse.builder()
        .withSource("Санкт-Петербург, Торфяная дорога, 7 лит Ф, 812")
        .withResult("г Санкт-Петербург, дор Торфяная, д 7 литер ф, кв 812")
        .withPostalCode("197374")
        .withCountry("Россия")
        .withCountryIsoCode("RU")
        .withFederalDistrict("Северо-Западный")
        .withRegionFiasId("c2deb16a-0330-4f05-821f-1d09c93331e6")
        .withRegionKladrId("7800000000000")
        .withRegionIsoCode("RU-SPE")
        .withRegionWithType("г Санкт-Петербург")
        .withRegionType("г")
        .withRegionTypeFull("город")
        .withRegion("Санкт-Петербург")
        .withAreaFiasId(null)
        .withAreaKladrId(null)
        .withAreaWithType(null)
        .withAreaType(null)
        .withAreaTypeFull(null)
        .withArea(null)
        .withCityFiasId(null)
        .withCityKladrId(null)
        .withCityWithType(null)
        .withCityType(null)
        .withCityTypeFull(null)
        .withCity(null)
        .withCityArea(null)
        .withCityDistrictFiasId(null)
        .withCityDistrictKladrId(null)
        .withCityDistrictWithType("р-н Приморский")
        .withCityDistrictType("р-н")
        .withCityDistrictTypeFull("район")
        .withCityDistrict("Приморский")
        .withSettlementFiasId(null)
        .withSettlementKladrId(null)
        .withSettlementWithType(null)
        .withSettlementType(null)
        .withSettlementTypeFull(null)
        .withSettlement(null)
        .withStreetFiasId("9e8bc9e3-e687-4c1e-a9cb-283209129e21")
        .withStreetKladrId("78000000000140300")
        .withStreetWithType("дор Торфяная")
        .withStreetType("дор")
        .withStreetTypeFull("дорога")
        .withStreet("Торфяная")
        .withHouseFiasId("4972a027-c20b-4837-bffe-1a8fd2dbdb42")
        .withHouseKladrId("7800000000014030014")
        .withHouseType("д")
        .withHouseTypeFull("дом")
        .withHouse("7")
        .withBlockType("литер")
        .withBlockTypeFull("литер")
        .withBlock("ф")
        .withFlatType("кв")
        .withFlatTypeFull("квартира")
        .withFlat("812")
        .withFlatArea(null)
        .withSquareMeterPrice(null)
        .withFlatPrice(null)
        .withPostalBox(null)
        .withFiasId("4972a027-c20b-4837-bffe-1a8fd2dbdb42")
        .withFiasCode("78000000000000014030014")
        .withFiasLevel("8")
        .withFiasActualityState("0")
        .withKladrId("7800000000014030014")
        .withCapitalMarker("0")
        .withOkato("40270565000")
        .withOktmo("40325000")
        .withTaxOffice("7814")
        .withTaxOfficeLegal("7814")
        .withTimezone("UTC+3")
        .withGeoLat("59.9908661")
        .withGeoLon("30.2599807")
        .withBeltwayHit("IN_KAD")
        .withBeltwayDistance(null)
        .withQcGeo(0)
        .withQcComplete(0)
        .withQcHouse(2)
        .withQc(0)
        .withUnparsedParts(null)
        .withMetro(metroList)
        .build();
        AddressStandardResponse[] responses = new AddressStandardResponse[1];
        responses[0] = response;
        return responses;
    }

    private AddressResponse getAddressResponse() {
        Data data = Data.builder()
                .withPostalCode("196601")
                .withCountry("Россия")
                .withCountryIsoCode("RU")
                .withFederalDistrict(null)
                .withRegionFiasId("c2deb16a-0330-4f05-821f-1d09c93331e6")
                .withRegionKladrId("7800000000000")
                .withRegionIsoCode("RU-SPE")
                .withRegionWithType("г Санкт-Петербург")
                .withRegionType("г")
                .withRegionTypeFull("город")
                .withRegion("Санкт-Петербург")
                .withAreaFiasId(null)
                .withAreaKladrId(null)
                .withAreaWithType(null)
                .withAreaType(null)
                .withAreaTypeFull(null)
                .withArea(null)
                .withCityFiasId("110d6ad9-0b64-47cf-a2ee-7e935228799c")
                .withCityKladrId("7800000900000")
                .withCityWithType("г Пушкин")
                .withCityType("г")
                .withCityTypeFull("город")
                .withCity("Пушкин")
                .withCityArea(null)
                .withCityDistrictFiasId(null)
                .withCityDistrictKladrId(null)
                .withCityDistrictWithType(null)
                .withCityDistrictType(null)
                .withCityDistrictTypeFull(null)
                .withCityDistrict(null)
                .withSettlementFiasId(null)
                .withSettlementKladrId(null)
                .withSettlementWithType(null)
                .withSettlementType(null)
                .withSettlementTypeFull(null)
                .withSettlement(null)
                .withStreetFiasId("26361819-f20b-4d5b-a8d1-b638ff5e5af3")
                .withStreetKladrId("78000009000007300")
                .withStreetWithType("Московское шоссе")
                .withStreetType("ш")
                .withStreetTypeFull("шоссе")
                .withStreet("Московское")
                .withHouseFiasId(null)
                .withHouseKladrId(null)
                .withHouseType("д")
                .withHouseTypeFull("дом")
                .withHouse("7")
                .withBlockType(null)
                .withBlockTypeFull(null)
                .withBlock(null)
                .withFlatType(null)
                .withFlatTypeFull(null)
                .withFlat(null)
                .withFlatArea(null)
                .withSquareMeterPrice(null)
                .withFlatPrice(null)
                .withPostalBox(null)
                .withFiasId("26361819-f20b-4d5b-a8d1-b638ff5e5af3")
                .withFiasCode(null)
                .withFiasLevel("7")
                .withFiasActualityState(null)
                .withKladrId("78000009000007300")
                .withGeonameId(null)
                .withCapitalMarker("0")
                .withOkato("40294501000")
                .withOktmo("40397000")
                .withTaxOffice("7820")
                .withTaxOfficeLegal("7820")
                .withTimezone(null)
                .withGeoLat("59.714622")
                .withGeoLon("30.422256")
                .withBeltwayHit(null)
                .withBeltwayDistance(null)
                .withMetro(null)
                .withQcGeo("0")
                .withQcComplete(null)
                .withQcHouse(null)
                .withHistoryValues(null)
                .withUnparsedParts(null)
                .withSource(null)
                .withQc(null)
                .build();
        List<Suggestion> suggestionList = new ArrayList<>();
        Suggestion suggestion = new Suggestion("г Санкт-Петербург, г Пушкин, Московское шоссе, д 7", "196601, г Санкт-Петербург, г Пушкин, Московское шоссе, д 7", data);
        suggestionList.add(suggestion);
        return new AddressResponse(suggestionList);
    }
}
