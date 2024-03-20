package com.baltinfo.radius.avito.dto.truck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author Andrei Shymko
 * @since 09.11.2020
 */
public class TruckModificationClassifiers {

    private final HashMap<TruckProperty, List<TruckObjectPropertyDto>> storage = new HashMap<>();

    public boolean addValue(TruckObjectPropertyDto truckObjectPropertyDto) {
        if (truckObjectPropertyDto == null) {
            return false;
        }
        if (!storage.containsKey(truckObjectPropertyDto.getCode())) {
            storage.put(truckObjectPropertyDto.getCode(), new ArrayList<>());
        }
        storage.get(truckObjectPropertyDto.getCode()).add(truckObjectPropertyDto);
        return true;
    }

    public List<TruckObjectPropertyDto> getStorage(TruckProperty truckProperty) {
        return storage.get(truckProperty);
    }

    public Set<TruckProperty> getProperties() {
        return storage.keySet();
    }

    public HashMap<TruckProperty, List<TruckObjectPropertyDto>> getStorage() {
        return storage;
    }
}