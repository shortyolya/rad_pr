package com.baltinfo.radius.application.configuration;

import com.baltinfo.radius.db.constants.AvitoCategoryCode;
import com.baltinfo.radius.db.constants.CianCategoryCode;
import com.baltinfo.radius.db.controller.AvitoController;
import com.baltinfo.radius.db.controller.AvitoMovableController;
import com.baltinfo.radius.db.controller.CianController;
import com.baltinfo.radius.db.controller.CianHouseController;
import com.baltinfo.radius.db.controller.VAvitoAutoController;
import com.baltinfo.radius.db.controller.VAvitoRealEstateController;
import com.baltinfo.radius.db.controller.CianFlatController;
import com.baltinfo.radius.db.controller.CianShareController;
import com.baltinfo.radius.db.controller.CianNewBuildingController;
import com.baltinfo.radius.db.controller.VCianRealEstateController;
import com.baltinfo.radius.db.model.FeedObject;
import com.baltinfo.radius.feed.avito.converter.AvitoAutoConverter;
import com.baltinfo.radius.feed.avito.converter.AvitoAutoPartsConverter;
import com.baltinfo.radius.feed.avito.converter.AvitoBusinessConverter;
import com.baltinfo.radius.feed.avito.converter.AvitoBusinessEquipmentConverter;
import com.baltinfo.radius.feed.avito.converter.AvitoCollectingConverter;
import com.baltinfo.radius.feed.avito.converter.AvitoCommercialRealEstateConverter;
import com.baltinfo.radius.feed.avito.converter.AvitoComputerProductsConverter;
import com.baltinfo.radius.feed.avito.converter.AvitoConverter;
import com.baltinfo.radius.feed.avito.converter.AvitoFlatConverter;
import com.baltinfo.radius.feed.avito.converter.AvitoFurnitureConverter;
import com.baltinfo.radius.feed.avito.converter.AvitoGarageConverter;
import com.baltinfo.radius.feed.avito.converter.AvitoHouseConverter;
import com.baltinfo.radius.feed.avito.converter.AvitoJewelryConverter;
import com.baltinfo.radius.feed.avito.converter.AvitoLandPlotConverter;
import com.baltinfo.radius.feed.avito.converter.AvitoRoomConverter;
import com.baltinfo.radius.feed.avito.converter.AvitoTruckConverter;
import com.baltinfo.radius.feed.avito.converter.AvitoWaterTransportConverter;
import com.baltinfo.radius.feed.cian.converter.CianBuildingConverter;
import com.baltinfo.radius.feed.cian.converter.CianBusinessConverter;
import com.baltinfo.radius.feed.cian.converter.CianCommercialLandConverter;
import com.baltinfo.radius.feed.cian.converter.CianConverter;
import com.baltinfo.radius.feed.cian.converter.CianFlatConverter;
import com.baltinfo.radius.feed.cian.converter.CianFlatShareConverter;
import com.baltinfo.radius.feed.cian.converter.CianFreeAppointmentObjectConverter;
import com.baltinfo.radius.feed.cian.converter.CianGarageConverter;
import com.baltinfo.radius.feed.cian.converter.CianHouseConverter;
import com.baltinfo.radius.feed.cian.converter.CianHouseShareConverter;
import com.baltinfo.radius.feed.cian.converter.CianIndustryConverter;
import com.baltinfo.radius.feed.cian.converter.CianLandConverter;
import com.baltinfo.radius.feed.cian.converter.CianOfficeConverter;
import com.baltinfo.radius.feed.cian.converter.CianRoomConverter;
import com.baltinfo.radius.feed.cian.converter.CianShoppingAreaConverter;
import com.baltinfo.radius.feed.cian.converter.CianWarehouseConverter;
import com.baltinfo.radius.feed.services.FeedDescriptionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Suvorina Aleksandra
 * @since 21.09.2020
 */
@Configuration
public class FeedMappingConfiguration {

    @Bean
    FeedDescriptionService feedDescriptionService() {
        return new FeedDescriptionService();
    }

