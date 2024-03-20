package com.baltinfo.radius.vitrina;

import com.baltinfo.radius.application.configuration.OuterLinkProperties;
import com.baltinfo.radius.exchange.OuterLinkService;

/**
 * @author Suvorina Aleksandra
 * @since 18.10.2021
 */
public class VitrinaOuterLinkService implements OuterLinkService {

    private final OuterLinkProperties outerLinkProperties;

    public VitrinaOuterLinkService(OuterLinkProperties outerLinkProperties) {
        this.outerLinkProperties = outerLinkProperties;
    }

    @Override
    public String formOuterLink(Long lotUnid) {
        return outerLinkProperties.getVitrinaBaseUrl() +
                outerLinkProperties.getVitrinaLotCardPattern().replaceAll("###PRODUCT_ID###", lotUnid + "");
    }
}
