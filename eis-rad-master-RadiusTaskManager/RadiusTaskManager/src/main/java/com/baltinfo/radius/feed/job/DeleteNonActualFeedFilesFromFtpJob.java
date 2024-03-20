package com.baltinfo.radius.feed.job;

import com.baltinfo.radius.feed.services.ExportFeedPhotoToFtp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.util.Objects;

/**
 * @author Kulikov Semyon
 * @since 26.06.2020
 */

public class DeleteNonActualFeedFilesFromFtpJob {

    private static final Logger logger = LoggerFactory.getLogger(DeleteNonActualFeedFilesFromFtpJob.class);

    private final ExportFeedPhotoToFtp exportFeedPhotoToFtp;

    public DeleteNonActualFeedFilesFromFtpJob(ExportFeedPhotoToFtp exportFeedPhotoToFtp) {
        this.exportFeedPhotoToFtp = Objects.requireNonNull(exportFeedPhotoToFtp, "Can't get exportFeedPhotoToFtp");
    }

    @Scheduled(cron = "${job.cron.delete.non.actual.feed.files.from.ftp}")
    public void runJob() {
        logger.info("start run job method at {}", Instant.now());
        try {
            exportFeedPhotoToFtp.deleteNonActualFeedFilesFromFtp();
        } catch (Exception ex) {
            logger.error("Error when run deleteNonActualFeedFilesFromFtp", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }
}