    @Bean
    VAvitoRealEstateController vAvitoRealEstateController() {
        return new VAvitoRealEstateController();
    }

    @Bean
    AvitoMovableController avitoMovableController() {
        return new AvitoMovableController();
    }

    @Bean
    VAvitoAutoController vAvitoAutoController() {
        return new VAvitoAutoController();
    }

    @Bean
    AvitoTruckConverter avitoTruckConverter(FeedDescriptionService feedDescriptionService) {
        return new AvitoTruckConverter(feedDescriptionService);
    }

    @Bean
    AvitoWaterTransportConverter avitoWaterConverter(FeedDescriptionService feedDescriptionService) {
        return new AvitoWaterTransportConverter(feedDescriptionService);
    }

    @Bean
    AvitoFlatConverter avitoFlatConverter(FeedDescriptionService feedDescriptionService) {
        return new AvitoFlatConverter(feedDescriptionService);
    }

    @Bean
    AvitoHouseConverter avitoHouseConverter(FeedDescriptionService feedDescriptionService) {
        return new AvitoHouseConverter(feedDescriptionService);
    }

    @Bean
    AvitoRoomConverter avitoRoomConverter(FeedDescriptionService feedDescriptionService) {
        return new AvitoRoomConverter(feedDescriptionService);
    }

    @Bean
    AvitoLandPlotConverter avitoLandPlotConverter(FeedDescriptionService feedDescriptionService) {
        return new AvitoLandPlotConverter(feedDescriptionService);
    }

    @Bean
    AvitoGarageConverter avitoGarageConverter(FeedDescriptionService feedDescriptionService) {
        return new AvitoGarageConverter(feedDescriptionService);
    }

    @Bean
    AvitoCommercialRealEstateConverter avitoCommercialRealEstateConverter(FeedDescriptionService feedDescriptionService) {
        return new AvitoCommercialRealEstateConverter(feedDescriptionService);
    }

    @Bean
    AvitoBusinessEquipmentConverter avitoBusinessEquipmentConverter(FeedDescriptionService feedDescriptionService) {
        return new AvitoBusinessEquipmentConverter(feedDescriptionService);
    }

    @Bean
    AvitoBusinessConverter avitoBusinessConverter(FeedDescriptionService feedDescriptionService) {
        return new AvitoBusinessConverter(feedDescriptionService);
    }

    @Bean
    AvitoComputerProductsConverter avitoComputerProductsConverter(FeedDescriptionService feedDescriptionService) {
        return new AvitoComputerProductsConverter(feedDescriptionService);
    }

    @Bean
    AvitoAutoPartsConverter avitoAutoPartsConverter(FeedDescriptionService feedDescriptionService) {
        return new AvitoAutoPartsConverter(feedDescriptionService);
    }

    @Bean
    AvitoCollectingConverter avitoCollectingConverter(FeedDescriptionService feedDescriptionService) {
        return new AvitoCollectingConverter(feedDescriptionService);
    }

    @Bean
    AvitoJewelryConverter avitoJewelryConverter(FeedDescriptionService feedDescriptionService) {
        return new AvitoJewelryConverter(feedDescriptionService);
    }

    @Bean
    AvitoAutoConverter avitoAutoConverter(FeedDescriptionService feedDescriptionService) {
        return new AvitoAutoConverter(feedDescriptionService);
    }

    @Bean
    AvitoFurnitureConverter avitoFurnitureConverter(FeedDescriptionService feedDescriptionService) {
        return new AvitoFurnitureConverter(feedDescriptionService);
    }

