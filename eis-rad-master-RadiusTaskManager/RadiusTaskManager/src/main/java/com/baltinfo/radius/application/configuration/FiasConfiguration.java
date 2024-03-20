package com.baltinfo.radius.application.configuration;

import com.baltinfo.radius.fias.dao.*;
import com.baltinfo.radius.fias.job.UpdateFiasJob;
import com.baltinfo.radius.fias.parser.impl.*;
import com.baltinfo.radius.fias.service.FiasServiceProvider;
import com.baltinfo.radius.fias.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Class of configuration for FIAS
 *
 * @author Andrei Shymko
 * @since 16.10.2020
 */

@Configuration
@EnableScheduling
@PropertySource("classpath:application.properties")
public class FiasConfiguration {

    @Bean
    VersionDao versionDao() {
        return new VersionDao();
    }

    @Bean
    AddressObjectTypeDao addressObjectTypeDao() {
        return new AddressObjectTypeDao();
    }

    @Bean
    ApartmentDao apartmentDao() {
        return new ApartmentDao();
    }

    @Bean
    ApartmentTypeDao apartmentTypeDao() {
        return new ApartmentTypeDao();
    }

    @Bean
    CarPlaceDao carPlaceDao() {
        return new CarPlaceDao();
    }

    @Bean
    ChangeHistoryDao changeHistoryDao() {
        return new ChangeHistoryDao();
    }

    @Bean
    HierarchyDao hierarchyDao() {
        return new HierarchyDao();
    }

    @Bean
    HouseDao houseDao() {
        return new HouseDao();
    }

    @Bean
    HouseTypeDao houseTypeDao() {
        return new HouseTypeDao();
    }

    @Bean
    MinHierarchyDao minHierarchyDao() {
        return new MinHierarchyDao();
    }

    @Bean
    NdocKindDao ndocKindDao() {
        return new NdocKindDao();
    }

    @Bean
    NdocTypeDao ndocTypeDao() {
        return new NdocTypeDao();
    }

    @Bean
    NormDocDao normDocDao() {
        return new NormDocDao();
    }

    @Bean
    ObjDivisionDao objDivisionDao() {
        return new ObjDivisionDao();
    }

    @Bean
    ObjectGarDao objectGarDao() {
        return new ObjectGarDao();
    }

    @Bean
    ObjectLevelDao objectLevelDao() {
        return new ObjectLevelDao();
    }

    @Bean
    OperationTypeDao operationTypeDao() {
        return new OperationTypeDao();
    }

    @Bean
    ParamDao paramDao() {
        return new ParamDao();
    }

    @Bean
    ParamTypeDao paramTypeDao() {
        return new ParamTypeDao();
    }

    @Bean
    RoomDao roomDao() {
        return new RoomDao();
    }

    @Bean
    RoomTypeDao roomTypeDao() {
        return new RoomTypeDao();
    }

    @Bean
    SteadDao steadDao() {
        return new SteadDao();
    }

    @Bean
    AddressObjectTypeParser addressObjectTypeParser() {
        return new AddressObjectTypeParser();
    }

    @Bean
    ApartmentParser apartmentParser() {
        return new ApartmentParser();
    }

    @Bean
    ApartmentTypeParser apartmentTypeParser() {
        return new ApartmentTypeParser();
    }

    @Bean
    CarPlaceParser carPlaceParser() {
        return new CarPlaceParser();
    }

    @Bean
    ChangeHistoryParser changeHistoryParser() {
        return new ChangeHistoryParser();
    }

    @Bean
    HierarchyParser hierarchyParser() {
        return new HierarchyParser();
    }

    @Bean
    HouseParser houseParser() {
        return new HouseParser();
    }

    @Bean
    HouseTypeParser houseTypeParser() {
        return new HouseTypeParser();
    }

    @Bean
    MinHierarchyParser minHierarchyParser() {
        return new MinHierarchyParser();
    }

    @Bean
    NdocKindParser ndocKindParser() {
        return new NdocKindParser();
    }

    @Bean
    NdocTypeParser ndocTypeParser() {
        return new NdocTypeParser();
    }

    @Bean
    NormDocParser normDocParser() {
        return new NormDocParser();
    }

    @Bean
    ObjDivisionParser objDivisionParser() {
        return new ObjDivisionParser();
    }

    @Bean
    ObjectGarParser objectGarParser() {
        return new ObjectGarParser();
    }

    @Bean
    ObjectLevelParser objectLevelParser() {
        return new ObjectLevelParser();
    }

    @Bean
    OperationTypeParser operationTypeParser() {
        return new OperationTypeParser();
    }

    @Bean
    ParamParser paramParser() {
        return new ParamParser();
    }

    @Bean
    ParamTypeParser paramTypeParser() {
        return new ParamTypeParser();
    }

    @Bean
    RoomParser roomParser() {
        return new RoomParser();
    }

    @Bean
    RoomTypeParser roomTypeParser() {
        return new RoomTypeParser();
    }

    @Bean
    SteadParser steadParser() {
        return new SteadParser();
    }


    @Bean
    AddressObjectTypeService addressObjectTypeService(AddressObjectTypeDao addressObjectTypeDao, AddressObjectTypeParser addressObjectTypeParser) {
        return new AddressObjectTypeService(addressObjectTypeDao, addressObjectTypeParser);
    }

    @Bean
    ApartmentService apartmentService(ApartmentDao apartmentDao, ApartmentParser apartmentParser) {
        return new ApartmentService(apartmentDao, apartmentParser);
    }

