package com.baltinfo.radius.bankruptcy.export;

import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.controller.ObjPropertyController;
import com.baltinfo.radius.db.controller.PropertyGroupController;
import com.baltinfo.radius.db.controller.PropertyValueController;
import com.baltinfo.radius.db.model.AllowObjProp;
import com.baltinfo.radius.db.model.ObjProperty;
import com.baltinfo.radius.db.model.ObjSaleCategory;
import com.baltinfo.radius.db.model.PropertyGroup;
import com.baltinfo.radius.db.model.PropertyValue;
import com.baltinfo.radius.db.pojo.AdditionalProperties;
import com.baltinfo.radius.db.pojo.Group;
import com.baltinfo.radius.db.pojo.Value;
import com.baltinfo.radius.utils.Result;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 14.11.2020
 */
@Service
public class AdditionalPropertiesService {

    private static final Logger logger = LoggerFactory.getLogger(AdditionalPropertiesService.class);

    private final PropertyValueController propertyValueController;
    private final ObjPropertyController objPropertyController;
    private final PropertyGroupController propertyGroupController;
    private final LotController lotController;

    public AdditionalPropertiesService(PropertyValueController propertyValueController,
                                       ObjPropertyController objPropertyController,
                                       PropertyGroupController propertyGroupController,
                                       LotController lotController) {
        this.propertyValueController = Objects.requireNonNull(propertyValueController, "Can't get propertyValueController");
        this.objPropertyController = Objects.requireNonNull(objPropertyController, "Can't get objPropertyController");
        this.propertyGroupController = Objects.requireNonNull(propertyGroupController, "Can't get propertyGroupController");
        this.lotController = Objects.requireNonNull(lotController, "Can't get lotController");
    }

    public Optional<String> readContentByInputStream(InputStream is) {
        try {
            String res = IOUtils.toString(is, StandardCharsets.UTF_8);
            return Optional.of(res);
        } catch (Exception e) {
            logger.error("Can't read content from inputStream", e);
            return Optional.empty();
        }
    }

    public Optional<String> getAdditionalPropertiesJsonByObjUnid(Long objUnid) {
        Optional<AdditionalProperties> additionalPropertiesOptional = fillPojoForExportByObjUnid(objUnid);
        return additionalPropertiesOptional.flatMap(this::serializeJson);
    }

    public Result<Void, String> updateAdditionalPropertiesByInputStream(InputStream is, Long objUnid) {
        Optional<String> result = readContentByInputStream(is);
        if (result.isPresent()) {
            Optional<AdditionalProperties> additionalPropertiesOptional = deserializeJson(result.get());
            additionalPropertiesOptional.ifPresent(additionalProperties -> updateAdditionalProperties(additionalProperties, objUnid));
            return Result.ok();
        } else {
            logger.error("Can't updateAdditionalPropertiesByInputStream");
            return Result.error("Can't updateAdditionalPropertiesByInputStream");
        }
    }

    public void updateAdditionalPropertiesFromJson(String json, Long objUnid) {
        Optional<AdditionalProperties> additionalPropertiesOptional = deserializeJson(json);
        additionalPropertiesOptional.ifPresent(additionalProperties -> updateAdditionalProperties(additionalProperties, objUnid));
    }

