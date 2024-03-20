package com.baltinfo.radius.application.configuration;

import com.baltinfo.radius.lotonline.categories.LoCategoriesClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Suvorina Aleksandra
 * @since 17.02.2021
 */
@Configuration
@PropertySource("classpath:lotonline-rest-client.properties")
public class LotonlineClientConfiguration {
    @Value("${lotonline.client.timeout}")
    private Integer timeout;

    @Value("${lotonline.client.base.url}")
    private String baseUrl;

    @Value("${lotonline.client.categories.url}")
    private String categoriesUrl;

    @Bean
    LoCategoriesClient loCategoriesClient() {
        return new LoCategoriesClient(baseUrl, categoriesUrl, timeout, timeout, timeout);
    }
}
