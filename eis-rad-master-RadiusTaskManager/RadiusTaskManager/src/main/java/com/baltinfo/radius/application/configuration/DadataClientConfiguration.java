package com.baltinfo.radius.application.configuration;

import com.baltinfo.radius.dadata.client.DadataClient;
import com.baltinfo.radius.dadata.services.DadataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Конфигурация клиента dadata.ru
 *
 * @author Igor Lapenok
 * @since 09.12.2019
 */
@Configuration
@PropertySource("classpath:dadata-rest-client.properties")
public class DadataClientConfiguration {

    @Value("${dadata.client.demo.mode}")
    private Boolean demoMode;

    @Value("${dadata.client.base.url}")
    private String baseUrl;

    @Value("${dadata.client.standard.base.url}")
    private String standardBaseUrl;

    @Value("${dadata.client.api.key}")
    private String apiKey;

    @Value("${dadata.client.secret.key}")
    private String secretKey;

    @Bean
    DadataClient dadataClient() {
        return new DadataClient(baseUrl, standardBaseUrl, apiKey,
                secretKey, demoMode);
    }

    @Bean
    DadataService dadataService(DadataClient dadataClient) {
        return new DadataService(dadataClient);
    }
}
