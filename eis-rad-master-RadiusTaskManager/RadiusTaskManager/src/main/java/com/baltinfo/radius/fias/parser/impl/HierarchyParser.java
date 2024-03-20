package com.baltinfo.radius.fias.parser.impl;

import com.baltinfo.radius.fias.model.Hierarchy;
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

public class HierarchyParser extends FiasAbstractParser {

    @Override
    public void parse(EntityManager entityManager, String path, BiConsumer<EntityManager, List<?>> consumer) throws IOException, XMLStreamException {

        Hierarchy hierarchy = null;
        List<Hierarchy> hierarchyList = new ArrayList<>();

        InputStream inputStream = Files.newInputStream(Paths.get(path));
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {

                case START_ELEMENT:
                    if (ITEM.equals(reader.getLocalName())) {
                        hierarchy = new Hierarchy();

                        String id = getValue(reader, ID);
                        if (isNotBlank(id)) {
                            hierarchy.setId(Long.valueOf(id));
                        }

                        String objectId = getValue(reader, OBJECT_ID);
                        if (isNotBlank(objectId)) {
                            hierarchy.setObjectId(Long.valueOf(objectId));
                        }

                        String parentObjId = getValue(reader, PARENT_OBJ_ID);
                        if (isNotBlank(parentObjId)) {
                            hierarchy.setParentObjId(Long.valueOf(parentObjId));
                        }

                        String changeId = getValue(reader, CHANGE_ID);
                        if (isNotBlank(changeId)) {
                            hierarchy.setChangeId(Long.valueOf(changeId));
                        }

                        String regionCode = getValue(reader, REGION_CODE);
                        hierarchy.setRegionCode(regionCode);

                        String areaCode = getValue(reader, AREA_CODE);
                        hierarchy.setAreaCode(areaCode);

                        String cityCode = getValue(reader, CITY_CODE);
                        hierarchy.setCityCode(cityCode);

                        String placeCode = getValue(reader, PLACE_CODE);
                        hierarchy.setPlaceCode(placeCode);

                        String planCode = getValue(reader, PLAN_CODE);
                        hierarchy.setPlanCode(planCode);

                        String streetCode = getValue(reader, STREET_CODE);
                        hierarchy.setStreetCode(streetCode);

                        String prevId = getValue(reader, PREV_ID);
                        if (isNotBlank(prevId)) {
                            hierarchy.setPrevId(Long.valueOf(prevId));
                        }

                        String nextId = getValue(reader, NEXT_ID);
                        if (isNotBlank(nextId)) {
                            hierarchy.setNextId(Long.valueOf(nextId));
                        }

                        String updateDate = getValue(reader, UPDATE_DATE);
                        if (isNotBlank(updateDate)) {
                            hierarchy.setUpdateDate(Date.valueOf(updateDate));
                        }

                        String startDate = getValue(reader, START_DATE);
                        if (isNotBlank(startDate)) {
                            hierarchy.setStartDate(Date.valueOf(startDate));
                        }

                        String endDate = getValue(reader, END_DATE);
                        if (isNotBlank(endDate)) {
                            hierarchy.setEndDate(Date.valueOf(endDate));
                        }

                        String isActive = getValue(reader, IS_ACTIVE);
                        if (isNotBlank(isActive)) {
                            hierarchy.setIsActive(Integer.valueOf(isActive));
                        }
                    }
                    break;

                case END_ELEMENT:

                    if (ITEM.equals(reader.getLocalName()) && hierarchy.getId() != null) {
                        hierarchyList.add(hierarchy);
                    }

                    if (hierarchyList.size() == BATCH_SIZE) {
                        consumer.accept(entityManager, hierarchyList);
                        hierarchyList.clear();
                    }
                    break;
            }
        }
        consumer.accept(entityManager, hierarchyList);
        closeReader(reader);
        closeStream(inputStream);
    }
}