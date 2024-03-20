package com.baltinfo.radius.template;

import com.baltinfo.radius.application.configuration.NavigationProperties;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.navigation.NavigationService;
import com.baltinfo.radius.navigation.NavigationUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.HashMap;
import java.util.Map;


public class ThymeleafTemplateHelperTest {

    @Test
    @Ignore
    public void testUrl() {
        NavigationUtils utils = new NavigationUtils();
        NavigationProperties navigationProperties = new NavigationProperties();
        NavigationService navigationService = new NavigationService(utils, navigationProperties);
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(new StringTemplateResolver());
        ThymeleafTemplateHelper helper = new ThymeleafTemplateHelper(templateEngine, navigationService);

        Map<String, Object> data = new HashMap<>();
        Auction auction = new Auction();
        auction.setAuctionUnid(12334L);
        auction.setAuctionEtpCode("35122");
        data.put("entity", auction);
        data.put("auctionName", "auction Name");
        String template = "<p>По лотам где вы являетесь менеджером произошла публикация торгов на ЭТП.</p> \\\n" +
                "<p>Наименование торгов: <span th:text=\"${auctionName}\"/></p> \\\n" +
                "<p><a th:href=\"${navigationService.formEisAuctionCardUrl(entity.auctionUnid)}\"><span>Карта торгов в ЕИС</span></a></p> \\\n" +
                "<p><a th:href=\"@{${navigationService.getVitrineBaseUrl()}(dispatch='categories.view', category_id=9876, filter_fields[tender_code]=${entity.auctionEtpCode})}\"><span>Торги на площадке</span></a></p>\n" +
                "\n";
        String notificationText = helper.formTextFromTemplate(template, data);

        System.out.println(notificationText);
    }

}