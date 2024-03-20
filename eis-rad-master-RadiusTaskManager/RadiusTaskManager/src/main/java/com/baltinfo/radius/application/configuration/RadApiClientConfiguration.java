package com.baltinfo.radius.application.configuration;

import com.baltinfo.radius.db.controller.MapRegionController;
import com.baltinfo.radius.radapi.client.RadApiClient;
import com.baltinfo.radius.radapi.security.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Suvorina Aleksandra
 * @since 23.01.2022
 */
@Configuration
public class RadApiClientConfiguration {

    @Value("${rad.api.url}")
    private String radApiUrl;
    @Value("${rad.api.timeout}")
    private Integer radApiTimeout;

    @Bean
    RadApiClient radApiClient() {
        return new RadApiClient(radApiUrl, radApiTimeout);
    }

}
