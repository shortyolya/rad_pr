package com.baltinfo.radius.application.configuration;

import com.baltinfo.radius.bankruptcy.export.AdditionalPropertiesService;
import com.baltinfo.radius.bankruptcy.export.BkrFileService;
import com.baltinfo.radius.dadata.services.DadataService;
import com.baltinfo.radius.db.controller.*;
import com.baltinfo.radius.db.dao.*;
import com.baltinfo.radius.fias.client.FiasGarApiClient;
import com.baltinfo.radius.loadauction.service.JsonExportToAssetsService;
import com.baltinfo.radius.loadauction.service.LoadFileService;
import com.baltinfo.radius.loadauction.service.LoadJournalService;
import com.baltinfo.radius.lotonline.export.LotOnlineFileService;
import com.baltinfo.radius.lotonline.export.LotOnlineProtocolService;
import com.baltinfo.radius.notification.impl.AccountantNotificationService;
import com.baltinfo.radius.notification.impl.ManagerNotificationService;
import com.baltinfo.radius.radapi.security.TokenService;
import com.baltinfo.radius.rest.client.HttpRequestService;
import com.baltinfo.radius.subject.SubjectUtils;
import com.baltinfo.radius.template.ThymeleafTemplateHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Suvorina Aleksandra
 * @since 02.08.2019
 */
@Configuration
@PropertySource("classpath:application.properties")
@Import({LotOnlineServerProperties.class, DadataClientConfiguration.class, FiasClientConfiguration.class,
        HttpRequestConfiguration.class,
        NotificationConfiguration.class, PayDocProperties.class})
public class ControllerConfiguration {

    @Value("${radius.path.to.files}")
    private String radiusPathToFiles;
    @Value("${feed.photo.base.url}")
    private String feedPhotoBaseUrl;

    @Value("${lotonline.client.base.url}")
    private String lotonlineBaseUrl;
    @Value("${lotonline.client.timeout}")
    private Integer timeout;
    @Value("${lotonline.auth.jwt.token.subject}")
    private String lotonlineAuthJwtTokenSubjectJson;

    @Bean
    AuctionDao auctionDao() {
        return new AuctionDao();
    }

    @Bean
    LoadFileService loadFileService() {
        return new LoadFileService(radiusPathToFiles);
    }

    @Bean
    LoadAuctionDao loadAuctionDao(LotController lotController) {
        return new LoadAuctionDao(lotController);
    }

    @Bean
    ObjectJpaDao objectJpaDao() {
        return new ObjectJpaDao();
    }

    @Bean
    ObjectController objectController(ObjectJpaDao objectJpaDao) {
        return new ObjectController(objectJpaDao);
    }

    @Bean
    RateController rateController() {
        return new RateController();
    }

    @Bean
    LotDao lotDao() {
        return new LotDao();
    }

    @Bean
    LotsDao lotsDao() {
        return new LotsDao();
    }

    @Bean
    LoadJournalDao loadJournalDao() {
        return new LoadJournalDao();
    }

    @Bean
    BlockAuctionDao blockAuctionDao() {
        return new BlockAuctionDao();
    }

    @Bean
    LoadJournalController loadJournalController(LoadJournalDao loadJournalDao) {
        return new LoadJournalController(loadJournalDao);
    }

    @Bean
    LoadJournalService loadJournalService(LoadJournalController loadJournalController) {
        return new LoadJournalService(loadJournalController);
    }

    @Bean
    LoadAuctionController loadAuctionController(AuctionDao auctionDao, LoadAuctionDao loadAuctionDao,
                                                SubjectUtils subjectUtils, SubjectDao subjectDao,
                                                BlockAuctionDao blockAuctionDao) {
        return new LoadAuctionController(auctionDao, loadAuctionDao, subjectUtils, subjectDao, blockAuctionDao);
    }

    @Bean
    LotController lotController(LotDao lotDao, AuctionDao auctionDao, ObjectController objectController,
                                DadataService dadataService, JsonExportToAssetsService jsonExportToAssetsService,
                                DealController dealController, PropertyValueController propertyValueController, ObjRoleDao objRoleDao) {
        return new LotController(lotDao, auctionDao, objectController, dadataService, jsonExportToAssetsService, dealController, propertyValueController, objRoleDao);
    }
    @Bean
    LotsController lotsController(LotsDao lotsDao) {
        return new LotsController(lotsDao);
    }