    @Bean
    Map<AvitoCategoryCode, AvitoController<? extends FeedObject>> avitoFeedControllers(VAvitoRealEstateController vAvitoRealEstateController,
                                                                                       AvitoMovableController avitoMovableController,
                                                                                       VAvitoAutoController vAvitoAutoController) {
        Map<AvitoCategoryCode, AvitoController<? extends FeedObject>> avitoFeedControllers = new HashMap<>();
        avitoFeedControllers.put(AvitoCategoryCode.FLAT, vAvitoRealEstateController);
        avitoFeedControllers.put(AvitoCategoryCode.ROOM, vAvitoRealEstateController);
        avitoFeedControllers.put(AvitoCategoryCode.HOUSE, vAvitoRealEstateController);
        avitoFeedControllers.put(AvitoCategoryCode.LAND_PLOT, vAvitoRealEstateController);
        avitoFeedControllers.put(AvitoCategoryCode.GARAGE, vAvitoRealEstateController);
        avitoFeedControllers.put(AvitoCategoryCode.COMMERCIAL_REAL_ESTATE, vAvitoRealEstateController);
        avitoFeedControllers.put(AvitoCategoryCode.TRUCK, avitoMovableController);
        avitoFeedControllers.put(AvitoCategoryCode.WATER_TRANSPORT, avitoMovableController);
        avitoFeedControllers.put(AvitoCategoryCode.BUSINESS_EQUIPMENT, avitoMovableController);
        avitoFeedControllers.put(AvitoCategoryCode.BUSINESS, avitoMovableController);
        avitoFeedControllers.put(AvitoCategoryCode.COMPUTER_PRODUCTS, avitoMovableController);
        avitoFeedControllers.put(AvitoCategoryCode.AUTO_PARTS, avitoMovableController);
        avitoFeedControllers.put(AvitoCategoryCode.COLLECTING, avitoMovableController);
        avitoFeedControllers.put(AvitoCategoryCode.JEWELRY, avitoMovableController);
        avitoFeedControllers.put(AvitoCategoryCode.AUTO, vAvitoAutoController);
        avitoFeedControllers.put(AvitoCategoryCode.FURNITURE, avitoMovableController);
        return avitoFeedControllers;
    }

    @Bean
    Map<AvitoCategoryCode, AvitoConverter> avitoFeedConverters(AvitoTruckConverter avitoTruckConverter,
                                                               AvitoWaterTransportConverter avitoWaterConverter,
                                                               AvitoFlatConverter avitoFlatConverter,
                                                               AvitoRoomConverter avitoRoomConverter,
                                                               AvitoHouseConverter avitoHouseConverter,
                                                               AvitoLandPlotConverter avitoLandPlotConverter,
                                                               AvitoGarageConverter avitoGarageConverter,
                                                               AvitoCommercialRealEstateConverter avitoCommercialRealEstateConverter,
                                                               AvitoBusinessEquipmentConverter avitoBusinessEquipmentConverter,
                                                               AvitoBusinessConverter avitoBusinessConverter,
                                                               AvitoComputerProductsConverter avitoComputerProductsConverter,
                                                               AvitoAutoPartsConverter avitoAutoPartsConverter,
                                                               AvitoCollectingConverter avitoCollectingConverter,
                                                               AvitoJewelryConverter avitoJewelryConverter,
                                                               AvitoAutoConverter avitoAutoConverter,
                                                               AvitoFurnitureConverter avitoFurnitureConverter
    ) {
        Map<AvitoCategoryCode, AvitoConverter> avitoFeedConverters = new HashMap<>();
        avitoFeedConverters.put(AvitoCategoryCode.FLAT, avitoFlatConverter);
        avitoFeedConverters.put(AvitoCategoryCode.ROOM, avitoRoomConverter);
        avitoFeedConverters.put(AvitoCategoryCode.HOUSE, avitoHouseConverter);
        avitoFeedConverters.put(AvitoCategoryCode.LAND_PLOT, avitoLandPlotConverter);
        avitoFeedConverters.put(AvitoCategoryCode.GARAGE, avitoGarageConverter);
        avitoFeedConverters.put(AvitoCategoryCode.COMMERCIAL_REAL_ESTATE, avitoCommercialRealEstateConverter);
        avitoFeedConverters.put(AvitoCategoryCode.TRUCK, avitoTruckConverter);
        avitoFeedConverters.put(AvitoCategoryCode.WATER_TRANSPORT, avitoWaterConverter);
        avitoFeedConverters.put(AvitoCategoryCode.BUSINESS_EQUIPMENT, avitoBusinessEquipmentConverter);
        avitoFeedConverters.put(AvitoCategoryCode.BUSINESS, avitoBusinessConverter);
        avitoFeedConverters.put(AvitoCategoryCode.COMPUTER_PRODUCTS, avitoComputerProductsConverter);
        avitoFeedConverters.put(AvitoCategoryCode.AUTO_PARTS, avitoAutoPartsConverter);
        avitoFeedConverters.put(AvitoCategoryCode.COLLECTING, avitoCollectingConverter);
        avitoFeedConverters.put(AvitoCategoryCode.JEWELRY, avitoJewelryConverter);
        avitoFeedConverters.put(AvitoCategoryCode.AUTO, avitoAutoConverter);
        avitoFeedConverters.put(AvitoCategoryCode.FURNITURE, avitoFurnitureConverter);
        return avitoFeedConverters;
    }

