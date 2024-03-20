package com.baltinfo.radius.rest.client;

import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.Serializable;
import java.util.Arrays;

/**
 * <p>
 * </p>
 *
 * @author Lapenok Igor
 * @since 10.04.2019
 */
public class HttpClientFactory implements Serializable {

    private static final int DEFAULT_CONN_TIMEOUT = 30000;
    private static final int DEFAULT_SOCKET_TIMEOUT = 30000;
    private static final int DEFAULT_REQUEST_TIMEOUT = 30000;

    public static CloseableHttpClient createHttpClient(int connectTimeout, int socketTimeout, int requestTimeout) {
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.DEFAULT)
                .setExpectContinueEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(requestTimeout)
                .build();

        return HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();
    }

    public static CloseableHttpClient createHttpClient() {
        return createHttpClient(DEFAULT_CONN_TIMEOUT, DEFAULT_SOCKET_TIMEOUT, DEFAULT_REQUEST_TIMEOUT);
    }
}
