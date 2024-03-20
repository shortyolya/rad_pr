package com.baltinfo.radius.bankruptcy.export;

import com.baltinfo.radius.rest.client.HttpRequestService;
import com.baltinfo.radius.rest.request.FileRequest;
import com.baltinfo.radius.rest.response.common.FileResponse;
import com.baltinfo.radius.rest.response.common.LongResponse;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ssv
 * @since 31.10.2021
 */
public class BkrFileService {

    private static final Logger logger = LoggerFactory.getLogger(BkrFileService.class);

    private final HttpRequestService httpRequestService;

    public BkrFileService(HttpRequestService httpRequestService) {
        this.httpRequestService = httpRequestService;
    }

    public Long sendFileToStorage(byte[] bytes, String fileName) {
        FileRequest request = new FileRequest(bytes, fileName);
        try {
            Result<LongResponse, String> result = httpRequestService
                    .executePostObject("/docfile/storage/send", LongResponse.class, request);
            if (result.isSuccess()) {
                return result.getResult().getValue();
            } else {
                logger.error("Error in sendFileToStorage. Message: {}", result.getError());
            }
        } catch (Exception ex) {
            logger.error("Error in sendFileToStorage: ", ex);
        }
        return null;
    }

    public FileResponse getFileFromStorage(Long dfUnid) {
        try {
            Result<FileResponse, String> result = httpRequestService
                    .executeGetObject("/docfile/storage/get/" + dfUnid, FileResponse.class);
            if (result.isSuccess()) {
                return result.getResult();
            } else {
                logger.error("Error getFileFromStorage by dfUnid = {}. Message: {}",
                        dfUnid, result.getError());
            }
        } catch (Exception ex) {
            logger.error("Error getFileFromStorage by dfUnid = {}", dfUnid, ex);
        }
        return null;
    }
}