package com.baltinfo.radius.feed.avito.converter;

import com.baltinfo.radius.db.constants.MarketingEvent;
import com.baltinfo.radius.db.model.VAvitoAuto;
import com.baltinfo.radius.feed.avito.constant.VAvitoAutoParams;
import com.baltinfo.radius.feed.avito.model.Ad;
import com.baltinfo.radius.feed.services.FeedDescriptionService;
import com.baltinfo.radius.utils.Result;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Kulikov Semyon
 * @since 27.11.2020
 */

public class AvitoAutoConverter extends VAvitoAutoConverter implements AvitoConverter<VAvitoAuto> {

    private final String DEFAULT_CAR_TYPE = "С пробегом";

    private static final List<Pair<VAvitoAutoParams, Boolean>> params = new ArrayList<Pair<VAvitoAutoParams, Boolean>>() {{
        add(new Pair(VAvitoAutoParams.TS_UNID, true));
        add(new Pair(VAvitoAutoParams.PRICE, true));
        add(new Pair(VAvitoAutoParams.DESCRIPTION, true));
        add(new Pair(VAvitoAutoParams.OBJ_CODE, true));
        add(new Pair(VAvitoAutoParams.ADDRESS, true));
        add(new Pair(VAvitoAutoParams.CATEGORY_FEED_CODE, true));
        add(new Pair(VAvitoAutoParams.SUBAGENT_NAME_F, true));
        add(new Pair(VAvitoAutoParams.SUBAGENT_NAME_I, true));
        add(new Pair(VAvitoAutoParams.SUBAGENT_MOBILE_PHONE, true));
        add(new Pair(VAvitoAutoParams.MAKE, true));
        add(new Pair(VAvitoAutoParams.MODEL, true));
        add(new Pair(VAvitoAutoParams.YEAR, true));
        add(new Pair(VAvitoAutoParams.KILOMETRAGE, true));
        add(new Pair(VAvitoAutoParams.ACCIDENT, true));
        add(new Pair(VAvitoAutoParams.VIN, true));
        add(new Pair(VAvitoAutoParams.BODY_TYPE, true));
        add(new Pair(VAvitoAutoParams.DOORS, true));
        add(new Pair(VAvitoAutoParams.GENERATION_ID, true));
        add(new Pair(VAvitoAutoParams.MODIFICATION_ID, true));
        add(new Pair(VAvitoAutoParams.COLOR, true));
        add(new Pair(VAvitoAutoParams.WHEEL_TYPE, true));
        add(new Pair(VAvitoAutoParams.OWNERS, true));
        add(new Pair(VAvitoAutoParams.PICTURE_COUNT, false));
        add(new Pair(VAvitoAutoParams.FUEL_TYPE, false));
        add(new Pair(VAvitoAutoParams.ENGINE_SIZE, false));
        add(new Pair(VAvitoAutoParams.POWER, false));
        add(new Pair(VAvitoAutoParams.TRANSMISSION, false));
        add(new Pair(VAvitoAutoParams.DRIVE_TYPE, false));
    }};

    public AvitoAutoConverter(FeedDescriptionService feedDescriptionService) {
        super(feedDescriptionService);
    }

    @Override
    public Ad makeAd(VAvitoAuto avitoObj, Long mevUnid) {
        Ad ad = new Ad();
        ad.setId(avitoObj.getObjCode());
        ad.setPrice(avitoObj.getPrice());
        ad.setDescription(feedDescriptionService.formDescription(avitoObj));
        ad.setAddress(avitoObj.getAddress());
        ad.setCategory(avitoObj.getCategoryFeedCode());
        ad.setCarType(DEFAULT_CAR_TYPE);
        ad.setManagerName(createManagerName(avitoObj.getSubagentNameF(), avitoObj.getSubagentNameI()));
        ad.setContactPhone(avitoObj.getSubagentMobilePhone());
        ad.setMake(avitoObj.getMake());
        ad.setModel(avitoObj.getModel());
        ad.setYear(Double.valueOf(avitoObj.getYear()));
        ad.setKilometrage(Double.valueOf(avitoObj.getKilometrage()));
        ad.setAccident(avitoObj.getAccident());
        ad.setVin(avitoObj.getVin());
        ad.setBodyType(avitoObj.getBodyType());
        ad.setDoors(Long.valueOf(avitoObj.getDoors()));
        ad.setGenerationId(avitoObj.getGenerationId());
        ad.setModificationId(avitoObj.getModificationId());
        ad.setColor(avitoObj.getColor());
        ad.setFuelType(avitoObj.getFuelType());
        ad.setEngineSize(Double.valueOf(avitoObj.getEngineSize()));
        ad.setPower(Double.valueOf(avitoObj.getPower()).longValue());
        ad.setTransmission(avitoObj.getTransmission());
        ad.setDriveType(avitoObj.getDriveType());
        ad.setWheelType(avitoObj.getWheelType());
        ad.setOwners(Double.valueOf(avitoObj.getOwners()));
        ad.setAllowEmail(AvitoDefaultValues.DEFAULT_ALLOW_EMAIL);
        if (mevUnid.equals(MarketingEvent.AVITO.getUnid())) {
            ad.setListingFee(avitoObj.getListingFee() != null && !avitoObj.getListingFee().isEmpty()
                    ? avitoObj.getListingFee()
                    : AvitoDefaultValues.DEFAULT_LISTING_FEE);
        }
        return ad;
    }

    @Override
    public Result<String, String> adValidCheck(VAvitoAuto avitoObj) {
        Result<List<Pair<String, Boolean>>, List<Pair<String, Boolean>>> checkResult =
                adValidCheck(avitoObj, params);
        boolean isCheckSuccess = checkResult.isSuccess();
        List<Pair<String, Boolean>> errorList = isCheckSuccess ? checkResult.getResult() : checkResult.getError();

        String errorStr = formErrorStrFromPairList(errorList);
        return isCheckSuccess ? Result.ok(errorStr) : Result.error(errorStr);
    }

    private String createManagerName(String surname, String name) {
        return Stream.of(surname, name)
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining(" "));
    }
}
