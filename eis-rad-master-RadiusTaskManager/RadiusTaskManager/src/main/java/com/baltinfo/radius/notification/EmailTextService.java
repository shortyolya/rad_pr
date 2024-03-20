package com.baltinfo.radius.notification;


import com.baltinfo.radius.db.model.ParticipantAgent;
import com.baltinfo.radius.template.TemplateHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Suvorina Aleksandra
 * @since 07.05.2018
 */
public class EmailTextService {

    private final TemplateHelper templateHelper;
    private final String greetingTemplate;
    private final String footerTemplate;

    public EmailTextService(TemplateHelper templateHelper, String greetingTemplate, String footerTemplate) {
        this.templateHelper = templateHelper;
        this.greetingTemplate = greetingTemplate;
        this.footerTemplate = footerTemplate;
    }

    public String formEmailText(ParticipantAgent participantAgent, String messageText) throws Exception {
        String greeting = formGreeting(participantAgent);
        return greeting + messageText + "<br/>" + footerTemplate;
    }

    public String formEmailText(String messageText) {
        return messageText + "<br/>" + footerTemplate;
    }

    private String formGreeting(ParticipantAgent pa) throws Exception {
        Map<String, Object> paInfo = new HashMap<>();
        paInfo.put("subNameIO", pa.getSubSubUnid().getSubNameI() + " " + pa.getSubSubUnid().getSubNameO());

        return templateHelper.formTextFromTemplate(greetingTemplate, paInfo);
    }

}
