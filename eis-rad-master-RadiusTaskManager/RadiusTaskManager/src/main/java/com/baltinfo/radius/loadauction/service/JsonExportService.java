package com.baltinfo.radius.loadauction.service;

import com.baltinfo.radius.loadauction.model.Publication;
import com.baltinfo.radius.utils.Result;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author Suvorina Aleksandra
 * @since 12.10.2021
 */
public class JsonExportService {
    private static final Logger logger = LoggerFactory.getLogger(JsonExportService.class);

    /**
     * Производит загрузку информации из строки {@param source}
     * в объект {@link Publication}
     *
     * @param source json-файл записанный в строку типа String
     * @return объект {@link Publication} в случае успешной загрузки или строку, содержащую информацию об ошибке
     */
    public Result<Publication, String> loadJsonFromString(String source, String filename) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            source = source.replace("\uFEFF", "");
            Publication deserializedData = mapper.readValue(source, Publication.class);
            if (deserializedData != null) {
                return Result.ok(deserializedData);
            } else {
                logger.error("Can't deserialize json-file = {}, deserialization result is null", filename);
                return Result.error("Can't deserialize json-file, deserialization result is null");
            }
        } catch (Exception e) {
            logger.error("Can't deserialize json-file = {}", filename, e);
            return Result.error("Can't deserialize json-file. Error: " + e);
        }
    }


}
