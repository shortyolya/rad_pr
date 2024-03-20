package com.baltinfo.radius.yandex.client;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Optional;

/**
 * <p>
 * Клиент для вызова StaticAPI от Yandex
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 03.06.2020
 */
public class YandexMapApiClient {
    private static Logger logger = LoggerFactory.getLogger(YandexMapApiClient.class);

    private static String baseUrl;
    private static String apiKey;
    private static boolean demoMode;

    private static final int STATUS_OK = 200;

    public YandexMapApiClient(String baseUrl, String apiKey, boolean demoMode) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.demoMode = demoMode;
    }

    public Optional<byte[]> getMapImage(String lon, String lat) {
        int width = 650;
        int height = 450;
        return getMapImage(lon, lat, width, height, 17);
    }

    public Optional<byte[]> getMapImage(String lon, String lat, int width, int height, int z) {
        String coordinate = lon.replace(',', '.') + "," + lat.replace(',', '.');
        try {
            if (demoMode) {
                HttpResponse<InputStream> response = Unirest.get(baseUrl)
                        .queryString("ll", coordinate)
                        .queryString("z", z)
                        .queryString("size", width + "," + height)
                        .queryString("l", "map")
                        .queryString("pt", coordinate + ",org")
                        .asBinary();
                return checkResponse(response);
            } else {
                HttpResponse<InputStream> response = Unirest.get(baseUrl)
                        .queryString("key", apiKey)
                        .queryString("ll", coordinate)
                        .queryString("z", 17)
                        .queryString("size", width + "," + height)
                        .queryString("l", "map")
                        .queryString("pt", coordinate + ",org")
                        .asBinary();
                return checkResponse(response);
            }
        } catch (Exception e) {
            logger.error("Error in call service YandexMap Static API url = {}", baseUrl, e);
            return Optional.empty();
        }
    }

    private Optional<byte[]> checkResponse(HttpResponse<InputStream> response) {
        try {
            if (response.getStatus() != STATUS_OK) {
                logger.error("Error in call service YandexMap Static API url = {}. Failure response code = {}", baseUrl, response.getStatus());
                return Optional.empty();
            }
            byte[] resultArray = IOUtils.toByteArray(response.getBody());
            return Optional.of(resultArray);
        } catch (Exception e) {
            logger.error("Can't get image source from YandexMap Static API url = {}", baseUrl, e);
            return Optional.empty();
        }
    }
}
