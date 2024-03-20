package com.baltinfo.radius.lotonline.export;

import com.baltinfo.radius.db.model.Address;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.ObjCost;
import com.baltinfo.radius.db.model.ObjectJPA;
import com.baltinfo.radius.db.model.ParticipantAgent;
import com.baltinfo.radius.db.model.SaleCategory;
import com.baltinfo.radius.db.model.Subject;
import com.baltinfo.radius.exchange.ExchangeEntityParams;

import java.io.Serializable;
import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 22.08.2019
 */
public class LotOnlineLotParams implements ExchangeEntityParams, Serializable {

    private final Lot lot;
    private final ObjectJPA object;
    private final Auction auction;
    private final ObjCost startCost;
    private final ObjCost minCost;
    private final Address address;
    private final List<LotOnlinePictureParams> pictures;
    private final List<LotOnlineDocParams> documents;
    private final Long saleCategory;
    private final Subject saleManager;

    public LotOnlineLotParams(Lot lot, ObjectJPA object, ObjCost startCost,
                              Address address, ObjCost minCost, Auction auction, SaleCategory saleCategory,
                              List<LotOnlinePictureParams> pictures,
                              List<LotOnlineDocParams> documents,
                              ParticipantAgent paSaleManager) {
        this.lot = lot;
        this.auction = auction;
        this.object = object;
        this.startCost = startCost;
        this.minCost = minCost;
        this.address = address;
        this.saleCategory = new Long(saleCategory.getScCode());
        this.saleManager = paSaleManager != null
                ? paSaleManager.getSubSubUnid()
                : null;
        this.pictures = pictures;
        this.documents = documents;
    }

    public Lot getLot() {
        return lot;
    }

    public Auction getAuction() {
        return auction;
    }

    public ObjectJPA getObject() {
        return object;
    }

    public ObjCost getStartCost() {
        return startCost;
    }

    public ObjCost getMinCost() {
        return minCost;
    }

    public Address getAddress() {
        return address;
    }

    public List<LotOnlinePictureParams> getPictures() {
        return pictures;
    }

    public Long getSaleCategory() {
        return saleCategory;
    }

    public Subject getSaleManager() {
        return saleManager;
    }

    public List<LotOnlineDocParams> getDocuments() {
        return documents;
    }
}
