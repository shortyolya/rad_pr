package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.ObjDivisionDao;
import com.baltinfo.radius.fias.parser.impl.ObjDivisionParser;
import com.baltinfo.radius.fias.service.FiasAbstractService;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class ObjDivisionService extends FiasAbstractService<ObjDivisionDao, ObjDivisionParser> {

    public ObjDivisionService(ObjDivisionDao dao, ObjDivisionParser parser) {
        super(dao, parser);
    }
}