    @Bean
    ExchangeProcRunDao exchangeProcRunDao() {
        return new ExchangeProcRunDao();
    }

    @Bean
    RunningDetailsDao runningDetailsDao() {
        return new RunningDetailsDao();
    }

    @Bean
    ExchangeProcDao exchangeProcDao() {
        return new ExchangeProcDao();
    }

    @Bean
    ExchangeProcController exchangeProcController(ExchangeProcRunDao exchangeProcRunDao,
                                                  RunningDetailsDao runningDetailsDao,
                                                  ExchangeProcDao exchangeProcDao) {
        return new ExchangeProcController(exchangeProcRunDao, runningDetailsDao, exchangeProcDao);
    }

    @Bean
    BkrExchangeUnloadController bankruptcyController(AdditionalPropertiesService additionalPropertiesService,
                                                     BkrFileService bkrFileService) {
        return new BkrExchangeUnloadController(additionalPropertiesService, bkrFileService);
    }

    @Bean
    OperJournalController operJournalController() {
        return new OperJournalController();
    }

    @Bean
    SubjectController subjectController() {
        return new SubjectController();
    }

    @Bean
    SafetyReceiptController safetyReceiptController() {
        return new SafetyReceiptController();
    }

    @Bean
    SubjectUtils subjectUtils(SubjectController subjectController) {
        return new SubjectUtils(subjectController);
    }

    @Bean
    DealController dealController() {
        return new DealController();
    }

    @Bean
    ObjRoleDao objRoleDao() {
        return new ObjRoleDao();
    }


    @Bean
    RewardDao rewardDao() {
        return new RewardDao();
    }

    @Bean
    ActDao actDao() {
        return new ActDao();
    }

    @Bean
    SubjectDao subjectDao() {
        return new SubjectDao();
    }

    @Bean
    RewardController rewardController(RewardDao rewardDao, ActDao actDao, DealController dealController) {
        return new RewardController(rewardDao, actDao, dealController);
    }

    @Bean
    VitrinaController vitrinaController() {
        return new VitrinaController();
    }

    @Bean
    BlockAuctionController blockAuctionController(BlockAuctionDao blockAuctionDao) {
        return new BlockAuctionController(blockAuctionDao);
    }

    @Bean
    AccessProfileController accessProfileController() {
        return new AccessProfileController();
    }

    @Value("${notification.tradeResults.theme}")
    private String tradeResultNotificationThemeTemplate;

    @Value("${notification.tradeResults.template}")
    private String tradeResultNotificationTextTemplate;

    @Bean
    AccountantNotificationService accountantNotificationService(AccessProfileController accessProfileController,
                                                                ThymeleafTemplateHelper templateHelper,
                                                                NotificationController notificationController) {
        return new AccountantNotificationService(accessProfileController, templateHelper, tradeResultNotificationThemeTemplate,
                tradeResultNotificationTextTemplate, notificationController);
    }

    @Value("${notification.tradePublished.theme}")
    private String tradePublishedNotificationThemeTemplate;

    @Value("${notification.tradePublished.template}")
    private String tradePublishedNotificationTextTemplate;

    @Bean
    ManagerNotificationService managerNotificationService(AccessProfileController accessProfileController,
                                                          ThymeleafTemplateHelper templateHelper,
                                                          NotificationController notificationController,
                                                          AuctionController auctionController,
                                                          LotController lotController,
                                                          BlockAuctionController blockAuctionController) {
        return new ManagerNotificationService(accessProfileController, templateHelper, tradePublishedNotificationThemeTemplate,
                tradePublishedNotificationTextTemplate, notificationController, auctionController, lotController, blockAuctionController);
    }

