package com.baltinfo.radius.fias.parser.impl;

import com.baltinfo.radius.fias.model.ParamType;
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

public class ParamTypeParser extends FiasAbstractParser {

    @Override
    public void parse(EntityManager entityManager, String path, BiConsumer<EntityManager, List<?>> consumer) throws IOException, XMLStreamException {

        ParamType paramType = null;
        List<ParamType> paramTypeList = new ArrayList<>();

        InputStream inputStream = Files.newInputStream(Paths.get(path));
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {

                case START_ELEMENT:
                    if (PARAM_TYPE.equals(reader.getLocalName())) {
                        paramType = new ParamType();

                        String id = getValue(reader, ID);
                        if (isNotBlank(id)) {
                            paramType.setId(Integer.valueOf(id));
                        }

                        String name = getValue(reader, NAME);
                        paramType.setName(name);

                        String code = getValue(reader, CODE);
                        paramType.setCode(code);

                        String desc = getValue(reader, DESC);
                        paramType.setDesc(desc);

                        String updateDate = getValue(reader, UPDATE_DATE);
                        if (isNotBlank(updateDate)) {
                            paramType.setUpdateDate(Date.valueOf(updateDate));
                        }

                        String startDate = getValue(reader, START_DATE);
                        if (isNotBlank(startDate)) {
                            paramType.setStartDate(Date.valueOf(startDate));
                        }

                        String endDate = getValue(reader, END_DATE);
                        if (isNotBlank(endDate)) {
                            paramType.setEndDate(Date.valueOf(endDate));
                        }

                        String isActive = getValue(reader, IS_ACTIVE);
                        if (isNotBlank(isActive)) {
                            paramType.setIsActive(Boolean.valueOf(isActive));
                        }
                    }
                    break;

                case END_ELEMENT:

                    if (PARAM_TYPE.equals(reader.getLocalName()) && paramType.getId() != null) {
                        paramTypeList.add(paramType);
                    }

                    if (paramTypeList.size() == BATCH_SIZE) {
                        consumer.accept(entityManager, paramTypeList);
                        paramTypeList.clear();
                    }
                    break;
            }
        }
        consumer.accept(entityManager, paramTypeList);
        closeReader(reader);
        closeStream(inputStream);
    }
}