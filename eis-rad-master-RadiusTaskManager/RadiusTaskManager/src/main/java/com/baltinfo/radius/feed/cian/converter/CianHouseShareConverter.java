package com.baltinfo.radius.feed.cian.converter;

import com.baltinfo.radius.db.model.VCianRealEstate;
import com.baltinfo.radius.feed.cian.constant.VCianRealEstateParams;
import com.baltinfo.radius.feed.cian.model.Object;
import com.baltinfo.radius.feed.services.FeedDescriptionService;
import com.baltinfo.radius.utils.Result;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Lapenok
 * @since 22.03.2023
 */
public class CianHouseShareConverter extends CianHouseConverter {

    private static final List<Pair<VCianRealEstateParams, Boolean>> params =
            new ArrayList<Pair<VCianRealEstateParams, Boolean>>() {{
                add(new Pair(VCianRealEstateParams.TS_UNID, true));
                add(new Pair(VCianRealEstateParams.OBJ_CODE, true));
                add(new Pair(VCianRealEstateParams.SUBAGENT_MOBILE_PHONE, true));
                add(new Pair(VCianRealEstateParams.TITLE, true));
                add(new Pair(VCianRealEstateParams.DESCRIPTION, true));
                add(new Pair(VCianRealEstateParams.ADDRESS, true));
                add(new Pair(VCianRealEstateParams.SUBAGENT_EMAIL, true));
                add(new Pair(VCianRealEstateParams.CATEGORY_FEED_NAME, true));
                add(new Pair(VCianRealEstateParams.LAND_AREA, true));
                add(new Pair(VCianRealEstateParams.TOTAL_AREA, true));
                add(new Pair(VCianRealEstateParams.SHARE_AMOUNT, true));
                add(new Pair(VCianRealEstateParams.PRICE, true));
                add(new Pair(VCianRealEstateParams.PICTURE_COUNT, false));
                add(new Pair(VCianRealEstateParams.COST_IND_TAX, false));
                add(new Pair(VCianRealEstateParams.SUBAGENT_NAME_F, false));
                add(new Pair(VCianRealEstateParams.SUBAGENT_NAME_I, false));
            }};

    public CianHouseShareConverter(FeedDescriptionService feedDescriptionService) {
        super(feedDescriptionService);
    }

    @Override
    public Object makeObject(VCianRealEstate cianObj) {
        Object obj = fillGeneralProperties(cianObj);
        obj.setBargainTerms(makeBargainTerms(cianObj));
        obj.setBuilding(makeBuilding(cianObj));
        obj.setLand(makeLand(cianObj.getLandStatus(), Double.valueOf(cianObj.getLandArea())));
        obj.setTotalArea(cianObj.getTotalArea());
        obj.setShareAmount(cianObj.getShareAmount());
        return obj;
    }

    @Override
    public Result<String, String> objValidCheck(VCianRealEstate cianObj) {
        Result<List<Pair<String, Boolean>>, List<Pair<String, Boolean>>> checkResult =
                adValidCheck(cianObj, params);
        boolean isCheckSuccess = checkResult.isSuccess();
        List<Pair<String, Boolean>> errorList = isCheckSuccess ? checkResult.getResult() : checkResult.getError();

        String errorStr = formErrorStrFromPairList(errorList);
        return isCheckSuccess ? Result.ok(errorStr) : Result.error(errorStr);
    }
}
