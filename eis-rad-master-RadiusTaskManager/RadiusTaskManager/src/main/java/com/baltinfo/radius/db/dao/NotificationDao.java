package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.model.Notification;

import javax.persistence.EntityManager;
import java.util.Date;

/**
 * @author Suvorina Aleksandra
 * @since 26.11.2018
 */
public class NotificationDao extends AbstractDao {

    public Notification createNotification(EntityManager em, Notification notification) {
        setNewHisory(new Date(), notification);
        em.persist(notification);
        return notification;
    }
}
