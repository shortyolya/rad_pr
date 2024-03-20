package com.baltinfo.radius.avito.service;

import com.baltinfo.radius.application.configuration.JobConfiguration;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

/**
 * @author Igor Lapenok
 * @since 10.09.2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobConfiguration.class)
public class AvitoClassificatorServiceTest {

    @Autowired
    AvitoClassificatorService avitoClassificatorService;

    @Ignore
    @Test
    public void shouldTrue_whenGetAutoCatalog() {
        assertTrue(avitoClassificatorService.parseAutoCatalog().isSuccess());
    }
}