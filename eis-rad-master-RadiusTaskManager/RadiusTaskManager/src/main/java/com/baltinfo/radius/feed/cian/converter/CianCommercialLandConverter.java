package com.baltinfo.radius.feed.cian.converter;

import com.baltinfo.radius.db.model.VCianRealEstate;
import com.baltinfo.radius.feed.cian.constant.VCianRealEstateParams;
import com.baltinfo.radius.feed.cian.model.CianLandUnitType;
import com.baltinfo.radius.feed.cian.model.Land;
import com.baltinfo.radius.feed.cian.model.Object;
import com.baltinfo.radius.feed.cian.model.SubAgent;
import com.baltinfo.radius.feed.services.FeedDescriptionService;
import com.baltinfo.radius.utils.AreaConverter;
import com.baltinfo.radius.utils.Result;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kulikov Semyon
 * @since 30.10.2020
 */

public class CianCommercialLandConverter extends CianRealEstateConverter implements CianConverter<VCianRealEstate> {

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
                add(new Pair(VCianRealEstateParams.LAND_SQR, true));
                add(new Pair(VCianRealEstateParams.PRICE, true));
                add(new Pair(VCianRealEstateParams.LAND_STATUS, true));
                add(new Pair(VCianRealEstateParams.PICTURE_COUNT, false));
                add(new Pair(VCianRealEstateParams.COST_IND_TAX, false));
                add(new Pair(VCianRealEstateParams.SUBAGENT_NAME_F, false));
                add(new Pair(VCianRealEstateParams.SUBAGENT_NAME_I, false));
            }};

    public CianCommercialLandConverter(FeedDescriptionService feedDescriptionService) {
        super(feedDescriptionService);
    }

    @Override
    protected SubAgent makeSubAgent(VCianRealEstate cianObj) {
        SubAgent subAgent = new SubAgent();
        subAgent.setEmail(cianObj.getSubAgentEmail());
        subAgent.setPhone(cianObj.getSubAgentMobilePhone());
        subAgent.setFirstName(cianObj.getSubAgentNameI());
        subAgent.setLastName(cianObj.getSubAgentNameF());
        return subAgent;
    }

    @Override
    public Object makeObject(VCianRealEstate cianObj) {
        Object obj = fillGeneralProperties(cianObj);
        obj.setLand(makeLand(cianObj.getLandStatus(), cianObj.getLandSqr()));
        obj.setBargainTerms(makeBargainTerms(cianObj));
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
