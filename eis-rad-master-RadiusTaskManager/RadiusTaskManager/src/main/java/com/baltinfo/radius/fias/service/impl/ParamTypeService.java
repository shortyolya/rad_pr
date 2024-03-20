package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.ParamTypeDao;
import com.baltinfo.radius.fias.parser.impl.ParamTypeParser;
import com.baltinfo.radius.fias.service.FiasAbstractService;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class ParamTypeService extends FiasAbstractService<ParamTypeDao, ParamTypeParser> {

    public ParamTypeService(ParamTypeDao dao, ParamTypeParser parser) {
        super(dao, parser);
    }
}