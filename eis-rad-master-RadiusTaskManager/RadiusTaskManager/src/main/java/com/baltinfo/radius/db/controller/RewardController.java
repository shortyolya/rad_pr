package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.constants.ActFoundationConstant;
import com.baltinfo.radius.db.constants.LotStatus;
import com.baltinfo.radius.db.constants.RewardFormulasCode;
import com.baltinfo.radius.db.constants.RewardTypeConstant;
import com.baltinfo.radius.db.constants.SaleGuideTrade;
import com.baltinfo.radius.db.constants.TpaDivision;
import com.baltinfo.radius.db.constants.TypeActConstant;
import com.baltinfo.radius.db.constants.TypeAuctionCode;
import com.baltinfo.radius.db.constants.TypeAuctionUnid;
import com.baltinfo.radius.db.constants.TypeStateConstant;
import com.baltinfo.radius.db.dao.ActDao;
import com.baltinfo.radius.db.dao.RewardDao;
import com.baltinfo.radius.db.model.Act;
import com.baltinfo.radius.db.model.ActFoundation;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.Deal;
import com.baltinfo.radius.db.model.Declarant;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.ObjectJPA;
import com.baltinfo.radius.db.model.Reward;
import com.baltinfo.radius.db.model.RewardFormula;
import com.baltinfo.radius.db.model.VAct;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Suvorina Aleksandra
 * @since 18.11.2020
 */
public class RewardController extends AbstractController {

    private static Logger logger = LoggerFactory.getLogger(RewardController.class);

    private static final BigDecimal ASV_REWARD_MAN_PROC = new BigDecimal(55);
    private static final BigDecimal ASV_REWARD_PROC = new BigDecimal(45);
    private static final BigDecimal NON_ASV_REWARD_PROC = new BigDecimal(100);

    private final RewardDao rewardDao;
    private final ActDao actDao;
    private final DealController dealController;

    public RewardController(RewardDao rewardDao, ActDao actDao, DealController dealController) {
        this.rewardDao = rewardDao;
        this.actDao = actDao;
        this.dealController = dealController;
    }

    public Result<Void, String> createPlanReward(EntityManager emEis, ObjectJPA object) {
        Long objUnid = object.getObjUnid();
        try {
            Optional<Deal> dealResult = dealController.getCommissionDeal(emEis, objUnid);
            if (!dealResult.isPresent()) {
                return Result.error("Отсутствует договор.");
            }
            Deal deal = dealResult.get();

            RewardFormula rewardFormula = null;
            if (object.getSgtUnid() != null && object.getSgtUnid().equals(SaleGuideTrade.ASV)) {
                rewardFormula = getRewardFormulaByRfCode(emEis, RewardFormulasCode.RF_ASV);
            } else if (deal.getRfOwnerUnid() != null) {
                rewardFormula = getRewardFormulaByUnid(emEis, deal.getRfOwnerUnid());
            } else {
                if (deal.getDeaDealUnid() != null && deal.getDeaDealUnid().getRfOwnerUnid() != null) {
                    rewardFormula = getRewardFormulaByUnid(emEis, deal.getDeaDealUnid().getRfOwnerUnid());
                }
            }
            if (rewardFormula == null) {
                return Result.error("Отсутствует формула расчета вознаграждения для собственника.");
            }
            Reward planReward;
            try {
                planReward = (Reward) emEis.createQuery("select r from Reward r " +
                                " where r.indActual = 1 and r.rewardType = :rewardType and r.objUnid = :objUnid ")
                        .setParameter("rewardType", RewardTypeConstant.PLAN.getUnid())
                        .setParameter("objUnid", objUnid)
                        .getSingleResult();
            } catch (NoResultException ex) {
                planReward = new Reward();
                planReward.setObjUnid(objUnid);
                planReward.setRewardType(RewardTypeConstant.PLAN.getUnid());
            }

            BigDecimal rewardSum = getRewardSumByFormula(emEis, rewardFormula.getRfFormula(), objUnid);
            if (rewardSum == null) {
                return Result.error("Не удалось рассчитать вознаграждение.");
            }
            planReward.setRewardSum(rewardSum);
            planReward.setRfUnid(rewardFormula.getRfUnid());
            rewardDao.saveReward(emEis, planReward);
        } catch (Exception ex) {
            logger.error("Error createPlanReward for objUnid = {}", objUnid, ex);
            return Result.error(ex.getMessage());
        }

        return Result.ok();
    }

