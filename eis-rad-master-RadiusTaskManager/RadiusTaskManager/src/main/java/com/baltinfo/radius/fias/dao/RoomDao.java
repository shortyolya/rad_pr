package com.baltinfo.radius.fias.dao;

import com.baltinfo.radius.fias.model.Room;

/**
 * @author Andrei Shymko
 * @since 11.10.2020
 */
public class RoomDao extends FiasAbstractDao<Room> {
    public RoomDao() {
        super(Room.class);
    }
}