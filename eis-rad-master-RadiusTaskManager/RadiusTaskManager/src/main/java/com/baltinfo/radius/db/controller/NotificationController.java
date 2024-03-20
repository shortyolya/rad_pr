/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.constants.DocumStatus;
import com.baltinfo.radius.db.constants.TypeEventConstant;
import com.baltinfo.radius.db.model.Document;
import com.baltinfo.radius.db.model.Event;
import com.baltinfo.radius.db.model.MessageEmail;
import com.baltinfo.radius.db.model.NotifSettings;
import com.baltinfo.radius.db.model.Notification;
import com.baltinfo.radius.db.model.ParticipantAgent;
import com.baltinfo.radius.db.model.Subject;
import com.baltinfo.radius.db.model.TypeEvent;
import com.baltinfo.radius.notification.EmailTextService;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author taa
 */
public class NotificationController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
    private static final Long paUnid = 1L;
    private final EmailTextService emailTextService;

    public NotificationController(EmailTextService emailTextService) {
        this.emailTextService = emailTextService;
    }

    public void createDocumentFormNotification(Document document) {
        EntityManager em = null;
        Date now = new Date();
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();

            Event event = new Event();
            event.setDateB(now);
            event.setIndActual(1);
            event.setPersCodeB(paUnid);
            event.setEventDateB(now);
            event.setEventDateE(now);
            TypeEvent typeEvent = em.find(TypeEvent.class, TypeEventConstant.DOC_FORM.getUnid());
            event.setTevUnid(typeEvent.getTevUnid());
            event.setEventTheme(typeEvent.getTevName());
            em.persist(event);

            document.setEventUnid(event.getEventUnid());
            em.merge(document);

            ParticipantAgent pa = em.find(ParticipantAgent.class, document.getPaUnid());
            NotifSettings notifSettings = getNotifSettings(event.getTevUnid(), pa.getSubSubUnid().getSubUnid());

            Notification notification = new Notification();
            notification.setIndActual(1);
            notification.setDateB(now);
            notification.setPersCodeB(paUnid);
            notification.setSubUnid(pa.getSubSubUnid().getSubUnid());
            notification.setNtfIndExec(0);
            notification.setNtfIndEmail(notifSettings != null && notifSettings.getNsIndRemindEmail());
            notification.setNtfIndSystem(notifSettings == null || notifSettings.getNsIndRemindSystem());
            notification.setNtfTime(now);
            notification.setEventUnid(event);
            if (document.getDocumStatus().equals(DocumStatus.FORMATION_COMPLETED.getStatuseId())) {
                notification.setNtfText("По вашему запросу сформирован документ " + document.getDocumName());
            } else {
                notification.setNtfText("При формировании документа произошла ошибка.");
            }

            List<MessageEmail> messages = createEmailMessages(pa, notification, event.getEventTheme(), now);

            if (notifSettings != null) {
                if (notifSettings.getNsIndRemindSystem()) em.persist(notification);
                if (notifSettings.getNsIndRemindEmail()) messages.stream().forEach(em::persist);
            } else {
                em.persist(notification);
                messages.stream().forEach(em::persist);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            logger.error("Error in NotificationController.createDocumentFormNotification with docUnid = {}",
                    document.getDocumUnid(), ex);
            rollbackTransaction(em);
        } finally {
            closeEntityManager(em);
        }
    }


    public NotifSettings getNotifSettings(Long tevUnid, long subUnid) {
        NotifSettings nsRez;
        List<NotifSettings> notifSettings = getNotifSettingsBySubAndTevUnid(tevUnid, subUnid);
        if (notifSettings.isEmpty()) return null;

        nsRez = notifSettings.stream().filter(ns -> ns.getSubUnid() != null).findFirst().orElse(null);
        if (nsRez != null) return nsRez;

        nsRez = new NotifSettings();
        nsRez.setNsIndRemindSystem(0);
        nsRez.setNsIndRemindEmail(0);
        for (NotifSettings ns : notifSettings) {
            if (ns.getNsIndRemindEmail()) nsRez.setNsIndRemindEmail(1);
            if (ns.getNsIndRemindSystem()) nsRez.setNsIndRemindSystem(1);
        }

        return nsRez;
    }

    public List<NotifSettings> getNotifSettingsBySubAndTevUnid(Long tevUnid, Long subUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            List<NotifSettings> notifSettingsList = em.createQuery("select ns from NotifSettings ns " +
                            " where ns.indActual = 1 and ns.tevUnid.tevUnid = :tevUnid" +
                            " and ns.subUnid.subUnid = :subUnid")
                    .setHint("javax.persistence.fetchgraph", em.getEntityGraph("graph.NotifSettings.all"))
                    .setParameter("tevUnid", tevUnid)
                    .setParameter("subUnid", subUnid)
                    .getResultList();

            notifSettingsList.addAll(em.createQuery("select ns from NotifSettings ns, ParticipantAgent pa" +
                            " where ns.indActual = 1 and ns.tevUnid.tevUnid = :tevUnid" +
                            " and pa.tpaUnid = ns.tpaUnid.tpaUnid and pa.indActual = 1 and pa.subSubUnid.subUnid = :subUnid")
                    .setHint("javax.persistence.fetchgraph", em.getEntityGraph("graph.NotifSettings.all"))
                    .setParameter("tevUnid", tevUnid)
                    .setParameter("subUnid", subUnid)
                    .getResultList());

            notifSettingsList.addAll(em.createQuery("select ns from NotifSettings ns, PartAgentRole par" +
                            " where ns.indActual = 1 and ns.tevUnid.tevUnid = :tevUnid" +
                            " and par.tpaUnid = ns.tpaUnid.tpaUnid and par.indActual = 1 and par.paUnid.indActual = 1" +
                            " and par.paUnid.subSubUnid.subUnid = :subUnid")
                    .setHint("javax.persistence.fetchgraph", em.getEntityGraph("graph.NotifSettings.all"))
                    .setParameter("tevUnid", tevUnid)
                    .setParameter("subUnid", subUnid)
                    .getResultList());

            return notifSettingsList;
        } catch (Throwable ex) {
            logger.error("Error in getNotifSettingsBySubAndTevUnid by tevUnid={}, subUnid={}", tevUnid, subUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public boolean createNotification(Long typeEventUnid, Long paUnid, String theme, String text) {
        EntityManager em = null;
        Date now = new Date();
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();

            Event event = new Event();
            event.setDateB(now);
            event.setIndActual(1);
            event.setPersCodeB(paUnid);
            event.setEventDateB(now);
            event.setEventDateE(now);
            TypeEvent typeEvent = em.find(TypeEvent.class, typeEventUnid);
            event.setTevUnid(typeEvent.getTevUnid());
            event.setEventTheme(typeEvent.getTevName());
            em.persist(event);

            ParticipantAgent pa = em.find(ParticipantAgent.class, paUnid);
            NotifSettings notifSettings = getNotifSettings(event.getTevUnid(), pa.getSubSubUnid().getSubUnid());

            Notification notification = new Notification();
            notification.setIndActual(1);
            notification.setDateB(now);
            notification.setPersCodeB(paUnid);
            notification.setSubUnid(pa.getSubSubUnid().getSubUnid());
            notification.setNtfIndExec(0);
            notification.setNtfIndEmail(notifSettings == null || notifSettings.getNsIndRemindEmail());
            notification.setNtfIndSystem(notifSettings == null || notifSettings.getNsIndRemindSystem());
            notification.setNtfTime(now);
            notification.setEventUnid(event);
            notification.setNtfText(text);

            List<MessageEmail> messages = createEmailMessages(pa, notification, theme, now);

            if (notifSettings != null) {
                if (notifSettings.getNsIndRemindSystem()) em.persist(notification);
                if (notifSettings.getNsIndRemindEmail()) messages.stream().forEach(em::persist);
            } else {
                em.persist(notification);
                messages.stream().forEach(em::persist);
            }

            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            logger.error("Error createNotification typeEventUnid = {}, paUnid = {}",
                    typeEventUnid, paUnid, ex);
            rollbackTransaction(em);
        } finally {
            closeEntityManager(em);
        }
        return false;
    }

    private List<MessageEmail> createEmailMessages(ParticipantAgent pa, Notification notification, String theme, Date now) throws Exception {
        List<MessageEmail> messages = new ArrayList<>();
        Subject paSubject = pa.getSubSubUnid();
        List<String> emailArr = new ArrayList<>();
        StringBuilder emails = new StringBuilder(paSubject.getSubEMail());
        if (paSubject.getSubAdditionalEmail() != null && !paSubject.getSubAdditionalEmail().trim().isEmpty()) {
            emails.append(",").append(paSubject.getSubAdditionalEmail());
        }
        String email = emails.toString();
        if (email.contains(",")) {
            emailArr = Arrays.stream(email.split(",")).map(item -> item.trim()).collect(Collectors.toList());
        } else if (!email.trim().isEmpty()) {
            emailArr.add(email.trim());
        }
        String emailMessage = emailTextService.formEmailText(pa, notification.getNtfText());
        for (String emailItem : emailArr) {
            MessageEmail messageEmail = new MessageEmail(emailItem, theme,
                    emailMessage, notification.getNtfTime());
            messageEmail.setPersCodeB(paUnid);
            messageEmail.setIndActual(1);
            messageEmail.setDateB(now);
            messages.add(messageEmail);
        }
        return messages;
    }
}
