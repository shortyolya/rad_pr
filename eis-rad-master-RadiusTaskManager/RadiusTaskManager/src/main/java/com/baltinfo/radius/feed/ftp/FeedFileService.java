package com.baltinfo.radius.feed.ftp;

import com.baltinfo.radius.utils.Result;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 25.06.2020
 */
public interface FeedFileService {
    Result<Boolean, String> isFileExistsInDirectory(Long objUnid, String fileName);
    Result<Void, String> deleteUnnecessaryFiles(Long objUnid, List<String> fileNames);
    Result<Void, String> deleteUnnecessaryDirectories(List<Long> objUnidList);
    Result<Void, String> postImageFileOnServer(Long objUnid, String fileName, String filePath, Boolean withWatermark);
}
