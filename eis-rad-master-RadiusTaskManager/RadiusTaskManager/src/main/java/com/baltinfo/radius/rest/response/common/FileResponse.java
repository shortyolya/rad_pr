package com.baltinfo.radius.rest.response.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileResponse implements Serializable {
    private final String bytes;
    private final String fileName;

    public FileResponse(@JsonProperty("bytes") String bytes,
                        @JsonProperty("fileName") String fileName) {
        this.bytes = bytes;
        this.fileName = fileName;
    }

    public byte[] getBytes() {
        return Base64.getDecoder().decode(bytes);
    }

    public String getFileName() {
        return fileName;
    }
}
