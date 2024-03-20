package com.baltinfo.radius.fias.service;

import com.baltinfo.radius.fias.service.impl.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.baltinfo.radius.fias.utils.FiasConstants.*;

/**
 * Service provider that provides the required class of service
 * according to the file name. To exclude a file from processing by the service,
 * need to remove the key-value from <cose>Map<String, FiasCommonService> serviceMap</cose>
 *
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class FiasServiceProvider {

    private final Map<String, FiasCommonService> serviceMap = new HashMap<>();

    public FiasServiceProvider(AddressObjectTypeService addressObjectTypeService,
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

        serviceMap.put(ADDR_OBJ_DIVISION, objDivisionService);
        serviceMap.put(ADDR_OBJ_TYPES, addressObjectTypeService);
        serviceMap.put(ADM_HIERARCHY, hierarchyService);
        serviceMap.put(APARTMENT_TYPES, apartmentTypeService);
        serviceMap.put(APARTMENTS, apartmentService);
        serviceMap.put(CAR_PLACES, carPlaceService);
        serviceMap.put(CHANGE_HISTORY, changeHistoryService);
        serviceMap.put(HOUSE_TYPES, houseTypeService);
        serviceMap.put(HOUSES, houseService);
        serviceMap.put(MUN_HIERARCHY, minHierarchyService);
        serviceMap.put(NORMATIVE_DOCS, normDocService);
        serviceMap.put(NORMATIVE_DOCS_KINDS, ndocKindService);
        serviceMap.put(NORMATIVE_DOCS_TYPES, ndocTypeService);
        serviceMap.put(OBJECT_LEVELS, objectLevelService);
        serviceMap.put(OPERATION_TYPES, operationTypeService);
        serviceMap.put(PARAMS, paramService);
        serviceMap.put(PARAM_TYPES, paramTypeService);
        serviceMap.put(ADDR_OBJ, objectGarService);
        serviceMap.put(ROOM_TYPES, roomTypeService);
        serviceMap.put(ROOMS, roomService);
        serviceMap.put(STEADS, steadService);
    }

    /**
     * Return service for parsing and saving entity to database
     *
     * @param fileName XML file to parse
     * @return service for parsing and saving entity to database
     */
    public FiasCommonService getService(String fileName) {

        FiasCommonService service = null;
        Set<String> serviceKey = serviceMap.keySet();

        for (String serviceName : serviceKey) {
            if (fileName.toUpperCase().contains(serviceName)) {
                service = serviceMap.get(serviceName);
            }
        }
        return service;
    }
}