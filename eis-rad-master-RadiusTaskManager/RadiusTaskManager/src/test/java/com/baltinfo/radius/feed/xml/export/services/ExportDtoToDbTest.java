package com.baltinfo.radius.feed.xml.export.services;

import com.baltinfo.radius.application.configuration.ControllerConfiguration;
import com.baltinfo.radius.application.configuration.JobConfiguration;
import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.loadauction.constants.FileTypeConstant;
import com.baltinfo.radius.loadauction.ftp.FileStorage;
import com.baltinfo.radius.loadauction.ftp.TenderSource;
import com.baltinfo.radius.loadauction.job.SendAsvTenderFileToFtpJob;
import com.baltinfo.radius.loadauction.service.ExportDtoToDb;
import com.baltinfo.radius.utils.Result;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * <p>
 *     Тест, проверяющий возможность загрузки xml файлов с FTP сервера
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 23.01.2020
 */

@SpringBootTest(classes = JobConfiguration.class)
public class ExportDtoToDbTest {

    private static final Logger logger = LoggerFactory.getLogger(ExportDtoToDbTest.class);

    private ExportDtoToDb exportDtoToDb;
    private FileStorage fileStorage;
    private SendAsvTenderFileToFtpJob sendAsvTenderFileToFtpJob;
    @Value("${ftp.new.file.format}")
    private boolean isNewFormat;
    private ExchangeProcController exchangeProcController;

    private static final String STRING_FORMAT_FOR_FILE_NAME = "Tender_%s_%02d%02d%04d_%s_%s.xml";

    @Before
    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JobConfiguration.class);
        exportDtoToDb = (ExportDtoToDb) context.getBean("exportDtoToTempDb");
        fileStorage = (FileStorage) context.getBean("fileStorage");
        sendAsvTenderFileToFtpJob = (SendAsvTenderFileToFtpJob) context.getBean("sendAsvTenderFileToFtpJob");
        context = new AnnotationConfigApplicationContext(ControllerConfiguration.class);
        exchangeProcController = (ExchangeProcController) context.getBean("exchangeProcController");
    }

    @Ignore
    @Test
    public void shouldSaveDataFromFtpToDb() {
        List<TenderSource> xmlFileSource;
        try {
            xmlFileSource = fileStorage.getTenderSource();
        } catch (Exception e) {
            logger.error("Ошибка чтения xml файлов");
            return;
        }
        try {
            for (TenderSource tenderSource : xmlFileSource) {
                Result<Void, String> result = exportDtoToDb.exportToDb(tenderSource);
                if (result.isError()) {
                    logger.error(result.getError());
                }
            }
            Thread.sleep(10000);
        } catch (Exception e) {
            logger.error("Some problem in test", e);
        }
    }

    @Ignore
    @Test
    public void testDirectoryByDate() {
        DateTime now = new DateTime();
        now.minusDays(1);
        int year = now.getYear();
        int month = now.getMonthOfYear();
        int day = now.getDayOfMonth();
        String todayDirectory;
        todayDirectory = String.format("/Ot_ASV/%04d/%04d-%02d/%04d-%02d-%02d/", year, year, month, year, month, day);
        System.out.println(todayDirectory);
    }

    @Ignore
    @Test
    public void getFileTypeConstant() {
        Optional<FileTypeConstant> fileType = FileTypeConstant.getByName("Документ");
        if (!fileType.isPresent()) {
            System.out.println("FileTypeConstant с таким именем не найден");
        }
        System.out.println(fileType.get());
    }

    @Test
    @Ignore
    public void testShouldReturnRightDescriptionForObjects() {
        StringBuilder sb = new StringBuilder();
        String lotName = "Российская монета - портретный рубль императора Павла I Санкт-Петербургского монетного двора 1796 г.";
        String description = "рубль 1796 г., пробная, СПБ-CLF. серебро, вес 23,67 г, диаметр 39.0-41.1 мм, гурт-шнур. Биткин №219(R4), Узденников №1255(!!), Северин №2377 (черта с тремя точками), выше 300 руб. по Ильину";
        String restriction = "нумизматический памятник";
        sb.append(lotName);
        if (description != null && !description.trim().isEmpty()) {
            sb.append(", ");
            sb.append(description);
        }
        if (restriction != null && !restriction.trim().isEmpty()) {
            sb.append(", ограничения и обременения: ");
            sb.append(restriction);
        }
        logger.info(sb.toString());
    }

    @Ignore
    @Test
    public void checkDocName() {
        Stream<String> stream = Stream.of("L003002", "L002015", "L03015", "L003PTS", "L0003EGRN", "L0301", "L0302");
        stream.filter(str -> isWantedFile(str, 3L, isNewFormat))
                .forEach(System.out::println);
    }

    private boolean isWantedFile(String name, Long num, boolean flag) {
        Pattern pattern = Pattern.compile(flag
                ? "^l" + String.format("%03d", num)
                : "^l0" + num + "0[^0]");
        Matcher matcher = pattern.matcher(name.toLowerCase());
        return matcher.find();
    }

    @Ignore
    @Test
    public void test() {
        String ogrn = "1213123123";
        String efrsbCode = "111";
        String etpCode = "333";
        DateTime now = new DateTime();
        int year = now.getYear();
        int month = now.getMonthOfYear();
        int day = now.getDayOfMonth();
        String fileName = String.format(STRING_FORMAT_FOR_FILE_NAME, ogrn, day, month, year, efrsbCode, etpCode);
        System.out.println(fileName);
    }

    @Ignore
    @Test
    public void sendXmlToFtpTest() {
        sendAsvTenderFileToFtpJob.runJob();
    }

    @Ignore
    @Test
    public void scriptForCreatingPairExchangeProcRun() {
        List<ExchangeProcRun> exchangeProcRunList = exchangeProcController.getEPRSendWithoutFinishedReceive(
                ExchangeProcs.RECEIVE_CODES_FROM_BANKRUPTCY.getUnid(),
                ExchangeProcs.SEND_AUCTION_TO_ASV.getUnid());
        for (ExchangeProcRun epr : exchangeProcRunList) {
            ExchangeProcRun eprPair = buildExchangeProcRun(ExchangeProcs.SEND_AUCTION_TO_ASV.getUnid(), epr.getEprSourceId(), epr.getEprReceiverId());
            exchangeProcController.saveExchangeProcRun(eprPair);
        }
    }

    private ExchangeProcRun buildExchangeProcRun(long exchangeProcUnid, Long eprSourceId, Long eprReceiverId) {
        ExchangeProcRun epr = new ExchangeProcRun();
        Long paUnid = 1L;
        epr.setFoundB("19662");
        epr.setEpUnid(exchangeProcUnid);
        epr.setEprSourceId(eprSourceId);
        epr.setEprLoadStatus(ExchangeProcStatus.FINISHED.getCode());
        epr.setEprPaUnid(paUnid);
        epr.setEprRunDate(new Date());
        epr.setEprReceiverId(eprReceiverId);
        return epr;
    }

}
