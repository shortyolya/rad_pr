package com.baltinfo.radius.xls;

import com.baltinfo.radius.application.configuration.JobConfiguration;
import com.baltinfo.radius.application.configuration.NotificationConfiguration;
import com.baltinfo.radius.db.dto.AuctionDto;
import com.baltinfo.radius.job.ExportLotsFromTempToDbJob;
import com.baltinfo.radius.job.XlsExportToTempJob;
import com.baltinfo.radius.notification.paydoc.PayDocNotificationJob;
import com.baltinfo.radius.utils.Result;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 * </p>
 *
 * @author Lapenok Igor
 * @since 21.08.2018
 */
public class XlsExportServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(XlsExportServiceTest.class);
    private static final String CURRENT_TEMPLATE_VERSION = "v. ЕИС-21.4";
    private static final String CURRENT_PRIVATE_PROPERTY_TEMPLATE_VERSION = "v. ЕИС-Ч-21.4";
    private static final String TEST_FILE_PATH = "E:\\Projects\\RAD\\eis_lots_load_template.xlsx";


    @Test
    @Ignore
    public void testLoadFromFile() {
//      Путь для тестирования
//      String path = "\\\\Bifileserver\\works\\radius\\fileserver\\" + loadAuction.getLaFilePath();
//        ApplicationContext context = new AnnotationConfigApplicationContext(JobConfiguration.class);
//        XlsExportToTempJob xlsExportToTempJob = context.getBean(XlsExportToTempJob.class);
//        xlsExportToTempJob.runJob();
//
//        ExportLotsFromTempToDbJob exportLotsFromTempToDbJob = context.getBean(ExportLotsFromTempToDbJob.class);
//        exportLotsFromTempToDbJob.runJob();

        ApplicationContext context = new AnnotationConfigApplicationContext(NotificationConfiguration.class);
        PayDocNotificationJob xlsExportToTempJob = context.getBean(PayDocNotificationJob.class);
        xlsExportToTempJob.run();


//        XlsExportService xlsExportService = new XlsExportService(CURRENT_TEMPLATE_VERSION, CURRENT_PRIVATE_PROPERTY_TEMPLATE_VERSION, new LoadLotService());
//        Result<AuctionDto, String> result =
//                xlsExportService.loadFromFile(TEST_FILE_PATH);
//        LOG.info("testLoadFromFile result: {}", result.isSuccess() ? "success" : "error");
//        if (result.isError()) {
//            LOG.info("Error message: {}", result.getError());
//        }
//        assert Boolean.TRUE.equals(result.isSuccess());
    }

    public void testLoadToTempDb() {
//        LoadAuctionController loadAuctionController = new LoadAuctionController(new AuctionDao(), new LoadAuctionDao());
//        for (LoadAuction loadAuction : loadAuctionController.getLoadAuctionListForRun()) {
//            String path = xlsTemplatePath + File.separator + loadAuction.getLaFilePath();
//            logger.info("start load xlsx from file = " + path + "; duration = " + (new Date().getTime() - startDate));
//            Result<AuctionDto, String> exportResult = xlsExportService.loadFromFile(path);
//            logger.info("after load xlsx file; duration = " + (new Date().getTime() - startDate));
//            Optional<String> saveResult = loadAuctionController.save(loadAuction, exportResult.getResult());
//            if (saveResult.isPresent()) {
//                loadAuctionController.updateLoadStatus(loadAuction, LoadStatus.TEMP_DB_SAVE_ERROR_STATUS, exportResult.getError());
//            }
//            logger.info("after save in db; duration = " + (new Date().getTime() - startDate));
//        }
    }

//    @Test
//    public void testParseRubric() throws Exception {
//        LotController lotController = new LotController(new LotDao());
//        EntityManager em = null;
//        try {
//            em = HibernateUtil.getInstance().getEntityManager();
//            System.out.println(lotController.convertRubr(em, "Финансовые активы  - Ценные бумаги"));
//        }
//        finally {
//            em.close();
//        }
//    }
}
