package com.baltinfo.radius.radapi.client;


import com.baltinfo.radius.notification.paydoc.DocumentDto;
import com.baltinfo.radius.radapi.model.ApiExplanationResponse;
import com.baltinfo.radius.radapi.model.StringDto;
import com.baltinfo.radius.utils.Result;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Objects;

/**
 * @author Igor Lapenok
 * @since 13.12.2021
 */
public class RadApiClient {
    private static final Logger logger = LoggerFactory.getLogger(RadApiClient.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String LANGUAGE_HEADER = "lang";
    private static final String DEFAULT_LANGUAGE = "ru";

    private final String baseUrl;
    private final CloseableHttpClient client;
    private final ObjectMapper objectMapper;

    public RadApiClient(String baseUrl, int timeout) {
        this.baseUrl = baseUrl;
        this.objectMapper = new ObjectMapper()
                .findAndRegisterModules()
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .enable(SerializationFeature.INDENT_OUTPUT);

        this.client = getHttpsClient(timeout);
    }

    private CloseableHttpClient getHttpsClient(int timeout) {
        try {
            SSLContext sslContext = new SSLContextBuilder()
                    .loadTrustMaterial(null, new TrustSelfSignedStrategy() {
                        public boolean isTrusted(X509Certificate[] chain, String authType) {
                            return true;
                        }
                    }).build();

            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(timeout)
                    .setConnectionRequestTimeout(timeout)
                    .setSocketTimeout(timeout)
                    .build();

            return HttpClients.custom()
                    .setSSLContext(sslContext)
                    .setSSLHostnameVerifier(new NoopHostnameVerifier())
                    .setDefaultRequestConfig(config)
                    .build();
        } catch (Exception e) {
            logger.error("Can't create Static API rest client", e);
        }
        return null;
    }

    public Result<List<ApiExplanationResponse>, String> getUnansweredExplanationResponses(String token) {

        Result<List<ApiExplanationResponse>, String> response = this.get("/exr/list",
                token,
                new TypeReference<List<ApiExplanationResponse>>() {
                });
        if (response.isError()) {
            return Result.error("Ошибка при вызове сервиса получения запрсов на разъяснение: " + response.getError());
        }
        return Result.ok(response.getResult());
    }

    public Result<StringDto, String> uploadEisFile(String token, DocumentDto document, Long dfUnid) {

        Result<StringDto, String> response = this.post("/eis/upload-file/" + dfUnid,
                document,
                token,
                new TypeReference<StringDto>() {
                });
        if (response.isError()) {
            return Result.error("Ошибка при вызове сервиса редактирования документа: " + response.getError());
        }
        return Result.ok(response.getResult());
    }

    public Result<Void, String> updateLotVitrinaNfa(Long lotId, String token) {
        Result<StringDto, String> response = this.post("/bkr-exchange/update-lot",
                lotId,
                token,
                new TypeReference<StringDto>() {
                });
        if (response.isError()) {
            return Result.error("Не удалось передать идентификатор лота для обновления на витрине НФА. Текст ошибки: " + response.getError());
        }
        return Result.ok();
    }

    public Result<Void, String> updateLotVitrinaNfaLO(Long lotId, String token) {
        Result<StringDto, String> response = this.post("/lot-exchange/update-lot",
                lotId,
                token,
                new TypeReference<StringDto>() {
                });
        if (response.isError()) {
            return Result.error("Не удалось передать идентификатор лота для обновления на витрине НФА. Текст ошибки: " + response.getError());
        }
        return Result.ok();
    }

    private <T> Result<T, String> post(String path, Object body, String token, TypeReference<T> type) {
        String uri = this.baseUrl + path;
        HttpPost httpPost = new HttpPost(uri);
        try {
            logger.debug("Executing POST request to {}", uri);
            StringEntity entity = new StringEntity(objectMapper.writeValueAsString(body), StandardCharsets.UTF_8);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json;charset=utf-8");
            httpPost.setHeader(AUTHORIZATION_HEADER, token);
            httpPost.setHeader(LANGUAGE_HEADER, DEFAULT_LANGUAGE);
            try (CloseableHttpResponse httpResponse = this.client.execute(httpPost)) {
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                if (Objects.equals(statusCode, HttpStatus.SC_OK)) {
                    return Result.ok(objectMapper.readValue(httpResponse.getEntity().getContent(), type));
                }
                return Result.error("Ошибка при вызове АПИ: Status code " + statusCode + "; " + httpResponse.getStatusLine().getReasonPhrase());
            }
        } catch (Exception e) {
            logger.error("Cannot execute POST request to {}:", uri, e);
            return Result.error("Ошибка при вызове АПИ: " + e.getMessage());
        }
    }

    private <T> Result<T, String> get(String path, String token, TypeReference<T> type) {
        String uri = this.baseUrl + path;
        HttpGet httpGet = new HttpGet(uri);
        try {
            logger.debug("Executing GET request to {}", uri);
            httpGet.setHeader("Accept", "application/json");
            httpGet.setHeader("Content-type", "application/json;charset=utf-8");
            httpGet.setHeader(AUTHORIZATION_HEADER, token);
            httpGet.setHeader(LANGUAGE_HEADER, DEFAULT_LANGUAGE);
            try (CloseableHttpResponse httpResponse = this.client.execute(httpGet)) {
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                if (Objects.equals(statusCode, HttpStatus.SC_OK)) {
                    return Result.ok(objectMapper.readValue(httpResponse.getEntity().getContent(), type));
                }
                return Result.error("Ошибка при вызове АПИ: Status code " + statusCode + "; " + httpResponse.getStatusLine().getReasonPhrase());
            }
        } catch (Exception e) {
            logger.error("Cannot execute GET request to {}:", uri, e);
            return Result.error("Ошибка при вызове АПИ: " + e.getMessage());
        }
    }
}
