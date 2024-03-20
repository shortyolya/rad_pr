package com.baltinfo.radius.bankruptcy.counter;

import com.baltinfo.radius.application.configuration.JobConfiguration;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Suvorina Aleksandra
 * @since 26.10.2020
 */
public class BkrReceiveLotCounterServiceTest {

    @Ignore
    @Test
    public void runProcedure() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(JobConfiguration.class);
        BkrReceiveLotCounterService bkrReceiveLotCounterService = (BkrReceiveLotCounterService) context.getBean("bkrReceiveLotCounterService");
        bkrReceiveLotCounterService.runProcedure();

    }

}