    @Bean
    VCianRealEstateController vCianRealEstateController() {
        return new VCianRealEstateController();
    }

    @Bean
    CianFlatController cianFlatController() { return new CianFlatController(); }

    @Bean
    CianShareController cianFlatShareController() { return new CianShareController(); }

    @Bean
    CianNewBuildingController cianNewBuildingController() { return new CianNewBuildingController(); }

    @Bean
    CianHouseController cianHouseController() { return new CianHouseController(); }

    @Bean
    CianBuildingConverter cianBuildingConverter(FeedDescriptionService feedDescriptionService) {
        return new CianBuildingConverter(feedDescriptionService);
    }

    @Bean
    CianBusinessConverter cianBusinessConverter(FeedDescriptionService feedDescriptionService) {
        return new CianBusinessConverter(feedDescriptionService);
    }

    @Bean
    CianCommercialLandConverter cianCommercialLandConverter(FeedDescriptionService feedDescriptionService) {
        return new CianCommercialLandConverter(feedDescriptionService);
    }

    @Bean
    CianFreeAppointmentObjectConverter cianFreeAppointmentObjectConverter(FeedDescriptionService feedDescriptionService) {
        return new CianFreeAppointmentObjectConverter(feedDescriptionService);
    }

    @Bean
    CianGarageConverter cianGarageConverter(FeedDescriptionService feedDescriptionService) {
        return new CianGarageConverter(feedDescriptionService);
    }

    @Bean
    CianIndustryConverter cianIndustryConverter(FeedDescriptionService feedDescriptionService) {
        return new CianIndustryConverter(feedDescriptionService);
    }

    @Bean
    CianOfficeConverter cianOfficeConverter(FeedDescriptionService feedDescriptionService) {
        return new CianOfficeConverter(feedDescriptionService);
    }

    @Bean
    CianShoppingAreaConverter cianShoppingAreaConverter(FeedDescriptionService feedDescriptionService) {
        return new CianShoppingAreaConverter(feedDescriptionService);
    }

    @Bean
    CianWarehouseConverter cianWarehouseConverter(FeedDescriptionService feedDescriptionService) {
        return new CianWarehouseConverter(feedDescriptionService);
    }

    @Bean
    CianFlatConverter cianFlatConverter(FeedDescriptionService feedDescriptionService) {
        return new CianFlatConverter(feedDescriptionService);
    }

    @Bean
    CianFlatShareConverter cianFlatShareConverter(FeedDescriptionService feedDescriptionService) {
        return new CianFlatShareConverter(feedDescriptionService);
    }

    @Bean
    CianRoomConverter cianRoomConverter(FeedDescriptionService feedDescriptionService) {
        return new CianRoomConverter(feedDescriptionService);
    }

    @Bean
    CianHouseConverter cianHouseConverter(FeedDescriptionService feedDescriptionService) {
        return new CianHouseConverter(feedDescriptionService);
    }

