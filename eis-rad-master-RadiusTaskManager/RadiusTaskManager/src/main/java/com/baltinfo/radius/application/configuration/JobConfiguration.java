package com.baltinfo.radius.application.configuration;

import com.baltinfo.radius.avito.job.AvitoClassifierJob;
import com.baltinfo.radius.avito.job.AvitoDevelopmentClassifierJob;
import com.baltinfo.radius.avito.job.AvitoTruckClassifierJob;
import com.baltinfo.radius.avito.service.AvitoClassificatorService;
import com.baltinfo.radius.avito.service.AvitoDevelopmentClassificatorService;
import com.baltinfo.radius.avito.service.AvitoTruckClassificatorService;
import com.baltinfo.radius.bankruptcy.BkrLotParams;
import com.baltinfo.radius.bankruptcy.categories.SynchronizeCategoriesFromEisToBkrService;
import com.baltinfo.radius.bankruptcy.check.BkrCheckResultsJob;
import com.baltinfo.radius.bankruptcy.check.BkrCheckResultsService;
import com.baltinfo.radius.bankruptcy.codes.ExportCodesFromBkrToRadJob;
import com.baltinfo.radius.bankruptcy.codes.ExportCodesFromBkrToRadService;
import com.baltinfo.radius.bankruptcy.counter.BkrReceiveLotCounterJob;
import com.baltinfo.radius.bankruptcy.counter.BkrReceiveLotCounterService;
import com.baltinfo.radius.bankruptcy.dates.UpdateDatesFromBkrToRadJob;
import com.baltinfo.radius.bankruptcy.dates.UpdateDatesFromBkrToRadService;
import com.baltinfo.radius.bankruptcy.export.BkrExchangeParamsBuilder;
import com.baltinfo.radius.bankruptcy.export.BkrOuterLinkService;
import com.baltinfo.radius.bankruptcy.export.ExportToBankruptcyJob;
import com.baltinfo.radius.bankruptcy.results.BkrReceiveResultsJob;
import com.baltinfo.radius.bankruptcy.results.BkrReceiveResultsService;
import com.baltinfo.radius.bankruptcy.status.CheckResumedAuctionFromBkrToRadService;
import com.baltinfo.radius.bankruptcy.status.SynchroniseStatusFromBkrToRadJob;
import com.baltinfo.radius.bankruptcy.status.CheckSuspendedAuctionFromBkrToRadService;
import com.baltinfo.radius.blockAuction.UpdateDataJob;
import com.baltinfo.radius.blockAuction.UpdateDataService;
import com.baltinfo.radius.calls.client.CallsApiClient;
import com.baltinfo.radius.categories.SynchronizeCategoriesJob;
import com.baltinfo.radius.categories.SynchronizeCategoriesService;
import com.baltinfo.radius.db.constants.AvitoCategoryCode;
import com.baltinfo.radius.db.constants.CianCategoryCode;
import com.baltinfo.radius.db.constants.MarketingEvent;
import com.baltinfo.radius.db.constants.Marketplaces;
import com.baltinfo.radius.db.controller.*;
import com.baltinfo.radius.db.model.FeedObject;
import com.baltinfo.radius.db.model.bankruptcy.VAuctionBkr;
import com.baltinfo.radius.db.model.lotonline.Tender;
import com.baltinfo.radius.dep.exchange.DepExchangeJob;
import com.baltinfo.radius.dep.exchange.DepExchangeService;
import com.baltinfo.radius.documents.CreatingDocumentJob;
import com.baltinfo.radius.documents.generator.CreatingDocumentService;
import com.baltinfo.radius.exchange.ExchangeParamsBuilder;
import com.baltinfo.radius.exchange.impl.ExportToEtpService;
import com.baltinfo.radius.explanationresponse.ExplanationResponseJob;
import com.baltinfo.radius.explanationresponse.ExplanationResponseService;
import com.baltinfo.radius.feed.archive.DeleteArchiveFeedJob;
import com.baltinfo.radius.feed.archive.DeleteArchiveFeedService;
import com.baltinfo.radius.feed.avito.api.client.AvitoApiClient;
import com.baltinfo.radius.feed.avito.converter.AvitoConverter;
import com.baltinfo.radius.feed.cian.api.client.CianApiClient;
import com.baltinfo.radius.feed.cian.converter.CianConverter;
import com.baltinfo.radius.feed.ftp.FeedFtpService;
import com.baltinfo.radius.feed.jcat.api.client.JCatApiClient;
import com.baltinfo.radius.feed.job.AvitoServiceJob;
import com.baltinfo.radius.feed.job.CianCommercialServiceJob;
import com.baltinfo.radius.feed.job.DeleteNonActualFeedFilesFromFtpJob;
import com.baltinfo.radius.feed.job.ExportPhotoToFtpJob;
import com.baltinfo.radius.feed.job.JCatServiceJob;
import com.baltinfo.radius.feed.services.ExportFeedPhotoToFtp;
import com.baltinfo.radius.feed.services.FeedAllCategoriesService;
import com.baltinfo.radius.feed.services.FeedByCategoryService;
import com.baltinfo.radius.feed.services.FeedGroupCategoriesService;
import com.baltinfo.radius.feed.services.FeedService;
import com.baltinfo.radius.feed.services.impl.AvitoFeedXmlService;
import com.baltinfo.radius.feed.services.impl.CianFeedXmlService;
import com.baltinfo.radius.feed.statistic.AdStatisticService;
import com.baltinfo.radius.feed.statistic.AvitoAdStatisticsJob;
import com.baltinfo.radius.feed.statistic.AvitoAdStatisticsService;
import com.baltinfo.radius.feed.statistic.CianAdStatisticsJob;
import com.baltinfo.radius.feed.statistic.CianAdStatisticsService;
import com.baltinfo.radius.feed.statistic.FeedStatisticsService;
import com.baltinfo.radius.feed.statistic.JCatAdStatisticsService;
import com.baltinfo.radius.feed.statistic.JcatAdStatisticJob;
import com.baltinfo.radius.job.ExportLotsFromTempToDbJob;
import com.baltinfo.radius.job.FillCallsCountToTrustLotsJob;
import com.baltinfo.radius.job.XlsExportToTempJob;
import com.baltinfo.radius.links.check.CheckLinksJob;
import com.baltinfo.radius.links.check.CheckLinksService;
import com.baltinfo.radius.links.results.SynchronizeLinksJob;
import com.baltinfo.radius.links.results.SynchronizeLinksService;
import com.baltinfo.radius.loadauction.ftp.FileStorage;
import com.baltinfo.radius.loadauction.ftp.FileSystemClient;
import com.baltinfo.radius.loadauction.ftp.FileSystemService;
import com.baltinfo.radius.loadauction.ftp.FtpClient;
import com.baltinfo.radius.loadauction.ftp.FtpService;
import com.baltinfo.radius.loadauction.job.ExportFromFtpToDbJob;
import com.baltinfo.radius.loadauction.job.SendAsvTenderFileToFtpJob;
import com.baltinfo.radius.loadauction.job.SendResultsToFtpJob;
import com.baltinfo.radius.loadauction.service.AsvResultService;
import com.baltinfo.radius.loadauction.service.ExportDtoToDb;
import com.baltinfo.radius.loadauction.service.JsonExportService;
import com.baltinfo.radius.loadauction.service.JsonExportToAssetsService;
import com.baltinfo.radius.loadauction.service.LoadFileService;
import com.baltinfo.radius.loadauction.service.LoadJournalService;
import com.baltinfo.radius.loadauction.service.MessageAboutNewTenderService;
import com.baltinfo.radius.loadauction.service.PublicationConverterService;
import com.baltinfo.radius.loadauction.service.SendResultsToFtpService;
import com.baltinfo.radius.loadauction.service.XmlExportService;
import com.baltinfo.radius.lotonline.categories.LoCategoriesClient;
import com.baltinfo.radius.lotonline.categories.SynchronizeCategoriesFromEisToLOService;
import com.baltinfo.radius.lotonline.check.LotOnlineCheckResultsJob;
import com.baltinfo.radius.lotonline.check.LotOnlineCheckResultsService;
import com.baltinfo.radius.lotonline.codes.ExportCodesFromLOToRadJob;
import com.baltinfo.radius.lotonline.codes.ExportCodesFromLOToRadService;
import com.baltinfo.radius.lotonline.dates.UpdateDatesFromLotOnlineToRadJob;
import com.baltinfo.radius.lotonline.dates.UpdateDatesFromLotOnlineToRadService;
import com.baltinfo.radius.lotonline.export.ExportToLotOnlineJob;
import com.baltinfo.radius.lotonline.export.LotOnlineExchangeParamsBuilder;
import com.baltinfo.radius.lotonline.export.LotOnlineLotParams;
import com.baltinfo.radius.lotonline.export.LotOnlineOuterLinkService;
import com.baltinfo.radius.lotonline.results.LotOnlineReceiveResultsJob;
import com.baltinfo.radius.lotonline.results.LotOnlineReceiveResultsService;
import com.baltinfo.radius.message.CreateEmailMessageService;
import com.baltinfo.radius.message.EmailMessageJob;
import com.baltinfo.radius.message.EmailMessageService;
import com.baltinfo.radius.message.impl.ReceiveInfoMessageEmailMessageService;
import com.baltinfo.radius.notification.EmailTextService;
import com.baltinfo.radius.notification.LotPublishedNotificationJob;
import com.baltinfo.radius.notification.impl.LotPublishedNotificationService;
import com.baltinfo.radius.notification.impl.ManagerNotificationService;
import com.baltinfo.radius.radapi.client.RadApiClient;
import com.baltinfo.radius.radapi.security.TokenService;
import com.baltinfo.radius.safetyReceipt.SequenceResetJob;
import com.baltinfo.radius.safetyReceipt.SequenceResetService;
import com.baltinfo.radius.template.TemplateHelper;
import com.baltinfo.radius.utils.EisFileService;
import com.baltinfo.radius.vitrina.UpdateVitrinaUrlJob;
import com.baltinfo.radius.vitrina.UpdateVitrinaUrlService;
import com.baltinfo.radius.vitrina.VitrinaOuterLinkService;
import com.baltinfo.radius.xls.LoadLotService;
import com.baltinfo.radius.xls.XlsExportService;
import com.baltinfo.radius.yandex.client.YandexMetrikaApiClient;
import com.baltinfo.radius.yandex.job.UpdateStatisticJob;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Конфигурация приложения
 * </p>
 *
 * @author Lapenok Igor
 * @since 15.08.2018
 */
