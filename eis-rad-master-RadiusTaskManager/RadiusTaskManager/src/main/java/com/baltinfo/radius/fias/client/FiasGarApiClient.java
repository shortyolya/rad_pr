package com.baltinfo.radius.fias.client;

import com.baltinfo.radius.fias.dto.FiasAddrobj;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FiasGarApiClient {

    private static final Logger log = LoggerFactory.getLogger(FiasGarApiClient.class);

    private static final String ADDR_LIST_BY_PARENT_PATH = "/addrobj/list/${guid}";
    private static final String ADDR_LIST_BY_PARENT_AND_LEVEL_PATH = "/addrobj/list/${guid}?level=${level}";
    private static final String ADDR_BY_ID_PATH = "/addrobj/id/${guid}";
    private static final String ADDR_BY_NAME_PATH = "/addrobj/name/${name}";
    private static final String ADDR_ROOT_LIST_PATH = "/addrobj/root/list";
    private static final String REGION_BY_ID_PATH = "/region/id/${guid}";
    private static final String REGION_LIST_PATH = "/region/list";
    private static final String HOUSES_LIST_PATH = "/house/list/${guid}";

    private String baseUrl;
    private CloseableHttpClient client;
    private ObjectMapper objectMapper;

    public FiasGarApiClient(String baseUrl, Integer timeout) {
        this(baseUrl, getClient(timeout));
    }

    FiasGarApiClient(String baseUrl, CloseableHttpClient client) {
        this.baseUrl = baseUrl;
        this.client = client;
        this.objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(new JSR310Module());
    }

    private static CloseableHttpClient getClient(int timeout) {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();

        return HttpClients.custom()
                .setDefaultRequestConfig(config)
                .build();
    }

    public FiasAddrobj getAddrobjByAoguid(String aoguid) {
        Map<String, String> values = new HashMap<String, String>() {
            {
                put("guid", aoguid);
            }
        };
        StrSubstitutor sub = new StrSubstitutor(values);
        String path = sub.replace(ADDR_BY_ID_PATH);

        return this.get(path, new TypeReference<FiasAddrobj>() {
        });
    }

    private <T> T get(String path, TypeReference<T> type) {
        String uri = this.baseUrl + path;
        HttpGet httpget = new HttpGet(uri);

        try {
            log.debug("Executing GET request to {}", uri);
            HttpResponse httpResponse = this.client.execute(httpget);
            return objectMapper.readValue(httpResponse.getEntity().getContent(), type);
        } catch (IOException e) {
            log.error("Cannot execute GET request to {}: {}", uri, e);
            return null;
        }
    }
}
