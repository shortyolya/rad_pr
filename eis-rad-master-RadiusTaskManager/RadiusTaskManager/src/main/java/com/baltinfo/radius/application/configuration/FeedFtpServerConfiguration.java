package com.baltinfo.radius.application.configuration;

import com.baltinfo.radius.db.controller.DocFileController;
import com.baltinfo.radius.feed.ftp.FeedFtpClient;
import com.baltinfo.radius.feed.ftp.FeedFtpService;
import com.baltinfo.radius.loadauction.service.LoadFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 26.06.2020
 */
@Configuration
@PropertySource("classpath:feed-ftp-server.properties")
@Import({ControllerConfiguration.class})
public class FeedFtpServerConfiguration {

    @Value("${feed.ftp.path.to.files}")
    private String pathToFiles;

    @Value("${feed.ftp.path.prefix}")
    private String pathPrefix;

    @Value("${feed.client.server.port}")
    private Integer port;

    @Value("${feed.client.user.timeout}")
    private Integer timeout;

    @Value("${feed.client.server.name}")
    private String serverName;

    @Value("${feed.client.user.name}")
    private String userName;

    @Value("${feed.client.user.password}")
    private String password;

    @Value("${asv.watermark.file.path}")
    private String asvWatermarkFilePath;

    @Value("${radius.path.to.files}")
    private String radiusPathToFiles;

    @Bean
    FeedFtpClient feedFtpClient() {
        return new FeedFtpClient(serverName, port, userName, password, timeout);
    }


    @Bean
    FeedFtpService feedFtpService(FeedFtpClient feedFtpClient, LoadFileService loadFileService,
                                  DocFileController docFileController) {
        return new FeedFtpService(feedFtpClient, loadFileService, docFileController, pathToFiles, pathPrefix, asvWatermarkFilePath, radiusPathToFiles);
    }
}
