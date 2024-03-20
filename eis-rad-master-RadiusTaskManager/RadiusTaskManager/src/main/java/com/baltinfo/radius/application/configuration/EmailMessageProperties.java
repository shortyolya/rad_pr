package com.baltinfo.radius.application.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 03.04.2020
 */
@Configuration
@PropertySource("classpath:message-email.properties")
public class EmailMessageProperties {
    @Value("${asv.email}")
    private String asvEmail;

    @Value("${asv.receive.info.message.body}")
    private String infoMessageBody;

    @Value("${asv.receive.info.message.theme}")
    private String infoMessageTheme;

    public String getAsvEmail() {
        return asvEmail;
    }

    public String getInfoMessageBody() {
        return infoMessageBody;
    }

    public String getInfoMessageTheme() {
        return infoMessageTheme;
    }
}
