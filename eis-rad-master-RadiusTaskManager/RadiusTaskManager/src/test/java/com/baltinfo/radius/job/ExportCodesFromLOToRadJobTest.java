package com.baltinfo.radius.job;

import com.baltinfo.radius.application.configuration.JobConfiguration;
import com.baltinfo.radius.lotonline.codes.ExportCodesFromLOToRadJob;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Kulikov Semyon
 * @since 10.08.2020
 */

public class ExportCodesFromLOToRadJobTest {

    private ExportCodesFromLOToRadJob exportCodesFromLOToRadJob;

    @Before
    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JobConfiguration.class);
        exportCodesFromLOToRadJob = (ExportCodesFromLOToRadJob) context.getBean("exportCodesFromLOToRadJob");
    }

    //@Test
    public void test() {
        exportCodesFromLOToRadJob.runJob();
    }
}
