package com.baltinfo.radius.feed.services;

import com.baltinfo.radius.application.configuration.JobConfiguration;
import com.baltinfo.radius.db.constants.MarketingEvent;
import com.baltinfo.radius.db.constants.SubjectConstant;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JCatFeedXmlServiceTest {

    private FeedService jCatFeedService;

    @Before
    public void pre() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JobConfiguration.class);
        jCatFeedService = (FeedService) context.getBean("avitoFeedService");
    }

    @Ignore
    @Test
    public void test1_RAD() {
        jCatFeedService.formFeed(MarketingEvent.JCAT.getUnid(), SubjectConstant.RAD.getId());
    }

    @Ignore
    @Test
    public void test1_RADHOLDING() {
        jCatFeedService.formFeed(MarketingEvent.JCAT.getUnid(), SubjectConstant.RAD_HOLDING.getId());
    }
}
