package com.baltinfo.radius.fias.service;

import com.baltinfo.radius.fias.dao.FiasAbstractDao;
import com.baltinfo.radius.fias.parser.FiasAbstractParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * Service for parsing and manage entities
 *
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public abstract class FiasAbstractService<D extends FiasAbstractDao, P extends FiasAbstractParser> implements FiasCommonService {

    private static final Logger logger = LoggerFactory.getLogger(FiasAbstractService.class);

    private final D dao;
    private final P parser;

    protected FiasAbstractService(D dao, P parser) {
        this.dao = dao;
        this.parser = parser;
    }

    @Override
    public void saveAll(EntityManager entityManager, String fileName) throws IOException, XMLStreamException {

        logger.debug("Saving parsed entities from file: {}", fileName);

        BiConsumer<EntityManager, List<?>> consumerSaveAll = dao::saveAll;
        parser.parse(entityManager, fileName, consumerSaveAll);
    }

    @Override
    public void saveOrUpdateAll(EntityManager entityManager, String fileName) throws IOException, XMLStreamException {

        logger.debug("Saving or updating parsed entities from file: {}", fileName);

        BiConsumer<EntityManager, List<?>> consumerSaveOrUpdateAll = dao::saveOrUpdateAll;
        parser.parse(entityManager, fileName, consumerSaveOrUpdateAll);
    }
}