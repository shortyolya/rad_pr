package com.baltinfo.radius.feed.avito.converter;

import com.baltinfo.radius.db.model.FeedObject;
import com.baltinfo.radius.feed.avito.model.Ad;
import com.baltinfo.radius.utils.Result;

/**
 * @author Suvorina Aleksandra
 * @since 21.09.2020
 */
public interface AvitoConverter<T extends FeedObject> {

    Ad makeAd(T avitoObj, Long mevUnid);

    Result<String, String> adValidCheck(T avitoObj);

}
