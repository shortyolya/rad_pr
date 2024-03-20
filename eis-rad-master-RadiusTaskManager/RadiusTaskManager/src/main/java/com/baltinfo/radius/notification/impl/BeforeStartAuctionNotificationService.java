package com.baltinfo.radius.notification.impl;

import com.baltinfo.radius.application.configuration.NotificationProperties;
import com.baltinfo.radius.db.constants.TypeEventConstant;
import com.baltinfo.radius.db.controller.AuctionNotificationController;
import com.baltinfo.radius.db.model.VAuctionNotification;
import com.baltinfo.radius.notification.CreateNotificationService;
import com.baltinfo.radius.notification.NotificationParam;
import com.baltinfo.radius.notification.NotificationService;
import com.baltinfo.radius.notification.NotificationType;
import com.baltinfo.radius.utils.DateUtils;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 26.11.2018
 */
public class BeforeStartAuctionNotificationService implements NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(BeforeStartAuctionNotificationService.class);

    private final AuctionNotificationController auctionNotificationController;
    private final CreateNotificationService createNotificationService;
    private final NotificationProperties notificationProperties;

    public BeforeStartAuctionNotificationService(AuctionNotificationController auctionNotificationController,
                                                 CreateNotificationService createNotificationService,
                                                 NotificationProperties notificationProperties) {
        this.auctionNotificationController = auctionNotificationController;
        this.createNotificationService = createNotificationService;
        this.notificationProperties = notificationProperties;
    }

    public void sendNotifications() {
        List<VAuctionNotification> auctions = auctionNotificationController.getAuctionsBeforeStartAuctionDate();
        for (VAuctionNotification auction : auctions) {
            NotificationParam param = NotificationParam.builder()
                    .withTypeEventUnid(TypeEventConstant.START_AUCTION.getUnid())
                    .withEventDate(auction.getAuctionDateB())
                    .withNotificationDate(DateUtils.minusDay(auction.getAuctionDateB(), 1))
                    .withThemeTemplate(notificationProperties.getBeforeStartAuctionTheme())
                    .withBodyTemplate(notificationProperties.getBeforeStartAuctionTemplate())
                    .withType(NotificationType.getByLsUnid(auction.getLsUnid()))
                    .build();
            Result<Void, String> createEventResult = createNotificationService.createNotifications(auction, param);
        }
    }

}
