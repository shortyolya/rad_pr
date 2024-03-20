package com.baltinfo.radius.lotonline.export;

import com.baltinfo.radius.application.configuration.OuterLinkProperties;
import com.baltinfo.radius.db.constants.LotOnlineSaleType;
import com.baltinfo.radius.db.controller.LotInfoController;
import com.baltinfo.radius.db.model.lotonline.LotInfo;
import com.baltinfo.radius.db.model.lotonline.Tender;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * @author Suvorina Aleksandra
 * @since 24.03.2021
 */
@RunWith(MockitoJUnitRunner.class)
public class LotOnlineOuterLinkServiceTest {

    @Mock
    private OuterLinkProperties outerLinkProperties;
    @Mock
    private LotInfoController lotInfoController;

    @Test
    public void testOuterLinkPrivatisation() {
        LotInfo lotInfo = new LotInfo();
        Long lotId = 222L;
        lotInfo.setId(lotId);
        Tender tender = new Tender();
        tender.setSaleTypeFk(LotOnlineSaleType.PRIVATIZATION.getId());
        lotInfo.setTenderFk(tender);

        when(lotInfoController.getLotInfo(anyLong())).thenReturn(lotInfo);
        String baseUrl = "https://catalog.lot-online.ru/";
        String baseUrlPrivatization = "https://privatization.lot-online.ru/";
        String lotPattern = "index.php?dispatch=lot_product&lot_id=###LOT_INFO_ID###&fallback=###FALLBACK###";
        String lotPatternOld = "lot/details.html?lotId=###LOT_INFO_ID###";
        when(outerLinkProperties.getLotonlineBaseUrl()).thenReturn(baseUrl);
        when(outerLinkProperties.getUrlFormTypeNew()).thenReturn(true);
        when(outerLinkProperties.getLotonlineBaseUrlPrivatization()).thenReturn(baseUrlPrivatization);
        when(outerLinkProperties.getLotonlineLotCardPattern()).thenReturn(lotPattern);
        when(outerLinkProperties.getLotonlineLotCardPatternOld()).thenReturn(lotPatternOld);

        LotOnlineOuterLinkService lotOnlineOuterLinkService = new LotOnlineOuterLinkService(outerLinkProperties, lotInfoController);
        String linkToLotonline = lotOnlineOuterLinkService.formOuterLink(lotId);
        Assert.assertEquals("https://catalog.lot-online.ru/index.php?dispatch=lot_product&lot_id=222&fallback=https://privatization.lot-online.ru/lot/details.html?lotId=222", linkToLotonline);

    }
    @Test
    public void testOuterLinkAuction() {
        LotInfo lotInfo = new LotInfo();
        Long lotId = 222L;
        lotInfo.setId(lotId);
        Tender tender = new Tender();
        tender.setSaleTypeFk(LotOnlineSaleType.RAD_AUCTION.getId());
        lotInfo.setTenderFk(tender);

        when(lotInfoController.getLotInfo(anyLong())).thenReturn(lotInfo);
        String baseUrl = "https://catalog.lot-online.ru/";
        String baseUrlAuction = "https://rad.lot-online.ru/";
        String lotPattern = "index.php?dispatch=lot_product&lot_id=###LOT_INFO_ID###&fallback=###FALLBACK###";
        String lotPatternOld = "lot/details.html?lotId=###LOT_INFO_ID###";
        when(outerLinkProperties.getLotonlineBaseUrl()).thenReturn(baseUrl);
        when(outerLinkProperties.getLotonlineBaseUrlAuction()).thenReturn(baseUrlAuction);
        when(outerLinkProperties.getLotonlineLotCardPattern()).thenReturn(lotPattern);
        when(outerLinkProperties.getLotonlineLotCardPatternOld()).thenReturn(lotPatternOld);
        when(outerLinkProperties.getUrlFormTypeNew()).thenReturn(true);

        LotOnlineOuterLinkService lotOnlineOuterLinkService = new LotOnlineOuterLinkService(outerLinkProperties, lotInfoController);
        String linkToLotonline = lotOnlineOuterLinkService.formOuterLink(lotId);

        Assert.assertEquals("https://catalog.lot-online.ru/index.php?dispatch=lot_product&lot_id=222&fallback=https://rad.lot-online.ru/lot/details.html?lotId=222", linkToLotonline);

    }

    @Test
    public void testOuterLinkAuctionOld() {
        LotInfo lotInfo = new LotInfo();
        Long lotId = 222L;
        lotInfo.setId(lotId);
        Tender tender = new Tender();
        tender.setSaleTypeFk(LotOnlineSaleType.RAD_AUCTION.getId());
        lotInfo.setTenderFk(tender);

        when(lotInfoController.getLotInfo(anyLong())).thenReturn(lotInfo);
        String baseUrlAuction = "https://rad.lot-online.ru/";
        String lotPatternOld = "lot/details.html?lotId=###LOT_INFO_ID###";
        when(outerLinkProperties.getLotonlineBaseUrl()).thenReturn(baseUrlAuction);
        when(outerLinkProperties.getLotonlineBaseUrlAuction()).thenReturn(baseUrlAuction);
        when(outerLinkProperties.getLotonlineLotCardPattern()).thenReturn(lotPatternOld);
        when(outerLinkProperties.getLotonlineLotCardPatternOld()).thenReturn(lotPatternOld);
        when(outerLinkProperties.getUrlFormTypeNew()).thenReturn(false);

        LotOnlineOuterLinkService lotOnlineOuterLinkService = new LotOnlineOuterLinkService(outerLinkProperties, lotInfoController);
        String linkToLotonline = lotOnlineOuterLinkService.formOuterLink(lotId);

        Assert.assertEquals("https://rad.lot-online.ru/lot/details.html?lotId=222", linkToLotonline);

    }

}
