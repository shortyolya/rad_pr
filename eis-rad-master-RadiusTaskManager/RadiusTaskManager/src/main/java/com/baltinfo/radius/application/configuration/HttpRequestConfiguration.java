package com.baltinfo.radius.application.configuration;

import com.baltinfo.radius.rest.client.HttpRequestService;
import com.baltinfo.radius.vitrina.VitrinaClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author ssv
 * @since 27.10.2021
 */
@Configuration
@PropertySource("classpath:outerlink.properties")
public class HttpRequestConfiguration {

    @Value("${bankruptcy.model.base.url}")
    private String baseUrl;
    @Value("${vitrina.base.url}")
    private String vitrinaBaseUrl;

    @Bean
    HttpRequestService httpRequestService() {
        return new HttpRequestService(baseUrl);
    }

    @Bean
    HttpRequestService vitrinaHttpRequestService() {
        return new HttpRequestService(vitrinaBaseUrl);
    }

    @Bean
    VitrinaClient vitrinaClient() {
        return new VitrinaClient(vitrinaBaseUrl);
    }


}