@Configuration
@EnableScheduling
@PropertySource("classpath:application.properties")
@PropertySource("classpath:notification.properties")
@Import({SchedulingConfigurerConfiguration.class, OuterLinkProperties.class, NotificationConfiguration.class,
        ControllerConfiguration.class, FtpServerConfiguration.class, EmailMessageProperties.class,
        FeedFtpServerConfiguration.class, FeedMappingConfiguration.class, FiasConfiguration.class,
        LotonlineClientConfiguration.class, AvitoApiClientConfiguration.class, CianApiClientConfiguration.class,
        JCatApiClientConfiguration.class, UnirestConfiguration.class, DocxGeneratorConfiguration.class, YandexApiConfiguration.class,
        RadApiClientConfiguration.class, TokenConfiguration.class, CallsApiClientConfiguration.class, AuctionHouseLinksProperties.class})
public class JobConfiguration {

    @Value("${xls.template.version.asv}")
    private String asvTemplateVersion;

    @Value("${xls.template.version.private.property}")
    private String privatePropertyTemplateVersion;

    @Value("${xls.template.version.sberbank.leasing}")
    private String sberbankLeasingVersion;

    @Value("${radius.path.to.files}")
    private String radiusPathToFiles;

    @Value("${xls.template.path}")
    private String xlsTemplatePath;

