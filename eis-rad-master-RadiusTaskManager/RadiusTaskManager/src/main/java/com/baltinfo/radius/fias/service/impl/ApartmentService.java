package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.ApartmentDao;
import com.baltinfo.radius.fias.parser.impl.ApartmentParser;
import com.baltinfo.radius.fias.service.FiasAbstractService;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class ApartmentService extends FiasAbstractService<ApartmentDao, ApartmentParser> {

    public ApartmentService(ApartmentDao dao, ApartmentParser parser) {
        super(dao, parser);
    }
}