package com.baltinfo.radius.notification;

import java.util.Date;

/**
 * @author Suvorina Aleksandra
 * @since 26.11.2018
 */
public class NotificationParam {

    private final Long typeEventUnid;
    private final Date eventDate;
    private final Date notificationDate;
    private final String themeTemplate;
    private final String bodyTemplate;
    private final NotificationType type;

    public static NotificationParamBuilder builder() {
        return new NotificationParamBuilder();
    }

    public NotificationParam(Long typeEventUnid, Date eventDate, Date notificationDate, String themeTemplate,
                             String bodyTemplate, NotificationType type) {
        this.typeEventUnid = typeEventUnid;
        this.eventDate = eventDate;
        this.notificationDate = notificationDate;
        this.themeTemplate = themeTemplate;
        this.bodyTemplate = bodyTemplate;
        this.type = type;
    }

    public Long getTypeEventUnid() {
        return typeEventUnid;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public String getThemeTemplate() {
        return themeTemplate;
    }

    public String getBodyTemplate() {
        return bodyTemplate;
    }

    public NotificationType getType() {
        return type;
    }

    public static final class NotificationParamBuilder {

        private Long typeEventUnid;
        private Date eventDate;
        private Date notificationDate;
        private String themeTemplate;
        private String bodyTemplate;
        private NotificationType type;

        private NotificationParamBuilder() {
        }

        public NotificationParamBuilder withTypeEventUnid(Long typeEventUnid) {
            this.typeEventUnid = typeEventUnid;
            return this;
        }

        public NotificationParamBuilder withEventDate(Date eventDate) {
            this.eventDate = eventDate;
            return this;
        }

        public NotificationParamBuilder withNotificationDate(Date notificationDate) {
            this.notificationDate = notificationDate;
            return this;
        }

        public NotificationParamBuilder withThemeTemplate(String themeTemplate) {
            this.themeTemplate = themeTemplate;
            return this;
        }

        public NotificationParamBuilder withBodyTemplate(String bodyTemplate) {
            this.bodyTemplate = bodyTemplate;
            return this;
        }

        public NotificationParamBuilder withType(NotificationType type) {
            this.type = type;
            return this;
        }

        public NotificationParam build() {
            return new NotificationParam(typeEventUnid, eventDate, notificationDate, themeTemplate, bodyTemplate, type);
        }
    }
}
