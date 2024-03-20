package com.baltinfo.radius.notification;

import com.baltinfo.radius.db.controller.AuctionNotificationController;
import com.baltinfo.radius.db.controller.NotificationController;
import com.baltinfo.radius.db.model.EntityNotification;
import com.baltinfo.radius.db.model.Event;
import com.baltinfo.radius.db.model.MessageEmail;
import com.baltinfo.radius.db.model.NotifSettings;
import com.baltinfo.radius.db.model.Notification;
import com.baltinfo.radius.db.model.ParticipantAgent;
import com.baltinfo.radius.db.model.Subject;
import com.baltinfo.radius.template.TemplateHelper;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Suvorina Aleksandra
 * @since 26.11.2018
 */
public class CreateNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(CreateNotificationService.class);

    private final AuctionNotificationController auctionNotificationController;
    private final TemplateHelper templateHelper;
    private final EmailTextService emailTextService;
    private final String asvCuratorEmail;
    private final String trustCuratorEmail;
    private final NotificationController notificationController;

    public CreateNotificationService(AuctionNotificationController auctionNotificationController,
                                     TemplateHelper templateHelper, EmailTextService emailTextService,
                                     String asvCuratorEmail, String trustCuratorEmail,
                                     NotificationController notificationController) {
        this.auctionNotificationController = auctionNotificationController;
        this.templateHelper = templateHelper;
        this.emailTextService = emailTextService;
        this.asvCuratorEmail = asvCuratorEmail;
        this.trustCuratorEmail = trustCuratorEmail;
        this.notificationController = notificationController;
    }

    public Result<Void, String> createNotifications(EntityNotification entity, NotificationParam params) {
        try {
            Event event = new Event();
            event.setAuctionUnid(entity.getAuctionUnid());
            event.setRedSchedUnid(entity.getRedSchedUnid());
            event.setTevUnid(params.getTypeEventUnid());
            event.setEventDateB(params.getEventDate());
            event.setEventDateE(params.getEventDate());

            Map<String, Object> data = new HashMap<>();
            data.put("entity", entity);

            String theme = templateHelper.formTextFromTemplate(params.getThemeTemplate(), data);
            String text = templateHelper.formTextFromTemplate(params.getBodyTemplate(), data);
            event.setEventTheme(theme);

            List<Notification> notifications = new ArrayList();
            List<MessageEmail> emails = new ArrayList();
            String meRecipient = "";

            if (params.getType() == NotificationType.ASV) {
                List<ParticipantAgent> curators = auctionNotificationController.getCuratorsByAuction(entity.getAuctionUnid());
                for (ParticipantAgent pa : curators) {
                    NotifSettings notifSettings = notificationController.getNotifSettings(event.getTevUnid(), pa.getSubSubUnid().getSubUnid());
                    Notification notification = new Notification();
                    notification.setEventUnid(event);
                    notification.setNtfIndEmail(notifSettings == null || notifSettings.getNsIndRemindEmail());
                    notification.setNtfIndSystem(notifSettings == null || notifSettings.getNsIndRemindSystem());
                    notification.setNtfTime(params.getNotificationDate());
                    notification.setSubUnid(pa.getSubSubUnid().getSubUnid());
                    notification.setNtfText(text);

                    List<MessageEmail> messages = createEmailMessages(pa, notification, event.getEventTheme());

                    if (notifSettings != null) {
                        if (notifSettings.getNsIndRemindSystem()) notifications.add(notification);
                        if (notifSettings.getNsIndRemindEmail()) emails.addAll(messages);
                    } else {
                        notifications.add(notification);
                        emails.addAll(messages);
                    }
                }
                meRecipient = asvCuratorEmail;
            } else if (params.getType() == NotificationType.TRUST) {
                meRecipient = trustCuratorEmail;
            }

            if (meRecipient.trim().length() > 0) {
                String emailMessage = emailTextService.formEmailText(text);
                MessageEmail messageEmail = new MessageEmail(meRecipient, event.getEventTheme(),
                        emailMessage, params.getNotificationDate());
                emails.add(messageEmail);
            }

            return auctionNotificationController.createEventWithNotifications(event, notifications, emails);

        } catch (Exception ex) {
            logger.error("Error create Notification and Email for entity with auctionUnid = {}, redSchedUnid = {}",
                    entity.getAuctionUnid(), entity.getRedSchedUnid(), ex);
            return Result.error("Error create notification and email for auction. Error: " + ex.getMessage());
        }
    }

    private List<MessageEmail> createEmailMessages(ParticipantAgent pa, Notification notification, String theme) throws Exception {
        List<MessageEmail> messages = new ArrayList<>();
        Subject paSubject = pa.getSubSubUnid();
        List<String> emailArr = new ArrayList<>();
        StringBuilder emails = new StringBuilder(paSubject.getSubEMail());
        if (paSubject.getSubAdditionalEmail() != null && !paSubject.getSubAdditionalEmail().trim().isEmpty()) {
            emails.append(",").append(paSubject.getSubAdditionalEmail());
        }
        String email = emails.toString();
        if (email.contains(",")) {
            emailArr = Arrays.stream(email.split(",")).map(item -> item.trim()).collect(Collectors.toList());
        } else if (!email.trim().isEmpty()) {
            emailArr.add(email.trim());
        }
        String emailMessage = emailTextService.formEmailText(pa, notification.getNtfText());
        for (String emailItem : emailArr) {
            MessageEmail messageEmail = new MessageEmail(emailItem, theme,
                    emailMessage, notification.getNtfTime());
            messages.add(messageEmail);
        }
        return messages;
    }
}
