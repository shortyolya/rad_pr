package com.baltinfo.radius.fias.parser.impl;

import com.baltinfo.radius.fias.model.ObjectLevel;
import com.baltinfo.radius.fias.parser.FiasAbstractParser;

import javax.persistence.EntityManager;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import static com.baltinfo.radius.fias.utils.FiasConstants.*;
import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

public class ObjectLevelParser extends FiasAbstractParser {

    @Override
    public void parse(EntityManager entityManager, String path, BiConsumer<EntityManager, List<?>> consumer) throws IOException, XMLStreamException {

        ObjectLevel objectLevel = null;
        List<ObjectLevel> objectLevelList = new ArrayList<>();

        InputStream inputStream = Files.newInputStream(Paths.get(path));
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {

                case START_ELEMENT:
                    if (OBJECT_LEVEL.equals(reader.getLocalName())) {
                        objectLevel = new ObjectLevel();

                        String id = getValue(reader, LEVEL);
                        if (isNotBlank(id)) {
                            objectLevel.setId(Integer.valueOf(id));
                        }

                        String name = getValue(reader, NAME);
                        objectLevel.setName(name);

                        String shortName = getValue(reader, SHORT_NAME);
                        objectLevel.setShortName(shortName);

                        String updateDate = getValue(reader, UPDATE_DATE);
                        if (isNotBlank(updateDate)) {
                            objectLevel.setUpdateDate(Date.valueOf(updateDate));
                        }

                        String startDate = getValue(reader, START_DATE);
                        if (isNotBlank(startDate)) {
                            objectLevel.setStartDate(Date.valueOf(startDate));
                        }

                        String endDate = getValue(reader, END_DATE);
                        if (isNotBlank(endDate)) {
                            objectLevel.setEndDate(Date.valueOf(endDate));
                        }

                        String isActive = getValue(reader, IS_ACTIVE);
                        if (isNotBlank(isActive)) {
                            objectLevel.setIsActive(Boolean.valueOf(isActive));
                        }
                    }
                    break;

                case END_ELEMENT:

                    if (OBJECT_LEVEL.equals(reader.getLocalName()) && objectLevel.getId() != null) {
                        objectLevelList.add(objectLevel);
                    }

                    if (objectLevelList.size() == BATCH_SIZE) {
                        consumer.accept(entityManager, objectLevelList);
                        objectLevelList.clear();
                    }
                    break;
            }
        }
        consumer.accept(entityManager, objectLevelList);
        closeReader(reader);
        closeStream(inputStream);
    }
}