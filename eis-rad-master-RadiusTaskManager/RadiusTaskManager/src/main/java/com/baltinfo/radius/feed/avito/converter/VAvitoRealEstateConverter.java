package com.baltinfo.radius.feed.avito.converter;

import com.baltinfo.radius.db.constants.SaleCategoryConstant;
import com.baltinfo.radius.db.constants.TypeStateConstant;
import com.baltinfo.radius.db.model.VAvitoRealEstate;
import com.baltinfo.radius.feed.avito.constant.VAvitoRealEstateParams;
import com.baltinfo.radius.feed.avito.model.Ad;
import com.baltinfo.radius.feed.services.FeedDescriptionService;
import com.baltinfo.radius.utils.Result;
import javafx.util.Pair;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Suvorina Aleksandra
 * @since 23.09.2020
 */
public abstract class VAvitoRealEstateConverter {

    private static final String PICTURE_COUNT_ERROR = "Отсутствуют фотографии для выгрузки";
    private static final String TS_UNID_ERROR = "Объект в данном статусе не выгружается на доску";
    private static final String MOBILEPHONE_REGEX = "(?:\\+|\\d)[\\d\\-\\(\\) ]{9,}\\d";
    private static final String SALE_OPTIONS_DEFAULT = "Аукцион";
    private static final List<Long> errorTsUnidList = new ArrayList<>(Arrays.asList(
            TypeStateConstant.OBJ_SALED.getId(),
            TypeStateConstant.ARCHIVE.getId(),
            TypeStateConstant.TRADE_DONE.getId(),
            TypeStateConstant.TRADE_CANCELED.getId(),
            TypeStateConstant.SOLD_WAITING_FOR_CONTRACT.getId(),
            TypeStateConstant.REFUSAL_OF_DKP.getId()));
    private static final List<Long> scUnidsWithoutFloor = new ArrayList<>(Arrays.asList(
            SaleCategoryConstant.OFFICE_BUILDINGS.getCode(),
            SaleCategoryConstant.TRADE_BUILDINGS.getCode(),
            SaleCategoryConstant.PROPERTY_COMPLEXES.getCode(),
            SaleCategoryConstant.HOTELS.getCode(),
            SaleCategoryConstant.CONSTRUCTIONS_IN_PROGRESS.getCode(),
            SaleCategoryConstant.CATERING_FACILITIES.getCode(),
            SaleCategoryConstant.MANSIONS.getCode()));
    private static final List<Pair<VAvitoRealEstateParams, Boolean>> rentParams = new ArrayList<Pair<VAvitoRealEstateParams, Boolean>>() {{
        add(new Pair(VAvitoRealEstateParams.LEASE_COMMISSION_SIZE, true));
    }};
    private final FeedDescriptionService feedDescriptionService;

    protected VAvitoRealEstateConverter(FeedDescriptionService feedDescriptionService) {
        this.feedDescriptionService = feedDescriptionService;
    }

    protected Ad fillGeneralProperties(VAvitoRealEstate realEstateFromDB) {
        Ad ad = new Ad();
        ad.setPropertyRights("Посредник");
        ad.setSaleOptions(SALE_OPTIONS_DEFAULT);
        ad.setPrice(realEstateFromDB.getPrice());
        ad.setDescription(feedDescriptionService.formDescription(realEstateFromDB));
        ad.setId(realEstateFromDB.getObjCode());
        ad.setAddress(realEstateFromDB.getAddress());
        ad.setCategory(realEstateFromDB.getCategoryFeedCode());
        if (realEstateFromDB.getObjIndRent() != null && realEstateFromDB.getObjIndRent()) {
            ad.setLeaseCommissionSize(Double.valueOf(realEstateFromDB.getLeaseCommissionSize()).intValue());
            ad.setLeaseDeposit("Без залога");
            ad.setOperationType("Сдам");
        } else {
            ad.setOperationType("Продам");
        }
        return ad;
    }

