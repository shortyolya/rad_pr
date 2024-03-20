package com.baltinfo.radius.feed.avito.converter;

import com.baltinfo.radius.db.constants.MarketingEvent;
import com.baltinfo.radius.db.model.VAvitoMovable;
import com.baltinfo.radius.feed.avito.constant.VAvitoMovableParams;
import com.baltinfo.radius.feed.avito.model.Ad;
import com.baltinfo.radius.feed.services.FeedDescriptionService;
import com.baltinfo.radius.utils.Result;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Suvorina Aleksandra
 * @since 21.09.2020
 */
public class AvitoBusinessEquipmentConverter extends VAvitoMovableConverter implements AvitoConverter<VAvitoMovable> {

    private static final String INDUSTRIAL_CATEGORY = "Промышленное";
    private static final String TRADE_CATEGORY = "Торговое";
    private static final List<String> TRADE_INDUSTRIAL_CATEGORY_CODES = Arrays.asList("202001", "202003");

    private static final List<Pair<VAvitoMovableParams, Boolean>> params = new ArrayList<Pair<VAvitoMovableParams, Boolean>>() {{
        add(new Pair(VAvitoMovableParams.TS_UNID, true));
        add(new Pair(VAvitoMovableParams.TITLE, true));
        add(new Pair(VAvitoMovableParams.DESCRIPTION, true));
        add(new Pair(VAvitoMovableParams.OBJ_CODE, true));
        add(new Pair(VAvitoMovableParams.ADDRESS, true));
        add(new Pair(VAvitoMovableParams.CATEGORY_CODE, true));
        add(new Pair(VAvitoMovableParams.PRICE, true));
        add(new Pair(VAvitoMovableParams.CATEGORY_FEED_CODE, true));
        add(new Pair(VAvitoMovableParams.PICTURE_COUNT, false));
        add(new Pair(VAvitoMovableParams.SUBAGENT_NAME_F, false));
        add(new Pair(VAvitoMovableParams.SUBAGENT_NAME_I, false));
        add(new Pair(VAvitoMovableParams.SUBAGENT_MOBILE_PHONE, false));
    }};

    private Map<String, String> goodTypeByCategory;

    public AvitoBusinessEquipmentConverter(FeedDescriptionService feedDescriptionService) {
        super(feedDescriptionService);
        goodTypeByCategory = new HashMap<>();
        goodTypeByCategory.put("202004", "Другое");
        goodTypeByCategory.put("203007", "Другое");
        goodTypeByCategory.put("203008", "Другое");
        goodTypeByCategory.put("203011", "Другое");
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
        if (INDUSTRIAL_CATEGORY.equalsIgnoreCase(avitoObj.getGoodsType())) {
            ad.setGoodsType(avitoObj.getGoodsType());
            ad.setGoodsPromType(avitoObj.getGoodsSubTypeNew());
            switch(avitoObj.getGoodsSubTypeNew()) {
                case "Приводное":
                    ad.setPrivodType(avitoObj.getGoodsSubSubType());
                    break;
                case "Электрическое":
                    ad.setElectricType(avitoObj.getGoodsSubSubType());
                    break;
                case "Станки":
                    ad.setStanokType(avitoObj.getGoodsSubSubType());
                    break;
                case "Контрольно-измерительное":
                    ad.setIzmerenieType(avitoObj.getGoodsSubSubType());
                    break;
                case "Конвейеры и транспортеры":
                    ad.setKonveierType(avitoObj.getGoodsSubSubType());
                    break;
                case "Паяльное и сварочное":
                    ad.setSvarkaType(avitoObj.getGoodsSubSubType());
                    break;
                case "Насосы и компрессоры":
                    ad.setNasosType(avitoObj.getGoodsSubSubType());
                    break;
                case "Климатическое":
                    ad.setKlimatType(avitoObj.getGoodsSubSubType());
                    break;
                case "Фасовочное и упаковочное":
                    ad.setPackType(avitoObj.getGoodsSubSubType());
                    break;
                case "Специализированное":
                    ad.setSpecType(avitoObj.getGoodsSubSubType());
                    break;
            }
        } else if(TRADE_CATEGORY.equalsIgnoreCase(avitoObj.getGoodsType())) {
            ad.setGoodsType(avitoObj.getGoodsType());
            ad.setGoodsSubType(avitoObj.getGoodsSubTypeNew());
            switch(avitoObj.getGoodsSubTypeNew()) {
                case "Мебель":
                    ad.setFurnitureType(avitoObj.getGoodsSubSubType());
                    break;
                case "Расчетно-кассовое":
                    ad.setCashierType(avitoObj.getGoodsSubSubType());
                    break;
                case "Рекламное":
                    ad.setAdvertisingType(avitoObj.getGoodsSubSubType());
                    break;
                case "Павильоны и фудтраки":
                    ad.setPavilionsType(avitoObj.getGoodsSubSubType());
                    break;
                case "Развлекательное":
                    ad.setEntertainingType(avitoObj.getGoodsSubSubType());
                    break;
                case "Торговый инвентарь":
                    ad.setInventoryType(avitoObj.getGoodsSubSubType());
                    break;
            }
        } else {
            ad.setGoodsType(goodTypeByCategory.get(avitoObj.getCategoryCode()));
        }
        ad.setCondition("Б/у");
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

        if (checkStringFieldAvailability(avitoObj.getCategoryCode()) &&
                !goodTypeByCategory.containsKey(avitoObj.getCategoryCode())) {
            errorList.add(formSyntaxError(VAvitoMovableParams.CATEGORY_CODE.getFieldName(), VAvitoMovableParams.CATEGORY_CODE.getFieldDescr(), true));
        }
        if (TRADE_INDUSTRIAL_CATEGORY_CODES.stream().anyMatch(c-> c.equals(avitoObj.getCategoryCode()))) {
            if (checkStringFieldAvailability(avitoObj.getGoodsType())) {
                errorList.add(formSyntaxError(VAvitoMovableParams.GOODS_TYPE.getFieldName(), VAvitoMovableParams.GOODS_TYPE.getFieldDescr(), true));
            }
            if (checkStringFieldAvailability(avitoObj.getGoodsSubTypeNew())) {
                errorList.add(formSyntaxError(VAvitoMovableParams.GOODS_SUB_TYPE.getFieldName(), VAvitoMovableParams.GOODS_SUB_TYPE.getFieldDescr(), true));
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
