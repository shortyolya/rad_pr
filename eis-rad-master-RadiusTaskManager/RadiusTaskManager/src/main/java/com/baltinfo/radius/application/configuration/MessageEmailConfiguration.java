package com.baltinfo.radius.application.configuration;


import com.baltinfo.radius.loadauction.service.MessageEmailList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author Kulikov Semyon
 * @since 02.03.2020
 */

@Configuration
@PropertySource("classpath:message-email.properties")
public class MessageEmailConfiguration {

    @Bean
    MessageEmailList messageEmailList(Environment env) {
        return new MessageEmailList(env.getProperty("email.list"));
    }

}