    @Value("${asv.path.to.files}")
    private String asvPathToFiles;

    @Value("${asv.path.to.result.files}")
    private String asvPathToResultFiles;

    @Value("${asv.path.to.rad.files}")
    private String asvPathToRadFiles;


    @Value("${xml.source.ftp}")
    private Boolean parseFromFtp;

    @Value("${radius.logo.path}")
    private String logoPath;

    @Value("${ftp.tpa.unid}")
    private Long ftpTpaUnid;

    @Value("${ftp.new.file.format}")
    private Boolean isNewFileFormat;

    @Value("${avito.auto.classificators.url}")
    private String avitoAutoUrl;

    @Value("${avito.truck.classificators.url}")
    private String avitoTruckUrl;

    @Value("${avito.development.classificators.url}")
    private String avitoDevelopmentUrl;

    @Value("${feed.photo.base.url}")
    private String feedPhotoBaseUrl;

    @Value("${exchange.lotonline.max.picture.count}")
    private Integer exchangeLotOnlineMaxPictureCount;

    @Value("${yandex.api.metrika.counterId.catalogLO}")
    private String counterIdCatalogLO;

    @Value("${yandex.api.metrika.counterId.auctionHouse}")
    private String counterIdAuctionHouse;

    @Value("${yandex.api.metrika.counterId.pledgeLO}")
    private String counterIdPledgeLO;

    @Bean
    LoadLotService loadLotService() {
        return new LoadLotService();
    }

    @Bean
    XlsExportService xlsExportService(LoadLotService loadLotService) {
        return new XlsExportService(asvTemplateVersion, privatePropertyTemplateVersion, sberbankLeasingVersion, loadLotService);
    }

    @Bean
    XlsExportToTempJob xlsExportToTempJob(XlsExportService xlsExportService,
                                          LoadAuctionController loadAuctionController) {
        return new XlsExportToTempJob(xlsExportService, loadAuctionController, xlsTemplatePath);
    }

    @Bean
    ExportLotsFromTempToDbJob exportLotsFromTempToDbJob(LoadAuctionController loadAuctionController,
                                                        LotController lotController, LoadJournalService loadJournalService,
                                                        MessageAboutNewTenderService messageAboutNewTenderService,
                                                        VLoadJournalController vLoadJournalController) {
        return new ExportLotsFromTempToDbJob(loadAuctionController, lotController, loadJournalService, messageAboutNewTenderService,
                vLoadJournalController);
    }

    @Bean
    XmlExportService xmlExportService() {
        return new XmlExportService();
    }

    @Bean
    FileSystemClient fileSystemClient() {
        return new FileSystemClient();
    }

    @Bean
    FileStorage fileStorage(FtpClient ftpClient, FileSystemClient fileSystemClient, LoadAuctionController loadAuctionController, LoadFileService loadFileService) {
        if (parseFromFtp) {
            return new FtpService(ftpClient, loadFileService, loadAuctionController,
                    asvPathToFiles, radiusPathToFiles, asvPathToResultFiles, asvPathToRadFiles, isNewFileFormat);
        } else {
            return new FileSystemService(fileSystemClient, loadFileService, loadAuctionController,
                    asvPathToFiles, radiusPathToFiles, asvPathToResultFiles, asvPathToRadFiles, isNewFileFormat);
        }
    }

    @Bean
    SendAsvTenderFileToFtpJob sendAsvTenderFileToFtpJob(FileStorage fileStorage, LoadAuctionController loadAuctionController,
                                                        ExchangeProcController exchangeProcController) {
        return new SendAsvTenderFileToFtpJob(fileStorage, loadAuctionController, exchangeProcController);
    }

    @Bean
    PublicationConverterService publicationConverterService(FileStorage fileStorage, LotController lotController) {
        return new PublicationConverterService(ftpTpaUnid, fileStorage, lotController);
    }

    @Bean
    JsonExportService jsonExportService() {
        return new JsonExportService();
    }

    @Bean
    ExportDtoToDb exportDtoToTempDb(XmlExportService xmlExportService, JsonExportService jsonExportService,
                                    LoadAuctionController loadAuctionController,
                                    PublicationConverterService publicationConverterService,
                                    LoadJournalController loadJournalController) {
        return new ExportDtoToDb(xmlExportService, jsonExportService, loadAuctionController, publicationConverterService, loadJournalController);
    }

    @Bean
    ExportFromFtpToDbJob exportFromFtpToDbJob(ExportDtoToDb exportDtoToTempDb, FileStorage fileStorage) {
        return new ExportFromFtpToDbJob(exportDtoToTempDb, fileStorage);
    }

    @Bean
    BkrOuterLinkService bkrOuterLinkService(OuterLinkProperties outerLinkProperties) {
        return new BkrOuterLinkService(outerLinkProperties);
    }

    @Bean
    ExchangeParamsBuilder<BkrLotParams> bkrExchangeParamsBuilder(LotController lotController,
                                                                 ParticipantAgentController participantAgentController) {
        return new BkrExchangeParamsBuilder(lotController, participantAgentController, radiusPathToFiles, logoPath);
    }