    @Bean
    CianHouseShareConverter cianHouseShareConverter(FeedDescriptionService feedDescriptionService) {
        return new CianHouseShareConverter(feedDescriptionService);
    }

    @Bean
    CianLandConverter cianLandConverter(FeedDescriptionService feedDescriptionService) {
        return new CianLandConverter(feedDescriptionService);
    }

    @Bean
    Map<CianCategoryCode, CianConverter> cianFeedConverters(CianBuildingConverter cianBuildingConverter,
                                                            CianBusinessConverter cianBusinessConverter,
                                                            CianCommercialLandConverter cianCommercialLandConverter,
                                                            CianFreeAppointmentObjectConverter cianFreeAppointmentObjectConverter,
                                                            CianGarageConverter cianGarageConverter,
                                                            CianIndustryConverter cianIndustryConverter,
                                                            CianOfficeConverter cianOfficeConverter,
                                                            CianShoppingAreaConverter cianShoppingAreaConverter,
                                                            CianWarehouseConverter cianWarehouseConverter,
                                                            CianFlatConverter cianFlatConverter,
                                                            CianFlatShareConverter cianFlatShareConverter,
                                                            CianRoomConverter cianRoomConverter,
                                                            CianHouseConverter cianHouseConverter,
                                                            CianHouseShareConverter cianHouseShareConverter,
                                                            CianLandConverter cianLandConverter
    ) {
        Map<CianCategoryCode, CianConverter> cianFeedConverters = new HashMap<>();
        cianFeedConverters.put(CianCategoryCode.BUILDING, cianBuildingConverter);
        cianFeedConverters.put(CianCategoryCode.BUSINESS, cianBusinessConverter);
        cianFeedConverters.put(CianCategoryCode.COMMERCIAL_LAND, cianCommercialLandConverter);
        cianFeedConverters.put(CianCategoryCode.FREE_APPOINTMENT_OBJECT, cianFreeAppointmentObjectConverter);
        cianFeedConverters.put(CianCategoryCode.GARAGE, cianGarageConverter);
        cianFeedConverters.put(CianCategoryCode.INDUSTRY, cianIndustryConverter);
        cianFeedConverters.put(CianCategoryCode.OFFICE, cianOfficeConverter);
        cianFeedConverters.put(CianCategoryCode.SHOPPING_AREA, cianShoppingAreaConverter);
        cianFeedConverters.put(CianCategoryCode.WAREHOUSE, cianWarehouseConverter);
        cianFeedConverters.put(CianCategoryCode.BUILDING_RENT, cianBuildingConverter);
        cianFeedConverters.put(CianCategoryCode.BUSINESS_RENT, cianBusinessConverter);
        cianFeedConverters.put(CianCategoryCode.COMMERCIAL_LAND_RENT, cianCommercialLandConverter);
        cianFeedConverters.put(CianCategoryCode.FREE_APPOINTMENT_OBJECT_RENT, cianFreeAppointmentObjectConverter);
        cianFeedConverters.put(CianCategoryCode.GARAGE_RENT, cianGarageConverter);
        cianFeedConverters.put(CianCategoryCode.INDUSTRY_RENT, cianIndustryConverter);
        cianFeedConverters.put(CianCategoryCode.OFFICE_RENT, cianOfficeConverter);
        cianFeedConverters.put(CianCategoryCode.SHOPPING_AREA_RENT, cianShoppingAreaConverter);
        cianFeedConverters.put(CianCategoryCode.WAREHOUSE_RENT, cianWarehouseConverter);

        cianFeedConverters.put(CianCategoryCode.FLAT_SALE, cianFlatConverter);
        cianFeedConverters.put(CianCategoryCode.FLAT_SHARE_SALE, cianFlatShareConverter);
        cianFeedConverters.put(CianCategoryCode.NEW_BUILDING_FLAT_SALE, cianFlatConverter);
        cianFeedConverters.put(CianCategoryCode.ROOM_SALE, cianRoomConverter);
        cianFeedConverters.put(CianCategoryCode.HOUSE_SALE, cianHouseConverter);
        cianFeedConverters.put(CianCategoryCode.COTTAGE_SALE, cianHouseConverter);
        cianFeedConverters.put(CianCategoryCode.TOWNHOUSE_SALE, cianHouseConverter);
        cianFeedConverters.put(CianCategoryCode.LAND_SALE, cianLandConverter);
        cianFeedConverters.put(CianCategoryCode.HOUSE_SHARE_SALE, cianHouseShareConverter);
        return cianFeedConverters;
    }

