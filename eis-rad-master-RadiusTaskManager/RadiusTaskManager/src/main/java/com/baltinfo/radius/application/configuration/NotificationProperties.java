package com.baltinfo.radius.application.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Suvorina Aleksandra
 * @since 23.11.2018
 */
@Configuration
@PropertySource("classpath:notification.properties")
public class NotificationProperties {

    @Value("${notification.endReceiptApplications.template}")
    private String endReceiptApplicationsTemplate;

    @Value("${notification.endReceiptApplications.theme}")
    private String endReceiptApplicationsTheme;

    @Value("${notification.beforeStartAuction.theme}")
    private String beforeStartAuctionTheme;

    @Value("${notification.beforeStartAuction.template}")
    private String beforeStartAuctionTemplate;

    @Value("${notification.endApplRedSchedPeriod.theme}")
    private String endApplRedSchedPerionTheme;

    @Value("${notification.endApplRedSchedPeriod.template}")
    private String endApplRedSchedPerionTemplate;

    @Value("${notification.afterCreateBlockTender.template}")
    private String afterCreateBlockTenderTemplate;

    public String getEndReceiptApplicationsTemplate() {
        return endReceiptApplicationsTemplate;
    }

    public String getEndReceiptApplicationsTheme() {
        return endReceiptApplicationsTheme;
    }

    public String getBeforeStartAuctionTheme() {
        return beforeStartAuctionTheme;
    }

    public String getBeforeStartAuctionTemplate() {
        return beforeStartAuctionTemplate;
    }

    public String getEndApplRedSchedPerionTheme() {
        return endApplRedSchedPerionTheme;
    }

    public String getEndApplRedSchedPerionTemplate() {
        return endApplRedSchedPerionTemplate;
    }

    public String getAfterCreateBlockTenderTemplate() {
        return afterCreateBlockTenderTemplate;
    }
}
