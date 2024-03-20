package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.application.configuration.JobConfiguration;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Suvorina Aleksandra
 * @since 23.09.2021
 */
public class VitrinaControllerTest {

    @Test
    @Ignore
    public void test() {

        ApplicationContext context = new AnnotationConfigApplicationContext(JobConfiguration.class);
        VitrinaController vitrinaController = (VitrinaController) context.getBean("vitrinaController");
        vitrinaController.getAddressByBkrWbUnid(123L);
    }

}
