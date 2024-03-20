package com.baltinfo.radius.avito.service;

import com.baltinfo.radius.avito.dto.AutoProperty;
import com.baltinfo.radius.avito.dto.ModificationClassifiers;
import com.baltinfo.radius.avito.dto.ModificationList;
import com.baltinfo.radius.avito.dto.ModificationValue;
import com.baltinfo.radius.avito.dto.ObjectPropertyDto;
import com.baltinfo.radius.avito.model.BodyTypeType;
import com.baltinfo.radius.avito.model.CatalogType;
import com.baltinfo.radius.avito.model.DoorsType;
import com.baltinfo.radius.avito.model.DriveTypeType;
import com.baltinfo.radius.avito.model.EngineSizeType;
import com.baltinfo.radius.avito.model.FuelTypeType;
import com.baltinfo.radius.avito.model.GenerationType;
import com.baltinfo.radius.avito.model.MakeType;
import com.baltinfo.radius.avito.model.ModelType;
import com.baltinfo.radius.avito.model.ModificationType;
import com.baltinfo.radius.avito.model.PowerType;
import com.baltinfo.radius.avito.model.TransmissionType;
import com.baltinfo.radius.avito.model.YearFromType;
import com.baltinfo.radius.avito.model.YearToType;
import com.baltinfo.radius.db.controller.AllowObjPropController;
import com.baltinfo.radius.utils.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author Igor Lapenok
 * @since 10.09.2020
 */
public class AvitoClassificatorService {
    private static final Logger logger = LoggerFactory.getLogger(AvitoClassificatorService.class);

    private final String autoCatalogUrl;
    private final AllowObjPropController allowObjPropController;

    public AvitoClassificatorService(String autoCatalogUrl,
                                     AllowObjPropController allowObjPropController) {
        this.autoCatalogUrl = autoCatalogUrl;
        this.allowObjPropController = allowObjPropController;
    }

    public Result<Void, String> parseAutoCatalog() {
        Optional<CatalogType> catalogTypeOptional = extractCatalog();
        if (catalogTypeOptional.isPresent()) {
            ModificationClassifiers modificationClassifiers = new ModificationClassifiers();
            List<ObjectPropertyDto> objectPropertyDtoList =
                    parseCatalog(catalogTypeOptional.get(), modificationClassifiers);

            Result<Void, String> result = allowObjPropController.fillMakeValues(objectPropertyDtoList);
            if (result.isError()) {
                return result;
            }

            for(AutoProperty property : modificationClassifiers.getProperties()) {
                allowObjPropController.fillValues(property, modificationClassifiers.getStorage(property));
            }
        }
        return Result.ok();
    }

    private Optional<CatalogType> extractCatalog() {
        try {
            JAXBContext context = JAXBContext.newInstance(CatalogType.class);
            JAXBElement<CatalogType> catalogType = (JAXBElement<CatalogType>) context.createUnmarshaller()
                    .unmarshal(new URL(autoCatalogUrl));
            return Optional.of(catalogType.getValue());
        } catch (Exception e) {
            logger.error("Can't parse xml from path = {}", autoCatalogUrl, e);
        }
        return Optional.empty();
    }

    private List<ObjectPropertyDto> parseCatalog(CatalogType catalogType, ModificationClassifiers modificationClassifiers) {
        List<ObjectPropertyDto> objectPropertyDtoList = new ArrayList<>();
        for (MakeType makeType : catalogType.getMake()) {
            ObjectPropertyDto make = new ObjectPropertyDto(AutoProperty.MAKE, makeType.getId(), makeType.getName(), null);
            for (Serializable modelTypeSer : makeType.getContent()) {
                fillMake(make, modelTypeSer, modificationClassifiers);
            }
            objectPropertyDtoList.add(make);
        }
        return objectPropertyDtoList;
    }

    private void fillMake(ObjectPropertyDto make, Serializable modelTypeSer, ModificationClassifiers modificationClassifiers) {
        if (modelTypeSer instanceof JAXBElement) {
            Object modelType = ((JAXBElement) modelTypeSer).getValue();
            if (modelType instanceof ModelType) {
                ObjectPropertyDto model = new ObjectPropertyDto(AutoProperty.MODEL, ((ModelType) modelType).getId(), ((ModelType) modelType).getName(), null);
                for (Serializable generationTypeSer : ((ModelType) modelType).getContent()) {
                    fillModel(model, generationTypeSer, modificationClassifiers);
                }
                make.getChildList().add(model);
            }
        }
    }

    private void fillModel(ObjectPropertyDto model, Serializable generationTypeSer, ModificationClassifiers modificationClassifiers) {
        if (generationTypeSer instanceof JAXBElement) {
            Object generationType = ((JAXBElement) generationTypeSer).getValue();
            if (generationType instanceof GenerationType) {
                ObjectPropertyDto generation = new ObjectPropertyDto(AutoProperty.GENERATION, ((GenerationType) generationType).getId(), ((GenerationType) generationType).getName(), null);
                for (Serializable modificationTypeSer : ((GenerationType) generationType).getContent()) {
                    fillGeneration(generation, modificationTypeSer, modificationClassifiers);
                }
                model.getChildList().add(generation);
            }
        }
    }

