package com.baltinfo.radius.feed.services.impl;

import com.baltinfo.radius.db.constants.CianCategoryCode;
import com.baltinfo.radius.db.constants.LoadSourceConstant;
import com.baltinfo.radius.db.controller.CianController;
import com.baltinfo.radius.db.controller.PictureController;
import com.baltinfo.radius.db.model.FeedCategory;
import com.baltinfo.radius.db.model.FeedObject;
import com.baltinfo.radius.db.model.Picture;
import com.baltinfo.radius.feed.cian.converter.CianConverter;
import com.baltinfo.radius.feed.cian.model.Feed;
import com.baltinfo.radius.feed.cian.model.Object;
import com.baltinfo.radius.feed.cian.model.PhotoSchema;
import com.baltinfo.radius.feed.cian.model.Photos;
import com.baltinfo.radius.feed.services.FeedXmlService;
import com.baltinfo.radius.feed.utils.ResultForFeed;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Suvorina Aleksandra
 * @since 08.09.2020
 */
public class CianFeedXmlService implements FeedXmlService {

    private final Logger logger = LoggerFactory.getLogger(CianFeedXmlService.class);

    private final Map<CianCategoryCode, CianController<? extends FeedObject>> controllerByCategory;
    private final Map<CianCategoryCode, CianConverter> converterByCategory;
    private final PictureController pictureController;
    private final String baseUrl;
    private static final String SUFFIX_FOR_ASV_DESCRIPTION = "Имущество реализуется по поручению ГК \"Агентство по страхованию вкладов\".";
    private static final int FEED_VERSION = 2;
    private static final int MAX_EXPORT_TO_SFTP_PHOTO_COUNT = 40;

    public CianFeedXmlService(Map<CianCategoryCode, CianController<? extends FeedObject>> controllerByCategory,
                              Map<CianCategoryCode, CianConverter> converterByCategory,
                              PictureController pictureController,
                              String baseUrl) {
        this.controllerByCategory = controllerByCategory;
        this.converterByCategory = converterByCategory;
        this.pictureController = Objects.requireNonNull(pictureController, "Can't load pictureController");
        this.baseUrl = baseUrl;
    }

