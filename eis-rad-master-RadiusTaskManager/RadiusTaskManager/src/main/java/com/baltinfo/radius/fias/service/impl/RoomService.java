package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.RoomDao;
import com.baltinfo.radius.fias.parser.impl.RoomParser;
import com.baltinfo.radius.fias.service.FiasAbstractService;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class RoomService extends FiasAbstractService<RoomDao, RoomParser> {

    public RoomService(RoomDao dao, RoomParser parser) {
        super(dao, parser);
    }
}