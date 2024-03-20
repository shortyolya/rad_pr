package com.baltinfo.radius.fias.parser.impl;

import com.baltinfo.radius.fias.model.NdocType;
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

public class NdocTypeParser extends FiasAbstractParser {

    @Override
    public void parse(EntityManager entityManager, String path, BiConsumer<EntityManager, List<?>> consumer) throws IOException, XMLStreamException {

        NdocType nDocType = null;
        List<NdocType> nDocTypeList = new ArrayList<>();

        InputStream inputStream = Files.newInputStream(Paths.get(path));
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {

                case START_ELEMENT:
                    if (NDOC_TYPE.equals(reader.getLocalName())) {
                        nDocType = new NdocType();

                        String id = getValue(reader, ID);
                        if (isNotBlank(id)) {
                            nDocType.setId(Integer.valueOf(id));
                        }

                        String name = getValue(reader, NAME);
                        nDocType.setName(name);

                        String startDate = getValue(reader, START_DATE);
                        if (isNotBlank(startDate)) {
                            nDocType.setStartDate(Date.valueOf(startDate));
                        }

                        String endDate = getValue(reader, END_DATE);
                        if (isNotBlank(endDate)) {
                            nDocType.setEndDate(Date.valueOf(endDate));
                        }
                    }
                    break;

                case END_ELEMENT:

                    if (NDOC_TYPE.equals(reader.getLocalName()) && nDocType.getId() != null) {
                        nDocTypeList.add(nDocType);
                    }

                    if (nDocTypeList.size() == BATCH_SIZE) {
                        consumer.accept(entityManager, nDocTypeList);
                        nDocTypeList.clear();
                    }
                    break;
            }
        }
        consumer.accept(entityManager, nDocTypeList);
        closeReader(reader);
        closeStream(inputStream);
    }
}