package com.baltinfo.radius.yandex.client;

import com.baltinfo.radius.utils.Result;
import com.baltinfo.radius.yandex.model.YandexMetricsResponse;
import com.mashape.unirest.http.HttpResponse;
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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * @author Igor Lapenok
 * @since 11.02.2021
 */
public class YandexMetrikaApiClient {
    private final Logger logger = LoggerFactory.getLogger(YandexMetrikaApiClient.class);

    private static final String PREFIX_TOKEN = "OAuth ";
    private final String baseUrl;
    private final String accessKey;

    public YandexMetrikaApiClient(String baseUrl, String accessKey, int connectTimeout, int connectionRequestTimeout, int socketTimeout) {
        this.baseUrl = baseUrl;
        this.accessKey = accessKey;
        try {
            CloseableHttpClient customHttpClient = getClient(connectTimeout, connectionRequestTimeout, socketTimeout);
            Unirest.setHttpClient(customHttpClient);
        } catch (Exception e) {
            logger.error("Can't create yandex api rest client", e);
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

    public HttpResponse<Object> getStatistics(String counterId, String objUrl, String date) {
        try {
            HttpResponse<Object> response = Unirest.get(baseUrl + "/stat/v1/data")
                    .queryString("date1", date)
                    .queryString("metrics", "ym:s:visits")
                    .queryString("filters", "ym:pv:URL=='" + objUrl + "'")
                    .queryString("id", counterId)
                    .header("Content-Type", "application/json")
                    .header("Authorization", PREFIX_TOKEN + accessKey)
                    .asObject(Object.class);
            return response;
        } catch (UnirestException e) {
            logger.error("Can't getStatistics by counterId = {}, objUrl = {}, date = {} ", counterId, objUrl, date, e);
        }
        return null;
    }
}
