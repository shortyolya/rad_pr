package com.baltinfo.radius.feed.jcat.api.client;

import com.baltinfo.radius.feed.jcat.api.model.JCatReportResponse;
import com.baltinfo.radius.feed.jcat.api.model.ReportUrl;
import com.baltinfo.radius.feed.jcat.api.model.Views;
import com.baltinfo.radius.utils.Result;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 29.03.2021
 */
public class JCatApiClient {

    private static final Logger logger = LoggerFactory.getLogger(JCatApiClient.class);

    private final String baseUrl;
    private final String apiKey;


    public JCatApiClient(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    public Result<List<ReportUrl>, String> getActiveReports() {
        try {
            HttpResponse<JCatReportResponse> response = Unirest.get(baseUrl + "reports/parser/xml")
                    .header("Content-Type", "application/json")
                    .header("X-ApiKey", apiKey)
                    .asObject(JCatReportResponse.class);
            if (response.getStatus() != HttpStatus.SC_OK) {
                logger.error("Error getActiveReports. statusCode: {}, statusText: {}", response.getStatus(), response.getStatusText());
                return Result.error(response.getStatus() + " " + response.getStatusText());
            }
            return Result.ok(response.getBody().getData().getItems());
        } catch (Exception e) {
            logger.error("Can't get JCat active reports", e);
            return Result.error("Can't get JCat active reports");
        }
    }

    public Result<String, String> getReportXml(String url) {
        try {
            HttpResponse<String> response = Unirest.get(url)
                    .asString();
            if (response.getStatus() != HttpStatus.SC_OK) {
                logger.error("Error getReportXml. statusCode: {}, statusText: {}", response.getStatus(), response.getStatusText());
                return Result.error(response.getStatus() + " " + response.getStatusText());
            }
            return Result.ok(response.getBody());
        } catch (Exception e) {
            logger.error("Can't get JCat report", e);
            return Result.error("Can't get JCat report");
        }
    }

    public Result<Views, String> getViews(String id) {
        try {
            Views response = Unirest.get(baseUrl + "orders/" + id + "/stats/views/boards")
                    .header("Content-Type", "application/json")
                    .header("X-ApiKey", apiKey)
                    .asObject(Views.class)
                    .getBody();
            return Result.ok(response);
        } catch (Exception e) {
            logger.error("Can't get JCat object views with id = {}", id, e);
            return Result.error("Can't get JCat object views");
        }
    }

}
