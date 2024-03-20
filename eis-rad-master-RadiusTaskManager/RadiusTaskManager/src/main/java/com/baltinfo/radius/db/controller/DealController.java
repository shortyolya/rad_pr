package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.constants.RolePartyDealConstant;
import com.baltinfo.radius.db.constants.TypeAuctionCode;
import com.baltinfo.radius.db.constants.TypeCostConstant;
import com.baltinfo.radius.db.constants.TypeDealClassConstants;
import com.baltinfo.radius.db.constants.TypeDealConstants;
import com.baltinfo.radius.db.constants.TypeStateConstant;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.Deal;
import com.baltinfo.radius.db.model.DealObject;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.ObjCost;
import com.baltinfo.radius.db.model.PartyDeal;
import com.baltinfo.radius.db.model.Subject;
import com.baltinfo.radius.db.model.SubjectHistory;
import com.baltinfo.radius.db.model.TypeDeal;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Date;
import java.util.Optional;

/**
 * @author Suvorina Aleksandra
 * @since 13.01.2020
 */
public class DealController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(DealController.class);

    private static final String RU_POST_INN = "7724261610";
    public static final Long DEFAULT_PERS_CODE = 1L;

    public DealController() {
    }

    public Result<Deal, String> createSaleDeal(EntityManager em, Long tpaUnid, Long paUnid, Long objUnid,
                                               ObjCost futureSaleCost, SubjectHistory participant) {
        try {
            Date now = new Date();
            Deal saleDeal = new Deal();
            saleDeal.setTdealUnid(TypeDealConstants.SALE_DEAL.getId());
            saleDeal.setTsUnid(TypeStateConstant.DEAL_PREPARATION.getId());
            saleDeal.setTpaUnid(tpaUnid);
            saleDeal.setIndActual(1);
            saleDeal.setDateB(now);
            saleDeal.setPersCodeB(paUnid);
            em.persist(saleDeal);

            if (futureSaleCost != null) {
                ObjCost saleCost = new ObjCost();
                saleCost.setCostValue(futureSaleCost.getCostValue());
                saleCost.setCostValueRub(futureSaleCost.getCostValueRub());
                saleCost.setCostIndTax(futureSaleCost.getCostIndTax());
                saleCost.setCostTaxProc(futureSaleCost.getCostTaxProc());
                saleCost.setCostTax(futureSaleCost.getCostTax());
                saleCost.setCurUnid(futureSaleCost.getCurUnid());
                saleCost.setCostIndCurrent(1);
                saleCost.setDealUnid(saleDeal.getDealUnid());
                saleCost.setObjUnid(futureSaleCost.getObjUnid());
                saleCost.setTypeCosUnid(TypeCostConstant.SALE.getId());

                saleCost.setIndActual(1);
                saleCost.setDateB(now);
                saleCost.setPersCodeB(paUnid);
                em.persist(saleCost);

                em.createNativeQuery("update web.obj_cost set cost_ind_current = 0, date_b_rec = :now, pers_code_b_rec = :paUnid"
                                + " where ind_actual = 1 and cost_ind_current = 1 and obj_unid = :objUnid")
                        .setParameter("objUnid", futureSaleCost.getObjUnid().getObjUnid())
                        .setParameter("paUnid", paUnid)
                        .setParameter("now", now)
                        .executeUpdate();
            }

            if (participant != null) {
                SubjectHistory buyerSh = new SubjectHistory(participant);
                buyerSh.setSubShUnid(participant.getShUnid());
                buyerSh.setIndActual(1);
                buyerSh.setDateB(now);
                buyerSh.setPersCodeB(paUnid);
                em.persist(buyerSh);

                PartyDeal partyDeal = new PartyDeal();
                partyDeal.setRpdUnid(RolePartyDealConstant.BUYER.getId());

                partyDeal.setShUnid(buyerSh);
                partyDeal.setSubUnid(buyerSh.getSubUnid());
                partyDeal.setDealUnid(saleDeal.getDealUnid());

                partyDeal.setIndActual(1);
                partyDeal.setDateB(now);
                partyDeal.setPersCodeB(paUnid);
                em.persist(partyDeal);
            }

            DealObject dealObject = new DealObject();
            dealObject.setDealUnid(saleDeal);
            dealObject.setObjUnid(objUnid);
            dealObject.setIndActual(1L);
            dealObject.setDateB(now);
            dealObject.setPersCodeB(paUnid);
            em.persist(dealObject);
            return Result.ok(saleDeal);
        } catch (Exception ex) {
            logger.error("Error createSaleDeal", ex);
            return Result.error("Ошибка при создании договора купли-продажи: " + ex.getMessage());
        }
    }

    public Result<Deal, String> createDeal(EntityManager em, Long tpaUnid, Long objUnid, String llTzNum, Date llTzDate, Deal curDeal) {
        try {
            Date now = new Date();

            Deal deal = getAssignmentDeal(em, curDeal, llTzNum, llTzDate);
            if (deal == null) {
                deal = new Deal();
                deal.setTdealUnid(TypeDealConstants.ASSIGNMENT_DEAL.getId());
                deal.setTsUnid(TypeStateConstant.DEAL_PREPARATION.getId());
                deal.setTpaUnid(tpaUnid);
                deal.setIndActual(1);
                deal.setDateB(now);
                deal.setPersCodeB(DEFAULT_PERS_CODE);
                deal.setDealNum(llTzNum);
                deal.setDealDate(llTzDate);
                deal.setDeaDealUnid(curDeal);
                em.persist(deal);
            }


            DealObject dealObject = new DealObject();
            dealObject.setDealUnid(deal);
            dealObject.setObjUnid(objUnid);
            dealObject.setIndActual(1L);
            dealObject.setDateB(now);
            dealObject.setPersCodeB(DEFAULT_PERS_CODE);
            em.persist(dealObject);
            return Result.ok(deal);
        } catch (Exception ex) {
            logger.error("Error createDeal", ex);
            return Result.error("Ошибка при создании договора поручения : " + ex.getMessage());
        }
    }

    public Optional<Deal> getSaleDeal(EntityManager em, Long objUnid) {
        try {
            Deal deal = (Deal) em.createQuery("SELECT d FROM Deal d, DealObject do, TypeDeal tdeal " +
                            " where d.indActual = 1 " +
                            "and do.indActual = 1 " +
                            "and d.tdealUnid = tdeal.tdealUnid " +
                            "and tdeal.tdealClass = :tdealClass " +
                            "and do.dealUnid = d " +
                            "and do.objUnid = :objUnid")
                    .setParameter("tdealClass", TypeDealClassConstants.CLASS_TRADE.getId())
                    .setParameter("objUnid", objUnid)
                    .getSingleResult();
            return Optional.of(deal);
        } catch (NoResultException ex) {
            logger.warn("Sale deal not found by objUnid = {}", objUnid);
            return Optional.empty();
        }
    }

    public Optional<Deal> getCommissionDeal(EntityManager em, Long objUnid) {
        try {
            Deal deal = (Deal) em.createQuery("select deal from DealObject as do, Deal as deal, TypeDeal td " +
                            "left join fetch deal.deaDealUnid dea " +
                            "left join fetch deal.afUnid " +
                            "left join fetch dea.afUnid " +
                            "where do.indActual = 1 " +
                            "and deal.indActual = 1 " +
                            "and do.dealUnid.tdealUnid = td.tdealUnid " +
                            "and td.tdealClass in (2, 4) " +
                            "and deal = do.dealUnid " +
                            "and do.objUnid = :objUnid " +
                            "order by deal.dateB desc")
                    .setParameter("objUnid", objUnid)
                    .getSingleResult();

            return Optional.of(deal);
        } catch (NoResultException ex) {
            logger.warn("Commission deal not found by objUnid = {}", objUnid);
            return Optional.empty();
        }
    }

    public Deal getAssignmentDeal(EntityManager em, Deal dealUnid, String dealNum, Date dealDate) {
        try {
            Deal deal = (Deal) em.createQuery("select deal from Deal as deal, TypeDeal td " +
                            "where deal.indActual = 1 " +
                            "and deal.tdealUnid = td.tdealUnid " +
                            "and td.tdealClass = 4 " +
                            "and deal.deaDealUnid = :deaDealUnid " +
                            "and trim(lower(deal.dealNum)) like :dealNum " +
                            "and deal.dealDate = :dealDate " +
                            "order by deal.dateB desc")
                    .setParameter("deaDealUnid", dealUnid)
                    .setParameter("dealNum", dealNum.toLowerCase().trim())
                    .setParameter("dealDate", dealDate)
                    .setMaxResults(1)
                    .getSingleResult();

            return deal;
        } catch (NoResultException ex) {
            logger.warn("Assignment Deal not found by dealUnid = {},dealNum = {} ", dealUnid.getDealUnid(), dealNum);
            return null;
        }
    }

    public boolean checkMailAct(EntityManager em, Lot lot, Auction eisAuction) {
        try {
            if (!eisAuction.getTypeAuctionUnid().getTypeAuctionUnid().equals(TypeAuctionCode.HOLLAND.getCode()))
                return false;

            Deal deal = (Deal) em.createQuery("select deal from DealObject do, Deal deal " +
                            "left join fetch deal.deaDealUnid " +
                            "where do.indActual = 1 " +
                            "and deal.indActual = 1 " +
                            "and deal.tdealUnid = 4 " +
                            "and deal = do.dealUnid " +
                            "and do.objUnid = :objUnid " +
                            "order by deal.dateB desc")
                    .setParameter("objUnid", lot.getObjUnid().getObjUnid())
                    .getSingleResult();
            if (deal == null) return false;

            PartyDeal partyDeal = (PartyDeal) em.createQuery("select pd from PartyDeal pd" +
                            " where pd.indActual = 1" +
                            " and pd.rpdUnid = :rpdUnid" +
                            " and pd.dealUnid = :dealUnid")
                    .setParameter("dealUnid", deal.getDeaDealUnid().getDealUnid())
                    .setParameter("rpdUnid", RolePartyDealConstant.CUSTOMER.getId())
                    .getSingleResult();
            if (partyDeal == null) return false;

            Subject subject = em.find(Subject.class, partyDeal.getSubUnid());
            if (!subject.getSubInn().equals(RU_POST_INN)) return false;

            return true;
        } catch (NoResultException ex) {
            logger.warn("checkMailAct fail by objUnid = {}");
            return false;
        }
    }

    public Result<Void, String> deleteSaleDeal(EntityManager em, Deal deal, Long paUnid) {
        try {
            deal.setPersCodeBRec(paUnid);
            deal.setIndActual(0);
            deal.setDateBRec(new Date());
            em.merge(deal);
            em.createNativeQuery("update web.obj_cost set ind_actual = 0, date_b_rec = now(), pers_code_b_rec = :paUnid "
                            + "where deal_unid = :dealUnid and ind_actual = 1")
                    .setParameter("paUnid", paUnid)
                    .setParameter("dealUnid", deal.getDealUnid())
                    .executeUpdate();

            em.createNativeQuery("update web.deal_object set ind_actual = 0, date_b_rec = now(), pers_code_b_rec = :paUnid "
                            + "where deal_unid = :dealUnid and ind_actual = 1")
                    .setParameter("paUnid", paUnid)
                    .setParameter("dealUnid", deal.getDealUnid())
                    .executeUpdate();

            em.createNativeQuery("update web.party_deal set ind_actual = 0, date_b_rec = now(), pers_code_b_rec = :paUnid "
                            + "where deal_unid = :dealUnid and ind_actual = 1")
                    .setParameter("paUnid", paUnid)
                    .setParameter("dealUnid", deal.getDealUnid())
                    .executeUpdate();

            return Result.ok();

        } catch (Throwable ex) {
            logger.error("Error deleteDeal", ex);
            return Result.error("Ошибка при удалении договора купли-продажи: " + ex.getMessage());
        }
    }

    public Deal getDeal(EntityManager em, long dealUnid) {
        try {
            return em.createNamedQuery("Deal.findByDealUnid", Deal.class)
                    .setParameter("dealUnid", dealUnid)
                    .getSingleResult();
        } catch (Throwable ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    public TypeDeal getTypeDealByUnid(EntityManager em, long tdUnid) {
        try {
            return em.find(TypeDeal.class, tdUnid);
        } catch (Throwable ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }
}
