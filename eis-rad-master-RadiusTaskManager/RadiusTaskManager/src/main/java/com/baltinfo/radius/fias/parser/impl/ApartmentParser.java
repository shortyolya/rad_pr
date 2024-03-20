package com.baltinfo.radius.fias.parser.impl;

import com.baltinfo.radius.fias.model.Apartment;
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

public class ApartmentParser extends FiasAbstractParser {

    @Override
    public void parse(EntityManager entityManager, String path, BiConsumer<EntityManager, List<?>> consumer) throws IOException, XMLStreamException {

        Apartment apartment = null;
        List<Apartment> apartmentList = new ArrayList<>();

        InputStream inputStream = Files.newInputStream(Paths.get(path));
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {

                case START_ELEMENT:
                    if (APARTMENT.equals(reader.getLocalName())) {
                        apartment = new Apartment();

                        String id = getValue(reader, ID);
                        if (isNotBlank(id)) {
                            apartment.setId(Long.valueOf(id));
                        }

                        String objectId = getValue(reader, OBJECT_ID);
                        if (isNotBlank(objectId)) {
                            apartment.setObjectId(Long.valueOf(objectId));
                        }

                        String objectGuid = getValue(reader, OBJECT_GUID);
                        apartment.setObjectGuid(objectGuid);


                        String changeId = getValue(reader, CHANGE_ID);
                        if (isNotBlank(changeId)) {
                            apartment.setChangeId(Long.valueOf(changeId));
                        }

                        String number = getValue(reader, NUMBER);
                        apartment.setNumber(number);

                        String apartType = getValue(reader, APART_TYPE);
                        apartment.setApartType(apartType);

                        String operTypeId = getValue(reader, OPER_TYPE_ID);
                        apartment.setOperTypeId(operTypeId);

                        String prevId = getValue(reader, PREV_ID);
                        if (isNotBlank(prevId)) {
                            apartment.setPrevId(Long.valueOf(prevId));
                        }

                        String nextId = getValue(reader, NEXT_ID);
                        if (isNotBlank(nextId)) {
                            apartment.setNextId(Long.valueOf(nextId));
                        }

                        String updateDate = getValue(reader, UPDATE_DATE);
                        if (isNotBlank(updateDate)) {
                            apartment.setUpdateDate(Date.valueOf(updateDate));
                        }

                        String startDate = getValue(reader, START_DATE);
                        if (isNotBlank(startDate)) {
                            apartment.setStartDate(Date.valueOf(startDate));
                        }

                        String endDate = getValue(reader, END_DATE);
                        if (isNotBlank(endDate)) {
                            apartment.setEndDate(Date.valueOf(endDate));
                        }

                        String isActual = getValue(reader, IS_ACTUAL);
                        if (isNotBlank(isActual)) {
                            apartment.setIsActual(Integer.valueOf(isActual));
                        }

                        String isActive = getValue(reader, IS_ACTIVE);
                        if (isNotBlank(isActive)) {
                            apartment.setIsActive(Integer.valueOf(isActive));
                        }
                    }
                    break;

                case END_ELEMENT:

                    if (APARTMENT.equals(reader.getLocalName()) && apartment.getId() != null) {
                        apartmentList.add(apartment);
                    }

                    if (apartmentList.size() == BATCH_SIZE) {
                        consumer.accept(entityManager, apartmentList);
                        apartmentList.clear();
                    }
                    break;
            }
        }
        consumer.accept(entityManager, apartmentList);
        closeReader(reader);
        closeStream(inputStream);
    }
}