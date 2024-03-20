package com.baltinfo.radius.notification.impl;

import com.baltinfo.radius.application.configuration.NavigationProperties;
import com.baltinfo.radius.db.controller.AccessProfileController;
import com.baltinfo.radius.db.controller.EventController;
import com.baltinfo.radius.db.controller.LotPublishedNotificationController;
import com.baltinfo.radius.db.controller.MessageEmailController;
import com.baltinfo.radius.db.dao.EventDao;
import com.baltinfo.radius.db.dao.MessageEmailDao;
import com.baltinfo.radius.navigation.NavigationService;
import com.baltinfo.radius.navigation.NavigationUtils;
import com.baltinfo.radius.notification.EmailTextService;
import com.baltinfo.radius.template.ThymeleafTemplateHelper;
import com.baltinfo.radius.utils.Result;
import org.junit.Ignore;
import org.junit.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import static org.junit.Assert.*;

public class LotPublishedNotificationServiceTest {

    private String notificationLotPublishedThemeTemplate = "Перечень опубликованных лотов с НЦ более 300 млн. руб.";

    private String notificationLotPublishedTextTemplate = "<style>\n" +
            "\t#template td, #template th {\n" +
            "\t  border: 1px solid #ddd;\n" +
            "\t  padding: 8px;\n" +
            "\t}\n" +
            "\t#template th {\n" +
            "\t  padding-top: 12px;\n" +
            "\t  padding-bottom: 12px;\n" +
            "\t  text-align: left;\n" +
            "\t}\n" +
            "</style> <p id=\"template\">" +
            "<table th:style=\"'border:1px;'\">" +
            "<thead>" +
            "<tr>" +
            "<th> Код объекта </th>" +
            "<th> Профильное подразделение </th>" +
            "<th> Продающее подразделение </th>" +
            "<th> Направление продаж ДП </th>" +
            "<th> Классификатор ДП </th>" +
            "<th> Наименование объекта </th>" +
            "<th> Регион </th>" +
            "<th> Вид торгов </th>" +
            "<th> Начальная стоимость в рублях </th>" +
            "<th> Начало приема заявок </th>" +
            "<th> Дата торгов </th>" +
            "<th> ЭТП </th>" +
            "<th> Сайт РАД </th>" +
            "</tr>" +
            "</thead>" +
            "<tbody>" +
            "<tr th:each=\"lot : ${lots}\">" +
            "<td><span th:text=\"${lot.objCode}\"></span></span></td>" +
            "<td><span th:text=\"${lot.createDivision}\"></span></td>" +
            "<td><span th:text=\"${lot.saleDivision}\"></span></td>" +
            "<td><span th:text=\"${lot.tstName}\"></span></td>" +
            "<td><span th:text=\"${lot.sgtName}\"></span></td>" +
            "<td><span th:text=\"${lot.objName}\"></span></td>" +
            "<td><span th:text=\"${lot.srfAsvName}\"></span></td>" +
            "<td><span th:text=\"${lot.typeAuctionName}\"></span></td>" +
            "<td><span th:text=\"${#numbers.formatDecimal(lot.startCostRub, 1, 'WHITESPACE', 2, 'POINT')}\"></span></td>" +
            "<td><span th:text=\"${#dates.format(lot.auctionRecepDateB, 'dd-MM-yyyy HH:mm')}\"></span></td>" +
            "<td><span th:text=\"${#dates.format(lot.auctionDateB, 'dd-MM-yyyy HH:mm')}\"></span></td>" +
            "<td><a th:href=\"${lot.lotLinkSite}\"><span th:text=\"${lot.lotEtpCode}\"></span></a></td>" +
            "<td><a th:href=\"${lot.lotRadSite}\"><span>Сайт РАД</span></a></td>" +
            "</tr>" +
            "</tbody>" +
            "</table>" +
            "</p>";


    private String greetingTemplate = "<p>Уважаемый(ая) <span th:text=\"${subNameIO}\"/>!</p>";
    private String footerTemplate = "";

    @Test
    @Ignore
    public void test() {
        AccessProfileController accessProfileController = new AccessProfileController();
        LotPublishedNotificationController lotPublishedNotificationController = new LotPublishedNotificationController();
        NavigationUtils utils = new NavigationUtils();
        NavigationProperties navigationProperties = new NavigationProperties();
        NavigationService navigationService = new NavigationService(utils, navigationProperties);
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.addDialect(new Java8TimeDialect());
        templateEngine.setTemplateResolver(new StringTemplateResolver());
        ThymeleafTemplateHelper templateHelper = new ThymeleafTemplateHelper(templateEngine, navigationService);

        MessageEmailDao messageEmailDao = new MessageEmailDao();
        MessageEmailController messageEmailController = new MessageEmailController(messageEmailDao);
        EventDao eventDao = new EventDao();
        EventController eventController = new EventController(eventDao);

        EmailTextService emailTextService = new EmailTextService(templateHelper, greetingTemplate, footerTemplate);

        LotPublishedNotificationService service = new LotPublishedNotificationService(accessProfileController, lotPublishedNotificationController,
                templateHelper, notificationLotPublishedThemeTemplate, notificationLotPublishedTextTemplate,
                messageEmailController, eventController, emailTextService);

        Result<Void, String> result = service.sendNotifications();

        assertEquals(true, result.isSuccess());
    }
}