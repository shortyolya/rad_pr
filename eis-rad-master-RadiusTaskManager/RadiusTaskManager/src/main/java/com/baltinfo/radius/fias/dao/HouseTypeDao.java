package com.baltinfo.radius.fias.dao;

import com.baltinfo.radius.fias.model.HouseType;

/**
 * @author Andrei Shymko
 * @since 11.10.2020
 */
public class HouseTypeDao extends FiasAbstractDao<HouseType> {
    public HouseTypeDao() {
        super(HouseType.class);
    }
}