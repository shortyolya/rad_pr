package com.baltinfo.radius.feed.avito.converter;

import com.baltinfo.radius.db.constants.TypeStateConstant;
import com.baltinfo.radius.db.model.VAvitoMovable;
import com.baltinfo.radius.feed.avito.constant.VAvitoMovableParams;
import com.baltinfo.radius.feed.services.FeedDescriptionService;
import com.baltinfo.radius.utils.Result;
import javafx.util.Pair;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class VAvitoMovableConverter {

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

    protected VAvitoMovableConverter(FeedDescriptionService feedDescriptionService) {
        this.feedDescriptionService = feedDescriptionService;
    }

    protected Result<List<Pair<String, Boolean>>, List<Pair<String, Boolean>>> adValidCheck(VAvitoMovable movable,
                                                                                            List<Pair<VAvitoMovableParams, Boolean>> params) {
        List<Pair<String, Boolean>> errorList = new ArrayList<>();
        boolean isFatalError = false;
        for (Pair<VAvitoMovableParams, Boolean> param : params) {
            switch (param.getKey()) {
                case OME_UNID:
                case OBJ_UNID:
                case MEV_UNID:
                    break; // 05.09.2021 ksv: Always not null
                case OBJ_CODE:
                    if (!checkStringFieldAvailability(movable.getObjCode())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case LS_UNID:
                    if (movable.getLsUnid() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case SC_UNID:
                    if (movable.getScUnid() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case FC_UNID:
                    if (movable.getFcUnid() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case CATEGORY_NAME:
                    if (!checkStringFieldAvailability(movable.getCategoryName())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case CATEGORY_CODE:
                    if (!checkStringFieldAvailability(movable.getCategoryCode())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case CATEGORY_FEED_CODE:
                    if (!checkStringFieldAvailability(movable.getCategoryFeedCode())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case TITLE:
                    if (!checkStringFieldAvailability(movable.getTitle())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case DESCRIPTION:
                    if (!checkStringFieldAvailability(movable.getDescription())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case MAKE:
                    if (!checkStringFieldAvailability(movable.getMake())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case MODEL:
                    if (!checkStringFieldAvailability(movable.getModel())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case BODY_TYPE:
                    if (!checkStringFieldAvailability(movable.getBodyType())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case TECHNICAL_PASSPORT:
                    if (!checkStringFieldAvailability(movable.getTechnicalPassport())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case YEAR:
                    if (movable.getYear() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    } else {
                        try {
                            Double year = Double.parseDouble(movable.getYear());
                        } catch (NumberFormatException e) {
                            errorList.add(formSyntaxError(param));
                            isFatalError = true;
                        }
                    }
                    break;
                case PRICE:
                    if (movable.getPrice() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case ADDRESS:
                    if (!checkStringFieldAvailability(movable.getAddress())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case SUBAGENT_MOBILE_PHONE:
                    if (!checkStringFieldAvailability(movable.getSubagentMobilePhone())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    } else if (!movable.getSubagentMobilePhone().matches(MOBILEPHONE_REGEX)) {
                        errorList.add(formSyntaxError(param));
                    }
                    break;
                case SUBAGENT_NAME_F:
                    if (!checkStringFieldAvailability(movable.getSubagentNameF())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case SUBAGENT_NAME_I:
                    if (!checkStringFieldAvailability(movable.getSubagentNameI())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case LISTING_FEE:
                    if (!checkStringFieldAvailability(movable.getListingFee())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case OBJ_INT_RENT:
                    if (movable.getObjIndRent() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case TS_UNID:
                    if (movable.getTsUnid() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    } else if (errorTsUnidList.contains(movable.getTsUnid())) {
                        errorList.add(new Pair<>(TS_UNID_ERROR, param.getValue()));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case PICTURE_COUNT:
                    if (movable.getPictureCount() == 0) {
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

    private Pair<String, Boolean> formAvailabilityError(Pair<VAvitoMovableParams, Boolean> param) {
        return new Pair<>("Поле \"" + param.getKey().getFieldName() + "\" (" + param.getKey().getFieldDescr() + ") не заполнено", param.getValue());
    }

    private Pair<String, Boolean> formSyntaxError(Pair<VAvitoMovableParams, Boolean> param) {
        return new Pair<>("Поле \"" + param.getKey().getFieldName() + "\" (" + param.getKey().getFieldDescr() + ") заполнено некорректно", param.getValue());
    }

    protected Pair<String, Boolean> formAvailabilityError(String fieldName, Boolean isRequired) {
        return new Pair<>("Поле \"" + fieldName + "\" не заполнено", isRequired);
    }

    protected Pair<String, Boolean> formSyntaxError(String fieldName, String fieldDescr, Boolean isRequired) {
        return new Pair<>("Поле \"" + fieldName + "\" (" + fieldDescr + ") заполнено некорректно", isRequired);
    }

    protected String formErrorStrFromPairList(List<Pair<String, Boolean>> errorList) {
        return errorList.stream()
                .sorted((p1, p2) -> Boolean.compare(p2.getValue(), p1.getValue()))
                .map(Pair::getKey)
                .collect(Collectors.joining("\n"));
    }
}
