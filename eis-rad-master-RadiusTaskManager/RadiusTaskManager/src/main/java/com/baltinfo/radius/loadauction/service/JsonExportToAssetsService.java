package com.baltinfo.radius.loadauction.service;

import com.baltinfo.radius.loadauction.constants.LabelsConformity;
import com.baltinfo.radius.loadauction.model.AssetDescriptionPledge;
import com.baltinfo.radius.loadauction.model.AssetFull;
import com.baltinfo.radius.loadauction.model.DescriptionPledge;
import com.baltinfo.radius.loadauction.model.assets.Asset;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetApartment;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetArt;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetBill;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetBond;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetCar;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetCarLoan;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetCoin;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetCommercialProperty;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetConstruction;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetEquipment;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetGarage;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetGem;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetHouse;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetIntangible;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetInvestments;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetLand;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetLaw;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetMortgage;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetOther;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetOtherTransport;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetShare;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetStock;
import com.baltinfo.radius.loadauction.model.assets.extend.AssetTruck;
import com.baltinfo.radius.utils.Result;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * Сервис для экспорта из JSON в Asset'ы для дальнешей записи в БД
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 29.01.2020
 */

public class JsonExportToAssetsService {

    private static final Logger logger = LoggerFactory.getLogger(JsonExportToAssetsService.class);

    private Result<List<Asset>, String> fromJsonToAsset(String jsonInput) {
        try {
            try {
                Result<List<AssetFull>, String> assetFullResult = formJsonToFullAsset(jsonInput);
                if (assetFullResult.isError()) {
                    return Result.error(assetFullResult.getError());
                }
                AssetFactory assetFactory = new AssetFactory();
                Result<List<Asset>, String> result = assetFactory.getListOfAsset(assetFullResult.getResult());
                if (result.isError()) {
                    return Result.error(result.getError());
                }
                return Result.ok(result.getResult());
            } catch (Exception e) {
                logger.error("Error when returning list of Asset ", e);
                return Result.error("Error when returning list of Asset" + e);
            }
        } catch (Exception ex) {
            logger.error("Error when run fromJsonToAsset ", ex);
            return Result.error("Error when run fromJsonToAsset" + ex);
        }
    }

