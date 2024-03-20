package com.baltinfo.radius.avito.dto.truck;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrei Shymko
 * @since 09.11.2020
 */
public class TruckObjectPropertyDto {
    private final TruckProperty code;
    private final String value;
    private final String jsonText;
    private final List<TruckObjectPropertyDto> childList = new ArrayList<>();

    public TruckObjectPropertyDto(TruckProperty code, String value, String jsonText) {
        this.code = code;
        this.value = value;
        this.jsonText = jsonText;
    }

    public TruckProperty getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public String getJsonText() {
        return jsonText;
    }

    public List<TruckObjectPropertyDto> getChildList() {
        return childList;
    }
}