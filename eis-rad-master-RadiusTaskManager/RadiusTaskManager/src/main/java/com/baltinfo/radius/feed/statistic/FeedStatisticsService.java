package com.baltinfo.radius.feed.statistic;

import com.baltinfo.radius.feed.utils.StatisticResult;

import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 14.03.2021
 */
public interface FeedStatisticsService {

    StatisticResult<List<FeedStatistic>, String> getFeedStatistics(boolean withAdditionalProps);

}
