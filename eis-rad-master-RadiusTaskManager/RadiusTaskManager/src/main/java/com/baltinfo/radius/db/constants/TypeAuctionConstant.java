package com.baltinfo.radius.db.constants;

import java.util.Arrays;
import java.util.Optional;

/**
 * <p>
 * Вид торгов для загрузки из шаблона
 * </p>
 *
 * @author Lapenok Igor
 * @since 16.08.2018
 */
public enum TypeAuctionConstant {
    OPEN_AUCTION("аукцион с открытой формой подачи предложений", TypeAuctionUnid.AUCTION, TypeAuctionCode.AUCTION.getCode(), AuctionStepFormConstant.OPEN),
    CLOSE_AUCTION("аукцион с закрытой формой подачи предложений", TypeAuctionUnid.AUCTION, TypeAuctionCode.AUCTION.getCode(), AuctionStepFormConstant.CLOSE),
    OPEN_CONCURS_AUCTION("конкурс с открытой формой подачи предложений", TypeAuctionUnid.CONTEST_ENGLISH, TypeAuctionCode.AUCTION.getCode(), AuctionStepFormConstant.OPEN),
    OPEN_CONCURS("конкурс с открытой формой подачи предложений", TypeAuctionUnid.CONTEST, TypeAuctionCode.COMPETITION.getCode(), AuctionStepFormConstant.OPEN),
    CLOSE_CONCURS("конкурс с закрытой формой подачи предложений", TypeAuctionUnid.CONTEST, TypeAuctionCode.COMPETITION.getCode(), AuctionStepFormConstant.CLOSE),
    PUBLIC("продажа посредством публичного предложения", TypeAuctionUnid.PUBLIC, TypeAuctionCode.PUBLIC_SALE.getCode(), AuctionStepFormConstant.OPEN),
    HOLLAND("голландский аукцион", TypeAuctionUnid.HOLLAND, TypeAuctionCode.HOLLAND.getCode(), AuctionStepFormConstant.OPEN);

    private final String nameInTemplate;
    private final Long radiusTypeAuctionUnid;
    private final Long radiusTypeAuctionCode;
    private final AuctionStepFormConstant radiusStepForm;

    TypeAuctionConstant(String nameInTemplate, Long radiusTypeAuctionUnid, Long radiusTypeAuctionCode, AuctionStepFormConstant radiusStepForm) {
        this.nameInTemplate = nameInTemplate;
        this.radiusTypeAuctionUnid = radiusTypeAuctionUnid;
        this.radiusTypeAuctionCode = radiusTypeAuctionCode;
        this.radiusStepForm = radiusStepForm;
    }

    /**
     * Возвращает наименование вида торгов, как указано в шаблоне
     *
     * @return наименование вида торгов, как указано в шаблоне
     */
    public String getNameInTemplate() {
        return nameInTemplate;
    }

    /**
     * Возвращает идентификатор вида торгов в ЕИС РАД
     *
     * @return идентификатор вида торгов в ЕИС РАД
     */
    public Long getRadiusTypeAuctionCode() {
        return radiusTypeAuctionCode;
    }

    /**
     * Возвращает форму подачи предложений для ЕИС РАД
     *
     * @return форма подачи предложений для ЕИС РАД
     */
    public AuctionStepFormConstant getRadiusStepForm() {
        return radiusStepForm;
    }

    public Long getRadiusTypeAuctionUnid() {
        return radiusTypeAuctionUnid;
    }

    /**
     * Возвращает {@link Optional<TypeAuctionConstant>} по наименованию вида торгов, как указано в шаблоне
     *
     * @param name наименование вида торгов, как указано в шаблоне
     * @return {@link Optional<TypeAuctionConstant>}
     */
    public static Optional<TypeAuctionConstant> getByNameInTemplate(String name) {
        if (name == null || name.isEmpty()) {
            return Optional.empty();
        }
        return Arrays.stream(values())
                .filter(x -> x.nameInTemplate.equals(name.toLowerCase().trim()))
                .findFirst();
    }
}
