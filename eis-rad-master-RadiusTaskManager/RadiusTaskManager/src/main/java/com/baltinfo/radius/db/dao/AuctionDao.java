package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.constants.TypeAuctionConstant;
import com.baltinfo.radius.db.dto.AuctionDto;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.DebitorProperty;
import com.baltinfo.radius.db.model.LoadAuction;
import com.baltinfo.radius.db.model.SRO;
import org.apache.commons.lang.StringUtils;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * DAO для работы с торгами ЕИС РАД
 * </p>
 *
 * @author Lapenok Igor
 * @since 20.08.2018
 */
public class AuctionDao extends AbstractDao {

    /**
     * Создание торгов
     *
     * @param em          экземпляр {@link EntityManager}
     * @param auctionDto  экземпляр {@link AuctionDto}, содержащий данные торгов
     * @param auction     экземпляр {@link Auction}, содержащий дополнительную информацию о торгах
     * @param loadAuction экземпляр {@link LoadAuction}, содержащий данные о загружаемых торгах
     * @return торги {@link Auction}
     */
    public Auction createAuction(EntityManager em, AuctionDto auctionDto, Auction auction, LoadAuction loadAuction) {
        Auction newAuction = convertAuction(auctionDto, auction);
        newAuction.setBaUnid(loadAuction.getBaUnid());
        if (loadAuction.getLaAuctionName() != null && !loadAuction.getLaAuctionName().trim().isEmpty()) {
            newAuction.setAuctionName(loadAuction.getLaAuctionName());
        }
        if (auctionDto.getAuctionStageNum() != null) {
            newAuction.setAuctionStageNum(auctionDto.getAuctionStageNum());
        } else {
            newAuction.setAuctionStageNum(loadAuction.getLaStageNum());
        }
        newAuction.setDpUnid(createDebitorProperty(em, auctionDto, auction));
        em.persist(newAuction);
        return newAuction;
    }

    public Auction activateAuction(EntityManager em, Long auctionUnid) {
        em.createNativeQuery("update web.object set ind_actual = 1 where obj_unid in " +
                        "(select l.obj_unid from web.lot l where l.auction_unid = :auctionUnid)")
                .setParameter("auctionUnid", auctionUnid)
                .executeUpdate();
        Auction auction = em.find(Auction.class, auctionUnid);
        auction.setIndActual(1);
        setRecHisory(new Date(), auction);
        return em.merge(auction);
    }

