package com.baltinfo.radius.application.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:paydoc.properties")
public class PayDocProperties {

    @Value("${payDoc.payerStatus}")
    private String payerStatus;
    @Value("${payDoc.code}")
    private String code;
    @Value("${payDoc.baseIndicator}")
    private String baseIndicator;
    @Value("${payDoc.taxablePeriod}")
    private String taxablePeriod;
    @Value("${payDoc.docNumIndicator}")
    private String docNumIndicator;
    @Value("${payDoc.docDateIndicator}")
    private String docDateIndicator;

    public String getPayerStatus() {
        return payerStatus;
    }

    public String getCode() {
        return code;
    }

    public String getBaseIndicator() {
        return baseIndicator;
    }

    public String getTaxablePeriod() {
        return taxablePeriod;
    }

    public String getDocNumIndicator() {
        return docNumIndicator;
    }

    public String getDocDateIndicator() {
        return docDateIndicator;
    }
}
