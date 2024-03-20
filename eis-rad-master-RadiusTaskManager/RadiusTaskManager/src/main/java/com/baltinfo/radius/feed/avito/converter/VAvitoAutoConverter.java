package com.baltinfo.radius.feed.avito.converter;

import com.baltinfo.radius.db.constants.TypeStateConstant;
import com.baltinfo.radius.db.model.VAvitoAuto;
import com.baltinfo.radius.feed.avito.constant.VAvitoAutoParams;
import com.baltinfo.radius.feed.services.FeedDescriptionService;
import com.baltinfo.radius.utils.Result;
import javafx.util.Pair;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class VAvitoAutoConverter {

    private static final String PICTURE_COUNT_ERROR = "Отсутствуют фотографии для выгрузки";
    private static final String TS_UNID_ERROR = "Объект в данном статусе не выгружается на доску";
    private static final String MOBILEPHONE_REGEX = "(?:\\+|\\d)[\\d\\-\\(\\) ]{9,}\\d";
    protected final FeedDescriptionService feedDescriptionService;

    private static final List<Long> errorTsUnidList = new ArrayList<>(Arrays.asList(
            TypeStateConstant.OBJ_SALED.getId(),
            TypeStateConstant.ARCHIVE.getId(),
            TypeStateConstant.TRADE_DONE.getId(),
            TypeStateConstant.TRADE_CANCELED.getId(),
            TypeStateConstant.SOLD_WAITING_FOR_CONTRACT.getId(),
            TypeStateConstant.REFUSAL_OF_DKP.getId()));

    protected VAvitoAutoConverter(FeedDescriptionService feedDescriptionService) {
        this.feedDescriptionService = feedDescriptionService;
    }

    protected Result<List<Pair<String, Boolean>>, List<Pair<String, Boolean>>> adValidCheck(VAvitoAuto auto,
                                                                                            List<Pair<VAvitoAutoParams, Boolean>> params) {
        List<Pair<String, Boolean>> errorList = new ArrayList<>();
        boolean isFatalError = false;
        for (Pair<VAvitoAutoParams, Boolean> param : params) {
            switch (param.getKey()) {
                case OME_UNID:
                case OBJ_UNID:
                case MEV_UNID:
                    break; // 05.09.2021 ksv: Always not null
                case OBJ_CODE:
                    if (!checkStringFieldAvailability(auto.getObjCode())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case LS_UNID:
                    if (auto.getLsUnid() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case SC_UNID:
                    if (auto.getScUnid() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case FC_UNID:
                    if (auto.getFcUnid() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case CATEGORY:
                    if (!checkStringFieldAvailability(auto.getCategory())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case CATEGORY_FEED_CODE:
                    if (!checkStringFieldAvailability(auto.getCategoryFeedCode())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case DESCRIPTION:
                    if (!checkStringFieldAvailability(auto.getDescription())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case MAKE:
                    if (!checkStringFieldAvailability(auto.getMake())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case MODEL:
                    if (!checkStringFieldAvailability(auto.getModel())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case YEAR:
                    if (auto.getYear() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    } else {
                        try {
                            Double year = Double.parseDouble(auto.getYear());
                        } catch (NumberFormatException e) {
                            errorList.add(formSyntaxError(param));
                            isFatalError = true;
                        }
                    }
                    break;
                case KILOMETRAGE:
                    if (auto.getKilometrage() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    } else {
                        try {
                            Double kilometrage = Double.parseDouble(auto.getKilometrage());
                        } catch (NumberFormatException e) {
                            errorList.add(formSyntaxError(param));
                            isFatalError = true;
                        }
                    }
                    break;
                case ACCIDENT:
                    if (!checkStringFieldAvailability(auto.getAccident())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case VIN:
                    if (!checkStringFieldAvailability(auto.getVin())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case BODY_TYPE:
                    if (!checkStringFieldAvailability(auto.getBodyType())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case DOORS:
                    if (auto.getDoors() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    } else {
                        try{
                            Long doors = Long.parseLong(auto.getDoors());
                        } catch (NumberFormatException e) {
                            errorList.add(formSyntaxError(param));
                            isFatalError = true;
                        }
                    }
                    break;
                case GENERATION_ID:
                    if (auto.getGenerationId() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case MODIFICATION_ID:
                    if (auto.getModificationId() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case COLOR:
                    if (!checkStringFieldAvailability(auto.getColor())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case FUEL_TYPE:
                    if (!checkStringFieldAvailability(auto.getFuelType())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case ENGINE_SIZE:
                    if (auto.getEngineSize() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    } else {
                        try {
                            Double engineSize = Double.parseDouble(auto.getEngineSize());
                        } catch (NumberFormatException e) {
                            errorList.add(formSyntaxError(param));
                            isFatalError = true;
                        }
                    }
                    break;
                case POWER:
                    if (auto.getPower() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    } else {
                        try {
                            Double power = Double.valueOf(auto.getPower());
                        } catch (NumberFormatException e) {
                            errorList.add(formSyntaxError(param));
                            isFatalError = true;
                        }
                    }
                    break;
                case TRANSMISSION:
                    if (!checkStringFieldAvailability(auto.getTransmission())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case DRIVE_TYPE:
                    if (!checkStringFieldAvailability(auto.getDriveType())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case WHEEL_TYPE:
                    if (!checkStringFieldAvailability(auto.getWheelType())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case OWNERS:
                    if (auto.getOwners() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    } else {
                        try {
                            Double owners = Double.parseDouble(auto.getOwners());
                        } catch (NumberFormatException e) {
                            errorList.add(formSyntaxError(param));
                            isFatalError = true;
                        }
                    }
                    break;
                case PRICE:
                    if (auto.getPrice() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case ADDRESS:
                    if (!checkStringFieldAvailability(auto.getAddress())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case SUBAGENT_MOBILE_PHONE:
                    if (!checkStringFieldAvailability(auto.getSubagentMobilePhone())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    } else if (!auto.getSubagentMobilePhone().matches(MOBILEPHONE_REGEX)) {
                        errorList.add(formSyntaxError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                        ;
                    }
                    break;
                case SUBAGENT_NAME_F:
                    if (!checkStringFieldAvailability(auto.getSubagentNameF())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case SUBAGENT_NAME_I:
                    if (!checkStringFieldAvailability(auto.getSubagentNameI())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case LISTING_FEE:
                    if (!checkStringFieldAvailability(auto.getListingFee())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case OBJ_INT_RENT:
                    if (auto.getObjIndRent() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case TS_UNID:
                    if (auto.getTsUnid() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    } else if (errorTsUnidList.contains(auto.getTsUnid())) {
                        errorList.add(new Pair<>(TS_UNID_ERROR, param.getValue()));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case PICTURE_COUNT:
                    if (auto.getPictureCount() == 0) {
                        errorList.add(new Pair<>(PICTURE_COUNT_ERROR, param.getValue()));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return isFatalError ? Result.error(errorList) : Result.ok(errorList);
    }

    protected boolean checkStringFieldAvailability(String str) {
        return StringUtils.hasText(str);
    }

    private Pair<String, Boolean> formAvailabilityError(Pair<VAvitoAutoParams, Boolean> param) {
        return new Pair<>("Поле \"" + param.getKey().getFieldName() + "\" (" + param.getKey().getFieldDescr() + ") не заполнено", param.getValue());
    }

    private Pair<String, Boolean> formSyntaxError(Pair<VAvitoAutoParams, Boolean> param) {
        return new Pair<>("Поле \"" + param.getKey().getFieldName() + "\" (" + param.getKey().getFieldDescr() + ") заполнено некорректно", param.getValue());
    }

    protected String formErrorStrFromPairList(List<Pair<String, Boolean>> errorList) {
        return errorList.stream()
                .sorted((p1, p2) -> Boolean.compare(p2.getValue(), p1.getValue()))
                .map(Pair::getKey)
                .collect(Collectors.joining("\n"));
    }
}
