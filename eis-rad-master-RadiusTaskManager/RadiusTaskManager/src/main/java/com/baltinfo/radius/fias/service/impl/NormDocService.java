package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.NormDocDao;
import com.baltinfo.radius.fias.parser.impl.NormDocParser;
import com.baltinfo.radius.fias.service.FiasAbstractService;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class NormDocService extends FiasAbstractService<NormDocDao, NormDocParser> {

    public NormDocService(NormDocDao dao, NormDocParser parser) {
        super(dao, parser);
    }
}