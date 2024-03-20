package com.baltinfo.radius.template;

import com.baltinfo.radius.navigation.NavigationService;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

/**
 * @author Suvorina Aleksandra
 * @since 13.03.2018
 */
public class ThymeleafTemplateHelper implements TemplateHelper {

    private final ITemplateEngine templateEngine;
    private final NavigationService navigationService;

    public ThymeleafTemplateHelper(ITemplateEngine templateEngine, NavigationService navigationService) {
        this.templateEngine = templateEngine;
        this.navigationService = navigationService;
    }

    @Override
    public String formTextFromTemplate(String template, Map<String, Object> data) {
        data.put("navigationService", navigationService);
        Context context = new Context();
        context.setVariables(data);
        return templateEngine.process(template, context);
    }
}

