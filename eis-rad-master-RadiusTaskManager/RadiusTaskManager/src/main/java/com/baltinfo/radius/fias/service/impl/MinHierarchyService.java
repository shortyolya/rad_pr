package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.MinHierarchyDao;
import com.baltinfo.radius.fias.parser.impl.MinHierarchyParser;
import com.baltinfo.radius.fias.service.FiasAbstractService;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class MinHierarchyService extends FiasAbstractService<MinHierarchyDao, MinHierarchyParser> {

    public MinHierarchyService(MinHierarchyDao dao, MinHierarchyParser parser) {
        super(dao, parser);
    }
}