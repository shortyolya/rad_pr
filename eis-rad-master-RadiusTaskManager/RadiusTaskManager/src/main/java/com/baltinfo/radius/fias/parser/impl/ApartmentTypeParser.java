package com.baltinfo.radius.fias.parser.impl;

import com.baltinfo.radius.fias.model.ApartmentType;
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

public class ApartmentTypeParser extends FiasAbstractParser {

    @Override
    public void parse(EntityManager entityManager, String path, BiConsumer<EntityManager, List<?>> consumer) throws IOException, XMLStreamException {

        ApartmentType apartmentType = null;
        List<ApartmentType> apartmentTypeList = new ArrayList<>();

        InputStream inputStream = Files.newInputStream(Paths.get(path));
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {

                case START_ELEMENT:
                    if (APARTMENT_TYPE.equals(reader.getLocalName())) {
                        apartmentType = new ApartmentType();

                        String id = getValue(reader, ID);
                        if (isNotBlank(id)) {
                            apartmentType.setId(Integer.valueOf(id));
                        }

                        String name = getValue(reader, NAME);
                        apartmentType.setName(name);

                        String shortName = getValue(reader, SHORT_NAME);
                        apartmentType.setShortName(shortName);

                        String desc = getValue(reader, DESC);
                        apartmentType.setDesc(desc);

                        String updateDate = getValue(reader, UPDATE_DATE);
                        if (isNotBlank(updateDate)) {
                            apartmentType.setUpdateDate(Date.valueOf(updateDate));
                        }

                        String startDate = getValue(reader, START_DATE);
                        if (isNotBlank(startDate)) {
                            apartmentType.setStartDate(Date.valueOf(startDate));
                        }

                        String endDate = getValue(reader, END_DATE);
                        if (isNotBlank(endDate)) {
                            apartmentType.setEndDate(Date.valueOf(endDate));
                        }

                        String isActive = getValue(reader, IS_ACTIVE);
                        if (isNotBlank(isActive)) {
                            if (isActive.equals("1")) {
                                apartmentType.setIsActive(Boolean.TRUE);
                            }
                            if (isActive.equals("0")) {
                                apartmentType.setIsActive(Boolean.FALSE);
                            }
                        }
                    }
                    break;

                case END_ELEMENT:

                    if (APARTMENT_TYPE.equals(reader.getLocalName()) && apartmentType.getId() != null) {
                        apartmentTypeList.add(apartmentType);
                    }

                    if (apartmentTypeList.size() == BATCH_SIZE) {
                        consumer.accept(entityManager, apartmentTypeList);
                        apartmentTypeList.clear();
                    }
                    break;
            }
        }
        consumer.accept(entityManager, apartmentTypeList);
        closeReader(reader);
        closeStream(inputStream);
    }
}