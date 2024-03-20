package com.baltinfo.radius.avito.service;

import com.baltinfo.radius.application.configuration.JobConfiguration;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Suvorina Aleksandra
 * @since 20.09.2021
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobConfiguration.class)
public class AvitoDevelopmentClassificatorServiceTest {


    @Autowired
    AvitoDevelopmentClassificatorService avitoDevelopmentClassificatorService;

    @Ignore
    @Test
    public void parseDevelopmentCatalog() throws Exception {
        avitoDevelopmentClassificatorService.parseDevelopmentCatalog();
    }

}
