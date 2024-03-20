package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.dadata.dto.AddressDto;
import com.baltinfo.radius.db.constants.CostIndTax;
import com.baltinfo.radius.db.constants.EisCurrencyUnid;
import com.baltinfo.radius.db.constants.EntityConstant;
import com.baltinfo.radius.db.constants.OksmConstant;
import com.baltinfo.radius.db.constants.SubjectConstant;
import com.baltinfo.radius.db.constants.TypeStateConstant;
import com.baltinfo.radius.db.model.Address;
import com.baltinfo.radius.db.model.ClAsv;
import com.baltinfo.radius.db.model.DocFile;
import com.baltinfo.radius.db.model.Document;
import com.baltinfo.radius.db.model.LoadAuction;
import com.baltinfo.radius.db.model.LoadLot;
import com.baltinfo.radius.db.model.LoadSource;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.ObjCost;
import com.baltinfo.radius.db.model.ObjDescr;
import com.baltinfo.radius.db.model.ObjMarketingEvents;
import com.baltinfo.radius.db.model.ObjRole;
import com.baltinfo.radius.db.model.ObjSaleCategory;
import com.baltinfo.radius.db.model.ObjectJPA;
import com.baltinfo.radius.db.model.Picture;
import com.baltinfo.radius.db.model.ReductionSchedule;
import com.baltinfo.radius.db.model.SaleCategory;
import com.baltinfo.radius.db.model.TypeObject;
import com.baltinfo.radius.db.model.TypePartAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * DAO для работы с лотами
 *
 * @author Lapenok Igor
 * @since 23.08.2018
 */
public class LotDao extends AbstractDao {
    private static final Logger logger = LoggerFactory.getLogger(LotDao.class);

    private static final String DEFAULT_LATITUDE = "61.698653";
    private static final String DEFAULT_LONGITUDE = "99.505405";

    public Lot createLot(EntityManager em, Lot lot) {
        setNewHisory(new Date(), lot);
        em.persist(lot);
        return lot;
    }

    public ObjectJPA createObject(EntityManager em, LoadAuction loadAuction, LoadLot loadLot, Long toUnid) {
        ObjectJPA objectJPA = new ObjectJPA();
        objectJPA.setPaUnid(loadAuction.getPaUnid());
        objectJPA.setParPaUnid(loadAuction.getPaUnid());
        objectJPA.setTsUnid(TypeStateConstant.TRADE_ASSIGNED.getId());
        objectJPA.setToUnid(toUnid);
        objectJPA.setEntityUnid(EntityConstant.OBJECT.getId());
        objectJPA.setSubUnid(loadAuction.getSubUnid());
        objectJPA.setSgUnid(loadAuction.getSgUnid());
        objectJPA.setSmUnid(loadAuction.getSmUnid());
        objectJPA.setObjName(loadLot.getLlLotSname());
        objectJPA.setObjNote(loadLot.getLlLotName());
        objectJPA.setObjIndMain(1);
        objectJPA.setObjCount(1L);
        objectJPA.setLsUnid(loadAuction.getLsUnid().getLsUnid());
        objectJPA.setObjCode(generateObjCode(em, loadAuction.getLsUnid(), loadAuction.getTpaUnid()));
        setNewHisory(new Date(), objectJPA);
        objectJPA.setIndActual(0);
        objectJPA.setObjLandCommanSqr(loadLot.getLlLandCommonSqr());
        objectJPA.setObjFlatCommanSqr(loadLot.getLlFlatCommonSqr());
        objectJPA.setObjLinks(loadLot.getLlObjectLinks());
        em.persist(objectJPA);
        return objectJPA;
    }

    private String generateObjCode(EntityManager em, LoadSource loadSource, Long tpaUnid) {
        if (loadSource.getLsPrefix() == null || loadSource.getLsPrefix().isEmpty()) {
            TypePartAgent tpa = em.find(TypePartAgent.class, tpaUnid);
            SubjectConstant subConst = SubjectConstant.getById(tpa.getSubUnid().getSubUnid());
            if (subConst != null) {
                return subConst.getPrefix() + getSeq(subConst.getSeqName(), em);
            } else {
                return "" + getSeq(loadSource.getLsNumSeq(), em);
            }
        } else {
            return loadSource.getLsPrefix() + getSeq(loadSource.getLsNumSeq(), em);
        }
    }

