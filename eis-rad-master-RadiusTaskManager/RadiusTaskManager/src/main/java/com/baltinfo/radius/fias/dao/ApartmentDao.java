package com.baltinfo.radius.fias.dao;

import com.baltinfo.radius.fias.model.Apartment;

/**
 * @author Andrei Shymko
 * @since 11.10.2020
 */
public class ApartmentDao extends FiasAbstractDao<Apartment> {
    public ApartmentDao() {
        super(Apartment.class);
    }
}