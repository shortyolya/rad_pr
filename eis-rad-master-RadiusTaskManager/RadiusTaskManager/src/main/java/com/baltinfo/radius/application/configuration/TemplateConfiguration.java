package com.baltinfo.radius.application.configuration;

import com.baltinfo.radius.navigation.NavigationService;
import com.baltinfo.radius.navigation.NavigationUtils;
import com.baltinfo.radius.template.ThymeleafTemplateHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templateresolver.StringTemplateResolver;

/**
 * @author Suvorina Aleksandra
 * @since 13.03.2018
 */
@Configuration
@Import({NavigationProperties.class})
public class TemplateConfiguration {

    @Bean
    NavigationUtils navigationUtils() {
        return new NavigationUtils();
    }

    @Bean
    NavigationService navigationService(NavigationUtils navigationUtils,
                                        NavigationProperties navigationProperties) {
        return new NavigationService(navigationUtils, navigationProperties);
    }

    @Bean
    TemplateEngine templateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.addDialect(new Java8TimeDialect());
        templateEngine.setTemplateResolver(new StringTemplateResolver());
        return templateEngine;
    }

    @Bean
    ThymeleafTemplateHelper thymeleafTemplateHelper(TemplateEngine templateEngine,
                                                    NavigationService navigationService) {
        return new ThymeleafTemplateHelper(templateEngine, navigationService);
    }

}
