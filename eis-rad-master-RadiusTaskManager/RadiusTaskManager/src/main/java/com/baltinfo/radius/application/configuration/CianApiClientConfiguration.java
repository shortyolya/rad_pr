package com.baltinfo.radius.application.configuration;

import com.baltinfo.radius.feed.avito.api.client.AvitoApiClient;
import com.baltinfo.radius.feed.cian.api.client.CianApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Igor Lapenok
 * @since 10.02.2021
 */
@Configuration
@PropertySource("classpath:cian-api-client.properties")
public class CianApiClientConfiguration {
    @Value("${cian.api.client.base.url}")
    private String baseUrl;

    @Value("#{'${cian.api.client.access.key}'.split(', ')}")
    private List<String> accessKeys;

    @Bean
    List<CianApiClient> cianApiClients() {
        return accessKeys.stream()
                .map(key -> new CianApiClient(baseUrl, key))
                .collect(Collectors.toList());
    }
}
