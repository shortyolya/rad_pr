package com.baltinfo.radius.feed.services;

import com.baltinfo.radius.db.model.FeedCategory;
import com.baltinfo.radius.feed.utils.ResultForFeed;
import com.baltinfo.radius.utils.Result;

/**
 * @author Suvorina Aleksandra
 * @since 08.09.2020
 */
public interface FeedXmlService {

    Result<ResultForFeed, String> getFeed(FeedCategory feedCategory, Long subUnid);

//    List<Long> getObjUnidListByFcUnid(Long saleCategoryUnid);

}
