package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.ObjectLevelDao;
import com.baltinfo.radius.fias.parser.impl.ObjectLevelParser;
import com.baltinfo.radius.fias.service.FiasAbstractService;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class ObjectLevelService extends FiasAbstractService<ObjectLevelDao, ObjectLevelParser> {

    public ObjectLevelService(ObjectLevelDao dao, ObjectLevelParser parser) {
        super(dao, parser);
    }
}