package com.baltinfo.radius.avito.dto;

import java.util.HashMap;
import java.util.Set;

/**
 * @author Igor Lapenok
 * @since 11.09.2020
 */
public class ModificationClassifiers {
    private final HashMap<AutoProperty, HashMap<String, ObjectPropertyDto>> storage = new HashMap<>();

    public boolean addValue(ObjectPropertyDto objectPropertyDto) {
        if (objectPropertyDto == null) {
            return false;
        }
        if (!storage.containsKey(objectPropertyDto.getCode())) {
            storage.put(objectPropertyDto.getCode(), new HashMap());
        }
        storage.get(objectPropertyDto.getCode())
                .putIfAbsent(objectPropertyDto.getId(), objectPropertyDto);
        return true;
    }

    public HashMap<String, ObjectPropertyDto> getStorage(AutoProperty autoProperty) {
        return storage.get(autoProperty);
    }

    public Set<AutoProperty> getProperties() {
        return storage.keySet();
    }

    public HashMap<AutoProperty, HashMap<String, ObjectPropertyDto>> getStorage() {
        return storage;
    }
}