    @Bean
    ApartmentTypeService apartmentTypeService(ApartmentTypeDao apartmentTypeDao, ApartmentTypeParser apartmentTypeParser) {
        return new ApartmentTypeService(apartmentTypeDao, apartmentTypeParser);
    }

    @Bean
    CarPlaceService carPlaceService(CarPlaceDao carPlaceDao, CarPlaceParser carPlaceParser) {
        return new CarPlaceService(carPlaceDao, carPlaceParser);
    }

    @Bean
    ChangeHistoryService changeHistoryService(ChangeHistoryDao changeHistoryDao, ChangeHistoryParser changeHistoryParser) {
        return new ChangeHistoryService(changeHistoryDao, changeHistoryParser);
    }

    @Bean
    HierarchyService hierarchyService(HierarchyDao hierarchyDao, HierarchyParser hierarchyParser) {
        return new HierarchyService(hierarchyDao, hierarchyParser);
    }

    @Bean
    HouseService houseService(HouseDao houseDao, HouseParser houseParser) {
        return new HouseService(houseDao, houseParser);
    }

    @Bean
    HouseTypeService houseTypeService(HouseTypeDao houseTypeDao, HouseTypeParser houseTypeParser) {
        return new HouseTypeService(houseTypeDao, houseTypeParser);
    }

    @Bean
    MinHierarchyService minHierarchyService(MinHierarchyDao minHierarchyDao, MinHierarchyParser minHierarchyParser) {
        return new MinHierarchyService(minHierarchyDao, minHierarchyParser);
    }

    @Bean
    NdocKindService ndocKindService(NdocKindDao ndocKindDao, NdocKindParser ndocKindParser) {
        return new NdocKindService(ndocKindDao, ndocKindParser);
    }

    @Bean
    NdocTypeService ndocTypeService(NdocTypeDao ndocTypeDao, NdocTypeParser ndocTypeParser) {
        return new NdocTypeService(ndocTypeDao, ndocTypeParser);
    }

    @Bean
    NormDocService normDocService(NormDocDao normDocDao, NormDocParser normDocParser) {
        return new NormDocService(normDocDao, normDocParser);
    }

    @Bean
    ObjDivisionService objDivisionService(ObjDivisionDao objDivisionDao, ObjDivisionParser objDivisionParser) {
        return new ObjDivisionService(objDivisionDao, objDivisionParser);
    }

    @Bean
    ObjectGarService objectGarService(ObjectGarDao objectGarDao, ObjectGarParser objectGarParser) {
        return new ObjectGarService(objectGarDao, objectGarParser);
    }

    @Bean
    ObjectLevelService objectLevelService(ObjectLevelDao objectLevelDao, ObjectLevelParser objectLevelParser) {
        return new ObjectLevelService(objectLevelDao, objectLevelParser);
    }

    @Bean
    OperationTypeService operationTypeService(OperationTypeDao operationTypeDao, OperationTypeParser operationTypeParser) {
        return new OperationTypeService(operationTypeDao, operationTypeParser);
    }

    @Bean
    ParamService paramService(ParamDao paramDao, ParamParser paramParser) {
        return new ParamService(paramDao, paramParser);
    }

    @Bean
    ParamTypeService paramTypeService(ParamTypeDao paramTypeDao, ParamTypeParser paramTypeParser) {
        return new ParamTypeService(paramTypeDao, paramTypeParser);
    }

    @Bean
    RoomService roomService(RoomDao roomDao, RoomParser roomParser) {
        return new RoomService(roomDao, roomParser);
    }

    @Bean
    RoomTypeService roomTypeService(RoomTypeDao roomTypeDao, RoomTypeParser roomTypeParser) {
        return new RoomTypeService(roomTypeDao, roomTypeParser);
    }

    @Bean
    SteadService steadService(SteadDao steadDao, SteadParser steadParser) {
        return new SteadService(steadDao, steadParser);
    }

    @Bean
    VersionService versionService(VersionDao versionDao) {
        return new VersionService(versionDao);
    }

    @Bean
    UpdateFiasJob updateFiasJob(FiasServiceProvider fiasServiceProvider, VersionService versionService) {
        return new UpdateFiasJob(fiasServiceProvider, versionService);
    }

    @Bean
    FiasServiceProvider fiasServiceProvider(AddressObjectTypeService addressObjectTypeService,
                                            HierarchyService hierarchyService,
                                            ApartmentService apartmentService,
                                            ApartmentTypeService apartmentTypeService,
                                            CarPlaceService carPlaceService,
                                            ChangeHistoryService changeHistoryService,
                                            HouseTypeService houseTypeService,
                                            HouseService houseService,
                                            MinHierarchyService minHierarchyService,
                                            NormDocService normDocService,
                                            NdocKindService ndocKindService,
                                            NdocTypeService ndocTypeService,
                                            ObjDivisionService objDivisionService,
                                            ObjectLevelService objectLevelService,
                                            OperationTypeService operationTypeService,
                                            ParamService paramService,
                                            ParamTypeService paramTypeService,
                                            ObjectGarService objectGarService,
                                            RoomTypeService roomTypeService,
                                            RoomService roomService,
                                            SteadService steadService) {
        return new FiasServiceProvider(addressObjectTypeService,
                hierarchyService,
                apartmentService,
                apartmentTypeService,
                carPlaceService,
                changeHistoryService,
                houseTypeService,
                houseService,
                minHierarchyService,
                normDocService,
                ndocKindService,
                ndocTypeService,
                objDivisionService,
                objectLevelService,
                operationTypeService,
                paramService,
                paramTypeService,
                objectGarService,
                roomTypeService,
                roomService,
                steadService);
    }
}