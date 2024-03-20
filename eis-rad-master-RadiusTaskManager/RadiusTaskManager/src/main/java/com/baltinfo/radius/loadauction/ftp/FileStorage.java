package com.baltinfo.radius.loadauction.ftp;

import com.baltinfo.radius.db.dto.LoadFileDto;
import com.baltinfo.radius.db.model.DocFile;
import com.baltinfo.radius.db.model.LoadFile;
import com.baltinfo.radius.loadauction.model.Lot;
import com.baltinfo.radius.utils.Result;
import javafx.util.Pair;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 03.03.2020
 */
public interface FileStorage {
    byte[] getDocFileSource(String path);
    List<LoadFileDto> getDocumentsForAuction(String blockTradeId);
    List<LoadFileDto> getDocumentsForLot(Set<String> alreadyLoaded, Lot lot, String blockTradeId, String tradeId);
    List<TenderSource> getTenderSource();
    Result<Void, String> postAsvTenderFile(String efrsbCode, String etpCode, LoadFile loadFile);
    Result<Void, String> writeResultFile(String baAsvId, String json);
    Result<Void, String> writeDocsToZip(String zipFileName, List<DocFile> docFiles);
}
