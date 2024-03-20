package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.dto.BlockAuctionDto;
import com.baltinfo.radius.db.model.BlockAuction;
import com.baltinfo.radius.db.model.DebitorProperty;
import com.baltinfo.radius.loadauction.model.Publication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.Date;

/**
 * <p>
 * DAO для работы с блочными торгами
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 10.02.2020
 */
public class BlockAuctionDao extends AbstractDao {

    private static Logger logger = LoggerFactory.getLogger(BlockAuctionDao.class);

    public BlockAuction createBlockAuction(EntityManager em, BlockAuctionDto blockAuctionDto) {
        BlockAuction blockAuction = new BlockAuction();
        blockAuction.setIndActual(1);
        Date date = new Date();
        Long paUnid = 1L;
        blockAuction.setDateB(date);
        blockAuction.setPersCodeB(paUnid);
        blockAuction.setBaName(blockAuctionDto.getBaName());
        blockAuction.setBaAsvId(blockAuctionDto.getBaAsvId());
        blockAuction.setBaIndNonBlock(blockAuctionDto.getBaIndBlock());
        blockAuction.setDpUnid(createDebitorProperty(em, blockAuctionDto.getTemplateDebitorPropertyUnid()));
        em.persist(blockAuction);
        return blockAuction;
    }

    private Long createDebitorProperty(EntityManager em, Long dpUnidTemplate) {
        DebitorProperty debitorPropertyTemplate = em.find(DebitorProperty.class, dpUnidTemplate);
        DebitorProperty debitorProperty = convertDebitorProperty(debitorPropertyTemplate);
        em.persist(debitorProperty);
        return debitorProperty.getDpUnid();
    }

    private DebitorProperty convertDebitorProperty(DebitorProperty debitorPropertyTemplate) {
        DebitorProperty debitorProperty = debitorPropertyTemplate != null ? debitorPropertyTemplate.clone() : new DebitorProperty();
        setNewHisory(new Date(), debitorProperty);
        return debitorProperty;
    }

    public BlockAuction getBlockAuctionByUnid(EntityManager em, Long baUnid) {
        return em.find(BlockAuction.class, baUnid);
    }
}