    public Result<Void, String> createWinnerReward(EntityManager emEis, Lot lot, ObjectJPA object, Auction auction, Long saleGuideTrade,
                                                   Long tpaUnidCreator,
                                                   Long tpaUnidSeller, List<Declarant> allowedApplicationDeclarants) {
        try {
            Optional<Deal> dealResult = dealController.getCommissionDeal(emEis, object.getObjUnid());
            if (!dealResult.isPresent()) {
                return Result.error("Отсутствует договор.");
            }
            Deal deal = dealResult.get();

            RewardFormula rewardFormula = getRewardFormula(emEis, deal);
            if (rewardFormula == null) {
                return Result.error("Отсутствует формула расчета вознаграждения для победителя.");
            }

            Optional<Declarant> buyerResult = getBuyerDeclarant(lot, allowedApplicationDeclarants);
            if (!buyerResult.isPresent()) {
                return Result.error("Не удалось определить покупателя.");
            }
            Declarant buyerDeclarant = buyerResult.get();

            Long paBk = deal.getPaUnid();

            if (paBk == null && deal.getDeaDealUnid() != null) {
                paBk = deal.getDeaDealUnid().getPaUnid();
            }
            Long taUnid = lot.getTsUnid().equals(TypeStateConstant.LOT_DONE.getId())
                    ? TypeActConstant.WINNER.getUnid()
                    : TypeActConstant.SINGLE_PARTICIPANT.getUnid();

            // Группируем объекты в акты по победителю, дате (если это публичка) и торгам
            List<VAct> acts = emEis.createQuery("select a from VAct a " +
                            " where ((date_trunc('day', a.objSaleDate) = date_trunc('day', cast(:saleDate as timestamp)) and a.typeAuctionCode in (4, 5)) or (a.typeAuctionCode not in (4, 5)))" +
                            " and a.taUnid = :taUnid " +
                            " and a.auctionUnid = :auctionUnid " +
                            " and a.subUnid = :buyerSubUnid ", VAct.class)
                    .setParameter("saleDate", object.getObjSaleDate())
                    .setParameter("taUnid", taUnid)
                    .setParameter("auctionUnid", lot.getAuctionUnid())
                    .setParameter("buyerSubUnid", buyerDeclarant.getShUnid().getSubUnid())
                    .getResultList();

            BigDecimal rewardSum = getRewardSumByFormula(emEis, rewardFormula.getRfFormula(), object.getObjUnid());
            if (rewardSum == null) {
                return Result.error("Не удалось рассчитать вознаграждение.");
            }


            ActFoundation actFoundation = getActFoundation(deal);
            Date actProtocolDate = null;

            if (auction.getTypeAuctionUnid().getTypeAuctionUnid().equals(TypeAuctionUnid.PRICELESS) ||
                    auction.getTypeAuctionUnid().getTypeAuctionCode().equals(TypeAuctionCode.PUBLIC_SALE.getCode())) {
                actProtocolDate = object.getObjSaleDate();
            } else if (actFoundation != null) {
                if (actFoundation.getAfUnid().equals(ActFoundationConstant.PROTOCOL.getUnid())) {
                    actProtocolDate = auction.getAuctionDateB();
                } else if (actFoundation.getAfUnid().equals(ActFoundationConstant.AGREEMENT.getUnid())) {
                    actProtocolDate = buyerDeclarant.getApplicatUnid().getApplicatAppTime();
                }
            }
            String actReason = actFoundation != null
                    ? actFoundation.getAfName()
                    : null;

            Long actUnid = null;
            if (!acts.isEmpty()) {
                Optional<VAct> notSendedTo1CAct = acts.stream()
                        .filter(a -> !a.getTsUnid().equals(TypeStateConstant.DISPLAY_IN_1C.getId()))
                        .findFirst();
                if (notSendedTo1CAct.isPresent()) {
                    actUnid = notSendedTo1CAct.get().getActUnid();
                }
            }
            if (actUnid == null) {
                Act act = new Act();
                act.setActNum(actDao.getNextActNum(emEis));
                act.setTaUnid(taUnid);
                act.setTsUnid(TypeStateConstant.ACT_FORMED.getId());
                act.setPaBkUnid(paBk);
                act.setActReason(actReason);
                act.setActProtocolDate(actProtocolDate);
                act.setSubUnid(buyerDeclarant.getShUnid().getSubUnid());
                actDao.saveAct(emEis, act);
                actUnid = act.getActUnid();
            }

            Reward winnerReward = new Reward();
            winnerReward.setLotUnid(lot.getLotUnid());
            winnerReward.setRewardType(RewardTypeConstant.FACT.getUnid());
            winnerReward.setActUnid(actUnid);

            winnerReward.setRewardSum(rewardSum);
            winnerReward.setRfUnid(rewardFormula.getRfUnid());
            winnerReward = calcPercent(winnerReward, saleGuideTrade, tpaUnidCreator, tpaUnidSeller);
            winnerReward = calcSumsByPercents(winnerReward);
            rewardDao.saveReward(emEis, winnerReward);

        } catch (Exception ex) {
            logger.error("Error createWinnerReward for objUnid = {}", object.getObjUnid(), ex);
            return Result.error(ex.getMessage());
        }

        return Result.ok();
    }

    private Optional<Declarant> getBuyerDeclarant(Lot lot, List<Declarant> allowedApplicationDeclarants) {
        if (lot.getLotStatus().equals(LotStatus.TRADE_NOT_DONE.getCode()) && allowedApplicationDeclarants.size() == 1) {
            return allowedApplicationDeclarants.stream()
                    .findFirst();
        } else if (lot.getLotStatus().equals(LotStatus.TRADE_DONE.getCode())) {
            return allowedApplicationDeclarants.stream()
                    .filter(decl -> decl.getApplicatUnid().getApplicatIndWinner())
                    .findFirst();
        }
        return Optional.empty();
    }

