package com.baltinfo.radius.loadauction.service;

import com.baltinfo.radius.loadauction.model.AssetDescriptionPledge;
import com.baltinfo.radius.loadauction.model.AssetFull;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Фабрика для создания определённого типа Asset
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 27.12.2019
 */
public class AssetFactory {
    private static final Logger logger = LoggerFactory.getLogger(AssetFactory.class);

    public Optional<Asset> getAsset(AssetFull assetFull) {
        Asset asset;
        Integer assetType;
        try {
            assetType = assetFull.getTypeAsset();
        } catch (Exception e) {
            logger.error("AssetFull is null!");
            return Optional.empty();
        }
        if (assetType != null) {
            try {
                switch (assetType) {
                    case 1:
                        asset = createAssetLand(assetFull);
                        break;
                    case 2:
                        asset = createAssetApartment(assetFull);
                        break;
                    case 3:
                        asset = createAssetHouse(assetFull);
                        break;
                    case 4:
                        asset = createAssetCommercialProperty(assetFull);
                        break;
                    case 5:
                        asset = createAssetConstruction(assetFull);
                        break;
                    case 6:
                        asset = createAssetGarage(assetFull);
                        break;
                    case 7:
                    case 8:
                        asset = createAssetCar(assetFull);
                        break;
                    case 9:
                        asset = createAssetOtherTransport(assetFull);
                        break;
                    case 10:
                        asset = createAssetTruck(assetFull);
                        break;
                    case 11:
                        asset = createAssetGem(assetFull);
                        break;
                    case 12:
                        asset = createAssetCoin(assetFull);
                        break;
                    case 13:
                        asset = createAssetArt(assetFull);
                        break;
                    case 14:
                        asset = createAssetStock(assetFull);
                        break;
                    case 15:
                        asset = createAssetBond(assetFull);
                        break;
                    case 16:
                        asset = createAssetBill(assetFull);
                        break;
                    case 17:
                        asset = createAssetShare(assetFull);
                        break;
                    case 18:
                        asset = createAssetInvestments(assetFull);
                        break;
                    case 19:
                        asset = createAssetMortgage(assetFull);
                        break;
                    case 20:
                        asset = createAssetOther(assetFull);
                        break;
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 35:
                        asset = createAssetCarLoan(assetFull);
                        break;
                    case 25:
                    case 26:
                        asset = createAssetLaw(assetFull);
                        break;
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                        asset = createAssetEquipment(assetFull);
                        break;
                    case 34:
                        asset = createAssetIntangible(assetFull);
                        break;
                    default:
                        logger.error("Illegal value of Asset_type field in xml-file");
                        return Optional.empty();
                }
            } catch (Exception e) {
                logger.error("Some problem while Asset was creating!", e);
                return Optional.empty();
            }
        } else {
            logger.error("Asset_type is not defined!");
            return Optional.empty();
        }
        return Optional.of(asset);
    }

    public Result<List<Asset>, String> getListOfAsset(List<AssetFull> assetFulls) {
        List<Asset> assets = new ArrayList<>();
        for (AssetFull assetFull : assetFulls) {
            Optional<Asset> asset = getAsset(assetFull);
            asset.ifPresent(assets::add);
        }
        if (!assets.isEmpty()) {
            return Result.ok(assets);
        } else {
            return Result.error("Can't convert assetFull to some Asset type");
        }
    }

    private Asset createAssetLand(AssetFull assetFull) {
        return AssetLand.builder()
                .withTypeOfObject(assetFull.getTypeOfObject())
                .withSquare(assetFull.getSquare())
                .withLandCategory(assetFull.getLandCategory())
                .withPermittedUse(assetFull.getPermittedUse())
                .withDistanceToCenter(assetFull.getDistanceToCenter())
                .withCadastralNumber(assetFull.getCadastralNumber())
                .withAssessmentValue(assetFull.getAssessmentValue())
                .withAssessmentDate(assetFull.getAssessmentDate())
                .withCommunications(assetFull.getCommunications())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .withAddress(assetFull.getAddress())
                .withShareFO(assetFull.getShareFO())
                .build();
    }

