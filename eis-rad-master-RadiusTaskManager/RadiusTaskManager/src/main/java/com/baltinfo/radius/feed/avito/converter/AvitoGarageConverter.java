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

public class AvitoGarageConverter extends VAvitoRealEstateConverter implements AvitoConverter<VAvitoRealEstate> {

    private static final String GARAGE = "Гараж";
    private static final String PARKING = "Машиноместо";

    private static final List<Pair<VAvitoRealEstateParams, Boolean>> params =
            new ArrayList<Pair<VAvitoRealEstateParams, Boolean>>() {{
                add(new Pair(VAvitoRealEstateParams.TS_UNID, true));
                add(new Pair(VAvitoRealEstateParams.OBJ_IND_RENT, true));
                add(new Pair(VAvitoRealEstateParams.PRICE, true));
                add(new Pair(VAvitoRealEstateParams.DESCRIPTION, true));
                add(new Pair(VAvitoRealEstateParams.OBJ_CODE, true));
                add(new Pair(VAvitoRealEstateParams.ADDRESS, true));
                add(new Pair(VAvitoRealEstateParams.CATEGORY_FEED_CODE, true));
                add(new Pair(VAvitoRealEstateParams.SECURED, true));
                add(new Pair(VAvitoRealEstateParams.SQUARE, true));
                add(new Pair(VAvitoRealEstateParams.PICTURE_COUNT, false));
                add(new Pair(VAvitoRealEstateParams.TITLE, false));
                add(new Pair(VAvitoRealEstateParams.SUBAGENT_NAME_F, false));
                add(new Pair(VAvitoRealEstateParams.SUBAGENT_NAME_I, false));
                add(new Pair(VAvitoRealEstateParams.SUBAGENT_MOBILE_PHONE, false));
            }};

    public AvitoGarageConverter(FeedDescriptionService feedDescriptionService) {
        super(feedDescriptionService);
    }

    @Override
    public Ad makeAd(VAvitoRealEstate avitoObj, Long mevUnid) {
        Ad ad = fillGeneralProperties(avitoObj);
        ad.setObjectType(avitoObj.getTypeOfGarage());
        ad.setObjectSubtype(avitoObj.getTypeOfGarage().equals(PARKING) ? avitoObj.getTypeOfParking() : avitoObj.getMaterialOfGarage());
        ad.setSecured(avitoObj.getSecured());
        ad.setManagerName(createManagerName(avitoObj.getSubagentNameF(), avitoObj.getSubagentNameI()));
        ad.setContactPhone(avitoObj.getSubagentMobilePhone());
        ad.setAllowEmail(AvitoDefaultValues.DEFAULT_ALLOW_EMAIL);
        ad.setSquare(avitoObj.getSquare());
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

        if (!checkStringFieldAvailability(avitoObj.getTypeOfGarage())) {
            errorList.add(formAvailabilityError(VAvitoRealEstateParams.TYPE_OF_GARAGE.getFieldName(), VAvitoRealEstateParams.TYPE_OF_GARAGE.getFieldDescr(), true));
        } else if (avitoObj.getTypeOfGarage().equals(PARKING) && avitoObj.getTypeOfParking() == null) {
            errorList.add(formAvailabilityError(VAvitoRealEstateParams.TYPE_OF_PARKING.getFieldName(), VAvitoRealEstateParams.TYPE_OF_PARKING.getFieldDescr(), true));
        } else if (avitoObj.getTypeOfGarage().equals(GARAGE) && avitoObj.getMaterialOfGarage() == null) {
            errorList.add(formAvailabilityError(VAvitoRealEstateParams.MATERIAL_OF_GARAGE.getFieldName(), VAvitoRealEstateParams.MATERIAL_OF_GARAGE.getFieldDescr(), true));
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
