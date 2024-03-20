package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.RoomTypeDao;
import com.baltinfo.radius.fias.parser.impl.RoomTypeParser;
import com.baltinfo.radius.fias.service.FiasAbstractService;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class RoomTypeService extends FiasAbstractService<RoomTypeDao, RoomTypeParser> {

    public RoomTypeService(RoomTypeDao dao, RoomTypeParser parser) {
        super(dao, parser);
    }
}