    @Bean
    ExportToEtpService<VAuctionBkr, BkrLotParams> exportToBankruptcyService(ExchangeProcController exchangeProcController,
                                                                            BkrExchangeUnloadController bankruptcyController,
                                                                            LotController lotController,
                                                                            OperJournalController operJournalController,
                                                                            BkrOuterLinkService bkrOuterLinkService,
                                                                            ExchangeParamsBuilder<BkrLotParams> bkrExchangeParamsBuilder) {
        return new ExportToEtpService<>(exchangeProcController, bankruptcyController, lotController,
                operJournalController, bkrOuterLinkService, bkrExchangeParamsBuilder, Marketplaces.BANKRUPTCY.getMpUnid());
    }

    @Bean
    ExportToBankruptcyJob exportToBankruptcyJob(ExchangeProcController exchangeProcController,
                                                ExportToEtpService<VAuctionBkr, BkrLotParams> exportToBankruptcyService) {
        return new ExportToBankruptcyJob(exchangeProcController, exportToBankruptcyService);
    }

    @Bean
    EisFileService eisFileService() {
        return new EisFileService(radiusPathToFiles);
    }

    @Bean
    ExchangeParamsBuilder<LotOnlineLotParams> lotOnlineExchangeParamsBuilder(LotController lotController,
                                                                             ParticipantAgentController participantAgentController,
                                                                             EisFileService eisFileService) {
        return new LotOnlineExchangeParamsBuilder(lotController, exchangeLotOnlineMaxPictureCount,
                participantAgentController, eisFileService);
    }

    @Bean
    LotOnlineOuterLinkService lotOnlineOuterLinkService(OuterLinkProperties outerLinkProperties,
                                                        LotInfoController lotInfoController) {
        return new LotOnlineOuterLinkService(outerLinkProperties, lotInfoController);
    }

    @Bean
    ExportToEtpService<Tender, LotOnlineLotParams> exportToLotOnlineService(ExchangeProcController exchangeProcController,
                                                                            LotOnlineExchangeUnloadController lotOnlineExchangeUnloadController,
                                                                            LotController lotController,
                                                                            OperJournalController operJournalController,
                                                                            LotOnlineOuterLinkService lotOnlineOuterLinkService,
                                                                            ExchangeParamsBuilder<LotOnlineLotParams> lotOnlineExchangeParamsBuilder) {
        return new ExportToEtpService<>(exchangeProcController,
                lotOnlineExchangeUnloadController,
                lotController,
                operJournalController,
                lotOnlineOuterLinkService,
                lotOnlineExchangeParamsBuilder,
                Marketplaces.LOT_ONLINE.getMpUnid());
    }

    @Bean
    ExportToLotOnlineJob exportToLotOnlineJob(ExchangeProcController exchangeProcController,
                                              ExportToEtpService<Tender, LotOnlineLotParams> exportToLotOnlineService) {
        return new ExportToLotOnlineJob(exchangeProcController, exportToLotOnlineService);
    }

    @Bean
    BkrReceiveResultsService bkrReceiveResultsService(ExchangeProcController exchangeProcController,
                                                      BkrExchangeResultsController bkrExchangeResultsController,
                                                      LotController lotController) {
        return new BkrReceiveResultsService(exchangeProcController, bkrExchangeResultsController, lotController);
    }

    @Bean
    BkrReceiveResultsJob bkrReceiveResultsJob(ExchangeProcController exchangeProcController,
                                              BkrReceiveResultsService bkrReceiveResultsService) {
        return new BkrReceiveResultsJob(exchangeProcController, bkrReceiveResultsService);
    }

    @Bean
    BkrCheckResultsService bkrCheckResultsService(ExchangeProcController exchangeProcController,
                                                  BkrExchangeResultsController bkrExchangeResultsController,
                                                  LotController lotController,
                                                  AuctionController auctionController) {
        return new BkrCheckResultsService(exchangeProcController, bkrExchangeResultsController,
                lotController, auctionController);
    }

    @Bean
    BkrCheckResultsJob bkrCheckResultsJob(BkrCheckResultsService bkrCheckResultsService) {
        return new BkrCheckResultsJob(bkrCheckResultsService);
    }

    @Bean
    LotOnlineReceiveResultsService lotOnlineReceiveResultsService(ExchangeProcController exchangeProcController,
                                                                  LotOnlineExchangeResultsController lotOnlineExchangeResultsController,
                                                                  LotController lotController) {
        return new LotOnlineReceiveResultsService(exchangeProcController, lotOnlineExchangeResultsController, lotController);
    }

    @Bean
    LotOnlineReceiveResultsJob lotOnlineReceiveResultsJob(ExchangeProcController exchangeProcController,
                                                          LotOnlineReceiveResultsService lotOnlineReceiveResultsService) {
        return new LotOnlineReceiveResultsJob(exchangeProcController, lotOnlineReceiveResultsService);
    }

    @Bean
    LotOnlineCheckResultsService lotOnlineCheckResultsService(ExchangeProcController exchangeProcController,
                                                              LotOnlineExchangeResultsController lotOnlineExchangeResultsController,
                                                              LotController lotController,
                                                              AuctionController auctionController) {
        return new LotOnlineCheckResultsService(exchangeProcController, lotOnlineExchangeResultsController,
                lotController, auctionController);
    }

    @Bean
    LotOnlineCheckResultsJob lotOnlineCheckResultsJob(LotOnlineCheckResultsService lotOnlineCheckResultsService) {
        return new LotOnlineCheckResultsJob(lotOnlineCheckResultsService);
    }

