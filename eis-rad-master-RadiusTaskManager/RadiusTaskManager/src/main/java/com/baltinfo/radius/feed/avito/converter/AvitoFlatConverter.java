package com.baltinfo.radius.feed.avito.converter;

import com.baltinfo.radius.db.constants.MarketingEvent;
import com.baltinfo.radius.db.model.VAvitoRealEstate;
import com.baltinfo.radius.feed.avito.constant.VAvitoRealEstateParams;
import com.baltinfo.radius.feed.avito.model.Ad;
import com.baltinfo.radius.feed.services.FeedDescriptionService;
import com.baltinfo.radius.utils.Result;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class AvitoFlatConverter extends VAvitoRealEstateConverter implements AvitoConverter<VAvitoRealEstate> {

    private final String FLAT_STATUS = "Квартира";
    private final String APARTMENTS_STATUS = "Апартаменты";
    private final String DEVELOPMENT_MARKET_TYPE = "Новостройка";
    private final String SECONDARY_MARKET_TYPE = "Вторичка";
    private final String DEVELOPMENT_ID_ERROR = "Отсутствует ID новостройки для \"MarketType\" = " + DEVELOPMENT_MARKET_TYPE;
    private final long FLAT_SC_UNID = 34;

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
                add(new Pair(VAvitoRealEstateParams.HOUSE_TYPE, true));
                add(new Pair(VAvitoRealEstateParams.MARKET_TYPE, true));
                add(new Pair(VAvitoRealEstateParams.PICTURE_COUNT, false));
                add(new Pair(VAvitoRealEstateParams.SUBAGENT_NAME_F, false));
                add(new Pair(VAvitoRealEstateParams.SUBAGENT_NAME_I, false));
                add(new Pair(VAvitoRealEstateParams.SUBAGENT_MOBILE_PHONE, false));
                add(new Pair(VAvitoRealEstateParams.BATHROOM_MULTI, false));
                add(new Pair(VAvitoRealEstateParams.RENOVATION, false));
            }};

    public AvitoFlatConverter(FeedDescriptionService feedDescriptionService) {
        super(feedDescriptionService);
    }

    @Override
    public Ad makeAd(VAvitoRealEstate realEstateFromDB, Long mevUnid) {
        Ad flat = fillGeneralProperties(realEstateFromDB);
        flat.setRooms(realEstateFromDB.getRooms());
        flat.setSquare(realEstateFromDB.getSquare());
        flat.setFloor(String.valueOf(Double.valueOf(realEstateFromDB.getFloor()).intValue()));
        flat.setFloors(Double.valueOf(realEstateFromDB.getFloors()).intValue());
        flat.setHouseType(realEstateFromDB.getHouseType());
        flat.setMarketType(realEstateFromDB.getMarketType());
        flat.setManagerName(createManagerName(realEstateFromDB.getSubagentNameF(), realEstateFromDB.getSubagentNameI()));
        flat.setContactPhone(realEstateFromDB.getSubagentMobilePhone());
        flat.setAllowEmail(AvitoDefaultValues.DEFAULT_ALLOW_EMAIL);
        flat.setBathroomMulti(realEstateFromDB.getBathroomMulti());
        if (realEstateFromDB.getMarketType().equals(DEVELOPMENT_MARKET_TYPE)) {
            flat.setNewDevelopmentId(realEstateFromDB.getDevelopmentId());
            flat.setDecoration(realEstateFromDB.getDecoration());
        } else if (realEstateFromDB.getMarketType().equals(SECONDARY_MARKET_TYPE)) {
            flat.setRenovation(realEstateFromDB.getRenovation());
        }
        if (realEstateFromDB.getObjIndRent() != null && realEstateFromDB.getObjIndRent()) {
            flat.setLeaseType(AvitoDefaultValues.DEFAULT_LEASE_TYPE);
            flat.setRenovation(realEstateFromDB.getRenovation());
        } else {
            flat.setStatus(realEstateFromDB.getScUnid() == FLAT_SC_UNID ? FLAT_STATUS : APARTMENTS_STATUS);
            flat.setKitchenSpace(Double.valueOf(realEstateFromDB.getKitchenSpace()));
            flat.setBalconyOrLoggiaMulti(realEstateFromDB.getBalconyOrLoggiaMulti());
            flat.setDealType(realEstateFromDB.getDealType());
            flat.setRoomType(realEstateFromDB.getRoomType());
        }
        if (mevUnid.equals(MarketingEvent.AVITO.getUnid())) {
            flat.setListingFee(AvitoDefaultValues.DEFAULT_LISTING_FEE);
        }
        return flat;
    }

    @Override
    public Result<String, String> adValidCheck(VAvitoRealEstate avitoObj) {
        Result<List<Pair<String, Boolean>>, List<Pair<String, Boolean>>> checkResult =
                adValidCheck(avitoObj, params);
        boolean isCheckSuccess = checkResult.isSuccess();
        List<Pair<String, Boolean>> errorList = isCheckSuccess ? checkResult.getResult() : checkResult.getError();

        if (avitoObj.getMarketType() != null &&
                avitoObj.getMarketType().equals(DEVELOPMENT_MARKET_TYPE) &&
                avitoObj.getDevelopmentId() == null) {
            errorList.add(new Pair(DEVELOPMENT_ID_ERROR, true));
            isCheckSuccess = false;
        }
        if (avitoObj.getMarketType() != null &&
                avitoObj.getMarketType().equals(SECONDARY_MARKET_TYPE) &&
                (!checkStringFieldAvailability(avitoObj.getBathroomMulti()))) {
            errorList.add(formAvailabilityError(VAvitoRealEstateParams.BATHROOM_MULTI.getFieldName(), VAvitoRealEstateParams.BATHROOM_MULTI.getFieldDescr(), true));
            isCheckSuccess = false;
        }
        if (avitoObj.getMarketType() != null &&
                avitoObj.getMarketType().equals(SECONDARY_MARKET_TYPE) &&
                (!checkStringFieldAvailability(avitoObj.getRenovation()))) {
            errorList.add(formAvailabilityError(VAvitoRealEstateParams.RENOVATION.getFieldName(), VAvitoRealEstateParams.RENOVATION.getFieldDescr(), true));
            isCheckSuccess = false;
        }
        if (avitoObj.getObjIndRent() == null || !avitoObj.getObjIndRent()) {
            if (avitoObj.getKitchenSpace() == null) {
                errorList.add(formAvailabilityError(VAvitoRealEstateParams.KITCHEN_SPACE.getFieldName(), VAvitoRealEstateParams.KITCHEN_SPACE.getFieldDescr(), true));
                isCheckSuccess = false;
            } else {
                try {
                    Double kitchenSpace = Double.valueOf(avitoObj.getKitchenSpace());
                } catch(NumberFormatException e) {
                    errorList.add(formSyntaxError(VAvitoRealEstateParams.KITCHEN_SPACE.getFieldName(), VAvitoRealEstateParams.KITCHEN_SPACE.getFieldDescr(), true));
                    isCheckSuccess = false;
                }
            }
            if (!checkStringFieldAvailability(avitoObj.getBalconyOrLoggiaMulti())) {
                errorList.add(formAvailabilityError(VAvitoRealEstateParams.BALCONY_OR_LOGGIA_MULTI.getFieldName(), VAvitoRealEstateParams.BALCONY_OR_LOGGIA_MULTI.getFieldDescr(), true));
                isCheckSuccess = false;
            }
            if (!checkStringFieldAvailability(avitoObj.getDealType())) {
                errorList.add(formAvailabilityError(VAvitoRealEstateParams.DEAL_TYPE.getFieldName(), VAvitoRealEstateParams.DEAL_TYPE.getFieldDescr(), true));
                isCheckSuccess = false;
            }
            if (!checkStringFieldAvailability(avitoObj.getRoomType())) {
                errorList.add(formAvailabilityError(VAvitoRealEstateParams.ROOM_TYPE.getFieldName(), VAvitoRealEstateParams.ROOM_TYPE.getFieldDescr(), true));
                isCheckSuccess = false;
            }
        }
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
