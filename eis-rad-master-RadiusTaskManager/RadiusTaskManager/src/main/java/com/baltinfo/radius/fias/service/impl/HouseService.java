package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.HouseDao;
import com.baltinfo.radius.fias.parser.impl.HouseParser;
import com.baltinfo.radius.fias.service.FiasAbstractService;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class HouseService extends FiasAbstractService<HouseDao, HouseParser> {

    public HouseService(HouseDao dao, HouseParser parser) {
        super(dao, parser);
    }
}