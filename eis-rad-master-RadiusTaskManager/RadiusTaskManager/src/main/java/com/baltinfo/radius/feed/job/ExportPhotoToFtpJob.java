package com.baltinfo.radius.feed.job;

import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.feed.services.ExportFeedPhotoToFtp;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

public class ExportPhotoToFtpJob {

    private static final Logger logger = LoggerFactory.getLogger(ExportPhotoToFtpJob.class);

    private final ExportFeedPhotoToFtp exportFeedPhotoToFtp;
    private final ExchangeProcController exchangeProcController;

    public ExportPhotoToFtpJob(ExportFeedPhotoToFtp exportFeedPhotoToFtp, ExchangeProcController exchangeProcController) {
        this.exportFeedPhotoToFtp = Objects.requireNonNull(exportFeedPhotoToFtp, "Can't get exportFeedPhotoToFtp");
        this.exchangeProcController = Objects.requireNonNull(exchangeProcController, "Can't get exchange controller");
    }

    @Scheduled(cron = "${job.cron.export.photo.to.ftp}")
    public void exportAllPhotos() {
        logger.info("start exportAllPhotos job method at {}", Instant.now());
        try {
            exportFeedPhotoToFtp.exportAllPhotos();
        } catch (Exception ex) {
            logger.error("Error when run exportAllPhotos", ex);
        }
        logger.info("end exportAllPhotos job method at {}", Instant.now());
    }

    @Scheduled(cron = "${job.cron.export.photo.to.ftp.with.epr}")
    public void exportWithEpr() {
        logger.info("start exportWithEpr job method at {}", Instant.now());
        try {
            Optional<ExchangeProcRun> exchangeProcRunOpt = exchangeProcController
                    .getFirstRunningExchangeProcRun(ExchangeProcs.EXPORT_PHOTO_TO_SFTP);
            while (exchangeProcRunOpt.isPresent()) {
                ExchangeProcRun exchangeProcRun = exchangeProcRunOpt.get();
                Result<Void, String> result = exportFeedPhotoToFtp.exportWithEpr(exchangeProcRun);
                exchangeProcRunOpt = exchangeProcController
                        .getFirstRunningExchangeProcRun(ExchangeProcs.EXPORT_PHOTO_TO_SFTP);
            }
        } catch (Exception ex) {
            logger.error("Error when run exportWithEpr", ex);
        }
        logger.info("end exportWithEpr job method at {}", Instant.now());
    }
}