    private Asset createAssetApartment(AssetFull assetFull) {
        return AssetApartment.builder()
                .withTypeOfObject(assetFull.getTypeOfObject())
                .withSquare(assetFull.getSquare())
                .withAddress(assetFull.getAddress())
                .withCadastralNumber(assetFull.getCadastralNumber())
                .withYearBuilt(assetFull.getYearBuilt())
                .withNumberOfRooms(assetFull.getNumberOfRooms())
                .withFloorNumber(assetFull.getFloorNumber())
                .withFloors(assetFull.getFloors())
                .withCeilingHeight(assetFull.getCeilingHeight())
                .withLivingSpace(assetFull.getLivingSpace())
                .withKitchenArea(assetFull.getKitchenArea())
                .withElevator(assetFull.getElevator())
                .withHouseType(assetFull.getHouseType())
                .withBalcony(assetFull.getBalcony())
                .withViewFromTheWindow(assetFull.getViewFromTheWindow())
                .withCommunications(assetFull.getCommunications())
                .withAssessmentValue(assetFull.getAssessmentValue())
                .withAssessmentDate(assetFull.getAssessmentDate())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .withShareFO(assetFull.getShareFO())
                .build();
    }

    private Asset createAssetHouse(AssetFull assetFull) {
        return AssetHouse.builder()
                .withTypeOfObject(assetFull.getTypeOfObject())
                .withSquare(assetFull.getSquare())
                .withNumberOfFloors(assetFull.getNumberOfFloors())
                .withMoreAboutNumberOfFloors(assetFull.getMoreAboutNumberOfFloors())
                .withCadastralNumber(assetFull.getCadastralNumber())
                .withYearBuilt(assetFull.getYearBuilt())
                .withWallMaterial(assetFull.getWallMaterial())
                .withRoofingMaterial(assetFull.getRoofingMaterial())
                .withTransportAccessibility(assetFull.getTransportAccessibility())
                .withFoundation(assetFull.getFoundation())
                .withCommunications(assetFull.getCommunications())
                .withAssessmentValue(assetFull.getAssessmentValue())
                .withAssessmentDate(assetFull.getAssessmentDate())
                .withAddress(assetFull.getAddress())
                .withShareFO(assetFull.getShareFO())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .build();
    }

    private Asset createAssetCommercialProperty(AssetFull assetFull) {
        return AssetCommercialProperty.builder()
                .withTypeOfObject(assetFull.getTypeOfObject())
                .withSquare(assetFull.getSquare())
                .withAddress(assetFull.getAddress())
                .withFloorNumber(assetFull.getFloorNumber())
                .withFloors(assetFull.getFloors())
                .withCadastralNumber(assetFull.getCadastralNumber())
                .withCommunications(assetFull.getCommunications())
                .withCategory(assetFull.getCategory())
                .withAssessmentValue(assetFull.getAssessmentValue())
                .withAssessmentDate(assetFull.getAssessmentDate())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .withShareFO(assetFull.getShareFO())
                .withYearBuilt(assetFull.getYearBuilt())
                .build();
    }

    private Asset createAssetConstruction(AssetFull assetFull) {
        return AssetConstruction.builder()
                .withTypeOfObject(assetFull.getTypeOfObject())
                .withSquare(assetFull.getSquare())
                .withAddress(assetFull.getAddress())
                .withReadiness(assetFull.getReadiness())
                .withCadastralNumber(assetFull.getCadastralNumber())
                .withAssessmentValue(assetFull.getAssessmentValue())
                .withAssessmentDate(assetFull.getAssessmentDate())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .withShareFO(assetFull.getShareFO())
                .withFloors(assetFull.getFloors())
                .build();
    }

    private Asset createAssetGarage(AssetFull assetFull) {
        return AssetGarage.builder()
                .withTypeOfObject(assetFull.getTypeOfObject())
                .withSquare(assetFull.getSquare())
                .withGarageType(assetFull.getGarageType())
                .withTypeOfParking(assetFull.getTypeOfParking())
                .withCadastralNumber(assetFull.getCadastralNumber())
                .withCommunications(assetFull.getCommunications())
                .withPresenceOfSecurity(assetFull.getPresenceOfSecurity())
                .withAssessmentValue(assetFull.getAssessmentValue())
                .withAssessmentDate(assetFull.getAssessmentDate())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAddress(assetFull.getAddress())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .withShareFO(assetFull.getShareFO())
                .build();
    }

    private Asset createAssetCar(AssetFull assetFull) {
        return AssetCar.builder()
                .withMark(assetFull.getMark())
                .withModel(assetFull.getModel())
                .withColor(assetFull.getColor())
                .withYearOfIssue(assetFull.getYearOfIssue())
                .withMileage(assetFull.getMileage())
                .withEngineCapacity(assetFull.getEngineCapacity())
                .withCarBody(assetFull.getCarBody())
                .withSteeringWheel(assetFull.getSteeringWheel())
                .withTransmission(assetFull.getTransmission())
                .withEnginePower(assetFull.getEnginePower())
                .withEngineType(assetFull.getEngineType())
                .withTypeOfDrive(assetFull.getTypeOfDrive())
                .withVin(assetFull.getVin())
                .withAddress(assetFull.getAddress())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .build();
    }

