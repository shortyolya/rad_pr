package com.baltinfo.radius.application.configuration;

import com.baltinfo.radius.fias.client.FiasGarApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Suvorina Aleksandra
 * @since 23.03.2021
 */
@Configuration
@PropertySource("classpath:fias-api-client.properties")
public class FiasClientConfiguration {


    @Value("${fias.api.url}")
    private String fiasApiUrl;
    @Value("${fias.api.timeout}")
    private Integer fiasApiTimeout;

    @Bean
    FiasGarApiClient fiasGarApiClient() {
        return new FiasGarApiClient(fiasApiUrl, fiasApiTimeout);
    }

}