    @Bean
    ExportFeedPhotoToFtp exportFeedPhotoToFtp(PictureController pictureController,
                                              ObjectController objectController,
                                              FeedFtpService feedFtpService,
                                              ExchangeProcController exchangeProcController) {
        return new ExportFeedPhotoToFtp(pictureController, objectController, feedFtpService, exchangeProcController);
    }

    @Bean
    ExportPhotoToFtpJob exportPhotoToFtpJob(ExportFeedPhotoToFtp exportFeedPhotoToFtp, ExchangeProcController exchangeProcController) {
        return new ExportPhotoToFtpJob(exportFeedPhotoToFtp, exchangeProcController);
    }

    @Bean
    DeleteNonActualFeedFilesFromFtpJob deleteNonActualFeedFilesFromFtpJob(ExportFeedPhotoToFtp exportFeedPhotoToFtp) {
        return new DeleteNonActualFeedFilesFromFtpJob(exportFeedPhotoToFtp);
    }

    @Bean
    CianFeedXmlService cianFeedXmlService(Map<CianCategoryCode, CianController<? extends FeedObject>> cianFeedControllers,
                                          Map<CianCategoryCode, CianConverter> cianFeedConverters,
                                          PictureController pictureController) {
        return new CianFeedXmlService(cianFeedControllers, cianFeedConverters, pictureController, feedPhotoBaseUrl);
    }

    @Bean
    AvitoFeedXmlService avitoFeedXmlService(Map<AvitoCategoryCode, AvitoController<? extends FeedObject>> avitoFeedControllers,
                                            Map<AvitoCategoryCode, AvitoConverter> avitoFeedConverters,
                                            PictureController pictureController) {
        return new AvitoFeedXmlService(avitoFeedControllers, avitoFeedConverters, pictureController, feedPhotoBaseUrl);
    }

    @Bean
    FeedByCategoryService cianFeedService(CategoryMatcherController categoryMatcherController,
                                          FeedAdController feedAdController,
                                          CianFeedXmlService cianFeedXmlService) {
        return new FeedByCategoryService(categoryMatcherController, feedAdController, cianFeedXmlService);
    }

    @Bean
    FeedAllCategoriesService jcatFeedService(CategoryMatcherController categoryMatcherController,
                                             FeedAdController feedAdController,
                                             AvitoFeedXmlService avitoFeedXmlService) {
        return new FeedAllCategoriesService(categoryMatcherController, feedAdController, avitoFeedXmlService);
    }

    @Bean
    FeedGroupCategoriesService avitoFeedService(CategoryMatcherController categoryMatcherController,
                                                FeedAdController feedAdController,
                                                AvitoFeedXmlService avitoFeedXmlService) {
        return new FeedGroupCategoriesService(categoryMatcherController, feedAdController, avitoFeedXmlService);
    }

    @Bean
    CianCommercialServiceJob commercialServiceJob(FeedByCategoryService cianFeedService) {
        return new CianCommercialServiceJob(cianFeedService);
    }

    @Bean
    AvitoServiceJob avitoServiceJob(FeedService avitoFeedService) {
        return new AvitoServiceJob(avitoFeedService);
    }

    @Bean
    JCatServiceJob jCatServiceJob(FeedService jcatFeedService) {
        return new JCatServiceJob(jcatFeedService);
    }

    @Bean
    ExportCodesFromBkrToRadService exportCodesFromBkrToRadService(AuctionController auctionController,
                                                                  ExportCodesFromBkrController exportCodesFromBkrController,
                                                                  ExchangeProcController exchangeProcController,
                                                                  LotController lotController, RadApiClient radApiClient, TokenService tokenService) {
        return new ExportCodesFromBkrToRadService(auctionController, exportCodesFromBkrController, exchangeProcController,
                lotController, radApiClient, tokenService);
    }

    @Bean
    ExportCodesFromBkrToRadJob exportCodesFromBkrToRadJob(ExportCodesFromBkrToRadService exportCodesFromBkrToRadService) {
        return new ExportCodesFromBkrToRadJob(exportCodesFromBkrToRadService);
    }

    @Bean
    ExportCodesFromLOToRadJob exportCodesFromLOToRadJob(ExportCodesFromLOToRadService exportCodesFromLOToRadService) {
        return new ExportCodesFromLOToRadJob(exportCodesFromLOToRadService);
    }

    @Bean
    ExportCodesFromLOToRadService exportCodesFromLOToRadService(AuctionController auctionController,
                                                                ExportCodesFromLOController exportCodesFromLOController,
                                                                ExchangeProcController exchangeProcController,
                                                                LotController lotController,  RadApiClient radApiClient, TokenService tokenService) {
        return new ExportCodesFromLOToRadService(auctionController, exportCodesFromLOController, exchangeProcController, lotController, radApiClient, tokenService);
    }

    @Bean
    EmailMessageJob emailMessageJob(EmailMessageService emailMessageService) {
        return new EmailMessageJob(emailMessageService);
    }

    @Bean
    EmailMessageService emailMessageService(EventController eventController, BlockAuctionController blockAuctionController,
                                            EmailMessageProperties emailMessageProperties, CreateEmailMessageService createEmailMessageService, ParticipantAgentController participantAgentController) {
        return new ReceiveInfoMessageEmailMessageService(eventController, blockAuctionController, emailMessageProperties, createEmailMessageService, participantAgentController);
    }

    @Bean
    CreateEmailMessageService createEmailMessageService(EventController eventController, TemplateHelper templateHelper,
                                                        EmailTextService emailTextService, MessageEmailController messageEmailController) {
        return new CreateEmailMessageService(eventController, templateHelper, emailTextService, messageEmailController);
    }

    @Bean
    AvitoClassificatorService avitoClassificatorService(AllowObjPropController allowObjPropController) {
        return new AvitoClassificatorService(avitoAutoUrl, allowObjPropController);
    }

