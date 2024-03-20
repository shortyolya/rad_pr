package com.baltinfo.radius.avito.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Lapenok
 * @since 10.09.2020
 */
public class ObjectPropertyDto {
    private final AutoProperty code;
    private final String id;
    private final String value;
    private final String jsonText;
    private final List<ObjectPropertyDto> childList = new ArrayList<>();

    public ObjectPropertyDto(AutoProperty code, String id, String value, String jsonText) {
        this.code = code;
        this.id = id;
        this.value = value;
        this.jsonText = jsonText;
    }

    public AutoProperty getCode() {
        return code;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getJsonText() {
        return jsonText;
    }

    public List<ObjectPropertyDto> getChildList() {
        return childList;
    }
}
