package com.baltinfo.radius.application.configuration;

import com.baltinfo.radius.db.controller.AuctionNotificationController;
import com.baltinfo.radius.db.controller.DocTemplateController;
import com.baltinfo.radius.db.controller.EventController;
import com.baltinfo.radius.db.controller.MessageEmailController;
import com.baltinfo.radius.db.controller.NotificationController;
import com.baltinfo.radius.db.controller.PayDocNotificationController;
import com.baltinfo.radius.db.dao.EventDao;
import com.baltinfo.radius.db.dao.MessageEmailDao;
import com.baltinfo.radius.db.dao.NotificationDao;
import com.baltinfo.radius.documents.generator.CreatingDocumentService;
import com.baltinfo.radius.loadauction.service.MessageAboutNewTenderService;
import com.baltinfo.radius.loadauction.service.MessageEmailList;
import com.baltinfo.radius.mailsender.MailSenderClient;
import com.baltinfo.radius.notification.AsvNotificationJob;
import com.baltinfo.radius.notification.CreateNotificationService;
import com.baltinfo.radius.notification.EmailTextService;
import com.baltinfo.radius.notification.impl.BeforeStartAuctionNotificationService;
import com.baltinfo.radius.notification.impl.EndApplReductionPeriodNotificationService;
import com.baltinfo.radius.notification.impl.EndReceiptApplicationsNotificationService;
import com.baltinfo.radius.notification.paydoc.PayDocNotificationJob;
import com.baltinfo.radius.notification.paydoc.PayDocNotificationService;
import com.baltinfo.radius.radapi.client.RadApiClient;
import com.baltinfo.radius.radapi.security.TokenService;
import com.baltinfo.radius.rest.client.HttpRequestService;
import com.baltinfo.radius.template.ThymeleafTemplateHelper;
import com.baltinfo.radius.vitrina.VitrinaClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Suvorina Aleksandra
 * @since 23.11.2018
 */
@Configuration
@PropertySource("classpath:application.properties")
@Import({NavigationProperties.class,
        NotificationProperties.class,
        TemplateConfiguration.class,
        MessageEmailConfiguration.class,
        DocxGeneratorConfiguration.class,
        HttpRequestConfiguration.class,
        TokenConfiguration.class,
        RadApiClientConfiguration.class})
public class NotificationConfiguration {

    @Value("${notification.greetingTemplate}")
    private String notificationGreetingTemplate;

    @Value("${asv.curator.email}")
    private String asvCuratorEmail;

    @Value("${trust.curator.email}")
    private String trustCuratorEmail;

    @Value("${notification.payDoc.text}")
    private String notificationPayDocTextTemplate;

    @Value("${notification.payDoc.theme}")
    private String notificationPayDocThemeTemplate;

    @Value("${radius.path.to.files}")
    private String radiusPathToFiles;

    @Bean
    EventDao eventDao() {
        return new EventDao();
    }

    @Bean
    EventController eventController(EventDao eventDao) {
        return new EventController(eventDao);
    }

    @Bean
    NotificationDao notificationDao() {
        return new NotificationDao();
    }

    @Bean
    MessageEmailDao messageEmailDao() {
        return new MessageEmailDao();
    }

    @Bean
    AuctionNotificationController auctionNotificationController(EventDao eventDao,
                                                                NotificationDao notificationDao,
                                                                MessageEmailDao messageEmailDao) {
        return new AuctionNotificationController(eventDao, notificationDao, messageEmailDao);
    }

    @Bean
    EmailTextService emailTextService(ThymeleafTemplateHelper templateHelper) {
        return new EmailTextService(templateHelper, notificationGreetingTemplate, "");
    }

    @Bean
    CreateNotificationService createNotificationService(AuctionNotificationController auctionNotificationController,
                                                        ThymeleafTemplateHelper templateHelper,
                                                        EmailTextService emailTextService,
                                                        NotificationController notificationController) {
        return new CreateNotificationService(auctionNotificationController, templateHelper, emailTextService,
                asvCuratorEmail, trustCuratorEmail, notificationController);
    }