    @Bean
    AvitoTruckClassificatorService avitoTruckClassificatorService(TruckAllowObjPropController truckAllowObjPropController) {
        return new AvitoTruckClassificatorService(avitoTruckUrl, truckAllowObjPropController);
    }

    @Bean
    AvitoClassifierJob avitoClassifierJob(AvitoClassificatorService avitoClassificatorService,
                                          ExchangeProcController exchangeProcController) {
        return new AvitoClassifierJob(avitoClassificatorService, exchangeProcController);
    }

    @Bean
    AvitoTruckClassifierJob avitoTruckClassifierJob(AvitoTruckClassificatorService avitoTruckClassificatorService,
                                                    ExchangeProcController exchangeProcController) {
        return new AvitoTruckClassifierJob(avitoTruckClassificatorService, exchangeProcController);
    }

    @Bean
    AvitoDevelopmentClassifierJob avitoDevelopmentClassifierJob(
            AvitoDevelopmentClassificatorService avitoDevelopmentClassificatorService) {
        return new AvitoDevelopmentClassifierJob(avitoDevelopmentClassificatorService);
    }

    @Bean
    AvitoDevelopmentClassificatorService avitoDevelopmentClassificatorService(
            AllowObjPropController allowObjPropController,
            AllowPropValueController allowPropValueController) {
        return new AvitoDevelopmentClassificatorService(allowObjPropController,
                allowPropValueController,
                avitoDevelopmentUrl);
    }

    @Bean
    BkrReceiveLotCounterService bkrReceiveLotCounterService(ExchangeProcController exchangeProcController,
                                                            BkrCounterController bkrCounterController,
                                                            LotController lotController,
                                                            AuctionController auctionController) {
        return new BkrReceiveLotCounterService(exchangeProcController, bkrCounterController, lotController, auctionController);
    }

    @Bean
    BkrReceiveLotCounterJob bkrReceiveLotCounterJob(BkrReceiveLotCounterService bkrReceiveLotCounterService) {
        return new BkrReceiveLotCounterJob(bkrReceiveLotCounterService);
    }

    @Bean
    UpdateDatesFromBkrToRadJob updateDatesFromBkrToRadJob(UpdateDatesFromBkrToRadService updateDatesFromBkrToRadService, ExchangeProcController exchangeProcController) {
        return new UpdateDatesFromBkrToRadJob(updateDatesFromBkrToRadService, exchangeProcController);
    }

    @Bean
    UpdateDatesFromBkrToRadService updateDatesFromBkrToRadService(AuctionController auctionController,
                                                                  UpdateDatesFromBkrController updateDatesFromBkrController,
                                                                  ExchangeProcController exchangeProcController, LotController lotController) {
        return new UpdateDatesFromBkrToRadService(auctionController, updateDatesFromBkrController, exchangeProcController, lotController);
    }

    @Bean
    UpdateDatesFromBkrController updateDatesFromBkrController() {
        return new UpdateDatesFromBkrController();
    }

    @Bean
    UpdateDatesFromLotOnlineToRadJob updateDatesFromLotOnlineToRadJob(UpdateDatesFromLotOnlineToRadService updateDatesFromLotOnlineToRadService) {
        return new UpdateDatesFromLotOnlineToRadJob(updateDatesFromLotOnlineToRadService);
    }

    @Bean
    UpdateDatesFromLotOnlineToRadService updateDatesFromLotOnlineToRadService(AuctionController auctionController,
                                                                              UpdateDatesFromLOController updateDatesFromLOController,
                                                                              ExchangeProcController exchangeProcController, LotController lotController) {
        return new UpdateDatesFromLotOnlineToRadService(auctionController, updateDatesFromLOController, exchangeProcController, lotController);
    }

    @Bean
    UpdateDatesFromLOController updateDatesFromLOController() {
        return new UpdateDatesFromLOController();
    }

    @Bean
    SynchronizeCategoriesFromEisToBkrService synchronizeCategoriesFromEisToBkrService(SaleCategoryController saleCategoryController,
                                                                                      BkrRubricatorController bkrRubricatorController) {
        return new SynchronizeCategoriesFromEisToBkrService(saleCategoryController, bkrRubricatorController);
    }

    @Bean
    SynchronizeCategoriesFromEisToLOService synchronizeCategoriesFromEisToLOService(SaleCategoryController saleCategoryController,
                                                                                    LOCategoryController loCategoryController,
                                                                                    LoCategoriesClient loCategoriesClient) {
        return new SynchronizeCategoriesFromEisToLOService(saleCategoryController,
                loCategoryController,
                loCategoriesClient);
    }

    @Bean
    SynchronizeCategoriesService synchronizeCategoriesService(ExchangeProcController exchangeProcController,
                                                              SaleCategoryController saleCategoryController,
                                                              SynchronizeCategoriesFromEisToBkrService synchronizeCategoriesFromEisToBkrService,
                                                              SynchronizeCategoriesFromEisToLOService synchronizeCategoriesFromEisToLOService) {
        return new SynchronizeCategoriesService(exchangeProcController, saleCategoryController,
                synchronizeCategoriesFromEisToBkrService,
                synchronizeCategoriesFromEisToLOService
        );
    }

    @Bean
    SynchronizeCategoriesJob synchronizeCategoriesJob(SynchronizeCategoriesService synchronizeCategoriesService) {
        return new SynchronizeCategoriesJob(synchronizeCategoriesService);
    }

