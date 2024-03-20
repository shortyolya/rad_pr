package com.baltinfo.radius.fias.parser.impl;

import com.baltinfo.radius.fias.model.House;
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

public class HouseParser extends FiasAbstractParser {

    @Override
    public void parse(EntityManager entityManager, String path, BiConsumer<EntityManager, List<?>> consumer) throws IOException, XMLStreamException {

        House house = null;
        List<House> houseList = new ArrayList<>();

        InputStream inputStream = Files.newInputStream(Paths.get(path));
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {

                case START_ELEMENT:
                    if (HOUSE.equals(reader.getLocalName())) {
                        house = new House();

                        String id = getValue(reader, ID);
                        if (isNotBlank(id)) {
                            house.setId(Long.valueOf(id));
                        }

                        String objectId = getValue(reader, OBJECT_ID);
                        if (isNotBlank(objectId)) {
                            house.setObjectId(Long.valueOf(objectId));
                        }

                        String objectGuid = getValue(reader, OBJECT_GUID);
                        house.setObjectGuid(objectGuid);

                        String changeId = getValue(reader, CHANGE_ID);
                        if (isNotBlank(changeId)) {
                            house.setChangeId(Long.valueOf(changeId));
                        }

                        String houseNum = getValue(reader, HOUSE_NUM);
                        house.setHouseNum(houseNum);

                        String addNum1 = getValue(reader, ADD_NUM_1);
                        house.setAddNum1(addNum1);

                        String addNum2 = getValue(reader, ADD_NUM_2);
                        house.setAddNum2(addNum2);

                        String houseType = getValue(reader, HOUSE_TYPE);
                        if (isNotBlank(houseType)) {
                            house.setHouseType(Integer.valueOf(houseType));
                        }

                        String addType1 = getValue(reader, ADD_TYPE_1);
                        if (isNotBlank(addType1)) {
                            house.setAddType1(Integer.valueOf(addType1));
                        }

                        String addType2 = getValue(reader, ADD_TYPE_2);
                        if (isNotBlank(addType2)) {
                            house.setAddType2(Integer.valueOf(addType2));
                        }

                        String operTypeId = getValue(reader, OPER_TYPE_ID);
                        if (isNotBlank(operTypeId)) {
                            house.setOperTypeId(Integer.valueOf(operTypeId));
                        }

                        String prevId = getValue(reader, PREV_ID);
                        if (isNotBlank(prevId)) {
                            house.setPrevId(Long.valueOf(prevId));
                        }

                        String nextId = getValue(reader, NEXT_ID);
                        if (isNotBlank(nextId)) {
                            house.setNextId(Long.valueOf(nextId));
                        }

                        String updateDate = getValue(reader, UPDATE_DATE);
                        if (isNotBlank(updateDate)) {
                            house.setUpdateDate(Date.valueOf(updateDate));
                        }

                        String startDate = getValue(reader, START_DATE);
                        if (isNotBlank(startDate)) {
                            house.setStartDate(Date.valueOf(startDate));
                        }

                        String endDate = getValue(reader, END_DATE);
                        if (isNotBlank(endDate)) {
                            house.setEndDate(Date.valueOf(endDate));
                        }

                        String isActual = getValue(reader, IS_ACTUAL);
                        if (isNotBlank(isActual)) {
                            house.setIsActual(Integer.valueOf(isActual));
                        }

                        String isActive = getValue(reader, IS_ACTIVE);
                        if (isNotBlank(isActive)) {
                            house.setIsActive(Integer.valueOf(isActive));
                        }
                    }
                    break;

                case END_ELEMENT:

                    if (HOUSE.equals(reader.getLocalName()) && house.getId() != null) {
                        houseList.add(house);
                    }

                    if (houseList.size() == BATCH_SIZE) {
                        consumer.accept(entityManager, houseList);
                        houseList.clear();
                    }
                    break;
            }
        }
        consumer.accept(entityManager, houseList);
        closeReader(reader);
        closeStream(inputStream);
    }
}