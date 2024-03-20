package com.baltinfo.radius.links.constant;

import com.baltinfo.radius.db.constants.ExchangeProcs;

public enum RecipientSite {
    AUCTION_HOUSE("ah", "auction-house.ru",
            ExchangeProcs.SEND_AUCTION_TO_AUCTION_HOUSE),
    HOLDING("holding", "radholding.ru",
            ExchangeProcs.SEND_AUCTION_TO_RAD_HOLDING);

    private final String propertyPref;
    private final String siteUrl;
    private final Long sendAuctionEpUnid;

    RecipientSite(String propertyPref, String siteUrl, ExchangeProcs sendAuctionEpUnid) {
        this.propertyPref = propertyPref;
        this.siteUrl = siteUrl;
        this.sendAuctionEpUnid = sendAuctionEpUnid.getUnid();
    }

    public String getPropertyPref() {
        return propertyPref;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public Long getSendAuctionEpUnid() {
        return sendAuctionEpUnid;
    }

}