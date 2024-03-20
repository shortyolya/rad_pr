package com.baltinfo.radius.feed.xml.controller;

import com.baltinfo.radius.db.constants.MarketingEvent;
import com.baltinfo.radius.db.controller.CategoryMatcherController;
import com.baltinfo.radius.db.model.CategoryMatcher;
import com.baltinfo.radius.db.model.FeedCategory;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class CategoryMatcherControllerTest {
    @Ignore
    @Test
    public void test() {
        List<FeedCategory> categoryMatchers = new CategoryMatcherController().findByMevUnid(MarketingEvent.CIAN.getUnid());
        CategoryMatcher categoryMatcher = new CategoryMatcherController().findByCategoryUnidAndMevUnid(23L, MarketingEvent.CIAN.getUnid());
    }
}
