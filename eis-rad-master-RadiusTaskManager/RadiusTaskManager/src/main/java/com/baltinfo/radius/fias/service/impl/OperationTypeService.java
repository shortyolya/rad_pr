package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.OperationTypeDao;
import com.baltinfo.radius.fias.parser.impl.OperationTypeParser;
import com.baltinfo.radius.fias.service.FiasAbstractService;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class OperationTypeService extends FiasAbstractService<OperationTypeDao, OperationTypeParser> {

    public OperationTypeService(OperationTypeDao dao, OperationTypeParser parser) {
        super(dao, parser);
    }
}