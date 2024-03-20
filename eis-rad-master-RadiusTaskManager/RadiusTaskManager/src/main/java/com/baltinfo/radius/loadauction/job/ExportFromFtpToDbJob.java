package com.baltinfo.radius.loadauction.job;

import com.baltinfo.radius.loadauction.ftp.FileStorage;
import com.baltinfo.radius.loadauction.ftp.TenderSource;
import com.baltinfo.radius.loadauction.service.ExportDtoToDb;
import com.baltinfo.radius.utils.Result;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *     Job для совершения экспорта данных с FTP сервера в БД
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 04.02.2020
 */
public class ExportFromFtpToDbJob {
    private static final Logger logger = LoggerFactory.getLogger(ExportFromFtpToDbJob.class);

    private final ExportDtoToDb exportDtoToDb;
    private final FileStorage fileStorage;

    public ExportFromFtpToDbJob(ExportDtoToDb exportDtoToDb, FileStorage fileStorage) {
        this.exportDtoToDb = Objects.requireNonNull(exportDtoToDb, "Can't get exportDtoToDb");
        this.fileStorage = Objects.requireNonNull(fileStorage, "Can't get fileStorage");
    }

    @Scheduled(cron = "${job.cron.asv.export.to.db}")
    public void runJob() {
        Long startDate = new Date().getTime();
        logger.info("start ExportFromFtpToDbJob run job method at " + startDate);
        List<TenderSource> tenderFileSource;
        try {
            tenderFileSource = fileStorage.getTenderSource();
            if (tenderFileSource.isEmpty()) {
                logger.info("FTP doesn't contain any new xml for upload");
            }
            for (TenderSource tenderSource : tenderFileSource) {
                Result<Void, String> result = exportDtoToDb.exportToDb(tenderSource);
                if (result.isError()) {
                    logger.error(result.getError());
                }
            }
        } catch (Exception e) {
            logger.error("Can't load tender to DB", e);
        }
        logger.info("end ExportFromFtpToDbJob run job method at " + new Date().getTime() + " duration = " + (new Date().getTime() - startDate));
    }
}
