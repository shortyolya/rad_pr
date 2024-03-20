package com.baltinfo.radius.fias.parser.impl;

import com.baltinfo.radius.fias.model.MinHierarchy;
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

public class MinHierarchyParser extends FiasAbstractParser {

    @Override
    public void parse(EntityManager entityManager, String path, BiConsumer<EntityManager, List<?>> consumer) throws IOException, XMLStreamException {

        MinHierarchy minHierarchy = null;
        List<MinHierarchy> minHierarchyList = new ArrayList<>();

        InputStream inputStream = Files.newInputStream(Paths.get(path));
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {

                case START_ELEMENT:
                    if (ITEM.equals(reader.getLocalName())) {
                        minHierarchy = new MinHierarchy();

                        String id = getValue(reader, ID);
                        if (isNotBlank(id)) {
                            minHierarchy.setId(Long.valueOf(id));
                        }

                        String objectId = getValue(reader, OBJECT_ID);
                        if (isNotBlank(objectId)) {
                            minHierarchy.setObjectId(Long.valueOf(objectId));
                        }

                        String parentObjId = getValue(reader, PARENT_OBJ_ID);
                        if (isNotBlank(parentObjId)) {
                            minHierarchy.setParentObjId(Long.valueOf(parentObjId));
                        }

                        String changeId = getValue(reader, CHANGE_ID);
                        if (isNotBlank(changeId)) {
                            minHierarchy.setChangeId(Long.valueOf(changeId));
                        }

                        String oktmo = getValue(reader, OKTMO);
                        minHierarchy.setOktmo(oktmo);

                        String prevId = getValue(reader, PREV_ID);
                        if (isNotBlank(prevId)) {
                            minHierarchy.setPrevId(Long.valueOf(prevId));
                        }

                        String nextId = getValue(reader, NEXT_ID);
                        if (isNotBlank(nextId)) {
                            minHierarchy.setNextId(Long.valueOf(nextId));
                        }

                        String updateDate = getValue(reader, UPDATE_DATE);
                        if (isNotBlank(updateDate)) {
                            minHierarchy.setUpdateDate(Date.valueOf(updateDate));
                        }

                        String startDate = getValue(reader, START_DATE);
                        if (isNotBlank(startDate)) {
                            minHierarchy.setStartDate(Date.valueOf(startDate));
                        }

                        String endDate = getValue(reader, END_DATE);
                        if (isNotBlank(endDate)) {
                            minHierarchy.setEndDate(Date.valueOf(endDate));
                        }

                        String isActive = getValue(reader, IS_ACTIVE);
                        if (isNotBlank(isActive)) {
                            minHierarchy.setIsActive(Integer.valueOf(isActive));
                        }
                    }
                    break;

                case END_ELEMENT:

                    if (ITEM.equals(reader.getLocalName()) && minHierarchy.getId() != null) {
                        minHierarchyList.add(minHierarchy);
                    }

                    if (minHierarchyList.size() == BATCH_SIZE) {
                        consumer.accept(entityManager, minHierarchyList);
                        minHierarchyList.clear();
                    }
                    break;
            }
        }
        consumer.accept(entityManager, minHierarchyList);
        closeReader(reader);
        closeStream(inputStream);
    }
}