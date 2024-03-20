package com.baltinfo.radius.loadauction.ftp;

/**
 * @author Suvorina Aleksandra
 * @since 12.10.2021
 */
public class TenderSource {

    private final String directoryName;
    private final String xmlFileName;
    private final String xmlFileSource;
    private final String jsonFileName;
    private final String jsonFileSource;

    private TenderSource(String directoryName, String xmlFileName, String xmlFileSource, String jsonFileName, String jsonFileSource) {
        this.directoryName = directoryName;
        this.xmlFileName = xmlFileName;
        this.xmlFileSource = xmlFileSource;
        this.jsonFileName = jsonFileName;
        this.jsonFileSource = jsonFileSource;
    }

    public String getXmlFileName() {
        return xmlFileName;
    }

    public String getXmlFileSource() {
        return xmlFileSource;
    }

    public String getJsonFileName() {
        return jsonFileName;
    }

    public String getJsonFileSource() {
        return jsonFileSource;
    }

    public static TenderSourceBuilder builder() {
        return new TenderSourceBuilder();
    }

    public static final class TenderSourceBuilder {

        private String directoryName;
        private String xmlFileName;
        private String xmlFileSource;
        private String jsonFileName;
        private String jsonFileSource;

        private TenderSourceBuilder() {
        }

        public TenderSourceBuilder withDirectoryName(String directoryName) {
            this.directoryName = directoryName;
            return this;
        }

        public TenderSourceBuilder withXmlFileName(String xmlFileName) {
            this.xmlFileName = xmlFileName;
            return this;
        }

        public TenderSourceBuilder withXmlFileSource(String xmlFileSource) {
            this.xmlFileSource = xmlFileSource;
            return this;
        }

        public TenderSourceBuilder withJsonFileName(String jsonFileName) {
            this.jsonFileName = jsonFileName;
            return this;
        }

        public TenderSourceBuilder withJsonFileSource(String jsonFileSource) {
            this.jsonFileSource = jsonFileSource;
            return this;
        }

        public TenderSource build() {
            return new TenderSource(directoryName, xmlFileName, xmlFileSource, jsonFileName, jsonFileSource);
        }
    }

    @Override
    public String toString() {
        return "TenderSource{" +
                "directoryName='" + directoryName + '\'' +
                ", xmlFileName='" + xmlFileName + '\'' +
                ", jsonFileName='" + jsonFileName + '\'' +
                '}';
    }
}
