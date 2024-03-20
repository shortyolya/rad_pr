package com.baltinfo.radius.db.constants;

/**
 * @author Suvorina Aleksandra
 * @since 26.08.2019
 */
public enum LotOnlineProtocol {

    PARTICIPATION_PURCHASES_PROTOCOL,
    PARTICIPATION_REQUEST_PROTOCOL,
    NOT_TAKE_PLACE_PROTOCOL,
    PRIVATIZATION_NOT_TAKE_PLACE_PROTOCOL,
    PRIVATIZATION_PARTICIPATION_PROCESSING_PROTOCOL,
    AUCTION_PROTOCOL,
    PRIV_PUBL_PARTICIPATION_PROCESSING_PROTOCOL,
    PRIVATIZATION_RESULT_PROTOCOL,
    PRIVATIZATION_PUBL_RESULT_PROTOCOL,
    PRIVATIZATION_PARTICIPATION_NO_REQUEST_PROTOCOL,
    PRIVATIZATION_RESULT_SINGLE_PART_PROTOCOL,
    CONTEST_PROTOCOL,
    COMMERCIAL_OFFERS_PROTOCOL,
    NOT_TAKE_PLACE_CONTEST_PROTOCOL,
    NOT_TAKE_PLACE_COMMERCIAL_OFFERS_PROTOCOL,
    FISH_NOT_TAKE_PLACE_PROTOCOL,
    FISH_RESULT_PROTOCOL,
    FISH_PUBL_RESULT_PROTOCOL,
    LAND_NOT_TAKE_PLACE_PROTOCOL,
    LAND_RESULT_PROTOCOL,
    WOOD_NOT_TAKE_PLACE_PROTOCOL,
    CONFISCATE_NOT_TAKE_PLACE_PROTOCOL,
    ARRESTED_NOT_TAKE_PLACE_PROTOCOL,
    WOOD_RESULT_PROTOCOL,
    CONFISCATE_RESULT_PROTOCOL,
    ARRESTED_RESULT_PROTOCOL,
    LEASE_NOT_TAKE_PLACE_PROTOCOL,
    LEASE_RESULT_PROTOCOL,
    LEASE_PUBL_RESULT_PROTOCOL,
    PP1906_NOT_TAKE_PLACE_PROTOCOL,
    PP1906_RESULT_PROTOCOL,
    LEASE_PARTICIPATION_NO_REQUEST_PROTOCOL,
    LEASE_PARTICIPATION_PROCESSING_PROTOCOL,
    LEASE_PUBL_PARTICIPATION_PROCESSING_PROTOCOL,
    LAND_PARTICIPATION_NO_REQUEST_PROTOCOL,
    LAND_PARTICIPATION_PROCESSING_PROTOCOL,
    WOOD_PARTICIPATION_NO_REQUEST_PROTOCOL,
    WOOD_PARTICIPATION_PROCESSING_PROTOCOL,
    FISH_PARTICIPATION_NO_REQUEST_PROTOCOL,
    FISH_PARTICIPATION_PROCESSING_PROTOCOL,
    FISH_PUBL_PARTICIPATION_PROCESSING_PROTOCOL;

    public static boolean isSummingUpProtocol(String code) {
        return AUCTION_PROTOCOL.name().equals(code) ||
                CONTEST_PROTOCOL.name().equals(code) ||
                COMMERCIAL_OFFERS_PROTOCOL.name().equals(code) ||
                NOT_TAKE_PLACE_PROTOCOL.name().equals(code) ||
                NOT_TAKE_PLACE_CONTEST_PROTOCOL.name().equals(code) ||
                NOT_TAKE_PLACE_COMMERCIAL_OFFERS_PROTOCOL.name().equals(code) ||
                PRIVATIZATION_NOT_TAKE_PLACE_PROTOCOL.name().equals(code) ||
                PRIVATIZATION_RESULT_PROTOCOL.name().equals(code) ||
                PRIVATIZATION_PUBL_RESULT_PROTOCOL.name().equals(code) ||
                FISH_NOT_TAKE_PLACE_PROTOCOL.name().equals(code) ||
                FISH_RESULT_PROTOCOL.name().equals(code) ||
                FISH_PUBL_RESULT_PROTOCOL.name().equals(code) ||
                LAND_NOT_TAKE_PLACE_PROTOCOL.name().equals(code) ||
                LAND_RESULT_PROTOCOL.name().equals(code) ||
                WOOD_NOT_TAKE_PLACE_PROTOCOL.name().equals(code) ||
                CONFISCATE_NOT_TAKE_PLACE_PROTOCOL.name().equals(code) ||
                ARRESTED_NOT_TAKE_PLACE_PROTOCOL.name().equals(code) ||
                WOOD_RESULT_PROTOCOL.name().equals(code) ||
                CONFISCATE_RESULT_PROTOCOL.name().equals(code) ||
                ARRESTED_RESULT_PROTOCOL.name().equals(code) ||
                LEASE_NOT_TAKE_PLACE_PROTOCOL.name().equals(code) ||
                LEASE_RESULT_PROTOCOL.name().equals(code) ||
                LEASE_PUBL_RESULT_PROTOCOL.name().equals(code) ||
                PP1906_NOT_TAKE_PLACE_PROTOCOL.name().equals(code) ||
                PP1906_RESULT_PROTOCOL.name().equals(code) ||
                PRIVATIZATION_RESULT_SINGLE_PART_PROTOCOL.name().equals(code);
    }

    public static boolean isParticipationProtocol(String code) {
        return PARTICIPATION_REQUEST_PROTOCOL.name().equals(code) ||
                PARTICIPATION_PURCHASES_PROTOCOL.name().equals(code) ||
                PRIVATIZATION_PARTICIPATION_NO_REQUEST_PROTOCOL.name().equals(code) ||
                PRIVATIZATION_PARTICIPATION_PROCESSING_PROTOCOL.name().equals(code) ||
                LEASE_PARTICIPATION_NO_REQUEST_PROTOCOL.name().equals(code) ||
                LEASE_PARTICIPATION_PROCESSING_PROTOCOL.name().equals(code) ||
                LEASE_PUBL_PARTICIPATION_PROCESSING_PROTOCOL.name().equals(code) ||
                LAND_PARTICIPATION_NO_REQUEST_PROTOCOL.name().equals(code) ||
                LAND_PARTICIPATION_PROCESSING_PROTOCOL.name().equals(code) ||
                WOOD_PARTICIPATION_NO_REQUEST_PROTOCOL.name().equals(code) ||
                WOOD_PARTICIPATION_PROCESSING_PROTOCOL.name().equals(code) ||
                FISH_PARTICIPATION_NO_REQUEST_PROTOCOL.name().equals(code) ||
                FISH_PARTICIPATION_PROCESSING_PROTOCOL.name().equals(code) ||
                FISH_PUBL_PARTICIPATION_PROCESSING_PROTOCOL.name().equals(code) ||
                PRIV_PUBL_PARTICIPATION_PROCESSING_PROTOCOL.name().equals(code);
    }

}
