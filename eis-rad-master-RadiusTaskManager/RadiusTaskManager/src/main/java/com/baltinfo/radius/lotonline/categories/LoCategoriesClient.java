package com.baltinfo.radius.lotonline.categories;

import com.baltinfo.radius.utils.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * @author Suvorina Aleksandra
 * @since 17.02.2021
 */
public class LoCategoriesClient {

    private final Logger logger = LoggerFactory.getLogger(LoCategoriesClient.class);

    private final String baseUrl;
    private final String refreshCategoriesUrl;

    public LoCategoriesClient(String baseUrl, String refreshCategoriesUrl, int connectTimeout,
                              int connectionRequestTimeout, int socketTimeout) {
        this.baseUrl = baseUrl;
        this.refreshCategoriesUrl = refreshCategoriesUrl;

        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
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
        });
        try {
            CloseableHttpClient customHttpClient = getClient(connectTimeout, connectionRequestTimeout, socketTimeout);
            Unirest.setHttpClient(customHttpClient);
        } catch (Exception e) {
            logger.error("Can't create LoCategoriesClient", e);
        }
    }

    private CloseableHttpClient getClient(int connectTimeout, int connectionRequestTimeout, int socketTimeout)
            throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy() {
            public boolean isTrusted(X509Certificate[] chain, String authType) {
                return true;
            }
        }).build();

        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout)
                .build();

        return HttpClients.custom()
                .setSSLContext(sslContext)
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .setDefaultRequestConfig(config)
                .build();
    }

    public Result<Void, String> refreshCategories() {
        String url = baseUrl + refreshCategoriesUrl;
        try {
            CategoriesRefreshResponse response = Unirest.get(url)
                    .header("Content-Type", "application/json")
                    .asObject(CategoriesRefreshResponse.class)
                    .getBody();
            if (response.getErrorCode() != 0) {
                return Result.error(response.getErrorMessage());
            } else {
                return Result.ok();
            }
        } catch (UnirestException e) {
            logger.error("Error in call refreshCategories url = {}", url, e);
            return Result.error("Error in call refreshCategories " + url);
        }
    }

}
