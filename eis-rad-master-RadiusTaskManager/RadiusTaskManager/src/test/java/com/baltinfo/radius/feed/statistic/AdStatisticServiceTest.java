package com.baltinfo.radius.feed.statistic;

import com.baltinfo.radius.application.configuration.UnirestConfigurationTest;
import com.baltinfo.radius.db.constants.MarketingEvent;
import com.baltinfo.radius.db.controller.AdStatisticsController;
import com.baltinfo.radius.db.controller.ObjectController;
import com.baltinfo.radius.db.dao.AdStatisticsDao;
import com.baltinfo.radius.db.dao.ObjectJpaDao;
import com.baltinfo.radius.feed.avito.api.client.AvitoApiClient;
import com.baltinfo.radius.feed.cian.api.client.CianApiClient;
import com.baltinfo.radius.utils.Result;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author Suvorina Aleksandra
 * @since 14.03.2021
 */
public class AdStatisticServiceTest {


    @Before
    public void beforeAll() {
        new UnirestConfigurationTest();
    }

    @Test
    @Ignore
    public void updateAdStatisticAvito() throws Exception {
        AvitoApiClient client = new AvitoApiClient("https://api.avito.ru",
                "zBygctFVnWPL3XiGtKiR",
                "P7cy5LHUKfLwCcG0EeC2PVdBFEeafbaKSZDDVgoA",
                "2021-03-18");

        AvitoAdStatisticsService avitoStatisticsService = new AvitoAdStatisticsService(Collections.singletonList(client));

        ObjectJpaDao objectJpaDao = new ObjectJpaDao();
        ObjectController objectController = new ObjectController(objectJpaDao);
        AdStatisticsDao adStatisticsDao = new AdStatisticsDao();
        AdStatisticsController adStatisticsController = new AdStatisticsController(adStatisticsDao);
        AdStatisticService adStatisticService = new AdStatisticService(avitoStatisticsService, objectController,
                adStatisticsController, MarketingEvent.AVITO.getUnid());
        Result<String, String> result = adStatisticService.updateAdStatistic(false);

        assertTrue(result.isSuccess());
    }

    @Test
    @Ignore
    public void updateAdStatisticCian() throws Exception {
        CianApiClient client = new CianApiClient("https://public-api.cian.ru/v1",
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjQ1MzA1ODQ3fQ.p5NcwO-oORlnR5_qOYGi-p8Gxr0jOGfY9uMdLDZhNF0");

        CianAdStatisticsService cianAdStatisticsService = new CianAdStatisticsService(Collections.singletonList(client));

        ObjectJpaDao objectJpaDao = new ObjectJpaDao();
        ObjectController objectController = new ObjectController(objectJpaDao);
        AdStatisticsDao adStatisticsDao = new AdStatisticsDao();
        AdStatisticsController adStatisticsController = new AdStatisticsController(adStatisticsDao);
        AdStatisticService adStatisticService = new AdStatisticService(cianAdStatisticsService, objectController,
                adStatisticsController, MarketingEvent.CIAN.getUnid());
        Result<String, String> result = adStatisticService.updateAdStatistic(false);

        assertTrue(result.isSuccess());
    }

}
