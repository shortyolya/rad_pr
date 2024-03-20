package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.HierarchyDao;
import com.baltinfo.radius.fias.parser.impl.HierarchyParser;
import com.baltinfo.radius.fias.service.FiasAbstractService;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class HierarchyService extends FiasAbstractService<HierarchyDao, HierarchyParser> {

    public HierarchyService(HierarchyDao dao, HierarchyParser parser) {
        super(dao, parser);
    }
}