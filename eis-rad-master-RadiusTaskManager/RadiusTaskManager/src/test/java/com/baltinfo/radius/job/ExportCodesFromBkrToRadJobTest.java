package com.baltinfo.radius.job;

import com.baltinfo.radius.application.configuration.JobConfiguration;
import com.baltinfo.radius.bankruptcy.codes.ExportCodesFromBkrToRadJob;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Kulikov Semyon
 * @since 18.03.2020
 */

public class ExportCodesFromBkrToRadJobTest {

    private ExportCodesFromBkrToRadJob exportCodesFromBkrToRadJob;

    @Before
    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JobConfiguration.class);
        exportCodesFromBkrToRadJob = (ExportCodesFromBkrToRadJob) context.getBean("exportCodesFromBkrToRadJob");
    }

    @Ignore
    @Test
    public void test() {
        exportCodesFromBkrToRadJob.runJob();
    }
}
