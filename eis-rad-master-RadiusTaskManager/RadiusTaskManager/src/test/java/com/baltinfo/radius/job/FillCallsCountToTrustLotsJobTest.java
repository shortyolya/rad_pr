package com.baltinfo.radius.job;

import com.baltinfo.radius.application.configuration.JobConfiguration;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Igor Lapenok
 * @since 06.09.2023
 */
public class FillCallsCountToTrustLotsJobTest {

    private FillCallsCountToTrustLotsJob fillCallsCountToTrustLotsJob;

    @Before
    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JobConfiguration.class);
        fillCallsCountToTrustLotsJob = (FillCallsCountToTrustLotsJob) context.getBean("fillCallsCountToTrustLotsJob");
    }

    @Ignore
    @Test
    public void test() {
        fillCallsCountToTrustLotsJob.runJob();
    }
}