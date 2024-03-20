package com.baltinfo.radius.notification.impl;

import com.baltinfo.radius.db.constants.AccessProfiles;
import com.baltinfo.radius.db.constants.TypeEventConstant;
import com.baltinfo.radius.db.controller.AccessProfileController;
import com.baltinfo.radius.db.controller.NotificationController;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.template.TemplateHelper;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class AccountantNotificationService {
    private static final Logger logger = LoggerFactory.getLogger(AccountantNotificationService.class);

    private final AccessProfileController accessProfileController;
    private final TemplateHelper templateHelper;
    private final String notificationThemeTemplate;
    private final String notificationTextTemplate;
    private final NotificationController notificationController;

    public AccountantNotificationService(AccessProfileController accessProfileController,
                                         TemplateHelper templateHelper,
                                         String notificationThemeTemplate,
                                         String notificationTextTemplate,
                                         NotificationController notificationController) {
        this.accessProfileController = accessProfileController;
        this.templateHelper = templateHelper;
        this.notificationThemeTemplate = notificationThemeTemplate;
        this.notificationTextTemplate = notificationTextTemplate;
        this.notificationController = notificationController;
    }

    public boolean createTradeResultNotification(Long accountantPa, Lot lot) {
        try {
            boolean paHasProfile = accessProfileController.checkPaHasAccessProfile(accountantPa, AccessProfiles.RESULT_MESSAGE_ACCOUNTANT.getApUnid());
            if (paHasProfile) {
                Map<String, Object> data = new HashMap<>();
                data.put("lot", lot);
                String notificationTheme = templateHelper.formTextFromTemplate(notificationThemeTemplate, data);
                String notificationText = templateHelper.formTextFromTemplate(notificationTextTemplate, data);


                return notificationController.createNotification(TypeEventConstant.LOT_RESULTS.getUnid(),
                        accountantPa, notificationTheme, notificationText);
            }
            return true;
        } catch (Exception ex) {
            logger.error("Error createTradeResultNotification for accountantPa = {}, lotUnid = {}", accountantPa, lot.getLotUnid(), ex);
            return false;
        }
    }
}
