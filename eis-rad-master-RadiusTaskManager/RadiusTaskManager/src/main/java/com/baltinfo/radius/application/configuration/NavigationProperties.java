package com.baltinfo.radius.application.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Suvorina Aleksandra
 * @since 20.04.2018
 */
@Configuration
@PropertySource("classpath:navigation.properties")
public class NavigationProperties {

    @Value("${navigation.eis.baseUrl}")
    private String eisBaseUrl;

    @Value("${navigation.bkr.baseUrl}")
    private String bkrBaseUrl;

    @Value("${navigation.vitrina.bkr.baseUrl}")
    private String vitrinaBkrBaseUrl;

    @Value("${navigation.vitrina.baseUrl}")
    private String vitrinaBaseUrl;

    @Value("${navigation.eis.mainPage}")
    private String eisMainPage;

    public String getEisBaseUrl() {
        return eisBaseUrl;
    }

    public String getBkrBaseUrl() {
        return bkrBaseUrl;
    }

    public String getVitrinaBkrBaseUrl() {
        return vitrinaBkrBaseUrl;
    }

    public String getVitrinaBaseUrl() {
        return vitrinaBaseUrl;
    }

    public String getEisMainPage() {
        return eisMainPage;
    }
}
