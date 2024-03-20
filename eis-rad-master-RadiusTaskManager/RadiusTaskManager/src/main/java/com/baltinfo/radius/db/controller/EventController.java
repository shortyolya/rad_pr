package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.constants.TypeDocConstant;
import com.baltinfo.radius.db.constants.TypeEventConstant;
import com.baltinfo.radius.db.dao.EventDao;
import com.baltinfo.radius.db.model.Document;
import com.baltinfo.radius.db.model.Event;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 02.04.2020
 */
public class EventController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    private final EventDao eventDao;

    public EventController(EventDao eventDao) {
        this.eventDao = Objects.requireNonNull(eventDao, "Can't get eventDao");
    }

    public List<Document> getDocumentsWithoutEvent(TypeDocConstant typeDoc, TypeEventConstant typeEvent) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return eventDao.getDocumentsWithoutEventsByTypeDoc(em, typeDoc, typeEvent);
        } catch (Exception ex) {
            logger.error("Can't get Documents with tdUnid = {} without events with tevUnid = {}", typeDoc.getUnid(), typeEvent.getUnid(), ex);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }

    public void createEvent(Event event) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            eventDao.createEvent(em, event);
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Can't create event", e);
            rollbackTransaction(em);
        } finally {
            closeEntityManager(em);
        }
    }

    public void createEvents(List<Event> events) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            for (Event event : events) {
                eventDao.createEvent(em, event);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Can't create events", e);
            rollbackTransaction(em);
        } finally {
            closeEntityManager(em);
        }
    }
}
