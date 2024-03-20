package com.baltinfo.radius.yandex;

import com.baltinfo.radius.application.configuration.JobConfiguration;
import com.baltinfo.radius.yandex.job.UpdateStatisticJob;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class YandexMetricsTest {

    @Test
    @Ignore
    public void getYandexStatistics() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JobConfiguration.class);
        UpdateStatisticJob updateStatisticJob = context.getBean(UpdateStatisticJob.class);
        updateStatisticJob.runJob();
    }

}
