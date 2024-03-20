package com.baltinfo.radius.application.configuration;

import com.baltinfo.radius.calls.client.CallsApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Igor Lapenok
 * @since 06.09.2023
 */
@Configuration
@PropertySource("classpath:calls-api-client.properties")
public class CallsApiClientConfiguration {
    @Value("${calls.api.client.base.url}")
    private String callsApiBaseUrl;

    @Value("${calls.api.client.api.key}")
    private String callsApiKey;

    @Bean
    CallsApiClient callsApiClient() {
        return new CallsApiClient(callsApiBaseUrl, callsApiKey);
    }

}
