package com.baltinfo.radius.lotonline.export;

import com.baltinfo.radius.utils.Result;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.cert.X509Certificate;
import java.util.Objects;

public class LotOnlineProtocolService {
    private static final Logger logger = LoggerFactory.getLogger(LotOnlineProtocolService.class);
    private static final String TOKEN_COOKIE_NAME = "token";
    private static final String TOKEN_COOKIE_DOMAIN = ".lot-online.ru";

    private final String baseUrl;
    private final int timeout;

    public LotOnlineProtocolService(String baseUrl, int timeout) {
        this.baseUrl = baseUrl;
        this.timeout = timeout;
    }

    private CloseableHttpClient getHttpsClient(BasicCookieStore cookieStore) {
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
                    .setDefaultCookieStore(cookieStore)
                    .build();
        } catch (Exception e) {
            logger.error("Can't create Static LO rest client", e);
        }
        return null;
    }

    public Result<byte[], String> getProtocol(Long reportId, String fileName, String token) {
        try {
            String type = URLConnection.getFileNameMap().getContentTypeFor(fileName);
            String encFileName = URLEncoder.encode(fileName, "UTF-8");
            Result<byte[], String> response = this.getFile("lot/report/" + reportId + "/" + encFileName,
                    token, type);
            if (response.isError()) {
                return Result.error("Ошибка при вызове сервиса получения протокола: " + response.getError());
            }
            return Result.ok(response.getResult());
        } catch (Exception ex) {
            logger.error("Error in getProtocol: reportId = {} fileName = {}", reportId, fileName, ex);
            return Result.error("Ошибка при вызове сервиса получения протокола: " + ex.getMessage());
        }
    }

    private Result<byte[], String> getFile(String path, String token, String fileType) {
        String uri = this.baseUrl + path;
        try {
            BasicCookieStore cookieStore = new BasicCookieStore();
            BasicClientCookie cookie = new BasicClientCookie(TOKEN_COOKIE_NAME, token);
            cookie.setDomain(TOKEN_COOKIE_DOMAIN);
            cookie.setPath("/");
            cookie.setAttribute("domain", "true");
            cookieStore.addCookie(cookie);

            HttpGet httpGet = new HttpGet(uri);
            logger.debug("Executing GET request to {}", uri);
            String mimeType = fileType == null ? "application/octet-stream" : fileType;
            httpGet.setHeader("Content-Type", mimeType);
            try (CloseableHttpClient client = getHttpsClient(cookieStore);
                 CloseableHttpResponse httpResponse = client.execute(httpGet)) {
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                if (Objects.equals(statusCode, HttpStatus.SC_OK)) {
                    ContentType contentType = ContentType.get(httpResponse.getEntity());
                    String responseMimeType = contentType.getMimeType();
                    if (!responseMimeType.equals(mimeType)) {
                        if (responseMimeType.equals("text/html")) {
                            String res = EntityUtils.toString(httpResponse.getEntity());
                            logger.error("httpResponse: {}", res);
                        }
                        return Result.error("Ошибка при вызове " + uri + ": response contentType " + contentType );
                    }
                    byte[] bytes = EntityUtils.toByteArray(httpResponse.getEntity());
                    return Result.ok(bytes);
                }
                return Result.error("Ошибка при вызове " + uri + ": Status code " + statusCode + "; " + httpResponse.getStatusLine().getReasonPhrase());
            }
        } catch (Exception e) {
            logger.error("Cannot execute GET request to {}:", uri, e);
            return Result.error("Ошибка при вызове " + uri + ": " + e.getMessage());
        }
    }
}
