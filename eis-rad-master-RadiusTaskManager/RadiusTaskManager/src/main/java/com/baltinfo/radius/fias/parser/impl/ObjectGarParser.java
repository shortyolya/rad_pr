package com.baltinfo.radius.fias.parser.impl;

import com.baltinfo.radius.fias.model.ObjectGar;
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

public class ObjectGarParser extends FiasAbstractParser {

    @Override
    public void parse(EntityManager entityManager, String path, BiConsumer<EntityManager, List<?>> consumer) throws IOException, XMLStreamException {

        ObjectGar objectGar = null;
        List<ObjectGar> objectGarList = new ArrayList<>();

        InputStream inputStream = Files.newInputStream(Paths.get(path));
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {

                case START_ELEMENT:
                    if (OBJECT.equals(reader.getLocalName())) {
                        objectGar = new ObjectGar();

                        String id = getValue(reader, ID);
                        if (isNotBlank(id)) {
                            objectGar.setId(Long.valueOf(id));
                        }

                        String objectId = getValue(reader, OBJECT_ID);
                        if (isNotBlank(objectId)) {
                            objectGar.setObjectId(Long.valueOf(objectId));
                        }

                        String objectGuid = getValue(reader, OBJECT_GUID);
                        objectGar.setObjectGuid(objectGuid);


                        String changeId = getValue(reader, CHANGE_ID);
                        if (isNotBlank(changeId)) {
                            objectGar.setChangeId(Long.valueOf(changeId));
                        }

                        String name = getValue(reader, NAME);
                        objectGar.setName(name);

                        String typeName = getValue(reader, TYPE_NAME);
                        objectGar.setTypeName(typeName);

                        String level = getValue(reader, LEVEL);
                        objectGar.setLevel(level);

                        String operTypeId = getValue(reader, OPER_TYPE_ID);
                        if (isNotBlank(operTypeId)) {
                            objectGar.setOperTypeId(Integer.valueOf(operTypeId));
                        }

                        String prevId = getValue(reader, PREV_ID);
                        if (isNotBlank(prevId)) {
                            objectGar.setPrevId(Long.valueOf(prevId));
                        }

                        String nextId = getValue(reader, NEXT_ID);
                        if (isNotBlank(nextId)) {
                            objectGar.setNextId(Long.valueOf(nextId));
                        }

                        String updateDate = getValue(reader, UPDATE_DATE);
                        if (isNotBlank(updateDate)) {
                            objectGar.setUpdateDate(Date.valueOf(updateDate));
                        }

                        String startDate = getValue(reader, START_DATE);
                        if (isNotBlank(startDate)) {
                            objectGar.setStartDate(Date.valueOf(startDate));
                        }

                        String endDate = getValue(reader, END_DATE);
                        if (isNotBlank(endDate)) {
                            objectGar.setEndDate(Date.valueOf(endDate));
                        }

                        String isActual = getValue(reader, IS_ACTUAL);
                        if (isNotBlank(isActual)) {
                            objectGar.setIsActual(Integer.valueOf(isActual));
                        }

                        String isActive = getValue(reader, IS_ACTIVE);
                        if (isNotBlank(isActive)) {
                            objectGar.setIsActive(Integer.valueOf(isActive));
                        }
                    }
                    break;

                case END_ELEMENT:

                    if (OBJECT.equals(reader.getLocalName()) && objectGar.getId() != null) {
                        objectGarList.add(objectGar);
                    }

                    if (objectGarList.size() == BATCH_SIZE) {
                        consumer.accept(entityManager, objectGarList);
                        objectGarList.clear();
                    }
                    break;
            }
        }
        consumer.accept(entityManager, objectGarList);
        closeReader(reader);
        closeStream(inputStream);
    }
}