    public ObjCost createObjCost(EntityManager em, Lot lot, ObjectJPA objectJPA, Long typeCostUnid, BigDecimal costValue, boolean isCurrent) {
        if (isCurrent) {
            em.createQuery("Update ObjCost set costIndCurrent = 0 where objUnid.objUnid = :objUnid")
                    .setParameter("objUnid", objectJPA.getObjUnid())
                    .executeUpdate();
        }
        ObjCost objCost = new ObjCost();
        objCost.setTypeCosUnid(typeCostUnid);
        objCost.setLotUnid(lot);
        objCost.setObjUnid(objectJPA);
        objCost.setCostValue(costValue);
        objCost.setCostValueRub(costValue);
        objCost.setCurUnid(lot.getCurUnid() != null ? lot.getCurUnid() : EisCurrencyUnid.RUB.getUnid());
        objCost.setCostStatus(2);
        objCost.setCostIndCurrent(isCurrent ? 1 : 0);
        objCost.setCostIndTax(CostIndTax.NO_TAX.getIndTax());
        setNewHisory(new Date(), objCost);
        em.persist(objCost);
        return objCost;
    }

    public ObjRole createObjRole(EntityManager em, ObjectJPA objectJPA, Long tpaUnid) {
        ObjRole objRole = new ObjRole();
        objRole.setObjUnid(objectJPA);
        objRole.setTpaUnid(tpaUnid);
        objRole.setOrIndCreate(1);
        setNewHisory(new Date(), objRole);
        em.persist(objRole);
        return objRole;
    }

    public Address createRussianAddress(EntityManager em, ObjectJPA objectJPA, AddressDto addressDto, String regionId, String regionName) {
        Address address = new Address();
        address.setObjUnid(objectJPA);
        address.setOkcmUnid(OksmConstant.RUSSIA.getId());
        address.setAddrAddress(addressDto.getSource());
        address.setAddrFiasId(addressDto.getFiasId());
        address.setAddrHouse(addressDto.getHouseNumber());
        address.setAddrIndex(addressDto.getPostalCode());
        address.setAddrCorp(addressDto.getBlockNumber());
        address.setAddrFlat(addressDto.getFlatNumber());
        address.setAddrLat(addressDto.getLatitude() == null ? DEFAULT_LATITUDE : addressDto.getLatitude());
        address.setAddrLong(addressDto.getLongitude() == null ? DEFAULT_LONGITUDE : addressDto.getLongitude());
        address.setAddrRegionId(addressDto.getRegionFiasId() == null ? regionId : addressDto.getRegionFiasId());
        address.setAddrRegionName(addressDto.getRegionFiasName() == null ? regionName : addressDto.getRegionFiasName());
        address.setAddrDistrict(addressDto.getSPBDistrictFiasName());
        address.setAddrRegionCode(addressDto.getRegionFiasCode());
        address.setAddrAreaId(addressDto.getAreaFiasId());
        address.setAddrCityId(addressDto.getCityFiasId());
        address.setAddrCtarId(addressDto.getCityDistrictFiasId());
        address.setAddrPlaceId(addressDto.getSettlementFiasId());
        address.setAddrStreetId(addressDto.getStreetFiasId());
        address.setAddrNote(addressDto.getInfo());
        address.setAddrInputMode(2L);
        setNewHisory(new Date(), address);
        em.persist(address);
        return address;
    }


    public ReductionSchedule createRs(EntityManager em, Lot lot, Date dateB, Date applDateE, BigDecimal price,
                                      Date depDateE, BigDecimal depSum, BigDecimal redValue) {
        ReductionSchedule reductionSchedule = new ReductionSchedule();
        reductionSchedule.setLotUnid(lot);
        reductionSchedule.setRedSchedDateB(dateB);
        reductionSchedule.setRedSchedApplDateE(applDateE);
        reductionSchedule.setRedSchedAskPrice(price);
        reductionSchedule.setRedSchedDepDateE(depDateE);
        reductionSchedule.setRedSchedDepSum(depSum);
        reductionSchedule.setRedSchedReductionValue(redValue);
        setNewHisory(new Date(), reductionSchedule);
        em.persist(reductionSchedule);
        return reductionSchedule;
    }

