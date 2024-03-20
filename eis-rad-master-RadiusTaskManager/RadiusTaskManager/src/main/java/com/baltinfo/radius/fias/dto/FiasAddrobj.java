package com.baltinfo.radius.fias.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FiasAddrobj {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("objectid")
    private Long objectid;

    @JsonProperty("aoguid")
    private String aoguid;

    @JsonProperty("aolevel")
    private Long aolevel;

    @JsonProperty("areacode")
    private String areacode;

    @JsonProperty("autocode")
    private String autocode;

    @JsonProperty("citycode")
    private String citycode;

    @JsonProperty("formalname")
    private String formalname;

    @JsonProperty("offname")
    private String offname;

    @JsonProperty("parentguid")
    private String parentguid;

    @JsonProperty("placecode")
    private String placecode;

    @JsonProperty("plaincode")
    private String plaincode;

    @JsonProperty("postalcode")
    private String postalcode;

    @JsonProperty("regioncode")
    private String regioncode;

    @JsonProperty("shortname")
    private String shortname;

    @JsonProperty("streetcode")
    private String streetcode;

    @JsonProperty("plancode")
    private String ctarcode;

    public FiasAddrobj() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObjectid() {
        return objectid;
    }

    public void setObjectid(Long objectid) {
        this.objectid = objectid;
    }

    public String getAoguid() {
        return aoguid;
    }

    public void setAoguid(String aoguid) {
        this.aoguid = aoguid;
    }

    public Long getAolevel() {
        return aolevel;
    }

    public void setAolevel(Long aolevel) {
        this.aolevel = aolevel;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getAutocode() {
        return autocode;
    }

    public void setAutocode(String autocode) {
        this.autocode = autocode;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getFormalname() {
        return formalname;
    }

    public void setFormalname(String formalname) {
        this.formalname = formalname;
    }

    public String getOffname() {
        return offname;
    }

    public void setOffname(String offname) {
        this.offname = offname;
    }

    public String getParentguid() {
        return parentguid;
    }

    public void setParentguid(String parentguid) {
        this.parentguid = parentguid;
    }

    public String getPlacecode() {
        return placecode;
    }

    public void setPlacecode(String placecode) {
        this.placecode = placecode;
    }

    public String getPlaincode() {
        return plaincode;
    }

    public void setPlaincode(String plaincode) {
        this.plaincode = plaincode;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getRegioncode() {
        return regioncode;
    }

    public void setRegioncode(String regioncode) {
        this.regioncode = regioncode;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getStreetcode() {
        return streetcode;
    }

    public void setStreetcode(String streetcode) {
        this.streetcode = streetcode;
    }

    public String getCtarcode() {
        return ctarcode;
    }

    public void setCtarcode(String ctarcode) {
        this.ctarcode = ctarcode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FiasAddrobj)) {
            return false;
        }
        FiasAddrobj other = (FiasAddrobj) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.baltinfo.model.dto.FiasAddrobj[ aoguid=" + aoguid + " ]";
    }

}