    protected Result<List<Pair<String, Boolean>>, List<Pair<String, Boolean>>> adValidCheck(VAvitoRealEstate realEstate,
                                                                                            List<Pair<VAvitoRealEstateParams, Boolean>> params) {
        List<Pair<String, Boolean>> errorList = new ArrayList<>();
        boolean isFatalError = false;
        for (Pair<VAvitoRealEstateParams, Boolean> param : params) {
            switch (param.getKey()) {
                case OME_UNID:
                case OBJ_UNID:
                case MEV_UNID:
                    break; // 05.09.2021 ksv: Always not null
                case OBJ_CODE:
                    if (!checkStringFieldAvailability(realEstate.getObjCode())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case LS_UNID:
                    if (realEstate.getLsUnid() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case SC_UNID:
                    if (realEstate.getScUnid() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case FC_UNID:
                    if (realEstate.getFcUnid() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case CATEGORY:
                    if (!checkStringFieldAvailability(realEstate.getCategory())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case CATEGORY_FEED_CODE:
                    if (!checkStringFieldAvailability(realEstate.getCategoryFeedCode())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case TITLE:
                    if (!checkStringFieldAvailability(realEstate.getTitle())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case DESCRIPTION:
                    if (!checkStringFieldAvailability(realEstate.getDescription())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case SQUARE:
                    if (realEstate.getSquare() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case LAND_AREA:
                    if (realEstate.getLandArea() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case FLOOR:
                    if (!scUnidsWithoutFloor.contains(realEstate.getScUnid()) && !checkStringFieldAvailability(realEstate.getFloor())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case FLOORS:
                    if (realEstate.getFloors() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    } else {
                        try {
                            Double floors = Double.valueOf(realEstate.getFloors());
                        } catch (NumberFormatException e) {
                            errorList.add(formSyntaxError(param));
                            isFatalError = true;
                        }
                    }
                    break;
                case MARKET_TYPE:
                    if (!checkStringFieldAvailability(realEstate.getMarketType())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case ROOMS:
                    if (!checkStringFieldAvailability(realEstate.getRooms())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case HOUSE_TYPE:
                    if (!checkStringFieldAvailability(realEstate.getHouseType())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case PRICE:
                    if (realEstate.getPrice() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case ADDRESS:
                    if (!checkStringFieldAvailability(realEstate.getAddress())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case WALLS_TYPE:
                    if (!checkStringFieldAvailability(realEstate.getWallsType())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case MATERIAL_OF_GARAGE:
                    if (!checkStringFieldAvailability(realEstate.getMaterialOfGarage())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case SECURED:
                    if (!checkStringFieldAvailability(realEstate.getSecured())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case TYPE_OF_PARKING:
                    if (!checkStringFieldAvailability(realEstate.getTypeOfParking())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case TYPE_OF_GARAGE:
                    if (!checkStringFieldAvailability(realEstate.getTypeOfGarage())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case STATUS:
                    if (!checkStringFieldAvailability(realEstate.getStatus())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case SUBAGENT_MOBILE_PHONE:
                    if (!checkStringFieldAvailability(realEstate.getSubagentMobilePhone())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    } else if (!realEstate.getSubagentMobilePhone().trim().matches(MOBILEPHONE_REGEX)) {
                        errorList.add(formSyntaxError(param));
                    }
                    break;
                case SUBAGENT_NAME_F:
                    if (!checkStringFieldAvailability(realEstate.getSubagentNameF())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case SUBAGENT_NAME_I:
                    if (!checkStringFieldAvailability(realEstate.getSubagentNameI())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case OBJ_IND_RENT:
                    if (realEstate.getObjIndRent() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    } else if (realEstate.getObjIndRent()) {
                        Result<List<Pair<String, Boolean>>, List<Pair<String, Boolean>>> leaseCheck =
                                adValidCheck(realEstate, rentParams);
                        if (leaseCheck.isSuccess()) {
                            errorList.addAll(leaseCheck.getResult());
                        } else {
                            errorList.addAll(leaseCheck.getError());
                            isFatalError = true;
                        }
                    }
                    break;
                case LEASE_COMMISSION_SIZE:
                    if (realEstate.getLeaseCommissionSize() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    } else {
                        try {
                            Double leaseCommissionSize = Double.valueOf(realEstate.getLeaseCommissionSize());
                        } catch (NumberFormatException e) {
                            isFatalError = true;
                        }
                    }
                    break;
                case DEVELOPMENT_ID:
                    if (realEstate.getDevelopmentId() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case TS_UNID:
                    if (realEstate.getTsUnid() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    } else if (errorTsUnidList.contains(realEstate.getTsUnid())) {
                        errorList.add(new Pair<>(TS_UNID_ERROR, param.getValue()));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case PICTURE_COUNT:
                    if (realEstate.getPictureCount() == 0) {
                        errorList.add(new Pair<>(PICTURE_COUNT_ERROR, param.getValue()));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case OBJECT_TYPE:
                    if (!checkStringFieldAvailability(realEstate.getObjectType())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case BUILDING_TYPE:
                    if (!checkStringFieldAvailability(realEstate.getBuildingType())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case DECORATION_COMM:
                    if (realEstate.getDecorationComm() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case PARKING_TYPE:
                    if (checkSaleCategory(realEstate.getScUnid(), param.getKey().getSaleCategories()) && realEstate.getParkingType() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case ENTRANCE:
                    if (checkSaleCategory(realEstate.getScUnid(), param.getKey().getSaleCategories()) && realEstate.getEntrance() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case LAYOUT:
                    if (checkSaleCategory(realEstate.getScUnid(), param.getKey().getSaleCategories()) && realEstate.getLayout() == null) {
                        errorList.add(formAvailabilityError(param));
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

    private Pair<String, Boolean> formAvailabilityError(Pair<VAvitoRealEstateParams, Boolean> param) {
        return new Pair<>("Поле \"" + param.getKey().getFieldName() + "\" (" + param.getKey().getFieldDescr() + ") не заполнено", param.getValue());
    }

    private Pair<String, Boolean> formSyntaxError(Pair<VAvitoRealEstateParams, Boolean> param) {
        return new Pair<>("Поле \"" + param.getKey().getFieldName() + "\" (" + param.getKey().getFieldDescr() + ") заполнено некорректно", param.getValue());
    }

    protected Pair<String, Boolean> formAvailabilityError(String fieldName, String fieldDescr, Boolean isRequired) {
        return new Pair<>("Поле \"" + fieldName + "\" (" + fieldDescr + ") не заполнено", isRequired);
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

    protected boolean checkSaleCategory(Long scUnid, List<SaleCategoryConstant> verifiedSC) {
        if (verifiedSC == null) {
            return true;
        }
        return verifiedSC.stream().anyMatch(saleCategory -> saleCategory.getCode().equals(scUnid));
    }
}
