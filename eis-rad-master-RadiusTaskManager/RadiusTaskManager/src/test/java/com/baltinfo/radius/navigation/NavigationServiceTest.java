package com.baltinfo.radius.navigation;

import com.baltinfo.radius.application.configuration.NavigationProperties;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Suvorina Aleksandra
 * @since 16.09.2021
 */
public class NavigationServiceTest {

    @Test
    public void formBkrAuctionCardUrl() throws Exception {
        NavigationUtils navigationUtils = new NavigationUtils();
        NavigationProperties navigationProperties = mock(NavigationProperties.class);
        when(navigationProperties.getVitrinaBkrBaseUrl()).thenReturn("https://catalog.lot-online.ru/index.php?dispatch=bkrEtp.p");
        NavigationService navigationService = new NavigationService(navigationUtils, navigationProperties);
        String auctionUrl = navigationService.formVitrinaBkrAuctionCardUrl(960000216727L);
        assertEquals(auctionUrl, "https://catalog.lot-online.ru/index.php?dispatch=bkrEtp.p&p=account-sale-create.xhtml&parm=7072676820594C485A3E647866776C727158716C67203B39333333333534395F355F3B");
    }

}
