package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.constants.TypeSubject;
import com.baltinfo.radius.db.model.Account;
import com.baltinfo.radius.db.model.Address;
import com.baltinfo.radius.db.model.ParticipantAgent;
import com.baltinfo.radius.db.model.Subject;
import com.baltinfo.radius.db.model.SubjectHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 29.07.2021
 */
public class SubjectDao extends AbstractDao {
    private static final Logger logger = LoggerFactory.getLogger(SubjectDao.class);
    public SubjectHistory createSubjectFromSubjectHistory(EntityManager em, SubjectHistory sh, Long paUnid) {
        Date now = new Date();
        Subject sub = new Subject();
        fillSubject(sub, sh);
        setNewHisory(paUnid, now, sub);
        em.persist(sub);

        ParticipantAgent chief = extractPaChief(sh);
        if (chief != null) {
            Subject chiefSub = chief.getSubSubUnid();
            setNewHisory(paUnid, now, chiefSub);
            em.persist(chiefSub);

            setNewHisory(paUnid, now, chief);
            em.persist(chief);
        }
        Account account = extractAccount(sh);
        if (account != null) {
            account.setSubUnid(sub.getSubUnid());
            setNewHisory(paUnid, now, account);
            em.persist(account);
        }

        setNewHisory(paUnid, now, sh);
        sh.setSubUnid(sub.getSubUnid());
        em.persist(sh);
        return sh;
    }

    public SubjectHistory createSubjectHistory(EntityManager em, SubjectHistory sh, Long paUnid) {
        Date now = new Date();
        setNewHisory(paUnid, now, sh);
        em.persist(sh);
        return sh;
    }

    public Subject updateSubject(EntityManager em, Subject subject, Long paUnid) {
        Date now = new Date();
        setRecHisory(paUnid, now, subject);
        em.merge(subject);
        return subject;
    }

    private void fillSubject(Subject subject, SubjectHistory subjectHistory) {
        subject.setTypesUnid(subjectHistory.getTypesUnid());
        subject.setSubName(subjectHistory.getShSubName());
        subject.setSubSname(subjectHistory.getShSubSname());
        subject.setSubNameF(subjectHistory.getShSubNameF());
        subject.setSubNameI(subjectHistory.getShSubNameI());
        subject.setSubNameO(subjectHistory.getShSubNameO());
        subject.setSubSex(subjectHistory.getShSubSex());
        subject.setSubBirthday(subjectHistory.getShSubBirthday());
        subject.setSubBirthplace(subjectHistory.getShSubBirthplace());
        subject.setSubRegOrgan(subjectHistory.getShSubRegOrgan());
        subject.setSubRegNumber(subjectHistory.getShSubRegNumber());
        subject.setSubRegDate(subjectHistory.getShSubRegDate());
        subject.setSubDocumName(subjectHistory.getShSubDocumName());
        subject.setSubDocumNumber(subjectHistory.getShSubDocumNumber());
        subject.setSubDocumSeries(subjectHistory.getShSubDocumSeries());
        subject.setSubDocumIssueDate(subjectHistory.getShSubDocumIssueDate());
        subject.setSubDocumIssueAgency(subjectHistory.getShSubDocumIssueAgency());
        subject.setSubOkpo(subjectHistory.getShSubOkpo());
        subject.setSubInn(subjectHistory.getShSubInn());
        subject.setSubAddrLegal(subjectHistory.getShSubAddrLegal());
        subject.setSubAddrFact(subjectHistory.getShSubAddrFact());
        subject.setSubPhone(subjectHistory.getShSubPhone());
        subject.setSubFax(subjectHistory.getShSubFax());
        subject.setSubEMail(subjectHistory.getShSubEMail());
        subject.setSubWeb(subjectHistory.getShSubWeb());
        subject.setSubCodeKpp(subjectHistory.getShSubCodeKpp());
        subject.setSubOgrn(subjectHistory.getShSubOgrn());
        subject.setSubSnils(subjectHistory.getShSubSnils());
        subject.setSubOkato(subjectHistory.getShSubOkato());
        subject.setSubRegEditDate(subjectHistory.getShSubRegEditDate());
        subject.setSubRegEditDoc(subjectHistory.getShSubRegEditDoc());
        subject.setSubRegEditNum(subjectHistory.getShSubRegEditNum());
        subject.setSubRegEditOrgan(subjectHistory.getShSubRegEditOrgan());
        subject.setSubMobilePhone(subjectHistory.getShSubMobilePhone());
        subject.setSubAddrLegalFiasId(subjectHistory.getShSubAddrLegalFiasId());
        subject.setSubAddrFactFiasId(subjectHistory.getShSubAddrFactFiasId());
    }