    private Asset createAssetOtherTransport(AssetFull assetFull) {
        return AssetOtherTransport.builder()
                .withAddress(assetFull.getAddress())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .build();
    }

    private Asset createAssetTruck(AssetFull assetFull) {
        return AssetTruck.builder()
                .withTypeOfTransport(assetFull.getTypeOfTransport())
                .withMark(assetFull.getMark())
                .withModel(assetFull.getModel())
                .withColor(assetFull.getColor())
                .withYearOfIssue(assetFull.getYearOfIssue())
                .withMileage(assetFull.getMileage())
                .withEngineCapacity(assetFull.getEngineCapacity())
                .withTransmission(assetFull.getTransmission())
                .withEnginePower(assetFull.getEnginePower())
                .withEngineType(assetFull.getEngineType())
                .withTypeOfDrive(assetFull.getTypeOfDrive())
                .withVin(assetFull.getVin())
                .withSteeringWheel(assetFull.getSteeringWheel())
                .withAddress(assetFull.getAddress())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .build();
    }

    private Asset createAssetGem(AssetFull assetFull) {
        return AssetGem.builder()
                .withTypeOfObject(assetFull.getTypeOfObject())
                .withMetal(assetFull.getMetal())
                .withSample(assetFull.getSample())
                .withTotalWeight(assetFull.getTotalWeight())
                .withNameStone(assetFull.getNameStone())
                .withAddress(assetFull.getAddress())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .withTypeThings(assetFull.getTypeThings())
                .build();
    }

    private Asset createAssetCoin(AssetFull assetFull) {
        return AssetCoin.builder()
                .withTypeOfObject(assetFull.getTypeOfObject())
                .withName(assetFull.getName())
                .withCountry(assetFull.getCountry())
                .withYearOfIssue(assetFull.getYearOfIssue())
                .withSeries(assetFull.getSeries())
                .withMetal(assetFull.getMetal())
                .withSample(assetFull.getSample())
                .withNominalCost(assetFull.getNominalCost())
                .withWeight(assetFull.getWeight())
                .withAddress(assetFull.getAddress())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .withAmount(assetFull.getAmount())
                .build();
    }

    private Asset createAssetArt(AssetFull assetFull) {
        return AssetArt.builder()
                .withTypeOfObject(assetFull.getTypeOfObject())
                .withProductName(assetFull.getProductName())
                .withAuthor(assetFull.getAuthor())
                .withDate(assetFull.getDate())
                .withSize(assetFull.getSize())
                .withMaterial(assetFull.getMaterial())
                .withExpertiseInfo(assetFull.getExpertiseInfo())
                .withAddress(assetFull.getAddress())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .build();
    }

    private Asset createAssetStock(AssetFull assetFull) {
        return AssetStock.builder()
                .withView(assetFull.getView())
                .withIssuerName(assetFull.getIssuerName())
                .withIssuerInn(assetFull.getIssuerInn())
                .withAmount(assetFull.getAmount())
                .withShare(assetFull.getShare())
                .withRegistrationNumber(assetFull.getRegistrationNumber())
                .withIsin(assetFull.getIsin())
                .withNominalCost(assetFull.getNominalCost())
                .withCurrency(assetFull.getCurrency())
                .withIssuerLocation(assetFull.getIssuerLocation())
                .withStorage(assetFull.getStorage())
                .withUrl(assetFull.getUrl())
                .withAssessmentValue(assetFull.getAssessmentValue())
                .withAssessmentDate(assetFull.getAssessmentDate())
                .withTurnOverLimit(assetFull.getTurnOverLimit())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .build();
    }

    private Asset createAssetBond(AssetFull assetFull) {
        return AssetBond.builder()
                .withTypeBond(assetFull.getTypeBond())
                .withIssuerName(assetFull.getIssuerName())
                .withIssuerInn(assetFull.getIssuerInn())
                .withAmount(assetFull.getAmount())
                .withCouponRate(assetFull.getCouponRate())
                .withPaymentFrequency(assetFull.getPaymentFrequency())
                .withMaturityDate(assetFull.getMaturityDate())
                .withRegistrationNumber(assetFull.getRegistrationNumber())
                .withIsin(assetFull.getIsin())
                .withNominalCost(assetFull.getNominalCost())
                .withCurrency(assetFull.getCurrency())
                .withIssuerLocation(assetFull.getIssuerLocation())
                .withStorage(assetFull.getStorage())
                .withDefaults(assetFull.getDefaults())
                .withBankruptcyOfTheIssuer(assetFull.getBankruptcyOfTheIssuer())
                .withUrl(assetFull.getUrl())
                .withAssessmentValue(assetFull.getAssessmentValue())
                .withAssessmentDate(assetFull.getAssessmentDate())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .withTurnoverLimit(assetFull.getTurnOverLimit())
                .build();
    }

