package com.baltinfo.radius.lotonline.export;

import com.baltinfo.radius.application.configuration.OuterLinkProperties;
import com.baltinfo.radius.db.constants.LotOnlineSaleType;
import com.baltinfo.radius.db.controller.LotInfoController;
import com.baltinfo.radius.db.model.lotonline.LotInfo;
import com.baltinfo.radius.exchange.OuterLinkService;

/**
 * @author Suvorina Aleksandra
 * @since 23.03.2021
 */
public class LotOnlineOuterLinkService implements OuterLinkService {

    private final OuterLinkProperties outerLinkProperties;
    private final LotInfoController lotInfoController;

    public LotOnlineOuterLinkService(OuterLinkProperties outerLinkProperties, LotInfoController lotInfoController) {
        this.outerLinkProperties = outerLinkProperties;
        this.lotInfoController = lotInfoController;
    }

    @Override
    public String formOuterLink(Long lotUnid) {
        String result = "";
        String baseUrl = "";
        String lotCard = "";
        String fallbackUrl = "";
        LotInfo lotInfo = lotInfoController.getLotInfo(lotUnid);

        if (lotInfo.getTenderFk().getSaleTypeFk() == LotOnlineSaleType.PRIVATIZATION.getId()) {
            fallbackUrl = outerLinkProperties.getLotonlineBaseUrlPrivatization();
        } else if (lotInfo.getTenderFk().getSaleTypeFk() == LotOnlineSaleType.RAD_AUCTION.getId()) {
            fallbackUrl = outerLinkProperties.getLotonlineBaseUrlAuction();
        } else if (lotInfo.getTenderFk().getSaleTypeFk() == LotOnlineSaleType.RENT.getId()) {
            fallbackUrl = outerLinkProperties.getLotOnlineBaseUrlRent();
        }
        fallbackUrl += outerLinkProperties.getLotonlineLotCardPatternOld().replace("###LOT_INFO_ID###", lotInfo.getId() + "");
        if (outerLinkProperties.getUrlFormTypeNew()) {
            baseUrl = outerLinkProperties.getLotonlineBaseUrl();
            lotCard = outerLinkProperties.getLotonlineLotCardPattern().replaceAll("###LOT_INFO_ID###", lotInfo.getId() + "").replaceAll("###FALLBACK###", fallbackUrl);
            result = baseUrl + lotCard;
        } else {
            result = fallbackUrl;
        }
        return result;
    }
}
