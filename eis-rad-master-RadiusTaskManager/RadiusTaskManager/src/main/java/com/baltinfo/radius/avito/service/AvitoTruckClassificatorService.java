package com.baltinfo.radius.avito.service;

import com.baltinfo.radius.avito.dto.truck.TruckModificationClassifiers;
import com.baltinfo.radius.avito.dto.truck.TruckObjectPropertyDto;
import com.baltinfo.radius.avito.dto.truck.TruckProperty;
import com.baltinfo.radius.avito.model.truck.*;
import com.baltinfo.radius.db.controller.TruckAllowObjPropController;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrei Shymko
 * @since 10.11.2020
 */
public class AvitoTruckClassificatorService {
    private static final Logger logger = LoggerFactory.getLogger(AvitoTruckClassificatorService.class);

    private final String truckCatalogUrl;
    private final TruckAllowObjPropController truckAllowObjPropController;

    public AvitoTruckClassificatorService(String truckCatalogUrl,
                                          TruckAllowObjPropController truckAllowObjPropController) {
        this.truckCatalogUrl = truckCatalogUrl;
        this.truckAllowObjPropController = truckAllowObjPropController;
    }

    public Result<Void, String> parseTruckCatalog() {
        Optional<MakesType> catalogTypeOptional = extractCatalog();
        if (catalogTypeOptional.isPresent()) {
            TruckModificationClassifiers truckModificationClassifiers = new TruckModificationClassifiers();
            List<TruckObjectPropertyDto> truckObjectPropertyDtoList =
                    parseCatalog(catalogTypeOptional.get(), truckModificationClassifiers);

            Result<Void, String> result = truckAllowObjPropController.fillMakeValues(truckObjectPropertyDtoList);
            if (result.isError()) {
                return result;
            }

            for (TruckProperty property : truckModificationClassifiers.getProperties()) {
                truckAllowObjPropController.fillValues(property, truckModificationClassifiers.getStorage(property));
            }
        }
        return Result.ok();
    }

    public Optional<MakesType> extractCatalog() {
        try {
            JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
            JAXBElement<MakesType> catalogType = (JAXBElement<MakesType>) context.createUnmarshaller()
                    .unmarshal(new URL(truckCatalogUrl));
            return Optional.of(catalogType.getValue());
        } catch (Exception e) {
            logger.error("Can't parse xml from path = {}", truckCatalogUrl, e);
        }
        return Optional.empty();
    }

    private List<TruckObjectPropertyDto> parseCatalog(MakesType catalogType, TruckModificationClassifiers truckModificationClassifiers) {
        List<TruckObjectPropertyDto> objectPropertyDtoList = new ArrayList<>();
        for (MakeType makeType : catalogType.getMake()) {
            TruckObjectPropertyDto make = new TruckObjectPropertyDto(TruckProperty.TRUCK_MAKE, makeType.getName(), null);
            for (Serializable modelTypeSer : makeType.getContent()) {
                fillMake(make, modelTypeSer, truckModificationClassifiers);
            }
            objectPropertyDtoList.add(make);
        }
        return objectPropertyDtoList;
    }

    private void fillMake(TruckObjectPropertyDto make, Serializable modelTypeSer, TruckModificationClassifiers truckModificationClassifiers) {
        if (modelTypeSer instanceof JAXBElement) {
            Object modelType = ((JAXBElement) modelTypeSer).getValue();
            if (modelType instanceof ModelType) {
                TruckObjectPropertyDto model = new TruckObjectPropertyDto(TruckProperty.TRUCK_MODEL, ((ModelType) modelType).getName(), null);
                for (Serializable bodyTypeTypeSer : ((ModelType) modelType).getContent()) {
                    fillModel(model, bodyTypeTypeSer, truckModificationClassifiers);
                }
                make.getChildList().add(model);
            }
        }
    }

    private void fillModel(TruckObjectPropertyDto model, Serializable bodyTypeTypeSer, TruckModificationClassifiers truckModificationClassifiers) {
        if (bodyTypeTypeSer instanceof JAXBElement) {
            Object bodyTypeType = ((JAXBElement) bodyTypeTypeSer).getValue();
            if (bodyTypeType instanceof BodyTypeType) {
                TruckObjectPropertyDto bodyType = new TruckObjectPropertyDto(TruckProperty.TRUCK_BODY_TYPE, ((BodyTypeType) bodyTypeType).getValue(), null);
                model.getChildList().add(bodyType);
            }
        }
    }
}