    private RewardFormula getRewardFormula(EntityManager emEis, Deal deal) {
        if (deal.getRfWinnerUnid() != null) {
            return getRewardFormulaByUnid(emEis, deal.getRfWinnerUnid());
        }
        if (deal.getDeaDealUnid() != null && deal.getDeaDealUnid().getRfWinnerUnid() != null) {
            return getRewardFormulaByUnid(emEis, deal.getDeaDealUnid().getRfWinnerUnid());
        }
        return null;
    }

    private ActFoundation getActFoundation(Deal deal) {
        if (deal.getAfUnid() != null) {
            return deal.getAfUnid();
        }
        if (deal.getDeaDealUnid() != null) {
            return deal.getDeaDealUnid().getAfUnid();
        }
        return null;
    }

    private Reward calcPercent(Reward reward, Long saleGuideTrade, Long tpaUnidCreator, Long tpaUnidSeller) {
        boolean isAsvSgt = SaleGuideTrade.ASV.equals(saleGuideTrade)
                || SaleGuideTrade.ASV_NONCORE.equals(saleGuideTrade)
                || SaleGuideTrade.ASV_PLEDGE.equals(saleGuideTrade);
        if (isAsvSgt) {
            reward.setRewardManProc(ASV_REWARD_MAN_PROC);
        } else {
            reward.setRewardManProc(new BigDecimal("0"));
        }

        if (tpaUnidCreator != null && tpaUnidSeller != null
                && tpaUnidCreator.equals(tpaUnidSeller)) {
            reward.setRewardProfProc(isAsvSgt
                    ? ASV_REWARD_PROC
                    : NON_ASV_REWARD_PROC);
        } else {
            if (tpaUnidSeller != null) {
                if (tpaUnidSeller.equals(TpaDivision.SALES_DEPARTMENT_SPB.getTpaUnid())
                        || tpaUnidSeller.equals(TpaDivision.SALES_DEPARTMENT_MOSCOW.getTpaUnid())) {
                    reward.setRewardProfProc(isAsvSgt
                            ? ASV_REWARD_PROC
                            : NON_ASV_REWARD_PROC);
                } else {
                    BigDecimal rewardProc = isAsvSgt
                            ? ASV_REWARD_PROC
                            .divide(new BigDecimal("2"), 2, RoundingMode.HALF_UP)
                            : NON_ASV_REWARD_PROC
                            .divide(new BigDecimal("2"), 2, RoundingMode.HALF_UP);
                    reward.setRewardSalerProc(rewardProc);
                    reward.setRewardProfProc(rewardProc);
                }
            } else {
                reward.setRewardSalerProc(new BigDecimal("0"));
                reward.setRewardProfProc(new BigDecimal("0"));
            }
        }
        return reward;
    }

    private Reward calcSumsByPercents(Reward reward) {
        BigDecimal rewardSum = reward.getRewardSum();
        if (rewardSum == null || rewardSum.equals(BigDecimal.ZERO)) {
            return reward;
        }
        reward.setRewardProfSum(reward.getRewardProfProc() == null
                ? null
                : rewardSum.multiply(reward.getRewardProfProc())
                .divide(new BigDecimal("100.0"), 2, RoundingMode.HALF_UP));
        reward.setRewardSalerSum(reward.getRewardSalerProc() == null
                ? null
                : rewardSum.multiply(reward.getRewardSalerProc())
                .divide(new BigDecimal("100.0"), 2, RoundingMode.HALF_UP));
        reward.setRewardManSum(reward.getRewardManProc() == null
                ? null
                : rewardSum.multiply(reward.getRewardManProc())
                .divide(new BigDecimal("100.0"), 2, RoundingMode.HALF_UP));
        return reward;
    }

    public BigDecimal getRewardSumByFormula(EntityManager em, String rfFormula, Long objUnid) {
        if (rfFormula == null || rfFormula.isEmpty()) {
            return null;
        }
        try {
            BigDecimal result = (BigDecimal) em.createNativeQuery(rfFormula)
                    .setParameter("objUnid", objUnid)
                    .getSingleResult();
            return result;
        } catch (Exception e) {
            logger.error("Can't get reward sum for object with unid = {}", objUnid, e);
            return null;
        }
    }

    public RewardFormula getRewardFormulaByUnid(EntityManager em, Long rfUnid) {
        return em.find(RewardFormula.class, rfUnid);
    }

    public RewardFormula getRewardFormulaByRfCode(EntityManager em, String rfCode) {
        try {
            return em.createQuery("SELECT rf FROM RewardFormula rf where rf.indActual = 1 and trim(lower(rf.rfCode)) like :rfCode ", RewardFormula.class)
                    .setParameter("rfCode", rfCode.trim().toLowerCase())
                    .getSingleResult();
        } catch (Throwable ex) {
            logger.error("Error in RewardFormulaController.getRewardFormulaByRfCode with rfCode = {}", rfCode, ex);
        }
        return null;
    }

}