    @Bean
    BkrExchangeResultsController bkrExchangeResultsController(SubjectUtils subjectUtils,
                                                              SubjectDao subjectDao,
                                                              LotDao lotDao,
                                                              OperJournalController operJournalController,
                                                              DealController dealController,
                                                              ObjRoleDao objRoleDao,
                                                              AdditionalPropertiesService additionalPropertiesService,
                                                              RewardController rewardController,
                                                              VitrinaController vitrinaController,
                                                              DadataService dadataService,
                                                              BkrFileService bkrFileService, RateController rateController,
                                                              AccountantNotificationService accountantNotificationService) {
        return new BkrExchangeResultsController(subjectUtils, subjectDao, lotDao, operJournalController,
                radiusPathToFiles, dealController, objRoleDao, additionalPropertiesService, rewardController,
                vitrinaController, dadataService, bkrFileService, rateController, accountantNotificationService);
    }

    @Bean
    LotOnlineFileService lotOnlineFileService(LotOnlineServerProperties lotOnlineServerProperties) {
        return new LotOnlineFileService(lotOnlineServerProperties);
    }

    @Bean
    LotOnlineProtocolService lotOnlineProtocolService() {
        return new LotOnlineProtocolService(lotonlineBaseUrl, timeout);
    }

    @Bean
    BkrFileService bkrFileService(HttpRequestService httpRequestService) {
        return new BkrFileService(httpRequestService);
    }

    @Bean
    LotOnlineExchangeResultsController lotOnlineExchangeResultsController(SubjectUtils subjectUtils,
                                                                          SubjectDao subjectDao,
                                                                          OperJournalController operJournalController,
                                                                          LotOnlineProtocolService lotOnlineProtocolService,
                                                                          DealController dealController,
                                                                          ObjRoleDao objRoleDao,
                                                                          RewardController rewardController,
                                                                          AdditionalPropertiesService additionalPropertiesService,
                                                                          RateController rateController,
                                                                          AccountantNotificationService accountantNotificationService,
                                                                          DadataService dadataService,
                                                                          VitrinaController vitrinaController,
                                                                          TokenService tokenService
                                                                          ) {
        return new LotOnlineExchangeResultsController(subjectUtils, subjectDao, operJournalController,
                radiusPathToFiles, lotOnlineProtocolService, dealController, objRoleDao, rewardController,
                additionalPropertiesService, rateController, accountantNotificationService, dadataService, vitrinaController, tokenService, lotonlineAuthJwtTokenSubjectJson);
    }

    @Bean
    LotOnlineImageUtils lotOnlineImageUtils() {
        return new LotOnlineImageUtils();
    }

    @Bean
    LotOnlineExchangeUnloadController lotOnlineExchangeUnloadController(LotOnlineFileService lotOnlineFileService,
                                                                        LotOnlineImageUtils lotOnlineImageUtils,
                                                                        FiasGarApiClient fiasGarApiClient,
                                                                        AdditionalPropertiesService additionalPropertiesService) {
        return new LotOnlineExchangeUnloadController(lotOnlineFileService, lotOnlineImageUtils, fiasGarApiClient, additionalPropertiesService);
    }

    @Bean
    ParticipantAgentController participantAgentController() {
        return new ParticipantAgentController();
    }

    @Bean
    CategoryMatcherController categoryMatcherController() {
        return new CategoryMatcherController();
    }

    @Bean
    FeedAdDao feedAdDao() {
        return new FeedAdDao();
    }

    @Bean
    FeedAdObjDao feedAdObjDao() {
        return new FeedAdObjDao();
    }

    @Bean
    JsonExportToAssetsService jsonExportToAssetsService() {
        return new JsonExportToAssetsService();
    }

    @Bean
    FeedAdController feedAdController(FeedAdDao feedAdDao, FeedAdObjDao feedAdObjDao) {
        return new FeedAdController(feedAdDao, feedAdObjDao);
    }

    @Bean
    VLoadJournalController vLoadJournalController() {
        return new VLoadJournalController();
    }

    @Bean
    AuctionController auctionController(LotController lotController, AuctionDao auctionDao, OperJournalController operJournalController) {
        return new AuctionController(lotController, auctionDao, operJournalController);
    }

    @Bean
    ExportCodesFromBkrController exportCodesFromBkrController() {
        return new ExportCodesFromBkrController();
    }

    @Bean
    DocFileDao docFileDao() {
        return new DocFileDao();
    }

    @Bean
    DocFileController docFileController(DocFileDao docFileDao) {
        return new DocFileController(docFileDao);
    }

