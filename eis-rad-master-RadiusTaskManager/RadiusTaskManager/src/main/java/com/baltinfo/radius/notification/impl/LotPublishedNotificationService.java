package com.baltinfo.radius.notification.impl;

import com.baltinfo.radius.db.constants.AccessProfiles;
import com.baltinfo.radius.db.constants.TypeEventConstant;
import com.baltinfo.radius.db.controller.AccessProfileController;
import com.baltinfo.radius.db.controller.EventController;
import com.baltinfo.radius.db.controller.LotPublishedNotificationController;
import com.baltinfo.radius.db.controller.MessageEmailController;
import com.baltinfo.radius.db.model.Event;
import com.baltinfo.radius.db.model.MessageEmail;
import com.baltinfo.radius.db.model.ParticipantAgent;
import com.baltinfo.radius.db.model.VAuctionLot;
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
import java.util.stream.Collectors;

public class LotPublishedNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(LotPublishedNotificationService.class);


    private final AccessProfileController accessProfileController;
    private final LotPublishedNotificationController lotPublishedNotificationController;
    private final TemplateHelper templateHelper;
    private final String notificationThemeTemplate;
    private final String notificationTextTemplate;
    private final MessageEmailController messageEmailController;
    private final EventController eventController;
    private final EmailTextService emailTextService;


    public LotPublishedNotificationService(AccessProfileController accessProfileController,
                                           LotPublishedNotificationController lotPublishedNotificationController,
                                           TemplateHelper templateHelper,
                                           String notificationThemeTemplate,
                                           String notificationTextTemplate,
                                           MessageEmailController messageEmailController,
                                           EventController eventController, EmailTextService emailTextService) {
        this.accessProfileController = accessProfileController;
        this.lotPublishedNotificationController = lotPublishedNotificationController;
        this.templateHelper = templateHelper;
        this.notificationThemeTemplate = notificationThemeTemplate;
        this.notificationTextTemplate = notificationTextTemplate;
        this.messageEmailController = messageEmailController;
        this.eventController = eventController;
        this.emailTextService = emailTextService;
    }

    public Result<Void, String> sendNotifications() {
        try {
            List<VAuctionLot> lots = lotPublishedNotificationController.getLotsForPublishNotification();
            if (lots.isEmpty()) {
                return Result.ok();
            }
            List<ParticipantAgent> participants = accessProfileController.getParticipantsWithProfile(AccessProfiles.LOT_PUBLISHED_MESSAGE.getApUnid());

            Map<String, Object> data = new HashMap<>();
            data.put("lots", lots);
            String notificationTheme = templateHelper.formTextFromTemplate(notificationThemeTemplate, data);
            String notificationText = templateHelper.formTextFromTemplate(notificationTextTemplate, data);

            List<Event> events = lots.stream()
                    .map(lot -> {
                        Event event = new Event();
                        event.setLotUnid(lot.getLotUnid());
                        event.setTevUnid(TypeEventConstant.LOT_PUBLISHED.getUnid());
                        event.setEventTheme(notificationTheme);
                        return event;
                    })
                    .collect(Collectors.toList());

            List<MessageEmail> messageEmailList = new ArrayList<>();

            for (ParticipantAgent pa: participants) {
                String emailText = emailTextService.formEmailText(pa, notificationText);
                MessageEmail messageEmail = new MessageEmail();
                messageEmail.setMeSubject(notificationTheme);
                messageEmail.setMeText(emailText);
                messageEmail.setMeIndSend(0);
                messageEmail.setMeSendDatePlan(new Date());
                messageEmail.setMeRecipient(pa.getSubSubUnid().getSubEMail());
                messageEmailList.add(messageEmail);
            }
            Result<Void, String> sentResult = messageEmailController.addMessageToDb(messageEmailList);
            if (sentResult.isError()) {
                logger.error(sentResult.getError());
                return Result.error("Error create email for auction. Error: " + sentResult.getError());
            }
            eventController.createEvents(events);
            return Result.ok();

        } catch (Exception ex) {
            logger.error("Error send lot publish notifications", ex);
            return Result.error("Can't send lot publish notifications " + ex);
        }
    }

}
