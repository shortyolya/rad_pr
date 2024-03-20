package com.baltinfo.radius.lotonline.check;

import com.baltinfo.radius.application.configuration.JobConfiguration;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Suvorina Aleksandra
 * @since 10.11.2020
 */
public class LotOnlineCheckResultsServiceTest {

    @Test
    @Ignore
    public void checkAndRunProcedure() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(JobConfiguration.class);
        LotOnlineCheckResultsService lotOnlineCheckResultsService = (LotOnlineCheckResultsService) context.getBean("lotOnlineCheckResultsService");
        lotOnlineCheckResultsService.checkAndRunProcedure();
    }

}
