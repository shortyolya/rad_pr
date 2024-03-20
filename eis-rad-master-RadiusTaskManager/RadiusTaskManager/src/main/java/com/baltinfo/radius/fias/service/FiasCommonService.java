package com.baltinfo.radius.fias.service;

import javax.persistence.EntityManager;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public interface FiasCommonService {

    /**
     * Save new entities to database
     *
     * @param entityManager
     * @param fileName
     */
    void saveAll(EntityManager entityManager, String fileName) throws IOException, XMLStreamException;

    /**
     * Save or update entities to database
     *
     * @param entityManager
     * @param fileName
     */
    void saveOrUpdateAll(EntityManager entityManager, String fileName) throws IOException, XMLStreamException;
}