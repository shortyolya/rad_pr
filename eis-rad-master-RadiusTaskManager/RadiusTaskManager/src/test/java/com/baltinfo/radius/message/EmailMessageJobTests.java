package com.baltinfo.radius.message;

import com.baltinfo.radius.application.configuration.JobConfiguration;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 03.04.2020
 */
@SpringBootTest(classes = JobConfiguration.class)
public class EmailMessageJobTests {

    private EmailMessageJob emailMessageJob;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(JobConfiguration.class);
        emailMessageJob = (EmailMessageJob) context.getBean("emailMessageJob");
    }

//    @Test
    public void run() {
        emailMessageJob.run();
    }
}