    public ObjDescr createObjDescr(EntityManager em, String odText, Long odNum, Long objUnid, Long todUnid) {
        ObjDescr od = new ObjDescr();
        od.setObjUnid(objUnid);
        od.setTodUnid(todUnid);
        od.setOdText(odText);
        od.setOdNum(odNum);
        setNewHisory(new Date(), od);
        em.persist(od);
        return od;
    }

    public ObjMarketingEvents createObjMarketingEvent(EntityManager em, Long objUnid, Long mevUnid) {
        ObjMarketingEvents ome = new ObjMarketingEvents();
        ome.setObjUnid(objUnid);
        ome.setMevUnid(mevUnid);
        ome.setOmeIndExists(false);
        setNewHisory(new Date(), ome);
        em.persist(ome);
        return ome;
    }

    public DocFile createDocFile(EntityManager em, DocFile docFile) {
        setNewHisory(new Date(), docFile);
        em.persist(docFile);
        return docFile;
    }

    public Document createDocument(EntityManager em, Document document) {
        setNewHisory(new Date(), document);
        em.persist(document);
        return document;
    }

    public Picture createPicture(EntityManager em, Picture picture) {
        setNewHisory(new Date(), picture);
        em.persist(picture);
        return picture;
    }

    public Lot getLot(EntityManager em, Long lotUnid) {
        return em.find(Lot.class, lotUnid);
    }

    public ObjCost getStartObjCost(EntityManager em, Long lotUnid) {
        return (ObjCost) em.createNamedQuery("ObjCost.findStartCostByLotUnid")
                .setParameter("lotUnid", lotUnid)
                .getSingleResult();
    }

    public ObjSaleCategory getObjSaleCategoryByObjUnid(EntityManager em, Long objUnid) {
        return (ObjSaleCategory) em.createQuery("SELECT osc FROM ObjSaleCategory osc " +
                        "where osc.objUnid = :objUnid " +
                        "and osc.oscIndMain = true " +
                        "and osc.indActual = 1")
                .setParameter("objUnid", objUnid)
                .getSingleResult();
    }

    public ObjCost getMinObjCost(EntityManager em, Long lotUnid) {
        return (ObjCost) em.createNamedQuery("ObjCost.findMinCostByLotUnid")
                .setParameter("lotUnid", lotUnid)
                .getSingleResult();
    }

    public SaleCategory getSaleCategoryByScCode(EntityManager em, String scCode) {
        return em.createNamedQuery("SaleCategory.findByScCode", SaleCategory.class)
                .setParameter("scCode", scCode)
                .getSingleResult();
    }

    public String getRegionIdByName(EntityManager em, String regionName) {
        return (String) em.createNativeQuery("SELECT a.aoguid FROM fias.Addrobj a " +
                        "WHERE (a.actstatus = 1 OR a.actstatus = 49) " +
                        "  AND (a.parentguid IS NULL OR a.parentguid = '')" +
                        "  AND (a.offname || ' ' || a.shortname) = :regionName")
                .setParameter("regionName", regionName)
                .getSingleResult();
    }

    public String getRegionIdByNameInASV(EntityManager em, String regionName) {
        return (String) em.createNativeQuery("SELECT srf.aoguid FROM web.subject_rf_asv srf " +
                        "WHERE srf.srf_asv_name = :regionName")
                .setParameter("regionName", regionName)
                .getSingleResult();
    }

    public TypeObject getTypeObjectByName(EntityManager em, String name) {
        return (TypeObject) em.createNamedQuery("TypeObject.findByToName")
                .setParameter("toName", name)
                .getSingleResult();
    }

    public TypeObject getTypeObjectByScUnid(EntityManager em, Long scUnid) {
        try {
            return (TypeObject) em.createNamedQuery("SaleCategory.findToUnidByScUnid")
                    .setParameter("scUnid", scUnid)
                    .getSingleResult();
        } catch (NoResultException e) {
            logger.warn("Cant find type object by scUnid = {}", scUnid);
            return null;
        }
    }

    public ClAsv getClAsvByName(EntityManager em, String name) {
        return (ClAsv) em.createNamedQuery("ClAsv.findByCaName")
                .setParameter("caName", name)
                .getSingleResult();
    }

