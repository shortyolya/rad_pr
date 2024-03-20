package com.baltinfo.radius.feed.avito.api.client;

import com.baltinfo.radius.feed.avito.api.model.AvitoReportItem;
import com.baltinfo.radius.feed.avito.api.model.AvitoReportResponse;
import com.baltinfo.radius.feed.avito.api.model.AvitoReportsList;
import com.baltinfo.radius.feed.avito.api.model.AvitoUser;
import com.baltinfo.radius.feed.avito.api.model.PostStatisticResponse;
import com.baltinfo.radius.feed.avito.api.model.ReportFromList;
import com.baltinfo.radius.feed.avito.api.model.Token;
import com.baltinfo.radius.utils.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Lapenok
 * @since 10.02.2021
 */
public class AvitoApiClient {

    private static final Logger logger = LoggerFactory.getLogger(AvitoApiClient.class);

    private static final String PREFIX_TOKEN = "Bearer ";
    private static final int MAX_ATTEMPTS = 5;
    private static final int PER_PAGE_ITEMS = 200;
    private final String baseUrl;
    private final String clientId;
    private final String clientSecret;
    private final LocalDate firstDay;
    private final ObjectMapper objectMapper;


    public AvitoApiClient(String baseUrl, String clientId, String clientSecret, String firstDay) {
        this.baseUrl = baseUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.firstDay = LocalDate.parse(firstDay);

        objectMapper = new ObjectMapper() {
            private final com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    public Result<String, String> authorization() {
        try {
            HttpResponse<String> response = Unirest.post(baseUrl + "/token")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .body("grant_type=client_credentials&client_id=" + clientId + "&client_secret=" + clientSecret)
                    .asString();
            if (response.getStatus() != HttpStatus.SC_OK) {
                logger.error("Error authorization. statusCode: {}, statusText: {}, body: {}",
                        response.getStatus(), response.getStatusText(), response.getBody());
                return Result.error(response.getStatus() + " " + response.getStatusText());
            }
            return Result.ok(objectMapper.readValue(response.getBody(), Token.class).getAccessToken());
        } catch (UnirestException e) {
            logger.error("Error in authorization to avito api", e);
            return Result.error("Error in authorization to avito api");
        }
    }

    public Result<AvitoUser, String> getUser(String token) {
        try {
            HttpResponse<String> response = Unirest.get(baseUrl + "/core/v1/accounts/self")
                    .header("Content-Type", "application/json")
                    .header("Authorization", PREFIX_TOKEN + token)
                    .asString();
            if (response.getStatus() != HttpStatus.SC_OK) {
                logger.error("Error getUser. token: {}, statusCode: {}, statusText: {}, body: {}",
                        token, response.getStatus(), response.getStatusText(), response.getBody());
                return Result.error(response.getStatus() + " " + response.getStatusText());
            }
            return Result.ok(objectMapper.readValue(response.getBody(), AvitoUser.class));
        } catch (UnirestException e) {
            logger.error("Can't get avito user info", e);
            return Result.error("Can't get avito user info");
        }
    }

    private Result<AvitoReportsList, String> getReportsList(String token, int page, int perPage) {
        try {
            GetRequest request = Unirest.get(baseUrl + "/autoload/v2/reports?page=" + page + "&per_page=" + perPage)
                    .header("Content-Type", "application/json")
                    .header("Authorization", PREFIX_TOKEN + token);
            Result<HttpResponse<String>, String> response = getRequest(request, 1);
            if (response.isSuccess()) {
                return Result.ok(objectMapper.readValue(response.getResult().getBody(), AvitoReportsList.class));
            } else {
                logger.error("Can't get avito user reports list.");
                return Result.error("Can't get avito user reports list");
            }
        } catch (Exception e) {
            logger.error("Can't get avito user reports list.", e);
            return Result.error("Can't get avito user reports list");
        }
    }

    public Result<List<ReportFromList>, String> getReportsList(String token) {
        Result<AvitoReportsList, String> result = getReportsList(token, 0, PER_PAGE_ITEMS);
        if (result.isError()) {
            return Result.error(result.getError());
        }
        List<ReportFromList> items = result.getResult().getReports();
        for (int page = 1; page < result.getResult().getMeta().getPages(); page++ ) {
            result = getReportsList(token, page, PER_PAGE_ITEMS);
            if (result.isError()) {
                return Result.error(result.getError());
            }
            items.addAll(result.getResult().getReports());
        }
        return Result.ok(items);
    }

    public Result<List<AvitoReportItem>, String> getReportById(String token, Long reportId) {
        Result<AvitoReportResponse, String> result = getReportById(token, reportId, 0, PER_PAGE_ITEMS);
        if (result.isError()) {
            return Result.error(result.getError());
        }
        List<AvitoReportItem> items = result.getResult().getItems();
        for (int page = 1; page < result.getResult().getMeta().getPages(); page++ ) {
            result = getReportById(token, reportId, page, PER_PAGE_ITEMS);
            if (result.isError()) {
                return Result.error(result.getError());
            }
            items.addAll(result.getResult().getItems());
        }
        return Result.ok(items);
    }

    private Result<AvitoReportResponse, String> getReportById(String token, Long reportId, int page, int perPage) {
        try {
            GetRequest request = Unirest.get(baseUrl + "/autoload/v2/reports/" + reportId + "/items?page=" + page + "&per_page=" + perPage)
                    .header("Content-Type", "application/json")
                    .header("Authorization", PREFIX_TOKEN + token);
            Result<HttpResponse<String>, String> response = getRequest(request, 1);
            if (response.isSuccess()) {
                return Result.ok(objectMapper.readValue(response.getResult().getBody(), AvitoReportResponse.class));
            } else {
                logger.error("Can't get avito user report by id: {}", reportId);
                return Result.error("Can't get avito user report by id");
            }
        } catch (Exception e) {
            logger.error("Can't get avito user report by id: {}", reportId, e);
            return Result.error("Can't get avito user report by id");
        }
    }

    public Result<PostStatisticResponse, String> getViewsStatistics(String token, Long userId, String body) {
        try {
            PostStatisticResponse response = Unirest.post(baseUrl + "/stats/v1/accounts/" + userId + "/items")
                    .header("Content-Type", "application/json")
                    .header("Authorization", PREFIX_TOKEN + token)
                    .body(body)
                    .asObject(PostStatisticResponse.class)
                    .getBody();
            return Result.ok(response);
        } catch (UnirestException e) {
            logger.error("Can't get avito views statistic", e);
            return Result.error("Can't get avito views statistic");
        }
    }

    public Result<PostStatisticResponse, String> getCallsStatistics(String token, Long userId, String body) {
        try {
            PostStatisticResponse response = Unirest.post(baseUrl + "/core/v1/accounts/" + userId + "/calls/stats/")
                    .header("Content-Type", "application/json")
                    .header("Authorization", PREFIX_TOKEN + token)
                    .body(body)
                    .asObject(PostStatisticResponse.class)
                    .getBody();
            return Result.ok(response);
        } catch (UnirestException e) {
            logger.error("Can't get avito calls statistic", e);
            return Result.error("Can't get avito calls statistic: " + e.getMessage());
        }
    }

    private Result<HttpResponse<String>, String> getRequest(GetRequest getRequest, int attempt) {
        logger.info("exec getRequest. request: {},  attempt: {}", getRequest.getUrl(), attempt);
        if (attempt > MAX_ATTEMPTS) {
            return Result.error("Не удалось выполнить запрос " + getRequest.getUrl());
        }
        try {
            HttpResponse<String> response = getRequest.asString();
            if (response.getStatus() == HttpStatus.SC_GATEWAY_TIMEOUT) {
                logger.error("Error getRequest. request: {},  attempt: {}, statusCode: {}, statusText: {}, body: {}",
                        getRequest.getUrl(), attempt, response.getStatus(), response.getStatusText(), response.getBody());
                return getRequest(getRequest, attempt + 1);
            } else if (response.getStatus() != HttpStatus.SC_OK) {
                logger.error("Error getRequest. request: {},  attempt: {}, statusCode: {}, statusText: {}, body: {}",
                        getRequest.getUrl(), attempt, response.getStatus(), response.getStatusText(), response.getBody());
                return Result.error("Не удалось выполнить запрос " + getRequest.getUrl());
            }
            return Result.ok(response);
        } catch (UnirestException e) {
            logger.error("Error getRequest. request: {}, attempt: {}",
                    getRequest.getUrl(), attempt, e);
            return getRequest(getRequest, attempt + 1);
        }
    }

    public String getClientId() {
        return clientId;
    }

    public LocalDate getFirstDay() {
        return firstDay;
    }

}
