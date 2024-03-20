package com.baltinfo.radius.fias.parser.impl;

import com.baltinfo.radius.fias.model.HouseType;
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

public class HouseTypeParser extends FiasAbstractParser {

    @Override
    public void parse(EntityManager entityManager, String path, BiConsumer<EntityManager, List<?>> consumer) throws IOException, XMLStreamException {

        HouseType houseType = null;
        List<HouseType> houseTypeList = new ArrayList<>();

        InputStream inputStream = Files.newInputStream(Paths.get(path));
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {

                case START_ELEMENT:
                    if (HOUSE_TYPE.equals(reader.getLocalName())) {
                        houseType = new HouseType();

                        String id = getValue(reader, ID);
                        if (isNotBlank(id)) {
                            houseType.setId(Integer.valueOf(id));
                        }

                        String name = getValue(reader, NAME);
                        houseType.setName(name);

                        String shortName = getValue(reader, SHORT_NAME);
                        houseType.setShortName(shortName);

                        String desc = getValue(reader, DESC);
                        houseType.setDesc(desc);

                        String updateDate = getValue(reader, UPDATE_DATE);
                        if (isNotBlank(updateDate)) {
                            houseType.setUpdateDate(Date.valueOf(updateDate));
                        }

                        String startDate = getValue(reader, START_DATE);
                        if (isNotBlank(startDate)) {
                            houseType.setStartDate(Date.valueOf(startDate));
                        }

                        String endDate = getValue(reader, END_DATE);
                        if (isNotBlank(endDate)) {
                            houseType.setEndDate(Date.valueOf(endDate));
                        }

                        String isActive = getValue(reader, IS_ACTIVE);
                        if (isNotBlank(isActive)) {
                            houseType.setIsActive(Boolean.valueOf(isActive));
                        }
                    }
                    break;

                case END_ELEMENT:

                    if (HOUSE_TYPE.equals(reader.getLocalName()) && houseType.getId() != null) {
                        houseTypeList.add(houseType);
                    }

                    if (houseTypeList.size() == BATCH_SIZE) {
                        consumer.accept(entityManager, houseTypeList);
                        houseTypeList.clear();
                    }
                    break;
            }
        }
        consumer.accept(entityManager, houseTypeList);
        closeReader(reader);
        closeStream(inputStream);
    }
}