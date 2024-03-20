package com.baltinfo.radius.avito.service;

import com.baltinfo.radius.avito.model.development.City;
import com.baltinfo.radius.avito.model.development.Development;
import com.baltinfo.radius.avito.model.development.Housing;
import com.baltinfo.radius.avito.model.development.Object;
import com.baltinfo.radius.avito.model.development.Region;
import com.baltinfo.radius.db.controller.AllowObjPropController;
import com.baltinfo.radius.db.controller.AllowPropValueController;
import com.baltinfo.radius.db.model.AllowObjProp;
import com.baltinfo.radius.db.model.AllowPropVal;
import com.baltinfo.radius.utils.Result;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AvitoDevelopmentClassificatorService {

    private static final Logger logger = LoggerFactory.getLogger(AvitoDevelopmentClassificatorService.class);

    private final String REGION_AOP_CODE = "DevelopmentRegion";
    private final String CITY_AOP_CODE = "DevelopmentCity";
    private final String OBJECT_AOP_CODE = "DevelopmentObject";
    private final XmlMapper xmlMapper = new XmlMapper();

    private final AllowObjPropController allowObjPropController;
    private final AllowPropValueController allowPropValueController;
    private final String avitoDevelopmentUrl;

    public AvitoDevelopmentClassificatorService(AllowObjPropController allowObjPropController,
                                                AllowPropValueController allowPropValueController,
                                                String avitoDevelopmentUrl) {
        this.allowObjPropController = allowObjPropController;
        this.allowPropValueController = allowPropValueController;
        this.avitoDevelopmentUrl = avitoDevelopmentUrl;
    }

    public void parseDevelopmentCatalog() {
        Result<Development, Void> parsingResult = getParsedCatalog();
        if (parsingResult.isError()) {
            return;
        }
        Result<AllowObjProp, Void> regionResult = allowObjPropController.findAllowObjProp(REGION_AOP_CODE);
        if (regionResult.isError()) {
            return;
        }
        Result<AllowObjProp, Void> cityResult = allowObjPropController.findAllowObjProp(CITY_AOP_CODE);
        if (cityResult.isError()) {
            return;
        }
        Result<AllowObjProp, Void> objectResult = allowObjPropController.findAllowObjProp(OBJECT_AOP_CODE);
        if (objectResult.isError()) {
            return;
        }
        Development development = parsingResult.getResult();
        AllowObjProp regionAop = regionResult.getResult();
        AllowObjProp cityAop = cityResult.getResult();
        AllowObjProp objectAop = objectResult.getResult();
        List<AllowPropVal> regionApvList = new ArrayList<>();
        List<AllowPropVal> cityApvList = new ArrayList<>();
        List<AllowPropVal> objectApvList = new ArrayList<>();
        Result<List<AllowPropVal>, Void> regionApvResult =
                allowPropValueController.findAllowPropVal(regionAop.getAopUnid());
        if (regionApvResult.isSuccess()) {
            regionApvList = regionApvResult.getResult();
        }
        Result<List<AllowPropVal>, Void> cityApvResult =
                allowPropValueController.findAllowPropVal(cityAop.getAopUnid());
        if (cityApvResult.isSuccess()) {
            cityApvList = cityApvResult.getResult();
        }
        Result<List<AllowPropVal>, Void> objectApvResult =
                allowPropValueController.findAllowPropVal(objectAop.getAopUnid());
        if (objectApvResult.isSuccess()) {
            objectApvList = objectApvResult.getResult();
        }
        fillRegions(regionApvList,
                cityApvList,
                objectApvList,
                regionAop.getAopUnid(),
                cityAop.getAopUnid(),
                objectAop.getAopUnid(),
                development.getRegionList());

    }

    private void fillRegions(List<AllowPropVal> regionPropVal,
                             List<AllowPropVal> cityPropVal,
                             List<AllowPropVal> objectPropVal,
                             Long regionAopUnid,
                             Long cityAopUnid,
                             Long objectAopUnid,
                             List<Region> xmlRegionList) {
        for (Region reg : xmlRegionList) {
            Optional<AllowPropVal> foundApv = regionPropVal.stream()
                    .filter(t -> t.getApvValue().equals(reg.getName())).findFirst();
            if (!foundApv.isPresent()) {
                allowPropValueController.saveRegion(reg, regionAopUnid, cityAopUnid, objectAopUnid);
            } else {
                regionPropVal.remove(foundApv.get());
                Long regionApvUnid = foundApv.get().getApvUnid();
                List<AllowPropVal> citiesFromDb = cityPropVal.stream()
                        .filter(t -> t.getApvParentUnid().equals(regionApvUnid))
                        .collect(Collectors.toList());
                fillCities(citiesFromDb, objectPropVal, reg.getCityList(), cityAopUnid, objectAopUnid, regionApvUnid);
            }
        }
        allowPropValueController.deactualizeApvList(regionPropVal);
    }

    private void fillCities(List<AllowPropVal> citiesFromDb,
                            List<AllowPropVal> objectPropVal,
                            List<City> citiesFromXml,
                            Long cityAopUnid,
                            Long objectAopUnid,
                            Long regionApvUnid) {
        for (City city : citiesFromXml) {
            Optional<AllowPropVal> foundApv = citiesFromDb.stream()
                    .filter(t -> t.getApvValue().equals(city.getName())).findFirst();
            if (!foundApv.isPresent()) {
                allowPropValueController.saveCity(city, cityAopUnid, objectAopUnid, regionApvUnid);
            } else {
                citiesFromDb.remove(foundApv.get());
                Long cityApvUnid = foundApv.get().getApvUnid();
                List<AllowPropVal> housesFromDb = objectPropVal.stream()
                        .filter(t -> t.getApvParentUnid().equals(cityApvUnid))
                        .collect(Collectors.toList());
                fillHouses(housesFromDb, city.getObjectList(), objectAopUnid, cityApvUnid);
            }
        }
        allowPropValueController.deactualizeApvList(citiesFromDb);
    }

    private void fillHouses(List<AllowPropVal> housesFromDb,
                            List<Object> objectsFromXml,
                            Long objectAopUnid,
                            Long cityApvUnid) {
        List<Housing> houseList = new ArrayList<>();
        objectsFromXml.forEach(obj -> {
            if (obj.getHouseList() != null && !obj.getHouseList().isEmpty()) {
                houseList.addAll(obj.getHouseList());
            } else {
                houseList.add(new Housing(obj.getId(), obj.getName(), obj.getAddress()));
            }
        });
        for (Housing house : houseList) {
            Optional<AllowPropVal> foundApv = housesFromDb.stream()
                    .filter(t -> t.getApvCode().equals(house.getId().toString())).findFirst();
            if (!foundApv.isPresent()) {
                allowPropValueController.saveHouse(house, objectAopUnid, cityApvUnid);
            } else {
                housesFromDb.remove(foundApv.get());
            }
        }
        allowPropValueController.deactualizeApvList(housesFromDb);
    }

    private Result<Development, Void> getParsedCatalog() {
        try {
            return Result.ok(xmlMapper.readValue(new URL(avitoDevelopmentUrl), Development.class));
        } catch (Exception ex) {
            logger.error("Error in getParsedCatalog method", ex);
            return Result.error();
        }
    }
}