    @Bean
    ExportCodesFromLOController exportCodesFromLOController() {
        return new ExportCodesFromLOController();
    }

    @Bean
    PictureController pictureController() {
        return new PictureController();
    }

    @Bean
    AllowObjPropDao allowObjPropDao() {
        return new AllowObjPropDao();
    }

    @Bean
    AllowPropValueDao allowPropValueDao() {
        return new AllowPropValueDao();
    }

    @Bean
    AllowObjPropController allowObjPropController(AllowObjPropDao allowObjPropDao, AllowPropValueDao allowPropValueDao) {
        return new AllowObjPropController(allowObjPropDao, allowPropValueDao);
    }

    @Bean
    AllowPropValueController allowPropValueController(AllowPropValueDao allowPropValueDao) {
        return new AllowPropValueController(allowPropValueDao);
    }

    @Bean
    TruckAllowObjPropController truckAllowObjPropController(AllowObjPropDao allowObjPropDao, AllowPropValueDao allowPropValueDao) {
        return new TruckAllowObjPropController(allowObjPropDao, allowPropValueDao);
    }

    @Bean
    BkrCounterController bkrCounterController() {
        return new BkrCounterController();
    }

    @Bean
    AdditionalPropertiesService additionalPropertiesService(PropertyValueController propertyValueController,
                                                            ObjPropertyController objPropertyController,
                                                            PropertyGroupController propertyGroupController,
                                                            LotController lotController) {
        return new AdditionalPropertiesService(propertyValueController, objPropertyController, propertyGroupController, lotController);
    }

    @Bean
    PropertyValueDao propertyValueDao() {
        return new PropertyValueDao();
    }

    @Bean
    PropertyValueController propertyValueController(PropertyValueDao propertyValueDao) {
        return new PropertyValueController(propertyValueDao);
    }

    @Bean
    PropertyGroupDao propertyGroupDao() {
        return new PropertyGroupDao();
    }

    @Bean
    PropertyGroupController propertyGroupController(PropertyGroupDao propertyGroupDao) {
        return new PropertyGroupController(propertyGroupDao);
    }

    @Bean
    ObjPropertyDao objPropertyDao() {
        return new ObjPropertyDao();
    }

    @Bean
    ObjPropertyController objPropertyController(ObjPropertyDao objPropertyDao) {
        return new ObjPropertyController(objPropertyDao);
    }

    @Bean
    SaleCategoryController saleCategoryController() {
        return new SaleCategoryController();
    }

    @Bean
    BkrRubricatorController bkrRubricatorController() {
        return new BkrRubricatorController();
    }

    @Bean
    LOCategoryController loCategoryController() {
        return new LOCategoryController();
    }

    @Bean
    AdStatisticsDao adStatisticsDao() {
        return new AdStatisticsDao();
    }

    @Bean
    AdStatisticsController adStatisticsController(AdStatisticsDao adStatisticsDao) {
        return new AdStatisticsController(adStatisticsDao);
    }

    @Bean
    LotInfoController lotInfoController() {
        return new LotInfoController();
    }

    @Bean
    ExplanationResponseDao explanationResponseDao() {
        return new ExplanationResponseDao();
    }

    @Bean
    ExplanationResponseController explanationResponseController(ExplanationResponseDao explanationResponseDao) {
        return new ExplanationResponseController(explanationResponseDao);
    }

    @Bean
    MapRegionController mapRegionController() {
        return new MapRegionController();
    }

    @Bean
    LotPublishedNotificationController lotPublishedNotificationController() {
        return new LotPublishedNotificationController();
    }

    @Bean
    AccountDepositController accountDepositController() {
        return new AccountDepositController();
    }

    @Bean
    ClDateController clDateController() {
        return new ClDateController();
    }

    @Bean
    BkrDepController bkrDepController(AccountDepositController accountDepositController, ClDateController clDateController) {
        return new BkrDepController(accountDepositController, clDateController);
    }

    @Bean
    LoDepController loDepController(AccountDepositController accountDepositController, ClDateController clDateController,
                                    PayDocProperties payDocProperties) {
        return new LoDepController(accountDepositController, clDateController, payDocProperties);
    }

}
