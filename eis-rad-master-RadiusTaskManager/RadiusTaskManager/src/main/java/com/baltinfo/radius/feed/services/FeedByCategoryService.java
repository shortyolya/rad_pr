package com.baltinfo.radius.feed.services;

import com.baltinfo.radius.db.controller.CategoryMatcherController;
import com.baltinfo.radius.db.controller.FeedAdController;
import com.baltinfo.radius.db.model.FeedCategory;
import com.baltinfo.radius.feed.utils.ResultForFeed;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 08.09.2020
 */
public class FeedByCategoryService extends FeedService {

    private static final Logger logger = LoggerFactory.getLogger(FeedByCategoryService.class);

    private final CategoryMatcherController categoryMatcherController;
    private final FeedAdController feedAdController;
    private final FeedXmlService feedXmlService;

    public FeedByCategoryService(CategoryMatcherController categoryMatcherController,
                                 FeedAdController feedAdController,
                                 FeedXmlService feedXmlService) {
        this.categoryMatcherController = categoryMatcherController;
        this.feedAdController = feedAdController;
        this.feedXmlService = feedXmlService;
    }

    @Override
    public void formFeed(Long mevUnid, Long subUnid) {
        List<FeedCategory> categories = categoryMatcherController.findByMevUnid(mevUnid);
        for (FeedCategory category : categories) {
            Result<ResultForFeed, String> result = feedXmlService.getFeed(category, subUnid);
            ResultForFeed resultForFeed = result.getResult();
            Result<String, String> xmlResult = getXml(resultForFeed);
            if (xmlResult.isError()) {
                feedAdController.createBadAd(xmlResult.getError(),
                        mevUnid,
                        category.getFcUnid(),
                        subUnid,
                        resultForFeed.getObjUnidsWithErrors().keySet());
            } else {
                feedAdController.createGoodAd(xmlResult.getResult(),
                        resultForFeed.getError(),
                        mevUnid, category.getFcUnid(),
                        subUnid,
                        resultForFeed.getObjUnidsWithErrors(),
                        resultForFeed.getBadObjUnids());
            }
        }
        feedAdController.createFeedAdWithCategoryError(mevUnid, subUnid);
    }

}
