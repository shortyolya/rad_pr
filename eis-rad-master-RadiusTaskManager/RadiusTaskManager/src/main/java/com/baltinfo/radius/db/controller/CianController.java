package com.baltinfo.radius.db.controller;

import java.util.List;

/**
 * @author Kulikov Semyon
 * @since 30.10.2020
 */

public interface CianController<FeedObject> {
    List<FeedObject> getObjectsByCategoryFeedNameAndRent(String categoryFeedCode, Boolean isRent, Long subUnid);
}
