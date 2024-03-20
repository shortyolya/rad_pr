package com.baltinfo.radius.feed.statistic;

import com.baltinfo.radius.application.configuration.UnirestConfigurationTest;
import com.baltinfo.radius.feed.jcat.api.client.JCatApiClient;
import com.baltinfo.radius.feed.utils.StatisticResult;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author Suvorina Aleksandra
 * @since 30.03.2021
 */
public class JCatAdStatisticsServiceTest {

    @Before
    public void beforeAll() {
        new UnirestConfigurationTest();
    }

    @Test
    @Ignore
    public void getFeedStatistics() throws Exception {
        JCatApiClient jCatApiClient = new JCatApiClient(" https://api.jcat.ru/v1/", "ae77200f-37a4-11ea-bf6e-ac1f6bf61c28");
        JCatAdStatisticsService jCatAdStatisticsService = new JCatAdStatisticsService(jCatApiClient);
        StatisticResult<List<FeedStatistic>, String> result = jCatAdStatisticsService.getFeedStatistics(false);
        assertTrue(result.isSuccess());
    }

}