    @Bean
    EndReceiptApplicationsNotificationService endReceiptApplicationsNotificationService(AuctionNotificationController auctionNotificationController,
                                                                                        CreateNotificationService createNotificationService,
                                                                                        NotificationProperties notificationProperties) {
        return new EndReceiptApplicationsNotificationService(auctionNotificationController, createNotificationService,
                notificationProperties);
    }

    @Bean
    AsvNotificationJob asvEndReceiptApplicationsNotificationJob(EndReceiptApplicationsNotificationService endReceiptApplicationsNotificationService) {
        return new AsvNotificationJob(endReceiptApplicationsNotificationService);
    }

    @Bean
    BeforeStartAuctionNotificationService beforeStartAuctionNotificationService(AuctionNotificationController auctionNotificationController,
                                                                                CreateNotificationService createNotificationService,
                                                                                NotificationProperties notificationProperties) {
        return new BeforeStartAuctionNotificationService(auctionNotificationController, createNotificationService,
                notificationProperties);
    }

    @Bean
    AsvNotificationJob asvBeforeStartAuctionNotificationJob(BeforeStartAuctionNotificationService beforeStartAuctionNotificationService) {
        return new AsvNotificationJob(beforeStartAuctionNotificationService);
    }

    @Bean
    EndApplReductionPeriodNotificationService endApplReductionPeriodNotificationService(AuctionNotificationController auctionNotificationController,
                                                                                        CreateNotificationService createNotificationService,
                                                                                        NotificationProperties notificationProperties) {
        return new EndApplReductionPeriodNotificationService(auctionNotificationController, createNotificationService,
                notificationProperties);
    }

    @Bean
    AsvNotificationJob asvEndApplReductionPeriodNotificationJob(EndApplReductionPeriodNotificationService endApplReductionPeriodNotificationService) {
        return new AsvNotificationJob(endApplReductionPeriodNotificationService);
    }

    @Bean
    MessageAboutNewTenderService messageAboutNewTenderService(ThymeleafTemplateHelper templateHelper, MessageEmailController messageEmailController,
                                                              NotificationProperties notificationProperties, MessageEmailList messageEmailList) {
        return new MessageAboutNewTenderService(templateHelper, messageEmailController, notificationProperties, messageEmailList);
    }

    @Bean
    MessageEmailController messageEmailController(MessageEmailDao messageEmailDao) {
        return new MessageEmailController(messageEmailDao);
    }

    @Bean
    NotificationController notificationController(EmailTextService emailTextService) {
        return new NotificationController(emailTextService);
    }

    @Bean
    PayDocNotificationController payDocNotificationController() {
        return new PayDocNotificationController();
    }

    @Value("${paydoc.notification.docTemplateUnid}")
    private Long payDocDocTemplateUnid;

    @Bean
    PayDocNotificationService payDocNotificationService(ThymeleafTemplateHelper templateHelper,
                                                        PayDocNotificationController payDocNotificationController,
                                                        DocTemplateController docTemplateController,
                                                        CreatingDocumentService creatingDocumentService,
                                                        TokenService tokenService,
                                                        VitrinaClient vitrinaClient,
                                                        MailSenderClient mailSenderClient, RadApiClient radApiClient) {
        return new PayDocNotificationService(payDocNotificationController, docTemplateController, creatingDocumentService,
                tokenService, radApiClient, vitrinaClient, mailSenderClient, payDocDocTemplateUnid,
                templateHelper, notificationPayDocThemeTemplate, notificationPayDocTextTemplate, radiusPathToFiles);
    }

    @Bean
    MailSenderClient mailSenderClient(HttpRequestService vitrinaHttpRequestService) {
        return new MailSenderClient(vitrinaHttpRequestService);
    }

    @Bean
    PayDocNotificationJob payDocNotificationJob(PayDocNotificationService payDocNotificationService) {
        return new PayDocNotificationJob(payDocNotificationService);
    }
}
