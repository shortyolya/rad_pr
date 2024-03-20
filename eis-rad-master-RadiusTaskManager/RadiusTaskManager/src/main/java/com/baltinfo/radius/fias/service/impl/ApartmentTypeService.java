package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.ApartmentTypeDao;
import com.baltinfo.radius.fias.parser.impl.ApartmentTypeParser;
import com.baltinfo.radius.fias.service.FiasAbstractService;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class ApartmentTypeService extends FiasAbstractService<ApartmentTypeDao, ApartmentTypeParser> {

    public ApartmentTypeService(ApartmentTypeDao dao, ApartmentTypeParser parser) {
        super(dao, parser);
    }
}