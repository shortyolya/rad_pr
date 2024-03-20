package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.NdocKindDao;
import com.baltinfo.radius.fias.parser.impl.NdocKindParser;
import com.baltinfo.radius.fias.service.FiasAbstractService;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class NdocKindService extends FiasAbstractService<NdocKindDao, NdocKindParser> {

    public NdocKindService(NdocKindDao dao, NdocKindParser parser) {
        super(dao, parser);
    }
}