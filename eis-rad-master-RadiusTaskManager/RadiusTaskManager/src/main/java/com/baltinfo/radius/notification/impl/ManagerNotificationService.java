package com.baltinfo.radius.notification.impl;

import com.baltinfo.radius.db.constants.AccessProfiles;
import com.baltinfo.radius.db.constants.TypeEventConstant;
import com.baltinfo.radius.db.controller.AccessProfileController;
import com.baltinfo.radius.db.controller.AuctionController;
import com.baltinfo.radius.db.controller.BlockAuctionController;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.controller.NotificationController;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.BlockAuction;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.template.TemplateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ManagerNotificationService {
    private static final Logger logger = LoggerFactory.getLogger(ManagerNotificationService.class);

    private final AccessProfileController accessProfileController;
    private final TemplateHelper templateHelper;
    private final String notificationThemeTemplate;
    private final String notificationTextTemplate;
    private final NotificationController notificationController;
    private final AuctionController auctionController;
    private final LotController lotController;
    private final BlockAuctionController blockAuctionController;

    public ManagerNotificationService(AccessProfileController accessProfileController,
                                      TemplateHelper templateHelper,
                                      String notificationThemeTemplate,
                                      String notificationTextTemplate,
                                      NotificationController notificationController,
                                      AuctionController auctionController, LotController lotController, BlockAuctionController blockAuctionController) {
        this.accessProfileController = accessProfileController;
        this.templateHelper = templateHelper;
        this.notificationThemeTemplate = notificationThemeTemplate;
        this.notificationTextTemplate = notificationTextTemplate;
        this.notificationController = notificationController;
        this.auctionController = auctionController;
        this.lotController = lotController;
        this.blockAuctionController = blockAuctionController;
    }

    public boolean createTradePublishedNotification(Long auctionUnid) {
        try {
            Auction auction = auctionController.findByAuctionUnid(auctionUnid);
            List<Lot> lots = lotController.getCurrentLotsByAuction(auctionUnid);
            Set<Long> managerPaUnids = lots.stream()
                    .map(l -> l.getObjUnid().getPaManagerUnid())
                    .collect(Collectors.toSet());

            for (Long paUnid : managerPaUnids) {
                boolean paHasProfile = accessProfileController.checkPaHasAccessProfile(paUnid, AccessProfiles.AUCTION_PUBLISH_MESSAGE_MANAGER.getApUnid());
                if (paHasProfile) {
                    BlockAuction blockAuction = null;
                    if (auction.getBaUnid() != null) {
                        blockAuction = blockAuctionController.getBlockAuctionByUnid(auction.getBaUnid());
                    }
                    Map<String, Object> data = new HashMap<>();

                    data.put("entity", auction);
                    data.put("auctionName", blockAuction != null ? blockAuction.getBaName() : auction.getAuctionName());
                    String notificationTheme = templateHelper.formTextFromTemplate(notificationThemeTemplate, data);
                    String notificationText = templateHelper.formTextFromTemplate(notificationTextTemplate, data);
                    notificationController.createNotification(TypeEventConstant.AUCTION_PUBLISHED.getUnid(),
                            paUnid, notificationTheme, notificationText);
                }
            }
            return true;
        } catch (Exception ex) {
            logger.error("Error createTradePublishedNotification for auctionUnid = {}", auctionUnid, ex);
            return false;
        }
    }
}
