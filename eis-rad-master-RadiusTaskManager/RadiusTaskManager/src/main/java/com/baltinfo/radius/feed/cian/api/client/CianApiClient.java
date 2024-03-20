package com.baltinfo.radius.feed.cian.api.client;

import com.baltinfo.radius.feed.cian.api.model.GetOrderResponse;
import com.baltinfo.radius.feed.cian.api.model.GetStatisticResponse;
import com.baltinfo.radius.utils.Result;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Igor Lapenok
 * @since 10.02.2021
 */
public class CianApiClient {

    private static final Logger logger = LoggerFactory.getLogger(CianApiClient.class);

    private static final String PREFIX_TOKEN = "Bearer ";
    private final String baseUrl;
    private final String accessKey;

    public CianApiClient(String baseUrl, String accessKey) {
        this.baseUrl = baseUrl;
        this.accessKey = accessKey;
    }

    public Result<GetOrderResponse, String> getOrder() {
        try {
            HttpResponse<GetOrderResponse> response = Unirest.get(baseUrl + "/get-order")
                    .header("Content-Type", "application/json")
                    .header("Authorization", PREFIX_TOKEN + accessKey)
                    .asObject(GetOrderResponse.class);
            if (response.getStatus() != HttpStatus.SC_OK) {
                logger.error("Error getOrder. statusCode: {}, statusText: {}", response.getStatus(), response.getStatusText());
                return Result.error(response.getStatus() + " " + response.getStatusText());
            }
            return Result.ok(response.getBody());
        } catch (UnirestException e) {
            logger.error("Error when call get-order cian api method", e);
            return Result.error("Error when call get-order cian api method");
        }
    }

    public Result<GetStatisticResponse, String> getViewsStatistics(List<String> offersIds) {
        try {
            HttpResponse<GetStatisticResponse> response = Unirest.get(baseUrl +
                    "/get-views-statistics?offersIds=" +
                    String.join("&offersIds=", offersIds))
                    .header("Content-Type", "application/json")
                    .header("Authorization", PREFIX_TOKEN + accessKey)
                    .asObject(GetStatisticResponse.class);

            if (response.getStatus() != HttpStatus.SC_OK) {
                logger.error("Error getViewsStatistics. statusCode: {}, statusText: {}", response.getStatus(), response.getStatusText());
                return Result.error(response.getStatus() + " " + response.getStatusText());
            }
            return Result.ok(response.getBody());
        } catch (UnirestException e) {
            logger.error("Error when call get-views-statistics cian api method", e);
            return Result.error("Error when call get-views-statistics cian api method");
        }
    }
}