    @Bean
    UpdateDataService updateDataService(ExchangeProcController exchangeProcController,
                                        BlockAuctionController blockAuctionController,
                                        AuctionController auctionController,
                                        JsonExportService jsonExportService,
                                        JsonExportToAssetsService jsonExportToAssetsService,
                                        XmlExportService xmlExportService,
                                        LotController lotController, DocFileController docFileController) {
        return new UpdateDataService(exchangeProcController, blockAuctionController,
                auctionController, jsonExportService, jsonExportToAssetsService,
                xmlExportService, lotController,
                docFileController, radiusPathToFiles);
    }

    @Bean
    UpdateDataJob updateDataJob(UpdateDataService updateDataService) {
        return new UpdateDataJob(updateDataService);
    }

    @Bean
    FeedStatisticsService avitoFeedStatisticService(List<AvitoApiClient> avitoApiClient) {
        return new AvitoAdStatisticsService(avitoApiClient);
    }

    @Bean
    AdStatisticService avitoAdStatisticService(FeedStatisticsService avitoFeedStatisticService,
                                               ObjectController objectController,
                                               AdStatisticsController adStatisticsController) {
        return new AdStatisticService(avitoFeedStatisticService, objectController, adStatisticsController,
                MarketingEvent.AVITO.getUnid());
    }

    @Bean
    AvitoAdStatisticsJob avitoAdStatisticsJob(AdStatisticService avitoAdStatisticService,
                                              ExchangeProcController exchangeProcController) {
        return new AvitoAdStatisticsJob(avitoAdStatisticService, exchangeProcController);
    }

    @Bean
    FeedStatisticsService cianFeedStatisticsService(List<CianApiClient> cianApiClients) {
        return new CianAdStatisticsService(cianApiClients);
    }

    @Bean
    AdStatisticService cianAdStatisticService(FeedStatisticsService cianFeedStatisticsService,
                                              ObjectController objectController,
                                              AdStatisticsController adStatisticsController) {
        return new AdStatisticService(cianFeedStatisticsService, objectController, adStatisticsController,
                MarketingEvent.CIAN.getUnid());
    }

    @Bean
    CianAdStatisticsJob cianAdStatisticsJob(AdStatisticService cianAdStatisticService,
                                            ExchangeProcController exchangeProcController) {
        return new CianAdStatisticsJob(cianAdStatisticService, exchangeProcController);
    }

    @Bean
    FeedStatisticsService jcatFeedStatisticsService(JCatApiClient jCatApiClient) {
        return new JCatAdStatisticsService(jCatApiClient);
    }

    @Bean
    AdStatisticService jcatAdStatisticService(FeedStatisticsService jcatFeedStatisticsService,
                                              ObjectController objectController,
                                              AdStatisticsController adStatisticsController) {
        return new AdStatisticService(jcatFeedStatisticsService, objectController, adStatisticsController,
                MarketingEvent.JCAT.getUnid());
    }

    @Bean
    JcatAdStatisticJob jcatAdStatisticJob(AdStatisticService jcatAdStatisticService,
                                          ExchangeProcController exchangeProcController) {
        return new JcatAdStatisticJob(jcatAdStatisticService, exchangeProcController);
    }

    @Bean
    CreatingDocumentJob creatingDocumentJob(DocumentController documentController,
                                            DocParamValueController docParamValueController,
                                            CreatingDocumentService creatingDocumentService,
                                            DocTemplateController docTemplateController,
                                            NotificationController notificationController) {
        return new CreatingDocumentJob(documentController,
                docParamValueController,
                creatingDocumentService,
                docTemplateController,
                notificationController);
    }

    @Bean
    VitrinaOuterLinkService vitrinaOuterLinkService(OuterLinkProperties outerLinkProperties) {
        return new VitrinaOuterLinkService(outerLinkProperties);
    }

    @Bean
    UpdateVitrinaUrlService updateVitrinaUrlService(ExchangeProcController exchangeProcController,
                                                    VitrinaController vitrinaController,
                                                    VitrinaOuterLinkService vitrinaOuterLinkService,
                                                    LotController lotController,
                                                    ManagerNotificationService managerNotificationService) {
        return new UpdateVitrinaUrlService(exchangeProcController, vitrinaController, vitrinaOuterLinkService,
                lotController, managerNotificationService);
    }

    @Bean
    UpdateVitrinaUrlJob updateVitrinaUrlJob(UpdateVitrinaUrlService updateVitrinaUrlService) {
        return new UpdateVitrinaUrlJob(updateVitrinaUrlService);
    }

    @Bean
    UpdateStatisticJob updateStatisticJob(YandexMetrikaApiClient yandexMetrikaApiClient, ObjectController objectController,
                                          AdStatisticsController adStatisticsController, ExchangeProcController exchangeProcController) {
        return new UpdateStatisticJob(counterIdCatalogLO, counterIdAuctionHouse, counterIdPledgeLO,
                yandexMetrikaApiClient, objectController, adStatisticsController, exchangeProcController);
    }


    @Bean
    SynchroniseStatusFromBkrToRadJob synchroniseStatusFromBkrToRadJob(CheckSuspendedAuctionFromBkrToRadService checkSuspendedAuctionFromBkrToRadService,
                                                                      CheckResumedAuctionFromBkrToRadService checkResumedAuctionFromBkrToRadService) {
        return new SynchroniseStatusFromBkrToRadJob(checkSuspendedAuctionFromBkrToRadService, checkResumedAuctionFromBkrToRadService);
    }

    @Bean
    CheckSuspendedAuctionFromBkrToRadService checkSuspendedAuctionFromBkrToRadService(AuctionController auctionController,
                                                                                      UpdateDatesFromBkrController updateDatesFromBkrController,
                                                                                      ExchangeProcController exchangeProcController, LotController lotController) {
        return new CheckSuspendedAuctionFromBkrToRadService(auctionController, updateDatesFromBkrController, exchangeProcController, lotController);
    }

