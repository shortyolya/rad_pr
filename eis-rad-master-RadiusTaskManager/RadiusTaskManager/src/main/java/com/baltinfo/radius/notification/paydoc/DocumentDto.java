package com.baltinfo.radius.notification.paydoc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//Загруженный через витрину документ (POST /index.php?dispatch=rad_files.new)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentDto {
    //Идентификатор на витрине загруженного файла
    @JsonProperty("fileid")
    private Long fileid;
    //Имя загруженного файла (игнорируется при приеме)
    @JsonProperty("uploadName")
    private String uploadName;
    //Ссылка на скачивание файла (игнорируется при приеме)
    @JsonProperty("file")
    private String file;
    //ГОСТ 2012 Хеш файла (игнорируется при приеме)
    @JsonProperty("hash")
    private String hash;

    @JsonCreator
    public DocumentDto(@JsonProperty("fileid") Long fileid,
                       @JsonProperty("uploadName") String uploadName,
                       @JsonProperty("file") String file,
                       @JsonProperty("hash") String hash) {
        this.fileid = fileid;
        this.uploadName = uploadName;
        this.file = file;
        this.hash = hash;
    }

    public Long getFileid() {
        return fileid;
    }

    public String getUploadName() {
        return uploadName;
    }

    public String getFile() {
        return file;
    }

    public String getHash() {
        return hash;
    }
}