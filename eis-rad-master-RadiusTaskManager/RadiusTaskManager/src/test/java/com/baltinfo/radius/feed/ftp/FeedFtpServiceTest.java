package com.baltinfo.radius.feed.ftp;

import com.baltinfo.radius.application.configuration.FeedFtpServerConfiguration;
import com.baltinfo.radius.utils.Result;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  Тесты
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 29.06.2020
 */

@SpringBootTest(classes = FeedFtpServerConfiguration.class)
public class FeedFtpServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(FeedFtpServiceTest.class);

    private FeedFtpService feedFtpService;

    @Before
    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(FeedFtpServerConfiguration.class);
        feedFtpService = (FeedFtpService) context.getBean("feedFtpService");
    }

    @Ignore
    @Test
    public void should_true_then_FileIsExistInTestDirectory() {
        Long directoryName = 1L;
        String fileName = "test (1).jpg";
        Result<Boolean, String> result = feedFtpService.isFileExistsInDirectory(directoryName, fileName);
        if (result.isError()) {
            logger.error(result.getError());
        } else {
            Assert.assertTrue(result.getResult());
        }
    }

    @Ignore
    @Test
    public void should_true_deleteUnnecessaryFiles() {
        Long directoryName = 1L;
        List<String> fileList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            String fileName = "test (" + i + ").jpg";
            fileList.add(fileName);
        }
        Result<Void, String> result = feedFtpService.deleteUnnecessaryFiles(directoryName, fileList);
        Assert.assertFalse(result.isError());
    }

    @Ignore
    @Test
    public void should_true_deleteUnnecessaryDirectories() {
        List<Long> directoryList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            directoryList.add((long) i);
        }
        Result<Void, String> result = feedFtpService.deleteUnnecessaryDirectories(directoryList);
        Assert.assertFalse(result.isError());
    }

    @Ignore
    @Test
    public void should_true_postImageFileOnServer() {
        Long directoryName = 4L;
        String fileName = "test (2).jpg";
        String filePath = "/20200625/tmp-7992547550203572281.jpg";
        Result<Void, String> result = feedFtpService.postImageFileOnServer(directoryName, fileName, filePath, false);
        Assert.assertFalse(result.isError());
    }
}
