package com.baltinfo.radius.bankruptcy.export;

import com.baltinfo.radius.application.configuration.OuterLinkProperties;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Suvorina Aleksandra
 * @since 17.10.2018
 */
public class BkrOuterLinkServiceTest {

    @Test
    @Ignore
    public void testFormLotCardUrl() {
        OuterLinkProperties outerLinkProperties = mock(OuterLinkProperties.class);
        when(outerLinkProperties.getBankruptcyBaseUrl()).thenReturn("https://catalog.lot-online.ru/");
        when(outerLinkProperties.getBankruptcyBaseUrlOld()).thenReturn("https://sales.lot-online.ru/e-auction/");
        when(outerLinkProperties.getBankruptcyLotCardPattern()).thenReturn("index.php?dispatch=lot_product&lot_id=###LOT_UNID###&lot_type=1&fallback=###FALLBACK###");
        when(outerLinkProperties.getBankruptcyLotCardPatternOld()).thenReturn("auctionLotProperty.xhtml?parm=lotUnid%3D###LOT_UNID###%3Bmode%3Djust");
        when(outerLinkProperties.getUrlFormTypeNew()).thenReturn(true);

        BkrOuterLinkService outerLinkService = new BkrOuterLinkService(outerLinkProperties);
        assertEquals(outerLinkService.formOuterLink(960000153260L), "https://catalog.lot-online.ru/index.php?dispatch=lot_product&lot_id=960000153260&lot_type=1&fallback=https://sales.lot-online.ru/e-auction/auctionLotProperty.xhtml?parm=lotUnid%3D960000153260%3Bmode%3Djust");
    }

    @Test
    public void testFormLotCardUrlOld() {
        OuterLinkProperties outerLinkProperties = mock(OuterLinkProperties.class);
        when(outerLinkProperties.getBankruptcyBaseUrlOld()).thenReturn("https://sales.lot-online.ru/e-auction/");
        when(outerLinkProperties.getBankruptcyLotCardPatternOld()).thenReturn("auctionLotProperty.xhtml?parm=lotUnid%3D###LOT_UNID###%3Bmode%3Djust");
        when(outerLinkProperties.getUrlFormTypeNew()).thenReturn(false);

        BkrOuterLinkService outerLinkService = new BkrOuterLinkService(outerLinkProperties);
        assertEquals(outerLinkService.formOuterLink(960000153260L), "https://sales.lot-online.ru/e-auction/auctionLotProperty.xhtml?parm=lotUnid%3D960000153260%3Bmode%3Djust");
    }
}
