package com.baltinfo.radius.rest.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Base64;

/**
 * <p>
 * </p>
 *
 * @author Lapenok Igor
 * @since 26.03.2019
 */
public class FileRequest implements Serializable {
    @JsonProperty("bytes")
    private final String bytes;
    @JsonProperty("fileName")
    private final String fileName;

    @JsonCreator
    public FileRequest(@JsonProperty("bytes") byte[] bytes,
                       @JsonProperty("bytes") String fileName) {
        this.bytes = Base64.getEncoder().encodeToString(bytes);
        this.fileName = fileName;
    }

    public byte[] getBytes() {
        return Base64.getDecoder().decode(bytes);
    }

    public String getFileName() {
        return fileName;
    }
}
