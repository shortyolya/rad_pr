package com.baltinfo.radius.fias.dao;

import com.baltinfo.radius.fias.model.ApartmentType;

/**
 * @author Andrei Shymko
 * @since 11.10.2020
 */
public class ApartmentTypeDao extends FiasAbstractDao<ApartmentType> {
    public ApartmentTypeDao() {
        super(ApartmentType.class);
    }
}