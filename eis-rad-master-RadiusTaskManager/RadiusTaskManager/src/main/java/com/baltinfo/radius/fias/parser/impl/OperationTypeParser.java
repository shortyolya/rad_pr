package com.baltinfo.radius.fias.parser.impl;

import com.baltinfo.radius.fias.model.OperationType;
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

public class OperationTypeParser extends FiasAbstractParser {

    @Override
    public void parse(EntityManager entityManager, String path, BiConsumer<EntityManager, List<?>> consumer) throws IOException, XMLStreamException {

        OperationType operationType = null;
        List<OperationType> operationTypeList = new ArrayList<>();

        InputStream inputStream = Files.newInputStream(Paths.get(path));
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {

                case START_ELEMENT:
                    if (OPERATION_TYPE.equals(reader.getLocalName())) {
                        operationType = new OperationType();

                        String id = getValue(reader, ID);
                        if (isNotBlank(id)) {
                            operationType.setId(Integer.valueOf(id));
                        }

                        String name = getValue(reader, NAME);
                        operationType.setName(name);

                        String shortName = getValue(reader, SHORT_NAME);
                        operationType.setShortName(shortName);

                        String desc = getValue(reader, DESC);
                        operationType.setDesc(desc);

                        String updateDate = getValue(reader, UPDATE_DATE);
                        if (isNotBlank(updateDate)) {
                            operationType.setUpdateDate(Date.valueOf(updateDate));
                        }

                        String startDate = getValue(reader, START_DATE);
                        if (isNotBlank(startDate)) {
                            operationType.setStartDate(Date.valueOf(startDate));
                        }

                        String endDate = getValue(reader, END_DATE);
                        if (isNotBlank(endDate)) {
                            operationType.setEndDate(Date.valueOf(endDate));
                        }

                        String isActive = getValue(reader, IS_ACTIVE);
                        if (isNotBlank(isActive)) {
                            operationType.setIsActive(Boolean.valueOf(isActive));
                        }
                    }
                    break;

                case END_ELEMENT:

                    if (OPERATION_TYPE.equals(reader.getLocalName()) && operationType.getId() != null) {
                        operationTypeList.add(operationType);
                    }

                    if (operationTypeList.size() == BATCH_SIZE) {
                        consumer.accept(entityManager, operationTypeList);
                        operationTypeList.clear();
                    }
                    break;
            }
        }
        consumer.accept(entityManager, operationTypeList);
        closeReader(reader);
        closeStream(inputStream);
    }
}