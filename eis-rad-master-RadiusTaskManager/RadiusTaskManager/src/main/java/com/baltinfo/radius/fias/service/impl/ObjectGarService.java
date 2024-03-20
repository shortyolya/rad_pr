package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.ObjectGarDao;
import com.baltinfo.radius.fias.parser.impl.ObjectGarParser;
import com.baltinfo.radius.fias.service.FiasAbstractService;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class ObjectGarService extends FiasAbstractService<ObjectGarDao, ObjectGarParser> {

    public ObjectGarService(ObjectGarDao dao, ObjectGarParser parser) {
        super(dao, parser);
    }
}