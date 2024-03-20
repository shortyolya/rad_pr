package com.baltinfo.radius.feed.cian.converter;

import com.baltinfo.radius.db.model.FeedObject;
import com.baltinfo.radius.feed.cian.model.Object;
import com.baltinfo.radius.utils.Result;

public interface CianConverter<T extends FeedObject> {

    Object makeObject(T cianObj);

    Result<String, String> objValidCheck(T cianObj);
}
