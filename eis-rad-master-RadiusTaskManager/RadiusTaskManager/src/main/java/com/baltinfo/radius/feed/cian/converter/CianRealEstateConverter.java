package com.baltinfo.radius.feed.cian.converter;

import com.baltinfo.radius.db.constants.TypeStateConstant;
import com.baltinfo.radius.db.constants.VatType;
import com.baltinfo.radius.db.model.VCianRealEstate;
import com.baltinfo.radius.feed.cian.constant.VCianRealEstateParams;
import com.baltinfo.radius.feed.cian.model.BargainTerms;
import com.baltinfo.radius.feed.cian.model.CianLandUnitType;
import com.baltinfo.radius.feed.cian.model.Land;
import com.baltinfo.radius.feed.cian.model.Object;
import com.baltinfo.radius.feed.cian.model.PhoneSchema;
import com.baltinfo.radius.feed.cian.model.Phones;
import com.baltinfo.radius.feed.cian.model.SubAgent;
import com.baltinfo.radius.feed.services.FeedDescriptionService;
import com.baltinfo.radius.utils.AreaConverter;
import com.baltinfo.radius.utils.Result;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Kulikov Semyon
 * @since 30.10.2020
 */

public abstract class CianRealEstateConverter {
    public static final String DEFAULT_PAYMENT_PERIOD = "monthly";

    private static final Logger logger = LoggerFactory.getLogger(CianRealEstateConverter.class);
    private static final String DEFAULT_PRICE_TYPE = "all";
    private static final String PICTURE_COUNT_ERROR = "Отсутствуют фотографии для выгрузки";
    private static final String TS_UNID_ERROR = "Объект в данном статусе не выгружается на доску";
    private static final String MOBILEPHONE_REGEX = "(?:\\+|\\d)[\\d\\-\\(\\) ]{9,}\\d";
    private static final String RENT_SUFFIX = "_rent";
    private static final List<Long> errorTsUnidList = new ArrayList<>(Arrays.asList(
            TypeStateConstant.OBJ_SALED.getId(), // 3
            TypeStateConstant.ARCHIVE.getId(), // 4
            TypeStateConstant.TRADE_DONE.getId(), //13
            TypeStateConstant.TRADE_CANCELED.getId(), //14
            TypeStateConstant.SOLD_WAITING_FOR_CONTRACT.getId(), //31
            TypeStateConstant.REFUSAL_OF_DKP.getId())); //32
    public static Pattern PHONE_NUMBER_REGEX = Pattern.compile("(^\\+?\\d)(.*)");
    private final FeedDescriptionService feedDescriptionService;

    protected CianRealEstateConverter(FeedDescriptionService feedDescriptionService) {
        this.feedDescriptionService = feedDescriptionService;
    }

    protected Object fillGeneralProperties(VCianRealEstate cianObj) {
        Object obj = new Object();
        obj.setTitle(cianObj.getTitle());
        obj.setDescription(feedDescriptionService.formDescription(cianObj));
        if (cianObj.getObjIndRent() != null && cianObj.getObjIndRent()) {
            obj.setExternalId(cianObj.getObjCode() + RENT_SUFFIX);
        } else {
            obj.setExternalId(cianObj.getObjCode());
        }
        obj.setAddress(cianObj.getAddress());
        obj.setPhones(makePhones(cianObj.getSubAgentMobilePhone()));
        obj.setSubAgent(makeSubAgent(cianObj));
        obj.setCategory(cianObj.getCategoryFeedName());
        return obj;
    }

    protected boolean generalValidCheck(VCianRealEstate cianObj) {
        return StringUtils.hasText(cianObj.getObjCode())
                && StringUtils.hasText(cianObj.getSubAgentMobilePhone())
                && StringUtils.hasText(cianObj.getTitle())
                && StringUtils.hasText(cianObj.getDescription())
                && StringUtils.hasText(cianObj.getAddress())
                && StringUtils.hasText(cianObj.getSubAgentEmail())
                && cianObj.getCategoryFeedName() != null;
    }

    protected Phones makePhones(String mobilePhone) {
        Phones phones = new Phones();
        PhoneSchema phoneSchema = new PhoneSchema();
        Matcher match = PHONE_NUMBER_REGEX.matcher(mobilePhone);
        if (match.find()) {
            phoneSchema.setCountryCode(match.group(1));
            phoneSchema.setNumber(match.group(2).replaceAll("\\D", ""));
        }
        phones.getPhoneSchema().add(phoneSchema);
        return phones;
    }

    protected SubAgent makeSubAgent(VCianRealEstate cianObj) {
        SubAgent subAgent = new SubAgent();
        subAgent.setEmail(cianObj.getSubAgentEmail());
        subAgent.setPhone(cianObj.getSubAgentMobilePhone());
        subAgent.setFirstName(cianObj.getSubAgentNameI());
        subAgent.setLastName(cianObj.getSubAgentNameF());
        return subAgent;
    }

    public BargainTerms makeBargainTerms(VCianRealEstate cianObj) {
        BargainTerms bargainTerms = new BargainTerms();
        bargainTerms.setPrice(cianObj.getPrice());
        bargainTerms.setVatType(VatType.getByDbCode(cianObj.getCostIndTax()).getFeedCode());
        if (cianObj.getObjIndRent()) {
            bargainTerms.setPaymentPeriod(CianRealEstateConverter.DEFAULT_PAYMENT_PERIOD);
            bargainTerms.setLeaseTermType(cianObj.getLeaseTermType());
            bargainTerms.setClientFee(cianObj.getClientFee() != null
                    ? BigInteger.valueOf(cianObj.getClientFee())
                    : null);
            bargainTerms.setPriceType(DEFAULT_PRICE_TYPE);
        }
        return bargainTerms;
    }

