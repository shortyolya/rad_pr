package com.baltinfo.radius.feed.services;

import com.baltinfo.radius.application.configuration.JobConfiguration;
import com.baltinfo.radius.db.controller.VCianRealEstateController;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Kulikov Semyon
 * @since 23.06.2020
 */

@SpringBootTest(classes = JobConfiguration.class)
public class ExportFeedPhotoToFtpTest {

    private ExportFeedPhotoToFtp exportFeedPhotoToFtp;
    private VCianRealEstateController vCianRealEstateController;

    @Before
    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JobConfiguration.class);
        exportFeedPhotoToFtp = (ExportFeedPhotoToFtp) context.getBean("exportFeedPhotoToFtp");
        vCianRealEstateController = (VCianRealEstateController) context.getBean("vCianRealEstateController");
    }

    @Ignore
    @Test
    public void test() {
        exportFeedPhotoToFtp.exportAllPhotos();
    }

    @Ignore
    @Test
    public void deleteTest() {
        exportFeedPhotoToFtp.deleteNonActualFeedFilesFromFtp();
    }
}