    @Bean
    Map<CianCategoryCode, CianController<? extends FeedObject>> cianFeedControllers(
            VCianRealEstateController vCianRealEstateController,
            CianFlatController cianFlatController,
            CianShareController cianShareController,
            CianNewBuildingController cianNewBuildingController,
            CianHouseController cianHouseController) {
        Map<CianCategoryCode, CianController<? extends FeedObject>> cianFeedControllers = new HashMap<>();
        cianFeedControllers.put(CianCategoryCode.BUILDING, vCianRealEstateController);
        cianFeedControllers.put(CianCategoryCode.BUSINESS, vCianRealEstateController);
        cianFeedControllers.put(CianCategoryCode.COMMERCIAL_LAND, vCianRealEstateController);
        cianFeedControllers.put(CianCategoryCode.FREE_APPOINTMENT_OBJECT, vCianRealEstateController);
        cianFeedControllers.put(CianCategoryCode.GARAGE, vCianRealEstateController);
        cianFeedControllers.put(CianCategoryCode.INDUSTRY, vCianRealEstateController);
        cianFeedControllers.put(CianCategoryCode.OFFICE, vCianRealEstateController);
        cianFeedControllers.put(CianCategoryCode.SHOPPING_AREA, vCianRealEstateController);
        cianFeedControllers.put(CianCategoryCode.WAREHOUSE, vCianRealEstateController);
        cianFeedControllers.put(CianCategoryCode.BUILDING_RENT, vCianRealEstateController);
        cianFeedControllers.put(CianCategoryCode.BUSINESS_RENT, vCianRealEstateController);
        cianFeedControllers.put(CianCategoryCode.COMMERCIAL_LAND_RENT, vCianRealEstateController);
        cianFeedControllers.put(CianCategoryCode.FREE_APPOINTMENT_OBJECT_RENT, vCianRealEstateController);
        cianFeedControllers.put(CianCategoryCode.GARAGE_RENT, vCianRealEstateController);
        cianFeedControllers.put(CianCategoryCode.INDUSTRY_RENT, vCianRealEstateController);
        cianFeedControllers.put(CianCategoryCode.OFFICE_RENT, vCianRealEstateController);
        cianFeedControllers.put(CianCategoryCode.SHOPPING_AREA_RENT, vCianRealEstateController);
        cianFeedControllers.put(CianCategoryCode.WAREHOUSE_RENT, vCianRealEstateController);

        cianFeedControllers.put(CianCategoryCode.FLAT_SALE, cianFlatController);
        cianFeedControllers.put(CianCategoryCode.FLAT_SHARE_SALE, cianShareController);
        cianFeedControllers.put(CianCategoryCode.NEW_BUILDING_FLAT_SALE, cianNewBuildingController);
        cianFeedControllers.put(CianCategoryCode.ROOM_SALE, vCianRealEstateController);
        cianFeedControllers.put(CianCategoryCode.HOUSE_SALE, cianHouseController);
        cianFeedControllers.put(CianCategoryCode.COTTAGE_SALE, vCianRealEstateController);
        cianFeedControllers.put(CianCategoryCode.TOWNHOUSE_SALE, vCianRealEstateController);
        cianFeedControllers.put(CianCategoryCode.LAND_SALE, vCianRealEstateController);
        cianFeedControllers.put(CianCategoryCode.HOUSE_SHARE_SALE, cianShareController);
        return cianFeedControllers;
    }

}