    protected Land makeLand(String landStatus, Double landSqr) {
        Land land = new Land();
        land.setStatus(landStatus);
        if (landSqr < 10000) {
            land.setArea(AreaConverter.SQUARE_METER.convertTo(AreaConverter.SOTKA, landSqr));
            land.setAreaUnitType(CianLandUnitType.SOTKA.getCode());
        } else {
            land.setArea(AreaConverter.SQUARE_METER.convertTo(AreaConverter.HECTARE, landSqr));
            land.setAreaUnitType(CianLandUnitType.HECTARE.getCode());
        }
        return land;
    }

    protected Result<List<Pair<String, Boolean>>, List<Pair<String, Boolean>>> adValidCheck(VCianRealEstate realEstate,
                                                                                            List<Pair<VCianRealEstateParams, Boolean>> params) {
        List<Pair<String, Boolean>> errorList = new ArrayList<>();
        boolean isFatalError = false;
        for (Pair<VCianRealEstateParams, Boolean> param : params) {
            switch (param.getKey()) {
                case OBJ_CODE:
                    if (!checkStringFieldAvailability(realEstate.getObjCode())) {
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
                case CATEGORY:
                    if (!checkStringFieldAvailability(realEstate.getCategory())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case TOTAL_AREA:
                    if (realEstate.getTotalArea() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case FLOOR_NUMBER:
                    if (realEstate.getFloorNumber() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    } else {
                        try {
                            Double floorNumber = Double.parseDouble(realEstate.getFloorNumber());
                        } catch (NumberFormatException e) {
                            errorList.add(formSyntaxError(param));
                            isFatalError = true;
                        }
                    }
                    break;
                case FLOORS_COUNT:
                    if (realEstate.getFloorsCount() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    } else {
                        try {
                            Double floorsCount = Double.parseDouble(realEstate.getFloorsCount());
                        } catch (NumberFormatException e) {
                            errorList.add(formSyntaxError(param));
                            isFatalError = true;
                        }
                    }
                    break;
                case GARAGE_TYPE:
                    if (!checkStringFieldAvailability(realEstate.getGarageType())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case LAND_STATUS:
                    if (!checkStringFieldAvailability(realEstate.getLandStatus())) {
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
                case COST_IND_TAX:
                    if (realEstate.getCostIndTax() == null) {
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
                case SUBAGENT_MOBILE_PHONE:
                    if (!checkStringFieldAvailability(realEstate.getSubAgentMobilePhone())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    } else if (!realEstate.getSubAgentMobilePhone().matches(MOBILEPHONE_REGEX)) {
                        errorList.add(formSyntaxError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case SUBAGENT_EMAIL:
                    if (!checkStringFieldAvailability(realEstate.getSubAgentEmail())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case SUBAGENT_NAME_F:
                    if (!checkStringFieldAvailability(realEstate.getSubAgentNameF())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case SUBAGENT_NAME_I:
                    if (!checkStringFieldAvailability(realEstate.getSubAgentNameI())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case CATEGORY_FEED_NAME:
                    if (!checkStringFieldAvailability(realEstate.getCategoryFeedName())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case LAND_SQR:
                    if (realEstate.getLandSqr() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case FLAT_ROOMS_COUNT:
                    logger.info("CianRealEstateConverter FLAT_ROOMS_COUNT = {}", realEstate.getFlatRoomsCount());
                    if (realEstate.getFlatRoomsCount() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case SHARE_AMOUNT:
                    if (!checkStringFieldAvailability(realEstate.getShareAmount())) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case ROOM_AREA:
                    logger.info("CianRealEstateConverter ROOM_AREA = {}", realEstate.getRoomArea());
                    if (realEstate.getRoomArea() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case LAND_AREA:
                    logger.info("CianRealEstateConverter LAND_AREA = {}", realEstate.getLandArea());
                    if (realEstate.getLandArea() == null) {
                        errorList.add(formAvailabilityError(param));
                        if (param.getValue()) {
                            isFatalError = true;
                        }
                    }
                    break;
                case LEASE_TERM_TYPE:
                    if (!checkStringFieldAvailability(realEstate.getLeaseTermType())) {
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
                default:
                    break;
            }
        }
        return isFatalError ? Result.error(errorList) : Result.ok(errorList);
    }

    private boolean checkStringFieldAvailability(String str) {
        return StringUtils.hasText(str);
    }

    private Pair<String, Boolean> formAvailabilityError(Pair<VCianRealEstateParams, Boolean> param) {
        return new Pair<>("Поле \"" + param.getKey().getFieldName() + "\" (" + param.getKey().getFieldDescr() + ") не заполнено", param.getValue());
    }

    private Pair<String, Boolean> formSyntaxError(Pair<VCianRealEstateParams, Boolean> param) {
        return new Pair<>("Поле \"" + param.getKey().getFieldName() + "\" (" + param.getKey().getFieldDescr() + ") заполнено некорректно", param.getValue());
    }

    protected String formErrorStrFromPairList(List<Pair<String, Boolean>> errorList) {
        return errorList.stream()
                .sorted((p1, p2) -> Boolean.compare(p2.getValue(), p1.getValue()))
                .map(Pair::getKey)
                .collect(Collectors.joining("\n"));
    }
}
