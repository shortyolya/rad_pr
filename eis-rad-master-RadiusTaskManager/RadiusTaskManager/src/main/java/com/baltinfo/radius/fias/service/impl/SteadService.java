package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.SteadDao;
import com.baltinfo.radius.fias.parser.impl.SteadParser;
import com.baltinfo.radius.fias.service.FiasAbstractService;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class SteadService extends FiasAbstractService<SteadDao, SteadParser> {

    public SteadService(SteadDao dao, SteadParser parser) {
        super(dao, parser);
    }
}