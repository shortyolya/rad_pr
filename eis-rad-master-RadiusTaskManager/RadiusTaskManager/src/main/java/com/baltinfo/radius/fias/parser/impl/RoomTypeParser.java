package com.baltinfo.radius.fias.parser.impl;

import com.baltinfo.radius.fias.model.RoomType;
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

public class RoomTypeParser extends FiasAbstractParser {

    @Override
    public void parse(EntityManager entityManager, String path, BiConsumer<EntityManager, List<?>> consumer) throws IOException, XMLStreamException {

        RoomType roomType = null;
        List<RoomType> roomTypeList = new ArrayList<>();

        InputStream inputStream = Files.newInputStream(Paths.get(path));
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {

                case START_ELEMENT:
                    if (ROOM_TYPE.equals(reader.getLocalName())) {
                        roomType = new RoomType();

                        String id = getValue(reader, ID);
                        if (isNotBlank(id)) {
                            roomType.setId(Integer.valueOf(id));
                        }

                        String name = getValue(reader, NAME);
                        roomType.setName(name);

                        String shortName = getValue(reader, SHORT_NAME);
                        roomType.setShortName(shortName);

                        String desc = getValue(reader, DESC);
                        roomType.setDesc(desc);

                        String updateDate = getValue(reader, UPDATE_DATE);
                        if (isNotBlank(updateDate)) {
                            roomType.setUpdateDate(Date.valueOf(updateDate));
                        }

                        String startDate = getValue(reader, START_DATE);
                        if (isNotBlank(startDate)) {
                            roomType.setStartDate(Date.valueOf(startDate));
                        }

                        String endDate = getValue(reader, END_DATE);
                        if (isNotBlank(endDate)) {
                            roomType.setEndDate(Date.valueOf(endDate));
                        }

                        String isActive = getValue(reader, IS_ACTIVE);
                        if (isNotBlank(isActive)) {
                            roomType.setIsActive(Boolean.valueOf(isActive));
                        }
                    }
                    break;

                case END_ELEMENT:

                    if (ROOM_TYPE.equals(reader.getLocalName()) && roomType.getId() != null) {
                        roomTypeList.add(roomType);
                    }

                    if (roomTypeList.size() == BATCH_SIZE) {
                        consumer.accept(entityManager, roomTypeList);
                        roomTypeList.clear();
                    }
                    break;
            }
        }
        consumer.accept(entityManager, roomTypeList);
        closeReader(reader);
        closeStream(inputStream);
    }
}