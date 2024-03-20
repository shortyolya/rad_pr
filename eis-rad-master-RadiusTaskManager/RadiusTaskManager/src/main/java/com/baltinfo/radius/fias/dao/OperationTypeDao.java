package com.baltinfo.radius.fias.dao;

import com.baltinfo.radius.fias.model.OperationType;

/**
 * @author Andrei Shymko
 * @since 11.10.2020
 */
public class OperationTypeDao extends FiasAbstractDao<OperationType> {
    public OperationTypeDao() {
        super(OperationType.class);
    }
}