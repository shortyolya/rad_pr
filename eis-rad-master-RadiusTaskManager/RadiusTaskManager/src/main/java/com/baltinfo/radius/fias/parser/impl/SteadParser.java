package com.baltinfo.radius.fias.parser.impl;

import com.baltinfo.radius.fias.model.Stead;
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

public class SteadParser extends FiasAbstractParser {

    @Override
    public void parse(EntityManager entityManager, String path, BiConsumer<EntityManager, List<?>> consumer) throws IOException, XMLStreamException {

        Stead stead = null;
        List<Stead> steadList = new ArrayList<>();

        InputStream inputStream = Files.newInputStream(Paths.get(path));
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {

                case START_ELEMENT:
                    if (STEAD.equals(reader.getLocalName())) {
                        stead = new Stead();

                        String id = getValue(reader, ID);
                        if (isNotBlank(id)) {
                            stead.setId(Long.valueOf(id));
                        }

                        String objectId = getValue(reader, OBJECT_ID);
                        if (isNotBlank(objectId)) {
                            stead.setObjectId(Long.valueOf(objectId));
                        }

                        String objectGuid = getValue(reader, OBJECT_GUID);
                        stead.setObjectGuid(objectGuid);


                        String changeId = getValue(reader, CHANGE_ID);
                        if (isNotBlank(changeId)) {
                            stead.setChangeId(Long.valueOf(changeId));
                        }

                        String number = getValue(reader, NUMBER);
                        stead.setNumber(number);

                        String operTypeId = getValue(reader, OPER_TYPE_ID);
                        stead.setOperTypeId(operTypeId);

                        String prevId = getValue(reader, PREV_ID);
                        if (isNotBlank(prevId)) {
                            stead.setPrevId(Long.valueOf(prevId));
                        }

                        String nextId = getValue(reader, NEXT_ID);
                        if (isNotBlank(nextId)) {
                            stead.setNextId(Long.valueOf(nextId));
                        }

                        String updateDate = getValue(reader, UPDATE_DATE);
                        if (isNotBlank(updateDate)) {
                            stead.setUpdateDate(Date.valueOf(updateDate));
                        }

                        String startDate = getValue(reader, START_DATE);
                        if (isNotBlank(startDate)) {
                            stead.setStartDate(Date.valueOf(startDate));
                        }

                        String endDate = getValue(reader, END_DATE);
                        if (isNotBlank(endDate)) {
                            stead.setEndDate(Date.valueOf(endDate));
                        }

                        String isActual = getValue(reader, IS_ACTUAL);
                        if (isNotBlank(isActual)) {
                            stead.setIsActual(Integer.valueOf(isActual));
                        }

                        String isActive = getValue(reader, IS_ACTIVE);
                        if (isNotBlank(isActive)) {
                            stead.setIsActive(Integer.valueOf(isActive));
                        }
                    }
                    break;

                case END_ELEMENT:

                    if (STEAD.equals(reader.getLocalName()) && stead.getId() != null) {
                        steadList.add(stead);
                    }

                    if (steadList.size() == BATCH_SIZE) {
                        consumer.accept(entityManager, steadList);
                        steadList.clear();
                    }
                    break;
            }
        }
        consumer.accept(entityManager, steadList);
        closeReader(reader);
        closeStream(inputStream);
    }
}