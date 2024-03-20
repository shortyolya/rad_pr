package com.baltinfo.radius.bankruptcy.export;

import com.baltinfo.radius.application.configuration.OuterLinkProperties;
import com.baltinfo.radius.exchange.OuterLinkService;

/**
 * @author Suvorina Aleksandra
 * @since 17.10.2018
 */
public class BkrOuterLinkService implements OuterLinkService {

    private final OuterLinkProperties outerLinkProperties;

    public BkrOuterLinkService(OuterLinkProperties outerLinkProperties) {
        this.outerLinkProperties = outerLinkProperties;
    }

    @Override
    public String formOuterLink(Long lotUnid) {
        String fallbackUrl = outerLinkProperties.getBankruptcyBaseUrlOld()
                + outerLinkProperties.getBankruptcyLotCardPatternOld().replaceAll("###LOT_UNID###", lotUnid + "");
        if (!outerLinkProperties.getUrlFormTypeNew()){
            return fallbackUrl;
        }
        return outerLinkProperties.getBankruptcyBaseUrl() +
                outerLinkProperties.getBankruptcyLotCardPattern().replaceAll("###LOT_UNID###", lotUnid + "").replaceAll("###FALLBACK###", fallbackUrl);
    }

}
