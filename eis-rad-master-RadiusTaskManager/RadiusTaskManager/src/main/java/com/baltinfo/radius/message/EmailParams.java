package com.baltinfo.radius.message;

/**
 * <p>
 *     Параметры, необходимые для передачи при отправке email
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 02.04.2020
 */
public class EmailParams {
    private final Long typeEventUnid;
    private final String themeTemplate;
    private final String bodyTemplate;
    private final String emailAddress;


    public EmailParams(Long typeEventUnid, String themeTemplate, String bodyTemplate, String emailAddress) {
        this.typeEventUnid = typeEventUnid;
        this.themeTemplate = themeTemplate;
        this.bodyTemplate = bodyTemplate;
        this.emailAddress = emailAddress;
    }

    public Long getTypeEventUnid() {
        return typeEventUnid;
    }

    public String getThemeTemplate() {
        return themeTemplate;
    }

    public String getBodyTemplate() {
        return bodyTemplate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