    public Result<List<AssetFull>, String> formJsonToFullAsset(String jsonInput) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            mapper.setVisibility(PropertyAccessor.CREATOR, JsonAutoDetect.Visibility.ANY);
            try {
                List<AssetFull> output = mapper.readValue(jsonInput, new TypeReference<List<AssetFull>>() {
                });
                return Result.ok(output);
            } catch (IOException e) {
                logger.error("Error formJsonToFullAsset ", e);
                return Result.error("Error parse asset from json " + e.getMessage());
            }
        } catch (Exception ex) {
            logger.error("Error when run fromJsonToAsset ", ex);
            return Result.error("Error parse asset from json " + ex.getMessage());
        }
    }

    public Result<String, String> buildHtmlFromAsset(String jsonString) {
        Result<List<Asset>, String> result = fromJsonToAsset(jsonString);
        if (result.isError()) {
            logger.warn("Error when trying to get Assets from json because of :{}", result.getError());
            return Result.error(result.getError());
        }
        List<Asset> list = result.getResult();
        StringBuilder s = new StringBuilder();
        createAssetHtml(list, s, false);
        return Result.ok(s.toString());
    }

    public Result<String, String> buildHtmlFromAssets(List<AssetFull> assetsFull) {
        AssetFactory assetFactory = new AssetFactory();
        Result<List<Asset>, String> result = assetFactory.getListOfAsset(assetsFull);
        if (result.isError()) {
            return Result.error(result.getError());
        }
        List<Asset> assets = result.getResult();
        StringBuilder s = new StringBuilder();
        createAssetHtml(assets, s, false);
        return Result.ok(s.toString());
    }

    private void createAssetHtml(List<Asset> list, StringBuilder s, boolean isInnerAsset) {
        s.append(makeOpenFieldSet());
        for (Asset asset : list) {
            s.append(makeLegeng(asset.getNameAsset()));
            s.append(makeCommonFields(asset, isInnerAsset));
            Integer assetType = asset.getTypeAsset();
            switch (assetType) {
                case 1:
                    s.append(createAssetLand(asset, isInnerAsset));
                    break;
                case 2:
                    s.append(createAssetApartment(asset, isInnerAsset));
                    break;
                case 3:
                    s.append(createAssetHouse(asset, isInnerAsset));
                    break;
                case 4:
                    s.append(createAssetCommercialProperty(asset, isInnerAsset));
                    break;
                case 5:
                    s.append(createAssetConstruction(asset, isInnerAsset));
                    break;
                case 6:
                    s.append(createAssetGarage(asset, isInnerAsset));
                    break;
                case 7:
                case 8:
                    s.append(createAssetCar(asset, isInnerAsset));
                    break;
                case 9:
                    s.append(createAssetOtherTransport(asset, isInnerAsset));
                    break;
                case 10:
                    s.append(createAssetTruck(asset, isInnerAsset));
                    break;
                case 11:
                    s.append(createAssetGem(asset, isInnerAsset));
                    break;
                case 12:
                    s.append(createAssetCoin(asset, isInnerAsset));
                    break;
                case 13:
                    s.append(createAssetArt(asset, isInnerAsset));
                    break;
                case 14:
                    s.append(createAssetStock(asset, isInnerAsset));
                    break;
                case 15:
                    s.append(createAssetBond(asset, isInnerAsset));
                    break;
                case 16:
                    s.append(createAssetBill(asset, isInnerAsset));
                    break;
                case 17:
                    s.append(createAssetShare(asset, isInnerAsset));
                    break;
                case 18:
                    s.append(createAssetInvestments(asset, isInnerAsset));
                    break;
                case 19:
                    s.append(createAssetMortgage(asset, isInnerAsset));
                    break;
                case 20:
                    s.append(createAssetOther(asset, isInnerAsset));
                    break;
                case 21:
                case 22:
                case 23:
                case 24:
                case 35:
                    s.append(createAssetCarLoan(asset, isInnerAsset));
                    break;
                case 25:
                case 26:
                    s.append(createAssetLaw(asset, isInnerAsset));
                    break;
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                    s.append(createAssetEquipment(asset, isInnerAsset));
                    break;
                case 34:
                    s.append(createAssetIntangible(asset, isInnerAsset));
                    break;
            }
            s.append("\n");
        }
        s.setLength(s.length() - 1);
        s.append(makeCloseFieldSet());
    }

    private StringBuilder createAssetLand(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetLand assetLand = (AssetLand) asset;
        str.append(makeBlock(LabelsConformity.TYPE_OF_OBJECT.getlabel(), assetLand.getTypeOfObject(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.SQUARE.getlabel(), assetLand.getSquare(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.SHARE_FO.getlabel(), assetLand.getShareFO(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ADDRESS.getlabel(), assetLand.getAddress(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.LAND_CATEGORY.getlabel(), assetLand.getLandCategory(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.PERMITTED_USE.getlabel(), assetLand.getPermittedUse(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.DISTANCE_TO_THE_REGIONAL_CENTER.getlabel(), assetLand.getDistanceToCenter(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.CADASTRAL_NUMBER.getlabel(), assetLand.getCadastralNumber(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.COMMUNICATIONS.getlabel(), assetLand.getCommunications(), isInnerAsset));
        str.append(makeBlockWithMoney(LabelsConformity.ASSESSMENT_VALUE.getlabel(), assetLand.getAssessmentValue(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ASSESSMENT_DATE.getlabel(), assetLand.getAssessmentDate(), isInnerAsset));
        return str;
    }

    private StringBuilder createAssetApartment(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetApartment assetApartment = (AssetApartment) asset;
        str.append(makeBlock(LabelsConformity.TYPE_OF_OBJECT.getlabel(), assetApartment.getTypeOfObject(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.SHARE_FO.getlabel(), assetApartment.getShareFO(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.SQUARE.getlabel(), assetApartment.getSquare(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ADDRESS.getlabel(), assetApartment.getAddress(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.CADASTRAL_NUMBER.getlabel(), assetApartment.getCadastralNumber(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.YEAR_BUILT.getlabel(), assetApartment.getYearBuilt(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.NUMBER_OF_ROOMS.getlabel(), assetApartment.getNumberOfRooms(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.FLOOR_NUMBER.getlabel(), assetApartment.getFloorNumber(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.FLOORS.getlabel(), assetApartment.getFloors(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.CEILING_HEIGHT.getlabel(), assetApartment.getCeilingHeight(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.LIVING_SPACE.getlabel(), assetApartment.getLivingSpace(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.KITCHEN_AREA.getlabel(), assetApartment.getKitchenArea(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ELEVATOR.getlabel(), assetApartment.getElevator(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.HOUSE_TYPE.getlabel(), assetApartment.getHouseType(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.BALCONY.getlabel(), assetApartment.getBalcony(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.VIEW_FROM_THE_WINDOW.getlabel(), assetApartment.getViewFromTheWindow(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.COMMUNICATIONS.getlabel(), assetApartment.getCommunications(), isInnerAsset));
        str.append(makeBlockWithMoney(LabelsConformity.ASSESSMENT_VALUE.getlabel(), assetApartment.getAssessmentValue(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ASSESSMENT_DATE.getlabel(), assetApartment.getAssessmentDate(), isInnerAsset));
        return str;
    }

    private StringBuilder createAssetHouse(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetHouse assetHouse = (AssetHouse) asset;
        str.append(makeBlock(LabelsConformity.TYPE_OF_OBJECT.getlabel(), assetHouse.getTypeOfObject(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.SHARE_FO.getlabel(), assetHouse.getShareFO(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.SQUARE.getlabel(), assetHouse.getSquare(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.NUMBER_OF_FLOORS.getlabel(), assetHouse.getNumberOfFloors(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.MORE_ABOUT_NUMBER_OF_FLOORS.getlabel(), assetHouse.getMoreAboutNumberOfFloors(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.CADASTRAL_NUMBER.getlabel(), assetHouse.getCadastralNumber(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.YEAR_BUILT.getlabel(), assetHouse.getYearBuilt(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.WALL_MATERIAL.getlabel(), assetHouse.getWallMaterial(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ROOFING_MATERIAL.getlabel(), assetHouse.getRoofingMaterial(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.TRANSPORT_ACCESSIBILITY.getlabel(), assetHouse.getTransportAccessibility(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.FOUNDATION.getlabel(), assetHouse.getFoundation(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.COMMUNICATIONS.getlabel(), assetHouse.getCommunications(), isInnerAsset));
        str.append(makeBlockWithMoney(LabelsConformity.ASSESSMENT_VALUE.getlabel(), assetHouse.getAssessmentValue(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ASSESSMENT_DATE.getlabel(), assetHouse.getAssessmentDate(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ADDRESS.getlabel(), assetHouse.getAddress(), isInnerAsset));
        return str;
    }

    private StringBuilder createAssetCommercialProperty(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetCommercialProperty assetCommercialProperty = (AssetCommercialProperty) asset;
        str.append(makeBlock(LabelsConformity.TYPE_OF_OBJECT.getlabel(), assetCommercialProperty.getTypeOfObject(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.SQUARE.getlabel(), assetCommercialProperty.getSquare(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.SHARE_FO.getlabel(), assetCommercialProperty.getShareFO(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ADDRESS.getlabel(), assetCommercialProperty.getAddress(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.FLOOR_NUMBER.getlabel(), assetCommercialProperty.getFloorNumber(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.FLOORS.getlabel(), assetCommercialProperty.getFloors(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.YEAR_BUILT.getlabel(), assetCommercialProperty.getYearBuilt(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.CADASTRAL_NUMBER.getlabel(), assetCommercialProperty.getCadastralNumber(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.COMMUNICATIONS.getlabel(), assetCommercialProperty.getCommunications(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.CATEGOR.getlabel(), assetCommercialProperty.getCategory(), isInnerAsset));
        str.append(makeBlockWithMoney(LabelsConformity.ASSESSMENT_VALUE.getlabel(), assetCommercialProperty.getAssessmentValue(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ASSESSMENT_DATE.getlabel(), assetCommercialProperty.getAssessmentDate(), isInnerAsset));
        return str;
    }

    private StringBuilder createAssetConstruction(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetConstruction assetConstruction = (AssetConstruction) asset;
        str.append(makeBlock(LabelsConformity.TYPE_OF_OBJECT.getlabel(), assetConstruction.getTypeOfObject(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.SQUARE.getlabel(), assetConstruction.getSquare(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.SHARE_FO.getlabel(), assetConstruction.getShareFO(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ADDRESS.getlabel(), assetConstruction.getAddress(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.READINESS.getlabel(), assetConstruction.getReadiness(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.CADASTRAL_NUMBER.getlabel(), assetConstruction.getCadastralNumber(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.FLOORS.getlabel(), assetConstruction.getFloors(), isInnerAsset));
        str.append(makeBlockWithMoney(LabelsConformity.ASSESSMENT_VALUE.getlabel(), assetConstruction.getAssessmentValue(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ASSESSMENT_DATE.getlabel(), assetConstruction.getAssessmentDate(), isInnerAsset));
        return str;
    }

    private StringBuilder createAssetGarage(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetGarage assetGarage = (AssetGarage) asset;
        str.append(makeBlock(LabelsConformity.TYPE_OF_OBJECT.getlabel(), assetGarage.getTypeOfObject(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.SQUARE.getlabel(), assetGarage.getSquare(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ADDRESS.getlabel(), assetGarage.getAddress(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.GARAGE_TYPE.getlabel(), assetGarage.getGarageType(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.TYPE_OF_PARKING.getlabel(), assetGarage.getTypeOfParking(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.CADASTRAL_NUMBER.getlabel(), assetGarage.getCadastralNumber(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.COMMUNICATIONS.getlabel(), assetGarage.getCommunications(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.PRESENCE_OF_SECURITY.getlabel(), assetGarage.getPresenceOfSecurity(), isInnerAsset));
        str.append(makeBlockWithMoney(LabelsConformity.ASSESSMENT_VALUE.getlabel(), assetGarage.getAssessmentValue(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ASSESSMENT_DATE.getlabel(), assetGarage.getAssessmentDate(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.SHARE_FO.getlabel(), assetGarage.getShareFO(), isInnerAsset));
        return str;
    }

    private StringBuilder createAssetCar(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetCar assetCar = (AssetCar) asset;
        str.append(makeBlock(LabelsConformity.MARK.getlabel(), assetCar.getMark(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.MODEL.getlabel(), assetCar.getModel(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.COLOR.getlabel(), assetCar.getColor(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.YEAR_OF_ISSUE.getlabel(), assetCar.getYearOfIssue(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.MILEAGE.getlabel(), assetCar.getMileage(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ENGINE_CAPACITY.getlabel(), assetCar.getEngineCapacity(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.CAR_BODY.getlabel(), assetCar.getCarBody(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.STEERING_WHEEL.getlabel(), assetCar.getSteeringWheel(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.TRANSMISSION.getlabel(), assetCar.getTransmission(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ENGINE_POWER.getlabel(), assetCar.getEnginePower(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ENGINE_TYPE.getlabel(), assetCar.getEngineType(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.TYPE_OF_DRIVE.getlabel(), assetCar.getTypeOfDrive(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.VIN.getlabel(), assetCar.getVin(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ADDRESS.getlabel(), assetCar.getAddress(), isInnerAsset));
        return str;
    }

    private StringBuilder createAssetOtherTransport(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetOtherTransport assetOtherTransport = (AssetOtherTransport) asset;
        str.append(makeBlock(LabelsConformity.ADDRESS.getlabel(), assetOtherTransport.getAddress(), isInnerAsset));
        return str;
    }

    private StringBuilder createAssetTruck(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetTruck assetTruck = (AssetTruck) asset;
        str.append(makeBlock(LabelsConformity.TYPE_OF_TRANSPORT.getlabel(), assetTruck.getTypeOfTransport(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.MARK.getlabel(), assetTruck.getMark(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.MODEL.getlabel(), assetTruck.getModel(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.COLOR.getlabel(), assetTruck.getColor(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.YEAR_OF_ISSUE.getlabel(), assetTruck.getYearOfIssue(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.MILEAGE.getlabel(), assetTruck.getMileage(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ENGINE_CAPACITY.getlabel(), assetTruck.getEngineCapacity(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.TRANSMISSION.getlabel(), assetTruck.getTransmission(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ENGINE_POWER.getlabel(), assetTruck.getEnginePower(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ENGINE_TYPE.getlabel(), assetTruck.getEngineType(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.TYPE_OF_DRIVE.getlabel(), assetTruck.getTypeOfDrive(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.VIN.getlabel(), assetTruck.getVin(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.STEERING_WHEEL.getlabel(), assetTruck.getSteeringWheel(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ADDRESS.getlabel(), assetTruck.getAddress(), isInnerAsset));
        return str;
    }

    private StringBuilder createAssetGem(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetGem assetGem = (AssetGem) asset;
        str.append(makeBlock(LabelsConformity.TYPE_OF_OBJECT.getlabel(), assetGem.getTypeOfObject(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.METAL.getlabel(), assetGem.getMetal(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.SAMPLE.getlabel(), assetGem.getSample(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.TOTAL_WEIGHT.getlabel(), assetGem.getTotalWeight(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.NAME_STONE.getlabel(), assetGem.getNameStone(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.TYPE_THINGS.getlabel(), assetGem.getTypeThings(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ADDRESS.getlabel(), assetGem.getAddress(), isInnerAsset));
        return str;
    }

    private StringBuilder createAssetCoin(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetCoin assetCoin = (AssetCoin) asset;
        str.append(makeBlock(LabelsConformity.TYPE_OF_OBJECT.getlabel(), assetCoin.getTypeOfObject(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.NAME.getlabel(), assetCoin.getName(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.COUNTRY.getlabel(), assetCoin.getCountry(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.YEAR_OF_ISSUE.getlabel(), assetCoin.getYearOfIssue(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.SERIES.getlabel(), assetCoin.getSeries(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.METAL.getlabel(), assetCoin.getMetal(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.SAMPLE.getlabel(), assetCoin.getSample(), isInnerAsset));
        str.append(makeBlockWithMoney(LabelsConformity.NOMINAL_COST.getlabel(), assetCoin.getNominalCost(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.WEIGHT.getlabel(), assetCoin.getWeight(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.AMOUNT.getlabel(), assetCoin.getAmount(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ADDRESS.getlabel(), assetCoin.getAddress(), isInnerAsset));
        return str;
    }

    private StringBuilder createAssetArt(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetArt assetArt = (AssetArt) asset;
        str.append(makeBlock(LabelsConformity.TYPE_OF_OBJECT.getlabel(), assetArt.getTypeOfObject(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.NAME_PRODUCT.getlabel(), assetArt.getProductName(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.AUTHOR.getlabel(), assetArt.getAuthor(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.DATE.getlabel(), assetArt.getDate(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.SIZE.getlabel(), assetArt.getSize(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.MATERIAL.getlabel(), assetArt.getMaterial(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.EXPERTISE_INFORMATION.getlabel(), assetArt.getExpertiseInfo(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ADDRESS.getlabel(), assetArt.getAddress(), isInnerAsset));
        return str;
    }

    private StringBuilder createAssetStock(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetStock assetStock = (AssetStock) asset;
        str.append(makeBlock(LabelsConformity.VIEW.getlabel(), assetStock.getView(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ISSUER_NAME.getlabel(), assetStock.getIssuerName(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ISSUER_INN.getlabel(), assetStock.getIssuerInn(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.AMOUNT.getlabel(), assetStock.getAmount(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.SHARE.getlabel(), assetStock.getShare(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.REGISTRATION_NUMBER.getlabel(), assetStock.getRegistrationNumber(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ISIN.getlabel(), assetStock.getIsin(), isInnerAsset));
        str.append(makeBlockWithMoney(LabelsConformity.NOMINAL_COST.getlabel(), assetStock.getNominalCost(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.CURRENCY.getlabel(), assetStock.getCurrency(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ISSUER_LOCATION.getlabel(), assetStock.getIssuerLocation(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.STORAGE.getlabel(), assetStock.getStorage(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.URL.getlabel(), assetStock.getUrl(), isInnerAsset));
        str.append(makeBlockWithMoney(LabelsConformity.ASSESSMENT_VALUE.getlabel(), assetStock.getAssessmentValue(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ASSESSMENT_DATE.getlabel(), assetStock.getAssessmentDate(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.TURNOVER_LIMIT.getlabel(), assetStock.getTurnOverLimit(), isInnerAsset));
        return str;
    }

    private StringBuilder createAssetBond(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetBond assetBond = (AssetBond) asset;
        str.append(makeBlock(LabelsConformity.TYPE_BOND.getlabel(), assetBond.getTypeBond(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ISSUER_NAME.getlabel(), assetBond.getIssuerName(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ISSUER_INN.getlabel(), assetBond.getIssuerInn(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.AMOUNT.getlabel(), assetBond.getAmount(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.COUPON_RATE.getlabel(), assetBond.getCouponRate(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.PAYMENT_FREQUENCY.getlabel(), assetBond.getPaymentFrequency(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.MATURITY_DATE.getlabel(), assetBond.getMaturityDate(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.REGISTRATION_NUMBER.getlabel(), assetBond.getRegistrationNumber(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ISIN.getlabel(), assetBond.getIsin(), isInnerAsset));
        str.append(makeBlockWithMoney(LabelsConformity.NOMINAL_COST.getlabel(), assetBond.getNominalCost(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.CURRENCY.getlabel(), assetBond.getCurrency(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ISSUER_LOCATION.getlabel(), assetBond.getIssuerLocation(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.STORAGE.getlabel(), assetBond.getStorage(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.DEFAULT.getlabel(), assetBond.getDefaults(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.BANKRUPYCY_OF_THE_ISSUER.getlabel(), assetBond.getBankruptcyOfTheIssuer(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.URL.getlabel(), assetBond.getUrl(), isInnerAsset));
        str.append(makeBlockWithMoney(LabelsConformity.ASSESSMENT_VALUE.getlabel(), assetBond.getAssessmentValue(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ASSESSMENT_DATE.getlabel(), assetBond.getAssessmentDate(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.TURNOVER_LIMIT.getlabel(), assetBond.getTurnoverLimit(), isInnerAsset));
        return str;
    }

    private StringBuilder createAssetBill(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetBill assetBill = (AssetBill) asset;
        str.append(makeBlock(LabelsConformity.VIEW.getlabel(), assetBill.getView(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.PROMISER.getlabel(), assetBill.getPromiser(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.PROMISER_INN.getlabel(), assetBill.getPromiserInn(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.INTEREST_RATE.getlabel(), assetBill.getInterestRate(), isInnerAsset));
        str.append(makeBlockWithMoney(LabelsConformity.BILL_AMOUNT.getlabel(), assetBill.getBillAmount(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.CURRENCY.getlabel(), assetBill.getCurrency(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.DATE_OF_PREPARATION.getlabel(), assetBill.getDateOfPreparation(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.PLACE_OF_COMPILATION.getlabel(), assetBill.getPlaceOfCompilation(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.OBLIGATED_PERSON_NAME.getlabel(), assetBill.getObligatedPersonName(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.AVALIST.getlabel(), assetBill.getAvalist(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.PAYMENT_TERM.getlabel(), assetBill.getPaymentTerm(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.PLACE_OF_PAYMENT.getlabel(), assetBill.getPlaceOfPayment(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.PROMISER_LOCATION.getlabel(), assetBill.getPromiserLocation(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.STORAGE.getlabel(), assetBill.getStorage(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.PROMISER_BANKROT.getlabel(), assetBill.getPromiserBankrot(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.URL.getlabel(), assetBill.getUrl(), isInnerAsset));
        str.append(makeBlockWithMoney(LabelsConformity.ASSESSMENT_VALUE.getlabel(), assetBill.getAssessmentValue(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.TURNOVER_LIMIT.getlabel(), assetBill.getTurnoverLimit(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ASSESSMENT_DATE.getlabel(), assetBill.getAssessmentDate(), isInnerAsset));
        return str;
    }

    private StringBuilder createAssetShare(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetShare assetShare = (AssetShare) asset;
        str.append(makeBlock(LabelsConformity.ASSOCIATION_NAME.getlabel(), assetShare.getAssociationName(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ASSOCIATION_INN.getlabel(), assetShare.getAssociationInn(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.SHARE.getlabel(), assetShare.getShare(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.REAL_ACTIVITY.getlabel(), assetShare.getRealActivity(), isInnerAsset));
        str.append(makeBlockWithMoney(LabelsConformity.NOMINAL_COST.getlabel(), assetShare.getNominalCost(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ASSOCIATION_LOCATION.getlabel(), assetShare.getAssociationLocation(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.URL.getlabel(), assetShare.getUrl(), isInnerAsset));
        str.append(makeBlockWithMoney(LabelsConformity.ASSESSMENT_VALUE.getlabel(), assetShare.getAssessmentValue(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.TURNOVER_LIMIT.getlabel(), assetShare.getTurnoverLimit(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ASSESSMENT_DATE.getlabel(), assetShare.getAssessmentDate(), isInnerAsset));
        return str;
    }

    private StringBuilder createAssetInvestments(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetInvestments assetInvestments = (AssetInvestments) asset;
        str.append(makeBlock(LabelsConformity.FUND_NAME.getlabel(), assetInvestments.getFundName(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.FUND_CATEGORY.getlabel(), assetInvestments.getFundCategory(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.FUND_TYPE.getlabel(), assetInvestments.getFundType(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.REGISTRATION_NUMBER.getlabel(), assetInvestments.getRegistrationNumber(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.TERM_TlabelT_MANAGEMENT.getlabel(), assetInvestments.getTermTrustManagement(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.NUMBER_OF_SHARES.getlabel(), assetInvestments.getNumberOfShares(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.NUMBER_OF_SHARES_PERCENT.getlabel(), assetInvestments.getPartOfAllShares(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.STORAGE_OF_SHARES.getlabel(), assetInvestments.getStorageOfShare(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.MANAGEMENT_COMPANY_NAME.getlabel(), assetInvestments.getManagementCompanyName(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.INTERVALS.getlabel(), assetInvestments.getIntervals(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.MAIN_ASSETS_OF_THE_FUND.getlabel(), assetInvestments.getMainAssets(), isInnerAsset));
        str.append(makeBlockWithMoney(LabelsConformity.ASSESSMENT_VALUE.getlabel(), assetInvestments.getAssessmentValue(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ASSESSMENT_DATE.getlabel(), assetInvestments.getAssessmentDate(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.TURNOVER_LIMIT.getlabel(), assetInvestments.getTurnoverLimit(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.URL.getlabel(), assetInvestments.getUrl(), isInnerAsset));
        return str;
    }

    private StringBuilder createAssetMortgage(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetMortgage assetMortgage = (AssetMortgage) asset;
        str.append(makeBlock(LabelsConformity.ISU_NAME.getlabel(), assetMortgage.getIsuName(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.MANAGEMENT_COMPANY.getlabel(), assetMortgage.getManagementCompany(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.TERM_TlabelT_MANAGEMENT.getlabel(), assetMortgage.getTermTrustManagement(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.AMOUNT.getlabel(), assetMortgage.getAmount(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.AMOUNT_PERCENT.getlabel(), assetMortgage.getPartOfAllAmount(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.STORAGE.getlabel(), assetMortgage.getStorage(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.MORTGAGE_COVERAGE.getlabel(), assetMortgage.getMortgageCoverage(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.REGISTRATION_NUMBER.getlabel(), assetMortgage.getRegistrationNumber(), isInnerAsset));
        str.append(makeBlockWithMoney(LabelsConformity.ASSESSMENT_VALUE.getlabel(), assetMortgage.getAssessmentValue(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ASSESSMENT_DATE.getlabel(), assetMortgage.getAssessmentDate(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.TURNOVER_LIMIT.getlabel(), assetMortgage.getTurnoverLimit(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.URL.getlabel(), assetMortgage.getUrl(), isInnerAsset));
        return str;
    }

    public StringBuilder createAssetOther(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetOther assetOther = (AssetOther) asset;
        str.append(makeBlock(LabelsConformity.NAME_CB.getlabel(), assetOther.getNameCb(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.TYPE_CB.getlabel(), assetOther.getTypeCb(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ISSUER.getlabel(), assetOther.getIssuer(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.NUMBER.getlabel(), assetOther.getNumber(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.TERM.getlabel(), assetOther.getTerm(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.STORAGE.getlabel(), assetOther.getStorage(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.AMOUNT.getlabel(), assetOther.getAmount(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.SUPPORTING_DOCUMENTS.getlabel(), assetOther.getSupportingDocuments(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.COUNTERPARTY.getlabel(), assetOther.getCounterparty(), isInnerAsset));
        str.append(makeBlockWithMoney(LabelsConformity.ASSESSMENT_VALUE.getlabel(), assetOther.getAssessmentValue(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ASSESSMENT_DATE.getlabel(), assetOther.getAssessmentDate(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.TURNOVER_LIMIT.getlabel(), assetOther.getTurnoverLimit(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.URL.getlabel(), assetOther.getUrl(), isInnerAsset));
        return str;
    }

    private StringBuilder createAssetCarLoan(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetCarLoan assetCarLoan = (AssetCarLoan) asset;
        str.append(makeBlock(LabelsConformity.CREDIT_TYPE.getlabel(), assetCarLoan.getCreditType(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.AVERAGE_INTEREST_RATE.getlabel(), assetCarLoan.getAverageInterestRate(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.AVERAGE_REPAYMENT_DATE.getlabel(), assetCarLoan.getAverageRepaymentDate(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.DEBT_REPAYMENT_METHOD.getlabel(), assetCarLoan.getDebtRepaymentMethod(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.AVAILABILITY.getlabel(), assetCarLoan.getAvailability(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.CURRENCY.getlabel(), assetCarLoan.getCurrency(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.KD.getlabel(), assetCarLoan.getKd(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.BALANCE_DATE.getlabel(), assetCarLoan.getBalanceDate(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.DATE_KD.getlabel(), assetCarLoan.getDateKd(), isInnerAsset));
        str.append(makeBlockWithMoney(LabelsConformity.AMOUNT_OF_BALANCE.getlabel(), assetCarLoan.getAmountOfBalance(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.DOCUMENT_STORAGE.getlabel(), assetCarLoan.getDocumentStorage(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.OVERDUE.getlabel(), assetCarLoan.getOverdue(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.TRIAL.getlabel(), assetCarLoan.getTrial(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.END_DATE.getlabel(), assetCarLoan.getEndDate(), isInnerAsset));
        str.append(makeBlockWithDescriptionPledges(LabelsConformity.DESCRIPTION_PLEDGES.getlabel(), assetCarLoan.getDescriptionPledges(), isInnerAsset));
        return str;
    }

    private StringBuilder createAssetLaw(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetLaw assetLaw = (AssetLaw) asset;
        str.append(makeBlock(LabelsConformity.CREDIT_TYPE.getlabel(), assetLaw.getCreditType(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.BORROWER_NAME.getlabel(), assetLaw.getBorrowerName(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.BORROWER_INN.getlabel(), assetLaw.getBorrowerInn(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.KD.getlabel(), assetLaw.getKd(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.AVERAGE_INTEREST_RATE.getlabel(), assetLaw.getWeightAverageInterestRate(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.DATE_KD.getlabel(), assetLaw.getDateKd(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.AVERAGE_REPAYMENT_DATE.getlabel(), assetLaw.getAverageRepaymentDate(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.DEBT_REPAYMENT_METHOD.getlabel(), assetLaw.getDebtRepaymentMethod(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.AVAILABILITY.getlabel(), assetLaw.getAvailability(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.CURRENCY.getlabel(), assetLaw.getCurrency(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.BALANCE_DATE.getlabel(), assetLaw.getBalanceDate(), isInnerAsset));
        str.append(makeBlockWithMoney(LabelsConformity.AMOUNT_OF_BALANCE.getlabel(), assetLaw.getAmountOfBalance(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.OVERDUE.getlabel(), assetLaw.getOverdue(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.TRIAL.getlabel(), assetLaw.getTrial(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.END_DATE.getlabel(), assetLaw.getEndDate(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.DOCUMENT_STORAGE.getlabel(), assetLaw.getDocumentStorage(), isInnerAsset));
        str.append(makeBlockWithDescriptionPledges(LabelsConformity.DESCRIPTION_PLEDGES.getlabel(), assetLaw.getDescriptionPledges(), isInnerAsset));
        return str;
    }

    private StringBuilder createAssetEquipment(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetEquipment assetEquipment = (AssetEquipment) asset;
        str.append(makeBlock(LabelsConformity.TYPE_OF_OBJECT.getlabel(), assetEquipment.getTypeOfObject(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.MODEL.getlabel(), assetEquipment.getModel(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.MARK.getlabel(), assetEquipment.getMark(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ADDRESS.getlabel(), assetEquipment.getAddress(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.AMOUNT.getlabel(), assetEquipment.getAmount(), isInnerAsset));
        return str;
    }

    private StringBuilder createAssetIntangible(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        AssetIntangible assetIntangible = (AssetIntangible) asset;
        str.append(makeBlock(LabelsConformity.NAME_RIGHTHOLDER.getlabel(), assetIntangible.getNameRightHolder(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ADDRESS.getlabel(), assetIntangible.getAddress(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.NUMBER_OF_SVIDET.getlabel(), assetIntangible.getNumberOfSvidet(), isInnerAsset));
        return str;
    }

    private StringBuilder makeCommonFields(Asset asset, boolean isInnerAsset) {
        StringBuilder str = new StringBuilder();
        str.append(makeBlock(LabelsConformity.LOCATION_ASSET.getlabel(), asset.getLocationAsset(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.NAME_FO_ASSET.getlabel(), asset.getNameFoAsset(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.ABOUT_ASSET.getlabel(), asset.getAboutAsset(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.DEPOSITS.getlabel(), asset.getDeposits(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.OTHER.getlabel(), asset.getOther(), isInnerAsset));
        str.append(makeBlock(LabelsConformity.RESTRICTIONS_ASSET.getlabel(), asset.getRestrictionsAsset(), isInnerAsset));
        str.append(asset.getAbout() != null ? makeBlock(LabelsConformity.ABOUT.getlabel(), asset.getAbout(), isInnerAsset) : "");
        return str;
    }

    private String makeOpenFieldSet() {
        return "<fieldset>\n";
    }

    private String makeLegeng(String legend) {
        return "\t<legend>" + legend + "</legend>\n" +
                "\t<br><br>\n";
    }

    private String makeBlock(String key, Object value, boolean isInnerAsset) {
        if (value != null) {
            if (isInnerAsset) {
                return "\t<div class=\"form-item-asset\">\n" +
                        "\t\t<label>" + key + "</label>\n" +
                        "\t\t" + value + "\n" +
                        "\t</div>\n";
            } else {
                return "\t<div class=\"form-item\">\n" +
                        "\t\t<label>" + key + "</label>\n" +
                        "\t\t" + value + "\n" +
                        "\t</div>\n";
            }
        } else {
            return "";
        }
    }

    private String makeCloseFieldSet() {
        return "</fieldset>";
    }

    private String makeBlockWithMoney(String key, Object value, boolean isInnerAsset) {
        if (value != null) {
            String reverseString = new StringBuilder(value.toString()).reverse().toString();
            String reverseStringWithSpaces = "";
            String fractionalPart = "";
            if (reverseString.contains(".")) {
                fractionalPart = reverseString.substring(0, reverseString.indexOf(".") + 1);
                reverseStringWithSpaces = reverseString.substring(reverseString.indexOf(".") + 1).replaceAll("(.{3})", "$1 ");
            } else {
                reverseStringWithSpaces = reverseString.replaceAll("(.{3})", "$1 ");
            }
            String stringWithMoney = new StringBuilder(reverseStringWithSpaces).reverse().append(new StringBuilder(fractionalPart).reverse()).toString();
            return makeBlock(key, stringWithMoney, isInnerAsset);
        } else {
            return "";
        }
    }

    private String makeBlockWithDescriptionPledges(String key, AssetDescriptionPledge descriptionPledge, boolean isInnerAsset) {
        if (descriptionPledge != null && descriptionPledge.getPledges() != null) {
            StringBuilder str = new StringBuilder();
            createAssetHtml(descriptionPledge.getPledges(), str, true);
            return makeBlock(key, str, isInnerAsset);
        } else {
            return "";
        }
    }
}
