package com.baltinfo.radius.lotonline.export;


import com.baltinfo.radius.application.configuration.JobConfiguration;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ExportToLotOnlineJobTest {

    @Test
    @Ignore
    public void testLoadFromFile() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JobConfiguration.class);
        ExportToLotOnlineJob exportToLotOnlineJob = context.getBean(ExportToLotOnlineJob.class);
        exportToLotOnlineJob.run();
    }
}
