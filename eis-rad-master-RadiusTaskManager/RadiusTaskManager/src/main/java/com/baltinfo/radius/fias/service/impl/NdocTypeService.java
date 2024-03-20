package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.NdocTypeDao;
import com.baltinfo.radius.fias.parser.impl.NdocTypeParser;
import com.baltinfo.radius.fias.service.FiasAbstractService;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class NdocTypeService extends FiasAbstractService<NdocTypeDao, NdocTypeParser> {

    public NdocTypeService(NdocTypeDao dao, NdocTypeParser parser) {
        super(dao, parser);
    }
}