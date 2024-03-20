package com.baltinfo.radius.db.controller.exchange;

import com.baltinfo.radius.exchange.ExchangeEntityParams;
import com.baltinfo.radius.exchange.ExchangeResult;

/**
 * @author Suvorina Aleksandra
 * @since 22.08.2019
 */
public interface ExchangeUnloadController<T extends EtpEntity, K extends ExchangeEntityParams> {
    ExchangeResult exportLot(T auction, K params);
    T getEtpAuction(Long etpAuctionUnid);
}