    public Optional<AdditionalProperties> deserializeJson(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            mapper.setVisibility(PropertyAccessor.CREATOR, JsonAutoDetect.Visibility.ANY);
            AdditionalProperties deserializeResult = mapper.readValue(json, new TypeReference<AdditionalProperties>() {
            });
            return deserializeResult == null
                    ? Optional.empty()
                    : Optional.of(deserializeResult);
        } catch (Exception ex) {
            logger.error("Can't deserialize json", ex);
            return Optional.empty();
        }
    }

    public Optional<String> serializeJson(AdditionalProperties additionalProperties) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            String serializeResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(additionalProperties);
            return serializeResult == null
                    ? Optional.empty()
                    : Optional.of(serializeResult);
        } catch (Exception ex) {
            logger.error("Can't serialize json", ex);
            return Optional.empty();
        }
    }

    private void updateAdditionalProperties(AdditionalProperties additionalProperties, Long objUnid) {
        try {
            for (Group group : additionalProperties.getGroups()) {
                for (Value value : group.getValues()) {
                    if (value.getValue() == null || value.getValue().isEmpty()) {
                        continue;
                    }
                    PropertyValue pv = value.getPropertyCode() == null
                            ? null
                            : propertyValueController.getPropertyValueByAopCodeAndObjUnid(value.getPropertyCode().trim(), objUnid);
                    if (pv == null) {
                        pv = convertPojoToPropertyValue(value, group.getId(), objUnid);
                        if (pv == null) {
                            continue;
                        }
                        propertyValueController.createPropertyValue(pv);
                    } else {
                        if (valueWasChanged(value.getValue(), pv.getPvValue())) {
                            pv.setPvValue(value.getValue().trim());
                            propertyValueController.updatePropertyValue(pv);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("Can't update additional properties by deserialized json", ex);
        }
    }

    private boolean valueWasChanged(String newValue, String currentValue) {
        return !(newValue == null && currentValue == null) && (currentValue == null || !currentValue.equals(newValue));
    }

    private PropertyValue convertPojoToPropertyValue(Value value, Long pgUnid, Long objUnid) {
        PropertyValue pv = new PropertyValue();
        ObjProperty op = objPropertyController.getObjPropertyByPgUnidAndAopCode(pgUnid, value.getPropertyCode());
        if (op == null) {
            return null;
        }
        pv.setPvValue(value.getValue().trim());
        pv.setObjUnid(objUnid);
        pv.setOpUnid(op);
        return pv;
    }

    private Optional<AdditionalProperties> fillPojoForExportByObjUnid(Long objUnid) {
        try {
            ObjSaleCategory objSaleCategory = lotController.getObjSaleCategoryByObjUnid(objUnid);
            if (objSaleCategory == null) {
                logger.warn("Can't get objSaleCategory by objUnid = {}", objUnid);
                return Optional.empty();
            }
            Long scUnid = objSaleCategory.getScUnid() == null
                    ? null
                    : objSaleCategory.getScUnid().getScUnid();
            if (scUnid == null) {
                logger.warn("SaleCategory for objUnid = {} is null", objUnid);
                return Optional.empty();
            }
            List<PropertyValue> allPropertyValues = propertyValueController.getPropertyValuesByObject(objUnid);
            Boolean haveValues = allPropertyValues.stream()
                    .anyMatch(t -> StringUtils.hasText(t.getPvValue()));
            if (!haveValues) {
                return Optional.empty();
            }
            List<PropertyGroup> propertyGroups = propertyGroupController.getPropertyGroupsBySaleCategory(scUnid);
            List<Group> pojoGroups = new ArrayList<>();
            for (PropertyGroup group : propertyGroups) {
                List<ObjProperty> objProperties = objPropertyController.getObjPropertyListForExportByPgUnid(group.getPgUnid());
                List<Value> pojoValue = objProperties.stream()
                        .map(objProperty -> {
                            PropertyValue value = allPropertyValues.stream()
                                    .filter(v -> v.getOpUnid().getOpUnid().equals(objProperty.getOpUnid()))
                                    .findFirst()
                                    .orElse(null);
                            AllowObjProp allowObjProp = objProperty.getAopUnid();
                            return Value.builder().withPropertyCode(allowObjProp.getAopCode())
                                    .withPropertyName(allowObjProp.getAopName())
                                    .withParentValueCode((allowObjProp.getAopParentUnid() == null || value == null || value.getApvUnid() == null)
                                            ? null
                                            : getParentCode(objProperties, allPropertyValues, allowObjProp.getAopParentUnid()))
                                    .withValueCode(value == null || value.getApvUnid() == null
                                            ? null
                                            : value.getApvUnid().getApvCode())
                                    .withValue(value == null
                                            ? null
                                            : value.getPvValue())
                                    .build();
                        })
                        .filter(v -> v.getValue() != null && !v.getValue().isEmpty())
                        .collect(Collectors.toList());
                if (!pojoValue.isEmpty()) {
                    pojoGroups.add(Group.builder()
                            .withId(group.getPgUnid())
                            .withName(group.getPgName())
                            .withValues(pojoValue)
                            .build());
                }
            }
            return Optional.of(new AdditionalProperties(pojoGroups));
        } catch (Exception ex) {
            logger.error("Can't fill pojo for export by objUnid = {}", objUnid, ex);
            return Optional.empty();
        }
    }

    private String getParentCode(List<ObjProperty> objProperties, List<PropertyValue> propertyValues, Long aopParentUnid) {
        Long opUnid = objProperties.stream()
                .filter(value -> value.getAopUnid() != null
                        && value.getAopUnid().getAopUnid().equals(aopParentUnid))
                .map(ObjProperty::getOpUnid)
                .findFirst()
                .orElse(null);
        return propertyValues.stream()
                .filter(value -> value.getOpUnid() != null && value.getOpUnid().getOpUnid().equals(opUnid))
                .map(pv -> pv.getApvUnid().getApvCode())
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }
}
