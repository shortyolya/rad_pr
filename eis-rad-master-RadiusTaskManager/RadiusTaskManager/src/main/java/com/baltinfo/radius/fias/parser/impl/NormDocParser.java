package com.baltinfo.radius.fias.parser.impl;

import com.baltinfo.radius.fias.model.NormDoc;
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

public class NormDocParser extends FiasAbstractParser {

    @Override
    public void parse(EntityManager entityManager, String path, BiConsumer<EntityManager, List<?>> consumer) throws IOException, XMLStreamException {

        NormDoc normDoc = null;
        List<NormDoc> normDocList = new ArrayList<>();

        InputStream inputStream = Files.newInputStream(Paths.get(path));
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {

                case START_ELEMENT:
                    if (NORM_DOC.equals(reader.getLocalName())) {
                        normDoc = new NormDoc();

                        String id = getValue(reader, ID);
                        if (isNotBlank(id)) {
                            normDoc.setId(Long.valueOf(id));
                        }

                        String name = getValue(reader, NAME);
                        normDoc.setName(name);

                        String date = getValue(reader, DATE);
                        if (isNotBlank(date)) {
                            normDoc.setDate(Date.valueOf(date));
                        }

                        String number = getValue(reader, NUMBER);
                        normDoc.setNumber(number);

                        String type = getValue(reader, TYPE);
                        if (isNotBlank(type)) {
                            normDoc.setType(Integer.valueOf(type));
                        }

                        String kind = getValue(reader, KIND);
                        if (isNotBlank(kind)) {
                            normDoc.setKind(Integer.valueOf(kind));
                        }

                        String updateDate = getValue(reader, UPDATE_DATE);
                        if (isNotBlank(updateDate)) {
                            normDoc.setUpdateDate(Date.valueOf(updateDate));
                        }

                        String orgName = getValue(reader, ORG_NAME);
                        normDoc.setOrgName(orgName);

                        String regNum = getValue(reader, REG_NUM);
                        normDoc.setRegNum(regNum);

                        String regDate = getValue(reader, REG_DATE);
                        if (isNotBlank(regDate)) {
                            normDoc.setRegDate(Date.valueOf(regDate));
                        }

                        String accDate = getValue(reader, ACC_DATE);
                        if (isNotBlank(accDate)) {
                            normDoc.setAccDate(Date.valueOf(accDate));
                        }

                        String comment = getValue(reader, COMMENT);
                        normDoc.setComment(comment);
                    }
                    break;

                case END_ELEMENT:

                    if (NORM_DOC.equals(reader.getLocalName()) && normDoc.getId() != null) {
                        normDocList.add(normDoc);
                    }

                    if (normDocList.size() == BATCH_SIZE) {
                        consumer.accept(entityManager, normDocList);
                        normDocList.clear();
                    }
                    break;
            }
        }
        consumer.accept(entityManager, normDocList);
        closeReader(reader);
        closeStream(inputStream);
    }
}