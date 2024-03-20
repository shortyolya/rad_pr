package com.baltinfo.radius.application.configuration;


import com.baltinfo.radius.yandex.client.YandexMapApiClient;
import com.baltinfo.radius.yandex.client.YandexMetrikaApiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:yandex-api-client.properties")
public class YandexApiConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(YandexApiConfiguration.class);

    @Value("${yandex.map.api.url}")
    private String baseMapApiUrl;
    @Value("${yandex.map.api.key}")
    private String apiMapApiKey;
    @Value("${yandex.map.api.demo.mode}")
    private boolean demoMode;
    @Value("${yandex.api.client.base.url}")
    private String baseMetrikaApiUrl;
    @Value("${yandex.api.client.connect.timeout}")
    private Integer connectTimeout;
    @Value("${yandex.api.client.connection.request.timeout}")
    private Integer connectionRequestTimeout;
    @Value("${yandex.api.client.socket.timeout}")
    private Integer socketTimeout;
    @Value("${yandex.api.client.access.key}")
    private String apiMetrikaApiKey;


    @Bean
    YandexMapApiClient yandexMapApiClient() {
        return new YandexMapApiClient(baseMapApiUrl, apiMapApiKey, demoMode);
    }

    @Bean
    YandexMetrikaApiClient yandexMetrikaApiClient() {
        return new YandexMetrikaApiClient(baseMetrikaApiUrl, apiMetrikaApiKey, connectTimeout, connectionRequestTimeout, socketTimeout);
    }

}
