package com.baltinfo.radius.feed.cian.converter;

import com.baltinfo.radius.db.model.VCianRealEstate;
import com.baltinfo.radius.feed.cian.constant.VCianRealEstateParams;
import com.baltinfo.radius.feed.cian.model.Building;
import com.baltinfo.radius.feed.cian.model.Object;
import com.baltinfo.radius.feed.services.FeedDescriptionService;
import com.baltinfo.radius.utils.Result;
import javafx.util.Pair;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kulikov Semyon
 * @since 30.10.2020
 */

public class CianOfficeConverter extends CianRealEstateConverter implements CianConverter<VCianRealEstate> {

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
                add(new Pair(VCianRealEstateParams.FLOORS_COUNT, true));
                add(new Pair(VCianRealEstateParams.TOTAL_AREA, true));
                add(new Pair(VCianRealEstateParams.PRICE, true));
                add(new Pair(VCianRealEstateParams.FLOOR_NUMBER, true));
                add(new Pair(VCianRealEstateParams.PICTURE_COUNT, false));
                add(new Pair(VCianRealEstateParams.COST_IND_TAX, false));
                add(new Pair(VCianRealEstateParams.SUBAGENT_NAME_F, false));
                add(new Pair(VCianRealEstateParams.SUBAGENT_NAME_I, false));
            }};

    public CianOfficeConverter(FeedDescriptionService feedDescriptionService) {
        super(feedDescriptionService);
    }

    @Override
    public Object makeObject(VCianRealEstate cianObj) {
        Object obj = fillGeneralProperties(cianObj);
        obj.setBargainTerms(makeBargainTerms(cianObj));
        obj.setBuilding(makeBuilding(cianObj));
        obj.setFloorNumber(BigInteger.valueOf(Double.valueOf(cianObj.getFloorNumber()).longValue()));
        obj.setTotalArea(cianObj.getTotalArea());
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


    private Building makeBuilding(VCianRealEstate cianObj) {
        Building building = new Building();
        building.setFloorsCount(BigInteger.valueOf(Double.valueOf(cianObj.getFloorsCount()).longValue()));
        return building;
    }
}
