package com.baltinfo.radius.feed.statistic;

import com.baltinfo.radius.application.configuration.UnirestConfigurationTest;
import com.baltinfo.radius.feed.avito.api.client.AvitoApiClient;
import com.baltinfo.radius.feed.utils.StatisticResult;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author Suvorina Aleksandra
 * @since 12.03.2021
 */
public class AvitoStatisticsServiceTest {

    @Before
    public void beforeAll() {
        new UnirestConfigurationTest();
    }

    // @Ignore
    @Test
    public void getAvitoStatistic() throws Exception {
        AvitoApiClient client = new AvitoApiClient("https://api.avito.ru",
                "zBygctFVnWPL3XiGtKiR",
                "P7cy5LHUKfLwCcG0EeC2PVdBFEeafbaKSZDDVgoA",
                "2022-11-14");

        AvitoAdStatisticsService avitoStatisticsService = new AvitoAdStatisticsService(Collections.singletonList(client));
        StatisticResult<List<FeedStatistic>, String> result = avitoStatisticsService.getFeedStatistics(true);
//
//        ObjectJpaDao objectJpaDao = new ObjectJpaDao();
//        ObjectController objectController = new ObjectController(objectJpaDao);
//        AdStatisticsDao adStatisticsDao = new AdStatisticsDao();
//        AdStatisticsController adStatisticsController = new AdStatisticsController(adStatisticsDao);
//        AdStatisticService adStatisticService = new AdStatisticService(client, objectController, adStatisticsController, mevUnid);
//        Result<String, String> result = adStatisticService.updateAdStatistic();

        assertTrue(result.isSuccess());
    }

}
