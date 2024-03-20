package com.baltinfo.radius.feed.services.impl;

import com.baltinfo.radius.db.constants.AvitoCategoryCode;
import com.baltinfo.radius.db.constants.LoadSourceConstant;
import com.baltinfo.radius.db.controller.AvitoController;
import com.baltinfo.radius.db.controller.PictureController;
import com.baltinfo.radius.db.model.FeedCategory;
import com.baltinfo.radius.db.model.FeedObject;
import com.baltinfo.radius.db.model.Picture;
import com.baltinfo.radius.feed.avito.constant.AvitoMaxPhotoCount;
import com.baltinfo.radius.feed.avito.converter.AvitoConverter;
import com.baltinfo.radius.feed.avito.model.Ad;
import com.baltinfo.radius.feed.avito.model.Ads;
import com.baltinfo.radius.feed.avito.model.Image;
import com.baltinfo.radius.feed.avito.model.Images;
import com.baltinfo.radius.feed.services.AllCategoriesFeedXmlService;
import com.baltinfo.radius.feed.utils.ResultForFeed;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AvitoFeedXmlService implements AllCategoriesFeedXmlService {

    private final Logger logger = LoggerFactory.getLogger(AvitoFeedXmlService.class);

    private final Map<AvitoCategoryCode, AvitoController<? extends FeedObject>> controllerByCategory;
    private final Map<AvitoCategoryCode, AvitoConverter> converterByCategory;
    private final PictureController pictureController;
    private final String baseUrl;
    private static final String SUFFIX_FOR_ASV_DESCRIPTION = "Имущество реализуется по поручению ГК \"Агентство по страхованию вкладов\".";

    public AvitoFeedXmlService(Map<AvitoCategoryCode, AvitoController<? extends FeedObject>> controllerByCategory,
                               Map<AvitoCategoryCode, AvitoConverter> converterByCategory,
                               PictureController pictureController, String baseUrl) {
        this.controllerByCategory = controllerByCategory;
        this.converterByCategory = converterByCategory;
        this.pictureController = Objects.requireNonNull(pictureController, "Can't load pictureController");
        this.baseUrl = baseUrl;
    }

    public Result<ResultForFeed, String> getFeed(List<FeedCategory> categories, Long subUnid) {
        List<Ad> adList = new ArrayList<>();
        HashMap<Long, String> objUnidsWithErrors = new HashMap<>();
        List<Long> badObjUnids = new ArrayList<>();
        StringBuilder error = new StringBuilder();
        for (FeedCategory feedCategory : categories) {

            AvitoCategoryCode categoryCode;
            try {
                categoryCode = AvitoCategoryCode.getByValue(feedCategory.getFcCode());
            } catch (IllegalArgumentException ex) {
                error.append("Incorrect category ")
                        .append(feedCategory.getFcCode())
                        .append("\n");
                continue;
            }

            AvitoController<? extends FeedObject> controller = controllerByCategory.get(categoryCode);
            if (controller == null) {
                error.append("Can't find controller for category ")
                        .append(feedCategory.getFcCode())
                        .append("\n");
                continue;
            }

            AvitoConverter converter = converterByCategory.get(categoryCode);
            if (converter == null) {
                error.append("Can't find converter for category ")
                        .append(feedCategory.getFcCode())
                        .append("\n");
                continue;
            }

            List<? extends FeedObject> objectsForFeed =
                    controller.getObjectsByCategoryFeedName(categoryCode.getCode(), feedCategory.getMevUnid(), subUnid);
            int photoLimit = AvitoMaxPhotoCount.getByValue(feedCategory.getFcUnid()).getPhotoCount();
            for (FeedObject objectForFeed : objectsForFeed) {
                objUnidsWithErrors.put(objectForFeed.getObjUnid(), "");

                try {
                    List<Picture> pictures = pictureController
                            .getPicturesForExportToFtpWithLimit(objectForFeed.getObjUnid(), photoLimit);
                    objectForFeed.setPictureCount(pictures.size());
                    Result<String, String> result = converter.adValidCheck(objectForFeed);
                    if (result.isSuccess()) {
                        Ad ad = converter.makeAd(objectForFeed, feedCategory.getMevUnid());

                        ad.setImages(makeImages(pictures, exportWithWatermark(objectForFeed.getLsUnid())));
                        if (exportWithSuffixDescription(objectForFeed.getLsUnid())) {
                            ad.setDescription(SUFFIX_FOR_ASV_DESCRIPTION + " " + ad.getDescription());
                        }
                        adList.add(ad);
                        objUnidsWithErrors.put(objectForFeed.getObjUnid(), result.getResult());
                    } else {
                        error.append(objectForFeed.getObjUnid())
                                .append(": Required field is missing\n");
                        objUnidsWithErrors.put(objectForFeed.getObjUnid(), result.getError());
                        badObjUnids.add(objectForFeed.getObjUnid());
                    }
                } catch (Exception ex) {
                    logger.warn("Can't add object. obj_unid = {} ", objectForFeed.getObjUnid(), ex);
                    error.append(objectForFeed.getObjUnid())
                            .append(":")
                            .append(ex.getMessage())
                            .append("\n");
                    objUnidsWithErrors.put(objectForFeed.getObjUnid(), ex.getMessage());
                    badObjUnids.add(objectForFeed.getObjUnid());
                }

            }
        }
        if (adList.isEmpty()) {
            error.append("Can't find any correct objects");
        }
        Ads ads = new Ads(adList);
        return Result.ok(new ResultForFeed<>(ads, error.toString(), objUnidsWithErrors, badObjUnids));
    }

    private Boolean exportWithWatermark(Long lsUnid) {
        return lsUnid != null
                && (lsUnid.equals(LoadSourceConstant.ASV.getId()) || lsUnid.equals(LoadSourceConstant.ASVZ.getId()));
    }

    private Boolean exportWithSuffixDescription(Long lsUnid) {
        return lsUnid != null && lsUnid.equals(LoadSourceConstant.ASV.getId());
    }

    private Images makeImages(List<Picture> pictureList, Boolean withWatermark) {
        if (pictureList.isEmpty()) {
            return null;
        }
        Images images = new Images();
        for (Picture pic : pictureList) {
            if (!StringUtils.isEmpty(pic.getDfUnid().getDfFtpPath())) {
                Image image = new Image();
                if (withWatermark) {
                    image.setUrl(baseUrl + pic.getDfUnid().getDfFtpPath().replace(".", "_wm."));
                } else {
                    image.setUrl(baseUrl + pic.getDfUnid().getDfFtpPath());
                }
                images.getImageList().add(image);
            }
        }
        return images;
    }
}
