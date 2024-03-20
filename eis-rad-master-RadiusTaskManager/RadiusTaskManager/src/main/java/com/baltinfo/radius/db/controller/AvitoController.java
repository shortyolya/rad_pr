package com.baltinfo.radius.db.controller;

import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 21.09.2020
 */
public interface AvitoController<FeedObject> {

    List<FeedObject> getObjectsByCategoryFeedName(String categoryFeedCode, Long mevUnid, Long subUnid);

    List<Long> getObjUnidListByFcUnid(Long fcUnid);

}
