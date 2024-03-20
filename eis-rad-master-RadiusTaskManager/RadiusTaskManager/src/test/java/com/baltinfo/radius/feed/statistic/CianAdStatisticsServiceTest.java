package com.baltinfo.radius.feed.statistic;

import com.baltinfo.radius.application.configuration.UnirestConfigurationTest;
import com.baltinfo.radius.feed.cian.api.client.CianApiClient;
import com.baltinfo.radius.feed.utils.StatisticResult;
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
public class CianAdStatisticsServiceTest {

    @Before
    public void beforeAll() {
        new UnirestConfigurationTest();
    }

    @Test
    @Ignore
    public void getFeedStatistics() throws Exception {
        CianApiClient client = new CianApiClient("https://public-api.cian.ru/v1",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjgwMzQ4ODgxfQ._b4MMHIZ-BT7i1YIdr_WG9UFQ4oMPalo0FHhZa2ZcN8");
        CianAdStatisticsService cianAdStatisticsService = new CianAdStatisticsService(Collections.singletonList(client));
        StatisticResult<List<FeedStatistic>, String> result = cianAdStatisticsService.getFeedStatistics(false);
        assertTrue(result.isSuccess());
    }

}
