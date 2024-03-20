package com.baltinfo.radius.feed.avito.converter;

import com.baltinfo.radius.db.constants.MarketingEvent;
import com.baltinfo.radius.db.model.VAvitoRealEstate;
import com.baltinfo.radius.feed.avito.constant.VAvitoRealEstateParams;
import com.baltinfo.radius.feed.avito.model.Ad;
import com.baltinfo.radius.feed.services.FeedDescriptionService;
import com.baltinfo.radius.utils.Result;
import javafx.util.Pair;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AvitoRoomConverter extends VAvitoRealEstateConverter implements AvitoConverter<VAvitoRealEstate> {


    private static final List<Pair<VAvitoRealEstateParams, Boolean>> params =
            new ArrayList<Pair<VAvitoRealEstateParams, Boolean>>() {{
                add(new Pair(VAvitoRealEstateParams.TS_UNID, true));
                add(new Pair(VAvitoRealEstateParams.OBJ_IND_RENT, true));
                add(new Pair(VAvitoRealEstateParams.PRICE, true));
                add(new Pair(VAvitoRealEstateParams.DESCRIPTION, true));
                add(new Pair(VAvitoRealEstateParams.OBJ_CODE, true));
                add(new Pair(VAvitoRealEstateParams.ADDRESS, true));
                add(new Pair(VAvitoRealEstateParams.CATEGORY_FEED_CODE, true));
                add(new Pair(VAvitoRealEstateParams.ROOMS, true));
                add(new Pair(VAvitoRealEstateParams.SQUARE, true));
                add(new Pair(VAvitoRealEstateParams.FLOOR, true));
                add(new Pair(VAvitoRealEstateParams.FLOORS, true));
                add(new Pair(VAvitoRealEstateParams.PICTURE_COUNT, false));
                add(new Pair(VAvitoRealEstateParams.HOUSE_TYPE, false));
                add(new Pair(VAvitoRealEstateParams.SUBAGENT_NAME_F, false));
                add(new Pair(VAvitoRealEstateParams.SUBAGENT_NAME_I, false));
                add(new Pair(VAvitoRealEstateParams.SUBAGENT_MOBILE_PHONE, false));
            }};

    public AvitoRoomConverter(FeedDescriptionService feedDescriptionService) {
        super(feedDescriptionService);
    }

    @Override
    public Ad makeAd(VAvitoRealEstate avitoObj, Long mevUnid) {
        Ad ad = fillGeneralProperties(avitoObj);
        ad.setRooms(avitoObj.getRooms());
        ad.setSquare(avitoObj.getSquare());
        ad.setFloor(String.valueOf(Double.valueOf(avitoObj.getFloor()).intValue()));
        ad.setFloors(Double.valueOf(avitoObj.getFloors()).intValue());
        ad.setManagerName(createManagerName(avitoObj.getSubagentNameF(), avitoObj.getSubagentNameI()));
        ad.setContactPhone(avitoObj.getSubagentMobilePhone());
        ad.setHouseType(StringUtils.hasText(avitoObj.getHouseType()) ? avitoObj.getHouseType() : null);
        ad.setAllowEmail(AvitoDefaultValues.DEFAULT_ALLOW_EMAIL);
        if (avitoObj.getObjIndRent() != null && avitoObj.getObjIndRent()) {
            ad.setLeaseType(AvitoDefaultValues.DEFAULT_LEASE_TYPE);
        }
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

        if (avitoObj.getFloor() != null) {
            try {
                Double.valueOf(avitoObj.getFloor());
            } catch (NumberFormatException ex) {
                errorList.add(formSyntaxError(VAvitoRealEstateParams.FLOOR.getFieldName(), VAvitoRealEstateParams.FLOOR.getFieldDescr(), true));
                isCheckSuccess = false;
            }
        }

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
