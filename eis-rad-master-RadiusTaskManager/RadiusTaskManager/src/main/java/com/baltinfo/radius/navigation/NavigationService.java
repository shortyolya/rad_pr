package com.baltinfo.radius.navigation;


import com.baltinfo.radius.application.configuration.NavigationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Suvorina Aleksandra
 * @since 20.04.2018
 */
public class NavigationService {

    private static Logger logger = LoggerFactory.getLogger(NavigationService.class);

    private final NavigationUtils navigationUtils;
    private final NavigationProperties navigationProperties;

    public NavigationService(NavigationUtils navigationUtils, NavigationProperties navigationProperties) {
        this.navigationUtils = navigationUtils;
        this.navigationProperties = navigationProperties;
    }

    public String formEisAuctionCardUrl(Long auctionUnid) {
        String pageName = navigationProperties.getEisBaseUrl() + "/account-auction-card.xhtml?parm=";
        String param = "auctionUnid=" + auctionUnid + ";mode=VIEW;retPage=" + navigationProperties.getEisMainPage() +
                ";tabId=account-auction-card.xhtml";

        String url = null;
        try {
            url = pageName + navigationUtils.encrypt(param);
        } catch (Throwable ex) {
            logger.error("Error when encrypting eis auction card param", ex);
        }
        return url;
    }

    public String formEisBlockAuctionCardUrlForEdit(Long baUnid) {
        String pageName = navigationProperties.getEisBaseUrl() + "/account-block-auction-card.xhtml?parm=";
        String param = "auctionUnid=" + baUnid + ";mode=EDIT;retPage=" + navigationProperties.getEisMainPage() + ";";

        String url = null;
        try {
            url = pageName + navigationUtils.encrypt(param);
        } catch (Throwable ex) {
            logger.error("Error when encrypting eis block auction card param", ex);
        }
        return url;
    }

    public String formBkrAuctionCardUrl(Long bkrAuctionUnid) {
        String pageName = navigationProperties.getBkrBaseUrl() + "account-sale-create.xhtml?parm=";
        String param = "mode=VIEW;auctionUnid=" + bkrAuctionUnid;

        String url = null;
        try {
            url = pageName + navigationUtils.encrypt(param);
        } catch (Throwable ex) {
            logger.error("Error when encrypting bkr auction card param", ex);
        }
        return url;
    }

    public String formVitrinaBkrAuctionCardUrl(Long bkrAuctionUnid) {
        if (navigationProperties.getVitrinaBkrBaseUrl() == null || navigationProperties.getVitrinaBkrBaseUrl().isEmpty()) {
            return null;
        }
        String pageName = navigationProperties.getVitrinaBkrBaseUrl() + "&p=account-sale-create.xhtml&parm=";
        String param = "mode=VIEW;auctionUnid=" + bkrAuctionUnid;

        String url = null;
        try {
            url = pageName + navigationUtils.encrypt(param);
        } catch (Throwable ex) {
            logger.error("Error when encrypting bkr auction card param", ex);
        }
        return url;
    }

    public String formEisLotCardUrl(Long lotUnid) {
        String pageName = navigationProperties.getEisBaseUrl() + "/account-lot-card.xhtml?parm=";
        String param = "lotUnid=" + lotUnid + ";mode=VIEW;retPage=" + navigationProperties.getEisMainPage();

        String url = null;
        try {
            url = pageName + navigationUtils.encrypt(param);
        } catch (Throwable ex) {
            logger.error("Error when encrypting eis lot card param", ex);
        }
        return url;
    }

    public String formEisObjLotCardUrl(Long objUnid) {
        String pageName = navigationProperties.getEisBaseUrl() + "/account-obj-card-auction.xhtml?parm=";
        String param = "objUnid=" + objUnid + ";mode=VIEW;retPage=" + navigationProperties.getEisMainPage() +
        ";tabId=account-obj-card-auction.xhtml";

        String url = null;
        try {
            url = pageName + navigationUtils.encrypt(param);
        } catch (Throwable ex) {
            logger.error("Error when encrypting eis obj card param", ex);
        }
        return url;
    }

    public String formVitrinaAuctionLotListUrl(String tenderCode) {
        if (navigationProperties.getVitrinaBaseUrl() == null || navigationProperties.getVitrinaBaseUrl().isEmpty()) {
            return null;
        }
        return navigationProperties.getVitrinaBaseUrl() + "?dispatch=categories.view&category_id=9876&filter_fields[tender_code]=" + tenderCode;
    }

}
