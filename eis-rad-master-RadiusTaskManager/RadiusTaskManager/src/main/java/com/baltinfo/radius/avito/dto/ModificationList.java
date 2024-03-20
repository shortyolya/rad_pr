package com.baltinfo.radius.avito.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Igor Lapenok
 * @since 14.09.2020
 */
public class ModificationList {
    private static final Logger logger = LoggerFactory.getLogger(ModificationList.class);
    private final List<ModificationValue> values;

    @JsonCreator
    public ModificationList(@JsonProperty("values") List<ModificationValue> values) {
        this.values = values;
    }

    @JsonProperty("values")
    public List<ModificationValue> getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "ModificationList{" +
                "values=" + values +
                '}';
    }

    public String toJson() {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            logger.error("Can't create JSON", e);
            return null;
        }
    }
}
