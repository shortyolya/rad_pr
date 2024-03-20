package com.baltinfo.radius.fias.parser.impl;

import com.baltinfo.radius.fias.model.NdocKind;
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

public class NdocKindParser extends FiasAbstractParser {

    @Override
    public void parse(EntityManager entityManager, String path, BiConsumer<EntityManager, List<?>> consumer) throws IOException, XMLStreamException {

        NdocKind nDocKind = null;
        List<NdocKind> nDocKindList = new ArrayList<>();

        InputStream inputStream = Files.newInputStream(Paths.get(path));
        XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {

                case START_ELEMENT:
                    if (NDOC_KIND.equals(reader.getLocalName())) {
                        nDocKind = new NdocKind();

                        String id = getValue(reader, ID);
                        if (isNotBlank(id)) {
                            nDocKind.setId(Integer.valueOf(id));
                        }

                        String name = getValue(reader, NAME);
                        nDocKind.setName(name);
                    }
                    break;

                case END_ELEMENT:

                    if (NDOC_KIND.equals(reader.getLocalName()) && nDocKind.getId() != null) {
                        nDocKindList.add(nDocKind);
                    }

                    if (nDocKindList.size() == BATCH_SIZE) {
                        consumer.accept(entityManager, nDocKindList);
                        nDocKindList.clear();
                    }
                    break;
            }
        }
        consumer.accept(entityManager, nDocKindList);
        closeReader(reader);
        closeStream(inputStream);
    }
}