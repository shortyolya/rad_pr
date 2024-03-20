package com.baltinfo.radius.feed.avito.converter;

import com.baltinfo.radius.db.constants.MarketingEvent;
import com.baltinfo.radius.db.model.VAvitoRealEstate;
import com.baltinfo.radius.feed.avito.constant.AvitoObjectType;
import com.baltinfo.radius.feed.avito.constant.VAvitoRealEstateParams;
import com.baltinfo.radius.feed.avito.model.Ad;
import com.baltinfo.radius.feed.services.FeedDescriptionService;
import com.baltinfo.radius.utils.Result;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AvitoCommercialRealEstateConverter extends VAvitoRealEstateConverter implements AvitoConverter<VAvitoRealEstate> {

    private static final HashMap<String, String> floorsAdditional = new HashMap<String, String>() {{
        put("-1.00", "Цокольный");
        put("-2.00", "Подвальный");
    }};

    private static final List<Pair<VAvitoRealEstateParams, Boolean>> params =
            new ArrayList<Pair<VAvitoRealEstateParams, Boolean>>() {{
                add(new Pair(VAvitoRealEstateParams.TS_UNID, true));
                add(new Pair(VAvitoRealEstateParams.OBJ_IND_RENT, true));
                add(new Pair(VAvitoRealEstateParams.PRICE, true));
                add(new Pair(VAvitoRealEstateParams.DESCRIPTION, true));
                add(new Pair(VAvitoRealEstateParams.OBJ_CODE, true));
                add(new Pair(VAvitoRealEstateParams.ADDRESS, true));
                add(new Pair(VAvitoRealEstateParams.CATEGORY_FEED_CODE, true));
                add(new Pair(VAvitoRealEstateParams.SQUARE, true));
                add(new Pair(VAvitoRealEstateParams.PICTURE_COUNT, false));
                add(new Pair(VAvitoRealEstateParams.TITLE, false));
                add(new Pair(VAvitoRealEstateParams.SUBAGENT_NAME_F, false));
                add(new Pair(VAvitoRealEstateParams.SUBAGENT_NAME_I, false));
                add(new Pair(VAvitoRealEstateParams.SUBAGENT_MOBILE_PHONE, false));
                add(new Pair(VAvitoRealEstateParams.BUILDING_TYPE, true));
                add(new Pair(VAvitoRealEstateParams.FLOOR, false));
                add(new Pair(VAvitoRealEstateParams.DECORATION_COMM, true));
                add(new Pair(VAvitoRealEstateParams.PARKING_TYPE, true));
                add(new Pair(VAvitoRealEstateParams.ENTRANCE, true));
                add(new Pair(VAvitoRealEstateParams.LAYOUT, false));
            }};

    public AvitoCommercialRealEstateConverter(FeedDescriptionService feedDescriptionService) {
        super(feedDescriptionService);
    }

    @Override
    public Ad makeAd(VAvitoRealEstate avitoObj, Long mevUnid) {
        Ad ad = fillGeneralProperties(avitoObj);
        ad.setTitle(avitoObj.getTitle());
        ad.setObjectType(AvitoObjectType.getByValue(avitoObj.getScUnid()).getObjectType());
        ad.setSquare(avitoObj.getSquare());
        ad.setFloor(getFloor(avitoObj.getFloor()));
        ad.setManagerName(createManagerName(avitoObj.getSubagentNameF(), avitoObj.getSubagentNameI()));
        ad.setContactPhone(avitoObj.getSubagentMobilePhone());
        ad.setAllowEmail(AvitoDefaultValues.DEFAULT_ALLOW_EMAIL);
        ad.setBuildingType(avitoObj.getBuildingType());
        ad.setDecoration(avitoObj.getDecorationComm());
        if (checkSaleCategory(avitoObj.getScUnid(), VAvitoRealEstateParams.PARKING_TYPE.getSaleCategories())) {
            ad.setParkingType(avitoObj.getParkingType());
        }
        if (checkSaleCategory(avitoObj.getScUnid(), VAvitoRealEstateParams.ENTRANCE.getSaleCategories())) {
            ad.setEntrance(avitoObj.getEntrance());
        }
        if (checkSaleCategory(avitoObj.getScUnid(), VAvitoRealEstateParams.LAYOUT.getSaleCategories())) {
            ad.setLayout(avitoObj.getLayout());
        }
        if (avitoObj.getObjIndRent() != null && avitoObj.getObjIndRent()) {
            ad.setRentalType("Прямая");
        } else {
            ad.setTransactionType("Продажа");
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

        if (avitoObj.getFloor() != null && !floorsAdditional.keySet().contains(avitoObj.getFloor())) {
            try {
                Double.valueOf(avitoObj.getFloor());
            } catch (NumberFormatException e) {
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

    private String getFloor(String floorFromDb) {
        if (floorFromDb == null) {
            return null;
        }
        String floor = floorsAdditional.get(floorFromDb);
        if (floor != null) {
            return floor;
        }
        return String.valueOf(Double.valueOf(floorFromDb).intValue());
    }
}
