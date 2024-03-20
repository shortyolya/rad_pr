package com.baltinfo.radius.calls.client;

import com.baltinfo.radius.calls.model.CallServiceResponse;
import com.baltinfo.radius.dadata.client.DadataClient;
import com.baltinfo.radius.utils.Result;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Lapenok
 * @since 01.09.2023
 */
public class CallsApiClient {
    private static final Logger logger = LoggerFactory.getLogger(DadataClient.class);

    private static final String PREFIX_TOKEN = "Bearer ";
    private final String baseUrl;
    private final String apiKey;


    public CallsApiClient(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    public Result<CallServiceResponse, String> getCallCount(String lotCode) {
        String url = baseUrl + "/call-count";
        try {
            CallServiceResponse response = Unirest.get(baseUrl + "/call-count")
                    .queryString("code", lotCode)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", PREFIX_TOKEN + apiKey)
                    .asObject(CallServiceResponse.class)
                    .getBody();
            return Result.ok(response);
        } catch (UnirestException e) {
            logger.error("Error in call service: url = {}", url, e);
            return Result.error("Error in call service " + url);
        }
    }
}
