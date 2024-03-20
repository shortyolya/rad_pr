package com.baltinfo.radius.application.configuration;

import com.baltinfo.radius.feed.avito.api.client.AvitoApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Lapenok
 * @since 10.02.2021
 */
@Configuration
@PropertySource("classpath:avito-api-client.properties")
public class AvitoApiClientConfiguration {

    @Value("${avito.api.client.base.url}")
    private String baseUrl;
    @Value("#{'${avito.api.client.id.list}'.split(', ')}")
    private List<String> clientId;
    @Value("#{'${avito.api.client.secret.list}'.split(', ')}")
    private List<String> clientSecret;
    @Value("#{'${avito.api.client.firstDay.list}'.split(', ')}")
    private List<String> firstDay;

    @Bean
    List<AvitoApiClient> avitoApiClients() {
        List<AvitoApiClient> list = new ArrayList<>();
        for(int i = 0; i < clientId.size(); i++) {
            list.add(new AvitoApiClient(baseUrl,
                    clientId.get(i),
                    clientSecret.get(i),
                    firstDay.get(i)));
        }
        return list;
    }
}