    private Account extractAccount(SubjectHistory subjectHistory) {
        if (subjectHistory.getShSubAccountCode() != null && !subjectHistory.getShSubAccountCode().isEmpty()) {
            Account account = new Account();
            account.setAccName(subjectHistory.getShSubAccountName());
            account.setAccCode(subjectHistory.getShSubAccountCode());
            account.setAccBankName(subjectHistory.getShSubBankName());
            account.setAccBankAddr(subjectHistory.getShSubBankAddr());
            account.setAccBankBik(subjectHistory.getShSubBankBic());
            account.setAccBankAccount(subjectHistory.getShSubBankAccount());
            account.setAccBankInn(subjectHistory.getShSubBankInn());
            account.setAccBankKpp(subjectHistory.getShSubBankKpp());

            return account;
        } else {
            return null;
        }
    }

    private ParticipantAgent extractPaChief(SubjectHistory subjectHistory) {
        if (subjectHistory.getShSubManF() != null && !subjectHistory.getShSubManF().isEmpty()) {
            ParticipantAgent pa = new ParticipantAgent();

            pa.setDlaUnid(subjectHistory.getDlaUnid());
            pa.setPaDlaDateFound(subjectHistory.getShSubManBase());
            pa.setSubSubUnid(extractSubChief(subjectHistory));
            pa.setPaIndMan(true);
            return pa;
        } else {
            return null;
        }
    }

    private static Subject extractSubChief(SubjectHistory subjectHistory) {
        Subject sub = new Subject();
        sub.setTypesUnid(TypeSubject.FL.getUnid());
        String name = subjectHistory.getShSubManF();
        String sname = subjectHistory.getShSubManF();
        if (subjectHistory.getShSubManI() != null && !subjectHistory.getShSubManI().isEmpty()) {
            name += " " + subjectHistory.getShSubManI();
            sname += " " + subjectHistory.getShSubManI().substring(0, 1) + ".";
        }
        if (subjectHistory.getShSubManO() != null && !subjectHistory.getShSubManO().isEmpty()) {
            name += " " + subjectHistory.getShSubManO();
            sname += " " + subjectHistory.getShSubManO().substring(0, 1) + ".";
        }
        sub.setSubName(name.trim());
        sub.setSubSname(sname.trim());
        sub.setSubNameF(subjectHistory.getShSubManF());
        sub.setSubNameI(subjectHistory.getShSubManI());
        sub.setSubNameO(subjectHistory.getShSubManO());
        sub.setSubSex(subjectHistory.getShSubManSex());

        return sub;
    }

    public boolean isOwnerKio(EntityManager em, Long objUnid) {
        try{
            List<Long> subjects = em.createQuery(
                            "select s.subUnid " +
                                    " from Subject s," +
                                    "      ObjectJPA o " +
                                    "where s.subUnid = o.subUnid " +
                                    "  and o.objUnid = :objUnid " +
                                    "  and s.subInn = :inn " +
                                    "  and s.indActual = 1 " +
                                    "  and o.indActual = 1")
                    .setParameter("objUnid", objUnid)
                    .setParameter("inn", Subject.KIO_INN)
                    .getResultList();
            if (!subjects.isEmpty()) {
                return true;
            }
        } catch (RuntimeException ex) {
            logger.warn("Error when ger subject KIO by obj id", ex.getMessage());
        }
        return false;
    }

}
