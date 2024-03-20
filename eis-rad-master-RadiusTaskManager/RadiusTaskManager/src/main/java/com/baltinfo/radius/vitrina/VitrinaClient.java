package com.baltinfo.radius.vitrina;

import com.baltinfo.radius.notification.paydoc.DocumentDto;
import com.baltinfo.radius.utils.Result;
import com.mashape.unirest.http.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class VitrinaClient {
    private static final Logger logger = LoggerFactory.getLogger(VitrinaClient.class);

    private final String baseUrl;

    public VitrinaClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Result<DocumentDto, String> uploadFile(File file) {
        try {
            String url = baseUrl + "/index.php?dispatch=rad_files.new";
            DocumentDto documentDto = Unirest.post(url)
                    .field("file", file)
                    .asObject(DocumentDto.class)
                    .getBody();
            return Result.ok(documentDto);

        } catch (Exception e) {
            logger.error("Error in uploadFile", e);
            return Result.error("Ошибка при загрузке документа на витрину.");
        }
    }

}
