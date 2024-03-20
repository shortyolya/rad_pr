package com.baltinfo.radius.vitrina;

import com.baltinfo.radius.application.configuration.UnirestConfigurationTest;
import com.baltinfo.radius.notification.paydoc.DocumentDto;
import com.baltinfo.radius.utils.Result;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

public class VitrinaClientTest {

    @Before
    public void beforeAll() {
        new UnirestConfigurationTest();
    }

    @Test
    @Ignore
    public void testUploadFile() {
//        VitrinaClient vitrinaClient = new VitrinaClient("https://test-catalog.lot-online.ru");
//
//        Result<DocumentDto, String> document = vitrinaClient.uploadFile(new File("D:\\temp\\test.txt"));
//        System.out.println(document.getError());
    }
}