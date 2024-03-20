package com.baltinfo.radius.fias.service.impl;

import com.baltinfo.radius.fias.dao.ChangeHistoryDao;
import com.baltinfo.radius.fias.parser.impl.ChangeHistoryParser;
import com.baltinfo.radius.fias.service.FiasAbstractService;

/**
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class ChangeHistoryService extends FiasAbstractService<ChangeHistoryDao, ChangeHistoryParser> {

    public ChangeHistoryService(ChangeHistoryDao dao, ChangeHistoryParser parser) {
        super(dao, parser);
    }
}