    @Bean
    CheckResumedAuctionFromBkrToRadService checkResumedAuctionFromBkrToRadService(AuctionController auctionController,
                                                                                  UpdateDatesFromBkrController updateDatesFromBkrController,
                                                                                  ExchangeProcController exchangeProcController, LotController lotController) {
        return new CheckResumedAuctionFromBkrToRadService(auctionController, updateDatesFromBkrController, exchangeProcController, lotController);
    }


    @Value("${notification.explanationResponse.theme}")
    private String notificationExplanationResponseThemeTemplate;
    @Value("${notification.explanationResponse.template}")
    private String notificationExplanationResponseTextTemplate;

    @Bean
    ExplanationResponseService explanationResponseService(RadApiClient radApiClient,
                                                          TokenService tokenService,
                                                          ExplanationResponseController explanationResponseController,
                                                          ExchangeProcController exchangeProcController,
                                                          LotController lotController,
                                                          NotificationController notificationController,
                                                          TemplateHelper templateHelper) {
        return new ExplanationResponseService(radApiClient, tokenService, explanationResponseController,
                exchangeProcController, lotController, notificationController, templateHelper,
                notificationExplanationResponseThemeTemplate, notificationExplanationResponseTextTemplate);
    }

    @Bean
    ExplanationResponseJob explanationResponseJob(ExplanationResponseService explanationResponseService) {
        return new ExplanationResponseJob(explanationResponseService);
    }

    @Bean
    AsvResultService asvResultService(AuctionController auctionController,
                                      LotController lotController) {
        return new AsvResultService(auctionController, lotController);
    }

    @Bean
    SendResultsToFtpService sendResultsToFtpService(BlockAuctionController blockAuctionController,
                                                    LotController lotController,
                                                    ExchangeProcController exchangeProcController,
                                                    AsvResultService asvResultService,
                                                    FileStorage fileStorage) {
        return new SendResultsToFtpService(blockAuctionController,
                lotController, exchangeProcController, asvResultService, fileStorage);
    }

    @Bean
    SendResultsToFtpJob sendResultsToFtpJob(SendResultsToFtpService sendResultsToFtpService) {
        return new SendResultsToFtpJob(sendResultsToFtpService);
    }

    @Value("${notification.lot.theme}")
    private String notificationLotPublishedThemeTemplate;
    @Value("${notification.lot.template}")
    private String notificationLotPublishedTextTemplate;

    @Bean
    LotPublishedNotificationService lotPublishedNotificationService(AccessProfileController accessProfileController,
                                                                    LotPublishedNotificationController lotPublishedNotificationController,
                                                                    TemplateHelper templateHelper,
                                                                    MessageEmailController messageEmailController,
                                                                    EventController eventController,
                                                                    EmailTextService emailTextService) {
        return new LotPublishedNotificationService(accessProfileController, lotPublishedNotificationController,
                templateHelper, notificationLotPublishedThemeTemplate, notificationLotPublishedTextTemplate,
                messageEmailController, eventController, emailTextService);
    }

    @Bean
    LotPublishedNotificationJob lotPublishedNotificationJob(LotPublishedNotificationService lotPublishedNotificationService) {
        return new LotPublishedNotificationJob(lotPublishedNotificationService);
    }

    @Bean
    DeleteArchiveFeedService deleteArchiveFeedService(FeedAdController feedAdController) {
        return new DeleteArchiveFeedService(feedAdController);
    }

    @Bean
    DeleteArchiveFeedJob deleteArchiveFeedJob(DeleteArchiveFeedService deleteArchiveFeedService,
                                              ExchangeProcController exchangeProcController) {
        return new DeleteArchiveFeedJob(deleteArchiveFeedService, exchangeProcController);
    }

    @Bean
    DepExchangeService depExchangeService(ExchangeProcController exchangeProcController, BkrDepController bkrDepController,
                                          LoDepController loDepController) {
        return new DepExchangeService(exchangeProcController, bkrDepController, loDepController);
    }

    @Bean
    DepExchangeJob depExchangeJob(DepExchangeService depExchangeService) {
        return new DepExchangeJob(depExchangeService);
    }

    @Bean
    FillCallsCountToTrustLotsJob fillCallsCountToTrustLotsJob(LotController lotController,
                                                              CallsApiClient callsApiClient,
                                                              ExchangeProcController exchangeProcController) {
        return new FillCallsCountToTrustLotsJob(lotController, callsApiClient, exchangeProcController);
    }

    @Bean
    SynchronizeLinksService synchronizeLinksService(ExchangeProcController exchangeProcController, LotController lotController, LotsController lotsController, AuctionHouseLinksProperties auctionHouseLinksProperties) {
        return new SynchronizeLinksService(exchangeProcController, lotController, lotsController, auctionHouseLinksProperties);
    }

    @Bean
    SynchronizeLinksJob synchronizeLinksJob(SynchronizeLinksService synchronizeLinksService, ExchangeProcController exchangeProcController) {
        return new SynchronizeLinksJob(synchronizeLinksService, exchangeProcController);
    }

    @Bean
    CheckLinksService checkLinksService(ExchangeProcController exchangeProcController, AuctionController auctionController) {
        return new CheckLinksService(exchangeProcController, auctionController);
    }

    @Bean
    CheckLinksJob checkLinksJob(CheckLinksService checkLinksService) {
        return new CheckLinksJob(checkLinksService);
    }

    @Bean
    SequenceResetService sequenceResetService(SafetyReceiptController safetyReceiptController) {
        return new SequenceResetService(safetyReceiptController);
    }

    @Bean
    SequenceResetJob sequenceResetJob(SequenceResetService sequenceResetService) {
        return new SequenceResetJob(sequenceResetService);
    }
}
