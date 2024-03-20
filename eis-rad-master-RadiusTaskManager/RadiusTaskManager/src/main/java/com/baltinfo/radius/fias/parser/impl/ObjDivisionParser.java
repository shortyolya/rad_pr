package com.baltinfo.radius.fias.parser.impl;

import com.baltinfo.radius.fias.model.ObjDivision;
import com.baltinfo.radius.fias.parser.FiasAbstractParser;

import javax.persistence.EntityManager;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import static com.baltinfo.radius.fias.utils.FiasConstants.*;
import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

public class ObjDivisionParser extends FiasAbstractParser {

    @Override
    public void parse(EntityManager entityManager, String path, BiConsumer<EntityManager, List<?>> consumer) throws IOException, XMLStreamException {

        ObjDivision objDivision = null;
        List<ObjDivision> objDivisionList = new ArrayList<>();

        InputStream inputStream = Files.newInputStream(Paths.get(path));
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {

                case START_ELEMENT:
                    if (ITEM.equals(reader.getLocalName())) {
                        objDivision = new ObjDivision();

                        String id = getValue(reader, ID);
                        if (isNotBlank(id)) {
                            objDivision.setId(Long.valueOf(id));
                        }

                        String parentId = getValue(reader, PARENT_ID);
                        if (isNotBlank(parentId)) {
                            objDivision.setParentId(Long.valueOf(parentId));
                        }

                        String childId = getValue(reader, CHILD_ID);
                        if (isNotBlank(childId)) {
                            objDivision.setChildId(Long.valueOf(childId));
                        }

                        String changeId = getValue(reader, CHANGE_ID);
                        if (isNotBlank(changeId)) {
                            objDivision.setChangeId(Integer.valueOf(changeId));
                        }
                    }
                    break;

                case END_ELEMENT:

                    if (ITEM.equals(reader.getLocalName()) && objDivision.getId() != null) {
                        objDivisionList.add(objDivision);
                    }

                    if (objDivisionList.size() == BATCH_SIZE) {
                        consumer.accept(entityManager, objDivisionList);
                        objDivisionList.clear();
                    }
                    break;
            }
        }
        consumer.accept(entityManager, objDivisionList);
        closeReader(reader);
        closeStream(inputStream);
    }
}