    private Asset createAssetBill(AssetFull assetFull) {
        return AssetBill.builder()
                .withView(assetFull.getView())
                .withPromiser(assetFull.getPromiser())
                .withPromiserInn(assetFull.getPromiserInn())
                .withInterestRate(assetFull.getInterestRate())
                .withBillAmount(assetFull.getBillAmount())
                .withCurrency(assetFull.getCurrency())
                .withDateOfPreparation(assetFull.getDateOfPreparation())
                .withPlaceOfCompilation(assetFull.getPlaceOfCompilation())
                .withObligatedPersonName(assetFull.getObligatedPersonName())
                .withAvalist(assetFull.getAvalist())
                .withPaymentTerm(assetFull.getAssessmentDate())
                .withPlaceOfPayment(assetFull.getPlaceOfPayment())
                .withPromiserLocation(assetFull.getPromiserLocation())
                .withUrl(assetFull.getUrl())
                .withStorage(assetFull.getStorage())
                .withPromiserBankrot(assetFull.getPromiserBankrot())
                .withAssessmentValue(assetFull.getAssessmentValue())
                .withAssessmentDate(assetFull.getAssessmentDate())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .withTurnoverLimit(assetFull.getTurnOverLimit())
                .build();
    }

    private Asset createAssetShare(AssetFull assetFull) {
        return AssetShare.builder()
                .withAssociationName(assetFull.getAssociationName())
                .withAssociationInn(assetFull.getAssociationInn())
                .withShare(assetFull.getShare())
                .withRealActivity(assetFull.getRealActivity())
                .withNominalCost(assetFull.getNominalCost())
                .withAssociationLocation(assetFull.getAssociationLocation())
                .withUrl(assetFull.getUrl())
                .withAssessmentValue(assetFull.getAssessmentValue())
                .withAssessmentDate(assetFull.getAssessmentDate())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .withTurnoverLimit(assetFull.getTurnOverLimit())
                .build();
    }

    private Asset createAssetInvestments(AssetFull assetFull) {
        return AssetInvestments.builder()
                .withFundName(assetFull.getFundName())
                .withFundCategory(assetFull.getFundCategory())
                .withFundType(assetFull.getFundType())
                .withRegistrationNumber(assetFull.getRegistrationNumber())
                .withTermTrustManagement(assetFull.getTermTrustManagement())
                .withNumberOfShares(assetFull.getNumberOfShares())
                .withPartOfAllShares(assetFull.getPartOfAllShares())
                .withStorageOfShare(assetFull.getStorageOfShare())
                .withManagementCompanyName(assetFull.getManagementCompanyName())
                .withIntervals(assetFull.getIntervals())
                .withMainAssets(assetFull.getMainAssets())
                .withAssessmentValue(assetFull.getAssessmentValue())
                .withAssessmentDate(assetFull.getAssessmentDate())
                .withUrl(assetFull.getUrl())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .withTurnoverLimit(assetFull.getTurnOverLimit())
                .build();
    }

    private Asset createAssetMortgage(AssetFull assetFull) {
        return AssetMortgage.builder()
                .withIsuName(assetFull.getIsuName())
                .withManagementCompany(assetFull.getManagementCompany())
                .withTermTrustManagement(assetFull.getTermTrustManagement())
                .withAmount(assetFull.getAmount())
                .withPartOfAllAmount(assetFull.getPartOfAllAmount())
                .withStorage(assetFull.getStorage())
                .withMortgageCoverage(assetFull.getMortgageCoverage())
                .withRegistrationNumber(assetFull.getRegistrationNumber())
                .withAssessmentValue(assetFull.getAssessmentValue())
                .withAssessmentDate(assetFull.getAssessmentDate())
                .withUrl(assetFull.getUrl())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .withTurnoverLimit(assetFull.getTurnOverLimit())
                .build();
    }