    private SRO findSRO(EntityManager em, String sro) {
        if (sro == null || sro.isEmpty()) {
            return null;
        }
        List<SRO> list = em.createQuery("SELECT s FROM SRO s " +
                        "WHERE lower(trim(s.sroName)) like :sroName AND s.indActual = 1")
                .setParameter("sroName", sro.toLowerCase().trim())
                .setMaxResults(1)
                .getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private DebitorProperty createDebitorProperty(EntityManager em, AuctionDto auctionDto, Auction auctionTemplate) {
        DebitorProperty debitorProperty = convertDebitorProperty(auctionDto, auctionTemplate.getDpUnid());
        debitorProperty.setSroId(findSRO(em, auctionDto.getDpArbitManagerCro()));
        em.persist(debitorProperty);
        return debitorProperty;
    }

    private Auction convertAuction(AuctionDto auctionDto, Auction auction) {
        Auction newAuction = auction.clone();
        TypeAuctionConstant typeAuction = auctionDto.getTypeAuction();
        newAuction.setAuctionParticipantLimitation(auctionDto.getAuctionParticipantLimitation());
        newAuction.setAuctionStepForm(typeAuction.getRadiusStepForm().getId());
        newAuction.setAuctionDateB(auctionDto.getAuctionDatePlan() == null ? null : auctionDto.getAuctionDatePlan().getDate());
        newAuction.setAuctionDateE(auctionDto.getAuctionDateE() == null ? null : auctionDto.getAuctionDateE().getDate());
        newAuction.setAuctionRecepDateB(auctionDto.getAuctionRecepDateB() == null ? null : auctionDto.getAuctionRecepDateB().getDate());
        newAuction.setAuctionRecepDateE(auctionDto.getAuctionRecepDateE() == null ? null : auctionDto.getAuctionRecepDateE().getDate());
        newAuction.setAuctionRecepDateBF(auctionDto.getAuctionRecepDateB() == null ? null : auctionDto.getAuctionRecepDateB().getDate());
        newAuction.setAuctionRecepDateEF(auctionDto.getAuctionRecepDateE() == null ? null : auctionDto.getAuctionRecepDateE().getDate());
        newAuction.setAuctionDepDateE(auctionDto.getAuctionDepDateE() == null ? null : auctionDto.getAuctionDepDateE().getDate());
        newAuction.setAuctionAsvOrderNum(auctionDto.getAuctionAsvOrderNum());
        newAuction.setAuctionAsvId(auctionDto.getAuctionAsvId());
        newAuction.setAuctionNote(auctionDto.getAuctionNote());
        newAuction.setAuctionDealTypeCost(auctionDto.getAuctionDealTypeCost());
        setNewHisory(new Date(), newAuction);
        newAuction.setIndActual(0);
        return newAuction;
    }

    private DebitorProperty convertDebitorProperty(AuctionDto auctionDto, DebitorProperty debitorPropertyTemplate) {
        DebitorProperty debitorProperty = debitorPropertyTemplate != null ? debitorPropertyTemplate.clone() : new DebitorProperty();
        if (auctionDto.getDpCrashFileNum() != null) {
            debitorProperty.setDpCrashFileNum(auctionDto.getDpCrashFileNum());
        }
        if (auctionDto.getDpArbitrage() != null) {
            debitorProperty.setDpArbitrage(auctionDto.getDpArbitrage());
        }
        if (auctionDto.getDpDecreeNum() != null) {
            debitorProperty.setDpDecreeNum(auctionDto.getDpDecreeNum());
        }
        if (auctionDto.getDpDecreeDate() != null) {
            debitorProperty.setDpDecreeDate(auctionDto.getDpDecreeDate().getDate());
        }
        String arbitManager = auctionDto.getDpArbitManagerNameF() + " " +
                auctionDto.getDpArbitManagerNameI() + " " + auctionDto.getDpArbitManagerNameO();
        arbitManager = arbitManager.replace("null", "").trim();
        if (!arbitManager.isEmpty()) {
            debitorProperty.setDpArbitManager(arbitManager);
        }
        if (auctionDto.getDpArbitManagerNameF() != null) {
            debitorProperty.setDpArbitManagerNameF(auctionDto.getDpArbitManagerNameF());
        }
        if (auctionDto.getDpArbitManagerNameI() != null) {
            debitorProperty.setDpArbitManagerNameI(auctionDto.getDpArbitManagerNameI());
        }
        if (auctionDto.getDpArbitManagerNameO() != null) {
            debitorProperty.setDpArbitManagerNameO(auctionDto.getDpArbitManagerNameO());
        }
        if (auctionDto.getDpArbitManagerCro() != null) {
            debitorProperty.setDpArbitManagerCro(auctionDto.getDpArbitManagerCro());
        }
        if (auctionDto.getDpSroPaymentValue() != null) {
            debitorProperty.setDpSroPaymentValue(auctionDto.getDpSroPaymentValue());
        }
        if (auctionDto.getDpSroPaymentSum() != null) {
            debitorProperty.setDpSroPaymentSum(auctionDto.getDpSroPaymentSum());
        }
        if (auctionDto.getDpSroPaymentDate() != null) {
            debitorProperty.setDpSroPaymentDate(auctionDto.getDpSroPaymentDate().getDate());
        }
        if (auctionDto.getDpSroPaymentNote() != null) {
            debitorProperty.setDpSroPaymentNote(auctionDto.getDpSroPaymentNote());
        }
        if (auctionDto.getDpArbitManagerRegNum() != null) {
            debitorProperty.setDpArbitManagerRegNum(auctionDto.getDpArbitManagerRegNum());
        }
        if (auctionDto.getDpArbitManagerInn() != null) {
            debitorProperty.setDpArbitManagerInn(auctionDto.getDpArbitManagerInn());
        }
        if (auctionDto.getDpDepositOrder() != null) {
            debitorProperty.setDpDepositOrder(auctionDto.getDpDepositOrder());
        }
        if (auctionDto.getDpAccountsDetails() != null) {
            debitorProperty.setDpAccountsDetails(auctionDto.getDpAccountsDetails());
        }
        if (auctionDto.getDpAuctionRegOrder() != null) {
            debitorProperty.setDpAuctionRegOrder(auctionDto.getDpAuctionRegOrder());
        }
        if (auctionDto.getDpAuctionWinnerOrder() != null) {
            debitorProperty.setDpAuctionWinnerOrder(auctionDto.getDpAuctionWinnerOrder());
        }
        if (auctionDto.getDpAuctionResultPlace() != null) {
            debitorProperty.setDpAuctionResultPlace(auctionDto.getDpAuctionResultPlace());
        }
        if (auctionDto.getDpDealOrder() != null) {
            debitorProperty.setDpDealOrder(auctionDto.getDpDealOrder());
        }
        if (auctionDto.getDpPaymentsDates() != null) {
            debitorProperty.setDpPaymentsDates(auctionDto.getDpPaymentsDates());
        }
        if (auctionDto.getDpDatePublJournal() != null) {
            debitorProperty.setDpDatePublJournal(auctionDto.getDpDatePublJournal().getDate());
        }
        if (auctionDto.getDpDatePublEfir() != null) {
            debitorProperty.setDpDatePublEfir(auctionDto.getDpDatePublEfir().getDate());
        }
        //debitorProperty.setDpTenderTerm(auctionDto.);
        if (auctionDto.getDpIndTendManager() != null) {
            debitorProperty.setDpIndTendManager(auctionDto.getDpIndTendManager());
        }
        if (auctionDto.getDpTendManagerName() != null) {
            debitorProperty.setDpTendManagerName(auctionDto.getDpTendManagerName());
        }
        if (auctionDto.getDpTendManagerSname() != null) {
            debitorProperty.setDpTendManagerSname(auctionDto.getDpTendManagerSname());
        }
        if (auctionDto.getDpTendManagerOgrn() != null) {
            debitorProperty.setDpTendManagerOgrn(auctionDto.getDpTendManagerOgrn());
        }
        if (auctionDto.getDpParticipantRequirements() != null) {
            debitorProperty.setDpParticipantRequirements(auctionDto.getDpParticipantRequirements());
        }
        if (auctionDto.getDpIndSendOper() != null) {
            debitorProperty.setDpIndSendOper(new Short("1").equals(auctionDto.getDpIndSendOper()));
        }
        if (auctionDto.getDpPayeeName() != null) {
            debitorProperty.setDpPayeeName(auctionDto.getDpPayeeName());
        }
        if (auctionDto.getDpPayeeInn() != null) {
            debitorProperty.setDpPayeeInn(auctionDto.getDpPayeeInn());
        }
        if (auctionDto.getDpPayeeKpp() != null) {
            debitorProperty.setDpPayeeKpp(auctionDto.getDpPayeeKpp());
        }
        if (auctionDto.getDpPayeeAccount() != null) {
            debitorProperty.setDpPayeeAccount(auctionDto.getDpPayeeAccount());
        }
        if (auctionDto.getDpPayeeBankName() != null) {
            debitorProperty.setDpPayeeBankName(auctionDto.getDpPayeeBankName());
        }
        if (auctionDto.getDpPayeeBankBik() != null) {
            debitorProperty.setDpPayeeBankBik(auctionDto.getDpPayeeBankBik());
        }
        if (auctionDto.getDpPayeeBankAccount() != null) {
            debitorProperty.setDpPayeeBankAccount(auctionDto.getDpPayeeBankAccount());
        }
        if (StringUtils.isNotBlank(auctionDto.getDpContactEmail())) {
            debitorProperty.setDpContactEmail(auctionDto.getDpContactEmail());
        }
        if (auctionDto.getDpContactAsv() != null) {
            debitorProperty.setDpContactAsv(auctionDto.getDpContactAsv());
        }
        if (auctionDto.getDpDealAsv() != null) {
            debitorProperty.setDpDealAsv(auctionDto.getDpDealAsv());
        }
        if (auctionDto.getDpTendManagerProxy() != null) {
            debitorProperty.setDpTendManagerProxy(auctionDto.getDpTendManagerProxy());
        }
        setNewHisory(new Date(), debitorProperty);
        return debitorProperty;
    }

    public Auction getAuction(EntityManager em, Long auctionUnid) {
        return em.find(Auction.class, auctionUnid);
    }

    public Auction updateAuction(EntityManager em, Auction auction) {
        setRecHisory(new Date(), auction);
        return em.merge(auction);
    }

}
