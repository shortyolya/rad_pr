package com.baltinfo.radius.fias.parser.impl;

import com.baltinfo.radius.fias.model.ChangeHistory;
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

public class ChangeHistoryParser extends FiasAbstractParser {

    @Override
    public void parse(EntityManager entityManager, String path, BiConsumer<EntityManager, List<?>> consumer) throws IOException, XMLStreamException {

        ChangeHistory changeHistory = null;
        List<ChangeHistory> changeHistoryList = new ArrayList<>();

        InputStream inputStream = Files.newInputStream(Paths.get(path));
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {

                case START_ELEMENT:
                    if (ITEM.equals(reader.getLocalName())) {
                        changeHistory = new ChangeHistory();

                        String changeId = getValue(reader, CHANGE_ID);
                        if (isNotBlank(changeId)) {
                            changeHistory.setId(Integer.valueOf(changeId));
                        }

                        String objectId = getValue(reader, OBJECT_ID);
                        if (isNotBlank(objectId)) {
                            changeHistory.setObjectId(Long.valueOf(objectId));
                        }

                        String adrObjectId = getValue(reader, ADR_OBJECT_ID);
                        changeHistory.setAdrobjectid(adrObjectId);


                        String operTypeId = getValue(reader, OPER_TYPE_ID);
                        if (isNotBlank(operTypeId)) {
                            changeHistory.setOperTypeId(Long.valueOf(operTypeId));
                        }

                        String nDocId = getValue(reader, NDOC_ID);
                        if (isNotBlank(nDocId)) {
                            changeHistory.setNdocId(Long.valueOf(nDocId));
                        }

                        String changeDate = getValue(reader, CHANGE_DATE);
                        if (isNotBlank(changeDate)) {
                            changeHistory.setChangeDate(Date.valueOf(changeDate));
                        }
                    }
                    break;

                case END_ELEMENT:

                    if (ITEM.equals(reader.getLocalName()) && changeHistory.getId() != null) {
                        changeHistoryList.add(changeHistory);
                    }

                    if (changeHistoryList.size() == BATCH_SIZE) {
                        consumer.accept(entityManager, changeHistoryList);
                        changeHistoryList.clear();
                    }
                    break;
            }
        }
        consumer.accept(entityManager, changeHistoryList);
        closeReader(reader);
        closeStream(inputStream);
    }
}