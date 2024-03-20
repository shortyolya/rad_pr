package com.baltinfo.radius.dadata.dto;

/**
 * <p>
 *     DTO-объект для хранения полного адреса объекта
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 13.12.2019
 */
public class AddressDto {
    private final String source;
    private final String fiasId;
    private final String houseNumber;
    private final String postalCode;
    private final String blockNumber;
    private final String flatNumber;
    private final String latitude;
    private final String longitude;
    private final String regionFiasId;
    private final String areaFiasId;
    private final String cityFiasId;
    private final String cityDistrictFiasId;
    private final String settlementFiasId;
    private final String streetFiasId;
    private final String houseFiasId;
    private final String info;
    private final String regionFiasName;
    private final String regionFiasCode;
    private final String SPBDistrictFiasName;

    private AddressDto(String source, String fiasId, String houseNumber, String postalCode,
                       String blockNumber, String flatNumber, String latitude,
                       String longitude, String regionFiasId, String areaFiasId, String cityFiasId,
                       String cityDistrictFiasId, String settlementFiasId, String streetFiasId,
                       String houseFiasId, String info, String regionFiasName, String regionFiasCode, String SPBDistrictFiasName) {
        this.source = source;
        this.fiasId = fiasId;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.blockNumber = blockNumber;
        this.flatNumber = flatNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.regionFiasId = regionFiasId;
        this.areaFiasId = areaFiasId;
        this.cityFiasId = cityFiasId;
        this.cityDistrictFiasId = cityDistrictFiasId;
        this.settlementFiasId = settlementFiasId;
        this.streetFiasId = streetFiasId;
        this.houseFiasId = houseFiasId;
        this.info = info;
        this.regionFiasName = regionFiasName;
        this.regionFiasCode = regionFiasCode;
        this.SPBDistrictFiasName = SPBDistrictFiasName;
    }

    public static AddressBuilder builder() {
        return new AddressBuilder();
    }

    public String getSource() {
        return source;
    }

    public String getFiasId() {
        return fiasId;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getRegionFiasId() {
        return regionFiasId;
    }

    public String getAreaFiasId() {
        return areaFiasId;
    }

    public String getCityFiasId() {
        return cityFiasId;
    }

    public String getCityDistrictFiasId() {
        return cityDistrictFiasId;
    }

    public String getSettlementFiasId() {
        return settlementFiasId;
    }

    public String getStreetFiasId() {
        return streetFiasId;
    }

    public String getHouseFiasId() {
        return houseFiasId;
    }

    public String getInfo() {
        return info;
    }

    public String getRegionFiasName() {
        return regionFiasName;
    }

    public String getRegionFiasCode() {
        return regionFiasCode;
    }

    public String getSPBDistrictFiasName() {
        return SPBDistrictFiasName;
    }

    @Override
    public String toString() {
        return "AddressDto{" +
                "source='" + source + '\'' +
                ", fiasId='" + fiasId + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", blockNumber='" + blockNumber + '\'' +
                ", flatNumber='" + flatNumber + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", regionFiasId='" + regionFiasId + '\'' +
                ", areaFiasId='" + areaFiasId + '\'' +
                ", cityFiasId='" + cityFiasId + '\'' +
                ", cityDistrictFiasId='" + cityDistrictFiasId + '\'' +
                ", settlementFiasId='" + settlementFiasId + '\'' +
                ", streetFiasId='" + streetFiasId + '\'' +
                ", houseFiasId='" + houseFiasId + '\'' +
                ", info=" + info +
                ", regionFiasName='" + regionFiasName + '\'' +
                '}';
    }

    public static final class AddressBuilder {
        private String source;
        private String fiasId;
        private String houseNumber;
        private String postalCode;
        private String blockNumber;
        private String flatNumber;
        private String latitude;
        private String longitude;
        private String regionFiasId;
        private String areaFiasId;
        private String cityFiasId;
        private String cityDistrictFiasId;
        private String settlementFiasId;
        private String streetFiasId;
        private String houseFiasId;
        private String info;
        private String regionFiasName;
        private String regionFiasCode;
        private String SPBDistrictFiasName;

        private AddressBuilder() {
        }

        public AddressBuilder withSource(String source) {
            this.source = source;
            return this;
        }

        public AddressBuilder withFiasId(String fiasId) {
            this.fiasId = fiasId;
            return this;
        }

        public AddressBuilder withHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public AddressBuilder withPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public AddressBuilder withBlockNumber(String blockNumber) {
            this.blockNumber = blockNumber;
            return this;
        }

        public AddressBuilder withFlatNumber(String flatNumber) {
            this.flatNumber = flatNumber;
            return this;
        }

        public AddressBuilder withLatitude(String latitude) {
            this.latitude = latitude;
            return this;
        }

        public AddressBuilder withLongitude(String longitude) {
            this.longitude = longitude;
            return this;
        }

        public AddressBuilder withRegionFiasId(String regionFiasId) {
            this.regionFiasId = regionFiasId;
            return this;
        }

        public AddressBuilder withAreaFiasId(String areaFiasId) {
            this.areaFiasId = areaFiasId;
            return this;
        }

        public AddressBuilder withCityFiasId(String cityFiasId) {
            this.cityFiasId = cityFiasId;
            return this;
        }

        public AddressBuilder withCityDistrictFiasId(String cityDistrictFiasId) {
            this.cityDistrictFiasId = cityDistrictFiasId;
            return this;
        }

        public AddressBuilder withSettlementFiasId(String settlementFiasId) {
            this.settlementFiasId = settlementFiasId;
            return this;
        }

        public AddressBuilder withStreetFiasId(String streetFiasId) {
            this.streetFiasId = streetFiasId;
            return this;
        }

        public AddressBuilder withHouseFiasId(String houseFiasId) {
            this.houseFiasId = houseFiasId;
            return this;
        }

        public AddressBuilder withInfo(String info) {
            this.info = info;
            return this;
        }

        public AddressBuilder withRegionFiasName(String regionFiasName) {
            this.regionFiasName = regionFiasName;
            return this;
        }

        public AddressBuilder withRegionFiasCode(String regionFiasCode) {
            this.regionFiasCode = regionFiasCode;
            return this;
        }

        public AddressBuilder withSPBDistrictFiasName(String SPBDistrictFiasName) {
            this.SPBDistrictFiasName = SPBDistrictFiasName;
            return this;
        }

        public AddressDto build() {
            return new AddressDto(source, fiasId, houseNumber, postalCode, blockNumber, flatNumber, latitude, longitude, regionFiasId, areaFiasId, cityFiasId, cityDistrictFiasId,
                    settlementFiasId, streetFiasId, houseFiasId, info, regionFiasName, regionFiasCode, SPBDistrictFiasName);
        }
    }
}