    private void fillGeneration(ObjectPropertyDto generation, Serializable modificationTypeSer, ModificationClassifiers modificationClassifiers) {
        if (modificationTypeSer instanceof JAXBElement) {
            Object modificationType = ((JAXBElement) modificationTypeSer).getValue();
            if (modificationType instanceof ModificationType) {
                ModificationList modificationList = new ModificationList(new ArrayList<>());
                for (Serializable optionsSer : ((ModificationType) modificationType).getContent()) {
                    fillModification(modificationList, optionsSer, modificationClassifiers);
                }
                ObjectPropertyDto modification = new ObjectPropertyDto(AutoProperty.MODIFICATION,
                        ((ModificationType) modificationType).getId(),
                        ((ModificationType) modificationType).getName(),
                        modificationList.toJson());
                generation.getChildList().add(modification);
            }
        }
    }

    private void fillModification(ModificationList modificationList, Serializable optionsSer, ModificationClassifiers modificationClassifiers) {
        if (optionsSer instanceof JAXBElement) {
            Object options = ((JAXBElement) optionsSer).getValue();
            if (options instanceof YearToType) {
                ObjectPropertyDto objectPropertyDto = new ObjectPropertyDto(AutoProperty.YEAR_TO,
                        ((YearToType) options).getId(), ((YearToType) options).getValue(), null);
                modificationList.getValues().add(new ModificationValue(objectPropertyDto.getCode().getType(),
                        objectPropertyDto.getId(), objectPropertyDto.getValue()));
                // modificationClassifiers.addValue(objectPropertyDto);
            }
            if (options instanceof PowerType) {
                ObjectPropertyDto objectPropertyDto = new ObjectPropertyDto(AutoProperty.POWER,
                        ((PowerType) options).getId(), ((PowerType) options).getValue(), null);
                modificationList.getValues().add(new ModificationValue(objectPropertyDto.getCode().getType(),
                        objectPropertyDto.getValue(), objectPropertyDto.getValue()));
                modificationClassifiers.addValue(objectPropertyDto);
            }
            if (options instanceof TransmissionType) {
                ObjectPropertyDto objectPropertyDto = new ObjectPropertyDto(AutoProperty.TRANSMISSION,
                        ((TransmissionType) options).getId(), ((TransmissionType) options).getValue(), null);
                modificationList.getValues().add(new ModificationValue(objectPropertyDto.getCode().getType(),
                        objectPropertyDto.getValue(), objectPropertyDto.getValue()));
                modificationClassifiers.addValue(objectPropertyDto);
            }
            if (options instanceof YearFromType) {
                ObjectPropertyDto objectPropertyDto = new ObjectPropertyDto(AutoProperty.YEAR_FROM,
                        ((YearFromType) options).getId(), ((YearFromType) options).getValue(), null);
                modificationList.getValues().add(new ModificationValue(objectPropertyDto.getCode().getType(),
                        objectPropertyDto.getId(), objectPropertyDto.getValue()));
                // modificationClassifiers.addValue(objectPropertyDto);
            }
            if (options instanceof DoorsType) {
                ObjectPropertyDto objectPropertyDto = new ObjectPropertyDto(AutoProperty.DOORS,
                        ((DoorsType) options).getId(), ((DoorsType) options).getValue(), null);
                modificationList.getValues().add(new ModificationValue(objectPropertyDto.getCode().getType(),
                        objectPropertyDto.getValue(), objectPropertyDto.getValue()));
                //        objectPropertyDto.getId(), objectPropertyDto.getValue()));
                modificationClassifiers.addValue(objectPropertyDto);
            }
            if (options instanceof DriveTypeType) {
                ObjectPropertyDto objectPropertyDto = new ObjectPropertyDto(AutoProperty.DRIVE_TYPE,
                        ((DriveTypeType) options).getId(), ((DriveTypeType) options).getValue(), null);
                modificationList.getValues().add(new ModificationValue(objectPropertyDto.getCode().getType(),
                        objectPropertyDto.getValue(), objectPropertyDto.getValue()));
                modificationClassifiers.addValue(objectPropertyDto);
            }
            if (options instanceof EngineSizeType) {
                ObjectPropertyDto objectPropertyDto = new ObjectPropertyDto(AutoProperty.ENGINE_SIZE,
                        ((EngineSizeType) options).getId(), ((EngineSizeType) options).getValue(), null);
                modificationList.getValues().add(new ModificationValue(objectPropertyDto.getCode().getType(),
                        objectPropertyDto.getValue(), objectPropertyDto.getValue()));
                modificationClassifiers.addValue(objectPropertyDto);
            }
            if (options instanceof BodyTypeType) {
                ObjectPropertyDto objectPropertyDto = new ObjectPropertyDto(AutoProperty.BODY_TYPE,
                        ((BodyTypeType) options).getId(), ((BodyTypeType) options).getValue(), null);
                modificationList.getValues().add(new ModificationValue(objectPropertyDto.getCode().getType(),
                        objectPropertyDto.getValue(), objectPropertyDto.getValue()));
                modificationClassifiers.addValue(objectPropertyDto);
            }
            if (options instanceof FuelTypeType) {
                ObjectPropertyDto objectPropertyDto = new ObjectPropertyDto(AutoProperty.FUEL_TYPE,
                        ((FuelTypeType) options).getId(), ((FuelTypeType) options).getValue(), null);
                modificationList.getValues().add(new ModificationValue(objectPropertyDto.getCode().getType(),
                        objectPropertyDto.getValue(), objectPropertyDto.getValue()));
                modificationClassifiers.addValue(objectPropertyDto);
            }
        }
    }
}
