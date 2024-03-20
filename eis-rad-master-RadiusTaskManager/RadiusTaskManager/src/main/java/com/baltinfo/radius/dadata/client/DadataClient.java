package com.baltinfo.radius.dadata.client;

import com.baltinfo.radius.dadata.model.AddressResponse;
import com.baltinfo.radius.dadata.model.AddressStandardResponse;
import com.baltinfo.radius.utils.Result;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Клиент для вызова методов сервсиса dadata.ru
 *
 * @author Igor Lapenok
 * @since 09.12.2019
 */
public class DadataClient {
    private static final Logger logger = LoggerFactory.getLogger(DadataClient.class);

    private static final String PREFIX_TOKEN = "Token ";
    private final String baseUrl;
    private final String baseStandardUrl;
    private final String apiKey;
    private final String secretKey;
    private final boolean demoMode;

    public DadataClient(String baseUrl, String baseStandardUrl, String apiKey, String secretKey, boolean demoMode) {
        this.baseUrl = baseUrl;
        this.baseStandardUrl = baseStandardUrl;
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.demoMode = demoMode;
    }

    public Result<AddressResponse, String> getAddressByCoordinate(String lat, String lon) {
        if (this.demoMode) {
            logger.error("Service was running in demo mode! It don't return any value.");
            return Result.error("Service was running in demo mode! It don't return any value.");
        }
        if (isNumeric(lat) && isNumeric(lon)) {
            String url = baseUrl + "/geolocate/address";
            try {
                AddressResponse addressResponse = Unirest.get(url)
                        .queryString("lat", lat)
                        .queryString("lon", lon)
                        .header("Content-Type", "application/json")
                        .header("Authorization", PREFIX_TOKEN + apiKey)
                        .asObject(AddressResponse.class)
                        .getBody();
                return Result.ok(addressResponse);
            } catch (UnirestException e) {
                logger.error("Error in call ftp dadata.ru url = {}", url, e);
                return Result.error("Error in call ftp " + url);
            }
        } else {
            logger.error("Invalid parameters value: lat = {}, lon = {}", lat, lon);
            return Result.error("Invalid parameters value");
        }
    }

    public Result<AddressStandardResponse[], String> getAddressStandard(String address) {
        if (this.demoMode) {
            logger.error("Service was running in demo mode! It don't return any value.");
            return Result.error("Service was running in demo mode! It don't return any value.");
        }
        try {
            AddressStandardResponse[] addressStandardResponses = Unirest.post(baseStandardUrl)
                    .header("Content-Type", "application/json")
                    .header("Authorization", PREFIX_TOKEN + apiKey)
                    .header("X-Secret", secretKey)
                    .body("[\"" + address + "\"]")
                    .asObject(AddressStandardResponse[].class)
                    .getBody();
            return Result.ok(addressStandardResponses);
        } catch (UnirestException e) {
            logger.error("Error in call ftp dadata.ru url = {}", baseStandardUrl, e);
            return Result.error("Error in call ftp " + baseStandardUrl);
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
