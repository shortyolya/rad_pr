package com.baltinfo.radius.feed.avito.converter;

import com.baltinfo.radius.db.constants.MarketingEvent;
import com.baltinfo.radius.db.model.VAvitoMovable;
import com.baltinfo.radius.feed.avito.constant.VAvitoMovableParams;
import com.baltinfo.radius.feed.avito.model.Ad;
import com.baltinfo.radius.feed.services.FeedDescriptionService;
import com.baltinfo.radius.utils.Result;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 23.09.2020
 */
public class AvitoBusinessConverter extends VAvitoMovableConverter implements AvitoConverter<VAvitoMovable> {

    private static final List<Pair<VAvitoMovableParams, Boolean>> params = new ArrayList<Pair<VAvitoMovableParams, Boolean>>() {{
        add(new Pair(VAvitoMovableParams.TS_UNID, true));
        add(new Pair(VAvitoMovableParams.TITLE, true));
        add(new Pair(VAvitoMovableParams.DESCRIPTION, true));
        add(new Pair(VAvitoMovableParams.OBJ_CODE, true));
        add(new Pair(VAvitoMovableParams.ADDRESS, true));
        add(new Pair(VAvitoMovableParams.CATEGORY_FEED_CODE, true));
        add(new Pair(VAvitoMovableParams.PRICE, true));
        add(new Pair(VAvitoMovableParams.PICTURE_COUNT, false));
        add(new Pair(VAvitoMovableParams.SUBAGENT_NAME_F, false));
        add(new Pair(VAvitoMovableParams.SUBAGENT_NAME_I, false));
        add(new Pair(VAvitoMovableParams.SUBAGENT_MOBILE_PHONE, false));
    }};

    public AvitoBusinessConverter(FeedDescriptionService feedDescriptionService) {
        super(feedDescriptionService);
    }

    @Override
    public Ad makeAd(VAvitoMovable avitoObj, Long mevUnid) {
        Ad ad = new Ad();
        ad.setId(avitoObj.getObjCode());
        ad.setAddress(avitoObj.getAddress());
        ad.setTitle(avitoObj.getTitle());
        ad.setDescription(feedDescriptionService.formDescription(avitoObj, false));
        ad.setCategory(avitoObj.getCategoryFeedCode());
        ad.setPrice(avitoObj.getPrice());
        ad.setGoodsType("Другое");
        ad.setDealGoal("Продать бизнес");
        ad.setManagerName(createManagerName(avitoObj.getSubagentNameF(), avitoObj.getSubagentNameI()));
        ad.setContactPhone(avitoObj.getSubagentMobilePhone());
        ad.setAllowEmail(AvitoDefaultValues.DEFAULT_ALLOW_EMAIL);
        if (mevUnid.equals(MarketingEvent.AVITO.getUnid())) {
            ad.setListingFee(avitoObj.getListingFee() != null && !avitoObj.getListingFee().isEmpty()
                    ? avitoObj.getListingFee()
                    : AvitoDefaultValues.DEFAULT_LISTING_FEE);
        }
        return ad;
    }

    @Override
    public Result<String, String> adValidCheck(VAvitoMovable avitoObj) {
        Result<List<Pair<String, Boolean>>, List<Pair<String, Boolean>>> checkResult =
                adValidCheck(avitoObj, params);
        boolean isCheckSuccess = checkResult.isSuccess();
        List<Pair<String, Boolean>> errorList = isCheckSuccess ? checkResult.getResult() : checkResult.getError();

        String errorStr = formErrorStrFromPairList(errorList);
        return isCheckSuccess ? Result.ok(errorStr) : Result.error(errorStr);
    }

    private String createManagerName(String surname, String name) {
        if (surname != null) {
            if (name != null) {
                return surname + " " + name;
            } else {
                return surname;
            }
        } else {
            return name;
        }
    }
}
