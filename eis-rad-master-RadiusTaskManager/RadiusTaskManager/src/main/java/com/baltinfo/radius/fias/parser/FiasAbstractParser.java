package com.baltinfo.radius.fias.parser;

import com.baltinfo.radius.fias.service.FiasAbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.BiConsumer;

public abstract class FiasAbstractParser {

    private static final Logger logger = LoggerFactory.getLogger(FiasAbstractService.class);

    protected XMLInputFactory inputFactory = XMLInputFactory.newInstance();

    protected String getValue(XMLStreamReader reader, String attribute) {
        return reader.getAttributeValue(null, attribute);
    }

    protected Boolean isNotBlank(String value) {
        return value != null && value.trim().length() > 0;
    }

    protected void closeReader(XMLStreamReader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (XMLStreamException e) {
                logger.error("XMLStreamReader is not closed", e);
            }
        }
    }

    protected void closeStream(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.error("InputStream is not closed", e);
            }
        }
    }

    public abstract void parse(EntityManager entityManager, String path, BiConsumer<EntityManager, List<?>> consumer) throws IOException, XMLStreamException;
}