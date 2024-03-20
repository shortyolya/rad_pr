package com.baltinfo.radius.message;

import com.baltinfo.radius.db.controller.EventController;
import com.baltinfo.radius.db.controller.MessageEmailController;
import com.baltinfo.radius.db.model.BlockAuction;
import com.baltinfo.radius.db.model.Event;
import com.baltinfo.radius.db.model.MessageEmail;
import com.baltinfo.radius.db.model.ParticipantAgent;
import com.baltinfo.radius.notification.EmailTextService;
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
 * <p>
 *     Сервис для создания и записи MessageEmail и Event, сообщающих о том, что письмо уже было отправлено
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 02.04.2020
 */
public class CreateEmailMessageService {
    private static final Logger logger = LoggerFactory.getLogger(CreateEmailMessageService.class);

    private final EventController eventController;
    private final TemplateHelper templateHelper;
    private final EmailTextService emailTextService;
    private final MessageEmailController messageEmailController;

    public CreateEmailMessageService(EventController eventController,
                                     TemplateHelper templateHelper, EmailTextService emailTextService, MessageEmailController messageEmailController) {
        this.eventController = Objects.requireNonNull(eventController, "Can't get eventController");
        this.templateHelper = Objects.requireNonNull(templateHelper, "Can't get templateHelper");
        this.emailTextService = Objects.requireNonNull(emailTextService, "Can't get emailTextService");
        this.messageEmailController = Objects.requireNonNull(messageEmailController, "Can't get messageEmailController");
    }

    public Result<Void, String> createEmailMessage(BlockAuction entity, EmailParams params, ParticipantAgent pa) {
        try {
            Event event = new Event();
            event.setBaUnid(entity.getBaUnid());
            event.setTevUnid(params.getTypeEventUnid());

            Map<String, Object> data = new HashMap<>();
            data.put("entity", entity);
            data.put("paName", pa);

            String theme = templateHelper.formTextFromTemplate(params.getThemeTemplate(), data);
            String body = templateHelper.formTextFromTemplate(params.getBodyTemplate(), data);
            event.setEventTheme(theme);
            String emailMessage = emailTextService.formEmailText(body);

            List<MessageEmail> messageEmailList = new ArrayList<>();
            MessageEmail messageEmail = new MessageEmail();
            messageEmail.setMeSubject(theme);
            messageEmail.setMeText(emailMessage);
            messageEmail.setMeIndSend(0);
            messageEmail.setMeSendDatePlan(new Date());
            messageEmail.setMeRecipient(params.getEmailAddress());
            messageEmailList.add(messageEmail);
            Result<Void, String> sentResult = messageEmailController.addMessageToDb(messageEmailList);
            if (sentResult.isError()) {
                logger.error(sentResult.getError());
                return Result.error("Error create email for auction. Error: " + sentResult.getError());
            }
            eventController.createEvent(event);
            return Result.ok();
        } catch (Exception ex) {
            logger.error("Can't create message email", ex);
            return Result.error("Can't create message email " + ex);
        }
    }
}
