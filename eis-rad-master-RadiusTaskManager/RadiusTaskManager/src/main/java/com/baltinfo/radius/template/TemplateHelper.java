package com.baltinfo.radius.template;

import java.util.Map;

/**
 * @author Suvorina Aleksandra
 * @since 12.03.2018
 */
public interface TemplateHelper {

    /**
     * Заполнение шаблона
     *
     * @param template шаблон
     * @param data     данные для заполнения
     * @return заполненный шаблон
     */
    String formTextFromTemplate(String template, Map<String, Object> data) throws Exception;

}
