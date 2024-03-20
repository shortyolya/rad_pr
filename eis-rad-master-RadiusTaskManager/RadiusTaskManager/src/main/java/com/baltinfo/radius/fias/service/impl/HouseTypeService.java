package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.HouseTypeDao;
import com.baltinfo.radius.fias.parser.impl.HouseTypeParser;
import com.baltinfo.radius.fias.service.FiasAbstractService;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class HouseTypeService extends FiasAbstractService<HouseTypeDao, HouseTypeParser> {

    public HouseTypeService(HouseTypeDao dao, HouseTypeParser parser) {
        super(dao, parser);
    }
}