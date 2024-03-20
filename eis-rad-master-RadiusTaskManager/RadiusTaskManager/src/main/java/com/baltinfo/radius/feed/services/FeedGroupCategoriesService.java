package com.baltinfo.radius.feed.services;

import com.baltinfo.radius.db.controller.CategoryMatcherController;
import com.baltinfo.radius.db.controller.FeedAdController;
import com.baltinfo.radius.db.model.FeedCategory;
import com.baltinfo.radius.db.model.FeedCategoryGroup;
import com.baltinfo.radius.feed.utils.ResultForFeed;
import com.baltinfo.radius.utils.Result;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FeedGroupCategoriesService extends FeedService {

    private final CategoryMatcherController categoryMatcherController;
    private final FeedAdController feedAdController;
    private final AllCategoriesFeedXmlService feedXmlService;

    public FeedGroupCategoriesService(CategoryMatcherController categoryMatcherController,
                                      FeedAdController feedAdController,
                                      AllCategoriesFeedXmlService feedXmlService) {
        this.categoryMatcherController = categoryMatcherController;
        this.feedAdController = feedAdController;
        this.feedXmlService = feedXmlService;
    }

    @Override
    public void formFeed(Long mevUnid, Long subUnid) {
        List<FeedCategory> categories = categoryMatcherController.findByMevUnid(mevUnid);
        List<FeedCategoryGroup> fcgList = categoryMatcherController.getActualGroups();
        for (FeedCategoryGroup fcg : fcgList) {
            Long fcgUnid = fcg.getFcgUnid();
            List<FeedCategory> categoryList = categories.stream()
                    .filter(category -> Objects.equals(category.getFcgUnid(), fcgUnid))
                    .collect(Collectors.toList());
            Result<ResultForFeed, String> result = feedXmlService.getFeed(categoryList, subUnid);
            ResultForFeed resultForFeed = result.getResult();
            Result<String, String> xmlResult = getXml(resultForFeed);
            if (xmlResult.isError()) {
                feedAdController.createBadAd(xmlResult.getError(),
                        mevUnid,
                        null,
                        fcgUnid,
                        subUnid,
                        resultForFeed.getObjUnidsWithErrors().keySet());
            } else {
                feedAdController.createGoodAd(xmlResult.getResult(),
                        resultForFeed.getError(),
                        mevUnid,
                        null,
                        fcgUnid,
                        subUnid,
                        resultForFeed.getObjUnidsWithErrors(),
                        resultForFeed.getBadObjUnids());
            }
        }
        feedAdController.createFeedAdWithCategoryError(mevUnid, subUnid);
    }
}
