package com.baltinfo.radius.loadauction.service;

import com.baltinfo.radius.application.configuration.NotificationProperties;
import com.baltinfo.radius.db.controller.MessageEmailController;
import com.baltinfo.radius.db.model.MessageEmail;
import com.baltinfo.radius.db.model.VLoadJournal;
import com.baltinfo.radius.template.TemplateHelper;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Kulikov Semyon
 * @since 02.03.2020
 */

public class MessageAboutNewTenderService {

    private static final Logger logger = LoggerFactory.getLogger(MessageAboutNewTenderService.class);
    private final TemplateHelper templateHelper;
    private final MessageEmailController messageEmailController;
    private final NotificationProperties notificationProperties;
    private final MessageEmailList messageEmailList;

    public MessageAboutNewTenderService(TemplateHelper templateHelper, MessageEmailController messageEmailController,
                                        NotificationProperties notificationProperties, MessageEmailList messageEmailList) {
        this.templateHelper =
                Objects.requireNonNull(templateHelper, "Can't get template helper");
        this.messageEmailController =
                Objects.requireNonNull(messageEmailController, "Can't get message email controller");
        this.notificationProperties =
                Objects.requireNonNull(notificationProperties, "Can't get notification properties");
        this.messageEmailList =
                Objects.requireNonNull(messageEmailList, "Can't get message email list");
    }

    public void createMessageEmail(VLoadJournal auctionFromJournal) {
        try {
            String messageTheme = auctionFromJournal.getLjDebtorName();
            String messageBody;
            Map<String, Object> data = new HashMap<>();
            data.put("auctionFromJournal", auctionFromJournal);
            messageBody = templateHelper.formTextFromTemplate(notificationProperties.getAfterCreateBlockTenderTemplate(), data);
            if (messageBody != null && messageTheme != null) {
                addMessageToDb(messageTheme, messageBody);
            }
        } catch (Exception ex) {
            logger.error("Error when trying create message email", ex);
        }
    }

    private void addMessageToDb(String messageTheme, String messageBody) {
        List<MessageEmail> messageList = new ArrayList<>();
        for (String recepient : messageEmailList.getRecepients()) {
            MessageEmail messageEmail = new MessageEmail();
            messageEmail.setMeSubject(messageTheme);
            messageEmail.setMeText(messageBody);
            messageEmail.setMeIndSend(0);
            messageEmail.setMeSendDatePlan(new Date());
            messageEmail.setMeRecipient(recepient);
            messageList.add(messageEmail);
        }
        Result<Void, String> result = messageEmailController.addMessageToDb(messageList);
    }
}
