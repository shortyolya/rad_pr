package com.baltinfo.radius.bankruptcy.results;

import com.baltinfo.radius.application.configuration.JobConfiguration;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Suvorina Aleksandra
 * @since 19.11.2020
 */
public class BkrReceiveResultsJobTest {

    @Ignore
    @Test
    public void runProcedure() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(JobConfiguration.class);
        BkrReceiveResultsJob bkrReceiveResultsJob = (BkrReceiveResultsJob) context.getBean("bkrReceiveResultsJob");
        bkrReceiveResultsJob.run();
    }
}
