
package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.constants.EntityConstant;
import com.baltinfo.radius.db.constants.OperType;
import com.baltinfo.radius.db.model.OperJournal;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.Date;

/**
 *
 * @author sas
 */
public class OperJournalController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(OperJournalController.class);

    private void createOperJournal(EntityManager em, long entityUnid, long instanceUnid, int operTypes, String name,
                                   String note, Long paUnid) {
        Date now = new Date();

        OperJournal oj = new OperJournal();
        oj.setDateB(now);
        oj.setPersCodeB(paUnid);
        oj.setIndActual(1L);
        oj.setPaUnid(paUnid);
        oj.setOjOperTime(now);
        oj.setEntityUnid(entityUnid);
        oj.setOjInstanceUnid(instanceUnid);
        oj.setOjOperType(operTypes);
        oj.setOjOperName(name);
        oj.setOjOperNote(note);
        em.persist(oj);
    }

    public void createOperJournalForObjectCreate(EntityManager em, long objUnid, long paUnid) {
        String str = "Создание объекта";
        createOperJournal(em, EntityConstant.OBJECT.getId(), objUnid, OperType.CREATE.getUnid(), str, str, paUnid);
    }

    public void createOperJournalForObjectExchange(EntityManager em, long objUnid, String note, long paUnid) {
        createOperJournal(em, EntityConstant.OBJECT.getId(), objUnid, OperType.OTHER.getUnid(), "Передача объекта", note, paUnid);
    }

    public void createOperJournalForObjectEdit(EntityManager em, long objUnid, String note, long paUnid) {
        createOperJournal(em, EntityConstant.OBJECT.getId(), objUnid, OperType.EDIT.getUnid(), "Изменение объекта", note, paUnid);
    }

    public void createOperJournalForAuctionCreate(EntityManager em, long auctionUnid, long paUnid) {
        String str = "Создание торгов";
        createOperJournal(em, EntityConstant.AUCTION.getId(), auctionUnid, OperType.CREATE.getUnid(), str, str, paUnid);
    }

    public void createOperJournalForAuctionExchange(EntityManager em, long auctionUnid, String note, long paUnid) {
        createOperJournal(em, EntityConstant.AUCTION.getId(), auctionUnid, OperType.OTHER.getUnid(), "Передача торгов", note, paUnid);
    }

    public void createOperJournalForLotCreate(EntityManager em, long lotUnid, long paUnid) {
        String str = "Создание лота";
        createOperJournal(em, EntityConstant.LOT.getId(), lotUnid, OperType.CREATE.getUnid(), str, str, paUnid);
    }

    public void createOperJournalForLotEdit(EntityManager em, long lotUnid, String note, long paUnid) {
        createOperJournal(em, EntityConstant.LOT.getId(), lotUnid, OperType.EDIT.getUnid(), "Изменение лота", note, paUnid);
    }

    public void createOperJournalForLotExchange(EntityManager em, long lotUnid, String note, long paUnid) {
        createOperJournal(em, EntityConstant.LOT.getId(), lotUnid, OperType.OTHER.getUnid(), "Передача лота", note, paUnid);
    }

    public void createOperJournalForAuctionEdit(EntityManager em, long auctionUnid, String note, long paUnid) {
        createOperJournal(em, EntityConstant.AUCTION.getId(), auctionUnid, OperType.EDIT.getUnid(), "Изменение торгов", note, paUnid);
    }

    public void createOperJournalForDealDelete(EntityManager em, long dealUnid, long paUnid) {
        String str = "Удаление договора";
        createOperJournal(em, EntityConstant.DEAL.getId(), dealUnid, OperType.DELETE.getUnid(), str, str, paUnid);
    }

    public void createOperJournalForDealCreate(EntityManager em, long dealUnid, long paUnid) {
        String str = "Создание договора";
        createOperJournal(em, EntityConstant.DEAL.getId(), dealUnid, OperType.CREATE.getUnid(), str, str, paUnid);
    }

    public void createOperJournalForLotExchange(long lotUnid, String note, long paUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            createOperJournalForLotExchange(em, lotUnid, note, paUnid);
            em.getTransaction().commit();
        } catch(Exception ex) {
            logger.error("Error create lot exchange operJournal. lotUnid = {}, note = {}, paUnid = {}", lotUnid, note, paUnid, ex);
            rollbackTransaction(em);
        } finally {
            closeEntityManager(em);
        }
    }
}