    private Asset createAssetOther(AssetFull assetFull) {
        return AssetOther.builder()
                .withNameCb(assetFull.getNameCb())
                .withTypeCb(assetFull.getTypeCb())
                .withIssuer(assetFull.getIssuer())
                .withNumber(assetFull.getNumber())
                .withTerm(assetFull.getTerm())
                .withStorage(assetFull.getStorage())
                .withSupportingDocuments(assetFull.getSupportingDocuments())
                .withCounterparty(assetFull.getCounterparty())
                .withAssessmentValue(assetFull.getAssessmentValue())
                .withAssessmentDate(assetFull.getAssessmentDate())
                .withUrl(assetFull.getUrl())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .withAmount(assetFull.getAmount())
                .withTurnoverLimit(assetFull.getTurnOverLimit())
                .build();
    }

    private Asset createAssetCarLoan(AssetFull assetFull) {
        AssetDescriptionPledge descriptionPledge = null;
        if (assetFull.getDescriptionPledges() != null) {
            List<Asset> assets = getListOfAsset(assetFull.getDescriptionPledges().getPledges()).getResultOrThrow();
            descriptionPledge = new AssetDescriptionPledge(assets);
        }
        return AssetCarLoan.builder()
                .withCreditType(assetFull.getCreditType())
                .withAverageInterestRate(assetFull.getAverageInterestRate())
                .withAverageRepaymentDate(assetFull.getAverageRepaymentDate())
                .withDebtRepaymentMethod(assetFull.getDebtRepaymentMethod())
                .withAvailability(assetFull.getAvailability())
                .withCurrency(assetFull.getCurrency())
                .withKd(assetFull.getKd())
                .withBalanceDate(assetFull.getDate())
                .withDateKd(assetFull.getDateKD())
                .withAmountOfBalance(assetFull.getAmountOfBalance())
                .withDocumentStorage(assetFull.getDocumentStorage())
                .withOverdue(assetFull.getOverdue())
                .withTrial(assetFull.getTrial())
                .withEndDate(assetFull.getDate())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .withDescriptionPledges(descriptionPledge)
                .build();
    }

    private Asset createAssetLaw(AssetFull assetFull) {
        AssetDescriptionPledge descriptionPledge = null;
        if (assetFull.getDescriptionPledges() != null) {
            List<Asset> assets = getListOfAsset(assetFull.getDescriptionPledges().getPledges()).getResultOrThrow();
            descriptionPledge = new AssetDescriptionPledge(assets);
        }

        return AssetLaw.builder()
                .withBorrowerName(assetFull.getBorrowerName())
                .withBorrowerInn(assetFull.getBorrowerInn())
                .withKd(assetFull.getKd())
                .withWeightAverageInterestRate(assetFull.getWeightAverageInterestRate())
                .withDateKd(assetFull.getDateKD())
                .withAverageRepaymentDate(assetFull.getAverageRepaymentDate())
                .withDebtRepaymentMethod(assetFull.getDebtRepaymentMethod())
                .withAvailability(assetFull.getAvailability())
                .withCurrency(assetFull.getCurrency())
                .withAmountOfBalance(assetFull.getAmountOfBalance())
                .withOverdue(assetFull.getOverdue())
                .withTrial(assetFull.getTrial())
                .withEndDate(assetFull.getDate())
                .withDocumentStorage(assetFull.getDocumentStorage())
                .withCreditType(assetFull.getCreditType())
                .withBalanceDate(assetFull.getBalanceDate())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .withDescriptionPledges(descriptionPledge)
                .build();
    }

    private Asset createAssetEquipment(AssetFull assetFull) {
        return AssetEquipment.builder()
                .withTypeOfObject(assetFull.getTypeOfObject())
                .withModel(assetFull.getModel())
                .withMark(assetFull.getMark())
                .withAddress(assetFull.getAddress())
                .withAmount(assetFull.getAmount())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .build();
    }

    private Asset createAssetIntangible(AssetFull assetFull) {
        return AssetIntangible.builder()
                .withNameRightHolder(assetFull.getNameRightHolder())
                .withAddress(assetFull.getAddress())
                .withNumberOfSvidet(assetFull.getNumberOfSvidet())
                .withTypeAsset(assetFull.getTypeAsset())
                .withIdSya(assetFull.getIdSya())
                .withAbout(assetFull.getAbout())
                .withNameFoAsset(assetFull.getNameFoAsset())
                .withNameAsset(assetFull.getNameAsset())
                .withLocationAsset(assetFull.getLocationAsset())
                .withAboutAsset(assetFull.getAboutAsset())
                .withSoonAsset(assetFull.getSoonAsset())
                .withDeposits(assetFull.getDeposits())
                .withOther(assetFull.getOther())
                .withRestrictionsAsset(assetFull.getRestrictionsAsset())
                .build();
    }
}
