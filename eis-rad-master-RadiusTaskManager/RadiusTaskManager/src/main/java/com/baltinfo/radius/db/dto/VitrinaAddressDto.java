package com.baltinfo.radius.db.dto;

/**
 * @author Suvorina Aleksandra
 * @since 23.09.2021
 */
public class VitrinaAddressDto {

    private final String legalAddressFiasId;
    private final String postAddressFiasId;


    public VitrinaAddressDto(String legalAddressFiasId, String postAddressFiasId) {
        this.legalAddressFiasId = legalAddressFiasId;
        this.postAddressFiasId = postAddressFiasId;
    }

    public String getLegalAddressFiasId() {
        return legalAddressFiasId;
    }

    public String getPostAddressFiasId() {
        return postAddressFiasId;
    }
}
