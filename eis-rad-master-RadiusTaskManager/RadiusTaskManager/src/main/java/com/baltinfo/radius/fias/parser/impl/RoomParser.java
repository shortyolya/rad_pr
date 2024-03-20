package com.baltinfo.radius.fias.parser.impl;

import com.baltinfo.radius.fias.model.Room;
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

public class RoomParser extends FiasAbstractParser {

    @Override
    public void parse(EntityManager entityManager, String path, BiConsumer<EntityManager, List<?>> consumer) throws IOException, XMLStreamException {

        Room room = null;
        List<Room> roomList = new ArrayList<>();

        InputStream inputStream = Files.newInputStream(Paths.get(path));
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {

                case START_ELEMENT:
                    if (ROOM.equals(reader.getLocalName())) {
                        room = new Room();

                        String id = getValue(reader, ID);
                        if (isNotBlank(id)) {
                            room.setId(Long.valueOf(id));
                        }

                        String objectId = getValue(reader, OBJECT_ID);
                        if (isNotBlank(objectId)) {
                            room.setObjectId(Long.valueOf(objectId));
                        }

                        String objectGuid = getValue(reader, OBJECT_GUID);
                        room.setObjectGuid(objectGuid);


                        String changeId = getValue(reader, CHANGE_ID);
                        if (isNotBlank(changeId)) {
                            room.setChangeId(Long.valueOf(changeId));
                        }

                        String number = getValue(reader, NUMBER);
                        room.setNumber(number);

                        String roomType = getValue(reader, ROOM_TYPE);
                        room.setRoomType(roomType);

                        String operTypeId = getValue(reader, OPER_TYPE_ID);
                        room.setOperTypeId(operTypeId);

                        String prevId = getValue(reader, PREV_ID);
                        if (isNotBlank(prevId)) {
                            room.setPrevId(Long.valueOf(prevId));
                        }

                        String nextId = getValue(reader, NEXT_ID);
                        if (isNotBlank(nextId)) {
                            room.setNextId(Long.valueOf(nextId));
                        }

                        String updateDate = getValue(reader, UPDATE_DATE);
                        if (isNotBlank(updateDate)) {
                            room.setUpdateDate(Date.valueOf(updateDate));
                        }

                        String startDate = getValue(reader, START_DATE);
                        if (isNotBlank(startDate)) {
                            room.setStartDate(Date.valueOf(startDate));
                        }

                        String endDate = getValue(reader, END_DATE);
                        if (isNotBlank(endDate)) {
                            room.setEndDate(Date.valueOf(endDate));
                        }

                        String isActual = getValue(reader, IS_ACTUAL);
                        if (isNotBlank(isActual)) {
                            room.setIsActual(Integer.valueOf(isActual));
                        }

                        String isActive = getValue(reader, IS_ACTIVE);
                        if (isNotBlank(isActive)) {
                            room.setIsActive(Integer.valueOf(isActive));
                        }
                    }
                    break;

                case END_ELEMENT:

                    if (ROOM.equals(reader.getLocalName()) && room.getId() != null) {
                        roomList.add(room);
                    }

                    if (roomList.size() == BATCH_SIZE) {
                        consumer.accept(entityManager, roomList);
                        roomList.clear();
                    }
                    break;
            }
        }
        consumer.accept(entityManager, roomList);
        closeReader(reader);
        closeStream(inputStream);
    }
}