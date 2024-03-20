package com.baltinfo.radius.rest.client;

import com.baltinfo.radius.utils.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author Lapenok Igor
 * @since 11.04.2019
 */
public class HttpRequestService implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(HttpRequestService.class);

    private final String baseUrl;

    public HttpRequestService(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public <ResponseT> Result<ResponseT, String> executeGetObject(String url, Class<ResponseT> clazz) {
        Long start = new Date().getTime();
        logger.info("start executeGetObject url={} at {}", url, start);
        Result<String, String> result = executeGetRequest(url);
        logger.info("end executeGetObject url={} started at {} duration={}", url, start, new Date().getTime() - start);
        if (result.isError()) {
            return Result.error("Get response error by url = " + url + "; error text = " + result.getError());
        }
        return parse(result.getResult(), clazz);
    }

    public <ResponseT> Result<List<ResponseT>, String> executeGetList(String url, Class<ResponseT> clazz) {
        Long start = new Date().getTime();
        logger.info("start executeGetList url={} at {}", url, start);
        Result<String, String> result = executeGetRequest(url);
        logger.info("end executeGetList url={} started at {} duration={}", url, start, new Date().getTime() - start);
        if (result.isError()) {
            return Result.error("Get response error by url = " + url + "; error text = " + result.getError());
        }
        return parseList(result.getResult(), clazz);
    }


    public <RequestT, ResponseT> Result<ResponseT, String> executePostObject(String url, Class<ResponseT> clazz, RequestT requestT) {
        Long start = new Date().getTime();
        logger.info("start executePostObject url={} at {}", url, start);
        Result<String, String> result = executePostRequest(url, requestT);
        logger.info("end executePostObject url={} started at {} duration={}", url, start, new Date().getTime() - start);
        if (result.isError()) {
            return Result.error("Post response error by url = " + url + "; error text = " + result.getError());
        }
        return parse(result.getResult(), clazz);
    }

    public <RequestT, ResponseT> Result<List<ResponseT>, String> executePostList(String url, Class<ResponseT> clazz, RequestT requestT) {
        Long start = new Date().getTime();
        logger.info("start executePostList url={} at {}", url, start);
        Result<String, String> result = executePostRequest(url, requestT);
        logger.info("end executePostList url={} started at {} duration={}", url, start, new Date().getTime() - start);
        if (result.isError()) {
            return Result.error("Post response error by url = " + url + "; error text = " + result.getError());
        }
        return parseList(result.getResult(), clazz);
    }

    private Result<String, String> executeGetRequest(String url) {
        try (CloseableHttpClient httpclient = HttpClientFactory.createHttpClient()) {
            HttpUriRequest request = new HttpGet(baseUrl + url);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");
            ResponseHandler<Result<String, String>> responseHandler = getResponseHandler(url);
            return httpclient.execute(request, responseHandler);
        } catch (Exception ex) {
            logger.error("Can't execute get request - {}", url, ex);
            return Result.error("Can't execute get request - " + url + "; error text = " + ex.getMessage());
        }
    }

    private <RequestT> Result<String, String> executePostRequest(String url, RequestT requestT) {
        logger.info("executePostRequest baseUrl = {}, url= {}, requestT = {}", baseUrl, url, requestT);
        try (CloseableHttpClient httpclient = HttpClientFactory.createHttpClient()) {
            StringEntity stringEntity;
            if (requestT instanceof String) {
                stringEntity = new StringEntity((String) requestT, Charsets.UTF_8);
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                String requestStr = objectMapper.writeValueAsString(requestT);
                logger.info("executePostRequest requestStr = {}", requestStr);
                stringEntity = new StringEntity(requestStr, Charsets.UTF_8);
            }
            stringEntity.setContentEncoding("utf-8");
            stringEntity.setContentType("application/json");
            HttpPost request = new HttpPost(baseUrl + url);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");
            request.setEntity(stringEntity);

            ResponseHandler<Result<String, String>> responseHandler = getResponseHandler(url);
            return httpclient.execute(request, responseHandler);
        } catch (Exception ex) {
            logger.error("Can't execute post request - {}", url, ex);
            return Result.error("Can't execute post request - " + url + "; error text = " + ex.getMessage());
        }
    }

    private ResponseHandler<Result<String, String>> getResponseHandler(String url) {
        return response -> {
            try {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return Result.ok(entity != null ? EntityUtils.toString(entity) : null);
                } else {
                    return Result.error("Unexpected response status: " + status);
                }
            } catch (Exception e) {
                logger.error("Get response error by url = {}", url, e);
                return Result.error("Get response error by url = " + url + "; error text = " + e.getMessage());
            }
        };
    }

    private <ResponseT> Result<ResponseT, String> parse(String json, Class<ResponseT> clazz) {
        try {
            if (json == null || json.isEmpty()) {
                logger.warn("Service returned an empty object");
                return Result.error("Service returned an empty object");
            }
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseT result = objectMapper.readValue(json, clazz);
            return Result.ok(result);
        } catch (Exception ex) {
            logger.error("Can't parse response: {}", json, ex);
            return Result.error("Can't parse response: " + json + "; error text = " + ex.getMessage());
        }
    }

    private <ResponseT> Result<List<ResponseT>, String> parseList(String json, Class<ResponseT> clazz) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CollectionType typeReference = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
            List<ResponseT> result = objectMapper.readValue(json, typeReference);
            return Result.ok(result);
        } catch (Exception ex) {
            logger.error("Can't parse response: {}", json, ex);
            return Result.error("Can't parse response: " + json + "; error text = " + ex.getMessage());
        }
    }
}
