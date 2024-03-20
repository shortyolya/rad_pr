package com.baltinfo.radius.application.configuration;

import com.baltinfo.radius.loadauction.ftp.FtpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * <p>
 *     Класс конфигурации для FTP сервиса
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 04.02.2020
 */

@Configuration
@PropertySource("classpath:ftp-server.properties")
@Import({ControllerConfiguration.class})
public class FtpServerConfiguration {

    @Value("${client.server.port}")
    private Integer port;

    @Value("${client.user.timeout}")
    private Integer timeout;

    @Value("${client.server.name}")
    private String serverName;

    @Value("${client.user.name}")
    private String userName;

    @Value("${client.user.password}")
    private String password;

    @Bean
    FtpClient ftpClient() {
        return new FtpClient(serverName, port, userName, password, timeout);
    }
}
