package com.baltinfo.radius.dadata.services;

import com.baltinfo.radius.dadata.client.DadataClient;
import com.baltinfo.radius.dadata.dto.AddressDto;
import com.baltinfo.radius.dadata.model.AddressStandardResponse;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 *     Сервис получение объекта AddressDto из строки адреса
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 13.12.2019
 */
public class DadataService {
    private final Logger logger = LoggerFactory.getLogger(DadataService.class);

    private final DadataClient dadataClient;
    private static final String REGION_SPB_ID = "c2deb16a-0330-4f05-821f-1d09c93331e6";

    public DadataService(DadataClient dadataClient) {
        this.dadataClient = dadataClient;
    }

    public AddressDto createAddressDtoByAddress(String address) {
        address = address.replace("\"", "").replace("\n", " ").replace("\r", " ").replace("\t", " ");
        Result<AddressStandardResponse[], String> result = dadataClient.getAddressStandard(address);
        if (result.isSuccess() && result.getResult().length > 0) {
            AddressStandardResponse addressFromApi = result.getResult()[0];
            return AddressDto.builder()
                    .withSource(addressFromApi.getSource())
                    .withFiasId(addressFromApi.getFiasId())
                    .withHouseNumber(addressFromApi.getHouse())
                    .withPostalCode(addressFromApi.getPostalCode())
                    .withBlockNumber((String) addressFromApi.getBlock())
                    .withFlatNumber(addressFromApi.getFlat())
                    .withLatitude(addressFromApi.getGeoLat())
                    .withLongitude(addressFromApi.getGeoLon())
                    .withRegionFiasId(addressFromApi.getRegionFiasId())
                    .withAreaFiasId((String) addressFromApi.getAreaFiasId())
                    .withCityFiasId((String) addressFromApi.getCityFiasId())
                    .withCityDistrictFiasId((String) addressFromApi.getCityDistrictFiasId())
                    .withSPBDistrictFiasName(addressFromApi.getRegionFiasId() != null && addressFromApi.getRegionFiasId().equals(REGION_SPB_ID)
                            ? addressFromApi.getCityDistrict()
                            : "")
                    .withSettlementFiasId((String) addressFromApi.getSettlementFiasId())
                    .withStreetFiasId(addressFromApi.getStreetFiasId())
                    .withHouseFiasId(addressFromApi.getHouseFiasId())
                    .withInfo(addressFromApi.getUnparsedParts())
                    .withRegionFiasName(addressFromApi.getRegionWithType())
                    .withRegionFiasCode(addressFromApi.getFiasCode() != null && !addressFromApi.getFiasCode().isEmpty()
                            ? addressFromApi.getFiasCode().substring(0, 2)
                            : null)
                    .build();
        } else {
            return AddressDto.builder()
                    .withSource(address)
                    .build();
        }
    }
}
