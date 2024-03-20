package com.baltinfo.radius.notification.impl;

import com.baltinfo.radius.application.configuration.NotificationProperties;
import com.baltinfo.radius.db.constants.TypeEventConstant;
import com.baltinfo.radius.db.controller.AuctionNotificationController;
import com.baltinfo.radius.db.model.VRsNotification;
import com.baltinfo.radius.notification.CreateNotificationService;
import com.baltinfo.radius.notification.NotificationParam;
import com.baltinfo.radius.notification.NotificationService;
import com.baltinfo.radius.notification.NotificationType;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 27.11.2018
 */
public class EndApplReductionPeriodNotificationService implements NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(EndApplReductionPeriodNotificationService.class);

    private final AuctionNotificationController auctionNotificationController;
    private final CreateNotificationService createNotificationService;
    private final NotificationProperties notificationProperties;

    public EndApplReductionPeriodNotificationService(AuctionNotificationController auctionNotificationController,
                                                     CreateNotificationService createNotificationService,
                                                     NotificationProperties notificationProperties) {
        this.auctionNotificationController = auctionNotificationController;
        this.createNotificationService = createNotificationService;
        this.notificationProperties = notificationProperties;
    }

    public void sendNotifications() {
        List<VRsNotification> redScheds = auctionNotificationController.getRedSchedForEndPeriod();
        for (VRsNotification rs : redScheds) {
            NotificationParam param = NotificationParam.builder()
                    .withTypeEventUnid(TypeEventConstant.END_APPL_REDUCTION_PERIOD.getUnid())
                    .withEventDate(rs.getRedSchedApplDateE())
                    .withNotificationDate(rs.getRedSchedApplDateE())
                    .withThemeTemplate(notificationProperties.getEndApplRedSchedPerionTheme())
                    .withBodyTemplate(notificationProperties.getEndApplRedSchedPerionTemplate())
                    .withType(NotificationType.getByLsUnid(rs.getLsUnid()))
                    .build();
            Result<Void, String> createEventResult = createNotificationService.createNotifications(rs, param);
        }
    }

}
