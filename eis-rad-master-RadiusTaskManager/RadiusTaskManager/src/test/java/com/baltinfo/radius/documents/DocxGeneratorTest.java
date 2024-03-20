package com.baltinfo.radius.documents;

import com.baltinfo.radius.application.configuration.JobConfiguration;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class DocxGeneratorTest {

    @Test
    @Ignore
    public void runTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JobConfiguration.class);
        CreatingDocumentJob creatingDocumentJob = context.getBean(CreatingDocumentJob.class);
        creatingDocumentJob.run();
    }
}
