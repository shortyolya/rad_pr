package com.baltinfo.radius.fias.parser.impl;

import com.baltinfo.radius.fias.model.Param;
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

public class ParamParser extends FiasAbstractParser {

    @Override
    public void parse(EntityManager entityManager, String path, BiConsumer<EntityManager, List<?>> consumer) throws IOException, XMLStreamException {

        Param param = null;
        List<Param> paramList = new ArrayList<>();

        InputStream inputStream = Files.newInputStream(Paths.get(path));
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {

                case START_ELEMENT:
                    if (PARAM.equals(reader.getLocalName())) {
                        param = new Param();

                        String id = getValue(reader, ID);
                        if (isNotBlank(id)) {
                            param.setId(Long.valueOf(id));
                        }

                        String objectId = getValue(reader, OBJECT_ID);
                        if (isNotBlank(objectId)) {
                            param.setObjectId(Integer.valueOf(objectId));
                        }

                        String typeId = getValue(reader, TYPE_ID);
                        if (isNotBlank(typeId)) {
                            param.setTypeId(Integer.valueOf(typeId));
                        }

                        String changeId = getValue(reader, CHANGE_ID);
                        if (isNotBlank(changeId)) {
                            param.setChangeId(Integer.valueOf(changeId));
                        }

                        String updateDate = getValue(reader, UPDATE_DATE);
                        if (isNotBlank(updateDate)) {
                            param.setUpdateDate(Date.valueOf(updateDate));
                        }

                        String value = getValue(reader, VALUE);
                        param.setValue(value);
                    }
                    break;

                case END_ELEMENT:

                    if (PARAM.equals(reader.getLocalName()) && param.getId() != null) {
                        paramList.add(param);
                    }

                    if (paramList.size() == BATCH_SIZE) {
                        consumer.accept(entityManager, paramList);
                        paramList.clear();
                    }
                    break;
            }
        }
        consumer.accept(entityManager, paramList);
        closeReader(reader);
        closeStream(inputStream);
    }
}