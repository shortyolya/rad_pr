package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.constants.TypeDocConstant;
import com.baltinfo.radius.db.constants.TypeEventConstant;
import com.baltinfo.radius.db.model.Document;
import com.baltinfo.radius.db.model.Event;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 26.11.2018
 */
public class EventDao extends AbstractDao {

    public Event createEvent(EntityManager em, Event event) {
        setNewHisory(new Date(), event);
        em.persist(event);
        return event;
    }

    public List<Document> getDocumentsWithoutEventsByTypeDoc(EntityManager em, TypeDocConstant typeDoc, TypeEventConstant typeEvent) {
        TypedQuery<Document> resultList = em.createQuery("select d" +
                " from Document d" +
                " where d.tdUnid.tdUnid = :tdUnid" +
                "  and d.indActual = 1" +
                "  and d.baUnid is not null" +
                "  and d.baUnid" +
                "    not in (" +
                "  select e.baUnid" +
                "  from Event e" +
                "   where e.baUnid is not null" +
                "     and e.indActual = 1" +
                "     and e.tevUnid = :tevUnid)", Document.class)
            .setParameter("tdUnid", typeDoc.getUnid())
            .setParameter("tevUnid", typeEvent.getUnid());
            return resultList.getResultList();
    }

}
