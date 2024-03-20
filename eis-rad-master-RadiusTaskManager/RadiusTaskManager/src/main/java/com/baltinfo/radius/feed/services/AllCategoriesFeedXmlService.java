package com.baltinfo.radius.feed.services;

import com.baltinfo.radius.db.model.FeedCategory;
import com.baltinfo.radius.feed.utils.ResultForFeed;
import com.baltinfo.radius.utils.Result;

import java.util.List;

public interface AllCategoriesFeedXmlService {

    Result<ResultForFeed, String> getFeed(List<FeedCategory> categories, Long subUnid);
}
