package com.baltinfo.radius.feed.services;

import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.LoadSourceConstant;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.controller.ObjectController;
import com.baltinfo.radius.db.controller.PictureController;
import com.baltinfo.radius.db.dto.FeedObjectDto;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.db.model.Picture;
import com.baltinfo.radius.feed.ftp.FeedFtpService;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Kulikov Semyon
 * @since 23.06.2020
 */

public class ExportFeedPhotoToFtp {
    private final Logger logger = LoggerFactory.getLogger(ExportFeedPhotoToFtp.class);
    private final PictureController pictureController;
    private final ObjectController objectController;
    private final FeedFtpService feedFtpService;
    private final ExchangeProcController exchangeProcController;

    private static final int MAX_EXPORT_TO_SFTP_PHOTO_COUNT = 40;

    public ExportFeedPhotoToFtp(PictureController pictureController,
                                ObjectController objectController,
                                FeedFtpService feedFtpService,
                                ExchangeProcController exchangeProcController) {
        this.pictureController = Objects.requireNonNull(pictureController, "Can't get controller");
        this.objectController = Objects.requireNonNull(objectController, "Can't get controller");
        this.feedFtpService = Objects.requireNonNull(feedFtpService, "Can't get feed ftp ftp");
        this.exchangeProcController = Objects.requireNonNull(exchangeProcController, "Can't get eprController");
    }

    public Result<Void, String> exportWithEpr(ExchangeProcRun epr) {
        FeedObjectDto feedObject = objectController.getObjectForFeed(epr.getEprSourceId());
        if (feedObject == null) {
            String message = "Объект не выгружается в фид.";
            epr.setEprErrorText(message);
            epr.setEprLoadStatus(ExchangeProcStatus.ERROR_RUNNING.getCode());
            exchangeProcController.saveExchangeProcRun(epr);
            return Result.error(message);
        }
        String error = exportPhotos(feedObject);
        if (StringUtils.hasText(error)) {
            epr.setEprLoadStatus(ExchangeProcStatus.ERROR_RUNNING.getCode());
        } else {
            epr.setEprLoadStatus(ExchangeProcStatus.FINISHED.getCode());
        }
        epr.setEprErrorText(error);
        exchangeProcController.saveExchangeProcRun(epr);
        return Result.ok();
    }

    public void exportAllPhotos() {
        List<FeedObjectDto> objects = objectController.getObjectsForFeed();
        for (FeedObjectDto feedObject : objects) {
            exportPhotos(feedObject);
        }
    }

    private String exportPhotos(FeedObjectDto feedObject) {
        String error = "";
        Result result = null;
        List<Picture> pictureList = pictureController.getPicturesForExportToFtpWithLimit(feedObject.getObjUnid(), MAX_EXPORT_TO_SFTP_PHOTO_COUNT);
        for (Picture pic : pictureList) {
            if (exportWithWatermark(feedObject)) {
                Result<Boolean, String> fileExistResult = feedFtpService.isFileExistsInDirectory(feedObject.getObjUnid(), pic.getDfUnid().getDfNameDb().replace(".", "_wm."));
                if (fileExistResult.isSuccess() && !fileExistResult.getResult()) {
                    result = feedFtpService.postImageFileOnServer(feedObject.getObjUnid(), pic.getDfUnid().getDfNameDb(), pic.getDfUnid().getDfFilePath(), true);
                }
            }
            if (result != null && result.isError()) {
                error += result.getError() + "\n";
            }
            if (exportWithoutWatermark(feedObject)) {
                Result<Boolean, String> fileExistResult = feedFtpService.isFileExistsInDirectory(feedObject.getObjUnid(), pic.getDfUnid().getDfNameDb());
                if (fileExistResult.isSuccess() && !fileExistResult.getResult()) {
                    result = feedFtpService.postImageFileOnServer(feedObject.getObjUnid(), pic.getDfUnid().getDfNameDb(), pic.getDfUnid().getDfFilePath(), false);
                }
            }
            if (result != null && result.isError()) {
                error += result.getError() + "\n";
            }
        }
        return error;
    }


    public void deleteNonActualFeedFilesFromFtp() {
        List<FeedObjectDto> objects = objectController.getObjectsForFeed();
        List<Long> objUnidsList = new ArrayList<>();
        objects.forEach(t -> objUnidsList.add(t.getObjUnid()));
        feedFtpService.deleteUnnecessaryDirectories(objUnidsList);

        for (FeedObjectDto feedObject : objects) {
            List<Picture> pictureList = pictureController.getPicturesForExportToFtpWithLimit(feedObject.getObjUnid(), MAX_EXPORT_TO_SFTP_PHOTO_COUNT);
            List<String> picturesDbNameList = new ArrayList<>();
            if (exportWithWatermark(feedObject)) {
                pictureList.forEach(t -> picturesDbNameList.add(t.getDfUnid().getDfNameDb().replace(".", "_wm.")));
            }
            if (exportWithoutWatermark(feedObject)) {
                pictureList.forEach(t -> picturesDbNameList.add(t.getDfUnid().getDfNameDb()));
            }
            feedFtpService.deleteUnnecessaryFiles(feedObject.getObjUnid(), picturesDbNameList);
        }
    }

    public boolean exportWithWatermark(FeedObjectDto objectForFeed) {
        Long lsUnid = objectForFeed.getLsUnid();
        if (lsUnid != null) {
            if (lsUnid.equals(LoadSourceConstant.ASV.getId()) || lsUnid.equals(LoadSourceConstant.ASVZ.getId())) {
                if (objectForFeed.getToCian() != null) {
                    if (objectForFeed.getToCian()) {
                        return true;
                    }
                }
                if (objectForFeed.getToAvito() != null) {
                    if (objectForFeed.getToAvito()) {
                        return true;
                    }
                }
                if (objectForFeed.getToJCat() != null) {
                    if (objectForFeed.getToJCat()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean exportWithoutWatermark(FeedObjectDto feedObject) {
        Long lsUnid = feedObject.getLsUnid();
        if (lsUnid == null) {
            return true;
        } else {
            if (!(lsUnid.equals(LoadSourceConstant.ASV.getId()) || lsUnid.equals(LoadSourceConstant.ASVZ.getId()))) {
                return true;
            }
        }
        return false;
    }
}
