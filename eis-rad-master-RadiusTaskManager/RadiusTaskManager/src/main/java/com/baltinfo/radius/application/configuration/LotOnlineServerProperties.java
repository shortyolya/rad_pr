package com.baltinfo.radius.application.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Suvorina Aleksandra
 * @since 23.08.2019
 */
@Configuration
@PropertySource("classpath:lotonline.properties")
public class LotOnlineServerProperties {

    @Value("${lotonline.user}")
    private String user;
    @Value("${lotonline.host}")
    private String host;
    @Value("${lotonline.port}")
    private Integer port;
    @Value("${lotonline.photo.path}")
    private String photoPath;
    @Value("${lotonline.doc.path}")
    private String docPath;
    @Value("${lotonline.protocol.path}")
    private String protocolPath;

    public String getUser() {
        return user;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public String getDocPath() {
        return docPath;
    }

    public String getProtocolPath() {
        return protocolPath;
    }

}
