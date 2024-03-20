package com.baltinfo.radius.bankruptcy.export;

import com.baltinfo.radius.application.configuration.JobConfiguration;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Suvorina Aleksandra
 * @since 04.10.2021
 */
@SpringBootTest(classes = JobConfiguration.class)
public class AdditionalPropertiesServiceTest {

    @Test
    @Ignore
    public void test() {

        ApplicationContext context = new AnnotationConfigApplicationContext(JobConfiguration.class);
        AdditionalPropertiesService additionalPropertiesService = (AdditionalPropertiesService) context.getBean("additionalPropertiesService");

        additionalPropertiesService.getAdditionalPropertiesJsonByObjUnid(40000002867L);
    }

}