    @Override
    public Result<ResultForFeed, String> getFeed(FeedCategory feedCategory, Long subUnid) {
        CianCategoryCode categoryCode;
        try {
            categoryCode = CianCategoryCode.getByValue(feedCategory.getFcCode());
        } catch (IllegalArgumentException ex) {
            String error = "Incorrect category " + feedCategory.getFcCode();
            return Result.ok(new ResultForFeed<>(initFeed(), error, new HashMap<>(), new ArrayList<>()));
        }

        CianController<? extends FeedObject> controller = controllerByCategory.get(categoryCode);
        if (controller == null) {
            String error = "Can't find controller for category " + feedCategory.getFcCode();
            return Result.ok(new ResultForFeed<>(initFeed(), error, new HashMap<>(), new ArrayList<>()));
        }

        CianConverter converter = converterByCategory.get(categoryCode);
        if (converter == null) {
            String error = "Can't find converter for category " + feedCategory.getFcCode();
            return Result.ok(new ResultForFeed<>(initFeed(), error, new HashMap<>(), new ArrayList<>()));
        }


        Feed feed = initFeed();
        String error = "";
        HashMap<Long, String> objUnidsWithErrors = new HashMap<>();
        List<Long> badObjUnids = new ArrayList<>();
        List<? extends FeedObject> objectsForFeed = controller.getObjectsByCategoryFeedNameAndRent(feedCategory.getFcCode(), feedCategory.getFcIndRent() >= 1, subUnid);
        logger.info("getObjectsByCategoryFeedNameAndRent objectsForFeed count = {}, fcCode = {}, indRent = {}, subUnid = {}",
                objectsForFeed.size(), feedCategory.getFcCode(), feedCategory.getFcIndRent() >= 1, subUnid);
        for (FeedObject objectForFeed : objectsForFeed) {
            objUnidsWithErrors.put(objectForFeed.getObjUnid(), "");
            try {
                List<Picture> pictures = pictureController.getPicturesForExportToFtpWithLimit(objectForFeed.getObjUnid(), MAX_EXPORT_TO_SFTP_PHOTO_COUNT);
                objectForFeed.setPictureCount(pictures.size());
                Result<String, String> result = converter.objValidCheck(objectForFeed);
                if (result.isSuccess()) {
                    Object obj = converter.makeObject(objectForFeed);

                    obj.setPhotos(makePhotos(pictures, exportWithWatermark(objectForFeed.getLsUnid())));
                    if (exportWithSuffixDescription(objectForFeed.getLsUnid())) {
                        obj.setDescription(SUFFIX_FOR_ASV_DESCRIPTION + " " + obj.getDescription());
                    }
                    feed.getObject().add(obj);
                    objUnidsWithErrors.put(objectForFeed.getObjUnid(), result.getResult());
                } else {
                    error += (objectForFeed.getObjUnid() + ": Required field is missing\n");
                    objUnidsWithErrors.put(objectForFeed.getObjUnid(), result.getError());
                    badObjUnids.add(objectForFeed.getObjUnid());
                }
            } catch (Exception ex) {
                logger.warn("Can't add object. objUnid = {}", objectForFeed.getObjUnid(), ex);
                error += (objectForFeed.getObjUnid() + ":" + ex.getMessage() + "\n");
                objUnidsWithErrors.put(objectForFeed.getObjUnid(), ex.getMessage());
                badObjUnids.add(objectForFeed.getObjUnid());
            }

        }
        logger.info("getObjectsByCategoryFeedNameAndRent error = {}", error);
        if (feed.getObject().isEmpty()) {
            String noObjectsForFeedNoteText = "Can't find any correct objects in category " + feedCategory.getFcCode();
            return Result.ok(new ResultForFeed<>(initFeed(), noObjectsForFeedNoteText, objUnidsWithErrors, badObjUnids));
        }
        return Result.ok(new ResultForFeed<>(feed, error, objUnidsWithErrors, badObjUnids));
    }

//    @Override
//    public List<Long> getObjUnidListByFcUnid(Long fcUnid) {
//        return vCianRealEstateController.getObjUnidListByFcUnid(fcUnid);
//    }

    private Feed initFeed() {
        Feed feed = new Feed();
        feed.setFeedVersion(BigInteger.valueOf(FEED_VERSION));
        return feed;
    }

    private Boolean exportWithWatermark(Long lsUnid) {
        return lsUnid != null
                && (lsUnid.equals(LoadSourceConstant.ASV.getId()) || lsUnid.equals(LoadSourceConstant.ASVZ.getId()));
    }

    private Boolean exportWithSuffixDescription(Long lsUnid) {
        return lsUnid != null && lsUnid.equals(LoadSourceConstant.ASV.getId());
    }

    private Photos makePhotos(List<Picture> pictureList, boolean withWatermark) {
        if (pictureList.isEmpty()) {
            return null;
        }
        Photos photos = new Photos();
        for (Picture pic : pictureList) {
            if (pic.getDfUnid().getDfFtpPath() != null) {
                PhotoSchema photoSchema = new PhotoSchema();
                String fullPath;
                if (withWatermark) {
                    fullPath = baseUrl + pic.getDfUnid().getDfFtpPath().replace(".", "_wm.");
                } else {
                    fullPath = baseUrl + pic.getDfUnid().getDfFtpPath();
                }
                photoSchema.setFullUrl(fullPath);
                photoSchema.setIsDefault(false);
                photos.getPhotoSchema().add(photoSchema);
            }
        }
        Optional<PhotoSchema> firstPhoto = photos.getPhotoSchema().stream()
                .findFirst();
        firstPhoto.ifPresent(photoSchema -> photoSchema.setIsDefault(true));
        return photos;
    }
}