    public ClAsv getClAsvByUnid(EntityManager em, Long caUnid) {
        return (ClAsv) em.createNamedQuery("ClAsv.findByCaUnid")
                .setParameter("caUnid", caUnid)
                .getSingleResult();
    }

    public ClAsv getClAsvByCaCode(EntityManager em, Long caCode) {
        return (ClAsv) em.createNamedQuery("ClAsv.findByCaCode")
                .setParameter("caCode", caCode)
                .getSingleResult();
    }

    public ObjectJPA updateObject(EntityManager em, ObjectJPA obj) {
        setRecHisory(new Date(), obj);
        return em.merge(obj);
    }

    public Optional<ObjectJPA> getObjectByBaUnidAndLotNumber(EntityManager em, Long baUnid, Long lotNumber) {
        try {
            List<ObjectJPA> objects = new ArrayList<>();
            TypedQuery<ObjectJPA> resultList = em.createQuery("select o " +
                            "from ObjectJPA o, Lot l, Auction a " +
                            "where o = l.objUnid " +
                            "and a.auctionUnid = l.auctionUnid " +
                            "and l.indActual = 1 " +
                            "and o.indActual = 1 " +
                            "and a.indActual = 1 " +
                            "and a.baUnid = :baUnid " +
                            "and l.lotNumber = :lotNumber", ObjectJPA.class)
                    .setParameter("baUnid", baUnid)
                    .setParameter("lotNumber", lotNumber);
            objects = resultList.getResultList();
            return objects == null || objects.isEmpty() ? Optional.empty() : Optional.of(objects.get(0));
        } catch (NoResultException | NullPointerException e) {
            logger.info("Objects for baUnid = {} and lotNumber = {} not found", baUnid, lotNumber);
        }
        return Optional.empty();
    }

    public List<Lot> getNextStageLots(EntityManager em, Long baUnid, Integer auctionStageNum, Long lotNumber) {
        return em.createNamedQuery("Lot.findNextStageLots")
                .setParameter("baUnid", baUnid)
                .setParameter("auctionStageNum", auctionStageNum)
                .setParameter("lotNumber", lotNumber)
                .getResultList();
    }

    /**
     * Поиск минимального этапа в блочных торгах
     *
     * @param em        {@link EntityManager}
     * @param baUnid    идентификатор блочных торгов
     * @param lotNumber номер лота
     */
    public Optional<Integer> getMinStageForBlock(EntityManager em, Long baUnid, Long lotNumber) {
        try {
            Integer minStage = (Integer) em.createNamedQuery("Auction.findMinStageForBlock")
                    .setParameter("baUnid", baUnid)
                    .setParameter("lotNumber", lotNumber)
                    .getSingleResult();
            logger.info("getMinStageForBlock: baUnid = {}, lotNumber = {}, minStage = {}", baUnid, lotNumber, minStage);
            return Optional.of(minStage);
        } catch (NoResultException | NullPointerException e) {
            logger.info("getMinStageForBlock: Objects for baUnid = {}, lotNumber = {} not found", baUnid, lotNumber);
        }
        return Optional.empty();
    }

    /**
     * Очищает признак текущего лота для лотов предыдущих этапов блочных торгов
     *
     * @param em        {@link EntityManager}
     * @param baUnid    идентификатор блочных торгов
     * @param lotNumber номер лота
     */
    public void clearIndCurrentLot(EntityManager em, Long baUnid, Long lotNumber) {
        em.createQuery("Update Lot set lotIndCurrent = 0 where lotNumber = :lotNumber and indActual = 1 and " +
                        "auctionUnid in (select a.auctionUnid from Auction a where a.baUnid = :baUnid and a.indActual = 1)")
                .setParameter("baUnid", baUnid)
                .setParameter("lotNumber", lotNumber)
                .executeUpdate();
    }

    public Lot updateLot(EntityManager em, Lot lot) {
        setRecHisory(new Date(), lot);
        return em.merge(lot);
    }

    public ReductionSchedule saveOrUpdate(EntityManager em, ReductionSchedule reductionSchedule) {
        if (reductionSchedule.getRedSchedUnid() == 0) {
            setNewHisory(new Date(), reductionSchedule);
        } else {
            setRecHisory(new Date(), reductionSchedule);
        }
        return em.merge(reductionSchedule);
    }
}
