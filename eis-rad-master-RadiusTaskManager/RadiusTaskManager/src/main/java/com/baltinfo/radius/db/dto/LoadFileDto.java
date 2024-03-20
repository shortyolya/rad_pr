package com.baltinfo.radius.db.dto;

/**
 * <p>
 *     DTO для работы с загрузкой файлов
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 04.02.2020
 */
public class LoadFileDto {
    private final Short lfType;
    private final String lfEisPath;
    private final String lfFtpPath;
    private final String lfFileName;
    private final String lfFileNameAsv;
    private final String lfAsvId;
    private final String lfExtension;
    private final Integer lfSize;
    private final Long lfBaUnid;

    private LoadFileDto(Short lfType, String lfEisPath, String lfFtpPath, String lfFileName, String lfFileNameAsv,
                       String lfAsvId, String lfExtension, Integer lfSize, Long lfBaUnid) {
        this.lfType = lfType;
        this.lfEisPath = lfEisPath;
        this.lfFtpPath = lfFtpPath;
        this.lfFileName = lfFileName;
        this.lfFileNameAsv = lfFileNameAsv;
        this.lfAsvId = lfAsvId;
        this.lfExtension = lfExtension;
        this.lfSize = lfSize;
        this.lfBaUnid = lfBaUnid;
    }

    public static LoadFileDtoBuilder builder() {
        return new LoadFileDtoBuilder();
    }

    public Short getLfType() {
        return lfType;
    }

    public String getLfEisPath() {
        return lfEisPath;
    }

    public String getLfFtpPath() {
        return lfFtpPath;
    }

    public String getLfFileName() {
        return lfFileName;
    }

    public String getLfAsvId() {
        return lfAsvId;
    }

    public String getLfExtension() {
        return lfExtension;
    }

    public Integer getLfSize() {
        return lfSize;
    }

    public String getLfFileNameAsv() {
        return lfFileNameAsv;
    }

    public Long getLfBaUnid() {
        return lfBaUnid;
    }

    public static final class LoadFileDtoBuilder {
        private Short lfType;
        private String lfEisPath;
        private String lfFtpPath;
        private String lfFileName;
        private String lfFileNameAsv;
        private String lfAsvId;
        private String lfExtension;
        private Integer lfSize;
        private Long lfBaUnid;

        private LoadFileDtoBuilder() {
        }

        public LoadFileDtoBuilder withLfType(Short lfType) {
            this.lfType = lfType;
            return this;
        }

        public LoadFileDtoBuilder withLfEisPath(String lfEisPath) {
            this.lfEisPath = lfEisPath;
            return this;
        }

        public LoadFileDtoBuilder withLfFtpPath(String lfFtpPath) {
            this.lfFtpPath = lfFtpPath;
            return this;
        }

        public LoadFileDtoBuilder withLfFileName(String lfFileName) {
            this.lfFileName = lfFileName;
            return this;
        }

        public LoadFileDtoBuilder withLfFileNameAsv(String lfFileNameAsv) {
            this.lfFileNameAsv = lfFileNameAsv;
            return this;
        }

        public LoadFileDtoBuilder withLfAsvId(String lfAsvId) {
            this.lfAsvId = lfAsvId;
            return this;
        }

        public LoadFileDtoBuilder withLfExtension(String lfExtension) {
            this.lfExtension = lfExtension;
            return this;
        }

        public LoadFileDtoBuilder withLfSize(Integer lfSize) {
            this.lfSize = lfSize;
            return this;
        }

        public LoadFileDtoBuilder withLfBaUnid(Long lfBaUnid) {
            this.lfBaUnid = lfBaUnid;
            return this;
        }

        public LoadFileDto build() {
            return new LoadFileDto(lfType, lfEisPath, lfFtpPath, lfFileName, lfFileNameAsv, lfAsvId, lfExtension, lfSize, lfBaUnid);
        }
    }
}
