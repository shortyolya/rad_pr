package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.AddressObjectTypeDao;
import com.baltinfo.radius.fias.parser.impl.AddressObjectTypeParser;
import com.baltinfo.radius.fias.service.FiasAbstractService;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class AddressObjectTypeService extends FiasAbstractService<AddressObjectTypeDao, AddressObjectTypeParser> {

    public AddressObjectTypeService(AddressObjectTypeDao dao, AddressObjectTypeParser parser) {
        super(dao, parser);
    }
}