package com.baltinfo.radius.notification.impl;

import com.baltinfo.radius.application.configuration.NotificationProperties;
import com.baltinfo.radius.db.constants.TypeEventConstant;
import com.baltinfo.radius.db.controller.AuctionNotificationController;
import com.baltinfo.radius.db.model.VAuctionNotification;
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
 * @since 22.11.2018
 */
public class EndReceiptApplicationsNotificationService implements NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(EndReceiptApplicationsNotificationService.class);

    private final AuctionNotificationController auctionNotificationController;
    private final CreateNotificationService createNotificationService;
    private final NotificationProperties notificationProperties;

    public EndReceiptApplicationsNotificationService(AuctionNotificationController auctionNotificationController,
                                                     CreateNotificationService createNotificationService,
                                                     NotificationProperties notificationProperties) {
        this.auctionNotificationController = auctionNotificationController;
        this.createNotificationService = createNotificationService;
        this.notificationProperties = notificationProperties;
    }

    public void sendNotifications() {
        List<VAuctionNotification> auctions = auctionNotificationController.getAuctionsForEndReceiptApplications();
        for (VAuctionNotification auction : auctions) {
            NotificationParam param = NotificationParam.builder()
                    .withTypeEventUnid(TypeEventConstant.END_RECEIPT_APPLICATIONS.getUnid())
                    .withEventDate(auction.getAuctionRecepDateE())
                    .withNotificationDate(auction.getAuctionRecepDateE())
                    .withThemeTemplate(notificationProperties.getEndReceiptApplicationsTheme())
                    .withBodyTemplate(notificationProperties.getEndReceiptApplicationsTemplate())
                    .withType(NotificationType.getByLsUnid(auction.getLsUnid()))
                    .build();
            Result<Void, String> createEventResult = createNotificationService.createNotifications(auction, param);
        }
    }

}
