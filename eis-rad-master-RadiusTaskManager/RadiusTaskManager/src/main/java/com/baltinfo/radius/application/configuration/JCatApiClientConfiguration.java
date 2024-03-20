package com.baltinfo.radius.application.configuration;

import com.baltinfo.radius.feed.jcat.api.client.JCatApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Suvorina Aleksandra
 * @since 30.03.2021
 */
@PropertySource("classpath:jcat-api-client.properties")
public class JCatApiClientConfiguration {

    @Value("${jcat.api.client.base.url}")
    private String baseUrl;
    @Value("${jcat.api.client.access.key}")
    private String accessKey;

    @Bean
    JCatApiClient jCatApiClient() {
        return new JCatApiClient(baseUrl, accessKey);
    }
}
