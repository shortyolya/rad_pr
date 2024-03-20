package com.baltinfo.radius.feed.avito.converter;

import com.baltinfo.radius.db.constants.MarketingEvent;
import com.baltinfo.radius.db.model.VAvitoRealEstate;
import com.baltinfo.radius.feed.avito.constant.AvitoObjectType;
import com.baltinfo.radius.feed.avito.constant.VAvitoRealEstateParams;
import com.baltinfo.radius.feed.avito.model.Ad;
import com.baltinfo.radius.feed.services.FeedDescriptionService;
import com.baltinfo.radius.utils.AreaConverter;
import com.baltinfo.radius.utils.Result;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class AvitoLandPlotConverter extends VAvitoRealEstateConverter implements AvitoConverter<VAvitoRealEstate> {

    private static final List<Pair<VAvitoRealEstateParams, Boolean>> params =
            new ArrayList<Pair<VAvitoRealEstateParams, Boolean>>() {{
                add(new Pair(VAvitoRealEstateParams.TS_UNID, true));
                add(new Pair(VAvitoRealEstateParams.OBJ_IND_RENT, true));
                add(new Pair(VAvitoRealEstateParams.PRICE, true));
                add(new Pair(VAvitoRealEstateParams.DESCRIPTION, true));
                add(new Pair(VAvitoRealEstateParams.OBJ_CODE, true));
                add(new Pair(VAvitoRealEstateParams.ADDRESS, true));
                add(new Pair(VAvitoRealEstateParams.CATEGORY_FEED_CODE, true));
                add(new Pair(VAvitoRealEstateParams.LAND_AREA, true));
                add(new Pair(VAvitoRealEstateParams.PICTURE_COUNT, false));
                add(new Pair(VAvitoRealEstateParams.SUBAGENT_NAME_F, false));
                add(new Pair(VAvitoRealEstateParams.SUBAGENT_NAME_I, false));
                add(new Pair(VAvitoRealEstateParams.SUBAGENT_MOBILE_PHONE, false));
                add(new Pair(VAvitoRealEstateParams.OBJECT_TYPE, true));
            }};

    public AvitoLandPlotConverter(FeedDescriptionService feedDescriptionService) {
        super(feedDescriptionService);
    }

    @Override
    public Ad makeAd(VAvitoRealEstate avitoObj, Long mevUnid) {
        Ad ad = fillGeneralProperties(avitoObj);
        ad.setObjectType(AvitoObjectType.getByValue(avitoObj.getScUnid()).getObjectType());
        ad.setLandArea(AreaConverter.SQUARE_METER.convertTo(AreaConverter.SOTKA, avitoObj.getLandArea()));
        ad.setManagerName(createManagerName(avitoObj.getSubagentNameF(), avitoObj.getSubagentNameI()));
        ad.setContactPhone(avitoObj.getSubagentMobilePhone());
        ad.setAllowEmail(AvitoDefaultValues.DEFAULT_ALLOW_EMAIL);
        ad.setObjectType(avitoObj.getObjectType());
        if (mevUnid.equals(MarketingEvent.AVITO.getUnid())) {
            ad.setListingFee(AvitoDefaultValues.DEFAULT_LISTING_FEE);
        }
        return ad;
    }

    @Override
    public Result<String, String> adValidCheck(VAvitoRealEstate avitoObj) {
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
