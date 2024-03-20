package com.baltinfo.radius.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Suvorina Aleksandra
 * @since 23.03.2021
 */
public class EisFileService {
    private static final Logger logger = LoggerFactory.getLogger(EisFileService.class);

    private final String pathToFiles;


    public EisFileService(String pathToFiles) {
        this.pathToFiles = pathToFiles;
    }

    public Result<byte[], String> getFileData(String filePath) {
        String eisPath = pathToFiles + File.separator + filePath;
        Path path = Paths.get(eisPath);
        try {
            return Result.ok(Files.readAllBytes(path));
        } catch (Exception e) {
            logger.error("Error getFileData by filePath = {}", filePath, e);
            return Result.error(e.getMessage());
        }
    }

    public String getPathToFiles() {
        return pathToFiles;
    }
}
