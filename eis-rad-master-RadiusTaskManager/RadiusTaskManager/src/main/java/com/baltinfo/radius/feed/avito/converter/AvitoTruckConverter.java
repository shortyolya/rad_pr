package com.baltinfo.radius.feed.avito.converter;

import com.baltinfo.radius.db.constants.MarketingEvent;
import com.baltinfo.radius.db.model.VAvitoMovable;
import com.baltinfo.radius.feed.avito.constant.VAvitoMovableParams;
import com.baltinfo.radius.feed.avito.model.Ad;
import com.baltinfo.radius.feed.services.FeedDescriptionService;
import com.baltinfo.radius.utils.Result;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Suvorina Aleksandra
 * @since 21.09.2020
 */
public class AvitoTruckConverter extends VAvitoMovableConverter implements AvitoConverter<VAvitoMovable> {

    private Map<String, String> goodTypeByCategory;

    private final String TRUCK_CODE = "201002";

    private static final List<Pair<VAvitoMovableParams, Boolean>> params = new ArrayList<Pair<VAvitoMovableParams, Boolean>>() {{
        add(new Pair(VAvitoMovableParams.TS_UNID, true));
        add(new Pair(VAvitoMovableParams.TITLE, true));
        add(new Pair(VAvitoMovableParams.DESCRIPTION, true));
        add(new Pair(VAvitoMovableParams.OBJ_CODE, true));
        add(new Pair(VAvitoMovableParams.ADDRESS, true));
        add(new Pair(VAvitoMovableParams.CATEGORY_FEED_CODE, true));
        add(new Pair(VAvitoMovableParams.PICTURE_COUNT, false));
        add(new Pair(VAvitoMovableParams.SUBAGENT_NAME_F, false));
        add(new Pair(VAvitoMovableParams.SUBAGENT_NAME_I, false));
        add(new Pair(VAvitoMovableParams.SUBAGENT_MOBILE_PHONE, false));
    }};

    public AvitoTruckConverter(FeedDescriptionService feedDescriptionService) {
        super(feedDescriptionService);
        goodTypeByCategory = new HashMap<>();
        goodTypeByCategory.put("201002", "Грузовики");
        goodTypeByCategory.put("201004", "Автобусы");
        goodTypeByCategory.put("201005", "Прицепы");
        goodTypeByCategory.put("201007", "Лёгкий транспорт");
        goodTypeByCategory.put("201008", "Прицепы");
        goodTypeByCategory.put("201009001", "Бульдозеры");
        goodTypeByCategory.put("201009002", "Сельхозтехника");
        goodTypeByCategory.put("201009003", "Коммунальная техника");
        goodTypeByCategory.put("201009004", "Техника для лесозаготовки");
        goodTypeByCategory.put("201010", "Грузовики");
    }

    public Ad makeAd(VAvitoMovable avitoObj, Long mevUnid) {
        Ad ad = new Ad();
        ad.setId(avitoObj.getObjCode());
        ad.setAddress(avitoObj.getAddress());
        ad.setTitle(avitoObj.getTitle());
        ad.setDescription(feedDescriptionService.formDescription(avitoObj));
        ad.setCategory(avitoObj.getCategoryFeedCode());
        ad.setPrice(avitoObj.getPrice());
        ad.setGoodsType(goodTypeByCategory.get(avitoObj.getCategoryCode()));
        ad.setCondition("Б/у");
        ad.setManagerName(createManagerName(avitoObj.getSubagentNameF(), avitoObj.getSubagentNameI()));
        ad.setContactPhone(avitoObj.getSubagentMobilePhone());
        if (avitoObj.getMake() != null && !avitoObj.getMake().isEmpty()) {
            ad.setMake(avitoObj.getMake());
            ad.setModel(avitoObj.getModel());
            ad.setBodyType(avitoObj.getBodyType());
            ad.setTechnicalPassport(avitoObj.getTechnicalPassport());
            ad.setYear(Double.valueOf(avitoObj.getYear()));
        }
        ad.setAllowEmail(AvitoDefaultValues.DEFAULT_ALLOW_EMAIL);
        ad.setAvailability("В наличии");
        ad.setVin(avitoObj.getVin());
        ad.setKilometrage(avitoObj.getKilometrage());
        if (avitoObj.getTypeOfVehicle() != null && !avitoObj.getTypeOfVehicle().isEmpty()) {
            ad.setTypeOfVehicle(avitoObj.getTypeOfVehicle());
        }
        if (avitoObj.getTypeOfTrailer() != null && !avitoObj.getTypeOfTrailer().isEmpty()) {
            ad.setTypeOfTrailer(avitoObj.getTypeOfTrailer());
        }
        if (mevUnid.equals(MarketingEvent.AVITO.getUnid())) {
            ad.setListingFee(avitoObj.getListingFee() != null && !avitoObj.getListingFee().isEmpty()
                    ? avitoObj.getListingFee()
                    : AvitoDefaultValues.DEFAULT_LISTING_FEE);
        }
        return ad;
    }

    public Result<String, String> adValidCheck(VAvitoMovable avitoObj) {
        Result<List<Pair<String, Boolean>>, List<Pair<String, Boolean>>> checkResult;
        if (avitoObj.getCategoryCode().equals(TRUCK_CODE)) {
            checkResult = adValidCheck(avitoObj, formTruckParams());
        } else {
            checkResult = adValidCheck(avitoObj, params);
        }
        boolean isCheckSuccess = checkResult.isSuccess();
        List<Pair<String, Boolean>> errorList = isCheckSuccess ? checkResult.getResult() : checkResult.getError();

        if (checkStringFieldAvailability(avitoObj.getCategoryCode()) &&
                !goodTypeByCategory.containsKey(avitoObj.getCategoryCode())) {
            errorList.add(formSyntaxError(VAvitoMovableParams.CATEGORY_CODE.getFieldName(), VAvitoMovableParams.CATEGORY_CODE.getFieldDescr(), true));
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

    private List<Pair<VAvitoMovableParams, Boolean>> formTruckParams() {
        List<Pair<VAvitoMovableParams, Boolean>> truckParams = new ArrayList<>(params);
        truckParams.addAll(new ArrayList<Pair<VAvitoMovableParams, Boolean>>() {{
            add(new Pair(VAvitoMovableParams.MAKE, true));
            add(new Pair(VAvitoMovableParams.MODEL, true));
            add(new Pair(VAvitoMovableParams.BODY_TYPE, true));
            add(new Pair(VAvitoMovableParams.TECHNICAL_PASSPORT, true));
            add(new Pair(VAvitoMovableParams.YEAR, true));
        }});
        return truckParams;
    }
}
