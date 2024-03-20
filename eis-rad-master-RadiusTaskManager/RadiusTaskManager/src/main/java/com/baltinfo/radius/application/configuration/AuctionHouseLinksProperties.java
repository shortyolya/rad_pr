package com.baltinfo.radius.application.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:links.properties")
public class AuctionHouseLinksProperties {

    @Value("${ah.base.url}")
    private String ahBaseUrl;
    @Value("${ah.artBase.url}")
    private String ahArtBaseUrl;
    @Value("${ah.lot.card.pattern}")
    private String ahLotCardPattern;
    @Value("${ah.artLot.card.pattern}")
    private String ahArtLotCardPattern;
    @Value("${holding.base.url}")
    private String holdingBaseUrl;
    @Value("${holding.lot.card.pattern}")
    private String holdingLotCardPattern;

    public String getAhBaseUrl() {
        return ahBaseUrl;
    }

    public String getAhArtBaseUrl() {
        return ahArtBaseUrl;
    }

    public String getAhLotCardPattern() {
        return ahLotCardPattern;
    }

    public String getAhArtLotCardPattern() {
        return ahArtLotCardPattern;
    }

    public String getHoldingBaseUrl() {
        return holdingBaseUrl;
    }

    public String getHoldingLotCardPattern() {
        return holdingLotCardPattern;
    }
}
