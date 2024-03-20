package com.baltinfo.radius.fias.parser.impl;

import com.baltinfo.radius.fias.model.AddressObjectType;
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

public class AddressObjectTypeParser extends FiasAbstractParser {

    @Override
    public void parse(EntityManager entityManager, String path, BiConsumer<EntityManager, List<?>> consumer) throws IOException, XMLStreamException {

        AddressObjectType addressObjectType = null;
        List<AddressObjectType> addressObjectTypeList = new ArrayList<>();

        InputStream inputStream = Files.newInputStream(Paths.get(path));
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {

                case START_ELEMENT:
                    if (ADDRESS_OBJECT_TYPE.equals(reader.getLocalName())) {
                        addressObjectType = new AddressObjectType();

                        String id = getValue(reader, ID);
                        if (isNotBlank(id)) {
                            addressObjectType.setId(Integer.valueOf(id));
                        }

                        String level = getValue(reader, LEVEL);
                        if (isNotBlank(level)) {
                            addressObjectType.setLevel(Integer.valueOf(level));
                        }

                        String shortName = getValue(reader, SHORT_NAME);
                        addressObjectType.setShortName(shortName);

                        String name = getValue(reader, NAME);
                        addressObjectType.setName(name);

                        String desc = getValue(reader, DESC);
                        addressObjectType.setDesc(desc);

                        String updateDate = getValue(reader, UPDATE_DATE);
                        if (isNotBlank(updateDate)) {
                            addressObjectType.setUpdateDate(Date.valueOf(updateDate));
                        }

                        String startDate = getValue(reader, START_DATE);
                        if (isNotBlank(startDate)) {
                            addressObjectType.setStartDate(Date.valueOf(startDate));
                        }

                        String endDate = getValue(reader, END_DATE);
                        if (isNotBlank(endDate)) {
                            addressObjectType.setEndDate(Date.valueOf(endDate));
                        }

                        String isActive = getValue(reader, IS_ACTIVE);
                        if (isNotBlank(isActive)) {
                            addressObjectType.setIsActive(Boolean.valueOf(isActive));
                        }
                    }
                    break;

                case END_ELEMENT:

                    if (ADDRESS_OBJECT_TYPE.equals(reader.getLocalName()) && addressObjectType.getId() != null) {
                        addressObjectTypeList.add(addressObjectType);
                    }

                    if (addressObjectTypeList.size() == BATCH_SIZE) {
                        consumer.accept(entityManager, addressObjectTypeList);
                        addressObjectTypeList.clear();
                    }
                    break;
            }
        }
        consumer.accept(entityManager, addressObjectTypeList);
        closeReader(reader);
        closeStream(inputStream);
    }
}