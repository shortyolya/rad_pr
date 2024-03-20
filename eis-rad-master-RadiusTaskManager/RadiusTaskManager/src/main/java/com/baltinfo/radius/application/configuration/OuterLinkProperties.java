package com.baltinfo.radius.application.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Suvorina Aleksandra
 * @since 17.10.2018
 */
@Configuration
@PropertySource("classpath:outerlink.properties")
public class OuterLinkProperties {

    @Value("${bankruptcy.base.url}")
    private String bankruptcyBaseUrl;

    @Value("${bankruptcy.base.url.old}")
    private String bankruptcyBaseUrlOld;

    @Value("${url.form.type.new}")
    private boolean urlFormTypeNew;

    @Value("${bankruptcy.lot.card.pattern}")
    private String bankruptcyLotCardPattern;

    @Value("${bankruptcy.lot.card.pattern.old}")
    private String bankruptcyLotCardPatternOld;

    @Value("${lotonline.base.url}")
    private String lotonlineBaseUrl;

    @Value("${lotonline.base.url.privatization}")
    private String lotonlineBaseUrlPrivatization;

    @Value("${lotonline.base.url.auction}")
    private String lotonlineBaseUrlAuction;

    @Value("${lotonline.base.url.rent}")
    private String lotonlineBaseUrlRent;

    @Value("${lotonline.lot.card.pattern}")
    private String lotonlineLotCardPattern;

    @Value("${lotonline.lot.card.pattern.old}")
    private String lotonlineLotCardPatternOld;

    @Value("${vitrina.base.url}")
    private String vitrinaBaseUrl;

    @Value("${vitrina.lot.card.pattern}")
    private String vitrinaLotCardPattern;

    public String getBankruptcyBaseUrl() {
        return bankruptcyBaseUrl;
    }

    public String getBankruptcyBaseUrlOld() {
        return bankruptcyBaseUrlOld;
    }

    public boolean getUrlFormTypeNew() {
        return urlFormTypeNew;
    }

    public String getBankruptcyLotCardPattern() {
        return bankruptcyLotCardPattern;
    }

    public String getBankruptcyLotCardPatternOld() {
        return bankruptcyLotCardPatternOld;
    }

    public String getLotonlineBaseUrl() {
        return lotonlineBaseUrl;
    }

    public String getLotonlineBaseUrlPrivatization() {
        return lotonlineBaseUrlPrivatization;
    }

    public String getLotonlineBaseUrlAuction() {
        return lotonlineBaseUrlAuction;
    }

    public String getLotonlineLotCardPattern() {
        return lotonlineLotCardPattern;
    }

    public String getLotonlineLotCardPatternOld() {
        return lotonlineLotCardPatternOld;
    }

    public String getLotOnlineBaseUrlRent() {
        return lotonlineBaseUrlRent;
    }

    public String getVitrinaBaseUrl() {
        return vitrinaBaseUrl;
    }

    public String getVitrinaLotCardPattern() {
        return vitrinaLotCardPattern;
    }
}
