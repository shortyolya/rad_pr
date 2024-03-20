package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.ParamDao;
import com.baltinfo.radius.fias.parser.impl.ParamParser;
import com.baltinfo.radius.fias.service.FiasAbstractService;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class ParamService extends FiasAbstractService<ParamDao, ParamParser> {

    public ParamService(ParamDao dao, ParamParser parser) {
        super(dao, parser);
    }
}