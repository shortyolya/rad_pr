package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.CarPlaceDao;
import com.baltinfo.radius.fias.parser.impl.CarPlaceParser;
import com.baltinfo.radius.fias.service.FiasAbstractService;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class CarPlaceService extends FiasAbstractService<CarPlaceDao, CarPlaceParser> {

    public CarPlaceService(CarPlaceDao dao, CarPlaceParser parser) {
        super(dao, parser);
    }
}