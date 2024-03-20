package com.baltinfo.radius.feed.services;

import com.baltinfo.radius.application.configuration.JobConfiguration;
import com.baltinfo.radius.db.constants.MarketingEvent;
import com.baltinfo.radius.db.constants.SubjectConstant;
import com.baltinfo.radius.db.model.FeedCategory;
import com.baltinfo.radius.feed.services.impl.CianFeedXmlService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Suvorina Aleksandra
 * @since 08.09.2020
 */
public class CianFeedXmlServiceTest {

    private CianFeedXmlService cianFeedXmlService;
    private FeedByCategoryService cianFeedService;

    @Before
    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JobConfiguration.class);
        cianFeedXmlService = (CianFeedXmlService) context.getBean("cianFeedXmlService");
        cianFeedService = (FeedByCategoryService) context.getBean("cianFeedService");
    }

    @Ignore
    @Test
    public void test1() {
        FeedCategory feedCategory = new FeedCategory();
        feedCategory.setFcUnid(1L);
        feedCategory.setFcCode("officeSale");
        feedCategory.setMevUnid(4L);
        feedCategory.setFcIndRent(0);

        cianFeedXmlService.getFeed(feedCategory, SubjectConstant.RAD.getId());
        cianFeedXmlService.getFeed(feedCategory, SubjectConstant.RAD_HOLDING.getId());
    }

    @Ignore
    @Test
    public void test2_RAD() {
        cianFeedService.formFeed(MarketingEvent.CIAN.getUnid(), SubjectConstant.RAD.getId());
    }

    @Ignore
    @Test
    public void test2_RADHOLDING() {
        cianFeedService.formFeed(MarketingEvent.CIAN.getUnid(), SubjectConstant.RAD_HOLDING.getId());
    }
}
